package org.mo.jfa.face.logic.webform;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.RXml;
import org.mo.core.aop.face.ALink;
import org.mo.eng.data.common.ISqlContext;
import org.mo.web.core.webform.FWebFormDatasetArgs;
import org.mo.web.core.webform.IWebFormDatasetConsole;
import org.mo.web.core.webform.control.XWebForm;
import org.mo.web.core.webform.control.XWebTable;
import org.mo.web.protocol.context.IWebContext;

public class FWebFormAction
      extends FAbstractCommon
      implements
         IWebFormAction
{

   @ALink
   protected IWebFormDatasetConsole _formDatasetConsole;

   public final String PAGE_FORM = "Form";

   public final String ROW_STATUS = "_os";

   /* (non-Javadoc)
    * @see com.linekong.euis.face.apl.form.IWebFormAction#back(org.mo.web.protocol.context.IWebContext, org.mo.eng.data.common.ISqlContext, com.linekong.euis.face.apl.form.FWebFormPage)
    */
   @Override
   public String back(IWebContext context,
                      ISqlContext sqlContext,
                      FWebFormPage page){
      // 获得表单信息
      FWebFormInfo info = page.formInfos().back();
      page.restoreInfo(info);
      // 执行当次的操作
      String action = info.getAction();
      disableEnvOnce();
      if("show".equals(action)){
         show(context, sqlContext, page);
      }else if("insert".equals(action)){
         insert(context, sqlContext, page);
      }else if("update".equals(action)){
         update(context, sqlContext, page);
      }else if("delete".equals(action)){
         delete(context, sqlContext, page);
      }else{
         throw new FFatalError("Unknown action. (action={0})", action);
      }
      return PAGE_FORM;
   }

   /* (non-Javadoc)
    * @see com.linekong.euis.face.apl.form.IWebFormAction#delete(org.mo.web.protocol.context.IWebContext, org.mo.eng.data.common.ISqlContext, com.linekong.euis.face.apl.form.FWebFormPage)
    */
   @Override
   public String delete(IWebContext context,
                        ISqlContext sqlContext,
                        FWebFormPage page){
      // 设置环境
      setFormEnv(context, sqlContext, page);
      // Attribute
      String formName = page.getFormName();
      IXmlObject xform = findForm(formName);
      // 设置要操作的记录的标识和版本
      FWebFormDatasetArgs args = new FWebFormDatasetArgs(context, sqlContext);
      args.setForm(xform);
      FAttributes search = new FAttributes();
      search.unpack(page.getFormSearch());
      args.search().appendSearch(search);
      if(XWebForm.isInstance(xform)){
         FXmlNode resultNode = new FXmlNode(RXml.DEFAULT_ROOT_NAME);
         FXmlNode dsNode = _formDatasetConsole.fetchNode(args);
         for(FXmlNode rowNode : dsNode){
            rowNode.set(ROW_STATUS, "D");
         }
         resultNode.push(dsNode);
         page.setFormValue(resultNode.xml());
      }else{
         throw new FFatalError("Invalid form type. (form={0}, type={1})", formName, xform.name());
      }
      return PAGE_FORM;
   }

   /* (non-Javadoc)
    * @see com.linekong.euis.face.apl.form.IWebFormAction#insert(org.mo.web.protocol.context.IWebContext, org.mo.eng.data.common.ISqlContext, com.linekong.euis.face.apl.form.FWebFormPage)
    */
   @Override
   public String insert(IWebContext context,
                        ISqlContext sqlContext,
                        FWebFormPage page){
      // 设置环境
      setFormEnv(context, sqlContext, page);
      // Attribute
      String formName = page.getFormName();
      IXmlObject xform = _webformConsole.get(formName);
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
         args.setFormName(formName);
         args.setForm(xform);
         args.setLogic(null);
         args.setValues(rowNode.attributes());
         _formDatasetConsole.prepare(args);
         // 修正新建数据
         //rowNode.attributes().unpack(formParent, true);
         rowNode.set(ROW_STATUS, "I");
         page.setFormValue(resultNode.xml());
      }
      return PAGE_FORM;
   }

   @Override
   public String list(IWebContext context,
                      ISqlContext sqlContext,
                      FWebFormPage page){
      return "List";
   }

   private void setFormEnv(IWebContext context,
                           ISqlContext sqlContext,
                           FWebFormPage page){
      // 设置环境
      setEnvironment(context, page);
      // 设置表单的设置
      FXmlNode formNode = _webformConsole.build(page.getFormName(), EXmlConfig.Simple);
      page.setFormConfig(formNode.xml());
   }

   /* (non-Javadoc)
    * @see com.linekong.euis.face.apl.form.IWebFormAction#show(org.mo.web.protocol.context.IWebContext, org.mo.eng.data.common.ISqlContext, com.linekong.euis.face.apl.form.FWebFormPage)
    */
   @Override
   public String show(IWebContext context,
                      ISqlContext sqlContext,
                      FWebFormPage page){
      // 设置环境
      setFormEnv(context, sqlContext, page);
      // 设置表单的数据
      IXmlObject xform = findForm(page.getFormName());
      String formParent = page.getFormParent();
      FAttributes search = new FAttributes();
      if(RString.isNotEmpty(formParent)){
         search.unpack(formParent);
      }
      FWebFormDatasetArgs args = new FWebFormDatasetArgs(context, sqlContext);
      args.search().appendSearch(search);
      args.setForm(xform);
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
      return PAGE_FORM;
   }

   /* (non-Javadoc)
    * @see com.linekong.euis.face.apl.form.IWebFormAction#update(org.mo.web.protocol.context.IWebContext, org.mo.eng.data.common.ISqlContext, com.linekong.euis.face.apl.form.FWebFormPage)
    */
   @Override
   public String update(IWebContext context,
                        ISqlContext sqlContext,
                        FWebFormPage page){
      // 设置环境
      setFormEnv(context, sqlContext, page);
      // 获取参数信息
      String formName = page.getFormName();
      IXmlObject xform = findForm(formName);
      // 设置要操作的记录的标识和版本
      FWebFormDatasetArgs args = new FWebFormDatasetArgs(context, sqlContext);
      FAttributes search = new FAttributes();
      search.unpack(page.getFormSearch());
      args.search().appendSearch(search);
      args.setForm(xform);
      if(XWebForm.isInstance(xform)){
         FXmlNode valueNode = _formDatasetConsole.fetchNodeAll(args);
         page.setFormValue(valueNode.xml());
      }else{
         throw new FFatalError("Invalid form type. (form={0}, type={1})", formName, xform.name());
      }
      return PAGE_FORM;

   }

}
