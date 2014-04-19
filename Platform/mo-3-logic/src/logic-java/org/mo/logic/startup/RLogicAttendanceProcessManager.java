package org.mo.logic.startup;

import org.mo.com.io.RFile;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.net.FServerSocket;
import org.mo.com.resource.IResource;
import org.mo.com.resource.RResource;
import org.mo.com.system.RSystem;
import org.mo.core.aop.RAop;

/**
 * <T>服务线程的启动控制器。</T>
 *
 * @author ZENGD
 * @version 1.00 - 2008/12/15
 */
public class RLogicAttendanceProcessManager
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(RLogicAttendanceProcessManager.class);

   private static IResource _resource = RResource.find(RLogicProcessManager.class);

   /**
    * <T>默认启动函数</T>
    * 
    * @param arg 参数集合
    */
   public static void main(String[] arg){
      try{
         // 获得工作路径和启动配置文件
         String path = RSystem.property("user.mobj.class");
         String config = RSystem.property("user.mobj.config");
         String fileName = RFile.makeFilename(path, config + ".xml");
         // 启动监听程序
         int serverport = _resource.findInteger("server.port.at");
         FServerSocket serverSocket = new FServerSocket(serverport);
         new FLogicProcessThread(serverSocket).start();
         // 初始化系统环境
         RAop.initialize(fileName);
         // 启动监听线程 
      }catch(Exception e){
         _logger.error(null, "main", e);
      }
   }
}
