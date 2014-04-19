package org.mo.web.core.container;

import org.mo.core.monitor.common.FAbstractMonitor;

//============================================================
// <T>数据容器监视器。</T>
//============================================================
public class FWebContainerMonitor
      extends FAbstractMonitor
{
   // 数据容器控制台
   protected FWebContainerConsole _console;

   //============================================================
   // <T>构造数据容器监视器。</T>
   //
   // @param oConsole 控制台
   //============================================================
   public FWebContainerMonitor(FWebContainerConsole oConsole){
      _console = oConsole;
   }

   //============================================================
   // <T>执行监视器的逻辑。</T>
   //============================================================
   @Override
   public void execute(){
      // _console.refresh();
   }
}
