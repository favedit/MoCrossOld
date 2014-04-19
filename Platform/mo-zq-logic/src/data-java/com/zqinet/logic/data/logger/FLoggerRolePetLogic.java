package com.zqinet.logic.data.logger;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>角色宠物变更日志逻辑。</T>
//============================================================
public class FLoggerRolePetLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("LOG_ROLE_PET");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段角色编号的定义。
   public final static FLogicField FieldRoleId = new FLogicField("ROLE_ID");

   // 字段宠物编号的定义。
   public final static FLogicField FieldPetId = new FLogicField("PET_ID");

   // 字段变更类型的定义。
   public final static FLogicField FieldChangeType = new FLogicField("CHANGE_TYPE");

   // 字段变更事件的定义。
   public final static FLogicField FieldObtainEvent = new FLogicField("OBTAIN_EVENT");

   // 字段获得类型的定义。
   public final static FLogicField FieldObtainType = new FLogicField("OBTAIN_TYPE");

   // 字段变化的值的定义。
   public final static FLogicField FieldValue = new FLogicField("VALUE");

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

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造角色宠物变更日志逻辑单元。</T>
   //============================================================
   public FLoggerRolePetLogic(){
   }

   //============================================================
   // <T>构造角色宠物变更日志逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FLoggerRolePetLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FLoggerRolePetUnit find(long recordId){
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
      FLoggerRolePetUnit unit = new FLoggerRolePetUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FLoggerRolePetUnit serach(String whereSql){
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
      FLoggerRolePetUnit unit = new FLoggerRolePetUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FLoggerRolePetUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FLoggerRolePetUnit[] fetch(String whereSql, String orderSql){
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
   public FLoggerRolePetUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FLoggerRolePetUnit[] units = new FLoggerRolePetUnit[count];
      for(int n = 0; n < count; n++){
         FLoggerRolePetUnit unit = new FLoggerRolePetUnit();
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
   public FLoggerRolePetUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FLoggerRolePetUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`ROLE_ID`");
      sql.append(",`PET_ID`");
      sql.append(",`CHANGE_TYPE`");
      sql.append(",`OBTAIN_EVENT`");
      sql.append(",`OBTAIN_TYPE`");
      sql.append(",`VALUE`");
      sql.append(",`PARAM1`");
      sql.append(",`PARAM2`");
      sql.append(",`PARAM3`");
      sql.append(",`PARAM4`");
      sql.append(",`PARAM5`");
      sql.append(",`PARAM6`");
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
      sql.append(unit.petId());
      sql.append(',');
      sql.append(unit.changeType());
      sql.append(',');
      sql.append(unit.obtainEvent());
      sql.append(',');
      sql.append(unit.obtainType());
      sql.append(',');
      sql.append(unit.value());
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
   public boolean doUpdate(FLoggerRolePetUnit unit, long recordId){
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
      if(unit.isPetIdChanged()){
         sql.append(",`PET_ID`=");
         sql.append(unit.petId());
      }
      if(unit.isChangeTypeChanged()){
         sql.append(",`CHANGE_TYPE`=");
         sql.append(unit.changeType());
      }
      if(unit.isObtainEventChanged()){
         sql.append(",`OBTAIN_EVENT`=");
         sql.append(unit.obtainEvent());
      }
      if(unit.isObtainTypeChanged()){
         sql.append(",`OBTAIN_TYPE`=");
         sql.append(unit.obtainType());
      }
      if(unit.isValueChanged()){
         sql.append(",`VALUE`=");
         sql.append(unit.value());
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