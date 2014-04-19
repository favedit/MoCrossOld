package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>角色装备信息逻辑。</T>
//============================================================
public class FGameRoleEquipLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("GM_ROLE_EQUIP");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段角色编号的定义。
   public final static FLogicField FieldRoleId = new FLogicField("ROLE_ID");

   // 字段装备模板编号的定义。
   public final static FLogicField FieldEquipTid = new FLogicField("EQUIP_TID");

   // 字段个数的定义。
   public final static FLogicField FieldCount = new FLogicField("COUNT");

   // 字段插槽类型的定义。
   public final static FLogicField FieldSlotCd = new FLogicField("SLOT_CD");

   // 字段任务编号的定义。
   public final static FLogicField FieldTaskId = new FLogicField("TASK_ID");

   // 字段交易类型的定义。
   public final static FLogicField FieldTradeCd = new FLogicField("TRADE_CD");

   // 字段星级的定义。
   public final static FLogicField FieldStars = new FLogicField("STARS");

   // 字段装备宝石插槽的定义。
   public final static FLogicField FieldEquipGemSlot = new FLogicField("EQUIP_GEM_SLOT");

   // 字段装备鉴定次数的定义。
   public final static FLogicField FieldEquipAppreciateTimes = new FLogicField("EQUIP_APPRECIATE_TIMES");

   // 字段装备强化次数的定义。
   public final static FLogicField FieldEquipStarsTimes = new FLogicField("EQUIP_STARS_TIMES");

   // 字段耐久的定义。
   public final static FLogicField FieldWear = new FLogicField("WEAR");

   // 字段绑定类型的定义。
   public final static FLogicField FieldBindCd = new FLogicField("BIND_CD");

   // 字段绑定状态的定义。
   public final static FLogicField FieldBindStatus = new FLogicField("BIND_STATUS");

   // 字段品级模版编号的定义。
   public final static FLogicField FieldQualityTid = new FLogicField("QUALITY_TID");

   // 字段鉴定内容打包的定义。
   public final static FLogicField FieldContentPack = new FLogicField("CONTENT_PACK");

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

   // 字段当前鉴定内容打包的定义。
   public final static FLogicField FieldIdentifyPack = new FLogicField("IDENTIFY_PACK");

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

   //============================================================
   // <T>构造角色装备信息逻辑单元。</T>
   //============================================================
   public FGameRoleEquipLogic(){
   }

   //============================================================
   // <T>构造角色装备信息逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FGameRoleEquipLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FGameRoleEquipUnit find(long recordId){
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
      FGameRoleEquipUnit unit = new FGameRoleEquipUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FGameRoleEquipUnit serach(String whereSql){
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
      FGameRoleEquipUnit unit = new FGameRoleEquipUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FGameRoleEquipUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FGameRoleEquipUnit[] fetch(String whereSql, String orderSql){
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
   public FGameRoleEquipUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FGameRoleEquipUnit[] units = new FGameRoleEquipUnit[count];
      for(int n = 0; n < count; n++){
         FGameRoleEquipUnit unit = new FGameRoleEquipUnit();
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
   public FGameRoleEquipUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FGameRoleEquipUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`ROLE_ID`");
      sql.append(",`EQUIP_TID`");
      sql.append(",`COUNT`");
      sql.append(",`SLOT_CD`");
      sql.append(",`TASK_ID`");
      sql.append(",`TRADE_CD`");
      sql.append(",`STARS`");
      sql.append(",`EQUIP_GEM_SLOT`");
      sql.append(",`EQUIP_APPRECIATE_TIMES`");
      sql.append(",`EQUIP_STARS_TIMES`");
      sql.append(",`WEAR`");
      sql.append(",`BIND_CD`");
      sql.append(",`BIND_STATUS`");
      sql.append(",`QUALITY_TID`");
      sql.append(",`CONTENT_PACK`");
      sql.append(",`EFFECTIVE_TIME`");
      sql.append(",`END_TIME`");
      sql.append(",`IS_CONTINUE`");
      sql.append(",`IS_TIMEOUT`");
      sql.append(",`TIMING_TYPE`");
      sql.append(",`BEGIN_TIME`");
      sql.append(",`TIME_ID`");
      sql.append(",`IDENTIFY_PACK`");
      sql.append(",`ITEM_BAG_CD`");
      sql.append(",`ITEM_BAG_ID`");
      sql.append(",`ITEM_BAG_INDEX`");
      sql.append(",`ITEM_BAG_SHORTCUT_ID`");
      sql.append(",`ITEM_BAG_SHORTCUT_INDEX`");
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
      sql.append(unit.equipTid());
      sql.append(',');
      sql.append(unit.count());
      sql.append(',');
      sql.append(unit.slotCd());
      sql.append(',');
      sql.append(unit.taskId());
      sql.append(',');
      sql.append(unit.tradeCd());
      sql.append(',');
      sql.append(unit.stars());
      sql.append(',');
      if(RString.isEmpty(unit.equipGemSlot())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.equipGemSlot()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.equipAppreciateTimes());
      sql.append(',');
      sql.append(unit.equipStarsTimes());
      sql.append(',');
      sql.append(unit.wear());
      sql.append(',');
      sql.append(unit.bindCd());
      sql.append(',');
      sql.append(unit.bindStatus());
      sql.append(',');
      sql.append(unit.qualityTid());
      sql.append(',');
      if(RString.isEmpty(unit.contentPack())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.contentPack()));
         sql.append('\'');
      }
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
      if(RString.isEmpty(unit.identifyPack())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.identifyPack()));
         sql.append('\'');
      }
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
   public boolean doUpdate(FGameRoleEquipUnit unit, long recordId){
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
      if(unit.isEquipTidChanged()){
         sql.append(",`EQUIP_TID`=");
         sql.append(unit.equipTid());
      }
      if(unit.isCountChanged()){
         sql.append(",`COUNT`=");
         sql.append(unit.count());
      }
      if(unit.isSlotCdChanged()){
         sql.append(",`SLOT_CD`=");
         sql.append(unit.slotCd());
      }
      if(unit.isTaskIdChanged()){
         sql.append(",`TASK_ID`=");
         sql.append(unit.taskId());
      }
      if(unit.isTradeCdChanged()){
         sql.append(",`TRADE_CD`=");
         sql.append(unit.tradeCd());
      }
      if(unit.isStarsChanged()){
         sql.append(",`STARS`=");
         sql.append(unit.stars());
      }
      if(unit.isEquipGemSlotChanged()){
         sql.append(",`EQUIP_GEM_SLOT`=");
         if(RString.isEmpty(unit.equipGemSlot())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.equipGemSlot()));
            sql.append("'");
         }
      }
      if(unit.isEquipAppreciateTimesChanged()){
         sql.append(",`EQUIP_APPRECIATE_TIMES`=");
         sql.append(unit.equipAppreciateTimes());
      }
      if(unit.isEquipStarsTimesChanged()){
         sql.append(",`EQUIP_STARS_TIMES`=");
         sql.append(unit.equipStarsTimes());
      }
      if(unit.isWearChanged()){
         sql.append(",`WEAR`=");
         sql.append(unit.wear());
      }
      if(unit.isBindCdChanged()){
         sql.append(",`BIND_CD`=");
         sql.append(unit.bindCd());
      }
      if(unit.isBindStatusChanged()){
         sql.append(",`BIND_STATUS`=");
         sql.append(unit.bindStatus());
      }
      if(unit.isQualityTidChanged()){
         sql.append(",`QUALITY_TID`=");
         sql.append(unit.qualityTid());
      }
      if(unit.isContentPackChanged()){
         sql.append(",`CONTENT_PACK`=");
         if(RString.isEmpty(unit.contentPack())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.contentPack()));
            sql.append("'");
         }
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
      if(unit.isIdentifyPackChanged()){
         sql.append(",`IDENTIFY_PACK`=");
         if(RString.isEmpty(unit.identifyPack())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.identifyPack()));
            sql.append("'");
         }
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