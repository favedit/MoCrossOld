/*
 * @(#)ILogicSubscribe.java
 *
 * Copyright 2008 microbject. All Rights Reserved.
 *
 */
package org.mo.logic.subscribe;

import org.mo.com.lang.type.TInteger;

/**
 * <T>订阅结构类接口</T>
 * <P>该类定义了订阅的基本字段，并定义了各个字段的存取函数</P>
 * <P>通过设置好的字段创建订阅</P>
 * 
 * @author YJHUA
 * @version 1.00 - 2008/11/03
 */
public interface ILogicSubscribe
{
   /** 
    * <T>获得订阅用户标识</T>
    * 
    * @return 订阅用户标识
    */
   TInteger userId();

   /** 
    * <T>获得订阅类型标识</T>
    * 
    * @return 订阅类型标识
    */
   TInteger typeId();

   /** 
    * <T>获得订阅关联标识</T>
    * 
    * @return 订阅关联标识
    */
   TInteger linkId();

   /** 
    * <T>获得订阅类型动作标识</T>
    * 
    * @return 订阅类型动作标识
    */
   TInteger actionId();

   /** 
    * <T>获得订阅类型事件标识</T>
    * 
    * @return 订阅类型事件标识
    */
   TInteger eventId();
}
