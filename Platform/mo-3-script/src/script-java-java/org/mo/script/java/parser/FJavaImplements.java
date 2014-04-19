package org.mo.script.java.parser;

import org.mo.com.lang.FObjects;

//============================================================
// <T>实现对象集合。</T>
//============================================================
public class FJavaImplements
      extends FObjects<FJavaImplement>
{
   //============================================================
   // <T>构造导入对象集合。</T>
   //============================================================
   public FJavaImplements(){
      super(FJavaImplement.class);
   }

   //============================================================
   // <T>构造导入对象集合。</T>
   //============================================================
   public FJavaImplement find(String name){
      for(int n = 0; n < _count; n++){
         FJavaImplement javaImplement = _items[n];
         String shortName = javaImplement.shortName();
         if(name.equals(shortName)){
            return javaImplement;
         }
      }
      return null;
   }
}
