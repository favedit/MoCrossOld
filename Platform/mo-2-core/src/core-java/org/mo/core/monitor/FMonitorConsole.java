package org.mo.core.monitor;

import org.mo.com.lang.reflect.RClass;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.face.AProperty;
import org.mo.core.monitor.common.IConfigMonitor;
import org.mo.core.monitor.common.IMonitor;

//============================================================
// <T>监视器控制台。</T>
// <P>1. 初始化和启动监视器。</P>
// <P>2. 初始化应用程序关闭时触发的线程。</P>
//============================================================
public class FMonitorConsole
      implements
         IMonitorConsole
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FMonitorConsole.class);

   // 监视线程优先度
   @AProperty
   protected int _priority = Thread.MIN_PRIORITY;

   // 设置监视器
   protected FConfigMonitor _configMonitor = new FConfigMonitor(this);

   // 监视器线程池
   protected FMonitorThreadPool _pool = new FMonitorThreadPool(this);

   //============================================================
   // <T>构造监视器控制台。</T>
   //============================================================
   public FMonitorConsole(){
   }

   //============================================================
   // <T>增加监视器的设置i目录。</T>
   //
   // @param directory 目录
   //============================================================
   public void addDirectory(String directory){
      _configMonitor.addDirectory(directory);
   }

   //============================================================
   // <T>注册一个监视器。</T>
   //
   // @param monitor 监视器
   //============================================================
   @Override
   public void register(IMonitor monitor){
      if(monitor.valid()){
         monitor.initialize();
         _pool.push(monitor);
         _logger.info(this, "register", "Register monitor success. (monitor={1})", monitor);
      }else{
         _logger.info(this, "register", "Register monitor failure. (monitor={1}, valid={2})", monitor, monitor.valid());
      }
   }

   //============================================================
   // <T>注销一个监视器。</T>
   //
   // @param monitor 监视器
   //============================================================
   @Override
   public synchronized void unregister(IMonitor monitor){
      _pool.remove(monitor);
      _logger.info(this, "unregister", "Unregister monitor. (monitor={1})", monitor);
   }

   //============================================================
   // <T>等待结束。</T>
   //============================================================
   @Override
   public void waitStop(){
      _pool.waitStop();
   }

   //============================================================
   // <T>加载设置信息。</T>
   //============================================================
   public void loadConfig(String filename){
      FXmlDocument cfgDoc = new FXmlDocument(filename);
      for(FXmlNode node : cfgDoc.root().nodes()){
         if(node.isName(IMonitor.NAME)){
            IConfigMonitor monitor = RClass.newInstance(node.get("instance"));
            monitor.construct(node);
            register(monitor);
         }
      }
      if(_logger.debugAble()){
         _logger.debug(this, "loadConfig", "Load monitor config (filename={0})", filename);
      }
   }

   //============================================================
   // <T>初始化操作。</T>
   //============================================================
   public void initializeThread(){
      _pool.setPriority(_priority);
      _pool.push(_configMonitor);
   }

   //   @Override
   //   public void processConfigs(){
   //      FFileInfos infos = _configMonitor.files();
   //      int count = infos.count();
   //      for(int n = 0; n < count; n++){
   //         FFileInfo info = infos.value(n);
   //         loadConfig(info.fileName());
   //      }
   //      if(_logger.debugAble()){
   //         _logger.debug(this, "processConfigs", "Load all config");
   //      }
   //   }
   //   /**
   //    * <p>运行当前线程的事务</p>
   //    *
   //    */
   //   public void processMonitors(FMonitors monitors){
   //      synchronized(monitors){
   //         int count = monitors.count();
   //         for(int n = 0; n < count; n++){
   //            IMonitor monitor = monitors.get(n);
   //            // 检查线程是否停止
   //            if(RThread.checkStop()){
   //               break;
   //            }
   //            // 执行逻辑
   //            try{
   //               boolean run = monitor.runable();
   //               if(run){
   //                  monitor.execute();
   //                  monitor.onSuspend();
   //               }
   //            }catch(Exception e){
   //               monitor.onError(new FFatalError(e));
   //               _logger.error(this, "processMonitors", e);
   //            }
   //         }
   //      }
   //   }
   //   public void releaseThread(){
   //      _pool.waitStop();
   //   }
}
