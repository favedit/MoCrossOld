/*
 * @(#)ICpMessage.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.data;

import org.mo.com.data.FSqlProcedure;

/**
 * <T>数据库逻辑包(CP_MESSAGE)的代理对象接口</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public interface ICpMessage
{
   /**
    * <T>重置系统的消息内容。</T>
    *
    */
   FSqlProcedure reset();

   /**
    * <T>增加一个通知消息。</T>
    * <P>系统根据逻辑名称和代码内容获得对应语言的翻译后内容，将内容替换后，生成最终显示内容。</P>
    *
    * @param logicName 逻辑名称
    * @param code 代码内容
    * @param param1 埋入参数1
    * @param param2 埋入参数2
    * @param param3 埋入参数3
    * @param param4 埋入参数4
    * @param param5 埋入参数5
    * @param param6 埋入参数6
    */
   FSqlProcedure addInfo(Object logicName,
                         Object code,
                         Object param1,
                         Object param2,
                         Object param3,
                         Object param4,
                         Object param5,
                         Object param6);

   /**
    * <T>增加一个警告消息。</T>
    * <P>系统根据逻辑名称和代码内容获得对应语言的翻译后内容，将内容替换后，生成最终显示内容。</P>
    *
    * @param logicName 逻辑名称
    * @param code 代码内容
    * @param param1 埋入参数1
    * @param param2 埋入参数2
    * @param param3 埋入参数3
    * @param param4 埋入参数4
    * @param param5 埋入参数5
    * @param param6 埋入参数6
    */
   FSqlProcedure addWarn(Object logicName,
                         Object code,
                         Object param1,
                         Object param2,
                         Object param3,
                         Object param4,
                         Object param5,
                         Object param6);

   /**
    * <T>产生一个系统例外。</T>
    * <P>系统根据逻辑名称和代码内容获得对应语言的翻译后内容，将内容替换后，生成最终显示内容。</P>
    * <P>程序运行到这里就停止，返回到起调端继续执行。</P>
    *
    * @param logicName 逻辑名称
    * @param code 代码内容
    * @param param1 埋入参数1
    * @param param2 埋入参数2
    * @param param3 埋入参数3
    * @param param4 埋入参数4
    * @param param5 埋入参数5
    * @param param6 埋入参数6
    */
   FSqlProcedure raiseFatal(Object logicName,
                            Object code,
                            Object param1,
                            Object param2,
                            Object param3,
                            Object param4,
                            Object param5,
                            Object param6);

   /**
    * <T>产生一个逻辑例外。</T>
    * <P>系统根据逻辑名称和代码内容获得对应语言的翻译后内容，将内容替换后，生成最终显示内容。</P>
    * <P>程序运行到这里就停止，返回到起调端继续执行。</P>
    *
    * @param logicName 逻辑名称
    * @param code 代码内容
    * @param param1 埋入参数1
    * @param param2 埋入参数2
    * @param param3 埋入参数3
    * @param param4 埋入参数4
    * @param param5 埋入参数5
    * @param param6 埋入参数6
    */
   FSqlProcedure raiseLogic(Object logicName,
                            Object code,
                            Object param1,
                            Object param2,
                            Object param3,
                            Object param4,
                            Object param5,
                            Object param6);

   /**
    * <T>锁定指定表时，已经被其他用户锁定产生例外。</T>
    *
    * @param logicName 逻辑名称
    */
   FSqlProcedure raiseLockTable(Object logicName);

   /**
    * <T>锁定指定标识的记录时，已经被其他用户锁定产生例外。</T>
    *
    * @param logicName 逻辑名称
    * @param objectId 对象标识
    * @param objectVersion 对象版本
    */
   FSqlProcedure raiseLockRecord(Object logicName,
                                 Object objectId,
                                 Object objectVersion);

   /**
    * <T>操作指定标识的记录时，记录不存在产生例外。</T>
    *
    * @param logicName 逻辑名称
    * @param objectId 对象标识
    * @param objectVersion 对象版本
    */
   FSqlProcedure raiseNoRecord(Object logicName,
                               Object objectId,
                               Object objectVersion);

   /**
    * <T>记录重复时产生例外。</T>
    *
    * @param logicName 逻辑名称
    * @param objectId 对象标识
    * @param objectVersion 对象版本
    */
   FSqlProcedure raiseDuplicateRecord(Object logicName,
                                      Object objectId,
                                      Object objectVersion);

   /**
    * <T>记录发生变更后，对当前记录做任何修改的操作时发生例外。</T>
    *
    * @param logicName 逻辑名称
    * @param objectId 对象标识
    * @param objectVersion 对象版本
    */
   FSqlProcedure raiseRecordChange(Object logicName,
                                   Object objectId,
                                   Object objectVersion);

   /**
    * <T>获得系统中产生的所有通知消息，警告消息的打包字符串。</T>
    * <P>格式：通知标志=通知消息打包，警告标志=警告消息打包。</P>
    * @return 打包字符串
    *
    * @param message
    */
   FSqlProcedure fetchMessage(Object message);

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
