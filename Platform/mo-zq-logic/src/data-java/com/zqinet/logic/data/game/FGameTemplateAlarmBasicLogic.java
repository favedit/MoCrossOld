package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>金钱预警逻辑。</T>
//============================================================
public class FGameTemplateAlarmBasicLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("GM_TPL_ALARM_BASIC");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段模版tid的定义。
   public final static FLogicField FieldTid = new FLogicField("TID");

   // 字段名称的定义。
   public final static FLogicField FieldLabel = new FLogicField("LABEL");

   // 字段单人存量的定义。
   public final static FLogicField FieldSingleBalance = new FLogicField("SINGLE_BALANCE");

   // 字段每10分钟增加次数的定义。
   public final static FLogicField FieldTenMinCount = new FLogicField("TEN_MIN_COUNT");

   // 字段每10分钟增加数量的定义。
   public final static FLogicField FieldTenMinSum = new FLogicField("TEN_MIN_SUM");

   // 字段一小时增加次数的定义。
   public final static FLogicField FieldHourCount = new FLogicField("HOUR_COUNT");

   // 字段一小时增加数量的定义。
   public final static FLogicField FieldHourSum = new FLogicField("HOUR_SUM");

   // 字段每天增加数量的定义。
   public final static FLogicField FieldDaySum = new FLogicField("DAY_SUM");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造金钱预警逻辑单元。</T>
   //============================================================
   public FGameTemplateAlarmBasicLogic(){
   }

   //============================================================
   // <T>构造金钱预警逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FGameTemplateAlarmBasicLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FGameTemplateAlarmBasicUnit find(long recordId){
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
      FGameTemplateAlarmBasicUnit unit = new FGameTemplateAlarmBasicUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FGameTemplateAlarmBasicUnit serach(String whereSql){
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
      FGameTemplateAlarmBasicUnit unit = new FGameTemplateAlarmBasicUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FGameTemplateAlarmBasicUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FGameTemplateAlarmBasicUnit[] fetch(String whereSql, String orderSql){
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
   public FGameTemplateAlarmBasicUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FGameTemplateAlarmBasicUnit[] units = new FGameTemplateAlarmBasicUnit[count];
      for(int n = 0; n < count; n++){
         FGameTemplateAlarmBasicUnit unit = new FGameTemplateAlarmBasicUnit();
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
   public FGameTemplateAlarmBasicUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FGameTemplateAlarmBasicUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`TID`");
      sql.append(",`LABEL`");
      sql.append(",`SINGLE_BALANCE`");
      sql.append(",`TEN_MIN_COUNT`");
      sql.append(",`TEN_MIN_SUM`");
      sql.append(",`HOUR_COUNT`");
      sql.append(",`HOUR_SUM`");
      sql.append(",`DAY_SUM`");
      sql.append(",`CREATE_USER_ID`");
      sql.append(",`CREATE_DATE`");
      sql.append(",`UPDATE_USER_ID`");
      sql.append(",`UPDATE_DATE`");
      sql.append(") VALUES(");
      sql.append(unit.ovld());
      sql.append(',');
      sql.append(unit.uniqueId());
      sql.append(',');
      sql.append(unit.tid());
      sql.append(',');
      if(RString.isEmpty(unit.label())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.label()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.singleBalance());
      sql.append(',');
      sql.append(unit.tenMinCount());
      sql.append(',');
      sql.append(unit.tenMinSum());
      sql.append(',');
      sql.append(unit.hourCount());
      sql.append(',');
      sql.append(unit.hourSum());
      sql.append(',');
      sql.append(unit.daySum());
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
   public boolean doUpdate(FGameTemplateAlarmBasicUnit unit, long recordId){
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
      if(unit.isTidChanged()){
         sql.append(",`TID`=");
         sql.append(unit.tid());
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
      if(unit.isSingleBalanceChanged()){
         sql.append(",`SINGLE_BALANCE`=");
         sql.append(unit.singleBalance());
      }
      if(unit.isTenMinCountChanged()){
         sql.append(",`TEN_MIN_COUNT`=");
         sql.append(unit.tenMinCount());
      }
      if(unit.isTenMinSumChanged()){
         sql.append(",`TEN_MIN_SUM`=");
         sql.append(unit.tenMinSum());
      }
      if(unit.isHourCountChanged()){
         sql.append(",`HOUR_COUNT`=");
         sql.append(unit.hourCount());
      }
      if(unit.isHourSumChanged()){
         sql.append(",`HOUR_SUM`=");
         sql.append(unit.hourSum());
      }
      if(unit.isDaySumChanged()){
         sql.append(",`DAY_SUM`=");
         sql.append(unit.daySum());
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
