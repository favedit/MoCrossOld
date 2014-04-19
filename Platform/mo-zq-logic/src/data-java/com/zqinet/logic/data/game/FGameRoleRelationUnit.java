package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>角色关系信息逻辑单元。</T>
//============================================================
public class FGameRoleRelationUnit extends FLogicUnit
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

   // 存储字段关系角色编号的定义。
   private long __relationRoleId;

   // 字段关系角色编号的定义。
   protected long _relationRoleId;

   // 存储字段关系类型的定义。
   private int __relationCd;

   // 字段关系类型的定义。
   protected int _relationCd;

   // 存储字段亲密度的定义。
   private int __closeValue;

   // 字段亲密度的定义。
   protected int _closeValue;

   // 存储字段侠缘值的定义。
   private int __kindValue;

   // 字段侠缘值的定义。
   protected int _kindValue;

   // 存储字段亲密度次数的定义。
   private int __closeValueCount;

   // 字段亲密度次数的定义。
   protected int _closeValueCount;

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
   // <T>构造角色关系信息逻辑单元。</T>
   //============================================================
   public FGameRoleRelationUnit(){
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
   // <T>判断关系角色编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRelationRoleIdChanged(){
      return __relationRoleId != _relationRoleId;
   }

   //============================================================
   // <T>获得关系角色编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long relationRoleId(){
      return _relationRoleId;
   }

   //============================================================
   // <T>设置关系角色编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRelationRoleId(long value){
      _relationRoleId = value;
   }

   //============================================================
   // <T>判断关系类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRelationCdChanged(){
      return __relationCd != _relationCd;
   }

   //============================================================
   // <T>获得关系类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int relationCd(){
      return _relationCd;
   }

   //============================================================
   // <T>设置关系类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRelationCd(int value){
      _relationCd = value;
   }

   //============================================================
   // <T>判断亲密度的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCloseValueChanged(){
      return __closeValue != _closeValue;
   }

   //============================================================
   // <T>获得亲密度的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int closeValue(){
      return _closeValue;
   }

   //============================================================
   // <T>设置亲密度的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCloseValue(int value){
      _closeValue = value;
   }

   //============================================================
   // <T>判断侠缘值的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isKindValueChanged(){
      return __kindValue != _kindValue;
   }

   //============================================================
   // <T>获得侠缘值的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int kindValue(){
      return _kindValue;
   }

   //============================================================
   // <T>设置侠缘值的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setKindValue(int value){
      _kindValue = value;
   }

   //============================================================
   // <T>判断亲密度次数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCloseValueCountChanged(){
      return __closeValueCount != _closeValueCount;
   }

   //============================================================
   // <T>获得亲密度次数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int closeValueCount(){
      return _closeValueCount;
   }

   //============================================================
   // <T>设置亲密度次数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCloseValueCount(int value){
      _closeValueCount = value;
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
      __relationRoleId = RLong.parse(row.get("relation_role_id"));
      _relationRoleId = __relationRoleId;
      __relationCd = row.getInteger("relation_cd");
      _relationCd = __relationCd;
      __closeValue = row.getInteger("close_value");
      _closeValue = __closeValue;
      __kindValue = row.getInteger("kind_value");
      _kindValue = __kindValue;
      __closeValueCount = row.getInteger("close_value_count");
      _closeValueCount = __closeValueCount;
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