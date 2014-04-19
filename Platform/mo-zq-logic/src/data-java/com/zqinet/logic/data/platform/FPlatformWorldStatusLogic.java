package com.zqinet.logic.data.platform;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>存储雕像逻辑。</T>
//============================================================
public class FPlatformWorldStatusLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("PF_WORLD_STATUS");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段雕像ID的定义。
   public final static FLogicField FieldStatueId = new FLogicField("STATUE_ID");

   // 字段雕像类型的定义。
   public final static FLogicField FieldStatueType = new FLogicField("STATUE_TYPE");

   // 字段角色ID的定义。
   public final static FLogicField FieldRoleId = new FLogicField("ROLE_ID");

   // 字段膜拜次数的定义。
   public final static FLogicField FieldWorshipCount = new FLogicField("WORSHIP_COUNT");

   // 字段鄙视次数的定义。
   public final static FLogicField FieldBsCount = new FLogicField("BS_COUNT");

   // 字段名称的定义。
   public final static FLogicField FieldLabel = new FLogicField("LABEL");

   // 字段样式动作集合的定义。
   public final static FLogicField FieldStyleActionSet = new FLogicField("STYLE_ACTION_SET");

   // 字段当前样式的定义。
   public final static FLogicField FieldCurStyleactionSet = new FLogicField("CUR_STYLEACTION_SET");

   // 字段当前角色编号的定义。
   public final static FLogicField FieldCurRoleId = new FLogicField("CUR_ROLE_ID");

   // 字段当然LABEL的定义。
   public final static FLogicField FieldCurLabel = new FLogicField("CUR_LABEL");

   // 字段限制值的定义。
   public final static FLogicField FieldLimitValue = new FLogicField("LIMIT_VALUE");

   // 字段等级的定义。
   public final static FLogicField FieldLevel = new FLogicField("LEVEL");

   // 字段当前等级的定义。
   public final static FLogicField FieldCurLevel = new FLogicField("CUR_LEVEL");

   // 字段门派模板编号的定义。
   public final static FLogicField FieldMetierTid = new FLogicField("METIER_TID");

   // 字段当前门派编号的定义。
   public final static FLogicField FieldCurMetierTid = new FLogicField("CUR_METIER_TID");

   // 字段VIP等级的定义。
   public final static FLogicField FieldVipLevel = new FLogicField("VIP_LEVEL");

   // 字段当前VIP等级的定义。
   public final static FLogicField FieldCurVipLevel = new FLogicField("CUR_VIP_LEVEL");

   // 字段额外VIP信息的定义。
   public final static FLogicField FieldExVipInfo = new FLogicField("EX_VIP_INFO");

   // 字段当前额外vip信息的定义。
   public final static FLogicField FieldCurExVipInfo = new FLogicField("CUR_EX_VIP_INFO");

   // 字段额外VIP等级的定义。
   public final static FLogicField FieldExVipLevel = new FLogicField("EX_VIP_LEVEL");

   // 字段当前额外VIP等级的定义。
   public final static FLogicField FieldCurExVipLevel = new FLogicField("CUR_EX_VIP_LEVEL");

   // 字段装备列表的定义。
   public final static FLogicField FieldEquipList = new FLogicField("EQUIP_LIST");

   // 字段当前装备列表的定义。
   public final static FLogicField FieldCurEquipList = new FLogicField("CUR_EQUIP_LIST");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造存储雕像逻辑单元。</T>
   //============================================================
   public FPlatformWorldStatusLogic(){
   }

   //============================================================
   // <T>构造存储雕像逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FPlatformWorldStatusLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FPlatformWorldStatusUnit find(long recordId){
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
      FPlatformWorldStatusUnit unit = new FPlatformWorldStatusUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FPlatformWorldStatusUnit serach(String whereSql){
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
      FPlatformWorldStatusUnit unit = new FPlatformWorldStatusUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FPlatformWorldStatusUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FPlatformWorldStatusUnit[] fetch(String whereSql, String orderSql){
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
   public FPlatformWorldStatusUnit[] fetch(String whereSql, String orderSql, int limitCount){
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
      FPlatformWorldStatusUnit[] units = new FPlatformWorldStatusUnit[count];
      for(int n = 0; n < count; n++){
         FPlatformWorldStatusUnit unit = new FPlatformWorldStatusUnit();
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
   public FPlatformWorldStatusUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FPlatformWorldStatusUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`STATUE_ID`");
      sql.append(",`STATUE_TYPE`");
      sql.append(",`ROLE_ID`");
      sql.append(",`WORSHIP_COUNT`");
      sql.append(",`BS_COUNT`");
      sql.append(",`LABEL`");
      sql.append(",`STYLE_ACTION_SET`");
      sql.append(",`CUR_STYLEACTION_SET`");
      sql.append(",`CUR_ROLE_ID`");
      sql.append(",`CUR_LABEL`");
      sql.append(",`LIMIT_VALUE`");
      sql.append(",`LEVEL`");
      sql.append(",`CUR_LEVEL`");
      sql.append(",`METIER_TID`");
      sql.append(",`CUR_METIER_TID`");
      sql.append(",`VIP_LEVEL`");
      sql.append(",`CUR_VIP_LEVEL`");
      sql.append(",`EX_VIP_INFO`");
      sql.append(",`CUR_EX_VIP_INFO`");
      sql.append(",`EX_VIP_LEVEL`");
      sql.append(",`CUR_EX_VIP_LEVEL`");
      sql.append(",`EQUIP_LIST`");
      sql.append(",`CUR_EQUIP_LIST`");
      sql.append(",`CREATE_USER_ID`");
      sql.append(",`CREATE_DATE`");
      sql.append(",`UPDATE_USER_ID`");
      sql.append(",`UPDATE_DATE`");
      sql.append(") VALUES(");
      sql.append(unit.ovld());
      sql.append(',');
      sql.append(unit.uniqueId());
      sql.append(',');
      sql.append(unit.statueId());
      sql.append(',');
      sql.append(unit.statueType());
      sql.append(',');
      sql.append(unit.roleId());
      sql.append(',');
      sql.append(unit.worshipCount());
      sql.append(',');
      sql.append(unit.bsCount());
      sql.append(',');
      if(RString.isEmpty(unit.label())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.label()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.styleActionSet())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.styleActionSet()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.curStyleactionSet())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.curStyleactionSet()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.curRoleId());
      sql.append(',');
      if(RString.isEmpty(unit.curLabel())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.curLabel()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.limitValue());
      sql.append(',');
      sql.append(unit.level());
      sql.append(',');
      sql.append(unit.curLevel());
      sql.append(',');
      sql.append(unit.metierTid());
      sql.append(',');
      sql.append(unit.curMetierTid());
      sql.append(',');
      sql.append(unit.vipLevel());
      sql.append(',');
      sql.append(unit.curVipLevel());
      sql.append(',');
      sql.append(unit.exVipInfo());
      sql.append(',');
      sql.append(unit.curExVipInfo());
      sql.append(',');
      sql.append(unit.exVipLevel());
      sql.append(',');
      sql.append(unit.curExVipLevel());
      sql.append(',');
      if(RString.isEmpty(unit.equipList())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.equipList()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.curEquipList())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.curEquipList()));
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
   public boolean doUpdate(FPlatformWorldStatusUnit unit, long recordId){
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
      if(unit.isStatueIdChanged()){
         sql.append(",`STATUE_ID`=");
         sql.append(unit.statueId());
      }
      if(unit.isStatueTypeChanged()){
         sql.append(",`STATUE_TYPE`=");
         sql.append(unit.statueType());
      }
      if(unit.isRoleIdChanged()){
         sql.append(",`ROLE_ID`=");
         sql.append(unit.roleId());
      }
      if(unit.isWorshipCountChanged()){
         sql.append(",`WORSHIP_COUNT`=");
         sql.append(unit.worshipCount());
      }
      if(unit.isBsCountChanged()){
         sql.append(",`BS_COUNT`=");
         sql.append(unit.bsCount());
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
      if(unit.isStyleActionSetChanged()){
         sql.append(",`STYLE_ACTION_SET`=");
         if(RString.isEmpty(unit.styleActionSet())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.styleActionSet()));
            sql.append("'");
         }
      }
      if(unit.isCurStyleactionSetChanged()){
         sql.append(",`CUR_STYLEACTION_SET`=");
         if(RString.isEmpty(unit.curStyleactionSet())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.curStyleactionSet()));
            sql.append("'");
         }
      }
      if(unit.isCurRoleIdChanged()){
         sql.append(",`CUR_ROLE_ID`=");
         sql.append(unit.curRoleId());
      }
      if(unit.isCurLabelChanged()){
         sql.append(",`CUR_LABEL`=");
         if(RString.isEmpty(unit.curLabel())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.curLabel()));
            sql.append("'");
         }
      }
      if(unit.isLimitValueChanged()){
         sql.append(",`LIMIT_VALUE`=");
         sql.append(unit.limitValue());
      }
      if(unit.isLevelChanged()){
         sql.append(",`LEVEL`=");
         sql.append(unit.level());
      }
      if(unit.isCurLevelChanged()){
         sql.append(",`CUR_LEVEL`=");
         sql.append(unit.curLevel());
      }
      if(unit.isMetierTidChanged()){
         sql.append(",`METIER_TID`=");
         sql.append(unit.metierTid());
      }
      if(unit.isCurMetierTidChanged()){
         sql.append(",`CUR_METIER_TID`=");
         sql.append(unit.curMetierTid());
      }
      if(unit.isVipLevelChanged()){
         sql.append(",`VIP_LEVEL`=");
         sql.append(unit.vipLevel());
      }
      if(unit.isCurVipLevelChanged()){
         sql.append(",`CUR_VIP_LEVEL`=");
         sql.append(unit.curVipLevel());
      }
      if(unit.isExVipInfoChanged()){
         sql.append(",`EX_VIP_INFO`=");
         sql.append(unit.exVipInfo());
      }
      if(unit.isCurExVipInfoChanged()){
         sql.append(",`CUR_EX_VIP_INFO`=");
         sql.append(unit.curExVipInfo());
      }
      if(unit.isExVipLevelChanged()){
         sql.append(",`EX_VIP_LEVEL`=");
         sql.append(unit.exVipLevel());
      }
      if(unit.isCurExVipLevelChanged()){
         sql.append(",`CUR_EX_VIP_LEVEL`=");
         sql.append(unit.curExVipLevel());
      }
      if(unit.isEquipListChanged()){
         sql.append(",`EQUIP_LIST`=");
         if(RString.isEmpty(unit.equipList())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.equipList()));
            sql.append("'");
         }
      }
      if(unit.isCurEquipListChanged()){
         sql.append(",`CUR_EQUIP_LIST`=");
         if(RString.isEmpty(unit.curEquipList())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.curEquipList()));
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