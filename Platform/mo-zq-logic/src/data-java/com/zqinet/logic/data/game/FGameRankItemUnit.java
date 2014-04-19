package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>排行榜信息逻辑单元。</T>
//============================================================
public class FGameRankItemUnit extends FLogicUnit
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

   // 存储字段编号的定义。
   private long __id;

   // 字段编号的定义。
   protected long _id;

   // 存储字段排行的定义。
   private int __rankIndex;

   // 字段排行的定义。
   protected int _rankIndex;

   // 存储字段数值的定义。
   private long __rankValue;

   // 字段数值的定义。
   protected long _rankValue;

   // 存储字段保留数值的定义。
   private long __reserveValue;

   // 字段保留数值的定义。
   protected long _reserveValue;

   // 存储字段类型的定义。
   private int __rankType;

   // 字段类型的定义。
   protected int _rankType;

   // 存储字段名称的定义。
   private String __name;

   // 字段名称的定义。
   protected String _name;

   // 存储字段性别的定义。
   private int __gender;

   // 字段性别的定义。
   protected int _gender;

   // 存储字段职业的定义。
   private int __metier;

   // 字段职业的定义。
   protected int _metier;

   // 存储字段所属名称的定义。
   private String __belongName;

   // 字段所属名称的定义。
   protected String _belongName;

   // 存储字段宠物品级的定义。
   private int __petQuailty;

   // 字段宠物品级的定义。
   protected int _petQuailty;

   // 存储字段等级的定义。
   private int __level;

   // 字段等级的定义。
   protected int _level;

   // 存储字段装备品质的定义。
   private int __equipQuailtyTid;

   // 字段装备品质的定义。
   protected int _equipQuailtyTid;

   // 存储字段帮会人数的定义。
   private String __memberCount;

   // 字段帮会人数的定义。
   protected String _memberCount;

   // 存储字段鄙视次数的定义。
   private int __disdainCount;

   // 字段鄙视次数的定义。
   protected int _disdainCount;

   // 存储字段膜拜次数的定义。
   private int __worshipCount;

   // 字段膜拜次数的定义。
   protected int _worshipCount;

   // 存储字段上榜日期的定义。
   private TDateTime __rankDate = new TDateTime();

   // 字段上榜日期的定义。
   protected TDateTime _rankDate = new TDateTime();

   // 存储字段角色等级的定义。
   private int __roleLevel;

   // 字段角色等级的定义。
   protected int _roleLevel;

   // 存储字段会员等级的定义。
   private int __vipLevel;

   // 字段会员等级的定义。
   protected int _vipLevel;

   // 存储字段额外VIP信息的定义。
   private int __exVipInfo;

   // 字段额外VIP信息的定义。
   protected int _exVipInfo;

   // 存储字段额外VIP等级的定义。
   private int __exVipLevel;

   // 字段额外VIP等级的定义。
   protected int _exVipLevel;

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
   // <T>构造排行榜信息逻辑单元。</T>
   //============================================================
   public FGameRankItemUnit(){
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
   // <T>判断编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isIdChanged(){
      return __id != _id;
   }

   //============================================================
   // <T>获得编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long id(){
      return _id;
   }

   //============================================================
   // <T>设置编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setId(long value){
      _id = value;
   }

   //============================================================
   // <T>判断排行的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRankIndexChanged(){
      return __rankIndex != _rankIndex;
   }

   //============================================================
   // <T>获得排行的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int rankIndex(){
      return _rankIndex;
   }

   //============================================================
   // <T>设置排行的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRankIndex(int value){
      _rankIndex = value;
   }

   //============================================================
   // <T>判断数值的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRankValueChanged(){
      return __rankValue != _rankValue;
   }

   //============================================================
   // <T>获得数值的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long rankValue(){
      return _rankValue;
   }

   //============================================================
   // <T>设置数值的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRankValue(long value){
      _rankValue = value;
   }

   //============================================================
   // <T>判断保留数值的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isReserveValueChanged(){
      return __reserveValue != _reserveValue;
   }

   //============================================================
   // <T>获得保留数值的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long reserveValue(){
      return _reserveValue;
   }

   //============================================================
   // <T>设置保留数值的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setReserveValue(long value){
      _reserveValue = value;
   }

   //============================================================
   // <T>判断类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRankTypeChanged(){
      return __rankType != _rankType;
   }

   //============================================================
   // <T>获得类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int rankType(){
      return _rankType;
   }

   //============================================================
   // <T>设置类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRankType(int value){
      _rankType = value;
   }

   //============================================================
   // <T>判断名称的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isNameChanged(){
      return !RString.equals(__name, _name);
   }

   //============================================================
   // <T>获得名称的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String name(){
      return _name;
   }

   //============================================================
   // <T>设置名称的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setName(String value){
      _name = value;
   }

   //============================================================
   // <T>判断性别的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isGenderChanged(){
      return __gender != _gender;
   }

   //============================================================
   // <T>获得性别的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int gender(){
      return _gender;
   }

   //============================================================
   // <T>设置性别的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setGender(int value){
      _gender = value;
   }

   //============================================================
   // <T>判断职业的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMetierChanged(){
      return __metier != _metier;
   }

   //============================================================
   // <T>获得职业的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int metier(){
      return _metier;
   }

   //============================================================
   // <T>设置职业的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMetier(int value){
      _metier = value;
   }

   //============================================================
   // <T>判断所属名称的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isBelongNameChanged(){
      return !RString.equals(__belongName, _belongName);
   }

   //============================================================
   // <T>获得所属名称的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String belongName(){
      return _belongName;
   }

   //============================================================
   // <T>设置所属名称的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setBelongName(String value){
      _belongName = value;
   }

   //============================================================
   // <T>判断宠物品级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPetQuailtyChanged(){
      return __petQuailty != _petQuailty;
   }

   //============================================================
   // <T>获得宠物品级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int petQuailty(){
      return _petQuailty;
   }

   //============================================================
   // <T>设置宠物品级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPetQuailty(int value){
      _petQuailty = value;
   }

   //============================================================
   // <T>判断等级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLevelChanged(){
      return __level != _level;
   }

   //============================================================
   // <T>获得等级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int level(){
      return _level;
   }

   //============================================================
   // <T>设置等级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLevel(int value){
      _level = value;
   }

   //============================================================
   // <T>判断装备品质的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isEquipQuailtyTidChanged(){
      return __equipQuailtyTid != _equipQuailtyTid;
   }

   //============================================================
   // <T>获得装备品质的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int equipQuailtyTid(){
      return _equipQuailtyTid;
   }

   //============================================================
   // <T>设置装备品质的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setEquipQuailtyTid(int value){
      _equipQuailtyTid = value;
   }

   //============================================================
   // <T>判断帮会人数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMemberCountChanged(){
      return !RString.equals(__memberCount, _memberCount);
   }

   //============================================================
   // <T>获得帮会人数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String memberCount(){
      return _memberCount;
   }

   //============================================================
   // <T>设置帮会人数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMemberCount(String value){
      _memberCount = value;
   }

   //============================================================
   // <T>判断鄙视次数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isDisdainCountChanged(){
      return __disdainCount != _disdainCount;
   }

   //============================================================
   // <T>获得鄙视次数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int disdainCount(){
      return _disdainCount;
   }

   //============================================================
   // <T>设置鄙视次数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDisdainCount(int value){
      _disdainCount = value;
   }

   //============================================================
   // <T>判断膜拜次数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isWorshipCountChanged(){
      return __worshipCount != _worshipCount;
   }

   //============================================================
   // <T>获得膜拜次数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int worshipCount(){
      return _worshipCount;
   }

   //============================================================
   // <T>设置膜拜次数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setWorshipCount(int value){
      _worshipCount = value;
   }

   //============================================================
   // <T>判断上榜日期的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRankDateChanged(){
      return !__rankDate.equals(_rankDate);
   }

   //============================================================
   // <T>获得上榜日期的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime rankDate(){
      return _rankDate;
   }

   //============================================================
   // <T>设置上榜日期的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRankDate(TDateTime value){
      _rankDate = value;
   }

   //============================================================
   // <T>判断角色等级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleLevelChanged(){
      return __roleLevel != _roleLevel;
   }

   //============================================================
   // <T>获得角色等级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleLevel(){
      return _roleLevel;
   }

   //============================================================
   // <T>设置角色等级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleLevel(int value){
      _roleLevel = value;
   }

   //============================================================
   // <T>判断会员等级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isVipLevelChanged(){
      return __vipLevel != _vipLevel;
   }

   //============================================================
   // <T>获得会员等级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int vipLevel(){
      return _vipLevel;
   }

   //============================================================
   // <T>设置会员等级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setVipLevel(int value){
      _vipLevel = value;
   }

   //============================================================
   // <T>判断额外VIP信息的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isExVipInfoChanged(){
      return __exVipInfo != _exVipInfo;
   }

   //============================================================
   // <T>获得额外VIP信息的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int exVipInfo(){
      return _exVipInfo;
   }

   //============================================================
   // <T>设置额外VIP信息的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setExVipInfo(int value){
      _exVipInfo = value;
   }

   //============================================================
   // <T>判断额外VIP等级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isExVipLevelChanged(){
      return __exVipLevel != _exVipLevel;
   }

   //============================================================
   // <T>获得额外VIP等级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int exVipLevel(){
      return _exVipLevel;
   }

   //============================================================
   // <T>设置额外VIP等级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setExVipLevel(int value){
      _exVipLevel = value;
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
      __id = RLong.parse(row.get("id"));
      _id = __id;
      __rankIndex = row.getInteger("rank_index");
      _rankIndex = __rankIndex;
      __rankValue = RLong.parse(row.get("rank_value"));
      _rankValue = __rankValue;
      __reserveValue = RLong.parse(row.get("reserve_value"));
      _reserveValue = __reserveValue;
      __rankType = row.getInteger("rank_type");
      _rankType = __rankType;
      __name = row.get("name");
      _name = __name;
      __gender = row.getInteger("gender");
      _gender = __gender;
      __metier = row.getInteger("metier");
      _metier = __metier;
      __belongName = row.get("belong_name");
      _belongName = __belongName;
      __petQuailty = row.getInteger("pet_quailty");
      _petQuailty = __petQuailty;
      __level = row.getInteger("level");
      _level = __level;
      __equipQuailtyTid = row.getInteger("equip_quailty_tid");
      _equipQuailtyTid = __equipQuailtyTid;
      __memberCount = row.get("member_count");
      _memberCount = __memberCount;
      __disdainCount = row.getInteger("disdain_count");
      _disdainCount = __disdainCount;
      __worshipCount = row.getInteger("worship_count");
      _worshipCount = __worshipCount;
      __rankDate.parse(row.get("rank_date"));
      _rankDate.assign(__rankDate);
      __roleLevel = row.getInteger("role_level");
      _roleLevel = __roleLevel;
      __vipLevel = row.getInteger("vip_level");
      _vipLevel = __vipLevel;
      __exVipInfo = row.getInteger("ex_vip_info");
      _exVipInfo = __exVipInfo;
      __exVipLevel = row.getInteger("ex_vip_level");
      _exVipLevel = __exVipLevel;
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