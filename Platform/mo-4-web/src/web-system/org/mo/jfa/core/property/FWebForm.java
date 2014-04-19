package org.mo.jfa.core.property;

import java.io.Serializable;
import org.mo.com.lang.generic.IStringNamed;

public class FWebForm
      implements
         Serializable,
         IStringNamed
{

   private static final long serialVersionUID = 1L;

   private String _action;

   private String _dataset;

   private String _editDelete;

   private String _editInsert;

   private String _editSearch;

   private String _editUpdate;

   private FWebFormFields _fileds = new FWebFormFields();

   private String _name;

   private String _source;

   private String _type;

   public FWebFormFields fileds(){
      return _fileds;
   }

   public String getAction(){
      return _action;
   }

   public String getDataset(){
      return _dataset;
   }

   public String getEditDelete(){
      return _editDelete;
   }

   public String getEditInsert(){
      return _editInsert;
   }

   public String getEditSearch(){
      return _editSearch;
   }

   public String getEditUpdate(){
      return _editUpdate;
   }

   public String getSource(){
      return _source;
   }

   public String getType(){
      return _type;
   }

   public String name(){
      return _name;
   }

   public void setAction(String action){
      _action = action;
   }

   public void setDataset(String dataset){
      _dataset = dataset;
   }

   public void setEditDelete(String delete){
      _editDelete = delete;
   }

   public void setEditInsert(String insert){
      _editInsert = insert;
   }

   public void setEditSearch(String search){
      _editSearch = search;
   }

   public void setEditUpdate(String update){
      _editUpdate = update;
   }

   public void setName(String name){
      _name = name;
   }

   public void setSource(String source){
      _source = source;
   }

   public void setType(String type){
      _type = type;
   }

}
