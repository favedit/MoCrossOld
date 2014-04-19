/*
 * @(#)ILogicEventResult.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.event;

import org.mo.com.lang.IAttributes;

/**
 * <T>事件执行结果接口</T>
 * <P>记录执行完的log结果信息</P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/27
 */
public interface ILogicEventResult
{
   /**
    * <T>设置执行结果的code编号</T>
    * <P>该编号是执行结果信息的标识，通过该编号可以提取到对应的结果信息</P>
    */
   String code();

   /**
    * <T>设置执行结果的描述</T>
    * <P>该编号是执行结果信息的标识，通过该编号可以提取到对应的结果信息</P>
    */
   String description();

   /**
    * <T>判断执行结果是否有参数</T>
    * <P>如果有参数，则将该参数添加到消息打包字符串，否则不添加</P>
    * 
    * @return 判断是否有参数
    * <UL>
    *    <L value = 'true'>表示有参数</L>
    *    <L value = 'false'>表示没有参数</L>
    * </UL>   
    */
   boolean hasParameter();

   /**
    * <T>对参数进行打包</T>
    * <P>对每个事件对应的log信息进行打包，打包字符串里有名字和值对应的关系</P>
    */
   IAttributes parameters();

   /**
    * <T>设置结果类型对应的枚举型值</T>
    * <P>执行结果共有5种，将对应的结果设置到该枚举类型里</P>
    */
   ELogicEventResult resultType();
}
