package org.mo.game.editor.face.apl.logic.report;

import org.mo.jfa.common.page.FAbstractFormPage;

public class FWebReportPage
      extends FAbstractFormPage{

   private static final long serialVersionUID = 1L;

   private String _tempName;

   public String getTempName(){
      return _tempName;
   }

   public void setTempName(String tempName){
      _tempName = tempName;
   }

}
