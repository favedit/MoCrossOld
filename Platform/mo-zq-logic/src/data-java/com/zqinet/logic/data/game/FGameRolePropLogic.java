package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>角色道具信息逻辑。</T>
//============================================================
public class FGameRolePropLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("GM_ROLE_PROP");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段角色编号的定义。
   public final static FLogicField FieldRoleId = new FLogicField("ROLE_ID");

   // 字段道具模板编号的定义。
   public final static FLogicField FieldPropTid = new FLogicField("PROP_TID");

   // 字段个数的定义。
   public final static FLogicField FieldCount = new FLogicField("COUNT");

   // 字段绑定类型的定义。
   public final static FLogicField FieldBindCd = new FLogicField("BIND_CD");

   // 字段任务编号的定义。
   public final static FLogicField FieldTaskId = new FLogicField("TASK_ID");

   // 字段交易类型的定义。
   public final static FLogicField FieldTradeCd = new FLogicField("TRADE_CD");

   // 字段等级的定义。
   public final static FLogicField FieldRank = new FLogicField("RANK");

   // 字段物品品质的定义。
   public final static FLogicField FieldQualityTid = new FLogicField("QUALITY_TID");

   // 字段绑定状态的定义。
   public final static FLogicField FieldBindStatus = new FLogicField("BIND_STATUS");

   // 字段有效时间的定义。
   public final static FLogicField FieldEffectiveTime = new FLogicField("EFFECTIVE_TIME");

   // 字段结束时间的定义。
   public final static FLogicField FieldEndTime = new FLogicField("END_TIME");

   // 字段是否可续时的定义。
   public final static FLogicField FieldIsContinue = new FLogicField("IS_CONTINUE");

   // 字段是否到期的定义。
   public final static FLogicField FieldIsTimeout = new FLogicField("IS_TIMEOUT");

   // 字段计时类型的定义。
   public final static FLogicField FieldTimingType = new FLogicField("TIMING_TYPE");

   // 字段开始计时时间的定义。
   public final static FLogicField FieldBeginTime = new FLogicField("BEGIN_TIME");

   // 字段计时器编号的定义。
   public final static FLogicField FieldTimeId = new FLogicField("TIME_ID");

   // 字段刷新道具编号的定义。
   public final static FLogicField FieldRefreshPropTid = new FLogicField("REFRESH_PROP_TID");

   // 字段物品包类型的定义。
   public final static FLogicField FieldItemBagCd = new FLogicField("ITEM_BAG_CD");

   // 字段物品背包编号的定义。
   public final static FLogicField FieldItemBagId = new FLogicField("ITEM_BAG_ID");

   // 字段物品包索引的定义。
   public final static FLogicField FieldItemBagIndex = new FLogicField("ITEM_BAG_INDEX");

   // 字段物品快捷栏编号的定义。
   public final static FLogicField FieldItemBagShortcutId = new FLogicField("ITEM_BAG_SHORTCUT_ID");

   // 字段物品快捷栏索引的定义。
   public final static FLogicField FieldItemBagShortcutIndex = new FLogicField("ITEM_BAG_SHORTCUT_INDEX");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   // 字段挖宝目标地图模板编号的定义。
   public final static FLogicField FieldTargetMapTid = new FLogicField("TARGET_MAP_TID");

   // 字段挖宝目标坐标的定义。
   public final static FLogicField FieldTargetLocation = new FLogicField("TARGET_LOCATION");

   //============================================================
   // <T>构造角色道具信息逻辑单元。</T>
   //============================================================
   public FGameRolePropLogic(){
   }

   //============================================================
   // <T>构造角色道具信息逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FGameRolePropLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FGameRolePropUnit find(long recordId){
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
      FGameRolePropUnit unit = new FGameRolePropUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FGameRolePropUnit serach(String whereSql){
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
      FGameRolePropUnit unit = new FGameRolePropUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FGameRolePropUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FGameRolePropUnit[] fetch(String whereSql, String orderSql){
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
   public FGameRolePropUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FGameRolePropUnit[] units = new FGameRolePropUnit[count];
      for(int n = 0; n < count; n++){
         FGameRolePropUnit unit = new FGameRolePropUnit();
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
   public FGameRolePropUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FGameRolePropUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`ROLE_ID`");
      sql.append(",`PROP_TID`");
      sql.append(",`COUNT`");
      sql.append(",`BIND_CD`");
      sql.append(",`TASK_ID`");
      sql.append(",`TRADE_CD`");
      sql.append(",`RANK`");
      sql.append(",`QUALITY_TID`");
      sql.append(",`BIND_STATUS`");
      sql.append(",`EFFECTIVE_TIME`");
      sql.append(",`END_TIME`");
      sql.append(",`IS_CONTINUE`");
      sql.append(",`IS_TIMEOUT`");
      sql.append(",`TIMING_TYPE`");
      sql.append(",`BEGIN_TIME`");
      sql.append(",`TIME_ID`");
      sql.append(",`REFRESH_PROP_TID`");
      sql.append(",`ITEM_BAG_CD`");
      sql.append(",`ITEM_BAG_ID`");
      sql.append(",`ITEM_BAG_INDEX`");
      sql.append(",`ITEM_BAG_SHORTCUT_ID`");
      sql.append(",`ITEM_BAG_SHORTCUT_INDEX`");
      sql.append(",`CREATE_USER_ID`");
      sql.append(",`CREATE_DATE`");
      sql.append(",`UPDATE_USER_ID`");
      sql.append(",`UPDATE_DATE`");
      sql.append(",`TARGET_MAP_TID`");
      sql.append(",`TARGET_LOCATION`");
      sql.append(") VALUES(");
      sql.append(unit.ovld());
      sql.append(',');
      sql.append(unit.uniqueId());
      sql.append(',');
      sql.append(unit.roleId());
      sql.append(',');
      sql.append(unit.propTid());
      sql.append(',');
      sql.append(unit.count());
      sql.append(',');
      sql.append(unit.bindCd());
      sql.append(',');
      sql.append(unit.taskId());
      sql.append(',');
      sql.append(unit.tradeCd());
      sql.append(',');
      sql.append(unit.rank());
      sql.append(',');
      sql.append(unit.qualityTid());
      sql.append(',');
      sql.append(unit.bindStatus());
      sql.append(',');
      sql.append(unit.effectiveTime());
      sql.append(',');
      if(unit.endTime().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.endTime().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      sql.append(unit.isContinue());
      sql.append(',');
      sql.append(unit.isTimeout());
      sql.append(',');
      sql.append(unit.timingType());
      sql.append(',');
      if(unit.beginTime().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.beginTime().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      sql.append(unit.timeId());
      sql.append(',');
      sql.append(unit.refreshPropTid());
      sql.append(',');
      sql.append(unit.itemBagCd());
      sql.append(',');
      sql.append(unit.itemBagId());
      sql.append(',');
      sql.append(unit.itemBagIndex());
      sql.append(',');
      sql.append(unit.itemBagShortcutId());
      sql.append(',');
      sql.append(unit.itemBagShortcutIndex());
      sql.append(',');
      sql.append(unit.targetMapTid());
      sql.append(',');
      if(RString.isEmpty(unit.targetLocation())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.targetLocation()));
         sql.append('\'');
      }
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
   public boolean doUpdate(FGameRolePropUnit unit, long recordId){
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
      if(unit.isPropTidChanged()){
         sql.append(",`PROP_TID`=");
         sql.append(unit.propTid());
      }
      if(unit.isCountChanged()){
         sql.append(",`COUNT`=");
         sql.append(unit.count());
      }
      if(unit.isBindCdChanged()){
         sql.append(",`BIND_CD`=");
         sql.append(unit.bindCd());
      }
      if(unit.isTaskIdChanged()){
         sql.append(",`TASK_ID`=");
         sql.append(unit.taskId());
      }
      if(unit.isTradeCdChanged()){
         sql.append(",`TRADE_CD`=");
         sql.append(unit.tradeCd());
      }
      if(unit.isRankChanged()){
         sql.append(",`RANK`=");
         sql.append(unit.rank());
      }
      if(unit.isQualityTidChanged()){
         sql.append(",`QUALITY_TID`=");
         sql.append(unit.qualityTid());
      }
      if(unit.isBindStatusChanged()){
         sql.append(",`BIND_STATUS`=");
         sql.append(unit.bindStatus());
      }
      if(unit.isEffectiveTimeChanged()){
         sql.append(",`EFFECTIVE_TIME`=");
         sql.append(unit.effectiveTime());
      }
      if(unit.isEndTimeChanged()){
         sql.append(",`END_TIME`=");
         if(unit.endTime().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.endTime().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isIsContinueChanged()){
         sql.append(",`IS_CONTINUE`=");
         sql.append(unit.isContinue());
      }
      if(unit.isIsTimeoutChanged()){
         sql.append(",`IS_TIMEOUT`=");
         sql.append(unit.isTimeout());
      }
      if(unit.isTimingTypeChanged()){
         sql.append(",`TIMING_TYPE`=");
         sql.append(unit.timingType());
      }
      if(unit.isBeginTimeChanged()){
         sql.append(",`BEGIN_TIME`=");
         if(unit.beginTime().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.beginTime().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isTimeIdChanged()){
         sql.append(",`TIME_ID`=");
         sql.append(unit.timeId());
      }
      if(unit.isRefreshPropTidChanged()){
         sql.append(",`REFRESH_PROP_TID`=");
         sql.append(unit.refreshPropTid());
      }
      if(unit.isItemBagCdChanged()){
         sql.append(",`ITEM_BAG_CD`=");
         sql.append(unit.itemBagCd());
      }
      if(unit.isItemBagIdChanged()){
         sql.append(",`ITEM_BAG_ID`=");
         sql.append(unit.itemBagId());
      }
      if(unit.isItemBagIndexChanged()){
         sql.append(",`ITEM_BAG_INDEX`=");
         sql.append(unit.itemBagIndex());
      }
      if(unit.isItemBagShortcutIdChanged()){
         sql.append(",`ITEM_BAG_SHORTCUT_ID`=");
         sql.append(unit.itemBagShortcutId());
      }
      if(unit.isItemBagShortcutIndexChanged()){
         sql.append(",`ITEM_BAG_SHORTCUT_INDEX`=");
         sql.append(unit.itemBagShortcutIndex());
      }
      if(unit.isCreateUserIdChanged()){
         sql.append(",`CREATE_USER_ID`=");
         sql.append(unit.createUserId());
      }
      if(unit.isTargetMapTidChanged()){
         sql.append(",`TARGET_MAP_TID`=");
         sql.append(unit.targetMapTid());
      }
      if(unit.isTargetLocationChanged()){
         sql.append(",`TARGET_LOCATION`=");
         if(RString.isEmpty(unit.targetLocation())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.targetLocation()));
            sql.append("'");
         }
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