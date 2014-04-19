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
// <T>项目对象的XML节点基类。</T>
//============================================================
public abstract class XBaseProject
      extends FXmlObject
      implements
         XObjectFace
{
   // 名称定义
   public static final String NAME = "Project";

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

   // 项目编号的名称定义
   public static final String PTY_CODE = "code";

   // 项目类型的名称定义
   public static final String PTY_TYPE_CD = "type_cd";

   // 项目状态的名称定义
   public static final String PTY_STATUS_CD = "status_cd";

   // 预期开始时间的名称定义
   public static final String PTY_EXPECT_BEGIN_DATE = "expect_begin_date";

   // 预期结束时间的名称定义
   public static final String PTY_EXPECT_END_DATE = "expect_end_date";

   // 开始时间的名称定义
   public static final String PTY_BEGIN_DATE = "begin_date";

   // 结束时间的名称定义
   public static final String PTY_END_DATE = "end_date";

   // 预计人数的名称定义
   public static final String PTY_PLAN_PERSON_COUNT = "plan_person_count";

   // 预计人月数的名称定义
   public static final String PTY_PLAN_PERSON_MONTH_COUNT = "plan_person_month_count";

   // 背景描述的名称定义
   public static final String PTY_BACKGROUND_NOTE = "background_note";

   // 目标描述的名称定义
   public static final String PTY_TARGET_NOTE = "target_note";

   // 规模类型的名称定义
   public static final String PTY_SCALE_CD = "scale_cd";

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

   // 项目编号的定义
   @AName("code")
   protected String _code;

   // 项目类型的定义
   @AName("type_cd")
   protected String _typeCd;

   // 项目状态的定义
   @AName("status_cd")
   protected String _statusCd;

   // 预期开始时间的定义
   @AName("expect_begin_date")
   protected String _expectBeginDate;

   // 预期结束时间的定义
   @AName("expect_end_date")
   protected String _expectEndDate;

   // 开始时间的定义
   @AName("begin_date")
   protected String _beginDate;

   // 结束时间的定义
   @AName("end_date")
   protected String _endDate;

   // 预计人数的定义
   @AName("plan_person_count")
   protected String _planPersonCount;

   // 预计人月数的定义
   @AName("plan_person_month_count")
   protected String _planPersonMonthCount;

   // 背景描述的定义
   @AName("background_note")
   protected String _backgroundNote;

   // 目标描述的定义
   @AName("target_note")
   protected String _targetNote;

   // 规模类型的定义
   @AName("scale_cd")
   protected String _scaleCd;

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
   // <T>获得项目编号的内容。</T>
   //
   // @return 项目编号
   //============================================================
   public String getCode(){
      return _code;
   }

   //============================================================
   // <T>设置项目编号的内容。</T>
   //
   // @param value 项目编号
   //============================================================
   public void setCode(String value){
      _code = value;
   }

   //============================================================
   // <T>获得项目类型的内容。</T>
   //
   // @return 项目类型
   //============================================================
   public String getTypeCd(){
      return _typeCd;
   }

   //============================================================
   // <T>设置项目类型的内容。</T>
   //
   // @param value 项目类型
   //============================================================
   public void setTypeCd(String value){
      _typeCd = value;
   }

   //============================================================
   // <T>获得项目状态的内容。</T>
   //
   // @return 项目状态
   //============================================================
   public String getStatusCd(){
      return _statusCd;
   }

   //============================================================
   // <T>设置项目状态的内容。</T>
   //
   // @param value 项目状态
   //============================================================
   public void setStatusCd(String value){
      _statusCd = value;
   }

   //============================================================
   // <T>获得预期开始时间的内容。</T>
   //
   // @return 预期开始时间
   //============================================================
   public String getExpectBeginDate(){
      return _expectBeginDate;
   }

   //============================================================
   // <T>设置预期开始时间的内容。</T>
   //
   // @param value 预期开始时间
   //============================================================
   public void setExpectBeginDate(String value){
      _expectBeginDate = value;
   }

   //============================================================
   // <T>获得预期结束时间的内容。</T>
   //
   // @return 预期结束时间
   //============================================================
   public String getExpectEndDate(){
      return _expectEndDate;
   }

   //============================================================
   // <T>设置预期结束时间的内容。</T>
   //
   // @param value 预期结束时间
   //============================================================
   public void setExpectEndDate(String value){
      _expectEndDate = value;
   }

   //============================================================
   // <T>获得开始时间的内容。</T>
   //
   // @return 开始时间
   //============================================================
   public String getBeginDate(){
      return _beginDate;
   }

   //============================================================
   // <T>设置开始时间的内容。</T>
   //
   // @param value 开始时间
   //============================================================
   public void setBeginDate(String value){
      _beginDate = value;
   }

   //============================================================
   // <T>获得结束时间的内容。</T>
   //
   // @return 结束时间
   //============================================================
   public String getEndDate(){
      return _endDate;
   }

   //============================================================
   // <T>设置结束时间的内容。</T>
   //
   // @param value 结束时间
   //============================================================
   public void setEndDate(String value){
      _endDate = value;
   }

   //============================================================
   // <T>获得预计人数的内容。</T>
   //
   // @return 预计人数
   //============================================================
   public String getPlanPersonCount(){
      return _planPersonCount;
   }

   //============================================================
   // <T>设置预计人数的内容。</T>
   //
   // @param value 预计人数
   //============================================================
   public void setPlanPersonCount(String value){
      _planPersonCount = value;
   }

   //============================================================
   // <T>获得预计人月数的内容。</T>
   //
   // @return 预计人月数
   //============================================================
   public String getPlanPersonMonthCount(){
      return _planPersonMonthCount;
   }

   //============================================================
   // <T>设置预计人月数的内容。</T>
   //
   // @param value 预计人月数
   //============================================================
   public void setPlanPersonMonthCount(String value){
      _planPersonMonthCount = value;
   }

   //============================================================
   // <T>获得背景描述的内容。</T>
   //
   // @return 背景描述
   //============================================================
   public String getBackgroundNote(){
      return _backgroundNote;
   }

   //============================================================
   // <T>设置背景描述的内容。</T>
   //
   // @param value 背景描述
   //============================================================
   public void setBackgroundNote(String value){
      _backgroundNote = value;
   }

   //============================================================
   // <T>获得目标描述的内容。</T>
   //
   // @return 目标描述
   //============================================================
   public String getTargetNote(){
      return _targetNote;
   }

   //============================================================
   // <T>设置目标描述的内容。</T>
   //
   // @param value 目标描述
   //============================================================
   public void setTargetNote(String value){
      _targetNote = value;
   }

   //============================================================
   // <T>获得规模类型的内容。</T>
   //
   // @return 规模类型
   //============================================================
   public String getScaleCd(){
      return _scaleCd;
   }

   //============================================================
   // <T>设置规模类型的内容。</T>
   //
   // @param value 规模类型
   //============================================================
   public void setScaleCd(String value){
      _scaleCd = value;
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
      }else if(PTY_TYPE_CD.equalsIgnoreCase(name)){
         return getTypeCd();
      }else if(PTY_STATUS_CD.equalsIgnoreCase(name)){
         return getStatusCd();
      }else if(PTY_EXPECT_BEGIN_DATE.equalsIgnoreCase(name)){
         return getExpectBeginDate();
      }else if(PTY_EXPECT_END_DATE.equalsIgnoreCase(name)){
         return getExpectEndDate();
      }else if(PTY_BEGIN_DATE.equalsIgnoreCase(name)){
         return getBeginDate();
      }else if(PTY_END_DATE.equalsIgnoreCase(name)){
         return getEndDate();
      }else if(PTY_PLAN_PERSON_COUNT.equalsIgnoreCase(name)){
         return getPlanPersonCount();
      }else if(PTY_PLAN_PERSON_MONTH_COUNT.equalsIgnoreCase(name)){
         return getPlanPersonMonthCount();
      }else if(PTY_BACKGROUND_NOTE.equalsIgnoreCase(name)){
         return getBackgroundNote();
      }else if(PTY_TARGET_NOTE.equalsIgnoreCase(name)){
         return getTargetNote();
      }else if(PTY_SCALE_CD.equalsIgnoreCase(name)){
         return getScaleCd();
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
      }else if(PTY_TYPE_CD.equalsIgnoreCase(name)){
         setTypeCd(value);
      }else if(PTY_STATUS_CD.equalsIgnoreCase(name)){
         setStatusCd(value);
      }else if(PTY_EXPECT_BEGIN_DATE.equalsIgnoreCase(name)){
         setExpectBeginDate(value);
      }else if(PTY_EXPECT_END_DATE.equalsIgnoreCase(name)){
         setExpectEndDate(value);
      }else if(PTY_BEGIN_DATE.equalsIgnoreCase(name)){
         setBeginDate(value);
      }else if(PTY_END_DATE.equalsIgnoreCase(name)){
         setEndDate(value);
      }else if(PTY_PLAN_PERSON_COUNT.equalsIgnoreCase(name)){
         setPlanPersonCount(value);
      }else if(PTY_PLAN_PERSON_MONTH_COUNT.equalsIgnoreCase(name)){
         setPlanPersonMonthCount(value);
      }else if(PTY_BACKGROUND_NOTE.equalsIgnoreCase(name)){
         setBackgroundNote(value);
      }else if(PTY_TARGET_NOTE.equalsIgnoreCase(name)){
         setTargetNote(value);
      }else if(PTY_SCALE_CD.equalsIgnoreCase(name)){
         setScaleCd(value);
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
         if(config.contains("type_cd")){
            setTypeCd(config.get(PTY_TYPE_CD));
         }
         if(config.contains("status_cd")){
            setStatusCd(config.get(PTY_STATUS_CD));
         }
         if(config.contains("expect_begin_date")){
            setExpectBeginDate(config.get(PTY_EXPECT_BEGIN_DATE));
         }
         if(config.contains("expect_end_date")){
            setExpectEndDate(config.get(PTY_EXPECT_END_DATE));
         }
         if(config.contains("begin_date")){
            setBeginDate(config.get(PTY_BEGIN_DATE));
         }
         if(config.contains("end_date")){
            setEndDate(config.get(PTY_END_DATE));
         }
         if(config.contains("plan_person_count")){
            setPlanPersonCount(config.get(PTY_PLAN_PERSON_COUNT));
         }
         if(config.contains("plan_person_month_count")){
            setPlanPersonMonthCount(config.get(PTY_PLAN_PERSON_MONTH_COUNT));
         }
         if(config.contains("background_note")){
            setBackgroundNote(config.get(PTY_BACKGROUND_NOTE));
         }
         if(config.contains("target_note")){
            setTargetNote(config.get(PTY_TARGET_NOTE));
         }
         if(config.contains("scale_cd")){
            setScaleCd(config.get(PTY_SCALE_CD));
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
         if(config.contains("type_cd")){
            setTypeCd(config.get(PTY_TYPE_CD));
         }
         if(config.contains("status_cd")){
            setStatusCd(config.get(PTY_STATUS_CD));
         }
         if(config.contains("expect_begin_date")){
            setExpectBeginDate(config.get(PTY_EXPECT_BEGIN_DATE));
         }
         if(config.contains("expect_end_date")){
            setExpectEndDate(config.get(PTY_EXPECT_END_DATE));
         }
         if(config.contains("begin_date")){
            setBeginDate(config.get(PTY_BEGIN_DATE));
         }
         if(config.contains("end_date")){
            setEndDate(config.get(PTY_END_DATE));
         }
         if(config.contains("plan_person_count")){
            setPlanPersonCount(config.get(PTY_PLAN_PERSON_COUNT));
         }
         if(config.contains("plan_person_month_count")){
            setPlanPersonMonthCount(config.get(PTY_PLAN_PERSON_MONTH_COUNT));
         }
         if(config.contains("background_note")){
            setBackgroundNote(config.get(PTY_BACKGROUND_NOTE));
         }
         if(config.contains("target_note")){
            setTargetNote(config.get(PTY_TARGET_NOTE));
         }
         if(config.contains("scale_cd")){
            setScaleCd(config.get(PTY_SCALE_CD));
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
         if(config.contains("type_cd")){
            setTypeCd(config.get(PTY_TYPE_CD));
         }
         if(config.contains("status_cd")){
            setStatusCd(config.get(PTY_STATUS_CD));
         }
         if(config.contains("expect_begin_date")){
            setExpectBeginDate(config.get(PTY_EXPECT_BEGIN_DATE));
         }
         if(config.contains("expect_end_date")){
            setExpectEndDate(config.get(PTY_EXPECT_END_DATE));
         }
         if(config.contains("begin_date")){
            setBeginDate(config.get(PTY_BEGIN_DATE));
         }
         if(config.contains("end_date")){
            setEndDate(config.get(PTY_END_DATE));
         }
         if(config.contains("plan_person_count")){
            setPlanPersonCount(config.get(PTY_PLAN_PERSON_COUNT));
         }
         if(config.contains("plan_person_month_count")){
            setPlanPersonMonthCount(config.get(PTY_PLAN_PERSON_MONTH_COUNT));
         }
         if(config.contains("background_note")){
            setBackgroundNote(config.get(PTY_BACKGROUND_NOTE));
         }
         if(config.contains("target_note")){
            setTargetNote(config.get(PTY_TARGET_NOTE));
         }
         if(config.contains("scale_cd")){
            setScaleCd(config.get(PTY_SCALE_CD));
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
         if(RString.isEmpty(getTypeCd())){
            if(config.contains("type_cd")){
               setTypeCd(config.get(PTY_TYPE_CD));
            }
         }
         if(RString.isEmpty(getStatusCd())){
            if(config.contains("status_cd")){
               setStatusCd(config.get(PTY_STATUS_CD));
            }
         }
         if(RString.isEmpty(getExpectBeginDate())){
            if(config.contains("expect_begin_date")){
               setExpectBeginDate(config.get(PTY_EXPECT_BEGIN_DATE));
            }
         }
         if(RString.isEmpty(getExpectEndDate())){
            if(config.contains("expect_end_date")){
               setExpectEndDate(config.get(PTY_EXPECT_END_DATE));
            }
         }
         if(RString.isEmpty(getBeginDate())){
            if(config.contains("begin_date")){
               setBeginDate(config.get(PTY_BEGIN_DATE));
            }
         }
         if(RString.isEmpty(getEndDate())){
            if(config.contains("end_date")){
               setEndDate(config.get(PTY_END_DATE));
            }
         }
         if(RString.isEmpty(getPlanPersonCount())){
            if(config.contains("plan_person_count")){
               setPlanPersonCount(config.get(PTY_PLAN_PERSON_COUNT));
            }
         }
         if(RString.isEmpty(getPlanPersonMonthCount())){
            if(config.contains("plan_person_month_count")){
               setPlanPersonMonthCount(config.get(PTY_PLAN_PERSON_MONTH_COUNT));
            }
         }
         if(RString.isEmpty(getBackgroundNote())){
            if(config.contains("background_note")){
               setBackgroundNote(config.get(PTY_BACKGROUND_NOTE));
            }
         }
         if(RString.isEmpty(getTargetNote())){
            if(config.contains("target_note")){
               setTargetNote(config.get(PTY_TARGET_NOTE));
            }
         }
         if(RString.isEmpty(getScaleCd())){
            if(config.contains("scale_cd")){
               setScaleCd(config.get(PTY_SCALE_CD));
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
         if(RString.isNotEmpty(getTypeCd())){
            config.set(PTY_TYPE_CD, getTypeCd());
         }
         if(RString.isNotEmpty(getStatusCd())){
            config.set(PTY_STATUS_CD, getStatusCd());
         }
         if(RString.isNotEmpty(getExpectBeginDate())){
            config.set(PTY_EXPECT_BEGIN_DATE, getExpectBeginDate());
         }
         if(RString.isNotEmpty(getExpectEndDate())){
            config.set(PTY_EXPECT_END_DATE, getExpectEndDate());
         }
         if(RString.isNotEmpty(getBeginDate())){
            config.set(PTY_BEGIN_DATE, getBeginDate());
         }
         if(RString.isNotEmpty(getEndDate())){
            config.set(PTY_END_DATE, getEndDate());
         }
         if(RString.isNotEmpty(getPlanPersonCount())){
            config.set(PTY_PLAN_PERSON_COUNT, getPlanPersonCount());
         }
         if(RString.isNotEmpty(getPlanPersonMonthCount())){
            config.set(PTY_PLAN_PERSON_MONTH_COUNT, getPlanPersonMonthCount());
         }
         if(RString.isNotEmpty(getBackgroundNote())){
            config.set(PTY_BACKGROUND_NOTE, getBackgroundNote());
         }
         if(RString.isNotEmpty(getTargetNote())){
            config.set(PTY_TARGET_NOTE, getTargetNote());
         }
         if(RString.isNotEmpty(getScaleCd())){
            config.set(PTY_SCALE_CD, getScaleCd());
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
         if(RString.isNotEmpty(getTypeCd())){
            config.set(PTY_TYPE_CD, getTypeCd());
         }
         if(RString.isNotEmpty(getStatusCd())){
            config.set(PTY_STATUS_CD, getStatusCd());
         }
         if(RString.isNotEmpty(getExpectBeginDate())){
            config.set(PTY_EXPECT_BEGIN_DATE, getExpectBeginDate());
         }
         if(RString.isNotEmpty(getExpectEndDate())){
            config.set(PTY_EXPECT_END_DATE, getExpectEndDate());
         }
         if(RString.isNotEmpty(getBeginDate())){
            config.set(PTY_BEGIN_DATE, getBeginDate());
         }
         if(RString.isNotEmpty(getEndDate())){
            config.set(PTY_END_DATE, getEndDate());
         }
         if(RString.isNotEmpty(getPlanPersonCount())){
            config.set(PTY_PLAN_PERSON_COUNT, getPlanPersonCount());
         }
         if(RString.isNotEmpty(getPlanPersonMonthCount())){
            config.set(PTY_PLAN_PERSON_MONTH_COUNT, getPlanPersonMonthCount());
         }
         if(RString.isNotEmpty(getBackgroundNote())){
            config.set(PTY_BACKGROUND_NOTE, getBackgroundNote());
         }
         if(RString.isNotEmpty(getTargetNote())){
            config.set(PTY_TARGET_NOTE, getTargetNote());
         }
         if(RString.isNotEmpty(getScaleCd())){
            config.set(PTY_SCALE_CD, getScaleCd());
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
         String sTypeCd = getTypeCd();
         if(RString.isNotEmpty(sTypeCd)){
            config.set(PTY_TYPE_CD, sTypeCd);
         }
         String sStatusCd = getStatusCd();
         if(RString.isNotEmpty(sStatusCd)){
            config.set(PTY_STATUS_CD, sStatusCd);
         }
         String sExpectBeginDate = getExpectBeginDate();
         if(RString.isNotEmpty(sExpectBeginDate)){
            config.set(PTY_EXPECT_BEGIN_DATE, sExpectBeginDate);
         }
         String sExpectEndDate = getExpectEndDate();
         if(RString.isNotEmpty(sExpectEndDate)){
            config.set(PTY_EXPECT_END_DATE, sExpectEndDate);
         }
         String sBeginDate = getBeginDate();
         if(RString.isNotEmpty(sBeginDate)){
            config.set(PTY_BEGIN_DATE, sBeginDate);
         }
         String sEndDate = getEndDate();
         if(RString.isNotEmpty(sEndDate)){
            config.set(PTY_END_DATE, sEndDate);
         }
         String sPlanPersonCount = getPlanPersonCount();
         if(RString.isNotEmpty(sPlanPersonCount)){
            config.set(PTY_PLAN_PERSON_COUNT, sPlanPersonCount);
         }
         String sPlanPersonMonthCount = getPlanPersonMonthCount();
         if(RString.isNotEmpty(sPlanPersonMonthCount)){
            config.set(PTY_PLAN_PERSON_MONTH_COUNT, sPlanPersonMonthCount);
         }
         String sBackgroundNote = getBackgroundNote();
         if(RString.isNotEmpty(sBackgroundNote)){
            config.set(PTY_BACKGROUND_NOTE, sBackgroundNote);
         }
         String sTargetNote = getTargetNote();
         if(RString.isNotEmpty(sTargetNote)){
            config.set(PTY_TARGET_NOTE, sTargetNote);
         }
         String sScaleCd = getScaleCd();
         if(RString.isNotEmpty(sScaleCd)){
            config.set(PTY_SCALE_CD, sScaleCd);
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
         String sTypeCd = getTypeCd();
         if(RString.isNotEmpty(sTypeCd)){
            config.set(PTY_TYPE_CD, sTypeCd);
         }
         String sStatusCd = getStatusCd();
         if(RString.isNotEmpty(sStatusCd)){
            config.set(PTY_STATUS_CD, sStatusCd);
         }
         String sExpectBeginDate = getExpectBeginDate();
         if(RString.isNotEmpty(sExpectBeginDate)){
            config.set(PTY_EXPECT_BEGIN_DATE, sExpectBeginDate);
         }
         String sExpectEndDate = getExpectEndDate();
         if(RString.isNotEmpty(sExpectEndDate)){
            config.set(PTY_EXPECT_END_DATE, sExpectEndDate);
         }
         String sBeginDate = getBeginDate();
         if(RString.isNotEmpty(sBeginDate)){
            config.set(PTY_BEGIN_DATE, sBeginDate);
         }
         String sEndDate = getEndDate();
         if(RString.isNotEmpty(sEndDate)){
            config.set(PTY_END_DATE, sEndDate);
         }
         String sPlanPersonCount = getPlanPersonCount();
         if(RString.isNotEmpty(sPlanPersonCount)){
            config.set(PTY_PLAN_PERSON_COUNT, sPlanPersonCount);
         }
         String sPlanPersonMonthCount = getPlanPersonMonthCount();
         if(RString.isNotEmpty(sPlanPersonMonthCount)){
            config.set(PTY_PLAN_PERSON_MONTH_COUNT, sPlanPersonMonthCount);
         }
         String sBackgroundNote = getBackgroundNote();
         if(RString.isNotEmpty(sBackgroundNote)){
            config.set(PTY_BACKGROUND_NOTE, sBackgroundNote);
         }
         String sTargetNote = getTargetNote();
         if(RString.isNotEmpty(sTargetNote)){
            config.set(PTY_TARGET_NOTE, sTargetNote);
         }
         String sScaleCd = getScaleCd();
         if(RString.isNotEmpty(sScaleCd)){
            config.set(PTY_SCALE_CD, sScaleCd);
         }
      }
   }
}