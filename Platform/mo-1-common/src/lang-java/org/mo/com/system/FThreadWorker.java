package org.mo.com.system;

public class FThreadWorker<T extends FRunnable>
      extends Thread
{
   // 等待任务超时
   protected long _idelTimeout;

   // 可运行对象
   T _runner;

   /**
    * <p>创建线程工作对象</p>
    *
    * @param name 线程名称
    */
   public FThreadWorker(String name){
      super(name);
   }

   /**
    * <p>开始执行任务线程</p>
    *
    */
   //      @Override
   //      public void run(){
   //         while(!_stopped && !RThread.checkStop()){
   //            // 等待执行任务
   //            synchronized(this){
   //               if(_runner == null){
   //                  try{
   //                     wait(_idelTimeout);
   //                  }catch(Exception e){
   //                     throw new FFatalError(e);
   //                  }
   //               }
   //            }
   //            // 检查任务和超时设置
   //            if(_runner == null){
   //               if(notifyTimeout(this)){
   //                  return;
   //               }else{
   //                  continue;
   //               }
   //            }
   //            // 运行任务
   //            try{
   //               _runner.run();
   //            }finally{
   //               _runner = null;
   //               if(notifyFree(this)){
   //                  return;
   //               }
   //            }
   //         }
   //      }
   /**
    * <p>唤醒任务线程</p>
    *
    * @param runner 线程名称
    */
   @SuppressWarnings("unused")
   private synchronized void wakeup(T runner,
                                    long idelTimeout){
      _runner = runner;
      _idelTimeout = idelTimeout;
      notify();
   }
}
