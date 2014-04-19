package org.mo.game.editor.web.apl.form;

import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNodes;
import org.mo.jfa.common.page.FAbstractFormPage;

public class FAbstractWebFormPage
      extends FAbstractFormPage{

   private static final long serialVersionUID = 1L;

   private FXmlNodes _examineFinalNodes;

   private FXmlNodes _examineFirstNodes;

   private FXmlNodes _examineManagerNodes;

   private FXmlNodes _examineNodes;

   private FWebFormInfo _formInfo;

   private FWebFormInfos _formInfos = new FWebFormInfos();

   private FWebFormInfosMap _formInfosMap = new FWebFormInfosMap();

   private String _history;

   private String _historyId;

   private String _ouid;

   private String _over;

   private String _pageValues;

   private String _selectControl;

   private String _selectForm;

   private String _selectType;

   public FXmlNodes examineFinalNodes(){
      return _examineFinalNodes;
   }

   public FXmlNodes examineManagerNodes(){
      return _examineManagerNodes;
   }

   public FXmlNodes examineNodes(){
      return _examineNodes;
   }

   public FWebFormInfo formInfo(){
      return _formInfo;
   }

   public FWebFormInfos formInfos(){
      if(RString.isNotEmpty(_historyId)){
         return _formInfosMap.sync(_historyId);
      }
      return _formInfos;
   }

   public FWebFormInfosMap formInfosMap(){
      return _formInfosMap;
   }

   public FXmlNodes getExamineFirstNodes(){
      return _examineFirstNodes;
   }

   public String getHistoryId(){
      return _historyId;
   }

   public String getOuid(){
      return _ouid;
   }

   public String getOver(){
      return _over;
   }

   public String getPageValues(){
      return _pageValues;
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

   public void setExamineFinalNodes(FXmlNodes examineFinalNodes){
      _examineFinalNodes = examineFinalNodes;
   }

   public void setExamineFirstNodes(FXmlNodes firstNodes){
      _examineFirstNodes = firstNodes;
   }

   public void setExamineManagerNode(FXmlNodes examineManagerNodes){
      _examineManagerNodes = examineManagerNodes;
   }

   public void setExamineNodes(FXmlNodes examineNodes){
      _examineNodes = examineNodes;
   }

   public void setFormInfo(FWebFormInfo info){
      _formInfo = info;
   }

   public void setHistory(String history){
      _history = history;
   }

   public void setHistoryId(String historyId){
      _historyId = historyId;
   }

   public void setOuid(String ouid){
      _ouid = ouid;
   }

   public void setOver(String over){
      _over = over;
   }

   public void setPageValues(String _pageValues){
      this._pageValues = _pageValues;
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
