/*
 * @(#)ILgProcessConditionEi.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.data;

import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlProcedure;

/**
 * <T>数据库逻辑包(LG_PROCESS_CONDITION_EI)的代理对象接口</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public interface ILgProcessConditionEi
{
   /**
    * 当前枚举中是否含有指定值。
    *
    * @param value 值
    * @return TRUE：含有<BR/>FALSE：不含有
    */
   FSqlFunction contains(Object value);

   /**
    * 将枚举值编码为枚举内容。
    *
    * @param value 枚举值
    * @return 枚举内容
    */
   FSqlFunction encode(Object value);

   /**
    * 将枚举内容编码为枚举值。
    *
    * @param value 枚举内容
    * @return 枚举值
    */
   FSqlFunction decode(Object value);

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
