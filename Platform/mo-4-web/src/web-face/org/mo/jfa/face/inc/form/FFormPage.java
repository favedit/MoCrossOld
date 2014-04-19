package org.mo.jfa.face.inc.form;

import org.mo.com.collections.FAttributesList;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FObjectId;
import org.mo.com.lang.FString;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;

public class FFormPage
      extends FObjectId
{
   private String _action;

   private final FXmlNode _environment = new FXmlNode("Environment");

   private IXmlObject _form;

   private FString _formConfig;

   private FString _formDataset;

   private String _formName;

   private FString _formValue;

   private final FAttributes _item = new FAttributes();

   private final FAttributesList _list = new FAttributesList();

   public String getAction(){
      return _action;
   }

   public FString getEnvironment(){
      return _environment.xml();
   }

   public IXmlObject getForm(){
      return _form;
   }

   public FString getFormConfig(){
      return _formConfig;
   }

   public FString getFormDataset(){
      return _formDataset;
   }

   public String getFormName(){
      return _formName;
   }

   public FString getFormValue(){
      return _formValue;
   }

   public FAttributes getItem(){
      return _item;
   }

   public FAttributesList getList(){
      return _list;
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

   public void setFormConfig(FString config){
      _formConfig = config;
   }

   public void setFormDataset(FString dataset){
      _formDataset = dataset;
   }

   public void setFormName(String formName){
      _formName = formName;
   }

   public void setFormValue(FString value){
      _formValue = value;
   }

}
