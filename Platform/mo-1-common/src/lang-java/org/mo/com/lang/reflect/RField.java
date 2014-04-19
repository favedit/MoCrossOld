package org.mo.com.lang.reflect;

import org.mo.com.lang.RString;

//============================================================
// <T>字段对象工具类。</T>
//============================================================
public class RField
{
   //============================================================
   // <T>修正字段名称。</T>
   //
   // @param name 名称
   // @return 修正后名称
   //============================================================
   private static String fixName(String name){
      // 截取
      int fix = name.lastIndexOf('.');
      if(fix != -1){
         name = name.substring(fix + 1);
      }
      // 格式化处理 
      if(name.length() > 2){
         if(name.startsWith("F") || name.startsWith("I")){
            String temp = name.substring(1, 1);
            if(temp.equals(temp.toUpperCase())){
               return name.substring(1);
            }
         }
      }
      return name;
   }

   //============================================================
   // <T>格式化字段名称。</T>
   //
   // @param name 名称
   // @return 格式化后名称
   //============================================================
   public static String format(String name){
      name = fixName(RString.trimLeft(name, '_'));
      return RString.firstLower(name);
   }

   //============================================================
   // <T>格式化字段名称。</T>
   //
   // @param prefix 前缀
   // @param name 名称
   // @return 格式化后名称
   //============================================================
   public static String format(String prefix,
                               String name){
      name = fixName(RString.trimLeft(name, '_'));
      name = RString.firstLower(name);
      return prefix + name;
   }

   //============================================================
   // <T>格式化字段大写名称。</T>
   //
   // @param prefix 前缀
   // @param name 名称
   // @return 格式化后名称
   //============================================================
   public static String formatUpper(String prefix,
                                    String name){
      name = fixName(RString.trimLeft(name, '_'));
      name = RString.firstUpper(name);
      return prefix + name;
   }
}
