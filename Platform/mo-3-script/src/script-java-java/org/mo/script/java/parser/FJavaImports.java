package org.mo.script.java.parser;

import org.mo.com.lang.FObjects;

//============================================================
// <T>导入对象集合。</T>
//============================================================
public class FJavaImports
      extends FObjects<FJavaImport>
{
   //============================================================
   // <T>构造导入对象集合。</T>
   //============================================================
   public FJavaImports(){
      super(FJavaImport.class);
   }

   //============================================================
   // <T>构造导入对象集合。</T>
   //============================================================
   public FJavaImport find(String name){
      for(int n = 0; n < _count; n++){
         FJavaImport javaImport = _items[n];
         String shortName = javaImport.shortName();
         if(name.equals(shortName)){
            return javaImport;
         }
      }
      return null;
   }
}
