package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>商城商品信息逻辑。</T>
//============================================================
public class FGameShopItemLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("GM_SHOP_ITEM");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段商城商品模版编号的定义。
   public final static FLogicField FieldShopItemTid = new FLogicField("SHOP_ITEM_TID");

   // 字段是否有效的定义。
   public final static FLogicField FieldIsValid = new FLogicField("IS_VALID");

   // 字段基本货币原价的定义。
   public final static FLogicField FieldCurrencyOrigin = new FLogicField("CURRENCY_ORIGIN");

   // 字段基本货币现价的定义。
   public final static FLogicField FieldCurrency = new FLogicField("CURRENCY");

   // 字段其他货币类型的定义。
   public final static FLogicField FieldCurrencyCd = new FLogicField("CURRENCY_CD");

   // 字段其他货币原价的定义。
   public final static FLogicField FieldCurrencyOriginValue = new FLogicField("CURRENCY_ORIGIN_VALUE");

   // 字段其他货币原价的定义。
   public final static FLogicField FieldCurrencyValue = new FLogicField("CURRENCY_VALUE");

   // 字段销售方式的定义。
   public final static FLogicField FieldSaleCd = new FLogicField("SALE_CD");

   // 字段购买数量限制的定义。
   public final static FLogicField FieldLimitCount = new FLogicField("LIMIT_COUNT");

   // 字段刷新数量的定义。
   public final static FLogicField FieldRefreshCount = new FLogicField("REFRESH_COUNT");

   // 字段刷新间隔的定义。
   public final static FLogicField FieldRefreshIntervalTs = new FLogicField("REFRESH_INTERVAL_TS");

   // 字段有效方式的定义。
   public final static FLogicField FieldValidCd = new FLogicField("VALID_CD");

   // 字段有效开始时间的定义。
   public final static FLogicField FieldValidBeginDate = new FLogicField("VALID_BEGIN_DATE");

   // 字段有效结束时间的定义。
   public final static FLogicField FieldValidEndDate = new FLogicField("VALID_END_DATE");

   // 字段显示顺序的定义。
   public final static FLogicField FieldDisplayOrder = new FLogicField("DISPLAY_ORDER");

   // 字段限购物品当前数量的定义。
   public final static FLogicField FieldCurrentCount = new FLogicField("CURRENT_COUNT");

   // 字段商城类型的定义。
   public final static FLogicField FieldShopCd = new FLogicField("SHOP_CD");

   // 字段抢购物品状态的定义。
   public final static FLogicField FieldShopItemStatus = new FLogicField("SHOP_ITEM_STATUS");

   // 字段销售状态的定义。
   public final static FLogicField FieldSaleStatus = new FLogicField("SALE_STATUS");

   // 字段抢购开始时间的定义。
   public final static FLogicField FieldPanicBeginDate = new FLogicField("PANIC_BEGIN_DATE");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造商城商品信息逻辑单元。</T>
   //============================================================
   public FGameShopItemLogic(){
   }

   //============================================================
   // <T>构造商城商品信息逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FGameShopItemLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FGameShopItemUnit find(long recordId){
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
      FGameShopItemUnit unit = new FGameShopItemUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FGameShopItemUnit serach(String whereSql){
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
      FGameShopItemUnit unit = new FGameShopItemUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FGameShopItemUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FGameShopItemUnit[] fetch(String whereSql, String orderSql){
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
   public FGameShopItemUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FGameShopItemUnit[] units = new FGameShopItemUnit[count];
      for(int n = 0; n < count; n++){
         FGameShopItemUnit unit = new FGameShopItemUnit();
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
   public FGameShopItemUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FGameShopItemUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`SHOP_ITEM_TID`");
      sql.append(",`IS_VALID`");
      sql.append(",`CURRENCY_ORIGIN`");
      sql.append(",`CURRENCY`");
      sql.append(",`CURRENCY_CD`");
      sql.append(",`CURRENCY_ORIGIN_VALUE`");
      sql.append(",`CURRENCY_VALUE`");
      sql.append(",`SALE_CD`");
      sql.append(",`LIMIT_COUNT`");
      sql.append(",`REFRESH_COUNT`");
      sql.append(",`REFRESH_INTERVAL_TS`");
      sql.append(",`VALID_CD`");
      sql.append(",`VALID_BEGIN_DATE`");
      sql.append(",`VALID_END_DATE`");
      sql.append(",`DISPLAY_ORDER`");
      sql.append(",`CURRENT_COUNT`");
      sql.append(",`SHOP_CD`");
      sql.append(",`SHOP_ITEM_STATUS`");
      sql.append(",`SALE_STATUS`");
      sql.append(",`PANIC_BEGIN_DATE`");
      sql.append(",`CREATE_USER_ID`");
      sql.append(",`CREATE_DATE`");
      sql.append(",`UPDATE_USER_ID`");
      sql.append(",`UPDATE_DATE`");
      sql.append(") VALUES(");
      sql.append(unit.ovld());
      sql.append(',');
      sql.append(unit.uniqueId());
      sql.append(',');
      sql.append(unit.shopItemTid());
      sql.append(',');
      sql.append(unit.isValid());
      sql.append(',');
      if(RString.isEmpty(unit.currencyOrigin())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.currencyOrigin()));
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
      sql.append(unit.currencyCd());
      sql.append(',');
      sql.append(unit.currencyOriginValue());
      sql.append(',');
      sql.append(unit.currencyValue());
      sql.append(',');
      sql.append(unit.saleCd());
      sql.append(',');
      sql.append(unit.limitCount());
      sql.append(',');
      sql.append(unit.refreshCount());
      sql.append(',');
      sql.append(unit.refreshIntervalTs());
      sql.append(',');
      sql.append(unit.validCd());
      sql.append(',');
      if(unit.validBeginDate().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.validBeginDate().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      if(unit.validEndDate().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.validEndDate().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      sql.append(unit.displayOrder());
      sql.append(',');
      sql.append(unit.currentCount());
      sql.append(',');
      sql.append(unit.shopCd());
      sql.append(',');
      sql.append(unit.shopItemStatus());
      sql.append(',');
      sql.append(unit.saleStatus());
      sql.append(',');
      if(unit.panicBeginDate().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.panicBeginDate().format());
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
   public boolean doUpdate(FGameShopItemUnit unit, long recordId){
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
      if(unit.isShopItemTidChanged()){
         sql.append(",`SHOP_ITEM_TID`=");
         sql.append(unit.shopItemTid());
      }
      if(unit.isIsValidChanged()){
         sql.append(",`IS_VALID`=");
         sql.append(unit.isValid());
      }
      if(unit.isCurrencyOriginChanged()){
         sql.append(",`CURRENCY_ORIGIN`=");
         if(RString.isEmpty(unit.currencyOrigin())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.currencyOrigin()));
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
      if(unit.isCurrencyCdChanged()){
         sql.append(",`CURRENCY_CD`=");
         sql.append(unit.currencyCd());
      }
      if(unit.isCurrencyOriginValueChanged()){
         sql.append(",`CURRENCY_ORIGIN_VALUE`=");
         sql.append(unit.currencyOriginValue());
      }
      if(unit.isCurrencyValueChanged()){
         sql.append(",`CURRENCY_VALUE`=");
         sql.append(unit.currencyValue());
      }
      if(unit.isSaleCdChanged()){
         sql.append(",`SALE_CD`=");
         sql.append(unit.saleCd());
      }
      if(unit.isLimitCountChanged()){
         sql.append(",`LIMIT_COUNT`=");
         sql.append(unit.limitCount());
      }
      if(unit.isRefreshCountChanged()){
         sql.append(",`REFRESH_COUNT`=");
         sql.append(unit.refreshCount());
      }
      if(unit.isRefreshIntervalTsChanged()){
         sql.append(",`REFRESH_INTERVAL_TS`=");
         sql.append(unit.refreshIntervalTs());
      }
      if(unit.isValidCdChanged()){
         sql.append(",`VALID_CD`=");
         sql.append(unit.validCd());
      }
      if(unit.isValidBeginDateChanged()){
         sql.append(",`VALID_BEGIN_DATE`=");
         if(unit.validBeginDate().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.validBeginDate().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isValidEndDateChanged()){
         sql.append(",`VALID_END_DATE`=");
         if(unit.validEndDate().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.validEndDate().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isDisplayOrderChanged()){
         sql.append(",`DISPLAY_ORDER`=");
         sql.append(unit.displayOrder());
      }
      if(unit.isCurrentCountChanged()){
         sql.append(",`CURRENT_COUNT`=");
         sql.append(unit.currentCount());
      }
      if(unit.isShopCdChanged()){
         sql.append(",`SHOP_CD`=");
         sql.append(unit.shopCd());
      }
      if(unit.isShopItemStatusChanged()){
         sql.append(",`SHOP_ITEM_STATUS`=");
         sql.append(unit.shopItemStatus());
      }
      if(unit.isSaleStatusChanged()){
         sql.append(",`SALE_STATUS`=");
         sql.append(unit.saleStatus());
      }
      if(unit.isPanicBeginDateChanged()){
         sql.append(",`PANIC_BEGIN_DATE`=");
         if(unit.panicBeginDate().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.panicBeginDate().format());
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