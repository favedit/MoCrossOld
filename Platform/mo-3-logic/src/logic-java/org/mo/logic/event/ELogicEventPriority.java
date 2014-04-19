/*
 * @(#)ELogicEventPriority.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.event;

/**
 * <T>事件优先级枚举类</T>
 * <P>根据事件优先级别设定为以下几类：<B/>
 * <UL>
 *    <L value='High'>高级</L>
 *    <L value='Medium'>中级</L>
 *    <L value='Low'>低级</L>
 * </UL>
 * </P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/27
 */
public enum ELogicEventPriority{
   /**
    * <T>高级</T>
    */
   High(9),
   /**
    * <T>中级</T>
    */
   Medium(5),
   /**
    * <T>低级</T>
    */
   Low(1);
   // 优先级整数值
   private int _priority;

   /**
    * <T>构造函数</T>
    * <P>按整数值初始化优先级，其中：<B/>
    * <UL>
    *    <L value='9'>高级</L>
    *    <L value='5'>中级</L>
    *    <L value='1'>低级</L>
    * </UL>
    * </P>
    * 
    * @param priority
    */
   private ELogicEventPriority(int priority){
      _priority = priority;
   }

   /**
    * <T>获得优先级的整数值</T>
    * 
    * @return 优先级的整数值
    */
   public int toInteger(){
      return _priority;
   }
}
