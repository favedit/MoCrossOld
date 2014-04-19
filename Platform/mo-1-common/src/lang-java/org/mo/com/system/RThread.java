package org.mo.com.system;

import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

/**
 * <p>线程工具类</p>
 * 
 * @author ALEX
 */
public class RThread
{
   private static ILogger _logger = RLogger.find(RThread.class);

   // 线程运行状态
   private static int _status = 0;

   /**
    * <p>默认线程事务间隔</p>
    */
   public static long DEFAULT_INTERVAL = 5000;

   /**
    * <p>默认线程优先级</p>
    */
   public static int DEFAULT_PRIORITY = 4;

   /**
    * <p>线程运行状态：运行中</p>
    */
   public static int STATUS_RUN = 1;

   /**
    * <p>线程运行状态：停止</p>
    */
   public static int STATUS_STOP = 2;

   /**
    * <p>检查当前线程是否已经要求结束</p>
    *
    * @return TRUE：是<br>FALSE：否
    */
   public static boolean checkStop(){
      return (_status == STATUS_STOP);
   }

   @SuppressWarnings("static-access")
   public static void sleep(int time){
      try{
         Thread.currentThread().sleep(time);
      }catch(Exception e){
         _logger.error(null, "sleep", e);
      }
   }

   @SuppressWarnings("static-access")
   public static void sleep(long time){
      try{
         Thread.currentThread().sleep(time);
      }catch(Exception e){
         _logger.error(null, "sleep", e);
      }
   }

   /**
    * <p>获得线程运行状态</p>
    *
    * @return 线程运行状态
    */
   public static int status(){
      return _status;
   }

   /**
    * <p>通知所有线程结束</p>
    *
    */
   public static void stopAll(){
      _status = STATUS_STOP;
      if(_logger.debugAble()){
         _logger.debug(null, "stopAll", "Stop all thread");
      }
   }
}
