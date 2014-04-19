package org.mo.com.lang;

import org.mo.com.lang.type.RBaseInteger;

//============================================================
// <T>整型数据工具类。</T>
//
// @history 080727 MAOCY 创建
//============================================================
public class RInteger
      extends RBaseInteger
{
   // 存储位长度
   public final static int BIT_LENGTH = 32;

   // 存储字节长度
   public final static int BYTE_LENGTH = 4;

   // 内存容量
   public final static int CAPACITY = 32;

   // 内存增长率
   public final static int MULTIPLIER = 2;

   // 1K的Byte数
   public final static int SIZE_1K = 1024;

   // 2K的Byte数
   public final static int SIZE_2K = 1024 * 2;

   // 4K的Byte数
   public final static int SIZE_4K = 1024 * 4;

   // 8K的Byte数
   public final static int SIZE_8K = 1024 * 8;

   // 16K的Byte数
   public final static int SIZE_16K = 1024 * 16;

   // 32K的Byte数
   public final static int SIZE_32K = 1024 * 32;

   // 64K的Byte数
   public final static int SIZE_64K = 1024 * 64;

   // 1M的Byte数
   public final static int SIZE_1M = 1024 * 1024;

   // 2M的Byte数
   public final static int SIZE_2M = 1024 * 1024 * 2;

   // 4M的Byte数
   public final static int SIZE_4M = 1024 * 1024 * 4;

   // 8M的Byte数
   public final static int SIZE_8M = 1024 * 1024 * 8;

   // 默认分割长度
   public static int DEFAULT_GROUP_LENGTH = 3;

   // 默认间隔符
   public static String DEFAULT_GROUP_CHAR = ",";

   // 有效字符集合
   public static char[] VALID_CHARS = "+-0123456789".toCharArray();

   //============================================================
   // <T>测试一个字符串是否为数字。</T>
   //
   // @param value 字符串
   // @return 是否为数字
   //============================================================
   public static boolean isInteger(String value){
      if(null != value){
         int length = value.length();
         for(int n = 0; n < length; n++){
            char ch = value.charAt(n);
            if(!RChar.contains(VALID_CHARS, ch)){
               return false;
            }
         }
         return true;
      }
      return false;
   }

   //============================================================
   // <T>将一个对象变换为整数。</T>
   // <P>如果变换失败，将得到整数类型的默认值，不会产生错误</P>
   //
   // @param value 字符串
   // @return 整数
   //============================================================
   public static int parse(String value){
      if(null != value){
         return parse(value, DEFAULT);
      }
      return DEFAULT;
   }

   //============================================================
   // <T>将一个对象变换为整数。</T>
   // <P>如果变换失败，将得到整数类型的默认值，不会产生错误</P>
   //
   // @param value 字符串
   // @param defaultValue 默认值
   // @return 整数
   //============================================================
   public static int parse(String value,
                           int defaultValue){
      if(null != value){
         try{
            // 解析数字
            value = value.trim();
            if(value.length() > 0){
               return Integer.parseInt(value);
            }
         }catch(Exception e){
         }
      }
      return defaultValue;
   }

   //============================================================
   // <T>将一个对象变换为整数。</T>
   // <P>如果变换失败，将得到整数类型的默认值，不会产生错误</P>
   //
   // @param value 对象
   // @return 整数
   //============================================================
   public static int parse(Object item){
      if(null != item){
         // 是否为数字
         if(item instanceof Integer){
            return ((Integer)item).intValue();
         }
         // 转换为字符串
         String value = null;
         if(item instanceof String){
            value = (String)item;
         }else{
            value = item.toString();
         }
         // 解析数字
         value = value.trim();
         if(value.length() > 0){
            return Integer.parseInt(value);
         }
      }
      return DEFAULT;
   }

   //============================================================
   // <T>将一个对象变换为整数。</T>
   // <P>如果变换失败，将得到整数类型的默认值，不会产生错误</P>
   //
   // @param item 对象
   // @param defaultValue 默认值
   // @return 整数
   //============================================================
   public static int parse(Object item,
                           int defaultValue){
      if(null != item){
         return parse(item.toString(), defaultValue);
      }
      return defaultValue;
   }

   //============================================================
   // <T>将一个对象变换为整数。</T>
   // <P>如果变换失败，将得到整数类型的默认值，不会产生错误</P>
   //
   // @param value 字符值
   // @param defaultValue 默认值
   // @return 整数
   //============================================================
   public static int parseMemory(String value,
                                 int defaultValue){
      if(null != value){
         try{
            value = value.trim();
            if(value.endsWith("k") || value.endsWith("K")){
               value = value.substring(0, value.length() - 1);
               return Integer.parseInt(value) * 1024;
            }else if(value.endsWith("m") || value.endsWith("M")){
               value = value.substring(0, value.length() - 1);
               return Integer.parseInt(value) * 1024 * 1024;
            }else if(value.endsWith("g") || value.endsWith("G")){
               value = value.substring(0, value.length() - 1);
               return Integer.parseInt(value) * 1024 * 1024 * 1024;
            }
            return Integer.parseInt(value);
         }catch(Exception e){
         }
      }
      return defaultValue;
   }

   //============================================================
   // <T>将一个整数数值格式化为字符串。</T>
   //
   // @param value 整数数值
   // @param length 格式化长度
   // @return 字符串
   //============================================================
   public static String format(int value,
                               int length){
      return RString.leftPad(Integer.toString(value), length, '0');
   }

   //============================================================
   // <T>将一个整数字符串格式化为字符串。</T>
   //
   // @param value 整数字符串
   // @param length 格式化长度
   // @return 字符串
   //============================================================
   public static String format(String value,
                               int length){
      return RString.leftPad(value, length, '0');
   }

   //============================================================
   // <T>将一个整数格式化为每长度插入一个间隔符。</T>
   //
   // @param value 整数
   // @return 字符串
   //============================================================
   public static String formatGroup(int value){
      return formatGroup(Integer.toString(value), DEFAULT_GROUP_LENGTH, DEFAULT_GROUP_CHAR);
   }

   //============================================================
   // <T>将一个字符串格式化为每长度插入一个间隔符。</T>
   //
   // @param value 整数字符串
   // @return 字符串
   //============================================================
   public static String formatGroup(String value){
      return formatGroup(value, DEFAULT_GROUP_LENGTH, DEFAULT_GROUP_CHAR);
   }

   //============================================================
   // <T>将一个字符串格式化为每长度插入一个间隔符。</T>
   //
   // @param value 整数字符串
   // @param length 格式化长度
   // @param split 间隔符
   // @return 字符串
   //============================================================
   public static String formatGroup(String value,
                                    int length,
                                    String split){
      StringBuilder result = new StringBuilder();
      int stringLength = value.length();
      char[] chars = new char[stringLength];
      value.getChars(0, stringLength, chars, 0);
      int splitCount = stringLength % length;
      if(splitCount == 0){
         splitCount = length;
      }
      for(int n = 0; n < stringLength; n++){
         if(n != 0){
            splitCount = length;
         }
         for(int m = 0; m < splitCount; m++){
            result.append(chars[n + m]);
         }
         if(n + splitCount < stringLength){
            result.append(split);
         }
         n += (splitCount - 1);
      }
      return result.toString();
   }

   //============================================================
   // <T>将一个整数转换为字节数组。</T>
   //
   // @param value 整数
   // @return 字节数组
   //============================================================
   public final static byte[] toBytes(int value){
      byte[] data = new byte[BYTE_LENGTH];
      data[0] = (byte)(value & 0xFF);
      data[1] = (byte)((value >>> 8) & 0xFF);
      data[2] = (byte)((value >>> 16) & 0xFF);
      data[3] = (byte)((value >>> 24) & 0xFF);
      return data;
   }

   //============================================================
   // <T>将一个整数转换为字节数组。</T>
   //
   // @param value 整数
   // @param data 字节数组
   // @param index 索引位置
   //============================================================
   public final static void toBytes(int value,
                                    byte[] data,
                                    int index){
      data[index] = (byte)(value & 0xFF);
      data[index + 1] = (byte)((value >>> 8) & 0xFF);
      data[index + 2] = (byte)((value >>> 16) & 0xFF);
      data[index + 3] = (byte)((value >>> 24) & 0xFF);
   }
   //   public static byte[] fromInteger(int value){
   //      return new byte[] { (byte)(value & 255), (byte)((value >> 8) & 255), (byte)((value >> 16) & 255), (byte)((value >> 24) & 255) };
   //   }
   //
   //   public static byte[] fromIntegers(int[] data,
   //                                     int offset,
   //                                     int length){
   //      int size = length << 2;
   //      byte[] result = new byte[size];
   //      int index = 0;
   //      for(int n = 0; n < length; index++, n += 4){
   //         result[n] = (byte)(data[index] & 255);
   //         result[n + 1] = (byte)((data[index] >> 8) & 255);
   //         result[n + 2] = (byte)((data[index] >> 16) & 255);
   //         result[n + 3] = (byte)((data[index] >> 32) & 255);
   //      }
   //      return result;
   //   }
   //
   //   //   /**
   //   //    * <p>将一个字符串转为数字，进行加法操作，返回字符串类型</p>
   //   //    * <p>create date:2005/02/14</p>
   //   //    *
   //   //    * @param sValue 字符串
   //   //    * @param nValue 加数
   //   //    * @return 字符串
   //   //    */
   //   //   public static String addAsString(String sValue,
   //   //                                    int nValue){
   //   //      return String.valueOf(toInteger(sValue, 0) + nValue);
   //   //   }
   //   //
   //   //   /**
   //   //    * <p>将一个整数字符串格式化为字符值</p>
   //   //    * <p>create date:2005/02/14</p>
   //   //    *
   //   //    * @param sValue 整数字符值
   //   //    * @param nLength 格式化长度
   //   //    * @param sChar 补足长度用的字符值
   //   //    * @return 字符值
   //   //    */
   //   //   public static String format(String sValue,
   //   //                               int nLength,
   //   //                               String sChar){
   //   //      return FStringUtil.leftPad(sValue, nLength, sChar);
   //   //   }
   //
   //   /**
   //    * <p>将一个整数转换为字节数组</p>
   //    * <p>当转换长度为0时，转换为最短长度的字节数组</p>
   //    * <p>create date:2005/01/02</p>
   //    *
   //    * @param nValue 整数
   //    * @param nLength 转换字节数组的长度
   //    * @return 字节数组
   //    */
   //   public final static byte[] getBytes(int nValue,
   //                                       int nLength){
   //      if (nLength == 0) {
   //         if ((nValue & 0xFF000000) > 0) {
   //            nLength = 4;
   //         } else if ((nValue & 0x00FF0000) > 0) {
   //            nLength = 3;
   //         } else if ((nValue & 0x0000FF00) > 0) {
   //            nLength = 2;
   //         } else if ((nValue & 0x000000FF) > 0) {
   //            nLength = 1;
   //         }
   //      } else if (nLength > 4) {
   //         throw new IllegalArgumentException("Byte array size > 4 !");
   //      }
   //      byte[] oInteger = new byte[nLength];
   //      for (int i = 0; i < nLength; i++) {
   //         oInteger[i] = (byte) (nValue & 0x000000FF);
   //         nValue >>= 8;
   //      }
   //      return oInteger;
   //   }
   //
   //   /**
   //    * <p>将一个字节数组转换为整数</p>
   //    * <p>create date:2005/01/02</p>
   //    *
   //    * @param oInteger 字节数组
   //    * @return 整数
   //    */
   //   public final static int getInteger(byte[] oInteger){
   //      if (oInteger == null) {
   //         throw new IllegalArgumentException("Byte array is null!");
   //      }
   //      if (oInteger.length > 4) {
   //         throw new IllegalArgumentException("Byte array size > 4 !");
   //      }
   //      int nResult = 0;
   //      for (int i = oInteger.length - 1; i >= 0; i--) {
   //         nResult <<= 8;
   //         nResult |= (oInteger[i] & 0x000000FF);
   //      }
   //      return nResult;
   //   }
   //
   //   /**
   //    * <p>将一个字符串转为数字，进行减法操作，返回字符串类型</p>
   //    * <p>create date:2005/02/14</p>
   //    *
   //    * @param sValue 字符串
   //    * @param nValue 减数
   //    * @return 字符串
   //    */
   //   public static String subAsString(String sValue,
   //                                    int nValue){
   //      return String.valueOf(toInteger(sValue, 0) - nValue);
   //   }
   //
   //   /**
   //    * <p>将一个整数类型格式化为16进制字符串</p>
   //    * <p>create date:2005/02/14</p>
   //    *
   //    * @param nValue 整数类型
   //    * @return 16进制字符串
   //    */
   //   public static String toHexString(int nValue){
   //      return toHexString(nValue, -1, " ");
   //   }
   //   /**
   //    * <p>将字节转换为整数数值</p>
   //    * <p>create date:2005/02/14</p>
   //    *
   //    * @param btValue1 字节1
   //    * @param btValue2 字节2
   //    * @return 整数数值
   //    */
   //   public static int toInteger(byte btValue1,
   //                               byte btValue2){
   //      return ((int) btValue1) * 256 + (int) btValue2;
   //   }
   //
}
