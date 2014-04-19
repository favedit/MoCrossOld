package org.mo.jfa.face.database.view;

import org.mo.com.lang.FString;
import org.mo.jfa.common.page.FAbstractFormPage;

public class FViewPage
      extends FAbstractFormPage
{

   private static final long serialVersionUID = 1L;

   private String _selectField;

   private String _selectView;

   private FString _source;

   public String getSelectField(){
      return _selectField;
   }

   public String getSelectView(){
      return _selectView;
   }

   public FString getSource(){
      return _source;
   }

   public void setSelectField(String field){
      _selectField = field;
   }

   public void setSelectView(String view){
      _selectView = view;
   }

   public void setSource(FString source){
      _source = source;
   }

}
