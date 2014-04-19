package org.mo.com.lang;

import org.mo.com.lang.type.RBaseByte;

//============================================================
// <T>字节数据类型工具。</T>
// <P>字节序默认为Little Endian</P>
//
// @history 130220 创建
//============================================================
public class RByte
      extends RBaseByte
{
   // 存储位长度
   public final static int BIT_LENGTH = 8;

   // 存储字节长度
   public final static int BYTE_LENGTH = 1;

   // 内存容量
   public final static int CAPACITY = 32;

   // 内存增长率
   public final static int MULTIPLIER = 2;

   //============================================================
   // <T>从字节数组的指定索引位置，获得一个16位整数。</T>
   //
   // @param data 字节数组
   // @param index 索引位置
   // @return 16位整数
   //============================================================
   public final static short getShort(byte[] data,
                                      int index){
      int v1 = data[index] & 0xFF;
      int v2 = data[index + 1] & 0xFF;
      return (short)(v1 + (v2 << 8));
   }

   //============================================================
   // <T>从字节数组的指定索引位置，设置一个16位整数。</T>
   //
   // @param data 字节数组
   // @param index 索引位置
   // @param value 16位整数
   //============================================================
   public final static void setShort(byte[] data,
                                     int index,
                                     short value){
      data[index++] = (byte)(value);
      data[index] = (byte)(value >>> 8);
   }

   //============================================================
   // <T>从字节数组的指定索引位置，获得一个32位整数。</T>
   //
   // @param data 字节数组
   // @param index 索引位置
   // @return 32位整数
   //============================================================
   public final static int getInteger(byte[] data,
                                      int offset){
      return (data[offset] & 0xFF) + ((data[offset + 1] & 0xFF) << 8) + ((data[offset + 2] & 0xFF) << 16) + ((data[offset + 3] & 0xFF) << 24);
   }

   //============================================================
   // <T>从字节数组的指定索引位置开始，指定长度的内容转换成32位整数数组。</T>
   //
   // @param data 字节数组
   // @param index 索引位置
   // @return 32位整数数组
   //============================================================
   public final static int[] getIntegers(byte[] data,
                                         int offset,
                                         int length){
      int size = length >> 2;
      if(length % 4 != 0){
         size++;
      }
      int[] result = new int[size];
      int index = 0;
      for(int n = 0; n < length; index++, n += 4){
         result[index] = data[n] + data[n + 1] << 8 + data[n + 2] << 16 + data[n + 3] << 24;
      }
      return result;
   }

   //============================================================
   // <T>从字节数组的指定索引位置，设置一个32位整数。</T>
   //
   // @param values 字节数组
   // @param index 索引位置
   // @param value 32位整数
   //============================================================
   public final static void setInteger(byte[] data,
                                       int offset,
                                       int value){
      data[offset++] = (byte)value;
      data[offset++] = (byte)(value >>> 8);
      data[offset++] = (byte)(value >>> 16);
      data[offset] = (byte)(value >>> 24);
   }

   //============================================================
   // <T>将一个16进制字符串转换为一个字节数组。</T>
   // <P>字符串必须为偶数长度，每2位转换为一个字节，第一个字符为高4位，第二个字符为低四位。</P>
   //
   // @param source 字符串
   // @return 字节数组
   //============================================================
   public final static byte[] fromHexString(String source){
      // 变换为字符串
      char[] chars = source.toCharArray();
      if(0 != chars.length % 2){
         throw new Error("Source length invalid. (source=" + source + ", length=" + chars.length + ")");
      }
      // 变换为字节
      int index = 0;
      int count = chars.length;
      byte[] bytes = new byte[count >> 1];
      for(int n = 0; n < count; n += 2){
         bytes[index++] = (byte)((RHex.parse(chars[n]) << 4) + RHex.parse(chars[n + 1]));
      }
      return bytes;
   }

   //============================================================
   // <T>将一个字节转换成16进制字符数组。</T>
   //
   // @param value 内容
   // @return 字符数组
   //============================================================
   public final static char[] toHexChars(byte value){
      return new char[]{RHex.HEX_CHARS[(value >> 4) & 0x0F], RHex.HEX_CHARS[value & 0x0F]};
   }

   //============================================================
   // <T>将一个字节转换成16进制字符数组。</T>
   //
   // @param source 字符串
   // @param value 内容
   //============================================================
   public final static void toHexChars(StringBuilder source,
                                       byte value){
      source.append(RHex.HEX_CHARS[(value >> 4) & 0x0F]);
      source.append(RHex.HEX_CHARS[value & 0x0F]);
   }

   //============================================================
   // <T>将一个字节数组格式化成显示字符串。</T>
   //
   // @param data 数据
   // @param offset 位置
   // @param length 长度
   // @return 字符串
   //============================================================
   public final static String toDisplay(byte[] data,
                                        int offset,
                                        int length){
      StringBuilder result = new StringBuilder();
      int n = offset - 1;
      length = Math.min(length, data.length - offset);
      while(++n < length){
         char ch = (char)data[n];
         if(ch >= ' ' && ch < 'z'){
            result.append((char)data[n]);
         }else{
            result.append('.');
         }
      }
      return result.toString();
   }

   //============================================================
   // <T>将一个字节数组格式化成字符串。</T>
   //
   // @param data 数据
   // @param offset 位置
   // @param length 长度
   // @return 字符串
   //============================================================
   public final static String toString(byte[] data){
      return toString(data, 0, data.length);
   }

   //============================================================
   // <T>将一个字节数组格式化成字符串。</T>
   //
   // @param data 数据
   // @param offset 位置
   // @param length 长度
   // @return 字符串
   //============================================================
   public final static String toString(byte[] data,
                                       int offset,
                                       int length){
      StringBuilder result = new StringBuilder();
      int n = offset - 1;
      length = Math.min(length, data.length - offset);
      while(++n < length){
         if(n > offset){
            result.append(' ');
         }
         result.append(RByte.toHexChars(data[n]));
      }
      return result.toString();
   }

   //============================================================
   // <T>将一个字节数组转换为16进制的字符串。</T>
   // <P>一个字节转换为2个字符，高4位在为第一个字符，低4位为第二个字符。</P>
   //
   // @param data 数据
   // @return 字符串
   //============================================================
   public final static String toHexString(byte[] bytes){
      int loop = bytes.length;
      StringBuilder buffer = new StringBuilder(loop + loop);
      for(int n = 0; n < loop; n++){
         toHexChars(buffer, bytes[n]);
      }
      return buffer.toString();
   }

   //============================================================
   // <T>将一个字节数组转换为16进制的字符串。</T>
   // <P>一个字节转换为2个字符，高4位在为第一个字符，低4位为第二个字符。</P>
   //
   // @param data 数据
   // @param offset 位置
   // @param length 长度
   // @return 字符串
   //============================================================
   public final static String toHexString(byte[] data,
                                          int offset,
                                          int length){
      int loop = offset + length;
      StringBuilder result = new StringBuilder(loop + loop);
      for(int n = offset; n < loop; n++){
         RByte.toHexChars(result, data[n]);
      }
      return result.toString();
   }
}
