package org.mo.game.editor.web.apl.form;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.RXml;
import org.mo.core.aop.face.ALink;
import org.mo.eng.data.common.ISqlContext;
import org.mo.game.editor.face.apl.logic.form.FWebFormAction;
import org.mo.game.editor.web.session.FUserSessionArgs;
import org.mo.game.editor.web.session.FUserSessionPage;
import org.mo.game.editor.web.session.IUserSessionConsole;
import org.mo.logic.data.ISyEncryptionParameterDi;
import org.mo.logic.data.impl.FSyEncryptionParameterImpl;
import org.mo.logic.session.ISqlSessionContext;
import org.mo.web.core.session.IWebSession;
import org.mo.web.core.webform.FWebFormDatasetArgs;
import org.mo.web.core.webform.IWebFormDatasetConsole;
import org.mo.web.core.webform.control.XWebForm;
import org.mo.web.core.webform.control.XWebTable;
import org.mo.web.protocol.context.IWebContext;

public class FAbstractWebFormAction
      extends FAbstractCommon{

   private static ILogger _logger = RLogger.find(FWebFormAction.class);

   @ALink
   protected IWebFormDatasetConsole _formDatasetConsole;

   @ALink
   protected IUserSessionConsole _userConsole;

   public final String ACTION_DELETE = "delete";

   public final String ACTION_DISPLAY = "display";

   public final String ACTION_INSERT = "insert";

   public final String ACTION_UPDATE = "update";

   public final String ROW_STATUS = "_os";

   public String back(IWebContext context,
                      ISqlSessionContext sqlContext,
                      FAbstractWebFormPage page,
                      String redirect){
      // 获得表单信息
      FWebFormInfo info = page.formInfos().back();
      page.restoreInfo(info);
      // 执行当次的操作 
      String action = info.getAction();
      if(ACTION_DISPLAY.equals(action)){
         display(context, sqlContext, page, redirect);
      }else if(ACTION_INSERT.equals(action)){
         insert(context, sqlContext, page, redirect);
      }else if(ACTION_UPDATE.equals(action)){
         update(context, sqlContext, page, redirect);
      }else if(ACTION_DELETE.equals(action)){
         delete(context, sqlContext, page, redirect);
      }else{
         throw new FFatalError("Unknown action. (action={0})", action);
      }
      return redirect;
   }

   public String delete(IWebContext context,
                        ISqlSessionContext sqlContext,
                        FAbstractWebFormPage page,
                        String redirect){
      // 设置环境
      setFormEnv(context, sqlContext, page);
      // Attribute
      String formName = page.getFormName();
      IXmlObject xform = findForm(formName);
      FAttributes search = new FAttributes();
      search.unpack(page.getFormSearch());
      // 设置要操作的记录的标识和版本
      FWebFormDatasetArgs args = new FWebFormDatasetArgs(context, sqlContext);
      args.setFormName(formName);
      args.setForm(xform);
      args.setLogic(null);
      args.search().appendSearch(search);
      args.parentParameters().unpack(page.getFormParameters());
      if(XWebForm.isInstance(xform)){
         FXmlNode dsNodes = _formDatasetConsole.fetchNodeAll(args);
         if(null != dsNodes && dsNodes.hasNode()){
            for(FXmlNode dsNode : dsNodes){
               if(null != dsNode && dsNode.hasNode()){
                  for(FXmlNode rowNode : dsNode){
                     rowNode.set(ROW_STATUS, "D");
                  }
               }
            }
         }
         page.setFormValue(dsNodes.xml());
      }else{
         throw new FFatalError("Invalid form type. (form={0}, type={1})", formName, xform.name());
      }
      return redirect;
   }

   public String display(IWebContext context,
                         ISqlSessionContext sqlContext,
                         FAbstractWebFormPage page,
                         String redirect){
      // 设置环境
      setFormEnv(context, sqlContext, page);
      // 设置表单的数据
      IXmlObject xform = findForm(page.getFormName());
      String formParent = page.getFormParent();
      FAttributes search = new FAttributes();
      if(RString.isNotEmpty(formParent)){
         search.unpack(formParent);
      }
      // 连接显示数据
      FWebFormDatasetArgs args = new FWebFormDatasetArgs(context, sqlContext);
      args.setForm(xform);
      args.search().appendSearch(search);
      args.parentParameters().unpack(page.getFormParameters());
      if(XWebTable.isInstance(xform)){
         FXmlNode resultNode = new FXmlNode(RXml.DEFAULT_ROOT_NAME);
         FXmlNode dsNode = _formDatasetConsole.fetchNode(args);
         resultNode.push(dsNode);
         page.setFormValue(resultNode.xml());
      }else if(XWebForm.isInstance(xform)){
         search.clear();
         search.set("ouid", page.getOuid());
         search.set("over", page.getOver());
         FXmlNode resultNode = _formDatasetConsole.fetchNodeAll(args);
         resultNode.setName(RXml.DEFAULT_ROOT_NAME);
         page.setFormValue(resultNode.xml());
      }
      // 显示表单
      return redirect;
   }

   public String insert(IWebContext context,
                        ISqlSessionContext sqlContext,
                        FAbstractWebFormPage page,
                        String redirect){
      // 设置环境
      setFormEnv(context, sqlContext, page);
      // Attribute
      String formName = page.getFormName();
      IXmlObject xform = findForm(formName);
      if(XWebTable.isInstance(xform)){
         throw new FFatalError("Invalid form type. (type={0},name={1})", XWebTable.NAME, page.getFormName());
      }else if(XWebForm.isInstance(xform)){
         // 创建数据行对象
         FXmlNode resultNode = new FXmlNode(RXml.DEFAULT_ROOT_NAME);
         FXmlNode dsNode = resultNode.createNode(FDataset.NAME);
         dsNode.set("name", formName);
         FXmlNode rowNode = dsNode.createNode(FRow.NAME);
         String formParent = page.getFormParent();
         rowNode.attributes().unpack(formParent);
         // 建立新建用的数据集
         FWebFormDatasetArgs args = new FWebFormDatasetArgs(context, sqlContext);
         args.setForm(xform);
         args.setLogic(null);
         args.setValues(rowNode.attributes());
         args.parentParameters().unpack(page.getFormParameters());
         _formDatasetConsole.prepare(args);
         // 修正新建数据
         //rowNode.attributes().unpack(formParent, true);
         rowNode.set(ROW_STATUS, "I");
         page.setFormValue(resultNode.xml());
      }
      return redirect;
   }

   public String list(IWebContext context,
                      ISqlSessionContext sqlContext,
                      FAbstractWebFormPage page,
                      String redirect){
      return redirect;
   }

   protected FXmlNode parseBusinessNode(String source){
      FXmlNode node = null;
      if(RString.isNotEmpty(source)){
         node = new FXmlNode();
         node.attributes().unpack(source);
      }
      return node;
   }

   protected FXmlNodes parseBusinessNodes(String source){
      FXmlNodes nodes = null;
      if(RString.isNotEmpty(source)){
         nodes = new FXmlNodes();
         FStrings lines = new FStrings();
         lines.unpack(source);
         for(String line : lines){
            nodes.create().attributes().unpack(line);
         }
      }
      return nodes;
   }

   protected String redirect(IWebContext context,
                             ISqlContext sqlContext,
                             FUserSessionPage sessionPage,
                             FAbstractWebFormPage formPage,
                             String defaultRedirect){
      // 获得线程信息
      IWebSession session = context.session();
      // 获得转向数据
      String redirect = context.parameter("flag");
      ISyEncryptionParameterDi syEncryptionParameterDi = new FSyEncryptionParameterImpl(sqlContext);
      String parameterPack = syEncryptionParameterDi.findParametersPack(redirect).returnAsString();
      if(RString.isEmpty(parameterPack)){
         throw new FFatalError("Unknown redirect. (redirect={0})", redirect);
      }
      IAttributes parameters = new FAttributes();
      parameters.unpack(parameterPack);
      _logger.debug(this, "redirect", "Redirect parameters {0}", parameters.dump());
      // 判断当前用户是否已经登录
      if(!session.user().isLogin()){
         // 如果没有自动登录的标识，则转到用户登录画面
         String loginId = parameters.get("login_id");
         if(RString.isEmpty(loginId)){
            return "/apl/login/Login.wa";
         }
         // 用户快速登录
         FUserSessionArgs sessionArgs = new FUserSessionArgs();
         sessionArgs.setSession(context.session());
         sessionArgs.setSqlContext(sqlContext);
         sessionArgs.setLoginId(loginId);
         sessionArgs.setViewName("Company");
         sessionArgs.setCode(session.storeId());
         sessionArgs.setSessionPage(sessionPage);
         _userConsole.loginDirect(sessionArgs);
      }
      // 将参数追加到页面参数上
      context.parameters().append(parameters);
      //      // 获得页面数据
      //      String formName = parameters.get("form_name");
      //      String formParent = parameters.get("form_parent");
      //      String ouid = parameters.get("ouid");
      //      // 设置表单的数据
      //      IXmlObject xform = findForm(formName);
      //      formPage.setFormName(formName);
      //      FAttributes search = new FAttributes();
      //      if(RString.isNotEmpty(formParent)){
      //         search.unpack(formParent);
      //      }
      //      // 连接显示数据
      //      FWebFormDatasetArgs args = new FWebFormDatasetArgs(context, sqlContext);
      //      args.setValues(parameters);
      //      args.setSessionConnectId(context.session().connectId());
      //      args.sessionParameters().append(parameters);
      //      // 显示数据
      //      args.setForm(xform);
      //      if(XWebTable.isInstance(xform)){
      //         FXmlNode resultNode = new FXmlNode(RXml.DEFAULT_ROOT_NAME);
      //         args.search().appendSearch(search);
      //         FXmlNode dsNode = _formDatasetConsole.fetchNode(args);
      //         resultNode.push(dsNode);
      //         formPage.setFormValue(resultNode.xml());
      //      }else if(XWebForm.isInstance(xform)){
      //         search.clear();
      //         search.set("ouid", ouid);
      //         args.search().appendSearch(search);
      //         FXmlNode resultNode = _formDatasetConsole.fetchNodeAll(args);
      //         resultNode.setName(RXml.DEFAULT_ROOT_NAME);
      //         formPage.setFormValue(resultNode.xml());
      //      }
      // 显示表单
      return defaultRedirect;
   }

   protected void setFormEnv(IWebContext context,
                             ISqlContext sqlContext,
                             FAbstractWebFormPage page){
      // 设置环境
      setEnvironment(context, page);
      // 设置表单的设置
      FXmlNode formNode = _webformConsole.build(page.getFormName(), EXmlConfig.Simple);
      page.setFormConfig(formNode.xml());
      //FXmlNode eventNode = _webformConsole.buildEvent(page.getFormName());
      //page.setFormEvent(eventNode.xml().toString());
   }

   public String update(IWebContext context,
                        ISqlSessionContext sqlContext,
                        FAbstractWebFormPage page,
                        String redirect){
      // 设置环境
      setFormEnv(context, sqlContext, page);
      return redirect;

   }

   public String zoom(IWebContext context,
                      ISqlSessionContext sqlContext,
                      FAbstractWebFormPage page,
                      String redirect){
      // 设置环境
      setFormEnv(context, sqlContext, page);
      // 设置表单的数据
      String formName = page.getFormName();
      IXmlObject xform = _webformConsole.get(formName);
      if(XWebForm.isInstance(xform)){
         // 建立搜索信息
         IAttributes search = new FAttributes();
         search.set("ouid", page.getOuid());
         // 搜索数据
         FWebFormDatasetArgs args = new FWebFormDatasetArgs(context, sqlContext);
         args.setFormName(formName);
         args.search().appendSearch(search);
         FXmlNode resultNode = _formDatasetConsole.fetchNodeAll(args);
         resultNode.setName(RXml.DEFAULT_ROOT_NAME);
         page.setFormValue(resultNode.xml());
      }else{
         throw new FFatalError("Zoom fowm can't is table form. (name={0})", formName);
      }
      page.setAction(ACTION_UPDATE);
      // 显示表单
      return redirect;
   }
}
