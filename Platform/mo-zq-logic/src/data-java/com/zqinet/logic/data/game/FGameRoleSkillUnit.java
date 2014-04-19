package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>角色物品信息逻辑单元。</T>
//============================================================
public class FGameRoleSkillUnit extends FLogicUnit
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

   // 存储字段技能模板编号的定义。
   private int __skillTid;

   // 字段技能模板编号的定义。
   protected int _skillTid;

   // 存储字段级别的定义。
   private int __level;

   // 字段级别的定义。
   protected int _level;

   // 存储字段技能归属的定义。
   private int __belongCd;

   // 字段技能归属的定义。
   protected int _belongCd;

   // 存储字段归属编号的定义。
   private long __belongId;

   // 字段归属编号的定义。
   protected long _belongId;

   // 存储字段標誌的定义。
   private int __flags;

   // 字段標誌的定义。
   protected int _flags;

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
   // <T>构造角色物品信息逻辑单元。</T>
   //============================================================
   public FGameRoleSkillUnit(){
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
   // <T>判断技能模板编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSkillTidChanged(){
      return __skillTid != _skillTid;
   }

   //============================================================
   // <T>获得技能模板编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int skillTid(){
      return _skillTid;
   }

   //============================================================
   // <T>设置技能模板编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSkillTid(int value){
      _skillTid = value;
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
   // <T>判断技能归属的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isBelongCdChanged(){
      return __belongCd != _belongCd;
   }

   //============================================================
   // <T>获得技能归属的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int belongCd(){
      return _belongCd;
   }

   //============================================================
   // <T>设置技能归属的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setBelongCd(int value){
      _belongCd = value;
   }

   //============================================================
   // <T>判断归属编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isBelongIdChanged(){
      return __belongId != _belongId;
   }

   //============================================================
   // <T>获得归属编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long belongId(){
      return _belongId;
   }

   //============================================================
   // <T>设置归属编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setBelongId(long value){
      _belongId = value;
   }

   //============================================================
   // <T>判断標誌的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isFlagsChanged(){
      return __flags != _flags;
   }

   //============================================================
   // <T>获得標誌的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int flags(){
      return _flags;
   }

   //============================================================
   // <T>设置標誌的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setFlags(int value){
      _flags = value;
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
      __skillTid = row.getInteger("skill_tid");
      _skillTid = __skillTid;
      __level = row.getInteger("level");
      _level = __level;
      __belongCd = row.getInteger("belong_cd");
      _belongCd = __belongCd;
      __belongId = RLong.parse(row.get("belong_id"));
      _belongId = __belongId;
      __flags = row.getInteger("flags");
      _flags = __flags;
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