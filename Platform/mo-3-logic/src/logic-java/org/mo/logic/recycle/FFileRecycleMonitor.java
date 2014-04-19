package org.mo.logic.recycle;

import org.mo.core.monitor.common.FAbstractMonitor;

public class FFileRecycleMonitor
      extends FAbstractMonitor
{
   protected FFileRecycleConsole _console;

   public FFileRecycleMonitor(FFileRecycleConsole console){
      _console = console;
   }

   @Override
   public void execute(){
      _console.refresh();
   }
}
