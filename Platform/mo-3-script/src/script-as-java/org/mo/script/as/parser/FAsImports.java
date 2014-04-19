package org.mo.script.as.parser;

import org.mo.com.lang.FObjects;

//============================================================
// <T>导入对象集合。</T>
//============================================================
public class FAsImports
      extends FObjects<FAsImport>
{
   //============================================================
   // <T>构造导入对象集合。</T>
   //============================================================
   public FAsImports(){
      super(FAsImport.class);
   }

   //============================================================
   // <T>构造导入对象集合。</T>
   //============================================================
   public FAsImport find(String name){
      for(int n = 0; n < _count; n++){
         FAsImport asImport = _items[n];
         String shortName = asImport.shortName();
         if(name.equals(shortName)){
            return asImport;
         }
      }
      return null;
   }
}
