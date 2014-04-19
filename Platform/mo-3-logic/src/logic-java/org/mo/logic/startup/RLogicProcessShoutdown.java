/*
 * @(#)RLogicProcessManager.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 */
package org.mo.logic.startup;

import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.resource.IResource;
import org.mo.com.resource.RResource;

/**
 * <T>服务线程的启动控制器。</T>
 *
 * @author maocy
 * @version 1.00 - 2008/12/15
 */
public class RLogicProcessShoutdown
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(RLogicProcessShoutdown.class);

   private static IResource _resource = RResource.find(RLogicProcessManager.class);

   @SuppressWarnings("unused")
   private static String CMD_SHOUTDOWN = "SHOUTDOWN\r\n";

   /**
    * <T>默认启动函数</T>
    * 
    * @param arg 参数集合
    */
   @SuppressWarnings("unused")
   public static void main(String[] arg){
      try{
         // 启动监听程序
         int serverport = _resource.findInteger("server.port");
         //FSocket socket = new FSocket(serverport);
         //socket.output().write(CMD_SHOUTDOWN);
         //socket.close();
      }catch(Exception e){
         _logger.error(null, "main", e);
      }
   }
}
