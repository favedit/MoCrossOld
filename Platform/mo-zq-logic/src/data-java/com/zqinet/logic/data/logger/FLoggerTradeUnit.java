package com.zqinet.logic.data.logger;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>玩家交易日志逻辑单元。</T>
//============================================================
public class FLoggerTradeUnit extends FLogicUnit
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

   // 存储字段交易类型的定义。
   private int __tradeType;

   // 字段交易类型的定义。
   protected int _tradeType;

   // 存储字段目标角色编号的定义。
   private long __targetRoleId;

   // 字段目标角色编号的定义。
   protected long _targetRoleId;

   // 存储字段交易的道具集合的定义。
   private String __propSet;

   // 字段交易的道具集合的定义。
   protected String _propSet;

   // 存储字段交易的装备集合的定义。
   private String __equipSet;

   // 字段交易的装备集合的定义。
   protected String _equipSet;

   // 存储字段交易的宠物编号的定义。
   private int __petTid;

   // 字段交易的宠物编号的定义。
   protected int _petTid;

   // 存储字段交易的银两的定义。
   private int __gold;

   // 字段交易的银两的定义。
   protected int _gold;

   // 存储字段交易的元宝数的定义。
   private int __money;

   // 字段交易的元宝数的定义。
   protected int _money;

   // 存储字段交易目标类型的定义。
   private int __itemCd;

   // 字段交易目标类型的定义。
   protected int _itemCd;

   // 存储字段交易目标编号的定义。
   private int __itemTid;

   // 字段交易目标编号的定义。
   protected int _itemTid;

   // 存储字段交易数量的定义。
   private int __itemCount;

   // 字段交易数量的定义。
   protected int _itemCount;

   // 存储字段参数1的定义。
   private int __param1;

   // 字段参数1的定义。
   protected int _param1;

   // 存储字段参数2的定义。
   private int __param2;

   // 字段参数2的定义。
   protected int _param2;

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
   // <T>构造玩家交易日志逻辑单元。</T>
   //============================================================
   public FLoggerTradeUnit(){
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
   // <T>判断交易类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTradeTypeChanged(){
      return __tradeType != _tradeType;
   }

   //============================================================
   // <T>获得交易类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int tradeType(){
      return _tradeType;
   }

   //============================================================
   // <T>设置交易类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTradeType(int value){
      _tradeType = value;
   }

   //============================================================
   // <T>判断目标角色编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTargetRoleIdChanged(){
      return __targetRoleId != _targetRoleId;
   }

   //============================================================
   // <T>获得目标角色编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long targetRoleId(){
      return _targetRoleId;
   }

   //============================================================
   // <T>设置目标角色编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTargetRoleId(long value){
      _targetRoleId = value;
   }

   //============================================================
   // <T>判断交易的道具集合的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPropSetChanged(){
      return !RString.equals(__propSet, _propSet);
   }

   //============================================================
   // <T>获得交易的道具集合的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String propSet(){
      return _propSet;
   }

   //============================================================
   // <T>设置交易的道具集合的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPropSet(String value){
      _propSet = value;
   }

   //============================================================
   // <T>判断交易的装备集合的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isEquipSetChanged(){
      return !RString.equals(__equipSet, _equipSet);
   }

   //============================================================
   // <T>获得交易的装备集合的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String equipSet(){
      return _equipSet;
   }

   //============================================================
   // <T>设置交易的装备集合的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setEquipSet(String value){
      _equipSet = value;
   }

   //============================================================
   // <T>判断交易的宠物编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPetTidChanged(){
      return __petTid != _petTid;
   }

   //============================================================
   // <T>获得交易的宠物编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int petTid(){
      return _petTid;
   }

   //============================================================
   // <T>设置交易的宠物编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPetTid(int value){
      _petTid = value;
   }

   //============================================================
   // <T>判断交易的银两的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isGoldChanged(){
      return __gold != _gold;
   }

   //============================================================
   // <T>获得交易的银两的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int gold(){
      return _gold;
   }

   //============================================================
   // <T>设置交易的银两的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setGold(int value){
      _gold = value;
   }

   //============================================================
   // <T>判断交易的元宝数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMoneyChanged(){
      return __money != _money;
   }

   //============================================================
   // <T>获得交易的元宝数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int money(){
      return _money;
   }

   //============================================================
   // <T>设置交易的元宝数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMoney(int value){
      _money = value;
   }

   //============================================================
   // <T>判断交易目标类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isItemCdChanged(){
      return __itemCd != _itemCd;
   }

   //============================================================
   // <T>获得交易目标类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int itemCd(){
      return _itemCd;
   }

   //============================================================
   // <T>设置交易目标类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setItemCd(int value){
      _itemCd = value;
   }

   //============================================================
   // <T>判断交易目标编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isItemTidChanged(){
      return __itemTid != _itemTid;
   }

   //============================================================
   // <T>获得交易目标编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int itemTid(){
      return _itemTid;
   }

   //============================================================
   // <T>设置交易目标编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setItemTid(int value){
      _itemTid = value;
   }

   //============================================================
   // <T>判断交易数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isItemCountChanged(){
      return __itemCount != _itemCount;
   }

   //============================================================
   // <T>获得交易数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int itemCount(){
      return _itemCount;
   }

   //============================================================
   // <T>设置交易数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setItemCount(int value){
      _itemCount = value;
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
      __tradeType = row.getInteger("trade_type");
      _tradeType = __tradeType;
      __targetRoleId = RLong.parse(row.get("target_role_id"));
      _targetRoleId = __targetRoleId;
      __propSet = row.get("prop_set");
      _propSet = __propSet;
      __equipSet = row.get("equip_set");
      _equipSet = __equipSet;
      __petTid = row.getInteger("pet_tid");
      _petTid = __petTid;
      __gold = row.getInteger("gold");
      _gold = __gold;
      __money = row.getInteger("money");
      _money = __money;
      __itemCd = row.getInteger("item_cd");
      _itemCd = __itemCd;
      __itemTid = row.getInteger("item_tid");
      _itemTid = __itemTid;
      __itemCount = row.getInteger("item_count");
      _itemCount = __itemCount;
      __param1 = row.getInteger("param1");
      _param1 = __param1;
      __param2 = row.getInteger("param2");
      _param2 = __param2;
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