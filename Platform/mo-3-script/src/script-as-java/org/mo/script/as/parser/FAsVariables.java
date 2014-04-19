package org.mo.script.as.parser;

import org.mo.com.lang.generic.MObjects;

//============================================================
// <T>变量对象集合。</T>
//============================================================
public class FAsVariables
      extends MObjects<FAsVariable>
{
   //============================================================
   // <T>构造参数对象集合。</T>
   //============================================================
   public FAsVariables(){
      super(FAsVariable.class);
   }

   //============================================================
   // <T>根据变量名称查找变量对象。</T>
   //
   // @param name 变量名称
   // @return 变量对象
   //============================================================
   public FAsVariable find(String name){
      int count = _count;
      for(int n = 0; n < count; n++){
         FAsVariable variable = _items[n];
         if(variable.name().equals(name)){
            return variable;
         }
      }
      return null;
   }
}
