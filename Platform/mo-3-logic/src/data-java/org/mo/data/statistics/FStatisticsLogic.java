package org.mo.data.statistics;

import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FObject;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.data.face.FDomainStatisticsLogicUnit;

//============================================================
// <T>逻辑统计基类。</T>
//============================================================
public abstract class FStatisticsLogic
      extends FObject
      implements
         IStatisticsLogic
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FStatisticsLogic.class);

   // 名称
   protected String _name;

   // 回调名称
   protected String _invokeName;

   // 统计分组
   protected FStatistics _statistics;

   // 单元单元
   protected FDomainStatisticsLogicUnit _unit;

   // 数据链接
   protected ISqlConnection _dataConnection;

   // 处理条数
   protected int _processCount;

   // 处理线程
   protected FStatisticsLogicThread _thread = new FStatisticsLogicThread();

   //============================================================
   // <T>构造逻辑统计基类。</T>
   //============================================================
   public FStatisticsLogic(){
      _thread.setStatisticsLogic(this);
   }

   //============================================================
   // <T>获得名称。</T>
   //
   // @return 名称
   //============================================================
   @Override
   public String name(){
      return _name;
   }

   //============================================================
   // <T>设置名称。</T>
   //
   // @param name 名称
   //============================================================
   public void setName(String name){
      _name = name;
   }

   //============================================================
   // <T>获得回调名称。</T>
   //
   // @return 回调名称
   //============================================================
   public String invokeName(){
      return _invokeName;
   }

   //============================================================
   // <T>设置回调名称。</T>
   //
   // @param invokeName 回调名称
   //============================================================
   public void setInvokeName(String invokeName){
      _invokeName = invokeName;
   }

   //============================================================
   // <T>获得数据统计组。</T>
   //
   // @return 数据统计组
   //============================================================
   @Override
   public FStatistics statistics(){
      return _statistics;
   }

   //============================================================
   // <T>设置数据统计组。</T>
   //
   // @param statistics 数据统计组
   //============================================================
   public void setStatistics(FStatistics statistics){
      _statistics = statistics;
   }

   //============================================================
   // <T>获得数据单元。</T>
   //
   // @return 数据单元
   //============================================================
   public FDomainStatisticsLogicUnit unit(){
      return _unit;
   }

   //============================================================
   // <T>设置数据单元。</T>
   //
   // @param unit 数据单元
   //============================================================
   public void setUnit(FDomainStatisticsLogicUnit unit){
      _unit = unit;
   }

   //============================================================
   // <T>获得处理条数。</T>
   //
   // @return 处理条数
   //============================================================
   @Override
   public int processCount(){
      return _processCount;
   }

   //============================================================
   // <T>设置处理条数。</T>
   //
   // @param processCount 处理条数
   //============================================================
   public void setProcessCount(int processCount){
      _processCount = processCount;
   }

   //============================================================
   // <T>获得线程。</T>
   //
   // @return 线程
   //============================================================
   public FStatisticsLogicThread thread(){
      return _thread;
   }

   //============================================================
   // <T>启动处理。</T>
   //============================================================
   @Override
   public void startup(){
      _logger.info(this, "startup", "Process logic startup. (name={1}, invoke_name={2})", _name, _invokeName);
   }

   //============================================================
   // <T>逻辑处理。</T>
   //
   // @return 统计结果
   //============================================================
   @Override
   public EStatisticsResult process(){
      EStatisticsResult result = EStatisticsResult.Unknown;
      // 计算处理
      _dataConnection = _statistics.allocDataConnection();
      try{
         result = calculate();
      }finally{
         if(_dataConnection != null){
            _statistics.free(_dataConnection);
            _dataConnection = null;
         }
      }
      return result;
   }

   //============================================================
   // <T>关闭处理。</T>
   //============================================================
   @Override
   public void shutdown(){
      _logger.info(this, "shutdown", "Process logic shutdown. (name={1}, invoke_name={2})", _name, _invokeName);
   }
}
