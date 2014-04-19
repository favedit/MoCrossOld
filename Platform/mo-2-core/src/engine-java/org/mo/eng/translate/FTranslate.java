package org.mo.eng.translate;

import org.mo.com.lang.generic.IStringNamed;

public class FTranslate
      implements
         IStringNamed
{
   private final FContentMaps _contents = new FContentMaps();

   private String _name;

   public FTranslate(){
   }

   public FContentMaps contents(){
      return _contents;
   }

   public FContent contents(String name){
      return _contents.get(name);
   }

   public int count(){
      return _contents.count();
   }

   @Override
   public String name(){
      return _name;
   }

   public void push(FContent content){
      _contents.set(content.name(), content);
   }

   public void setName(String name){
      _name = name;
   }
}
