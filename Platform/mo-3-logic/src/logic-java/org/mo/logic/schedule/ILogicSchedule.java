/*
 * @(#)ILogicSchedule.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.schedule;

import org.mo.com.lang.type.TDateTime;
import org.mo.com.lang.type.TInteger;

/**
 * <T>计划时间点对象接口</T>
 * <P>定义接口里可被访问的所有变量方法</P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/30
 */
public interface ILogicSchedule
{
   /**
   * <T>计划标识</T>
   */
   TInteger id();

   /**
   * <T>计划状态</T>
   */
   ELogicScheduleStatus status();

   /**
    * <T>计划开始日期</T>
    */
   TDateTime beginDate();

   /**
    * <T>计划结束日期</T>
    */
   TDateTime endDate();

   /**
    * <T>时间点集合</T>
    */
   FLogicScheduleTimes scheduleTimes();
}
