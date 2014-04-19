package com.zqinet.logic.data.logger;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>登陆的客户端信息日志逻辑单元。</T>
//============================================================
public class FLoggerClientLoginUnit extends FLogicUnit
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

   // 存储字段账号代码的定义。
   private String __accountCode;

   // 字段账号代码的定义。
   protected String _accountCode;

   // 存储字段驱动信息的定义。
   private String __driverInfo;

   // 字段驱动信息的定义。
   protected String _driverInfo;

   // 存储字段flash操作系统的定义。
   private String __flashOs;

   // 字段flash操作系统的定义。
   protected String _flashOs;

   // 存储字段flash版本的定义。
   private String __flashVersion;

   // 字段flash版本的定义。
   protected String _flashVersion;

   // 存储字段flash鉴定信息的定义。
   private String __flashIdentity;

   // 字段flash鉴定信息的定义。
   protected String _flashIdentity;

   // 存储字段登陆主机的定义。
   private String __loginHost;

   // 字段登陆主机的定义。
   protected String _loginHost;

   // 存储字段登陆时间的定义。
   private TDateTime __loginDate = new TDateTime();

   // 字段登陆时间的定义。
   protected TDateTime _loginDate = new TDateTime();

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
   // <T>构造登陆的客户端信息日志逻辑单元。</T>
   //============================================================
   public FLoggerClientLoginUnit(){
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
   // <T>判断账号代码的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAccountCodeChanged(){
      return !RString.equals(__accountCode, _accountCode);
   }

   //============================================================
   // <T>获得账号代码的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String accountCode(){
      return _accountCode;
   }

   //============================================================
   // <T>设置账号代码的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAccountCode(String value){
      _accountCode = value;
   }

   //============================================================
   // <T>判断驱动信息的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isDriverInfoChanged(){
      return !RString.equals(__driverInfo, _driverInfo);
   }

   //============================================================
   // <T>获得驱动信息的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String driverInfo(){
      return _driverInfo;
   }

   //============================================================
   // <T>设置驱动信息的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDriverInfo(String value){
      _driverInfo = value;
   }

   //============================================================
   // <T>判断flash操作系统的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isFlashOsChanged(){
      return !RString.equals(__flashOs, _flashOs);
   }

   //============================================================
   // <T>获得flash操作系统的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String flashOs(){
      return _flashOs;
   }

   //============================================================
   // <T>设置flash操作系统的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setFlashOs(String value){
      _flashOs = value;
   }

   //============================================================
   // <T>判断flash版本的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isFlashVersionChanged(){
      return !RString.equals(__flashVersion, _flashVersion);
   }

   //============================================================
   // <T>获得flash版本的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String flashVersion(){
      return _flashVersion;
   }

   //============================================================
   // <T>设置flash版本的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setFlashVersion(String value){
      _flashVersion = value;
   }

   //============================================================
   // <T>判断flash鉴定信息的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isFlashIdentityChanged(){
      return !RString.equals(__flashIdentity, _flashIdentity);
   }

   //============================================================
   // <T>获得flash鉴定信息的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String flashIdentity(){
      return _flashIdentity;
   }

   //============================================================
   // <T>设置flash鉴定信息的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setFlashIdentity(String value){
      _flashIdentity = value;
   }

   //============================================================
   // <T>判断登陆主机的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLoginHostChanged(){
      return !RString.equals(__loginHost, _loginHost);
   }

   //============================================================
   // <T>获得登陆主机的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String loginHost(){
      return _loginHost;
   }

   //============================================================
   // <T>设置登陆主机的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLoginHost(String value){
      _loginHost = value;
   }

   //============================================================
   // <T>判断登陆时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLoginDateChanged(){
      return !__loginDate.equals(_loginDate);
   }

   //============================================================
   // <T>获得登陆时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime loginDate(){
      return _loginDate;
   }

   //============================================================
   // <T>设置登陆时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLoginDate(TDateTime value){
      _loginDate = value;
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
      __accountCode = row.get("account_code");
      _accountCode = __accountCode;
      __driverInfo = row.get("driver_info");
      _driverInfo = __driverInfo;
      __flashOs = row.get("flash_os");
      _flashOs = __flashOs;
      __flashVersion = row.get("flash_version");
      _flashVersion = __flashVersion;
      __flashIdentity = row.get("flash_identity");
      _flashIdentity = __flashIdentity;
      __loginHost = row.get("login_host");
      _loginHost = __loginHost;
      __loginDate.parse(row.get("login_date"));
      _loginDate.assign(__loginDate);
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