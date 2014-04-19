/*
 * @(#)ELogicEventStatus.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.event;

import org.mo.com.lang.REnum;

/**
 * <T>事件状态枚举类型</T>
 * <P>根据事件状态类型可设置为以下几种：<B/>
 * <UL>
 *    <L value='Published'>发行</L>
 *    <L value='Waiting'>等待</L>
 *    <L value='Running'>运行</L>
 *    <L value='End'>结束</L>
 * </UL>
 * </P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/27
 */
public enum ELogicEventStatus{
   /**
    * <T>发行</T>
    */
   Published,
   /**
    * <T>等待</T>
    */
   Waiting,
   /**
    * <T>运行</T>
    */
   Running,
   /**
    * <T>结束</T>
    */
   End;
   /**
    * <T>按首字母转换状态</T>
    * <P>可以通过字符首字母得到事件状态<B/>
    *  <UL>
    *    <L value='P'>Published</L>
    *    <L value='W'>Waiting</L>
    *    <L value='R'>Running</L>
    *    <L value='E'>End</L>
    * </UL>
    * </P>
    * 
    * @param value 状态简称
    * @return 事件状态值
    * @see 参见<R link='jv:org.mo.com.lang|REnum'>REnum</R>
    * 的<R link='jv:org.mo.com.lang|REnum|toValueByStart'>toValueByStart</R>方法
    */
   public static ELogicEventStatus parse(String value){
      return REnum.parseStart(ELogicEventStatus.class, value);
   }
}
