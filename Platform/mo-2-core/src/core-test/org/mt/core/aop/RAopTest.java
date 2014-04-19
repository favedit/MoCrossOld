package org.mt.core.aop;

import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.RAop;

public class RAopTest
{
   private static ILogger _logger = RLogger.find(RAopTest.class);

   public static void main(String[] args){
      try{
         String configFileName = "D:/WP-Platform/mo-3-script/src/script-config/application.xml";
         RAop.configConsole().loadFile(configFileName);
         //IBindConsole dc = RAop.find(IBindConsole.class);
         //IDatabaseConsole dc = RAop.find(IDatabaseConsole.class);
         //System.out.println(dc);
         RAop.release();
      }catch(Exception e){
         _logger.error(RAopTest.class, "testLoadConfig", e);
      }
   }
}
