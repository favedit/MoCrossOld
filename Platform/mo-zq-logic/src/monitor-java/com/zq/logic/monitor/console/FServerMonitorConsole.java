package com.zq.logic.monitor.console;

import org.mo.com.lang.FObject;
import org.mo.com.lang.FObjects;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

//============================================================
// <T>服务器监视控制台。</T>
//============================================================ 
public class FServerMonitorConsole
      extends FObject
      implements
         IServerMonitorConsole{

   // 日志输出接口
   private static ILogger _logger = RLogger.find(FServerMonitorConsole.class);

   // 监视器集合
   protected FObjects<IServerMonitor> _monitors = new FObjects<IServerMonitor>(IServerMonitor.class);

   //============================================================
   // <T>注册监视器。</T>
   //
   // @param monitor 监视器
   //============================================================
   @Override
   public void register(IServerMonitor monitor){
      if(monitor.valid()){
         _logger.info(this, "register", "Register monitor. (monitor={1})", monitor);
         monitor.setup();
         _monitors.push(monitor);
      }
   }

   //============================================================
   // <T>启动处理。</T>
   //============================================================
   @Override
   public void startup(){
      // 启动所有线程
      for(IServerMonitor monitor : _monitors){
         monitor.startup();
      }
      // 等待线程结束
      for(IServerMonitor monitor : _monitors){
         monitor.join();
      }
   }
}
