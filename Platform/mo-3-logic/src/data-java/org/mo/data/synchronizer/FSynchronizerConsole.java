package org.mo.data.synchronizer;

import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FObjects;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.data.connector.IConnectorConsole;
import org.mo.data.face.FDomainSynchronizerUnitLogic;
import org.mo.data.face.FDomainSynchronizerUnitUnit;
import org.mo.data.synchronizer.common.XSynchronizer;
import org.mo.data.synchronizer.common.XUnit;
import org.mo.eng.store.FXmlConfigConsole;

//============================================================
// <T>数据同步器控制台。</T>
//============================================================ 
public class FSynchronizerConsole
      extends FXmlConfigConsole<XSynchronizer>
      implements
         ISynchronizerConsole
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FSynchronizerConsole.class);

   // 处理间隔
   @AProperty
   protected int _processInterval;

   // 数据库链接控制台
   @ALink
   protected IConnectorConsole _connectorConsole;

   // 来源集合
   protected FObjects<FSynchronizer> _synchronizers = new FObjects<FSynchronizer>(FSynchronizer.class);

   //============================================================
   // <T>创建类型数组。</T>
   //
   // @return 类型数组
   //============================================================
   @Override
   protected FObjects<XSynchronizer> createCollection(){
      return new FObjects<XSynchronizer>(XSynchronizer.class);
   }

   //============================================================
   // <T>创建同步单元。</T>
   //
   // @param synchronizer 同步器
   // @param xunit 单元
   // @return 同步单元
   //============================================================
   protected FSynchronizerUnit createUnit(FSynchronizer synchronizer,
                                          XUnit xunit){
      FSynchronizerUnit unit = null;
      ISqlConnection connection = synchronizer.allocDomainConnection();
      try{
         // 获得信息
         String synchronizerName = synchronizer.name();
         String unitName = xunit.getName();
         String unitLabel = xunit.getLabel();
         String sourceDataName = xunit.getSourceDataName();
         String targetDataName = xunit.getTargetDataName();
         String code = synchronizerName + "." + unitName;
         // 创建数据库记录
         FDomainSynchronizerUnitLogic unitLogic = new FDomainSynchronizerUnitLogic(connection);
         FDomainSynchronizerUnitUnit unitUnit = unitLogic.serach("CODE='" + code + "'");
         if(unitUnit == null){
            unitUnit = new FDomainSynchronizerUnitUnit();
            unitUnit.setCode(code);
            unitUnit.setSynchronizerName(synchronizerName);
            unitUnit.setName(unitName);
            unitUnit.setLabel(unitLabel);
            unitUnit.setSourceDataName(sourceDataName);
            unitUnit.setTargetDataName(targetDataName);
            unitLogic.doInsert(unitUnit);
         }
         // 创建同步单元
         unit = new FSynchronizerUnit();
         unit.setSynchronizer(synchronizer);
         unit.setName(unitName);
         unit.setSynchronizerUnit(unitUnit);
         // 加载数据
         unit.load();
      }finally{
         if(connection != null){
            synchronizer.free(connection);
            connection = null;
         }
      }
      return unit;
   }

   //============================================================
   // <T>加载处理。</T>
   //============================================================
   @Override
   public void load(){
      XSynchronizer[] xsynchronizers = list();
      for(XSynchronizer xsynchronizer : xsynchronizers){
         // 检查有效性
         if(!xsynchronizer.getIsValid()){
            continue;
         }
         // 获得信息
         String synchronizerName = xsynchronizer.getName();
         String domainConnectionName = xsynchronizer.getDomainConnectionName();
         String sourceConnectionName = xsynchronizer.getSourceConnectionName();
         String targetConnectionName = xsynchronizer.getTargetConnectionName();
         // 创建同步器
         FSynchronizer synchronizer = new FSynchronizer();
         synchronizer.setInterval(_processInterval);
         synchronizer.setName(synchronizerName);
         synchronizer.setDomainConnectionName(domainConnectionName);
         synchronizer.setSourceConnectionName(sourceConnectionName);
         synchronizer.setTargetConnectionName(targetConnectionName);
         synchronizer.setConnectorConsole(_connectorConsole);
         for(IXmlObject xobject : xsynchronizer.children()){
            if(XUnit.isInstance(xobject)){
               XUnit xunit = (XUnit)xobject;
               // 检查有效性
               if(!xunit.getIsValid()){
                  continue;
               }
               // 创建同步单元
               FSynchronizerUnit unit = createUnit(synchronizer, xunit);
               synchronizer.push(unit);
               _logger.info(this, "load", "Add synchronizer success. (source_data_name={1}, target_data_name={2})", unit.sourceDataName(), unit.targetDataName());
            }
         }
         _synchronizers.push(synchronizer);
      }
   }

   //============================================================
   // <T>启动处理。</T>
   //============================================================
   @Override
   public void startup(){
      // 启动所有线程
      for(FSynchronizer synchronizer : _synchronizers){
         synchronizer.startup();
      }
      // 等待线程结束
      for(FSynchronizer synchronizer : _synchronizers){
         try{
            synchronizer.thread().join();
         }catch(InterruptedException e){
            e.printStackTrace();
         }
      }
   }
}
