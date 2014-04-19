package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>角色宠物信息逻辑。</T>
//============================================================
public class FGameRolePetLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("GM_ROLE_PET");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段角色编号的定义。
   public final static FLogicField FieldRoleId = new FLogicField("ROLE_ID");

   // 字段宠物模板编号的定义。
   public final static FLogicField FieldPetTid = new FLogicField("PET_TID");

   // 字段中文名称的定义。
   public final static FLogicField FieldLabel = new FLogicField("LABEL");

   // 字段种族模板编号的定义。
   public final static FLogicField FieldPetRaceTid = new FLogicField("PET_RACE_TID");

   // 字段类别的定义。
   public final static FLogicField FieldPetTypeTid = new FLogicField("PET_TYPE_TID");

   // 字段宠物形象的定义。
   public final static FLogicField FieldPetStyleTid = new FLogicField("PET_STYLE_TID");

   // 字段级别的定义。
   public final static FLogicField FieldLevel = new FLogicField("LEVEL");

   // 字段寿命的定义。
   public final static FLogicField FieldLife = new FLogicField("LIFE");

   // 字段悟性的定义。
   public final static FLogicField FieldUnderstanding = new FLogicField("UNDERSTANDING");

   // 字段移动速度的定义。
   public final static FLogicField FieldSpeed = new FLogicField("SPEED");

   // 字段体质的定义。
   public final static FLogicField FieldVitality = new FLogicField("VITALITY");

   // 字段力量的定义。
   public final static FLogicField FieldStrength = new FLogicField("STRENGTH");

   // 字段耐力的定义。
   public final static FLogicField FieldStamina = new FLogicField("STAMINA");

   // 字段魔力的定义。
   public final static FLogicField FieldMagic = new FLogicField("MAGIC");

   // 字段敏捷的定义。
   public final static FLogicField FieldAgility = new FLogicField("AGILITY");

   // 字段魔法值的定义。
   public final static FLogicField FieldMp = new FLogicField("MP");

   // 字段生命值的定义。
   public final static FLogicField FieldHp = new FLogicField("HP");

   // 字段气血资质的定义。
   public final static FLogicField FieldItgHp = new FLogicField("ITG_HP");

   // 字段物攻资质的定义。
   public final static FLogicField FieldItgAttackPhysical = new FLogicField("ITG_ATTACK_PHYSICAL");

   // 字段物防资质的定义。
   public final static FLogicField FieldItgDefencePhysical = new FLogicField("ITG_DEFENCE_PHYSICAL");

   // 字段法攻资质的定义。
   public final static FLogicField FieldItgAttackMag = new FLogicField("ITG_ATTACK_MAG");

   // 字段法防资质的定义。
   public final static FLogicField FieldItgDefenMag = new FLogicField("ITG_DEFEN_MAG");

   // 字段速度资质的定义。
   public final static FLogicField FieldItgMobility = new FLogicField("ITG_MOBILITY");

   // 字段成长的定义。
   public final static FLogicField FieldItgGrow = new FLogicField("ITG_GROW");

   // 字段潜力点的定义。
   public final static FLogicField FieldPotencyPoint = new FLogicField("POTENCY_POINT");

   // 字段潜力点道具使用次数的定义。
   public final static FLogicField FieldItemPotencyPointTiems = new FLogicField("ITEM_POTENCY_POINT_TIEMS");

   // 字段物理攻击力的定义。
   public final static FLogicField FieldAttackPhysical = new FLogicField("ATTACK_PHYSICAL");

   // 字段魔法攻击力的定义。
   public final static FLogicField FieldAttackMagic = new FLogicField("ATTACK_MAGIC");

   // 字段物理防御力的定义。
   public final static FLogicField FieldDefencePhysical = new FLogicField("DEFENCE_PHYSICAL");

   // 字段魔法防御力的定义。
   public final static FLogicField FieldDefenceMagic = new FLogicField("DEFENCE_MAGIC");

   // 字段行动值的定义。
   public final static FLogicField FieldMobility = new FLogicField("MOBILITY");

   // 字段命中值的定义。
   public final static FLogicField FieldHit = new FLogicField("HIT");

   // 字段闪避值的定义。
   public final static FLogicField FieldDodge = new FLogicField("DODGE");

   // 字段暴击的定义。
   public final static FLogicField FieldCritical = new FLogicField("CRITICAL");

   // 字段标志的定义。
   public final static FLogicField FieldFlags = new FLogicField("FLAGS");

   // 字段五行的定义。
   public final static FLogicField FieldWuxing = new FLogicField("WUXING");

   // 字段升级所需经验的定义。
   public final static FLogicField FieldExperienceup = new FLogicField("EXPERIENCEUP");

   // 字段经验的定义。
   public final static FLogicField FieldExperience = new FLogicField("EXPERIENCE");

   // 字段初始等级的定义。
   public final static FLogicField FieldInitLevel = new FLogicField("INIT_LEVEL");

   // 字段初始体质的定义。
   public final static FLogicField FieldInitVitality = new FLogicField("INIT_VITALITY");

   // 字段初始力量的定义。
   public final static FLogicField FieldInitStrength = new FLogicField("INIT_STRENGTH");

   // 字段初始耐力的定义。
   public final static FLogicField FieldInitStamina = new FLogicField("INIT_STAMINA");

   // 字段初始魔力的定义。
   public final static FLogicField FieldInitMagic = new FLogicField("INIT_MAGIC");

   // 字段初始敏捷的定义。
   public final static FLogicField FieldInitAgility = new FLogicField("INIT_AGILITY");

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

   // 字段出售开始时间的定义。
   public final static FLogicField FieldSellBeginDate = new FLogicField("SELL_BEGIN_DATE");

   // 字段拍卖时间的定义。
   public final static FLogicField FieldAuctionTimeCd = new FLogicField("AUCTION_TIME_CD");

   // 字段金钱的定义。
   public final static FLogicField FieldCurrency = new FLogicField("CURRENCY");

   // 字段获得时间的定义。
   public final static FLogicField FieldObtainTime = new FLogicField("OBTAIN_TIME");

   // 字段卡牌信息的定义。
   public final static FLogicField FieldCardList = new FLogicField("CARD_LIST");

   // 字段交易状态的定义。
   public final static FLogicField FieldTradeStatus = new FLogicField("TRADE_STATUS");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造角色宠物信息逻辑单元。</T>
   //============================================================
   public FGameRolePetLogic(){
   }

   //============================================================
   // <T>构造角色宠物信息逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FGameRolePetLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FGameRolePetUnit find(long recordId){
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
      FGameRolePetUnit unit = new FGameRolePetUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FGameRolePetUnit serach(String whereSql){
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
      FGameRolePetUnit unit = new FGameRolePetUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FGameRolePetUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FGameRolePetUnit[] fetch(String whereSql, String orderSql){
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
   public FGameRolePetUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FGameRolePetUnit[] units = new FGameRolePetUnit[count];
      for(int n = 0; n < count; n++){
         FGameRolePetUnit unit = new FGameRolePetUnit();
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
   public FGameRolePetUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FGameRolePetUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`ROLE_ID`");
      sql.append(",`PET_TID`");
      sql.append(",`LABEL`");
      sql.append(",`PET_RACE_TID`");
      sql.append(",`PET_TYPE_TID`");
      sql.append(",`PET_STYLE_TID`");
      sql.append(",`LEVEL`");
      sql.append(",`LIFE`");
      sql.append(",`UNDERSTANDING`");
      sql.append(",`SPEED`");
      sql.append(",`VITALITY`");
      sql.append(",`STRENGTH`");
      sql.append(",`STAMINA`");
      sql.append(",`MAGIC`");
      sql.append(",`AGILITY`");
      sql.append(",`MP`");
      sql.append(",`HP`");
      sql.append(",`ITG_HP`");
      sql.append(",`ITG_ATTACK_PHYSICAL`");
      sql.append(",`ITG_DEFENCE_PHYSICAL`");
      sql.append(",`ITG_ATTACK_MAG`");
      sql.append(",`ITG_DEFEN_MAG`");
      sql.append(",`ITG_MOBILITY`");
      sql.append(",`ITG_GROW`");
      sql.append(",`POTENCY_POINT`");
      sql.append(",`ITEM_POTENCY_POINT_TIEMS`");
      sql.append(",`ATTACK_PHYSICAL`");
      sql.append(",`ATTACK_MAGIC`");
      sql.append(",`DEFENCE_PHYSICAL`");
      sql.append(",`DEFENCE_MAGIC`");
      sql.append(",`MOBILITY`");
      sql.append(",`HIT`");
      sql.append(",`DODGE`");
      sql.append(",`CRITICAL`");
      sql.append(",`FLAGS`");
      sql.append(",`WUXING`");
      sql.append(",`EXPERIENCEUP`");
      sql.append(",`EXPERIENCE`");
      sql.append(",`INIT_LEVEL`");
      sql.append(",`INIT_VITALITY`");
      sql.append(",`INIT_STRENGTH`");
      sql.append(",`INIT_STAMINA`");
      sql.append(",`INIT_MAGIC`");
      sql.append(",`INIT_AGILITY`");
      sql.append(",`ITEM_BAG_CD`");
      sql.append(",`ITEM_BAG_ID`");
      sql.append(",`ITEM_BAG_INDEX`");
      sql.append(",`ITEM_BAG_SHORTCUT_ID`");
      sql.append(",`ITEM_BAG_SHORTCUT_INDEX`");
      sql.append(",`SELL_BEGIN_DATE`");
      sql.append(",`AUCTION_TIME_CD`");
      sql.append(",`CURRENCY`");
      sql.append(",`OBTAIN_TIME`");
      sql.append(",`CARD_LIST`");
      sql.append(",`TRADE_STATUS`");
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
      sql.append(unit.petTid());
      sql.append(',');
      if(RString.isEmpty(unit.label())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.label()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.petRaceTid());
      sql.append(',');
      sql.append(unit.petTypeTid());
      sql.append(',');
      sql.append(unit.petStyleTid());
      sql.append(',');
      sql.append(unit.level());
      sql.append(',');
      sql.append(unit.life());
      sql.append(',');
      sql.append(unit.understanding());
      sql.append(',');
      sql.append(unit.speed());
      sql.append(',');
      sql.append(unit.vitality());
      sql.append(',');
      sql.append(unit.strength());
      sql.append(',');
      sql.append(unit.stamina());
      sql.append(',');
      sql.append(unit.magic());
      sql.append(',');
      sql.append(unit.agility());
      sql.append(',');
      if(RString.isEmpty(unit.mp())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.mp()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.hp())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.hp()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.itgHp());
      sql.append(',');
      sql.append(unit.itgAttackPhysical());
      sql.append(',');
      sql.append(unit.itgDefencePhysical());
      sql.append(',');
      sql.append(unit.itgAttackMag());
      sql.append(',');
      sql.append(unit.itgDefenMag());
      sql.append(',');
      sql.append(unit.itgMobility());
      sql.append(',');
      sql.append(unit.itgGrow());
      sql.append(',');
      sql.append(unit.potencyPoint());
      sql.append(',');
      sql.append(unit.itemPotencyPointTiems());
      sql.append(',');
      sql.append(unit.attackPhysical());
      sql.append(',');
      sql.append(unit.attackMagic());
      sql.append(',');
      sql.append(unit.defencePhysical());
      sql.append(',');
      sql.append(unit.defenceMagic());
      sql.append(',');
      sql.append(unit.mobility());
      sql.append(',');
      sql.append(unit.hit());
      sql.append(',');
      sql.append(unit.dodge());
      sql.append(',');
      sql.append(unit.critical());
      sql.append(',');
      sql.append(unit.flags());
      sql.append(',');
      sql.append(unit.wuxing());
      sql.append(',');
      sql.append(unit.experienceup());
      sql.append(',');
      sql.append(unit.experience());
      sql.append(',');
      sql.append(unit.initLevel());
      sql.append(',');
      sql.append(unit.initVitality());
      sql.append(',');
      sql.append(unit.initStrength());
      sql.append(',');
      sql.append(unit.initStamina());
      sql.append(',');
      sql.append(unit.initMagic());
      sql.append(',');
      sql.append(unit.initAgility());
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
      if(unit.sellBeginDate().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.sellBeginDate().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      sql.append(unit.auctionTimeCd());
      sql.append(',');
      if(RString.isEmpty(unit.currency())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.currency()));
         sql.append('\'');
      }
      sql.append(',');
      if(unit.obtainTime().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.obtainTime().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      if(RString.isEmpty(unit.cardList())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.cardList()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.tradeStatus());
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
   public boolean doUpdate(FGameRolePetUnit unit, long recordId){
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
      if(unit.isPetTidChanged()){
         sql.append(",`PET_TID`=");
         sql.append(unit.petTid());
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
      if(unit.isPetRaceTidChanged()){
         sql.append(",`PET_RACE_TID`=");
         sql.append(unit.petRaceTid());
      }
      if(unit.isPetTypeTidChanged()){
         sql.append(",`PET_TYPE_TID`=");
         sql.append(unit.petTypeTid());
      }
      if(unit.isPetStyleTidChanged()){
         sql.append(",`PET_STYLE_TID`=");
         sql.append(unit.petStyleTid());
      }
      if(unit.isLevelChanged()){
         sql.append(",`LEVEL`=");
         sql.append(unit.level());
      }
      if(unit.isLifeChanged()){
         sql.append(",`LIFE`=");
         sql.append(unit.life());
      }
      if(unit.isUnderstandingChanged()){
         sql.append(",`UNDERSTANDING`=");
         sql.append(unit.understanding());
      }
      if(unit.isSpeedChanged()){
         sql.append(",`SPEED`=");
         sql.append(unit.speed());
      }
      if(unit.isVitalityChanged()){
         sql.append(",`VITALITY`=");
         sql.append(unit.vitality());
      }
      if(unit.isStrengthChanged()){
         sql.append(",`STRENGTH`=");
         sql.append(unit.strength());
      }
      if(unit.isStaminaChanged()){
         sql.append(",`STAMINA`=");
         sql.append(unit.stamina());
      }
      if(unit.isMagicChanged()){
         sql.append(",`MAGIC`=");
         sql.append(unit.magic());
      }
      if(unit.isAgilityChanged()){
         sql.append(",`AGILITY`=");
         sql.append(unit.agility());
      }
      if(unit.isMpChanged()){
         sql.append(",`MP`=");
         if(RString.isEmpty(unit.mp())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.mp()));
            sql.append("'");
         }
      }
      if(unit.isHpChanged()){
         sql.append(",`HP`=");
         if(RString.isEmpty(unit.hp())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.hp()));
            sql.append("'");
         }
      }
      if(unit.isItgHpChanged()){
         sql.append(",`ITG_HP`=");
         sql.append(unit.itgHp());
      }
      if(unit.isItgAttackPhysicalChanged()){
         sql.append(",`ITG_ATTACK_PHYSICAL`=");
         sql.append(unit.itgAttackPhysical());
      }
      if(unit.isItgDefencePhysicalChanged()){
         sql.append(",`ITG_DEFENCE_PHYSICAL`=");
         sql.append(unit.itgDefencePhysical());
      }
      if(unit.isItgAttackMagChanged()){
         sql.append(",`ITG_ATTACK_MAG`=");
         sql.append(unit.itgAttackMag());
      }
      if(unit.isItgDefenMagChanged()){
         sql.append(",`ITG_DEFEN_MAG`=");
         sql.append(unit.itgDefenMag());
      }
      if(unit.isItgMobilityChanged()){
         sql.append(",`ITG_MOBILITY`=");
         sql.append(unit.itgMobility());
      }
      if(unit.isItgGrowChanged()){
         sql.append(",`ITG_GROW`=");
         sql.append(unit.itgGrow());
      }
      if(unit.isPotencyPointChanged()){
         sql.append(",`POTENCY_POINT`=");
         sql.append(unit.potencyPoint());
      }
      if(unit.isItemPotencyPointTiemsChanged()){
         sql.append(",`ITEM_POTENCY_POINT_TIEMS`=");
         sql.append(unit.itemPotencyPointTiems());
      }
      if(unit.isAttackPhysicalChanged()){
         sql.append(",`ATTACK_PHYSICAL`=");
         sql.append(unit.attackPhysical());
      }
      if(unit.isAttackMagicChanged()){
         sql.append(",`ATTACK_MAGIC`=");
         sql.append(unit.attackMagic());
      }
      if(unit.isDefencePhysicalChanged()){
         sql.append(",`DEFENCE_PHYSICAL`=");
         sql.append(unit.defencePhysical());
      }
      if(unit.isDefenceMagicChanged()){
         sql.append(",`DEFENCE_MAGIC`=");
         sql.append(unit.defenceMagic());
      }
      if(unit.isMobilityChanged()){
         sql.append(",`MOBILITY`=");
         sql.append(unit.mobility());
      }
      if(unit.isHitChanged()){
         sql.append(",`HIT`=");
         sql.append(unit.hit());
      }
      if(unit.isDodgeChanged()){
         sql.append(",`DODGE`=");
         sql.append(unit.dodge());
      }
      if(unit.isCriticalChanged()){
         sql.append(",`CRITICAL`=");
         sql.append(unit.critical());
      }
      if(unit.isFlagsChanged()){
         sql.append(",`FLAGS`=");
         sql.append(unit.flags());
      }
      if(unit.isWuxingChanged()){
         sql.append(",`WUXING`=");
         sql.append(unit.wuxing());
      }
      if(unit.isExperienceupChanged()){
         sql.append(",`EXPERIENCEUP`=");
         sql.append(unit.experienceup());
      }
      if(unit.isExperienceChanged()){
         sql.append(",`EXPERIENCE`=");
         sql.append(unit.experience());
      }
      if(unit.isInitLevelChanged()){
         sql.append(",`INIT_LEVEL`=");
         sql.append(unit.initLevel());
      }
      if(unit.isInitVitalityChanged()){
         sql.append(",`INIT_VITALITY`=");
         sql.append(unit.initVitality());
      }
      if(unit.isInitStrengthChanged()){
         sql.append(",`INIT_STRENGTH`=");
         sql.append(unit.initStrength());
      }
      if(unit.isInitStaminaChanged()){
         sql.append(",`INIT_STAMINA`=");
         sql.append(unit.initStamina());
      }
      if(unit.isInitMagicChanged()){
         sql.append(",`INIT_MAGIC`=");
         sql.append(unit.initMagic());
      }
      if(unit.isInitAgilityChanged()){
         sql.append(",`INIT_AGILITY`=");
         sql.append(unit.initAgility());
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
      if(unit.isSellBeginDateChanged()){
         sql.append(",`SELL_BEGIN_DATE`=");
         if(unit.sellBeginDate().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.sellBeginDate().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isAuctionTimeCdChanged()){
         sql.append(",`AUCTION_TIME_CD`=");
         sql.append(unit.auctionTimeCd());
      }
      if(unit.isCurrencyChanged()){
         sql.append(",`CURRENCY`=");
         if(RString.isEmpty(unit.currency())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.currency()));
            sql.append("'");
         }
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
      if(unit.isCardListChanged()){
         sql.append(",`CARD_LIST`=");
         if(RString.isEmpty(unit.cardList())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.cardList()));
            sql.append("'");
         }
      }
      if(unit.isTradeStatusChanged()){
         sql.append(",`TRADE_STATUS`=");
         sql.append(unit.tradeStatus());
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