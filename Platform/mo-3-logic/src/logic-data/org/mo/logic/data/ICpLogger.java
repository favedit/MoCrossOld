/*
 * @(#)ICpLogger.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.data;

import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlProcedure;

/**
 * <T>数据库逻辑包(CP_LOGGER)的代理对象接口</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public interface ICpLogger
{
   /**
    * <T>获得是否允许调试信息。</T>
    *
    * @return 是否允许
    */
   FSqlFunction debugable();

   /**
    * <T>清空当前日志的缓冲。</T>
    *
    */
   FSqlProcedure reset();

   /**
    * <T>设置日志输出的标志。</T>
    * <P>标志内容可以是
    * 调试(<C>LEVEL_DEBUG</C>),
    * 信息(<C>LEVEL_INFO</C>),
    * 警告(<C>LEVEL_WARN</C>),
    * 错误(<C>LEVEL_ERROR</C>)
    * 的任意组合。</P>
    *
    * @param flag 标志
    */
   FSqlProcedure setFlag(Object flag);

   /**
    * <T>设置当前处理中的调用函数。</T>
    *
    * @param method 函数名称
    */
   FSqlProcedure methodBegin(Object method);

   /**
    * <T>结束当前处理中的调用函数。</T>
    *
    */
   FSqlProcedure methodEnd();

   /**
    * <T>输出一条固定信息。</T>
    * <P>仅供方便调试代码时使用，最终代码中不应该含有此参数。</P>
    *
    * @param message 消息内容
    * @param param1 参数1
    * @param param2 参数2
    * @param param3 参数3
    * @param param4 参数4
    * @param param5 参数5
    */
   FSqlProcedure print(Object message,
                       Object param1,
                       Object param2,
                       Object param3,
                       Object param4,
                       Object param5);

   /**
    * <T>输出一条可埋入参数调试信息。</T>
    *
    * @param logic 逻辑名称
    * @param method 函数名称
    * @param message 消息内容
    * @param param1 参数1
    * @param param2 参数2
    * @param param3 参数3
    * @param param4 参数4
    * @param param5 参数5
    */
   FSqlProcedure debug(Object logic,
                       Object method,
                       Object message,
                       Object param1,
                       Object param2,
                       Object param3,
                       Object param4,
                       Object param5);

   /**
    * <T>获得所有调试信息。</T>
    * @return 调试信息
    *
    * @param buffer
    */
   FSqlProcedure fetchBuffer(Object buffer);

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
