package org.mo.logic.schedule.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.logic.schedule.common.XObjectFace;

//============================================================
// <T>计划对象的XML节点基类。</T>
//============================================================
public abstract class XBaseSchedule
      extends FXmlObject
      implements
         XObjectFace
{
   // 名称定义
   public static final String NAME = "Schedule";

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

   // 计划状态的名称定义
   public static final String PTY_STATUS_CD = "status_cd";

   // 执行开始时间的名称定义
   public static final String PTY_BEGIN_DATE = "begin_date";

   // 执行结束时间的名称定义
   public static final String PTY_END_DATE = "end_date";

   // 用户名称的名称定义
   public static final String PTY_CREATE_USER_NAME = "create_user_name";

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

   // 计划状态的定义
   @AName("status_cd")
   protected String _statusCd;

   // 执行开始时间的定义
   @AName("begin_date")
   protected String _beginDate;

   // 执行结束时间的定义
   @AName("end_date")
   protected String _endDate;

   // 用户名称的定义
   @AName("create_user_name")
   protected String _createUserName;

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
   // <T>获得计划状态的内容。</T>
   //
   // @return 计划状态
   //============================================================
   public String getStatusCd(){
      return _statusCd;
   }

   //============================================================
   // <T>设置计划状态的内容。</T>
   //
   // @param value 计划状态
   //============================================================
   public void setStatusCd(String value){
      _statusCd = value;
   }

   //============================================================
   // <T>获得执行开始时间的内容。</T>
   //
   // @return 执行开始时间
   //============================================================
   public String getBeginDate(){
      return _beginDate;
   }

   //============================================================
   // <T>设置执行开始时间的内容。</T>
   //
   // @param value 执行开始时间
   //============================================================
   public void setBeginDate(String value){
      _beginDate = value;
   }

   //============================================================
   // <T>获得执行结束时间的内容。</T>
   //
   // @return 执行结束时间
   //============================================================
   public String getEndDate(){
      return _endDate;
   }

   //============================================================
   // <T>设置执行结束时间的内容。</T>
   //
   // @param value 执行结束时间
   //============================================================
   public void setEndDate(String value){
      _endDate = value;
   }

   //============================================================
   // <T>获得用户名称的内容。</T>
   //
   // @return 用户名称
   //============================================================
   public String getCreateUserName(){
      return _createUserName;
   }

   //============================================================
   // <T>设置用户名称的内容。</T>
   //
   // @param value 用户名称
   //============================================================
   public void setCreateUserName(String value){
      _createUserName = value;
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
      }else if(PTY_BEGIN_DATE.equalsIgnoreCase(name)){
         return getBeginDate();
      }else if(PTY_END_DATE.equalsIgnoreCase(name)){
         return getEndDate();
      }else if(PTY_CREATE_USER_NAME.equalsIgnoreCase(name)){
         return getCreateUserName();
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
      }else if(PTY_BEGIN_DATE.equalsIgnoreCase(name)){
         setBeginDate(value);
      }else if(PTY_END_DATE.equalsIgnoreCase(name)){
         setEndDate(value);
      }else if(PTY_CREATE_USER_NAME.equalsIgnoreCase(name)){
         setCreateUserName(value);
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
         if(config.contains("begin_date")){
            setBeginDate(config.get(PTY_BEGIN_DATE));
         }
         if(config.contains("end_date")){
            setEndDate(config.get(PTY_END_DATE));
         }
         if(config.contains("create_user_name")){
            setCreateUserName(config.get(PTY_CREATE_USER_NAME));
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
         if(config.contains("begin_date")){
            setBeginDate(config.get(PTY_BEGIN_DATE));
         }
         if(config.contains("end_date")){
            setEndDate(config.get(PTY_END_DATE));
         }
         if(config.contains("create_user_name")){
            setCreateUserName(config.get(PTY_CREATE_USER_NAME));
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
         if(config.contains("begin_date")){
            setBeginDate(config.get(PTY_BEGIN_DATE));
         }
         if(config.contains("end_date")){
            setEndDate(config.get(PTY_END_DATE));
         }
         if(config.contains("create_user_name")){
            setCreateUserName(config.get(PTY_CREATE_USER_NAME));
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
         if(RString.isNotEmpty(getBeginDate())){
            config.set(PTY_BEGIN_DATE, getBeginDate());
         }
         if(RString.isNotEmpty(getEndDate())){
            config.set(PTY_END_DATE, getEndDate());
         }
         if(RString.isNotEmpty(getCreateUserName())){
            config.set(PTY_CREATE_USER_NAME, getCreateUserName());
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
         if(RString.isNotEmpty(getBeginDate())){
            config.set(PTY_BEGIN_DATE, getBeginDate());
         }
         if(RString.isNotEmpty(getEndDate())){
            config.set(PTY_END_DATE, getEndDate());
         }
         if(RString.isNotEmpty(getCreateUserName())){
            config.set(PTY_CREATE_USER_NAME, getCreateUserName());
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
      }
   }
}