using System;

namespace MO.Common.Lang
{
   //============================================================
   // <T>整数工具类。</T>
   //============================================================
   public class RInt : RArray<int>
   {
      public const int BIT_SIZE = 32;

      public const int BYTE_SIZE = 4;

      public const int SIZE_1K = 1024;

      public const int SIZE_2K = 2048;

      public const int SIZE_4K = 4096;

      public const int SIZE_8K = 8192;

      public const int SIZE_16K = 16384;

      public const int SIZE_64K = 1024 * 64;

      public const int SIZE_128K = 1024 * 128;

      public const int SIZE_256K = 1024 * 256;

      public const int SIZE_512K = 1024 * 512;

      public const int SIZE_1M = 1024 * 1024;

      //============================================================
      // <T>是否为合法16进制字符串。</T>
      //
      // @param source 字符串
      // @return 是否合法
      //============================================================
      public static bool IsHex(string source) {
         if (source == null) {
            return true;
         }
         string value = source.Trim();
         if (value.Length == 0) {
            return true;
         }
         int length = value.Length;
         for (int n = 0; n < length; n++) {
            char ch = value[n];
            if ("#xX0123456789ABCDEFabcdef".IndexOf(ch) == -1) {
               return false;
            }
         }
         return true;
      }

      //============================================================
      // <T>解析对象。</T>
      //
      // @param source 对象
      // @param value 默认值
      // @return 整数
      //============================================================
      public static int Parse(object source) {
         if (null != source) {
            string value = source.ToString().Trim();
            if (value.Length > 0) {
               return Int32.Parse(value);
            }
         }
         return 0;
      }

      //============================================================
      // <T>解析字符串。</T>
      //
      // @param source 字符串
      // @param defaultValue 默认内容
      // @return 整数
      //============================================================
      public static int Parse(string source, int defaultValue = 0) {
         if(null != source) {
            string value = source.Trim();
            if (value.Length > 0) {
               if (-1 != value.IndexOf(".")) {
                  return (int)Double.Parse(value);
               }
               return Int32.Parse(value);
            }
         }
         return defaultValue;
      }

      //============================================================
      // <T>解析16进制字符串。</T>
      //
      // @param source 字符串
      // @param defaultValue 默认内容
      // @return 整数
      //============================================================
      public static int ParseHex(string source, int defaultValue = 0) {
         if (RString.IsEmpty(source)) {
            return defaultValue;
         }
         string value = source.Trim();
         if (value.Length == 0) {
            return defaultValue;
         }
         if (value.StartsWith("#")) {
            value = value.Substring(1);
         }
         if (value.StartsWith("0x")) {
            value = value.Substring(2);
         }
         return Convert.ToInt32(value, 16);
      }

      //============================================================
      // <T>判断是否在范围中。</T>
      //
      // @param value 整数值
      // @param range 范围
      // @return 是否在范围中
      //============================================================
      public static bool InRange(int value, params int[] range) {
         if(null != range) {
            foreach(int item in range) {
               if(value == item) {
                  return true;
               }
            }
         }
         return false;
      }

      //============================================================
      // <T>左边补齐数字。</T>
      //============================================================
      public static string Pad(int value, int length) {
         string source = value.ToString();
         return source.PadLeft(length, '0');
      }

      //============================================================
      // <T>左边补齐数字。</T>
      //============================================================
      public static string Pad(int value, int length, char pad) {
         string source = value.ToString();
         return source.PadLeft(length, pad);
      }
   }
}
