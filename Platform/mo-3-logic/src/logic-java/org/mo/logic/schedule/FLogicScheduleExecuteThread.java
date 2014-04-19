/*
 * @(#)FLogicScheduleExecuteThread.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.schedule;

import org.mo.com.data.FSqlProcedure;
import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.RDateTime;
import org.mo.com.lang.type.TDateTime;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.system.FThread;
import org.mo.com.system.RThread;
import org.mo.core.aop.RAop;
import org.mo.data.date.IDataDateConsole;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.logic.data.ILgScheduleDi;
import org.mo.logic.data.impl.FLgScheduleImpl;

/**
 * <T>执行线程类</T>
 * <P>每一段时间取得计划列表里面的计划并判断是否需要执行，然后部署需执行的计划</P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/27
 * @history 090701 MAOCY 增加分钟间隔功能
 */
public class FLogicScheduleExecuteThread
      extends FThread
{
   // 日志输出对象
   private final static ILogger _logger = RLogger.find(FLogicScheduleExecuteThread.class);

   // 整形常数 0
   protected static final int FLG_ZERO = 0;

   // 执行开始时间
   protected TDateTime _beginDateTime = RDateTime.currentDateTime();

   private IDataDateConsole _dataDateConsole;

   // 执行时间间隔
   protected long _executeInterval;

   // 计数器
   protected int _removeCount = 0;

   // 计划项
   protected FLogicSchedules _schedules;

   // 停止标志
   protected boolean _stop = false;

   /**
    * <T>检查计划是否执行。</T>
    * <P>大于等于executeBeginDateTime并小于executeEndDateTime。</P>
    * 
    * @param schedule 计划数据
    * @return 是否执行
    *    <L value='true'>计划执行</L>
    *    <L value='false'>计划不执行</L>
    */
   public boolean checkExecute(ILogicSchedule schedule,
                               ILogicScheduleTime scheduleTime,
                               TDateTime dtExecuteBegin,
                               TDateTime dtExecuteEnd){
      boolean executeAble = false;
      //      // 计划的执行间隔
      //      int interval = scheduleTime.interval().get();
      //      // 计划的开始日期时间
      //      TDateTime dtBegin = schedule.beginDate();
      //      // 获得今天是今年的第几天
      //      int dayOfYear = dtExecuteBegin.day();
      //      // 获得今天是在第几个月
      //      int month = dtExecuteBegin.month();
      //      // 获得今天是在一个月的第几周
      //      int weekOfMonth = dtExecuteBegin.monthWeek();
      //      // 获得今天是在一个月的第几天
      //      int dayOfMonth = dtExecuteBegin.day();
      //      // 获得今天是星期几
      //      int dayOfWeek = dtExecuteBegin.weekDay();
      //      dayOfWeek = (dayOfWeek != 0) ? dayOfWeek : 7;
      //      // 根据类型判断是否执行
      //      try{
      //         switch(scheduleTime.timeType()){
      //         // 按每年来计算，判断今天是否是某一年的那几天即可
      //            case Year:
      //               if(RBoolean.parse(scheduleTime.dayFlag().charAt(dayOfYear - 1))){
      //                  executeAble = checkTime(scheduleTime, dtExecuteBegin, dtExecuteEnd);
      //               }
      //               break;
      //            // 按每月的那几天计算
      //            case MonthDay:
      //               if(RBoolean.parse(scheduleTime.monthFlag().charAt(month - 1)) && RBoolean.parse(scheduleTime.dayFlag().charAt(dayOfMonth - 1))){
      //                  executeAble = checkTime(scheduleTime, dtExecuteBegin, dtExecuteEnd);
      //               }
      //               break;
      //            // 按每月的那几周的那几天计算
      //            case MonthWeek:
      //               if(RBoolean.parse(scheduleTime.monthFlag().charAt(month - 1)) && RBoolean.parse(scheduleTime.weekFlag().charAt(weekOfMonth - 1)) && RBoolean.parse(scheduleTime.dayFlag().charAt(dayOfWeek))){
      //                  executeAble = checkTime(scheduleTime, dtExecuteBegin, dtExecuteEnd);
      //               }
      //               break;
      //            // 按周来计算
      //            case Week:
      //               int weeks = dtExecuteBegin.totalWeeks() - dtBegin.totalWeeks();
      //               if(FLG_ZERO == (weeks % interval) && RBoolean.parse(scheduleTime.dayFlag().charAt(dayOfWeek - 1))){
      //                  executeAble = checkTime(scheduleTime, dtExecuteBegin, dtExecuteEnd);
      //               }
      //               break;
      //            // 按天来计算
      //            case Day:
      //               int days = dtExecuteBegin.totalDays() - dtBegin.totalDays();
      //               if(FLG_ZERO == (days % interval)){
      //                  executeAble = checkTime(scheduleTime, dtExecuteBegin, dtExecuteEnd);
      //               }
      //               break;
      //            // 按小时来计算
      //            case Hour:
      //               int hours = dtExecuteBegin.totalHours() - dtBegin.totalHours();
      //               if(FLG_ZERO == (hours % interval)){
      //                  executeAble = checkHour(scheduleTime, dtExecuteBegin, dtExecuteEnd);
      //               }
      //               break;
      //            // 按分钟来计算
      //            case Minute:
      //               int minutes = dtExecuteBegin.totalMinutes() - dtBegin.totalMinutes();
      //               if(FLG_ZERO == (minutes % interval)){
      //                  executeAble = checkMinute(scheduleTime, dtExecuteBegin, dtExecuteEnd);
      //               }
      //               break;
      //            // 一次执行
      //            case Once:
      //               executeAble = checkTime(scheduleTime, dtExecuteBegin, dtExecuteEnd);
      //               break;
      //            // 缺省时，抛出异常
      //            default:
      //               throw new FFatalError("This type is not existed.");
      //         }
      //      }catch(Exception e){
      //         _logger.error(this, "checkExcute[schedule_id={0}]", e);
      //      }
      return executeAble;
   }

   /**
    * <T>检查计划是否在时间内执行</T>
    * <P>该时间检查是以小时为单位</P>
    * 
    * @param schedule 计划对象
    * @param beginTime 开始时间
    * @param endTime 结束时间
    * @return 是否在时间范围内
    *    <L value='true'>在时间范围</L>
    *    <L value='false'>在时间范围</L>
    */
   public boolean checkHour(ILogicScheduleTime schedule,
                            TDateTime beginTime,
                            TDateTime endTime){
      // 将当前日期和当前小时取出，和执行分钟和执行秒组装成新的执行时间
      //      TTime scheduleTime = schedule.executeTime().time();
      //      TDateTime executeTime = new TDateTime(endTime.year(), endTime.month(), endTime.day(), endTime.hour(), scheduleTime.minute(), scheduleTime.second(), scheduleTime.milliSecond());
      //      // 判断当前时间是否在开始时间和结束时间之间
      //      return executeTime.inRange(beginTime, endTime);
      return false;
   }

   /**
    * <T>检查计划是否在时间内执行</T>
    * <P>该时间检查是以分钟为单位</P>
    * 
    * @param schedule 计划对象
    * @param beginTime 开始时间
    * @param endTime 结束时间
    * @return 是否在时间范围内
    *    <L value='true'>在时间范围</L>
    *    <L value='false'>在时间范围</L>
    */
   public boolean checkMinute(ILogicScheduleTime schedule,
                              TDateTime beginTime,
                              TDateTime endTime){
      // 将当前日期和当前小时取出，和执行分钟和执行秒组装成新的执行时间
      //      TDateTime scheduleTime = schedule.executeTime();
      //      TDateTime executeTime = new TDateTime(endTime.year(), endTime.month(), endTime.day(), endTime.hour(), endTime.minute(), scheduleTime.second(), scheduleTime.milliSecond());
      //      // 判断当前时间是否在开始时间和结束时间之间
      //      return executeTime.inRange(beginTime, endTime);
      return false;
   }

   /**
    * <T>检查计划是否在时间内执行</T>
    * <P>该时间检查是以天为单位</P>
    * 
    * @param schedule 计划对象
    * @param beginTime 开始时间
    * @param endTime 结束时间
    * @return 是否在时间范围内
    *    <L value='true'>在时间范围</L>
    *    <L value='false'>在时间范围</L>
    */
   public boolean checkTime(ILogicScheduleTime schedule,
                            TDateTime beginTime,
                            TDateTime endTime){
      // 取出当前日期，和执行时间组装成新的执行时间
      //      TDateTime scheduleTime = schedule.executeTime();
      //      TDateTime executeTime = new TDateTime(endTime.year(), endTime.month(), endTime.day(), scheduleTime.hour(), scheduleTime.minute(), scheduleTime.second(), scheduleTime.milliSecond());
      //      // 判断当前时间是否在开始时间和结束时间之间
      //      return executeTime.inRange(beginTime, endTime);
      return false;
   }

   /**
    * <T>部署计划</T>
    * <P>调用<R link='ps:LG_SCHEDULE_DI'>LG_SCHEDULE_DI</R>中的<R link='ps:LG_SCHEDULE_DI|Do_Deploy'>Do_Deploy</R>方法
    * 部署计划。</P>
    * 
    * @param schedule 计划数据
    */
   public void doScheduleDeploy(ILogicSchedule schedule){
      ISqlConnection sqlCnn = null;
      IDatabaseConsole databaseConsole = RAop.find(IDatabaseConsole.class);
      try{
         sqlCnn = databaseConsole.alloc();
         ILgScheduleDi lgSchedule = new FLgScheduleImpl(sqlCnn);
         // 部署计划
         FSqlProcedure procedure = lgSchedule.doDeploy(null, schedule.id().get(), null);
         // 输出发布过的事件标识
         String eventIds = procedure.parameter("event_ids_").asString();
         FStrings ids = new FStrings();
         ids.unpack(eventIds);
         for(String eventId : ids){
            _logger.debug(this, "doScheduleDeploy", "Deploy event id={0}", eventId);
         }
      }finally{
         if(null != sqlCnn){
            databaseConsole.free(sqlCnn);
         }
      }
   }

   /**
    * <T>计划执行完毕后修改数据库</T>
    * <P>调用<R link='ps:LG_SCHEDULE_DI'>LG_SCHEDULE_DI</R>中的<R link='ps:LG_SCHEDULE_DI|Do_Deploy'>Do_Stop</R>方法
    * 部署计划。</P>
    * 
    * @param schedule 计划数据
    */
   public void doScheduleStop(ILogicSchedule schedule){
      ISqlConnection sqlCnn = null;
      IDatabaseConsole databaseConsole = RAop.find(IDatabaseConsole.class);
      try{
         sqlCnn = databaseConsole.alloc();
         ILgScheduleDi lgSchedule = new FLgScheduleImpl(sqlCnn);
         lgSchedule.doStop(null, schedule.id().get());
      }finally{
         if(null != sqlCnn){
            databaseConsole.free(sqlCnn);
         }
      }
   }

   /**
    * <T>起调函数。</T>
    * <P>通过三层过滤来筛选要执行的计划。<B/>
    * <OL>
    *    <L>当前日期在几计划的起始日期和结束日期之间</L>
    *    <L>计划是否在今天执行</L>
    *    <L>计划的执行时间在当前开始时间和结束时间之间</L>
    * </OL>
    * 筛选完后，调用<R link='ps:LG_SCHEDULE_DI'>LG_SCHEDULE_DI</R>中的<R link='ps:LG_SCHEDULE_DI|Do_Deploy'>Do_Deploy</R>方法
    * 发布事件。
    * </P>
    * 
    * @return 如果改线程没有处于停止状态，返回true，继续执行
    */
   @Override
   public boolean execute(){
      while(!_stop && !RThread.checkStop()){
         // 复制计划数组信息
         ILogicSchedule[] schedules = null;
         synchronized(_schedules){
            if(!_schedules.isEmpty()){
               schedules = _schedules.toObjects();
            }
         }
         // 检查计划触发时间
         if(null != schedules){
            TDateTime endDateTime = _dataDateConsole.currentDate();
            for(ILogicSchedule schedule : schedules){
               if(null != schedule){
                  // 如果当前日期时间在计划结束日期时间之后，则将该计划停止
                  //                  TDateTime endDate = schedule.endDate().addOn(_executeInterval);
                  //                  if(endDateTime.isAfter(endDate)){
                  //                     doScheduleStop(schedule);
                  //                     synchronized(_schedules){
                  //                        _schedules.remove(schedule.id().toString());
                  //                     }
                  //                  }else{
                  //                     // 处理该计划下所有的时间点
                  //                     FLogicScheduleTimes times = schedule.scheduleTimes();
                  //                     int count = times.count();
                  //                     for(int i = 0; i < count; i++){
                  //                        ILogicScheduleTime scheduleTime = times.value(i);
                  //                        if(null != scheduleTime){
                  //                           // 判断是否在执行时间内
                  //                           if(checkExecute(schedule, scheduleTime, _beginDateTime, endDateTime)){
                  //                              doScheduleDeploy(schedule);
                  //                           }
                  //                        }
                  //                     }
                  //                  }
               }
            }
            // 存储处理到的时间
            _beginDateTime.set(endDateTime);
         }
         // 设置线程睡眠时间
         RThread.sleep(_executeInterval);
      }
      return true;
   }

   /**
    * <T>设置数据库时间的控制台。</T>
    * 
    * @param dataDateConsole 数据库时间的控制台
    */
   public void setDataDateConsole(IDataDateConsole dataDateConsole){
      _dataDateConsole = dataDateConsole;
      _beginDateTime = _dataDateConsole.currentDate();
   }

   /**
    * <T>设置执行的时间间隔。</T>
    * 
    * @param interval 时间间隔，单位为微妙
    */
   public void setInterval(long interval){
      _executeInterval = interval;
   }

   /**
    * <T>设置计划对象。</T>
    * 
    * @param schedules 计划对象
    */
   public void setSchedules(FLogicSchedules schedules){
      _schedules = schedules;
   }

   /**
    * <T>停止执行。</T>
    */
   public void stopExecute(){
      _logger.info(this, "execute", "Stop Execute Thread...");
      _stop = true;
   }
}
