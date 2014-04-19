package com.zqinet.logic.data.platform;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>通知逻辑单元。</T>
//============================================================
public class FPlatformNotifyUnit extends FLogicUnit
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

   // 存储字段编号的定义。
   private int __id;

   // 字段编号的定义。
   protected int _id;

   // 存储字段编码的定义。
   private String __code;

   // 字段编码的定义。
   protected String _code;

   // 存储字段名称的定义。
   private String __name;

   // 字段名称的定义。
   protected String _name;

   // 存储字段标签的定义。
   private String __label;

   // 字段标签的定义。
   protected String _label;

   // 存储字段类型编号的定义。
   private int __typeId;

   // 字段类型编号的定义。
   protected int _typeId;

   // 存储字段状态的定义。
   private int __statusCd;

   // 字段状态的定义。
   protected int _statusCd;

   // 存储字段优先级的定义。
   private int __proprityCd;

   // 字段优先级的定义。
   protected int _proprityCd;

   // 存储字段开始时间的定义。
   private TDateTime __beginDate = new TDateTime();

   // 字段开始时间的定义。
   protected TDateTime _beginDate = new TDateTime();

   // 存储字段结束时间的定义。
   private TDateTime __endDate = new TDateTime();

   // 字段结束时间的定义。
   protected TDateTime _endDate = new TDateTime();

   // 存储字段访问数据库时间的定义。
   private TDateTime __inviteStorageDate = new TDateTime();

   // 字段访问数据库时间的定义。
   protected TDateTime _inviteStorageDate = new TDateTime();

   // 存储字段间隔时间的定义。
   private int __intervalData;

   // 字段间隔时间的定义。
   protected int _intervalData;

   // 存储字段内容的定义。
   private String __content;

   // 字段内容的定义。
   protected String _content;

   // 存储字段备注的定义。
   private String __note;

   // 字段备注的定义。
   protected String _note;

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
   // <T>构造通知逻辑单元。</T>
   //============================================================
   public FPlatformNotifyUnit(){
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
   // <T>判断编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isIdChanged(){
      return __id != _id;
   }

   //============================================================
   // <T>获得编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int id(){
      return _id;
   }

   //============================================================
   // <T>设置编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setId(int value){
      _id = value;
   }

   //============================================================
   // <T>判断编码的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCodeChanged(){
      return !RString.equals(__code, _code);
   }

   //============================================================
   // <T>获得编码的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String code(){
      return _code;
   }

   //============================================================
   // <T>设置编码的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCode(String value){
      _code = value;
   }

   //============================================================
   // <T>判断名称的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isNameChanged(){
      return !RString.equals(__name, _name);
   }

   //============================================================
   // <T>获得名称的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String name(){
      return _name;
   }

   //============================================================
   // <T>设置名称的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setName(String value){
      _name = value;
   }

   //============================================================
   // <T>判断标签的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLabelChanged(){
      return !RString.equals(__label, _label);
   }

   //============================================================
   // <T>获得标签的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String label(){
      return _label;
   }

   //============================================================
   // <T>设置标签的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLabel(String value){
      _label = value;
   }

   //============================================================
   // <T>判断类型编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTypeIdChanged(){
      return __typeId != _typeId;
   }

   //============================================================
   // <T>获得类型编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int typeId(){
      return _typeId;
   }

   //============================================================
   // <T>设置类型编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTypeId(int value){
      _typeId = value;
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
   // <T>判断优先级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isProprityCdChanged(){
      return __proprityCd != _proprityCd;
   }

   //============================================================
   // <T>获得优先级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int proprityCd(){
      return _proprityCd;
   }

   //============================================================
   // <T>设置优先级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setProprityCd(int value){
      _proprityCd = value;
   }

   //============================================================
   // <T>判断开始时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isBeginDateChanged(){
      return !__beginDate.equals(_beginDate);
   }

   //============================================================
   // <T>获得开始时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime beginDate(){
      return _beginDate;
   }

   //============================================================
   // <T>设置开始时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setBeginDate(TDateTime value){
      _beginDate = value;
   }

   //============================================================
   // <T>判断结束时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isEndDateChanged(){
      return !__endDate.equals(_endDate);
   }

   //============================================================
   // <T>获得结束时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime endDate(){
      return _endDate;
   }

   //============================================================
   // <T>设置结束时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setEndDate(TDateTime value){
      _endDate = value;
   }

   //============================================================
   // <T>判断访问数据库时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isInviteStorageDateChanged(){
      return !__inviteStorageDate.equals(_inviteStorageDate);
   }

   //============================================================
   // <T>获得访问数据库时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime inviteStorageDate(){
      return _inviteStorageDate;
   }

   //============================================================
   // <T>设置访问数据库时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setInviteStorageDate(TDateTime value){
      _inviteStorageDate = value;
   }

   //============================================================
   // <T>判断间隔时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isIntervalDataChanged(){
      return __intervalData != _intervalData;
   }

   //============================================================
   // <T>获得间隔时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int intervalData(){
      return _intervalData;
   }

   //============================================================
   // <T>设置间隔时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setIntervalData(int value){
      _intervalData = value;
   }

   //============================================================
   // <T>判断内容的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isContentChanged(){
      return !RString.equals(__content, _content);
   }

   //============================================================
   // <T>获得内容的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String content(){
      return _content;
   }

   //============================================================
   // <T>设置内容的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setContent(String value){
      _content = value;
   }

   //============================================================
   // <T>判断备注的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isNoteChanged(){
      return !RString.equals(__note, _note);
   }

   //============================================================
   // <T>获得备注的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String note(){
      return _note;
   }

   //============================================================
   // <T>设置备注的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setNote(String value){
      _note = value;
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
      __id = row.getInteger("id");
      _id = __id;
      __code = row.get("code");
      _code = __code;
      __name = row.get("name");
      _name = __name;
      __label = row.get("label");
      _label = __label;
      __typeId = row.getInteger("type_id");
      _typeId = __typeId;
      __statusCd = row.getInteger("status_cd");
      _statusCd = __statusCd;
      __proprityCd = row.getInteger("proprity_cd");
      _proprityCd = __proprityCd;
      __beginDate.parse(row.get("begin_date"));
      _beginDate.assign(__beginDate);
      __endDate.parse(row.get("end_date"));
      _endDate.assign(__endDate);
      __inviteStorageDate.parse(row.get("invite_storage_date"));
      _inviteStorageDate.assign(__inviteStorageDate);
      __intervalData = row.getInteger("interval_data");
      _intervalData = __intervalData;
      __content = row.get("content");
      _content = __content;
      __note = row.get("note");
      _note = __note;
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