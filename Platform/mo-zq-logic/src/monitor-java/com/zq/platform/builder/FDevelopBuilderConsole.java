package com.zq.platform.builder;

import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.ALink;
import org.mo.core.monitor.IMonitorConsole;

//============================================================
// <T>开发编译器控制台。</T>
//============================================================
public class FDevelopBuilderConsole
      implements
         IDevelopBuilderConsole
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FDevelopBuilderConsole.class);

   // 监视器控制台接口
   @ALink
   protected static IMonitorConsole _monitorConsole;

   //============================================================
   // <T>注册构建器。</T>
   //
   // @param builder 构建器
   //============================================================
   @Override
   public void register(IDevelopBuilder builder){
      FDevelopBuilderMonitor monitor = new FDevelopBuilderMonitor();
      monitor.setValid(builder.isValid());
      monitor.setInterval(builder.interval());
      monitor.setBuilder(builder);
      _monitorConsole.register(monitor);
   }

   //============================================================
   // <T>启动处理。</T>
   //============================================================
   @Override
   public void startup(){
      _logger.debug(this, "startup", "Startup process monitor console.");
      _monitorConsole.waitStop();
   }
}
