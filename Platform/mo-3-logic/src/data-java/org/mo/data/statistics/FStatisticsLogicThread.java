package org.mo.data.statistics;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RDateTime;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.lang.type.TDateTime;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.system.FThread;

//============================================================
// <T>数据同步器线程。</T>
//============================================================
public class FStatisticsLogicThread
      extends FThread
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FStatisticsLogicThread.class);

   // 同步来源
   protected FStatistics _statistics;

   // 计算器
   protected IStatisticsLogic _statisticsLogic;

   //============================================================
   // <T>构造数据同步器线程。</T>
   //============================================================
   public FStatisticsLogicThread(){
   }

   //============================================================
   // <T>获得同步来源。</T>
   //
   // @return 同步来源
   //============================================================ 
   public FStatistics statistics(){
      return _statistics;
   }

   //============================================================
   // <T>设置同步来源。</T>
   //
   // @param source 同步来源
   //============================================================ 
   public void setStatistics(FStatistics statistics){
      _statistics = statistics;
   }

   //============================================================
   // <T>获得统计器逻辑。</T>
   //
   // @return 统计器逻辑
   //============================================================ 
   public IStatisticsLogic statisticsLogic(){
      return _statisticsLogic;
   }

   //============================================================
   // <T>设置统计器逻辑。</T>
   //
   // @param statisticsLogic 统计器逻辑
   //============================================================ 
   public void setStatisticsLogic(IStatisticsLogic statisticsLogic){
      _statisticsLogic = statisticsLogic;
   }

   //============================================================
   // <T>执行处理。</T>
   //
   // @return 处理结果
   //============================================================
   @Override
   public boolean execute(){
      // 启动处理
      _statisticsLogic.startup();
      // 逻辑处理
      while(!_stopped){
         // 计算处理
         TDateTime beginDate = RDateTime.currentDateTime();
         EStatisticsResult resultCd = _statisticsLogic.process();
         TDateTime endDate = RDateTime.currentDateTime();
         // 没有需要计算的时候，则休眠处理
         switch(resultCd){
            case Success:
               long spend = endDate.get() - beginDate.get();
               _logger.info(this, "execute", "Process logic. (class={1}, start={2}, end={3}, process={4}, spend={5}ms)", RClass.shortName(_statisticsLogic), beginDate.format(), endDate.format(), _statisticsLogic.processCount(), spend);
               // 休眠处理
               trySleep(_statistics.interval());
               break;
            case Continue:
               break;
            default:
               throw new FFatalError("Unknown process result. (result={1})", resultCd);
         }
      }
      // 结束处理
      _statisticsLogic.shutdown();
      return true;
   }
}
