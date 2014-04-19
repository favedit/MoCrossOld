package com.zqinet.logic.data.logger;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>角色装备数据变化日志逻辑。</T>
//============================================================
public class FLoggerRoleEquipLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("LOG_ROLE_EQUIP");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段角色编号的定义。
   public final static FLogicField FieldRoleId = new FLogicField("ROLE_ID");

   // 字段装备编号的定义。
   public final static FLogicField FieldEquipTid = new FLogicField("EQUIP_TID");

   // 字段装备级别的定义。
   public final static FLogicField FieldEquipLevel = new FLogicField("EQUIP_LEVEL");

   // 字段装备品质的定义。
   public final static FLogicField FieldEquipQuality = new FLogicField("EQUIP_QUALITY");

   // 字段装备星级的定义。
   public final static FLogicField FieldEquipStarLevel = new FLogicField("EQUIP_STAR_LEVEL");

   // 字段操作类型的定义。
   public final static FLogicField FieldOperationType = new FLogicField("OPERATION_TYPE");

   // 字段参数1的定义。
   public final static FLogicField FieldParam1 = new FLogicField("PARAM1");

   // 字段参数2的定义。
   public final static FLogicField FieldParam2 = new FLogicField("PARAM2");

   // 字段参数3的定义。
   public final static FLogicField FieldParam3 = new FLogicField("PARAM3");

   // 字段参数4的定义。
   public final static FLogicField FieldParam4 = new FLogicField("PARAM4");

   // 字段参数5的定义。
   public final static FLogicField FieldParam5 = new FLogicField("PARAM5");

   // 字段参数6的定义。
   public final static FLogicField FieldParam6 = new FLogicField("PARAM6");

   // 字段参数7的定义。
   public final static FLogicField FieldParam7 = new FLogicField("PARAM7");

   // 字段参数8的定义。
   public final static FLogicField FieldParam8 = new FLogicField("PARAM8");

   // 字段参数9的定义。
   public final static FLogicField FieldParam9 = new FLogicField("PARAM9");

   // 字段参数10的定义。
   public final static FLogicField FieldParam10 = new FLogicField("PARAM10");

   // 字段参数11的定义。
   public final static FLogicField FieldParam11 = new FLogicField("PARAM11");

   // 字段参数12的定义。
   public final static FLogicField FieldParam12 = new FLogicField("PARAM12");

   // 字段参数13的定义。
   public final static FLogicField FieldParam13 = new FLogicField("PARAM13");

   // 字段参数14的定义。
   public final static FLogicField FieldParam14 = new FLogicField("PARAM14");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造角色装备数据变化日志逻辑单元。</T>
   //============================================================
   public FLoggerRoleEquipLogic(){
   }

   //============================================================
   // <T>构造角色装备数据变化日志逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FLoggerRoleEquipLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FLoggerRoleEquipUnit find(long recordId){
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
      FLoggerRoleEquipUnit unit = new FLoggerRoleEquipUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FLoggerRoleEquipUnit serach(String whereSql){
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
      FLoggerRoleEquipUnit unit = new FLoggerRoleEquipUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FLoggerRoleEquipUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FLoggerRoleEquipUnit[] fetch(String whereSql, String orderSql){
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
   public FLoggerRoleEquipUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FLoggerRoleEquipUnit[] units = new FLoggerRoleEquipUnit[count];
      for(int n = 0; n < count; n++){
         FLoggerRoleEquipUnit unit = new FLoggerRoleEquipUnit();
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
   public FLoggerRoleEquipUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FLoggerRoleEquipUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`ROLE_ID`");
      sql.append(",`EQUIP_TID`");
      sql.append(",`EQUIP_LEVEL`");
      sql.append(",`EQUIP_QUALITY`");
      sql.append(",`EQUIP_STAR_LEVEL`");
      sql.append(",`OPERATION_TYPE`");
      sql.append(",`PARAM1`");
      sql.append(",`PARAM2`");
      sql.append(",`PARAM3`");
      sql.append(",`PARAM4`");
      sql.append(",`PARAM5`");
      sql.append(",`PARAM6`");
      sql.append(",`PARAM7`");
      sql.append(",`PARAM8`");
      sql.append(",`PARAM9`");
      sql.append(",`PARAM10`");
      sql.append(",`PARAM11`");
      sql.append(",`PARAM12`");
      sql.append(",`PARAM13`");
      sql.append(",`PARAM14`");
      sql.append(",`CREATE_USER_ID`");
      sql.append(",`CREATE_DATE`");
      sql.append(",`UPDATE_USER_ID`");
      sql.append(",`UPDATE_DATE`");
      sql.append(") VALUES(");
      sql.append(unit.ovld());
      sql.append(',');
      sql.append(unit.uniqueId());
      sql.append(',');
      sql.append(unit.roleId());
      sql.append(',');
      sql.append(unit.equipTid());
      sql.append(',');
      sql.append(unit.equipLevel());
      sql.append(',');
      sql.append(unit.equipQuality());
      sql.append(',');
      sql.append(unit.equipStarLevel());
      sql.append(',');
      sql.append(unit.operationType());
      sql.append(',');
      sql.append(unit.param1());
      sql.append(',');
      sql.append(unit.param2());
      sql.append(',');
      sql.append(unit.param3());
      sql.append(',');
      sql.append(unit.param4());
      sql.append(',');
      sql.append(unit.param5());
      sql.append(',');
      sql.append(unit.param6());
      sql.append(',');
      sql.append(unit.param7());
      sql.append(',');
      sql.append(unit.param8());
      sql.append(',');
      sql.append(unit.param9());
      sql.append(',');
      sql.append(unit.param10());
      sql.append(',');
      sql.append(unit.param11());
      sql.append(',');
      sql.append(unit.param12());
      sql.append(',');
      sql.append(unit.param13());
      sql.append(',');
      sql.append(unit.param14());
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
   public boolean doUpdate(FLoggerRoleEquipUnit unit, long recordId){
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
      if(unit.isEquipTidChanged()){
         sql.append(",`EQUIP_TID`=");
         sql.append(unit.equipTid());
      }
      if(unit.isEquipLevelChanged()){
         sql.append(",`EQUIP_LEVEL`=");
         sql.append(unit.equipLevel());
      }
      if(unit.isEquipQualityChanged()){
         sql.append(",`EQUIP_QUALITY`=");
         sql.append(unit.equipQuality());
      }
      if(unit.isEquipStarLevelChanged()){
         sql.append(",`EQUIP_STAR_LEVEL`=");
         sql.append(unit.equipStarLevel());
      }
      if(unit.isOperationTypeChanged()){
         sql.append(",`OPERATION_TYPE`=");
         sql.append(unit.operationType());
      }
      if(unit.isParam1Changed()){
         sql.append(",`PARAM1`=");
         sql.append(unit.param1());
      }
      if(unit.isParam2Changed()){
         sql.append(",`PARAM2`=");
         sql.append(unit.param2());
      }
      if(unit.isParam3Changed()){
         sql.append(",`PARAM3`=");
         sql.append(unit.param3());
      }
      if(unit.isParam4Changed()){
         sql.append(",`PARAM4`=");
         sql.append(unit.param4());
      }
      if(unit.isParam5Changed()){
         sql.append(",`PARAM5`=");
         sql.append(unit.param5());
      }
      if(unit.isParam6Changed()){
         sql.append(",`PARAM6`=");
         sql.append(unit.param6());
      }
      if(unit.isParam7Changed()){
         sql.append(",`PARAM7`=");
         sql.append(unit.param7());
      }
      if(unit.isParam8Changed()){
         sql.append(",`PARAM8`=");
         sql.append(unit.param8());
      }
      if(unit.isParam9Changed()){
         sql.append(",`PARAM9`=");
         sql.append(unit.param9());
      }
      if(unit.isParam10Changed()){
         sql.append(",`PARAM10`=");
         sql.append(unit.param10());
      }
      if(unit.isParam11Changed()){
         sql.append(",`PARAM11`=");
         sql.append(unit.param11());
      }
      if(unit.isParam12Changed()){
         sql.append(",`PARAM12`=");
         sql.append(unit.param12());
      }
      if(unit.isParam13Changed()){
         sql.append(",`PARAM13`=");
         sql.append(unit.param13());
      }
      if(unit.isParam14Changed()){
         sql.append(",`PARAM14`=");
         sql.append(unit.param14());
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