package org.mo.jfa.face.database.table;

import org.mo.com.lang.FString;
import org.mo.jfa.common.page.FAbstractFormPage;

public class FTablePage
      extends FAbstractFormPage
{

   private static final long serialVersionUID = 1L;

   private String _selectField;

   private String _selectTable;

   private FString _source;

   public String getSelectField(){
      return _selectField;
   }

   public String getSelectTable(){
      return _selectTable;
   }

   public FString getSource(){
      return _source;
   }

   public void setSelectField(String field){
      _selectField = field;
   }

   public void setSelectTable(String table){
      _selectTable = table;
   }

   public void setSource(FString source){
      _source = source;
   }

}
