package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>记录上场战斗的详细信息逻辑。</T>
//============================================================
public class FGameLastbattleLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("GM_LASTBATTLE");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段战场id的定义。
   public final static FLogicField FieldBattleId = new FLogicField("BATTLE_ID");

   // 字段角色ID的定义。
   public final static FLogicField FieldRoleId = new FLogicField("ROLE_ID");

   // 字段用户名的定义。
   public final static FLogicField FieldLabel = new FLogicField("LABEL");

   // 字段等级的定义。
   public final static FLogicField FieldLevel = new FLogicField("LEVEL");

   // 字段最大连杀数的定义。
   public final static FLogicField FieldMaxCombokill = new FLogicField("MAX_COMBOKILL");

   // 字段阵营的定义。
   public final static FLogicField FieldSide = new FLogicField("SIDE");

   // 字段累计分数的定义。
   public final static FLogicField FieldTotalPoint = new FLogicField("TOTAL_POINT");

   // 字段获得的竞技场点数的定义。
   public final static FLogicField FieldPoint = new FLogicField("POINT");

   // 字段总共击杀的个数的定义。
   public final static FLogicField FieldTotalKill = new FLogicField("TOTAL_KILL");

   // 字段战场结果的定义。
   public final static FLogicField FieldBattleResult = new FLogicField("BATTLE_RESULT");

   // 字段是否是帮会记录的定义。
   public final static FLogicField FieldIsSociety = new FLogicField("IS_SOCIETY");

   // 字段帮会ID的定义。
   public final static FLogicField FieldSocietyId = new FLogicField("SOCIETY_ID");

   // 字段所在战区的定义。
   public final static FLogicField FieldBattleSection = new FLogicField("BATTLE_SECTION");

   // 字段所在等级组的定义。
   public final static FLogicField FieldLevelgroup = new FLogicField("LEVELGROUP");

   // 字段个人帮贡的定义。
   public final static FLogicField FieldBanggong = new FLogicField("BANGGONG");

   // 字段帮会资金 的定义。
   public final static FLogicField FieldMoney = new FLogicField("MONEY");

   // 字段繁荣度的定义。
   public final static FLogicField FieldFanrong = new FLogicField("FANRONG");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造记录上场战斗的详细信息逻辑单元。</T>
   //============================================================
   public FGameLastbattleLogic(){
   }

   //============================================================
   // <T>构造记录上场战斗的详细信息逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FGameLastbattleLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FGameLastbattleUnit find(long recordId){
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
      FGameLastbattleUnit unit = new FGameLastbattleUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FGameLastbattleUnit serach(String whereSql){
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
      FGameLastbattleUnit unit = new FGameLastbattleUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FGameLastbattleUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FGameLastbattleUnit[] fetch(String whereSql, String orderSql){
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
   public FGameLastbattleUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FGameLastbattleUnit[] units = new FGameLastbattleUnit[count];
      for(int n = 0; n < count; n++){
         FGameLastbattleUnit unit = new FGameLastbattleUnit();
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
   public FGameLastbattleUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FGameLastbattleUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`BATTLE_ID`");
      sql.append(",`ROLE_ID`");
      sql.append(",`LABEL`");
      sql.append(",`LEVEL`");
      sql.append(",`MAX_COMBOKILL`");
      sql.append(",`SIDE`");
      sql.append(",`TOTAL_POINT`");
      sql.append(",`POINT`");
      sql.append(",`TOTAL_KILL`");
      sql.append(",`BATTLE_RESULT`");
      sql.append(",`IS_SOCIETY`");
      sql.append(",`SOCIETY_ID`");
      sql.append(",`BATTLE_SECTION`");
      sql.append(",`LEVELGROUP`");
      sql.append(",`BANGGONG`");
      sql.append(",`MONEY`");
      sql.append(",`FANRONG`");
      sql.append(",`CREATE_USER_ID`");
      sql.append(",`CREATE_DATE`");
      sql.append(",`UPDATE_USER_ID`");
      sql.append(",`UPDATE_DATE`");
      sql.append(") VALUES(");
      sql.append(unit.ovld());
      sql.append(',');
      sql.append(unit.uniqueId());
      sql.append(',');
      sql.append(unit.battleId());
      sql.append(',');
      sql.append(unit.roleId());
      sql.append(',');
      if(RString.isEmpty(unit.label())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.label()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.level());
      sql.append(',');
      sql.append(unit.maxCombokill());
      sql.append(',');
      sql.append(unit.side());
      sql.append(',');
      sql.append(unit.totalPoint());
      sql.append(',');
      sql.append(unit.point());
      sql.append(',');
      sql.append(unit.totalKill());
      sql.append(',');
      sql.append(unit.battleResult());
      sql.append(',');
      sql.append(unit.isSociety());
      sql.append(',');
      sql.append(unit.societyId());
      sql.append(',');
      sql.append(unit.battleSection());
      sql.append(',');
      sql.append(unit.levelgroup());
      sql.append(',');
      sql.append(unit.banggong());
      sql.append(',');
      sql.append(unit.money());
      sql.append(',');
      sql.append(unit.fanrong());
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
   public boolean doUpdate(FGameLastbattleUnit unit, long recordId){
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
      if(unit.isBattleIdChanged()){
         sql.append(",`BATTLE_ID`=");
         sql.append(unit.battleId());
      }
      if(unit.isRoleIdChanged()){
         sql.append(",`ROLE_ID`=");
         sql.append(unit.roleId());
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
      if(unit.isLevelChanged()){
         sql.append(",`LEVEL`=");
         sql.append(unit.level());
      }
      if(unit.isMaxCombokillChanged()){
         sql.append(",`MAX_COMBOKILL`=");
         sql.append(unit.maxCombokill());
      }
      if(unit.isSideChanged()){
         sql.append(",`SIDE`=");
         sql.append(unit.side());
      }
      if(unit.isTotalPointChanged()){
         sql.append(",`TOTAL_POINT`=");
         sql.append(unit.totalPoint());
      }
      if(unit.isPointChanged()){
         sql.append(",`POINT`=");
         sql.append(unit.point());
      }
      if(unit.isTotalKillChanged()){
         sql.append(",`TOTAL_KILL`=");
         sql.append(unit.totalKill());
      }
      if(unit.isBattleResultChanged()){
         sql.append(",`BATTLE_RESULT`=");
         sql.append(unit.battleResult());
      }
      if(unit.isIsSocietyChanged()){
         sql.append(",`IS_SOCIETY`=");
         sql.append(unit.isSociety());
      }
      if(unit.isSocietyIdChanged()){
         sql.append(",`SOCIETY_ID`=");
         sql.append(unit.societyId());
      }
      if(unit.isBattleSectionChanged()){
         sql.append(",`BATTLE_SECTION`=");
         sql.append(unit.battleSection());
      }
      if(unit.isLevelgroupChanged()){
         sql.append(",`LEVELGROUP`=");
         sql.append(unit.levelgroup());
      }
      if(unit.isBanggongChanged()){
         sql.append(",`BANGGONG`=");
         sql.append(unit.banggong());
      }
      if(unit.isMoneyChanged()){
         sql.append(",`MONEY`=");
         sql.append(unit.money());
      }
      if(unit.isFanrongChanged()){
         sql.append(",`FANRONG`=");
         sql.append(unit.fanrong());
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