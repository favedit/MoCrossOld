package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>角色装备信息逻辑单元。</T>
//============================================================
public class FGameRoleEquipUnit extends FLogicUnit
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

   // 存储字段装备模板编号的定义。
   private int __equipTid;

   // 字段装备模板编号的定义。
   protected int _equipTid;

   // 存储字段个数的定义。
   private int __count;

   // 字段个数的定义。
   protected int _count;

   // 存储字段插槽类型的定义。
   private int __slotCd;

   // 字段插槽类型的定义。
   protected int _slotCd;

   // 存储字段任务编号的定义。
   private long __taskId;

   // 字段任务编号的定义。
   protected long _taskId;

   // 存储字段交易类型的定义。
   private int __tradeCd;

   // 字段交易类型的定义。
   protected int _tradeCd;

   // 存储字段星级的定义。
   private int __stars;

   // 字段星级的定义。
   protected int _stars;

   // 存储字段装备宝石插槽的定义。
   private String __equipGemSlot;

   // 字段装备宝石插槽的定义。
   protected String _equipGemSlot;

   // 存储字段装备鉴定次数的定义。
   private int __equipAppreciateTimes;

   // 字段装备鉴定次数的定义。
   protected int _equipAppreciateTimes;

   // 存储字段装备强化次数的定义。
   private int __equipStarsTimes;

   // 字段装备强化次数的定义。
   protected int _equipStarsTimes;

   // 存储字段耐久的定义。
   private int __wear;

   // 字段耐久的定义。
   protected int _wear;

   // 存储字段绑定类型的定义。
   private int __bindCd;

   // 字段绑定类型的定义。
   protected int _bindCd;

   // 存储字段绑定状态的定义。
   private int __bindStatus;

   // 字段绑定状态的定义。
   protected int _bindStatus;

   // 存储字段品级模版编号的定义。
   private int __qualityTid;

   // 字段品级模版编号的定义。
   protected int _qualityTid;

   // 存储字段鉴定内容打包的定义。
   private String __contentPack;

   // 字段鉴定内容打包的定义。
   protected String _contentPack;

   // 存储字段有效时间的定义。
   private int __effectiveTime;

   // 字段有效时间的定义。
   protected int _effectiveTime;

   // 存储字段结束时间的定义。
   private TDateTime __endTime = new TDateTime();

   // 字段结束时间的定义。
   protected TDateTime _endTime = new TDateTime();

   // 存储字段是否可续时的定义。
   private int __isContinue;

   // 字段是否可续时的定义。
   protected int _isContinue;

   // 存储字段是否到期的定义。
   private int __isTimeout;

   // 字段是否到期的定义。
   protected int _isTimeout;

   // 存储字段计时类型的定义。
   private int __timingType;

   // 字段计时类型的定义。
   protected int _timingType;

   // 存储字段开始计时时间的定义。
   private TDateTime __beginTime = new TDateTime();

   // 字段开始计时时间的定义。
   protected TDateTime _beginTime = new TDateTime();

   // 存储字段计时器编号的定义。
   private long __timeId;

   // 字段计时器编号的定义。
   protected long _timeId;

   // 存储字段当前鉴定内容打包的定义。
   private String __identifyPack;

   // 字段当前鉴定内容打包的定义。
   protected String _identifyPack;

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
   // <T>构造角色装备信息逻辑单元。</T>
   //============================================================
   public FGameRoleEquipUnit(){
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
   // <T>判断装备模板编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isEquipTidChanged(){
      return __equipTid != _equipTid;
   }

   //============================================================
   // <T>获得装备模板编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int equipTid(){
      return _equipTid;
   }

   //============================================================
   // <T>设置装备模板编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setEquipTid(int value){
      _equipTid = value;
   }

   //============================================================
   // <T>判断个数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCountChanged(){
      return __count != _count;
   }

   //============================================================
   // <T>获得个数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int count(){
      return _count;
   }

   //============================================================
   // <T>设置个数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCount(int value){
      _count = value;
   }

   //============================================================
   // <T>判断插槽类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSlotCdChanged(){
      return __slotCd != _slotCd;
   }

   //============================================================
   // <T>获得插槽类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int slotCd(){
      return _slotCd;
   }

   //============================================================
   // <T>设置插槽类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSlotCd(int value){
      _slotCd = value;
   }

   //============================================================
   // <T>判断任务编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTaskIdChanged(){
      return __taskId != _taskId;
   }

   //============================================================
   // <T>获得任务编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long taskId(){
      return _taskId;
   }

   //============================================================
   // <T>设置任务编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTaskId(long value){
      _taskId = value;
   }

   //============================================================
   // <T>判断交易类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTradeCdChanged(){
      return __tradeCd != _tradeCd;
   }

   //============================================================
   // <T>获得交易类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int tradeCd(){
      return _tradeCd;
   }

   //============================================================
   // <T>设置交易类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTradeCd(int value){
      _tradeCd = value;
   }

   //============================================================
   // <T>判断星级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isStarsChanged(){
      return __stars != _stars;
   }

   //============================================================
   // <T>获得星级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int stars(){
      return _stars;
   }

   //============================================================
   // <T>设置星级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setStars(int value){
      _stars = value;
   }

   //============================================================
   // <T>判断装备宝石插槽的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isEquipGemSlotChanged(){
      return !RString.equals(__equipGemSlot, _equipGemSlot);
   }

   //============================================================
   // <T>获得装备宝石插槽的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String equipGemSlot(){
      return _equipGemSlot;
   }

   //============================================================
   // <T>设置装备宝石插槽的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setEquipGemSlot(String value){
      _equipGemSlot = value;
   }

   //============================================================
   // <T>判断装备鉴定次数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isEquipAppreciateTimesChanged(){
      return __equipAppreciateTimes != _equipAppreciateTimes;
   }

   //============================================================
   // <T>获得装备鉴定次数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int equipAppreciateTimes(){
      return _equipAppreciateTimes;
   }

   //============================================================
   // <T>设置装备鉴定次数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setEquipAppreciateTimes(int value){
      _equipAppreciateTimes = value;
   }

   //============================================================
   // <T>判断装备强化次数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isEquipStarsTimesChanged(){
      return __equipStarsTimes != _equipStarsTimes;
   }

   //============================================================
   // <T>获得装备强化次数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int equipStarsTimes(){
      return _equipStarsTimes;
   }

   //============================================================
   // <T>设置装备强化次数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setEquipStarsTimes(int value){
      _equipStarsTimes = value;
   }

   //============================================================
   // <T>判断耐久的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isWearChanged(){
      return __wear != _wear;
   }

   //============================================================
   // <T>获得耐久的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int wear(){
      return _wear;
   }

   //============================================================
   // <T>设置耐久的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setWear(int value){
      _wear = value;
   }

   //============================================================
   // <T>判断绑定类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isBindCdChanged(){
      return __bindCd != _bindCd;
   }

   //============================================================
   // <T>获得绑定类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int bindCd(){
      return _bindCd;
   }

   //============================================================
   // <T>设置绑定类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setBindCd(int value){
      _bindCd = value;
   }

   //============================================================
   // <T>判断绑定状态的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isBindStatusChanged(){
      return __bindStatus != _bindStatus;
   }

   //============================================================
   // <T>获得绑定状态的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int bindStatus(){
      return _bindStatus;
   }

   //============================================================
   // <T>设置绑定状态的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setBindStatus(int value){
      _bindStatus = value;
   }

   //============================================================
   // <T>判断品级模版编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isQualityTidChanged(){
      return __qualityTid != _qualityTid;
   }

   //============================================================
   // <T>获得品级模版编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int qualityTid(){
      return _qualityTid;
   }

   //============================================================
   // <T>设置品级模版编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setQualityTid(int value){
      _qualityTid = value;
   }

   //============================================================
   // <T>判断鉴定内容打包的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isContentPackChanged(){
      return !RString.equals(__contentPack, _contentPack);
   }

   //============================================================
   // <T>获得鉴定内容打包的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String contentPack(){
      return _contentPack;
   }

   //============================================================
   // <T>设置鉴定内容打包的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setContentPack(String value){
      _contentPack = value;
   }

   //============================================================
   // <T>判断有效时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isEffectiveTimeChanged(){
      return __effectiveTime != _effectiveTime;
   }

   //============================================================
   // <T>获得有效时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int effectiveTime(){
      return _effectiveTime;
   }

   //============================================================
   // <T>设置有效时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setEffectiveTime(int value){
      _effectiveTime = value;
   }

   //============================================================
   // <T>判断结束时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isEndTimeChanged(){
      return !__endTime.equals(_endTime);
   }

   //============================================================
   // <T>获得结束时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime endTime(){
      return _endTime;
   }

   //============================================================
   // <T>设置结束时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setEndTime(TDateTime value){
      _endTime = value;
   }

   //============================================================
   // <T>判断是否可续时的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isIsContinueChanged(){
      return __isContinue != _isContinue;
   }

   //============================================================
   // <T>获得是否可续时的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int isContinue(){
      return _isContinue;
   }

   //============================================================
   // <T>设置是否可续时的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setIsContinue(int value){
      _isContinue = value;
   }

   //============================================================
   // <T>判断是否到期的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isIsTimeoutChanged(){
      return __isTimeout != _isTimeout;
   }

   //============================================================
   // <T>获得是否到期的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int isTimeout(){
      return _isTimeout;
   }

   //============================================================
   // <T>设置是否到期的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setIsTimeout(int value){
      _isTimeout = value;
   }

   //============================================================
   // <T>判断计时类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTimingTypeChanged(){
      return __timingType != _timingType;
   }

   //============================================================
   // <T>获得计时类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int timingType(){
      return _timingType;
   }

   //============================================================
   // <T>设置计时类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTimingType(int value){
      _timingType = value;
   }

   //============================================================
   // <T>判断开始计时时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isBeginTimeChanged(){
      return !__beginTime.equals(_beginTime);
   }

   //============================================================
   // <T>获得开始计时时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime beginTime(){
      return _beginTime;
   }

   //============================================================
   // <T>设置开始计时时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setBeginTime(TDateTime value){
      _beginTime = value;
   }

   //============================================================
   // <T>判断计时器编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTimeIdChanged(){
      return __timeId != _timeId;
   }

   //============================================================
   // <T>获得计时器编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long timeId(){
      return _timeId;
   }

   //============================================================
   // <T>设置计时器编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTimeId(long value){
      _timeId = value;
   }

   //============================================================
   // <T>判断当前鉴定内容打包的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isIdentifyPackChanged(){
      return !RString.equals(__identifyPack, _identifyPack);
   }

   //============================================================
   // <T>获得当前鉴定内容打包的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String identifyPack(){
      return _identifyPack;
   }

   //============================================================
   // <T>设置当前鉴定内容打包的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setIdentifyPack(String value){
      _identifyPack = value;
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
      __equipTid = row.getInteger("equip_tid");
      _equipTid = __equipTid;
      __count = row.getInteger("count");
      _count = __count;
      __slotCd = row.getInteger("slot_cd");
      _slotCd = __slotCd;
      __taskId = RLong.parse(row.get("task_id"));
      _taskId = __taskId;
      __tradeCd = row.getInteger("trade_cd");
      _tradeCd = __tradeCd;
      __stars = row.getInteger("stars");
      _stars = __stars;
      __equipGemSlot = row.get("equip_gem_slot");
      _equipGemSlot = __equipGemSlot;
      __equipAppreciateTimes = row.getInteger("equip_appreciate_times");
      _equipAppreciateTimes = __equipAppreciateTimes;
      __equipStarsTimes = row.getInteger("equip_stars_times");
      _equipStarsTimes = __equipStarsTimes;
      __wear = row.getInteger("wear");
      _wear = __wear;
      __bindCd = row.getInteger("bind_cd");
      _bindCd = __bindCd;
      __bindStatus = row.getInteger("bind_status");
      _bindStatus = __bindStatus;
      __qualityTid = row.getInteger("quality_tid");
      _qualityTid = __qualityTid;
      __contentPack = row.get("content_pack");
      _contentPack = __contentPack;
      __effectiveTime = row.getInteger("effective_time");
      _effectiveTime = __effectiveTime;
      __endTime.parse(row.get("end_time"));
      _endTime.assign(__endTime);
      __isContinue = row.getInteger("is_continue");
      _isContinue = __isContinue;
      __isTimeout = row.getInteger("is_timeout");
      _isTimeout = __isTimeout;
      __timingType = row.getInteger("timing_type");
      _timingType = __timingType;
      __beginTime.parse(row.get("begin_time"));
      _beginTime.assign(__beginTime);
      __timeId = RLong.parse(row.get("time_id"));
      _timeId = __timeId;
      __identifyPack = row.get("identify_pack");
      _identifyPack = __identifyPack;
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