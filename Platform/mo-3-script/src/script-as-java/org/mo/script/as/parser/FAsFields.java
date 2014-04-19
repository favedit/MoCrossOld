package org.mo.script.as.parser;

import org.mo.com.lang.generic.MObjects;

//============================================================
// <T>字段对象集合。</T>
//============================================================
public class FAsFields
      extends MObjects<FAsField>
{
   //============================================================
   // <T>构造类对象集合。</T>
   //============================================================
   public FAsFields(){
      super(FAsField.class);
   }

   //============================================================
   // <T>根据字段名称查找字段对象。</T>
   //
   // @param name 字段名称
   // @return 字段对象
   //============================================================
   public FAsField find(String name){
      int count = _count;
      for(int n = 0; n < count; n++){
         FAsField field = _items[n];
         if(field.name().equals(name)){
            return field;
         }
      }
      return null;
   }
}
