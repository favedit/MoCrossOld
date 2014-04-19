package com.zqinet.logic.data.logger;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>角色邮件日志逻辑。</T>
//============================================================
public class FLoggerMailLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("LOG_MAIL");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段邮件标题的定义。
   public final static FLogicField FieldTitle = new FLogicField("TITLE");

   // 字段邮件内容的定义。
   public final static FLogicField FieldContent = new FLogicField("CONTENT");

   // 字段附件中的货币类型的定义。
   public final static FLogicField FieldCurrencyCd = new FLogicField("CURRENCY_CD");

   // 字段附件中的货币数量的定义。
   public final static FLogicField FieldCurrencyOunt = new FLogicField("CURRENCY_OUNT");

   // 字段发送者编号的定义。
   public final static FLogicField FieldSenderId = new FLogicField("SENDER_ID");

   // 字段接收者编号的定义。
   public final static FLogicField FieldReceiverId = new FLogicField("RECEIVER_ID");

   // 字段附件中的帮贡数的定义。
   public final static FLogicField FieldTribute = new FLogicField("TRIBUTE");

   // 字段附件中的门贡数量的定义。
   public final static FLogicField FieldSectTribute = new FLogicField("SECT_TRIBUTE");

   // 字段附件中的斗法积分的定义。
   public final static FLogicField FieldTournamentIntegral = new FLogicField("TOURNAMENT_INTEGRAL");

   // 字段附件中的逐鹿积分的定义。
   public final static FLogicField FieldSocietyTournamentIntegral = new FLogicField("SOCIETY_TOURNAMENT_INTEGRAL");

   // 字段经验的定义。
   public final static FLogicField FieldExperience = new FLogicField("EXPERIENCE");

   // 字段真元的定义。
   public final static FLogicField FieldCrystal = new FLogicField("CRYSTAL");

   // 字段物品类型的定义。
   public final static FLogicField FieldItemCd = new FLogicField("ITEM_CD");

   // 字段物品编号的定义。
   public final static FLogicField FieldItemTid = new FLogicField("ITEM_TID");

   // 字段物品品质的定义。
   public final static FLogicField FieldItemQuality = new FLogicField("ITEM_QUALITY");

   // 字段物品数量的定义。
   public final static FLogicField FieldItemCount = new FLogicField("ITEM_COUNT");

   // 字段邮件类型的定义。
   public final static FLogicField FieldMailType = new FLogicField("MAIL_TYPE");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造角色邮件日志逻辑单元。</T>
   //============================================================
   public FLoggerMailLogic(){
   }

   //============================================================
   // <T>构造角色邮件日志逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FLoggerMailLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FLoggerMailUnit find(long recordId){
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
      FLoggerMailUnit unit = new FLoggerMailUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FLoggerMailUnit serach(String whereSql){
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
      FLoggerMailUnit unit = new FLoggerMailUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FLoggerMailUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FLoggerMailUnit[] fetch(String whereSql, String orderSql){
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
   public FLoggerMailUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FLoggerMailUnit[] units = new FLoggerMailUnit[count];
      for(int n = 0; n < count; n++){
         FLoggerMailUnit unit = new FLoggerMailUnit();
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
   public FLoggerMailUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FLoggerMailUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`TITLE`");
      sql.append(",`CONTENT`");
      sql.append(",`CURRENCY_CD`");
      sql.append(",`CURRENCY_OUNT`");
      sql.append(",`SENDER_ID`");
      sql.append(",`RECEIVER_ID`");
      sql.append(",`TRIBUTE`");
      sql.append(",`SECT_TRIBUTE`");
      sql.append(",`TOURNAMENT_INTEGRAL`");
      sql.append(",`SOCIETY_TOURNAMENT_INTEGRAL`");
      sql.append(",`EXPERIENCE`");
      sql.append(",`CRYSTAL`");
      sql.append(",`ITEM_CD`");
      sql.append(",`ITEM_TID`");
      sql.append(",`ITEM_QUALITY`");
      sql.append(",`ITEM_COUNT`");
      sql.append(",`MAIL_TYPE`");
      sql.append(",`CREATE_USER_ID`");
      sql.append(",`CREATE_DATE`");
      sql.append(",`UPDATE_USER_ID`");
      sql.append(",`UPDATE_DATE`");
      sql.append(") VALUES(");
      sql.append(unit.ovld());
      sql.append(',');
      sql.append(unit.uniqueId());
      sql.append(',');
      if(RString.isEmpty(unit.title())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.title()));
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
      sql.append(unit.currencyCd());
      sql.append(',');
      sql.append(unit.currencyOunt());
      sql.append(',');
      sql.append(unit.senderId());
      sql.append(',');
      sql.append(unit.receiverId());
      sql.append(',');
      sql.append(unit.tribute());
      sql.append(',');
      sql.append(unit.sectTribute());
      sql.append(',');
      sql.append(unit.tournamentIntegral());
      sql.append(',');
      sql.append(unit.societyTournamentIntegral());
      sql.append(',');
      sql.append(unit.experience());
      sql.append(',');
      sql.append(unit.crystal());
      sql.append(',');
      sql.append(unit.itemCd());
      sql.append(',');
      sql.append(unit.itemTid());
      sql.append(',');
      sql.append(unit.itemQuality());
      sql.append(',');
      sql.append(unit.itemCount());
      sql.append(',');
      sql.append(unit.mailType());
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
   public boolean doUpdate(FLoggerMailUnit unit, long recordId){
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
      if(unit.isTitleChanged()){
         sql.append(",`TITLE`=");
         if(RString.isEmpty(unit.title())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.title()));
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
      if(unit.isCurrencyCdChanged()){
         sql.append(",`CURRENCY_CD`=");
         sql.append(unit.currencyCd());
      }
      if(unit.isCurrencyOuntChanged()){
         sql.append(",`CURRENCY_OUNT`=");
         sql.append(unit.currencyOunt());
      }
      if(unit.isSenderIdChanged()){
         sql.append(",`SENDER_ID`=");
         sql.append(unit.senderId());
      }
      if(unit.isReceiverIdChanged()){
         sql.append(",`RECEIVER_ID`=");
         sql.append(unit.receiverId());
      }
      if(unit.isTributeChanged()){
         sql.append(",`TRIBUTE`=");
         sql.append(unit.tribute());
      }
      if(unit.isSectTributeChanged()){
         sql.append(",`SECT_TRIBUTE`=");
         sql.append(unit.sectTribute());
      }
      if(unit.isTournamentIntegralChanged()){
         sql.append(",`TOURNAMENT_INTEGRAL`=");
         sql.append(unit.tournamentIntegral());
      }
      if(unit.isSocietyTournamentIntegralChanged()){
         sql.append(",`SOCIETY_TOURNAMENT_INTEGRAL`=");
         sql.append(unit.societyTournamentIntegral());
      }
      if(unit.isExperienceChanged()){
         sql.append(",`EXPERIENCE`=");
         sql.append(unit.experience());
      }
      if(unit.isCrystalChanged()){
         sql.append(",`CRYSTAL`=");
         sql.append(unit.crystal());
      }
      if(unit.isItemCdChanged()){
         sql.append(",`ITEM_CD`=");
         sql.append(unit.itemCd());
      }
      if(unit.isItemTidChanged()){
         sql.append(",`ITEM_TID`=");
         sql.append(unit.itemTid());
      }
      if(unit.isItemQualityChanged()){
         sql.append(",`ITEM_QUALITY`=");
         sql.append(unit.itemQuality());
      }
      if(unit.isItemCountChanged()){
         sql.append(",`ITEM_COUNT`=");
         sql.append(unit.itemCount());
      }
      if(unit.isMailTypeChanged()){
         sql.append(",`MAIL_TYPE`=");
         sql.append(unit.mailType());
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