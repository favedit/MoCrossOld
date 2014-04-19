package org.mo.com.lang;

import org.mo.com.lang.type.RBaseChar;

//============================================================
// <T>字符数据类型工具。</T>
//
// @history 130220 创建
//============================================================
public class RChar
      extends RBaseChar
{
   // 存储位长度
   public static final int BIT_LENGTH = 16;

   // 存储字节长度
   public static final int BYTE_LENGTH = 2;

   // 内存容量
   public final static int CAPACITY = 32;

   // 内存增长率
   public final static int MULTIPLIER = 2;

   //============================================================
   // <T>判断当前字符是否为数字。</T>
   //
   // @param value 字符
   // @return 是否为数字
   //============================================================
   public final static boolean isNumber(char value){
      return (value >= '0' && value <= '9');
   }

   //============================================================
   // <T>判断当前字符是否为小写字符。</T>
   //
   // @param value 字符
   // @return 是否为小写字符
   //============================================================
   public final static boolean isLower(char value){
      return (value >= 'a' && value <= 'z');
   }

   //============================================================
   // <T>判断当前字符是否为大写字符。</T>
   //
   // @param value 字符
   // @return 是否为大写字符
   //============================================================
   public final static boolean isUpper(char value){
      return (value >= 'A' && value <= 'Z');
   }

   //============================================================
   // <T>删去字符数组前面的不可打印字符。</T>
   //
   // @param source 字符数组
   // @return 删去不可打印字符后的长度
   //============================================================
   public final static int trimLeft(char[] source){
      int s = 0;
      int e = source.length;
      while((s < e) && (source[s] <= ' ')){
         s++;
      }
      if(s > 0){
         System.arraycopy(source, s, source, 0, source.length - s);
      }
      return e - s;
   }

   //============================================================
   // <T>删去字符数组后面的不可打印字符。</T>
   //
   // @param source 字符数组
   // @return 删去不可打印字符后的长度
   //============================================================
   public final static int trimRight(char[] source){
      int e = source.length;
      while((0 < e) && (source[e - 1] <= ' ')){
         e--;
      }
      if(e != source.length){
         System.arraycopy(source, 0, source, 0, e);
      }
      return e;
   }

   //============================================================
   // <T>删去字符数组前后的不可打印字符。</T>
   //
   // @param source 字符数组
   // @return 删去不可打印字符后的长度
   //============================================================
   public final static int trim(char[] source){
      int s = 0;
      int e = source.length;
      while((s < e) && (source[s] <= ' ')){
         s++;
      }
      while((s < e) && (source[e - 1] <= ' ')){
         e--;
      }
      if(s > 0){
         System.arraycopy(source, s, source, 0, e - s);
      }
      return e - s;
   }

   //============================================================
   // <T>将当前字符转换成小写字符。</T>
   //
   // @param value 字符
   // @return 小写字符
   //============================================================
   public final static char toLower(char value){
      return isUpper(value) ? (char)(value + 32) : value;
   }

   //============================================================
   // <T>将当前字符转换成大写字符。</T>
   //
   // @param value 字符
   // @return 大写字符
   //============================================================
   public final static char toUpper(char value){
      return isLower(value) ? (char)(value - 32) : value;
   }

   //============================================================
   // <T>将字符数组转换为整数数组。</T>
   //
   // @param values 字符数组
   // @return 整数数组
   //============================================================
   public final static int[] toIntegers(char[] values){
      int length = values.length;
      int[] result = new int[length];
      for(int n = 0; n < length; n++){
         result[n] = values[n];
      }
      return result;
   }

   //============================================================
   // <T>将字符转换为字符串。</T>
   //
   // @param value 字符
   // @return 字符串
   //============================================================
   public final static String toString(char value){
      return String.valueOf(value);
   }

   //============================================================
   // <T>将指定字符转换成16进制字符数组。</T>
   //
   // @param value 字符内容
   // @param splitter 分割字符
   // @return 字符数组
   //============================================================
   public final static char[] toHexChars(char value,
                                         char splitter){
      char v1 = RHex.HEX_CHARS[(value >> 4) & 0x0F];
      char v2 = RHex.HEX_CHARS[value & 0x0F];
      char v3 = RHex.HEX_CHARS[(value >> 12) & 0x0F];
      char v4 = RHex.HEX_CHARS[(value >> 8) & 0x0F];
      return new char[]{splitter, v1, v2, splitter, v3, v4};
   }
   //   /**
   //    * <p>将当前字符转换成16进制字符数组</p>
   //    * <p>create date:2005/02/15</p>
   //    *
   //    * @return 16进制字符数组
   //    */
   //   public static char[] toHexChars(char chValue) {
   //      return toHexChars(chValue, true);
   //   }
   //   /**
   //    * <p>将指定字符转换成长度为4的16进制字符串</p>
   //    * <p>create date:2005/02/15</p>
   //    *
   //    * @param chValue 指定字符
   //    * @return 长度为4的16进制字符串
   //    */
   //   public static String toHexString(char chValue) {
   //      return new String(toHexChars(chValue, true));
   //   }
   //
   //   /**
   //    * <p>将指定字符转换成16进制字符串</p>
   //    * <p>是双字节字符，转换长度为4，否则转换为长度为2</p>
   //    * <p>create date:2005/02/15</p>
   //    *
   //    * @param chValue 指定字符
   //    * @param bDoubleChars 是否为双字节字符
   //    * @return 16进制字符串
   //    */
   //   public static String toHexString(char chValue, boolean bDoubleChars) {
   //      return new String(toHexChars(chValue, bDoubleChars));
   //   }
   // /**
   // * <p>将指定字符转换成16进制字符数组</p>
   // * <p>是双字节字符，转换长度为4，否则转换为长度为2</p>
   // * <p>create date:2007/12/09</p>
   // *
   // * @param value 指定字符
   // * @param bDoubleChars 是否为双字节字符
   // * @return 16进制字符数组
   // */
   //public static char[] toChars2(char value){
   //   return new char[] { FHexUtil.HEX_CHARS[(value >> 4) & 0x0F], FHexUtil.HEX_CHARS[value & 0x0F] };
   //}
   //
   //public static FString toChars2(FString data,
   //                               char value){
   //   data.append(FHexUtil.HEX_CHARS[(value >> 4) & 0x0F]);
   //   data.append(FHexUtil.HEX_CHARS[value & 0x0F]);
   //   return data;
   //}
   //
   //public static char[] toChars4(char value){
   //   return new char[] { FHexUtil.HEX_CHARS[(value >> 4) & 0x0F], FHexUtil.HEX_CHARS[value & 0x0F], FHexUtil.HEX_CHARS[(value >> 12) & 0x0F], FHexUtil.HEX_CHARS[(value >> 8) & 0x0F] };
   //}
   //
   //public static FString toChars4(FString data,
   //                               char value){
   //   data.append(FHexUtil.HEX_CHARS[(value >> 4) & 0x0F]);
   //   data.append(FHexUtil.HEX_CHARS[value & 0x0F]);
   //   data.append(FHexUtil.HEX_CHARS[(value >> 12) & 0x0F]);
   //   data.append(FHexUtil.HEX_CHARS[(value >> 8) & 0x0F]);
   //   return data;
   //}
}
