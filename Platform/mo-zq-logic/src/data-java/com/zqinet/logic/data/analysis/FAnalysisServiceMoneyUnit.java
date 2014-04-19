package com.zqinet.logic.data.analysis;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>金钱分析逻辑单元。</T>
//============================================================
public class FAnalysisServiceMoneyUnit extends FLogicUnit
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

   // 存储字段角色ID的定义。
   private long __roleId;

   // 字段角色ID的定义。
   protected long _roleId;

   // 存储字段角色名称的定义。
   private String __roleLabel;

   // 字段角色名称的定义。
   protected String _roleLabel;

   // 存储字段增加绑定金币的定义。
   private int __addGoldBind;

   // 字段增加绑定金币的定义。
   protected int _addGoldBind;

   // 存储字段增加非绑定金币的定义。
   private int __addGoldUnbind;

   // 字段增加非绑定金币的定义。
   protected int _addGoldUnbind;

   // 存储字段增加绑定元宝的定义。
   private int __addMoneyBind;

   // 字段增加绑定元宝的定义。
   protected int _addMoneyBind;

   // 存储字段增加非绑定元宝的定义。
   private int __addMoneyUnbind;

   // 字段增加非绑定元宝的定义。
   protected int _addMoneyUnbind;

   // 存储字段减少绑定金币的定义。
   private int __reduceGoldBind;

   // 字段减少绑定金币的定义。
   protected int _reduceGoldBind;

   // 存储字段减少非绑定金币的定义。
   private int __reduceGoldUnbind;

   // 字段减少非绑定金币的定义。
   protected int _reduceGoldUnbind;

   // 存储字段减少绑定元宝的定义。
   private int __reduceMoneyBind;

   // 字段减少绑定元宝的定义。
   protected int _reduceMoneyBind;

   // 存储字段减少非绑定元宝的定义。
   private int __reduceMoneyUnbind;

   // 字段减少非绑定元宝的定义。
   protected int _reduceMoneyUnbind;

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
   // <T>构造金钱分析逻辑单元。</T>
   //============================================================
   public FAnalysisServiceMoneyUnit(){
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
   // <T>判断角色名称的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleLabelChanged(){
      return !RString.equals(__roleLabel, _roleLabel);
   }

   //============================================================
   // <T>获得角色名称的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String roleLabel(){
      return _roleLabel;
   }

   //============================================================
   // <T>设置角色名称的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleLabel(String value){
      _roleLabel = value;
   }

   //============================================================
   // <T>判断增加绑定金币的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAddGoldBindChanged(){
      return __addGoldBind != _addGoldBind;
   }

   //============================================================
   // <T>获得增加绑定金币的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int addGoldBind(){
      return _addGoldBind;
   }

   //============================================================
   // <T>设置增加绑定金币的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAddGoldBind(int value){
      _addGoldBind = value;
   }

   //============================================================
   // <T>判断增加非绑定金币的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAddGoldUnbindChanged(){
      return __addGoldUnbind != _addGoldUnbind;
   }

   //============================================================
   // <T>获得增加非绑定金币的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int addGoldUnbind(){
      return _addGoldUnbind;
   }

   //============================================================
   // <T>设置增加非绑定金币的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAddGoldUnbind(int value){
      _addGoldUnbind = value;
   }

   //============================================================
   // <T>判断增加绑定元宝的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAddMoneyBindChanged(){
      return __addMoneyBind != _addMoneyBind;
   }

   //============================================================
   // <T>获得增加绑定元宝的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int addMoneyBind(){
      return _addMoneyBind;
   }

   //============================================================
   // <T>设置增加绑定元宝的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAddMoneyBind(int value){
      _addMoneyBind = value;
   }

   //============================================================
   // <T>判断增加非绑定元宝的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAddMoneyUnbindChanged(){
      return __addMoneyUnbind != _addMoneyUnbind;
   }

   //============================================================
   // <T>获得增加非绑定元宝的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int addMoneyUnbind(){
      return _addMoneyUnbind;
   }

   //============================================================
   // <T>设置增加非绑定元宝的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAddMoneyUnbind(int value){
      _addMoneyUnbind = value;
   }

   //============================================================
   // <T>判断减少绑定金币的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isReduceGoldBindChanged(){
      return __reduceGoldBind != _reduceGoldBind;
   }

   //============================================================
   // <T>获得减少绑定金币的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int reduceGoldBind(){
      return _reduceGoldBind;
   }

   //============================================================
   // <T>设置减少绑定金币的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setReduceGoldBind(int value){
      _reduceGoldBind = value;
   }

   //============================================================
   // <T>判断减少非绑定金币的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isReduceGoldUnbindChanged(){
      return __reduceGoldUnbind != _reduceGoldUnbind;
   }

   //============================================================
   // <T>获得减少非绑定金币的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int reduceGoldUnbind(){
      return _reduceGoldUnbind;
   }

   //============================================================
   // <T>设置减少非绑定金币的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setReduceGoldUnbind(int value){
      _reduceGoldUnbind = value;
   }

   //============================================================
   // <T>判断减少绑定元宝的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isReduceMoneyBindChanged(){
      return __reduceMoneyBind != _reduceMoneyBind;
   }

   //============================================================
   // <T>获得减少绑定元宝的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int reduceMoneyBind(){
      return _reduceMoneyBind;
   }

   //============================================================
   // <T>设置减少绑定元宝的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setReduceMoneyBind(int value){
      _reduceMoneyBind = value;
   }

   //============================================================
   // <T>判断减少非绑定元宝的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isReduceMoneyUnbindChanged(){
      return __reduceMoneyUnbind != _reduceMoneyUnbind;
   }

   //============================================================
   // <T>获得减少非绑定元宝的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int reduceMoneyUnbind(){
      return _reduceMoneyUnbind;
   }

   //============================================================
   // <T>设置减少非绑定元宝的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setReduceMoneyUnbind(int value){
      _reduceMoneyUnbind = value;
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
      __roleLabel = row.get("role_label");
      _roleLabel = __roleLabel;
      __addGoldBind = row.getInteger("add_gold_bind");
      _addGoldBind = __addGoldBind;
      __addGoldUnbind = row.getInteger("add_gold_unbind");
      _addGoldUnbind = __addGoldUnbind;
      __addMoneyBind = row.getInteger("add_money_bind");
      _addMoneyBind = __addMoneyBind;
      __addMoneyUnbind = row.getInteger("add_money_unbind");
      _addMoneyUnbind = __addMoneyUnbind;
      __reduceGoldBind = row.getInteger("reduce_gold_bind");
      _reduceGoldBind = __reduceGoldBind;
      __reduceGoldUnbind = row.getInteger("reduce_gold_unbind");
      _reduceGoldUnbind = __reduceGoldUnbind;
      __reduceMoneyBind = row.getInteger("reduce_money_bind");
      _reduceMoneyBind = __reduceMoneyBind;
      __reduceMoneyUnbind = row.getInteger("reduce_money_unbind");
      _reduceMoneyUnbind = __reduceMoneyUnbind;
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