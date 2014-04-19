package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>排行榜信息逻辑。</T>
//============================================================
public class FGameRankItemLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("GM_RANK_ITEM");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段角色编号的定义。
   public final static FLogicField FieldRoleId = new FLogicField("ROLE_ID");

   // 字段编号的定义。
   public final static FLogicField FieldId = new FLogicField("ID");

   // 字段排行的定义。
   public final static FLogicField FieldRankIndex = new FLogicField("RANK_INDEX");

   // 字段数值的定义。
   public final static FLogicField FieldRankValue = new FLogicField("RANK_VALUE");

   // 字段保留数值的定义。
   public final static FLogicField FieldReserveValue = new FLogicField("RESERVE_VALUE");

   // 字段类型的定义。
   public final static FLogicField FieldRankType = new FLogicField("RANK_TYPE");

   // 字段名称的定义。
   public final static FLogicField FieldName = new FLogicField("NAME");

   // 字段性别的定义。
   public final static FLogicField FieldGender = new FLogicField("GENDER");

   // 字段职业的定义。
   public final static FLogicField FieldMetier = new FLogicField("METIER");

   // 字段所属名称的定义。
   public final static FLogicField FieldBelongName = new FLogicField("BELONG_NAME");

   // 字段宠物品级的定义。
   public final static FLogicField FieldPetQuailty = new FLogicField("PET_QUAILTY");

   // 字段等级的定义。
   public final static FLogicField FieldLevel = new FLogicField("LEVEL");

   // 字段装备品质的定义。
   public final static FLogicField FieldEquipQuailtyTid = new FLogicField("EQUIP_QUAILTY_TID");

   // 字段帮会人数的定义。
   public final static FLogicField FieldMemberCount = new FLogicField("MEMBER_COUNT");

   // 字段鄙视次数的定义。
   public final static FLogicField FieldDisdainCount = new FLogicField("DISDAIN_COUNT");

   // 字段膜拜次数的定义。
   public final static FLogicField FieldWorshipCount = new FLogicField("WORSHIP_COUNT");

   // 字段上榜日期的定义。
   public final static FLogicField FieldRankDate = new FLogicField("RANK_DATE");

   // 字段角色等级的定义。
   public final static FLogicField FieldRoleLevel = new FLogicField("ROLE_LEVEL");

   // 字段会员等级的定义。
   public final static FLogicField FieldVipLevel = new FLogicField("VIP_LEVEL");

   // 字段额外VIP信息的定义。
   public final static FLogicField FieldExVipInfo = new FLogicField("EX_VIP_INFO");

   // 字段额外VIP等级的定义。
   public final static FLogicField FieldExVipLevel = new FLogicField("EX_VIP_LEVEL");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造排行榜信息逻辑单元。</T>
   //============================================================
   public FGameRankItemLogic(){
   }

   //============================================================
   // <T>构造排行榜信息逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FGameRankItemLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FGameRankItemUnit find(long recordId){
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
      FGameRankItemUnit unit = new FGameRankItemUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FGameRankItemUnit serach(String whereSql){
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
      FGameRankItemUnit unit = new FGameRankItemUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FGameRankItemUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FGameRankItemUnit[] fetch(String whereSql, String orderSql){
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
   public FGameRankItemUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FGameRankItemUnit[] units = new FGameRankItemUnit[count];
      for(int n = 0; n < count; n++){
         FGameRankItemUnit unit = new FGameRankItemUnit();
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
   public FGameRankItemUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FGameRankItemUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`ROLE_ID`");
      sql.append(",`ID`");
      sql.append(",`RANK_INDEX`");
      sql.append(",`RANK_VALUE`");
      sql.append(",`RESERVE_VALUE`");
      sql.append(",`RANK_TYPE`");
      sql.append(",`NAME`");
      sql.append(",`GENDER`");
      sql.append(",`METIER`");
      sql.append(",`BELONG_NAME`");
      sql.append(",`PET_QUAILTY`");
      sql.append(",`LEVEL`");
      sql.append(",`EQUIP_QUAILTY_TID`");
      sql.append(",`MEMBER_COUNT`");
      sql.append(",`DISDAIN_COUNT`");
      sql.append(",`WORSHIP_COUNT`");
      sql.append(",`RANK_DATE`");
      sql.append(",`ROLE_LEVEL`");
      sql.append(",`VIP_LEVEL`");
      sql.append(",`EX_VIP_INFO`");
      sql.append(",`EX_VIP_LEVEL`");
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
      sql.append(unit.id());
      sql.append(',');
      sql.append(unit.rankIndex());
      sql.append(',');
      sql.append(unit.rankValue());
      sql.append(',');
      sql.append(unit.reserveValue());
      sql.append(',');
      sql.append(unit.rankType());
      sql.append(',');
      if(RString.isEmpty(unit.name())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.name()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.gender());
      sql.append(',');
      sql.append(unit.metier());
      sql.append(',');
      if(RString.isEmpty(unit.belongName())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.belongName()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.petQuailty());
      sql.append(',');
      sql.append(unit.level());
      sql.append(',');
      sql.append(unit.equipQuailtyTid());
      sql.append(',');
      if(RString.isEmpty(unit.memberCount())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.memberCount()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.disdainCount());
      sql.append(',');
      sql.append(unit.worshipCount());
      sql.append(',');
      if(unit.rankDate().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.rankDate().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      sql.append(unit.roleLevel());
      sql.append(',');
      sql.append(unit.vipLevel());
      sql.append(',');
      sql.append(unit.exVipInfo());
      sql.append(',');
      sql.append(unit.exVipLevel());
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
   public boolean doUpdate(FGameRankItemUnit unit, long recordId){
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
      if(unit.isIdChanged()){
         sql.append(",`ID`=");
         sql.append(unit.id());
      }
      if(unit.isRankIndexChanged()){
         sql.append(",`RANK_INDEX`=");
         sql.append(unit.rankIndex());
      }
      if(unit.isRankValueChanged()){
         sql.append(",`RANK_VALUE`=");
         sql.append(unit.rankValue());
      }
      if(unit.isReserveValueChanged()){
         sql.append(",`RESERVE_VALUE`=");
         sql.append(unit.reserveValue());
      }
      if(unit.isRankTypeChanged()){
         sql.append(",`RANK_TYPE`=");
         sql.append(unit.rankType());
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
      if(unit.isGenderChanged()){
         sql.append(",`GENDER`=");
         sql.append(unit.gender());
      }
      if(unit.isMetierChanged()){
         sql.append(",`METIER`=");
         sql.append(unit.metier());
      }
      if(unit.isBelongNameChanged()){
         sql.append(",`BELONG_NAME`=");
         if(RString.isEmpty(unit.belongName())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.belongName()));
            sql.append("'");
         }
      }
      if(unit.isPetQuailtyChanged()){
         sql.append(",`PET_QUAILTY`=");
         sql.append(unit.petQuailty());
      }
      if(unit.isLevelChanged()){
         sql.append(",`LEVEL`=");
         sql.append(unit.level());
      }
      if(unit.isEquipQuailtyTidChanged()){
         sql.append(",`EQUIP_QUAILTY_TID`=");
         sql.append(unit.equipQuailtyTid());
      }
      if(unit.isMemberCountChanged()){
         sql.append(",`MEMBER_COUNT`=");
         if(RString.isEmpty(unit.memberCount())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.memberCount()));
            sql.append("'");
         }
      }
      if(unit.isDisdainCountChanged()){
         sql.append(",`DISDAIN_COUNT`=");
         sql.append(unit.disdainCount());
      }
      if(unit.isWorshipCountChanged()){
         sql.append(",`WORSHIP_COUNT`=");
         sql.append(unit.worshipCount());
      }
      if(unit.isRankDateChanged()){
         sql.append(",`RANK_DATE`=");
         if(unit.rankDate().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.rankDate().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isRoleLevelChanged()){
         sql.append(",`ROLE_LEVEL`=");
         sql.append(unit.roleLevel());
      }
      if(unit.isVipLevelChanged()){
         sql.append(",`VIP_LEVEL`=");
         sql.append(unit.vipLevel());
      }
      if(unit.isExVipInfoChanged()){
         sql.append(",`EX_VIP_INFO`=");
         sql.append(unit.exVipInfo());
      }
      if(unit.isExVipLevelChanged()){
         sql.append(",`EX_VIP_LEVEL`=");
         sql.append(unit.exVipLevel());
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