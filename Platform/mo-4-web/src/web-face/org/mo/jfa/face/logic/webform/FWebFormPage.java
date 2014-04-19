package org.mo.jfa.face.logic.webform;

import org.mo.jfa.common.page.FAbstractFormPage;

public class FWebFormPage
      extends FAbstractFormPage
{

   private static final long serialVersionUID = 1L;

   private FWebFormInfo _formInfo;

   private FWebFormInfos _formInfos = new FWebFormInfos();

   private String _ouid;

   private String _over;

   private String _selectControl;

   private String _selectForm;

   private String _selectType;

   private String _history;

   public FWebFormInfo formInfo(){
      return _formInfo;
   }

   public FWebFormInfos formInfos(){
      return _formInfos;
   }

   public String getOuid(){
      return _ouid;
   }

   public String getOver(){
      return _over;
   }

   public String getSelectControl(){
      return _selectControl;
   }

   public String getSelectForm(){
      return _selectForm;
   }

   public String getSelectType(){
      return _selectType;
   }

   public String history(){
      return _history;
   }

   public void restoreInfo(FWebFormInfo info){
      _action = info.getAction();
      _history = info.getHistory();
      _formName = info.getFormName();
      _formValue = info.getFormValue();
      _formSearch = info.getFormSearch();
      _formOrder = info.getFormOrder();
   }

   public void setFormInfo(FWebFormInfo info){
      _formInfo = info;
   }

   public void setHistory(String history){
      _history = history;
   }

   public void setOuid(String ouid){
      _ouid = ouid;
   }

   public void setOver(String over){
      _over = over;
   }

   public void setSelectControl(String control){
      _selectControl = control;
   }

   public void setSelectForm(String form){
      _selectForm = form;
   }

   public void setSelectType(String type){
      _selectType = type;
   }

   public void storeInfo(FWebFormInfo info){
      info.setAction(_action);
      info.setHistory(_history);
      info.setFormName(_formName);
      info.setFormValue(_formValue);
      info.setFormSearch(_formSearch);
      info.setFormOrder(_formOrder);
   }

}
