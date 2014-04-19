package com.zqinet.logic.data.platform;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>玩家bug列表逻辑单元。</T>
//============================================================
public class FPlatformBugItemUnit extends FLogicUnit
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

   // 存储字段角色姓名的定义。
   private String __roleLabel;

   // 字段角色姓名的定义。
   protected String _roleLabel;

   // 存储字段主题的定义。
   private String __topic;

   // 字段主题的定义。
   protected String _topic;

   // 存储字段内容的定义。
   private String __content;

   // 字段内容的定义。
   protected String _content;

   // 存储字段类型的定义。
   private int __type;

   // 字段类型的定义。
   protected int _type;

   // 存储字段是否有效的定义。
   private int __isValid;

   // 字段是否有效的定义。
   protected int _isValid;

   // 存储字段qq号码的定义。
   private String __qq;

   // 字段qq号码的定义。
   protected String _qq;

   // 存储字段手机号的定义。
   private String __phone;

   // 字段手机号的定义。
   protected String _phone;

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
   // <T>构造玩家bug列表逻辑单元。</T>
   //============================================================
   public FPlatformBugItemUnit(){
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
   // <T>判断角色姓名的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleLabelChanged(){
      return !RString.equals(__roleLabel, _roleLabel);
   }

   //============================================================
   // <T>获得角色姓名的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String roleLabel(){
      return _roleLabel;
   }

   //============================================================
   // <T>设置角色姓名的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleLabel(String value){
      _roleLabel = value;
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
   // <T>判断类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTypeChanged(){
      return __type != _type;
   }

   //============================================================
   // <T>获得类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int type(){
      return _type;
   }

   //============================================================
   // <T>设置类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setType(int value){
      _type = value;
   }

   //============================================================
   // <T>判断是否有效的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isIsValidChanged(){
      return __isValid != _isValid;
   }

   //============================================================
   // <T>获得是否有效的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int isValid(){
      return _isValid;
   }

   //============================================================
   // <T>设置是否有效的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setIsValid(int value){
      _isValid = value;
   }

   //============================================================
   // <T>判断qq号码的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isQqChanged(){
      return !RString.equals(__qq, _qq);
   }

   //============================================================
   // <T>获得qq号码的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String qq(){
      return _qq;
   }

   //============================================================
   // <T>设置qq号码的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setQq(String value){
      _qq = value;
   }

   //============================================================
   // <T>判断手机号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPhoneChanged(){
      return !RString.equals(__phone, _phone);
   }

   //============================================================
   // <T>获得手机号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String phone(){
      return _phone;
   }

   //============================================================
   // <T>设置手机号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPhone(String value){
      _phone = value;
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
      __topic = row.get("topic");
      _topic = __topic;
      __content = row.get("content");
      _content = __content;
      __type = row.getInteger("type");
      _type = __type;
      __isValid = row.getInteger("is_valid");
      _isValid = __isValid;
      __qq = row.get("qq");
      _qq = __qq;
      __phone = row.get("phone");
      _phone = __phone;
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