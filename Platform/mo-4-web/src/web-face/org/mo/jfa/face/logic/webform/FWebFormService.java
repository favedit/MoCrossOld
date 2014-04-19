package org.mo.jfa.face.logic.webform;

import org.mo.com.data.ESqlDataDirection;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.data.dataset.FDatasetConsole;
import org.mo.eng.data.common.ISqlContext;
import org.mo.eng.list.IListConsole;
import org.mo.eng.list.TListArgs;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.logic.session.ISqlSessionContext;
import org.mo.web.core.webform.IWebFormConsole;
import org.mo.web.core.webform.IWebFormDatasetConsole;
import org.mo.web.core.webform.TWebFormArgs;
import org.mo.web.protocol.common.IWebUser;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FWebFormService
      extends FAbsXmlObjectService<IXmlObject>
      implements
         IWebFormService
{

   private static ILogger _logger = RLogger.find(FDatasetConsole.class);

   public static String NAME_DATASET = "Dataset";

   public static String NAME_FORM = "Form";

   public static String NAME_CODE = "Code";

   public static String NAME_ROW = "Row";

   public static String PTY_CODE = "code";

   public final static String SQL_CODE_LIST = "code.list";

   @ALink
   protected IWebFormConsole _formConsole;

   @ALink
   protected IWebFormDatasetConsole _formDatasetConsole;

   @ALink
   protected IListConsole _listConsole;

   protected void buildEvent(IWebContext context,
                             FXmlNode eventsNode){
      for(FXmlNode eventNode : eventsNode){
         String code = eventNode.get(PTY_CODE);
         if(RString.isNotEmpty(code)){
            boolean changed = false;
            // 设置用户信息
            IWebUser user = context.session().user();
            if(null != user){
               String userId = user.userId();
               code = RString.replace(code, "${session.user_id}", userId);
               changed = true;
            }
            if(changed){
               eventNode.set(PTY_CODE, code);
            }
         }
      }
   }

   public void codeList(IWebContext context,
                        ISqlSessionContext sqlContext,
                        IWebInput input,
                        IWebOutput output){
      FXmlNode codeNode = input.config().findNode("CodeList");
      String name = codeNode.get("name");
      //      FWebFormDatasetArgs args = new FWebFormDatasetArgs(context, sqlContext);
      //      FXmlNode valueNode = codeNode.findNode("Values");
      //      args.setValues(valueNode.attributes());
      FXmlNode xlist = _listConsole.buildListConfig(new TListArgs(name, sqlContext));
      output.config().push(xlist);
   }

   protected IAttributes excuteProcedure(ISqlContext sqlContext,
                                         String pack,
                                         String produrce,
                                         IAttributes row,
                                         IAttributes codes){
      // 建立代码
      IAttributes params = new FAttributes();
      int count = row.count();
      for(int n = 0; n < count; n++){
         String name = row.name(n);
         String code = codes.get(name);
         params.set(RString.nvl(code, name), row.value(n));
      }
      // 执行处理
      FSqlProcedure procedure = new FSqlProcedure(produrce);
      procedure.setLogicName(pack);
      procedure.createParameter("logic_", null, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("params_", params, ESqlDataType.String, ESqlDataDirection.InOut);
      sqlContext.activeConnection().execute(procedure);
      IAttributes results = procedure.parameter("params_").asAttributes();
      return results;
   }

   public void list(IWebContext context,
                    ISqlSessionContext sqlContext,
                    IWebInput input,
                    IWebOutput output){

   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.webform.IWebFormService#loadDefine(org.mo.web.protocol.context.IWebContext, org.mo.eng.data.common.ISqlContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void loadDefine(IWebContext context,
                          ISqlContext sqlContext,
                          IWebInput input,
                          IWebOutput output){
      for(FXmlNode node : input.config()){
         if(node.isName("WebForm")){
            String formName = node.get("name");
            _logger.debug(this, "loadDefine", "Load form config (name={0})", formName);
            // 获得表单定义
            FXmlNode formConfig = _formConsole.buildConfig(new TWebFormArgs(formName, EXmlConfig.Simple, sqlContext, true));
            // 获得事件定义
            //FXmlNode eventNode = _formConsole.buildEvent(new TWebFormArgs(formName, EXmlConfig.Full, sqlContext));
            //if(eventNode.hasNode()){
            //buildEvent(context, eventNode);
            //}
            // 建立输出信息
            FXmlNode defineNode = new FXmlNode("Define");
            defineNode.push(formConfig);
            FXmlNode outputNode = output.config().createNode("WebForm");
            outputNode.attributes().assign(node.attributes());
            outputNode.push(defineNode);
            //outputNode.push(eventNode);
         }
      }
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.webform.IWebFormService#process(org.mo.web.protocol.context.IWebContext, org.mo.logic.session.ISqlSessionContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void process(IWebContext context,
                       ISqlSessionContext sqlContext,
                       IWebInput input,
                       IWebOutput output){
      FXmlNode formNode = input.config().findNode(NAME_FORM);
      String formName = formNode.get("name");
      String dataAction = formNode.get("data_action");
      FXmlNode codes = formNode.syncNode(NAME_CODE);
      FXmlNode outputNode = output.config().createNode(NAME_FORM);
      outputNode.attributes().assign(formNode.attributes());
      // 获得表单定义
      FXmlNode xform = _formConsole.findConfig(new TWebFormArgs(formName, EXmlConfig.Value, sqlContext));
      // 递归查找到控件 
      FXmlNode xAction = xform.findNode("name", dataAction);
      // ---------------------------------------------------------
      // 执行单条记录的处理
      String executeString = xAction.get("execute_action");
      if(!RString.isEmpty(executeString)){
         // 分解处理函数
         String[] executes = RString.split(executeString, '\n');
         // 每条记录执行数据库处理
         for(FXmlNode node : formNode){
            if(node.isName(NAME_ROW)){
               for(String execute : executes){
                  execute = RString.trim(execute);
                  if(RString.isNotEmpty(execute)){
                     // 执行单个数据库处理过程
                     String logicName = RString.left(execute, ".");
                     String produrceName = RString.left(RString.right(execute, "."), "(");
                     IAttributes results = excuteProcedure(sqlContext, logicName, produrceName, node.attributes(), codes.attributes());
                     // 建立输出节点信息
                     FXmlNode rowNode = new FXmlNode(NAME_ROW);
                     rowNode.attributes().assign(results);
                     outputNode.push(rowNode);
                  }
               }
            }
         }
      }
      // ---------------------------------------------------------
      // 如果含有最后执行过程的话
      String executeFinalString = xAction.get("execute_final_action");
      if(!RString.isEmpty(executeFinalString)){
         // 分解处理函数
         String[] executes = RString.split(executeFinalString, '\n');
         // 查找数据集行的信息
         FXmlNode attrNode = formNode.findNode(NAME_DATASET);
         if(null != attrNode){
            for(String execute : executes){
               execute = RString.trim(execute);
               if(RString.isNotEmpty(execute)){
                  // 执行单个数据库处理过程
                  String logicName = RString.left(execute, ".");
                  String produrceName = RString.left(RString.right(execute, "."), "(");
                  IAttributes results = excuteProcedure(sqlContext, logicName, produrceName, attrNode.attributes(), null);
                  // 建立输出节点信息
                  FXmlNode rowNode = new FXmlNode(NAME_ROW);
                  rowNode.attributes().assign(results);
                  outputNode.push(rowNode);
               }
            }
         }
      }
   }
}
