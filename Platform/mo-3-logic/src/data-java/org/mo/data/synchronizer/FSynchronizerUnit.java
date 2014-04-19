package org.mo.data.synchronizer;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSql;
import org.mo.com.data.FSqlDatasetMeta;
import org.mo.com.data.FSqlField;
import org.mo.com.data.FSqlFields;
import org.mo.com.data.ISqlConnection;
import org.mo.com.data.ISqlDatasetReader;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;
import org.mo.com.lang.RDateTime;
import org.mo.com.lang.RString;
import org.mo.com.lang.type.TDateTime;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.data.face.FDomainSynchronizerHistoryLogic;
import org.mo.data.face.FDomainSynchronizerHistoryUnit;
import org.mo.data.face.FDomainSynchronizerUnitLogic;
import org.mo.data.face.FDomainSynchronizerUnitUnit;

//============================================================
// <T>数据同步器。</T>
//============================================================
public class FSynchronizerUnit
      extends FObject
      implements
         ISynchronizerUnit
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FSynchronizerUnit.class);

   // 同步器
   protected FSynchronizer _synchronizer;

   // 名称
   protected String _name;

   // 来源数据名称
   protected String _sourceDataName;

   // 来源数据描述
   protected FSqlDatasetMeta _sourceMeta;

   // 目标数据名称
   protected String _targetDataName;

   // 目标数据描述
   protected FSqlDatasetMeta _targetMeta;

   // 同步数据集单元
   protected FDomainSynchronizerUnitUnit _synchronizerUnit;

   // 记录数据时间
   protected TDateTime _recordDateTime = new TDateTime();

   // 同步等待间隔 (1分钟)
   protected int _interval = 1000 * 60;

   // 同步间隔 (60分钟)
   protected int _syncInterval = 1000 * 60 * 60;

   // 上次执行时间
   protected TDateTime _lastDate = new TDateTime();

   //============================================================
   // <T>数据同步器。</T>
   //============================================================
   public FSynchronizerUnit(){
   }

   //============================================================
   // <T>获得同步器。</T>
   //
   // @return 同步器
   //============================================================
   public FSynchronizer synchronizer(){
      return _synchronizer;
   }

   //============================================================
   // <T>设置同步器。</T>
   //
   // @param synchronizer 同步器
   //============================================================
   public void setSynchronizer(FSynchronizer synchronizer){
      _synchronizer = synchronizer;
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
   // <T>获得来源数据名称。</T>
   //
   // @return 来源数据名称
   //============================================================
   public String sourceDataName(){
      return _sourceDataName;
   }

   //============================================================
   // <T>设置来源数据名称。</T>
   //
   // @param value 来源数据名称
   //============================================================
   public void setSourceDataName(String value){
      _sourceDataName = value;
   }

   //============================================================
   // <T>获得目标数据名称。</T>
   //
   // @return 目标数据名称
   //============================================================
   public String targetDataName(){
      return _targetDataName;
   }

   //============================================================
   // <T>设置目标数据名称。</T>
   //
   // @param value 目标数据名称
   //============================================================
   public void setTargetDataName(String value){
      _targetDataName = value;
   }

   //============================================================
   // <T>获得同步数据单元。</T>
   //
   // @return 同步数据单元
   //============================================================ 
   public FDomainSynchronizerUnitUnit synchronizerUnit(){
      return _synchronizerUnit;
   }

   //============================================================
   // <T>设置同步数据单元。</T>
   //
   // @param synchronizerDataUnit 同步数据单元
   //============================================================ 
   public void setSynchronizerUnit(FDomainSynchronizerUnitUnit synchronizerUnit){
      _synchronizerUnit = synchronizerUnit;
   }

   //============================================================
   // <T>转换数据。</T>
   //============================================================ 
   public String convertValue(ISqlConnection connection,
                              String value,
                              ESqlDataType typeCd){
      switch(typeCd){
         case Integer:
            return value;
         case Float:
            return value;
         case DateTime:
            return "STR_TO_DATE('" + value + "','%Y%c%d%H%i%s')";
         case String:
         case Memo:
            return "'" + connection.formatValue(value) + "'";
         default:
            throw new FFatalError("Unknown type.");
      }
   }

   //============================================================
   // <T>目标中新建记录。</T>
   //
   // @param row 行记录
   //============================================================
   public void datasetInsertRow(ISqlConnection connection,
                                FRow row){
      FSql nameSql = new FSql();
      FSql valueSql = new FSql();
      FSqlFields fields = _targetMeta.fields();
      int count = fields.count();
      // 增加名称
      for(int n = 0; n < count; n++){
         FSqlField field = fields.get(n);
         String fieldName = field.name().toLowerCase();
         ESqlDataType fieldTypeCd = field.typeCd();
         // 追加名称
         if(n > 0){
            nameSql.append(',');
         }
         nameSql.append('`');
         nameSql.append(fieldName);
         nameSql.append('`');
         // 追加内容
         if(n > 0){
            valueSql.append(',');
         }
         if(fieldName.equals("ovld")){
            // 新建的记录一定为无效
            valueSql.append("0");
         }else{
            String value = row.find(fieldName);
            if(RString.isEmpty(value)){
               valueSql.append("NULL");
            }else{
               valueSql.append(convertValue(connection, value, fieldTypeCd));
            }
         }
      }
      // 生成命令
      FSql sql = new FSql("INSERT INTO " + _targetDataName + "(");
      sql.append(nameSql.toString());
      sql.append(") VALUES(");
      sql.append(valueSql.toString());
      sql.append(")");
      // 执行命令
      connection.executeSql(sql.toString());
   }

   //============================================================
   // <T>目标中更新记录。</T>
   //
   // @param row 行记录
   //============================================================
   public void datasetUpdateRow(ISqlConnection connection,
                                FRow row,
                                String ouid){
      FSql sql = new FSql("UPDATE " + _targetDataName + " SET ");
      int count = row.count();
      // 增加名称
      int index = 0;
      for(int n = 0; n < count; n++){
         if(index > 0){
            sql.append(',');
         }
         // 设置名称
         String name = row.name(n);
         if(name.equals("ouid")){
            continue;
         }
         sql.append('`');
         sql.append(row.name(n));
         sql.append("`=");
         // 设置内容
         String value = row.value(n);
         if(RString.isEmpty(value)){
            sql.append("NULL");
         }else{
            FSqlField field = _targetMeta.fields().get(name);
            ESqlDataType typeCd = field.typeCd();
            sql.append(convertValue(connection, value, typeCd));
         }
         index++;
      }
      sql.append(" WHERE OUID=" + ouid);
      // 执行数据处理
      connection.executeSql(sql.toString());
   }

   //============================================================
   // <T>向目标中插入数据集合。</T>
   //============================================================
   public void datasetSyncDataset(ISqlConnection connection,
                                  FDataset dataset){
      for(FRow row : dataset){
         // 获取数据行
         String ouid = row.get("ouid");
         boolean exsits = connection.executeExist("SELECT 1 FROM " + _targetDataName + " WHERE OUID=" + ouid);
         if(exsits){
            datasetUpdateRow(connection, row, ouid);
         }else{
            datasetInsertRow(connection, row);
         }
      }
   }

   //============================================================
   // <T>同步处理。</T>
   //
   // @param synchronizerUnit 同步器
   // @param synchronizerDatasetUnit 同步数据集
   //============================================================
   public void load(){
      // 加载行对象
      _sourceDataName = _synchronizerUnit.sourceDataName();
      _targetDataName = _synchronizerUnit.targetDataName();
      _recordDateTime.assign(_synchronizerUnit.recordDate());
      // 获得来源描述
      ISqlConnection sourceConnection = _synchronizer.allocSourceConnection();
      try{
         _sourceMeta = sourceConnection.fetchTableMeta(_sourceDataName);
      }finally{
         _synchronizer.free(sourceConnection);
      }
      // 获得目标描述
      ISqlConnection targetConnection = _synchronizer.allocTargetConnection();
      try{
         _targetMeta = targetConnection.fetchTableMeta(_targetDataName);
      }finally{
         _synchronizer.free(targetConnection);
      }
      // 输出日志
      _logger.debug(this, "load", "Load synchrornizer. (source_data_name={1}, target_data_name={2}, record_date={3})", _sourceDataName, _targetDataName, _recordDateTime.format());
   }

   //============================================================
   // <T>更新主域同步信息。</T>
   //
   // @param recordDate 记录时间
   // @param recordCount 记录数量
   //============================================================
   public void updateDomain(TDateTime recordDate,
                            int recordCount){
      // 更新开始时间
      ISqlConnection connection = _synchronizer.allocDomainConnection();
      try{
         // 更新同步记录
         FDomainSynchronizerUnitLogic synchronizerDatasetLogic = new FDomainSynchronizerUnitLogic(connection);
         _synchronizerUnit.recordDate().assign(recordDate);
         _synchronizerUnit.setRecordTotal(_synchronizerUnit.recordTotal() + recordCount);
         synchronizerDatasetLogic.doUpdate(_synchronizerUnit, _synchronizerUnit.ouid());
         // 插入历史记录
         FDomainSynchronizerHistoryLogic synchronizerHistoryLogic = new FDomainSynchronizerHistoryLogic(connection);
         FDomainSynchronizerHistoryUnit historyUnit = new FDomainSynchronizerHistoryUnit();
         historyUnit.setUnitId(_synchronizerUnit.ouid());
         historyUnit.recordDate().assign(recordDate);
         historyUnit.setRecordCount(recordCount);
         synchronizerHistoryLogic.doInsert(historyUnit);
      }finally{
         _synchronizer.free(connection);
      }
   }

   //============================================================
   // <T>同步处理。</T>
   //
   // @return 处理结果
   //============================================================
   @Override
   public boolean process(){
      boolean result = false;
      // 检查间隔时间
      long currentDate = RDateTime.currentDateTime().get();
      long interval = currentDate - _lastDate.get();
      if(interval < _interval){
         return false;
      }
      // 获得来源数据库信息
      ISqlConnection sourceConnection = _synchronizer.allocSourceConnection();
      try{
         // 获得开始时间
         TDateTime beginDateTime = _recordDateTime.clone();
         if(beginDateTime.isEmpty()){
            // 获得数据库最小更新时间
            FSql minSql = new FSql("SELECT MIN(UPDATE_DATE) UPDATE_MIN_DATE FROM " + _sourceDataName);
            FRow minRow = sourceConnection.find(minSql.toString());
            // 计算时间段
            TDateTime recordMinDate = new TDateTime(minRow.get("update_min_date"));
            beginDateTime.assign(recordMinDate);
         }
         if(beginDateTime.isEmpty()){
            // 没有数据需要同步
            _lastDate.set(currentDate);
            return false;
         }
         //............................................................
         // 获得数据库最大更新时间
         FSql maxSql = new FSql("SELECT MAX(UPDATE_DATE) UPDATE_MAX_DATE FROM " + _sourceDataName + " WHERE UPDATE_DATE >= '" + beginDateTime.format("YYYY-MM-DD HH24:MI:SS") + "'");
         FRow maxRow = sourceConnection.find(maxSql.toString());
         // 计算时间段
         TDateTime recordMaxDate = new TDateTime(maxRow.get("update_max_date"));
         //............................................................
         TDateTime endDateTime = beginDateTime.clone();
         endDateTime.add(_syncInterval);
         if(endDateTime.isAfter(recordMaxDate)){
            _lastDate.set(currentDate);
            endDateTime.assign(recordMaxDate);
         }
         if(!beginDateTime.isBefore(endDateTime)){
            // 没有数据需要同步
            _lastDate.set(currentDate);
            return false;
         }
         // 检查是否时间是满载
         long dataInterval = endDateTime.get() - beginDateTime.get();
         if(dataInterval != _syncInterval){
            _lastDate.set(currentDate);
         }
         //............................................................
         // 生成查询命令
         FSql fetchSql = new FSql("SELECT * FROM " + _sourceDataName + " WHERE");
         fetchSql.append(" UPDATE_DATE >= '" + beginDateTime.format("YYYY-MM-DD HH24:MI:SS") + "'");
         fetchSql.append(" AND UPDATE_DATE < '" + endDateTime.format("YYYY-MM-DD HH24:MI:SS") + "'");
         // 获取数据集合
         try(ISqlDatasetReader reader = sourceConnection.fetchReader(fetchSql)){
            if(reader.hasNext()){
               // 获得目标数据库信息
               ISqlConnection targetConnection = _synchronizer.allocTargetConnection();
               try{
                  for(FRow row : reader){
                     // 获取数据行
                     String ouid = row.get("ouid");
                     boolean exsits = targetConnection.executeExist("SELECT 1 FROM " + _targetDataName + " WHERE OUID=" + ouid);
                     if(exsits){
                        // 更新处理
                        datasetUpdateRow(targetConnection, row, ouid);
                     }else{
                        // 新建处理
                        datasetInsertRow(targetConnection, row);
                     }
                  }
               }finally{
                  _synchronizer.free(targetConnection);
               }
               // 输出信息
               int rowCount = reader.currentIndex();
               _logger.info(this, "process", "Process synchrornizer. (source_data_name={1}, target_data_name={2}, start_date={3}, end_date={4}, count={5})", _sourceDataName, _targetDataName, beginDateTime.format(), endDateTime.format(), rowCount);
               //............................................................
               // 更新主域信息
               updateDomain(endDateTime, rowCount);
            }
         }
         //............................................................
         // 调整时间
         _recordDateTime.assign(endDateTime);
         result = true;
      }finally{
         // 释放数据库链接
         _synchronizer.free(sourceConnection);
      }
      return result;
   }
}
