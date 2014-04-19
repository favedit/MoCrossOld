package org.mo.logic.subscribe;

import org.mo.com.lang.FAttributes;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

public class FSubscribeMain
{
   @SuppressWarnings("unused")
   private static ILogger _logger = RLogger.find(FSubscribeMain.class);

   public static void main(String[] arg){
      //String config = "D:/Workspace/eUIS/src/eUIS-config/application-logic.xml";
      // try{
      // RAop.initialize(config);
      // ILogicSubscribeConsole subscribe = RAop.find(ILogicSubscribeConsole.class);
      // }catch(Exception e){
      //  _logger.error(null, "main", e);
      // }
      FAttributes a = new FAttributes();
      for(int i = 0; i < 5; i++){
         FAttributes b = new FAttributes();
         for(int j = 0; j < 5; j++){
            b.set(String.valueOf(j), String.valueOf(j));
         }
         a.set(String.valueOf(i), b.pack().toString());
         System.out.println(a.toString());
      }
      //      for(int i = 0; i < 5; i++){
      System.out.println(a.pack().toString());
      //      }
   }
}
