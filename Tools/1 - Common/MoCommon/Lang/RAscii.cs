using System;
using System.Text;

namespace MO.Common.Lang
{
   //============================================================
   // <T>ASCII字符工具类。</T>
   //============================================================
   public class RAscii
   {
      //============================================================
      // <T>从数组中获得字符串。</T>
      //
      // @param bytes 数组
      // @return 字符串
      //============================================================
      public static string GetString(byte[] bytes) {
         if(null == bytes) {
            int index = Array.IndexOf<byte>(bytes, 0);
            if(index == -1) {
               index = bytes.Length;
            }
            return Encoding.ASCII.GetString(bytes, 0, index);
         }
         return null;
      }
   }
}
