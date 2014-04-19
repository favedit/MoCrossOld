/*
 * @(#)FLongUtil.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.com.lang;

import org.mo.com.lang.type.RBaseLong;

//============================================================
// <T>长整数数据类型工具类。</T>
//
// @history 051012 MAOCY 创建
//============================================================
public class RLong
      extends RBaseLong
{
   // 存储位长度
   public final static int BIT_LENGTH = 64;

   // 存储字节长度
   public final static int BYTE_LENGTH = 8;

   // 内存容量
   public final static int CAPACITY = 32;

   // 内存增长率
   public final static int MULTIPLIER = 2;

   //============================================================
   // <T>格式化内存数据。</T>
   //
   // @param value 数值
   // @return 格式化后字符串
   //============================================================
   public static String formatMemory(long value){
      return formatMemory(value, 'a');
   }

   //============================================================
   // <T>格式化内存数据。</T>
   //
   // @param value 数值
   // @param unit 单位
   // @return 格式化后字符串
   //============================================================
   public static String formatMemory(long value,
                                     char unit){
      long b = value % 1024;
      long k = (value / 1024) % 1024;
      long m = (value / 1024 / 1024) % 1024;
      long g = (value / 1024 / 1024 / 1024) % 1024;
      if(unit == 'g'){
         return g + "g";
      }else if(unit == 'm'){
         if(g > 0){
            return g + "g" + m + "m";
         }else{
            return m + "m";
         }
      }else if(unit == 'k'){
         if(g > 0){
            return g + "g" + m + "m" + k + "k";
         }else if(m > 0){
            return m + "m" + k + "k";
         }else{
            return k + "k";
         }
      }else{
         if(g > 0){
            return g + "g" + m + "m" + k + "k" + b + "b";
         }else if(m > 0){
            return m + "m" + k + "k" + b + "b";
         }else if(k > 0){
            return k + "k" + b + "b";
         }else{
            return b + "b";
         }
      }
   }

   //============================================================
   // <T>格式化内存数据。</T>
   //
   // @param value 数值
   // @return 格式化后字符串
   //============================================================
   public static String formatMemoryPad(long value){
      return formatMemoryPad(value, 'a');
   }

   //============================================================
   // <T>格式化内存数据。</T>
   //
   // @param value 数值
   // @param unit 类型
   // @return 格式化后字符串
   //============================================================
   public static String formatMemoryPad(long value,
                                        char unit){
      long b = value % 1024;
      long k = (value / 1024) % 1024;
      long m = (value / 1024 / 1024) % 1024;
      long g = (value / 1024 / 1024 / 1024) % 1024;
      String bs = RString.leftPad(Long.toString(b), 4, " ");
      String ks = RString.leftPad(Long.toString(k), 4, " ");
      String ms = RString.leftPad(Long.toString(m), 4, " ");
      String gs = RString.leftPad(Long.toString(g), 4, " ");
      if(unit == 'g'){
         return gs + "g";
      }else if(unit == 'm'){
         if(g > 0){
            return gs + "g" + ms + "m";
         }else{
            return ms + "m";
         }
      }else if(unit == 'k'){
         if(g > 0){
            return gs + "g" + ms + "m" + ks + "k";
         }else if(m > 0){
            return ms + "m" + ks + "k";
         }else{
            return ks + "k";
         }
      }else{
         if(g > 0){
            return gs + "g" + ms + "m" + ks + "k" + bs + "b";
         }else if(m > 0){
            return ms + "m" + ks + "k" + bs + "b";
         }else if(k > 0){
            return ks + "k" + bs + "b";
         }else{
            return bs + "b";
         }
      }
   }

   //============================================================
   // <T>将一个对象变换为长整数。</T>
   // <P>如果变换失败，将得到整数类型的默认值，不会产生错误</P>
   //
   // @param value 对象
   // @return 长整数
   //============================================================
   public static long parse(String value){
      if(value != null){
         return parse(value, DEFAULT);
      }
      return DEFAULT;
   }

   //============================================================
   // <T>将一个对象变换为长整数。</T>
   // <P>如果变换失败，将得到整数类型的默认值，不会产生错误</P>
   //
   // @param value 对象
   // @param defaultValue 默认值
   // @return 长整数
   //============================================================
   public static long parse(String value,
                            long defaultValue){
      try{
         return Long.parseLong(value);
      }catch(Exception exception){
      }
      return defaultValue;
   }

   //============================================================
   // <T>将一个对象变换为长整数。</T>
   // <P>如果变换失败，将得到整数类型的默认值，不会产生错误</P>
   //
   // @param value 对象
   // @return 长整数
   //============================================================
   public static long parse(Object item){
      if(item != null){
         // 是否为数字
         if(item instanceof Long){
            return ((Long)item).longValue();
         }
         // 转换为字符串
         String value = null;
         if(item instanceof String){
            value = (String)item;
         }else{
            value = item.toString();
         }
         value = value.trim();
         if(value.length() > 0){
            return Long.parseLong(value);
         }
      }
      return DEFAULT;
   }

   //============================================================
   // <T>将一个对象变换为长整数。</T>
   // <P>如果变换失败，将得到整数类型的默认值，不会产生错误</P>
   //
   // @param value 对象
   // @param defaultValue 默认值
   // @return 长整数
   //============================================================
   public static long parse(Object item,
                            long defaultValue){
      if(null != item){
         return parse(item.toString(), defaultValue);
      }
      return defaultValue;
   }

   //============================================================
   // <T>将一个长整数转换为字节数组。</T>
   //
   // @param value 长整数
   // @return 字节数组
   //============================================================
   public final static byte[] toBytes(long value){
      byte[] data = new byte[RLong.BYTE_LENGTH];
      data[0] = (byte)(value & 0xFF);
      data[1] = (byte)((value >>> 8) & 0xFF);
      data[2] = (byte)((value >>> 16) & 0xFF);
      data[3] = (byte)((value >>> 24) & 0xFF);
      data[4] = (byte)((value >>> 32) & 0xFF);
      data[5] = (byte)((value >>> 40) & 0xFF);
      data[6] = (byte)((value >>> 48) & 0xFF);
      data[7] = (byte)((value >>> 56) & 0xFF);
      return data;
   }

   //============================================================
   // <T>将一个长整数转换为字节数组。</T>
   //
   // @param value 长整数
   // @param data 字节数组
   // @param index 索引位置
   //============================================================
   public final static void toBytes(long value,
                                    byte[] data,
                                    int index){
      data[index] = (byte)(value & 0xFF);
      data[index + 1] = (byte)((value >>> 8) & 0xFF);
      data[index + 2] = (byte)((value >>> 16) & 0xFF);
      data[index + 3] = (byte)((value >>> 24) & 0xFF);
      data[index + 4] = (byte)((value >>> 32) & 0xFF);
      data[index + 5] = (byte)((value >>> 40) & 0xFF);
      data[index + 6] = (byte)((value >>> 48) & 0xFF);
      data[index + 7] = (byte)((value >>> 56) & 0xFF);
   }

   //============================================================
   // <T>将一个整数类型格式化为16进制字符串。</T>
   //
   // @param value 长整数
   // @param length 格式化长度
   // @return 16进制字符串
   //============================================================
   public static String toHexNumber(long value,
                                    int length){
      return toHexNumber(value, length, '0');
   }

   //============================================================
   // <T>将一个整数类型格式化为16进制字符串。</T>
   //
   // @param value 长整数
   // @param length 格式化长度
   // @param pad 补足长度用的字符
   // @return 16进制字符串
   //============================================================
   public static String toHexNumber(long value,
                                    int length,
                                    char pad){
      String hex = Long.toHexString(value).toUpperCase();
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

   //   /**
   //    * <p>将一个长整数数值格式化为字符串</p>
   //    * <p>create date:2005/02/14</p>
   //    *
   //    * @param lValue 长整数数值
   //    * @param nLength 格式化长度
   //    * @return 字符串
   //    */
   //   public static String format(long lValue, int nLength) {
   //      return FStringUtil.leftPad(Long.toString(lValue), nLength, "0");
   //   }
   //
   //   /**
   //    * <p>将一个长整数数值格式化为字符串</p>
   //    * <p>create date:2005/02/14</p>
   //    *
   //    * @param lValue 长整数数值
   //    * @param nLength 格式化长度
   //    * @param sChar 补足长度用的字符串
   //    * @return 字符串
   //    */
   //   public static String format(long lValue, int nLength, String sChar) {
   //      return FStringUtil.leftPad(Long.toString(lValue), nLength, sChar);
   //   }
   //
   //   /**
   //    * <p>将一个长整数字符串格式化为字符串</p>
   //    * <p>create date:2005/02/14</p>
   //    *
   //    * @param sValue 长整数字符串
   //    * @param nLength 格式化长度
   //    * @return 字符串
   //    */
   //   public static String format(String sValue, int nLength) {
   //      return FStringUtil.leftPad(sValue, nLength, "0");
   //   }
   //
   //   /**
   //    * <p>将一个长整数字符串格式化为字符串</p>
   //    * <p>create date:2005/02/14</p>
   //    *
   //    * @param sValue 长整数字符串
   //    * @param nLength 格式化长度
   //    * @param sChar 补足长度用的字符串
   //    * @return 字符串
   //    */
   //   public static String format(String sValue, int nLength, String sChar) {
   //      return FStringUtil.leftPad(sValue, nLength, sChar);
   //   }
   //
   //
   //   public final static String toString(long lValue){
   //      return Long.toString(lValue);
   //   }
   //   /**
   //    * <p>将一个长整数转换为字节数组</p>
   //    * <p>create date:2005/01/02</p>
   //    *
   //    * @param lValue 长整数
   //    * @param nLength 转换字节数组的长度
   //    * @return 字节数组
   //    */
   //   public final static byte[] getBytes(long lValue, int nLength) {
   //      byte[] oLong = new byte[nLength];
   //      for (int i = 0; i < nLength; i++) {
   //         oLong[i] = (byte) (lValue & 0x00000000000000FF);
   //         lValue >>= 8;
   //      }
   //      return oLong;
   //   }
   //
   //   /**
   //    * <p>将一个字节数组转换为长整数</p>
   //    * <p>create date:2005/01/02</p>
   //    *
   //    * @param oLong 字节数组
   //    * @return 长整数
   //    */
   //   public final static long getLong(byte[] oLong) {
   //      if (oLong == null) {
   //         throw new IllegalArgumentException("Byte array is null!");
   //      }
   //      if (oLong.length > 8) {
   //         throw new IllegalArgumentException("Byte array size > 8 !");
   //      }
   //      long lResult = 0;
   //      for (int n = oLong.length - 1; n >= 0; n--) {
   //         lResult <<= 8;
   //         lResult |= (oLong[n] & 0x00000000000000FF);
   //      }
   //      return lResult;
   //   }
   //
   //   /**
   //    * <p>将一个长整数类型格式化为16进制字符串</p>
   //    * <p>create date:2005/02/14</p>
   //    *
   //    * @param lValue 长整数类型
   //    * @return 16进值字符串
   //    */
   //   public static String toHexString(long lValue) {
   //      return toHexString(lValue, -1, " ");
   //   }
   //
   //   /**
   //    * <p>将一个长整数类型格式化为16进制字符串</p>
   //    * <p>create date:2005/02/14</p>
   //    *
   //    * @param lValue 长整数类型
   //    * @param nLength 格式化长度
   //    * @return 16进制字符串
   //    */
   //   public static String toHexString(long lValue, int nLength) {
   //      return toHexString(lValue, nLength, " ");
   //   }
   //
   //   /**
   //    * <p>将一个长整数类型格式化为16进制字符串</p>
   //    * <p>create date:2005/02/14</p>
   //    *
   //    * @param lValue 长整数类型
   //    * @param nLength 格式化长度
   //    * @param sChar 补足长度用的字符串
   //    * @return 16进制字符串
   //    */
   //   public static String toHexString(long lValue, int nLength, String sChar) {
   //      String sFormat = Long.toHexString(lValue).toUpperCase();
   //      return (nLength > 0) ? FStringUtil.rightPad(sFormat, nLength, sChar)
   //            : sFormat;
   //   }
   //   public byte[] toByteArray(int number){
   //      int temp = number;
   //      byte[] b = new byte[8];
   //      for(int i = b.length - 1; i > -1; i--){
   //         b[i] = new Integer(temp & 0xff).byteValue();
   //         temp = temp >> 8;
   //      }
   //      return b;
   //   }
   //   public long toLong(byte[] b){
   //      long l = 0;
   //      l = b[0];
   //      l |= ((long)b[1] << 8);
   //      l |= ((long)b[2] << 16);
   //      l |= ((long)b[3] << 24);
   //      l |= ((long)b[4] << 32);
   //      l |= ((long)b[5] << 40);
   //      l |= ((long)b[6] << 48);
   //      l |= ((long)b[7] << 56);
   //      return l;
   //   }
}
