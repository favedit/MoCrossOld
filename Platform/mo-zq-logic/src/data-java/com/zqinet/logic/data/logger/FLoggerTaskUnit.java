package com.zqinet.logic.data.logger;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>任务日志逻辑单元。</T>
//============================================================
public class FLoggerTaskUnit extends FLogicUnit
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

   // 存储字段角色所在的地图ID的定义。
   private int __mapId;

   // 字段角色所在的地图ID的定义。
   protected int _mapId;

   // 存储字段职业ID的定义。
   private int __metierTid;

   // 字段职业ID的定义。
   protected int _metierTid;

   // 存储字段当前等级的定义。
   private int __roleLevel;

   // 字段当前等级的定义。
   protected int _roleLevel;

   // 存储字段任务ID的定义。
   private int __taskTid;

   // 字段任务ID的定义。
   protected int _taskTid;

   // 存储字段操作类型的定义。
   private int __opType;

   // 字段操作类型的定义。
   protected int _opType;

   // 存储字段累计完成任务的个数的定义。
   private int __completeNum;

   // 字段累计完成任务的个数的定义。
   protected int _completeNum;

   // 存储字段参数1的定义。
   private String __param1;

   // 字段参数1的定义。
   protected String _param1;

   // 存储字段参数2的定义。
   private String __param2;

   // 字段参数2的定义。
   protected String _param2;

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
   // <T>构造任务日志逻辑单元。</T>
   //============================================================
   public FLoggerTaskUnit(){
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
   // <T>判断角色所在的地图ID的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMapIdChanged(){
      return __mapId != _mapId;
   }

   //============================================================
   // <T>获得角色所在的地图ID的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int mapId(){
      return _mapId;
   }

   //============================================================
   // <T>设置角色所在的地图ID的数据内容。</T>
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
   // <T>判断当前等级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleLevelChanged(){
      return __roleLevel != _roleLevel;
   }

   //============================================================
   // <T>获得当前等级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleLevel(){
      return _roleLevel;
   }

   //============================================================
   // <T>设置当前等级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleLevel(int value){
      _roleLevel = value;
   }

   //============================================================
   // <T>判断任务ID的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTaskTidChanged(){
      return __taskTid != _taskTid;
   }

   //============================================================
   // <T>获得任务ID的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int taskTid(){
      return _taskTid;
   }

   //============================================================
   // <T>设置任务ID的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTaskTid(int value){
      _taskTid = value;
   }

   //============================================================
   // <T>判断操作类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isOpTypeChanged(){
      return __opType != _opType;
   }

   //============================================================
   // <T>获得操作类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int opType(){
      return _opType;
   }

   //============================================================
   // <T>设置操作类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setOpType(int value){
      _opType = value;
   }

   //============================================================
   // <T>判断累计完成任务的个数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCompleteNumChanged(){
      return __completeNum != _completeNum;
   }

   //============================================================
   // <T>获得累计完成任务的个数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int completeNum(){
      return _completeNum;
   }

   //============================================================
   // <T>设置累计完成任务的个数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCompleteNum(int value){
      _completeNum = value;
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
      __mapId = row.getInteger("map_id");
      _mapId = __mapId;
      __metierTid = row.getInteger("metier_tid");
      _metierTid = __metierTid;
      __roleLevel = row.getInteger("role_level");
      _roleLevel = __roleLevel;
      __taskTid = row.getInteger("task_tid");
      _taskTid = __taskTid;
      __opType = row.getInteger("op_type");
      _opType = __opType;
      __completeNum = row.getInteger("complete_num");
      _completeNum = __completeNum;
      __param1 = row.get("param1");
      _param1 = __param1;
      __param2 = row.get("param2");
      _param2 = __param2;
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