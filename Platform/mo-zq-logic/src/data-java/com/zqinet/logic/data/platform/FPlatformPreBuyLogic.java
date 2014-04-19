package com.zqinet.logic.data.platform;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>存储预付费表逻辑。</T>
//============================================================
public class FPlatformPreBuyLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("PF_PRE_BUY");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段购买类型的定义。
   public final static FLogicField FieldBuyType = new FLogicField("BUY_TYPE");

   // 字段编号的定义。
   public final static FLogicField FieldPreId = new FLogicField("PRE_ID");

   // 字段数量的定义。
   public final static FLogicField FieldNum = new FLogicField("NUM");

   // 字段图肯的定义。
   public final static FLogicField FieldToken = new FLogicField("TOKEN");

   // 字段腾讯图肯的定义。
   public final static FLogicField FieldTxToken = new FLogicField("TX_TOKEN");

   // 字段花费的定义。
   public final static FLogicField FieldCostMoney = new FLogicField("COST_MONEY");

   // 字段真实扣费的定义。
   public final static FLogicField FieldRealCostMoney = new FLogicField("REAL_COST_MONEY");

   // 字段状态的定义。
   public final static FLogicField FieldStatus = new FLogicField("STATUS");

   // 字段openid的定义。
   public final static FLogicField FieldOpenId = new FLogicField("OPEN_ID");

   // 字段扣税的定义。
   public final static FLogicField FieldFee = new FLogicField("FEE");

   // 字段跟踪ID的定义。
   public final static FLogicField FieldExId = new FLogicField("EX_ID");

   // 字段跟踪时间的定义。
   public final static FLogicField FieldExTime = new FLogicField("EX_TIME");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造存储预付费表逻辑单元。</T>
   //============================================================
   public FPlatformPreBuyLogic(){
   }

   //============================================================
   // <T>构造存储预付费表逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FPlatformPreBuyLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FPlatformPreBuyUnit find(long recordId){
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
      FPlatformPreBuyUnit unit = new FPlatformPreBuyUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FPlatformPreBuyUnit serach(String whereSql){
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
      FPlatformPreBuyUnit unit = new FPlatformPreBuyUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FPlatformPreBuyUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FPlatformPreBuyUnit[] fetch(String whereSql, String orderSql){
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
   public FPlatformPreBuyUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FPlatformPreBuyUnit[] units = new FPlatformPreBuyUnit[count];
      for(int n = 0; n < count; n++){
         FPlatformPreBuyUnit unit = new FPlatformPreBuyUnit();
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
   public FPlatformPreBuyUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FPlatformPreBuyUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`BUY_TYPE`");
      sql.append(",`PRE_ID`");
      sql.append(",`NUM`");
      sql.append(",`TOKEN`");
      sql.append(",`TX_TOKEN`");
      sql.append(",`COST_MONEY`");
      sql.append(",`REAL_COST_MONEY`");
      sql.append(",`STATUS`");
      sql.append(",`OPEN_ID`");
      sql.append(",`FEE`");
      sql.append(",`EX_ID`");
      sql.append(",`EX_TIME`");
      sql.append(",`CREATE_USER_ID`");
      sql.append(",`CREATE_DATE`");
      sql.append(",`UPDATE_USER_ID`");
      sql.append(",`UPDATE_DATE`");
      sql.append(") VALUES(");
      sql.append(unit.ovld());
      sql.append(',');
      sql.append(unit.uniqueId());
      sql.append(',');
      sql.append(unit.buyType());
      sql.append(',');
      sql.append(unit.preId());
      sql.append(',');
      sql.append(unit.num());
      sql.append(',');
      sql.append(unit.token());
      sql.append(',');
      if(RString.isEmpty(unit.txToken())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.txToken()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.costMoney());
      sql.append(',');
      sql.append(unit.realCostMoney());
      sql.append(',');
      sql.append(unit.status());
      sql.append(',');
      if(RString.isEmpty(unit.openId())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.openId()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.fee());
      sql.append(',');
      sql.append(unit.exId());
      sql.append(',');
      sql.append(unit.exTime());
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
   public boolean doUpdate(FPlatformPreBuyUnit unit, long recordId){
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
      if(unit.isBuyTypeChanged()){
         sql.append(",`BUY_TYPE`=");
         sql.append(unit.buyType());
      }
      if(unit.isPreIdChanged()){
         sql.append(",`PRE_ID`=");
         sql.append(unit.preId());
      }
      if(unit.isNumChanged()){
         sql.append(",`NUM`=");
         sql.append(unit.num());
      }
      if(unit.isTokenChanged()){
         sql.append(",`TOKEN`=");
         sql.append(unit.token());
      }
      if(unit.isTxTokenChanged()){
         sql.append(",`TX_TOKEN`=");
         if(RString.isEmpty(unit.txToken())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.txToken()));
            sql.append("'");
         }
      }
      if(unit.isCostMoneyChanged()){
         sql.append(",`COST_MONEY`=");
         sql.append(unit.costMoney());
      }
      if(unit.isRealCostMoneyChanged()){
         sql.append(",`REAL_COST_MONEY`=");
         sql.append(unit.realCostMoney());
      }
      if(unit.isStatusChanged()){
         sql.append(",`STATUS`=");
         sql.append(unit.status());
      }
      if(unit.isOpenIdChanged()){
         sql.append(",`OPEN_ID`=");
         if(RString.isEmpty(unit.openId())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.openId()));
            sql.append("'");
         }
      }
      if(unit.isFeeChanged()){
         sql.append(",`FEE`=");
         sql.append(unit.fee());
      }
      if(unit.isExIdChanged()){
         sql.append(",`EX_ID`=");
         sql.append(unit.exId());
      }
      if(unit.isExTimeChanged()){
         sql.append(",`EX_TIME`=");
         sql.append(unit.exTime());
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