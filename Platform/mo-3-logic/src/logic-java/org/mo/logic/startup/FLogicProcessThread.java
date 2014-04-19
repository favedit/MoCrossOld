package org.mo.logic.startup;

import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.net.FServerSocket;
import org.mo.com.net.FSocket;
import org.mo.com.system.FThread;
import org.mo.core.aop.RAop;

public class FLogicProcessThread
      extends FThread
{
   private static ILogger _logger = RLogger.find(FLogicProcessThread.class);

   private FServerSocket _serverSocket;

   public FLogicProcessThread(FServerSocket serverSocket){
      // 初始化系统环境
      _serverSocket = serverSocket;
   }

   @Override
   public boolean execute(){
      // 循环监听合法命令。
      while(true){
         FSocket socket = _serverSocket.accept();
         String command = socket.input().readLine(64).toString();
         _logger.info(this, "execute", "Receive command. (command={0})", command);
         if("shoutdown".equalsIgnoreCase(command)){
            RAop.release();
            break;
         }
         socket.close();
      }
      return true;
   }
}
