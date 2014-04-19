package com.zqinet.logic.data.analysis;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>日志分析消费逻辑。</T>
//============================================================
public class FAnalysisServiceConsumeLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("AS_SVC_CONSUME");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段游戏ID的定义。
   public final static FLogicField FieldGameId = new FLogicField("GAME_ID");

   // 字段记录时间的定义。
   public final static FLogicField FieldRecordDate = new FLogicField("RECORD_DATE");

   // 字段记录时的定义。
   public final static FLogicField FieldRecordHour = new FLogicField("RECORD_HOUR");

   // 字段记录天的定义。
   public final static FLogicField FieldRecordDay = new FLogicField("RECORD_DAY");

   // 字段记录周的定义。
   public final static FLogicField FieldRecordWeek = new FLogicField("RECORD_WEEK");

   // 字段记录月的定义。
   public final static FLogicField FieldRecordMonth = new FLogicField("RECORD_MONTH");

   // 字段记录时长的定义。
   public final static FLogicField FieldRecordInterval = new FLogicField("RECORD_INTERVAL");

   // 字段角色ID的定义。
   public final static FLogicField FieldRoleId = new FLogicField("ROLE_ID");

   // 字段消费类型的定义。
   public final static FLogicField FieldConsumeCd = new FLogicField("CONSUME_CD");

   // 字段消费金额的定义。
   public final static FLogicField FieldConsumeValue = new FLogicField("CONSUME_VALUE");

   // 字段剩余货币的定义。
   public final static FLogicField FieldBalanceValue = new FLogicField("BALANCE_VALUE");

   // 字段物品ID的定义。
   public final static FLogicField FieldItemTid = new FLogicField("ITEM_TID");

   // 字段物品数量的定义。
   public final static FLogicField FieldItemNum = new FLogicField("ITEM_NUM");

   // 字段折扣后的价格的定义。
   public final static FLogicField FieldDiscountPrice = new FLogicField("DISCOUNT_PRICE");

   // 字段物品类型的定义。
   public final static FLogicField FieldItemType = new FLogicField("ITEM_TYPE");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造日志分析消费逻辑单元。</T>
   //============================================================
   public FAnalysisServiceConsumeLogic(){
   }

   //============================================================
   // <T>构造日志分析消费逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FAnalysisServiceConsumeLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FAnalysisServiceConsumeUnit find(long recordId){
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
      FAnalysisServiceConsumeUnit unit = new FAnalysisServiceConsumeUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FAnalysisServiceConsumeUnit serach(String whereSql){
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
      FAnalysisServiceConsumeUnit unit = new FAnalysisServiceConsumeUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FAnalysisServiceConsumeUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FAnalysisServiceConsumeUnit[] fetch(String whereSql, String orderSql){
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
   public FAnalysisServiceConsumeUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FAnalysisServiceConsumeUnit[] units = new FAnalysisServiceConsumeUnit[count];
      for(int n = 0; n < count; n++){
         FAnalysisServiceConsumeUnit unit = new FAnalysisServiceConsumeUnit();
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
   public FAnalysisServiceConsumeUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FAnalysisServiceConsumeUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`GAME_ID`");
      sql.append(",`RECORD_DATE`");
      sql.append(",`RECORD_HOUR`");
      sql.append(",`RECORD_DAY`");
      sql.append(",`RECORD_WEEK`");
      sql.append(",`RECORD_MONTH`");
      sql.append(",`RECORD_INTERVAL`");
      sql.append(",`ROLE_ID`");
      sql.append(",`CONSUME_CD`");
      sql.append(",`CONSUME_VALUE`");
      sql.append(",`BALANCE_VALUE`");
      sql.append(",`ITEM_TID`");
      sql.append(",`ITEM_NUM`");
      sql.append(",`DISCOUNT_PRICE`");
      sql.append(",`ITEM_TYPE`");
      sql.append(",`CREATE_USER_ID`");
      sql.append(",`CREATE_DATE`");
      sql.append(",`UPDATE_USER_ID`");
      sql.append(",`UPDATE_DATE`");
      sql.append(") VALUES(");
      sql.append(unit.ovld());
      sql.append(',');
      sql.append(unit.uniqueId());
      sql.append(',');
      sql.append(unit.gameId());
      sql.append(',');
      if(unit.recordDate().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.recordDate().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      if(unit.recordHour().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.recordHour().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      if(unit.recordDay().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.recordDay().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      if(unit.recordWeek().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.recordWeek().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      if(unit.recordMonth().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.recordMonth().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      sql.append(unit.recordInterval());
      sql.append(',');
      sql.append(unit.roleId());
      sql.append(',');
      sql.append(unit.consumeCd());
      sql.append(',');
      sql.append(unit.consumeValue());
      sql.append(',');
      sql.append(unit.balanceValue());
      sql.append(',');
      sql.append(unit.itemTid());
      sql.append(',');
      sql.append(unit.itemNum());
      sql.append(',');
      sql.append(unit.discountPrice());
      sql.append(',');
      sql.append(unit.itemType());
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
   public boolean doUpdate(FAnalysisServiceConsumeUnit unit, long recordId){
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
      if(unit.isGameIdChanged()){
         sql.append(",`GAME_ID`=");
         sql.append(unit.gameId());
      }
      if(unit.isRecordDateChanged()){
         sql.append(",`RECORD_DATE`=");
         if(unit.recordDate().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.recordDate().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isRecordHourChanged()){
         sql.append(",`RECORD_HOUR`=");
         if(unit.recordHour().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.recordHour().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isRecordDayChanged()){
         sql.append(",`RECORD_DAY`=");
         if(unit.recordDay().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.recordDay().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isRecordWeekChanged()){
         sql.append(",`RECORD_WEEK`=");
         if(unit.recordWeek().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.recordWeek().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isRecordMonthChanged()){
         sql.append(",`RECORD_MONTH`=");
         if(unit.recordMonth().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.recordMonth().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isRecordIntervalChanged()){
         sql.append(",`RECORD_INTERVAL`=");
         sql.append(unit.recordInterval());
      }
      if(unit.isRoleIdChanged()){
         sql.append(",`ROLE_ID`=");
         sql.append(unit.roleId());
      }
      if(unit.isConsumeCdChanged()){
         sql.append(",`CONSUME_CD`=");
         sql.append(unit.consumeCd());
      }
      if(unit.isConsumeValueChanged()){
         sql.append(",`CONSUME_VALUE`=");
         sql.append(unit.consumeValue());
      }
      if(unit.isBalanceValueChanged()){
         sql.append(",`BALANCE_VALUE`=");
         sql.append(unit.balanceValue());
      }
      if(unit.isItemTidChanged()){
         sql.append(",`ITEM_TID`=");
         sql.append(unit.itemTid());
      }
      if(unit.isItemNumChanged()){
         sql.append(",`ITEM_NUM`=");
         sql.append(unit.itemNum());
      }
      if(unit.isDiscountPriceChanged()){
         sql.append(",`DISCOUNT_PRICE`=");
         sql.append(unit.discountPrice());
      }
      if(unit.isItemTypeChanged()){
         sql.append(",`ITEM_TYPE`=");
         sql.append(unit.itemType());
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