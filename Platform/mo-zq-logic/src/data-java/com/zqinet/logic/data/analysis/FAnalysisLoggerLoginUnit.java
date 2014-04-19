package com.zqinet.logic.data.analysis;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>日志分析登录逻辑单元。</T>
//============================================================
public class FAnalysisLoggerLoginUnit extends FLogicUnit
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

   // 存储字段游戏ID的定义。
   private long __gameId;

   // 字段游戏ID的定义。
   protected long _gameId;

   // 存储字段账号编号的定义。
   private long __accountId;

   // 字段账号编号的定义。
   protected long _accountId;

   // 存储字段角色编号的定义。
   private long __roleId;

   // 字段角色编号的定义。
   protected long _roleId;

   // 存储字段记录时间的定义。
   private TDateTime __recordDate = new TDateTime();

   // 字段记录时间的定义。
   protected TDateTime _recordDate = new TDateTime();

   // 存储字段记录天的定义。
   private TDateTime __recordDay = new TDateTime();

   // 字段记录天的定义。
   protected TDateTime _recordDay = new TDateTime();

   // 存储字段账号创建日期的定义。
   private TDateTime __accountCreateDate = new TDateTime();

   // 字段账号创建日期的定义。
   protected TDateTime _accountCreateDate = new TDateTime();

   // 存储字段角色创建中日期的定义。
   private TDateTime __roleCreatingDate = new TDateTime();

   // 字段角色创建中日期的定义。
   protected TDateTime _roleCreatingDate = new TDateTime();

   // 存储字段角色创建日期的定义。
   private TDateTime __roleCreateDate = new TDateTime();

   // 字段角色创建日期的定义。
   protected TDateTime _roleCreateDate = new TDateTime();

   // 存储字段角色登录日期的定义。
   private TDateTime __roleLoadDate = new TDateTime();

   // 字段角色登录日期的定义。
   protected TDateTime _roleLoadDate = new TDateTime();

   // 存储字段登录日期的定义。
   private TDateTime __roleLoginDate = new TDateTime();

   // 字段登录日期的定义。
   protected TDateTime _roleLoginDate = new TDateTime();

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
   // <T>构造日志分析登录逻辑单元。</T>
   //============================================================
   public FAnalysisLoggerLoginUnit(){
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
   // <T>判断游戏ID的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isGameIdChanged(){
      return __gameId != _gameId;
   }

   //============================================================
   // <T>获得游戏ID的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long gameId(){
      return _gameId;
   }

   //============================================================
   // <T>设置游戏ID的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setGameId(long value){
      _gameId = value;
   }

   //============================================================
   // <T>判断账号编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAccountIdChanged(){
      return __accountId != _accountId;
   }

   //============================================================
   // <T>获得账号编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long accountId(){
      return _accountId;
   }

   //============================================================
   // <T>设置账号编号的数据内容。</T>
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
   // <T>判断记录时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRecordDateChanged(){
      return !__recordDate.equals(_recordDate);
   }

   //============================================================
   // <T>获得记录时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime recordDate(){
      return _recordDate;
   }

   //============================================================
   // <T>设置记录时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRecordDate(TDateTime value){
      _recordDate = value;
   }

   //============================================================
   // <T>判断记录天的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRecordDayChanged(){
      return !__recordDay.equals(_recordDay);
   }

   //============================================================
   // <T>获得记录天的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime recordDay(){
      return _recordDay;
   }

   //============================================================
   // <T>设置记录天的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRecordDay(TDateTime value){
      _recordDay = value;
   }

   //============================================================
   // <T>判断账号创建日期的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAccountCreateDateChanged(){
      return !__accountCreateDate.equals(_accountCreateDate);
   }

   //============================================================
   // <T>获得账号创建日期的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime accountCreateDate(){
      return _accountCreateDate;
   }

   //============================================================
   // <T>设置账号创建日期的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAccountCreateDate(TDateTime value){
      _accountCreateDate = value;
   }

   //============================================================
   // <T>判断角色创建中日期的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleCreatingDateChanged(){
      return !__roleCreatingDate.equals(_roleCreatingDate);
   }

   //============================================================
   // <T>获得角色创建中日期的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime roleCreatingDate(){
      return _roleCreatingDate;
   }

   //============================================================
   // <T>设置角色创建中日期的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleCreatingDate(TDateTime value){
      _roleCreatingDate = value;
   }

   //============================================================
   // <T>判断角色创建日期的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleCreateDateChanged(){
      return !__roleCreateDate.equals(_roleCreateDate);
   }

   //============================================================
   // <T>获得角色创建日期的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime roleCreateDate(){
      return _roleCreateDate;
   }

   //============================================================
   // <T>设置角色创建日期的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleCreateDate(TDateTime value){
      _roleCreateDate = value;
   }

   //============================================================
   // <T>判断角色登录日期的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleLoadDateChanged(){
      return !__roleLoadDate.equals(_roleLoadDate);
   }

   //============================================================
   // <T>获得角色登录日期的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime roleLoadDate(){
      return _roleLoadDate;
   }

   //============================================================
   // <T>设置角色登录日期的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleLoadDate(TDateTime value){
      _roleLoadDate = value;
   }

   //============================================================
   // <T>判断登录日期的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleLoginDateChanged(){
      return !__roleLoginDate.equals(_roleLoginDate);
   }

   //============================================================
   // <T>获得登录日期的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime roleLoginDate(){
      return _roleLoginDate;
   }

   //============================================================
   // <T>设置登录日期的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleLoginDate(TDateTime value){
      _roleLoginDate = value;
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
      __gameId = RLong.parse(row.get("game_id"));
      _gameId = __gameId;
      __accountId = RLong.parse(row.get("account_id"));
      _accountId = __accountId;
      __roleId = RLong.parse(row.get("role_id"));
      _roleId = __roleId;
      __recordDate.parse(row.get("record_date"));
      _recordDate.assign(__recordDate);
      __recordDay.parse(row.get("record_day"));
      _recordDay.assign(__recordDay);
      __accountCreateDate.parse(row.get("account_create_date"));
      _accountCreateDate.assign(__accountCreateDate);
      __roleCreatingDate.parse(row.get("role_creating_date"));
      _roleCreatingDate.assign(__roleCreatingDate);
      __roleCreateDate.parse(row.get("role_create_date"));
      _roleCreateDate.assign(__roleCreateDate);
      __roleLoadDate.parse(row.get("role_load_date"));
      _roleLoadDate.assign(__roleLoadDate);
      __roleLoginDate.parse(row.get("role_login_date"));
      _roleLoginDate.assign(__roleLoginDate);
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