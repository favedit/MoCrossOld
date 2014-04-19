package com.zqinet.logic.data.logger;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>道具销售日志逻辑单元。</T>
//============================================================
public class FLoggerItemsellUnit extends FLogicUnit
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

   // 存储字段角色ID的定义。
   private long __roleId;

   // 字段角色ID的定义。
   protected long _roleId;

   // 存储字段操作时间的定义。
   private TDateTime __opTime = new TDateTime();

   // 字段操作时间的定义。
   protected TDateTime _opTime = new TDateTime();

   // 存储字段登陆IP的定义。
   private String __loginIp;

   // 字段登陆IP的定义。
   protected String _loginIp;

   // 存储字段玩家所在的地图ID的定义。
   private int __mapId;

   // 字段玩家所在的地图ID的定义。
   protected int _mapId;

   // 存储字段职业ID的定义。
   private int __metierTid;

   // 字段职业ID的定义。
   protected int _metierTid;

   // 存储字段玩家等级的定义。
   private int __roleLevel;

   // 字段玩家等级的定义。
   protected int _roleLevel;

   // 存储字段物品ID的定义。
   private int __itemTid;

   // 字段物品ID的定义。
   protected int _itemTid;

   // 存储字段购买的数量的定义。
   private int __itemNum;

   // 字段购买的数量的定义。
   protected int _itemNum;

   // 存储字段折扣前的单价的定义。
   private int __price;

   // 字段折扣前的单价的定义。
   protected int _price;

   // 存储字段折扣后的价格的定义。
   private int __discountPrice;

   // 字段折扣后的价格的定义。
   protected int _discountPrice;

   // 存储字段总价的定义。
   private int __totalPrice;

   // 字段总价的定义。
   protected int _totalPrice;

   // 存储字段余额的定义。
   private int __balance;

   // 字段余额的定义。
   protected int _balance;

   // 存储字段货币类型的定义。
   private int __moneyType;

   // 字段货币类型的定义。
   protected int _moneyType;

   // 存储字段商城类型的定义。
   private int __storeType;

   // 字段商城类型的定义。
   protected int _storeType;

   // 存储字段商品包Tid的定义。
   private int __itembagTid;

   // 字段商品包Tid的定义。
   protected int _itembagTid;

   // 存储字段参数1的定义。
   private String __param1;

   // 字段参数1的定义。
   protected String _param1;

   // 存储字段参数2的定义。
   private String __param2;

   // 字段参数2的定义。
   protected String _param2;

   // 存储字段物品类型的定义。
   private int __itemType;

   // 字段物品类型的定义。
   protected int _itemType;

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

   // 存储字段是否已经发送的定义。
   private boolean __isSend;

   // 字段是否已经发送的定义。
   protected boolean _isSend;

   //============================================================
   // <T>构造道具销售日志逻辑单元。</T>
   //============================================================
   public FLoggerItemsellUnit(){
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
   // <T>判断角色ID的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleIdChanged(){
      return __roleId != _roleId;
   }

   //============================================================
   // <T>获得角色ID的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long roleId(){
      return _roleId;
   }

   //============================================================
   // <T>设置角色ID的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleId(long value){
      _roleId = value;
   }

   //============================================================
   // <T>判断操作时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isOpTimeChanged(){
      return !__opTime.equals(_opTime);
   }

   //============================================================
   // <T>获得操作时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime opTime(){
      return _opTime;
   }

   //============================================================
   // <T>设置操作时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setOpTime(TDateTime value){
      _opTime = value;
   }

   //============================================================
   // <T>判断登陆IP的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLoginIpChanged(){
      return !RString.equals(__loginIp, _loginIp);
   }

   //============================================================
   // <T>获得登陆IP的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String loginIp(){
      return _loginIp;
   }

   //============================================================
   // <T>设置登陆IP的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLoginIp(String value){
      _loginIp = value;
   }

   //============================================================
   // <T>判断玩家所在的地图ID的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMapIdChanged(){
      return __mapId != _mapId;
   }

   //============================================================
   // <T>获得玩家所在的地图ID的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int mapId(){
      return _mapId;
   }

   //============================================================
   // <T>设置玩家所在的地图ID的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMapId(int value){
      _mapId = value;
   }

   //============================================================
   // <T>判断职业ID的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMetierTidChanged(){
      return __metierTid != _metierTid;
   }

   //============================================================
   // <T>获得职业ID的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int metierTid(){
      return _metierTid;
   }

   //============================================================
   // <T>设置职业ID的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMetierTid(int value){
      _metierTid = value;
   }

   //============================================================
   // <T>判断玩家等级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleLevelChanged(){
      return __roleLevel != _roleLevel;
   }

   //============================================================
   // <T>获得玩家等级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleLevel(){
      return _roleLevel;
   }

   //============================================================
   // <T>设置玩家等级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleLevel(int value){
      _roleLevel = value;
   }

   //============================================================
   // <T>判断物品ID的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isItemTidChanged(){
      return __itemTid != _itemTid;
   }

   //============================================================
   // <T>获得物品ID的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int itemTid(){
      return _itemTid;
   }

   //============================================================
   // <T>设置物品ID的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setItemTid(int value){
      _itemTid = value;
   }

   //============================================================
   // <T>判断购买的数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isItemNumChanged(){
      return __itemNum != _itemNum;
   }

   //============================================================
   // <T>获得购买的数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int itemNum(){
      return _itemNum;
   }

   //============================================================
   // <T>设置购买的数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setItemNum(int value){
      _itemNum = value;
   }

   //============================================================
   // <T>判断折扣前的单价的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPriceChanged(){
      return __price != _price;
   }

   //============================================================
   // <T>获得折扣前的单价的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int price(){
      return _price;
   }

   //============================================================
   // <T>设置折扣前的单价的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPrice(int value){
      _price = value;
   }

   //============================================================
   // <T>判断折扣后的价格的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isDiscountPriceChanged(){
      return __discountPrice != _discountPrice;
   }

   //============================================================
   // <T>获得折扣后的价格的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int discountPrice(){
      return _discountPrice;
   }

   //============================================================
   // <T>设置折扣后的价格的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDiscountPrice(int value){
      _discountPrice = value;
   }

   //============================================================
   // <T>判断总价的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTotalPriceChanged(){
      return __totalPrice != _totalPrice;
   }

   //============================================================
   // <T>获得总价的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int totalPrice(){
      return _totalPrice;
   }

   //============================================================
   // <T>设置总价的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTotalPrice(int value){
      _totalPrice = value;
   }

   //============================================================
   // <T>判断余额的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isBalanceChanged(){
      return __balance != _balance;
   }

   //============================================================
   // <T>获得余额的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int balance(){
      return _balance;
   }

   //============================================================
   // <T>设置余额的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setBalance(int value){
      _balance = value;
   }

   //============================================================
   // <T>判断货币类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMoneyTypeChanged(){
      return __moneyType != _moneyType;
   }

   //============================================================
   // <T>获得货币类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int moneyType(){
      return _moneyType;
   }

   //============================================================
   // <T>设置货币类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMoneyType(int value){
      _moneyType = value;
   }

   //============================================================
   // <T>判断商城类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isStoreTypeChanged(){
      return __storeType != _storeType;
   }

   //============================================================
   // <T>获得商城类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int storeType(){
      return _storeType;
   }

   //============================================================
   // <T>设置商城类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setStoreType(int value){
      _storeType = value;
   }

   //============================================================
   // <T>判断商品包Tid的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isItembagTidChanged(){
      return __itembagTid != _itembagTid;
   }

   //============================================================
   // <T>获得商品包Tid的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int itembagTid(){
      return _itembagTid;
   }

   //============================================================
   // <T>设置商品包Tid的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setItembagTid(int value){
      _itembagTid = value;
   }

   //============================================================
   // <T>判断参数1的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isParam1Changed(){
      return !RString.equals(__param1, _param1);
   }

   //============================================================
   // <T>获得参数1的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String param1(){
      return _param1;
   }

   //============================================================
   // <T>设置参数1的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setParam1(String value){
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
   // <T>判断物品类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isItemTypeChanged(){
      return __itemType != _itemType;
   }

   //============================================================
   // <T>获得物品类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int itemType(){
      return _itemType;
   }

   //============================================================
   // <T>设置物品类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setItemType(int value){
      _itemType = value;
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
   // <T>判断是否已经发送的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isIsSendChanged(){
      return __isSend != _isSend;
   }

   //============================================================
   // <T>获得是否已经发送的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSend(){
      return _isSend;
   }

   //============================================================
   // <T>设置是否已经发送的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setIsSend(boolean value){
      _isSend = value;
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
      __opTime.parse(row.get("op_time"));
      _opTime.assign(__opTime);
      __loginIp = row.get("login_ip");
      _loginIp = __loginIp;
      __mapId = row.getInteger("map_id");
      _mapId = __mapId;
      __metierTid = row.getInteger("metier_tid");
      _metierTid = __metierTid;
      __roleLevel = row.getInteger("role_level");
      _roleLevel = __roleLevel;
      __itemTid = row.getInteger("item_tid");
      _itemTid = __itemTid;
      __itemNum = row.getInteger("item_num");
      _itemNum = __itemNum;
      __price = row.getInteger("price");
      _price = __price;
      __discountPrice = row.getInteger("discount_price");
      _discountPrice = __discountPrice;
      __totalPrice = row.getInteger("total_price");
      _totalPrice = __totalPrice;
      __balance = row.getInteger("balance");
      _balance = __balance;
      __moneyType = row.getInteger("money_type");
      _moneyType = __moneyType;
      __storeType = row.getInteger("store_type");
      _storeType = __storeType;
      __itembagTid = row.getInteger("itembag_tid");
      _itembagTid = __itembagTid;
      __param1 = row.get("param1");
      _param1 = __param1;
      __param2 = row.get("param2");
      _param2 = __param2;
      __itemType = row.getInteger("item_type");
      _itemType = __itemType;
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