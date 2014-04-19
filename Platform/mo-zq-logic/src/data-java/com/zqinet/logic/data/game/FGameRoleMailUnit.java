package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>邮件信息逻辑单元。</T>
//============================================================
public class FGameRoleMailUnit extends FLogicUnit
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

   // 存储字段收件人角色编号的定义。
   private long __roleId;

   // 字段收件人角色编号的定义。
   protected long _roleId;

   // 存储字段收件人角色姓名的定义。
   private String __roleLabel;

   // 字段收件人角色姓名的定义。
   protected String _roleLabel;

   // 存储字段邮件类型的定义。
   private int __typeCd;

   // 字段邮件类型的定义。
   protected int _typeCd;

   // 存储字段是否阅读的定义。
   private int __isRead;

   // 字段是否阅读的定义。
   protected int _isRead;

   // 存储字段是否有附件的定义。
   private int __isAttachment;

   // 字段是否有附件的定义。
   protected int _isAttachment;

   // 存储字段发件人编号的定义。
   private long __senderId;

   // 字段发件人编号的定义。
   protected long _senderId;

   // 存储字段发件人姓名的定义。
   private String __senderLabel;

   // 字段发件人姓名的定义。
   protected String _senderLabel;

   // 存储字段主题的定义。
   private String __theme;

   // 字段主题的定义。
   protected String _theme;

   // 存储字段内容的定义。
   private String __content;

   // 字段内容的定义。
   protected String _content;

   // 存储字段物品编号的定义。
   private long __itemId;

   // 字段物品编号的定义。
   protected long _itemId;

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

   // 存储字段货币类型的定义。
   private int __currencyCd;

   // 字段货币类型的定义。
   protected int _currencyCd;

   // 存储字段货币数量的定义。
   private int __currencyValue;

   // 字段货币数量的定义。
   protected int _currencyValue;

   // 存储字段发送时间的定义。
   private TDateTime __sendTime = new TDateTime();

   // 字段发送时间的定义。
   protected TDateTime _sendTime = new TDateTime();

   // 存储字段过期时间的定义。
   private TDateTime __overTime = new TDateTime();

   // 字段过期时间的定义。
   protected TDateTime _overTime = new TDateTime();

   // 存储字段是否已删除的定义。
   private int __isDelete;

   // 字段是否已删除的定义。
   protected int _isDelete;

   // 存储字段可删除时间的定义。
   private TDateTime __canDeleteTime = new TDateTime();

   // 字段可删除时间的定义。
   protected TDateTime _canDeleteTime = new TDateTime();

   // 存储字段GM邮件编号的定义。
   private long __gmMailId;

   // 字段GM邮件编号的定义。
   protected long _gmMailId;

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
   // <T>构造邮件信息逻辑单元。</T>
   //============================================================
   public FGameRoleMailUnit(){
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
   // <T>判断收件人角色编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleIdChanged(){
      return __roleId != _roleId;
   }

   //============================================================
   // <T>获得收件人角色编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long roleId(){
      return _roleId;
   }

   //============================================================
   // <T>设置收件人角色编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleId(long value){
      _roleId = value;
   }

   //============================================================
   // <T>判断收件人角色姓名的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleLabelChanged(){
      return !RString.equals(__roleLabel, _roleLabel);
   }

   //============================================================
   // <T>获得收件人角色姓名的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String roleLabel(){
      return _roleLabel;
   }

   //============================================================
   // <T>设置收件人角色姓名的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleLabel(String value){
      _roleLabel = value;
   }

   //============================================================
   // <T>判断邮件类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTypeCdChanged(){
      return __typeCd != _typeCd;
   }

   //============================================================
   // <T>获得邮件类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int typeCd(){
      return _typeCd;
   }

   //============================================================
   // <T>设置邮件类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTypeCd(int value){
      _typeCd = value;
   }

   //============================================================
   // <T>判断是否阅读的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isIsReadChanged(){
      return __isRead != _isRead;
   }

   //============================================================
   // <T>获得是否阅读的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int isRead(){
      return _isRead;
   }

   //============================================================
   // <T>设置是否阅读的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setIsRead(int value){
      _isRead = value;
   }

   //============================================================
   // <T>判断是否有附件的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isIsAttachmentChanged(){
      return __isAttachment != _isAttachment;
   }

   //============================================================
   // <T>获得是否有附件的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int isAttachment(){
      return _isAttachment;
   }

   //============================================================
   // <T>设置是否有附件的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setIsAttachment(int value){
      _isAttachment = value;
   }

   //============================================================
   // <T>判断发件人编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSenderIdChanged(){
      return __senderId != _senderId;
   }

   //============================================================
   // <T>获得发件人编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long senderId(){
      return _senderId;
   }

   //============================================================
   // <T>设置发件人编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSenderId(long value){
      _senderId = value;
   }

   //============================================================
   // <T>判断发件人姓名的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSenderLabelChanged(){
      return !RString.equals(__senderLabel, _senderLabel);
   }

   //============================================================
   // <T>获得发件人姓名的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String senderLabel(){
      return _senderLabel;
   }

   //============================================================
   // <T>设置发件人姓名的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSenderLabel(String value){
      _senderLabel = value;
   }

   //============================================================
   // <T>判断主题的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isThemeChanged(){
      return !RString.equals(__theme, _theme);
   }

   //============================================================
   // <T>获得主题的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String theme(){
      return _theme;
   }

   //============================================================
   // <T>设置主题的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTheme(String value){
      _theme = value;
   }

   //============================================================
   // <T>判断内容的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isContentChanged(){
      return !RString.equals(__content, _content);
   }

   //============================================================
   // <T>获得内容的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String content(){
      return _content;
   }

   //============================================================
   // <T>设置内容的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setContent(String value){
      _content = value;
   }

   //============================================================
   // <T>判断物品编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isItemIdChanged(){
      return __itemId != _itemId;
   }

   //============================================================
   // <T>获得物品编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long itemId(){
      return _itemId;
   }

   //============================================================
   // <T>设置物品编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setItemId(long value){
      _itemId = value;
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
   // <T>判断货币类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCurrencyCdChanged(){
      return __currencyCd != _currencyCd;
   }

   //============================================================
   // <T>获得货币类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int currencyCd(){
      return _currencyCd;
   }

   //============================================================
   // <T>设置货币类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCurrencyCd(int value){
      _currencyCd = value;
   }

   //============================================================
   // <T>判断货币数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCurrencyValueChanged(){
      return __currencyValue != _currencyValue;
   }

   //============================================================
   // <T>获得货币数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int currencyValue(){
      return _currencyValue;
   }

   //============================================================
   // <T>设置货币数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCurrencyValue(int value){
      _currencyValue = value;
   }

   //============================================================
   // <T>判断发送时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSendTimeChanged(){
      return !__sendTime.equals(_sendTime);
   }

   //============================================================
   // <T>获得发送时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime sendTime(){
      return _sendTime;
   }

   //============================================================
   // <T>设置发送时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSendTime(TDateTime value){
      _sendTime = value;
   }

   //============================================================
   // <T>判断过期时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isOverTimeChanged(){
      return !__overTime.equals(_overTime);
   }

   //============================================================
   // <T>获得过期时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime overTime(){
      return _overTime;
   }

   //============================================================
   // <T>设置过期时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setOverTime(TDateTime value){
      _overTime = value;
   }

   //============================================================
   // <T>判断是否已删除的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isIsDeleteChanged(){
      return __isDelete != _isDelete;
   }

   //============================================================
   // <T>获得是否已删除的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int isDelete(){
      return _isDelete;
   }

   //============================================================
   // <T>设置是否已删除的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setIsDelete(int value){
      _isDelete = value;
   }

   //============================================================
   // <T>判断可删除时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCanDeleteTimeChanged(){
      return !__canDeleteTime.equals(_canDeleteTime);
   }

   //============================================================
   // <T>获得可删除时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime canDeleteTime(){
      return _canDeleteTime;
   }

   //============================================================
   // <T>设置可删除时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCanDeleteTime(TDateTime value){
      _canDeleteTime = value;
   }

   //============================================================
   // <T>判断GM邮件编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isGmMailIdChanged(){
      return __gmMailId != _gmMailId;
   }

   //============================================================
   // <T>获得GM邮件编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long gmMailId(){
      return _gmMailId;
   }

   //============================================================
   // <T>设置GM邮件编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setGmMailId(long value){
      _gmMailId = value;
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
      __roleLabel = row.get("role_label");
      _roleLabel = __roleLabel;
      __typeCd = row.getInteger("type_cd");
      _typeCd = __typeCd;
      __isRead = row.getInteger("is_read");
      _isRead = __isRead;
      __isAttachment = row.getInteger("is_attachment");
      _isAttachment = __isAttachment;
      __senderId = RLong.parse(row.get("sender_id"));
      _senderId = __senderId;
      __senderLabel = row.get("sender_label");
      _senderLabel = __senderLabel;
      __theme = row.get("theme");
      _theme = __theme;
      __content = row.get("content");
      _content = __content;
      __itemId = RLong.parse(row.get("item_id"));
      _itemId = __itemId;
      __itemTypeCd = row.getInteger("item_type_cd");
      _itemTypeCd = __itemTypeCd;
      __itemBindType = row.getInteger("item_bind_type");
      _itemBindType = __itemBindType;
      __itemCount = row.getInteger("item_count");
      _itemCount = __itemCount;
      __qualityTid = row.getInteger("quality_tid");
      _qualityTid = __qualityTid;
      __currencyCd = row.getInteger("currency_cd");
      _currencyCd = __currencyCd;
      __currencyValue = row.getInteger("currency_value");
      _currencyValue = __currencyValue;
      __sendTime.parse(row.get("send_time"));
      _sendTime.assign(__sendTime);
      __overTime.parse(row.get("over_time"));
      _overTime.assign(__overTime);
      __isDelete = row.getInteger("is_delete");
      _isDelete = __isDelete;
      __canDeleteTime.parse(row.get("can_delete_time"));
      _canDeleteTime.assign(__canDeleteTime);
      __gmMailId = RLong.parse(row.get("gm_mail_id"));
      _gmMailId = __gmMailId;
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