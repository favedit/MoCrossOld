/*
 * @(#)ILogicScheduleTime.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.schedule;

import org.mo.com.collections.FRow;
import org.mo.com.lang.type.TDateTime;
import org.mo.com.lang.type.TInteger;

/**
 * <T>计划时间点对象接口</T>
 * <P>定义接口里可被访问的所有变量方法</P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/30
 */
public interface ILogicScheduleTime
{
   /**
    * <T>时间编号</T>
    */
   TInteger id();

   /**
    * <T>事件编号</T>
    */
   TInteger eventId();

   /**
    * <T>间隔标识</T>
    */
   TInteger interval();

   /**
    * <T>计划执行时间</T>
    */
   TDateTime executeTime();

   /**
    * <T>天标识</T>
    */
   String dayFlag();

   /**
    * <T>月标识</T>
    */
   String monthFlag();

   /**
    * <T>周标识</T>
    */
   String weekFlag();

   /**
    * <T>对象版本号</T>
    */
   ELogicScheduleTimeStatus status();

   /**
    * <T>时间类型</T>
    * <P>共分一下几类：<B/>
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
    */
   ELogicScheduleDateType timeType();

   /**
    * <T>从检索出来的row对象中读取值并给对应的变量设置值</T>
    */
   void loadConfig(FRow row);

   /**
    * <T>计划编号</T>
    */
   TInteger scheduleId();

   boolean isValid();
}
