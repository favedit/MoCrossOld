package com.zqinet.logic.data.logger;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>四声血脉变更日志逻辑。</T>
//============================================================
public class FLoggerFourGodLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("LOG_ROLE_FOUR_GOD");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段角色编号的定义。
   public final static FLogicField FieldRoleId = new FLogicField("ROLE_ID");

   // 字段四圣血脉类型的定义。
   public final static FLogicField FieldFourGodType = new FLogicField("FOUR_GOD_TYPE");

   // 字段获得的HP值的定义。
   public final static FLogicField FieldHp = new FLogicField("HP");

   // 字段获得物攻值的定义。
   public final static FLogicField FieldAttackPhysical = new FLogicField("ATTACK_PHYSICAL");

   // 字段获得法攻值的定义。
   public final static FLogicField FieldAttackMagic = new FLogicField("ATTACK_MAGIC");

   // 字段获得物防值的定义。
   public final static FLogicField FieldDefensePhysical = new FLogicField("DEFENSE_PHYSICAL");

   // 字段获得的法防值的定义。
   public final static FLogicField FieldDefenseMagic = new FLogicField("DEFENSE_MAGIC");

   // 字段操作次数的定义。
   public final static FLogicField FieldOperCount = new FLogicField("OPER_COUNT");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造四声血脉变更日志逻辑单元。</T>
   //============================================================
   public FLoggerFourGodLogic(){
   }

   //============================================================
   // <T>构造四声血脉变更日志逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FLoggerFourGodLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FLoggerFourGodUnit find(long recordId){
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
      FLoggerFourGodUnit unit = new FLoggerFourGodUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FLoggerFourGodUnit serach(String whereSql){
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
      FLoggerFourGodUnit unit = new FLoggerFourGodUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FLoggerFourGodUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FLoggerFourGodUnit[] fetch(String whereSql, String orderSql){
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
   public FLoggerFourGodUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FLoggerFourGodUnit[] units = new FLoggerFourGodUnit[count];
      for(int n = 0; n < count; n++){
         FLoggerFourGodUnit unit = new FLoggerFourGodUnit();
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
   public FLoggerFourGodUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FLoggerFourGodUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`ROLE_ID`");
      sql.append(",`FOUR_GOD_TYPE`");
      sql.append(",`HP`");
      sql.append(",`ATTACK_PHYSICAL`");
      sql.append(",`ATTACK_MAGIC`");
      sql.append(",`DEFENSE_PHYSICAL`");
      sql.append(",`DEFENSE_MAGIC`");
      sql.append(",`OPER_COUNT`");
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
      sql.append(unit.fourGodType());
      sql.append(',');
      sql.append(unit.hp());
      sql.append(',');
      sql.append(unit.attackPhysical());
      sql.append(',');
      sql.append(unit.attackMagic());
      sql.append(',');
      sql.append(unit.defensePhysical());
      sql.append(',');
      sql.append(unit.defenseMagic());
      sql.append(',');
      sql.append(unit.operCount());
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
   public boolean doUpdate(FLoggerFourGodUnit unit, long recordId){
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
      if(unit.isFourGodTypeChanged()){
         sql.append(",`FOUR_GOD_TYPE`=");
         sql.append(unit.fourGodType());
      }
      if(unit.isHpChanged()){
         sql.append(",`HP`=");
         sql.append(unit.hp());
      }
      if(unit.isAttackPhysicalChanged()){
         sql.append(",`ATTACK_PHYSICAL`=");
         sql.append(unit.attackPhysical());
      }
      if(unit.isAttackMagicChanged()){
         sql.append(",`ATTACK_MAGIC`=");
         sql.append(unit.attackMagic());
      }
      if(unit.isDefensePhysicalChanged()){
         sql.append(",`DEFENSE_PHYSICAL`=");
         sql.append(unit.defensePhysical());
      }
      if(unit.isDefenseMagicChanged()){
         sql.append(",`DEFENSE_MAGIC`=");
         sql.append(unit.defenseMagic());
      }
      if(unit.isOperCountChanged()){
         sql.append(",`OPER_COUNT`=");
         sql.append(unit.operCount());
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