/*
 * @(#)FLogicEventQueryThread.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.event;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.IStringPair;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.system.FThread;
import org.mo.com.system.RThread;
import org.mo.core.aop.RAop;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.data.common.FSqlQuery;
import org.mo.eng.environment.IEnvironmentConsole;
import org.mo.logic.data.ILgEventDi;
import org.mo.logic.data.impl.FLgEventImpl;

/**
 * <T>事件查询线程类</T>
 * <P>该类主要用于从数据库中查询事件表，并将数据加载到对应的事件运行线程上<B/>
 * 该类继承了<R link='jv:org.mo.com.system|FThread'>FThread线程类</R>,并对外提供了
 * 设置线程起调时间等方法</P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/27
 */
public class FLogicEventQueryThread
      extends FThread
{
   // 全局日志对象
   private final static ILogger _logger = RLogger.find(FLogicEventConsole.class);

   public static final String NAME_MAIN = "main";

   // 数据服务对象
   protected IDatabaseConsole _databaseConsole;

   // 环境服务对象
   protected IEnvironmentConsole _environmentConsole;

   // 事件列表
   protected FLogicEvents _events = new FLogicEvents();

   // 数据查询命令
   protected FSqlQuery _eventsQuery;

   // 线程起调间隔
   protected int _interval;

   // 设置线程的运行
   protected boolean _notifyStop = false;

   // 线程池对象
   protected FLogicEventThreadPool _pool;

   // runners列表
   protected FLogicEventRunners _runners = new FLogicEventRunners();

   /**
    * <T>设置事件为等待状态</T>
    * <P>调用<R link = 'ps:LG_EVENT_DI'>LG_EVENT_DI</R>包的
    * <R link = 'ps:LG_EVENT_DI|Do_Wait'>Do_Wait()</R>函数，用来更改事件的状态为等待</P>
    * 
    * @param event 要修改状态的事件对象
    * @see 参见<R link = 'ps:LG_EVENT_DI'>LG_EVENT_DI包</R>
    */
   public void doWait(ISqlConnection connection,
                      ILogicEvent event){
      _logger.debug(this, "doWait", "change event's status to Wait");
      /// 收集数据库连接
      // 获得事件的参数内容
      FSqlQuery parameterQuery = new FSqlQuery(connection, FLogicEventConsole.class, "event.parameter");
      parameterQuery.bindInteger("event_id", event.id().get());
      FDataset dsParameter = parameterQuery.fetchDataset();
      if(null != dsParameter){
         FAttributes names = new FAttributes();
         // 获取参数
         for(FRow row : dsParameter){
            String paramName = row.get("name");
            String paramType = row.get("type_cd");
            names.set(paramName, paramType);
            if(paramType.equals(ELogicEventParameter.String)){
               event.eventParameters().set(paramName, row.get("data_value"));
            }else if(paramType.equals(ELogicEventParameter.Row)){
               FRow paramRow = new FRow();
               paramRow.unpack(row.get("data_value"));
               event.eventParameters().set(paramName, paramRow);
            }
         }
         // 获取数据集类型的参数
         for(IStringPair pair : names){
            String paramName = pair.name();
            String paramType = pair.value();
            FDataset paramDs = new FDataset();
            if(paramType.equals(ELogicEventParameter.Dataset.toString())){
               for(FRow row : dsParameter){
                  if(paramName.equals(row.get("name"))){
                     FRow paramRow = new FRow();
                     paramRow.unpack(row.get("data_value"));
                     paramDs.push(paramRow);
                  }
               }
               event.eventParameters().set(paramName, paramDs);
            }
         }
      }
      /// 创建procedures对象
      ILgEventDi lgEvent = new FLgEventImpl(connection);
      /// 执行procedures函数
      lgEvent.doWait(null, event.id().get());
   }

   /**
    * <T>起调函数</T>
    *
    * @history 090618 YANGH  收到停止命令后停止运行。
    * @history 090608 MAOCY 当查询过程出现错误的时候，不发生例外退出。
    */
   @Override
   public boolean execute(){
      /// 获得服务对象
      _databaseConsole = RAop.find(IDatabaseConsole.class);
      _environmentConsole = RAop.find(IEnvironmentConsole.class);
      /// 获得数据库管理器
      _eventsQuery = new FSqlQuery(FLogicEventConsole.class, "event.list");
      _eventsQuery.bindString("server_code", _environmentConsole.findRegister("server_process"));
      /// 在系统未停止的情况下按时间间隔循环执行
      while((!RThread.checkStop()) && (!_notifyStop)){
         try{
            fetchEvents();
         }catch(Exception e){
            _logger.error(this, "execute", e);
         }
         RThread.sleep(_interval);
      }
      return true;
   }

   /**
    * <T>查询数据库取事件</T>
    * <P>查询数据库取得事件，并将事件按分组插入到对应的runner上</P>
    */
   public void fetchEvents(){
      _logger.debug(this, "fetchEvents", "connect database to fetch events");
      // 清空事件队列
      _events.clear();
      ISqlConnection connection = null;
      try{
         /// 获得数据库连接
         connection = _databaseConsole.alloc();
         _eventsQuery.linkConnect(connection);
         /// 查询数据库信息，获得可执行得事件列表
         FDataset dataset = _eventsQuery.fetchDataset();
         if(!dataset.isEmpty()){
            for(FRow row : dataset){
               // 一个事件执行错误，继续执行下一个事件，仅对当前事件输出错误信息
               try{
                  FLogicEvent event = new FLogicEvent();
                  event.loadConfig(row);
                  /// 设置事件的状态为等待
                  doWait(connection, event);
                  _events.push(event);
               }catch(Exception e){
                  _logger.error(this, "fetchEvents", e);
               }
            }
         }
      }finally{
         if(null != connection){
            _databaseConsole.free(connection);
         }
      }
      // 将所有事件对象放入线程
      if(!_events.isEmpty()){
         for(ILogicEvent event : _events){
            /// 按分组部署事件对象到对应的工作线程上，如没有分组，则放入主工作线程
            String groupName = RString.nvl(event.groupName(), NAME_MAIN);
            FLogicEventRunner runner = _runners.get(groupName);
            if(null == runner){
               runner = new FLogicEventRunner();
               _runners.set(groupName, runner);
            }
            runner.push(event);
            if(!runner.isExecute()){
               /// 标识线程的状态为在执行
               runner.setExecuteStart();
               /// 将事件放入缓冲池
               _pool.process(runner);
            }
         }
      }
   }

   /**
    * <T>设置线程是否停止</T>
    * 
    */
   public void notifyStop(){
      _notifyStop = true;
   }

   /**
    * <T>设置线程的起调间隔</T>
    * 
    * @param interval 线程的起调间隔，从console中获得
    */
   public void setInterval(int interval){
      _interval = interval;
   }

   /**
    * <T>启动线程池</T>
    * 
    */
   public void startPool(FLogicEventThreadPool pool){
      _pool = pool;
   }
}
