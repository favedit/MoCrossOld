package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>角色信息逻辑单元。</T>
//============================================================
public class FGameRoleDataUnit extends FLogicUnit
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

   // 存储字段副本的CD状况的定义。
   private String __copyCd;

   // 字段副本的CD状况的定义。
   protected String _copyCd;

   // 存储字段周常数据存放的定义。
   private String __weeklyData;

   // 字段周常数据存放的定义。
   protected String _weeklyData;

   // 存储字段活动数据的定义。
   private String __activityData;

   // 字段活动数据的定义。
   protected String _activityData;

   // 存储字段九界Boss数据的定义。
   private String __nineCirlesBossData;

   // 字段九界Boss数据的定义。
   protected String _nineCirlesBossData;

   // 存储字段九界数据的定义。
   private String __nineCirlesData;

   // 字段九界数据的定义。
   protected String _nineCirlesData;

   // 存储字段共血boss数据的定义。
   private String __shareHpBossData;

   // 字段共血boss数据的定义。
   protected String _shareHpBossData;

   // 存储字段特殊演员的定义。
   private String __specialActors;

   // 字段特殊演员的定义。
   protected String _specialActors;

   // 存储字段功能预告的定义。
   private String __functionPreview;

   // 字段功能预告的定义。
   protected String _functionPreview;

   // 存储字段皇榜任务的定义。
   private String __taskRoyal;

   // 字段皇榜任务的定义。
   protected String _taskRoyal;

   // 存储字段角色答题信息的定义。
   private String __roleAnswer;

   // 字段角色答题信息的定义。
   protected String _roleAnswer;

   // 存储字段新手引导模板编号链表的定义。
   private String __newerGuideTidList;

   // 字段新手引导模板编号链表的定义。
   protected String _newerGuideTidList;

   // 存储字段十天目标的定义。
   private String __tenDaysTarget;

   // 字段十天目标的定义。
   protected String _tenDaysTarget;

   // 存储字段神秘商店信息的定义。
   private String __mystery;

   // 字段神秘商店信息的定义。
   protected String _mystery;

   // 存储字段勋章的定义。
   private String __medal;

   // 字段勋章的定义。
   protected String _medal;

   // 存储字段勋章奖励状态的定义。
   private String __medalAwardStatus;

   // 字段勋章奖励状态的定义。
   protected String _medalAwardStatus;

   // 存储字段勋章记录数据的定义。
   private String __medalRecordData;

   // 字段勋章记录数据的定义。
   protected String _medalRecordData;

   // 存储字段buff集合的定义。
   private String __buffSet;

   // 字段buff集合的定义。
   protected String _buffSet;

   // 存储字段四圣血脉的定义。
   private String __fourGodBlood;

   // 字段四圣血脉的定义。
   protected String _fourGodBlood;

   // 存储字段使用道具限制的定义。
   private String __propLimit;

   // 字段使用道具限制的定义。
   protected String _propLimit;

   // 存储字段样式动作集合的定义。
   private String __styleActionSet;

   // 字段样式动作集合的定义。
   protected String _styleActionSet;

   // 存储字段目标的定义。
   private String __universeTarget;

   // 字段目标的定义。
   protected String _universeTarget;

   // 存储字段申请帮会列表的定义。
   private String __applyList;

   // 字段申请帮会列表的定义。
   protected String _applyList;

   // 存储字段消费信息的定义。
   private String __spendData;

   // 字段消费信息的定义。
   protected String _spendData;

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
   // <T>构造角色信息逻辑单元。</T>
   //============================================================
   public FGameRoleDataUnit(){
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
   // <T>判断副本的CD状况的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCopyCdChanged(){
      return !RString.equals(__copyCd, _copyCd);
   }

   //============================================================
   // <T>获得副本的CD状况的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String copyCd(){
      return _copyCd;
   }

   //============================================================
   // <T>设置副本的CD状况的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCopyCd(String value){
      _copyCd = value;
   }

   //============================================================
   // <T>判断周常数据存放的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isWeeklyDataChanged(){
      return !RString.equals(__weeklyData, _weeklyData);
   }

   //============================================================
   // <T>获得周常数据存放的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String weeklyData(){
      return _weeklyData;
   }

   //============================================================
   // <T>设置周常数据存放的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setWeeklyData(String value){
      _weeklyData = value;
   }

   //============================================================
   // <T>判断活动数据的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isActivityDataChanged(){
      return !RString.equals(__activityData, _activityData);
   }

   //============================================================
   // <T>获得活动数据的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String activityData(){
      return _activityData;
   }

   //============================================================
   // <T>设置活动数据的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setActivityData(String value){
      _activityData = value;
   }

   //============================================================
   // <T>判断九界Boss数据的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isNineCirlesBossDataChanged(){
      return !RString.equals(__nineCirlesBossData, _nineCirlesBossData);
   }

   //============================================================
   // <T>获得九界Boss数据的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String nineCirlesBossData(){
      return _nineCirlesBossData;
   }

   //============================================================
   // <T>设置九界Boss数据的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setNineCirlesBossData(String value){
      _nineCirlesBossData = value;
   }

   //============================================================
   // <T>判断九界数据的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isNineCirlesDataChanged(){
      return !RString.equals(__nineCirlesData, _nineCirlesData);
   }

   //============================================================
   // <T>获得九界数据的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String nineCirlesData(){
      return _nineCirlesData;
   }

   //============================================================
   // <T>设置九界数据的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setNineCirlesData(String value){
      _nineCirlesData = value;
   }

   //============================================================
   // <T>判断共血boss数据的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isShareHpBossDataChanged(){
      return !RString.equals(__shareHpBossData, _shareHpBossData);
   }

   //============================================================
   // <T>获得共血boss数据的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String shareHpBossData(){
      return _shareHpBossData;
   }

   //============================================================
   // <T>设置共血boss数据的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setShareHpBossData(String value){
      _shareHpBossData = value;
   }

   //============================================================
   // <T>判断特殊演员的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSpecialActorsChanged(){
      return !RString.equals(__specialActors, _specialActors);
   }

   //============================================================
   // <T>获得特殊演员的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String specialActors(){
      return _specialActors;
   }

   //============================================================
   // <T>设置特殊演员的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSpecialActors(String value){
      _specialActors = value;
   }

   //============================================================
   // <T>判断功能预告的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isFunctionPreviewChanged(){
      return !RString.equals(__functionPreview, _functionPreview);
   }

   //============================================================
   // <T>获得功能预告的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String functionPreview(){
      return _functionPreview;
   }

   //============================================================
   // <T>设置功能预告的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setFunctionPreview(String value){
      _functionPreview = value;
   }

   //============================================================
   // <T>判断皇榜任务的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTaskRoyalChanged(){
      return !RString.equals(__taskRoyal, _taskRoyal);
   }

   //============================================================
   // <T>获得皇榜任务的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String taskRoyal(){
      return _taskRoyal;
   }

   //============================================================
   // <T>设置皇榜任务的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTaskRoyal(String value){
      _taskRoyal = value;
   }

   //============================================================
   // <T>判断角色答题信息的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleAnswerChanged(){
      return !RString.equals(__roleAnswer, _roleAnswer);
   }

   //============================================================
   // <T>获得角色答题信息的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String roleAnswer(){
      return _roleAnswer;
   }

   //============================================================
   // <T>设置角色答题信息的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleAnswer(String value){
      _roleAnswer = value;
   }

   //============================================================
   // <T>判断新手引导模板编号链表的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isNewerGuideTidListChanged(){
      return !RString.equals(__newerGuideTidList, _newerGuideTidList);
   }

   //============================================================
   // <T>获得新手引导模板编号链表的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String newerGuideTidList(){
      return _newerGuideTidList;
   }

   //============================================================
   // <T>设置新手引导模板编号链表的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setNewerGuideTidList(String value){
      _newerGuideTidList = value;
   }

   //============================================================
   // <T>判断十天目标的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTenDaysTargetChanged(){
      return !RString.equals(__tenDaysTarget, _tenDaysTarget);
   }

   //============================================================
   // <T>获得十天目标的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String tenDaysTarget(){
      return _tenDaysTarget;
   }

   //============================================================
   // <T>设置十天目标的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTenDaysTarget(String value){
      _tenDaysTarget = value;
   }

   //============================================================
   // <T>判断神秘商店信息的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMysteryChanged(){
      return !RString.equals(__mystery, _mystery);
   }

   //============================================================
   // <T>获得神秘商店信息的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String mystery(){
      return _mystery;
   }

   //============================================================
   // <T>设置神秘商店信息的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMystery(String value){
      _mystery = value;
   }

   //============================================================
   // <T>判断勋章的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMedalChanged(){
      return !RString.equals(__medal, _medal);
   }

   //============================================================
   // <T>获得勋章的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String medal(){
      return _medal;
   }

   //============================================================
   // <T>设置勋章的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMedal(String value){
      _medal = value;
   }

   //============================================================
   // <T>判断勋章奖励状态的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMedalAwardStatusChanged(){
      return !RString.equals(__medalAwardStatus, _medalAwardStatus);
   }

   //============================================================
   // <T>获得勋章奖励状态的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String medalAwardStatus(){
      return _medalAwardStatus;
   }

   //============================================================
   // <T>设置勋章奖励状态的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMedalAwardStatus(String value){
      _medalAwardStatus = value;
   }

   //============================================================
   // <T>判断勋章记录数据的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMedalRecordDataChanged(){
      return !RString.equals(__medalRecordData, _medalRecordData);
   }

   //============================================================
   // <T>获得勋章记录数据的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String medalRecordData(){
      return _medalRecordData;
   }

   //============================================================
   // <T>设置勋章记录数据的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMedalRecordData(String value){
      _medalRecordData = value;
   }

   //============================================================
   // <T>判断buff集合的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isBuffSetChanged(){
      return !RString.equals(__buffSet, _buffSet);
   }

   //============================================================
   // <T>获得buff集合的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String buffSet(){
      return _buffSet;
   }

   //============================================================
   // <T>设置buff集合的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setBuffSet(String value){
      _buffSet = value;
   }

   //============================================================
   // <T>判断四圣血脉的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isFourGodBloodChanged(){
      return !RString.equals(__fourGodBlood, _fourGodBlood);
   }

   //============================================================
   // <T>获得四圣血脉的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String fourGodBlood(){
      return _fourGodBlood;
   }

   //============================================================
   // <T>设置四圣血脉的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setFourGodBlood(String value){
      _fourGodBlood = value;
   }

   //============================================================
   // <T>判断使用道具限制的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPropLimitChanged(){
      return !RString.equals(__propLimit, _propLimit);
   }

   //============================================================
   // <T>获得使用道具限制的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String propLimit(){
      return _propLimit;
   }

   //============================================================
   // <T>设置使用道具限制的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPropLimit(String value){
      _propLimit = value;
   }

   //============================================================
   // <T>判断样式动作集合的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isStyleActionSetChanged(){
      return !RString.equals(__styleActionSet, _styleActionSet);
   }

   //============================================================
   // <T>获得样式动作集合的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String styleActionSet(){
      return _styleActionSet;
   }

   //============================================================
   // <T>设置样式动作集合的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setStyleActionSet(String value){
      _styleActionSet = value;
   }

   //============================================================
   // <T>判断目标的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isUniverseTargetChanged(){
      return !RString.equals(__universeTarget, _universeTarget);
   }

   //============================================================
   // <T>获得目标的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String universeTarget(){
      return _universeTarget;
   }

   //============================================================
   // <T>设置目标的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setUniverseTarget(String value){
      _universeTarget = value;
   }

   //============================================================
   // <T>判断申请帮会列表的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isApplyListChanged(){
      return !RString.equals(__applyList, _applyList);
   }

   //============================================================
   // <T>获得申请帮会列表的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String applyList(){
      return _applyList;
   }

   //============================================================
   // <T>设置申请帮会列表的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setApplyList(String value){
      _applyList = value;
   }

   //============================================================
   // <T>判断消费信息的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSpendDataChanged(){
      return !RString.equals(__spendData, _spendData);
   }

   //============================================================
   // <T>获得消费信息的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String spendData(){
      return _spendData;
   }

   //============================================================
   // <T>设置消费信息的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSpendData(String value){
      _spendData = value;
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
      __roleId = RLong.parse(row.get("role_id"));
      _roleId = __roleId;
      __copyCd = row.get("copy_cd");
      _copyCd = __copyCd;
      __weeklyData = row.get("weekly_data");
      _weeklyData = __weeklyData;
      __activityData = row.get("activity_data");
      _activityData = __activityData;
      __nineCirlesBossData = row.get("nine_cirles_boss_data");
      _nineCirlesBossData = __nineCirlesBossData;
      __nineCirlesData = row.get("nine_cirles_data");
      _nineCirlesData = __nineCirlesData;
      __shareHpBossData = row.get("share_hp_boss_data");
      _shareHpBossData = __shareHpBossData;
      __specialActors = row.get("special_actors");
      _specialActors = __specialActors;
      __functionPreview = row.get("function_preview");
      _functionPreview = __functionPreview;
      __taskRoyal = row.get("task_royal");
      _taskRoyal = __taskRoyal;
      __roleAnswer = row.get("role_answer");
      _roleAnswer = __roleAnswer;
      __newerGuideTidList = row.get("newer_guide_tid_list");
      _newerGuideTidList = __newerGuideTidList;
      __tenDaysTarget = row.get("ten_days_target");
      _tenDaysTarget = __tenDaysTarget;
      __mystery = row.get("mystery");
      _mystery = __mystery;
      __medal = row.get("medal");
      _medal = __medal;
      __medalAwardStatus = row.get("medal_award_status");
      _medalAwardStatus = __medalAwardStatus;
      __medalRecordData = row.get("medal_record_data");
      _medalRecordData = __medalRecordData;
      __buffSet = row.get("buff_set");
      _buffSet = __buffSet;
      __fourGodBlood = row.get("four_god_blood");
      _fourGodBlood = __fourGodBlood;
      __propLimit = row.get("prop_limit");
      _propLimit = __propLimit;
      __styleActionSet = row.get("style_action_set");
      _styleActionSet = __styleActionSet;
      __universeTarget = row.get("universe_target");
      _universeTarget = __universeTarget;
      __applyList = row.get("apply_list");
      _applyList = __applyList;
      __spendData = row.get("spend_data");
      _spendData = __spendData;
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