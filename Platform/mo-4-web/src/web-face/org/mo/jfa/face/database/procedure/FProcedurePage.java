package org.mo.jfa.face.database.procedure;

import org.mo.com.lang.FString;
import org.mo.jfa.common.page.FAbstractFormPage;

public class FProcedurePage
      extends FAbstractFormPage
{

   private static final long serialVersionUID = 1L;

   private String _selectField;

   private String _selectTable;

   private FString _source;

   private String _selectFunction;

   private String _selectProcedure;

   private String _selectPackage;

   public String getSelectField(){
      return _selectField;
   }

   public String getSelectFunction(){
      return _selectFunction;
   }

   public String getSelectPackage(){
      return _selectPackage;
   }

   public String getSelectProcedure(){
      return _selectProcedure;
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

   public void setSelectFunction(String function){
      _selectFunction = function;
   }

   public void setSelectPackage(String package1){
      _selectPackage = package1;
   }

   public void setSelectProcedure(String procedure){
      _selectProcedure = procedure;
   }

   public void setSelectTable(String table){
      _selectTable = table;
   }

   public void setSource(FString source){
      _source = source;
   }

}
