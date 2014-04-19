package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>擂台存储表逻辑。</T>
//============================================================
public class FGameRoleArenaLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("GM_ROLE_ARENA");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段角色编号的定义。
   public final static FLogicField FieldRoleId = new FLogicField("ROLE_ID");

   // 字段胜利场数的定义。
   public final static FLogicField FieldWin = new FLogicField("WIN");

   // 字段连杀的定义。
   public final static FLogicField FieldComboKill = new FLogicField("COMBO_KILL");

   // 字段积分的定义。
   public final static FLogicField FieldPoint = new FLogicField("POINT");

   // 字段师门竞技排名的定义。
   public final static FLogicField FieldMetierArenaRank = new FLogicField("METIER_ARENA_RANK");

   // 字段战力的定义。
   public final static FLogicField FieldGradePoint = new FLogicField("GRADE_POINT");

   // 字段等级的定义。
   public final static FLogicField FieldLevel = new FLogicField("LEVEL");

   // 字段名称的定义。
   public final static FLogicField FieldLabel = new FLogicField("LABEL");

   // 字段师门竞技战况的定义。
   public final static FLogicField FieldMetierArenaResult = new FLogicField("METIER_ARENA_RESULT");

   // 字段头像编号的定义。
   public final static FLogicField FieldStyleIconTid = new FLogicField("STYLE_ICON_TID");

   // 字段职业模板编号的定义。
   public final static FLogicField FieldMetierTid = new FLogicField("METIER_TID");

   // 字段师门竞技积分的定义。
   public final static FLogicField FieldMetierArenaPoint = new FLogicField("METIER_ARENA_POINT");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造擂台存储表逻辑单元。</T>
   //============================================================
   public FGameRoleArenaLogic(){
   }

   //============================================================
   // <T>构造擂台存储表逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FGameRoleArenaLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FGameRoleArenaUnit find(long recordId){
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
      FGameRoleArenaUnit unit = new FGameRoleArenaUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FGameRoleArenaUnit serach(String whereSql){
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
      FGameRoleArenaUnit unit = new FGameRoleArenaUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FGameRoleArenaUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FGameRoleArenaUnit[] fetch(String whereSql, String orderSql){
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
   public FGameRoleArenaUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FGameRoleArenaUnit[] units = new FGameRoleArenaUnit[count];
      for(int n = 0; n < count; n++){
         FGameRoleArenaUnit unit = new FGameRoleArenaUnit();
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
   public FGameRoleArenaUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FGameRoleArenaUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`ROLE_ID`");
      sql.append(",`WIN`");
      sql.append(",`COMBO_KILL`");
      sql.append(",`POINT`");
      sql.append(",`METIER_ARENA_RANK`");
      sql.append(",`GRADE_POINT`");
      sql.append(",`LEVEL`");
      sql.append(",`LABEL`");
      sql.append(",`METIER_ARENA_RESULT`");
      sql.append(",`STYLE_ICON_TID`");
      sql.append(",`METIER_TID`");
      sql.append(",`METIER_ARENA_POINT`");
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
      sql.append(unit.win());
      sql.append(',');
      sql.append(unit.comboKill());
      sql.append(',');
      sql.append(unit.point());
      sql.append(',');
      sql.append(unit.metierArenaRank());
      sql.append(',');
      sql.append(unit.gradePoint());
      sql.append(',');
      sql.append(unit.level());
      sql.append(',');
      if(RString.isEmpty(unit.label())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.label()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.metierArenaResult())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.metierArenaResult()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.styleIconTid());
      sql.append(',');
      sql.append(unit.metierTid());
      sql.append(',');
      sql.append(unit.metierArenaPoint());
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
   public boolean doUpdate(FGameRoleArenaUnit unit, long recordId){
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
      if(unit.isWinChanged()){
         sql.append(",`WIN`=");
         sql.append(unit.win());
      }
      if(unit.isComboKillChanged()){
         sql.append(",`COMBO_KILL`=");
         sql.append(unit.comboKill());
      }
      if(unit.isPointChanged()){
         sql.append(",`POINT`=");
         sql.append(unit.point());
      }
      if(unit.isMetierArenaRankChanged()){
         sql.append(",`METIER_ARENA_RANK`=");
         sql.append(unit.metierArenaRank());
      }
      if(unit.isGradePointChanged()){
         sql.append(",`GRADE_POINT`=");
         sql.append(unit.gradePoint());
      }
      if(unit.isLevelChanged()){
         sql.append(",`LEVEL`=");
         sql.append(unit.level());
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
      if(unit.isMetierArenaResultChanged()){
         sql.append(",`METIER_ARENA_RESULT`=");
         if(RString.isEmpty(unit.metierArenaResult())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.metierArenaResult()));
            sql.append("'");
         }
      }
      if(unit.isStyleIconTidChanged()){
         sql.append(",`STYLE_ICON_TID`=");
         sql.append(unit.styleIconTid());
      }
      if(unit.isMetierTidChanged()){
         sql.append(",`METIER_TID`=");
         sql.append(unit.metierTid());
      }
      if(unit.isMetierArenaPointChanged()){
         sql.append(",`METIER_ARENA_POINT`=");
         sql.append(unit.metierArenaPoint());
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