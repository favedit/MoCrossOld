package com.zqinet.logic.data.platform;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>聊天记录逻辑。</T>
//============================================================
public class FPlatformChatRecordLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("PF_CHAT_RECORD");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段账号ID的定义。
   public final static FLogicField FieldAccountId = new FLogicField("ACCOUNT_ID");

   // 字段角色编号的定义。
   public final static FLogicField FieldRoleId = new FLogicField("ROLE_ID");

   // 字段角色名称的定义。
   public final static FLogicField FieldRoleLabel = new FLogicField("ROLE_LABEL");

   // 字段说话类型的定义。
   public final static FLogicField FieldTalkType = new FLogicField("TALK_TYPE");

   // 字段内容的定义。
   public final static FLogicField FieldContent = new FLogicField("CONTENT");

   // 字段状态的定义。
   public final static FLogicField FieldStatus = new FLogicField("STATUS");

   // 字段说话时间的定义。
   public final static FLogicField FieldTalkTime = new FLogicField("TALK_TIME");

   // 字段账号的定义。
   public final static FLogicField FieldPassport = new FLogicField("PASSPORT");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造聊天记录逻辑单元。</T>
   //============================================================
   public FPlatformChatRecordLogic(){
   }

   //============================================================
   // <T>构造聊天记录逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FPlatformChatRecordLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FPlatformChatRecordUnit find(long recordId){
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
      FPlatformChatRecordUnit unit = new FPlatformChatRecordUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FPlatformChatRecordUnit serach(String whereSql){
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
      FPlatformChatRecordUnit unit = new FPlatformChatRecordUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FPlatformChatRecordUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FPlatformChatRecordUnit[] fetch(String whereSql, String orderSql){
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
   public FPlatformChatRecordUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FPlatformChatRecordUnit[] units = new FPlatformChatRecordUnit[count];
      for(int n = 0; n < count; n++){
         FPlatformChatRecordUnit unit = new FPlatformChatRecordUnit();
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
   public FPlatformChatRecordUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FPlatformChatRecordUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`ACCOUNT_ID`");
      sql.append(",`ROLE_ID`");
      sql.append(",`ROLE_LABEL`");
      sql.append(",`TALK_TYPE`");
      sql.append(",`CONTENT`");
      sql.append(",`STATUS`");
      sql.append(",`TALK_TIME`");
      sql.append(",`PASSPORT`");
      sql.append(",`CREATE_USER_ID`");
      sql.append(",`CREATE_DATE`");
      sql.append(",`UPDATE_USER_ID`");
      sql.append(",`UPDATE_DATE`");
      sql.append(") VALUES(");
      sql.append(unit.ovld());
      sql.append(',');
      sql.append(unit.uniqueId());
      sql.append(',');
      sql.append(unit.accountId());
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
      sql.append(unit.talkType());
      sql.append(',');
      if(RString.isEmpty(unit.content())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.content()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.status());
      sql.append(',');
      if(unit.talkTime().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.talkTime().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      if(RString.isEmpty(unit.passport())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.passport()));
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
   public boolean doUpdate(FPlatformChatRecordUnit unit, long recordId){
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
      if(unit.isAccountIdChanged()){
         sql.append(",`ACCOUNT_ID`=");
         sql.append(unit.accountId());
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
      if(unit.isTalkTypeChanged()){
         sql.append(",`TALK_TYPE`=");
         sql.append(unit.talkType());
      }
      if(unit.isContentChanged()){
         sql.append(",`CONTENT`=");
         if(RString.isEmpty(unit.content())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.content()));
            sql.append("'");
         }
      }
      if(unit.isStatusChanged()){
         sql.append(",`STATUS`=");
         sql.append(unit.status());
      }
      if(unit.isTalkTimeChanged()){
         sql.append(",`TALK_TIME`=");
         if(unit.talkTime().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.talkTime().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isPassportChanged()){
         sql.append(",`PASSPORT`=");
         if(RString.isEmpty(unit.passport())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.passport()));
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