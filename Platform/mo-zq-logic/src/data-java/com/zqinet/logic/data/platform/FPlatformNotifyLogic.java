package com.zqinet.logic.data.platform;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>通知逻辑。</T>
//============================================================
public class FPlatformNotifyLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("PF_NOTIFY");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段编号的定义。
   public final static FLogicField FieldId = new FLogicField("ID");

   // 字段编码的定义。
   public final static FLogicField FieldCode = new FLogicField("CODE");

   // 字段名称的定义。
   public final static FLogicField FieldName = new FLogicField("NAME");

   // 字段标签的定义。
   public final static FLogicField FieldLabel = new FLogicField("LABEL");

   // 字段类型编号的定义。
   public final static FLogicField FieldTypeId = new FLogicField("TYPE_ID");

   // 字段状态的定义。
   public final static FLogicField FieldStatusCd = new FLogicField("STATUS_CD");

   // 字段优先级的定义。
   public final static FLogicField FieldProprityCd = new FLogicField("PROPRITY_CD");

   // 字段开始时间的定义。
   public final static FLogicField FieldBeginDate = new FLogicField("BEGIN_DATE");

   // 字段结束时间的定义。
   public final static FLogicField FieldEndDate = new FLogicField("END_DATE");

   // 字段访问数据库时间的定义。
   public final static FLogicField FieldInviteStorageDate = new FLogicField("INVITE_STORAGE_DATE");

   // 字段间隔时间的定义。
   public final static FLogicField FieldIntervalData = new FLogicField("INTERVAL_DATA");

   // 字段内容的定义。
   public final static FLogicField FieldContent = new FLogicField("CONTENT");

   // 字段备注的定义。
   public final static FLogicField FieldNote = new FLogicField("NOTE");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造通知逻辑单元。</T>
   //============================================================
   public FPlatformNotifyLogic(){
   }

   //============================================================
   // <T>构造通知逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FPlatformNotifyLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FPlatformNotifyUnit find(long recordId){
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
      FPlatformNotifyUnit unit = new FPlatformNotifyUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FPlatformNotifyUnit serach(String whereSql){
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
      FPlatformNotifyUnit unit = new FPlatformNotifyUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FPlatformNotifyUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FPlatformNotifyUnit[] fetch(String whereSql, String orderSql){
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
   public FPlatformNotifyUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FPlatformNotifyUnit[] units = new FPlatformNotifyUnit[count];
      for(int n = 0; n < count; n++){
         FPlatformNotifyUnit unit = new FPlatformNotifyUnit();
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
   public FPlatformNotifyUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FPlatformNotifyUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`ID`");
      sql.append(",`CODE`");
      sql.append(",`NAME`");
      sql.append(",`LABEL`");
      sql.append(",`TYPE_ID`");
      sql.append(",`STATUS_CD`");
      sql.append(",`PROPRITY_CD`");
      sql.append(",`BEGIN_DATE`");
      sql.append(",`END_DATE`");
      sql.append(",`INVITE_STORAGE_DATE`");
      sql.append(",`INTERVAL_DATA`");
      sql.append(",`CONTENT`");
      sql.append(",`NOTE`");
      sql.append(",`CREATE_USER_ID`");
      sql.append(",`CREATE_DATE`");
      sql.append(",`UPDATE_USER_ID`");
      sql.append(",`UPDATE_DATE`");
      sql.append(") VALUES(");
      sql.append(unit.ovld());
      sql.append(',');
      sql.append(unit.uniqueId());
      sql.append(',');
      sql.append(unit.id());
      sql.append(',');
      if(RString.isEmpty(unit.code())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.code()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.name())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.name()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.label())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.label()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.typeId());
      sql.append(',');
      sql.append(unit.statusCd());
      sql.append(',');
      sql.append(unit.proprityCd());
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
      if(unit.inviteStorageDate().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.inviteStorageDate().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      sql.append(unit.intervalData());
      sql.append(',');
      if(RString.isEmpty(unit.content())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.content()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.note())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.note()));
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
   public boolean doUpdate(FPlatformNotifyUnit unit, long recordId){
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
      if(unit.isIdChanged()){
         sql.append(",`ID`=");
         sql.append(unit.id());
      }
      if(unit.isCodeChanged()){
         sql.append(",`CODE`=");
         if(RString.isEmpty(unit.code())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.code()));
            sql.append("'");
         }
      }
      if(unit.isNameChanged()){
         sql.append(",`NAME`=");
         if(RString.isEmpty(unit.name())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.name()));
            sql.append("'");
         }
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
      if(unit.isTypeIdChanged()){
         sql.append(",`TYPE_ID`=");
         sql.append(unit.typeId());
      }
      if(unit.isStatusCdChanged()){
         sql.append(",`STATUS_CD`=");
         sql.append(unit.statusCd());
      }
      if(unit.isProprityCdChanged()){
         sql.append(",`PROPRITY_CD`=");
         sql.append(unit.proprityCd());
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
      if(unit.isInviteStorageDateChanged()){
         sql.append(",`INVITE_STORAGE_DATE`=");
         if(unit.inviteStorageDate().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.inviteStorageDate().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isIntervalDataChanged()){
         sql.append(",`INTERVAL_DATA`=");
         sql.append(unit.intervalData());
      }
      if(unit.isContentChanged()){
         sql.append(",`CONTENT`=");
         if(RString.isEmpty(unit.content())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.content()));
            sql.append("'");
         }
      }
      if(unit.isNoteChanged()){
         sql.append(",`NOTE`=");
         if(RString.isEmpty(unit.note())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.note()));
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