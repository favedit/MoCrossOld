package com.zqinet.logic.data.platform;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>系统邮件附件逻辑单元。</T>
//============================================================
public class FPlatformMailAttachmentUnit extends FLogicUnit
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

   // 存储字段邮件编号的定义。
   private long __mailId;

   // 字段邮件编号的定义。
   protected long _mailId;

   // 存储字段物品模板编号的定义。
   private int __itemTid;

   // 字段物品模板编号的定义。
   protected int _itemTid;

   // 存储字段物品类型的定义。
   private int __itemTypeCd;

   // 字段物品类型的定义。
   protected int _itemTypeCd;

   // 存储字段物品绑定类型的定义。
   private int __itemBindType;

   // 字段物品绑定类型的定义。
   protected int _itemBindType;

   // 存储字段物品叠加数量的定义。
   private int __itemCount;

   // 字段物品叠加数量的定义。
   protected int _itemCount;

   // 存储字段品级模板编号的定义。
   private int __qualityTid;

   // 字段品级模板编号的定义。
   protected int _qualityTid;

   // 存储字段邮件货币类型的定义。
   private int __mailCurrencyCd;

   // 字段邮件货币类型的定义。
   protected int _mailCurrencyCd;

   // 存储字段邮件货币数值的定义。
   private int __mailCurrencyValue;

   // 字段邮件货币数值的定义。
   protected int _mailCurrencyValue;

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
   // <T>构造系统邮件附件逻辑单元。</T>
   //============================================================
   public FPlatformMailAttachmentUnit(){
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
   // <T>判断邮件编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMailIdChanged(){
      return __mailId != _mailId;
   }

   //============================================================
   // <T>获得邮件编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long mailId(){
      return _mailId;
   }

   //============================================================
   // <T>设置邮件编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMailId(long value){
      _mailId = value;
   }

   //============================================================
   // <T>判断物品模板编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isItemTidChanged(){
      return __itemTid != _itemTid;
   }

   //============================================================
   // <T>获得物品模板编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int itemTid(){
      return _itemTid;
   }

   //============================================================
   // <T>设置物品模板编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setItemTid(int value){
      _itemTid = value;
   }

   //============================================================
   // <T>判断物品类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isItemTypeCdChanged(){
      return __itemTypeCd != _itemTypeCd;
   }

   //============================================================
   // <T>获得物品类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int itemTypeCd(){
      return _itemTypeCd;
   }

   //============================================================
   // <T>设置物品类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setItemTypeCd(int value){
      _itemTypeCd = value;
   }

   //============================================================
   // <T>判断物品绑定类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isItemBindTypeChanged(){
      return __itemBindType != _itemBindType;
   }

   //============================================================
   // <T>获得物品绑定类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int itemBindType(){
      return _itemBindType;
   }

   //============================================================
   // <T>设置物品绑定类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setItemBindType(int value){
      _itemBindType = value;
   }

   //============================================================
   // <T>判断物品叠加数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isItemCountChanged(){
      return __itemCount != _itemCount;
   }

   //============================================================
   // <T>获得物品叠加数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int itemCount(){
      return _itemCount;
   }

   //============================================================
   // <T>设置物品叠加数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setItemCount(int value){
      _itemCount = value;
   }

   //============================================================
   // <T>判断品级模板编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isQualityTidChanged(){
      return __qualityTid != _qualityTid;
   }

   //============================================================
   // <T>获得品级模板编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int qualityTid(){
      return _qualityTid;
   }

   //============================================================
   // <T>设置品级模板编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setQualityTid(int value){
      _qualityTid = value;
   }

   //============================================================
   // <T>判断邮件货币类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMailCurrencyCdChanged(){
      return __mailCurrencyCd != _mailCurrencyCd;
   }

   //============================================================
   // <T>获得邮件货币类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int mailCurrencyCd(){
      return _mailCurrencyCd;
   }

   //============================================================
   // <T>设置邮件货币类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMailCurrencyCd(int value){
      _mailCurrencyCd = value;
   }

   //============================================================
   // <T>判断邮件货币数值的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMailCurrencyValueChanged(){
      return __mailCurrencyValue != _mailCurrencyValue;
   }

   //============================================================
   // <T>获得邮件货币数值的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int mailCurrencyValue(){
      return _mailCurrencyValue;
   }

   //============================================================
   // <T>设置邮件货币数值的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMailCurrencyValue(int value){
      _mailCurrencyValue = value;
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
      __mailId = RLong.parse(row.get("mail_id"));
      _mailId = __mailId;
      __itemTid = row.getInteger("item_tid");
      _itemTid = __itemTid;
      __itemTypeCd = row.getInteger("item_type_cd");
      _itemTypeCd = __itemTypeCd;
      __itemBindType = row.getInteger("item_bind_type");
      _itemBindType = __itemBindType;
      __itemCount = row.getInteger("item_count");
      _itemCount = __itemCount;
      __qualityTid = row.getInteger("quality_tid");
      _qualityTid = __qualityTid;
      __mailCurrencyCd = row.getInteger("mail_currency_cd");
      _mailCurrencyCd = __mailCurrencyCd;
      __mailCurrencyValue = row.getInteger("mail_currency_value");
      _mailCurrencyValue = __mailCurrencyValue;
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