package org.mo.com.lang.temp;

import org.mo.com.lang.RString;

//============================================================
// <T>多语言字符串工具类。</T>
//============================================================
public class RMultiString
{
   // 标志
   public static final String FLAG = "M#";

   // 标志长度
   public static final int FLAG_LEN = FLAG.length();

   //============================================================
   // <T>判断字符串是否为空。</T>
   //
   // @param value 内容
   // @return 是否为空
   //============================================================
   public static boolean isEmpty(String value){
      if(RString.isEmpty(value)){
         return true;
      }
      if(FLAG.equals(value)){
         return true;
      }
      return false;
   }
}
