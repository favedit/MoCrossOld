package org.mo.com.lang;

import java.text.DecimalFormat;

import org.mo.com.lang.type.RBaseDouble;

//============================================================
// <T>双精度数据类型工具类。</T>
//
// @history 051012 MAOCY 创建
//============================================================
public class RDouble
      extends RBaseDouble
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
   // <T>判断字符串是否为双精度数。</T>
   //
   // @param value 字符串
   // @return 双精度数
   //============================================================
   public static boolean isDouble(String value){
      return RString.isPartten(value, "+-.n");
   }

   //============================================================
   // <T>将一个字符串变换为双精度数。</T>
   // <P>如果变换失败，将得到双精度类型的默认值，不会产生错误。</P>
   //
   // @param value 字符串
   // @return 双精度数
   //============================================================
   public static double parse(String value){
      if(null != value){
         return parse(value, DEFAULT);
      }
      return DEFAULT;
   }

   //============================================================
   // <T>将一个字符串变换为双精度数。</T>
   // <P>如果变换失败，将得到双精度类型的默认值，不会产生错误。</P>
   //
   // @param sValue 字符串
   // @param defaultValue 默认值
   // @return 双精度数
   //============================================================
   public static double parse(String value,
                              double defaultValue){
      try{
         return Double.parseDouble(value);
      }catch(Exception oException){
      }
      return defaultValue;
   }

   //============================================================
   // <T>将一个对象变换为双精度数。</T>
   // <P>如果变换失败，将得到双精度类型的默认值，不会产生错误。</P>
   //
   // @param item 对象
   // @return 双精度数
   //============================================================
   public static double parse(Object item){
      if(null != item){
         // 是否为数字
         if(item instanceof Double){
            return ((Double)item).doubleValue();
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
            return Double.parseDouble(value);
         }
      }
      return DEFAULT;
   }

   //============================================================
   // <T>将一个对象变换为双精度数。</T>
   // <P>如果变换失败，将得到双精度类型的默认值，不会产生错误。</P>
   //
   // @param item 对象
   // @param defaultValue 默认值
   // @return 双精度数
   //============================================================
   public static double parse(Object item,
                              double defaultValue){
      if(null != item){
         return parse(item.toString(), defaultValue);
      }
      return defaultValue;
   }
   //============================================================
   // <T>计算百分比</T>
   //
   // @param use 已使用
   // @param total 总量
   // @return percent 百分比
   //============================================================
   public static double calculatePercent(long used,
                                         long total){
	  double per = ((total - used)*1.0)/(total*1.0)*100;
	  DecimalFormat df = new DecimalFormat("#####0.00");
	  double percent =RDouble.parse(df.format(per).toString());
      return percent;
   }
   //   /**
   //    * <p>将一个双精度数值格式化为字符串</p>
   //    * <p>create date:2005/02/14</p>
   //    *
   //    * @param fValue 双精度数
   //    * @param nLength 格式化长度
   //    * @return 字符串
   //    */
   //   public static String format(double fValue,
   //                               int nLength){
   //      return RString.leftPad(double.toString(fValue), nLength, "0");
   //   }
   //
   //   /**
   //    * <p>将一个双精度数值格式化为字符串</p>
   //    * <p>create date:2005/02/14</p>
   //    *
   //    * @param fValue 双精度数
   //    * @param nLength 格式化长度
   //    * @param sChar 补足长度用的字符串
   //    * @return 字符串
   //    */
   //   public static String format(double fValue,
   //                               int nLength,
   //                               String sChar){
   //      return RString.leftPad(double.toString(fValue), nLength, sChar);
   //   }
   //
   //   /**
   //    * <p>将一个双精度字符串格式化为字符串</p>
   //    * <p>create date:2005/02/14</p>
   //    *
   //    * @param sValue 双精度字符串
   //    * @param nLength 格式化长度
   //    * @return 字符串
   //    */
   //   public static String format(String sValue,
   //                               int nLength){
   //      return RString.leftPad(sValue, nLength, "0");
   //   }
   //
   //   /**
   //    * <p>将一个双精度字符串格式化为字符串</p>
   //    * <p>create date:2005/02/14</p>
   //    *
   //    * @param sValue 双精度字符串
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
   //    * <p>将一个字符串数组变换为双精度数组</p>
   //    * <p>如果变换失败，将得到双精度类型的默认值，不会产生错误</p>
   //    * <p>create date:2005/02/14</p>
   //    *
   //    * @param arValue 字符串数组
   //    * @return 双精度数组
   //    */
   //   public static double[] todouble(String[] arValue){
   //      int nSize = arValue.length;
   //      double[] arResult = new double[nSize];
   //      for(int n = 0; n < nSize; n++){
   //         arResult[n] = todouble(arValue[n]);
   //      }
   //      return arResult;
   //   }
}
