/*
 * @(#)ILogicSubscribeAction.java
 *
 * Copyright 2008 microbject. All Rights Reserved.
 *
 */
package org.mo.logic.subscribe;

import org.mo.com.lang.type.TInteger;

/**
 * <T>订阅动作打包类接口</T>
 * <P>该类是一个打包容器，具有存到容器和取得打包结果</P>
 * 
 * @author YJHUA
 * @version 1.00 - 2008/11/03
 */
public interface ILogicSubscribeAction
{
   /** 
    * <T>把事件标识和参数存入到该打包容器中</T>
    * 
    * @param eventId 订阅事件标识
    * @param event 订阅事件打包类接口
    */
   void push(TInteger actionId,
             ILogicSubscribeEvent event);

   /** 
    * <T>获得打包后的字符串</T>
    * 
    * @return 打包后的字符串
    */
   String pack();

   /** 
    * <T>判断该容器是否为空</T>
    * 
    * @return 该容器是否为空
    *    <L value='true'>该容器为空<L>
    *    <L value='false'>该容器不为空<L>
    */
   boolean isEmpty();
}
