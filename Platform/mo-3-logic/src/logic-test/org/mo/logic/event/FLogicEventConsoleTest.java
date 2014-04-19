package org.mo.logic.event;

import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.RAop;

public class FLogicEventConsoleTest
{
   private static ILogger _logger = RLogger.find(FLogicEventConsoleTest.class);

   public static void main(String[] args){
      String config = "D:/Workspace/eUIS/src/eUIS-config/application-test.xml";
      try{
         RAop.initialize(config);
         //ILogicEventConsole console = RAop.find(ILogicEventConsole.class);
         //console.fetchSchedules();
         // RAop.release();
      }catch(Exception e){
         _logger.error(null, "main", e);
      }
   }
}
