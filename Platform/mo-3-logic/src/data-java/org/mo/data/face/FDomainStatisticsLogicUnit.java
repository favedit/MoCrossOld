package org.mo.data.face;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>主域统计逻辑逻辑单元。</T>
//============================================================
public class FDomainStatisticsLogicUnit extends FLogicUnit
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

   // 存储字段统计编号的定义。
   private long __statisticsId;

   // 字段统计编号的定义。
   protected long _statisticsId;

   // 存储字段代码的定义。
   private String __code;

   // 字段代码的定义。
   protected String _code;

   // 存储字段组名称的定义。
   private String __statisticsName;

   // 字段组名称的定义。
   protected String _statisticsName;

   // 存储字段名称的定义。
   private String __name;

   // 字段名称的定义。
   protected String _name;

   // 存储字段标签的定义。
   private String __label;

   // 字段标签的定义。
   protected String _label;

   // 存储字段调用名称的定义。
   private String __invokeName;

   // 字段调用名称的定义。
   protected String _invokeName;

   // 存储字段处理时间的定义。
   private TDateTime __processDate = new TDateTime();

   // 字段处理时间的定义。
   protected TDateTime _processDate = new TDateTime();

   // 存储字段处理条数的定义。
   private int __processCount;

   // 字段处理条数的定义。
   protected int _processCount;

   // 存储字段处理总数的定义。
   private int __processTotal;

   // 字段处理总数的定义。
   protected int _processTotal;

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
   // <T>构造主域统计逻辑逻辑单元。</T>
   //============================================================
   public FDomainStatisticsLogicUnit(){
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
   // <T>判断统计编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isStatisticsIdChanged(){
      return __statisticsId != _statisticsId;
   }

   //============================================================
   // <T>获得统计编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long statisticsId(){
      return _statisticsId;
   }

   //============================================================
   // <T>设置统计编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setStatisticsId(long value){
      _statisticsId = value;
   }

   //============================================================
   // <T>判断代码的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCodeChanged(){
      return !RString.equals(__code, _code);
   }

   //============================================================
   // <T>获得代码的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String code(){
      return _code;
   }

   //============================================================
   // <T>设置代码的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCode(String value){
      _code = value;
   }

   //============================================================
   // <T>判断组名称的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isStatisticsNameChanged(){
      return !RString.equals(__statisticsName, _statisticsName);
   }

   //============================================================
   // <T>获得组名称的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String statisticsName(){
      return _statisticsName;
   }

   //============================================================
   // <T>设置组名称的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setStatisticsName(String value){
      _statisticsName = value;
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
   // <T>判断调用名称的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isInvokeNameChanged(){
      return !RString.equals(__invokeName, _invokeName);
   }

   //============================================================
   // <T>获得调用名称的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String invokeName(){
      return _invokeName;
   }

   //============================================================
   // <T>设置调用名称的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setInvokeName(String value){
      _invokeName = value;
   }

   //============================================================
   // <T>判断处理时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isProcessDateChanged(){
      return !__processDate.equals(_processDate);
   }

   //============================================================
   // <T>获得处理时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime processDate(){
      return _processDate;
   }

   //============================================================
   // <T>设置处理时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setProcessDate(TDateTime value){
      _processDate = value;
   }

   //============================================================
   // <T>判断处理条数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isProcessCountChanged(){
      return __processCount != _processCount;
   }

   //============================================================
   // <T>获得处理条数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int processCount(){
      return _processCount;
   }

   //============================================================
   // <T>设置处理条数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setProcessCount(int value){
      _processCount = value;
   }

   //============================================================
   // <T>判断处理总数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isProcessTotalChanged(){
      return __processTotal != _processTotal;
   }

   //============================================================
   // <T>获得处理总数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int processTotal(){
      return _processTotal;
   }

   //============================================================
   // <T>设置处理总数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setProcessTotal(int value){
      _processTotal = value;
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
      __statisticsId = RLong.parse(row.get("statistics_id"));
      _statisticsId = __statisticsId;
      __code = row.get("code");
      _code = __code;
      __statisticsName = row.get("statistics_name");
      _statisticsName = __statisticsName;
      __name = row.get("name");
      _name = __name;
      __label = row.get("label");
      _label = __label;
      __invokeName = row.get("invoke_name");
      _invokeName = __invokeName;
      __processDate.parse(row.get("process_date"));
      _processDate.assign(__processDate);
      __processCount = row.getInteger("process_count");
      _processCount = __processCount;
      __processTotal = row.getInteger("process_total");
      _processTotal = __processTotal;
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