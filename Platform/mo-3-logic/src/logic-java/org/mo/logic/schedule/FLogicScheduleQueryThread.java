/*
 * @(#)FLogicScheduleQueryThread.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.schedule;

import org.mo.com.collections.FDataset;
import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.RDateTime;
import org.mo.com.lang.type.TDateTime;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.system.FThread;
import org.mo.com.system.RThread;
import org.mo.core.aop.RAop;
import org.mo.data.date.IDataDateConsole;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.data.common.FSqlQuery;

/**
 * <T>计划查询线程</T>
 * <P>该类主要包含了从数据库取得对应的数据并将数据推入到计划列表的过程，主要包括如下内容：</B>
 * <OL>
 *    <L>一定的时间间隔不断的从数据表里取得数据</L>
 *    <L>数据的取得以更新时间是否在当前时间范围内为依据</L>
 *    <L>将取得的数据推入到计划列表容器里，每次推入前判断该数据在计划列表容器里是否存在</L>
 * </OL>
 * </P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/30
 */
public class FLogicScheduleQueryThread
      extends FThread
{
   // 全局日志对象
   private final static ILogger _logger = RLogger.find(FLogicScheduleConsole.class);

   private IDataDateConsole _dataDateConsole;

   // 最终日期时间类型
   protected TDateTime _lastDateTime = new TDateTime();

   // 线程执行间隔
   protected long _queryInterval;

   protected FLogicSchedules _schedules;

   protected boolean _stop = false;

   /* (non-Javadoc)
    * @see org.mo.com.system.FThread#execute()
    */
   @Override
   public boolean execute(){
      while(!_stop && !RThread.checkStop()){
         fetchSchedule();
         RThread.sleep(_queryInterval);
      }
      return true;
   }

   /**
    * <T>从数据库提取数据</T>
    * <P>查询数据库，查询所有从开始时间至当前时间之间的所有更新（包括修改的和新建的）<B/>
    * 计划，如果记录的id已存在，则删除原纪录，将该记录插入</P>
    */
   public void fetchSchedule(){
      // 记录对应的log信息，表示线程开始提取数据
      if(_logger.debugAble()){
         _logger.debug(this, "fetchSchedule", "Connect database to fetch scheules start.");
      }
      ISqlConnection sqlConnect = null;
      IDatabaseConsole databaseConsole = RAop.find(IDatabaseConsole.class);
      try{
         // 创建连接
         sqlConnect = databaseConsole.alloc();
         // 执行查询
         FSqlQuery query = new FSqlQuery(sqlConnect, FLogicScheduleConsole.class, "schedule.list");
         // 取得当前时间
         TDateTime dtNow = _dataDateConsole.currentDate();
         // 为SQL语句绑定参数
         // 开始时间为上次执行的最终时间
         query.bindString("begin_time", RDateTime.format(_lastDateTime.get()));
         // 结束时间为当前时间
         query.bindString("end_time", RDateTime.format(dtNow.get()));
         FDataset dataset = query.fetchDataset();
         // 如果数据不为空，循环取出
         if(!dataset.isEmpty()){
            int count = dataset.count();
            ILogicSchedule[] fetchSchedules = new ILogicSchedule[count];
            ILogicScheduleTime[] fetchScheduleTimes = new ILogicScheduleTime[count];
            for(int n = 0; n < count; n++){
               // 创建计划对象和计划时间对象
               FLogicSchedule schedule = new FLogicSchedule();
               FLogicScheduleTime scheduleTime = new FLogicScheduleTime();
               // 将数据设置到对应的变量
               schedule.loadConfig(dataset.get(n));
               scheduleTime.loadConfig(dataset.get(n));
               fetchSchedules[n] = schedule;
               fetchScheduleTimes[n] = scheduleTime;
            }
            // 同步其他线程，更新计划集合
            synchronized(_schedules){
               for(ILogicSchedule item : fetchSchedules){
                  if(_schedules.contains(item.id().toString()) && item.status() == ELogicScheduleStatus.Stop){
                     _schedules.remove(item.id().toString());
                     continue;
                  }
                  _schedules.set(item.id().toString(), item);
               }
               for(ILogicScheduleTime item : fetchScheduleTimes){
                  ILogicSchedule temp = _schedules.get(item.scheduleId().toString());
                  if(null != temp){
                     FLogicScheduleTimes logicScheduleTimes = _schedules.get(item.scheduleId().toString()).scheduleTimes();
                     logicScheduleTimes.set(item.id().toString(), item);
                  }
               }
            }
         }
         // 把上次取得的当前日期设置保存
         _lastDateTime.set(dtNow);
      }catch(Exception e){
         // 有异常，记录log
         _logger.error(this, "fetchSchedule", e);
      }finally{
         // 关闭连接
         if(null != sqlConnect){
            databaseConsole.free(sqlConnect);
         }
         _logger.debug(this, "fetchSchedule", "Connect database to fetch scheules end.");
      }
   }

   public void setDataDateConsole(IDataDateConsole dataDateConsole){
      _dataDateConsole = dataDateConsole;
   }

   /**
    * <T>设置时间间隔</T>
    * 
    * @param interval 线程睡眠时间 
    */
   public void setInterval(long interval){
      _queryInterval = interval;
   }

   /**
    * <T>设置计划数据值</T>
    * 
    * @param scheduleTimes 计划数据项
    * 
    */
   public void setSchedules(FLogicSchedules schedules){
      _schedules = schedules;
   }

   public void stopQuery(){
      this._stop = true;
   }
}
