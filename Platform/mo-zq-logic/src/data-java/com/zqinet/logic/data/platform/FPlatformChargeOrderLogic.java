package com.zqinet.logic.data.platform;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>充值订单逻辑。</T>
//============================================================
public class FPlatformChargeOrderLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("PF_CHARGE_ORDER");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段用户ID 字母或数字的定义。
   public final static FLogicField FieldUserId = new FLogicField("USER_ID");

   // 字段订单号的定义。
   public final static FLogicField FieldOrderId = new FLogicField("ORDER_ID");

   // 字段RMB的定义。
   public final static FLogicField FieldMoney = new FLogicField("MONEY");

   // 字段游戏元宝的定义。
   public final static FLogicField FieldGameMoney = new FLogicField("GAME_MONEY");

   // 字段对方时间戳的定义。
   public final static FLogicField FieldTimeStamp = new FLogicField("TIME_STAMP");

   // 字段状态的定义。
   public final static FLogicField FieldStatus = new FLogicField("STATUS");

   // 字段对方IP的定义。
   public final static FLogicField FieldHostIp = new FLogicField("HOST_IP");

   // 字段余额（腾讯平台用）的定义。
   public final static FLogicField FieldBalance = new FLogicField("BALANCE");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造充值订单逻辑单元。</T>
   //============================================================
   public FPlatformChargeOrderLogic(){
   }

   //============================================================
   // <T>构造充值订单逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FPlatformChargeOrderLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FPlatformChargeOrderUnit find(long recordId){
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
      FPlatformChargeOrderUnit unit = new FPlatformChargeOrderUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FPlatformChargeOrderUnit serach(String whereSql){
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
      FPlatformChargeOrderUnit unit = new FPlatformChargeOrderUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FPlatformChargeOrderUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FPlatformChargeOrderUnit[] fetch(String whereSql, String orderSql){
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
   public FPlatformChargeOrderUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FPlatformChargeOrderUnit[] units = new FPlatformChargeOrderUnit[count];
      for(int n = 0; n < count; n++){
         FPlatformChargeOrderUnit unit = new FPlatformChargeOrderUnit();
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
   public FPlatformChargeOrderUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FPlatformChargeOrderUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`USER_ID`");
      sql.append(",`ORDER_ID`");
      sql.append(",`MONEY`");
      sql.append(",`GAME_MONEY`");
      sql.append(",`TIME_STAMP`");
      sql.append(",`STATUS`");
      sql.append(",`HOST_IP`");
      sql.append(",`BALANCE`");
      sql.append(",`CREATE_USER_ID`");
      sql.append(",`CREATE_DATE`");
      sql.append(",`UPDATE_USER_ID`");
      sql.append(",`UPDATE_DATE`");
      sql.append(") VALUES(");
      sql.append(unit.ovld());
      sql.append(',');
      sql.append(unit.uniqueId());
      sql.append(',');
      if(RString.isEmpty(unit.userId())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.userId()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.orderId())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.orderId()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.money());
      sql.append(',');
      sql.append(unit.gameMoney());
      sql.append(',');
      sql.append(unit.timeStamp());
      sql.append(',');
      sql.append(unit.status());
      sql.append(',');
      if(RString.isEmpty(unit.hostIp())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.hostIp()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.balance());
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
   public boolean doUpdate(FPlatformChargeOrderUnit unit, long recordId){
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
      if(unit.isOrderIdChanged()){
         sql.append(",`ORDER_ID`=");
         if(RString.isEmpty(unit.orderId())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.orderId()));
            sql.append("'");
         }
      }
      if(unit.isMoneyChanged()){
         sql.append(",`MONEY`=");
         sql.append(unit.money());
      }
      if(unit.isGameMoneyChanged()){
         sql.append(",`GAME_MONEY`=");
         sql.append(unit.gameMoney());
      }
      if(unit.isTimeStampChanged()){
         sql.append(",`TIME_STAMP`=");
         sql.append(unit.timeStamp());
      }
      if(unit.isStatusChanged()){
         sql.append(",`STATUS`=");
         sql.append(unit.status());
      }
      if(unit.isHostIpChanged()){
         sql.append(",`HOST_IP`=");
         if(RString.isEmpty(unit.hostIp())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.hostIp()));
            sql.append("'");
         }
      }
      if(unit.isBalanceChanged()){
         sql.append(",`BALANCE`=");
         sql.append(unit.balance());
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