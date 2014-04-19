package org.mo.data.statistics;

import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.INamePair;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.RAop;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.data.connector.IConnectorConsole;
import org.mo.data.face.FDomainStatisticsLogicLogic;
import org.mo.data.face.FDomainStatisticsLogicUnit;
import org.mo.data.statistics.common.XLogic;
import org.mo.data.statistics.common.XStatistics;
import org.mo.eng.store.FXmlConfigConsole;

//============================================================
// <T>数据统计控制台。</T>
//============================================================ 
public class FStatisticsConsole
      extends FXmlConfigConsole<XStatistics>
      implements
         IStatisticsConsole
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FStatisticsConsole.class);

   // 单处理模式
   public static final String MODE_SINGLE = "single";

   // 线程处理模式
   public static final String MODE_THREAD = "thread";

   // 处理模式
   @AProperty
   protected String _processMode;

   // 处理间隔
   @AProperty
   protected int _processInterval;

   // 数据库链接控制台
   @ALink
   protected IConnectorConsole _connectorConsole;

   // 统计集合
   protected FDictionary<FStatistics> _statisticses = new FDictionary<FStatistics>(FStatistics.class);

   //============================================================
   // <T>构造数据统计控制台。</T>
   //============================================================
   public FStatisticsConsole(){
   }

   //============================================================
   // <T>创建类型数组。</T>
   //
   // @return 类型数组
   //============================================================
   @Override
   protected FObjects<XStatistics> createCollection(){
      return new FObjects<XStatistics>(XStatistics.class);
   }

   //============================================================
   // <T>获得处理模式。</T>
   //
   // @return 处理模式
   //============================================================
   @Override
   public String processMode(){
      return _processMode;
   }

   //============================================================
   // <T>根据代码同步一个统计来源。</T>
   //
   // @param code 代码
   // @return 统计来源
   //============================================================
   public FStatistics syncStatistics(XStatistics xstatistics){
      String name = xstatistics.getName();
      FStatistics statistics = _statisticses.find(name);
      // 查找代码对象
      if(statistics == null){
         // 创建新对象
         statistics = new FStatistics();
         statistics.setConsole(this);
         statistics.setName(name);
         statistics.setInterval(_interval);
         // 获得内容
         String domainConnectionName = xstatistics.getDomainConnectionName();
         String analysisConnectionName = xstatistics.getAnalysisConnectionName();
         String dataConnectionName = xstatistics.getDataConnectionName();
         String parametersValue = xstatistics.getParameters();
         // 设置内容
         statistics.setDomainConnectionName(domainConnectionName);
         statistics.setAnalysisConnectionName(analysisConnectionName);
         statistics.setDataConnectionName(dataConnectionName);
         statistics.parameters().split(parametersValue, '=', '\n', false, true);
         statistics.setConnectorConsole(_connectorConsole);
         // 存储对象
         _statisticses.set(name, statistics);
         _logger.info(this, "syncSource", "Add statistics. (name={1}, connection={2}, parameters={3})", name, dataConnectionName, statistics.parameters().dump());
      }
      return statistics;
   }

   //============================================================
   // <T>根据代码同步一个统计单元。</T>
   //
   // @param code 代码
   // @return 统计来源
   //============================================================
   public IStatisticsLogic syncLogic(FStatistics statistics,
                                     XLogic xlogic){
      // 收集数据库链接
      IStatisticsLogic logic = null;
      String domainConnectionName = statistics.domainConnectionName();
      ISqlConnection connection = _connectorConsole.alloc(domainConnectionName);
      //............................................................
      // 获得属性
      String statisticsName = statistics.name();
      String logicName = xlogic.getName();
      String logicLabel = xlogic.getLabel();
      String invokeName = xlogic.getInvokeName();
      String code = statisticsName + "." + logicName;
      try{
         // 创建统计器
         logic = RAop.find(invokeName);
         logic.setName(logicName);
         logic.setInvokeName(invokeName);
         logic.setStatistics(statistics);
         // 创建数据库记录
         FDomainStatisticsLogicLogic statisticsLogic = new FDomainStatisticsLogicLogic(connection);
         FDomainStatisticsLogicUnit statisticsUnit = statisticsLogic.serach("CODE='" + code + "'");
         if(statisticsUnit == null){
            statisticsUnit = new FDomainStatisticsLogicUnit();
            statisticsUnit.setCode(code);
            statisticsUnit.setStatisticsName(statisticsName);
            statisticsUnit.setName(logicName);
            statisticsUnit.setLabel(logicLabel);
            statisticsUnit.setInvokeName(invokeName);
            statisticsLogic.doInsert(statisticsUnit);
         }
         logic.setUnit(statisticsUnit);
         // 存储内容
         statistics.push(logic);
         _logger.info(this, "load", "Add statistics logic. (name={1}, invoke_name={2})", logicName, invokeName);
      }finally{
         // 释放数据库链接
         _connectorConsole.free(connection);
      }
      return logic;
   }

   //============================================================
   // <T>加载处理。</T>
   //============================================================
   @Override
   public void load(){
      XStatistics xstatisticses[] = list();
      for(XStatistics xstatistics : xstatisticses){
         // 检查有效性
         if(!xstatistics.getIsValid()){
            continue;
         }
         // 同步统计
         FStatistics statistics = syncStatistics(xstatistics);
         for(IXmlObject xchildren : xstatistics.children()){
            if(XLogic.isInstance(xchildren)){
               XLogic xlogic = (XLogic)xchildren;
               // 检查有效性
               if(!xlogic.getIsValid()){
                  continue;
               }
               // 同步统计逻辑
               syncLogic(statistics, xlogic);
            }
         }
      }
   }

   //============================================================
   // <T>启动处理。</T>
   //============================================================
   @Override
   public void startup(){
      // 启动所有线程
      for(INamePair<FStatistics> pair : _statisticses){
         FStatistics statistics = pair.value();
         statistics.startupThreads();
      }
      // 等待所有线程结束
      for(INamePair<FStatistics> pair : _statisticses){
         FStatistics statistics = pair.value();
         statistics.join();
      }
   }
}
