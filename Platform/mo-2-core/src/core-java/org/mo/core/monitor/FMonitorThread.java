package org.mo.core.monitor;

import org.mo.com.lang.FFatalError;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.system.FThread;
import org.mo.com.system.RThread;
import org.mo.core.monitor.common.FMonitors;
import org.mo.core.monitor.common.IMonitor;

//============================================================
// <T>监视器线程。</T>
// <P>初始化和启动监视器。</P>
//============================================================
public class FMonitorThread
      extends FThread
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FMonitorThread.class);

   // 监视器控制台
   protected IMonitorConsole _console;

   // 监视器时间间隔
   private long _interval = 100;

   // 监视器集合
   protected FMonitors _monitors = new FMonitors();

   //============================================================
   // <T>构造监视器线程。</T>
   //
   // @param console 监视器控制台
   //============================================================
   public FMonitorThread(IMonitorConsole console){
      _console = console;
   }

   //============================================================
   // <T>获得监视器执行间隔。</T>
   //
   // @return 执行间隔
   //============================================================
   public long interval(){
      return _interval;
   }

   //============================================================
   // <T>设置监视器执行间隔。</T>
   //
   // @param interval 执行间隔
   //============================================================
   public void setInterval(long interval){
      _interval = interval;
   }

   //============================================================
   // <T>获得监视器集合。</T>
   //
   // @return 监视器集合
   //============================================================
   public FMonitors monitors(){
      return _monitors;
   }

   //============================================================
   // <T>放入一个监视器。</T>
   //
   // @param monitor 监视器
   //============================================================
   public void push(IMonitor monitor){
      synchronized(_monitors){
         _monitors.push(monitor);
      }
   }

   //============================================================
   // <T>移除一个监视器。</T>
   //
   // @param monitor 监视器
   //============================================================
   public void remove(IMonitor monitor){
      _monitors.remove(monitor);
   }

   //============================================================
   // <T>处理所有监视器。</T>
   //============================================================
   public void processMonitors(){
      IMonitor[] monitors = null;
      synchronized(_monitors){
         monitors = _monitors.toObjects();
      }
      for(IMonitor monitor : monitors){
         // 检查线程是否停止
         if(checkStop()){
            break;
         }
         // 执行逻辑
         try{
            boolean run = monitor.runable();
            if(run){
               monitor.execute();
               monitor.onSuspend();
            }
         }catch(Exception e){
            monitor.onError(new FFatalError(e));
            _logger.error(this, "processMonitors", e);
         }
      }
   }

   //============================================================
   // <T>运行当前线程的事务。</T>
   //
   // @return 处理结果
   //============================================================
   @Override
   public boolean execute(){
      while(!checkStop()){
         // 刷新监视器
         processMonitors();
         // 检查线程是否停止
         if(RThread.checkStop()){
            break;
         }
         // 线程挂起一段时间
         try{
            Thread.sleep(_interval);
         }catch(Exception e){
            _logger.error(this, "execute", e);
         }
      }
      return true;
   }
}
