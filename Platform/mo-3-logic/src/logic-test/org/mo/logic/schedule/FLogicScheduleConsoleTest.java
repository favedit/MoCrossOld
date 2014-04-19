package org.mo.logic.schedule;

import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.RAop;

public class FLogicScheduleConsoleTest
{
   private static ILogger _logger = RLogger.find(FLogicScheduleConsoleTest.class);

   public static void main(String[] args){
      String config = "D:/Workspace/eUIS/src/eUIS-config/application-develop-process.xml";
      try{
         RAop.initialize(config);
         ILogicScheduleConsole console = RAop.find(ILogicScheduleConsole.class);
         //console.fetchSchedules();
         //RAop.release();
         console.initializeThreads();
      }catch(Exception e){
         _logger.error(null, "main", e);
      }
   }
}
