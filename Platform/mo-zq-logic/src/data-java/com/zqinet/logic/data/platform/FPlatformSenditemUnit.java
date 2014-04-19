package com.zqinet.logic.data.platform;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>发货表逻辑单元。</T>
//============================================================
public class FPlatformSenditemUnit extends FLogicUnit
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

   // 存储字段账户的定义。
   private String __passport;

   // 字段账户的定义。
   protected String _passport;

   // 存储字段交易对方账户的定义。
   private String __srcPassport;

   // 字段交易对方账户的定义。
   protected String _srcPassport;

   // 存储字段模板ID的定义。
   private int __itemTid;

   // 字段模板ID的定义。
   protected int _itemTid;

   // 存储字段物品ID的定义。
   private long __itemId;

   // 字段物品ID的定义。
   protected long _itemId;

   // 存储字段价格的定义。
   private float __price;

   // 字段价格的定义。
   protected float _price;

   // 存储字段数量的定义。
   private int __num;

   // 字段数量的定义。
   protected int _num;

   // 存储字段交易token的定义。
   private String __token;

   // 字段交易token的定义。
   protected String _token;

   // 存储字段账单号的定义。
   private String __billNo;

   // 字段账单号的定义。
   protected String _billNo;

   // 存储字段分区ID的定义。
   private int __zoneId;

   // 字段分区ID的定义。
   protected int _zoneId;

   // 存储字段扣除RMB （Q点）的定义。
   private float __costRmb;

   // 字段扣除RMB （Q点）的定义。
   protected float _costRmb;

   // 存储字段消耗元宝的定义。
   private int __costMoney;

   // 字段消耗元宝的定义。
   protected int _costMoney;

   // 存储字段抵扣券消耗的定义。
   private int __costPubacct;

   // 字段抵扣券消耗的定义。
   protected int _costPubacct;

   // 存储字段发货类型 0商城 3交易行的定义。
   private int __sendType;

   // 字段发货类型 0商城 3交易行的定义。
   protected int _sendType;

   // 存储字段交易税的定义。
   private int __fee;

   // 字段交易税的定义。
   protected int _fee;

   // 存储字段处理状态的定义。
   private int __opStatus;

   // 字段处理状态的定义。
   protected int _opStatus;

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
   // <T>构造发货表逻辑单元。</T>
   //============================================================
   public FPlatformSenditemUnit(){
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
   // <T>判断账户的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPassportChanged(){
      return !RString.equals(__passport, _passport);
   }

   //============================================================
   // <T>获得账户的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String passport(){
      return _passport;
   }

   //============================================================
   // <T>设置账户的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPassport(String value){
      _passport = value;
   }

   //============================================================
   // <T>判断交易对方账户的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSrcPassportChanged(){
      return !RString.equals(__srcPassport, _srcPassport);
   }

   //============================================================
   // <T>获得交易对方账户的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String srcPassport(){
      return _srcPassport;
   }

   //============================================================
   // <T>设置交易对方账户的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSrcPassport(String value){
      _srcPassport = value;
   }

   //============================================================
   // <T>判断模板ID的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isItemTidChanged(){
      return __itemTid != _itemTid;
   }

   //============================================================
   // <T>获得模板ID的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int itemTid(){
      return _itemTid;
   }

   //============================================================
   // <T>设置模板ID的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setItemTid(int value){
      _itemTid = value;
   }

   //============================================================
   // <T>判断物品ID的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isItemIdChanged(){
      return __itemId != _itemId;
   }

   //============================================================
   // <T>获得物品ID的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long itemId(){
      return _itemId;
   }

   //============================================================
   // <T>设置物品ID的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setItemId(long value){
      _itemId = value;
   }

   //============================================================
   // <T>判断价格的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPriceChanged(){
      return __price != _price;
   }

   //============================================================
   // <T>获得价格的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public float price(){
      return _price;
   }

   //============================================================
   // <T>设置价格的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPrice(float value){
      _price = value;
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
   // <T>判断交易token的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTokenChanged(){
      return !RString.equals(__token, _token);
   }

   //============================================================
   // <T>获得交易token的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String token(){
      return _token;
   }

   //============================================================
   // <T>设置交易token的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setToken(String value){
      _token = value;
   }

   //============================================================
   // <T>判断账单号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isBillNoChanged(){
      return !RString.equals(__billNo, _billNo);
   }

   //============================================================
   // <T>获得账单号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String billNo(){
      return _billNo;
   }

   //============================================================
   // <T>设置账单号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setBillNo(String value){
      _billNo = value;
   }

   //============================================================
   // <T>判断分区ID的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isZoneIdChanged(){
      return __zoneId != _zoneId;
   }

   //============================================================
   // <T>获得分区ID的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int zoneId(){
      return _zoneId;
   }

   //============================================================
   // <T>设置分区ID的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setZoneId(int value){
      _zoneId = value;
   }

   //============================================================
   // <T>判断扣除RMB （Q点）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCostRmbChanged(){
      return __costRmb != _costRmb;
   }

   //============================================================
   // <T>获得扣除RMB （Q点）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public float costRmb(){
      return _costRmb;
   }

   //============================================================
   // <T>设置扣除RMB （Q点）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCostRmb(float value){
      _costRmb = value;
   }

   //============================================================
   // <T>判断消耗元宝的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCostMoneyChanged(){
      return __costMoney != _costMoney;
   }

   //============================================================
   // <T>获得消耗元宝的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int costMoney(){
      return _costMoney;
   }

   //============================================================
   // <T>设置消耗元宝的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCostMoney(int value){
      _costMoney = value;
   }

   //============================================================
   // <T>判断抵扣券消耗的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCostPubacctChanged(){
      return __costPubacct != _costPubacct;
   }

   //============================================================
   // <T>获得抵扣券消耗的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int costPubacct(){
      return _costPubacct;
   }

   //============================================================
   // <T>设置抵扣券消耗的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCostPubacct(int value){
      _costPubacct = value;
   }

   //============================================================
   // <T>判断发货类型 0商城 3交易行的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSendTypeChanged(){
      return __sendType != _sendType;
   }

   //============================================================
   // <T>获得发货类型 0商城 3交易行的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int sendType(){
      return _sendType;
   }

   //============================================================
   // <T>设置发货类型 0商城 3交易行的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSendType(int value){
      _sendType = value;
   }

   //============================================================
   // <T>判断交易税的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isFeeChanged(){
      return __fee != _fee;
   }

   //============================================================
   // <T>获得交易税的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int fee(){
      return _fee;
   }

   //============================================================
   // <T>设置交易税的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setFee(int value){
      _fee = value;
   }

   //============================================================
   // <T>判断处理状态的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isOpStatusChanged(){
      return __opStatus != _opStatus;
   }

   //============================================================
   // <T>获得处理状态的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int opStatus(){
      return _opStatus;
   }

   //============================================================
   // <T>设置处理状态的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setOpStatus(int value){
      _opStatus = value;
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
      __srcPassport = row.get("src_passport");
      _srcPassport = __srcPassport;
      __itemTid = row.getInteger("item_tid");
      _itemTid = __itemTid;
      __itemId = RLong.parse(row.get("item_id"));
      _itemId = __itemId;
      __price = row.getFloat("price");
      _price = __price;
      __num = row.getInteger("num");
      _num = __num;
      __token = row.get("token");
      _token = __token;
      __billNo = row.get("bill_no");
      _billNo = __billNo;
      __zoneId = row.getInteger("zone_id");
      _zoneId = __zoneId;
      __costRmb = row.getFloat("cost_rmb");
      _costRmb = __costRmb;
      __costMoney = row.getInteger("cost_money");
      _costMoney = __costMoney;
      __costPubacct = row.getInteger("cost_pubacct");
      _costPubacct = __costPubacct;
      __sendType = row.getInteger("send_type");
      _sendType = __sendType;
      __fee = row.getInteger("fee");
      _fee = __fee;
      __opStatus = row.getInteger("op_status");
      _opStatus = __opStatus;
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