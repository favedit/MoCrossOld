package com.zqinet.logic.data.platform;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>GM工具控制逻辑。</T>
//============================================================
public class FPlatformGmControlLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("PF_GM_CONTROL");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段控制状态的定义。
   public final static FLogicField FieldOperateCd = new FLogicField("OPERATE_CD");

   // 字段控制类型的定义。
   public final static FLogicField FieldCmd = new FLogicField("CMD");

   // 字段角色编号的定义。
   public final static FLogicField FieldRoleId = new FLogicField("ROLE_ID");

   // 字段角色名的定义。
   public final static FLogicField FieldLabel = new FLogicField("LABEL");

   // 字段开始时间的定义。
   public final static FLogicField FieldBeginDate = new FLogicField("BEGIN_DATE");

   // 字段结束时间的定义。
   public final static FLogicField FieldEndDate = new FLogicField("END_DATE");

   // 字段参数1的定义。
   public final static FLogicField FieldParam1 = new FLogicField("PARAM1");

   // 字段参数2的定义。
   public final static FLogicField FieldParam2 = new FLogicField("PARAM2");

   // 字段参数3的定义。
   public final static FLogicField FieldParam3 = new FLogicField("PARAM3");

   // 字段操作人名称的定义。
   public final static FLogicField FieldControlUser = new FLogicField("CONTROL_USER");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造GM工具控制逻辑单元。</T>
   //============================================================
   public FPlatformGmControlLogic(){
   }

   //============================================================
   // <T>构造GM工具控制逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FPlatformGmControlLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FPlatformGmControlUnit find(long recordId){
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
      FPlatformGmControlUnit unit = new FPlatformGmControlUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FPlatformGmControlUnit serach(String whereSql){
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
      FPlatformGmControlUnit unit = new FPlatformGmControlUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FPlatformGmControlUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FPlatformGmControlUnit[] fetch(String whereSql, String orderSql){
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
   public FPlatformGmControlUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FPlatformGmControlUnit[] units = new FPlatformGmControlUnit[count];
      for(int n = 0; n < count; n++){
         FPlatformGmControlUnit unit = new FPlatformGmControlUnit();
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
   public FPlatformGmControlUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FPlatformGmControlUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`OPERATE_CD`");
      sql.append(",`CMD`");
      sql.append(",`ROLE_ID`");
      sql.append(",`LABEL`");
      sql.append(",`BEGIN_DATE`");
      sql.append(",`END_DATE`");
      sql.append(",`PARAM1`");
      sql.append(",`PARAM2`");
      sql.append(",`PARAM3`");
      sql.append(",`CONTROL_USER`");
      sql.append(",`CREATE_USER_ID`");
      sql.append(",`CREATE_DATE`");
      sql.append(",`UPDATE_USER_ID`");
      sql.append(",`UPDATE_DATE`");
      sql.append(") VALUES(");
      sql.append(unit.ovld());
      sql.append(',');
      sql.append(unit.uniqueId());
      sql.append(',');
      sql.append(unit.operateCd());
      sql.append(',');
      sql.append(unit.cmd());
      sql.append(',');
      sql.append(unit.roleId());
      sql.append(',');
      if(RString.isEmpty(unit.label())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.label()));
         sql.append('\'');
      }
      sql.append(',');
      if(unit.beginDate().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.beginDate().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      if(unit.endDate().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.endDate().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      sql.append(unit.param1());
      sql.append(',');
      if(RString.isEmpty(unit.param2())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.param2()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.param3())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.param3()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.controlUser())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.controlUser()));
         sql.append('\'');
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
   public boolean doUpdate(FPlatformGmControlUnit unit, long recordId){
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
      if(unit.isOperateCdChanged()){
         sql.append(",`OPERATE_CD`=");
         sql.append(unit.operateCd());
      }
      if(unit.isCmdChanged()){
         sql.append(",`CMD`=");
         sql.append(unit.cmd());
      }
      if(unit.isRoleIdChanged()){
         sql.append(",`ROLE_ID`=");
         sql.append(unit.roleId());
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
      if(unit.isBeginDateChanged()){
         sql.append(",`BEGIN_DATE`=");
         if(unit.beginDate().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.beginDate().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isEndDateChanged()){
         sql.append(",`END_DATE`=");
         if(unit.endDate().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.endDate().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isParam1Changed()){
         sql.append(",`PARAM1`=");
         sql.append(unit.param1());
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
      if(unit.isParam3Changed()){
         sql.append(",`PARAM3`=");
         if(RString.isEmpty(unit.param3())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.param3()));
            sql.append("'");
         }
      }
      if(unit.isControlUserChanged()){
         sql.append(",`CONTROL_USER`=");
         if(RString.isEmpty(unit.controlUser())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.controlUser()));
            sql.append("'");
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