package com.zqinet.logic.data.logger;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>登陆的客户端信息日志逻辑。</T>
//============================================================
public class FLoggerClientLoginLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("LOG_CLIENT_LOGIN");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段账号代码的定义。
   public final static FLogicField FieldAccountCode = new FLogicField("ACCOUNT_CODE");

   // 字段驱动信息的定义。
   public final static FLogicField FieldDriverInfo = new FLogicField("DRIVER_INFO");

   // 字段flash操作系统的定义。
   public final static FLogicField FieldFlashOs = new FLogicField("FLASH_OS");

   // 字段flash版本的定义。
   public final static FLogicField FieldFlashVersion = new FLogicField("FLASH_VERSION");

   // 字段flash鉴定信息的定义。
   public final static FLogicField FieldFlashIdentity = new FLogicField("FLASH_IDENTITY");

   // 字段登陆主机的定义。
   public final static FLogicField FieldLoginHost = new FLogicField("LOGIN_HOST");

   // 字段登陆时间的定义。
   public final static FLogicField FieldLoginDate = new FLogicField("LOGIN_DATE");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造登陆的客户端信息日志逻辑单元。</T>
   //============================================================
   public FLoggerClientLoginLogic(){
   }

   //============================================================
   // <T>构造登陆的客户端信息日志逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FLoggerClientLoginLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FLoggerClientLoginUnit find(long recordId){
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
      FLoggerClientLoginUnit unit = new FLoggerClientLoginUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FLoggerClientLoginUnit serach(String whereSql){
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
      FLoggerClientLoginUnit unit = new FLoggerClientLoginUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FLoggerClientLoginUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FLoggerClientLoginUnit[] fetch(String whereSql, String orderSql){
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
   public FLoggerClientLoginUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FLoggerClientLoginUnit[] units = new FLoggerClientLoginUnit[count];
      for(int n = 0; n < count; n++){
         FLoggerClientLoginUnit unit = new FLoggerClientLoginUnit();
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
   public FLoggerClientLoginUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FLoggerClientLoginUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`ACCOUNT_CODE`");
      sql.append(",`DRIVER_INFO`");
      sql.append(",`FLASH_OS`");
      sql.append(",`FLASH_VERSION`");
      sql.append(",`FLASH_IDENTITY`");
      sql.append(",`LOGIN_HOST`");
      sql.append(",`LOGIN_DATE`");
      sql.append(",`CREATE_USER_ID`");
      sql.append(",`CREATE_DATE`");
      sql.append(",`UPDATE_USER_ID`");
      sql.append(",`UPDATE_DATE`");
      sql.append(") VALUES(");
      sql.append(unit.ovld());
      sql.append(',');
      sql.append(unit.uniqueId());
      sql.append(',');
      if(RString.isEmpty(unit.accountCode())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.accountCode()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.driverInfo())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.driverInfo()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.flashOs())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.flashOs()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.flashVersion())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.flashVersion()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.flashIdentity())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.flashIdentity()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.loginHost())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.loginHost()));
         sql.append('\'');
      }
      sql.append(',');
      if(unit.loginDate().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.loginDate().format());
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
   public boolean doUpdate(FLoggerClientLoginUnit unit, long recordId){
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
      if(unit.isAccountCodeChanged()){
         sql.append(",`ACCOUNT_CODE`=");
         if(RString.isEmpty(unit.accountCode())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.accountCode()));
            sql.append("'");
         }
      }
      if(unit.isDriverInfoChanged()){
         sql.append(",`DRIVER_INFO`=");
         if(RString.isEmpty(unit.driverInfo())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.driverInfo()));
            sql.append("'");
         }
      }
      if(unit.isFlashOsChanged()){
         sql.append(",`FLASH_OS`=");
         if(RString.isEmpty(unit.flashOs())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.flashOs()));
            sql.append("'");
         }
      }
      if(unit.isFlashVersionChanged()){
         sql.append(",`FLASH_VERSION`=");
         if(RString.isEmpty(unit.flashVersion())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.flashVersion()));
            sql.append("'");
         }
      }
      if(unit.isFlashIdentityChanged()){
         sql.append(",`FLASH_IDENTITY`=");
         if(RString.isEmpty(unit.flashIdentity())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.flashIdentity()));
            sql.append("'");
         }
      }
      if(unit.isLoginHostChanged()){
         sql.append(",`LOGIN_HOST`=");
         if(RString.isEmpty(unit.loginHost())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.loginHost()));
            sql.append("'");
         }
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