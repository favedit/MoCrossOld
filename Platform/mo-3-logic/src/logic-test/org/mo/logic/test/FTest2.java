package org.mo.logic.test;

import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.AProperty;

public class FTest2
      implements
         ITest2
{
   private static ILogger _logger = RLogger.find(FTest2.class);

   @AProperty
   private String _password;

   @Override
   public void test(){
      _logger.debug(this, "test", "Hello [{0}] {1} {0} {2}", _password, "B", "C");
   }
}
