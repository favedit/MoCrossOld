/*
 * @(#)FRefreshMonitor.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.core.monitor.common;

public class FRefreshMonitor
      extends FAbstractMonitor
{
   private final IMonitorRefresh _refresh;

   public FRefreshMonitor(IMonitorRefresh refresh){
      _refresh = refresh;
   }

   @Override
   public void execute(){
      if(null != _refresh){
         _refresh.refreshMonitor();
      }
   }
}
