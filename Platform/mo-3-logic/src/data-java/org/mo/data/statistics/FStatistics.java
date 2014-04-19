package org.mo.data.statistics;

import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;
import org.mo.com.lang.INamePair;
import org.mo.com.lang.RDateTime;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.lang.type.TDateTime;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.data.connector.IConnectorConsole;

//============================================================
// <T>数据统计器来源。</T>
//============================================================
public class FStatistics
      extends FObject
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FStatistics.class);

   // 统计控制台
   protected IStatisticsConsole _console;

   // 名称
   protected String _name;

   // 处理间隔
   protected int _interval;

   // 主域链接名称
   protected String _domainConnectionName;

   // 分析链接名称
   protected String _analysisConnectionName;

   // 数据链接名称
   protected String _dataConnectionName;

   // 链接控制台
   protected IConnectorConsole _connectorConsole;

   // 参数集合
   FAttributes _parameters = new FAttributes();

   // 统计器字典
   protected FDictionary<IStatisticsLogic> _statisticss = new FDictionary<IStatisticsLogic>(IStatisticsLogic.class);

   // 处理线程
   protected FStatisticsThread _thread = new FStatisticsThread();

   //============================================================
   // <T>构造数据统计器来源。</T>
   //============================================================
   public FStatistics(){
      _thread.setStatistics(this);
   }

   //============================================================
   // <T>获得统计控制台。</T>
   //
   // @return 统计控制台
   //============================================================
   public IStatisticsConsole console(){
      return _console;
   }

   //============================================================
   // <T>设置统计控制台。</T>
   //
   // @param console 统计控制台
   //============================================================
   public void setConsole(IStatisticsConsole console){
      _console = console;
   }

   //============================================================
   // <T>获得名称。</T>
   //
   // @return 名称
   //============================================================
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
   // <T>获得间隔。</T>
   //
   // @return 间隔
   //============================================================
   public int interval(){
      return _interval;
   }

   //============================================================
   // <T>设置间隔。</T>
   //
   // @param interval 间隔
   //============================================================
   public void setInterval(int interval){
      _interval = interval;
   }

   //============================================================
   // <T>获得主域链接名称。</T>
   //
   // @return 主域链接名称
   //============================================================
   public String domainConnectionName(){
      return _domainConnectionName;
   }

   //============================================================
   // <T>设置主域链接名称。</T>
   //
   // @param value 主域链接名称
   //============================================================
   public void setDomainConnectionName(String value){
      _domainConnectionName = value;
   }

   //============================================================
   // <T>获得分析链接名称。</T>
   //
   // @return 分析链接名称
   //============================================================
   public String analysisConnectionName(){
      return _analysisConnectionName;
   }

   //============================================================
   // <T>设置分析链接名称。</T>
   //
   // @param value 分析链接名称
   //============================================================
   public void setAnalysisConnectionName(String value){
      _analysisConnectionName = value;
   }

   //============================================================
   // <T>获得数据链接名称。</T>
   //
   // @return 数据链接名称
   //============================================================
   public String dataConnectionName(){
      return _dataConnectionName;
   }

   //============================================================
   // <T>设置数据链接名称。</T>
   //
   // @param value 数据链接名称
   //============================================================
   public void setDataConnectionName(String value){
      _dataConnectionName = value;
   }

   //============================================================
   // <T>获得链接控制台。</T>
   //
   // @return 链接控制台
   //============================================================
   public IConnectorConsole connectorConsole(){
      return _connectorConsole;
   }

   //============================================================
   // <T>设置链接控制台。</T>
   //
   // @param connectorConsole 链接控制台
   //============================================================
   public void setConnectorConsole(IConnectorConsole connectorConsole){
      _connectorConsole = connectorConsole;
   }

   //============================================================
   // <T>获得参数集合。</T>
   //
   // @return 参数集合
   //============================================================
   public FAttributes parameters(){
      return _parameters;
   }

   //============================================================
   // <T>获得统计器集合。</T>
   //
   // @return 统计器集合
   //============================================================
   public FDictionary<IStatisticsLogic> statisticss(){
      return _statisticss;
   }

   //============================================================
   // <T>增加一个统计器。</T>
   //
   // @param statistics 统计器
   //============================================================
   public void push(IStatisticsLogic statistics){
      String name = statistics.name();
      if(_statisticss.contains(name)){
         throw new FFatalError("Statistics is already exists. (name={1})", name);
      }
      _statisticss.set(name, statistics);
   }

   //============================================================
   // <T>收集一个主域链接。</T>
   //
   // @return 链接
   //============================================================
   public ISqlConnection allocDomainConnection(){
      return _connectorConsole.alloc(_domainConnectionName);
   }

   //============================================================
   // <T>收集一个分析链接。</T>
   //
   // @return 链接
   //============================================================
   public ISqlConnection allocAnalysisConnection(){
      return _connectorConsole.alloc(_analysisConnectionName);
   }

   //============================================================
   // <T>收集一个数据链接。</T>
   //
   // @return 链接
   //============================================================
   public ISqlConnection allocDataConnection(){
      return _connectorConsole.alloc(_dataConnectionName);
   }

   //============================================================
   // <T>收集一个数据链接。</T>
   //
   // @param connection 链接
   //============================================================
   public void free(ISqlConnection connection){
      _connectorConsole.free(connection);
   }

   //============================================================
   // <T>启动线程处理。</T>
   //============================================================
   public void startupThreads(){
      String processMode = _console.processMode();
      if(FStatisticsConsole.MODE_THREAD.equals(processMode)){
         // 线程处理模式
         for(INamePair<IStatisticsLogic> pair : _statisticss){
            IStatisticsLogic logic = pair.value();
            FStatisticsLogicThread thread = logic.thread();
            thread.setStatistics(this);
            thread.start();
         }
      }else if(FStatisticsConsole.MODE_SINGLE.equals(processMode)){
         // 单步处理模式
         _thread.start();
      }else{
         throw new FFatalError("Unknown process mode.");
      }
   }

   //============================================================
   // <T>启动处理。</T>
   //============================================================
   public void startup(){
      String processMode = _console.processMode();
      if(FStatisticsConsole.MODE_SINGLE.equals(processMode)){
         // 单步处理模式
         for(INamePair<IStatisticsLogic> pair : _statisticss){
            IStatisticsLogic logic = pair.value();
            logic.startup();
         }
      }else{
         throw new FFatalError("Unknown process mode.");
      }
   }

   //============================================================
   // <T>逻辑处理。</T>
   //
   // @return 处理结果
   //============================================================
   public boolean process(){
      boolean result = true;
      String processMode = _console.processMode();
      if(FStatisticsConsole.MODE_SINGLE.equals(processMode)){
         // 单步处理模式
         for(INamePair<IStatisticsLogic> pair : _statisticss){
            IStatisticsLogic logic = pair.value();
            // 执行处理
            TDateTime beginDate = RDateTime.currentDateTime();
            EStatisticsResult processResultCd = logic.process();
            TDateTime endDate = RDateTime.currentDateTime();
            // 处理结果
            switch(processResultCd){
               case Success:
                  break;
               case Continue:
                  result = false;
                  long spend = endDate.get() - beginDate.get();
                  _logger.info(this, "execute", "Process logic. (class={1}, start={2}, end={3}, process={4}, spend={5}ms)", RClass.shortName(logic), beginDate.format(), endDate.format(), logic.processCount(), spend);
                  break;
               default:
                  throw new FFatalError("Unknown process result. (result={1})", processResultCd);
            }
         }
      }else{
         throw new FFatalError("Unknown process mode.");
      }
      return result;
   }

   //============================================================
   // <T>停止处理。</T>
   //============================================================
   public void shutdown(){
      String processMode = _console.processMode();
      if(FStatisticsConsole.MODE_SINGLE.equals(processMode)){
         // 单步处理模式
         for(INamePair<IStatisticsLogic> pair : _statisticss){
            IStatisticsLogic logic = pair.value();
            logic.shutdown();
         }
      }else{
         throw new FFatalError("Unknown process mode.");
      }
   }

   //============================================================
   // <T>等待处理。</T>
   //============================================================
   public void join(){
      String processMode = _console.processMode();
      if(FStatisticsConsole.MODE_THREAD.equals(processMode)){
         // 线程处理模式
         for(INamePair<IStatisticsLogic> pair : _statisticss){
            IStatisticsLogic logic = pair.value();
            FStatisticsLogicThread thread = logic.thread();
            thread.tryJoin();
         }
      }else if(FStatisticsConsole.MODE_SINGLE.equals(processMode)){
         // 单步处理模式
         _thread.tryJoin();
      }else{
         throw new FFatalError("Unknown process mode.");
      }
   }
}
