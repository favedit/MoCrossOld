/*
 * @(#)FShutdownThread.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.system;

import org.mo.com.lang.FError;
import org.mo.com.system.FThread;

/**
 * <p>系统关闭线程</p>
 *
 * @author ALEX
 * @version 1.00 - 2005/11/03
 */
public class FShutdownThread
      extends FThread
{
   @SuppressWarnings("unused")
   private FSystemConsole m_oConsole;

   /**
    * <p>创建系统关闭线程的实例</p>
    * <p>create date:2005/11/03</p>
    *
    * @param oSystemConsole 系统控制台
    * @exception FError 应用程序例外
    */
   public FShutdownThread(FSystemConsole oSystemConsole){
      m_oConsole = oSystemConsole;
   }

   /**
    * <p>执行业务逻辑</p>
    * <p>create date:2005/02/18</p>
    *
    */
   public boolean execute(){
      //m_oConsole.contextDestroyed(true);
      return true;
   }
}
