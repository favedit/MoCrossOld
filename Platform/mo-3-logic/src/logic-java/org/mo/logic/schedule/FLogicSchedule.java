/*
 * @(#)FLogicSchedule.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.schedule;

import org.mo.com.collections.FRow;
import org.mo.com.lang.REnum;
import org.mo.com.lang.type.TDateTime;
import org.mo.com.lang.type.TInteger;

/**
 * <T>计划结构类</T>
 * <P>该类定义了计划对象的主体结构，并定义了各个字段的存取函数</P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/27
 */
public class FLogicSchedule
      implements
         ILogicSchedule
{
   /**
    * <T>对象标识</T>
    */
   public static String PTY_ID = "schedule_id";

   /**
    * <T>对象标识</T>
    */
   public static String PTY_STATUS = "schedule_status";

   /**
    * <T>开始日期</T>
    */
   public static String PTY_BEGIN_DATE = "begin_date";

   /**
    * <T>结束日期</T>
    */
   public static String PTY_END_DATE = "end_date";

   // 开始时间
   private TDateTime _beginDate = new TDateTime();

   // 结束时间
   private TDateTime _endDate = new TDateTime();

   // 对象标识
   private TInteger _id = new TInteger();

   // 对象标识
   private ELogicScheduleStatus _status;

   private FLogicScheduleTimes _scheduleTimes = new FLogicScheduleTimes();

   /* (non-Javadoc)
    * @see org.mo.logic.schedule.ILogicSchedule#beginDate()
    */
   @Override
   public TDateTime beginDate(){
      return _beginDate;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.schedule.ILogicSchedule#endDate()
    */
   @Override
   public TDateTime endDate(){
      return _endDate;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.schedule.ILogicSchedule#id()
    */
   @Override
   public TInteger id(){
      return _id;
   }

   public void loadConfig(FRow row){
      _id.set(row.getInteger(PTY_ID));
      _beginDate.set(row.getDate(PTY_BEGIN_DATE));
      _endDate.set(row.getDate(PTY_END_DATE));
      setStatus(row.get(PTY_STATUS));
   }

   /* (non-Javadoc)
    * @see org.mo.logic.schedule.ILogicSchedule#scheduleTimes()
    */
   @Override
   public FLogicScheduleTimes scheduleTimes(){
      return _scheduleTimes;
   }

   /**
    * <T>设置开始时间</T>
    * 
    * @param beginDate 开始时间
    */
   public void setBeginDate(TDateTime beginDate){
      _beginDate = beginDate;
   }

   /**
    * <T>设置结束时间</T>
    * 
    * @param endDate 结束时间
    */
   public void setEndDate(TDateTime endDate){
      _endDate = endDate;
   }

   public void setStatus(String status){
      _status = REnum.parseStart(ELogicScheduleStatus.class, status);
   }

   /* (non-Javadoc)
    * @see org.mo.logic.schedule.ILogicSchedule#over()
    */
   @Override
   public ELogicScheduleStatus status(){
      return _status;
   }
}
