/*
 * @(#)FWebServletMonitor.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.core.servlet;

import org.mo.com.lang.FError;
import org.mo.core.monitor.common.FDirectoriesMonitor;
import org.mo.core.monitor.common.FFileInfo;
import org.mo.web.core.service.FServiceConsole;

/**
 * <p>网络请求设置文件监视器</p>
 * 
 * @author ALEX
 */
public class FWebServletMonitor
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
   public FWebServletMonitor(FServiceConsole oConsole){
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
