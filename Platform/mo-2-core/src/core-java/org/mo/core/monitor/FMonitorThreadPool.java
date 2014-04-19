package org.mo.core.monitor;

import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;
import org.mo.core.monitor.common.IMonitor;

//============================================================
// <T>监视器线程缓冲池。</T>
//============================================================
public class FMonitorThreadPool
      extends FObject
{
   // 监视器控制台
   protected IMonitorConsole _console;

   // 优先级
   protected int _priority;

   // 线程集合
   protected FDictionary<FMonitorThread> _threads = new FDictionary<FMonitorThread>(FMonitorThread.class);

   //============================================================
   // <T>构造监视器线程缓冲池。</T>
   //
   // @param console 监视器控制台接口
   //============================================================
   public FMonitorThreadPool(IMonitorConsole console){
      _console = console;
   }

   //============================================================
   // <T>获得优先级。</T>
   //
   // @return 优先级
   //============================================================
   public int priority(){
      return _priority;
   }

   //============================================================
   // <T>设置优先级。</T>
   //
   // @param priority 优先级
   //============================================================
   public void setPriority(int priority){
      _priority = priority;
   }

   //============================================================
   // <T>根据分组名称同步监视线程。</T>
   //
   // @param groupName 分组名称
   // @return 监视线程
   //============================================================
   public FMonitorThread syncThread(String groupName){
      FMonitorThread thread = _threads.find(groupName);
      if(null == thread){
         thread = new FMonitorThread(_console);
         thread.start();
         _threads.set(groupName, thread);
      }
      return thread;
   }

   //============================================================
   // <T>将一个监视器加入到监视器线程中。</T>
   //
   // @param monitor 监视器
   //============================================================
   public void push(IMonitor monitor){
      String groupName = monitor.groupName();
      FMonitorThread thread = syncThread(groupName);
      thread.push(monitor);
   }

   //============================================================
   // <T>将一个监视器从监视器线程中移除。</T>
   //
   // @param monitor 监视器
   //============================================================
   public void remove(IMonitor monitor){
      for(FMonitorThread runner : _threads.toObjects()){
         runner.remove(monitor);
      }
   }

   //============================================================
   // <T>等待结束。</T>
   //============================================================
   public void waitStop(){
      try{
         for(FMonitorThread thread : _threads.toObjects()){
            thread.join();
         }
      }catch(InterruptedException e){
         throw new FFatalError(e);
      }
   }
}
