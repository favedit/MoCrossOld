package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>物品预警逻辑单元。</T>
//============================================================
public class FGameTemplateAlarmItemUnit extends FLogicUnit
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

   // 存储字段模版tid的定义。
   private int __tid;

   // 字段模版tid的定义。
   protected int _tid;

   // 存储字段名称的定义。
   private String __label;

   // 字段名称的定义。
   protected String _label;

   // 存储字段单次获得数量的定义。
   private int __singleMaxCount;

   // 字段单次获得数量的定义。
   protected int _singleMaxCount;

   // 存储字段每10分钟获得次数的定义。
   private int __tenMinCount;

   // 字段每10分钟获得次数的定义。
   protected int _tenMinCount;

   // 存储字段每10分钟增加数量的定义。
   private int __tenMinSum;

   // 字段每10分钟增加数量的定义。
   protected int _tenMinSum;

   // 存储字段一小时增加次数的定义。
   private int __hourCount;

   // 字段一小时增加次数的定义。
   protected int _hourCount;

   // 存储字段一小时增加数量的定义。
   private int __hourSum;

   // 字段一小时增加数量的定义。
   protected int _hourSum;

   // 存储字段每天增加数量的定义。
   private int __daySum;

   // 字段每天增加数量的定义。
   protected int _daySum;

   // 存储字段物品类型的定义。
   private int __itemType;

   // 字段物品类型的定义。
   protected int _itemType;

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
   // <T>构造物品预警逻辑单元。</T>
   //============================================================
   public FGameTemplateAlarmItemUnit(){
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
   // <T>判断模版tid的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTidChanged(){
      return __tid != _tid;
   }

   //============================================================
   // <T>获得模版tid的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int tid(){
      return _tid;
   }

   //============================================================
   // <T>设置模版tid的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTid(int value){
      _tid = value;
   }

   //============================================================
   // <T>判断名称的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLabelChanged(){
      return !RString.equals(__label, _label);
   }

   //============================================================
   // <T>获得名称的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String label(){
      return _label;
   }

   //============================================================
   // <T>设置名称的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLabel(String value){
      _label = value;
   }

   //============================================================
   // <T>判断单次获得数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSingleMaxCountChanged(){
      return __singleMaxCount != _singleMaxCount;
   }

   //============================================================
   // <T>获得单次获得数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int singleMaxCount(){
      return _singleMaxCount;
   }

   //============================================================
   // <T>设置单次获得数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSingleMaxCount(int value){
      _singleMaxCount = value;
   }

   //============================================================
   // <T>判断每10分钟获得次数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTenMinCountChanged(){
      return __tenMinCount != _tenMinCount;
   }

   //============================================================
   // <T>获得每10分钟获得次数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int tenMinCount(){
      return _tenMinCount;
   }

   //============================================================
   // <T>设置每10分钟获得次数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTenMinCount(int value){
      _tenMinCount = value;
   }

   //============================================================
   // <T>判断每10分钟增加数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTenMinSumChanged(){
      return __tenMinSum != _tenMinSum;
   }

   //============================================================
   // <T>获得每10分钟增加数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int tenMinSum(){
      return _tenMinSum;
   }

   //============================================================
   // <T>设置每10分钟增加数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTenMinSum(int value){
      _tenMinSum = value;
   }

   //============================================================
   // <T>判断一小时增加次数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isHourCountChanged(){
      return __hourCount != _hourCount;
   }

   //============================================================
   // <T>获得一小时增加次数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int hourCount(){
      return _hourCount;
   }

   //============================================================
   // <T>设置一小时增加次数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setHourCount(int value){
      _hourCount = value;
   }

   //============================================================
   // <T>判断一小时增加数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isHourSumChanged(){
      return __hourSum != _hourSum;
   }

   //============================================================
   // <T>获得一小时增加数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int hourSum(){
      return _hourSum;
   }

   //============================================================
   // <T>设置一小时增加数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setHourSum(int value){
      _hourSum = value;
   }

   //============================================================
   // <T>判断每天增加数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isDaySumChanged(){
      return __daySum != _daySum;
   }

   //============================================================
   // <T>获得每天增加数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int daySum(){
      return _daySum;
   }

   //============================================================
   // <T>设置每天增加数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDaySum(int value){
      _daySum = value;
   }

   //============================================================
   // <T>判断物品类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isItemTypeChanged(){
      return __itemType != _itemType;
   }

   //============================================================
   // <T>获得物品类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int itemType(){
      return _itemType;
   }

   //============================================================
   // <T>设置物品类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setItemType(int value){
      _itemType = value;
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
      __tid = row.getInteger("tid");
      _tid = __tid;
      __label = row.get("label");
      _label = __label;
      __singleMaxCount = row.getInteger("single_max_count");
      _singleMaxCount = __singleMaxCount;
      __tenMinCount = row.getInteger("ten_min_count");
      _tenMinCount = __tenMinCount;
      __tenMinSum = row.getInteger("ten_min_sum");
      _tenMinSum = __tenMinSum;
      __hourCount = row.getInteger("hour_count");
      _hourCount = __hourCount;
      __hourSum = row.getInteger("hour_sum");
      _hourSum = __hourSum;
      __daySum = row.getInteger("day_sum");
      _daySum = __daySum;
      __itemType = row.getInteger("item_type");
      _itemType = __itemType;
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
