package org.mo.jfa.face.logic.resource;

import org.mo.jfa.common.page.FAbstractFormPage;

public class FResourcePage
      extends FAbstractFormPage
{

   private static final long serialVersionUID = 1L;

   private String _viewId;

   public String getViewId(){
      return _viewId;
   }

   public void setViewId(String id){
      _viewId = id;
   }

}
