package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>角色信息逻辑。</T>
//============================================================
public class FGameRoleDataLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("GM_ROLE_DATA");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段角色ID的定义。
   public final static FLogicField FieldRoleId = new FLogicField("ROLE_ID");

   // 字段副本的CD状况的定义。
   public final static FLogicField FieldCopyCd = new FLogicField("COPY_CD");

   // 字段周常数据存放的定义。
   public final static FLogicField FieldWeeklyData = new FLogicField("WEEKLY_DATA");

   // 字段活动数据的定义。
   public final static FLogicField FieldActivityData = new FLogicField("ACTIVITY_DATA");

   // 字段九界Boss数据的定义。
   public final static FLogicField FieldNineCirlesBossData = new FLogicField("NINE_CIRLES_BOSS_DATA");

   // 字段九界数据的定义。
   public final static FLogicField FieldNineCirlesData = new FLogicField("NINE_CIRLES_DATA");

   // 字段共血boss数据的定义。
   public final static FLogicField FieldShareHpBossData = new FLogicField("SHARE_HP_BOSS_DATA");

   // 字段特殊演员的定义。
   public final static FLogicField FieldSpecialActors = new FLogicField("SPECIAL_ACTORS");

   // 字段功能预告的定义。
   public final static FLogicField FieldFunctionPreview = new FLogicField("FUNCTION_PREVIEW");

   // 字段皇榜任务的定义。
   public final static FLogicField FieldTaskRoyal = new FLogicField("TASK_ROYAL");

   // 字段角色答题信息的定义。
   public final static FLogicField FieldRoleAnswer = new FLogicField("ROLE_ANSWER");

   // 字段新手引导模板编号链表的定义。
   public final static FLogicField FieldNewerGuideTidList = new FLogicField("NEWER_GUIDE_TID_LIST");

   // 字段十天目标的定义。
   public final static FLogicField FieldTenDaysTarget = new FLogicField("TEN_DAYS_TARGET");

   // 字段神秘商店信息的定义。
   public final static FLogicField FieldMystery = new FLogicField("MYSTERY");

   // 字段勋章的定义。
   public final static FLogicField FieldMedal = new FLogicField("MEDAL");

   // 字段勋章奖励状态的定义。
   public final static FLogicField FieldMedalAwardStatus = new FLogicField("MEDAL_AWARD_STATUS");

   // 字段勋章记录数据的定义。
   public final static FLogicField FieldMedalRecordData = new FLogicField("MEDAL_RECORD_DATA");

   // 字段buff集合的定义。
   public final static FLogicField FieldBuffSet = new FLogicField("BUFF_SET");

   // 字段四圣血脉的定义。
   public final static FLogicField FieldFourGodBlood = new FLogicField("FOUR_GOD_BLOOD");

   // 字段使用道具限制的定义。
   public final static FLogicField FieldPropLimit = new FLogicField("PROP_LIMIT");

   // 字段样式动作集合的定义。
   public final static FLogicField FieldStyleActionSet = new FLogicField("STYLE_ACTION_SET");

   // 字段目标的定义。
   public final static FLogicField FieldUniverseTarget = new FLogicField("UNIVERSE_TARGET");

   // 字段申请帮会列表的定义。
   public final static FLogicField FieldApplyList = new FLogicField("APPLY_LIST");

   // 字段消费信息的定义。
   public final static FLogicField FieldSpendData = new FLogicField("SPEND_DATA");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造角色信息逻辑单元。</T>
   //============================================================
   public FGameRoleDataLogic(){
   }

   //============================================================
   // <T>构造角色信息逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FGameRoleDataLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FGameRoleDataUnit find(long recordId){
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
      FGameRoleDataUnit unit = new FGameRoleDataUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FGameRoleDataUnit serach(String whereSql){
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
      FGameRoleDataUnit unit = new FGameRoleDataUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FGameRoleDataUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FGameRoleDataUnit[] fetch(String whereSql, String orderSql){
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
   public FGameRoleDataUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FGameRoleDataUnit[] units = new FGameRoleDataUnit[count];
      for(int n = 0; n < count; n++){
         FGameRoleDataUnit unit = new FGameRoleDataUnit();
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
   public FGameRoleDataUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FGameRoleDataUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`ROLE_ID`");
      sql.append(",`COPY_CD`");
      sql.append(",`WEEKLY_DATA`");
      sql.append(",`ACTIVITY_DATA`");
      sql.append(",`NINE_CIRLES_BOSS_DATA`");
      sql.append(",`NINE_CIRLES_DATA`");
      sql.append(",`SHARE_HP_BOSS_DATA`");
      sql.append(",`SPECIAL_ACTORS`");
      sql.append(",`FUNCTION_PREVIEW`");
      sql.append(",`TASK_ROYAL`");
      sql.append(",`ROLE_ANSWER`");
      sql.append(",`NEWER_GUIDE_TID_LIST`");
      sql.append(",`TEN_DAYS_TARGET`");
      sql.append(",`MYSTERY`");
      sql.append(",`MEDAL`");
      sql.append(",`MEDAL_AWARD_STATUS`");
      sql.append(",`MEDAL_RECORD_DATA`");
      sql.append(",`BUFF_SET`");
      sql.append(",`FOUR_GOD_BLOOD`");
      sql.append(",`PROP_LIMIT`");
      sql.append(",`STYLE_ACTION_SET`");
      sql.append(",`UNIVERSE_TARGET`");
      sql.append(",`APPLY_LIST`");
      sql.append(",`SPEND_DATA`");
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
      if(RString.isEmpty(unit.copyCd())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.copyCd()));
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
      if(RString.isEmpty(unit.activityData())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.activityData()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.nineCirlesBossData())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.nineCirlesBossData()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.nineCirlesData())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.nineCirlesData()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.shareHpBossData())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.shareHpBossData()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.specialActors())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.specialActors()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.functionPreview())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.functionPreview()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.taskRoyal())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.taskRoyal()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.roleAnswer())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.roleAnswer()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.newerGuideTidList())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.newerGuideTidList()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.tenDaysTarget())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.tenDaysTarget()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.mystery())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.mystery()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.medal())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.medal()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.medalAwardStatus())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.medalAwardStatus()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.medalRecordData())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.medalRecordData()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.buffSet())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.buffSet()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.fourGodBlood())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.fourGodBlood()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.propLimit())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.propLimit()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.styleActionSet())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.styleActionSet()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.universeTarget())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.universeTarget()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.applyList())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.applyList()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.spendData())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.spendData()));
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
   public boolean doUpdate(FGameRoleDataUnit unit, long recordId){
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
      if(unit.isCopyCdChanged()){
         sql.append(",`COPY_CD`=");
         if(RString.isEmpty(unit.copyCd())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.copyCd()));
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
      if(unit.isActivityDataChanged()){
         sql.append(",`ACTIVITY_DATA`=");
         if(RString.isEmpty(unit.activityData())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.activityData()));
            sql.append("'");
         }
      }
      if(unit.isNineCirlesBossDataChanged()){
         sql.append(",`NINE_CIRLES_BOSS_DATA`=");
         if(RString.isEmpty(unit.nineCirlesBossData())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.nineCirlesBossData()));
            sql.append("'");
         }
      }
      if(unit.isNineCirlesDataChanged()){
         sql.append(",`NINE_CIRLES_DATA`=");
         if(RString.isEmpty(unit.nineCirlesData())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.nineCirlesData()));
            sql.append("'");
         }
      }
      if(unit.isShareHpBossDataChanged()){
         sql.append(",`SHARE_HP_BOSS_DATA`=");
         if(RString.isEmpty(unit.shareHpBossData())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.shareHpBossData()));
            sql.append("'");
         }
      }
      if(unit.isSpecialActorsChanged()){
         sql.append(",`SPECIAL_ACTORS`=");
         if(RString.isEmpty(unit.specialActors())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.specialActors()));
            sql.append("'");
         }
      }
      if(unit.isFunctionPreviewChanged()){
         sql.append(",`FUNCTION_PREVIEW`=");
         if(RString.isEmpty(unit.functionPreview())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.functionPreview()));
            sql.append("'");
         }
      }
      if(unit.isTaskRoyalChanged()){
         sql.append(",`TASK_ROYAL`=");
         if(RString.isEmpty(unit.taskRoyal())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.taskRoyal()));
            sql.append("'");
         }
      }
      if(unit.isRoleAnswerChanged()){
         sql.append(",`ROLE_ANSWER`=");
         if(RString.isEmpty(unit.roleAnswer())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.roleAnswer()));
            sql.append("'");
         }
      }
      if(unit.isNewerGuideTidListChanged()){
         sql.append(",`NEWER_GUIDE_TID_LIST`=");
         if(RString.isEmpty(unit.newerGuideTidList())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.newerGuideTidList()));
            sql.append("'");
         }
      }
      if(unit.isTenDaysTargetChanged()){
         sql.append(",`TEN_DAYS_TARGET`=");
         if(RString.isEmpty(unit.tenDaysTarget())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.tenDaysTarget()));
            sql.append("'");
         }
      }
      if(unit.isMysteryChanged()){
         sql.append(",`MYSTERY`=");
         if(RString.isEmpty(unit.mystery())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.mystery()));
            sql.append("'");
         }
      }
      if(unit.isMedalChanged()){
         sql.append(",`MEDAL`=");
         if(RString.isEmpty(unit.medal())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.medal()));
            sql.append("'");
         }
      }
      if(unit.isMedalAwardStatusChanged()){
         sql.append(",`MEDAL_AWARD_STATUS`=");
         if(RString.isEmpty(unit.medalAwardStatus())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.medalAwardStatus()));
            sql.append("'");
         }
      }
      if(unit.isMedalRecordDataChanged()){
         sql.append(",`MEDAL_RECORD_DATA`=");
         if(RString.isEmpty(unit.medalRecordData())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.medalRecordData()));
            sql.append("'");
         }
      }
      if(unit.isBuffSetChanged()){
         sql.append(",`BUFF_SET`=");
         if(RString.isEmpty(unit.buffSet())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.buffSet()));
            sql.append("'");
         }
      }
      if(unit.isFourGodBloodChanged()){
         sql.append(",`FOUR_GOD_BLOOD`=");
         if(RString.isEmpty(unit.fourGodBlood())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.fourGodBlood()));
            sql.append("'");
         }
      }
      if(unit.isPropLimitChanged()){
         sql.append(",`PROP_LIMIT`=");
         if(RString.isEmpty(unit.propLimit())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.propLimit()));
            sql.append("'");
         }
      }
      if(unit.isStyleActionSetChanged()){
         sql.append(",`STYLE_ACTION_SET`=");
         if(RString.isEmpty(unit.styleActionSet())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.styleActionSet()));
            sql.append("'");
         }
      }
      if(unit.isUniverseTargetChanged()){
         sql.append(",`UNIVERSE_TARGET`=");
         if(RString.isEmpty(unit.universeTarget())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.universeTarget()));
            sql.append("'");
         }
      }
      if(unit.isApplyListChanged()){
         sql.append(",`APPLY_LIST`=");
         if(RString.isEmpty(unit.applyList())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.applyList()));
            sql.append("'");
         }
      }
      if(unit.isSpendDataChanged()){
         sql.append(",`SPEND_DATA`=");
         if(RString.isEmpty(unit.spendData())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.spendData()));
            sql.append("'");
         }
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