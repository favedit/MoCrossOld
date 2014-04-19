package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>帐号信息逻辑单元。</T>
//============================================================
public class FGameAccountUnit extends FLogicUnit
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

   // 存储字段帐号的定义。
   private String __passport;

   // 字段帐号的定义。
   protected String _passport;

   // 存储字段密码的定义。
   private String __password;

   // 字段密码的定义。
   protected String _password;

   // 存储字段状态的定义。
   private int __status;

   // 字段状态的定义。
   protected int _status;

   // 存储字段标签的定义。
   private String __label;

   // 字段标签的定义。
   protected String _label;

   // 存储字段成年标志的定义。
   private boolean __isAdult;

   // 字段成年标志的定义。
   protected boolean _isAdult;

   // 存储字段点数的定义。
   private int __point;

   // 字段点数的定义。
   protected int _point;

   // 存储字段登录时间的定义。
   private TDateTime __loginDate = new TDateTime();

   // 字段登录时间的定义。
   protected TDateTime _loginDate = new TDateTime();

   // 存储字段非绑定元宝的定义。
   private int __moneyUnbind;

   // 字段非绑定元宝的定义。
   protected int _moneyUnbind;

   // 存储字段消耗元宝数量的定义。
   private int __spendMoney;

   // 字段消耗元宝数量的定义。
   protected int _spendMoney;

   // 存储字段活动重置的定义。
   private int __activityCharge;

   // 字段活动重置的定义。
   protected int _activityCharge;

   // 存储字段渠道号的定义。
   private String __channel;

   // 字段渠道号的定义。
   protected String _channel;

   // 存储字段海报号的定义。
   private String __post;

   // 字段海报号的定义。
   protected String _post;

   // 存储字段子站ID的定义。
   private String __subId;

   // 字段子站ID的定义。
   protected String _subId;

   // 存储字段IP的定义。
   private String __ip;

   // 字段IP的定义。
   protected String _ip;

   // 存储字段默认名字的定义。
   private String __defaultName;

   // 字段默认名字的定义。
   protected String _defaultName;

   // 存储字段额外的VIP信息的定义。
   private int __exVipInfo;

   // 字段额外的VIP信息的定义。
   protected int _exVipInfo;

   // 存储字段额外的vip等级的定义。
   private int __exVipLevel;

   // 字段额外的vip等级的定义。
   protected int _exVipLevel;

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
   // <T>构造帐号信息逻辑单元。</T>
   //============================================================
   public FGameAccountUnit(){
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
   // <T>判断帐号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPassportChanged(){
      return !RString.equals(__passport, _passport);
   }

   //============================================================
   // <T>获得帐号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String passport(){
      return _passport;
   }

   //============================================================
   // <T>设置帐号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPassport(String value){
      _passport = value;
   }

   //============================================================
   // <T>判断密码的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPasswordChanged(){
      return !RString.equals(__password, _password);
   }

   //============================================================
   // <T>获得密码的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String password(){
      return _password;
   }

   //============================================================
   // <T>设置密码的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPassword(String value){
      _password = value;
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
   // <T>判断标签的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLabelChanged(){
      return !RString.equals(__label, _label);
   }

   //============================================================
   // <T>获得标签的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String label(){
      return _label;
   }

   //============================================================
   // <T>设置标签的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLabel(String value){
      _label = value;
   }

   //============================================================
   // <T>判断成年标志的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isIsAdultChanged(){
      return __isAdult != _isAdult;
   }

   //============================================================
   // <T>获得成年标志的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAdult(){
      return _isAdult;
   }

   //============================================================
   // <T>设置成年标志的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setIsAdult(boolean value){
      _isAdult = value;
   }

   //============================================================
   // <T>判断点数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPointChanged(){
      return __point != _point;
   }

   //============================================================
   // <T>获得点数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int point(){
      return _point;
   }

   //============================================================
   // <T>设置点数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPoint(int value){
      _point = value;
   }

   //============================================================
   // <T>判断登录时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLoginDateChanged(){
      return !__loginDate.equals(_loginDate);
   }

   //============================================================
   // <T>获得登录时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime loginDate(){
      return _loginDate;
   }

   //============================================================
   // <T>设置登录时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLoginDate(TDateTime value){
      _loginDate = value;
   }

   //============================================================
   // <T>判断非绑定元宝的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMoneyUnbindChanged(){
      return __moneyUnbind != _moneyUnbind;
   }

   //============================================================
   // <T>获得非绑定元宝的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int moneyUnbind(){
      return _moneyUnbind;
   }

   //============================================================
   // <T>设置非绑定元宝的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMoneyUnbind(int value){
      _moneyUnbind = value;
   }

   //============================================================
   // <T>判断消耗元宝数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSpendMoneyChanged(){
      return __spendMoney != _spendMoney;
   }

   //============================================================
   // <T>获得消耗元宝数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int spendMoney(){
      return _spendMoney;
   }

   //============================================================
   // <T>设置消耗元宝数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSpendMoney(int value){
      _spendMoney = value;
   }

   //============================================================
   // <T>判断活动重置的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isActivityChargeChanged(){
      return __activityCharge != _activityCharge;
   }

   //============================================================
   // <T>获得活动重置的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int activityCharge(){
      return _activityCharge;
   }

   //============================================================
   // <T>设置活动重置的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setActivityCharge(int value){
      _activityCharge = value;
   }

   //============================================================
   // <T>判断渠道号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isChannelChanged(){
      return !RString.equals(__channel, _channel);
   }

   //============================================================
   // <T>获得渠道号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String channel(){
      return _channel;
   }

   //============================================================
   // <T>设置渠道号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setChannel(String value){
      _channel = value;
   }

   //============================================================
   // <T>判断海报号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPostChanged(){
      return !RString.equals(__post, _post);
   }

   //============================================================
   // <T>获得海报号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String post(){
      return _post;
   }

   //============================================================
   // <T>设置海报号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPost(String value){
      _post = value;
   }

   //============================================================
   // <T>判断子站ID的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSubIdChanged(){
      return !RString.equals(__subId, _subId);
   }

   //============================================================
   // <T>获得子站ID的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String subId(){
      return _subId;
   }

   //============================================================
   // <T>设置子站ID的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSubId(String value){
      _subId = value;
   }

   //============================================================
   // <T>判断IP的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isIpChanged(){
      return !RString.equals(__ip, _ip);
   }

   //============================================================
   // <T>获得IP的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String ip(){
      return _ip;
   }

   //============================================================
   // <T>设置IP的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setIp(String value){
      _ip = value;
   }

   //============================================================
   // <T>判断默认名字的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isDefaultNameChanged(){
      return !RString.equals(__defaultName, _defaultName);
   }

   //============================================================
   // <T>获得默认名字的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String defaultName(){
      return _defaultName;
   }

   //============================================================
   // <T>设置默认名字的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDefaultName(String value){
      _defaultName = value;
   }

   //============================================================
   // <T>判断额外的VIP信息的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isExVipInfoChanged(){
      return __exVipInfo != _exVipInfo;
   }

   //============================================================
   // <T>获得额外的VIP信息的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int exVipInfo(){
      return _exVipInfo;
   }

   //============================================================
   // <T>设置额外的VIP信息的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setExVipInfo(int value){
      _exVipInfo = value;
   }

   //============================================================
   // <T>判断额外的vip等级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isExVipLevelChanged(){
      return __exVipLevel != _exVipLevel;
   }

   //============================================================
   // <T>获得额外的vip等级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int exVipLevel(){
      return _exVipLevel;
   }

   //============================================================
   // <T>设置额外的vip等级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setExVipLevel(int value){
      _exVipLevel = value;
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
      __passport = row.get("passport");
      _passport = __passport;
      __password = row.get("password");
      _password = __password;
      __status = row.getInteger("status");
      _status = __status;
      __label = row.get("label");
      _label = __label;
      __point = row.getInteger("point");
      _point = __point;
      __loginDate.parse(row.get("login_date"));
      _loginDate.assign(__loginDate);
      __moneyUnbind = row.getInteger("money_unbind");
      _moneyUnbind = __moneyUnbind;
      __spendMoney = row.getInteger("spend_money");
      _spendMoney = __spendMoney;
      __activityCharge = row.getInteger("activity_charge");
      _activityCharge = __activityCharge;
      __channel = row.get("channel");
      _channel = __channel;
      __post = row.get("post");
      _post = __post;
      __subId = row.get("sub_id");
      _subId = __subId;
      __ip = row.get("ip");
      _ip = __ip;
      __defaultName = row.get("default_name");
      _defaultName = __defaultName;
      __exVipInfo = row.getInteger("ex_vip_info");
      _exVipInfo = __exVipInfo;
      __exVipLevel = row.getInteger("ex_vip_level");
      _exVipLevel = __exVipLevel;
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