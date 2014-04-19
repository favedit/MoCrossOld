/*
 * @(#)ICpNumber.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.data;

import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlProcedure;

/**
 * <T>数据库逻辑包(CP_NUMBER)的代理对象接口</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public interface ICpNumber
{
   /**
    * <p>将字符串键值转化为NUMBER型并将‘，’用‘’代替。</p>
    *
    * @param value 输入值
    * @return NUMBER
    */
   FSqlFunction parse(Object value);

   /**
    * <p>将NUMBER型键值转化为char型并将其以默认日期格式返回。</p>
    *
    * @param value
    * @return TVARCHAR2
    */
   FSqlFunction toString(Object value);

   /**
    * <p>检验value 的字符中是否是由纯数字组成。</p>
    *
    * @param value 输入值
    * @return BOOLEN
    */
   FSqlFunction isNumber(Object value);

   /**
    * <p>比较value1 和Value2 大小。</p>
    *
    * @param value1 比较值1
    * @param value2 比较值2
    * @return INTEGER
    */
   FSqlFunction minNumber(Object value1,
                          Object value2);

   /**
    * <p>比较value1 和Value2 大小。</p>
    *
    * @param value1 比较值1
    * @param value2 比较值2
    * @return INTEGER
    */
   FSqlFunction maxNumber(Object value1,
                          Object value2);

   /**
    * <p>如果value小于设定的min_range_ 则返回 value + min_sum_ </p>
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
    * <p>判定两个数值是否相等。</p>
    *
    * @param value1 被比较值
    * @param value2
    * @param case1 键值1、2相等则输出
    * @param case2 键值1、2不相等则输出
    * @return INTEGER
    */
   FSqlFunction caseValue(Object value1,
                          Object value2,
                          Object case1,
                          Object case2);

   /**
    * <p>将键值除以1024并按照指定格式输出为字符串型。</p>
    *
    * @param value
    * @return VARCHAR2
    */
   FSqlFunction formatKilobyte(Object value);

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
