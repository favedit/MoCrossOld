using System;
using System.Text;
using MO.Common.Lang;

namespace MO.Common.System
{
   //============================================================
   // <T>加密工具类。<T>
   //
   // @history 120213 MAOCY 创建
   //============================================================
   public class REncrypt
   {
      //============================================================
      // <T>根据键加密内容，获得加密后的内容。<T>
      //
      // @param key 键
      // @param value 内容
      // @return 加密后的内容
      //============================================================
      public static string Encode(string key, string value) {
         byte[] source = Encoding.ASCII.GetBytes(value);
         byte[] keys = RSha1.ComputeHashBytes(key);
         RByte.DataXorSet(source, 0, source.Length, keys);
         return Convert.ToBase64String(source);
      }

      //============================================================
      // <T>根据键解密内容，获得解密后的内容。<T>
      //
      // @param key 键
      // @param value 内容
      // @return 加密后的内容
      //============================================================
      public static string Decode(string key, string value) {
         byte[] source = Convert.FromBase64String(value);
         byte[] keys = RSha1.ComputeHashBytes(key);
         RByte.DataXorSet(source, keys);
         return Encoding.ASCII.GetString(source);
      }
   }
}
