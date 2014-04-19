package org.mo.data.connector.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.data.connector.common.XObjectFace;

//============================================================
// <T>数据链接对象的XML节点基类。</T>
//============================================================
public abstract class XBaseConnection
      extends FXmlObject
      implements
         XObjectFace
{
   // 名称定义
   public static final String NAME = "Connection";

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

   // 代码的名称定义
   public static final String PTY_CODE = "code";

   // 名称的名称定义
   public static final String PTY_NAME = "name";

   // 标签的名称定义
   public static final String PTY_LABEL = "label";

   // 有效性的名称定义
   public static final String PTY_IS_VALID = "is_valid";

   // 注释的名称定义
   public static final String PTY_NOTE = "note";

   // 账号的名称定义
   public static final String PTY_PASSPORT = "passport";

   // 密码的名称定义
   public static final String PTY_PASSWORD = "password";

   // 驱动名称的名称定义
   public static final String PTY_DRIVER_NAME = "driver_name";

   // 驱动类名称的名称定义
   public static final String PTY_DRIVER_CLASS = "driver_class";

   // 连接地址的名称定义
   public static final String PTY_URL = "url";

   // 代码的定义
   @AName("code")
   protected String _code;

   // 名称的定义
   @AName("name")
   protected String _name;

   // 标签的定义
   @AName("label")
   protected FMultiString _label = new FMultiString();

   // 有效性的定义
   @AName("is_valid")
   protected Boolean _isValid = Boolean.FALSE;

   // 注释的定义
   @AName("note")
   protected FMultiString _note = new FMultiString();

   // 账号的定义
   @AName("passport")
   protected String _passport;

   // 密码的定义
   @AName("password")
   protected String _password;

   // 驱动名称的定义
   @AName("driver_name")
   protected String _driverName;

   // 驱动类名称的定义
   @AName("driver_class")
   protected String _driverClass;

   // 连接地址的定义
   @AName("url")
   protected String _url;

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
   // <T>获得账号的内容。</T>
   //
   // @return 账号
   //============================================================
   public String getPassport(){
      return _passport;
   }

   //============================================================
   // <T>设置账号的内容。</T>
   //
   // @param value 账号
   //============================================================
   public void setPassport(String value){
      _passport = value;
   }

   //============================================================
   // <T>获得密码的内容。</T>
   //
   // @return 密码
   //============================================================
   public String getPassword(){
      return _password;
   }

   //============================================================
   // <T>设置密码的内容。</T>
   //
   // @param value 密码
   //============================================================
   public void setPassword(String value){
      _password = value;
   }

   //============================================================
   // <T>获得驱动名称的内容。</T>
   //
   // @return 驱动名称
   //============================================================
   public String getDriverName(){
      return _driverName;
   }

   //============================================================
   // <T>设置驱动名称的内容。</T>
   //
   // @param value 驱动名称
   //============================================================
   public void setDriverName(String value){
      _driverName = value;
   }

   //============================================================
   // <T>获得驱动类名称的内容。</T>
   //
   // @return 驱动类名称
   //============================================================
   public String getDriverClass(){
      return _driverClass;
   }

   //============================================================
   // <T>设置驱动类名称的内容。</T>
   //
   // @param value 驱动类名称
   //============================================================
   public void setDriverClass(String value){
      _driverClass = value;
   }

   //============================================================
   // <T>获得连接地址的内容。</T>
   //
   // @return 连接地址
   //============================================================
   public String getUrl(){
      return _url;
   }

   //============================================================
   // <T>设置连接地址的内容。</T>
   //
   // @param value 连接地址
   //============================================================
   public void setUrl(String value){
      _url = value;
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
      }else if(PTY_CODE.equalsIgnoreCase(name)){
         return getCode();
      }else if(PTY_NAME.equalsIgnoreCase(name)){
         return getName();
      }else if(PTY_LABEL.equalsIgnoreCase(name)){
         return getLabel();
      }else if(PTY_IS_VALID.equalsIgnoreCase(name)){
         return RBoolean.toString(getIsValid());
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         return getNote();
      }else if(PTY_PASSPORT.equalsIgnoreCase(name)){
         return getPassport();
      }else if(PTY_PASSWORD.equalsIgnoreCase(name)){
         return getPassword();
      }else if(PTY_DRIVER_NAME.equalsIgnoreCase(name)){
         return getDriverName();
      }else if(PTY_DRIVER_CLASS.equalsIgnoreCase(name)){
         return getDriverClass();
      }else if(PTY_URL.equalsIgnoreCase(name)){
         return getUrl();
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
      }else if(PTY_CODE.equalsIgnoreCase(name)){
         setCode(value);
      }else if(PTY_NAME.equalsIgnoreCase(name)){
         setName(value);
      }else if(PTY_LABEL.equalsIgnoreCase(name)){
         setLabel(value);
      }else if(PTY_IS_VALID.equalsIgnoreCase(name)){
         setIsValid(RBoolean.parse(value));
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         setNote(value);
      }else if(PTY_PASSPORT.equalsIgnoreCase(name)){
         setPassport(value);
      }else if(PTY_PASSWORD.equalsIgnoreCase(name)){
         setPassword(value);
      }else if(PTY_DRIVER_NAME.equalsIgnoreCase(name)){
         setDriverName(value);
      }else if(PTY_DRIVER_CLASS.equalsIgnoreCase(name)){
         setDriverClass(value);
      }else if(PTY_URL.equalsIgnoreCase(name)){
         setUrl(value);
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
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("label")){
            _label.unpack(config.get(PTY_LABEL));
         }
         if(config.contains("is_valid")){
            setIsValid(RBoolean.parse(config.get(PTY_IS_VALID)));
         }
         if(config.contains("note")){
            _note.unpack(config.get(PTY_NOTE));
         }
         if(config.contains("passport")){
            setPassport(config.get(PTY_PASSPORT));
         }
         if(config.contains("password")){
            setPassword(config.get(PTY_PASSWORD));
         }
         if(config.contains("driver_name")){
            setDriverName(config.get(PTY_DRIVER_NAME));
         }
         if(config.contains("driver_class")){
            setDriverClass(config.get(PTY_DRIVER_CLASS));
         }
         if(config.contains("url")){
            setUrl(config.get(PTY_URL));
         }
      }else if(EXmlConfig.Simple == type){
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
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
         if(config.contains("passport")){
            setPassport(config.get(PTY_PASSPORT));
         }
         if(config.contains("password")){
            setPassword(config.get(PTY_PASSWORD));
         }
         if(config.contains("driver_name")){
            setDriverName(config.get(PTY_DRIVER_NAME));
         }
         if(config.contains("driver_class")){
            setDriverClass(config.get(PTY_DRIVER_CLASS));
         }
         if(config.contains("url")){
            setUrl(config.get(PTY_URL));
         }
      }else if(EXmlConfig.Value == type){
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
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
         if(config.contains("passport")){
            setPassport(config.get(PTY_PASSPORT));
         }
         if(config.contains("password")){
            setPassword(config.get(PTY_PASSWORD));
         }
         if(config.contains("driver_name")){
            setDriverName(config.get(PTY_DRIVER_NAME));
         }
         if(config.contains("driver_class")){
            setDriverClass(config.get(PTY_DRIVER_CLASS));
         }
         if(config.contains("url")){
            setUrl(config.get(PTY_URL));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getCode())){
            if(config.contains("code")){
               setCode(config.get(PTY_CODE));
            }
         }
         if(RString.isEmpty(getPassport())){
            if(config.contains("passport")){
               setPassport(config.get(PTY_PASSPORT));
            }
         }
         if(RString.isEmpty(getPassword())){
            if(config.contains("password")){
               setPassword(config.get(PTY_PASSWORD));
            }
         }
         if(RString.isEmpty(getDriverName())){
            if(config.contains("driver_name")){
               setDriverName(config.get(PTY_DRIVER_NAME));
            }
         }
         if(RString.isEmpty(getDriverClass())){
            if(config.contains("driver_class")){
               setDriverClass(config.get(PTY_DRIVER_CLASS));
            }
         }
         if(RString.isEmpty(getUrl())){
            if(config.contains("url")){
               setUrl(config.get(PTY_URL));
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
         if(RString.isNotEmpty(getCode())){
            config.set(PTY_CODE, getCode());
         }
         if(RString.isNotEmpty(getName())){
            config.set(PTY_NAME, getName());
         }
         String label = _label.pack().toString();
         if(RString.isNotEmpty(label)){
            config.set(PTY_LABEL, label);
         }
         if(RBoolean.parse(getIsValid())){
            config.set(PTY_IS_VALID, RBoolean.toString(getIsValid()));
         }
         String note = _note.pack().toString();
         if(RString.isNotEmpty(note)){
            config.set(PTY_NOTE, note);
         }
         if(RString.isNotEmpty(getPassport())){
            config.set(PTY_PASSPORT, getPassport());
         }
         if(RString.isNotEmpty(getPassword())){
            config.set(PTY_PASSWORD, getPassword());
         }
         if(RString.isNotEmpty(getDriverName())){
            config.set(PTY_DRIVER_NAME, getDriverName());
         }
         if(RString.isNotEmpty(getDriverClass())){
            config.set(PTY_DRIVER_CLASS, getDriverClass());
         }
         if(RString.isNotEmpty(getUrl())){
            config.set(PTY_URL, getUrl());
         }
      }else if(EXmlConfig.Simple == type){
         if(RString.isNotEmpty(getCode())){
            config.set(PTY_CODE, getCode());
         }
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
         if(RString.isNotEmpty(getPassport())){
            config.set(PTY_PASSPORT, getPassport());
         }
         if(RString.isNotEmpty(getPassword())){
            config.set(PTY_PASSWORD, getPassword());
         }
         if(RString.isNotEmpty(getDriverName())){
            config.set(PTY_DRIVER_NAME, getDriverName());
         }
         if(RString.isNotEmpty(getDriverClass())){
            config.set(PTY_DRIVER_CLASS, getDriverClass());
         }
         if(RString.isNotEmpty(getUrl())){
            config.set(PTY_URL, getUrl());
         }
      }else if(EXmlConfig.Value == type){
         String sCode = getCode();
         if(RString.isNotEmpty(sCode)){
            config.set(PTY_CODE, sCode);
         }
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
         String sPassport = getPassport();
         if(RString.isNotEmpty(sPassport)){
            config.set(PTY_PASSPORT, sPassport);
         }
         String sPassword = getPassword();
         if(RString.isNotEmpty(sPassword)){
            config.set(PTY_PASSWORD, sPassword);
         }
         String sDriverName = getDriverName();
         if(RString.isNotEmpty(sDriverName)){
            config.set(PTY_DRIVER_NAME, sDriverName);
         }
         String sDriverClass = getDriverClass();
         if(RString.isNotEmpty(sDriverClass)){
            config.set(PTY_DRIVER_CLASS, sDriverClass);
         }
         String sUrl = getUrl();
         if(RString.isNotEmpty(sUrl)){
            config.set(PTY_URL, sUrl);
         }
      }else if(EXmlConfig.Default == type){
         String sCode = getCode();
         if(RString.isNotEmpty(sCode)){
            config.set(PTY_CODE, sCode);
         }
         String sPassport = getPassport();
         if(RString.isNotEmpty(sPassport)){
            config.set(PTY_PASSPORT, sPassport);
         }
         String sPassword = getPassword();
         if(RString.isNotEmpty(sPassword)){
            config.set(PTY_PASSWORD, sPassword);
         }
         String sDriverName = getDriverName();
         if(RString.isNotEmpty(sDriverName)){
            config.set(PTY_DRIVER_NAME, sDriverName);
         }
         String sDriverClass = getDriverClass();
         if(RString.isNotEmpty(sDriverClass)){
            config.set(PTY_DRIVER_CLASS, sDriverClass);
         }
         String sUrl = getUrl();
         if(RString.isNotEmpty(sUrl)){
            config.set(PTY_URL, sUrl);
         }
      }
   }
}