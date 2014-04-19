package com.zqinet.logic.data.platform;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>聊天记录逻辑单元。</T>
//============================================================
public class FPlatformChatRecordUnit extends FLogicUnit
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

   // 存储字段账号ID的定义。
   private long __accountId;

   // 字段账号ID的定义。
   protected long _accountId;

   // 存储字段角色编号的定义。
   private long __roleId;

   // 字段角色编号的定义。
   protected long _roleId;

   // 存储字段角色名称的定义。
   private String __roleLabel;

   // 字段角色名称的定义。
   protected String _roleLabel;

   // 存储字段说话类型的定义。
   private int __talkType;

   // 字段说话类型的定义。
   protected int _talkType;

   // 存储字段内容的定义。
   private String __content;

   // 字段内容的定义。
   protected String _content;

   // 存储字段状态的定义。
   private int __status;

   // 字段状态的定义。
   protected int _status;

   // 存储字段说话时间的定义。
   private TDateTime __talkTime = new TDateTime();

   // 字段说话时间的定义。
   protected TDateTime _talkTime = new TDateTime();

   // 存储字段账号的定义。
   private String __passport;

   // 字段账号的定义。
   protected String _passport;

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
   // <T>构造聊天记录逻辑单元。</T>
   //============================================================
   public FPlatformChatRecordUnit(){
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
   // <T>判断账号ID的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAccountIdChanged(){
      return __accountId != _accountId;
   }

   //============================================================
   // <T>获得账号ID的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long accountId(){
      return _accountId;
   }

   //============================================================
   // <T>设置账号ID的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAccountId(long value){
      _accountId = value;
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
   // <T>判断角色名称的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleLabelChanged(){
      return !RString.equals(__roleLabel, _roleLabel);
   }

   //============================================================
   // <T>获得角色名称的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String roleLabel(){
      return _roleLabel;
   }

   //============================================================
   // <T>设置角色名称的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleLabel(String value){
      _roleLabel = value;
   }

   //============================================================
   // <T>判断说话类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTalkTypeChanged(){
      return __talkType != _talkType;
   }

   //============================================================
   // <T>获得说话类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int talkType(){
      return _talkType;
   }

   //============================================================
   // <T>设置说话类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTalkType(int value){
      _talkType = value;
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
   // <T>判断状态的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isStatusChanged(){
      return __status != _status;
   }

   //============================================================
   // <T>获得状态的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int status(){
      return _status;
   }

   //============================================================
   // <T>设置状态的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setStatus(int value){
      _status = value;
   }

   //============================================================
   // <T>判断说话时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTalkTimeChanged(){
      return !__talkTime.equals(_talkTime);
   }

   //============================================================
   // <T>获得说话时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime talkTime(){
      return _talkTime;
   }

   //============================================================
   // <T>设置说话时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTalkTime(TDateTime value){
      _talkTime = value;
   }

   //============================================================
   // <T>判断账号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPassportChanged(){
      return !RString.equals(__passport, _passport);
   }

   //============================================================
   // <T>获得账号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String passport(){
      return _passport;
   }

   //============================================================
   // <T>设置账号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPassport(String value){
      _passport = value;
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
      __accountId = RLong.parse(row.get("account_id"));
      _accountId = __accountId;
      __roleId = RLong.parse(row.get("role_id"));
      _roleId = __roleId;
      __roleLabel = row.get("role_label");
      _roleLabel = __roleLabel;
      __talkType = row.getInteger("talk_type");
      _talkType = __talkType;
      __content = row.get("content");
      _content = __content;
      __status = row.getInteger("status");
      _status = __status;
      __talkTime.parse(row.get("talk_time"));
      _talkTime.assign(__talkTime);
      __passport = row.get("passport");
      _passport = __passport;
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