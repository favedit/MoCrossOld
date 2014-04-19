package com.zqinet.logic.data.logger;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>角色宠物变更日志逻辑单元。</T>
//============================================================
public class FLoggerRolePetUnit extends FLogicUnit
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

   // 存储字段宠物编号的定义。
   private long __petId;

   // 字段宠物编号的定义。
   protected long _petId;

   // 存储字段变更类型的定义。
   private int __changeType;

   // 字段变更类型的定义。
   protected int _changeType;

   // 存储字段变更事件的定义。
   private int __obtainEvent;

   // 字段变更事件的定义。
   protected int _obtainEvent;

   // 存储字段获得类型的定义。
   private int __obtainType;

   // 字段获得类型的定义。
   protected int _obtainType;

   // 存储字段变化的值的定义。
   private int __value;

   // 字段变化的值的定义。
   protected int _value;

   // 存储字段参数1的定义。
   private int __param1;

   // 字段参数1的定义。
   protected int _param1;

   // 存储字段参数2的定义。
   private int __param2;

   // 字段参数2的定义。
   protected int _param2;

   // 存储字段参数3的定义。
   private int __param3;

   // 字段参数3的定义。
   protected int _param3;

   // 存储字段参数4的定义。
   private int __param4;

   // 字段参数4的定义。
   protected int _param4;

   // 存储字段参数5的定义。
   private int __param5;

   // 字段参数5的定义。
   protected int _param5;

   // 存储字段参数6的定义。
   private int __param6;

   // 字段参数6的定义。
   protected int _param6;

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
   // <T>构造角色宠物变更日志逻辑单元。</T>
   //============================================================
   public FLoggerRolePetUnit(){
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
   // <T>判断宠物编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPetIdChanged(){
      return __petId != _petId;
   }

   //============================================================
   // <T>获得宠物编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long petId(){
      return _petId;
   }

   //============================================================
   // <T>设置宠物编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPetId(long value){
      _petId = value;
   }

   //============================================================
   // <T>判断变更类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isChangeTypeChanged(){
      return __changeType != _changeType;
   }

   //============================================================
   // <T>获得变更类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int changeType(){
      return _changeType;
   }

   //============================================================
   // <T>设置变更类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setChangeType(int value){
      _changeType = value;
   }

   //============================================================
   // <T>判断变更事件的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isObtainEventChanged(){
      return __obtainEvent != _obtainEvent;
   }

   //============================================================
   // <T>获得变更事件的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int obtainEvent(){
      return _obtainEvent;
   }

   //============================================================
   // <T>设置变更事件的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setObtainEvent(int value){
      _obtainEvent = value;
   }

   //============================================================
   // <T>判断获得类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isObtainTypeChanged(){
      return __obtainType != _obtainType;
   }

   //============================================================
   // <T>获得获得类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int obtainType(){
      return _obtainType;
   }

   //============================================================
   // <T>设置获得类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setObtainType(int value){
      _obtainType = value;
   }

   //============================================================
   // <T>判断变化的值的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isValueChanged(){
      return __value != _value;
   }

   //============================================================
   // <T>获得变化的值的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int value(){
      return _value;
   }

   //============================================================
   // <T>设置变化的值的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setValue(int value){
      _value = value;
   }

   //============================================================
   // <T>判断参数1的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isParam1Changed(){
      return __param1 != _param1;
   }

   //============================================================
   // <T>获得参数1的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int param1(){
      return _param1;
   }

   //============================================================
   // <T>设置参数1的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setParam1(int value){
      _param1 = value;
   }

   //============================================================
   // <T>判断参数2的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isParam2Changed(){
      return __param2 != _param2;
   }

   //============================================================
   // <T>获得参数2的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int param2(){
      return _param2;
   }

   //============================================================
   // <T>设置参数2的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setParam2(int value){
      _param2 = value;
   }

   //============================================================
   // <T>判断参数3的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isParam3Changed(){
      return __param3 != _param3;
   }

   //============================================================
   // <T>获得参数3的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int param3(){
      return _param3;
   }

   //============================================================
   // <T>设置参数3的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setParam3(int value){
      _param3 = value;
   }

   //============================================================
   // <T>判断参数4的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isParam4Changed(){
      return __param4 != _param4;
   }

   //============================================================
   // <T>获得参数4的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int param4(){
      return _param4;
   }

   //============================================================
   // <T>设置参数4的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setParam4(int value){
      _param4 = value;
   }

   //============================================================
   // <T>判断参数5的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isParam5Changed(){
      return __param5 != _param5;
   }

   //============================================================
   // <T>获得参数5的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int param5(){
      return _param5;
   }

   //============================================================
   // <T>设置参数5的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setParam5(int value){
      _param5 = value;
   }

   //============================================================
   // <T>判断参数6的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isParam6Changed(){
      return __param6 != _param6;
   }

   //============================================================
   // <T>获得参数6的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int param6(){
      return _param6;
   }

   //============================================================
   // <T>设置参数6的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setParam6(int value){
      _param6 = value;
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
      __petId = RLong.parse(row.get("pet_id"));
      _petId = __petId;
      __changeType = row.getInteger("change_type");
      _changeType = __changeType;
      __obtainEvent = row.getInteger("obtain_event");
      _obtainEvent = __obtainEvent;
      __obtainType = row.getInteger("obtain_type");
      _obtainType = __obtainType;
      __value = row.getInteger("value");
      _value = __value;
      __param1 = row.getInteger("param1");
      _param1 = __param1;
      __param2 = row.getInteger("param2");
      _param2 = __param2;
      __param3 = row.getInteger("param3");
      _param3 = __param3;
      __param4 = row.getInteger("param4");
      _param4 = __param4;
      __param5 = row.getInteger("param5");
      _param5 = __param5;
      __param6 = row.getInteger("param6");
      _param6 = __param6;
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