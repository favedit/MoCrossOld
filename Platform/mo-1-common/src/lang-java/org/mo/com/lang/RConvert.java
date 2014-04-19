package org.mo.com.lang;

import java.util.Date;

//============================================================
// <T>类型转换工具。</T>
//============================================================
public class RConvert
{
   //============================================================
   // <T>将内容转换为指定类型。</T>
   //
   // @param clazz 类型
   // @param item 内容
   // @return 转换后内容
   //============================================================
   public static Object convert(Class<?> clazz,
                                Object item){
      // 检查参数
      if((null == clazz) || (null == item)){
         throw new FFatalError("Parameter is invalid. (clazz={1}, item={2}", clazz, item);
      }
      // 获得内容
      String type = clazz.getName();
      if(clazz == String.class){
         return RString.parse(item);
      }else if((clazz == Integer.class) || ("int".equals(type))){
         return RInteger.parse(item);
      }else if((clazz == Long.class) || "long".equals(type)){
         return RLong.parse(item);
      }else if((clazz == Float.class) || ("float".equals(type))){
         return RFloat.parse(item);
      }else if((clazz == Boolean.class) || ("boolean".equals(type))){
         return RBoolean.parse(item);
      }else if(clazz == Date.class){
         return RDateTime.parse(item);
      }
      return item;
   }
}
