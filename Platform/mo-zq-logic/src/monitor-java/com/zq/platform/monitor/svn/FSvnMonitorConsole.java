package com.zq.platform.monitor.svn;

import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.ALink;
import org.mo.core.monitor.IMonitorConsole;

//============================================================
// <T>版本监视器控制台。</T>
//============================================================
public class FSvnMonitorConsole
      implements
         ISvnMonitorConsole
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FSvnMonitorConsole.class);

   // 监视器控制台接口
   @ALink
   protected static IMonitorConsole _monitorConsole;

   //============================================================
   // <T>注册监视器。</T>
   //
   // @param monitor 监视器
   //============================================================
   @Override
   public void register(ISvnMonitor monitor){
      _monitorConsole.register(monitor);
   }

   //============================================================
   // <T>启动处理。</T>
   //============================================================
   @Override
   public void startup(){
      _logger.debug(this, "startup", "Startup svn monitor console.");
      _monitorConsole.waitStop();
   }
}
