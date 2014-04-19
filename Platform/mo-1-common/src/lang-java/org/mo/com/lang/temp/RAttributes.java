package org.mo.com.lang.temp;

import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RString;

//============================================================
// <T>属性集合工具。</T>
//============================================================
public class RAttributes
{
   //============================================================
   // <T>追加内容为空的集合。</T>
   //
   // @param target 目标属性集合
   // @param source 来源属性集合
   //============================================================
   public static void appendEmpty(IAttributes target,
                                  IAttributes source){
      int count = source.count();
      for(int n = 0; n < count; n++){
         String name = source.name(n);
         if(RString.isEmpty(target.get(name, null))){
            target.set(name, source.value(n));
         }
      }
   }

   //============================================================
   // <T>追加内容为非空的集合。</T>
   //
   // @param target 目标属性集合
   // @param source 来源属性集合
   //============================================================
   public static void appendNotEmpty(IAttributes target,
                                     IAttributes source){
      int count = source.count();
      for(int n = 0; n < count; n++){
         String name = source.name(n);
         if(!RString.isEmpty(target.get(name, null))){
            target.set(name, source.value(n));
         }
      }
   }
}
