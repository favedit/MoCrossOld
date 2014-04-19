package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>交易行物品信息逻辑。</T>
//============================================================
public class FGameAuctionItemLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("GM_AUCTION_ITEM");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段归属角色编号的定义。
   public final static FLogicField FieldBelongRoleId = new FLogicField("BELONG_ROLE_ID");

   // 字段拍卖状态的定义。
   public final static FLogicField FieldAuctionStatus = new FLogicField("AUCTION_STATUS");

   // 字段物品类型的定义。
   public final static FLogicField FieldTypeCd = new FLogicField("TYPE_CD");

   // 字段物品编号的定义。
   public final static FLogicField FieldItemId = new FLogicField("ITEM_ID");

   // 字段出售金钱的定义。
   public final static FLogicField FieldSellCurrency = new FLogicField("SELL_CURRENCY");

   // 字段出售价格的定义。
   public final static FLogicField FieldCurrency = new FLogicField("CURRENCY");

   // 字段出售开始时间的定义。
   public final static FLogicField FieldSellBeginDate = new FLogicField("SELL_BEGIN_DATE");

   // 字段拍卖时间的定义。
   public final static FLogicField FieldAuctionTime = new FLogicField("AUCTION_TIME");

   // 字段出售类型的定义。
   public final static FLogicField FieldSellType = new FLogicField("SELL_TYPE");

   // 字段查询大类的定义。
   public final static FLogicField FieldSumType = new FLogicField("SUM_TYPE");

   // 字段查询小类的定义。
   public final static FLogicField FieldDetailType = new FLogicField("DETAIL_TYPE");

   // 字段品级的定义。
   public final static FLogicField FieldQuality = new FLogicField("QUALITY");

   // 字段等级的定义。
   public final static FLogicField FieldLevel = new FLogicField("LEVEL");

   // 字段物品模版编号的定义。
   public final static FLogicField FieldItemTid = new FLogicField("ITEM_TID");

   // 字段数量的定义。
   public final static FLogicField FieldCount = new FLogicField("COUNT");

   // 字段状态的定义。
   public final static FLogicField FieldStatus = new FLogicField("STATUS");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造交易行物品信息逻辑单元。</T>
   //============================================================
   public FGameAuctionItemLogic(){
   }

   //============================================================
   // <T>构造交易行物品信息逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FGameAuctionItemLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FGameAuctionItemUnit find(long recordId){
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
      FGameAuctionItemUnit unit = new FGameAuctionItemUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FGameAuctionItemUnit serach(String whereSql){
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
      FGameAuctionItemUnit unit = new FGameAuctionItemUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FGameAuctionItemUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FGameAuctionItemUnit[] fetch(String whereSql, String orderSql){
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
   public FGameAuctionItemUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FGameAuctionItemUnit[] units = new FGameAuctionItemUnit[count];
      for(int n = 0; n < count; n++){
         FGameAuctionItemUnit unit = new FGameAuctionItemUnit();
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
   public FGameAuctionItemUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FGameAuctionItemUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`BELONG_ROLE_ID`");
      sql.append(",`AUCTION_STATUS`");
      sql.append(",`TYPE_CD`");
      sql.append(",`ITEM_ID`");
      sql.append(",`SELL_CURRENCY`");
      sql.append(",`CURRENCY`");
      sql.append(",`SELL_BEGIN_DATE`");
      sql.append(",`AUCTION_TIME`");
      sql.append(",`SELL_TYPE`");
      sql.append(",`SUM_TYPE`");
      sql.append(",`DETAIL_TYPE`");
      sql.append(",`QUALITY`");
      sql.append(",`LEVEL`");
      sql.append(",`ITEM_TID`");
      sql.append(",`COUNT`");
      sql.append(",`STATUS`");
      sql.append(",`CREATE_USER_ID`");
      sql.append(",`CREATE_DATE`");
      sql.append(",`UPDATE_USER_ID`");
      sql.append(",`UPDATE_DATE`");
      sql.append(") VALUES(");
      sql.append(unit.ovld());
      sql.append(',');
      sql.append(unit.uniqueId());
      sql.append(',');
      sql.append(unit.belongRoleId());
      sql.append(',');
      sql.append(unit.auctionStatus());
      sql.append(',');
      sql.append(unit.typeCd());
      sql.append(',');
      sql.append(unit.itemId());
      sql.append(',');
      if(RString.isEmpty(unit.sellCurrency())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.sellCurrency()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.currency())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.currency()));
         sql.append('\'');
      }
      sql.append(',');
      if(unit.sellBeginDate().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.sellBeginDate().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      sql.append(unit.auctionTime());
      sql.append(',');
      sql.append(unit.sellType());
      sql.append(',');
      sql.append(unit.sumType());
      sql.append(',');
      sql.append(unit.detailType());
      sql.append(',');
      sql.append(unit.quality());
      sql.append(',');
      sql.append(unit.level());
      sql.append(',');
      sql.append(unit.itemTid());
      sql.append(',');
      sql.append(unit.count());
      sql.append(',');
      sql.append(unit.status());
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
   public boolean doUpdate(FGameAuctionItemUnit unit, long recordId){
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
      if(unit.isBelongRoleIdChanged()){
         sql.append(",`BELONG_ROLE_ID`=");
         sql.append(unit.belongRoleId());
      }
      if(unit.isAuctionStatusChanged()){
         sql.append(",`AUCTION_STATUS`=");
         sql.append(unit.auctionStatus());
      }
      if(unit.isTypeCdChanged()){
         sql.append(",`TYPE_CD`=");
         sql.append(unit.typeCd());
      }
      if(unit.isItemIdChanged()){
         sql.append(",`ITEM_ID`=");
         sql.append(unit.itemId());
      }
      if(unit.isSellCurrencyChanged()){
         sql.append(",`SELL_CURRENCY`=");
         if(RString.isEmpty(unit.sellCurrency())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.sellCurrency()));
            sql.append("'");
         }
      }
      if(unit.isCurrencyChanged()){
         sql.append(",`CURRENCY`=");
         if(RString.isEmpty(unit.currency())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.currency()));
            sql.append("'");
         }
      }
      if(unit.isSellBeginDateChanged()){
         sql.append(",`SELL_BEGIN_DATE`=");
         if(unit.sellBeginDate().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.sellBeginDate().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isAuctionTimeChanged()){
         sql.append(",`AUCTION_TIME`=");
         sql.append(unit.auctionTime());
      }
      if(unit.isSellTypeChanged()){
         sql.append(",`SELL_TYPE`=");
         sql.append(unit.sellType());
      }
      if(unit.isSumTypeChanged()){
         sql.append(",`SUM_TYPE`=");
         sql.append(unit.sumType());
      }
      if(unit.isDetailTypeChanged()){
         sql.append(",`DETAIL_TYPE`=");
         sql.append(unit.detailType());
      }
      if(unit.isQualityChanged()){
         sql.append(",`QUALITY`=");
         sql.append(unit.quality());
      }
      if(unit.isLevelChanged()){
         sql.append(",`LEVEL`=");
         sql.append(unit.level());
      }
      if(unit.isItemTidChanged()){
         sql.append(",`ITEM_TID`=");
         sql.append(unit.itemTid());
      }
      if(unit.isCountChanged()){
         sql.append(",`COUNT`=");
         sql.append(unit.count());
      }
      if(unit.isStatusChanged()){
         sql.append(",`STATUS`=");
         sql.append(unit.status());
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