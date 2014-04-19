package org.mo.data.face;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>主域统计逻辑逻辑。</T>
//============================================================
public class FDomainStatisticsLogicLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("DM_STATISTICS_LOGIC");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段统计编号的定义。
   public final static FLogicField FieldStatisticsId = new FLogicField("STATISTICS_ID");

   // 字段代码的定义。
   public final static FLogicField FieldCode = new FLogicField("CODE");

   // 字段组名称的定义。
   public final static FLogicField FieldStatisticsName = new FLogicField("STATISTICS_NAME");

   // 字段名称的定义。
   public final static FLogicField FieldName = new FLogicField("NAME");

   // 字段标签的定义。
   public final static FLogicField FieldLabel = new FLogicField("LABEL");

   // 字段调用名称的定义。
   public final static FLogicField FieldInvokeName = new FLogicField("INVOKE_NAME");

   // 字段处理时间的定义。
   public final static FLogicField FieldProcessDate = new FLogicField("PROCESS_DATE");

   // 字段处理条数的定义。
   public final static FLogicField FieldProcessCount = new FLogicField("PROCESS_COUNT");

   // 字段处理总数的定义。
   public final static FLogicField FieldProcessTotal = new FLogicField("PROCESS_TOTAL");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造主域统计逻辑逻辑单元。</T>
   //============================================================
   public FDomainStatisticsLogicLogic(){
   }

   //============================================================
   // <T>构造主域统计逻辑逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FDomainStatisticsLogicLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FDomainStatisticsLogicUnit find(long recordId){
      // 检查记录编号
      if(0 == recordId){
         return null;
      }
      // 生成命令
      FSql sql = new FSql("SELECT * FROM ");
      sql.append(Table.name());
      sql.append(" WHERE OUID=");
      sql.append(recordId);
      // 执行命令
      FRow row = _connection.find(sql.toString());
      if(null == row){
         return null;
      }
      // 获得数据
      FDomainStatisticsLogicUnit unit = new FDomainStatisticsLogicUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FDomainStatisticsLogicUnit serach(String whereSql){
      // 生成命令
      FSql sql = new FSql("SELECT * FROM ");
      sql.append(Table.name());
      sql.append(" WHERE ");
      sql.append(whereSql);
      // 执行命令
      FRow row = _connection.find(sql.toString());
      // 获得数据
      if(null == row){
         return null;
      }
      FDomainStatisticsLogicUnit unit = new FDomainStatisticsLogicUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FDomainStatisticsLogicUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FDomainStatisticsLogicUnit[] fetch(String whereSql, String orderSql){
      return fetch(whereSql, orderSql, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @param limitCount 限制条数
   // @return 数据单元集合
   //============================================================
   public FDomainStatisticsLogicUnit[] fetch(String whereSql, String orderSql, int limitCount){
      // 生成命令
      FSql sql = new FSql("SELECT * FROM ");
      sql.append(Table.name());
      if(!RString.isEmpty(whereSql)){
         sql.append(" WHERE ");
         sql.append(whereSql);
      }
      if(!RString.isEmpty(orderSql)){
         sql.append(" ORDER BY ");
         sql.append(orderSql);
      }
      if(limitCount > 0){
         sql.append(" LIMIT ");
         sql.append(limitCount);
      }
      // 执行命令
      FDataset dataset = _connection.fetchDataset(sql.toString());
      int count = dataset.count();
      // 获得数据
      FDomainStatisticsLogicUnit[] units = new FDomainStatisticsLogicUnit[count];
      for(int n = 0; n < count; n++){
         FDomainStatisticsLogicUnit unit = new FDomainStatisticsLogicUnit();
         unit.load(dataset.get(n));
         units[n] = unit;
      }
      return units;
   }

   //============================================================
   // <T>根据条件获得全部数据单元集合。</T>
   //
   // @return 数据单元集合
   //============================================================
   public FDomainStatisticsLogicUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FDomainStatisticsLogicUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`STATISTICS_ID`");
      sql.append(",`CODE`");
      sql.append(",`STATISTICS_NAME`");
      sql.append(",`NAME`");
      sql.append(",`LABEL`");
      sql.append(",`INVOKE_NAME`");
      sql.append(",`PROCESS_DATE`");
      sql.append(",`PROCESS_COUNT`");
      sql.append(",`PROCESS_TOTAL`");
      sql.append(",`CREATE_USER_ID`");
      sql.append(",`CREATE_DATE`");
      sql.append(",`UPDATE_USER_ID`");
      sql.append(",`UPDATE_DATE`");
      sql.append(") VALUES(");
      sql.append(unit.ovld());
      sql.append(',');
      sql.append(unit.uniqueId());
      sql.append(',');
      sql.append(unit.statisticsId());
      sql.append(',');
      if(RString.isEmpty(unit.code())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.code()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.statisticsName())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.statisticsName()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.name())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.name()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.label())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.label()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.invokeName())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.invokeName()));
         sql.append('\'');
      }
      sql.append(',');
      if(unit.processDate().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.processDate().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      sql.append(unit.processCount());
      sql.append(',');
      sql.append(unit.processTotal());
      sql.append(",0,NOW(),0,NOW())");
      // 执行命令
      long recordId = _connection.executeInsertSql(sql.toString());
      unit.setOuid(recordId);
      return true;
   }

   //============================================================
   // <T>更新一个数据单元。</T>
   //
   // @param unit 数据单元
   // @param recordId 记录编号
   // @return 处理结果
   //============================================================
   public boolean doUpdate(FDomainStatisticsLogicUnit unit, long recordId){
      // 检查记录编号
      if(0 == recordId){
         throw new FFatalError("Record id is empty. (record_id={1})", recordId);
      }
      // 生成命令
      FSql sql = new FSql("UPDATE ");
      sql.append(Table.name());
      sql.append(" SET OVLD=");
      sql.append(unit.ovld());
      if(unit.isUniqueIdChanged()){
         sql.append(",`UNIQUE_ID`=");
         sql.append(unit.uniqueId());
      }
      if(unit.isStatisticsIdChanged()){
         sql.append(",`STATISTICS_ID`=");
         sql.append(unit.statisticsId());
      }
      if(unit.isCodeChanged()){
         sql.append(",`CODE`=");
         if(RString.isEmpty(unit.code())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.code()));
            sql.append("'");
         }
      }
      if(unit.isStatisticsNameChanged()){
         sql.append(",`STATISTICS_NAME`=");
         if(RString.isEmpty(unit.statisticsName())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.statisticsName()));
            sql.append("'");
         }
      }
      if(unit.isNameChanged()){
         sql.append(",`NAME`=");
         if(RString.isEmpty(unit.name())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.name()));
            sql.append("'");
         }
      }
      if(unit.isLabelChanged()){
         sql.append(",`LABEL`=");
         if(RString.isEmpty(unit.label())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.label()));
            sql.append("'");
         }
      }
      if(unit.isInvokeNameChanged()){
         sql.append(",`INVOKE_NAME`=");
         if(RString.isEmpty(unit.invokeName())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.invokeName()));
            sql.append("'");
         }
      }
      if(unit.isProcessDateChanged()){
         sql.append(",`PROCESS_DATE`=");
         if(unit.processDate().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.processDate().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isProcessCountChanged()){
         sql.append(",`PROCESS_COUNT`=");
         sql.append(unit.processCount());
      }
      if(unit.isProcessTotalChanged()){
         sql.append(",`PROCESS_TOTAL`=");
         sql.append(unit.processTotal());
      }
      if(unit.isCreateUserIdChanged()){
         sql.append(",`CREATE_USER_ID`=");
         sql.append(unit.createUserId());
      }
      sql.append(",UPDATE_USER_ID=0,UPDATE_DATE=NOW()");
      sql.append(" WHERE OUID=");
      sql.append(recordId);
      // 执行命令
      return _connection.executeSql(sql.toString());
   }

   //============================================================
   // <T>删除一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 处理结果
   //============================================================
   public boolean doDelete(long recordId){
      // 生成命令
      FSql sql = new FSql("DELETE FROM ");
      sql.append(Table.name());
      sql.append("WHERE OUID=");
      sql.append(recordId);
      // 执行命令
      return _connection.executeSql(sql.toString());
   }
}