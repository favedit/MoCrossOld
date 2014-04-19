package org.mo.eng.translate.common;

public class XTranslate
      extends XBaseTranslate
{
   private final XTranslateContents _contents = new XTranslateContents();

   public XTranslate(){
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

   public void push(XTranslateContent content){
      _contents.set(content.name(), content);
   }
}
