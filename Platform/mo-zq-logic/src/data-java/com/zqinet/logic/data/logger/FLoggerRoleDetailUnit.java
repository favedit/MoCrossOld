package com.zqinet.logic.data.logger;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>角色详细数值变更日志逻辑单元。</T>
//============================================================
public class FLoggerRoleDetailUnit extends FLogicUnit
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

   // 存储字段变更值的类型的定义。
   private int __valueCd;

   // 字段变更值的类型的定义。
   protected int _valueCd;

   // 存储字段获得类型的定义。
   private int __obtainType;

   // 字段获得类型的定义。
   protected int _obtainType;

   // 存储字段获得值的事件类型的定义。
   private int __obtainEvent;

   // 字段获得值的事件类型的定义。
   protected int _obtainEvent;

   // 存储字段变更的值大小的定义。
   private int __value;

   // 字段变更的值大小的定义。
   protected int _value;

   // 存储字段道具编号的定义。
   private int __propTid;

   // 字段道具编号的定义。
   protected int _propTid;

   // 存储字段道具数量的定义。
   private int __propCount;

   // 字段道具数量的定义。
   protected int _propCount;

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
   // <T>构造角色详细数值变更日志逻辑单元。</T>
   //============================================================
   public FLoggerRoleDetailUnit(){
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
   // <T>判断变更值的类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isValueCdChanged(){
      return __valueCd != _valueCd;
   }

   //============================================================
   // <T>获得变更值的类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int valueCd(){
      return _valueCd;
   }

   //============================================================
   // <T>设置变更值的类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setValueCd(int value){
      _valueCd = value;
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
   // <T>判断获得值的事件类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isObtainEventChanged(){
      return __obtainEvent != _obtainEvent;
   }

   //============================================================
   // <T>获得获得值的事件类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int obtainEvent(){
      return _obtainEvent;
   }

   //============================================================
   // <T>设置获得值的事件类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setObtainEvent(int value){
      _obtainEvent = value;
   }

   //============================================================
   // <T>判断变更的值大小的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isValueChanged(){
      return __value != _value;
   }

   //============================================================
   // <T>获得变更的值大小的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int value(){
      return _value;
   }

   //============================================================
   // <T>设置变更的值大小的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setValue(int value){
      _value = value;
   }

   //============================================================
   // <T>判断道具编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPropTidChanged(){
      return __propTid != _propTid;
   }

   //============================================================
   // <T>获得道具编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int propTid(){
      return _propTid;
   }

   //============================================================
   // <T>设置道具编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPropTid(int value){
      _propTid = value;
   }

   //============================================================
   // <T>判断道具数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPropCountChanged(){
      return __propCount != _propCount;
   }

   //============================================================
   // <T>获得道具数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int propCount(){
      return _propCount;
   }

   //============================================================
   // <T>设置道具数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPropCount(int value){
      _propCount = value;
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
      __valueCd = row.getInteger("value_cd");
      _valueCd = __valueCd;
      __obtainType = row.getInteger("obtain_type");
      _obtainType = __obtainType;
      __obtainEvent = row.getInteger("obtain_event");
      _obtainEvent = __obtainEvent;
      __value = row.getInteger("value");
      _value = __value;
      __propTid = row.getInteger("prop_tid");
      _propTid = __propTid;
      __propCount = row.getInteger("prop_count");
      _propCount = __propCount;
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