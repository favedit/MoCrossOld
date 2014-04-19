package org.mo.jfa.core.webform;

import org.mo.com.logging.RLogger;
import org.mo.core.aop.RAop;
import org.mo.web.core.webform.IWebFormConsole;

public class FWebFormTest
{

   public static void main(String[] args){
      try{
         String config = "D:/Workspace/mylife/src/home/application.xml";
         RAop.initialize(config);

         IWebFormConsole console = RAop.find(IWebFormConsole.class);
         System.out.println(console);

         RAop.release();
      }catch(Exception e){
         RLogger.find(FWebFormTest.class).error(null, "main", e);
      }
   }
}
