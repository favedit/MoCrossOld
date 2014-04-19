package org.mo.logic.project.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.logic.project.common.XObjectFace;

//============================================================
// <T>测试用例对象的XML节点基类。</T>
//============================================================
public abstract class XBaseTestCase
      extends FXmlObject
      implements
         XObjectFace
{
   // 名称定义
   public static final String NAME = "TestCase";

   //============================================================
   // <T>获得名称定义。</T>
   //
   // @return 名称定义
   //============================================================
   public String name(){
      return NAME;
   }

   //============================================================
   // <T>判断是否指定名称。</T>
   //
   // @param name 名称
   // @return 是否指定
   //============================================================
   public static boolean isName(String name){
      return NAME.equals(name);
   }

   //============================================================
   // <T>判断是否指定实例。</T>
   //
   // @param xobject 对象
   // @return 是否指定
   //============================================================
   public static boolean isInstance(IXmlObject xobject){
      return NAME.equals(xobject.name());
   }

   // 名称的名称定义
   public static final String PTY_NAME = "name";

   // 标签的名称定义
   public static final String PTY_LABEL = "label";

   // 注释的名称定义
   public static final String PTY_NOTE = "note";

   // 类型的名称定义
   public static final String PTY__TYPE = "_type";

   // 有效性的名称定义
   public static final String PTY_IS_VALID = "is_valid";

   // 代码编号的名称定义
   public static final String PTY_CODE = "code";

   // 状态类型的名称定义
   public static final String PTY_STATUS_CD = "status_cd";

   // 重要度的名称定义
   public static final String PTY_IMPORTANT_CD = "important_cd";

   // 优先级的名称定义
   public static final String PTY_PRIORITY_CD = "priority_cd";

   // 困难度的名称定义
   public static final String PTY_DIFFICULT_CD = "difficult_cd";

   // 测试描述的名称定义
   public static final String PTY_CASE_NOTE = "case_note";

   // 输入描述的名称定义
   public static final String PTY_INPUT_NOTE = "input_note";

   // 输入描述的名称定义
   public static final String PTY_OUTPUT_NOTE = "output_note";

   // 结果类型的名称定义
   public static final String PTY_RESULT_CD = "result_cd";

   // 结果描述的名称定义
   public static final String PTY_RESULT_NOTE = "result_note";

   // 失败类型的名称定义
   public static final String PTY_FAILURE_CD = "failure_cd";

   // 失败描述的名称定义
   public static final String PTY_FAILURE_NOTE = "failure_note";

   // 原因类型的名称定义
   public static final String PTY_REASON_CD = "reason_cd";

   // 原因描述的名称定义
   public static final String PTY_REASON_NOTE = "reason_note";

   // 创建人名称的名称定义
   public static final String PTY_CREATE_USER_NAME = "create_user_name";

   // 测试人名称的名称定义
   public static final String PTY_TEST_USER_NAME = "test_user_name";

   // 测试时间的名称定义
   public static final String PTY_TEST_DATE = "test_date";

   // 确认人的名称定义
   public static final String PTY_CONFIRM_USER_NAME = "confirm_user_name";

   // 确认日期的名称定义
   public static final String PTY_CONFIRM_DATE = "confirm_date";

   // 名称的定义
   @AName("name")
   protected String _name;

   // 标签的定义
   @AName("label")
   protected FMultiString _label = new FMultiString();

   // 注释的定义
   @AName("note")
   protected FMultiString _note = new FMultiString();

   // 类型的定义
   @AName("_type")
   protected String __type;

   // 有效性的定义
   @AName("is_valid")
   protected String _isValid;

   // 代码编号的定义
   @AName("code")
   protected String _code;

   // 状态类型的定义
   @AName("status_cd")
   protected String _statusCd;

   // 重要度的定义
   @AName("important_cd")
   protected String _importantCd;

   // 优先级的定义
   @AName("priority_cd")
   protected String _priorityCd;

   // 困难度的定义
   @AName("difficult_cd")
   protected String _difficultCd;

   // 测试描述的定义
   @AName("case_note")
   protected String _caseNote;

   // 输入描述的定义
   @AName("input_note")
   protected String _inputNote;

   // 输入描述的定义
   @AName("output_note")
   protected String _outputNote;

   // 结果类型的定义
   @AName("result_cd")
   protected String _resultCd;

   // 结果描述的定义
   @AName("result_note")
   protected String _resultNote;

   // 失败类型的定义
   @AName("failure_cd")
   protected String _failureCd;

   // 失败描述的定义
   @AName("failure_note")
   protected String _failureNote;

   // 原因类型的定义
   @AName("reason_cd")
   protected String _reasonCd;

   // 原因描述的定义
   @AName("reason_note")
   protected String _reasonNote;

   // 创建人名称的定义
   @AName("create_user_name")
   protected String _createUserName;

   // 测试人名称的定义
   @AName("test_user_name")
   protected String _testUserName;

   // 测试时间的定义
   @AName("test_date")
   protected String _testDate;

   // 确认人的定义
   @AName("confirm_user_name")
   protected String _confirmUserName;

   // 确认日期的定义
   @AName("confirm_date")
   protected String _confirmDate;

   //============================================================
   // <T>获得名称的内容。</T>
   //
   // @return 名称
   //============================================================
   public String getName(){
      return _name;
   }

   //============================================================
   // <T>设置名称的内容。</T>
   //
   // @param value 名称
   //============================================================
   public void setName(String value){
      _name = value;
   }

   //============================================================
   // <T>获得标签的内容。</T>
   //
   // @return 标签
   //============================================================
   public String getLabel(){
      return _label.get();
   }

   //============================================================
   // <T>设置标签的内容。</T>
   //
   // @param value 标签
   //============================================================
   public void setLabel(String value){
      _label.set(value);
   }

   //============================================================
   // <T>获得注释的内容。</T>
   //
   // @return 注释
   //============================================================
   public String getNote(){
      return _note.get();
   }

   //============================================================
   // <T>设置注释的内容。</T>
   //
   // @param value 注释
   //============================================================
   public void setNote(String value){
      _note.set(value);
   }

   //============================================================
   // <T>获得类型的内容。</T>
   //
   // @return 类型
   //============================================================
   public String get_type(){
      return __type;
   }

   //============================================================
   // <T>设置类型的内容。</T>
   //
   // @param value 类型
   //============================================================
   public void set_type(String value){
      __type = value;
   }

   //============================================================
   // <T>获得有效性的内容。</T>
   //
   // @return 有效性
   //============================================================
   public String getIsValid(){
      return _isValid;
   }

   //============================================================
   // <T>设置有效性的内容。</T>
   //
   // @param value 有效性
   //============================================================
   public void setIsValid(String value){
      _isValid = value;
   }

   //============================================================
   // <T>获得代码编号的内容。</T>
   //
   // @return 代码编号
   //============================================================
   public String getCode(){
      return _code;
   }

   //============================================================
   // <T>设置代码编号的内容。</T>
   //
   // @param value 代码编号
   //============================================================
   public void setCode(String value){
      _code = value;
   }

   //============================================================
   // <T>获得状态类型的内容。</T>
   //
   // @return 状态类型
   //============================================================
   public String getStatusCd(){
      return _statusCd;
   }

   //============================================================
   // <T>设置状态类型的内容。</T>
   //
   // @param value 状态类型
   //============================================================
   public void setStatusCd(String value){
      _statusCd = value;
   }

   //============================================================
   // <T>获得重要度的内容。</T>
   //
   // @return 重要度
   //============================================================
   public String getImportantCd(){
      return _importantCd;
   }

   //============================================================
   // <T>设置重要度的内容。</T>
   //
   // @param value 重要度
   //============================================================
   public void setImportantCd(String value){
      _importantCd = value;
   }

   //============================================================
   // <T>获得优先级的内容。</T>
   //
   // @return 优先级
   //============================================================
   public String getPriorityCd(){
      return _priorityCd;
   }

   //============================================================
   // <T>设置优先级的内容。</T>
   //
   // @param value 优先级
   //============================================================
   public void setPriorityCd(String value){
      _priorityCd = value;
   }

   //============================================================
   // <T>获得困难度的内容。</T>
   //
   // @return 困难度
   //============================================================
   public String getDifficultCd(){
      return _difficultCd;
   }

   //============================================================
   // <T>设置困难度的内容。</T>
   //
   // @param value 困难度
   //============================================================
   public void setDifficultCd(String value){
      _difficultCd = value;
   }

   //============================================================
   // <T>获得测试描述的内容。</T>
   //
   // @return 测试描述
   //============================================================
   public String getCaseNote(){
      return _caseNote;
   }

   //============================================================
   // <T>设置测试描述的内容。</T>
   //
   // @param value 测试描述
   //============================================================
   public void setCaseNote(String value){
      _caseNote = value;
   }

   //============================================================
   // <T>获得输入描述的内容。</T>
   //
   // @return 输入描述
   //============================================================
   public String getInputNote(){
      return _inputNote;
   }

   //============================================================
   // <T>设置输入描述的内容。</T>
   //
   // @param value 输入描述
   //============================================================
   public void setInputNote(String value){
      _inputNote = value;
   }

   //============================================================
   // <T>获得输入描述的内容。</T>
   //
   // @return 输入描述
   //============================================================
   public String getOutputNote(){
      return _outputNote;
   }

   //============================================================
   // <T>设置输入描述的内容。</T>
   //
   // @param value 输入描述
   //============================================================
   public void setOutputNote(String value){
      _outputNote = value;
   }

   //============================================================
   // <T>获得结果类型的内容。</T>
   //
   // @return 结果类型
   //============================================================
   public String getResultCd(){
      return _resultCd;
   }

   //============================================================
   // <T>设置结果类型的内容。</T>
   //
   // @param value 结果类型
   //============================================================
   public void setResultCd(String value){
      _resultCd = value;
   }

   //============================================================
   // <T>获得结果描述的内容。</T>
   //
   // @return 结果描述
   //============================================================
   public String getResultNote(){
      return _resultNote;
   }

   //============================================================
   // <T>设置结果描述的内容。</T>
   //
   // @param value 结果描述
   //============================================================
   public void setResultNote(String value){
      _resultNote = value;
   }

   //============================================================
   // <T>获得失败类型的内容。</T>
   //
   // @return 失败类型
   //============================================================
   public String getFailureCd(){
      return _failureCd;
   }

   //============================================================
   // <T>设置失败类型的内容。</T>
   //
   // @param value 失败类型
   //============================================================
   public void setFailureCd(String value){
      _failureCd = value;
   }

   //============================================================
   // <T>获得失败描述的内容。</T>
   //
   // @return 失败描述
   //============================================================
   public String getFailureNote(){
      return _failureNote;
   }

   //============================================================
   // <T>设置失败描述的内容。</T>
   //
   // @param value 失败描述
   //============================================================
   public void setFailureNote(String value){
      _failureNote = value;
   }

   //============================================================
   // <T>获得原因类型的内容。</T>
   //
   // @return 原因类型
   //============================================================
   public String getReasonCd(){
      return _reasonCd;
   }

   //============================================================
   // <T>设置原因类型的内容。</T>
   //
   // @param value 原因类型
   //============================================================
   public void setReasonCd(String value){
      _reasonCd = value;
   }

   //============================================================
   // <T>获得原因描述的内容。</T>
   //
   // @return 原因描述
   //============================================================
   public String getReasonNote(){
      return _reasonNote;
   }

   //============================================================
   // <T>设置原因描述的内容。</T>
   //
   // @param value 原因描述
   //============================================================
   public void setReasonNote(String value){
      _reasonNote = value;
   }

   //============================================================
   // <T>获得创建人名称的内容。</T>
   //
   // @return 创建人名称
   //============================================================
   public String getCreateUserName(){
      return _createUserName;
   }

   //============================================================
   // <T>设置创建人名称的内容。</T>
   //
   // @param value 创建人名称
   //============================================================
   public void setCreateUserName(String value){
      _createUserName = value;
   }

   //============================================================
   // <T>获得测试人名称的内容。</T>
   //
   // @return 测试人名称
   //============================================================
   public String getTestUserName(){
      return _testUserName;
   }

   //============================================================
   // <T>设置测试人名称的内容。</T>
   //
   // @param value 测试人名称
   //============================================================
   public void setTestUserName(String value){
      _testUserName = value;
   }

   //============================================================
   // <T>获得测试时间的内容。</T>
   //
   // @return 测试时间
   //============================================================
   public String getTestDate(){
      return _testDate;
   }

   //============================================================
   // <T>设置测试时间的内容。</T>
   //
   // @param value 测试时间
   //============================================================
   public void setTestDate(String value){
      _testDate = value;
   }

   //============================================================
   // <T>获得确认人的内容。</T>
   //
   // @return 确认人
   //============================================================
   public String getConfirmUserName(){
      return _confirmUserName;
   }

   //============================================================
   // <T>设置确认人的内容。</T>
   //
   // @param value 确认人
   //============================================================
   public void setConfirmUserName(String value){
      _confirmUserName = value;
   }

   //============================================================
   // <T>获得确认日期的内容。</T>
   //
   // @return 确认日期
   //============================================================
   public String getConfirmDate(){
      return _confirmDate;
   }

   //============================================================
   // <T>设置确认日期的内容。</T>
   //
   // @param value 确认日期
   //============================================================
   public void setConfirmDate(String value){
      _confirmDate = value;
   }

   //============================================================
   // <T>内部获得内容置信息。</T>
   //
   // @param name 名称
   // @return 内容
   //============================================================
   public String innerGet(String name){
      if(RString.isEmpty(name)){
         return null;
      }else if(PTY_NAME.equalsIgnoreCase(name)){
         return getName();
      }else if(PTY_LABEL.equalsIgnoreCase(name)){
         return getLabel();
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         return getNote();
      }else if(PTY__TYPE.equalsIgnoreCase(name)){
         return get_type();
      }else if(PTY_IS_VALID.equalsIgnoreCase(name)){
         return getIsValid();
      }else if(PTY_CODE.equalsIgnoreCase(name)){
         return getCode();
      }else if(PTY_STATUS_CD.equalsIgnoreCase(name)){
         return getStatusCd();
      }else if(PTY_IMPORTANT_CD.equalsIgnoreCase(name)){
         return getImportantCd();
      }else if(PTY_PRIORITY_CD.equalsIgnoreCase(name)){
         return getPriorityCd();
      }else if(PTY_DIFFICULT_CD.equalsIgnoreCase(name)){
         return getDifficultCd();
      }else if(PTY_CASE_NOTE.equalsIgnoreCase(name)){
         return getCaseNote();
      }else if(PTY_INPUT_NOTE.equalsIgnoreCase(name)){
         return getInputNote();
      }else if(PTY_OUTPUT_NOTE.equalsIgnoreCase(name)){
         return getOutputNote();
      }else if(PTY_RESULT_CD.equalsIgnoreCase(name)){
         return getResultCd();
      }else if(PTY_RESULT_NOTE.equalsIgnoreCase(name)){
         return getResultNote();
      }else if(PTY_FAILURE_CD.equalsIgnoreCase(name)){
         return getFailureCd();
      }else if(PTY_FAILURE_NOTE.equalsIgnoreCase(name)){
         return getFailureNote();
      }else if(PTY_REASON_CD.equalsIgnoreCase(name)){
         return getReasonCd();
      }else if(PTY_REASON_NOTE.equalsIgnoreCase(name)){
         return getReasonNote();
      }else if(PTY_CREATE_USER_NAME.equalsIgnoreCase(name)){
         return getCreateUserName();
      }else if(PTY_TEST_USER_NAME.equalsIgnoreCase(name)){
         return getTestUserName();
      }else if(PTY_TEST_DATE.equalsIgnoreCase(name)){
         return getTestDate();
      }else if(PTY_CONFIRM_USER_NAME.equalsIgnoreCase(name)){
         return getConfirmUserName();
      }else if(PTY_CONFIRM_DATE.equalsIgnoreCase(name)){
         return getConfirmDate();
      }
      return null;
   }

   //============================================================
   // <T>内部设置内容置信息。</T>
   //
   // @param name 名称
   // @param value 内容
   //============================================================
   public void innerSet(String name, String value){
      if(RString.isEmpty(name)){
         return;
      }else if(PTY_NAME.equalsIgnoreCase(name)){
         setName(value);
      }else if(PTY_LABEL.equalsIgnoreCase(name)){
         setLabel(value);
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         setNote(value);
      }else if(PTY__TYPE.equalsIgnoreCase(name)){
         set_type(value);
      }else if(PTY_IS_VALID.equalsIgnoreCase(name)){
         setIsValid(value);
      }else if(PTY_CODE.equalsIgnoreCase(name)){
         setCode(value);
      }else if(PTY_STATUS_CD.equalsIgnoreCase(name)){
         setStatusCd(value);
      }else if(PTY_IMPORTANT_CD.equalsIgnoreCase(name)){
         setImportantCd(value);
      }else if(PTY_PRIORITY_CD.equalsIgnoreCase(name)){
         setPriorityCd(value);
      }else if(PTY_DIFFICULT_CD.equalsIgnoreCase(name)){
         setDifficultCd(value);
      }else if(PTY_CASE_NOTE.equalsIgnoreCase(name)){
         setCaseNote(value);
      }else if(PTY_INPUT_NOTE.equalsIgnoreCase(name)){
         setInputNote(value);
      }else if(PTY_OUTPUT_NOTE.equalsIgnoreCase(name)){
         setOutputNote(value);
      }else if(PTY_RESULT_CD.equalsIgnoreCase(name)){
         setResultCd(value);
      }else if(PTY_RESULT_NOTE.equalsIgnoreCase(name)){
         setResultNote(value);
      }else if(PTY_FAILURE_CD.equalsIgnoreCase(name)){
         setFailureCd(value);
      }else if(PTY_FAILURE_NOTE.equalsIgnoreCase(name)){
         setFailureNote(value);
      }else if(PTY_REASON_CD.equalsIgnoreCase(name)){
         setReasonCd(value);
      }else if(PTY_REASON_NOTE.equalsIgnoreCase(name)){
         setReasonNote(value);
      }else if(PTY_CREATE_USER_NAME.equalsIgnoreCase(name)){
         setCreateUserName(value);
      }else if(PTY_TEST_USER_NAME.equalsIgnoreCase(name)){
         setTestUserName(value);
      }else if(PTY_TEST_DATE.equalsIgnoreCase(name)){
         setTestDate(value);
      }else if(PTY_CONFIRM_USER_NAME.equalsIgnoreCase(name)){
         setConfirmUserName(value);
      }else if(PTY_CONFIRM_DATE.equalsIgnoreCase(name)){
         setConfirmDate(value);
      }
   }

   //============================================================
   // <T>加载设置信息。</T>
   //
   // @param config 设置信息
   // @param type 类型
   //============================================================
   public void loadConfig(FXmlNode config, EXmlConfig type){
      super.loadConfig(config, type);
      if(EXmlConfig.Full == type){
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("label")){
            _label.unpack(config.get(PTY_LABEL));
         }
         if(config.contains("note")){
            _note.unpack(config.get(PTY_NOTE));
         }
         if(config.contains("_type")){
            set_type(config.get(PTY__TYPE));
         }
         if(config.contains("is_valid")){
            setIsValid(config.get(PTY_IS_VALID));
         }
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
         if(config.contains("status_cd")){
            setStatusCd(config.get(PTY_STATUS_CD));
         }
         if(config.contains("important_cd")){
            setImportantCd(config.get(PTY_IMPORTANT_CD));
         }
         if(config.contains("priority_cd")){
            setPriorityCd(config.get(PTY_PRIORITY_CD));
         }
         if(config.contains("difficult_cd")){
            setDifficultCd(config.get(PTY_DIFFICULT_CD));
         }
         if(config.contains("case_note")){
            setCaseNote(config.get(PTY_CASE_NOTE));
         }
         if(config.contains("input_note")){
            setInputNote(config.get(PTY_INPUT_NOTE));
         }
         if(config.contains("output_note")){
            setOutputNote(config.get(PTY_OUTPUT_NOTE));
         }
         if(config.contains("result_cd")){
            setResultCd(config.get(PTY_RESULT_CD));
         }
         if(config.contains("result_note")){
            setResultNote(config.get(PTY_RESULT_NOTE));
         }
         if(config.contains("failure_cd")){
            setFailureCd(config.get(PTY_FAILURE_CD));
         }
         if(config.contains("failure_note")){
            setFailureNote(config.get(PTY_FAILURE_NOTE));
         }
         if(config.contains("reason_cd")){
            setReasonCd(config.get(PTY_REASON_CD));
         }
         if(config.contains("reason_note")){
            setReasonNote(config.get(PTY_REASON_NOTE));
         }
         if(config.contains("create_user_name")){
            setCreateUserName(config.get(PTY_CREATE_USER_NAME));
         }
         if(config.contains("test_user_name")){
            setTestUserName(config.get(PTY_TEST_USER_NAME));
         }
         if(config.contains("test_date")){
            setTestDate(config.get(PTY_TEST_DATE));
         }
         if(config.contains("confirm_user_name")){
            setConfirmUserName(config.get(PTY_CONFIRM_USER_NAME));
         }
         if(config.contains("confirm_date")){
            setConfirmDate(config.get(PTY_CONFIRM_DATE));
         }
      }else if(EXmlConfig.Simple == type){
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("label")){
            setLabel(config.get(PTY_LABEL));
         }
         if(config.contains("note")){
            setNote(config.get(PTY_NOTE));
         }
         if(config.contains("_type")){
            set_type(config.get(PTY__TYPE));
         }
         if(config.contains("is_valid")){
            setIsValid(config.get(PTY_IS_VALID));
         }
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
         if(config.contains("status_cd")){
            setStatusCd(config.get(PTY_STATUS_CD));
         }
         if(config.contains("important_cd")){
            setImportantCd(config.get(PTY_IMPORTANT_CD));
         }
         if(config.contains("priority_cd")){
            setPriorityCd(config.get(PTY_PRIORITY_CD));
         }
         if(config.contains("difficult_cd")){
            setDifficultCd(config.get(PTY_DIFFICULT_CD));
         }
         if(config.contains("case_note")){
            setCaseNote(config.get(PTY_CASE_NOTE));
         }
         if(config.contains("input_note")){
            setInputNote(config.get(PTY_INPUT_NOTE));
         }
         if(config.contains("output_note")){
            setOutputNote(config.get(PTY_OUTPUT_NOTE));
         }
         if(config.contains("result_cd")){
            setResultCd(config.get(PTY_RESULT_CD));
         }
         if(config.contains("result_note")){
            setResultNote(config.get(PTY_RESULT_NOTE));
         }
         if(config.contains("failure_cd")){
            setFailureCd(config.get(PTY_FAILURE_CD));
         }
         if(config.contains("failure_note")){
            setFailureNote(config.get(PTY_FAILURE_NOTE));
         }
         if(config.contains("reason_cd")){
            setReasonCd(config.get(PTY_REASON_CD));
         }
         if(config.contains("reason_note")){
            setReasonNote(config.get(PTY_REASON_NOTE));
         }
         if(config.contains("create_user_name")){
            setCreateUserName(config.get(PTY_CREATE_USER_NAME));
         }
         if(config.contains("test_user_name")){
            setTestUserName(config.get(PTY_TEST_USER_NAME));
         }
         if(config.contains("test_date")){
            setTestDate(config.get(PTY_TEST_DATE));
         }
         if(config.contains("confirm_user_name")){
            setConfirmUserName(config.get(PTY_CONFIRM_USER_NAME));
         }
         if(config.contains("confirm_date")){
            setConfirmDate(config.get(PTY_CONFIRM_DATE));
         }
      }else if(EXmlConfig.Value == type){
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("label")){
            setLabel(config.get(PTY_LABEL));
         }
         if(config.contains("note")){
            setNote(config.get(PTY_NOTE));
         }
         if(config.contains("_type")){
            set_type(config.get(PTY__TYPE));
         }
         if(config.contains("is_valid")){
            setIsValid(config.get(PTY_IS_VALID));
         }
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
         if(config.contains("status_cd")){
            setStatusCd(config.get(PTY_STATUS_CD));
         }
         if(config.contains("important_cd")){
            setImportantCd(config.get(PTY_IMPORTANT_CD));
         }
         if(config.contains("priority_cd")){
            setPriorityCd(config.get(PTY_PRIORITY_CD));
         }
         if(config.contains("difficult_cd")){
            setDifficultCd(config.get(PTY_DIFFICULT_CD));
         }
         if(config.contains("case_note")){
            setCaseNote(config.get(PTY_CASE_NOTE));
         }
         if(config.contains("input_note")){
            setInputNote(config.get(PTY_INPUT_NOTE));
         }
         if(config.contains("output_note")){
            setOutputNote(config.get(PTY_OUTPUT_NOTE));
         }
         if(config.contains("result_cd")){
            setResultCd(config.get(PTY_RESULT_CD));
         }
         if(config.contains("result_note")){
            setResultNote(config.get(PTY_RESULT_NOTE));
         }
         if(config.contains("failure_cd")){
            setFailureCd(config.get(PTY_FAILURE_CD));
         }
         if(config.contains("failure_note")){
            setFailureNote(config.get(PTY_FAILURE_NOTE));
         }
         if(config.contains("reason_cd")){
            setReasonCd(config.get(PTY_REASON_CD));
         }
         if(config.contains("reason_note")){
            setReasonNote(config.get(PTY_REASON_NOTE));
         }
         if(config.contains("create_user_name")){
            setCreateUserName(config.get(PTY_CREATE_USER_NAME));
         }
         if(config.contains("test_user_name")){
            setTestUserName(config.get(PTY_TEST_USER_NAME));
         }
         if(config.contains("test_date")){
            setTestDate(config.get(PTY_TEST_DATE));
         }
         if(config.contains("confirm_user_name")){
            setConfirmUserName(config.get(PTY_CONFIRM_USER_NAME));
         }
         if(config.contains("confirm_date")){
            setConfirmDate(config.get(PTY_CONFIRM_DATE));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(get_type())){
            if(config.contains("_type")){
               set_type(config.get(PTY__TYPE));
            }
         }
         if(RString.isEmpty(getIsValid())){
            if(config.contains("is_valid")){
               setIsValid(config.get(PTY_IS_VALID));
            }
         }
         if(RString.isEmpty(getCode())){
            if(config.contains("code")){
               setCode(config.get(PTY_CODE));
            }
         }
         if(RString.isEmpty(getStatusCd())){
            if(config.contains("status_cd")){
               setStatusCd(config.get(PTY_STATUS_CD));
            }
         }
         if(RString.isEmpty(getImportantCd())){
            if(config.contains("important_cd")){
               setImportantCd(config.get(PTY_IMPORTANT_CD));
            }
         }
         if(RString.isEmpty(getPriorityCd())){
            if(config.contains("priority_cd")){
               setPriorityCd(config.get(PTY_PRIORITY_CD));
            }
         }
         if(RString.isEmpty(getDifficultCd())){
            if(config.contains("difficult_cd")){
               setDifficultCd(config.get(PTY_DIFFICULT_CD));
            }
         }
         if(RString.isEmpty(getCaseNote())){
            if(config.contains("case_note")){
               setCaseNote(config.get(PTY_CASE_NOTE));
            }
         }
         if(RString.isEmpty(getInputNote())){
            if(config.contains("input_note")){
               setInputNote(config.get(PTY_INPUT_NOTE));
            }
         }
         if(RString.isEmpty(getOutputNote())){
            if(config.contains("output_note")){
               setOutputNote(config.get(PTY_OUTPUT_NOTE));
            }
         }
         if(RString.isEmpty(getResultCd())){
            if(config.contains("result_cd")){
               setResultCd(config.get(PTY_RESULT_CD));
            }
         }
         if(RString.isEmpty(getResultNote())){
            if(config.contains("result_note")){
               setResultNote(config.get(PTY_RESULT_NOTE));
            }
         }
         if(RString.isEmpty(getFailureCd())){
            if(config.contains("failure_cd")){
               setFailureCd(config.get(PTY_FAILURE_CD));
            }
         }
         if(RString.isEmpty(getFailureNote())){
            if(config.contains("failure_note")){
               setFailureNote(config.get(PTY_FAILURE_NOTE));
            }
         }
         if(RString.isEmpty(getReasonCd())){
            if(config.contains("reason_cd")){
               setReasonCd(config.get(PTY_REASON_CD));
            }
         }
         if(RString.isEmpty(getReasonNote())){
            if(config.contains("reason_note")){
               setReasonNote(config.get(PTY_REASON_NOTE));
            }
         }
         if(RString.isEmpty(getCreateUserName())){
            if(config.contains("create_user_name")){
               setCreateUserName(config.get(PTY_CREATE_USER_NAME));
            }
         }
         if(RString.isEmpty(getTestUserName())){
            if(config.contains("test_user_name")){
               setTestUserName(config.get(PTY_TEST_USER_NAME));
            }
         }
         if(RString.isEmpty(getTestDate())){
            if(config.contains("test_date")){
               setTestDate(config.get(PTY_TEST_DATE));
            }
         }
         if(RString.isEmpty(getConfirmUserName())){
            if(config.contains("confirm_user_name")){
               setConfirmUserName(config.get(PTY_CONFIRM_USER_NAME));
            }
         }
         if(RString.isEmpty(getConfirmDate())){
            if(config.contains("confirm_date")){
               setConfirmDate(config.get(PTY_CONFIRM_DATE));
            }
         }
      }
   }

   //============================================================
   // <T>保存设置信息。</T>
   //
   // @param config 设置信息
   // @param type 类型
   //============================================================
   public void saveConfig(FXmlNode config, EXmlConfig type){
      config.setName(NAME);
      super.saveConfig(config, type);
      if(EXmlConfig.Full == type){
         if(RString.isNotEmpty(getName())){
            config.set(PTY_NAME, getName());
         }
         String label = _label.pack().toString();
         if(RString.isNotEmpty(label)){
            config.set(PTY_LABEL, label);
         }
         String note = _note.pack().toString();
         if(RString.isNotEmpty(note)){
            config.set(PTY_NOTE, note);
         }
         if(RString.isNotEmpty(get_type())){
            config.set(PTY__TYPE, get_type());
         }
         if(RString.isNotEmpty(getIsValid())){
            config.set(PTY_IS_VALID, getIsValid());
         }
         if(RString.isNotEmpty(getCode())){
            config.set(PTY_CODE, getCode());
         }
         if(RString.isNotEmpty(getStatusCd())){
            config.set(PTY_STATUS_CD, getStatusCd());
         }
         if(RString.isNotEmpty(getImportantCd())){
            config.set(PTY_IMPORTANT_CD, getImportantCd());
         }
         if(RString.isNotEmpty(getPriorityCd())){
            config.set(PTY_PRIORITY_CD, getPriorityCd());
         }
         if(RString.isNotEmpty(getDifficultCd())){
            config.set(PTY_DIFFICULT_CD, getDifficultCd());
         }
         if(RString.isNotEmpty(getCaseNote())){
            config.set(PTY_CASE_NOTE, getCaseNote());
         }
         if(RString.isNotEmpty(getInputNote())){
            config.set(PTY_INPUT_NOTE, getInputNote());
         }
         if(RString.isNotEmpty(getOutputNote())){
            config.set(PTY_OUTPUT_NOTE, getOutputNote());
         }
         if(RString.isNotEmpty(getResultCd())){
            config.set(PTY_RESULT_CD, getResultCd());
         }
         if(RString.isNotEmpty(getResultNote())){
            config.set(PTY_RESULT_NOTE, getResultNote());
         }
         if(RString.isNotEmpty(getFailureCd())){
            config.set(PTY_FAILURE_CD, getFailureCd());
         }
         if(RString.isNotEmpty(getFailureNote())){
            config.set(PTY_FAILURE_NOTE, getFailureNote());
         }
         if(RString.isNotEmpty(getReasonCd())){
            config.set(PTY_REASON_CD, getReasonCd());
         }
         if(RString.isNotEmpty(getReasonNote())){
            config.set(PTY_REASON_NOTE, getReasonNote());
         }
         if(RString.isNotEmpty(getCreateUserName())){
            config.set(PTY_CREATE_USER_NAME, getCreateUserName());
         }
         if(RString.isNotEmpty(getTestUserName())){
            config.set(PTY_TEST_USER_NAME, getTestUserName());
         }
         if(RString.isNotEmpty(getTestDate())){
            config.set(PTY_TEST_DATE, getTestDate());
         }
         if(RString.isNotEmpty(getConfirmUserName())){
            config.set(PTY_CONFIRM_USER_NAME, getConfirmUserName());
         }
         if(RString.isNotEmpty(getConfirmDate())){
            config.set(PTY_CONFIRM_DATE, getConfirmDate());
         }
      }else if(EXmlConfig.Simple == type){
         if(RString.isNotEmpty(getName())){
            config.set(PTY_NAME, getName());
         }
         if(RString.isNotEmpty(getLabel())){
            config.set(PTY_LABEL, getLabel());
         }
         if(RString.isNotEmpty(getNote())){
            config.set(PTY_NOTE, getNote());
         }
         if(RString.isNotEmpty(get_type())){
            config.set(PTY__TYPE, get_type());
         }
         if(RString.isNotEmpty(getIsValid())){
            config.set(PTY_IS_VALID, getIsValid());
         }
         if(RString.isNotEmpty(getCode())){
            config.set(PTY_CODE, getCode());
         }
         if(RString.isNotEmpty(getStatusCd())){
            config.set(PTY_STATUS_CD, getStatusCd());
         }
         if(RString.isNotEmpty(getImportantCd())){
            config.set(PTY_IMPORTANT_CD, getImportantCd());
         }
         if(RString.isNotEmpty(getPriorityCd())){
            config.set(PTY_PRIORITY_CD, getPriorityCd());
         }
         if(RString.isNotEmpty(getDifficultCd())){
            config.set(PTY_DIFFICULT_CD, getDifficultCd());
         }
         if(RString.isNotEmpty(getCaseNote())){
            config.set(PTY_CASE_NOTE, getCaseNote());
         }
         if(RString.isNotEmpty(getInputNote())){
            config.set(PTY_INPUT_NOTE, getInputNote());
         }
         if(RString.isNotEmpty(getOutputNote())){
            config.set(PTY_OUTPUT_NOTE, getOutputNote());
         }
         if(RString.isNotEmpty(getResultCd())){
            config.set(PTY_RESULT_CD, getResultCd());
         }
         if(RString.isNotEmpty(getResultNote())){
            config.set(PTY_RESULT_NOTE, getResultNote());
         }
         if(RString.isNotEmpty(getFailureCd())){
            config.set(PTY_FAILURE_CD, getFailureCd());
         }
         if(RString.isNotEmpty(getFailureNote())){
            config.set(PTY_FAILURE_NOTE, getFailureNote());
         }
         if(RString.isNotEmpty(getReasonCd())){
            config.set(PTY_REASON_CD, getReasonCd());
         }
         if(RString.isNotEmpty(getReasonNote())){
            config.set(PTY_REASON_NOTE, getReasonNote());
         }
         if(RString.isNotEmpty(getCreateUserName())){
            config.set(PTY_CREATE_USER_NAME, getCreateUserName());
         }
         if(RString.isNotEmpty(getTestUserName())){
            config.set(PTY_TEST_USER_NAME, getTestUserName());
         }
         if(RString.isNotEmpty(getTestDate())){
            config.set(PTY_TEST_DATE, getTestDate());
         }
         if(RString.isNotEmpty(getConfirmUserName())){
            config.set(PTY_CONFIRM_USER_NAME, getConfirmUserName());
         }
         if(RString.isNotEmpty(getConfirmDate())){
            config.set(PTY_CONFIRM_DATE, getConfirmDate());
         }
      }else if(EXmlConfig.Value == type){
         String sName = getName();
         if(RString.isNotEmpty(sName)){
            config.set(PTY_NAME, sName);
         }
         String sLabel = getLabel();
         if(RString.isNotEmpty(sLabel)){
            config.set(PTY_LABEL, sLabel);
         }
         String sNote = getNote();
         if(RString.isNotEmpty(sNote)){
            config.set(PTY_NOTE, sNote);
         }
         String s_type = get_type();
         if(RString.isNotEmpty(s_type)){
            config.set(PTY__TYPE, s_type);
         }
         String sIsValid = getIsValid();
         if(RString.isNotEmpty(sIsValid)){
            config.set(PTY_IS_VALID, sIsValid);
         }
         String sCode = getCode();
         if(RString.isNotEmpty(sCode)){
            config.set(PTY_CODE, sCode);
         }
         String sStatusCd = getStatusCd();
         if(RString.isNotEmpty(sStatusCd)){
            config.set(PTY_STATUS_CD, sStatusCd);
         }
         String sImportantCd = getImportantCd();
         if(RString.isNotEmpty(sImportantCd)){
            config.set(PTY_IMPORTANT_CD, sImportantCd);
         }
         String sPriorityCd = getPriorityCd();
         if(RString.isNotEmpty(sPriorityCd)){
            config.set(PTY_PRIORITY_CD, sPriorityCd);
         }
         String sDifficultCd = getDifficultCd();
         if(RString.isNotEmpty(sDifficultCd)){
            config.set(PTY_DIFFICULT_CD, sDifficultCd);
         }
         String sCaseNote = getCaseNote();
         if(RString.isNotEmpty(sCaseNote)){
            config.set(PTY_CASE_NOTE, sCaseNote);
         }
         String sInputNote = getInputNote();
         if(RString.isNotEmpty(sInputNote)){
            config.set(PTY_INPUT_NOTE, sInputNote);
         }
         String sOutputNote = getOutputNote();
         if(RString.isNotEmpty(sOutputNote)){
            config.set(PTY_OUTPUT_NOTE, sOutputNote);
         }
         String sResultCd = getResultCd();
         if(RString.isNotEmpty(sResultCd)){
            config.set(PTY_RESULT_CD, sResultCd);
         }
         String sResultNote = getResultNote();
         if(RString.isNotEmpty(sResultNote)){
            config.set(PTY_RESULT_NOTE, sResultNote);
         }
         String sFailureCd = getFailureCd();
         if(RString.isNotEmpty(sFailureCd)){
            config.set(PTY_FAILURE_CD, sFailureCd);
         }
         String sFailureNote = getFailureNote();
         if(RString.isNotEmpty(sFailureNote)){
            config.set(PTY_FAILURE_NOTE, sFailureNote);
         }
         String sReasonCd = getReasonCd();
         if(RString.isNotEmpty(sReasonCd)){
            config.set(PTY_REASON_CD, sReasonCd);
         }
         String sReasonNote = getReasonNote();
         if(RString.isNotEmpty(sReasonNote)){
            config.set(PTY_REASON_NOTE, sReasonNote);
         }
         String sCreateUserName = getCreateUserName();
         if(RString.isNotEmpty(sCreateUserName)){
            config.set(PTY_CREATE_USER_NAME, sCreateUserName);
         }
         String sTestUserName = getTestUserName();
         if(RString.isNotEmpty(sTestUserName)){
            config.set(PTY_TEST_USER_NAME, sTestUserName);
         }
         String sTestDate = getTestDate();
         if(RString.isNotEmpty(sTestDate)){
            config.set(PTY_TEST_DATE, sTestDate);
         }
         String sConfirmUserName = getConfirmUserName();
         if(RString.isNotEmpty(sConfirmUserName)){
            config.set(PTY_CONFIRM_USER_NAME, sConfirmUserName);
         }
         String sConfirmDate = getConfirmDate();
         if(RString.isNotEmpty(sConfirmDate)){
            config.set(PTY_CONFIRM_DATE, sConfirmDate);
         }
      }else if(EXmlConfig.Default == type){
         String s_type = get_type();
         if(RString.isNotEmpty(s_type)){
            config.set(PTY__TYPE, s_type);
         }
         String sIsValid = getIsValid();
         if(RString.isNotEmpty(sIsValid)){
            config.set(PTY_IS_VALID, sIsValid);
         }
         String sCode = getCode();
         if(RString.isNotEmpty(sCode)){
            config.set(PTY_CODE, sCode);
         }
         String sStatusCd = getStatusCd();
         if(RString.isNotEmpty(sStatusCd)){
            config.set(PTY_STATUS_CD, sStatusCd);
         }
         String sImportantCd = getImportantCd();
         if(RString.isNotEmpty(sImportantCd)){
            config.set(PTY_IMPORTANT_CD, sImportantCd);
         }
         String sPriorityCd = getPriorityCd();
         if(RString.isNotEmpty(sPriorityCd)){
            config.set(PTY_PRIORITY_CD, sPriorityCd);
         }
         String sDifficultCd = getDifficultCd();
         if(RString.isNotEmpty(sDifficultCd)){
            config.set(PTY_DIFFICULT_CD, sDifficultCd);
         }
         String sCaseNote = getCaseNote();
         if(RString.isNotEmpty(sCaseNote)){
            config.set(PTY_CASE_NOTE, sCaseNote);
         }
         String sInputNote = getInputNote();
         if(RString.isNotEmpty(sInputNote)){
            config.set(PTY_INPUT_NOTE, sInputNote);
         }
         String sOutputNote = getOutputNote();
         if(RString.isNotEmpty(sOutputNote)){
            config.set(PTY_OUTPUT_NOTE, sOutputNote);
         }
         String sResultCd = getResultCd();
         if(RString.isNotEmpty(sResultCd)){
            config.set(PTY_RESULT_CD, sResultCd);
         }
         String sResultNote = getResultNote();
         if(RString.isNotEmpty(sResultNote)){
            config.set(PTY_RESULT_NOTE, sResultNote);
         }
         String sFailureCd = getFailureCd();
         if(RString.isNotEmpty(sFailureCd)){
            config.set(PTY_FAILURE_CD, sFailureCd);
         }
         String sFailureNote = getFailureNote();
         if(RString.isNotEmpty(sFailureNote)){
            config.set(PTY_FAILURE_NOTE, sFailureNote);
         }
         String sReasonCd = getReasonCd();
         if(RString.isNotEmpty(sReasonCd)){
            config.set(PTY_REASON_CD, sReasonCd);
         }
         String sReasonNote = getReasonNote();
         if(RString.isNotEmpty(sReasonNote)){
            config.set(PTY_REASON_NOTE, sReasonNote);
         }
         String sCreateUserName = getCreateUserName();
         if(RString.isNotEmpty(sCreateUserName)){
            config.set(PTY_CREATE_USER_NAME, sCreateUserName);
         }
         String sTestUserName = getTestUserName();
         if(RString.isNotEmpty(sTestUserName)){
            config.set(PTY_TEST_USER_NAME, sTestUserName);
         }
         String sTestDate = getTestDate();
         if(RString.isNotEmpty(sTestDate)){
            config.set(PTY_TEST_DATE, sTestDate);
         }
         String sConfirmUserName = getConfirmUserName();
         if(RString.isNotEmpty(sConfirmUserName)){
            config.set(PTY_CONFIRM_USER_NAME, sConfirmUserName);
         }
         String sConfirmDate = getConfirmDate();
         if(RString.isNotEmpty(sConfirmDate)){
            config.set(PTY_CONFIRM_DATE, sConfirmDate);
         }
      }
   }
}