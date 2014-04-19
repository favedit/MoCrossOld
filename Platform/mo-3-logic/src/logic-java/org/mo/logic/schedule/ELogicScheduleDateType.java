/*
 * @(#)FLogicScheduleDateType.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.schedule;

/**
 * <T>计划的事件类型枚举类</T>
 * <P>根据时间类型共分一下几类：<B/>
 * <UL>
 *    <L value='Year'>按每年的那些天执行</L>
 *    <L value='DayMonth'>按某(几)月的某几天执行</L>
 *    <L value='MonthWeek'>按某(几)月的某几周中的某几天执行</L>
 *    <L value='Week'>按每(几)周的某几天执行</L>
 *    <L value='Day'>按每(几)天执行</L>
 *    <L value='Hour'>按每(几)小时执行</L>
 *    <L value='Once'>按一次执行</L>
 * </UL>
 * </P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/27
 */
public enum ELogicScheduleDateType{
   /**
    * <T>按每(几)天执行</T>
    */
   Day("D"),
   /**
    * <T>按每(几)小时执行</T>
    */
   Hour("H"),
   /**
    * <T>按每(几)分钟执行</T>
    */
   Minute("I"),
   /**
    * <T>按某(几)月的某几天执行</T>
    */
   MonthDay("M"),
   /**
    * <T>按某(几)月的某几周中的某几天执行</T>
    */
   MonthWeek("W"),
   /**
    * <T>按一次执行</T>
    */
   Once("O"),
   /**
    * <T>按每(几)周的某几天执行</T>
    */
   Week("E"),
   /**
    * <T>按每年的那些天执行</T>
    */
   Year("Y");
   private String _value;

   private ELogicScheduleDateType(String code){
      _value = code;
   }

   @Override
   public String toString(){
      return _value;
   }
}
