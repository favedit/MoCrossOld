/*
 * @(#)ELogicScheduleTimeStatus.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.schedule;

/**
 * <T>计划时间点的状态类型枚举类</T>
 * <P>根据状态类型共分一下几类：<B/>
 * <UL>
 *    <L value='Publish'>发行</L>
 *    <L value='Stop'>停止</L>
 * </UL>
 * </P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/27
 */
public enum ELogicScheduleTimeStatus{
   /**
   * <T>发行</T>
   */
   Publish("P"),
   /**
    * <T>停止</T>
    */
   Stop("S");
   private String _value;

   /**
    * <T>构造函数</T>
    * <P>根据字符串值（简称）构造计划时间点状态，其中：<B/>
    * <UL>
    *    <L value='P'>Publish</L>
    *    <L value='S'>Stop</L>
    * </UL>
    * </P>
    * 
    * @param value 用于构造处理类型的字符串(简称)
    */
   private ELogicScheduleTimeStatus(String value){
      _value = value;
   }

   /* (non-Javadoc)
    * @see java.lang.Enum#toString()
    */
   @Override
   public String toString(){
      return _value;
   }
}
