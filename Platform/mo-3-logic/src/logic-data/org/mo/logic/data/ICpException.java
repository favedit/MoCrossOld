/*
 * @(#)ICpException.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.data;

import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlProcedure;

/**
 * <T>数据库逻辑包(CP_EXCEPTION)的代理对象接口</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public interface ICpException
{
   /**
    * <p>获得message</p>
    *
    * @return ????
    */
   FSqlFunction getMessage();

   /**
    *
    */
   FSqlProcedure reset();

   /**
    * <p>增加一个通知消息。</p>
    *
    * @param code 警告编号
    * @param message 消息内容
    * @param param1 消息埋入参数1
    * @param param2 消息埋入参数2
    * @param param3 消息埋入参数3
    * @param param4 消息埋入参数4
    * @param param5 消息埋入参数5
    * @param param6 消息埋入参数6
    */
   FSqlProcedure addInfo(Object code,
                         Object message,
                         Object param1,
                         Object param2,
                         Object param3,
                         Object param4,
                         Object param5,
                         Object param6);

   /**
    * <p>增加一个警告消息。</p>
    *
    * @param code 警告编号
    * @param message 消息内容
    * @param param1 消息埋入参数1
    * @param param2 消息埋入参数2
    * @param param3 消息埋入参数3
    * @param param4 消息埋入参数4
    * @param param5 消息埋入参数5
    * @param param6 消息埋入参数6
    */
   FSqlProcedure addWarn(Object code,
                         Object message,
                         Object param1,
                         Object param2,
                         Object param3,
                         Object param4,
                         Object param5,
                         Object param6);

   /**
    * <p>产生一个系统例外。</p>
    * <p>程序在此停止，返回到起调端继续执行。</p>
    *
    * @param code 例外编号
    * @param message 消息内容
    * @param param1 消息埋入参数1
    * @param param2 消息埋入参数2
    * @param param3 消息埋入参数3
    * @param param4 消息埋入参数4
    * @param param5 消息埋入参数5
    * @param param6 消息埋入参数6
    */
   FSqlProcedure raiseFatal(Object code,
                            Object message,
                            Object param1,
                            Object param2,
                            Object param3,
                            Object param4,
                            Object param5,
                            Object param6);

   /**
    * <p>产生一个逻辑例外。</p>
    * <p>程序在此停止，返回到起调端继续执行。</p>
    *
    * @param lgName 逻辑名称
    * @param code 例外编号
    * @param message 消息内容
    * @param param1 消息埋入参数1
    * @param param2 消息埋入参数2
    * @param param3 消息埋入参数3
    * @param param4 消息埋入参数4
    * @param param5 消息埋入参数5
    * @param param6 消息埋入参数6
    */
   FSqlProcedure raiseLogic(Object lgName,
                            Object code,
                            Object message,
                            Object param1,
                            Object param2,
                            Object param3,
                            Object param4,
                            Object param5,
                            Object param6);

   /**
    * <p>解析Lock_Table</p>
    *
    * @param tableName table的名字
    */
   FSqlProcedure raiseLockTable(Object tableName);

   /**
    * <p>解析Lock_Record</p>
    *
    * @param tableName table的名字
    * @param objectUid 对象ID
    * @param objectVersion 对象版本
    */
   FSqlProcedure raiseLockRecord(Object tableName,
                                 Object objectUid,
                                 Object objectVersion);

   /**
    * <p>解析No_Record</p>
    *
    * @param logicName
    * @param objectUid 对象ID
    * @param objectVersion 对象版本
    */
   FSqlProcedure raiseNoRecord(Object logicName,
                               Object objectUid,
                               Object objectVersion);

   /**
    * <p>解析No_Record</p>
    *
    * @param lgName
    * @param where
    */
   FSqlProcedure raiseNoRecordId(Object lgName,
                                 Object where);

   /**
    * <p>解析 Duplicate_Record</p>
    *
    * @param logicName
    * @param objectUid 对象ID
    * @param objectVersion 对象版本
    */
   FSqlProcedure raiseDuplicateRecord(Object logicName,
                                      Object objectUid,
                                      Object objectVersion);

   /**
    * <p>解析Record_Change</p>
    *
    * @param tableName table名字
    * @param objectUid 对象ID
    * @param objectVersion 对象版本
    */
   FSqlProcedure raiseRecordChange(Object tableName,
                                   Object objectUid,
                                   Object objectVersion);

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
