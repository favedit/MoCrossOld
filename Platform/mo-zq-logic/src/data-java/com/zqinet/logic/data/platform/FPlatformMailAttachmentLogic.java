package com.zqinet.logic.data.platform;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>系统邮件附件逻辑。</T>
//============================================================
public class FPlatformMailAttachmentLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("PF_MAIL_ATTACHMENT");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段邮件编号的定义。
   public final static FLogicField FieldMailId = new FLogicField("MAIL_ID");

   // 字段物品模板编号的定义。
   public final static FLogicField FieldItemTid = new FLogicField("ITEM_TID");

   // 字段物品类型的定义。
   public final static FLogicField FieldItemTypeCd = new FLogicField("ITEM_TYPE_CD");

   // 字段物品绑定类型的定义。
   public final static FLogicField FieldItemBindType = new FLogicField("ITEM_BIND_TYPE");

   // 字段物品叠加数量的定义。
   public final static FLogicField FieldItemCount = new FLogicField("ITEM_COUNT");

   // 字段品级模板编号的定义。
   public final static FLogicField FieldQualityTid = new FLogicField("QUALITY_TID");

   // 字段邮件货币类型的定义。
   public final static FLogicField FieldMailCurrencyCd = new FLogicField("MAIL_CURRENCY_CD");

   // 字段邮件货币数值的定义。
   public final static FLogicField FieldMailCurrencyValue = new FLogicField("MAIL_CURRENCY_VALUE");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造系统邮件附件逻辑单元。</T>
   //============================================================
   public FPlatformMailAttachmentLogic(){
   }

   //============================================================
   // <T>构造系统邮件附件逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FPlatformMailAttachmentLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FPlatformMailAttachmentUnit find(long recordId){
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
      FPlatformMailAttachmentUnit unit = new FPlatformMailAttachmentUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FPlatformMailAttachmentUnit serach(String whereSql){
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
      FPlatformMailAttachmentUnit unit = new FPlatformMailAttachmentUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FPlatformMailAttachmentUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FPlatformMailAttachmentUnit[] fetch(String whereSql, String orderSql){
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
   public FPlatformMailAttachmentUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FPlatformMailAttachmentUnit[] units = new FPlatformMailAttachmentUnit[count];
      for(int n = 0; n < count; n++){
         FPlatformMailAttachmentUnit unit = new FPlatformMailAttachmentUnit();
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
   public FPlatformMailAttachmentUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FPlatformMailAttachmentUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`MAIL_ID`");
      sql.append(",`ITEM_TID`");
      sql.append(",`ITEM_TYPE_CD`");
      sql.append(",`ITEM_BIND_TYPE`");
      sql.append(",`ITEM_COUNT`");
      sql.append(",`QUALITY_TID`");
      sql.append(",`MAIL_CURRENCY_CD`");
      sql.append(",`MAIL_CURRENCY_VALUE`");
      sql.append(",`CREATE_USER_ID`");
      sql.append(",`CREATE_DATE`");
      sql.append(",`UPDATE_USER_ID`");
      sql.append(",`UPDATE_DATE`");
      sql.append(") VALUES(");
      sql.append(unit.ovld());
      sql.append(',');
      sql.append(unit.uniqueId());
      sql.append(',');
      sql.append(unit.mailId());
      sql.append(',');
      sql.append(unit.itemTid());
      sql.append(',');
      sql.append(unit.itemTypeCd());
      sql.append(',');
      sql.append(unit.itemBindType());
      sql.append(',');
      sql.append(unit.itemCount());
      sql.append(',');
      sql.append(unit.qualityTid());
      sql.append(',');
      sql.append(unit.mailCurrencyCd());
      sql.append(',');
      sql.append(unit.mailCurrencyValue());
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
   public boolean doUpdate(FPlatformMailAttachmentUnit unit, long recordId){
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
      if(unit.isMailIdChanged()){
         sql.append(",`MAIL_ID`=");
         sql.append(unit.mailId());
      }
      if(unit.isItemTidChanged()){
         sql.append(",`ITEM_TID`=");
         sql.append(unit.itemTid());
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
      if(unit.isMailCurrencyCdChanged()){
         sql.append(",`MAIL_CURRENCY_CD`=");
         sql.append(unit.mailCurrencyCd());
      }
      if(unit.isMailCurrencyValueChanged()){
         sql.append(",`MAIL_CURRENCY_VALUE`=");
         sql.append(unit.mailCurrencyValue());
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