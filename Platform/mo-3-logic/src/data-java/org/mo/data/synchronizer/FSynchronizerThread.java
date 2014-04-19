package org.mo.data.synchronizer;

import org.mo.com.system.FThread;

//============================================================
// <T>数据同步器线程。</T>
//============================================================
public class FSynchronizerThread
      extends FThread
{
   // 同步来源
   protected FSynchronizer _synchronizer;

   //============================================================
   // <T>构造数据同步器线程。</T>
   //============================================================
   public FSynchronizerThread(){
   }

   //============================================================
   // <T>获得同步来源。</T>
   //
   // @return 同步来源
   //============================================================ 
   public FSynchronizer source(){
      return _synchronizer;
   }

   //============================================================
   // <T>设置同步来源。</T>
   //
   // @param driverName 同步来源
   //============================================================ 
   public void setSource(FSynchronizer source){
      _synchronizer = source;
   }

   //============================================================
   // <T>执行处理。</T>
   //
   // @return 处理结果
   //============================================================
   @Override
   public boolean execute(){
      // 获得设置
      int interval = _synchronizer.interval();
      // 循环处理
      while(!_stopped){
         // 同步处理
         boolean result = _synchronizer.process();
         // 休眠处理
         if(!result){
            trySleep(interval);
         }
      }
      return true;
   }
}
