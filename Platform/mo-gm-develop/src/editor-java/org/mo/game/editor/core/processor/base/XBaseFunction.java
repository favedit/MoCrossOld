package org.mo.game.editor.core.processor.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.game.editor.core.processor.common.XObjectFace;

//============================================================
// <T>方法对象的XML节点基类。</T>
//============================================================
public abstract class XBaseFunction
      extends FXmlObject
      implements
         XObjectFace
{
   // 名称定义
   public static final String NAME = "Function";

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

   // 有效性的名称定义
   public static final String PTY_IS_VALID = "is_valid";

   // 注释的名称定义
   public static final String PTY_NOTE = "note";

   // 类型的名称定义
   public static final String PTY_TYPE = "type";

   // 内容的名称定义
   public static final String PTY_VALUE = "value";

   // 代码的名称定义
   public static final String PTY_CODE = "code";

   // 代码名称的名称定义
   public static final String PTY_FULL_CODE = "full_code";

   // 代码来源的名称定义
   public static final String PTY_SOURCE_FROM = "source_from";

   // 代码目标的名称定义
   public static final String PTY_SOURCE_TARGET = "source_target";

   // 异步标志的名称定义
   public static final String PTY_IS_ASYNCHRONOUS = "is_asynchronous";

   // 会话标志的名称定义
   public static final String PTY_IS_SESSION = "is_session";

   // 账号登录的名称定义
   public static final String PTY_IS_LOGIN = "is_login";

   // 角色登录的名称定义
   public static final String PTY_IS_LOGIN_ROLE = "is_login_role";

   // 管理标志的名称定义
   public static final String PTY_IS_MANAGE = "is_manage";

   // 名称的定义
   @AName("name")
   protected String _name;

   // 标签的定义
   @AName("label")
   protected String _label;

   // 有效性的定义
   @AName("is_valid")
   protected Boolean _isValid = Boolean.FALSE;

   // 注释的定义
   @AName("note")
   protected FMultiString _note = new FMultiString();

   // 类型的定义
   @AName("type")
   protected String _type;

   // 内容的定义
   @AName("value")
   protected String _value;

   // 代码的定义
   @AName("code")
   protected String _code;

   // 代码名称的定义
   @AName("full_code")
   protected String _fullCode;

   // 代码来源的定义
   @AName("source_from")
   protected String _sourceFrom;

   // 代码目标的定义
   @AName("source_target")
   protected String _sourceTarget;

   // 异步标志的定义
   @AName("is_asynchronous")
   protected String _isAsynchronous;

   // 会话标志的定义
   @AName("is_session")
   protected String _isSession;

   // 账号登录的定义
   @AName("is_login")
   protected String _isLogin;

   // 角色登录的定义
   @AName("is_login_role")
   protected String _isLoginRole;

   // 管理标志的定义
   @AName("is_manage")
   protected String _isManage;

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
      return _label;
   }

   //============================================================
   // <T>设置标签的内容。</T>
   //
   // @param value 标签
   //============================================================
   public void setLabel(String value){
      _label = value;
   }

   //============================================================
   // <T>获得有效性的内容。</T>
   //
   // @return 有效性
   //============================================================
   public Boolean getIsValid(){
      return _isValid;
   }

   //============================================================
   // <T>设置有效性的内容。</T>
   //
   // @param value 有效性
   //============================================================
   public void setIsValid(Boolean value){
      _isValid = value;
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
   public String getType(){
      return _type;
   }

   //============================================================
   // <T>设置类型的内容。</T>
   //
   // @param value 类型
   //============================================================
   public void setType(String value){
      _type = value;
   }

   //============================================================
   // <T>获得内容的内容。</T>
   //
   // @return 内容
   //============================================================
   public String getValue(){
      return _value;
   }

   //============================================================
   // <T>设置内容的内容。</T>
   //
   // @param value 内容
   //============================================================
   public void setValue(String value){
      _value = value;
   }

   //============================================================
   // <T>获得代码的内容。</T>
   //
   // @return 代码
   //============================================================
   public String getCode(){
      return _code;
   }

   //============================================================
   // <T>设置代码的内容。</T>
   //
   // @param value 代码
   //============================================================
   public void setCode(String value){
      _code = value;
   }

   //============================================================
   // <T>获得代码名称的内容。</T>
   //
   // @return 代码名称
   //============================================================
   public String getFullCode(){
      return _fullCode;
   }

   //============================================================
   // <T>设置代码名称的内容。</T>
   //
   // @param value 代码名称
   //============================================================
   public void setFullCode(String value){
      _fullCode = value;
   }

   //============================================================
   // <T>获得代码来源的内容。</T>
   //
   // @return 代码来源
   //============================================================
   public String getSourceFrom(){
      return _sourceFrom;
   }

   //============================================================
   // <T>设置代码来源的内容。</T>
   //
   // @param value 代码来源
   //============================================================
   public void setSourceFrom(String value){
      _sourceFrom = value;
   }

   //============================================================
   // <T>获得代码目标的内容。</T>
   //
   // @return 代码目标
   //============================================================
   public String getSourceTarget(){
      return _sourceTarget;
   }

   //============================================================
   // <T>设置代码目标的内容。</T>
   //
   // @param value 代码目标
   //============================================================
   public void setSourceTarget(String value){
      _sourceTarget = value;
   }

   //============================================================
   // <T>获得异步标志的内容。</T>
   //
   // @return 异步标志
   //============================================================
   public String getIsAsynchronous(){
      return _isAsynchronous;
   }

   //============================================================
   // <T>设置异步标志的内容。</T>
   //
   // @param value 异步标志
   //============================================================
   public void setIsAsynchronous(String value){
      _isAsynchronous = value;
   }

   //============================================================
   // <T>获得会话标志的内容。</T>
   //
   // @return 会话标志
   //============================================================
   public String getIsSession(){
      return _isSession;
   }

   //============================================================
   // <T>设置会话标志的内容。</T>
   //
   // @param value 会话标志
   //============================================================
   public void setIsSession(String value){
      _isSession = value;
   }

   //============================================================
   // <T>获得账号登录的内容。</T>
   //
   // @return 账号登录
   //============================================================
   public String getIsLogin(){
      return _isLogin;
   }

   //============================================================
   // <T>设置账号登录的内容。</T>
   //
   // @param value 账号登录
   //============================================================
   public void setIsLogin(String value){
      _isLogin = value;
   }

   //============================================================
   // <T>获得角色登录的内容。</T>
   //
   // @return 角色登录
   //============================================================
   public String getIsLoginRole(){
      return _isLoginRole;
   }

   //============================================================
   // <T>设置角色登录的内容。</T>
   //
   // @param value 角色登录
   //============================================================
   public void setIsLoginRole(String value){
      _isLoginRole = value;
   }

   //============================================================
   // <T>获得管理标志的内容。</T>
   //
   // @return 管理标志
   //============================================================
   public String getIsManage(){
      return _isManage;
   }

   //============================================================
   // <T>设置管理标志的内容。</T>
   //
   // @param value 管理标志
   //============================================================
   public void setIsManage(String value){
      _isManage = value;
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
      }else if(PTY_IS_VALID.equalsIgnoreCase(name)){
         return RBoolean.toString(getIsValid());
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         return getNote();
      }else if(PTY_TYPE.equalsIgnoreCase(name)){
         return getType();
      }else if(PTY_VALUE.equalsIgnoreCase(name)){
         return getValue();
      }else if(PTY_CODE.equalsIgnoreCase(name)){
         return getCode();
      }else if(PTY_FULL_CODE.equalsIgnoreCase(name)){
         return getFullCode();
      }else if(PTY_SOURCE_FROM.equalsIgnoreCase(name)){
         return getSourceFrom();
      }else if(PTY_SOURCE_TARGET.equalsIgnoreCase(name)){
         return getSourceTarget();
      }else if(PTY_IS_ASYNCHRONOUS.equalsIgnoreCase(name)){
         return getIsAsynchronous();
      }else if(PTY_IS_SESSION.equalsIgnoreCase(name)){
         return getIsSession();
      }else if(PTY_IS_LOGIN.equalsIgnoreCase(name)){
         return getIsLogin();
      }else if(PTY_IS_LOGIN_ROLE.equalsIgnoreCase(name)){
         return getIsLoginRole();
      }else if(PTY_IS_MANAGE.equalsIgnoreCase(name)){
         return getIsManage();
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
      }else if(PTY_IS_VALID.equalsIgnoreCase(name)){
         setIsValid(RBoolean.parse(value));
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         setNote(value);
      }else if(PTY_TYPE.equalsIgnoreCase(name)){
         setType(value);
      }else if(PTY_VALUE.equalsIgnoreCase(name)){
         setValue(value);
      }else if(PTY_CODE.equalsIgnoreCase(name)){
         setCode(value);
      }else if(PTY_FULL_CODE.equalsIgnoreCase(name)){
         setFullCode(value);
      }else if(PTY_SOURCE_FROM.equalsIgnoreCase(name)){
         setSourceFrom(value);
      }else if(PTY_SOURCE_TARGET.equalsIgnoreCase(name)){
         setSourceTarget(value);
      }else if(PTY_IS_ASYNCHRONOUS.equalsIgnoreCase(name)){
         setIsAsynchronous(value);
      }else if(PTY_IS_SESSION.equalsIgnoreCase(name)){
         setIsSession(value);
      }else if(PTY_IS_LOGIN.equalsIgnoreCase(name)){
         setIsLogin(value);
      }else if(PTY_IS_LOGIN_ROLE.equalsIgnoreCase(name)){
         setIsLoginRole(value);
      }else if(PTY_IS_MANAGE.equalsIgnoreCase(name)){
         setIsManage(value);
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
            setLabel(config.get(PTY_LABEL));
         }
         if(config.contains("is_valid")){
            setIsValid(RBoolean.parse(config.get(PTY_IS_VALID)));
         }
         if(config.contains("note")){
            _note.unpack(config.get(PTY_NOTE));
         }
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
         }
         if(config.contains("value")){
            setValue(config.get(PTY_VALUE));
         }
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
         if(config.contains("full_code")){
            setFullCode(config.get(PTY_FULL_CODE));
         }
         if(config.contains("source_from")){
            setSourceFrom(config.get(PTY_SOURCE_FROM));
         }
         if(config.contains("source_target")){
            setSourceTarget(config.get(PTY_SOURCE_TARGET));
         }
         if(config.contains("is_asynchronous")){
            setIsAsynchronous(config.get(PTY_IS_ASYNCHRONOUS));
         }
         if(config.contains("is_session")){
            setIsSession(config.get(PTY_IS_SESSION));
         }
         if(config.contains("is_login")){
            setIsLogin(config.get(PTY_IS_LOGIN));
         }
         if(config.contains("is_login_role")){
            setIsLoginRole(config.get(PTY_IS_LOGIN_ROLE));
         }
         if(config.contains("is_manage")){
            setIsManage(config.get(PTY_IS_MANAGE));
         }
      }else if(EXmlConfig.Simple == type){
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("label")){
            setLabel(config.get(PTY_LABEL));
         }
         if(config.contains("is_valid")){
            setIsValid(RBoolean.parse(config.get(PTY_IS_VALID)));
         }
         if(config.contains("note")){
            setNote(config.get(PTY_NOTE));
         }
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
         }
         if(config.contains("value")){
            setValue(config.get(PTY_VALUE));
         }
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
         if(config.contains("full_code")){
            setFullCode(config.get(PTY_FULL_CODE));
         }
         if(config.contains("source_from")){
            setSourceFrom(config.get(PTY_SOURCE_FROM));
         }
         if(config.contains("source_target")){
            setSourceTarget(config.get(PTY_SOURCE_TARGET));
         }
         if(config.contains("is_asynchronous")){
            setIsAsynchronous(config.get(PTY_IS_ASYNCHRONOUS));
         }
         if(config.contains("is_session")){
            setIsSession(config.get(PTY_IS_SESSION));
         }
         if(config.contains("is_login")){
            setIsLogin(config.get(PTY_IS_LOGIN));
         }
         if(config.contains("is_login_role")){
            setIsLoginRole(config.get(PTY_IS_LOGIN_ROLE));
         }
         if(config.contains("is_manage")){
            setIsManage(config.get(PTY_IS_MANAGE));
         }
      }else if(EXmlConfig.Value == type){
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("label")){
            setLabel(config.get(PTY_LABEL));
         }
         if(config.contains("is_valid")){
            setIsValid(RBoolean.parse(config.get(PTY_IS_VALID)));
         }
         if(config.contains("note")){
            setNote(config.get(PTY_NOTE));
         }
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
         }
         if(config.contains("value")){
            setValue(config.get(PTY_VALUE));
         }
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
         if(config.contains("full_code")){
            setFullCode(config.get(PTY_FULL_CODE));
         }
         if(config.contains("source_from")){
            setSourceFrom(config.get(PTY_SOURCE_FROM));
         }
         if(config.contains("source_target")){
            setSourceTarget(config.get(PTY_SOURCE_TARGET));
         }
         if(config.contains("is_asynchronous")){
            setIsAsynchronous(config.get(PTY_IS_ASYNCHRONOUS));
         }
         if(config.contains("is_session")){
            setIsSession(config.get(PTY_IS_SESSION));
         }
         if(config.contains("is_login")){
            setIsLogin(config.get(PTY_IS_LOGIN));
         }
         if(config.contains("is_login_role")){
            setIsLoginRole(config.get(PTY_IS_LOGIN_ROLE));
         }
         if(config.contains("is_manage")){
            setIsManage(config.get(PTY_IS_MANAGE));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getCode())){
            if(config.contains("code")){
               setCode(config.get(PTY_CODE));
            }
         }
         if(RString.isEmpty(getFullCode())){
            if(config.contains("full_code")){
               setFullCode(config.get(PTY_FULL_CODE));
            }
         }
         if(RString.isEmpty(getSourceFrom())){
            if(config.contains("source_from")){
               setSourceFrom(config.get(PTY_SOURCE_FROM));
            }
         }
         if(RString.isEmpty(getSourceTarget())){
            if(config.contains("source_target")){
               setSourceTarget(config.get(PTY_SOURCE_TARGET));
            }
         }
         if(RString.isEmpty(getIsAsynchronous())){
            if(config.contains("is_asynchronous")){
               setIsAsynchronous(config.get(PTY_IS_ASYNCHRONOUS));
            }
         }
         if(RString.isEmpty(getIsSession())){
            if(config.contains("is_session")){
               setIsSession(config.get(PTY_IS_SESSION));
            }
         }
         if(RString.isEmpty(getIsLogin())){
            if(config.contains("is_login")){
               setIsLogin(config.get(PTY_IS_LOGIN));
            }
         }
         if(RString.isEmpty(getIsLoginRole())){
            if(config.contains("is_login_role")){
               setIsLoginRole(config.get(PTY_IS_LOGIN_ROLE));
            }
         }
         if(RString.isEmpty(getIsManage())){
            if(config.contains("is_manage")){
               setIsManage(config.get(PTY_IS_MANAGE));
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
         if(RString.isNotEmpty(getLabel())){
            config.set(PTY_LABEL, getLabel());
         }
         if(RBoolean.parse(getIsValid())){
            config.set(PTY_IS_VALID, RBoolean.toString(getIsValid()));
         }
         String note = _note.pack().toString();
         if(RString.isNotEmpty(note)){
            config.set(PTY_NOTE, note);
         }
         if(RString.isNotEmpty(getType())){
            config.set(PTY_TYPE, getType());
         }
         if(RString.isNotEmpty(getValue())){
            config.set(PTY_VALUE, getValue());
         }
         if(RString.isNotEmpty(getCode())){
            config.set(PTY_CODE, getCode());
         }
         if(RString.isNotEmpty(getFullCode())){
            config.set(PTY_FULL_CODE, getFullCode());
         }
         if(RString.isNotEmpty(getSourceFrom())){
            config.set(PTY_SOURCE_FROM, getSourceFrom());
         }
         if(RString.isNotEmpty(getSourceTarget())){
            config.set(PTY_SOURCE_TARGET, getSourceTarget());
         }
         if(RString.isNotEmpty(getIsAsynchronous())){
            config.set(PTY_IS_ASYNCHRONOUS, getIsAsynchronous());
         }
         if(RString.isNotEmpty(getIsSession())){
            config.set(PTY_IS_SESSION, getIsSession());
         }
         if(RString.isNotEmpty(getIsLogin())){
            config.set(PTY_IS_LOGIN, getIsLogin());
         }
         if(RString.isNotEmpty(getIsLoginRole())){
            config.set(PTY_IS_LOGIN_ROLE, getIsLoginRole());
         }
         if(RString.isNotEmpty(getIsManage())){
            config.set(PTY_IS_MANAGE, getIsManage());
         }
      }else if(EXmlConfig.Simple == type){
         if(RString.isNotEmpty(getName())){
            config.set(PTY_NAME, getName());
         }
         if(RString.isNotEmpty(getLabel())){
            config.set(PTY_LABEL, getLabel());
         }
         if(RBoolean.parse(getIsValid())){
            config.set(PTY_IS_VALID, RBoolean.toString(getIsValid()));
         }
         if(RString.isNotEmpty(getNote())){
            config.set(PTY_NOTE, getNote());
         }
         if(RString.isNotEmpty(getType())){
            config.set(PTY_TYPE, getType());
         }
         if(RString.isNotEmpty(getValue())){
            config.set(PTY_VALUE, getValue());
         }
         if(RString.isNotEmpty(getCode())){
            config.set(PTY_CODE, getCode());
         }
         if(RString.isNotEmpty(getFullCode())){
            config.set(PTY_FULL_CODE, getFullCode());
         }
         if(RString.isNotEmpty(getSourceFrom())){
            config.set(PTY_SOURCE_FROM, getSourceFrom());
         }
         if(RString.isNotEmpty(getSourceTarget())){
            config.set(PTY_SOURCE_TARGET, getSourceTarget());
         }
         if(RString.isNotEmpty(getIsAsynchronous())){
            config.set(PTY_IS_ASYNCHRONOUS, getIsAsynchronous());
         }
         if(RString.isNotEmpty(getIsSession())){
            config.set(PTY_IS_SESSION, getIsSession());
         }
         if(RString.isNotEmpty(getIsLogin())){
            config.set(PTY_IS_LOGIN, getIsLogin());
         }
         if(RString.isNotEmpty(getIsLoginRole())){
            config.set(PTY_IS_LOGIN_ROLE, getIsLoginRole());
         }
         if(RString.isNotEmpty(getIsManage())){
            config.set(PTY_IS_MANAGE, getIsManage());
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
         Boolean bIsValid = getIsValid();
         if(RBoolean.parse(bIsValid)){
            config.set(PTY_IS_VALID, RBoolean.toString(bIsValid));
         }
         String sNote = getNote();
         if(RString.isNotEmpty(sNote)){
            config.set(PTY_NOTE, sNote);
         }
         String sType = getType();
         if(RString.isNotEmpty(sType)){
            config.set(PTY_TYPE, sType);
         }
         String sValue = getValue();
         if(RString.isNotEmpty(sValue)){
            config.set(PTY_VALUE, sValue);
         }
         String sCode = getCode();
         if(RString.isNotEmpty(sCode)){
            config.set(PTY_CODE, sCode);
         }
         String sFullCode = getFullCode();
         if(RString.isNotEmpty(sFullCode)){
            config.set(PTY_FULL_CODE, sFullCode);
         }
         String sSourceFrom = getSourceFrom();
         if(RString.isNotEmpty(sSourceFrom)){
            config.set(PTY_SOURCE_FROM, sSourceFrom);
         }
         String sSourceTarget = getSourceTarget();
         if(RString.isNotEmpty(sSourceTarget)){
            config.set(PTY_SOURCE_TARGET, sSourceTarget);
         }
         String sIsAsynchronous = getIsAsynchronous();
         if(RString.isNotEmpty(sIsAsynchronous)){
            config.set(PTY_IS_ASYNCHRONOUS, sIsAsynchronous);
         }
         String sIsSession = getIsSession();
         if(RString.isNotEmpty(sIsSession)){
            config.set(PTY_IS_SESSION, sIsSession);
         }
         String sIsLogin = getIsLogin();
         if(RString.isNotEmpty(sIsLogin)){
            config.set(PTY_IS_LOGIN, sIsLogin);
         }
         String sIsLoginRole = getIsLoginRole();
         if(RString.isNotEmpty(sIsLoginRole)){
            config.set(PTY_IS_LOGIN_ROLE, sIsLoginRole);
         }
         String sIsManage = getIsManage();
         if(RString.isNotEmpty(sIsManage)){
            config.set(PTY_IS_MANAGE, sIsManage);
         }
      }else if(EXmlConfig.Default == type){
         String sCode = getCode();
         if(RString.isNotEmpty(sCode)){
            config.set(PTY_CODE, sCode);
         }
         String sFullCode = getFullCode();
         if(RString.isNotEmpty(sFullCode)){
            config.set(PTY_FULL_CODE, sFullCode);
         }
         String sSourceFrom = getSourceFrom();
         if(RString.isNotEmpty(sSourceFrom)){
            config.set(PTY_SOURCE_FROM, sSourceFrom);
         }
         String sSourceTarget = getSourceTarget();
         if(RString.isNotEmpty(sSourceTarget)){
            config.set(PTY_SOURCE_TARGET, sSourceTarget);
         }
         String sIsAsynchronous = getIsAsynchronous();
         if(RString.isNotEmpty(sIsAsynchronous)){
            config.set(PTY_IS_ASYNCHRONOUS, sIsAsynchronous);
         }
         String sIsSession = getIsSession();
         if(RString.isNotEmpty(sIsSession)){
            config.set(PTY_IS_SESSION, sIsSession);
         }
         String sIsLogin = getIsLogin();
         if(RString.isNotEmpty(sIsLogin)){
            config.set(PTY_IS_LOGIN, sIsLogin);
         }
         String sIsLoginRole = getIsLoginRole();
         if(RString.isNotEmpty(sIsLoginRole)){
            config.set(PTY_IS_LOGIN_ROLE, sIsLoginRole);
         }
         String sIsManage = getIsManage();
         if(RString.isNotEmpty(sIsManage)){
            config.set(PTY_IS_MANAGE, sIsManage);
         }
      }
   }
}