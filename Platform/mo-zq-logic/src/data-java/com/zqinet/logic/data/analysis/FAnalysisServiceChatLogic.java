package com.zqinet.logic.data.analysis;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>日志聊天分析逻辑。</T>
//============================================================
public class FAnalysisServiceChatLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("AS_SVC_CHAT");

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

   // 字段发送时间的定义。
   public final static FLogicField FieldSendTime = new FLogicField("SEND_TIME");

   // 字段帐号编号的定义。
   public final static FLogicField FieldUserId = new FLogicField("USER_ID");

   // 字段角色编号的定义。
   public final static FLogicField FieldRoleId = new FLogicField("ROLE_ID");

   // 字段角色名称的定义。
   public final static FLogicField FieldRoleLabel = new FLogicField("ROLE_LABEL");

   // 字段说话类型的定义。
   public final static FLogicField FieldChatType = new FLogicField("CHAT_TYPE");

   // 字段聊天信息的定义。
   public final static FLogicField FieldChatMessage = new FLogicField("CHAT_MESSAGE");

   // 字段重置次数的定义。
   public final static FLogicField FieldChatRepeat = new FLogicField("CHAT_REPEAT");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造日志聊天分析逻辑单元。</T>
   //============================================================
   public FAnalysisServiceChatLogic(){
   }

   //============================================================
   // <T>构造日志聊天分析逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FAnalysisServiceChatLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FAnalysisServiceChatUnit find(long recordId){
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
      FAnalysisServiceChatUnit unit = new FAnalysisServiceChatUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FAnalysisServiceChatUnit serach(String whereSql){
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
      FAnalysisServiceChatUnit unit = new FAnalysisServiceChatUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FAnalysisServiceChatUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FAnalysisServiceChatUnit[] fetch(String whereSql, String orderSql){
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
   public FAnalysisServiceChatUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FAnalysisServiceChatUnit[] units = new FAnalysisServiceChatUnit[count];
      for(int n = 0; n < count; n++){
         FAnalysisServiceChatUnit unit = new FAnalysisServiceChatUnit();
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
   public FAnalysisServiceChatUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FAnalysisServiceChatUnit unit){
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
      sql.append(",`SEND_TIME`");
      sql.append(",`USER_ID`");
      sql.append(",`ROLE_ID`");
      sql.append(",`ROLE_LABEL`");
      sql.append(",`CHAT_TYPE`");
      sql.append(",`CHAT_MESSAGE`");
      sql.append(",`CHAT_REPEAT`");
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
      if(unit.sendTime().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.sendTime().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      if(RString.isEmpty(unit.userId())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.userId()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.roleId());
      sql.append(',');
      if(RString.isEmpty(unit.roleLabel())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.roleLabel()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.chatType());
      sql.append(',');
      if(RString.isEmpty(unit.chatMessage())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.chatMessage()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.chatRepeat());
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
   public boolean doUpdate(FAnalysisServiceChatUnit unit, long recordId){
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
      if(unit.isSendTimeChanged()){
         sql.append(",`SEND_TIME`=");
         if(unit.sendTime().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.sendTime().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isUserIdChanged()){
         sql.append(",`USER_ID`=");
         if(RString.isEmpty(unit.userId())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.userId()));
            sql.append("'");
         }
      }
      if(unit.isRoleIdChanged()){
         sql.append(",`ROLE_ID`=");
         sql.append(unit.roleId());
      }
      if(unit.isRoleLabelChanged()){
         sql.append(",`ROLE_LABEL`=");
         if(RString.isEmpty(unit.roleLabel())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.roleLabel()));
            sql.append("'");
         }
      }
      if(unit.isChatTypeChanged()){
         sql.append(",`CHAT_TYPE`=");
         sql.append(unit.chatType());
      }
      if(unit.isChatMessageChanged()){
         sql.append(",`CHAT_MESSAGE`=");
         if(RString.isEmpty(unit.chatMessage())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.chatMessage()));
            sql.append("'");
         }
      }
      if(unit.isChatRepeatChanged()){
         sql.append(",`CHAT_REPEAT`=");
         sql.append(unit.chatRepeat());
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