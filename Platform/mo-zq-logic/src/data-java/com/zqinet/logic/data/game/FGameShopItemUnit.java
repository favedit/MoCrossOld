package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>商城商品信息逻辑单元。</T>
//============================================================
public class FGameShopItemUnit extends FLogicUnit
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

   // 存储字段商城商品模版编号的定义。
   private int __shopItemTid;

   // 字段商城商品模版编号的定义。
   protected int _shopItemTid;

   // 存储字段是否有效的定义。
   private int __isValid;

   // 字段是否有效的定义。
   protected int _isValid;

   // 存储字段基本货币原价的定义。
   private String __currencyOrigin;

   // 字段基本货币原价的定义。
   protected String _currencyOrigin;

   // 存储字段基本货币现价的定义。
   private String __currency;

   // 字段基本货币现价的定义。
   protected String _currency;

   // 存储字段其他货币类型的定义。
   private int __currencyCd;

   // 字段其他货币类型的定义。
   protected int _currencyCd;

   // 存储字段其他货币原价的定义。
   private int __currencyOriginValue;

   // 字段其他货币原价的定义。
   protected int _currencyOriginValue;

   // 存储字段其他货币原价的定义。
   private int __currencyValue;

   // 字段其他货币原价的定义。
   protected int _currencyValue;

   // 存储字段销售方式的定义。
   private int __saleCd;

   // 字段销售方式的定义。
   protected int _saleCd;

   // 存储字段购买数量限制的定义。
   private int __limitCount;

   // 字段购买数量限制的定义。
   protected int _limitCount;

   // 存储字段刷新数量的定义。
   private int __refreshCount;

   // 字段刷新数量的定义。
   protected int _refreshCount;

   // 存储字段刷新间隔的定义。
   private int __refreshIntervalTs;

   // 字段刷新间隔的定义。
   protected int _refreshIntervalTs;

   // 存储字段有效方式的定义。
   private int __validCd;

   // 字段有效方式的定义。
   protected int _validCd;

   // 存储字段有效开始时间的定义。
   private TDateTime __validBeginDate = new TDateTime();

   // 字段有效开始时间的定义。
   protected TDateTime _validBeginDate = new TDateTime();

   // 存储字段有效结束时间的定义。
   private TDateTime __validEndDate = new TDateTime();

   // 字段有效结束时间的定义。
   protected TDateTime _validEndDate = new TDateTime();

   // 存储字段显示顺序的定义。
   private int __displayOrder;

   // 字段显示顺序的定义。
   protected int _displayOrder;

   // 存储字段限购物品当前数量的定义。
   private int __currentCount;

   // 字段限购物品当前数量的定义。
   protected int _currentCount;

   // 存储字段商城类型的定义。
   private int __shopCd;

   // 字段商城类型的定义。
   protected int _shopCd;

   // 存储字段抢购物品状态的定义。
   private int __shopItemStatus;

   // 字段抢购物品状态的定义。
   protected int _shopItemStatus;

   // 存储字段销售状态的定义。
   private int __saleStatus;

   // 字段销售状态的定义。
   protected int _saleStatus;

   // 存储字段抢购开始时间的定义。
   private TDateTime __panicBeginDate = new TDateTime();

   // 字段抢购开始时间的定义。
   protected TDateTime _panicBeginDate = new TDateTime();

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
   // <T>构造商城商品信息逻辑单元。</T>
   //============================================================
   public FGameShopItemUnit(){
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
   // <T>判断商城商品模版编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isShopItemTidChanged(){
      return __shopItemTid != _shopItemTid;
   }

   //============================================================
   // <T>获得商城商品模版编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int shopItemTid(){
      return _shopItemTid;
   }

   //============================================================
   // <T>设置商城商品模版编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setShopItemTid(int value){
      _shopItemTid = value;
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
   // <T>判断基本货币原价的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCurrencyOriginChanged(){
      return !RString.equals(__currencyOrigin, _currencyOrigin);
   }

   //============================================================
   // <T>获得基本货币原价的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String currencyOrigin(){
      return _currencyOrigin;
   }

   //============================================================
   // <T>设置基本货币原价的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCurrencyOrigin(String value){
      _currencyOrigin = value;
   }

   //============================================================
   // <T>判断基本货币现价的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCurrencyChanged(){
      return !RString.equals(__currency, _currency);
   }

   //============================================================
   // <T>获得基本货币现价的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String currency(){
      return _currency;
   }

   //============================================================
   // <T>设置基本货币现价的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCurrency(String value){
      _currency = value;
   }

   //============================================================
   // <T>判断其他货币类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCurrencyCdChanged(){
      return __currencyCd != _currencyCd;
   }

   //============================================================
   // <T>获得其他货币类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int currencyCd(){
      return _currencyCd;
   }

   //============================================================
   // <T>设置其他货币类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCurrencyCd(int value){
      _currencyCd = value;
   }

   //============================================================
   // <T>判断其他货币原价的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCurrencyOriginValueChanged(){
      return __currencyOriginValue != _currencyOriginValue;
   }

   //============================================================
   // <T>获得其他货币原价的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int currencyOriginValue(){
      return _currencyOriginValue;
   }

   //============================================================
   // <T>设置其他货币原价的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCurrencyOriginValue(int value){
      _currencyOriginValue = value;
   }

   //============================================================
   // <T>判断其他货币原价的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCurrencyValueChanged(){
      return __currencyValue != _currencyValue;
   }

   //============================================================
   // <T>获得其他货币原价的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int currencyValue(){
      return _currencyValue;
   }

   //============================================================
   // <T>设置其他货币原价的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCurrencyValue(int value){
      _currencyValue = value;
   }

   //============================================================
   // <T>判断销售方式的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSaleCdChanged(){
      return __saleCd != _saleCd;
   }

   //============================================================
   // <T>获得销售方式的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int saleCd(){
      return _saleCd;
   }

   //============================================================
   // <T>设置销售方式的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSaleCd(int value){
      _saleCd = value;
   }

   //============================================================
   // <T>判断购买数量限制的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLimitCountChanged(){
      return __limitCount != _limitCount;
   }

   //============================================================
   // <T>获得购买数量限制的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int limitCount(){
      return _limitCount;
   }

   //============================================================
   // <T>设置购买数量限制的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLimitCount(int value){
      _limitCount = value;
   }

   //============================================================
   // <T>判断刷新数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRefreshCountChanged(){
      return __refreshCount != _refreshCount;
   }

   //============================================================
   // <T>获得刷新数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int refreshCount(){
      return _refreshCount;
   }

   //============================================================
   // <T>设置刷新数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRefreshCount(int value){
      _refreshCount = value;
   }

   //============================================================
   // <T>判断刷新间隔的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRefreshIntervalTsChanged(){
      return __refreshIntervalTs != _refreshIntervalTs;
   }

   //============================================================
   // <T>获得刷新间隔的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int refreshIntervalTs(){
      return _refreshIntervalTs;
   }

   //============================================================
   // <T>设置刷新间隔的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRefreshIntervalTs(int value){
      _refreshIntervalTs = value;
   }

   //============================================================
   // <T>判断有效方式的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isValidCdChanged(){
      return __validCd != _validCd;
   }

   //============================================================
   // <T>获得有效方式的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int validCd(){
      return _validCd;
   }

   //============================================================
   // <T>设置有效方式的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setValidCd(int value){
      _validCd = value;
   }

   //============================================================
   // <T>判断有效开始时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isValidBeginDateChanged(){
      return !__validBeginDate.equals(_validBeginDate);
   }

   //============================================================
   // <T>获得有效开始时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime validBeginDate(){
      return _validBeginDate;
   }

   //============================================================
   // <T>设置有效开始时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setValidBeginDate(TDateTime value){
      _validBeginDate = value;
   }

   //============================================================
   // <T>判断有效结束时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isValidEndDateChanged(){
      return !__validEndDate.equals(_validEndDate);
   }

   //============================================================
   // <T>获得有效结束时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime validEndDate(){
      return _validEndDate;
   }

   //============================================================
   // <T>设置有效结束时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setValidEndDate(TDateTime value){
      _validEndDate = value;
   }

   //============================================================
   // <T>判断显示顺序的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isDisplayOrderChanged(){
      return __displayOrder != _displayOrder;
   }

   //============================================================
   // <T>获得显示顺序的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int displayOrder(){
      return _displayOrder;
   }

   //============================================================
   // <T>设置显示顺序的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDisplayOrder(int value){
      _displayOrder = value;
   }

   //============================================================
   // <T>判断限购物品当前数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCurrentCountChanged(){
      return __currentCount != _currentCount;
   }

   //============================================================
   // <T>获得限购物品当前数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int currentCount(){
      return _currentCount;
   }

   //============================================================
   // <T>设置限购物品当前数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCurrentCount(int value){
      _currentCount = value;
   }

   //============================================================
   // <T>判断商城类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isShopCdChanged(){
      return __shopCd != _shopCd;
   }

   //============================================================
   // <T>获得商城类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int shopCd(){
      return _shopCd;
   }

   //============================================================
   // <T>设置商城类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setShopCd(int value){
      _shopCd = value;
   }

   //============================================================
   // <T>判断抢购物品状态的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isShopItemStatusChanged(){
      return __shopItemStatus != _shopItemStatus;
   }

   //============================================================
   // <T>获得抢购物品状态的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int shopItemStatus(){
      return _shopItemStatus;
   }

   //============================================================
   // <T>设置抢购物品状态的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setShopItemStatus(int value){
      _shopItemStatus = value;
   }

   //============================================================
   // <T>判断销售状态的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSaleStatusChanged(){
      return __saleStatus != _saleStatus;
   }

   //============================================================
   // <T>获得销售状态的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int saleStatus(){
      return _saleStatus;
   }

   //============================================================
   // <T>设置销售状态的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSaleStatus(int value){
      _saleStatus = value;
   }

   //============================================================
   // <T>判断抢购开始时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPanicBeginDateChanged(){
      return !__panicBeginDate.equals(_panicBeginDate);
   }

   //============================================================
   // <T>获得抢购开始时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime panicBeginDate(){
      return _panicBeginDate;
   }

   //============================================================
   // <T>设置抢购开始时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPanicBeginDate(TDateTime value){
      _panicBeginDate = value;
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
      __shopItemTid = row.getInteger("shop_item_tid");
      _shopItemTid = __shopItemTid;
      __isValid = row.getInteger("is_valid");
      _isValid = __isValid;
      __currencyOrigin = row.get("currency_origin");
      _currencyOrigin = __currencyOrigin;
      __currency = row.get("currency");
      _currency = __currency;
      __currencyCd = row.getInteger("currency_cd");
      _currencyCd = __currencyCd;
      __currencyOriginValue = row.getInteger("currency_origin_value");
      _currencyOriginValue = __currencyOriginValue;
      __currencyValue = row.getInteger("currency_value");
      _currencyValue = __currencyValue;
      __saleCd = row.getInteger("sale_cd");
      _saleCd = __saleCd;
      __limitCount = row.getInteger("limit_count");
      _limitCount = __limitCount;
      __refreshCount = row.getInteger("refresh_count");
      _refreshCount = __refreshCount;
      __refreshIntervalTs = row.getInteger("refresh_interval_ts");
      _refreshIntervalTs = __refreshIntervalTs;
      __validCd = row.getInteger("valid_cd");
      _validCd = __validCd;
      __validBeginDate.parse(row.get("valid_begin_date"));
      _validBeginDate.assign(__validBeginDate);
      __validEndDate.parse(row.get("valid_end_date"));
      _validEndDate.assign(__validEndDate);
      __displayOrder = row.getInteger("display_order");
      _displayOrder = __displayOrder;
      __currentCount = row.getInteger("current_count");
      _currentCount = __currentCount;
      __shopCd = row.getInteger("shop_cd");
      _shopCd = __shopCd;
      __shopItemStatus = row.getInteger("shop_item_status");
      _shopItemStatus = __shopItemStatus;
      __saleStatus = row.getInteger("sale_status");
      _saleStatus = __saleStatus;
      __panicBeginDate.parse(row.get("panic_begin_date"));
      _panicBeginDate.assign(__panicBeginDate);
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