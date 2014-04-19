package org.mo.logic.test;

import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;

public class FTest
      implements
         ITest
{
   private static ILogger _logger = RLogger.find(FTest.class);

   @AProperty
   private String _name;

   @ALink
   private ITest2 _asdfdsf;

   @Override
   public void connect(){
      _logger.debug(this, "connect", "Hello [{0}] {1} {0} {2}", _name, "B", "C");
      _asdfdsf.test();
   }

   @Override
   public void start(int num,
                     String name){
      _logger.debug(this, "start", "Hello [{0}] {1} {0} {2}", _name, num, name);
   }

   @Override
   public void stop(){
      _logger.debug(this, "stop", "Hello [{0}] {1} {0} {2}", _name, "B", "C");
   }
}
