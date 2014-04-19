package org.mo.jfa.common.page;

import java.io.Serializable;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FObjectId;
import org.mo.com.lang.FString;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.web.protocol.context.IWebContext;

//============================================================
// <T>表单页面基类。</T>
//============================================================
public abstract class FAbstractFormPage
      extends FObjectId
      implements
         Serializable
{
   // 序列化编号
   private static final long serialVersionUID = 1L;

   // 表单名称
   public final static String PTY_FORM_NAME = "form_name";

   // 表单服务
   public final static String PTY_FORM_SERVICE = "form_service";

   protected String _action;

   protected FXmlNode _environment = new FXmlNode("Environment");

   protected IXmlObject _form;

   private String _formAction;

   protected String _formConfig;

   protected String _formDataset;

   private String _formEvent;

   protected String _formName;

   protected String _formOrder;

   private String _formPack;

   private String _formParameters;

   protected String _formParent;

   protected String _formSearch;

   private String _formService;

   protected String _formValue;

   protected FXmlNode _result = new FXmlNode("Result");

   public void appachContext(IWebContext context){
      IAttributes parameters = context.parameters();
      // 填充数据
      String formName = parameters.get(PTY_FORM_NAME, null);
      if(RString.isNotEmpty(formName)){
         setFormName(formName);
      }
      String formService = parameters.get(PTY_FORM_SERVICE, null);
      if(RString.isNotEmpty(formService)){
         setFormService(formService);
      }
   }

   public FXmlNode commandsNode(){
      FXmlNode commands = _result.nodes().findNode("Commands");
      if(null == commands){
         commands = _result.nodes().create("Commands");
      }
      return commands;
   }

   public String getAction(){
      return _action;
   }

   public FString getEnvironment(){
      return _environment.xml();
   }

   public String getEnvironmentPack(){
      return _environment.attributes().pack();
   }

   public IXmlObject getForm(){
      return _form;
   }

   public String getFormAction(){
      return _formAction;
   }

   public String getFormConfig(){
      return _formConfig;
   }

   public String getFormDataset(){
      return _formDataset;
   }

   public String getFormEvent(){
      return _formEvent;
   }

   public String getFormName(){
      return _formName;
   }

   public String getFormOrder(){
      return _formOrder;
   }

   public String getFormPack(){
      return _formPack;
   }

   public String getFormParameters(){
      return _formParameters;
   }

   public String getFormParent(){
      return _formParent;
   }

   public String getFormSearch(){
      return _formSearch;
   }

   public String getFormService(){
      return _formService;
   }

   public String getFormValue(){
      return _formValue;
   }

   public FString getResultXml(){
      return _result.xml();
   }

   public IAttributes makeFormParent(){
      if(RString.isNotEmpty(_formParent)){
         FAttributes pack = new FAttributes();
         pack.unpack(_formParent);
         return pack;
      }
      return null;
   }

   public void reset(){
      _result.clear();
   }

   public void resetCommands(){
      commandsNode().clear();
   }

   public void resetFormValue(){
      _formValue = null;
   }

   public void setAction(String action){
      _action = action;
   }

   public void setEnv(String name,
                      String value){
      _environment.set(name, value);
   }

   public void setForm(IXmlObject form){
      _form = form;
   }

   public void setFormAction(String formAction){
      _formAction = formAction;
   }

   public void setFormConfig(FString config){
      _formConfig = config.toString();
   }

   public void setFormConfig(String config){
      _formConfig = config;
   }

   public void setFormDataset(String dataset){
      _formDataset = dataset;
   }

   public void setFormEvent(String formEvent){
      _formEvent = formEvent;
   }

   public void setFormName(String formName){
      _formName = formName;
   }

   public void setFormOrder(String order){
      _formOrder = order;
   }

   public void setFormPack(String formPack){
      _formPack = formPack;
   }

   public void setFormParameters(String formParameters){
      _formParameters = formParameters;
   }

   public void setFormParent(String parent){
      _formParent = parent;
   }

   public void setFormSearch(String search){
      _formSearch = search;
   }

   public void setFormService(String formService){
      _formService = formService;
   }

   public void setFormValue(FString value){
      _formValue = value.toString();
   }

   public void setFormValue(String value){
      _formValue = value;
   }

   public void setTreeParentRefresh(){
      FXmlNode command = commandsNode().createNode("command");
      command.set("name", "tree.parent.refresh");
   }

   public void setTreeRefresh(){
      FXmlNode command = commandsNode().createNode("command");
      command.set("name", "tree.node.refresh");
   }

}
