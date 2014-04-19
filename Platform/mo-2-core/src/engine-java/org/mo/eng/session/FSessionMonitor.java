/*
 * @(#)FSessionMonitor.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.session;

import org.mo.core.monitor.common.FAbstractMonitor;

/**
 * <T>应用程序线程监视器。</T>
 * 
 * @author maocy
 * @version 1.00 - 2008/11/18
 */
public class FSessionMonitor
      extends FAbstractMonitor
{
   // 关联的线程控制台
   private final FSessionConsole _console;

   /**
    * <p>创建数据容器监视器的实例</p>
    *
    * @param console 线程控制台
    */
   public FSessionMonitor(FSessionConsole console){
      _console = console;
   }

   /* (non-Javadoc)
    * @see org.mo.core.monitor.common.FAbstractMonitor#execute()
    */
   @Override
   public void execute(){
      _console.refresh();
   }
}
