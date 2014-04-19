package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>角色关系信息逻辑。</T>
//============================================================
public class FGameRoleRelationLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("GM_ROLE_RELATION");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段角色编号的定义。
   public final static FLogicField FieldRoleId = new FLogicField("ROLE_ID");

   // 字段关系角色编号的定义。
   public final static FLogicField FieldRelationRoleId = new FLogicField("RELATION_ROLE_ID");

   // 字段关系类型的定义。
   public final static FLogicField FieldRelationCd = new FLogicField("RELATION_CD");

   // 字段亲密度的定义。
   public final static FLogicField FieldCloseValue = new FLogicField("CLOSE_VALUE");

   // 字段侠缘值的定义。
   public final static FLogicField FieldKindValue = new FLogicField("KIND_VALUE");

   // 字段亲密度次数的定义。
   public final static FLogicField FieldCloseValueCount = new FLogicField("CLOSE_VALUE_COUNT");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造角色关系信息逻辑单元。</T>
   //============================================================
   public FGameRoleRelationLogic(){
   }

   //============================================================
   // <T>构造角色关系信息逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FGameRoleRelationLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FGameRoleRelationUnit find(long recordId){
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
      FGameRoleRelationUnit unit = new FGameRoleRelationUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FGameRoleRelationUnit serach(String whereSql){
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
      FGameRoleRelationUnit unit = new FGameRoleRelationUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FGameRoleRelationUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FGameRoleRelationUnit[] fetch(String whereSql, String orderSql){
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
   public FGameRoleRelationUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FGameRoleRelationUnit[] units = new FGameRoleRelationUnit[count];
      for(int n = 0; n < count; n++){
         FGameRoleRelationUnit unit = new FGameRoleRelationUnit();
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
   public FGameRoleRelationUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FGameRoleRelationUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`ROLE_ID`");
      sql.append(",`RELATION_ROLE_ID`");
      sql.append(",`RELATION_CD`");
      sql.append(",`CLOSE_VALUE`");
      sql.append(",`KIND_VALUE`");
      sql.append(",`CLOSE_VALUE_COUNT`");
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
      sql.append(unit.relationRoleId());
      sql.append(',');
      sql.append(unit.relationCd());
      sql.append(',');
      sql.append(unit.closeValue());
      sql.append(',');
      sql.append(unit.kindValue());
      sql.append(',');
      sql.append(unit.closeValueCount());
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
   public boolean doUpdate(FGameRoleRelationUnit unit, long recordId){
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
      if(unit.isRelationRoleIdChanged()){
         sql.append(",`RELATION_ROLE_ID`=");
         sql.append(unit.relationRoleId());
      }
      if(unit.isRelationCdChanged()){
         sql.append(",`RELATION_CD`=");
         sql.append(unit.relationCd());
      }
      if(unit.isCloseValueChanged()){
         sql.append(",`CLOSE_VALUE`=");
         sql.append(unit.closeValue());
      }
      if(unit.isKindValueChanged()){
         sql.append(",`KIND_VALUE`=");
         sql.append(unit.kindValue());
      }
      if(unit.isCloseValueCountChanged()){
         sql.append(",`CLOSE_VALUE_COUNT`=");
         sql.append(unit.closeValueCount());
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