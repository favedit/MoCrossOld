package org.mo.eng.translate;

import java.io.Serializable;
import org.mo.com.lang.RString;
import org.mo.eng.translate.common.XTranslateContent;
import org.mo.eng.translate.common.XTranslateContents;

public class FSessionTranslate
      implements
         Serializable
{
   private static final long serialVersionUID = 1L;

   private final XTranslateContents _contents = new XTranslateContents();

   private String _language;

   private String _name;

   public FSessionTranslate(){
   }

   public XTranslateContents contents(){
      return _contents;
   }

   public XTranslateContent contents(String name){
      return _contents.get(name);
   }

   public int count(){
      return _contents.count();
   }

   public String language(){
      return _language;
   }

   public String name(){
      return _name;
   }

   public void push(XTranslateContent content){
      _contents.set(content.name(), content);
   }

   public void setLanguage(String language){
      _language = language;
   }

   public void setName(String name){
      _name = name;
   }

   public String translate(ETranslateType type,
                           String object,
                           String code){
      if(object != null && code != null){
         int index = code.lastIndexOf('|');
         String nvl = RString.EMPTY;
         if(index != -1){
            nvl = code.substring(index + 1);
            code = code.substring(0, index);
         }
         if(!RString.isBlank(code)){
            // Find map
            XTranslateContent map = _contents.get(object);
            if(map == null){
               //map = loadContentMap(object);
            }
            // Find context
            String source = null;
            /*FContent content = map.get(code);
            if(content != null){
               source = content.get(_cultureConsole.find().language());
            }*/
            if(RString.isBlank(source)){
               return nvl;
            }else{
               return source;
            }
         }
      }
      return code;
   }

   public String translate(String text){
      return null;
   }
}
