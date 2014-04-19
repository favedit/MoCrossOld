package org.mo.game.editor.face.apl.logic.form;

import org.mo.game.editor.web.apl.form.FAbstractWebFormPage;

public class FWebFormPage
      extends FAbstractWebFormPage{

   private static final long serialVersionUID = 1L;

   private String _filePath;

   public String getFilePath(){
      return _filePath;
   }

   public void setFilePath(String path){
      _filePath = path;
   }

}
