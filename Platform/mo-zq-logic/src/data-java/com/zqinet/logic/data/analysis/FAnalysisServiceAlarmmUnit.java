package com.zqinet.logic.data.analysis;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>日志分析金钱预警逻辑单元。</T>
//============================================================
public class FAnalysisServiceAlarmmUnit extends FLogicUnit
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

   // 存储字段记录时间的定义。
   private TDateTime __recordDate = new TDateTime();

   // 字段记录时间的定义。
   protected TDateTime _recordDate = new TDateTime();

   // 存储字段记录时的定义。
   private TDateTime __recordHour = new TDateTime();

   // 字段记录时的定义。
   protected TDateTime _recordHour = new TDateTime();

   // 存储字段记录天的定义。
   private TDateTime __recordDay = new TDateTime();

   // 字段记录天的定义。
   protected TDateTime _recordDay = new TDateTime();

   // 存储字段记录周的定义。
   private TDateTime __recordWeek = new TDateTime();

   // 字段记录周的定义。
   protected TDateTime _recordWeek = new TDateTime();

   // 存储字段记录月的定义。
   private TDateTime __recordMonth = new TDateTime();

   // 字段记录月的定义。
   protected TDateTime _recordMonth = new TDateTime();

   // 存储字段记录时长的定义。
   private int __recordInterval;

   // 字段记录时长的定义。
   protected int _recordInterval;

   // 存储字段用户编号的定义。
   private long __roleId;

   // 字段用户编号的定义。
   protected long _roleId;

   // 存储字段单人存量（元宝）的定义。
   private int __singleBalanceMoney;

   // 字段单人存量（元宝）的定义。
   protected int _singleBalanceMoney;

   // 存储字段单次获得数量（元宝）的定义。
   private int __singleMaxCountMoney;

   // 字段单次获得数量（元宝）的定义。
   protected int _singleMaxCountMoney;

   // 存储字段获得次数（元宝）的定义。
   private int __sumCountMoney;

   // 字段获得次数（元宝）的定义。
   protected int _sumCountMoney;

   // 存储字段单人存量（绑定元宝）的定义。
   private int __singleBalanceBindMoney;

   // 字段单人存量（绑定元宝）的定义。
   protected int _singleBalanceBindMoney;

   // 存储字段单次获得数量（绑定元宝）的定义。
   private int __singleMaxCountBindMoney;

   // 字段单次获得数量（绑定元宝）的定义。
   protected int _singleMaxCountBindMoney;

   // 存储字段获得次数（绑定元宝）的定义。
   private int __sumCountBindMoney;

   // 字段获得次数（绑定元宝）的定义。
   protected int _sumCountBindMoney;

   // 存储字段单人存量（银两）的定义。
   private int __singleBalanceGold;

   // 字段单人存量（银两）的定义。
   protected int _singleBalanceGold;

   // 存储字段单次获得数量（银两）的定义。
   private int __singleMaxCountGold;

   // 字段单次获得数量（银两）的定义。
   protected int _singleMaxCountGold;

   // 存储字段获得次数（银两）的定义。
   private int __sumCountGold;

   // 字段获得次数（银两）的定义。
   protected int _sumCountGold;

   // 存储字段单人存量（绑定银两）的定义。
   private int __singleBalanceBindGold;

   // 字段单人存量（绑定银两）的定义。
   protected int _singleBalanceBindGold;

   // 存储字段单次获得数量（绑定银两）的定义。
   private int __singleMaxCountBindGold;

   // 字段单次获得数量（绑定银两）的定义。
   protected int _singleMaxCountBindGold;

   // 存储字段获得次数（绑定银两）的定义。
   private int __sumCountBindGold;

   // 字段获得次数（绑定银两）的定义。
   protected int _sumCountBindGold;

   // 存储字段单人存量（斗法）的定义。
   private int __singleBalanceTournament;

   // 字段单人存量（斗法）的定义。
   protected int _singleBalanceTournament;

   // 存储字段单次获得数量（斗法）的定义。
   private int __singleMaxCountTournament;

   // 字段单次获得数量（斗法）的定义。
   protected int _singleMaxCountTournament;

   // 存储字段获得次数（斗法）的定义。
   private int __sumCountTournament;

   // 字段获得次数（斗法）的定义。
   protected int _sumCountTournament;

   // 存储字段单人存量（逐鹿）的定义。
   private int __singleBalanceSociety;

   // 字段单人存量（逐鹿）的定义。
   protected int _singleBalanceSociety;

   // 存储字段单次获得数量（逐鹿）的定义。
   private int __singleMaxCountSociety;

   // 字段单次获得数量（逐鹿）的定义。
   protected int _singleMaxCountSociety;

   // 存储字段获得次数（逐鹿）的定义。
   private int __sumCountSociety;

   // 字段获得次数（逐鹿）的定义。
   protected int _sumCountSociety;

   // 存储字段单人存量（门贡）的定义。
   private int __singleBalanceMetier;

   // 字段单人存量（门贡）的定义。
   protected int _singleBalanceMetier;

   // 存储字段单人获得数量（门贡）的定义。
   private int __singleMaxCountMetier;

   // 字段单人获得数量（门贡）的定义。
   protected int _singleMaxCountMetier;

   // 存储字段获得次数（门贡）的定义。
   private int __sumCountMetier;

   // 字段获得次数（门贡）的定义。
   protected int _sumCountMetier;

   // 存储字段单人存量（帮贡）的定义。
   private int __singleBalanceGang;

   // 字段单人存量（帮贡）的定义。
   protected int _singleBalanceGang;

   // 存储字段单人获得数量（帮贡）的定义。
   private int __singleMaxCountGang;

   // 字段单人获得数量（帮贡）的定义。
   protected int _singleMaxCountGang;

   // 存储字段获得次数（帮贡）的定义。
   private int __sumCountGang;

   // 字段获得次数（帮贡）的定义。
   protected int _sumCountGang;

   // 存储字段单人存量（经验）的定义。
   private int __singleBalanceExp;

   // 字段单人存量（经验）的定义。
   protected int _singleBalanceExp;

   // 存储字段单人获得数量（经验）的定义。
   private int __singleMaxCountExp;

   // 字段单人获得数量（经验）的定义。
   protected int _singleMaxCountExp;

   // 存储字段获得次数（经验）的定义。
   private int __sumCountExp;

   // 字段获得次数（经验）的定义。
   protected int _sumCountExp;

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
   // <T>构造日志分析金钱预警逻辑单元。</T>
   //============================================================
   public FAnalysisServiceAlarmmUnit(){
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
   // <T>判断记录时的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRecordHourChanged(){
      return !__recordHour.equals(_recordHour);
   }

   //============================================================
   // <T>获得记录时的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime recordHour(){
      return _recordHour;
   }

   //============================================================
   // <T>设置记录时的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRecordHour(TDateTime value){
      _recordHour = value;
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
   // <T>判断记录周的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRecordWeekChanged(){
      return !__recordWeek.equals(_recordWeek);
   }

   //============================================================
   // <T>获得记录周的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime recordWeek(){
      return _recordWeek;
   }

   //============================================================
   // <T>设置记录周的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRecordWeek(TDateTime value){
      _recordWeek = value;
   }

   //============================================================
   // <T>判断记录月的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRecordMonthChanged(){
      return !__recordMonth.equals(_recordMonth);
   }

   //============================================================
   // <T>获得记录月的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime recordMonth(){
      return _recordMonth;
   }

   //============================================================
   // <T>设置记录月的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRecordMonth(TDateTime value){
      _recordMonth = value;
   }

   //============================================================
   // <T>判断记录时长的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRecordIntervalChanged(){
      return __recordInterval != _recordInterval;
   }

   //============================================================
   // <T>获得记录时长的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int recordInterval(){
      return _recordInterval;
   }

   //============================================================
   // <T>设置记录时长的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRecordInterval(int value){
      _recordInterval = value;
   }

   //============================================================
   // <T>判断用户编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleIdChanged(){
      return __roleId != _roleId;
   }

   //============================================================
   // <T>获得用户编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long roleId(){
      return _roleId;
   }

   //============================================================
   // <T>设置用户编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleId(long value){
      _roleId = value;
   }

   //============================================================
   // <T>判断单人存量（元宝）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSingleBalanceMoneyChanged(){
      return __singleBalanceMoney != _singleBalanceMoney;
   }

   //============================================================
   // <T>获得单人存量（元宝）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int singleBalanceMoney(){
      return _singleBalanceMoney;
   }

   //============================================================
   // <T>设置单人存量（元宝）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSingleBalanceMoney(int value){
      _singleBalanceMoney = value;
   }

   //============================================================
   // <T>判断单次获得数量（元宝）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSingleMaxCountMoneyChanged(){
      return __singleMaxCountMoney != _singleMaxCountMoney;
   }

   //============================================================
   // <T>获得单次获得数量（元宝）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int singleMaxCountMoney(){
      return _singleMaxCountMoney;
   }

   //============================================================
   // <T>设置单次获得数量（元宝）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSingleMaxCountMoney(int value){
      _singleMaxCountMoney = value;
   }

   //============================================================
   // <T>判断获得次数（元宝）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSumCountMoneyChanged(){
      return __sumCountMoney != _sumCountMoney;
   }

   //============================================================
   // <T>获得获得次数（元宝）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int sumCountMoney(){
      return _sumCountMoney;
   }

   //============================================================
   // <T>设置获得次数（元宝）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSumCountMoney(int value){
      _sumCountMoney = value;
   }

   //============================================================
   // <T>判断单人存量（绑定元宝）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSingleBalanceBindMoneyChanged(){
      return __singleBalanceBindMoney != _singleBalanceBindMoney;
   }

   //============================================================
   // <T>获得单人存量（绑定元宝）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int singleBalanceBindMoney(){
      return _singleBalanceBindMoney;
   }

   //============================================================
   // <T>设置单人存量（绑定元宝）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSingleBalanceBindMoney(int value){
      _singleBalanceBindMoney = value;
   }

   //============================================================
   // <T>判断单次获得数量（绑定元宝）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSingleMaxCountBindMoneyChanged(){
      return __singleMaxCountBindMoney != _singleMaxCountBindMoney;
   }

   //============================================================
   // <T>获得单次获得数量（绑定元宝）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int singleMaxCountBindMoney(){
      return _singleMaxCountBindMoney;
   }

   //============================================================
   // <T>设置单次获得数量（绑定元宝）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSingleMaxCountBindMoney(int value){
      _singleMaxCountBindMoney = value;
   }

   //============================================================
   // <T>判断获得次数（绑定元宝）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSumCountBindMoneyChanged(){
      return __sumCountBindMoney != _sumCountBindMoney;
   }

   //============================================================
   // <T>获得获得次数（绑定元宝）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int sumCountBindMoney(){
      return _sumCountBindMoney;
   }

   //============================================================
   // <T>设置获得次数（绑定元宝）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSumCountBindMoney(int value){
      _sumCountBindMoney = value;
   }

   //============================================================
   // <T>判断单人存量（银两）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSingleBalanceGoldChanged(){
      return __singleBalanceGold != _singleBalanceGold;
   }

   //============================================================
   // <T>获得单人存量（银两）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int singleBalanceGold(){
      return _singleBalanceGold;
   }

   //============================================================
   // <T>设置单人存量（银两）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSingleBalanceGold(int value){
      _singleBalanceGold = value;
   }

   //============================================================
   // <T>判断单次获得数量（银两）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSingleMaxCountGoldChanged(){
      return __singleMaxCountGold != _singleMaxCountGold;
   }

   //============================================================
   // <T>获得单次获得数量（银两）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int singleMaxCountGold(){
      return _singleMaxCountGold;
   }

   //============================================================
   // <T>设置单次获得数量（银两）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSingleMaxCountGold(int value){
      _singleMaxCountGold = value;
   }

   //============================================================
   // <T>判断获得次数（银两）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSumCountGoldChanged(){
      return __sumCountGold != _sumCountGold;
   }

   //============================================================
   // <T>获得获得次数（银两）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int sumCountGold(){
      return _sumCountGold;
   }

   //============================================================
   // <T>设置获得次数（银两）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSumCountGold(int value){
      _sumCountGold = value;
   }

   //============================================================
   // <T>判断单人存量（绑定银两）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSingleBalanceBindGoldChanged(){
      return __singleBalanceBindGold != _singleBalanceBindGold;
   }

   //============================================================
   // <T>获得单人存量（绑定银两）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int singleBalanceBindGold(){
      return _singleBalanceBindGold;
   }

   //============================================================
   // <T>设置单人存量（绑定银两）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSingleBalanceBindGold(int value){
      _singleBalanceBindGold = value;
   }

   //============================================================
   // <T>判断单次获得数量（绑定银两）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSingleMaxCountBindGoldChanged(){
      return __singleMaxCountBindGold != _singleMaxCountBindGold;
   }

   //============================================================
   // <T>获得单次获得数量（绑定银两）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int singleMaxCountBindGold(){
      return _singleMaxCountBindGold;
   }

   //============================================================
   // <T>设置单次获得数量（绑定银两）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSingleMaxCountBindGold(int value){
      _singleMaxCountBindGold = value;
   }

   //============================================================
   // <T>判断获得次数（绑定银两）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSumCountBindGoldChanged(){
      return __sumCountBindGold != _sumCountBindGold;
   }

   //============================================================
   // <T>获得获得次数（绑定银两）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int sumCountBindGold(){
      return _sumCountBindGold;
   }

   //============================================================
   // <T>设置获得次数（绑定银两）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSumCountBindGold(int value){
      _sumCountBindGold = value;
   }

   //============================================================
   // <T>判断单人存量（斗法）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSingleBalanceTournamentChanged(){
      return __singleBalanceTournament != _singleBalanceTournament;
   }

   //============================================================
   // <T>获得单人存量（斗法）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int singleBalanceTournament(){
      return _singleBalanceTournament;
   }

   //============================================================
   // <T>设置单人存量（斗法）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSingleBalanceTournament(int value){
      _singleBalanceTournament = value;
   }

   //============================================================
   // <T>判断单次获得数量（斗法）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSingleMaxCountTournamentChanged(){
      return __singleMaxCountTournament != _singleMaxCountTournament;
   }

   //============================================================
   // <T>获得单次获得数量（斗法）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int singleMaxCountTournament(){
      return _singleMaxCountTournament;
   }

   //============================================================
   // <T>设置单次获得数量（斗法）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSingleMaxCountTournament(int value){
      _singleMaxCountTournament = value;
   }

   //============================================================
   // <T>判断获得次数（斗法）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSumCountTournamentChanged(){
      return __sumCountTournament != _sumCountTournament;
   }

   //============================================================
   // <T>获得获得次数（斗法）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int sumCountTournament(){
      return _sumCountTournament;
   }

   //============================================================
   // <T>设置获得次数（斗法）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSumCountTournament(int value){
      _sumCountTournament = value;
   }

   //============================================================
   // <T>判断单人存量（逐鹿）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSingleBalanceSocietyChanged(){
      return __singleBalanceSociety != _singleBalanceSociety;
   }

   //============================================================
   // <T>获得单人存量（逐鹿）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int singleBalanceSociety(){
      return _singleBalanceSociety;
   }

   //============================================================
   // <T>设置单人存量（逐鹿）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSingleBalanceSociety(int value){
      _singleBalanceSociety = value;
   }

   //============================================================
   // <T>判断单次获得数量（逐鹿）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSingleMaxCountSocietyChanged(){
      return __singleMaxCountSociety != _singleMaxCountSociety;
   }

   //============================================================
   // <T>获得单次获得数量（逐鹿）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int singleMaxCountSociety(){
      return _singleMaxCountSociety;
   }

   //============================================================
   // <T>设置单次获得数量（逐鹿）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSingleMaxCountSociety(int value){
      _singleMaxCountSociety = value;
   }

   //============================================================
   // <T>判断获得次数（逐鹿）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSumCountSocietyChanged(){
      return __sumCountSociety != _sumCountSociety;
   }

   //============================================================
   // <T>获得获得次数（逐鹿）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int sumCountSociety(){
      return _sumCountSociety;
   }

   //============================================================
   // <T>设置获得次数（逐鹿）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSumCountSociety(int value){
      _sumCountSociety = value;
   }

   //============================================================
   // <T>判断单人存量（门贡）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSingleBalanceMetierChanged(){
      return __singleBalanceMetier != _singleBalanceMetier;
   }

   //============================================================
   // <T>获得单人存量（门贡）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int singleBalanceMetier(){
      return _singleBalanceMetier;
   }

   //============================================================
   // <T>设置单人存量（门贡）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSingleBalanceMetier(int value){
      _singleBalanceMetier = value;
   }

   //============================================================
   // <T>判断单人获得数量（门贡）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSingleMaxCountMetierChanged(){
      return __singleMaxCountMetier != _singleMaxCountMetier;
   }

   //============================================================
   // <T>获得单人获得数量（门贡）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int singleMaxCountMetier(){
      return _singleMaxCountMetier;
   }

   //============================================================
   // <T>设置单人获得数量（门贡）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSingleMaxCountMetier(int value){
      _singleMaxCountMetier = value;
   }

   //============================================================
   // <T>判断获得次数（门贡）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSumCountMetierChanged(){
      return __sumCountMetier != _sumCountMetier;
   }

   //============================================================
   // <T>获得获得次数（门贡）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int sumCountMetier(){
      return _sumCountMetier;
   }

   //============================================================
   // <T>设置获得次数（门贡）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSumCountMetier(int value){
      _sumCountMetier = value;
   }

   //============================================================
   // <T>判断单人存量（帮贡）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSingleBalanceGangChanged(){
      return __singleBalanceGang != _singleBalanceGang;
   }

   //============================================================
   // <T>获得单人存量（帮贡）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int singleBalanceGang(){
      return _singleBalanceGang;
   }

   //============================================================
   // <T>设置单人存量（帮贡）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSingleBalanceGang(int value){
      _singleBalanceGang = value;
   }

   //============================================================
   // <T>判断单人获得数量（帮贡）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSingleMaxCountGangChanged(){
      return __singleMaxCountGang != _singleMaxCountGang;
   }

   //============================================================
   // <T>获得单人获得数量（帮贡）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int singleMaxCountGang(){
      return _singleMaxCountGang;
   }

   //============================================================
   // <T>设置单人获得数量（帮贡）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSingleMaxCountGang(int value){
      _singleMaxCountGang = value;
   }

   //============================================================
   // <T>判断获得次数（帮贡）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSumCountGangChanged(){
      return __sumCountGang != _sumCountGang;
   }

   //============================================================
   // <T>获得获得次数（帮贡）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int sumCountGang(){
      return _sumCountGang;
   }

   //============================================================
   // <T>设置获得次数（帮贡）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSumCountGang(int value){
      _sumCountGang = value;
   }

   //============================================================
   // <T>判断单人存量（经验）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSingleBalanceExpChanged(){
      return __singleBalanceExp != _singleBalanceExp;
   }

   //============================================================
   // <T>获得单人存量（经验）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int singleBalanceExp(){
      return _singleBalanceExp;
   }

   //============================================================
   // <T>设置单人存量（经验）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSingleBalanceExp(int value){
      _singleBalanceExp = value;
   }

   //============================================================
   // <T>判断单人获得数量（经验）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSingleMaxCountExpChanged(){
      return __singleMaxCountExp != _singleMaxCountExp;
   }

   //============================================================
   // <T>获得单人获得数量（经验）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int singleMaxCountExp(){
      return _singleMaxCountExp;
   }

   //============================================================
   // <T>设置单人获得数量（经验）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSingleMaxCountExp(int value){
      _singleMaxCountExp = value;
   }

   //============================================================
   // <T>判断获得次数（经验）的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSumCountExpChanged(){
      return __sumCountExp != _sumCountExp;
   }

   //============================================================
   // <T>获得获得次数（经验）的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int sumCountExp(){
      return _sumCountExp;
   }

   //============================================================
   // <T>设置获得次数（经验）的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSumCountExp(int value){
      _sumCountExp = value;
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
      __recordDate.parse(row.get("record_date"));
      _recordDate.assign(__recordDate);
      __recordHour.parse(row.get("record_hour"));
      _recordHour.assign(__recordHour);
      __recordDay.parse(row.get("record_day"));
      _recordDay.assign(__recordDay);
      __recordWeek.parse(row.get("record_week"));
      _recordWeek.assign(__recordWeek);
      __recordMonth.parse(row.get("record_month"));
      _recordMonth.assign(__recordMonth);
      __recordInterval = row.getInteger("record_interval");
      _recordInterval = __recordInterval;
      __roleId = RLong.parse(row.get("role_id"));
      _roleId = __roleId;
      __singleBalanceMoney = row.getInteger("single_balance_money");
      _singleBalanceMoney = __singleBalanceMoney;
      __singleMaxCountMoney = row.getInteger("single_max_count_money");
      _singleMaxCountMoney = __singleMaxCountMoney;
      __sumCountMoney = row.getInteger("sum_count_money");
      _sumCountMoney = __sumCountMoney;
      __singleBalanceBindMoney = row.getInteger("single_balance_bind_money");
      _singleBalanceBindMoney = __singleBalanceBindMoney;
      __singleMaxCountBindMoney = row.getInteger("single_max_count_bind_money");
      _singleMaxCountBindMoney = __singleMaxCountBindMoney;
      __sumCountBindMoney = row.getInteger("sum_count_bind_money");
      _sumCountBindMoney = __sumCountBindMoney;
      __singleBalanceGold = row.getInteger("single_balance_gold");
      _singleBalanceGold = __singleBalanceGold;
      __singleMaxCountGold = row.getInteger("single_max_count_gold");
      _singleMaxCountGold = __singleMaxCountGold;
      __sumCountGold = row.getInteger("sum_count_gold");
      _sumCountGold = __sumCountGold;
      __singleBalanceBindGold = row.getInteger("single_balance_bind_gold");
      _singleBalanceBindGold = __singleBalanceBindGold;
      __singleMaxCountBindGold = row.getInteger("single_max_count_bind_gold");
      _singleMaxCountBindGold = __singleMaxCountBindGold;
      __sumCountBindGold = row.getInteger("sum_count_bind_gold");
      _sumCountBindGold = __sumCountBindGold;
      __singleBalanceTournament = row.getInteger("single_balance_tournament");
      _singleBalanceTournament = __singleBalanceTournament;
      __singleMaxCountTournament = row.getInteger("single_max_count_tournament");
      _singleMaxCountTournament = __singleMaxCountTournament;
      __sumCountTournament = row.getInteger("sum_count_tournament");
      _sumCountTournament = __sumCountTournament;
      __singleBalanceSociety = row.getInteger("single_balance_society");
      _singleBalanceSociety = __singleBalanceSociety;
      __singleMaxCountSociety = row.getInteger("single_max_count_society");
      _singleMaxCountSociety = __singleMaxCountSociety;
      __sumCountSociety = row.getInteger("sum_count_society");
      _sumCountSociety = __sumCountSociety;
      __singleBalanceMetier = row.getInteger("single_balance_metier");
      _singleBalanceMetier = __singleBalanceMetier;
      __singleMaxCountMetier = row.getInteger("single_max_count_metier");
      _singleMaxCountMetier = __singleMaxCountMetier;
      __sumCountMetier = row.getInteger("sum_count_metier");
      _sumCountMetier = __sumCountMetier;
      __singleBalanceGang = row.getInteger("single_balance_gang");
      _singleBalanceGang = __singleBalanceGang;
      __singleMaxCountGang = row.getInteger("single_max_count_gang");
      _singleMaxCountGang = __singleMaxCountGang;
      __sumCountGang = row.getInteger("sum_count_gang");
      _sumCountGang = __sumCountGang;
      __singleBalanceExp = row.getInteger("single_balance_exp");
      _singleBalanceExp = __singleBalanceExp;
      __singleMaxCountExp = row.getInteger("single_max_count_exp");
      _singleMaxCountExp = __singleMaxCountExp;
      __sumCountExp = row.getInteger("sum_count_exp");
      _sumCountExp = __sumCountExp;
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