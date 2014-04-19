package com.zqinet.logic.data.platform;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>发货表逻辑。</T>
//============================================================
public class FPlatformSenditemLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("PF_SENDITEM");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段账户的定义。
   public final static FLogicField FieldPassport = new FLogicField("PASSPORT");

   // 字段交易对方账户的定义。
   public final static FLogicField FieldSrcPassport = new FLogicField("SRC_PASSPORT");

   // 字段模板ID的定义。
   public final static FLogicField FieldItemTid = new FLogicField("ITEM_TID");

   // 字段物品ID的定义。
   public final static FLogicField FieldItemId = new FLogicField("ITEM_ID");

   // 字段价格的定义。
   public final static FLogicField FieldPrice = new FLogicField("PRICE");

   // 字段数量的定义。
   public final static FLogicField FieldNum = new FLogicField("NUM");

   // 字段交易token的定义。
   public final static FLogicField FieldToken = new FLogicField("TOKEN");

   // 字段账单号的定义。
   public final static FLogicField FieldBillNo = new FLogicField("BILL_NO");

   // 字段分区ID的定义。
   public final static FLogicField FieldZoneId = new FLogicField("ZONE_ID");

   // 字段扣除RMB （Q点）的定义。
   public final static FLogicField FieldCostRmb = new FLogicField("COST_RMB");

   // 字段消耗元宝的定义。
   public final static FLogicField FieldCostMoney = new FLogicField("COST_MONEY");

   // 字段抵扣券消耗的定义。
   public final static FLogicField FieldCostPubacct = new FLogicField("COST_PUBACCT");

   // 字段发货类型 0商城 3交易行的定义。
   public final static FLogicField FieldSendType = new FLogicField("SEND_TYPE");

   // 字段交易税的定义。
   public final static FLogicField FieldFee = new FLogicField("FEE");

   // 字段处理状态的定义。
   public final static FLogicField FieldOpStatus = new FLogicField("OP_STATUS");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造发货表逻辑单元。</T>
   //============================================================
   public FPlatformSenditemLogic(){
   }

   //============================================================
   // <T>构造发货表逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FPlatformSenditemLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FPlatformSenditemUnit find(long recordId){
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
      FPlatformSenditemUnit unit = new FPlatformSenditemUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FPlatformSenditemUnit serach(String whereSql){
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
      FPlatformSenditemUnit unit = new FPlatformSenditemUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FPlatformSenditemUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FPlatformSenditemUnit[] fetch(String whereSql, String orderSql){
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
   public FPlatformSenditemUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FPlatformSenditemUnit[] units = new FPlatformSenditemUnit[count];
      for(int n = 0; n < count; n++){
         FPlatformSenditemUnit unit = new FPlatformSenditemUnit();
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
   public FPlatformSenditemUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FPlatformSenditemUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`PASSPORT`");
      sql.append(",`SRC_PASSPORT`");
      sql.append(",`ITEM_TID`");
      sql.append(",`ITEM_ID`");
      sql.append(",`PRICE`");
      sql.append(",`NUM`");
      sql.append(",`TOKEN`");
      sql.append(",`BILL_NO`");
      sql.append(",`ZONE_ID`");
      sql.append(",`COST_RMB`");
      sql.append(",`COST_MONEY`");
      sql.append(",`COST_PUBACCT`");
      sql.append(",`SEND_TYPE`");
      sql.append(",`FEE`");
      sql.append(",`OP_STATUS`");
      sql.append(",`CREATE_USER_ID`");
      sql.append(",`CREATE_DATE`");
      sql.append(",`UPDATE_USER_ID`");
      sql.append(",`UPDATE_DATE`");
      sql.append(") VALUES(");
      sql.append(unit.ovld());
      sql.append(',');
      sql.append(unit.uniqueId());
      sql.append(',');
      if(RString.isEmpty(unit.passport())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.passport()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.srcPassport())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.srcPassport()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.itemTid());
      sql.append(',');
      sql.append(unit.itemId());
      sql.append(',');
      sql.append(unit.price());
      sql.append(',');
      sql.append(unit.num());
      sql.append(',');
      if(RString.isEmpty(unit.token())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.token()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.billNo())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.billNo()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.zoneId());
      sql.append(',');
      sql.append(unit.costRmb());
      sql.append(',');
      sql.append(unit.costMoney());
      sql.append(',');
      sql.append(unit.costPubacct());
      sql.append(',');
      sql.append(unit.sendType());
      sql.append(',');
      sql.append(unit.fee());
      sql.append(',');
      sql.append(unit.opStatus());
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
   public boolean doUpdate(FPlatformSenditemUnit unit, long recordId){
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
      if(unit.isSrcPassportChanged()){
         sql.append(",`SRC_PASSPORT`=");
         if(RString.isEmpty(unit.srcPassport())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.srcPassport()));
            sql.append("'");
         }
      }
      if(unit.isItemTidChanged()){
         sql.append(",`ITEM_TID`=");
         sql.append(unit.itemTid());
      }
      if(unit.isItemIdChanged()){
         sql.append(",`ITEM_ID`=");
         sql.append(unit.itemId());
      }
      if(unit.isPriceChanged()){
         sql.append(",`PRICE`=");
         sql.append(unit.price());
      }
      if(unit.isNumChanged()){
         sql.append(",`NUM`=");
         sql.append(unit.num());
      }
      if(unit.isTokenChanged()){
         sql.append(",`TOKEN`=");
         if(RString.isEmpty(unit.token())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.token()));
            sql.append("'");
         }
      }
      if(unit.isBillNoChanged()){
         sql.append(",`BILL_NO`=");
         if(RString.isEmpty(unit.billNo())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.billNo()));
            sql.append("'");
         }
      }
      if(unit.isZoneIdChanged()){
         sql.append(",`ZONE_ID`=");
         sql.append(unit.zoneId());
      }
      if(unit.isCostRmbChanged()){
         sql.append(",`COST_RMB`=");
         sql.append(unit.costRmb());
      }
      if(unit.isCostMoneyChanged()){
         sql.append(",`COST_MONEY`=");
         sql.append(unit.costMoney());
      }
      if(unit.isCostPubacctChanged()){
         sql.append(",`COST_PUBACCT`=");
         sql.append(unit.costPubacct());
      }
      if(unit.isSendTypeChanged()){
         sql.append(",`SEND_TYPE`=");
         sql.append(unit.sendType());
      }
      if(unit.isFeeChanged()){
         sql.append(",`FEE`=");
         sql.append(unit.fee());
      }
      if(unit.isOpStatusChanged()){
         sql.append(",`OP_STATUS`=");
         sql.append(unit.opStatus());
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