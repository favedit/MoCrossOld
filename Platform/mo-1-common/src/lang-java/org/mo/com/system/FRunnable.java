package org.mo.com.system;

import org.mo.com.lang.FError;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

//============================================================
// <T>可运行对象。</T>
//
// @history 051012 MAOCY 创建
//============================================================
public abstract class FRunnable
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FRunnable.class);

   protected boolean _stopped = false;

   public boolean checkStop(){
      return _stopped || RThread.checkStop();
   }

   /**
    * <p>执行处理</p>
    *
    * @return TRUE：成功<br>FALSE：失败
    */
   public abstract boolean execute();

   /**
    * <p>初始化处理</p>
    *
    * @return TRUE：成功<br>FALSE：失败
    */
   public boolean initialize(){
      return true;
   }

   /**
    * <p>运行当前处理</p>
    *
    */
   public void run(){
      boolean result = false;
      try{
         result = initialize();
         if(result){
            result = execute();
         }
      }catch(FError e){
         _logger.error(this, "run", e);
      }finally{
         if(_logger.debugAble()){
            _logger.debug(this, "run", "Stop Runner: {0} = {1}", this, result);
         }
      }
   }

   public void stop(){
      _stopped = true;
   }
}
