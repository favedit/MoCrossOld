/*
 * @(#)FLogicScheduleConsole.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.schedule;

import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.data.date.IDataDateConsole;

/**
 * <T>计划服务类</T>
 * <P>该类主要提供了<R link='jv-c:org.mo.logic.schedule.FLogicScheduleQueryThread'>取数据线程</R>
 * 和<R link='jv-c:org.mo.logic.schedule.FLogicScheduleQueryThread'>执行线程</R>的初始化操作以及线程的启动<B/>
 * 同时该类作为计划的入口，也提供了一些基本变量的初始化。</P>
 * 
 * @author HYKUN
 * @modify HYKUN 增加停止线程的功能。
 * @version 1.00 - 2008/10/27
 */
public class FLogicScheduleConsole
      implements
         ILogicScheduleConsole
{
   // 日志输出对象
   private final static ILogger _logger = RLogger.find(FLogicScheduleExecuteThread.class);

   @ALink
   protected IDataDateConsole _dataDateConsole;

   // 执行线程事件间隔
   @AProperty
   protected int _executeInterval;

   // 执行线程
   protected FLogicScheduleExecuteThread _executeThread;

   // 查询线程事件间隔
   @AProperty
   protected int _queryInterval;

   // 查询线程
   protected FLogicScheduleQueryThread _queryThread;

   // 计划列表
   protected FLogicSchedules _schedules;

   /* (non-Javadoc)
    * @see org.mo.logic.schedule.ILogicScheduleConsole#initializeThreads()
    */
   @Override
   public void initializeThreads(){
      // 按传入的间隔时间构造取数据线程；
      _schedules = new FLogicSchedules();
      _queryThread = new FLogicScheduleQueryThread();
      _queryThread.setInterval(_queryInterval);
      _queryThread.setDataDateConsole(_dataDateConsole);
      // 从query线程读入_scheules；
      _queryThread.setSchedules(_schedules);
      /// 按传入的间隔时间构造执行线程；
      _executeThread = new FLogicScheduleExecuteThread();
      _executeThread.setInterval(_executeInterval);
      _executeThread.setDataDateConsole(_dataDateConsole);
      /// 执行线程从console读入_scheules；
      _executeThread.setSchedules(_schedules);
      /// 启动线程
      _queryThread.start();
      _logger.info(this, "initializeThreads", "Start Query Thread...");
      _executeThread.start();
      _logger.info(this, "initializeThreads", "Start Execute Thread...");
   }

   /* (non-Javadoc)
    * @see org.mo.logic.schedule.ILogicScheduleConsole#releaseThreads()
    */
   @Override
   public void releaseThreads(){
      // 按传入的间隔时间构造取数据线程；
      _queryThread.stopQuery();
      _executeThread.stopExecute();
   }
}
