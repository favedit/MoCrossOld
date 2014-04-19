package org.mo.platform.server.monitor;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.AProperty;


//============================================================
// <T>服务监视。</T>
//============================================================
public class FServerMonitor
      extends FObject
      implements
         IServerMonitor{

   // 日志输出接口
   private static ILogger _logger = RLogger.find(FServerMonitor.class);

   // 有效性
   @AProperty
   protected boolean _valid = true;

   // 处理间隔1000*60*15
   @AProperty
   protected int _interval = 5000;

   // 处理线程
   protected FServerMonitorThread _thread = new FServerMonitorThread();

   //============================================================
   // <T>构造服务监视。</T>
   //============================================================
   public FServerMonitor(){
      _thread.setMonitor(this);
   }

   //============================================================
   // <T>设置处理。</T>
   //============================================================
   public void setup(){
   }

   //============================================================
   // <T>获得名称。</T>
   //
   // @return 名称
   //============================================================
   public String name(){
      return RClass.shortName(this);
   }

   //============================================================
   // <T>获得有效性。</T>
   //
   // @return 有效性
   //============================================================
   public boolean valid(){
      return _valid;
   }

   //============================================================
   // <T>获得间隔。</T>
   //
   // @return 间隔
   //============================================================
   public int interval(){
      return _interval;
   }

   //============================================================
   // <T>同步处理。</T>
   //
   // @return 处理结果
   //============================================================
   public boolean process(){
      return true;
   }

   //============================================================
   // <T>启动处理。</T>
   //============================================================
   public void startup(){
      _logger.info(this, "startup", "Startup monitor. (name={1})", this);
      _thread.start();
   }

   //============================================================
   // <T>等待处理。</T>
   //============================================================
   public void join(){
      try{
         _thread.join();
      }catch(InterruptedException e){
         throw new FFatalError(e);
      }
   }
}
