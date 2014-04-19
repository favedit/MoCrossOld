using System;
using System.IO;

namespace MO.Common.Lang
{
   //============================================================
   // <T>Base64工具类。</T>
   //============================================================
   public class RBase64
   {
      //============================================================
      // <T>将一个字节数组编码成Base64字符串。</T>
      //
      // @param bytes 字节数组
      // @return 字符串
      //============================================================
      public static string Encode(byte[] bytes) {
         return Convert.ToBase64String(bytes);
      }

      //============================================================
      // <T>将一个Base64字符串解码成字节数组。</T>
      //
      // @param source 字符串
      // @return 字节数组
      //============================================================
      public static byte[] Decode(string source) {
         return Convert.FromBase64String(source);
      }

      //============================================================
      // <T>将一个字节文件编码成Base64字符串文件。</T>
      //
      // @param fromfile 读取文件名
      // @param tofile 存储文件名
      //============================================================
      public static void EncodeFile(string fromfile, string tofile) {
         byte[] bytes = File.ReadAllBytes(fromfile);
         string data = Convert.ToBase64String(bytes);
         File.WriteAllText(tofile, data);
      }
   }
}
