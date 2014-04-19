package org.mo.com.lang;

//============================================================
// <T>16进制工具类。</T>
//
// @history 130220 创建
//============================================================
public class RHex
{
   // 16进制字符集合
   public final static char[] HEX_CHARS = new String("0123456789ABCDEF").toCharArray();

   //============================================================
   // <T>解析一个字符为数字。</T>
   //
   // @param value 字符
   // @return 数字
   //============================================================
   public final static int parse(char value){
      if(value >= 'a'){
         return value - 'a' + 10;
      }
      if(value >= 'A'){
         return value - 'A' + 10;
      }
      return value - '0';
   }

   //============================================================
   // <T>将一个整数格式化为16进制字符串。</T>
   //
   // @param value 整数
   // @return 16进制字符串
   //============================================================
   public final static String format(int value){
      return Integer.toString(value, 16);
   }

   //============================================================
   // <T>将一个整数格式化为16进制字符串。</T>
   //
   // @param value 整数
   // @param length 格式化长度
   // @return 16进制字符串
   //============================================================
   public final static String format(int value,
                                     int length){
      return RString.leftPad(Integer.toString(value, 16).toUpperCase(), length, '0');
   }

   //============================================================
   // <T>将一个整数类型格式化为16进制字符串。</T>
   //
   // @param value 整数类型
   // @param length 格式化长度
   // @return 16进制字符串
   //============================================================
   public final static String toString(int value,
                                       int length){
      return toString(value, length, '0');
   }

   //============================================================
   // <T>将一个整数类型格式化为16进制字符串。</T>
   //
   // @param value 整数类型
   // @param length 格式化长度
   // @param pad 补足长度用的字符
   // @return 16进制字符串
   //============================================================
   public final static String toString(int value,
                                       int length,
                                       char pad){
      String hex = Integer.toHexString(value).toUpperCase();
      if(length > 0){
         int loop = length - hex.length();
         StringBuilder result = new StringBuilder();
         while(loop-- > 0){
            result.append(pad);
         }
         result.append(hex);
         return result.toString();
      }
      return hex;
   }

   //============================================================
   // <T>将一个整数类型格式化为16进制字符串。</T>
   //
   // @param data 字符串
   // @param value 整数类型
   // @param length 格式化长度
   // @return 16进制字符串
   //============================================================
   public final static void toString(StringBuilder data,
                                     int value,
                                     int length){
      toString(data, value, length, '0');
   }

   //============================================================
   // <T>将一个整数类型格式化为16进制字符串。</T>
   //
   // @param data 字符串
   // @param value 整数类型
   // @param length 格式化长度
   // @param pad 补足长度用的字符
   // @return 16进制字符串
   //============================================================
   public final static void toString(StringBuilder data,
                                     int value,
                                     int length,
                                     char pad){
      String hex = Integer.toHexString(value).toUpperCase();
      if(length > 0){
         int loop = length - hex.length();
         while(loop-- > 0){
            data.append(pad);
         }
         data.append(hex);
      }
   }
   //   //============================================================
   //   // <T>将一个长整数类型格式化为16进制字符串。</T>
   //   //
   //   // @param data 字符串
   //   // @param value 整数类型
   //   // @param length 格式化长度
   //   // @param pad 补足长度用的字符
   //   // @return 16进制字符串
   //   //============================================================
   //   public final static String toString(long value){
   //      return Long.toHexString(value);
   //   }
   //
   //   public final static String value(long value,
   //                                    int length){
   //      return value(value, length, '0');
   //   }
   //
   //   public final static String value(long value,
   //                                    int length,
   //                                    char pad){
   //      String hex = Long.toHexString(value).toUpperCase();
   //      if(length > 0){
   //         int loop = length - hex.length();
   //         FString result = new FString();
   //         while(loop-- > 0){
   //            result.append(pad);
   //         }
   //         result.append(hex);
   //         return result.toString();
   //      }
   //      return hex;
   //   }
}
