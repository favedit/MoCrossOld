package com.zqinet.logic.data.logger;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>四声血脉变更日志逻辑单元。</T>
//============================================================
public class FLoggerFourGodUnit extends FLogicUnit
{
   // 存储字段对象标识的定义。
   private long __ouid;

   // 字段对象标识的定义。
   protected long _ouid;

   // 存储字段有效性的定义。
   private boolean __ovld;

   // 字段有效性的定义。
   protected boolean _ovld;

   // 存储字段唯一标识的定义。
   private int __uniqueId;

   // 字段唯一标识的定义。
   protected int _uniqueId;

   // 存储字段角色编号的定义。
   private long __roleId;

   // 字段角色编号的定义。
   protected long _roleId;

   // 存储字段四圣血脉类型的定义。
   private int __fourGodType;

   // 字段四圣血脉类型的定义。
   protected int _fourGodType;

   // 存储字段获得的HP值的定义。
   private int __hp;

   // 字段获得的HP值的定义。
   protected int _hp;

   // 存储字段获得物攻值的定义。
   private int __attackPhysical;

   // 字段获得物攻值的定义。
   protected int _attackPhysical;

   // 存储字段获得法攻值的定义。
   private int __attackMagic;

   // 字段获得法攻值的定义。
   protected int _attackMagic;

   // 存储字段获得物防值的定义。
   private int __defensePhysical;

   // 字段获得物防值的定义。
   protected int _defensePhysical;

   // 存储字段获得的法防值的定义。
   private int __defenseMagic;

   // 字段获得的法防值的定义。
   protected int _defenseMagic;

   // 存储字段操作次数的定义。
   private int __operCount;

   // 字段操作次数的定义。
   protected int _operCount;

   // 存储字段创建用户标识的定义。
   private int __createUserId;

   // 字段创建用户标识的定义。
   protected int _createUserId;

   // 存储字段创建日期的定义。
   private TDateTime __createDate = new TDateTime();

   // 字段创建日期的定义。
   protected TDateTime _createDate = new TDateTime();

   // 存储字段更新者标识的定义。
   private int __updateUserId;

   // 字段更新者标识的定义。
   protected int _updateUserId;

   // 存储字段更新时间的定义。
   private TDateTime __updateDate = new TDateTime();

   // 字段更新时间的定义。
   protected TDateTime _updateDate = new TDateTime();

   //============================================================
   // <T>构造四声血脉变更日志逻辑单元。</T>
   //============================================================
   public FLoggerFourGodUnit(){
   }

