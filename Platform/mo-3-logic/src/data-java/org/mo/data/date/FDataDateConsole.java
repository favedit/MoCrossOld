/*
 * @(#)FDataDateConsole.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.data.date;

import java.util.Date;
import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.RDateTime;
import org.mo.com.lang.type.TDateTime;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.core.monitor.IMonitorConsole;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.data.common.FSqlQuery;

/**
 * <T>时间控制台实体获取当前时间</>
 * 
 * @author ZENGG
 * @version 1.01
 */
public class FDataDateConsole
      implements
         IDataDateConsole
{
   // 连接数据库接口
   @ALink
   protected IDatabaseConsole _databaseConsole;

   // 时间
   private long _dateDiff;

   // 触发时间间隔
   @AProperty
   protected long _interval;

   // 日志
   private final ILogger _logger = RLogger.find(FDataDateConsole.class);

   // 监视器
   protected FDataDateMonitor _monitor;

   // 监视器接口
   @ALink
   protected IMonitorConsole _monitorConsole;

   /**
    * <T>获取线程中的配置文件信息<T>
    */
   public void connect(){
      _monitor = new FDataDateMonitor(this);
      // 设置触发时间间隔
      _monitor.setInterval(_interval);
      _monitorConsole.register(_monitor);
      // 取数据库时间
      fetchDatabaseDate();
   }

   /* (non-Javadoc)
    * @see org.mo.data.date.IDataDateConsole#currentDate()
    */
   @Override
   public TDateTime currentDate(){
      return new TDateTime(RDateTime.currentDateTime().get() + _dateDiff);
   }

   /**
    * <T>查数据库的时间与系统时间求时间差</T>
    */
   public void fetchDatabaseDate(){
      _logger.debug(this, "fetchDatabaseDate", "Check time difference.", "");
      ISqlConnection connection = null;
      try{
         // 建立数据库连接
         connection = _databaseConsole.alloc();
         FSqlQuery query = new FSqlQuery(connection, FDataDateConsole.class, "database.time");
         // 获取数据库的日期时间
         Date dbDate = RDateTime.parse(query.executeScalar());
         // 获取系统时间
         long systemTime = RDateTime.currentDateTime().get();
         // 获取时间差
         _dateDiff = dbDate.getTime() - systemTime;
      }catch(Exception e){
         _logger.error(this, "fetchDatabaseDate", e);
      }finally{
         if(null != connection){
            _databaseConsole.free(connection);
         }
      }
   }
}
