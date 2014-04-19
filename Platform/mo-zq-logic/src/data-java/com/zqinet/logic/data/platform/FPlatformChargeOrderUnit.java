package com.zqinet.logic.data.platform;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>充值订单逻辑单元。</T>
//============================================================
public class FPlatformChargeOrderUnit extends FLogicUnit
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

   // 存储字段用户ID 字母或数字的定义。
   private String __userId;

   // 字段用户ID 字母或数字的定义。
   protected String _userId;

   // 存储字段订单号的定义。
   private String __orderId;

   // 字段订单号的定义。
   protected String _orderId;

   // 存储字段RMB的定义。
   private float __money;

   // 字段RMB的定义。
   protected float _money;

   // 存储字段游戏元宝的定义。
   private int __gameMoney;

   // 字段游戏元宝的定义。
   protected int _gameMoney;

   // 存储字段对方时间戳的定义。
   private int __timeStamp;

   // 字段对方时间戳的定义。
   protected int _timeStamp;

   // 存储字段状态的定义。
   private int __status;

   // 字段状态的定义。
   protected int _status;

   // 存储字段对方IP的定义。
   private String __hostIp;

   // 字段对方IP的定义。
   protected String _hostIp;

   // 存储字段余额（腾讯平台用）的定义。
   private int __balance;

   // 字段余额（腾讯平台用）的定义。
   protected int _balance;

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
   // <T>构造充值订单逻辑单元。</T>
   //============================================================
   public FPlatformChargeOrderUnit(){
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
   // <T>判断用户ID 字母或数字的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isUserIdChanged(){
      return !RString.equals(__userId, _userId);
   }

   //============================================================
   // <T>获得用户ID 字母或数字的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String userId(){
      return _userId;
   }

   //============================================================
   // <T>设置用户ID 字母或数字的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setUserId(String value){
      _userId = value;
   }

   //============================================================
   // <T>判断订单号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isOrderIdChanged(){
      return !RString.equals(__orderId, _orderId);
   }

   //============================================================
   // <T>获得订单号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String orderId(){
      return _orderId;
   }

   //============================================================
   // <T>设置订单号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setOrderId(String value){
      _orderId = value;
   }

   //============================================================
   // <T>判断RMB的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMoneyChanged(){
      return __money != _money;
   }

   //============================================================
   // <T>获得RMB的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public float money(){
      return _money;
   }

   //============================================================
   // <T>设置RMB的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMoney(float value){
      _money = value;
   }

   //============================================================
   // <T>判断游戏元宝的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isGameMoneyChanged(){
      return __gameMoney != _gameMoney;
   }

   //============================================================
   // <T>获得游戏元宝的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int gameMoney(){
      return _gameMoney;
   }

   //============================================================
   // <T>设置游戏元宝的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setGameMoney(int value){
      _gameMoney = value;
   }

   //============================================================
   // <T>判断对方时间戳的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTimeStampChanged(){
      return __timeStamp != _timeStamp;
   }

   //============================================================
   // <T>获得对方时间戳的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int timeStamp(){
      return _timeStamp;
   }

   //============================================================
   // <T>设置对方时间戳的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTimeStamp(int value){
      _timeStamp = value;
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
   // <T>判断对方IP的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isHostIpChanged(){
      return !RString.equals(__hostIp, _hostIp);
   }

   //============================================================
   // <T>获得对方IP的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String hostIp(){
      return _hostIp;
   }

   //============================================================
   // <T>设置对方IP的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setHostIp(String value){
      _hostIp = value;
   }

   //============================================================
   // <T>判断余额（腾讯平台用）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isBalanceChanged(){
      return __balance != _balance;
   }

   //============================================================
   // <T>获得余额（腾讯平台用）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int balance(){
      return _balance;
   }

   //============================================================
   // <T>设置余额（腾讯平台用）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setBalance(int value){
      _balance = value;
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
      __userId = row.get("user_id");
      _userId = __userId;
      __orderId = row.get("order_id");
      _orderId = __orderId;
      __money = row.getFloat("money");
      _money = __money;
      __gameMoney = row.getInteger("game_money");
      _gameMoney = __gameMoney;
      __timeStamp = row.getInteger("time_stamp");
      _timeStamp = __timeStamp;
      __status = row.getInteger("status");
      _status = __status;
      __hostIp = row.get("host_ip");
      _hostIp = __hostIp;
      __balance = row.getInteger("balance");
      _balance = __balance;
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