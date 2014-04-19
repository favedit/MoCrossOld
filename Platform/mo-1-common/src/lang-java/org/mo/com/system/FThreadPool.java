package org.mo.com.system;

import java.util.LinkedList;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

//============================================================
// <T>线程缓冲池。</T>
//============================================================
public class FThreadPool<T extends FRunnable>
      extends FObject
{
   public static long DEFAULT_IDLE_TIMEOUT = 1000;

   // 等待任务超时
   private long _idelTimeout = DEFAULT_IDLE_TIMEOUT;

   private final ILogger _logger = RLogger.find(FThreadPool.class);

   // 线程池最大尺寸
   private int _maxCount = 100;

   // 线程池最小尺寸
   private int _minCount = 0;

   // 下一个线程编号
   private int _nextWorkerId = 0;

   // 监视线程优先度
   private int _priority = Thread.NORM_PRIORITY;

   // 任务线程总数
   private int _runnerTotalCount = 0;

   @SuppressWarnings("unused")
   private final boolean _stopped = false;

   // 线程缓冲
   private final LinkedList<Object> _threadPool = new LinkedList<Object>();

   @SuppressWarnings("rawtypes")
   protected FObjects<FThreadWorker> _workers = new FObjects<FThreadWorker>(FThreadWorker.class);

   /**
    * <p>创建线程缓冲池</p>
    *
    */
   public FThreadPool(){
   }

   //   /**
   //    * <p>获得当前缓冲池中运行的内部信息</p>
   //    *
   //    * @return 运行时的内部信息
   //    */
   //   @Override
   //   public TDumpInfo dump(){
   //      return dump(new TDumpInfo(this));
   //   }
   /**
    * <p>获得当前缓冲池中运行的内部信息</p>
    *
    * @param info 内部信息对象
    * @return 运行时的内部信息
    */
   //   @Override
   //   public TDumpInfo dump(TDumpInfo info){
   //      info.append("Count: " + _threadPool.size() + "\n");
   //      Iterator<?> iterator = _threadPool.iterator();
   //      FThreadWorker worker = null;
   //      while(iterator.hasNext()){
   //         worker = (FThreadWorker)iterator.next();
   //         info.append("ThreadPool: " + worker + "\n");
   //      }
   //      return info;
   //   }
   /**
    * <p>获得等待任务超时</p>
    *
    * @return 等待任务超时
    */
   public long idelTimeout(){
      return _idelTimeout;
   }

   /**
    * <p>获得线程池最大尺寸</p>
    *
    * @return 线程池最大尺寸
    */
   public int maxCount(){
      return _maxCount;
   }

   /**
    * <p>获得线程池最小尺寸</p>
    *
    * @return 线程池最小尺寸
    */
   public int minCount(){
      return _minCount;
   }

   // 通知线程自由，返回线程池
   @SuppressWarnings({"unused", "rawtypes"})
   private synchronized boolean notifyFree(FThreadWorker worker){
      // 如果在最大范围内，将指定线程放回线程池
      if(_threadPool.size() < _maxCount){
         _threadPool.addLast(worker);
         return false;
      }
      // 如果超过在最大范围，放弃当前线程
      //_workers.remove(worker);
      _runnerTotalCount--;
      return true;
   }

   // 通知线程执行超时
   @SuppressWarnings({"rawtypes", "unused"})
   private synchronized boolean notifyTimeout(FThreadWorker worker){
      if(worker._runner != null){
         return false;
      }
      if(_threadPool.size() > _minCount){
         _threadPool.remove(worker);
         //_workers.remove(worker);
         _runnerTotalCount--;
         return true;
      }
      return false;
   }

   /**
    * <p>获得缓冲池中线程总数</p>
    *
    * @return 线程总数
    */
   public int poolCount(){
      return _threadPool.size();
   }

   public int priority(){
      return _priority;
   }

   /**
    * <p>开始处理可执行对象</p>
    *
    * @param runner 可执行对象
    */
   @SuppressWarnings({"rawtypes"})
   public synchronized void process(T runner){
      if(runner == null){
         throw new NullPointerException();
      }
      FThreadWorker worker = null;
      if(!_threadPool.isEmpty()){
         worker = (FThreadWorker)_threadPool.removeFirst();
      }else{
         _nextWorkerId++;
         if(_nextWorkerId > 999){
            _nextWorkerId = 1;
         }
         worker = new FThreadWorker("R" + RString.leftPad(Integer.toString(_nextWorkerId), 3, "0"));
         //_workers.push(worker);
         worker.setPriority(_priority);
         worker.start();
         _runnerTotalCount++;
      }
      //worker.wakeup(runner, _idelTimeout);
   }

   /**
    * <p>获得当前正在运行的线程总数</p>
    *
    * @return 线程总数
    */
   public int runingCount(){
      return _runnerTotalCount - _threadPool.size();
   }

   /**
    * <p>设置等待任务超时</p>
    *
    * @param idelTimeout 等待任务超时
    */
   public void setIdelTimeout(long idelTimeout){
      // 空闲时间不能小于10秒,大约一小时
      if(idelTimeout < 10000 || idelTimeout > 3600000){
         throw new FFatalError("Idel timeour is invalid.");
      }
      _idelTimeout = idelTimeout;
   }

   /**
    * <p>设置线程池最大尺寸</p>
    *
    * @param count 线程池最大尺寸
    */
   public void setMaxCount(int count){
      _maxCount = count;
   }

   /**
    * <p>设置线程池最小尺寸</p>
    *
    * @param count 线程池最小尺寸
    */
   public void setMinCount(int count){
      _minCount = count;
   }

   public void setPriority(int priority){
      _priority = priority;
   }

   public void waitStop(){
      //_stopped = true;
      for(FThreadWorker<?> worker : _workers){
         try{
            worker.join();
         }catch(Exception e){
            _logger.error(this, "waitStop", e);
         }
      }
   }
}
