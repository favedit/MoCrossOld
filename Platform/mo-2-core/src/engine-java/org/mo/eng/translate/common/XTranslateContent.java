package org.mo.eng.translate.common;

public class XTranslateContent
      extends XBaseTranslate
{

   private String[] _languages;

   private String[] _texts;

   public String language(int index){
      return _languages[index];
   }

   public String[] languages(){
      return _languages;
   }

   public void setLanguages(String[] languages){
      _languages = languages;
   }

   public void setTexts(String[] texts){
      _texts = texts;
   }

   public String text(int index){
      return _texts[index];
   }

   public String[] texts(){
      return _texts;
   }
}
