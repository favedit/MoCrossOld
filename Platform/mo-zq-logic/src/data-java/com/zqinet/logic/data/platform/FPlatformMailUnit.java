package com.zqinet.logic.data.platform;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>系统邮件逻辑单元。</T>
//============================================================
public class FPlatformMailUnit extends FLogicUnit
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

   // 存储字段角色的个数的定义。
   private int __roleCount;

   // 字段角色的个数的定义。
   protected int _roleCount;

   // 存储字段收件人角色姓名的定义。
   private String __roleLabel;

   // 字段收件人角色姓名的定义。
   protected String _roleLabel;

   // 存储字段性别的定义。
   private int __gender;

   // 字段性别的定义。
   protected int _gender;

   // 存储字段玩家最小等级的定义。
   private int __roleMixLevel;

   // 字段玩家最小等级的定义。
   protected int _roleMixLevel;

   // 存储字段玩家最大等级的定义。
   private int __roleMaxLevel;

   // 字段玩家最大等级的定义。
   protected int _roleMaxLevel;

   // 存储字段门派编号的定义。
   private long __metierId;

   // 字段门派编号的定义。
   protected long _metierId;

   // 存储字段主题的定义。
   private String __topic;

   // 字段主题的定义。
   protected String _topic;

   // 存储字段内容的定义。
   private String __content;

   // 字段内容的定义。
   protected String _content;

   // 存储字段发送时间的定义。
   private TDateTime __sendTime = new TDateTime();

   // 字段发送时间的定义。
   protected TDateTime _sendTime = new TDateTime();

   // 存储字段变更时间的定义。
   private TDateTime __modifyTime = new TDateTime();

   // 字段变更时间的定义。
   protected TDateTime _modifyTime = new TDateTime();

   // 存储字段是否已发送的定义。
   private int __isSend;

   // 字段是否已发送的定义。
   protected int _isSend;

   // 存储字段是否有附件的定义。
   private int __isAttachment;

   // 字段是否有附件的定义。
   protected int _isAttachment;

   // 存储字段过期时间的定义。
   private TDateTime __overTime = new TDateTime();

   // 字段过期时间的定义。
   protected TDateTime _overTime = new TDateTime();

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
   // <T>构造系统邮件逻辑单元。</T>
   //============================================================
   public FPlatformMailUnit(){
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
   // <T>判断角色的个数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleCountChanged(){
      return __roleCount != _roleCount;
   }

   //============================================================
   // <T>获得角色的个数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleCount(){
      return _roleCount;
   }

   //============================================================
   // <T>设置角色的个数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleCount(int value){
      _roleCount = value;
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
   // <T>判断玩家最小等级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleMixLevelChanged(){
      return __roleMixLevel != _roleMixLevel;
   }

   //============================================================
   // <T>获得玩家最小等级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleMixLevel(){
      return _roleMixLevel;
   }

   //============================================================
   // <T>设置玩家最小等级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleMixLevel(int value){
      _roleMixLevel = value;
   }

   //============================================================
   // <T>判断玩家最大等级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleMaxLevelChanged(){
      return __roleMaxLevel != _roleMaxLevel;
   }

   //============================================================
   // <T>获得玩家最大等级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleMaxLevel(){
      return _roleMaxLevel;
   }

   //============================================================
   // <T>设置玩家最大等级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleMaxLevel(int value){
      _roleMaxLevel = value;
   }

   //============================================================
   // <T>判断门派编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMetierIdChanged(){
      return __metierId != _metierId;
   }

   //============================================================
   // <T>获得门派编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long metierId(){
      return _metierId;
   }

   //============================================================
   // <T>设置门派编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMetierId(long value){
      _metierId = value;
   }

   //============================================================
   // <T>判断主题的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTopicChanged(){
      return !RString.equals(__topic, _topic);
   }

   //============================================================
   // <T>获得主题的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String topic(){
      return _topic;
   }

   //============================================================
   // <T>设置主题的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTopic(String value){
      _topic = value;
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
   // <T>判断变更时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isModifyTimeChanged(){
      return !__modifyTime.equals(_modifyTime);
   }

   //============================================================
   // <T>获得变更时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime modifyTime(){
      return _modifyTime;
   }

   //============================================================
   // <T>设置变更时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setModifyTime(TDateTime value){
      _modifyTime = value;
   }

   //============================================================
   // <T>判断是否已发送的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isIsSendChanged(){
      return __isSend != _isSend;
   }

   //============================================================
   // <T>获得是否已发送的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int isSend(){
      return _isSend;
   }

   //============================================================
   // <T>设置是否已发送的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setIsSend(int value){
      _isSend = value;
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
      __roleCount = row.getInteger("role_count");
      _roleCount = __roleCount;
      __roleLabel = row.get("role_label");
      _roleLabel = __roleLabel;
      __gender = row.getInteger("gender");
      _gender = __gender;
      __roleMixLevel = row.getInteger("role_mix_level");
      _roleMixLevel = __roleMixLevel;
      __roleMaxLevel = row.getInteger("role_max_level");
      _roleMaxLevel = __roleMaxLevel;
      __metierId = RLong.parse(row.get("metier_id"));
      _metierId = __metierId;
      __topic = row.get("topic");
      _topic = __topic;
      __content = row.get("content");
      _content = __content;
      __sendTime.parse(row.get("send_time"));
      _sendTime.assign(__sendTime);
      __modifyTime.parse(row.get("modify_time"));
      _modifyTime.assign(__modifyTime);
      __isSend = row.getInteger("is_send");
      _isSend = __isSend;
      __isAttachment = row.getInteger("is_attachment");
      _isAttachment = __isAttachment;
      __overTime.parse(row.get("over_time"));
      _overTime.assign(__overTime);
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