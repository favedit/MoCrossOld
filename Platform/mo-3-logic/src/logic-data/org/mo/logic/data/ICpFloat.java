/*
 * @(#)ICpFloat.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.data;

import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlProcedure;

/**
 * <T>数据库逻辑包(CP_FLOAT)的代理对象接口</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public interface ICpFloat
{
   /**
    * <T>判断指定字段的数据内容的长度是否符合指定长度。</T>
    * <P>判断传入的数据内容的长度是否符合指定长度，如果符合指定长度，则返回真，否则返回假。</P>
    *
    * @param value 数据内容
    * @param length 规定长度
    * @return 是否符合指定长度
    */
   FSqlFunction testLength(Object value,
                           Object length);

   /**
    * <T>判断指定字段的数据内容的长度是否大于该字段所规定的最小长度。 </T>
    * <P>判断传入的指定字段的数据内容的长度是否大于该字段所规定的最小长度，如果大于，则表示该数据合法，返<B/>
    * 回真；否则返回假。</P>
    *
    * @param value 数据内容
    * @param length 规定长度
    * @return 是否大于字段的最小长度
    */
   FSqlFunction testLengthMin(Object value,
                              Object length);

   /**
    * <T>判断指定字段的数据内容的长度是否小于该字段所规定的最大长度。</T>
    * <P>判断传入的指定字段的数据内容的长度是否小于该字段所规定的最大长度，如果小于，则表示该数据合法，返<B/>
    * 回真；否则返回假。</P>
    *
    * @param value 数据内容
    * @param length 规定长度
    * @return 是否小于字段的最大长度
    */
   FSqlFunction testLengthMax(Object value,
                              Object length);

   /**
    * <T>判断指定字段的数据内容是否是浮点数。 </T>
    * <P>判断指定字段的数据内容是否是一个浮点数，如果是，表示该数据合法,返回真；否则返回假。</P>
    *
    * @param value 数据内容
    * @return 是否是浮点数
    */
   FSqlFunction testFloat(Object value);

   /**
    * <T>判断指定字段的数据内容所规定的最小值是否大于指定数值。</T>
    * <P>判断指定字段的数据内容所规定的最小值是否大于指定数值，如果大于，表示数据的最小值符合规定，返回真<B/>
    * 否则返回假。</P>
    *
    * @param minValue 字段的最小值
    * @param minMinValue 字段最小值的最小值
    * @return 是否大于指定数值
    */
   FSqlFunction testGreater(Object minValue,
                            Object minMinValue);

   /**
    * <T>判断指定字段的数据内容所规定的最大值是否小于指定数值。</T>
    * <P>判断指定字段的数据内容所规定的最大值是否小于指定数值，如果小于，返回真，否则返回假。</P>
    *
    * @param maxValue 字段的最小值
    * @param maxMaxValue 字段最大值的最小值
    * @return 是否大于指定数值
    */
   FSqlFunction testLess(Object maxValue,
                         Object maxMaxValue);

   /**
    * <T>判断指定字段的数据内容所规定的最小值是否大于等于指定数值。</T>
    * <P>判断指定字段的数据内容所规定的最小值是否大于等于指定数值，如果大于等于，返回真，否则返回假。</P>
    *
    * @param minValue 字段的最小值
    * @param minMinValue 字段最小值的最小值
    * @return 是否大于等于指定数值
    */
   FSqlFunction testGreaterEq(Object minValue,
                              Object minMinValue);

   /**
    * <T>检查指定字段的数据内容所规定的最大值是否小于等于指定数值。</T>
    * <P>检查指定字段的数据内容所规定的最小值是否小于等于指定数值，如果小于等于，则返回真，否则返回假。</P>
    *
    * @param maxValue 字段的最小值
    * @param maxMaxValue 字段最大值的最小值
    * @return 是否小于等于指定数值
    */
   FSqlFunction testLessEq(Object maxValue,
                           Object maxMaxValue);

   /**
    * <T>判断指定字段的数据内容是否在指定的范围之内。</T>
    * <P>判断指定字段的数据内容是否在指定的范围之内，如果不在，则返回false,否则返回true。</P>
    *
    * @param value 数据内容
    * @param minValue 范围上限
    * @param maxValue 范围下限
    * @return 是否在范围之内
    */
   FSqlFunction testBetween(Object value,
                            Object minValue,
                            Object maxValue);

   /**
    * <T>判断指定字段的数据内容是否在指定的范围之内。</T>
    * <P>判断指定字段的数据内容是否在指定的范围之内，如果不在，则返回false,否则返回true。</P>
    *
    * @param value 数据内容
    * @param minValue 范围上限
    * @param maxValue 范围下限
    * @return 是否在范围之内
    */
   FSqlFunction testRange(Object value,
                          Object minValue,
                          Object maxValue);

   /**
    * <T>将字符串键值转化为NUMBER型并将‘，’用‘’代替。</T>
    *
    * @param value 输入值
    * @return NUMBER
    */
   FSqlFunction parse(Object value);

   /**
    * <T>将NUMBER型键值转化为char型并将其以默认日期格式返回。</T>
    *
    * @param value
    * @return TVARCHAR2
    */
   FSqlFunction toString(Object value);

   /**
    * <T>检验value 的字符中是否是由纯数字组成。</T>
    *
    * @param value 被判断值
    * @return BOOLEN
    */
   FSqlFunction isFloat(Object value);

   /**
    * <T>比较value1 和Value2 大小。</T>
    *
    * @param value1 比较值
    * @param value2 比较值
    * @return INTEGER
    */
   FSqlFunction minNumber(Object value1,
                          Object value2);

   /**
    * <T>比较value1 和Value2 大小。</T>
    *
    * @param value1 比较值
    * @param value2 比较值
    * @return INTEGER
    */
   FSqlFunction maxNumber(Object value1,
                          Object value2);

   /**
    * <T>如果value小于设定的min_range_ 则返回 value + min_sum_ </T>
    *
    * @param value 比较值
    * @param minRange 被比较最大值
    * @param minSum
    * @return INTEGER
    */
   FSqlFunction fixMinNumber(Object value,
                             Object minRange,
                             Object minSum);

   /**
    * <T>判定两个数值是否相等。</T>
    *
    * @param value1 被比较值
    * @param value2
    * @param case1 键值1、2相等则输出
    * @param case2 键值1、2不相等则输出
    * @return
    */
   FSqlFunction caseValue(Object value1,
                          Object value2,
                          Object case1,
                          Object case2);

   /**
    * <T>检查指定字段的数据内容的长度是否符合指定长度。</T>
    * <P>检查传入的数据内容的长度是否符合指定长度，如果不符合指定长度，则报出逻辑错误。<B/>
    * 错误格式为：“The length of {0} must be {1} characters.”其中：<B/>
    * <L value='{0}'>字段名称</L>
    * <L value='{1}'>数据内容长度</L>
    * </P>
    *
    * @param value 数据内容
    * @param length 规定长度
    * @param logic 数据集名称
    * @param field 字段名称
    */
   FSqlProcedure checkLength(Object value,
                             Object length,
                             Object logic,
                             Object field);

   /**
    * <T>检查指定字段的数据内容的长度是否大于该字段所规定的最小长度。 </T>
    * <P>检查传入的指定字段的数据内容的长度是否大于该字段所规定的最小长度，如果大于，则表示该数据合法；否<B/>
    * 则报出具体的逻辑错误。<B/>
    * 错误格式为：“The length of {0} must be greater than {1}.”其中：
    * <L value='{0}'>字段名称</L>
    * <L value='{1}'>规定最小长度</L>
    * </P>
    *
    * @param value 数据内容
    * @param length 规定长度
    * @param logic 数据集名称
    * @param field 字段名称
    */
   FSqlProcedure checkLengthMin(Object value,
                                Object length,
                                Object logic,
                                Object field);

   /**
    * <T>检查指定字段的数据内容的长度是否小于该字段所规定的最大长度。</T>
    * <P>检查传入的指定字段的数据内容的长度是否小于该字段所规定的最大长度，如果小于，则表示该数据合法；否<B/>
    * 则报出具体的逻辑错误。<B/>
    * 错误格式为：“The length of {0} must be less than {1}.”其中：
    * <L value='{0}'>字段名称</L>
    * <L value='{1}'>规定最大长度</L>
    * </P>
    *
    * @param value 数据内容
    * @param length 规定长度
    * @param logic 数据集名称
    * @param field 字段名称
    */
   FSqlProcedure checkLengthMax(Object value,
                                Object length,
                                Object logic,
                                Object field);

   /**
    * <T>检查指定字段的数据内容是否是浮点数。 </T>
    * <P>检查指定字段的数据内容是否是一个浮点数，如果是，表示该数据合法；否则报出具体的逻辑错误。<B/>
    * 错误格式为：“{0} is not a float.”其中：
    * <L value='{0}'>字段名称</L>
    * </P>
    *
    * @param value 数据内容
    * @param logic 数据集名称
    * @param field 字段名称
    */
   FSqlProcedure checkFloat(Object value,
                            Object logic,
                            Object field);

   /**
    * <T>检查指定字段的数据内容所规定的最小值是否大于指定数值。</T>
    * <P>检查指定字段的数据内容所规定的最小值是否大于指定数值，如果大于，表示数据的最小值符合规定，否则报<B/>
    * 出具体的逻辑错误。<B/>
    * 错误格式为：“The minimum value of {0} must be greater than {1}.”其中：
    * <L value='{0}'>字段名称</L>
    * <L value='{1}'>字段最小值的最小值</L>
    * </P>
    *
    * @param minValue 字段的最小值
    * @param minMinValue 字段最小值的最小值
    * @param logic 数据集名称
    * @param field 字段名称
    */
   FSqlProcedure checkGreater(Object minValue,
                              Object minMinValue,
                              Object logic,
                              Object field);

   /**
    * <T>检查指定字段的数据内容所规定的最大值是否小于指定数值。</T>
    * <P>检查指定字段的数据内容所规定的最大值是否小于指定数值，如果小于，表示数据的最小值符合规定，否则报<B/>
    * 出具体的逻辑错误。<B/>
    * 错误格式为：“The maximum value of {0} must be less than {1}.”
    * <L value='{0}'>字段名称</L>
    * <L value='{1}'>字段最大值的最大值</L>
    * </P>
    *
    * @param maxValue 字段的最小值
    * @param maxMaxValue 字段最大值的最小值
    * @param logic 数据集名称
    * @param field 字段名称
    */
   FSqlProcedure checkLess(Object maxValue,
                           Object maxMaxValue,
                           Object logic,
                           Object field);

   /**
    * <T>检查指定字段的数据内容所规定的最小值是否大于等于指定数值。</T>
    * <P>检查指定字段的数据内容所规定的最小值是否大于等于指定数值，如果大于等于，则表示数据合法，否则报出逻<B/>
    * 辑错误<B/>
    * 错误格式为：“The minimum value of {0} must be equal or greater than {1}.”其中：
    * <L value='{0}'>字段名称</L>
    * <L value='{1}'>字段最小值的最小值</L>
    * </P>
    *
    * @param minValue 字段的最小值
    * @param minMinValue 字段最小值的最小值
    * @param logic 数据集名称
    * @param field 字段名称
    */
   FSqlProcedure checkGreaterEq(Object minValue,
                                Object minMinValue,
                                Object logic,
                                Object field);

   /**
    * <T>检查指定字段的数据内容所规定的最大值是否小于等于指定数值。</T>
    * <P>检查指定字段的数据内容所规定的最小值是否小于等于指定数值，如果小于等于，则表示数据合法，否则报出逻<B/>
    * 辑错误<B/>
    * 错误格式为：“The maximum value of {0} must be equal or lesser than {1}.”其中：
    * <L value='{0}'>字段名称</L>
    * <L value='{1}'>字段最大值的最小值</L>
    * </P>
    *
    * @param maxValue 字段的最小值
    * @param maxMaxValue 字段最大值的最小值
    * @param logic 数据集名称
    * @param field 字段名称
    */
   FSqlProcedure checkLessEq(Object maxValue,
                             Object maxMaxValue,
                             Object logic,
                             Object field);

   /**
    * <T>检查指定字段的数据内容是否在指定的范围之内。</T>
    * <P>检查指定字段的数据内容是否在指定的范围之内，如果不在，则报出具体的逻辑错误。
    * 错误格式为：“The value of {0} is not between {1} and {2}.”其中：
    * <L value='{0}'>字段名称</L>
    * <L value='{1}'>范围上限</L>
    * <L value='{2}'>范围下限</L>
    * </P>
    *
    * @param value 数据内容
    * @param minValue 范围上限
    * @param maxValue 范围下限
    * @param logic 数据集名称
    * @param field 字段名称
    */
   FSqlProcedure checkBetween(Object value,
                              Object minValue,
                              Object maxValue,
                              Object logic,
                              Object field);

   /**
    * <T>检查指定字段的数据内容是否在指定的范围之内。</T>
    * <P>检查指定字段的数据内容是否在指定的范围之内，如果不在，则报出具体的逻辑错误。
    * 错误格式为：“The value of {0} is not greater and equal than {1} and less than {2}.”其中：
    * <L value='{0}'>字段名称</L>
    * <L value='{1}'>范围上限</L>
    * <L value='{2}'>范围下限</L>
    * </P>
    *
    * @param value 数据内容
    * @param minValue
    * @param maxValue
    * @param logic 数据集名称
    * @param field 字段名称
    */
   FSqlProcedure checkRange(Object value,
                            Object minValue,
                            Object maxValue,
                            Object logic,
                            Object field);

   /**
    * <T>在数据集内新建一条记录</T>
    *
    * @param logic_ 业务逻辑
    * @param params_ 打包字符串
    */
   FSqlProcedure doInsert(Object logic,
                          Object params);

   /**
    * <T>根据记录的编号修改一条记录</T>
    *
    * @param logic_ 业务逻辑
    * @param params_ 打包字符串
    */
   FSqlProcedure doUpdate(Object logic,
                          Object params);

   /**
    * <T>根据记录的编号和数据集同步一条记录</T>
    * <UL>
    * <L>如果指定的记录不存在，进行新建处理。</L>
    * <L>如果指定的记录存在，进行更新处理。</L>
    * </UL>
    *
    * @param logic_ 业务逻辑
    * @param params_ 打包字符串
    */
   FSqlProcedure doSync(Object logic,
                        Object params);

   /**
    * <T>根据记录的编号删除一条记录</T>
    *
    * @param logic_ 业务逻辑
    * @param params_ 打包字符串
    */
   FSqlProcedure doDelete(Object logic,
                          Object params);
}
