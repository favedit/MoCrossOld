package org.mo.com.system;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RDump;
import org.mo.com.lang.cultrue.FCulture;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

//============================================================
// <T>线程基类。</T>
//
// @history 051012 MAOCY 创建
//============================================================
public abstract class FThread
      extends Thread
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FThread.class);

   // 是否停止
   protected boolean _stopped;

   // 文化信息
   private FCulture _culture;

   // 运行结果
   protected boolean _result;

   // 结果对象
   protected Object _resultLink;

   //============================================================
   // <T>构造线程基类。</T>
   //============================================================
   public FThread(){
   }

   //============================================================
   // <T>检查是否停止。</T>
   //
   // @return 是否停止
   //============================================================
   public boolean checkStop(){
      return _stopped;
   }

   //============================================================
   // <T>获得文化信息。</T>
   //
   // @return 文化信息
   //============================================================
   public FCulture culture(){
      if(null == _culture){
         _culture = new FCulture();
      }
      return _culture;
   }

   //============================================================
   // <T>设置文化信息。</T>
   //
   // @param culture 文化信息
   //============================================================
   public void setCulture(FCulture culture){
      _culture = culture;
   }

   //============================================================
   // <T>初始化当前线程。</T>
   //
   // @return TRUE：成功<br>FALSE：失败
   //============================================================
   public boolean initialize(){
      return true;
   }

   //============================================================
   // <T>运行当前线程的事务。</T>
   //
   // @return TRUE：成功<br>FALSE：失败
   //============================================================
   public abstract boolean execute();

   //============================================================
   // <T>运行当前线程。</T>
   //
   // @return TRUE：成功<br>FALSE：失败
   //============================================================
   @Override
   public void run(){
      boolean result = false;
      try{
         result = initialize();
         if(result && !_stopped){
            synchronized(this){
               result = execute();
            }
         }
      }catch(Throwable t){
         _logger.error(this, "run", t);
      }finally{
         if(!_stopped){
            if(_logger.debugAble()){
               _logger.debug(this, "run", "Stop thread. (result={1}, info=[{2})", result, RDump.dump(this));
            }
         }
      }
   }

   //============================================================
   // <T>尝试休眠处理。</T>
   //
   // @param interval 休眠间隔（毫秒）
   //============================================================
   public void trySleep(int interval){
      try{
         sleep(interval);
      }catch(InterruptedException e){
         e.printStackTrace();
      }
   }

   //============================================================
   // <T>尝试加入处理。</T>
   //============================================================
   public void tryJoin(){
      try{
         join();
      }catch(InterruptedException e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>等待停止。</T>
   //============================================================
   public void waitStop(){
      _stopped = true;
      synchronized(this){
         if(_logger.debugAble()){
            _logger.debug(this, "run", "Wait stop thread. (info={1})", RDump.dump(this));
         }
      }
   }
}
