package com.zqinet.logic.data.logger;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>玩家交易日志逻辑。</T>
//============================================================
public class FLoggerTradeLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("LOG_TRADE");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段角色编号的定义。
   public final static FLogicField FieldRoleId = new FLogicField("ROLE_ID");

   // 字段交易类型的定义。
   public final static FLogicField FieldTradeType = new FLogicField("TRADE_TYPE");

   // 字段目标角色编号的定义。
   public final static FLogicField FieldTargetRoleId = new FLogicField("TARGET_ROLE_ID");

   // 字段交易的道具集合的定义。
   public final static FLogicField FieldPropSet = new FLogicField("PROP_SET");

   // 字段交易的装备集合的定义。
   public final static FLogicField FieldEquipSet = new FLogicField("EQUIP_SET");

   // 字段交易的宠物编号的定义。
   public final static FLogicField FieldPetTid = new FLogicField("PET_TID");

   // 字段交易的银两的定义。
   public final static FLogicField FieldGold = new FLogicField("GOLD");

   // 字段交易的元宝数的定义。
   public final static FLogicField FieldMoney = new FLogicField("MONEY");

   // 字段交易目标类型的定义。
   public final static FLogicField FieldItemCd = new FLogicField("ITEM_CD");

   // 字段交易目标编号的定义。
   public final static FLogicField FieldItemTid = new FLogicField("ITEM_TID");

   // 字段交易数量的定义。
   public final static FLogicField FieldItemCount = new FLogicField("ITEM_COUNT");

   // 字段参数1的定义。
   public final static FLogicField FieldParam1 = new FLogicField("PARAM1");

   // 字段参数2的定义。
   public final static FLogicField FieldParam2 = new FLogicField("PARAM2");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造玩家交易日志逻辑单元。</T>
   //============================================================
   public FLoggerTradeLogic(){
   }

   //============================================================
   // <T>构造玩家交易日志逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FLoggerTradeLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FLoggerTradeUnit find(long recordId){
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
      FLoggerTradeUnit unit = new FLoggerTradeUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FLoggerTradeUnit serach(String whereSql){
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
      FLoggerTradeUnit unit = new FLoggerTradeUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FLoggerTradeUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FLoggerTradeUnit[] fetch(String whereSql, String orderSql){
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
   public FLoggerTradeUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FLoggerTradeUnit[] units = new FLoggerTradeUnit[count];
      for(int n = 0; n < count; n++){
         FLoggerTradeUnit unit = new FLoggerTradeUnit();
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
   public FLoggerTradeUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FLoggerTradeUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`ROLE_ID`");
      sql.append(",`TRADE_TYPE`");
      sql.append(",`TARGET_ROLE_ID`");
      sql.append(",`PROP_SET`");
      sql.append(",`EQUIP_SET`");
      sql.append(",`PET_TID`");
      sql.append(",`GOLD`");
      sql.append(",`MONEY`");
      sql.append(",`ITEM_CD`");
      sql.append(",`ITEM_TID`");
      sql.append(",`ITEM_COUNT`");
      sql.append(",`PARAM1`");
      sql.append(",`PARAM2`");
      sql.append(",`CREATE_USER_ID`");
      sql.append(",`CREATE_DATE`");
      sql.append(",`UPDATE_USER_ID`");
      sql.append(",`UPDATE_DATE`");
      sql.append(") VALUES(");
      sql.append(unit.ovld());
      sql.append(',');
      sql.append(unit.uniqueId());
      sql.append(',');
      sql.append(unit.roleId());
      sql.append(',');
      sql.append(unit.tradeType());
      sql.append(',');
      sql.append(unit.targetRoleId());
      sql.append(',');
      if(RString.isEmpty(unit.propSet())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.propSet()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.equipSet())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.equipSet()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.petTid());
      sql.append(',');
      sql.append(unit.gold());
      sql.append(',');
      sql.append(unit.money());
      sql.append(',');
      sql.append(unit.itemCd());
      sql.append(',');
      sql.append(unit.itemTid());
      sql.append(',');
      sql.append(unit.itemCount());
      sql.append(',');
      sql.append(unit.param1());
      sql.append(',');
      sql.append(unit.param2());
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
   public boolean doUpdate(FLoggerTradeUnit unit, long recordId){
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
      if(unit.isRoleIdChanged()){
         sql.append(",`ROLE_ID`=");
         sql.append(unit.roleId());
      }
      if(unit.isTradeTypeChanged()){
         sql.append(",`TRADE_TYPE`=");
         sql.append(unit.tradeType());
      }
      if(unit.isTargetRoleIdChanged()){
         sql.append(",`TARGET_ROLE_ID`=");
         sql.append(unit.targetRoleId());
      }
      if(unit.isPropSetChanged()){
         sql.append(",`PROP_SET`=");
         if(RString.isEmpty(unit.propSet())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.propSet()));
            sql.append("'");
         }
      }
      if(unit.isEquipSetChanged()){
         sql.append(",`EQUIP_SET`=");
         if(RString.isEmpty(unit.equipSet())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.equipSet()));
            sql.append("'");
         }
      }
      if(unit.isPetTidChanged()){
         sql.append(",`PET_TID`=");
         sql.append(unit.petTid());
      }
      if(unit.isGoldChanged()){
         sql.append(",`GOLD`=");
         sql.append(unit.gold());
      }
      if(unit.isMoneyChanged()){
         sql.append(",`MONEY`=");
         sql.append(unit.money());
      }
      if(unit.isItemCdChanged()){
         sql.append(",`ITEM_CD`=");
         sql.append(unit.itemCd());
      }
      if(unit.isItemTidChanged()){
         sql.append(",`ITEM_TID`=");
         sql.append(unit.itemTid());
      }
      if(unit.isItemCountChanged()){
         sql.append(",`ITEM_COUNT`=");
         sql.append(unit.itemCount());
      }
      if(unit.isParam1Changed()){
         sql.append(",`PARAM1`=");
         sql.append(unit.param1());
      }
      if(unit.isParam2Changed()){
         sql.append(",`PARAM2`=");
         sql.append(unit.param2());
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