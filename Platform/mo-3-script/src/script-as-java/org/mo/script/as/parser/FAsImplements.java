package org.mo.script.as.parser;

import org.mo.com.lang.FObjects;

//============================================================
// <T>实现对象集合。</T>
//============================================================
public class FAsImplements
      extends FObjects<FAsImplement>
{
   //============================================================
   // <T>构造导入对象集合。</T>
   //============================================================
   public FAsImplements(){
      super(FAsImplement.class);
   }

   //============================================================
   // <T>构造导入对象集合。</T>
   //============================================================
   public FAsImplement find(String name){
      for(int n = 0; n < _count; n++){
         FAsImplement asImplement = _items[n];
         String shortName = asImplement.shortName();
         if(name.equals(shortName)){
            return asImplement;
         }
      }
      return null;
   }
}
