package org.mo.eng.cache;

import org.mo.core.monitor.common.FAbstractMonitor;

public class FCacheMonitor
      extends FAbstractMonitor
{
   private final FCacheConsole _console;

   public FCacheMonitor(FCacheConsole console){
      _console = console;
   }

   @Override
   public void execute(){
      _console.refresh();
   }
}
