package org.mo.core.version;

import org.mo.com.system.RThread;
import org.mo.core.aop.face.AProperty;

public class FVersionConsole
      implements
         IVersionConsole
{
   @AProperty
   private long _interval;

   @AProperty
   private int _priority;

   @AProperty
   private String _workfile;

   private FVersionThread _thread = null;

   @Override
   public void allStop(){
      RThread.stopAll();
   }

   public void initialize(){
      _thread = new FVersionThread();
      _thread.setPriority(_priority);
      _thread.setInterval(_interval);
      _thread.setWorkfile(_workfile);
      _thread.start();
   }

   public void release(){
      if(_thread != null){
         _thread.setLoopFlag(false);
      }
   }
}
