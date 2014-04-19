package org.mo.game.editor.face.apl.logic.transfer;

import org.mo.jfa.common.page.FAbstractFormPage;

public class FCsvImportPage
      extends FAbstractFormPage{

   private static final long serialVersionUID = 1L;

   private String _file;

   private String _charset;

   public String getCharset(){
      return _charset;
   }

   public String getFile(){
      return _file;
   }

   public void setCharset(String charset){
      this._charset = charset;
   }

   public void setFile(String file){
      _file = file;
   }

}
