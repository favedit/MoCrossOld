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
// <T>测试点对象的XML节点基类。</T>
//============================================================
public abstract class XBasePoint
      extends FXmlObject
      implements
         XObjectFace
{
   // 名称定义
   public static final String NAME = "Point";

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

   // 状态代码的名称定义
   public static final String PTY_STATUS_CD = "status_cd";

   // 预期开始时间的名称定义
   public static final String PTY_EXPECT_BEGIN_DATE = "expect_begin_date";

   // 预期结束时间的名称定义
   public static final String PTY_EXPECT_END_DATE = "expect_end_date";

   // 开始时间的名称定义
   public static final String PTY_BEGIN_DATE = "begin_date";

   // 结束时间的名称定义
   public static final String PTY_END_DATE = "end_date";

   // 创建人名称的名称定义
   public static final String PTY_CREATE_USER_NAME = "create_user_name";

   // 测试范围描述的名称定义
   public static final String PTY_SCOPE_NOTE = "scope_note";

   // 重要度的名称定义
   public static final String PTY_IMPORTANT_CD = "important_cd";

   // 优先级的名称定义
   public static final String PTY_PRIORITY_CD = "priority_cd";

   // 困难度的名称定义
   public static final String PTY_DIFFICULT_CD = "difficult_cd";

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

   // 状态代码的定义
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

   // 创建人名称的定义
   @AName("create_user_name")
   protected String _createUserName;

   // 测试范围描述的定义
   @AName("scope_note")
   protected String _scopeNote;

   // 重要度的定义
   @AName("important_cd")
   protected String _importantCd;

   // 优先级的定义
   @AName("priority_cd")
   protected String _priorityCd;

   // 困难度的定义
   @AName("difficult_cd")
   protected String _difficultCd;

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
   // <T>获得状态代码的内容。</T>
   //
   // @return 状态代码
   //============================================================
   public String getStatusCd(){
      return _statusCd;
   }

   //============================================================
   // <T>设置状态代码的内容。</T>
   //
   // @param value 状态代码
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
   // <T>获得测试范围描述的内容。</T>
   //
   // @return 测试范围描述
   //============================================================
   public String getScopeNote(){
      return _scopeNote;
   }

   //============================================================
   // <T>设置测试范围描述的内容。</T>
   //
   // @param value 测试范围描述
   //============================================================
   public void setScopeNote(String value){
      _scopeNote = value;
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
      }else if(PTY_CREATE_USER_NAME.equalsIgnoreCase(name)){
         return getCreateUserName();
      }else if(PTY_SCOPE_NOTE.equalsIgnoreCase(name)){
         return getScopeNote();
      }else if(PTY_IMPORTANT_CD.equalsIgnoreCase(name)){
         return getImportantCd();
      }else if(PTY_PRIORITY_CD.equalsIgnoreCase(name)){
         return getPriorityCd();
      }else if(PTY_DIFFICULT_CD.equalsIgnoreCase(name)){
         return getDifficultCd();
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
      }else if(PTY_CREATE_USER_NAME.equalsIgnoreCase(name)){
         setCreateUserName(value);
      }else if(PTY_SCOPE_NOTE.equalsIgnoreCase(name)){
         setScopeNote(value);
      }else if(PTY_IMPORTANT_CD.equalsIgnoreCase(name)){
         setImportantCd(value);
      }else if(PTY_PRIORITY_CD.equalsIgnoreCase(name)){
         setPriorityCd(value);
      }else if(PTY_DIFFICULT_CD.equalsIgnoreCase(name)){
         setDifficultCd(value);
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
         if(config.contains("create_user_name")){
            setCreateUserName(config.get(PTY_CREATE_USER_NAME));
         }
         if(config.contains("scope_note")){
            setScopeNote(config.get(PTY_SCOPE_NOTE));
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
         if(config.contains("create_user_name")){
            setCreateUserName(config.get(PTY_CREATE_USER_NAME));
         }
         if(config.contains("scope_note")){
            setScopeNote(config.get(PTY_SCOPE_NOTE));
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
         if(config.contains("create_user_name")){
            setCreateUserName(config.get(PTY_CREATE_USER_NAME));
         }
         if(config.contains("scope_note")){
            setScopeNote(config.get(PTY_SCOPE_NOTE));
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
         if(RString.isEmpty(getCreateUserName())){
            if(config.contains("create_user_name")){
               setCreateUserName(config.get(PTY_CREATE_USER_NAME));
            }
         }
         if(RString.isEmpty(getScopeNote())){
            if(config.contains("scope_note")){
               setScopeNote(config.get(PTY_SCOPE_NOTE));
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
         if(RString.isNotEmpty(getCreateUserName())){
            config.set(PTY_CREATE_USER_NAME, getCreateUserName());
         }
         if(RString.isNotEmpty(getScopeNote())){
            config.set(PTY_SCOPE_NOTE, getScopeNote());
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
         if(RString.isNotEmpty(getCreateUserName())){
            config.set(PTY_CREATE_USER_NAME, getCreateUserName());
         }
         if(RString.isNotEmpty(getScopeNote())){
            config.set(PTY_SCOPE_NOTE, getScopeNote());
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
         String sCreateUserName = getCreateUserName();
         if(RString.isNotEmpty(sCreateUserName)){
            config.set(PTY_CREATE_USER_NAME, sCreateUserName);
         }
         String sScopeNote = getScopeNote();
         if(RString.isNotEmpty(sScopeNote)){
            config.set(PTY_SCOPE_NOTE, sScopeNote);
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
      }else if(EXmlConfig.Default == type){
         String s_type = get_type();
         if(RString.isNotEmpty(s_type)){
            config.set(PTY__TYPE, s_type);
         }
         String sIsValid = getIsValid();
         if(RString.isNotEmpty(sIsValid)){
            config.set(PTY_IS_VALID, sIsValid);
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
         String sCreateUserName = getCreateUserName();
         if(RString.isNotEmpty(sCreateUserName)){
            config.set(PTY_CREATE_USER_NAME, sCreateUserName);
         }
         String sScopeNote = getScopeNote();
         if(RString.isNotEmpty(sScopeNote)){
            config.set(PTY_SCOPE_NOTE, sScopeNote);
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
      }
   }
}