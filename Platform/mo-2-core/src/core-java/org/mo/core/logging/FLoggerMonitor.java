/*
 * @(#)FLoggerMonitor.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.core.logging;

import org.mo.core.monitor.common.FAbstractMonitor;

/**
 * <p>日志监视器</p>
 *
 * @author ALEX
 */
public class FLoggerMonitor
      extends FAbstractMonitor
{
   // 关联的属性控制台
   private final FLoggerConsole _console;

   /**
    * <p>创建属性监视器的实例</p>
    *
    * @param console 属性控制台
    */
   public FLoggerMonitor(FLoggerConsole console){
      _console = console;
   }

   /**
    * <T>执行监视器的逻辑</T>
    */
   @Override
   public void execute(){
      _console.refresh();
   }
}
