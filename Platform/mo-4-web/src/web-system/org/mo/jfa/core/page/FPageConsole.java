package org.mo.jfa.core.page;

import org.mo.com.lang.FFatalError;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.eng.common.FAbstractConfigConsole;
import org.mo.eng.data.common.ISqlContext;
import org.mo.eng.translate.ITranslateConsole;

public class FPageConsole
      extends FAbstractConfigConsole
      implements
         IPageConsole
{

   private String _dataName = "Page";

   @ALink
   private ITranslateConsole _trsConsole;

   @AProperty
   private String _type;

   public FXmlNode fixValues(FXmlNode page,
                             String type){
      for(FXmlNode sheet : page.nodes()){
         if(sheet.isName("Sheet")){
            String label = sheet.get("label");
            // label = _trsConsole.translate(ETranslateType.CONFIG, type, label);
            sheet.set("label", label);
         }
      }
      return page;
   }

   public void initializeRegister(){
      //_dataConsole.registerType(_type);
   }

   public boolean isPage(String source){
      return isSource(source);
   }

   public FXmlNodes list(ISqlContext context,
                         String type,
                         String name){
      FXmlNode page = page("pg:" + type + "|" + name);
      return fixValues(page, type).nodes();
   }

   public FXmlNodes list(String path){
      //      FXmlNode page = page("pg:" + path);
      //      String[] items = RString.splitTwo(path, '|');
      //      return fixValues(page, items[0]).nodes();
      return null;
   }

   public FXmlNode page(String source){
      String[] items = splitSource(source);
      if(items.length == 2){
         return findNode(_type, items[0], _dataName, items[1]);
      }else{
         throw new FFatalError("Source invalid.({0})", source);
      }
   }

}
