package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>交易行物品信息逻辑单元。</T>
//============================================================
public class FGameAuctionItemUnit extends FLogicUnit
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

   // 存储字段归属角色编号的定义。
   private long __belongRoleId;

   // 字段归属角色编号的定义。
   protected long _belongRoleId;

   // 存储字段拍卖状态的定义。
   private int __auctionStatus;

   // 字段拍卖状态的定义。
   protected int _auctionStatus;

   // 存储字段物品类型的定义。
   private int __typeCd;

   // 字段物品类型的定义。
   protected int _typeCd;

   // 存储字段物品编号的定义。
   private long __itemId;

   // 字段物品编号的定义。
   protected long _itemId;

   // 存储字段出售金钱的定义。
   private String __sellCurrency;

   // 字段出售金钱的定义。
   protected String _sellCurrency;

   // 存储字段出售价格的定义。
   private String __currency;

   // 字段出售价格的定义。
   protected String _currency;

   // 存储字段出售开始时间的定义。
   private TDateTime __sellBeginDate = new TDateTime();

   // 字段出售开始时间的定义。
   protected TDateTime _sellBeginDate = new TDateTime();

   // 存储字段拍卖时间的定义。
   private int __auctionTime;

   // 字段拍卖时间的定义。
   protected int _auctionTime;

   // 存储字段出售类型的定义。
   private int __sellType;

   // 字段出售类型的定义。
   protected int _sellType;

   // 存储字段查询大类的定义。
   private int __sumType;

   // 字段查询大类的定义。
   protected int _sumType;

   // 存储字段查询小类的定义。
   private int __detailType;

   // 字段查询小类的定义。
   protected int _detailType;

   // 存储字段品级的定义。
   private int __quality;

   // 字段品级的定义。
   protected int _quality;

   // 存储字段等级的定义。
   private int __level;

   // 字段等级的定义。
   protected int _level;

   // 存储字段物品模版编号的定义。
   private int __itemTid;

   // 字段物品模版编号的定义。
   protected int _itemTid;

   // 存储字段数量的定义。
   private int __count;

   // 字段数量的定义。
   protected int _count;

   // 存储字段状态的定义。
   private int __status;

   // 字段状态的定义。
   protected int _status;

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
   // <T>构造交易行物品信息逻辑单元。</T>
   //============================================================
   public FGameAuctionItemUnit(){
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
   // <T>判断归属角色编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isBelongRoleIdChanged(){
      return __belongRoleId != _belongRoleId;
   }

   //============================================================
   // <T>获得归属角色编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long belongRoleId(){
      return _belongRoleId;
   }

   //============================================================
   // <T>设置归属角色编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setBelongRoleId(long value){
      _belongRoleId = value;
   }

   //============================================================
   // <T>判断拍卖状态的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAuctionStatusChanged(){
      return __auctionStatus != _auctionStatus;
   }

   //============================================================
   // <T>获得拍卖状态的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int auctionStatus(){
      return _auctionStatus;
   }

   //============================================================
   // <T>设置拍卖状态的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAuctionStatus(int value){
      _auctionStatus = value;
   }

   //============================================================
   // <T>判断物品类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTypeCdChanged(){
      return __typeCd != _typeCd;
   }

   //============================================================
   // <T>获得物品类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int typeCd(){
      return _typeCd;
   }

   //============================================================
   // <T>设置物品类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTypeCd(int value){
      _typeCd = value;
   }

   //============================================================
   // <T>判断物品编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isItemIdChanged(){
      return __itemId != _itemId;
   }

   //============================================================
   // <T>获得物品编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long itemId(){
      return _itemId;
   }

   //============================================================
   // <T>设置物品编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setItemId(long value){
      _itemId = value;
   }

   //============================================================
   // <T>判断出售金钱的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSellCurrencyChanged(){
      return !RString.equals(__sellCurrency, _sellCurrency);
   }

   //============================================================
   // <T>获得出售金钱的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String sellCurrency(){
      return _sellCurrency;
   }

   //============================================================
   // <T>设置出售金钱的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSellCurrency(String value){
      _sellCurrency = value;
   }

   //============================================================
   // <T>判断出售价格的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCurrencyChanged(){
      return !RString.equals(__currency, _currency);
   }

   //============================================================
   // <T>获得出售价格的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String currency(){
      return _currency;
   }

   //============================================================
   // <T>设置出售价格的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCurrency(String value){
      _currency = value;
   }

   //============================================================
   // <T>判断出售开始时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSellBeginDateChanged(){
      return !__sellBeginDate.equals(_sellBeginDate);
   }

   //============================================================
   // <T>获得出售开始时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime sellBeginDate(){
      return _sellBeginDate;
   }

   //============================================================
   // <T>设置出售开始时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSellBeginDate(TDateTime value){
      _sellBeginDate = value;
   }

   //============================================================
   // <T>判断拍卖时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAuctionTimeChanged(){
      return __auctionTime != _auctionTime;
   }

   //============================================================
   // <T>获得拍卖时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int auctionTime(){
      return _auctionTime;
   }

   //============================================================
   // <T>设置拍卖时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAuctionTime(int value){
      _auctionTime = value;
   }

   //============================================================
   // <T>判断出售类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSellTypeChanged(){
      return __sellType != _sellType;
   }

   //============================================================
   // <T>获得出售类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int sellType(){
      return _sellType;
   }

   //============================================================
   // <T>设置出售类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSellType(int value){
      _sellType = value;
   }

   //============================================================
   // <T>判断查询大类的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSumTypeChanged(){
      return __sumType != _sumType;
   }

   //============================================================
   // <T>获得查询大类的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int sumType(){
      return _sumType;
   }

   //============================================================
   // <T>设置查询大类的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSumType(int value){
      _sumType = value;
   }

   //============================================================
   // <T>判断查询小类的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isDetailTypeChanged(){
      return __detailType != _detailType;
   }

   //============================================================
   // <T>获得查询小类的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int detailType(){
      return _detailType;
   }

   //============================================================
   // <T>设置查询小类的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDetailType(int value){
      _detailType = value;
   }

   //============================================================
   // <T>判断品级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isQualityChanged(){
      return __quality != _quality;
   }

   //============================================================
   // <T>获得品级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int quality(){
      return _quality;
   }

   //============================================================
   // <T>设置品级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setQuality(int value){
      _quality = value;
   }

   //============================================================
   // <T>判断等级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLevelChanged(){
      return __level != _level;
   }

   //============================================================
   // <T>获得等级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int level(){
      return _level;
   }

   //============================================================
   // <T>设置等级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLevel(int value){
      _level = value;
   }

   //============================================================
   // <T>判断物品模版编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isItemTidChanged(){
      return __itemTid != _itemTid;
   }

   //============================================================
   // <T>获得物品模版编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int itemTid(){
      return _itemTid;
   }

   //============================================================
   // <T>设置物品模版编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setItemTid(int value){
      _itemTid = value;
   }

   //============================================================
   // <T>判断数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCountChanged(){
      return __count != _count;
   }

   //============================================================
   // <T>获得数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int count(){
      return _count;
   }

   //============================================================
   // <T>设置数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCount(int value){
      _count = value;
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
      __belongRoleId = RLong.parse(row.get("belong_role_id"));
      _belongRoleId = __belongRoleId;
      __auctionStatus = row.getInteger("auction_status");
      _auctionStatus = __auctionStatus;
      __typeCd = row.getInteger("type_cd");
      _typeCd = __typeCd;
      __itemId = RLong.parse(row.get("item_id"));
      _itemId = __itemId;
      __sellCurrency = row.get("sell_currency");
      _sellCurrency = __sellCurrency;
      __currency = row.get("currency");
      _currency = __currency;
      __sellBeginDate.parse(row.get("sell_begin_date"));
      _sellBeginDate.assign(__sellBeginDate);
      __auctionTime = row.getInteger("auction_time");
      _auctionTime = __auctionTime;
      __sellType = row.getInteger("sell_type");
      _sellType = __sellType;
      __sumType = row.getInteger("sum_type");
      _sumType = __sumType;
      __detailType = row.getInteger("detail_type");
      _detailType = __detailType;
      __quality = row.getInteger("quality");
      _quality = __quality;
      __level = row.getInteger("level");
      _level = __level;
      __itemTid = row.getInteger("item_tid");
      _itemTid = __itemTid;
      __count = row.getInteger("count");
      _count = __count;
      __status = row.getInteger("status");
      _status = __status;
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