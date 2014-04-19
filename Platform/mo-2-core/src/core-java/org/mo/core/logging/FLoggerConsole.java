package org.mo.core.logging;

import org.mo.com.logging.ILoggerListener;
import org.mo.com.logging.RLogger;
import org.mo.com.system.FListeners;
import org.mo.com.system.IListener;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.core.logging.adopter.FFileLogListener;
import org.mo.core.monitor.IMonitorConsole;

//============================================================
// <T>日志控制台。</T>
//============================================================
public class FLoggerConsole
      implements
         ILoggerConsole
{
   // 时间格式
   @AProperty
   protected String _dateFormat;

   // 执行间隔
   @AProperty
   protected long _interval;

   // 监听器列表
   protected FListeners _listeners;

   // 日志监视器
   protected FLoggerMonitor _monitor;

   // 监视器控制台
   @ALink
   protected IMonitorConsole _monitorConsole;

   // 存储路径
   @AProperty
   protected String _storePath;

   @Override
   public void addFilter(String className){
      //FLogger logger = (FLogger)RLogger.find(className);
      //logger.setAbleFlag(ELoggerLevel.NONE);
   }

   @Override
   public void addListener(ILoggerListener listener){
      if(listener instanceof FFileLogListener){
         ((FFileLogListener)listener).setConsole(this);
      }
      listener.initialize();
      _listeners.push(listener);
   }

   @Override
   public String dateFormat(){
      return _dateFormat;
   }

   /**
    * <T>初始化操作</T>
    */
   public void initialize(){
      // 设置监听器列表
      _listeners = RLogger.listeners();
      // 创建日志监视器
      _monitor = new FLoggerMonitor(this);
      _monitor.setInterval(_interval);
      _monitorConsole.register(_monitor);
   }

   /**
    * <p>刷新日志缓冲，将日志缓冲的内容输出到文件中。</p>
    *
    */
   public void refresh(){
      int count = _listeners.count();
      for(int n = 0; n < count; n++){
         ((ILoggerListener)_listeners.get(n)).refresh();
      }
   }

   /**
    * <T>释放操做</T>
    */
   public void release(){
      // 关闭所有监听器
      for(IListener listener : _listeners){
         ((ILoggerListener)listener).release();
      }
   }

   @Override
   public String storePath(){
      return _storePath;
   }
}
