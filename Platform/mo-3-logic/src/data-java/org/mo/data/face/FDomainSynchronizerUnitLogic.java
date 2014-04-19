package org.mo.data.face;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>主域同步单元逻辑。</T>
//============================================================
public class FDomainSynchronizerUnitLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("DM_SYNCHRONIZER_UNIT");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段代码的定义。
   public final static FLogicField FieldCode = new FLogicField("CODE");

   // 字段分组名称的定义。
   public final static FLogicField FieldSynchronizerName = new FLogicField("SYNCHRONIZER_NAME");

   // 字段名称的定义。
   public final static FLogicField FieldName = new FLogicField("NAME");

   // 字段标签的定义。
   public final static FLogicField FieldLabel = new FLogicField("LABEL");

   // 字段来源数据名称的定义。
   public final static FLogicField FieldSourceDataName = new FLogicField("SOURCE_DATA_NAME");

   // 字段目标数据名称的定义。
   public final static FLogicField FieldTargetDataName = new FLogicField("TARGET_DATA_NAME");

   // 字段记录日期的定义。
   public final static FLogicField FieldRecordDate = new FLogicField("RECORD_DATE");

   // 字段同步记录总数的定义。
   public final static FLogicField FieldRecordTotal = new FLogicField("RECORD_TOTAL");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造主域同步单元逻辑单元。</T>
   //============================================================
   public FDomainSynchronizerUnitLogic(){
   }

   //============================================================
   // <T>构造主域同步单元逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FDomainSynchronizerUnitLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FDomainSynchronizerUnitUnit find(long recordId){
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
      FDomainSynchronizerUnitUnit unit = new FDomainSynchronizerUnitUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FDomainSynchronizerUnitUnit serach(String whereSql){
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
      FDomainSynchronizerUnitUnit unit = new FDomainSynchronizerUnitUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FDomainSynchronizerUnitUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FDomainSynchronizerUnitUnit[] fetch(String whereSql, String orderSql){
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
   public FDomainSynchronizerUnitUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FDomainSynchronizerUnitUnit[] units = new FDomainSynchronizerUnitUnit[count];
      for(int n = 0; n < count; n++){
         FDomainSynchronizerUnitUnit unit = new FDomainSynchronizerUnitUnit();
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
   public FDomainSynchronizerUnitUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FDomainSynchronizerUnitUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`CODE`");
      sql.append(",`SYNCHRONIZER_NAME`");
      sql.append(",`NAME`");
      sql.append(",`LABEL`");
      sql.append(",`SOURCE_DATA_NAME`");
      sql.append(",`TARGET_DATA_NAME`");
      sql.append(",`RECORD_DATE`");
      sql.append(",`RECORD_TOTAL`");
      sql.append(",`CREATE_USER_ID`");
      sql.append(",`CREATE_DATE`");
      sql.append(",`UPDATE_USER_ID`");
      sql.append(",`UPDATE_DATE`");
      sql.append(") VALUES(");
      sql.append(unit.ovld());
      sql.append(',');
      sql.append(unit.uniqueId());
      sql.append(',');
      if(RString.isEmpty(unit.code())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.code()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.synchronizerName())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.synchronizerName()));
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
      if(RString.isEmpty(unit.sourceDataName())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.sourceDataName()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.targetDataName())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.targetDataName()));
         sql.append('\'');
      }
      sql.append(',');
      if(unit.recordDate().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.recordDate().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      sql.append(unit.recordTotal());
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
   public boolean doUpdate(FDomainSynchronizerUnitUnit unit, long recordId){
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
      if(unit.isSynchronizerNameChanged()){
         sql.append(",`SYNCHRONIZER_NAME`=");
         if(RString.isEmpty(unit.synchronizerName())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.synchronizerName()));
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
      if(unit.isSourceDataNameChanged()){
         sql.append(",`SOURCE_DATA_NAME`=");
         if(RString.isEmpty(unit.sourceDataName())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.sourceDataName()));
            sql.append("'");
         }
      }
      if(unit.isTargetDataNameChanged()){
         sql.append(",`TARGET_DATA_NAME`=");
         if(RString.isEmpty(unit.targetDataName())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.targetDataName()));
            sql.append("'");
         }
      }
      if(unit.isRecordDateChanged()){
         sql.append(",`RECORD_DATE`=");
         if(unit.recordDate().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.recordDate().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isRecordTotalChanged()){
         sql.append(",`RECORD_TOTAL`=");
         sql.append(unit.recordTotal());
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