package com.zqinet.logic.data.logger;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>角色修炼经脉逻辑单元。</T>
//============================================================
public class FLoggerRolePracticeUnit extends FLogicUnit
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

   // 存储字段经脉编号的定义。
   private int __practiceTid;

   // 字段经脉编号的定义。
   protected int _practiceTid;

   // 存储字段修炼前等级的定义。
   private int __oldLevel;

   // 字段修炼前等级的定义。
   protected int _oldLevel;

   // 存储字段修炼后的级别的定义。
   private int __newLevel;

   // 字段修炼后的级别的定义。
   protected int _newLevel;

   // 存储字段道具1编号的定义。
   private int __prop1Tid;

   // 字段道具1编号的定义。
   protected int _prop1Tid;

   // 存储字段道具1数量的定义。
   private int __count1;

   // 字段道具1数量的定义。
   protected int _count1;

   // 存储字段道具2编号的定义。
   private int __prop2Tid;

   // 字段道具2编号的定义。
   protected int _prop2Tid;

   // 存储字段道具2数量的定义。
   private int __count2;

   // 字段道具2数量的定义。
   protected int _count2;

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
   // <T>构造角色修炼经脉逻辑单元。</T>
   //============================================================
   public FLoggerRolePracticeUnit(){
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
   // <T>判断经脉编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPracticeTidChanged(){
      return __practiceTid != _practiceTid;
   }

   //============================================================
   // <T>获得经脉编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int practiceTid(){
      return _practiceTid;
   }

   //============================================================
   // <T>设置经脉编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPracticeTid(int value){
      _practiceTid = value;
   }

   //============================================================
   // <T>判断修炼前等级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isOldLevelChanged(){
      return __oldLevel != _oldLevel;
   }

   //============================================================
   // <T>获得修炼前等级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int oldLevel(){
      return _oldLevel;
   }

   //============================================================
   // <T>设置修炼前等级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setOldLevel(int value){
      _oldLevel = value;
   }

   //============================================================
   // <T>判断修炼后的级别的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isNewLevelChanged(){
      return __newLevel != _newLevel;
   }

   //============================================================
   // <T>获得修炼后的级别的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int newLevel(){
      return _newLevel;
   }

   //============================================================
   // <T>设置修炼后的级别的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setNewLevel(int value){
      _newLevel = value;
   }

   //============================================================
   // <T>判断道具1编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isProp1TidChanged(){
      return __prop1Tid != _prop1Tid;
   }

   //============================================================
   // <T>获得道具1编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int prop1Tid(){
      return _prop1Tid;
   }

   //============================================================
   // <T>设置道具1编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setProp1Tid(int value){
      _prop1Tid = value;
   }

   //============================================================
   // <T>判断道具1数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCount1Changed(){
      return __count1 != _count1;
   }

   //============================================================
   // <T>获得道具1数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int count1(){
      return _count1;
   }

   //============================================================
   // <T>设置道具1数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCount1(int value){
      _count1 = value;
   }

   //============================================================
   // <T>判断道具2编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isProp2TidChanged(){
      return __prop2Tid != _prop2Tid;
   }

   //============================================================
   // <T>获得道具2编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int prop2Tid(){
      return _prop2Tid;
   }

   //============================================================
   // <T>设置道具2编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setProp2Tid(int value){
      _prop2Tid = value;
   }

   //============================================================
   // <T>判断道具2数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCount2Changed(){
      return __count2 != _count2;
   }

   //============================================================
   // <T>获得道具2数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int count2(){
      return _count2;
   }

   //============================================================
   // <T>设置道具2数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCount2(int value){
      _count2 = value;
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
      __practiceTid = row.getInteger("practice_tid");
      _practiceTid = __practiceTid;
      __oldLevel = row.getInteger("old_level");
      _oldLevel = __oldLevel;
      __newLevel = row.getInteger("new_level");
      _newLevel = __newLevel;
      __prop1Tid = row.getInteger("prop1_tid");
      _prop1Tid = __prop1Tid;
      __count1 = row.getInteger("count1");
      _count1 = __count1;
      __prop2Tid = row.getInteger("prop2_tid");
      _prop2Tid = __prop2Tid;
      __count2 = row.getInteger("count2");
      _count2 = __count2;
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