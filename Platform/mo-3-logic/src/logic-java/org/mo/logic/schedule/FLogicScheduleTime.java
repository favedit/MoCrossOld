/*
 * @(#)FLogicScheduleTime.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.schedule;

import org.mo.com.collections.FRow;
import org.mo.com.lang.REnum;
import org.mo.com.lang.type.TBoolean;
import org.mo.com.lang.type.TDateTime;
import org.mo.com.lang.type.TInteger;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

/**
 * <T>计划时间点结构类</T>
 * <P>该类定义了计划时间点对象的主体结构，并定义了各个字段的存取函数</P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/27
 */
public class FLogicScheduleTime
      implements
         ILogicScheduleTime
{
   // 全局日志对象
   private final static ILogger _logger = RLogger.find(FLogicScheduleConsole.class);

   /**
    * <T>开始日期</T>
    */
   public static String PTY_BEGIN_DATE = "begin_date";

   /**
    * <T>天标识</T>
    */
   public static String PTY_DAY_FLAG = "day_flag";

   /**
    * <T>结束日期</T>
    */
   public static String PTY_END_DATE = "end_date";

   /**
    * <T>事件标识</T>
    */
   public static String PTY_EVENT_ID = "event_id";

   /**
    * <T>执行时间</T>
    */
   public static String PTY_EXECUTE_TIME = "execute_time";

   /**
    * <T>对象标识</T>
    */
   public static String PTY_ID = "time_id";

   /**
    * <P>间隔时间</P>
    */
   public static String PTY_INTERVAL = "interval";

   /**
    * <T>有效性标识</T>
    */
   public static String PTY_ISVALID = "ovld";

   /**
    * <T>月标识</T>
    */
   public static String PTY_MONTH_FLAG = "month_flag";

   /**
    * <T>计划标识</T>
    */
   public static String PTY_SCHEDULE_ID = "schedule_id";

   /**
    * <T>计划状态</T>
    */
   public static String PTY_STATUS = "schedule_status";

   /**
    * <T>时间类型</T>
    */
   public static String PTY_TIME_TYPE = "type_cd";

   /**
    * <T>周标识</T>
    */
   public static String PTY_WEEK_FLAG = "week_flag";

   // 天标识
   private String _dayFlag;

   // 事件标识
   private TInteger _eventId = new TInteger();

   // 执行时间
   private TDateTime _executeTime = new TDateTime();

   // 对象标识
   private TInteger _id = new TInteger();

   // 时间间隔
   private TInteger _interval = new TInteger();

   private TBoolean _isValid;

   // 月标识
   private String _monthFlag;

   // 计划标识
   private TInteger _scheduleId = new TInteger();

   // 对象版本
   private ELogicScheduleTimeStatus _status;

   // 时间类型
   private ELogicScheduleDateType _timeType;

   // 周标识
   private String _weekFlag;

   /* (non-Javadoc)
    * @see org.mo.logic.schedule.ILogicSchedule#dayFlag()
    */
   @Override
   public String dayFlag(){
      return _dayFlag;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.schedule.ILogicSchedule#eventId()
    */
   @Override
   public TInteger eventId(){
      return _eventId;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.schedule.ILogicSchedule#executeTime()
    */
   @Override
   public TDateTime executeTime(){
      return _executeTime;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.schedule.ILogicSchedule#id()
    */
   @Override
   public TInteger id(){
      return _id;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.schedule.ILogicSchedule#interval()
    */
   @Override
   public TInteger interval(){
      return _interval;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.schedule.ILogicSchedule#id()
    */
   @Override
   public boolean isValid(){
      return _isValid.get();
   }

   /* (non-Javadoc)
    * @see org.mo.logic.schedule.ILogicSchedule#loadConfig(org.mo.com.lang.FRow)
    */
   @Override
   public void loadConfig(FRow row){
      _logger.debug(this, "fetchSchedules", "load config row={0}", row.dump());
      _id.set(row.getInteger(PTY_ID));
      _scheduleId.set(row.getInteger(PTY_SCHEDULE_ID));
      _eventId.set(row.getInteger(PTY_EVENT_ID));
      setStatus(row.get(PTY_STATUS));
      //_isValid.set(row.get(PTY_ISVALID));
      _executeTime.set(row.getDate(PTY_EXECUTE_TIME));
      _monthFlag = row.get(PTY_MONTH_FLAG);
      _weekFlag = row.get(PTY_WEEK_FLAG);
      _dayFlag = row.get(PTY_DAY_FLAG);
      _interval.set(row.getInteger(PTY_INTERVAL));
      setTimeType(row.get(PTY_TIME_TYPE));
   }

   /* (non-Javadoc)
    * @see org.mo.logic.schedule.ILogicSchedule#monthFlag()
    */
   @Override
   public String monthFlag(){
      return _monthFlag;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.schedule.ILogicSchedule#id()
    */
   @Override
   public TInteger scheduleId(){
      return _scheduleId;
   }

   /**
    * <T>设置天标识</T>
    * <P>设置计划的天标识，其格式是用一串10字符串来标识以天为执行单位的时间类型</P>
    * 
    * @param dayFlag 天标识
    */
   public void setDayFlag(String dayFlag){
      _dayFlag = dayFlag;
   }

   /**
    * <T>设置事件标识</T>
    * 
    * @param eventId 计划标识
    */
   public void setEventId(TInteger eventId){
      _eventId = eventId;
   }

   /**
    * <T>设置执行时间</T>
    * 
    * @param executeTime 执行时间
    */
   public void setExecuteTime(TDateTime executeTime){
      _executeTime = executeTime;
   }

   /**
    * <T>设置对象标识</T>
    * 
    * @param id 对象标识
    */
   public void setId(TInteger id){
      _id = id;
   }

   /**
    * <T>设置时间间隔</T>
    * 
    * @param interval 间隔时间
    */
   public void setInterval(TInteger interval){
      _interval = interval;
   }

   /**
    * <T>设置月标识</T>
    * <P>设置计划的月标识，其格式是用一串10字符串来标识以月为执行单位的时间类型</P>
    * 
    * @param monthFlag 月标识
    */
   public void setMonthFlag(String monthFlag){
      _monthFlag = monthFlag;
   }

   /**
    * <T>设置计划标识</T>
    * 
    * @param scheduleId 计划标识
    */
   public void setScheduleId(TInteger scheduleId){
      _scheduleId = scheduleId;
   }

   public void setStatus(String status){
      _status = REnum.parseStart(ELogicScheduleTimeStatus.class, status);
   }

   /**
    * <T>设置时间类型</T>
    * 
    * @param timeType <R link='jv:org.mo.logic.schedule|ELogicScheduleDateType'>事件类型</R>
    */
   public void setTimeType(String timeType){
      _timeType = REnum.parseByString(ELogicScheduleDateType.class, timeType);
   }

   /**
    * <T>设置周标识</T>
    * <P>设置计划的周标识，其格式是用一串10字符串来标识以周为执行单位的时间类型</P>
    * 
    * @param weekFlag 周标识
    */
   public void setWeekFlag(String weekFlag){
      _weekFlag = weekFlag;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.schedule.ILogicSchedule#over()
    */
   @Override
   public ELogicScheduleTimeStatus status(){
      return _status;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.schedule.ILogicSchedule#timeType()
    */
   @Override
   public ELogicScheduleDateType timeType(){
      return _timeType;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.schedule.ILogicSchedule#weekFlag()
    */
   @Override
   public String weekFlag(){
      return _weekFlag;
   }
}
