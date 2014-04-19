using System;

namespace MO.Common.Lang
{
   //============================================================
   // <T>长整数工具类。</T>
   //============================================================
   public class RLong
   {
      //============================================================
      // <T>解析字符串。</T>
      //
      // @param source 字符串
      // @return 整数
      //============================================================
      public static long Parse(string source) {
         if(null != source) {
            string value = source.Trim();
            if(value.Length > 0) {
               if(-1 != value.IndexOf(".")) {
                  return (long)Double.Parse(value);
               }
               return long.Parse(value);
            }
         }
         return 0;
      }
   }
}
