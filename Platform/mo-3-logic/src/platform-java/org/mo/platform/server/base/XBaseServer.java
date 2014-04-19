package org.mo.platform.server.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.platform.server.common.XObjectFace;

//============================================================
// <T>服务器对象的XML节点基类。</T>
//============================================================
public abstract class XBaseServer
      extends FXmlObject
      implements
         XObjectFace
{
   // 名称定义
   public static final String NAME = "Server";

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

   // 主机名称的名称定义
   public static final String PTY_HOST = "host";

   // 主机端口的名称定义
   public static final String PTY_PORT = "port";

   // 用户的名称定义
   public static final String PTY_USER = "user";

   // 标志的名称定义
   public static final String PTY_FLAG = "flag";

   // 路径的名称定义
   public static final String PTY_PATH = "path";

   // 服务器编号的名称定义
   public static final String PTY_SERVER_ID = "server_id";

   // 资源来源地址的名称定义
   public static final String PTY_PACKAGE_URL = "package_url";

   // 资源名称的名称定义
   public static final String PTY_PACKAGE_NAME = "package_name";

   // 对外端口的名称定义
   public static final String PTY_CLIENT_PORT = "client_port";

   // 数据库用户名的名称定义
   public static final String PTY_DATABASE_PASSPORT = "database_passport";

   // 数据库密码的名称定义
   public static final String PTY_DATABASE_PASSWORD = "database_password";

   // 数据库连接地址的名称定义
   public static final String PTY_DATABASE_HOST = "database_host";

   // 数据库连接端口的名称定义
   public static final String PTY_DATABASE_PORT = "database_port";

   // 管理员邮箱的名称定义
   public static final String PTY_ADMIN_EMAIL = "admin_email";

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

   // 主机名称的定义
   @AName("host")
   protected String _host;

   // 主机端口的定义
   @AName("port")
   protected String _port;

   // 用户的定义
   @AName("user")
   protected String _user;

   // 标志的定义
   @AName("flag")
   protected String _flag;

   // 路径的定义
   @AName("path")
   protected String _path;

   // 服务器编号的定义
   @AName("server_id")
   protected String _serverId;

   // 资源来源地址的定义
   @AName("package_url")
   protected String _packageUrl;

   // 资源名称的定义
   @AName("package_name")
   protected String _packageName;

   // 对外端口的定义
   @AName("client_port")
   protected String _clientPort;

   // 数据库用户名的定义
   @AName("database_passport")
   protected String _databasePassport;

   // 数据库密码的定义
   @AName("database_password")
   protected String _databasePassword;

   // 数据库连接地址的定义
   @AName("database_host")
   protected String _databaseHost;

   // 数据库连接端口的定义
   @AName("database_port")
   protected String _databasePort;

   // 管理员邮箱的定义
   @AName("admin_email")
   protected String _adminEmail;

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
   // <T>获得主机名称的内容。</T>
   //
   // @return 主机名称
   //============================================================
   public String getHost(){
      return _host;
   }

   //============================================================
   // <T>设置主机名称的内容。</T>
   //
   // @param value 主机名称
   //============================================================
   public void setHost(String value){
      _host = value;
   }

   //============================================================
   // <T>获得主机端口的内容。</T>
   //
   // @return 主机端口
   //============================================================
   public String getPort(){
      return _port;
   }

   //============================================================
   // <T>设置主机端口的内容。</T>
   //
   // @param value 主机端口
   //============================================================
   public void setPort(String value){
      _port = value;
   }

   //============================================================
   // <T>获得用户的内容。</T>
   //
   // @return 用户
   //============================================================
   public String getUser(){
      return _user;
   }

   //============================================================
   // <T>设置用户的内容。</T>
   //
   // @param value 用户
   //============================================================
   public void setUser(String value){
      _user = value;
   }

   //============================================================
   // <T>获得标志的内容。</T>
   //
   // @return 标志
   //============================================================
   public String getFlag(){
      return _flag;
   }

   //============================================================
   // <T>设置标志的内容。</T>
   //
   // @param value 标志
   //============================================================
   public void setFlag(String value){
      _flag = value;
   }

   //============================================================
   // <T>获得路径的内容。</T>
   //
   // @return 路径
   //============================================================
   public String getPath(){
      return _path;
   }

   //============================================================
   // <T>设置路径的内容。</T>
   //
   // @param value 路径
   //============================================================
   public void setPath(String value){
      _path = value;
   }

   //============================================================
   // <T>获得服务器编号的内容。</T>
   //
   // @return 服务器编号
   //============================================================
   public String getServerId(){
      return _serverId;
   }

   //============================================================
   // <T>设置服务器编号的内容。</T>
   //
   // @param value 服务器编号
   //============================================================
   public void setServerId(String value){
      _serverId = value;
   }

   //============================================================
   // <T>获得资源来源地址的内容。</T>
   //
   // @return 资源来源地址
   //============================================================
   public String getPackageUrl(){
      return _packageUrl;
   }

   //============================================================
   // <T>设置资源来源地址的内容。</T>
   //
   // @param value 资源来源地址
   //============================================================
   public void setPackageUrl(String value){
      _packageUrl = value;
   }

   //============================================================
   // <T>获得资源名称的内容。</T>
   //
   // @return 资源名称
   //============================================================
   public String getPackageName(){
      return _packageName;
   }

   //============================================================
   // <T>设置资源名称的内容。</T>
   //
   // @param value 资源名称
   //============================================================
   public void setPackageName(String value){
      _packageName = value;
   }

   //============================================================
   // <T>获得对外端口的内容。</T>
   //
   // @return 对外端口
   //============================================================
   public String getClientPort(){
      return _clientPort;
   }

   //============================================================
   // <T>设置对外端口的内容。</T>
   //
   // @param value 对外端口
   //============================================================
   public void setClientPort(String value){
      _clientPort = value;
   }

   //============================================================
   // <T>获得数据库用户名的内容。</T>
   //
   // @return 数据库用户名
   //============================================================
   public String getDatabasePassport(){
      return _databasePassport;
   }

   //============================================================
   // <T>设置数据库用户名的内容。</T>
   //
   // @param value 数据库用户名
   //============================================================
   public void setDatabasePassport(String value){
      _databasePassport = value;
   }

   //============================================================
   // <T>获得数据库密码的内容。</T>
   //
   // @return 数据库密码
   //============================================================
   public String getDatabasePassword(){
      return _databasePassword;
   }

   //============================================================
   // <T>设置数据库密码的内容。</T>
   //
   // @param value 数据库密码
   //============================================================
   public void setDatabasePassword(String value){
      _databasePassword = value;
   }

   //============================================================
   // <T>获得数据库连接地址的内容。</T>
   //
   // @return 数据库连接地址
   //============================================================
   public String getDatabaseHost(){
      return _databaseHost;
   }

   //============================================================
   // <T>设置数据库连接地址的内容。</T>
   //
   // @param value 数据库连接地址
   //============================================================
   public void setDatabaseHost(String value){
      _databaseHost = value;
   }

   //============================================================
   // <T>获得数据库连接端口的内容。</T>
   //
   // @return 数据库连接端口
   //============================================================
   public String getDatabasePort(){
      return _databasePort;
   }

   //============================================================
   // <T>设置数据库连接端口的内容。</T>
   //
   // @param value 数据库连接端口
   //============================================================
   public void setDatabasePort(String value){
      _databasePort = value;
   }

   //============================================================
   // <T>获得管理员邮箱的内容。</T>
   //
   // @return 管理员邮箱
   //============================================================
   public String getAdminEmail(){
      return _adminEmail;
   }

   //============================================================
   // <T>设置管理员邮箱的内容。</T>
   //
   // @param value 管理员邮箱
   //============================================================
   public void setAdminEmail(String value){
      _adminEmail = value;
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
      }else if(PTY_HOST.equalsIgnoreCase(name)){
         return getHost();
      }else if(PTY_PORT.equalsIgnoreCase(name)){
         return getPort();
      }else if(PTY_USER.equalsIgnoreCase(name)){
         return getUser();
      }else if(PTY_FLAG.equalsIgnoreCase(name)){
         return getFlag();
      }else if(PTY_PATH.equalsIgnoreCase(name)){
         return getPath();
      }else if(PTY_SERVER_ID.equalsIgnoreCase(name)){
         return getServerId();
      }else if(PTY_PACKAGE_URL.equalsIgnoreCase(name)){
         return getPackageUrl();
      }else if(PTY_PACKAGE_NAME.equalsIgnoreCase(name)){
         return getPackageName();
      }else if(PTY_CLIENT_PORT.equalsIgnoreCase(name)){
         return getClientPort();
      }else if(PTY_DATABASE_PASSPORT.equalsIgnoreCase(name)){
         return getDatabasePassport();
      }else if(PTY_DATABASE_PASSWORD.equalsIgnoreCase(name)){
         return getDatabasePassword();
      }else if(PTY_DATABASE_HOST.equalsIgnoreCase(name)){
         return getDatabaseHost();
      }else if(PTY_DATABASE_PORT.equalsIgnoreCase(name)){
         return getDatabasePort();
      }else if(PTY_ADMIN_EMAIL.equalsIgnoreCase(name)){
         return getAdminEmail();
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
      }else if(PTY_HOST.equalsIgnoreCase(name)){
         setHost(value);
      }else if(PTY_PORT.equalsIgnoreCase(name)){
         setPort(value);
      }else if(PTY_USER.equalsIgnoreCase(name)){
         setUser(value);
      }else if(PTY_FLAG.equalsIgnoreCase(name)){
         setFlag(value);
      }else if(PTY_PATH.equalsIgnoreCase(name)){
         setPath(value);
      }else if(PTY_SERVER_ID.equalsIgnoreCase(name)){
         setServerId(value);
      }else if(PTY_PACKAGE_URL.equalsIgnoreCase(name)){
         setPackageUrl(value);
      }else if(PTY_PACKAGE_NAME.equalsIgnoreCase(name)){
         setPackageName(value);
      }else if(PTY_CLIENT_PORT.equalsIgnoreCase(name)){
         setClientPort(value);
      }else if(PTY_DATABASE_PASSPORT.equalsIgnoreCase(name)){
         setDatabasePassport(value);
      }else if(PTY_DATABASE_PASSWORD.equalsIgnoreCase(name)){
         setDatabasePassword(value);
      }else if(PTY_DATABASE_HOST.equalsIgnoreCase(name)){
         setDatabaseHost(value);
      }else if(PTY_DATABASE_PORT.equalsIgnoreCase(name)){
         setDatabasePort(value);
      }else if(PTY_ADMIN_EMAIL.equalsIgnoreCase(name)){
         setAdminEmail(value);
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
         if(config.contains("host")){
            setHost(config.get(PTY_HOST));
         }
         if(config.contains("port")){
            setPort(config.get(PTY_PORT));
         }
         if(config.contains("user")){
            setUser(config.get(PTY_USER));
         }
         if(config.contains("flag")){
            setFlag(config.get(PTY_FLAG));
         }
         if(config.contains("path")){
            setPath(config.get(PTY_PATH));
         }
         if(config.contains("server_id")){
            setServerId(config.get(PTY_SERVER_ID));
         }
         if(config.contains("package_url")){
            setPackageUrl(config.get(PTY_PACKAGE_URL));
         }
         if(config.contains("package_name")){
            setPackageName(config.get(PTY_PACKAGE_NAME));
         }
         if(config.contains("client_port")){
            setClientPort(config.get(PTY_CLIENT_PORT));
         }
         if(config.contains("database_passport")){
            setDatabasePassport(config.get(PTY_DATABASE_PASSPORT));
         }
         if(config.contains("database_password")){
            setDatabasePassword(config.get(PTY_DATABASE_PASSWORD));
         }
         if(config.contains("database_host")){
            setDatabaseHost(config.get(PTY_DATABASE_HOST));
         }
         if(config.contains("database_port")){
            setDatabasePort(config.get(PTY_DATABASE_PORT));
         }
         if(config.contains("admin_email")){
            setAdminEmail(config.get(PTY_ADMIN_EMAIL));
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
         if(config.contains("host")){
            setHost(config.get(PTY_HOST));
         }
         if(config.contains("port")){
            setPort(config.get(PTY_PORT));
         }
         if(config.contains("user")){
            setUser(config.get(PTY_USER));
         }
         if(config.contains("flag")){
            setFlag(config.get(PTY_FLAG));
         }
         if(config.contains("path")){
            setPath(config.get(PTY_PATH));
         }
         if(config.contains("server_id")){
            setServerId(config.get(PTY_SERVER_ID));
         }
         if(config.contains("package_url")){
            setPackageUrl(config.get(PTY_PACKAGE_URL));
         }
         if(config.contains("package_name")){
            setPackageName(config.get(PTY_PACKAGE_NAME));
         }
         if(config.contains("client_port")){
            setClientPort(config.get(PTY_CLIENT_PORT));
         }
         if(config.contains("database_passport")){
            setDatabasePassport(config.get(PTY_DATABASE_PASSPORT));
         }
         if(config.contains("database_password")){
            setDatabasePassword(config.get(PTY_DATABASE_PASSWORD));
         }
         if(config.contains("database_host")){
            setDatabaseHost(config.get(PTY_DATABASE_HOST));
         }
         if(config.contains("database_port")){
            setDatabasePort(config.get(PTY_DATABASE_PORT));
         }
         if(config.contains("admin_email")){
            setAdminEmail(config.get(PTY_ADMIN_EMAIL));
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
         if(config.contains("host")){
            setHost(config.get(PTY_HOST));
         }
         if(config.contains("port")){
            setPort(config.get(PTY_PORT));
         }
         if(config.contains("user")){
            setUser(config.get(PTY_USER));
         }
         if(config.contains("flag")){
            setFlag(config.get(PTY_FLAG));
         }
         if(config.contains("path")){
            setPath(config.get(PTY_PATH));
         }
         if(config.contains("server_id")){
            setServerId(config.get(PTY_SERVER_ID));
         }
         if(config.contains("package_url")){
            setPackageUrl(config.get(PTY_PACKAGE_URL));
         }
         if(config.contains("package_name")){
            setPackageName(config.get(PTY_PACKAGE_NAME));
         }
         if(config.contains("client_port")){
            setClientPort(config.get(PTY_CLIENT_PORT));
         }
         if(config.contains("database_passport")){
            setDatabasePassport(config.get(PTY_DATABASE_PASSPORT));
         }
         if(config.contains("database_password")){
            setDatabasePassword(config.get(PTY_DATABASE_PASSWORD));
         }
         if(config.contains("database_host")){
            setDatabaseHost(config.get(PTY_DATABASE_HOST));
         }
         if(config.contains("database_port")){
            setDatabasePort(config.get(PTY_DATABASE_PORT));
         }
         if(config.contains("admin_email")){
            setAdminEmail(config.get(PTY_ADMIN_EMAIL));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getCode())){
            if(config.contains("code")){
               setCode(config.get(PTY_CODE));
            }
         }
         if(RString.isEmpty(getHost())){
            if(config.contains("host")){
               setHost(config.get(PTY_HOST));
            }
         }
         if(RString.isEmpty(getPort())){
            if(config.contains("port")){
               setPort(config.get(PTY_PORT));
            }
         }
         if(RString.isEmpty(getUser())){
            if(config.contains("user")){
               setUser(config.get(PTY_USER));
            }
         }
         if(RString.isEmpty(getFlag())){
            if(config.contains("flag")){
               setFlag(config.get(PTY_FLAG));
            }
         }
         if(RString.isEmpty(getPath())){
            if(config.contains("path")){
               setPath(config.get(PTY_PATH));
            }
         }
         if(RString.isEmpty(getServerId())){
            if(config.contains("server_id")){
               setServerId(config.get(PTY_SERVER_ID));
            }
         }
         if(RString.isEmpty(getPackageUrl())){
            if(config.contains("package_url")){
               setPackageUrl(config.get(PTY_PACKAGE_URL));
            }
         }
         if(RString.isEmpty(getPackageName())){
            if(config.contains("package_name")){
               setPackageName(config.get(PTY_PACKAGE_NAME));
            }
         }
         if(RString.isEmpty(getClientPort())){
            if(config.contains("client_port")){
               setClientPort(config.get(PTY_CLIENT_PORT));
            }
         }
         if(RString.isEmpty(getDatabasePassport())){
            if(config.contains("database_passport")){
               setDatabasePassport(config.get(PTY_DATABASE_PASSPORT));
            }
         }
         if(RString.isEmpty(getDatabasePassword())){
            if(config.contains("database_password")){
               setDatabasePassword(config.get(PTY_DATABASE_PASSWORD));
            }
         }
         if(RString.isEmpty(getDatabaseHost())){
            if(config.contains("database_host")){
               setDatabaseHost(config.get(PTY_DATABASE_HOST));
            }
         }
         if(RString.isEmpty(getDatabasePort())){
            if(config.contains("database_port")){
               setDatabasePort(config.get(PTY_DATABASE_PORT));
            }
         }
         if(RString.isEmpty(getAdminEmail())){
            if(config.contains("admin_email")){
               setAdminEmail(config.get(PTY_ADMIN_EMAIL));
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
         if(RString.isNotEmpty(getHost())){
            config.set(PTY_HOST, getHost());
         }
         if(RString.isNotEmpty(getPort())){
            config.set(PTY_PORT, getPort());
         }
         if(RString.isNotEmpty(getUser())){
            config.set(PTY_USER, getUser());
         }
         if(RString.isNotEmpty(getFlag())){
            config.set(PTY_FLAG, getFlag());
         }
         if(RString.isNotEmpty(getPath())){
            config.set(PTY_PATH, getPath());
         }
         if(RString.isNotEmpty(getServerId())){
            config.set(PTY_SERVER_ID, getServerId());
         }
         if(RString.isNotEmpty(getPackageUrl())){
            config.set(PTY_PACKAGE_URL, getPackageUrl());
         }
         if(RString.isNotEmpty(getPackageName())){
            config.set(PTY_PACKAGE_NAME, getPackageName());
         }
         if(RString.isNotEmpty(getClientPort())){
            config.set(PTY_CLIENT_PORT, getClientPort());
         }
         if(RString.isNotEmpty(getDatabasePassport())){
            config.set(PTY_DATABASE_PASSPORT, getDatabasePassport());
         }
         if(RString.isNotEmpty(getDatabasePassword())){
            config.set(PTY_DATABASE_PASSWORD, getDatabasePassword());
         }
         if(RString.isNotEmpty(getDatabaseHost())){
            config.set(PTY_DATABASE_HOST, getDatabaseHost());
         }
         if(RString.isNotEmpty(getDatabasePort())){
            config.set(PTY_DATABASE_PORT, getDatabasePort());
         }
         if(RString.isNotEmpty(getAdminEmail())){
            config.set(PTY_ADMIN_EMAIL, getAdminEmail());
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
         if(RString.isNotEmpty(getHost())){
            config.set(PTY_HOST, getHost());
         }
         if(RString.isNotEmpty(getPort())){
            config.set(PTY_PORT, getPort());
         }
         if(RString.isNotEmpty(getUser())){
            config.set(PTY_USER, getUser());
         }
         if(RString.isNotEmpty(getFlag())){
            config.set(PTY_FLAG, getFlag());
         }
         if(RString.isNotEmpty(getPath())){
            config.set(PTY_PATH, getPath());
         }
         if(RString.isNotEmpty(getServerId())){
            config.set(PTY_SERVER_ID, getServerId());
         }
         if(RString.isNotEmpty(getPackageUrl())){
            config.set(PTY_PACKAGE_URL, getPackageUrl());
         }
         if(RString.isNotEmpty(getPackageName())){
            config.set(PTY_PACKAGE_NAME, getPackageName());
         }
         if(RString.isNotEmpty(getClientPort())){
            config.set(PTY_CLIENT_PORT, getClientPort());
         }
         if(RString.isNotEmpty(getDatabasePassport())){
            config.set(PTY_DATABASE_PASSPORT, getDatabasePassport());
         }
         if(RString.isNotEmpty(getDatabasePassword())){
            config.set(PTY_DATABASE_PASSWORD, getDatabasePassword());
         }
         if(RString.isNotEmpty(getDatabaseHost())){
            config.set(PTY_DATABASE_HOST, getDatabaseHost());
         }
         if(RString.isNotEmpty(getDatabasePort())){
            config.set(PTY_DATABASE_PORT, getDatabasePort());
         }
         if(RString.isNotEmpty(getAdminEmail())){
            config.set(PTY_ADMIN_EMAIL, getAdminEmail());
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
         String sHost = getHost();
         if(RString.isNotEmpty(sHost)){
            config.set(PTY_HOST, sHost);
         }
         String sPort = getPort();
         if(RString.isNotEmpty(sPort)){
            config.set(PTY_PORT, sPort);
         }
         String sUser = getUser();
         if(RString.isNotEmpty(sUser)){
            config.set(PTY_USER, sUser);
         }
         String sFlag = getFlag();
         if(RString.isNotEmpty(sFlag)){
            config.set(PTY_FLAG, sFlag);
         }
         String sPath = getPath();
         if(RString.isNotEmpty(sPath)){
            config.set(PTY_PATH, sPath);
         }
         String sServerId = getServerId();
         if(RString.isNotEmpty(sServerId)){
            config.set(PTY_SERVER_ID, sServerId);
         }
         String sPackageUrl = getPackageUrl();
         if(RString.isNotEmpty(sPackageUrl)){
            config.set(PTY_PACKAGE_URL, sPackageUrl);
         }
         String sPackageName = getPackageName();
         if(RString.isNotEmpty(sPackageName)){
            config.set(PTY_PACKAGE_NAME, sPackageName);
         }
         String sClientPort = getClientPort();
         if(RString.isNotEmpty(sClientPort)){
            config.set(PTY_CLIENT_PORT, sClientPort);
         }
         String sDatabasePassport = getDatabasePassport();
         if(RString.isNotEmpty(sDatabasePassport)){
            config.set(PTY_DATABASE_PASSPORT, sDatabasePassport);
         }
         String sDatabasePassword = getDatabasePassword();
         if(RString.isNotEmpty(sDatabasePassword)){
            config.set(PTY_DATABASE_PASSWORD, sDatabasePassword);
         }
         String sDatabaseHost = getDatabaseHost();
         if(RString.isNotEmpty(sDatabaseHost)){
            config.set(PTY_DATABASE_HOST, sDatabaseHost);
         }
         String sDatabasePort = getDatabasePort();
         if(RString.isNotEmpty(sDatabasePort)){
            config.set(PTY_DATABASE_PORT, sDatabasePort);
         }
         String sAdminEmail = getAdminEmail();
         if(RString.isNotEmpty(sAdminEmail)){
            config.set(PTY_ADMIN_EMAIL, sAdminEmail);
         }
      }else if(EXmlConfig.Default == type){
         String sCode = getCode();
         if(RString.isNotEmpty(sCode)){
            config.set(PTY_CODE, sCode);
         }
         String sHost = getHost();
         if(RString.isNotEmpty(sHost)){
            config.set(PTY_HOST, sHost);
         }
         String sPort = getPort();
         if(RString.isNotEmpty(sPort)){
            config.set(PTY_PORT, sPort);
         }
         String sUser = getUser();
         if(RString.isNotEmpty(sUser)){
            config.set(PTY_USER, sUser);
         }
         String sFlag = getFlag();
         if(RString.isNotEmpty(sFlag)){
            config.set(PTY_FLAG, sFlag);
         }
         String sPath = getPath();
         if(RString.isNotEmpty(sPath)){
            config.set(PTY_PATH, sPath);
         }
         String sServerId = getServerId();
         if(RString.isNotEmpty(sServerId)){
            config.set(PTY_SERVER_ID, sServerId);
         }
         String sPackageUrl = getPackageUrl();
         if(RString.isNotEmpty(sPackageUrl)){
            config.set(PTY_PACKAGE_URL, sPackageUrl);
         }
         String sPackageName = getPackageName();
         if(RString.isNotEmpty(sPackageName)){
            config.set(PTY_PACKAGE_NAME, sPackageName);
         }
         String sClientPort = getClientPort();
         if(RString.isNotEmpty(sClientPort)){
            config.set(PTY_CLIENT_PORT, sClientPort);
         }
         String sDatabasePassport = getDatabasePassport();
         if(RString.isNotEmpty(sDatabasePassport)){
            config.set(PTY_DATABASE_PASSPORT, sDatabasePassport);
         }
         String sDatabasePassword = getDatabasePassword();
         if(RString.isNotEmpty(sDatabasePassword)){
            config.set(PTY_DATABASE_PASSWORD, sDatabasePassword);
         }
         String sDatabaseHost = getDatabaseHost();
         if(RString.isNotEmpty(sDatabaseHost)){
            config.set(PTY_DATABASE_HOST, sDatabaseHost);
         }
         String sDatabasePort = getDatabasePort();
         if(RString.isNotEmpty(sDatabasePort)){
            config.set(PTY_DATABASE_PORT, sDatabasePort);
         }
         String sAdminEmail = getAdminEmail();
         if(RString.isNotEmpty(sAdminEmail)){
            config.set(PTY_ADMIN_EMAIL, sAdminEmail);
         }
      }
   }
}