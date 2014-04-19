package org.mo.game.editor.web.apl.form;

import org.mo.com.lang.FDictionary;

public class FWebFormInfosMap
      extends FDictionary<FWebFormInfos>
{

   public FWebFormInfos sync(String name){
      FWebFormInfos infos = get(name);
      if(null == infos){
         infos = new FWebFormInfos();
         set(name, infos);
      }
      return infos;
   }
}
