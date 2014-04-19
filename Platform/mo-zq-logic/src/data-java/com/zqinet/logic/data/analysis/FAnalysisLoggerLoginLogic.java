package com.zqinet.logic.data.analysis;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>日志分析登录逻辑。</T>
//============================================================
public class FAnalysisLoggerLoginLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("AS_LOG_LOGIN");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段游戏ID的定义。
   public final static FLogicField FieldGameId = new FLogicField("GAME_ID");

   // 字段账号编号的定义。
   public final static FLogicField FieldAccountId = new FLogicField("ACCOUNT_ID");

   // 字段角色编号的定义。
   public final static FLogicField FieldRoleId = new FLogicField("ROLE_ID");

   // 字段记录时间的定义。
   public final static FLogicField FieldRecordDate = new FLogicField("RECORD_DATE");

   // 字段记录天的定义。
   public final static FLogicField FieldRecordDay = new FLogicField("RECORD_DAY");

   // 字段账号创建日期的定义。
   public final static FLogicField FieldAccountCreateDate = new FLogicField("ACCOUNT_CREATE_DATE");

   // 字段角色创建中日期的定义。
   public final static FLogicField FieldRoleCreatingDate = new FLogicField("ROLE_CREATING_DATE");

   // 字段角色创建日期的定义。
   public final static FLogicField FieldRoleCreateDate = new FLogicField("ROLE_CREATE_DATE");

   // 字段角色登录日期的定义。
   public final static FLogicField FieldRoleLoadDate = new FLogicField("ROLE_LOAD_DATE");

   // 字段登录日期的定义。
   public final static FLogicField FieldRoleLoginDate = new FLogicField("ROLE_LOGIN_DATE");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造日志分析登录逻辑单元。</T>
   //============================================================
   public FAnalysisLoggerLoginLogic(){
   }

   //============================================================
   // <T>构造日志分析登录逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FAnalysisLoggerLoginLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FAnalysisLoggerLoginUnit find(long recordId){
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
      FAnalysisLoggerLoginUnit unit = new FAnalysisLoggerLoginUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FAnalysisLoggerLoginUnit serach(String whereSql){
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
      FAnalysisLoggerLoginUnit unit = new FAnalysisLoggerLoginUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FAnalysisLoggerLoginUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FAnalysisLoggerLoginUnit[] fetch(String whereSql, String orderSql){
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
   public FAnalysisLoggerLoginUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FAnalysisLoggerLoginUnit[] units = new FAnalysisLoggerLoginUnit[count];
      for(int n = 0; n < count; n++){
         FAnalysisLoggerLoginUnit unit = new FAnalysisLoggerLoginUnit();
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
   public FAnalysisLoggerLoginUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FAnalysisLoggerLoginUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`GAME_ID`");
      sql.append(",`ACCOUNT_ID`");
      sql.append(",`ROLE_ID`");
      sql.append(",`RECORD_DATE`");
      sql.append(",`RECORD_DAY`");
      sql.append(",`ACCOUNT_CREATE_DATE`");
      sql.append(",`ROLE_CREATING_DATE`");
      sql.append(",`ROLE_CREATE_DATE`");
      sql.append(",`ROLE_LOAD_DATE`");
      sql.append(",`ROLE_LOGIN_DATE`");
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
      sql.append(unit.accountId());
      sql.append(',');
      sql.append(unit.roleId());
      sql.append(',');
      if(unit.recordDate().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.recordDate().format());
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
      if(unit.accountCreateDate().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.accountCreateDate().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      if(unit.roleCreatingDate().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.roleCreatingDate().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      if(unit.roleCreateDate().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.roleCreateDate().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      if(unit.roleLoadDate().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.roleLoadDate().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      if(unit.roleLoginDate().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.roleLoginDate().format());
         sql.append("','%Y%m%d%H%i%s')");
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
   public boolean doUpdate(FAnalysisLoggerLoginUnit unit, long recordId){
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
      if(unit.isAccountIdChanged()){
         sql.append(",`ACCOUNT_ID`=");
         sql.append(unit.accountId());
      }
      if(unit.isRoleIdChanged()){
         sql.append(",`ROLE_ID`=");
         sql.append(unit.roleId());
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
      if(unit.isAccountCreateDateChanged()){
         sql.append(",`ACCOUNT_CREATE_DATE`=");
         if(unit.accountCreateDate().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.accountCreateDate().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isRoleCreatingDateChanged()){
         sql.append(",`ROLE_CREATING_DATE`=");
         if(unit.roleCreatingDate().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.roleCreatingDate().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isRoleCreateDateChanged()){
         sql.append(",`ROLE_CREATE_DATE`=");
         if(unit.roleCreateDate().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.roleCreateDate().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isRoleLoadDateChanged()){
         sql.append(",`ROLE_LOAD_DATE`=");
         if(unit.roleLoadDate().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.roleLoadDate().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isRoleLoginDateChanged()){
         sql.append(",`ROLE_LOGIN_DATE`=");
         if(unit.roleLoginDate().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.roleLoginDate().format());
            sql.append("','%Y%m%d%H%i%s')");
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