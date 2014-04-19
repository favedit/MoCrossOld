/*
 * @(#)FServiceMonitor.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.web.core.service;

import org.mo.com.lang.FError;
import org.mo.core.monitor.common.FDirectoriesMonitor;
import org.mo.core.monitor.common.FFileInfo;

/**
 * <p>网络服务设置文件监视器</p>
 * 
 * @author ALEX
 */
public class FServiceMonitor
      extends FDirectoriesMonitor
{
   // 网络服务控制台
   @SuppressWarnings("unused")
   private FServiceConsole _console = null;

   /**
    * <p>创建文件路径监视器的实例</p>
    * <p>create date:2005/10/26</p>
    *
    * @param oConsole 网络服务控制台
    * @param sDirectory 文件根路径
    * @exception FError 应用程序例外
    */
   public FServiceMonitor(FServiceConsole oConsole){
      _console = oConsole;
   }

   @Override
   public void doFileChange(FFileInfo info){
      // TODO Auto-generated method stub

   }

   @Override
   public void doFileRemove(FFileInfo info){
      // TODO Auto-generated method stub

   }

   @Override
   public void doFileSave(FFileInfo info){
      // TODO Auto-generated method stub

   }

   @Override
   public void doInitializeFile(FFileInfo info){
      // TODO Auto-generated method stub

   }

}
