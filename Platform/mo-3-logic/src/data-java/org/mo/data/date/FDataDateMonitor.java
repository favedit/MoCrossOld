/*
 * @(#)FDataDateMonitor.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.data.date;

import org.mo.core.monitor.common.FAbstractMonitor;

/**
 * <T>监视器获取初始化的配置信息<T>
 * 
 * @author ZENGG
 * @version 1.01
 */
public class FDataDateMonitor
      extends FAbstractMonitor
{
   // 时间控制台对象
   private final FDataDateConsole _dateConsole;

   /**
    * <T>初始化时间控制台对象</T>
    * 
    * @param dateConsole 时间控制台对象
    */
   public FDataDateMonitor(FDataDateConsole dateConsole){
      _dateConsole = dateConsole;
   }

   /* (non-Javadoc)
    * @see org.mo.core.monitor.common.FAbstractMonitor#execute()
    */
   @Override
   public void execute(){
      _dateConsole.fetchDatabaseDate();
   }
}
