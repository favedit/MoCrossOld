/*
 * @(#)ICpDate.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.data;

import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlProcedure;

/**
 * <T>数据库逻辑包(CP_DATE)的代理对象接口</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public interface ICpDate
{
   /**
    * <T>根据value判断输出日期格式 </T>
    *
    * @param value 被强制转换值
    * @return DATE
    */
   FSqlFunction parse(Object value);

   /**
    * <T>获得最小允许日期 。</T>
    *
    * @return DATE
    */
   FSqlFunction getMinDate();

   /**
    * <T>获得最大允许日期 。</T>
    *
    * @return DATE
    */
   FSqlFunction getMaxDate();

   /**
    * <T>按默认格式强制转换</T>
    *
    * @param value 被强制转换值
    * @return VARCHAR2
    */
   FSqlFunction toString(Object value);

   /**
    * <T>获得当前日期 。</T>
    *
    * @return DATE
    */
   FSqlFunction currentDate();

   /**
    * <T>获得当前时间 。</T>
    *
    * @return DATE
    */
   FSqlFunction currentTime();

   /**
    * <T>检查月份格式。</T>
    *
    * @param month 月份
    * @return 月份格式的有效性
    */
   FSqlFunction testMonth(Object month);

   /**
    * <T>检查日格式。</T>
    *
    * @param year 年份
    * @param month 月份
    * @param day 日
    * @return 日格式的有效性
    */
   FSqlFunction testDay(Object year,
                        Object month,
                        Object day);

   /**
    * <T>检查小时格式。</T>
    *
    * @param hour 小时数
    * @return 时间格式的有效性
    */
   FSqlFunction testHour(Object hour);

   /**
    * <T>检查分钟格式。</T>
    *
    * @param minute 分钟
    * @return 时间格式的有效性
    */
   FSqlFunction testMinute(Object minute);

   /**
    * <T>检查秒格式。</T>
    *
    * @param second 秒数
    * @return 秒格式的有效性
    */
   FSqlFunction testSecond(Object second);

   /**
    * <T>检查时间范围。</T>
    *
    * @param date 项目时间
    * @param beforeDate 前置时间
    * @param afterDate 后置时间
    * @return 时间范围的有效性
    */
   FSqlFunction testBetween(Object date,
                            Object beforeDate,
                            Object afterDate);

   /**
    * <T>检查时间范围(左闭右开)。</T>
    *
    * @param date 项目时间
    * @param beforeDate 前置时间
    * @param afterDate 后置时间
    * @return 时间范围的有效性
    */
   FSqlFunction testRange(Object date,
                          Object beforeDate,
                          Object afterDate);

   /**
    * <T>项目时间在某个时间之后。</T>
    *
    * @param date 项目时间
    * @param afterDate 比较时间
    * @return 时间范围的有效性
    */
   FSqlFunction testAfter(Object date,
                          Object afterDate);

   /**
    * <T>项目时间在某个时间之前。</T>
    *
    * @param date 项目时间
    * @param beforeDate
    * @return 时间范围的有效性
    */
   FSqlFunction testBefore(Object date,
                           Object beforeDate);

   /**
    * <T>项目时间不早于后置时间。</T>
    *
    * @param date 项目时间
    * @param afterDate 比较时间
    * @return 时间范围的有效性
    */
   FSqlFunction testAfterEq(Object date,
                            Object afterDate);

   /**
    * <T>项目时间不晚于前置时间。</T>
    *
    * @param date 项目时间
    * @param beforeDate
    * @return 时间范围的有效性
    */
   FSqlFunction testBeforeEq(Object date,
                             Object beforeDate);

   /**
    * <T>检查日期格式。</T>
    * <P>检查指定的日期格式是否为指定格式，如果不符合，则抛出错误。
    * 错误格式为：“{0}的日期格式不正确。”其中：
    * <L value='{0}'>项目日期</L>
    * </P>
    *
    * @param value 项目日期
    * @param logic 业务逻辑
    * @param field
    */
   FSqlProcedure checkDate(Object value,
                           Object logic,
                           Object field);

   /**
    * <T>检查时间格式。</T>
    * <P>检查指定的时间格式是否为指定格式，如果不符合，则抛出错误。
    * 错误格式为：“{0}的时间格式不正确。”其中：
    * <L value='{0}'>项目时间</L>
    * </P>
    *
    * @param value 项目时间
    * @param logic 业务逻辑
    * @param field
    */
   FSqlProcedure checkTime(Object value,
                           Object logic,
                           Object field);

   /**
    * <T>检查日期时间格式。</T>
    * <P>检查指定的日期时间格式是否为指定格式，如果不符合，则抛出错误。
    * 错误格式为：“{0}的日期时间格式不正确。”其中：
    * <L value='{0}'>项目日期时间</L>
    * </P>
    *
    * @param value 项目日期时间
    * @param logic 业务逻辑
    * @param field
    */
   FSqlProcedure checkDatetime(Object value,
                               Object logic,
                               Object field);

   /**
    * <T>检查时间范围。</T>
    * <P>检查时间范围是否在指定范围，如果不符合，则抛出错误。
    * 错误格式为：“{0}的时间必须在{1}和{2}范围之间。”其中：
    * <L value='{0}'>项目时间</L>
    * <L value='{1}'>前置时间</L>
    * <L value='{2}'>后置时间</L>
    * </P>
    *
    * @param value 后置时间
    * @param logic 业务逻辑
    * @param field
    * @param beforeDate
    * @param afterDate
    */
   FSqlProcedure checkBetween(Object value,
                              Object logic,
                              Object field,
                              Object beforeDate,
                              Object afterDate);

   /**
    * <T>检查时间范围（左闭右开）。</T>
    * <P>检查时间范围是否在指定范围，如果不符合，则抛出错误。
    * 错误格式为：“{0}的时间必须在{1}和{2}范围之间（左闭右开）。”其中：
    * <L value='{0}'>项目时间</L>
    * <L value='{1}'>前置时间</L>
    * <L value='{2}'>后置时间</L>
    * </P>
    *
    * @param value 后置时间
    * @param logic 业务逻辑
    * @param field
    * @param beforeDate
    * @param afterDate
    */
   FSqlProcedure checkRange(Object value,
                            Object logic,
                            Object field,
                            Object beforeDate,
                            Object afterDate);

   /**
    * <T>项目时间在某个时间之后。</T>
    * <P>检查项目时间是否在某个时间之后，如果不符合，则抛出错误。
    * 错误格式为：“{0}的时间在{1}的之后。”其中：
    * <L value='{0}'>项目时间</L>
    * <L value='{1}'>比较时间</L>
    * </P>
    *
    * @param value 比较时间
    * @param logic 业务逻辑
    * @param field
    * @param afterDate
    */
   FSqlProcedure checkAfter(Object value,
                            Object logic,
                            Object field,
                            Object afterDate);

   /**
    * <T>项目时间在某个时间之前。</T>
    * <P>检查项目时间是否在某时间之前，如果不符合，则抛出错误。
    * 错误格式为：“{0}的时间在{1}的之前。”其中：
    * <L value='{0}'>项目时间</L>
    * <L value='{1}'>比较时间</L>
    * </P>
    *
    * @param value 比较时间
    * @param logic 业务逻辑
    * @param field
    * @param beforeDate
    */
   FSqlProcedure checkBefore(Object value,
                             Object logic,
                             Object field,
                             Object beforeDate);

   /**
    * <T>项目时间不早于后置时间。</T>
    * <P>检查项目时间不早于后置时间，如果不符合，则抛出错误。
    * 错误格式为：“{0}的时间不早于{1}。”其中：
    * <L value='{0}'>项目时间</L>
    * <L value='{1}'>比较时间</L>
    * </P>
    *
    * @param value 比较时间
    * @param logic 业务逻辑
    * @param field
    * @param afterDate
    */
   FSqlProcedure checkAfterEq(Object value,
                              Object logic,
                              Object field,
                              Object afterDate);

   /**
    * <T>项目时间不晚于前置时间。</T>
    * <P>检查项目时间不晚于前置时间，如果不符合，则抛出错误。
    * 错误格式为：“{0}的时间不晚于{1}。”其中：
    * <L value='{0}'>项目时间</L>
    * <L value='{1}'>比较时间</L>
    * </P>
    *
    * @param value 比较时间
    * @param logic 业务逻辑
    * @param field
    * @param beforeDate
    */
   FSqlProcedure checkBeforeEq(Object value,
                               Object logic,
                               Object field,
                               Object beforeDate);

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
