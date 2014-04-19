using System;
using System.Security.Cryptography;
using System.Text;

namespace MO.Common.Lang
{
   //============================================================
   // <T>SHA加密工具类。</T>
   //
   // @history 110209 MAOCY 创建
   //============================================================
   public class RSha1
   {
      // SHA对象
      protected static SHA1 _sha = new SHA1CryptoServiceProvider();

      //============================================================
      public static void Setup() {
         _sha.Initialize();
      }

      //============================================================
      // <T>计算字符串的哈希值数组。</T>
      //
      // @param source 字符串
      // @return 哈希字符串
      //============================================================
      public static byte[] ComputeHashBytes(string source) {
         byte[] bytes = Encoding.ASCII.GetBytes(source);
         byte[] result = _sha.ComputeHash(bytes);
         return result;
      }

      //============================================================
      // <T>计算字符串的哈希值。</T>
      //
      // @param source 字符串
      // @return 哈希字符串
      //============================================================
      public static string ComputeHash(string source) {
         byte[] bytes = Encoding.ASCII.GetBytes(source);
         byte[] result = _sha.ComputeHash(bytes);
         return Convert.ToBase64String(result);         
      }

      //============================================================
      // <T>计算字节数组的哈希值。</T>
      //
      // @param source 字节数组
      // @return 哈希字符串
      //============================================================
      public static string ComputeHash(byte[] source) {
         byte[] result = _sha.ComputeHash(source);
         return Convert.ToBase64String(result);
      }

      //============================================================
      // <T>计算字节数组的哈希值。</T>
      //
      // @param source 字节数组
      // @return 哈希字符串
      //============================================================
      public static byte[] ComputeHashCode(byte[] source) {
         _sha.Initialize();
         return _sha.ComputeHash(source);
      }

      //============================================================
      // <T>计算字节数组的哈希值。</T>
      //
      // @param source 字节数组
      // @return 哈希字符串
      //============================================================
      public static string ComputeHashSimple(byte[] source) {
         byte[] result = new SHA1CryptoServiceProvider().ComputeHash(source);
         string hash = Convert.ToBase64String(result);
         if (hash.EndsWith("=")) {
            hash = hash.Substring(0, hash.Length - 1);
         }
         hash = hash.Replace('+', '*');
         hash = hash.Replace('/', '#');
         return hash;
      }

   }
}
