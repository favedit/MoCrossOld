/*
 * @(#)ICpTransfer.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.data;

import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlProcedure;

/**
 * <T>数据库逻辑包(CP_TRANSFER)的代理对象接口</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public interface ICpTransfer
{
   /**
    * <p>获得property_的VALUE</p>
    *
    * @param property 被查询key名称
    * @return VARCHAR2
    */
   FSqlFunction getGlobalProperty(Object property);

   /**
    * <p>获得Session属性值</p>
    *
    * @param property 被查询属性名称
    * @return VARCHAR2
    */
   FSqlFunction getSessionProperty(Object property);

   /**
    *
    * @param globalPack
    * @param sessionPack
    */
   FSqlProcedure reset(Object globalPack,
                       Object sessionPack);

   /**
    * <p>获得消息和调试内容（在弹出例外时使用）</p>
    *
    * @param message 消息内容
    * @param logger 调试信息
    */
   FSqlProcedure fetchResult(Object message,
                             Object logger);

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
