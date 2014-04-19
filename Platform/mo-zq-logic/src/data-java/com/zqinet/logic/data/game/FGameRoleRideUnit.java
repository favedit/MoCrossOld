package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>角色坐骑信息逻辑单元。</T>
//============================================================
public class FGameRoleRideUnit extends FLogicUnit
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

   // 存储字段坐骑模板编号的定义。
   private int __rideTid;

   // 字段坐骑模板编号的定义。
   protected int _rideTid;

   // 存储字段标志的定义。
   private int __flags;

   // 字段标志的定义。
   protected int _flags;

   // 存储字段级别的定义。
   private int __level;

   // 字段级别的定义。
   protected int _level;

   // 存储字段身价的定义。
   private int __cost;

   // 字段身价的定义。
   protected int _cost;

   // 存储字段星级的定义。
   private int __starLevel;

   // 字段星级的定义。
   protected int _starLevel;

   // 存储字段移动速度的定义。
   private int __speed;

   // 字段移动速度的定义。
   protected int _speed;

   // 存储字段档阶的定义。
   private int __fileOrder;

   // 字段档阶的定义。
   protected int _fileOrder;

   // 存储字段气血的定义。
   private int __hpArg;

   // 字段气血的定义。
   protected int _hpArg;

   // 存储字段魔法的定义。
   private int __mpArg;

   // 字段魔法的定义。
   protected int _mpArg;

   // 存储字段物攻的定义。
   private int __attackPhysicalArg;

   // 字段物攻的定义。
   protected int _attackPhysicalArg;

   // 存储字段物防的定义。
   private int __defencePhysicalArg;

   // 字段物防的定义。
   protected int _defencePhysicalArg;

   // 存储字段法攻的定义。
   private int __attackMagicArg;

   // 字段法攻的定义。
   protected int _attackMagicArg;

   // 存储字段法防的定义。
   private int __defenceMagicArg;

   // 字段法防的定义。
   protected int _defenceMagicArg;

   // 存储字段当前经验的定义。
   private int __experience;

   // 字段当前经验的定义。
   protected int _experience;

   // 存储字段升级所需经验的定义。
   private int __experienceUp;

   // 字段升级所需经验的定义。
   protected int _experienceUp;

   // 存储字段中文名称的定义。
   private String __label;

   // 字段中文名称的定义。
   protected String _label;

   // 存储字段幸运值的定义。
   private int __luck;

   // 字段幸运值的定义。
   protected int _luck;

   // 存储字段获得时间的定义。
   private TDateTime __obtainTime = new TDateTime();

   // 字段获得时间的定义。
   protected TDateTime _obtainTime = new TDateTime();

   // 存储字段物品包类型的定义。
   private int __itemBagCd;

   // 字段物品包类型的定义。
   protected int _itemBagCd;

   // 存储字段物品背包编号的定义。
   private long __itemBagId;

   // 字段物品背包编号的定义。
   protected long _itemBagId;

   // 存储字段物品包索引的定义。
   private int __itemBagIndex;

   // 字段物品包索引的定义。
   protected int _itemBagIndex;

   // 存储字段物品快捷栏编号的定义。
   private long __itemBagShortcutId;

   // 字段物品快捷栏编号的定义。
   protected long _itemBagShortcutId;

   // 存储字段物品快捷栏索引的定义。
   private int __itemBagShortcutIndex;

   // 字段物品快捷栏索引的定义。
   protected int _itemBagShortcutIndex;

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
   // <T>构造角色坐骑信息逻辑单元。</T>
   //============================================================
   public FGameRoleRideUnit(){
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
   // <T>判断坐骑模板编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRideTidChanged(){
      return __rideTid != _rideTid;
   }

   //============================================================
   // <T>获得坐骑模板编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int rideTid(){
      return _rideTid;
   }

   //============================================================
   // <T>设置坐骑模板编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRideTid(int value){
      _rideTid = value;
   }

   //============================================================
   // <T>判断标志的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isFlagsChanged(){
      return __flags != _flags;
   }

   //============================================================
   // <T>获得标志的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int flags(){
      return _flags;
   }

   //============================================================
   // <T>设置标志的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setFlags(int value){
      _flags = value;
   }

   //============================================================
   // <T>判断级别的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLevelChanged(){
      return __level != _level;
   }

   //============================================================
   // <T>获得级别的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int level(){
      return _level;
   }

   //============================================================
   // <T>设置级别的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLevel(int value){
      _level = value;
   }

   //============================================================
   // <T>判断身价的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCostChanged(){
      return __cost != _cost;
   }

   //============================================================
   // <T>获得身价的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int cost(){
      return _cost;
   }

   //============================================================
   // <T>设置身价的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCost(int value){
      _cost = value;
   }

   //============================================================
   // <T>判断星级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isStarLevelChanged(){
      return __starLevel != _starLevel;
   }

   //============================================================
   // <T>获得星级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int starLevel(){
      return _starLevel;
   }

   //============================================================
   // <T>设置星级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setStarLevel(int value){
      _starLevel = value;
   }

   //============================================================
   // <T>判断移动速度的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSpeedChanged(){
      return __speed != _speed;
   }

   //============================================================
   // <T>获得移动速度的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int speed(){
      return _speed;
   }

   //============================================================
   // <T>设置移动速度的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSpeed(int value){
      _speed = value;
   }

   //============================================================
   // <T>判断档阶的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isFileOrderChanged(){
      return __fileOrder != _fileOrder;
   }

   //============================================================
   // <T>获得档阶的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int fileOrder(){
      return _fileOrder;
   }

   //============================================================
   // <T>设置档阶的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setFileOrder(int value){
      _fileOrder = value;
   }

   //============================================================
   // <T>判断气血的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isHpArgChanged(){
      return __hpArg != _hpArg;
   }

   //============================================================
   // <T>获得气血的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int hpArg(){
      return _hpArg;
   }

   //============================================================
   // <T>设置气血的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setHpArg(int value){
      _hpArg = value;
   }

   //============================================================
   // <T>判断魔法的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMpArgChanged(){
      return __mpArg != _mpArg;
   }

   //============================================================
   // <T>获得魔法的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int mpArg(){
      return _mpArg;
   }

   //============================================================
   // <T>设置魔法的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMpArg(int value){
      _mpArg = value;
   }

   //============================================================
   // <T>判断物攻的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAttackPhysicalArgChanged(){
      return __attackPhysicalArg != _attackPhysicalArg;
   }

   //============================================================
   // <T>获得物攻的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int attackPhysicalArg(){
      return _attackPhysicalArg;
   }

   //============================================================
   // <T>设置物攻的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAttackPhysicalArg(int value){
      _attackPhysicalArg = value;
   }

   //============================================================
   // <T>判断物防的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isDefencePhysicalArgChanged(){
      return __defencePhysicalArg != _defencePhysicalArg;
   }

   //============================================================
   // <T>获得物防的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int defencePhysicalArg(){
      return _defencePhysicalArg;
   }

   //============================================================
   // <T>设置物防的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDefencePhysicalArg(int value){
      _defencePhysicalArg = value;
   }

   //============================================================
   // <T>判断法攻的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAttackMagicArgChanged(){
      return __attackMagicArg != _attackMagicArg;
   }

   //============================================================
   // <T>获得法攻的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int attackMagicArg(){
      return _attackMagicArg;
   }

   //============================================================
   // <T>设置法攻的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAttackMagicArg(int value){
      _attackMagicArg = value;
   }

   //============================================================
   // <T>判断法防的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isDefenceMagicArgChanged(){
      return __defenceMagicArg != _defenceMagicArg;
   }

   //============================================================
   // <T>获得法防的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int defenceMagicArg(){
      return _defenceMagicArg;
   }

   //============================================================
   // <T>设置法防的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDefenceMagicArg(int value){
      _defenceMagicArg = value;
   }

   //============================================================
   // <T>判断当前经验的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isExperienceChanged(){
      return __experience != _experience;
   }

   //============================================================
   // <T>获得当前经验的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int experience(){
      return _experience;
   }

   //============================================================
   // <T>设置当前经验的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setExperience(int value){
      _experience = value;
   }

   //============================================================
   // <T>判断升级所需经验的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isExperienceUpChanged(){
      return __experienceUp != _experienceUp;
   }

   //============================================================
   // <T>获得升级所需经验的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int experienceUp(){
      return _experienceUp;
   }

   //============================================================
   // <T>设置升级所需经验的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setExperienceUp(int value){
      _experienceUp = value;
   }

   //============================================================
   // <T>判断中文名称的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLabelChanged(){
      return !RString.equals(__label, _label);
   }

   //============================================================
   // <T>获得中文名称的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String label(){
      return _label;
   }

   //============================================================
   // <T>设置中文名称的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLabel(String value){
      _label = value;
   }

   //============================================================
   // <T>判断幸运值的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLuckChanged(){
      return __luck != _luck;
   }

   //============================================================
   // <T>获得幸运值的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int luck(){
      return _luck;
   }

   //============================================================
   // <T>设置幸运值的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLuck(int value){
      _luck = value;
   }

   //============================================================
   // <T>判断获得时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isObtainTimeChanged(){
      return !__obtainTime.equals(_obtainTime);
   }

   //============================================================
   // <T>获得获得时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime obtainTime(){
      return _obtainTime;
   }

   //============================================================
   // <T>设置获得时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setObtainTime(TDateTime value){
      _obtainTime = value;
   }

   //============================================================
   // <T>判断物品包类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isItemBagCdChanged(){
      return __itemBagCd != _itemBagCd;
   }

   //============================================================
   // <T>获得物品包类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int itemBagCd(){
      return _itemBagCd;
   }

   //============================================================
   // <T>设置物品包类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setItemBagCd(int value){
      _itemBagCd = value;
   }

   //============================================================
   // <T>判断物品背包编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isItemBagIdChanged(){
      return __itemBagId != _itemBagId;
   }

   //============================================================
   // <T>获得物品背包编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long itemBagId(){
      return _itemBagId;
   }

   //============================================================
   // <T>设置物品背包编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setItemBagId(long value){
      _itemBagId = value;
   }

   //============================================================
   // <T>判断物品包索引的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isItemBagIndexChanged(){
      return __itemBagIndex != _itemBagIndex;
   }

   //============================================================
   // <T>获得物品包索引的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int itemBagIndex(){
      return _itemBagIndex;
   }

   //============================================================
   // <T>设置物品包索引的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setItemBagIndex(int value){
      _itemBagIndex = value;
   }

   //============================================================
   // <T>判断物品快捷栏编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isItemBagShortcutIdChanged(){
      return __itemBagShortcutId != _itemBagShortcutId;
   }

   //============================================================
   // <T>获得物品快捷栏编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long itemBagShortcutId(){
      return _itemBagShortcutId;
   }

   //============================================================
   // <T>设置物品快捷栏编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setItemBagShortcutId(long value){
      _itemBagShortcutId = value;
   }

   //============================================================
   // <T>判断物品快捷栏索引的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isItemBagShortcutIndexChanged(){
      return __itemBagShortcutIndex != _itemBagShortcutIndex;
   }

   //============================================================
   // <T>获得物品快捷栏索引的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int itemBagShortcutIndex(){
      return _itemBagShortcutIndex;
   }

   //============================================================
   // <T>设置物品快捷栏索引的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setItemBagShortcutIndex(int value){
      _itemBagShortcutIndex = value;
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
      __rideTid = row.getInteger("ride_tid");
      _rideTid = __rideTid;
      __flags = row.getInteger("flags");
      _flags = __flags;
      __level = row.getInteger("level");
      _level = __level;
      __cost = row.getInteger("cost");
      _cost = __cost;
      __starLevel = row.getInteger("star_level");
      _starLevel = __starLevel;
      __speed = row.getInteger("speed");
      _speed = __speed;
      __fileOrder = row.getInteger("file_order");
      _fileOrder = __fileOrder;
      __hpArg = row.getInteger("hp_arg");
      _hpArg = __hpArg;
      __mpArg = row.getInteger("mp_arg");
      _mpArg = __mpArg;
      __attackPhysicalArg = row.getInteger("attack_physical_arg");
      _attackPhysicalArg = __attackPhysicalArg;
      __defencePhysicalArg = row.getInteger("defence_physical_arg");
      _defencePhysicalArg = __defencePhysicalArg;
      __attackMagicArg = row.getInteger("attack_magic_arg");
      _attackMagicArg = __attackMagicArg;
      __defenceMagicArg = row.getInteger("defence_magic_arg");
      _defenceMagicArg = __defenceMagicArg;
      __experience = row.getInteger("experience");
      _experience = __experience;
      __experienceUp = row.getInteger("experience_up");
      _experienceUp = __experienceUp;
      __label = row.get("label");
      _label = __label;
      __luck = row.getInteger("luck");
      _luck = __luck;
      __obtainTime.parse(row.get("obtain_time"));
      _obtainTime.assign(__obtainTime);
      __itemBagCd = row.getInteger("item_bag_cd");
      _itemBagCd = __itemBagCd;
      __itemBagId = RLong.parse(row.get("item_bag_id"));
      _itemBagId = __itemBagId;
      __itemBagIndex = row.getInteger("item_bag_index");
      _itemBagIndex = __itemBagIndex;
      __itemBagShortcutId = RLong.parse(row.get("item_bag_shortcut_id"));
      _itemBagShortcutId = __itemBagShortcutId;
      __itemBagShortcutIndex = row.getInteger("item_bag_shortcut_index");
      _itemBagShortcutIndex = __itemBagShortcutIndex;
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