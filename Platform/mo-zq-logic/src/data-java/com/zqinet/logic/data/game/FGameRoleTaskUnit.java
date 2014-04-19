package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>角色任务信息逻辑单元。</T>
//============================================================
public class FGameRoleTaskUnit extends FLogicUnit
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

   // 存储字段角色编号的定义。
   private long __roleId;

   // 字段角色编号的定义。
   protected long _roleId;

   // 存储字段状态的定义。
   private int __statusCd;

   // 字段状态的定义。
   protected int _statusCd;

   // 存储字段标志的定义。
   private int __flags;

   // 字段标志的定义。
   protected int _flags;

   // 存储字段任务模板编号的定义。
   private int __taskTid;

   // 字段任务模板编号的定义。
   protected int _taskTid;

   // 存储字段部分模板编号的定义。
   private int __partTid;

   // 字段部分模板编号的定义。
   protected int _partTid;

   // 存储字段接取任务演员模板编号的定义。
   private int __beginActorTid;

   // 字段接取任务演员模板编号的定义。
   protected int _beginActorTid;

   // 存储字段完成任务演员模板编号的定义。
   private int __endActorTid;

   // 字段完成任务演员模板编号的定义。
   protected int _endActorTid;

   // 存储字段开始条件组模板编号的定义。
   private int __acceptLimitGroupTid;

   // 字段开始条件组模板编号的定义。
   protected int _acceptLimitGroupTid;

   // 存储字段开始奖励组模板编号的定义。
   private int __acceptRewardGroupTid;

   // 字段开始奖励组模板编号的定义。
   protected int _acceptRewardGroupTid;

   // 存储字段完成条件组模板编号的定义。
   private int __finishLimitGroupTid;

   // 字段完成条件组模板编号的定义。
   protected int _finishLimitGroupTid;

   // 存储字段完成奖励组模板编号的定义。
   private int __finishRewardGroupTid;

   // 字段完成奖励组模板编号的定义。
   protected int _finishRewardGroupTid;

   // 存储字段交付任务条件组模板编号的定义。
   private int __deliverLimitGroupTid;

   // 字段交付任务条件组模板编号的定义。
   protected int _deliverLimitGroupTid;

   // 存储字段交付任务奖励组编号的定义。
   private int __deliverRewardGroupTid;

   // 字段交付任务奖励组编号的定义。
   protected int _deliverRewardGroupTid;

   // 存储字段完成次数的定义。
   private int __finishCount;

   // 字段完成次数的定义。
   protected int _finishCount;

   // 存储字段内容打包的定义。
   private String __contentPack;

   // 字段内容打包的定义。
   protected String _contentPack;

   // 存储字段接任务时间的定义。
   private String __beginTaskTime;

   // 字段接任务时间的定义。
   protected String _beginTaskTime;

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
   // <T>构造角色任务信息逻辑单元。</T>
   //============================================================
   public FGameRoleTaskUnit(){
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
   // <T>判断角色编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleIdChanged(){
      return __roleId != _roleId;
   }

   //============================================================
   // <T>获得角色编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long roleId(){
      return _roleId;
   }

   //============================================================
   // <T>设置角色编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleId(long value){
      _roleId = value;
   }

   //============================================================
   // <T>判断状态的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isStatusCdChanged(){
      return __statusCd != _statusCd;
   }

   //============================================================
   // <T>获得状态的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int statusCd(){
      return _statusCd;
   }

   //============================================================
   // <T>设置状态的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setStatusCd(int value){
      _statusCd = value;
   }

   //============================================================
   // <T>判断标志的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isFlagsChanged(){
      return __flags != _flags;
   }

   //============================================================
   // <T>获得标志的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int flags(){
      return _flags;
   }

   //============================================================
   // <T>设置标志的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setFlags(int value){
      _flags = value;
   }

   //============================================================
   // <T>判断任务模板编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTaskTidChanged(){
      return __taskTid != _taskTid;
   }

   //============================================================
   // <T>获得任务模板编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int taskTid(){
      return _taskTid;
   }

   //============================================================
   // <T>设置任务模板编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTaskTid(int value){
      _taskTid = value;
   }

   //============================================================
   // <T>判断部分模板编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPartTidChanged(){
      return __partTid != _partTid;
   }

   //============================================================
   // <T>获得部分模板编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int partTid(){
      return _partTid;
   }

   //============================================================
   // <T>设置部分模板编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPartTid(int value){
      _partTid = value;
   }

   //============================================================
   // <T>判断接取任务演员模板编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isBeginActorTidChanged(){
      return __beginActorTid != _beginActorTid;
   }

   //============================================================
   // <T>获得接取任务演员模板编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int beginActorTid(){
      return _beginActorTid;
   }

   //============================================================
   // <T>设置接取任务演员模板编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setBeginActorTid(int value){
      _beginActorTid = value;
   }

   //============================================================
   // <T>判断完成任务演员模板编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isEndActorTidChanged(){
      return __endActorTid != _endActorTid;
   }

   //============================================================
   // <T>获得完成任务演员模板编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int endActorTid(){
      return _endActorTid;
   }

   //============================================================
   // <T>设置完成任务演员模板编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setEndActorTid(int value){
      _endActorTid = value;
   }

   //============================================================
   // <T>判断开始条件组模板编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAcceptLimitGroupTidChanged(){
      return __acceptLimitGroupTid != _acceptLimitGroupTid;
   }

   //============================================================
   // <T>获得开始条件组模板编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int acceptLimitGroupTid(){
      return _acceptLimitGroupTid;
   }

   //============================================================
   // <T>设置开始条件组模板编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAcceptLimitGroupTid(int value){
      _acceptLimitGroupTid = value;
   }

   //============================================================
   // <T>判断开始奖励组模板编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAcceptRewardGroupTidChanged(){
      return __acceptRewardGroupTid != _acceptRewardGroupTid;
   }

   //============================================================
   // <T>获得开始奖励组模板编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int acceptRewardGroupTid(){
      return _acceptRewardGroupTid;
   }

   //============================================================
   // <T>设置开始奖励组模板编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAcceptRewardGroupTid(int value){
      _acceptRewardGroupTid = value;
   }

   //============================================================
   // <T>判断完成条件组模板编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isFinishLimitGroupTidChanged(){
      return __finishLimitGroupTid != _finishLimitGroupTid;
   }

   //============================================================
   // <T>获得完成条件组模板编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int finishLimitGroupTid(){
      return _finishLimitGroupTid;
   }

   //============================================================
   // <T>设置完成条件组模板编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setFinishLimitGroupTid(int value){
      _finishLimitGroupTid = value;
   }

   //============================================================
   // <T>判断完成奖励组模板编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isFinishRewardGroupTidChanged(){
      return __finishRewardGroupTid != _finishRewardGroupTid;
   }

   //============================================================
   // <T>获得完成奖励组模板编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int finishRewardGroupTid(){
      return _finishRewardGroupTid;
   }

   //============================================================
   // <T>设置完成奖励组模板编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setFinishRewardGroupTid(int value){
      _finishRewardGroupTid = value;
   }

   //============================================================
   // <T>判断交付任务条件组模板编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isDeliverLimitGroupTidChanged(){
      return __deliverLimitGroupTid != _deliverLimitGroupTid;
   }

   //============================================================
   // <T>获得交付任务条件组模板编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int deliverLimitGroupTid(){
      return _deliverLimitGroupTid;
   }

   //============================================================
   // <T>设置交付任务条件组模板编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDeliverLimitGroupTid(int value){
      _deliverLimitGroupTid = value;
   }

   //============================================================
   // <T>判断交付任务奖励组编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isDeliverRewardGroupTidChanged(){
      return __deliverRewardGroupTid != _deliverRewardGroupTid;
   }

   //============================================================
   // <T>获得交付任务奖励组编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int deliverRewardGroupTid(){
      return _deliverRewardGroupTid;
   }

   //============================================================
   // <T>设置交付任务奖励组编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDeliverRewardGroupTid(int value){
      _deliverRewardGroupTid = value;
   }

   //============================================================
   // <T>判断完成次数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isFinishCountChanged(){
      return __finishCount != _finishCount;
   }

   //============================================================
   // <T>获得完成次数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int finishCount(){
      return _finishCount;
   }

   //============================================================
   // <T>设置完成次数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setFinishCount(int value){
      _finishCount = value;
   }

   //============================================================
   // <T>判断内容打包的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isContentPackChanged(){
      return !RString.equals(__contentPack, _contentPack);
   }

   //============================================================
   // <T>获得内容打包的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String contentPack(){
      return _contentPack;
   }

   //============================================================
   // <T>设置内容打包的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setContentPack(String value){
      _contentPack = value;
   }

   //============================================================
   // <T>判断接任务时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isBeginTaskTimeChanged(){
      return !RString.equals(__beginTaskTime, _beginTaskTime);
   }

   //============================================================
   // <T>获得接任务时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String beginTaskTime(){
      return _beginTaskTime;
   }

   //============================================================
   // <T>设置接任务时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setBeginTaskTime(String value){
      _beginTaskTime = value;
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
      __statusCd = row.getInteger("status_cd");
      _statusCd = __statusCd;
      __flags = row.getInteger("flags");
      _flags = __flags;
      __taskTid = row.getInteger("task_tid");
      _taskTid = __taskTid;
      __partTid = row.getInteger("part_tid");
      _partTid = __partTid;
      __beginActorTid = row.getInteger("begin_actor_tid");
      _beginActorTid = __beginActorTid;
      __endActorTid = row.getInteger("end_actor_tid");
      _endActorTid = __endActorTid;
      __acceptLimitGroupTid = row.getInteger("accept_limit_group_tid");
      _acceptLimitGroupTid = __acceptLimitGroupTid;
      __acceptRewardGroupTid = row.getInteger("accept_reward_group_tid");
      _acceptRewardGroupTid = __acceptRewardGroupTid;
      __finishLimitGroupTid = row.getInteger("finish_limit_group_tid");
      _finishLimitGroupTid = __finishLimitGroupTid;
      __finishRewardGroupTid = row.getInteger("finish_reward_group_tid");
      _finishRewardGroupTid = __finishRewardGroupTid;
      __deliverLimitGroupTid = row.getInteger("deliver_limit_group_tid");
      _deliverLimitGroupTid = __deliverLimitGroupTid;
      __deliverRewardGroupTid = row.getInteger("deliver_reward_group_tid");
      _deliverRewardGroupTid = __deliverRewardGroupTid;
      __finishCount = row.getInteger("finish_count");
      _finishCount = __finishCount;
      __contentPack = row.get("content_pack");
      _contentPack = __contentPack;
      __beginTaskTime = row.get("begin_task_time");
      _beginTaskTime = __beginTaskTime;
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