   //============================================================
   // <T>判断对象标识的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isOuidChanged(){
      return __ouid != _ouid;
   }

   //============================================================
   // <T>获得对象标识的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long ouid(){
      return _ouid;
   }

   //============================================================
   // <T>设置对象标识的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setOuid(long value){
      _ouid = value;
   }

   //============================================================
   // <T>判断有效性的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isOvldChanged(){
      return __ovld != _ovld;
   }

   //============================================================
   // <T>获得有效性的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean ovld(){
      return _ovld;
   }

   //============================================================
   // <T>设置有效性的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setOvld(boolean value){
      _ovld = value;
   }

   //============================================================
   // <T>判断唯一标识的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isUniqueIdChanged(){
      return __uniqueId != _uniqueId;
   }

   //============================================================
   // <T>获得唯一标识的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int uniqueId(){
      return _uniqueId;
   }

   //============================================================
   // <T>设置唯一标识的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setUniqueId(int value){
      _uniqueId = value;
   }

   //============================================================
   // <T>判断角色编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleIdChanged(){
      return __roleId != _roleId;
   }

   //============================================================
   // <T>获得角色编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long roleId(){
      return _roleId;
   }

   //============================================================
   // <T>设置角色编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleId(long value){
      _roleId = value;
   }

   //============================================================
   // <T>判断四圣血脉类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isFourGodTypeChanged(){
      return __fourGodType != _fourGodType;
   }

   //============================================================
   // <T>获得四圣血脉类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int fourGodType(){
      return _fourGodType;
   }

   //============================================================
   // <T>设置四圣血脉类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setFourGodType(int value){
      _fourGodType = value;
   }

   //============================================================
   // <T>判断获得的HP值的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isHpChanged(){
      return __hp != _hp;
   }

   //============================================================
   // <T>获得获得的HP值的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int hp(){
      return _hp;
   }

   //============================================================
   // <T>设置获得的HP值的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setHp(int value){
      _hp = value;
   }

   //============================================================
   // <T>判断获得物攻值的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAttackPhysicalChanged(){
      return __attackPhysical != _attackPhysical;
   }

   //============================================================
   // <T>获得获得物攻值的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int attackPhysical(){
      return _attackPhysical;
   }

   //============================================================
   // <T>设置获得物攻值的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAttackPhysical(int value){
      _attackPhysical = value;
   }

   //============================================================
   // <T>判断获得法攻值的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAttackMagicChanged(){
      return __attackMagic != _attackMagic;
   }

   //============================================================
   // <T>获得获得法攻值的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int attackMagic(){
      return _attackMagic;
   }

   //============================================================
   // <T>设置获得法攻值的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAttackMagic(int value){
      _attackMagic = value;
   }

   //============================================================
   // <T>判断获得物防值的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isDefensePhysicalChanged(){
      return __defensePhysical != _defensePhysical;
   }

   //============================================================
   // <T>获得获得物防值的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int defensePhysical(){
      return _defensePhysical;
   }

   //============================================================
   // <T>设置获得物防值的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDefensePhysical(int value){
      _defensePhysical = value;
   }

   //============================================================
   // <T>判断获得的法防值的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isDefenseMagicChanged(){
      return __defenseMagic != _defenseMagic;
   }

   //============================================================
   // <T>获得获得的法防值的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int defenseMagic(){
      return _defenseMagic;
   }

   //============================================================
   // <T>设置获得的法防值的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDefenseMagic(int value){
      _defenseMagic = value;
   }

   //============================================================
   // <T>判断操作次数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isOperCountChanged(){
      return __operCount != _operCount;
   }

   //============================================================
   // <T>获得操作次数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int operCount(){
      return _operCount;
   }

   //============================================================
   // <T>设置操作次数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setOperCount(int value){
      _operCount = value;
   }

   //============================================================
   // <T>判断创建用户标识的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCreateUserIdChanged(){
      return __createUserId != _createUserId;
   }

   //============================================================
   // <T>获得创建用户标识的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int createUserId(){
      return _createUserId;
   }

   //============================================================
   // <T>设置创建用户标识的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCreateUserId(int value){
      _createUserId = value;
   }

   //============================================================
   // <T>判断创建日期的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCreateDateChanged(){
      return !__createDate.equals(_createDate);
   }

   //============================================================
   // <T>获得创建日期的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime createDate(){
      return _createDate;
   }

   //============================================================
   // <T>设置创建日期的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCreateDate(TDateTime value){
      _createDate = value;
   }

   //============================================================
   // <T>判断更新者标识的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isUpdateUserIdChanged(){
      return __updateUserId != _updateUserId;
   }

   //============================================================
   // <T>获得更新者标识的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int updateUserId(){
      return _updateUserId;
   }

   //============================================================
   // <T>设置更新者标识的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setUpdateUserId(int value){
      _updateUserId = value;
   }

   //============================================================
   // <T>判断更新时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isUpdateDateChanged(){
      return !__updateDate.equals(_updateDate);
   }

   //============================================================
   // <T>获得更新时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime updateDate(){
      return _updateDate;
   }

   //============================================================
   // <T>设置更新时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setUpdateDate(TDateTime value){
      _updateDate = value;
   }

   //============================================================
   // <T>加载行记录。</T>
   //
   // @param row 行记录
   //============================================================
   public void load(FRow row){
      __ouid = RLong.parse(row.get("ouid"));
      _ouid = __ouid;
      __uniqueId = row.getInteger("unique_id");
      _uniqueId = __uniqueId;
      __roleId = RLong.parse(row.get("role_id"));
      _roleId = __roleId;
      __fourGodType = row.getInteger("four_god_type");
      _fourGodType = __fourGodType;
      __hp = row.getInteger("hp");
      _hp = __hp;
      __attackPhysical = row.getInteger("attack_physical");
      _attackPhysical = __attackPhysical;
      __attackMagic = row.getInteger("attack_magic");
      _attackMagic = __attackMagic;
      __defensePhysical = row.getInteger("defense_physical");
      _defensePhysical = __defensePhysical;
      __defenseMagic = row.getInteger("defense_magic");
      _defenseMagic = __defenseMagic;
      __operCount = row.getInteger("oper_count");
      _operCount = __operCount;
      __createUserId = row.getInteger("create_user_id");
      _createUserId = __createUserId;
      __createDate.parse(row.get("create_date"));
      _createDate.assign(__createDate);
      __updateUserId = row.getInteger("update_user_id");
      _updateUserId = __updateUserId;
      __updateDate.parse(row.get("update_date"));
      _updateDate.assign(__updateDate);
   }
}