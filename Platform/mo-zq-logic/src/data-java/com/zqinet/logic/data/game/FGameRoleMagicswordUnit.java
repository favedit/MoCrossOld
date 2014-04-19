package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>角色神剑信息逻辑单元。</T>
//============================================================
public class FGameRoleMagicswordUnit extends FLogicUnit
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

   // 存储字段模板编号的定义。
   private int __magicswordTid;

   // 字段模板编号的定义。
   protected int _magicswordTid;

   // 存储字段品级的定义。
   private int __gradeLevel;

   // 字段品级的定义。
   protected int _gradeLevel;

   // 存储字段内容打包的定义。
   private String __contentPack;

   // 字段内容打包的定义。
   protected String _contentPack;

   // 存储字段是否升级中的定义。
   private int __isUpgrade;

   // 字段是否升级中的定义。
   protected int _isUpgrade;

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
   // <T>构造角色神剑信息逻辑单元。</T>
   //============================================================
   public FGameRoleMagicswordUnit(){
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
   // <T>判断模板编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMagicswordTidChanged(){
      return __magicswordTid != _magicswordTid;
   }

   //============================================================
   // <T>获得模板编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int magicswordTid(){
      return _magicswordTid;
   }

   //============================================================
   // <T>设置模板编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMagicswordTid(int value){
      _magicswordTid = value;
   }

   //============================================================
   // <T>判断品级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isGradeLevelChanged(){
      return __gradeLevel != _gradeLevel;
   }

   //============================================================
   // <T>获得品级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int gradeLevel(){
      return _gradeLevel;
   }

   //============================================================
   // <T>设置品级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setGradeLevel(int value){
      _gradeLevel = value;
   }

   //============================================================
   // <T>判断内容打包的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isContentPackChanged(){
      return !RString.equals(__contentPack, _contentPack);
   }

   //============================================================
   // <T>获得内容打包的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String contentPack(){
      return _contentPack;
   }

   //============================================================
   // <T>设置内容打包的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setContentPack(String value){
      _contentPack = value;
   }

   //============================================================
   // <T>判断是否升级中的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isIsUpgradeChanged(){
      return __isUpgrade != _isUpgrade;
   }

   //============================================================
   // <T>获得是否升级中的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int isUpgrade(){
      return _isUpgrade;
   }

   //============================================================
   // <T>设置是否升级中的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setIsUpgrade(int value){
      _isUpgrade = value;
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
      __magicswordTid = row.getInteger("magicsword_tid");
      _magicswordTid = __magicswordTid;
      __gradeLevel = row.getInteger("grade_level");
      _gradeLevel = __gradeLevel;
      __contentPack = row.get("content_pack");
      _contentPack = __contentPack;
      __isUpgrade = row.getInteger("is_upgrade");
      _isUpgrade = __isUpgrade;
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