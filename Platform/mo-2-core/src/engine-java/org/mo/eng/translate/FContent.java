package org.mo.eng.translate;

import java.io.Serializable;

public class FContent
      implements
         Serializable
{
   private static final long serialVersionUID = 1L;

   private String[] _languages;

   private String _name;

   private String[] _texts;

   public String language(int index){
      return _languages[index];
   }

   public String[] languages(){
      return _languages;
   }

   public String name(){
      return _name;
   }

   public void setLanguages(String[] languages){
      _languages = languages;
   }

   public void setName(String name){
      _name = name;
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
