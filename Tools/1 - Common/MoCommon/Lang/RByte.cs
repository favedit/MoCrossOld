using System;
namespace MO.Common.Lang
{
   //============================================================
   // <T>布尔工具类。</T>
   //============================================================
   public class RByte : RArray<byte>
   {
      // 每字节位数
      public const int BIT_SIZE = 8;

      // 每字节字节数
      public const int BYTE_SIZE = 1;

      //============================================================
      // <T>字节XOR设置运算。</T>
      //
      // @param source 源字节数组
      // @param keys 键字节数组
      //============================================================
      public static void DataXorSet(byte[] source, byte[] keys) {
         DataXorSet(source, 0, source.Length, keys);
      }

      //============================================================
      // <T>字节XOR设置运算。</T>
      //
      // @param source 源字节数组
      // @param offset 源开始位置
      // @param length 源内容长度
      // @param keys 键字节数组
      //============================================================
      public static void DataXorSet(byte[] source, int offset, int length, byte[] keys) {
         int keyLength = keys.Length;
         for(int n = 0; n < length; n++) {
            source[offset++] ^= keys[n % keyLength];
         }
      }

      //============================================================
      // <T>格式化字节数组为字符串。</T>
      //
      // @param bytes 字节数组
      // @return 字符串
      //============================================================
      public static string Format(byte[] memory) {
         if(null != memory) {
            return Format(memory, 0, memory.Length);
         }
         return null;
      }

      //============================================================
      // <T>格式化字节数组为字符串。</T>
      //
      // @param bytes 字节数组
      // @param offset 开始长度
      // @param length 长度
      // @return 字符串
      //============================================================
      public static string Format(byte[] memory, int offset, int length, char spliter = ' ') {
         FString result = new FString();
         for(int n = 0; n < length; n++) {
            if(n > 0) {
               if(0 != spliter) {
                  result.Append(spliter);
               }
            }
            result.Append(memory[offset + n].ToString("X2"));
         }
         return result.ToString();
      }

      //============================================================
      // <T>计算字节数组的哈希值。</T>
      //
      // @param source 字节数组
      // @return 哈希字符串
      //============================================================
      public static int ComputeHash32(byte[] source, int offset, int length) {
         long hash = ComputeHash64(source, offset, length);
         return (int)(hash & 0x7FFFFFFF);
      }

      //============================================================
      // <T>计算字节数组的哈希值。</T>
      //
      // @param source 字节数组
      // @return 哈希字符串
      //============================================================
      public static long ComputeHash64(byte[] source, int offset, int length) {
         long hash = 0;
         int position = offset + length;
         for(int n = offset; n < position; n++) {
            hash += (hash << 4) + (hash << 3) + (hash << 2) + (hash << 1) + source[n];
         }
         return hash;
      }

      //============================================================
      // <T>计算字节数组的哈希值。</T>
      //
      // @param source 字节数组
      // @return 哈希字符串
      //============================================================
      public static string ComputeHashSimple(byte[] source, int offset, int length) {
         long hash = ComputeHash64(source, offset, length);
         byte[] memory = BitConverter.GetBytes((int)hash);
         return Format(memory, 0, memory.Length, (char)0);
      }
   }
}
