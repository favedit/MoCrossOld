package org.mo.logic.test;

import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.RAop;

public class RTest
{
   private static ILogger _logger = RLogger.find(RTest.class);

   public static void main(String[] args){
      args = new String[]{"D:/Workspace/eUIS/src/eUIS-config/application.xml"};
      if(args.length != 1){
         _logger.error(null, "main", "Parameter error! (count!=1)");
         return;
      }
      try{
         RAop.configConsole().loadFile(args[0]);
         ITest test = RAop.find(ITest.class);
         test.connect();
         RAop.release();
      }catch(Exception e){
         _logger.error(null, "main", e);
      }
   }
}
