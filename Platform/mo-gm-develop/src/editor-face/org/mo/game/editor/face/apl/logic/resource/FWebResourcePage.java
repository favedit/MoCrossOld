package org.mo.game.editor.face.apl.logic.resource;

import org.mo.com.xml.FXmlNode;
import org.mo.jfa.common.page.FAbstractFormPage;

public class FWebResourcePage
      extends FAbstractFormPage{

   private static final long serialVersionUID = 1L;

   private String _ouid;

   private String _over;

   private String _resourceId;

   private FXmlNode _moduleCatalogNode;

   private String _resourceLabel;

   private String _selectControl;

   private String _selectForm;

   private String _selectType;

   private String _history;

   public FXmlNode getModuleCatalogNode(){
      return _moduleCatalogNode;
   }

   public String getOuid(){
      return _ouid;
   }

   public String getOver(){
      return _over;
   }

   public String getResourceId(){
      return _resourceId;
   }

   public String getResourceLabel(){
      return _resourceLabel;
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

   public void setHistory(String history){
      _history = history;
   }

   public void setModuleCatalogNode(FXmlNode moduleCatalogNode){
      _moduleCatalogNode = moduleCatalogNode;
   }

   public void setOuid(String ouid){
      _ouid = ouid;
   }

   public void setOver(String over){
      _over = over;
   }

   public void setResourceId(String resourceId){
      _resourceId = resourceId;
   }

   public void setResourceLabel(String resourceLabel){
      _resourceLabel = resourceLabel;
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

}
