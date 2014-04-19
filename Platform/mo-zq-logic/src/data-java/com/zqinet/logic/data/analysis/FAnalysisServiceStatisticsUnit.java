package com.zqinet.logic.data.analysis;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>分析服务统计逻辑单元。</T>
//============================================================
public class FAnalysisServiceStatisticsUnit extends FLogicUnit
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

   // 存储字段账号创建数量的定义。
   private int __accountCreateCount;

   // 字段账号创建数量的定义。
   protected int _accountCreateCount;

   // 存储字段角色创建中日期的定义。
   private int __roleCreatingCount;

   // 字段角色创建中日期的定义。
   protected int _roleCreatingCount;

   // 存储字段角色创建数量的定义。
   private int __roleCreateCount;

   // 字段角色创建数量的定义。
   protected int _roleCreateCount;

   // 存储字段角色创建成功数量的定义。
   private int __roleCreateSuccessCount;

   // 字段角色创建成功数量的定义。
   protected int _roleCreateSuccessCount;

   // 存储字段角色加载数量的定义。
   private int __roleLoadCount;

   // 字段角色加载数量的定义。
   protected int _roleLoadCount;

   // 存储字段角色加载总次数的定义。
   private int __roleLoadTotal;

   // 字段角色加载总次数的定义。
   protected int _roleLoadTotal;

   // 存储字段角色登录数量的定义。
   private int __roleLoginCount;

   // 字段角色登录数量的定义。
   protected int _roleLoginCount;

   // 存储字段角色登录总次数的定义。
   private int __roleLoginTotal;

   // 字段角色登录总次数的定义。
   protected int _roleLoginTotal;

   // 存储字段角色在线数量的定义。
   private int __roleOnlineCount;

   // 字段角色在线数量的定义。
   protected int _roleOnlineCount;

   // 存储字段角色在线地址数量的定义。
   private int __roleOnlineHostCount;

   // 字段角色在线地址数量的定义。
   protected int _roleOnlineHostCount;

   // 存储字段角色登录历史1天的定义。
   private int __roleHistoryLogin1Count;

   // 字段角色登录历史1天的定义。
   protected int _roleHistoryLogin1Count;

   // 存储字段角色登录历史2天的定义。
   private int __roleHistoryLogin2Count;

   // 字段角色登录历史2天的定义。
   protected int _roleHistoryLogin2Count;

   // 存储字段角色登录历史3天的定义。
   private int __roleHistoryLogin3Count;

   // 字段角色登录历史3天的定义。
   protected int _roleHistoryLogin3Count;

   // 存储字段角色登录历史4天的定义。
   private int __roleHistoryLogin4Count;

   // 字段角色登录历史4天的定义。
   protected int _roleHistoryLogin4Count;

   // 存储字段角色登录历史5天的定义。
   private int __roleHistoryLogin5Count;

   // 字段角色登录历史5天的定义。
   protected int _roleHistoryLogin5Count;

   // 存储字段角色登录历史6天的定义。
   private int __roleHistoryLogin6Count;

   // 字段角色登录历史6天的定义。
   protected int _roleHistoryLogin6Count;

   // 存储字段角色登录历史7天的定义。
   private int __roleHistoryLogin7Count;

   // 字段角色登录历史7天的定义。
   protected int _roleHistoryLogin7Count;

   // 存储字段角色登录历史14天的定义。
   private int __roleHistoryLogin14Count;

   // 字段角色登录历史14天的定义。
   protected int _roleHistoryLogin14Count;

   // 存储字段角色登录历史30天的定义。
   private int __roleHistoryLogin30Count;

   // 字段角色登录历史30天的定义。
   protected int _roleHistoryLogin30Count;

   // 存储字段角色登录历史60天的定义。
   private int __roleHistoryLogin60Count;

   // 字段角色登录历史60天的定义。
   protected int _roleHistoryLogin60Count;

   // 存储字段剩余元宝的定义。
   private int __remainMoney;

   // 字段剩余元宝的定义。
   protected int _remainMoney;

   // 存储字段消耗元宝的定义。
   private int __consumeMoney;

   // 字段消耗元宝的定义。
   protected int _consumeMoney;

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
   // <T>构造分析服务统计逻辑单元。</T>
   //============================================================
   public FAnalysisServiceStatisticsUnit(){
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
   // <T>判断账号创建数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAccountCreateCountChanged(){
      return __accountCreateCount != _accountCreateCount;
   }

   //============================================================
   // <T>获得账号创建数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int accountCreateCount(){
      return _accountCreateCount;
   }

   //============================================================
   // <T>设置账号创建数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAccountCreateCount(int value){
      _accountCreateCount = value;
   }

   //============================================================
   // <T>判断角色创建中日期的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleCreatingCountChanged(){
      return __roleCreatingCount != _roleCreatingCount;
   }

   //============================================================
   // <T>获得角色创建中日期的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleCreatingCount(){
      return _roleCreatingCount;
   }

   //============================================================
   // <T>设置角色创建中日期的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleCreatingCount(int value){
      _roleCreatingCount = value;
   }

   //============================================================
   // <T>判断角色创建数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleCreateCountChanged(){
      return __roleCreateCount != _roleCreateCount;
   }

   //============================================================
   // <T>获得角色创建数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleCreateCount(){
      return _roleCreateCount;
   }

   //============================================================
   // <T>设置角色创建数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleCreateCount(int value){
      _roleCreateCount = value;
   }

   //============================================================
   // <T>判断角色创建成功数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleCreateSuccessCountChanged(){
      return __roleCreateSuccessCount != _roleCreateSuccessCount;
   }

   //============================================================
   // <T>获得角色创建成功数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleCreateSuccessCount(){
      return _roleCreateSuccessCount;
   }

   //============================================================
   // <T>设置角色创建成功数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleCreateSuccessCount(int value){
      _roleCreateSuccessCount = value;
   }

   //============================================================
   // <T>判断角色加载数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleLoadCountChanged(){
      return __roleLoadCount != _roleLoadCount;
   }

   //============================================================
   // <T>获得角色加载数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleLoadCount(){
      return _roleLoadCount;
   }

   //============================================================
   // <T>设置角色加载数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleLoadCount(int value){
      _roleLoadCount = value;
   }

   //============================================================
   // <T>判断角色加载总次数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleLoadTotalChanged(){
      return __roleLoadTotal != _roleLoadTotal;
   }

   //============================================================
   // <T>获得角色加载总次数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleLoadTotal(){
      return _roleLoadTotal;
   }

   //============================================================
   // <T>设置角色加载总次数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleLoadTotal(int value){
      _roleLoadTotal = value;
   }

   //============================================================
   // <T>判断角色登录数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleLoginCountChanged(){
      return __roleLoginCount != _roleLoginCount;
   }

   //============================================================
   // <T>获得角色登录数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleLoginCount(){
      return _roleLoginCount;
   }

   //============================================================
   // <T>设置角色登录数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleLoginCount(int value){
      _roleLoginCount = value;
   }

   //============================================================
   // <T>判断角色登录总次数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleLoginTotalChanged(){
      return __roleLoginTotal != _roleLoginTotal;
   }

   //============================================================
   // <T>获得角色登录总次数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleLoginTotal(){
      return _roleLoginTotal;
   }

   //============================================================
   // <T>设置角色登录总次数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleLoginTotal(int value){
      _roleLoginTotal = value;
   }

   //============================================================
   // <T>判断角色在线数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleOnlineCountChanged(){
      return __roleOnlineCount != _roleOnlineCount;
   }

   //============================================================
   // <T>获得角色在线数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleOnlineCount(){
      return _roleOnlineCount;
   }

   //============================================================
   // <T>设置角色在线数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleOnlineCount(int value){
      _roleOnlineCount = value;
   }

   //============================================================
   // <T>判断角色在线地址数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleOnlineHostCountChanged(){
      return __roleOnlineHostCount != _roleOnlineHostCount;
   }

   //============================================================
   // <T>获得角色在线地址数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleOnlineHostCount(){
      return _roleOnlineHostCount;
   }

   //============================================================
   // <T>设置角色在线地址数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleOnlineHostCount(int value){
      _roleOnlineHostCount = value;
   }

   //============================================================
   // <T>判断角色登录历史1天的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleHistoryLogin1CountChanged(){
      return __roleHistoryLogin1Count != _roleHistoryLogin1Count;
   }

   //============================================================
   // <T>获得角色登录历史1天的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleHistoryLogin1Count(){
      return _roleHistoryLogin1Count;
   }

   //============================================================
   // <T>设置角色登录历史1天的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleHistoryLogin1Count(int value){
      _roleHistoryLogin1Count = value;
   }

   //============================================================
   // <T>判断角色登录历史2天的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleHistoryLogin2CountChanged(){
      return __roleHistoryLogin2Count != _roleHistoryLogin2Count;
   }

   //============================================================
   // <T>获得角色登录历史2天的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleHistoryLogin2Count(){
      return _roleHistoryLogin2Count;
   }

   //============================================================
   // <T>设置角色登录历史2天的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleHistoryLogin2Count(int value){
      _roleHistoryLogin2Count = value;
   }

   //============================================================
   // <T>判断角色登录历史3天的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleHistoryLogin3CountChanged(){
      return __roleHistoryLogin3Count != _roleHistoryLogin3Count;
   }

   //============================================================
   // <T>获得角色登录历史3天的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleHistoryLogin3Count(){
      return _roleHistoryLogin3Count;
   }

   //============================================================
   // <T>设置角色登录历史3天的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleHistoryLogin3Count(int value){
      _roleHistoryLogin3Count = value;
   }

   //============================================================
   // <T>判断角色登录历史4天的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleHistoryLogin4CountChanged(){
      return __roleHistoryLogin4Count != _roleHistoryLogin4Count;
   }

   //============================================================
   // <T>获得角色登录历史4天的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleHistoryLogin4Count(){
      return _roleHistoryLogin4Count;
   }

   //============================================================
   // <T>设置角色登录历史4天的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleHistoryLogin4Count(int value){
      _roleHistoryLogin4Count = value;
   }

   //============================================================
   // <T>判断角色登录历史5天的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleHistoryLogin5CountChanged(){
      return __roleHistoryLogin5Count != _roleHistoryLogin5Count;
   }

   //============================================================
   // <T>获得角色登录历史5天的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleHistoryLogin5Count(){
      return _roleHistoryLogin5Count;
   }

   //============================================================
   // <T>设置角色登录历史5天的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleHistoryLogin5Count(int value){
      _roleHistoryLogin5Count = value;
   }

   //============================================================
   // <T>判断角色登录历史6天的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleHistoryLogin6CountChanged(){
      return __roleHistoryLogin6Count != _roleHistoryLogin6Count;
   }

   //============================================================
   // <T>获得角色登录历史6天的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleHistoryLogin6Count(){
      return _roleHistoryLogin6Count;
   }

   //============================================================
   // <T>设置角色登录历史6天的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleHistoryLogin6Count(int value){
      _roleHistoryLogin6Count = value;
   }

   //============================================================
   // <T>判断角色登录历史7天的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleHistoryLogin7CountChanged(){
      return __roleHistoryLogin7Count != _roleHistoryLogin7Count;
   }

   //============================================================
   // <T>获得角色登录历史7天的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleHistoryLogin7Count(){
      return _roleHistoryLogin7Count;
   }

   //============================================================
   // <T>设置角色登录历史7天的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleHistoryLogin7Count(int value){
      _roleHistoryLogin7Count = value;
   }

   //============================================================
   // <T>判断角色登录历史14天的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleHistoryLogin14CountChanged(){
      return __roleHistoryLogin14Count != _roleHistoryLogin14Count;
   }

   //============================================================
   // <T>获得角色登录历史14天的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleHistoryLogin14Count(){
      return _roleHistoryLogin14Count;
   }

   //============================================================
   // <T>设置角色登录历史14天的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleHistoryLogin14Count(int value){
      _roleHistoryLogin14Count = value;
   }

   //============================================================
   // <T>判断角色登录历史30天的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleHistoryLogin30CountChanged(){
      return __roleHistoryLogin30Count != _roleHistoryLogin30Count;
   }

   //============================================================
   // <T>获得角色登录历史30天的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleHistoryLogin30Count(){
      return _roleHistoryLogin30Count;
   }

   //============================================================
   // <T>设置角色登录历史30天的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleHistoryLogin30Count(int value){
      _roleHistoryLogin30Count = value;
   }

   //============================================================
   // <T>判断角色登录历史60天的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleHistoryLogin60CountChanged(){
      return __roleHistoryLogin60Count != _roleHistoryLogin60Count;
   }

   //============================================================
   // <T>获得角色登录历史60天的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleHistoryLogin60Count(){
      return _roleHistoryLogin60Count;
   }

   //============================================================
   // <T>设置角色登录历史60天的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleHistoryLogin60Count(int value){
      _roleHistoryLogin60Count = value;
   }

   //============================================================
   // <T>判断剩余元宝的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRemainMoneyChanged(){
      return __remainMoney != _remainMoney;
   }

   //============================================================
   // <T>获得剩余元宝的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int remainMoney(){
      return _remainMoney;
   }

   //============================================================
   // <T>设置剩余元宝的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRemainMoney(int value){
      _remainMoney = value;
   }

   //============================================================
   // <T>判断消耗元宝的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isConsumeMoneyChanged(){
      return __consumeMoney != _consumeMoney;
   }

   //============================================================
   // <T>获得消耗元宝的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int consumeMoney(){
      return _consumeMoney;
   }

   //============================================================
   // <T>设置消耗元宝的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setConsumeMoney(int value){
      _consumeMoney = value;
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
      __accountCreateCount = row.getInteger("account_create_count");
      _accountCreateCount = __accountCreateCount;
      __roleCreatingCount = row.getInteger("role_creating_count");
      _roleCreatingCount = __roleCreatingCount;
      __roleCreateCount = row.getInteger("role_create_count");
      _roleCreateCount = __roleCreateCount;
      __roleCreateSuccessCount = row.getInteger("role_create_success_count");
      _roleCreateSuccessCount = __roleCreateSuccessCount;
      __roleLoadCount = row.getInteger("role_load_count");
      _roleLoadCount = __roleLoadCount;
      __roleLoadTotal = row.getInteger("role_load_total");
      _roleLoadTotal = __roleLoadTotal;
      __roleLoginCount = row.getInteger("role_login_count");
      _roleLoginCount = __roleLoginCount;
      __roleLoginTotal = row.getInteger("role_login_total");
      _roleLoginTotal = __roleLoginTotal;
      __roleOnlineCount = row.getInteger("role_online_count");
      _roleOnlineCount = __roleOnlineCount;
      __roleOnlineHostCount = row.getInteger("role_online_host_count");
      _roleOnlineHostCount = __roleOnlineHostCount;
      __roleHistoryLogin1Count = row.getInteger("role_history_login1_count");
      _roleHistoryLogin1Count = __roleHistoryLogin1Count;
      __roleHistoryLogin2Count = row.getInteger("role_history_login2_count");
      _roleHistoryLogin2Count = __roleHistoryLogin2Count;
      __roleHistoryLogin3Count = row.getInteger("role_history_login3_count");
      _roleHistoryLogin3Count = __roleHistoryLogin3Count;
      __roleHistoryLogin4Count = row.getInteger("role_history_login4_count");
      _roleHistoryLogin4Count = __roleHistoryLogin4Count;
      __roleHistoryLogin5Count = row.getInteger("role_history_login5_count");
      _roleHistoryLogin5Count = __roleHistoryLogin5Count;
      __roleHistoryLogin6Count = row.getInteger("role_history_login6_count");
      _roleHistoryLogin6Count = __roleHistoryLogin6Count;
      __roleHistoryLogin7Count = row.getInteger("role_history_login7_count");
      _roleHistoryLogin7Count = __roleHistoryLogin7Count;
      __roleHistoryLogin14Count = row.getInteger("role_history_login14_count");
      _roleHistoryLogin14Count = __roleHistoryLogin14Count;
      __roleHistoryLogin30Count = row.getInteger("role_history_login30_count");
      _roleHistoryLogin30Count = __roleHistoryLogin30Count;
      __roleHistoryLogin60Count = row.getInteger("role_history_login60_count");
      _roleHistoryLogin60Count = __roleHistoryLogin60Count;
      __remainMoney = row.getInteger("remain_money");
      _remainMoney = __remainMoney;
      __consumeMoney = row.getInteger("consume_money");
      _consumeMoney = __consumeMoney;
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