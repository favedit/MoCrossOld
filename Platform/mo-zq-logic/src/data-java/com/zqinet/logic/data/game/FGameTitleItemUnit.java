package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>称号信息逻辑单元。</T>
//============================================================
public class FGameTitleItemUnit extends FLogicUnit
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

   // 存储字段称号类型的定义。
   private int __titleType;

   // 字段称号类型的定义。
   protected int _titleType;

   // 存储字段称号tid的定义。
   private int __titleTid;

   // 字段称号tid的定义。
   protected int _titleTid;

   // 存储字段是否有属性的定义。
   private int __isProperty;

   // 字段是否有属性的定义。
   protected int _isProperty;

   // 存储字段属性类别的定义。
   private int __propertyCd;

   // 字段属性类别的定义。
   protected int _propertyCd;

   // 存储字段更改类型的定义。
   private int __modifyCd;

   // 字段更改类型的定义。
   protected int _modifyCd;

   // 存储字段属性值的定义。
   private int __propertyValue;

   // 字段属性值的定义。
   protected int _propertyValue;

   // 存储字段称号状态的定义。
   private int __titleStatuse;

   // 字段称号状态的定义。
   protected int _titleStatuse;

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
   // <T>构造称号信息逻辑单元。</T>
   //============================================================
   public FGameTitleItemUnit(){
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
   // <T>判断称号类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTitleTypeChanged(){
      return __titleType != _titleType;
   }

   //============================================================
   // <T>获得称号类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int titleType(){
      return _titleType;
   }

   //============================================================
   // <T>设置称号类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTitleType(int value){
      _titleType = value;
   }

   //============================================================
   // <T>判断称号tid的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTitleTidChanged(){
      return __titleTid != _titleTid;
   }

   //============================================================
   // <T>获得称号tid的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int titleTid(){
      return _titleTid;
   }

   //============================================================
   // <T>设置称号tid的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTitleTid(int value){
      _titleTid = value;
   }

   //============================================================
   // <T>判断是否有属性的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isIsPropertyChanged(){
      return __isProperty != _isProperty;
   }

   //============================================================
   // <T>获得是否有属性的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int isProperty(){
      return _isProperty;
   }

   //============================================================
   // <T>设置是否有属性的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setIsProperty(int value){
      _isProperty = value;
   }

   //============================================================
   // <T>判断属性类别的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPropertyCdChanged(){
      return __propertyCd != _propertyCd;
   }

   //============================================================
   // <T>获得属性类别的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int propertyCd(){
      return _propertyCd;
   }

   //============================================================
   // <T>设置属性类别的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPropertyCd(int value){
      _propertyCd = value;
   }

   //============================================================
   // <T>判断更改类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isModifyCdChanged(){
      return __modifyCd != _modifyCd;
   }

   //============================================================
   // <T>获得更改类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int modifyCd(){
      return _modifyCd;
   }

   //============================================================
   // <T>设置更改类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setModifyCd(int value){
      _modifyCd = value;
   }

   //============================================================
   // <T>判断属性值的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPropertyValueChanged(){
      return __propertyValue != _propertyValue;
   }

   //============================================================
   // <T>获得属性值的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int propertyValue(){
      return _propertyValue;
   }

   //============================================================
   // <T>设置属性值的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPropertyValue(int value){
      _propertyValue = value;
   }

   //============================================================
   // <T>判断称号状态的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTitleStatuseChanged(){
      return __titleStatuse != _titleStatuse;
   }

   //============================================================
   // <T>获得称号状态的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int titleStatuse(){
      return _titleStatuse;
   }

   //============================================================
   // <T>设置称号状态的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTitleStatuse(int value){
      _titleStatuse = value;
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
      __titleType = row.getInteger("title_type");
      _titleType = __titleType;
      __titleTid = row.getInteger("title_tid");
      _titleTid = __titleTid;
      __isProperty = row.getInteger("is_property");
      _isProperty = __isProperty;
      __propertyCd = row.getInteger("property_cd");
      _propertyCd = __propertyCd;
      __modifyCd = row.getInteger("modify_cd");
      _modifyCd = __modifyCd;
      __propertyValue = row.getInteger("property_value");
      _propertyValue = __propertyValue;
      __titleStatuse = row.getInteger("title_statuse");
      _titleStatuse = __titleStatuse;
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