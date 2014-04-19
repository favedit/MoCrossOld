package org.mo.com.system;

import org.mo.com.lang.FStrings;

//============================================================
// <T>应用参数。</T>
//============================================================
public class FApplicationArgements
      extends FStrings
{
   //============================================================
   // <T>构造应用参数。</T>
   //============================================================
   public FApplicationArgements(){
   }

   //============================================================
   // <T>根据名称查找参数。</T>
   //
   // @param name 名称
   // @return 参数
   //============================================================
   public String find(String name){
      return find(name, null);
   }

   //============================================================
   // <T>根据名称查找参数。</T>
   //
   // @param name 名称
   // @param defaultValue 默认内容
   // @return 参数
   //============================================================
   public String find(String name,
                      String defaultValue){
      for(int n = 0; n < _count; n++){
         String value = _items[n];
         if(value.equals(name)){
            if(n + 1 < _count){
               return _items[n + 1];
            }
            return defaultValue;
         }
      }
      return defaultValue;
   }
}
