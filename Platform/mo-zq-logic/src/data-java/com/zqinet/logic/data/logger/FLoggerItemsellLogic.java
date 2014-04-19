package com.zqinet.logic.data.logger;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>道具销售日志逻辑。</T>
//============================================================
public class FLoggerItemsellLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("LOG_ROLE_ITEMSELL");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段角色ID的定义。
   public final static FLogicField FieldRoleId = new FLogicField("ROLE_ID");

   // 字段操作时间的定义。
   public final static FLogicField FieldOpTime = new FLogicField("OP_TIME");

   // 字段登陆IP的定义。
   public final static FLogicField FieldLoginIp = new FLogicField("LOGIN_IP");

   // 字段玩家所在的地图ID的定义。
   public final static FLogicField FieldMapId = new FLogicField("MAP_ID");

   // 字段职业ID的定义。
   public final static FLogicField FieldMetierTid = new FLogicField("METIER_TID");

   // 字段玩家等级的定义。
   public final static FLogicField FieldRoleLevel = new FLogicField("ROLE_LEVEL");

   // 字段物品ID的定义。
   public final static FLogicField FieldItemTid = new FLogicField("ITEM_TID");

   // 字段购买的数量的定义。
   public final static FLogicField FieldItemNum = new FLogicField("ITEM_NUM");

   // 字段折扣前的单价的定义。
   public final static FLogicField FieldPrice = new FLogicField("PRICE");

   // 字段折扣后的价格的定义。
   public final static FLogicField FieldDiscountPrice = new FLogicField("DISCOUNT_PRICE");

   // 字段总价的定义。
   public final static FLogicField FieldTotalPrice = new FLogicField("TOTAL_PRICE");

   // 字段余额的定义。
   public final static FLogicField FieldBalance = new FLogicField("BALANCE");

   // 字段货币类型的定义。
   public final static FLogicField FieldMoneyType = new FLogicField("MONEY_TYPE");

   // 字段商城类型的定义。
   public final static FLogicField FieldStoreType = new FLogicField("STORE_TYPE");

   // 字段商品包Tid的定义。
   public final static FLogicField FieldItembagTid = new FLogicField("ITEMBAG_TID");

   // 字段参数1的定义。
   public final static FLogicField FieldParam1 = new FLogicField("PARAM1");

   // 字段参数2的定义。
   public final static FLogicField FieldParam2 = new FLogicField("PARAM2");

   // 字段物品类型的定义。
   public final static FLogicField FieldItemType = new FLogicField("ITEM_TYPE");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   // 字段是否已经发送的定义。
   public final static FLogicField FieldIsSend = new FLogicField("IS_SEND");

   //============================================================
   // <T>构造道具销售日志逻辑单元。</T>
   //============================================================
   public FLoggerItemsellLogic(){
   }

   //============================================================
   // <T>构造道具销售日志逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FLoggerItemsellLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FLoggerItemsellUnit find(long recordId){
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
      FLoggerItemsellUnit unit = new FLoggerItemsellUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FLoggerItemsellUnit serach(String whereSql){
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
      FLoggerItemsellUnit unit = new FLoggerItemsellUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FLoggerItemsellUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FLoggerItemsellUnit[] fetch(String whereSql, String orderSql){
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
   public FLoggerItemsellUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FLoggerItemsellUnit[] units = new FLoggerItemsellUnit[count];
      for(int n = 0; n < count; n++){
         FLoggerItemsellUnit unit = new FLoggerItemsellUnit();
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
   public FLoggerItemsellUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FLoggerItemsellUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`ROLE_ID`");
      sql.append(",`OP_TIME`");
      sql.append(",`LOGIN_IP`");
      sql.append(",`MAP_ID`");
      sql.append(",`METIER_TID`");
      sql.append(",`ROLE_LEVEL`");
      sql.append(",`ITEM_TID`");
      sql.append(",`ITEM_NUM`");
      sql.append(",`PRICE`");
      sql.append(",`DISCOUNT_PRICE`");
      sql.append(",`TOTAL_PRICE`");
      sql.append(",`BALANCE`");
      sql.append(",`MONEY_TYPE`");
      sql.append(",`STORE_TYPE`");
      sql.append(",`ITEMBAG_TID`");
      sql.append(",`PARAM1`");
      sql.append(",`PARAM2`");
      sql.append(",`ITEM_TYPE`");
      sql.append(",`CREATE_USER_ID`");
      sql.append(",`CREATE_DATE`");
      sql.append(",`UPDATE_USER_ID`");
      sql.append(",`UPDATE_DATE`");
      sql.append(",`IS_SEND`");
      sql.append(") VALUES(");
      sql.append(unit.ovld());
      sql.append(',');
      sql.append(unit.uniqueId());
      sql.append(',');
      sql.append(unit.roleId());
      sql.append(',');
      if(unit.opTime().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.opTime().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      if(RString.isEmpty(unit.loginIp())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.loginIp()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.mapId());
      sql.append(',');
      sql.append(unit.metierTid());
      sql.append(',');
      sql.append(unit.roleLevel());
      sql.append(',');
      sql.append(unit.itemTid());
      sql.append(',');
      sql.append(unit.itemNum());
      sql.append(',');
      sql.append(unit.price());
      sql.append(',');
      sql.append(unit.discountPrice());
      sql.append(',');
      sql.append(unit.totalPrice());
      sql.append(',');
      sql.append(unit.balance());
      sql.append(',');
      sql.append(unit.moneyType());
      sql.append(',');
      sql.append(unit.storeType());
      sql.append(',');
      sql.append(unit.itembagTid());
      sql.append(',');
      if(RString.isEmpty(unit.param1())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.param1()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.param2())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.param2()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.itemType());
      sql.append(',');
      sql.append(unit.isSend());
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
   public boolean doUpdate(FLoggerItemsellUnit unit, long recordId){
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
      if(unit.isOpTimeChanged()){
         sql.append(",`OP_TIME`=");
         if(unit.opTime().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.opTime().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isLoginIpChanged()){
         sql.append(",`LOGIN_IP`=");
         if(RString.isEmpty(unit.loginIp())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.loginIp()));
            sql.append("'");
         }
      }
      if(unit.isMapIdChanged()){
         sql.append(",`MAP_ID`=");
         sql.append(unit.mapId());
      }
      if(unit.isMetierTidChanged()){
         sql.append(",`METIER_TID`=");
         sql.append(unit.metierTid());
      }
      if(unit.isRoleLevelChanged()){
         sql.append(",`ROLE_LEVEL`=");
         sql.append(unit.roleLevel());
      }
      if(unit.isItemTidChanged()){
         sql.append(",`ITEM_TID`=");
         sql.append(unit.itemTid());
      }
      if(unit.isItemNumChanged()){
         sql.append(",`ITEM_NUM`=");
         sql.append(unit.itemNum());
      }
      if(unit.isPriceChanged()){
         sql.append(",`PRICE`=");
         sql.append(unit.price());
      }
      if(unit.isDiscountPriceChanged()){
         sql.append(",`DISCOUNT_PRICE`=");
         sql.append(unit.discountPrice());
      }
      if(unit.isTotalPriceChanged()){
         sql.append(",`TOTAL_PRICE`=");
         sql.append(unit.totalPrice());
      }
      if(unit.isBalanceChanged()){
         sql.append(",`BALANCE`=");
         sql.append(unit.balance());
      }
      if(unit.isMoneyTypeChanged()){
         sql.append(",`MONEY_TYPE`=");
         sql.append(unit.moneyType());
      }
      if(unit.isStoreTypeChanged()){
         sql.append(",`STORE_TYPE`=");
         sql.append(unit.storeType());
      }
      if(unit.isItembagTidChanged()){
         sql.append(",`ITEMBAG_TID`=");
         sql.append(unit.itembagTid());
      }
      if(unit.isParam1Changed()){
         sql.append(",`PARAM1`=");
         if(RString.isEmpty(unit.param1())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.param1()));
            sql.append("'");
         }
      }
      if(unit.isParam2Changed()){
         sql.append(",`PARAM2`=");
         if(RString.isEmpty(unit.param2())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.param2()));
            sql.append("'");
         }
      }
      if(unit.isItemTypeChanged()){
         sql.append(",`ITEM_TYPE`=");
         sql.append(unit.itemType());
      }
      if(unit.isCreateUserIdChanged()){
         sql.append(",`CREATE_USER_ID`=");
         sql.append(unit.createUserId());
      }
      if(unit.isIsSendChanged()){
         sql.append(",`IS_SEND`=");
         sql.append(unit.isSend());
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