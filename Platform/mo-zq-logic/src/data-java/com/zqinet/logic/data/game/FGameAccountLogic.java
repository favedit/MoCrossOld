package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>帐号信息逻辑。</T>
//============================================================
public class FGameAccountLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("GM_ACCOUNT");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段帐号的定义。
   public final static FLogicField FieldPassport = new FLogicField("PASSPORT");

   // 字段密码的定义。
   public final static FLogicField FieldPassword = new FLogicField("PASSWORD");

   // 字段状态的定义。
   public final static FLogicField FieldStatus = new FLogicField("STATUS");

   // 字段标签的定义。
   public final static FLogicField FieldLabel = new FLogicField("LABEL");

   // 字段成年标志的定义。
   public final static FLogicField FieldIsAdult = new FLogicField("IS_ADULT");

   // 字段点数的定义。
   public final static FLogicField FieldPoint = new FLogicField("POINT");

   // 字段登录时间的定义。
   public final static FLogicField FieldLoginDate = new FLogicField("LOGIN_DATE");

   // 字段非绑定元宝的定义。
   public final static FLogicField FieldMoneyUnbind = new FLogicField("MONEY_UNBIND");

   // 字段消耗元宝数量的定义。
   public final static FLogicField FieldSpendMoney = new FLogicField("SPEND_MONEY");

   // 字段活动重置的定义。
   public final static FLogicField FieldActivityCharge = new FLogicField("ACTIVITY_CHARGE");

   // 字段渠道号的定义。
   public final static FLogicField FieldChannel = new FLogicField("CHANNEL");

   // 字段海报号的定义。
   public final static FLogicField FieldPost = new FLogicField("POST");

   // 字段子站ID的定义。
   public final static FLogicField FieldSubId = new FLogicField("SUB_ID");

   // 字段IP的定义。
   public final static FLogicField FieldIp = new FLogicField("IP");

   // 字段默认名字的定义。
   public final static FLogicField FieldDefaultName = new FLogicField("DEFAULT_NAME");

   // 字段额外的VIP信息的定义。
   public final static FLogicField FieldExVipInfo = new FLogicField("EX_VIP_INFO");

   // 字段额外的vip等级的定义。
   public final static FLogicField FieldExVipLevel = new FLogicField("EX_VIP_LEVEL");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造帐号信息逻辑单元。</T>
   //============================================================
   public FGameAccountLogic(){
   }

   //============================================================
   // <T>构造帐号信息逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FGameAccountLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FGameAccountUnit find(long recordId){
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
      FGameAccountUnit unit = new FGameAccountUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FGameAccountUnit serach(String whereSql){
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
      FGameAccountUnit unit = new FGameAccountUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FGameAccountUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FGameAccountUnit[] fetch(String whereSql, String orderSql){
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
   public FGameAccountUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FGameAccountUnit[] units = new FGameAccountUnit[count];
      for(int n = 0; n < count; n++){
         FGameAccountUnit unit = new FGameAccountUnit();
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
   public FGameAccountUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FGameAccountUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`PASSPORT`");
      sql.append(",`PASSWORD`");
      sql.append(",`STATUS`");
      sql.append(",`LABEL`");
      sql.append(",`IS_ADULT`");
      sql.append(",`POINT`");
      sql.append(",`LOGIN_DATE`");
      sql.append(",`MONEY_UNBIND`");
      sql.append(",`SPEND_MONEY`");
      sql.append(",`ACTIVITY_CHARGE`");
      sql.append(",`CHANNEL`");
      sql.append(",`POST`");
      sql.append(",`SUB_ID`");
      sql.append(",`IP`");
      sql.append(",`DEFAULT_NAME`");
      sql.append(",`EX_VIP_INFO`");
      sql.append(",`EX_VIP_LEVEL`");
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
      if(RString.isEmpty(unit.password())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.password()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.status());
      sql.append(',');
      if(RString.isEmpty(unit.label())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.label()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.isAdult());
      sql.append(',');
      sql.append(unit.point());
      sql.append(',');
      if(unit.loginDate().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.loginDate().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      sql.append(unit.moneyUnbind());
      sql.append(',');
      sql.append(unit.spendMoney());
      sql.append(',');
      sql.append(unit.activityCharge());
      sql.append(',');
      if(RString.isEmpty(unit.channel())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.channel()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.post())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.post()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.subId())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.subId()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.ip())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.ip()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.defaultName())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.defaultName()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.exVipInfo());
      sql.append(',');
      sql.append(unit.exVipLevel());
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
   public boolean doUpdate(FGameAccountUnit unit, long recordId){
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
      if(unit.isPasswordChanged()){
         sql.append(",`PASSWORD`=");
         if(RString.isEmpty(unit.password())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.password()));
            sql.append("'");
         }
      }
      if(unit.isStatusChanged()){
         sql.append(",`STATUS`=");
         sql.append(unit.status());
      }
      if(unit.isLabelChanged()){
         sql.append(",`LABEL`=");
         if(RString.isEmpty(unit.label())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.label()));
            sql.append("'");
         }
      }
      if(unit.isIsAdultChanged()){
         sql.append(",`IS_ADULT`=");
         sql.append(unit.isAdult());
      }
      if(unit.isPointChanged()){
         sql.append(",`POINT`=");
         sql.append(unit.point());
      }
      if(unit.isLoginDateChanged()){
         sql.append(",`LOGIN_DATE`=");
         if(unit.loginDate().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.loginDate().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isMoneyUnbindChanged()){
         sql.append(",`MONEY_UNBIND`=");
         sql.append(unit.moneyUnbind());
      }
      if(unit.isSpendMoneyChanged()){
         sql.append(",`SPEND_MONEY`=");
         sql.append(unit.spendMoney());
      }
      if(unit.isActivityChargeChanged()){
         sql.append(",`ACTIVITY_CHARGE`=");
         sql.append(unit.activityCharge());
      }
      if(unit.isChannelChanged()){
         sql.append(",`CHANNEL`=");
         if(RString.isEmpty(unit.channel())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.channel()));
            sql.append("'");
         }
      }
      if(unit.isPostChanged()){
         sql.append(",`POST`=");
         if(RString.isEmpty(unit.post())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.post()));
            sql.append("'");
         }
      }
      if(unit.isSubIdChanged()){
         sql.append(",`SUB_ID`=");
         if(RString.isEmpty(unit.subId())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.subId()));
            sql.append("'");
         }
      }
      if(unit.isIpChanged()){
         sql.append(",`IP`=");
         if(RString.isEmpty(unit.ip())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.ip()));
            sql.append("'");
         }
      }
      if(unit.isDefaultNameChanged()){
         sql.append(",`DEFAULT_NAME`=");
         if(RString.isEmpty(unit.defaultName())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.defaultName()));
            sql.append("'");
         }
      }
      if(unit.isExVipInfoChanged()){
         sql.append(",`EX_VIP_INFO`=");
         sql.append(unit.exVipInfo());
      }
      if(unit.isExVipLevelChanged()){
         sql.append(",`EX_VIP_LEVEL`=");
         sql.append(unit.exVipLevel());
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