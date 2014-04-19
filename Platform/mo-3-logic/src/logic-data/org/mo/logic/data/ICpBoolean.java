/*
 * @(#)ICpBoolean.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.data;

import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlProcedure;

/**
 * <T>数据库逻辑包(CP_BOOLEAN)的代理对象接口</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public interface ICpBoolean
{
   /**
    * <p>判定value_是否为N或者Y,不是则抛出例外</p>
    *
    * @param value 被判断值
    * @return Y<BR>N
    */
   FSqlFunction parse(Object value);

   /**
    * <p>判定键值是否为Y,是则返回TURE</p>
    *
    * @param value 被判断值
    * @param allowNull 控制输出null参数
    * @return Y<BR>N<BR>NULL
    */
   FSqlFunction isTrue(Object value,
                       Object allowNull);

   /**
    * <p>判定键值是否为N,是则返回TURE</p>
    *
    * @param value 被判断值
    * @param allowNull 控制输出null参数
    * @return Y<BR>N<BR>NULL
    */
   FSqlFunction isFalse(Object value,
                        Object allowNull);

   /**
    * <p>判断键值1是否为1、0、NULL，为1则返回Y，为0则返回N,为NULL则返回NULL</p>
    *
    * @param value 被判断值
    * @param allowNull 控制输出null参数
    * @return Y<BR>N<BR>NULL
    */
   FSqlFunction toString(Object value,
                         Object allowNull);

   /**
    * <p>判断键值1是否为1、0、NULL,为1则返回true_value_，为0则返回false_value_，为NULL则返回allow_null_</p>
    *
    * @param value 被判断值
    * @param trueValue 为1输出值
    * @param falseValue 为0输出值
    * @param allowNull 为null输出值
    * @return true_value_<BR>false_value_<BR>allow_null_
    */
   FSqlFunction toString(Object value,
                         Object trueValue,
                         Object falseValue,
                         Object allowNull);

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
