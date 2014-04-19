package com.zqinet.logic.data.platform;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>GM工具控制逻辑单元。</T>
//============================================================
public class FPlatformGmControlUnit extends FLogicUnit
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

   // 存储字段控制状态的定义。
   private int __operateCd;

   // 字段控制状态的定义。
   protected int _operateCd;

   // 存储字段控制类型的定义。
   private int __cmd;

   // 字段控制类型的定义。
   protected int _cmd;

   // 存储字段角色编号的定义。
   private long __roleId;

   // 字段角色编号的定义。
   protected long _roleId;

   // 存储字段角色名的定义。
   private String __label;

   // 字段角色名的定义。
   protected String _label;

   // 存储字段开始时间的定义。
   private TDateTime __beginDate = new TDateTime();

   // 字段开始时间的定义。
   protected TDateTime _beginDate = new TDateTime();

   // 存储字段结束时间的定义。
   private TDateTime __endDate = new TDateTime();

   // 字段结束时间的定义。
   protected TDateTime _endDate = new TDateTime();

   // 存储字段参数1的定义。
   private long __param1;

   // 字段参数1的定义。
   protected long _param1;

   // 存储字段参数2的定义。
   private String __param2;

   // 字段参数2的定义。
   protected String _param2;

   // 存储字段参数3的定义。
   private String __param3;

   // 字段参数3的定义。
   protected String _param3;

   // 存储字段操作人名称的定义。
   private String __controlUser;

   // 字段操作人名称的定义。
   protected String _controlUser;

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
   // <T>构造GM工具控制逻辑单元。</T>
   //============================================================
   public FPlatformGmControlUnit(){
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
   // <T>判断控制状态的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isOperateCdChanged(){
      return __operateCd != _operateCd;
   }

   //============================================================
   // <T>获得控制状态的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int operateCd(){
      return _operateCd;
   }

   //============================================================
   // <T>设置控制状态的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setOperateCd(int value){
      _operateCd = value;
   }

   //============================================================
   // <T>判断控制类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCmdChanged(){
      return __cmd != _cmd;
   }

   //============================================================
   // <T>获得控制类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int cmd(){
      return _cmd;
   }

   //============================================================
   // <T>设置控制类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCmd(int value){
      _cmd = value;
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
   // <T>判断角色名的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLabelChanged(){
      return !RString.equals(__label, _label);
   }

   //============================================================
   // <T>获得角色名的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String label(){
      return _label;
   }

   //============================================================
   // <T>设置角色名的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLabel(String value){
      _label = value;
   }

   //============================================================
   // <T>判断开始时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isBeginDateChanged(){
      return !__beginDate.equals(_beginDate);
   }

   //============================================================
   // <T>获得开始时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime beginDate(){
      return _beginDate;
   }

   //============================================================
   // <T>设置开始时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setBeginDate(TDateTime value){
      _beginDate = value;
   }

   //============================================================
   // <T>判断结束时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isEndDateChanged(){
      return !__endDate.equals(_endDate);
   }

   //============================================================
   // <T>获得结束时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime endDate(){
      return _endDate;
   }

   //============================================================
   // <T>设置结束时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setEndDate(TDateTime value){
      _endDate = value;
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
   public long param1(){
      return _param1;
   }

   //============================================================
   // <T>设置参数1的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setParam1(long value){
      _param1 = value;
   }

   //============================================================
   // <T>判断参数2的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isParam2Changed(){
      return !RString.equals(__param2, _param2);
   }

   //============================================================
   // <T>获得参数2的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String param2(){
      return _param2;
   }

   //============================================================
   // <T>设置参数2的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setParam2(String value){
      _param2 = value;
   }

   //============================================================
   // <T>判断参数3的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isParam3Changed(){
      return !RString.equals(__param3, _param3);
   }

   //============================================================
   // <T>获得参数3的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String param3(){
      return _param3;
   }

   //============================================================
   // <T>设置参数3的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setParam3(String value){
      _param3 = value;
   }

   //============================================================
   // <T>判断操作人名称的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isControlUserChanged(){
      return !RString.equals(__controlUser, _controlUser);
   }

   //============================================================
   // <T>获得操作人名称的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String controlUser(){
      return _controlUser;
   }

   //============================================================
   // <T>设置操作人名称的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setControlUser(String value){
      _controlUser = value;
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
      __operateCd = row.getInteger("operate_cd");
      _operateCd = __operateCd;
      __cmd = row.getInteger("cmd");
      _cmd = __cmd;
      __roleId = RLong.parse(row.get("role_id"));
      _roleId = __roleId;
      __label = row.get("label");
      _label = __label;
      __beginDate.parse(row.get("begin_date"));
      _beginDate.assign(__beginDate);
      __endDate.parse(row.get("end_date"));
      _endDate.assign(__endDate);
      __param1 = RLong.parse(row.get("param1"));
      _param1 = __param1;
      __param2 = row.get("param2");
      _param2 = __param2;
      __param3 = row.get("param3");
      _param3 = __param3;
      __controlUser = row.get("control_user");
      _controlUser = __controlUser;
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