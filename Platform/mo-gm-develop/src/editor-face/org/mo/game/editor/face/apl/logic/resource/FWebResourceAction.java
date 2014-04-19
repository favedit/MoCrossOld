package org.mo.game.editor.face.apl.logic.resource;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.IAttributes;
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

public class FWebResourceAction
      extends FAbstractWebResource
      implements
         IWebResourceAction
{

   @ALink
   protected IWebFormDatasetConsole _formDatasetConsole;

   public final String ACTION_DELETE = "delete";

   public final String ACTION_INSERT = "insert";

   public final String ACTION_SHOW = "show";

   public final String ACTION_UPDATE = "update";

   public final String PAGE_DIALOG = "Dialog";

   public final String PAGE_FORM = "Form";

   public final String ROW_STATUS = "_os";

   /* (non-Javadoc)
    * @see com.linekong.euis.face.apl.form.IWebFormAction#back(org.mo.web.protocol.context.IWebContext, org.mo.eng.data.common.ISqlContext, com.linekong.euis.face.apl.form.FWebFormPage)
    */
   @Override
   public String back(IWebContext context,
                      ISqlContext sqlContext,
                      FWebResourcePage page){
      return PAGE_FORM;
   }

   @SuppressWarnings("unused")
   @Override
   public String construct(IWebContext context,
                           ISqlContext sqlContext,
                           FWebResourcePage page){
      // 获取数据的关联信息
      String ouid = context.parameter("ouid");
      String over = context.parameter("over");
      String formName = context.parameter("form_name");
      IXmlObject xform = _webformConsole.get(formName);
      // 获得显示的表单
      if(XWebTable.isInstance(xform)){
         formName = xform.innerGet("form_name");
         xform = _webformConsole.get(formName);
      }
      // 获取关联的资源标识
      String dataset = xform.innerGet("dataset");
      if(RString.isEmpty(dataset)){
         throw new FFatalError("Resource dataset is null. (form={0})", formName);
      }
      //      FSqlProcedure procedure = rsResourceDi.makeResourceId(null, null, null, dataset, ouid);
      //      String resourceId = procedure.parameter("resource_id_").asString();
      //      page.setResourceId(resourceId);
      //      // 设置资源的输出信息
      //      page.setFormName(formName);
      //      page.setOuid(ouid);
      //      page.setOver(over);
      return "Resource";
   }

   /* (non-Javadoc)
    * @see com.linekong.euis.face.apl.form.IWebFormAction#delete(org.mo.web.protocol.context.IWebContext, org.mo.eng.data.common.ISqlContext, com.linekong.euis.face.apl.form.FWebFormPage)
    */
   @Override
   public String delete(IWebContext context,
                        ISqlContext sqlContext,
                        FWebResourcePage page){
      // 设置环境
      setFormEnv(context, sqlContext, page);
      // Attribute
      String formName = page.getFormName();
      IXmlObject xform = findForm(formName);
      // 设置要操作的记录的标识和版本
      FWebFormDatasetArgs args = new FWebFormDatasetArgs(context, sqlContext);
      args.setFormName(formName);
      FAttributes search = new FAttributes();
      search.unpack(page.getFormSearch());
      args.search().appendSearch(search);
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
      return PAGE_FORM;
   }

   /* (non-Javadoc)
    * @see com.linekong.euis.face.apl.form.IWebFormAction#insert(org.mo.web.protocol.context.IWebContext, org.mo.eng.data.common.ISqlContext, com.linekong.euis.face.apl.form.FWebFormPage)
    */
   @Override
   public String insert(IWebContext context,
                        ISqlContext sqlContext,
                        FWebResourcePage page){
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
                      FWebResourcePage page){
      return "List";
   }

   private void setFormEnv(IWebContext context,
                           ISqlContext sqlContext,
                           FWebResourcePage page){
      // 设置环境
      setEnvironment(context, page);
      // 设置表单的设置
      FXmlNode formNode = _webformConsole.build(page.getFormName(), EXmlConfig.Simple);
      page.setFormConfig(formNode.xml());
   }

   /* (non-Javadoc)
    * @see com.linekong.euis.face.apl.form.IWebFormAction#show(org.mo.web.protocol.context.IWebContext, org.mo.eng.data.common.ISqlContext, com.linekong.euis.face.apl.form.FWebFormPage)
    */
   //   @Override
   //   public String show(IWebContext context,
   //                      ISqlContext sqlContext,
   //                      FWebResourcePage page){
   //      // 设置环境
   //      setFormEnv(context, sqlContext, page);
   //      // 设置表单的数据
   //      IXmlObject xform = findForm(page.getFormName());
   //      String formParent = page.getFormParent();
   //      FAttributes search = new FAttributes();
   //      if(RString.isNotEmpty(formParent)){
   //         search.unpack(formParent);
   //      }
   //      FWebFormDatasetArgs args = new FWebFormDatasetArgs(sqlContext);
   //      args.search().appendSearch(search);
   //      args.setForm(xform);
   //      if(XWebTable.isInstance(xform)){
   //         FXmlNode resultNode = new FXmlNode(RXml.DEFAULT_ROOT_NAME);
   //         FXmlNode dsNode = _formDatasetConsole.fetch(args);
   //         resultNode.push(dsNode);
   //         page.setFormValue(resultNode.xml());
   //      }else if(XWebForm.isInstance(xform)){
   //         search.clear();
   //         search.set("ouid", page.getOuid());
   //         search.set("over", page.getOver());
   //         FXmlNode resultNode = _formDatasetConsole.fetchAll(args);
   //         resultNode.setName(RXml.DEFAULT_ROOT_NAME);
   //         page.setFormValue(resultNode.xml());
   //      }
   //      // 显示表单
   //      return PAGE_FORM;
   //   }
   /* (non-Javadoc)
    * @see com.linekong.euis.face.apl.form.IWebFormAction#update(org.mo.web.protocol.context.IWebContext, org.mo.eng.data.common.ISqlContext, com.linekong.euis.face.apl.form.FWebFormPage)
    */
   @Override
   public String update(IWebContext context,
                        ISqlContext sqlContext,
                        FWebResourcePage page){
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

   /* (non-Javadoc)
    * @see com.linekong.euis.face.apl.logic.form.IWebFormAction#zoom(org.mo.web.protocol.context.IWebContext, org.mo.eng.data.common.ISqlContext, com.linekong.euis.face.apl.logic.form.FWebFormPage)
    */
   @Override
   public String zoom(IWebContext context,
                      ISqlContext sqlContext,
                      FWebResourcePage page){
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
      return PAGE_DIALOG;
   }
}
