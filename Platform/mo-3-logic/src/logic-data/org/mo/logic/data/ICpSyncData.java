/*
 * @(#)ICpSyncData.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.data;

import org.mo.com.data.FSqlProcedure;

/**
 * <T>数据库逻辑包(CP_SYNC_DATA)的代理对象接口</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public interface ICpSyncData
{
   /**
    *
    * @param tableName
    * @param databaseName
    */
   FSqlProcedure clearTable(Object tableName,
                            Object databaseName);

   /**
    * <p>将表同步</p>
    *
    * @param tableName 被操作表名
    * @param databaseName 被修改dateset名
    * @param orderBy 排序字段名称
    * @param versionBegin 限定选取条件最小值
    * @param versionEnd 限定选取条件最大值
    */
   FSqlProcedure syncTable(Object tableName,
                           Object databaseName,
                           Object orderBy,
                           Object versionBegin,
                           Object versionEnd);

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
