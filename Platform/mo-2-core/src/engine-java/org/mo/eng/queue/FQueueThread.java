/*
 * @(#)FQueueThread.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.queue;

import org.mo.com.lang.FObjects;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.system.FThread;
import org.mo.com.system.RThread;

/**
 * <p>监视器线程</p>
 * <p>1. 初始化和启动监视器</p>
 *
 * @author FEDT
 * @version 1.00 - 2005/02/18
 */
public class FQueueThread
      extends FThread
{
   // 监视器时间间隔
   private long _interval = 0;

   private final ILogger _logger = RLogger.find(FQueueThread.class);

   // 队列事件控制台
   @SuppressWarnings("unused")
   private FQueueConsole _queueConsole;

   // 队列事件列表
   private final FObjects<FQueue> _queues = new FObjects<FQueue>(FQueue.class);

   /**
    * <p>创建监视线程的实例</p>
    *
    */
   public FQueueThread(){
   }

   /**
    * <p>创建监视线程的实例</p>
    *
    * @param queueConsole 队列事件控制台
    */
   public FQueueThread(FQueueConsole queueConsole){
      _queueConsole = queueConsole;
   }

   /**
    * <p>清除所有队列事件</p>
    *
    */
   public synchronized void clear(){
      _queues.clear();
   }

   /**
    * <p>运行当前线程的队列事件</p>
    *
    * @return TRUE：成功<br>FALSE：失败
    */
   public boolean execute(){
      int count = 0;
      FQueue queue = null;
      while(true){
         if(RThread.checkStop()){
            // 检查线程是否停止
            break;
         }
         // 线程触发的监视器
         synchronized(this){
            count = _queues.count();
            for(int n = 0; n < count; n++){
               queue = (FQueue)_queues.get(n);
               try{
                  queue.execute();
               }catch(Exception e){
                  _logger.error(this, "execute", e);
               }
            }
         }
         try{
            sleep(_interval);
         }catch(Exception exception){
            _logger.error(this, "execute", exception);
         }
      }
      return true;
   }

   /**
    * <p>将一个队列事件加入到监视线程中</p>
    *
    * @param queue 队列事件
    */
   public synchronized void pushQueue(FQueue queue){
      _queues.push(queue);
   }

   /**
    * <p>将一个队列事件从监视线程中移除</p>
    *
    * @param queue 队列事件
    */
   public synchronized void removeQueue(FQueue queue){
      _queues.remove(queue);
   }

   /**
    * <p>设置监视执行间隔</p>
    *
    * @param interval 执行间隔
    */
   public void setInterval(long interval){
      _interval = interval;
   }
}
