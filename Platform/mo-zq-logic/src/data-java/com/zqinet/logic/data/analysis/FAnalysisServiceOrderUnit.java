package com.zqinet.logic.data.analysis;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>日志分析消费统计逻辑单元。</T>
//============================================================
public class FAnalysisServiceOrderUnit extends FLogicUnit
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

   // 存储字段充值人数的定义。
   private int __roleCount;

   // 字段充值人数的定义。
   protected int _roleCount;

   // 存储字段每日新增充值人数的定义。
   private int __newRoleCount;

   // 字段每日新增充值人数的定义。
   protected int _newRoleCount;

   // 存储字段充值人次的定义。
   private int __consumeCount;

   // 字段充值人次的定义。
   protected int _consumeCount;

   // 存储字段领取礼包个数的定义。
   private int __receiveGiftNum;

   // 字段领取礼包个数的定义。
   protected int _receiveGiftNum;

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
   // <T>构造日志分析消费统计逻辑单元。</T>
   //============================================================
   public FAnalysisServiceOrderUnit(){
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
   // <T>判断充值人数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleCountChanged(){
      return __roleCount != _roleCount;
   }

   //============================================================
   // <T>获得充值人数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleCount(){
      return _roleCount;
   }

   //============================================================
   // <T>设置充值人数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleCount(int value){
      _roleCount = value;
   }

   //============================================================
   // <T>判断每日新增充值人数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isNewRoleCountChanged(){
      return __newRoleCount != _newRoleCount;
   }

   //============================================================
   // <T>获得每日新增充值人数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int newRoleCount(){
      return _newRoleCount;
   }

   //============================================================
   // <T>设置每日新增充值人数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setNewRoleCount(int value){
      _newRoleCount = value;
   }

   //============================================================
   // <T>判断充值人次的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isConsumeCountChanged(){
      return __consumeCount != _consumeCount;
   }

   //============================================================
   // <T>获得充值人次的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int consumeCount(){
      return _consumeCount;
   }

   //============================================================
   // <T>设置充值人次的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setConsumeCount(int value){
      _consumeCount = value;
   }

   //============================================================
   // <T>判断领取礼包个数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isReceiveGiftNumChanged(){
      return __receiveGiftNum != _receiveGiftNum;
   }

   //============================================================
   // <T>获得领取礼包个数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int receiveGiftNum(){
      return _receiveGiftNum;
   }

   //============================================================
   // <T>设置领取礼包个数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setReceiveGiftNum(int value){
      _receiveGiftNum = value;
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
      __roleCount = row.getInteger("role_count");
      _roleCount = __roleCount;
      __newRoleCount = row.getInteger("new_role_count");
      _newRoleCount = __newRoleCount;
      __consumeCount = row.getInteger("consume_count");
      _consumeCount = __consumeCount;
      __receiveGiftNum = row.getInteger("receive_gift_num");
      _receiveGiftNum = __receiveGiftNum;
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