package org.mo.core.monitor;

import org.mo.core.monitor.common.FDirectoriesMonitor;

//============================================================
// <T>设置监视器。</T>
//============================================================
public class FConfigMonitor
      extends FDirectoriesMonitor
{
   // 控制台
   @SuppressWarnings("unused")
   private final IMonitorConsole _console;

   //============================================================
   // <T>构造设置监视器。</T>
   //
   // @param 监视控制台
   //============================================================
   public FConfigMonitor(IMonitorConsole console){
      _console = console;
   }
}
