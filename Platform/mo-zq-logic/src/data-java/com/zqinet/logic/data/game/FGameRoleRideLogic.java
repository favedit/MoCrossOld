package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>角色坐骑信息逻辑。</T>
//============================================================
public class FGameRoleRideLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("GM_ROLE_RIDE");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段角色编号的定义。
   public final static FLogicField FieldRoleId = new FLogicField("ROLE_ID");

   // 字段坐骑模板编号的定义。
   public final static FLogicField FieldRideTid = new FLogicField("RIDE_TID");

   // 字段标志的定义。
   public final static FLogicField FieldFlags = new FLogicField("FLAGS");

   // 字段级别的定义。
   public final static FLogicField FieldLevel = new FLogicField("LEVEL");

   // 字段身价的定义。
   public final static FLogicField FieldCost = new FLogicField("COST");

   // 字段星级的定义。
   public final static FLogicField FieldStarLevel = new FLogicField("STAR_LEVEL");

   // 字段移动速度的定义。
   public final static FLogicField FieldSpeed = new FLogicField("SPEED");

   // 字段档阶的定义。
   public final static FLogicField FieldFileOrder = new FLogicField("FILE_ORDER");

   // 字段气血的定义。
   public final static FLogicField FieldHpArg = new FLogicField("HP_ARG");

   // 字段魔法的定义。
   public final static FLogicField FieldMpArg = new FLogicField("MP_ARG");

   // 字段物攻的定义。
   public final static FLogicField FieldAttackPhysicalArg = new FLogicField("ATTACK_PHYSICAL_ARG");

   // 字段物防的定义。
   public final static FLogicField FieldDefencePhysicalArg = new FLogicField("DEFENCE_PHYSICAL_ARG");

   // 字段法攻的定义。
   public final static FLogicField FieldAttackMagicArg = new FLogicField("ATTACK_MAGIC_ARG");

   // 字段法防的定义。
   public final static FLogicField FieldDefenceMagicArg = new FLogicField("DEFENCE_MAGIC_ARG");

   // 字段当前经验的定义。
   public final static FLogicField FieldExperience = new FLogicField("EXPERIENCE");

   // 字段升级所需经验的定义。
   public final static FLogicField FieldExperienceUp = new FLogicField("EXPERIENCE_UP");

   // 字段中文名称的定义。
   public final static FLogicField FieldLabel = new FLogicField("LABEL");

   // 字段幸运值的定义。
   public final static FLogicField FieldLuck = new FLogicField("LUCK");

   // 字段获得时间的定义。
   public final static FLogicField FieldObtainTime = new FLogicField("OBTAIN_TIME");

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
   // <T>构造角色坐骑信息逻辑单元。</T>
   //============================================================
   public FGameRoleRideLogic(){
   }

   //============================================================
   // <T>构造角色坐骑信息逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FGameRoleRideLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FGameRoleRideUnit find(long recordId){
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
      FGameRoleRideUnit unit = new FGameRoleRideUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FGameRoleRideUnit serach(String whereSql){
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
      FGameRoleRideUnit unit = new FGameRoleRideUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FGameRoleRideUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FGameRoleRideUnit[] fetch(String whereSql, String orderSql){
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
   public FGameRoleRideUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FGameRoleRideUnit[] units = new FGameRoleRideUnit[count];
      for(int n = 0; n < count; n++){
         FGameRoleRideUnit unit = new FGameRoleRideUnit();
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
   public FGameRoleRideUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FGameRoleRideUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`ROLE_ID`");
      sql.append(",`RIDE_TID`");
      sql.append(",`FLAGS`");
      sql.append(",`LEVEL`");
      sql.append(",`COST`");
      sql.append(",`STAR_LEVEL`");
      sql.append(",`SPEED`");
      sql.append(",`FILE_ORDER`");
      sql.append(",`HP_ARG`");
      sql.append(",`MP_ARG`");
      sql.append(",`ATTACK_PHYSICAL_ARG`");
      sql.append(",`DEFENCE_PHYSICAL_ARG`");
      sql.append(",`ATTACK_MAGIC_ARG`");
      sql.append(",`DEFENCE_MAGIC_ARG`");
      sql.append(",`EXPERIENCE`");
      sql.append(",`EXPERIENCE_UP`");
      sql.append(",`LABEL`");
      sql.append(",`LUCK`");
      sql.append(",`OBTAIN_TIME`");
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
      sql.append(unit.rideTid());
      sql.append(',');
      sql.append(unit.flags());
      sql.append(',');
      sql.append(unit.level());
      sql.append(',');
      sql.append(unit.cost());
      sql.append(',');
      sql.append(unit.starLevel());
      sql.append(',');
      sql.append(unit.speed());
      sql.append(',');
      sql.append(unit.fileOrder());
      sql.append(',');
      sql.append(unit.hpArg());
      sql.append(',');
      sql.append(unit.mpArg());
      sql.append(',');
      sql.append(unit.attackPhysicalArg());
      sql.append(',');
      sql.append(unit.defencePhysicalArg());
      sql.append(',');
      sql.append(unit.attackMagicArg());
      sql.append(',');
      sql.append(unit.defenceMagicArg());
      sql.append(',');
      sql.append(unit.experience());
      sql.append(',');
      sql.append(unit.experienceUp());
      sql.append(',');
      if(RString.isEmpty(unit.label())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.label()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.luck());
      sql.append(',');
      if(unit.obtainTime().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.obtainTime().format());
         sql.append("','%Y%m%d%H%i%s')");
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
   public boolean doUpdate(FGameRoleRideUnit unit, long recordId){
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
      if(unit.isRideTidChanged()){
         sql.append(",`RIDE_TID`=");
         sql.append(unit.rideTid());
      }
      if(unit.isFlagsChanged()){
         sql.append(",`FLAGS`=");
         sql.append(unit.flags());
      }
      if(unit.isLevelChanged()){
         sql.append(",`LEVEL`=");
         sql.append(unit.level());
      }
      if(unit.isCostChanged()){
         sql.append(",`COST`=");
         sql.append(unit.cost());
      }
      if(unit.isStarLevelChanged()){
         sql.append(",`STAR_LEVEL`=");
         sql.append(unit.starLevel());
      }
      if(unit.isSpeedChanged()){
         sql.append(",`SPEED`=");
         sql.append(unit.speed());
      }
      if(unit.isFileOrderChanged()){
         sql.append(",`FILE_ORDER`=");
         sql.append(unit.fileOrder());
      }
      if(unit.isHpArgChanged()){
         sql.append(",`HP_ARG`=");
         sql.append(unit.hpArg());
      }
      if(unit.isMpArgChanged()){
         sql.append(",`MP_ARG`=");
         sql.append(unit.mpArg());
      }
      if(unit.isAttackPhysicalArgChanged()){
         sql.append(",`ATTACK_PHYSICAL_ARG`=");
         sql.append(unit.attackPhysicalArg());
      }
      if(unit.isDefencePhysicalArgChanged()){
         sql.append(",`DEFENCE_PHYSICAL_ARG`=");
         sql.append(unit.defencePhysicalArg());
      }
      if(unit.isAttackMagicArgChanged()){
         sql.append(",`ATTACK_MAGIC_ARG`=");
         sql.append(unit.attackMagicArg());
      }
      if(unit.isDefenceMagicArgChanged()){
         sql.append(",`DEFENCE_MAGIC_ARG`=");
         sql.append(unit.defenceMagicArg());
      }
      if(unit.isExperienceChanged()){
         sql.append(",`EXPERIENCE`=");
         sql.append(unit.experience());
      }
      if(unit.isExperienceUpChanged()){
         sql.append(",`EXPERIENCE_UP`=");
         sql.append(unit.experienceUp());
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
      if(unit.isLuckChanged()){
         sql.append(",`LUCK`=");
         sql.append(unit.luck());
      }
      if(unit.isObtainTimeChanged()){
         sql.append(",`OBTAIN_TIME`=");
         if(unit.obtainTime().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.obtainTime().format());
            sql.append("','%Y%m%d%H%i%s')");
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