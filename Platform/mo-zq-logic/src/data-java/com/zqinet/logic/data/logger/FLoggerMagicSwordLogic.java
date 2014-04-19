package com.zqinet.logic.data.logger;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>操作神剑日志逻辑。</T>
//============================================================
public class FLoggerMagicSwordLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("LOG_MAGIC_SWORD");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段角色编号的定义。
   public final static FLogicField FieldRoleId = new FLogicField("ROLE_ID");

   // 字段神剑编号的定义。
   public final static FLogicField FieldSwordId = new FLogicField("SWORD_ID");

   // 字段培养前级别的定义。
   public final static FLogicField FieldOldLevel = new FLogicField("OLD_LEVEL");

   // 字段培养前品级的定义。
   public final static FLogicField FieldOldQuality = new FLogicField("OLD_QUALITY");

   // 字段操作类型的定义。
   public final static FLogicField FieldOperationType = new FLogicField("OPERATION_TYPE");

   // 字段操作后级别的定义。
   public final static FLogicField FieldNewLevel = new FLogicField("NEW_LEVEL");

   // 字段操作后神剑品质的定义。
   public final static FLogicField FieldNewQuality = new FLogicField("NEW_QUALITY");

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
   // <T>构造操作神剑日志逻辑单元。</T>
   //============================================================
   public FLoggerMagicSwordLogic(){
   }

   //============================================================
   // <T>构造操作神剑日志逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FLoggerMagicSwordLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FLoggerMagicSwordUnit find(long recordId){
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
      FLoggerMagicSwordUnit unit = new FLoggerMagicSwordUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FLoggerMagicSwordUnit serach(String whereSql){
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
      FLoggerMagicSwordUnit unit = new FLoggerMagicSwordUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FLoggerMagicSwordUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FLoggerMagicSwordUnit[] fetch(String whereSql, String orderSql){
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
   public FLoggerMagicSwordUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FLoggerMagicSwordUnit[] units = new FLoggerMagicSwordUnit[count];
      for(int n = 0; n < count; n++){
         FLoggerMagicSwordUnit unit = new FLoggerMagicSwordUnit();
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
   public FLoggerMagicSwordUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FLoggerMagicSwordUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`ROLE_ID`");
      sql.append(",`SWORD_ID`");
      sql.append(",`OLD_LEVEL`");
      sql.append(",`OLD_QUALITY`");
      sql.append(",`OPERATION_TYPE`");
      sql.append(",`NEW_LEVEL`");
      sql.append(",`NEW_QUALITY`");
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
      sql.append(unit.swordId());
      sql.append(',');
      sql.append(unit.oldLevel());
      sql.append(',');
      sql.append(unit.oldQuality());
      sql.append(',');
      sql.append(unit.operationType());
      sql.append(',');
      sql.append(unit.newLevel());
      sql.append(',');
      sql.append(unit.newQuality());
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
   public boolean doUpdate(FLoggerMagicSwordUnit unit, long recordId){
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
      if(unit.isSwordIdChanged()){
         sql.append(",`SWORD_ID`=");
         sql.append(unit.swordId());
      }
      if(unit.isOldLevelChanged()){
         sql.append(",`OLD_LEVEL`=");
         sql.append(unit.oldLevel());
      }
      if(unit.isOldQualityChanged()){
         sql.append(",`OLD_QUALITY`=");
         sql.append(unit.oldQuality());
      }
      if(unit.isOperationTypeChanged()){
         sql.append(",`OPERATION_TYPE`=");
         sql.append(unit.operationType());
      }
      if(unit.isNewLevelChanged()){
         sql.append(",`NEW_LEVEL`=");
         sql.append(unit.newLevel());
      }
      if(unit.isNewQualityChanged()){
         sql.append(",`NEW_QUALITY`=");
         sql.append(unit.newQuality());
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