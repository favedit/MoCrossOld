package com.zqinet.logic.data.logger;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>角色邮件日志逻辑单元。</T>
//============================================================
public class FLoggerMailUnit extends FLogicUnit
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

   // 存储字段邮件标题的定义。
   private String __title;

   // 字段邮件标题的定义。
   protected String _title;

   // 存储字段邮件内容的定义。
   private String __content;

   // 字段邮件内容的定义。
   protected String _content;

   // 存储字段附件中的货币类型的定义。
   private int __currencyCd;

   // 字段附件中的货币类型的定义。
   protected int _currencyCd;

   // 存储字段附件中的货币数量的定义。
   private int __currencyOunt;

   // 字段附件中的货币数量的定义。
   protected int _currencyOunt;

   // 存储字段发送者编号的定义。
   private long __senderId;

   // 字段发送者编号的定义。
   protected long _senderId;

   // 存储字段接收者编号的定义。
   private long __receiverId;

   // 字段接收者编号的定义。
   protected long _receiverId;

   // 存储字段附件中的帮贡数的定义。
   private int __tribute;

   // 字段附件中的帮贡数的定义。
   protected int _tribute;

   // 存储字段附件中的门贡数量的定义。
   private int __sectTribute;

   // 字段附件中的门贡数量的定义。
   protected int _sectTribute;

   // 存储字段附件中的斗法积分的定义。
   private int __tournamentIntegral;

   // 字段附件中的斗法积分的定义。
   protected int _tournamentIntegral;

   // 存储字段附件中的逐鹿积分的定义。
   private int __societyTournamentIntegral;

   // 字段附件中的逐鹿积分的定义。
   protected int _societyTournamentIntegral;

   // 存储字段经验的定义。
   private int __experience;

   // 字段经验的定义。
   protected int _experience;

   // 存储字段真元的定义。
   private int __crystal;

   // 字段真元的定义。
   protected int _crystal;

   // 存储字段物品类型的定义。
   private int __itemCd;

   // 字段物品类型的定义。
   protected int _itemCd;

   // 存储字段物品编号的定义。
   private int __itemTid;

   // 字段物品编号的定义。
   protected int _itemTid;

   // 存储字段物品品质的定义。
   private int __itemQuality;

   // 字段物品品质的定义。
   protected int _itemQuality;

   // 存储字段物品数量的定义。
   private int __itemCount;

   // 字段物品数量的定义。
   protected int _itemCount;

   // 存储字段邮件类型的定义。
   private int __mailType;

   // 字段邮件类型的定义。
   protected int _mailType;

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
   // <T>构造角色邮件日志逻辑单元。</T>
   //============================================================
   public FLoggerMailUnit(){
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
   // <T>判断邮件标题的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTitleChanged(){
      return !RString.equals(__title, _title);
   }

   //============================================================
   // <T>获得邮件标题的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String title(){
      return _title;
   }

   //============================================================
   // <T>设置邮件标题的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTitle(String value){
      _title = value;
   }

   //============================================================
   // <T>判断邮件内容的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isContentChanged(){
      return !RString.equals(__content, _content);
   }

   //============================================================
   // <T>获得邮件内容的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String content(){
      return _content;
   }

   //============================================================
   // <T>设置邮件内容的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setContent(String value){
      _content = value;
   }

   //============================================================
   // <T>判断附件中的货币类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCurrencyCdChanged(){
      return __currencyCd != _currencyCd;
   }

   //============================================================
   // <T>获得附件中的货币类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int currencyCd(){
      return _currencyCd;
   }

   //============================================================
   // <T>设置附件中的货币类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCurrencyCd(int value){
      _currencyCd = value;
   }

   //============================================================
   // <T>判断附件中的货币数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCurrencyOuntChanged(){
      return __currencyOunt != _currencyOunt;
   }

   //============================================================
   // <T>获得附件中的货币数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int currencyOunt(){
      return _currencyOunt;
   }

   //============================================================
   // <T>设置附件中的货币数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCurrencyOunt(int value){
      _currencyOunt = value;
   }

   //============================================================
   // <T>判断发送者编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSenderIdChanged(){
      return __senderId != _senderId;
   }

   //============================================================
   // <T>获得发送者编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long senderId(){
      return _senderId;
   }

   //============================================================
   // <T>设置发送者编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSenderId(long value){
      _senderId = value;
   }

   //============================================================
   // <T>判断接收者编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isReceiverIdChanged(){
      return __receiverId != _receiverId;
   }

   //============================================================
   // <T>获得接收者编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long receiverId(){
      return _receiverId;
   }

   //============================================================
   // <T>设置接收者编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setReceiverId(long value){
      _receiverId = value;
   }

   //============================================================
   // <T>判断附件中的帮贡数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTributeChanged(){
      return __tribute != _tribute;
   }

   //============================================================
   // <T>获得附件中的帮贡数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int tribute(){
      return _tribute;
   }

   //============================================================
   // <T>设置附件中的帮贡数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTribute(int value){
      _tribute = value;
   }

   //============================================================
   // <T>判断附件中的门贡数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSectTributeChanged(){
      return __sectTribute != _sectTribute;
   }

   //============================================================
   // <T>获得附件中的门贡数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int sectTribute(){
      return _sectTribute;
   }

   //============================================================
   // <T>设置附件中的门贡数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSectTribute(int value){
      _sectTribute = value;
   }

   //============================================================
   // <T>判断附件中的斗法积分的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTournamentIntegralChanged(){
      return __tournamentIntegral != _tournamentIntegral;
   }

   //============================================================
   // <T>获得附件中的斗法积分的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int tournamentIntegral(){
      return _tournamentIntegral;
   }

   //============================================================
   // <T>设置附件中的斗法积分的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTournamentIntegral(int value){
      _tournamentIntegral = value;
   }

   //============================================================
   // <T>判断附件中的逐鹿积分的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSocietyTournamentIntegralChanged(){
      return __societyTournamentIntegral != _societyTournamentIntegral;
   }

   //============================================================
   // <T>获得附件中的逐鹿积分的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int societyTournamentIntegral(){
      return _societyTournamentIntegral;
   }

   //============================================================
   // <T>设置附件中的逐鹿积分的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSocietyTournamentIntegral(int value){
      _societyTournamentIntegral = value;
   }

   //============================================================
   // <T>判断经验的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isExperienceChanged(){
      return __experience != _experience;
   }

   //============================================================
   // <T>获得经验的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int experience(){
      return _experience;
   }

   //============================================================
   // <T>设置经验的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setExperience(int value){
      _experience = value;
   }

   //============================================================
   // <T>判断真元的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCrystalChanged(){
      return __crystal != _crystal;
   }

   //============================================================
   // <T>获得真元的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int crystal(){
      return _crystal;
   }

   //============================================================
   // <T>设置真元的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCrystal(int value){
      _crystal = value;
   }

   //============================================================
   // <T>判断物品类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isItemCdChanged(){
      return __itemCd != _itemCd;
   }

   //============================================================
   // <T>获得物品类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int itemCd(){
      return _itemCd;
   }

   //============================================================
   // <T>设置物品类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setItemCd(int value){
      _itemCd = value;
   }

   //============================================================
   // <T>判断物品编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isItemTidChanged(){
      return __itemTid != _itemTid;
   }

   //============================================================
   // <T>获得物品编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int itemTid(){
      return _itemTid;
   }

   //============================================================
   // <T>设置物品编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setItemTid(int value){
      _itemTid = value;
   }

   //============================================================
   // <T>判断物品品质的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isItemQualityChanged(){
      return __itemQuality != _itemQuality;
   }

   //============================================================
   // <T>获得物品品质的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int itemQuality(){
      return _itemQuality;
   }

   //============================================================
   // <T>设置物品品质的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setItemQuality(int value){
      _itemQuality = value;
   }

   //============================================================
   // <T>判断物品数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isItemCountChanged(){
      return __itemCount != _itemCount;
   }

   //============================================================
   // <T>获得物品数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int itemCount(){
      return _itemCount;
   }

   //============================================================
   // <T>设置物品数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setItemCount(int value){
      _itemCount = value;
   }

   //============================================================
   // <T>判断邮件类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMailTypeChanged(){
      return __mailType != _mailType;
   }

   //============================================================
   // <T>获得邮件类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int mailType(){
      return _mailType;
   }

   //============================================================
   // <T>设置邮件类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMailType(int value){
      _mailType = value;
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
      __title = row.get("title");
      _title = __title;
      __content = row.get("content");
      _content = __content;
      __currencyCd = row.getInteger("currency_cd");
      _currencyCd = __currencyCd;
      __currencyOunt = row.getInteger("currency_ount");
      _currencyOunt = __currencyOunt;
      __senderId = RLong.parse(row.get("sender_id"));
      _senderId = __senderId;
      __receiverId = RLong.parse(row.get("receiver_id"));
      _receiverId = __receiverId;
      __tribute = row.getInteger("tribute");
      _tribute = __tribute;
      __sectTribute = row.getInteger("sect_tribute");
      _sectTribute = __sectTribute;
      __tournamentIntegral = row.getInteger("tournament_integral");
      _tournamentIntegral = __tournamentIntegral;
      __societyTournamentIntegral = row.getInteger("society_tournament_integral");
      _societyTournamentIntegral = __societyTournamentIntegral;
      __experience = row.getInteger("experience");
      _experience = __experience;
      __crystal = row.getInteger("crystal");
      _crystal = __crystal;
      __itemCd = row.getInteger("item_cd");
      _itemCd = __itemCd;
      __itemTid = row.getInteger("item_tid");
      _itemTid = __itemTid;
      __itemQuality = row.getInteger("item_quality");
      _itemQuality = __itemQuality;
      __itemCount = row.getInteger("item_count");
      _itemCount = __itemCount;
      __mailType = row.getInteger("mail_type");
      _mailType = __mailType;
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