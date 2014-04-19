package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>邮件信息逻辑。</T>
//============================================================
public class FGameRoleMailLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("GM_ROLE_MAIL");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段收件人角色编号的定义。
   public final static FLogicField FieldRoleId = new FLogicField("ROLE_ID");

   // 字段收件人角色姓名的定义。
   public final static FLogicField FieldRoleLabel = new FLogicField("ROLE_LABEL");

   // 字段邮件类型的定义。
   public final static FLogicField FieldTypeCd = new FLogicField("TYPE_CD");

   // 字段是否阅读的定义。
   public final static FLogicField FieldIsRead = new FLogicField("IS_READ");

   // 字段是否有附件的定义。
   public final static FLogicField FieldIsAttachment = new FLogicField("IS_ATTACHMENT");

   // 字段发件人编号的定义。
   public final static FLogicField FieldSenderId = new FLogicField("SENDER_ID");

   // 字段发件人姓名的定义。
   public final static FLogicField FieldSenderLabel = new FLogicField("SENDER_LABEL");

   // 字段主题的定义。
   public final static FLogicField FieldTheme = new FLogicField("THEME");

   // 字段内容的定义。
   public final static FLogicField FieldContent = new FLogicField("CONTENT");

   // 字段物品编号的定义。
   public final static FLogicField FieldItemId = new FLogicField("ITEM_ID");

   // 字段物品类型的定义。
   public final static FLogicField FieldItemTypeCd = new FLogicField("ITEM_TYPE_CD");

   // 字段物品绑定类型的定义。
   public final static FLogicField FieldItemBindType = new FLogicField("ITEM_BIND_TYPE");

   // 字段物品叠加数量的定义。
   public final static FLogicField FieldItemCount = new FLogicField("ITEM_COUNT");

   // 字段品级模板编号的定义。
   public final static FLogicField FieldQualityTid = new FLogicField("QUALITY_TID");

   // 字段货币类型的定义。
   public final static FLogicField FieldCurrencyCd = new FLogicField("CURRENCY_CD");

   // 字段货币数量的定义。
   public final static FLogicField FieldCurrencyValue = new FLogicField("CURRENCY_VALUE");

   // 字段发送时间的定义。
   public final static FLogicField FieldSendTime = new FLogicField("SEND_TIME");

   // 字段过期时间的定义。
   public final static FLogicField FieldOverTime = new FLogicField("OVER_TIME");

   // 字段是否已删除的定义。
   public final static FLogicField FieldIsDelete = new FLogicField("IS_DELETE");

   // 字段可删除时间的定义。
   public final static FLogicField FieldCanDeleteTime = new FLogicField("CAN_DELETE_TIME");

   // 字段GM邮件编号的定义。
   public final static FLogicField FieldGmMailId = new FLogicField("GM_MAIL_ID");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造邮件信息逻辑单元。</T>
   //============================================================
   public FGameRoleMailLogic(){
   }

   //============================================================
   // <T>构造邮件信息逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FGameRoleMailLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FGameRoleMailUnit find(long recordId){
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
      FGameRoleMailUnit unit = new FGameRoleMailUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FGameRoleMailUnit serach(String whereSql){
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
      FGameRoleMailUnit unit = new FGameRoleMailUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FGameRoleMailUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FGameRoleMailUnit[] fetch(String whereSql, String orderSql){
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
   public FGameRoleMailUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FGameRoleMailUnit[] units = new FGameRoleMailUnit[count];
      for(int n = 0; n < count; n++){
         FGameRoleMailUnit unit = new FGameRoleMailUnit();
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
   public FGameRoleMailUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FGameRoleMailUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`ROLE_ID`");
      sql.append(",`ROLE_LABEL`");
      sql.append(",`TYPE_CD`");
      sql.append(",`IS_READ`");
      sql.append(",`IS_ATTACHMENT`");
      sql.append(",`SENDER_ID`");
      sql.append(",`SENDER_LABEL`");
      sql.append(",`THEME`");
      sql.append(",`CONTENT`");
      sql.append(",`ITEM_ID`");
      sql.append(",`ITEM_TYPE_CD`");
      sql.append(",`ITEM_BIND_TYPE`");
      sql.append(",`ITEM_COUNT`");
      sql.append(",`QUALITY_TID`");
      sql.append(",`CURRENCY_CD`");
      sql.append(",`CURRENCY_VALUE`");
      sql.append(",`SEND_TIME`");
      sql.append(",`OVER_TIME`");
      sql.append(",`IS_DELETE`");
      sql.append(",`CAN_DELETE_TIME`");
      sql.append(",`GM_MAIL_ID`");
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
      if(RString.isEmpty(unit.roleLabel())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.roleLabel()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.typeCd());
      sql.append(',');
      sql.append(unit.isRead());
      sql.append(',');
      sql.append(unit.isAttachment());
      sql.append(',');
      sql.append(unit.senderId());
      sql.append(',');
      if(RString.isEmpty(unit.senderLabel())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.senderLabel()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.theme())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.theme()));
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
      sql.append(unit.itemId());
      sql.append(',');
      sql.append(unit.itemTypeCd());
      sql.append(',');
      sql.append(unit.itemBindType());
      sql.append(',');
      sql.append(unit.itemCount());
      sql.append(',');
      sql.append(unit.qualityTid());
      sql.append(',');
      sql.append(unit.currencyCd());
      sql.append(',');
      sql.append(unit.currencyValue());
      sql.append(',');
      if(unit.sendTime().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.sendTime().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      if(unit.overTime().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.overTime().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      sql.append(unit.isDelete());
      sql.append(',');
      if(unit.canDeleteTime().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.canDeleteTime().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      sql.append(unit.gmMailId());
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
   public boolean doUpdate(FGameRoleMailUnit unit, long recordId){
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
      if(unit.isTypeCdChanged()){
         sql.append(",`TYPE_CD`=");
         sql.append(unit.typeCd());
      }
      if(unit.isIsReadChanged()){
         sql.append(",`IS_READ`=");
         sql.append(unit.isRead());
      }
      if(unit.isIsAttachmentChanged()){
         sql.append(",`IS_ATTACHMENT`=");
         sql.append(unit.isAttachment());
      }
      if(unit.isSenderIdChanged()){
         sql.append(",`SENDER_ID`=");
         sql.append(unit.senderId());
      }
      if(unit.isSenderLabelChanged()){
         sql.append(",`SENDER_LABEL`=");
         if(RString.isEmpty(unit.senderLabel())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.senderLabel()));
            sql.append("'");
         }
      }
      if(unit.isThemeChanged()){
         sql.append(",`THEME`=");
         if(RString.isEmpty(unit.theme())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.theme()));
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
      if(unit.isItemIdChanged()){
         sql.append(",`ITEM_ID`=");
         sql.append(unit.itemId());
      }
      if(unit.isItemTypeCdChanged()){
         sql.append(",`ITEM_TYPE_CD`=");
         sql.append(unit.itemTypeCd());
      }
      if(unit.isItemBindTypeChanged()){
         sql.append(",`ITEM_BIND_TYPE`=");
         sql.append(unit.itemBindType());
      }
      if(unit.isItemCountChanged()){
         sql.append(",`ITEM_COUNT`=");
         sql.append(unit.itemCount());
      }
      if(unit.isQualityTidChanged()){
         sql.append(",`QUALITY_TID`=");
         sql.append(unit.qualityTid());
      }
      if(unit.isCurrencyCdChanged()){
         sql.append(",`CURRENCY_CD`=");
         sql.append(unit.currencyCd());
      }
      if(unit.isCurrencyValueChanged()){
         sql.append(",`CURRENCY_VALUE`=");
         sql.append(unit.currencyValue());
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
      if(unit.isIsDeleteChanged()){
         sql.append(",`IS_DELETE`=");
         sql.append(unit.isDelete());
      }
      if(unit.isCanDeleteTimeChanged()){
         sql.append(",`CAN_DELETE_TIME`=");
         if(unit.canDeleteTime().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.canDeleteTime().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isGmMailIdChanged()){
         sql.append(",`GM_MAIL_ID`=");
         sql.append(unit.gmMailId());
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