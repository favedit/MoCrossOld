package com.zqinet.logic.data.platform;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>存储预付费表逻辑单元。</T>
//============================================================
public class FPlatformPreBuyUnit extends FLogicUnit
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

   // 存储字段购买类型的定义。
   private int __buyType;

   // 字段购买类型的定义。
   protected int _buyType;

   // 存储字段编号的定义。
   private long __preId;

   // 字段编号的定义。
   protected long _preId;

   // 存储字段数量的定义。
   private int __num;

   // 字段数量的定义。
   protected int _num;

   // 存储字段图肯的定义。
   private long __token;

   // 字段图肯的定义。
   protected long _token;

   // 存储字段腾讯图肯的定义。
   private String __txToken;

   // 字段腾讯图肯的定义。
   protected String _txToken;

   // 存储字段花费的定义。
   private int __costMoney;

   // 字段花费的定义。
   protected int _costMoney;

   // 存储字段真实扣费的定义。
   private int __realCostMoney;

   // 字段真实扣费的定义。
   protected int _realCostMoney;

   // 存储字段状态的定义。
   private int __status;

   // 字段状态的定义。
   protected int _status;

   // 存储字段openid的定义。
   private String __openId;

   // 字段openid的定义。
   protected String _openId;

   // 存储字段扣税的定义。
   private float __fee;

   // 字段扣税的定义。
   protected float _fee;

   // 存储字段跟踪ID的定义。
   private int __exId;

   // 字段跟踪ID的定义。
   protected int _exId;

   // 存储字段跟踪时间的定义。
   private int __exTime;

   // 字段跟踪时间的定义。
   protected int _exTime;

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
   // <T>构造存储预付费表逻辑单元。</T>
   //============================================================
   public FPlatformPreBuyUnit(){
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
   // <T>判断购买类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isBuyTypeChanged(){
      return __buyType != _buyType;
   }

   //============================================================
   // <T>获得购买类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int buyType(){
      return _buyType;
   }

   //============================================================
   // <T>设置购买类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setBuyType(int value){
      _buyType = value;
   }

   //============================================================
   // <T>判断编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPreIdChanged(){
      return __preId != _preId;
   }

   //============================================================
   // <T>获得编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long preId(){
      return _preId;
   }

   //============================================================
   // <T>设置编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPreId(long value){
      _preId = value;
   }

   //============================================================
   // <T>判断数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isNumChanged(){
      return __num != _num;
   }

   //============================================================
   // <T>获得数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int num(){
      return _num;
   }

   //============================================================
   // <T>设置数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setNum(int value){
      _num = value;
   }

   //============================================================
   // <T>判断图肯的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTokenChanged(){
      return __token != _token;
   }

   //============================================================
   // <T>获得图肯的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long token(){
      return _token;
   }

   //============================================================
   // <T>设置图肯的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setToken(long value){
      _token = value;
   }

   //============================================================
   // <T>判断腾讯图肯的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTxTokenChanged(){
      return !RString.equals(__txToken, _txToken);
   }

   //============================================================
   // <T>获得腾讯图肯的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String txToken(){
      return _txToken;
   }

   //============================================================
   // <T>设置腾讯图肯的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTxToken(String value){
      _txToken = value;
   }

   //============================================================
   // <T>判断花费的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCostMoneyChanged(){
      return __costMoney != _costMoney;
   }

   //============================================================
   // <T>获得花费的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int costMoney(){
      return _costMoney;
   }

   //============================================================
   // <T>设置花费的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCostMoney(int value){
      _costMoney = value;
   }

   //============================================================
   // <T>判断真实扣费的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRealCostMoneyChanged(){
      return __realCostMoney != _realCostMoney;
   }

   //============================================================
   // <T>获得真实扣费的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int realCostMoney(){
      return _realCostMoney;
   }

   //============================================================
   // <T>设置真实扣费的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRealCostMoney(int value){
      _realCostMoney = value;
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
   // <T>判断openid的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isOpenIdChanged(){
      return !RString.equals(__openId, _openId);
   }

   //============================================================
   // <T>获得openid的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String openId(){
      return _openId;
   }

   //============================================================
   // <T>设置openid的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setOpenId(String value){
      _openId = value;
   }

   //============================================================
   // <T>判断扣税的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isFeeChanged(){
      return __fee != _fee;
   }

   //============================================================
   // <T>获得扣税的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public float fee(){
      return _fee;
   }

   //============================================================
   // <T>设置扣税的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setFee(float value){
      _fee = value;
   }

   //============================================================
   // <T>判断跟踪ID的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isExIdChanged(){
      return __exId != _exId;
   }

   //============================================================
   // <T>获得跟踪ID的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int exId(){
      return _exId;
   }

   //============================================================
   // <T>设置跟踪ID的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setExId(int value){
      _exId = value;
   }

   //============================================================
   // <T>判断跟踪时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isExTimeChanged(){
      return __exTime != _exTime;
   }

   //============================================================
   // <T>获得跟踪时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int exTime(){
      return _exTime;
   }

   //============================================================
   // <T>设置跟踪时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setExTime(int value){
      _exTime = value;
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
      __buyType = row.getInteger("buy_type");
      _buyType = __buyType;
      __preId = RLong.parse(row.get("pre_id"));
      _preId = __preId;
      __num = row.getInteger("num");
      _num = __num;
      __token = RLong.parse(row.get("token"));
      _token = __token;
      __txToken = row.get("tx_token");
      _txToken = __txToken;
      __costMoney = row.getInteger("cost_money");
      _costMoney = __costMoney;
      __realCostMoney = row.getInteger("real_cost_money");
      _realCostMoney = __realCostMoney;
      __status = row.getInteger("status");
      _status = __status;
      __openId = row.get("open_id");
      _openId = __openId;
      __fee = row.getFloat("fee");
      _fee = __fee;
      __exId = row.getInteger("ex_id");
      _exId = __exId;
      __exTime = row.getInteger("ex_time");
      _exTime = __exTime;
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