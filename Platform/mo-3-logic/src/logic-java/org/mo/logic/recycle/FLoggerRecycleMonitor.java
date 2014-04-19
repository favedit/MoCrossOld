package org.mo.logic.recycle;

import org.mo.core.monitor.common.FAbstractMonitor;

public class FLoggerRecycleMonitor
      extends FAbstractMonitor
{
   protected FLoggerRecycleConsole _console;

   public FLoggerRecycleMonitor(FLoggerRecycleConsole console){
      _console = console;
   }

   @Override
   public void execute(){
      _console.refresh();
   }
}
