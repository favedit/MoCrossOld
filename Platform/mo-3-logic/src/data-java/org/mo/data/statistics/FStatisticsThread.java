package org.mo.data.statistics;

import org.mo.com.lang.RDateTime;
import org.mo.com.lang.type.TDateTime;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.system.FThread;

//============================================================
// <T>数据同步器线程。</T>
//============================================================
public class FStatisticsThread
      extends FThread
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FStatisticsThread.class);

   // 同步来源
   protected FStatistics _statistics;

   //============================================================
   // <T>构造数据同步器线程。</T>
   //============================================================
   public FStatisticsThread(){
   }

   //============================================================
   // <T>获得同步器。</T>
   //
   // @return 同步器
   //============================================================ 
   public FStatistics statistics(){
      return _statistics;
   }

   //============================================================
   // <T>设置同步器。</T>
   //
   // @param statistics 同步器
   //============================================================ 
   public void setStatistics(FStatistics statistics){
      _statistics = statistics;
   }

   //============================================================
   // <T>执行处理。</T>
   //
   // @return 处理结果
   //============================================================
   @Override
   public boolean execute(){
      // 启动处理
      _statistics.startup();
      // 逻辑处理
      while(!_stopped){
         // 计算处理
         TDateTime beginDate = RDateTime.currentDateTime();
         boolean result = _statistics.process();
         TDateTime endDate = RDateTime.currentDateTime();
         // 没有需要计算的时候，则休眠处理
         if(!result){
            trySleep(_statistics.interval());
         }else{
            long spend = endDate.get() - beginDate.get();
            _logger.info(this, "execute", "Process statistics logic. (start={1}, end={2}, spend={3}ms, process={4})", beginDate.format(), endDate.format(), spend);
         }
      }
      // 结束处理
      _statistics.shutdown();
      return true;
   }
}
