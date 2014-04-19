/*
 * @(#)FConnectionMonitor.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.data;

import org.mo.core.monitor.common.FAbstractMonitor;

/**
 * <p>数据库链接监视器</p>
 * 
 * @author ALEX
 */
public class FConnectionMonitor
      extends FAbstractMonitor
{
   // 数据库链接控制台
   protected IConnectionConsole _console;

   /**
    * <p>创建数据库链接监视器的实例</p>
    *
    * @param console 数据库链接控制台
    */
   public FConnectionMonitor(IConnectionConsole console){
      _console = console;
   }

   /**
    * <p>执行监视器的逻辑</p>
    *
    */
   @Override
   public void execute(){
      _console.refresh();
   }
}
