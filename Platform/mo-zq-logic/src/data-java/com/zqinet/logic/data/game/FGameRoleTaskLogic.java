package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>角色任务信息逻辑。</T>
//============================================================
public class FGameRoleTaskLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("GM_ROLE_TASK");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段角色编号的定义。
   public final static FLogicField FieldRoleId = new FLogicField("ROLE_ID");

   // 字段状态的定义。
   public final static FLogicField FieldStatusCd = new FLogicField("STATUS_CD");

   // 字段标志的定义。
   public final static FLogicField FieldFlags = new FLogicField("FLAGS");

   // 字段任务模板编号的定义。
   public final static FLogicField FieldTaskTid = new FLogicField("TASK_TID");

   // 字段部分模板编号的定义。
   public final static FLogicField FieldPartTid = new FLogicField("PART_TID");

   // 字段接取任务演员模板编号的定义。
   public final static FLogicField FieldBeginActorTid = new FLogicField("BEGIN_ACTOR_TID");

   // 字段完成任务演员模板编号的定义。
   public final static FLogicField FieldEndActorTid = new FLogicField("END_ACTOR_TID");

   // 字段开始条件组模板编号的定义。
   public final static FLogicField FieldAcceptLimitGroupTid = new FLogicField("ACCEPT_LIMIT_GROUP_TID");

   // 字段开始奖励组模板编号的定义。
   public final static FLogicField FieldAcceptRewardGroupTid = new FLogicField("ACCEPT_REWARD_GROUP_TID");

   // 字段完成条件组模板编号的定义。
   public final static FLogicField FieldFinishLimitGroupTid = new FLogicField("FINISH_LIMIT_GROUP_TID");

   // 字段完成奖励组模板编号的定义。
   public final static FLogicField FieldFinishRewardGroupTid = new FLogicField("FINISH_REWARD_GROUP_TID");

   // 字段交付任务条件组模板编号的定义。
   public final static FLogicField FieldDeliverLimitGroupTid = new FLogicField("DELIVER_LIMIT_GROUP_TID");

   // 字段交付任务奖励组编号的定义。
   public final static FLogicField FieldDeliverRewardGroupTid = new FLogicField("DELIVER_REWARD_GROUP_TID");

   // 字段完成次数的定义。
   public final static FLogicField FieldFinishCount = new FLogicField("FINISH_COUNT");

   // 字段内容打包的定义。
   public final static FLogicField FieldContentPack = new FLogicField("CONTENT_PACK");

   // 字段接任务时间的定义。
   public final static FLogicField FieldBeginTaskTime = new FLogicField("BEGIN_TASK_TIME");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造角色任务信息逻辑单元。</T>
   //============================================================
   public FGameRoleTaskLogic(){
   }

   //============================================================
   // <T>构造角色任务信息逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FGameRoleTaskLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FGameRoleTaskUnit find(long recordId){
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
      FGameRoleTaskUnit unit = new FGameRoleTaskUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FGameRoleTaskUnit serach(String whereSql){
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
      FGameRoleTaskUnit unit = new FGameRoleTaskUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FGameRoleTaskUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FGameRoleTaskUnit[] fetch(String whereSql, String orderSql){
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
   public FGameRoleTaskUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FGameRoleTaskUnit[] units = new FGameRoleTaskUnit[count];
      for(int n = 0; n < count; n++){
         FGameRoleTaskUnit unit = new FGameRoleTaskUnit();
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
   public FGameRoleTaskUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FGameRoleTaskUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`ROLE_ID`");
      sql.append(",`STATUS_CD`");
      sql.append(",`FLAGS`");
      sql.append(",`TASK_TID`");
      sql.append(",`PART_TID`");
      sql.append(",`BEGIN_ACTOR_TID`");
      sql.append(",`END_ACTOR_TID`");
      sql.append(",`ACCEPT_LIMIT_GROUP_TID`");
      sql.append(",`ACCEPT_REWARD_GROUP_TID`");
      sql.append(",`FINISH_LIMIT_GROUP_TID`");
      sql.append(",`FINISH_REWARD_GROUP_TID`");
      sql.append(",`DELIVER_LIMIT_GROUP_TID`");
      sql.append(",`DELIVER_REWARD_GROUP_TID`");
      sql.append(",`FINISH_COUNT`");
      sql.append(",`CONTENT_PACK`");
      sql.append(",`BEGIN_TASK_TIME`");
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
      sql.append(unit.statusCd());
      sql.append(',');
      sql.append(unit.flags());
      sql.append(',');
      sql.append(unit.taskTid());
      sql.append(',');
      sql.append(unit.partTid());
      sql.append(',');
      sql.append(unit.beginActorTid());
      sql.append(',');
      sql.append(unit.endActorTid());
      sql.append(',');
      sql.append(unit.acceptLimitGroupTid());
      sql.append(',');
      sql.append(unit.acceptRewardGroupTid());
      sql.append(',');
      sql.append(unit.finishLimitGroupTid());
      sql.append(',');
      sql.append(unit.finishRewardGroupTid());
      sql.append(',');
      sql.append(unit.deliverLimitGroupTid());
      sql.append(',');
      sql.append(unit.deliverRewardGroupTid());
      sql.append(',');
      sql.append(unit.finishCount());
      sql.append(',');
      if(RString.isEmpty(unit.contentPack())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.contentPack()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.beginTaskTime())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.beginTaskTime()));
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
   public boolean doUpdate(FGameRoleTaskUnit unit, long recordId){
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
      if(unit.isStatusCdChanged()){
         sql.append(",`STATUS_CD`=");
         sql.append(unit.statusCd());
      }
      if(unit.isFlagsChanged()){
         sql.append(",`FLAGS`=");
         sql.append(unit.flags());
      }
      if(unit.isTaskTidChanged()){
         sql.append(",`TASK_TID`=");
         sql.append(unit.taskTid());
      }
      if(unit.isPartTidChanged()){
         sql.append(",`PART_TID`=");
         sql.append(unit.partTid());
      }
      if(unit.isBeginActorTidChanged()){
         sql.append(",`BEGIN_ACTOR_TID`=");
         sql.append(unit.beginActorTid());
      }
      if(unit.isEndActorTidChanged()){
         sql.append(",`END_ACTOR_TID`=");
         sql.append(unit.endActorTid());
      }
      if(unit.isAcceptLimitGroupTidChanged()){
         sql.append(",`ACCEPT_LIMIT_GROUP_TID`=");
         sql.append(unit.acceptLimitGroupTid());
      }
      if(unit.isAcceptRewardGroupTidChanged()){
         sql.append(",`ACCEPT_REWARD_GROUP_TID`=");
         sql.append(unit.acceptRewardGroupTid());
      }
      if(unit.isFinishLimitGroupTidChanged()){
         sql.append(",`FINISH_LIMIT_GROUP_TID`=");
         sql.append(unit.finishLimitGroupTid());
      }
      if(unit.isFinishRewardGroupTidChanged()){
         sql.append(",`FINISH_REWARD_GROUP_TID`=");
         sql.append(unit.finishRewardGroupTid());
      }
      if(unit.isDeliverLimitGroupTidChanged()){
         sql.append(",`DELIVER_LIMIT_GROUP_TID`=");
         sql.append(unit.deliverLimitGroupTid());
      }
      if(unit.isDeliverRewardGroupTidChanged()){
         sql.append(",`DELIVER_REWARD_GROUP_TID`=");
         sql.append(unit.deliverRewardGroupTid());
      }
      if(unit.isFinishCountChanged()){
         sql.append(",`FINISH_COUNT`=");
         sql.append(unit.finishCount());
      }
      if(unit.isContentPackChanged()){
         sql.append(",`CONTENT_PACK`=");
         if(RString.isEmpty(unit.contentPack())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.contentPack()));
            sql.append("'");
         }
      }
      if(unit.isBeginTaskTimeChanged()){
         sql.append(",`BEGIN_TASK_TIME`=");
         if(RString.isEmpty(unit.beginTaskTime())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.beginTaskTime()));
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