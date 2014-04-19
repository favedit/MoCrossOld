package com.zqinet.logic.data.analysis;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>日志分析金钱预警逻辑。</T>
//============================================================
public class FAnalysisServiceAlarmmLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("AS_SVC_ALARMM");

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

   // 字段用户编号的定义。
   public final static FLogicField FieldRoleId = new FLogicField("ROLE_ID");

   // 字段单人存量（元宝）的定义。
   public final static FLogicField FieldSingleBalanceMoney = new FLogicField("SINGLE_BALANCE_MONEY");

   // 字段单次获得数量（元宝）的定义。
   public final static FLogicField FieldSingleMaxCountMoney = new FLogicField("SINGLE_MAX_COUNT_MONEY");

   // 字段获得次数（元宝）的定义。
   public final static FLogicField FieldSumCountMoney = new FLogicField("SUM_COUNT_MONEY");

   // 字段单人存量（绑定元宝）的定义。
   public final static FLogicField FieldSingleBalanceBindMoney = new FLogicField("SINGLE_BALANCE_BIND_MONEY");

   // 字段单次获得数量（绑定元宝）的定义。
   public final static FLogicField FieldSingleMaxCountBindMoney = new FLogicField("SINGLE_MAX_COUNT_BIND_MONEY");

   // 字段获得次数（绑定元宝）的定义。
   public final static FLogicField FieldSumCountBindMoney = new FLogicField("SUM_COUNT_BIND_MONEY");

   // 字段单人存量（银两）的定义。
   public final static FLogicField FieldSingleBalanceGold = new FLogicField("SINGLE_BALANCE_GOLD");

   // 字段单次获得数量（银两）的定义。
   public final static FLogicField FieldSingleMaxCountGold = new FLogicField("SINGLE_MAX_COUNT_GOLD");

   // 字段获得次数（银两）的定义。
   public final static FLogicField FieldSumCountGold = new FLogicField("SUM_COUNT_GOLD");

   // 字段单人存量（绑定银两）的定义。
   public final static FLogicField FieldSingleBalanceBindGold = new FLogicField("SINGLE_BALANCE_BIND_GOLD");

   // 字段单次获得数量（绑定银两）的定义。
   public final static FLogicField FieldSingleMaxCountBindGold = new FLogicField("SINGLE_MAX_COUNT_BIND_GOLD");

   // 字段获得次数（绑定银两）的定义。
   public final static FLogicField FieldSumCountBindGold = new FLogicField("SUM_COUNT_BIND_GOLD");

   // 字段单人存量（斗法）的定义。
   public final static FLogicField FieldSingleBalanceTournament = new FLogicField("SINGLE_BALANCE_TOURNAMENT");

   // 字段单次获得数量（斗法）的定义。
   public final static FLogicField FieldSingleMaxCountTournament = new FLogicField("SINGLE_MAX_COUNT_TOURNAMENT");

   // 字段获得次数（斗法）的定义。
   public final static FLogicField FieldSumCountTournament = new FLogicField("SUM_COUNT_TOURNAMENT");

   // 字段单人存量（逐鹿）的定义。
   public final static FLogicField FieldSingleBalanceSociety = new FLogicField("SINGLE_BALANCE_SOCIETY");

   // 字段单次获得数量（逐鹿）的定义。
   public final static FLogicField FieldSingleMaxCountSociety = new FLogicField("SINGLE_MAX_COUNT_SOCIETY");

   // 字段获得次数（逐鹿）的定义。
   public final static FLogicField FieldSumCountSociety = new FLogicField("SUM_COUNT_SOCIETY");

   // 字段单人存量（门贡）的定义。
   public final static FLogicField FieldSingleBalanceMetier = new FLogicField("SINGLE_BALANCE_METIER");

   // 字段单人获得数量（门贡）的定义。
   public final static FLogicField FieldSingleMaxCountMetier = new FLogicField("SINGLE_MAX_COUNT_METIER");

   // 字段获得次数（门贡）的定义。
   public final static FLogicField FieldSumCountMetier = new FLogicField("SUM_COUNT_METIER");

   // 字段单人存量（帮贡）的定义。
   public final static FLogicField FieldSingleBalanceGang = new FLogicField("SINGLE_BALANCE_GANG");

   // 字段单人获得数量（帮贡）的定义。
   public final static FLogicField FieldSingleMaxCountGang = new FLogicField("SINGLE_MAX_COUNT_GANG");

   // 字段获得次数（帮贡）的定义。
   public final static FLogicField FieldSumCountGang = new FLogicField("SUM_COUNT_GANG");

   // 字段单人存量（经验）的定义。
   public final static FLogicField FieldSingleBalanceExp = new FLogicField("SINGLE_BALANCE_EXP");

   // 字段单人获得数量（经验）的定义。
   public final static FLogicField FieldSingleMaxCountExp = new FLogicField("SINGLE_MAX_COUNT_EXP");

   // 字段获得次数（经验）的定义。
   public final static FLogicField FieldSumCountExp = new FLogicField("SUM_COUNT_EXP");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造日志分析金钱预警逻辑单元。</T>
   //============================================================
   public FAnalysisServiceAlarmmLogic(){
   }

   //============================================================
   // <T>构造日志分析金钱预警逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FAnalysisServiceAlarmmLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FAnalysisServiceAlarmmUnit find(long recordId){
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
      FAnalysisServiceAlarmmUnit unit = new FAnalysisServiceAlarmmUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FAnalysisServiceAlarmmUnit serach(String whereSql){
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
      FAnalysisServiceAlarmmUnit unit = new FAnalysisServiceAlarmmUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FAnalysisServiceAlarmmUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FAnalysisServiceAlarmmUnit[] fetch(String whereSql, String orderSql){
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
   public FAnalysisServiceAlarmmUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FAnalysisServiceAlarmmUnit[] units = new FAnalysisServiceAlarmmUnit[count];
      for(int n = 0; n < count; n++){
         FAnalysisServiceAlarmmUnit unit = new FAnalysisServiceAlarmmUnit();
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
   public FAnalysisServiceAlarmmUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FAnalysisServiceAlarmmUnit unit){
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
      sql.append(",`SINGLE_BALANCE_MONEY`");
      sql.append(",`SINGLE_MAX_COUNT_MONEY`");
      sql.append(",`SUM_COUNT_MONEY`");
      sql.append(",`SINGLE_BALANCE_BIND_MONEY`");
      sql.append(",`SINGLE_MAX_COUNT_BIND_MONEY`");
      sql.append(",`SUM_COUNT_BIND_MONEY`");
      sql.append(",`SINGLE_BALANCE_GOLD`");
      sql.append(",`SINGLE_MAX_COUNT_GOLD`");
      sql.append(",`SUM_COUNT_GOLD`");
      sql.append(",`SINGLE_BALANCE_BIND_GOLD`");
      sql.append(",`SINGLE_MAX_COUNT_BIND_GOLD`");
      sql.append(",`SUM_COUNT_BIND_GOLD`");
      sql.append(",`SINGLE_BALANCE_TOURNAMENT`");
      sql.append(",`SINGLE_MAX_COUNT_TOURNAMENT`");
      sql.append(",`SUM_COUNT_TOURNAMENT`");
      sql.append(",`SINGLE_BALANCE_SOCIETY`");
      sql.append(",`SINGLE_MAX_COUNT_SOCIETY`");
      sql.append(",`SUM_COUNT_SOCIETY`");
      sql.append(",`SINGLE_BALANCE_METIER`");
      sql.append(",`SINGLE_MAX_COUNT_METIER`");
      sql.append(",`SUM_COUNT_METIER`");
      sql.append(",`SINGLE_BALANCE_GANG`");
      sql.append(",`SINGLE_MAX_COUNT_GANG`");
      sql.append(",`SUM_COUNT_GANG`");
      sql.append(",`SINGLE_BALANCE_EXP`");
      sql.append(",`SINGLE_MAX_COUNT_EXP`");
      sql.append(",`SUM_COUNT_EXP`");
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
      sql.append(unit.singleBalanceMoney());
      sql.append(',');
      sql.append(unit.singleMaxCountMoney());
      sql.append(',');
      sql.append(unit.sumCountMoney());
      sql.append(',');
      sql.append(unit.singleBalanceBindMoney());
      sql.append(',');
      sql.append(unit.singleMaxCountBindMoney());
      sql.append(',');
      sql.append(unit.sumCountBindMoney());
      sql.append(',');
      sql.append(unit.singleBalanceGold());
      sql.append(',');
      sql.append(unit.singleMaxCountGold());
      sql.append(',');
      sql.append(unit.sumCountGold());
      sql.append(',');
      sql.append(unit.singleBalanceBindGold());
      sql.append(',');
      sql.append(unit.singleMaxCountBindGold());
      sql.append(',');
      sql.append(unit.sumCountBindGold());
      sql.append(',');
      sql.append(unit.singleBalanceTournament());
      sql.append(',');
      sql.append(unit.singleMaxCountTournament());
      sql.append(',');
      sql.append(unit.sumCountTournament());
      sql.append(',');
      sql.append(unit.singleBalanceSociety());
      sql.append(',');
      sql.append(unit.singleMaxCountSociety());
      sql.append(',');
      sql.append(unit.sumCountSociety());
      sql.append(',');
      sql.append(unit.singleBalanceMetier());
      sql.append(',');
      sql.append(unit.singleMaxCountMetier());
      sql.append(',');
      sql.append(unit.sumCountMetier());
      sql.append(',');
      sql.append(unit.singleBalanceGang());
      sql.append(',');
      sql.append(unit.singleMaxCountGang());
      sql.append(',');
      sql.append(unit.sumCountGang());
      sql.append(',');
      sql.append(unit.singleBalanceExp());
      sql.append(',');
      sql.append(unit.singleMaxCountExp());
      sql.append(',');
      sql.append(unit.sumCountExp());
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
   public boolean doUpdate(FAnalysisServiceAlarmmUnit unit, long recordId){
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
      if(unit.isSingleBalanceMoneyChanged()){
         sql.append(",`SINGLE_BALANCE_MONEY`=");
         sql.append(unit.singleBalanceMoney());
      }
      if(unit.isSingleMaxCountMoneyChanged()){
         sql.append(",`SINGLE_MAX_COUNT_MONEY`=");
         sql.append(unit.singleMaxCountMoney());
      }
      if(unit.isSumCountMoneyChanged()){
         sql.append(",`SUM_COUNT_MONEY`=");
         sql.append(unit.sumCountMoney());
      }
      if(unit.isSingleBalanceBindMoneyChanged()){
         sql.append(",`SINGLE_BALANCE_BIND_MONEY`=");
         sql.append(unit.singleBalanceBindMoney());
      }
      if(unit.isSingleMaxCountBindMoneyChanged()){
         sql.append(",`SINGLE_MAX_COUNT_BIND_MONEY`=");
         sql.append(unit.singleMaxCountBindMoney());
      }
      if(unit.isSumCountBindMoneyChanged()){
         sql.append(",`SUM_COUNT_BIND_MONEY`=");
         sql.append(unit.sumCountBindMoney());
      }
      if(unit.isSingleBalanceGoldChanged()){
         sql.append(",`SINGLE_BALANCE_GOLD`=");
         sql.append(unit.singleBalanceGold());
      }
      if(unit.isSingleMaxCountGoldChanged()){
         sql.append(",`SINGLE_MAX_COUNT_GOLD`=");
         sql.append(unit.singleMaxCountGold());
      }
      if(unit.isSumCountGoldChanged()){
         sql.append(",`SUM_COUNT_GOLD`=");
         sql.append(unit.sumCountGold());
      }
      if(unit.isSingleBalanceBindGoldChanged()){
         sql.append(",`SINGLE_BALANCE_BIND_GOLD`=");
         sql.append(unit.singleBalanceBindGold());
      }
      if(unit.isSingleMaxCountBindGoldChanged()){
         sql.append(",`SINGLE_MAX_COUNT_BIND_GOLD`=");
         sql.append(unit.singleMaxCountBindGold());
      }
      if(unit.isSumCountBindGoldChanged()){
         sql.append(",`SUM_COUNT_BIND_GOLD`=");
         sql.append(unit.sumCountBindGold());
      }
      if(unit.isSingleBalanceTournamentChanged()){
         sql.append(",`SINGLE_BALANCE_TOURNAMENT`=");
         sql.append(unit.singleBalanceTournament());
      }
      if(unit.isSingleMaxCountTournamentChanged()){
         sql.append(",`SINGLE_MAX_COUNT_TOURNAMENT`=");
         sql.append(unit.singleMaxCountTournament());
      }
      if(unit.isSumCountTournamentChanged()){
         sql.append(",`SUM_COUNT_TOURNAMENT`=");
         sql.append(unit.sumCountTournament());
      }
      if(unit.isSingleBalanceSocietyChanged()){
         sql.append(",`SINGLE_BALANCE_SOCIETY`=");
         sql.append(unit.singleBalanceSociety());
      }
      if(unit.isSingleMaxCountSocietyChanged()){
         sql.append(",`SINGLE_MAX_COUNT_SOCIETY`=");
         sql.append(unit.singleMaxCountSociety());
      }
      if(unit.isSumCountSocietyChanged()){
         sql.append(",`SUM_COUNT_SOCIETY`=");
         sql.append(unit.sumCountSociety());
      }
      if(unit.isSingleBalanceMetierChanged()){
         sql.append(",`SINGLE_BALANCE_METIER`=");
         sql.append(unit.singleBalanceMetier());
      }
      if(unit.isSingleMaxCountMetierChanged()){
         sql.append(",`SINGLE_MAX_COUNT_METIER`=");
         sql.append(unit.singleMaxCountMetier());
      }
      if(unit.isSumCountMetierChanged()){
         sql.append(",`SUM_COUNT_METIER`=");
         sql.append(unit.sumCountMetier());
      }
      if(unit.isSingleBalanceGangChanged()){
         sql.append(",`SINGLE_BALANCE_GANG`=");
         sql.append(unit.singleBalanceGang());
      }
      if(unit.isSingleMaxCountGangChanged()){
         sql.append(",`SINGLE_MAX_COUNT_GANG`=");
         sql.append(unit.singleMaxCountGang());
      }
      if(unit.isSumCountGangChanged()){
         sql.append(",`SUM_COUNT_GANG`=");
         sql.append(unit.sumCountGang());
      }
      if(unit.isSingleBalanceExpChanged()){
         sql.append(",`SINGLE_BALANCE_EXP`=");
         sql.append(unit.singleBalanceExp());
      }
      if(unit.isSingleMaxCountExpChanged()){
         sql.append(",`SINGLE_MAX_COUNT_EXP`=");
         sql.append(unit.singleMaxCountExp());
      }
      if(unit.isSumCountExpChanged()){
         sql.append(",`SUM_COUNT_EXP`=");
         sql.append(unit.sumCountExp());
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