/*
 * @(#)FConfigMonitor.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.queue;

import org.mo.com.lang.FError;
import org.mo.core.monitor.common.FFileMonitor;

/**
 * <p>队列事件控制文件监视</p>
 *
 * @author ALEX
 * @version 1.00 - 2005/10/26
 */
public class FConfigMonitor
      extends FFileMonitor
{

   // 队列事件控制台
   private FQueueConsole m_oQueueConsole = null;

   /**
    * <p>创建文件监视器的实例</p>
    * <p>create date:2005/02/18</p>
    *
    * @param oQueueConsole 监视器控制台
    * @param sPath 文件根路径
    */
   public FConfigMonitor(FQueueConsole oQueueConsole,
                         String sFileName){
      super(sFileName);
      m_oQueueConsole = oQueueConsole;
   }

   /**
    * <p>响应发生变动的文档</p>
    * <p>create date:2005/02/18</p>
    *
    * @exception FError 应用程序例外
    */
   @Override
   public void onFileChanged() throws FError{
      m_oQueueConsole.refreshConfig();
   }
}
