package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>社会系统逻辑。</T>
//============================================================
public class FGameSocietyLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("GM_SOCIETY");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段标签的定义。
   public final static FLogicField FieldLabel = new FLogicField("LABEL");

   // 字段级别的定义。
   public final static FLogicField FieldLevel = new FLogicField("LEVEL");

   // 字段帮会公告的定义。
   public final static FLogicField FieldPlacard = new FLogicField("PLACARD");

   // 字段公会宣言的定义。
   public final static FLogicField FieldManifesto = new FLogicField("MANIFESTO");

   // 字段帮会人数的定义。
   public final static FLogicField FieldMemberCount = new FLogicField("MEMBER_COUNT");

   // 字段帮会申请人数的定义。
   public final static FLogicField FieldApplyMemberCount = new FLogicField("APPLY_MEMBER_COUNT");

   // 字段帮主的定义。
   public final static FLogicField FieldFirstLeader = new FLogicField("FIRST_LEADER");

   // 字段创建人的编号的定义。
   public final static FLogicField FieldCreateRoleId = new FLogicField("CREATE_ROLE_ID");

   // 字段帮会繁荣度的定义。
   public final static FLogicField FieldProsperityDegree = new FLogicField("PROSPERITY_DEGREE");

   // 字段帮会资金的定义。
   public final static FLogicField FieldCapital = new FLogicField("CAPITAL");

   // 字段帮徽等级的定义。
   public final static FLogicField FieldBadgeLevel = new FLogicField("BADGE_LEVEL");

   // 字段创建世间的定义。
   public final static FLogicField FieldCreateTime = new FLogicField("CREATE_TIME");

   // 字段帮会申请列表的定义。
   public final static FLogicField FieldApplyMember = new FLogicField("APPLY_MEMBER");

   // 字段帮战总积分的定义。
   public final static FLogicField FieldTotalSocietywarPoint = new FLogicField("TOTAL_SOCIETYWAR_POINT");

   // 字段日常数据的定义。
   public final static FLogicField FieldDailyData = new FLogicField("DAILY_DATA");

   // 字段周常数据的定义。
   public final static FLogicField FieldWeeklyData = new FLogicField("WEEKLY_DATA");

   // 字段帮会财主活动记录的定义。
   public final static FLogicField FieldRichmanRecord = new FLogicField("RICHMAN_RECORD");

   // 字段帮会技能列表的定义。
   public final static FLogicField FieldSocietySkillList = new FLogicField("SOCIETY_SKILL_LIST");

   // 字段新手帮会标志的定义。
   public final static FLogicField FieldIsNewerSociety = new FLogicField("IS_NEWER_SOCIETY");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造社会系统逻辑单元。</T>
   //============================================================
   public FGameSocietyLogic(){
   }

   //============================================================
   // <T>构造社会系统逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FGameSocietyLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FGameSocietyUnit find(long recordId){
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
      FGameSocietyUnit unit = new FGameSocietyUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FGameSocietyUnit serach(String whereSql){
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
      FGameSocietyUnit unit = new FGameSocietyUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FGameSocietyUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FGameSocietyUnit[] fetch(String whereSql, String orderSql){
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
   public FGameSocietyUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FGameSocietyUnit[] units = new FGameSocietyUnit[count];
      for(int n = 0; n < count; n++){
         FGameSocietyUnit unit = new FGameSocietyUnit();
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
   public FGameSocietyUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FGameSocietyUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`LABEL`");
      sql.append(",`LEVEL`");
      sql.append(",`PLACARD`");
      sql.append(",`MANIFESTO`");
      sql.append(",`MEMBER_COUNT`");
      sql.append(",`APPLY_MEMBER_COUNT`");
      sql.append(",`FIRST_LEADER`");
      sql.append(",`CREATE_ROLE_ID`");
      sql.append(",`PROSPERITY_DEGREE`");
      sql.append(",`CAPITAL`");
      sql.append(",`BADGE_LEVEL`");
      sql.append(",`CREATE_TIME`");
      sql.append(",`APPLY_MEMBER`");
      sql.append(",`TOTAL_SOCIETYWAR_POINT`");
      sql.append(",`DAILY_DATA`");
      sql.append(",`WEEKLY_DATA`");
      sql.append(",`RICHMAN_RECORD`");
      sql.append(",`SOCIETY_SKILL_LIST`");
      sql.append(",`IS_NEWER_SOCIETY`");
      sql.append(",`CREATE_USER_ID`");
      sql.append(",`CREATE_DATE`");
      sql.append(",`UPDATE_USER_ID`");
      sql.append(",`UPDATE_DATE`");
      sql.append(") VALUES(");
      sql.append(unit.ovld());
      sql.append(',');
      sql.append(unit.uniqueId());
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
      if(RString.isEmpty(unit.placard())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.placard()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.manifesto())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.manifesto()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.memberCount())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.memberCount()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.applyMemberCount())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.applyMemberCount()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.firstLeader());
      sql.append(',');
      sql.append(unit.createRoleId());
      sql.append(',');
      sql.append(unit.prosperityDegree());
      sql.append(',');
      sql.append(unit.capital());
      sql.append(',');
      sql.append(unit.badgeLevel());
      sql.append(',');
      if(unit.createTime().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.createTime().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      if(RString.isEmpty(unit.applyMember())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.applyMember()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.totalSocietywarPoint());
      sql.append(',');
      if(RString.isEmpty(unit.dailyData())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.dailyData()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.weeklyData())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.weeklyData()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.richmanRecord())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.richmanRecord()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.societySkillList())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.societySkillList()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.isNewerSociety());
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
   public boolean doUpdate(FGameSocietyUnit unit, long recordId){
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
      if(unit.isPlacardChanged()){
         sql.append(",`PLACARD`=");
         if(RString.isEmpty(unit.placard())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.placard()));
            sql.append("'");
         }
      }
      if(unit.isManifestoChanged()){
         sql.append(",`MANIFESTO`=");
         if(RString.isEmpty(unit.manifesto())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.manifesto()));
            sql.append("'");
         }
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
      if(unit.isApplyMemberCountChanged()){
         sql.append(",`APPLY_MEMBER_COUNT`=");
         if(RString.isEmpty(unit.applyMemberCount())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.applyMemberCount()));
            sql.append("'");
         }
      }
      if(unit.isFirstLeaderChanged()){
         sql.append(",`FIRST_LEADER`=");
         sql.append(unit.firstLeader());
      }
      if(unit.isCreateRoleIdChanged()){
         sql.append(",`CREATE_ROLE_ID`=");
         sql.append(unit.createRoleId());
      }
      if(unit.isProsperityDegreeChanged()){
         sql.append(",`PROSPERITY_DEGREE`=");
         sql.append(unit.prosperityDegree());
      }
      if(unit.isCapitalChanged()){
         sql.append(",`CAPITAL`=");
         sql.append(unit.capital());
      }
      if(unit.isBadgeLevelChanged()){
         sql.append(",`BADGE_LEVEL`=");
         sql.append(unit.badgeLevel());
      }
      if(unit.isCreateTimeChanged()){
         sql.append(",`CREATE_TIME`=");
         if(unit.createTime().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.createTime().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isApplyMemberChanged()){
         sql.append(",`APPLY_MEMBER`=");
         if(RString.isEmpty(unit.applyMember())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.applyMember()));
            sql.append("'");
         }
      }
      if(unit.isTotalSocietywarPointChanged()){
         sql.append(",`TOTAL_SOCIETYWAR_POINT`=");
         sql.append(unit.totalSocietywarPoint());
      }
      if(unit.isDailyDataChanged()){
         sql.append(",`DAILY_DATA`=");
         if(RString.isEmpty(unit.dailyData())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.dailyData()));
            sql.append("'");
         }
      }
      if(unit.isWeeklyDataChanged()){
         sql.append(",`WEEKLY_DATA`=");
         if(RString.isEmpty(unit.weeklyData())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.weeklyData()));
            sql.append("'");
         }
      }
      if(unit.isRichmanRecordChanged()){
         sql.append(",`RICHMAN_RECORD`=");
         if(RString.isEmpty(unit.richmanRecord())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.richmanRecord()));
            sql.append("'");
         }
      }
      if(unit.isSocietySkillListChanged()){
         sql.append(",`SOCIETY_SKILL_LIST`=");
         if(RString.isEmpty(unit.societySkillList())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.societySkillList()));
            sql.append("'");
         }
      }
      if(unit.isIsNewerSocietyChanged()){
         sql.append(",`IS_NEWER_SOCIETY`=");
         sql.append(unit.isNewerSociety());
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