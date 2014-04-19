/*
 * @(#)ICpSql.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.data;

import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlProcedure;

/**
 * <T>数据库逻辑包(CP_SQL)的代理对象接口</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public interface ICpSql
{
   /**
    * <T>执行一个指定名称的函数处理，执行后返回结果内容。</T>
    *
    * @param functionName
    * @return 执行后的返回的结果内容
    */
   FSqlFunction executeFunction(Object functionName);

   /**
    * <T>执行一个指定名称的函数处理，执行后返回结果内容。</T>
    *
    * @param functionName
    * @param param1 变量1
    * @return 执行后的返回的结果内容
    */
   FSqlFunction executeFunction(Object functionName,
                                Object param1);

   /**
    * <T>执行一个指定名称的函数处理，执行后返回结果内容。</T>
    *
    * @param functionName
    * @param param1 变量1
    * @param param2 变量2
    * @return 执行后的返回的结果内容
    */
   FSqlFunction executeFunction(Object functionName,
                                Object param1,
                                Object param2);

   /**
    * <T>执行一个指定名称的函数处理，执行后返回结果内容。</T>
    *
    * @param functionName
    * @param param1 变量1
    * @param param2 变量2
    * @param param3 变量3
    * @return 执行后的返回的结果内容
    */
   FSqlFunction executeFunction(Object functionName,
                                Object param1,
                                Object param2,
                                Object param3);

   /**
    * <T>执行一个指定名称的函数处理，执行后返回结果内容。</T>
    *
    * @param functionName
    * @param param1 变量1
    * @param param2 变量2
    * @param param3 变量3
    * @param param4 变量4
    * @return 执行后的返回的结果内容
    */
   FSqlFunction executeFunction(Object functionName,
                                Object param1,
                                Object param2,
                                Object param3,
                                Object param4);

   /**
    * <T>执行一个指定名称的函数处理，执行后返回结果内容。</T>
    *
    * @param functionName
    * @param param1 变量1
    * @param param2 变量2
    * @param param3 变量3
    * @param param4 变量4
    * @param param5 变量5
    * @return 执行后的返回的结果内容
    */
   FSqlFunction executeFunction(Object functionName,
                                Object param1,
                                Object param2,
                                Object param3,
                                Object param4,
                                Object param5);

   /**
    * <T>执行一个指定名称的函数处理，执行后返回结果内容。</T>
    *
    * @param functionName
    * @param param1 变量1
    * @param param2 变量2
    * @param param3 变量3
    * @param param4 变量4
    * @param param5 变量5
    * @param param6 变量6
    * @return 执行后的返回的结果内容
    */
   FSqlFunction executeFunction(Object functionName,
                                Object param1,
                                Object param2,
                                Object param3,
                                Object param4,
                                Object param5,
                                Object param6);

   /**
    *
    * @param sql
    */
   FSqlProcedure executeSql(Object sql);

   /**
    * <T>执行一个指定名称的存储过程。</T>
    *
    * @param procedureName 函数名称
    */
   FSqlProcedure executeProcedure(Object procedureName);

   /**
    * <T>执行一个指定名称的存储过程。</T>
    *
    * @param procedureName 函数名称
    * @param param1 函数含有变量名称
    */
   FSqlProcedure executeProcedure(Object procedureName,
                                  Object param1);

   /**
    * <T>执行一个指定名称的存储过程。</T>
    *
    * @param procedureName 函数名称
    * @param param1 函数含有变量名称
    * @param param2
    */
   FSqlProcedure executeProcedure(Object procedureName,
                                  Object param1,
                                  Object param2);

   /**
    * <T>执行一个指定名称的存储过程。</T>
    *
    * @param procedureName 函数名称
    * @param param1 函数含有变量名称
    * @param param2
    * @param param3
    */
   FSqlProcedure executeProcedure(Object procedureName,
                                  Object param1,
                                  Object param2,
                                  Object param3);

   /**
    * <T>执行一个指定名称的存储过程。</T>
    *
    * @param procedureName 函数名称
    * @param param1 函数含有变量名称
    * @param param2
    * @param param3
    * @param param4
    */
   FSqlProcedure executeProcedure(Object procedureName,
                                  Object param1,
                                  Object param2,
                                  Object param3,
                                  Object param4);

   /**
    * <T>执行一个指定名称的存储过程。</T>
    *
    * @param procedureName 函数名称
    * @param param1 函数含有变量名称
    * @param param2
    * @param param3
    * @param param4
    * @param param5
    */
   FSqlProcedure executeProcedure(Object procedureName,
                                  Object param1,
                                  Object param2,
                                  Object param3,
                                  Object param4,
                                  Object param5);

   /**
    * <T>执行一个指定名称的存储过程。</T>
    *
    * @param procedureName 函数名称
    * @param param1 函数含有变量名称
    * @param param2
    * @param param3
    * @param param4
    * @param param5
    * @param param6
    */
   FSqlProcedure executeProcedure(Object procedureName,
                                  Object param1,
                                  Object param2,
                                  Object param3,
                                  Object param4,
                                  Object param5,
                                  Object param6);

   /**
    * <p>解释所有invalid对象</p>
    *
    */
   FSqlProcedure compileAllInvalidObject();

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
