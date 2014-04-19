package com.zq.logic.monitor.console;

import org.mo.com.lang.RDateTime;
import org.mo.com.lang.type.TDateTime;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.system.FThread;

//============================================================
// <T>服务监视线程。</T>
//============================================================
public class FServerMonitorThread
      extends FThread{

   // 日志输出接口
   private static ILogger _logger = RLogger.find(FServerMonitorThread.class);

   // 服务监视接口
   protected IServerMonitor _monitor;

   //============================================================
   // <T>构造服务监视线程。</T>
   //============================================================
   public FServerMonitorThread(){
   }

   //============================================================
   // <T>获得监视器接口。</T>
   //
   // @return 监视器接口
   //============================================================ 
   public IServerMonitor monitor(){
      return _monitor;
   }

   //============================================================
   // <T>设置监视器接口。</T>
   //
   // @param monitor 监视器接口
   //============================================================ 
   public void setMonitor(IServerMonitor monitor){
      _monitor = monitor;
   }

   //============================================================
   // <T>执行处理。</T>
   //
   // @return 处理结果
   //============================================================
   @Override
   public boolean execute(){
      while(!_stopped){
         // 计算处理
         TDateTime beginDate = RDateTime.currentDateTime();
         boolean result = _monitor.process();
         TDateTime endDate = RDateTime.currentDateTime();
         long spend = endDate.get() - beginDate.get();
         _logger.info(this, "execute", "Process monitor. (class={1}, start={2}, end={3}, spend={4}ms)", _monitor.name(), beginDate.format(), endDate.format(), spend);
         // 没有需要计算的时候，则休眠处理
         if(result){
            trySleep(_monitor.interval());
         }
      }
      return true;
   }
}
