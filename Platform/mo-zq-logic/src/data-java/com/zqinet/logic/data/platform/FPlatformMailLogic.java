package com.zqinet.logic.data.platform;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>系统邮件逻辑。</T>
//============================================================
public class FPlatformMailLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("PF_MAIL");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段角色的个数的定义。
   public final static FLogicField FieldRoleCount = new FLogicField("ROLE_COUNT");

   // 字段收件人角色姓名的定义。
   public final static FLogicField FieldRoleLabel = new FLogicField("ROLE_LABEL");

   // 字段性别的定义。
   public final static FLogicField FieldGender = new FLogicField("GENDER");

   // 字段玩家最小等级的定义。
   public final static FLogicField FieldRoleMixLevel = new FLogicField("ROLE_MIX_LEVEL");

   // 字段玩家最大等级的定义。
   public final static FLogicField FieldRoleMaxLevel = new FLogicField("ROLE_MAX_LEVEL");

   // 字段门派编号的定义。
   public final static FLogicField FieldMetierId = new FLogicField("METIER_ID");

   // 字段主题的定义。
   public final static FLogicField FieldTopic = new FLogicField("TOPIC");

   // 字段内容的定义。
   public final static FLogicField FieldContent = new FLogicField("CONTENT");

   // 字段发送时间的定义。
   public final static FLogicField FieldSendTime = new FLogicField("SEND_TIME");

   // 字段变更时间的定义。
   public final static FLogicField FieldModifyTime = new FLogicField("MODIFY_TIME");

   // 字段是否已发送的定义。
   public final static FLogicField FieldIsSend = new FLogicField("IS_SEND");

   // 字段是否有附件的定义。
   public final static FLogicField FieldIsAttachment = new FLogicField("IS_ATTACHMENT");

   // 字段过期时间的定义。
   public final static FLogicField FieldOverTime = new FLogicField("OVER_TIME");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造系统邮件逻辑单元。</T>
   //============================================================
   public FPlatformMailLogic(){
   }

   //============================================================
   // <T>构造系统邮件逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FPlatformMailLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FPlatformMailUnit find(long recordId){
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
      FPlatformMailUnit unit = new FPlatformMailUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FPlatformMailUnit serach(String whereSql){
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
      FPlatformMailUnit unit = new FPlatformMailUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FPlatformMailUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FPlatformMailUnit[] fetch(String whereSql, String orderSql){
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
   public FPlatformMailUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FPlatformMailUnit[] units = new FPlatformMailUnit[count];
      for(int n = 0; n < count; n++){
         FPlatformMailUnit unit = new FPlatformMailUnit();
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
   public FPlatformMailUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FPlatformMailUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`ROLE_COUNT`");
      sql.append(",`ROLE_LABEL`");
      sql.append(",`GENDER`");
      sql.append(",`ROLE_MIX_LEVEL`");
      sql.append(",`ROLE_MAX_LEVEL`");
      sql.append(",`METIER_ID`");
      sql.append(",`TOPIC`");
      sql.append(",`CONTENT`");
      sql.append(",`SEND_TIME`");
      sql.append(",`MODIFY_TIME`");
      sql.append(",`IS_SEND`");
      sql.append(",`IS_ATTACHMENT`");
      sql.append(",`OVER_TIME`");
      sql.append(",`CREATE_USER_ID`");
      sql.append(",`CREATE_DATE`");
      sql.append(",`UPDATE_USER_ID`");
      sql.append(",`UPDATE_DATE`");
      sql.append(") VALUES(");
      sql.append(unit.ovld());
      sql.append(',');
      sql.append(unit.uniqueId());
      sql.append(',');
      sql.append(unit.roleCount());
      sql.append(',');
      if(RString.isEmpty(unit.roleLabel())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.roleLabel()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.gender());
      sql.append(',');
      sql.append(unit.roleMixLevel());
      sql.append(',');
      sql.append(unit.roleMaxLevel());
      sql.append(',');
      sql.append(unit.metierId());
      sql.append(',');
      if(RString.isEmpty(unit.topic())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.topic()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.content())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.content()));
         sql.append('\'');
      }
      sql.append(',');
      if(unit.sendTime().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.sendTime().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      if(unit.modifyTime().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.modifyTime().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      sql.append(unit.isSend());
      sql.append(',');
      sql.append(unit.isAttachment());
      sql.append(',');
      if(unit.overTime().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.overTime().format());
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
   public boolean doUpdate(FPlatformMailUnit unit, long recordId){
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
      if(unit.isRoleCountChanged()){
         sql.append(",`ROLE_COUNT`=");
         sql.append(unit.roleCount());
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
      if(unit.isGenderChanged()){
         sql.append(",`GENDER`=");
         sql.append(unit.gender());
      }
      if(unit.isRoleMixLevelChanged()){
         sql.append(",`ROLE_MIX_LEVEL`=");
         sql.append(unit.roleMixLevel());
      }
      if(unit.isRoleMaxLevelChanged()){
         sql.append(",`ROLE_MAX_LEVEL`=");
         sql.append(unit.roleMaxLevel());
      }
      if(unit.isMetierIdChanged()){
         sql.append(",`METIER_ID`=");
         sql.append(unit.metierId());
      }
      if(unit.isTopicChanged()){
         sql.append(",`TOPIC`=");
         if(RString.isEmpty(unit.topic())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.topic()));
            sql.append("'");
         }
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
      if(unit.isModifyTimeChanged()){
         sql.append(",`MODIFY_TIME`=");
         if(unit.modifyTime().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.modifyTime().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isIsSendChanged()){
         sql.append(",`IS_SEND`=");
         sql.append(unit.isSend());
      }
      if(unit.isIsAttachmentChanged()){
         sql.append(",`IS_ATTACHMENT`=");
         sql.append(unit.isAttachment());
      }
      if(unit.isOverTimeChanged()){
         sql.append(",`OVER_TIME`=");
         if(unit.overTime().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.overTime().format());
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