package org.mo.com.lang;

import org.mo.com.lang.type.RBaseFloat;

//============================================================
// <T>浮点数据类型工具类。</T>
//
// @history 051012 MAOCY 创建
//============================================================
public class RFloat
      extends RBaseFloat
{
   // 存储位长度
   public final static int BIT_LENGTH = 32;

   // 存储字节长度
   public final static int BYTE_LENGTH = 4;

   // 内存容量
   public final static int CAPACITY = 32;

   // 内存增长率
   public final static int MULTIPLIER = 2;

   //============================================================
   // <T>判断字符串是否为浮点数。</T>
   //
   // @param value 字符串
   // @return 浮点数
   //============================================================
   public static boolean isFloat(String value){
      return RString.isPartten(value, "+-.n");
   }

   //============================================================
   // <T>将一个字符串变换为浮点数。</T>
   // <P>如果变换失败，将得到浮点类型的默认值，不会产生错误。</P>
   //
   // @param value 字符串
   // @return 浮点数
   //============================================================
   public static float parse(String value){
      if(null != value){
         return parse(value, DEFAULT);
      }
      return DEFAULT;
   }

   //============================================================
   // <T>将一个字符串变换为浮点数。</T>
   // <P>如果变换失败，将得到浮点类型的默认值，不会产生错误。</P>
   //
   // @param value 字符串
   // @param defaultValue 默认值
   // @return 浮点数
   //============================================================
   public static float parse(String value,
                             float defaultValue){
      try{
         return Float.parseFloat(value);
      }catch(Exception oException){
      }
      return defaultValue;
   }

   //============================================================
   // <T>将一个对象变换为浮点数。</T>
   // <P>如果变换失败，将得到浮点类型的默认值，不会产生错误。</P>
   //
   // @param item 对象
   // @return 浮点数
   //============================================================
   public static float parse(Object item){
      if(null != item){
         // 是否为数字
         if(item instanceof Float){
            return ((Float)item).floatValue();
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
            return Float.parseFloat(value);
         }
      }
      return DEFAULT;
   }

   //============================================================
   // <T>将一个对象变换为浮点数。</T>
   // <P>如果变换失败，将得到浮点类型的默认值，不会产生错误。</P>
   //
   // @param item 对象
   // @param defaultValue 默认值
   // @return 浮点数
   //============================================================
   public static float parse(Object item,
                             float defaultValue){
      if(null != item){
         return parse(item.toString(), defaultValue);
      }
      return defaultValue;
   }
   //   /**
   //    * <p>将一个浮点数值格式化为字符串</p>
   //    * <p>create date:2005/02/14</p>
   //    *
   //    * @param fValue 浮点数
   //    * @param nLength 格式化长度
   //    * @return 字符串
   //    */
   //   public static String format(float fValue,
   //                               int nLength){
   //      return RString.leftPad(Float.toString(fValue), nLength, "0");
   //   }
   //
   //   /**
   //    * <p>将一个浮点数值格式化为字符串</p>
   //    * <p>create date:2005/02/14</p>
   //    *
   //    * @param fValue 浮点数
   //    * @param nLength 格式化长度
   //    * @param sChar 补足长度用的字符串
   //    * @return 字符串
   //    */
   //   public static String format(float fValue,
   //                               int nLength,
   //                               String sChar){
   //      return RString.leftPad(Float.toString(fValue), nLength, sChar);
   //   }
   //
   //   /**
   //    * <p>将一个浮点字符串格式化为字符串</p>
   //    * <p>create date:2005/02/14</p>
   //    *
   //    * @param sValue 浮点字符串
   //    * @param nLength 格式化长度
   //    * @return 字符串
   //    */
   //   public static String format(String sValue,
   //                               int nLength){
   //      return RString.leftPad(sValue, nLength, "0");
   //   }
   //
   //   /**
   //    * <p>将一个浮点字符串格式化为字符串</p>
   //    * <p>create date:2005/02/14</p>
   //    *
   //    * @param sValue 浮点字符串
   //    * @param nLength 格式化长度
   //    * @param sChar 补足长度用的字符串
   //    * @return 字符串
   //    */
   //   public static String format(String sValue,
   //                               int nLength,
   //                               String sChar){
   //      return RString.leftPad(sValue, nLength, sChar);
   //   }
   //
   //   /**
   //    * <p>将一个字符串数组变换为浮点数组</p>
   //    * <p>如果变换失败，将得到浮点类型的默认值，不会产生错误</p>
   //    * <p>create date:2005/02/14</p>
   //    *
   //    * @param arValue 字符串数组
   //    * @return 浮点数组
   //    */
   //   public static float[] toFloat(String[] arValue){
   //      int nSize = arValue.length;
   //      float[] arResult = new float[nSize];
   //      for(int n = 0; n < nSize; n++){
   //         arResult[n] = toFloat(arValue[n]);
   //      }
   //      return arResult;
   //   }
}
