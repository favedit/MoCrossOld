package org.mo.com.lang;

import org.mo.com.lang.type.RBaseShort;

//============================================================
// <T>短整数数据类型工具类。</T>
//
// @history 051012 MAOCY 创建
//============================================================
public class RShort
      extends RBaseShort
{
   // 存储位长度
   public final static int BIT_LENGTH = 16;

   // 存储字节长度
   public final static int BYTE_LENGTH = 2;

   // 内存容量
   public final static int CAPACITY = 32;

   // 内存增长率
   public final static int MULTIPLIER = 2;

   //============================================================
   // <T>将一个对象变换为短整数。</T>
   // <P>如果变换失败，将得到整数类型的默认值，不会产生错误</P>
   //
   // @param value 对象
   // @return 短整数
   //============================================================
   public static short parse(String value){
      if(null != value){
         return parse(value, DEFAULT);
      }
      return DEFAULT;
   }

   //============================================================
   // <T>将一个对象变换为短整数。</T>
   // <P>如果变换失败，将得到整数类型的默认值，不会产生错误</P>
   //
   // @param value 对象
   // @param defaultValue 默认值
   // @return 短整数
   //============================================================
   public static short parse(String value,
                             short defaultValue){
      try{
         return Short.parseShort(value);
      }catch(Exception exception){
      }
      return defaultValue;
   }

   //============================================================
   // <T>将一个对象变换为短整数。</T>
   // <P>如果变换失败，将得到整数类型的默认值，不会产生错误</P>
   //
   // @param value 对象
   // @return 短整数
   //============================================================
   public static short parse(Object item){
      if(null != item){
         // 是否为数字
         if(item instanceof Short){
            return ((Short)item).shortValue();
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
            return Short.parseShort(value);
         }
      }
      return DEFAULT;
   }

   //============================================================
   // <T>将一个对象变换为短整数。</T>
   // <P>如果变换失败，将得到整数类型的默认值，不会产生错误</P>
   //
   // @param value 对象
   // @param defaultValue 默认值
   // @return 短整数
   //============================================================
   public static short parse(Object item,
                             short defaultValue){
      if(null != item){
         return parse(item.toString(), defaultValue);
      }
      return defaultValue;
   }

   //============================================================
   // <T>将一个短整数转换为字节数组。</T>
   //
   // @param value 短整数
   // @return 字节数组
   //============================================================
   public final static byte[] toBytes(short value){
      byte[] data = new byte[BYTE_LENGTH];
      data[0] = (byte)(value & 0xFF);
      data[1] = (byte)((value >>> 8) & 0xFF);
      return data;
   }

   //============================================================
   // <T>将一个短整数转换为字节数组。</T>
   //
   // @param value 短整数
   // @param data 字节数组
   // @param index 索引位置
   //============================================================
   public final static void toBytes(short value,
                                    byte[] data,
                                    int index){
      data[index] = (byte)(value & 0xFF);
      data[index + 1] = (byte)((value >>> 8) & 0xFF);
   }
}
