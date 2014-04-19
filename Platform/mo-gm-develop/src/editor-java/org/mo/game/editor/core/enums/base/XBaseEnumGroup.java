package org.mo.game.editor.core.enums.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.game.editor.core.enums.common.XObjectFace;
import org.mo.game.editor.core.enums.common.XObjectSource;

//============================================================
// <T>枚举分组对象的XML节点基类。</T>
//============================================================
public abstract class XBaseEnumGroup
      extends FXmlObject
      implements
         XObjectFace,
         XObjectSource
{
   // 名称定义
   public static final String NAME = "Group";

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

   // 服务器的名称定义
   public static final String PTY_IS_SERVER = "is_server";

   // 服务器CPP代码的名称定义
   public static final String PTY_IS_SERVER_CPP = "is_server_cpp";

   // 服务器JAVA代码的名称定义
   public static final String PTY_IS_SERVER_JAVA = "is_server_java";

   // 客户端的名称定义
   public static final String PTY_IS_CLIENT = "is_client";

   // 客户端CPP代码的名称定义
   public static final String PTY_IS_CLIENT_CPP = "is_client_cpp";

   // 客户端AS代码的名称定义
   public static final String PTY_IS_CLIENT_AS = "is_client_as";

   // 客户端CS代码的名称定义
   public static final String PTY_IS_CLIENT_CS = "is_client_cs";

   // 客户端JAVA代码的名称定义
   public static final String PTY_IS_CLIENT_JAVA = "is_client_java";

   // 代码名称的名称定义
   public static final String PTY_SOURCE_NAME = "source_name";

   // 代码的名称定义
   public static final String PTY_CODE = "code";

   // 代码名称的名称定义
   public static final String PTY_CODE_NAME = "code_name";

   // 代码全称的名称定义
   public static final String PTY_FULL_CODE = "full_code";

   // 显示顺序的名称定义
   public static final String PTY_DISPLAY_INDEX = "display_index";

   // 项目名称的名称定义
   public static final String PTY_PROJECT_NAME = "project_name";

   // 项目代码的名称定义
   public static final String PTY_PROJECT_CODE = "project_code";

   // 进入包含的名称定义
   public static final String PTY_IS_INCLUDE = "is_include";

   // 代码前缀的名称定义
   public static final String PTY_CODE_PREFIX = "code_prefix";

   // 代码后缀的名称定义
   public static final String PTY_CODE_AFTFIX = "code_aftfix";

   // 代码分组的名称定义
   public static final String PTY_CODE_GROUP = "code_group";

   // CPP头文件的名称定义
   public static final String PTY_SOURCE_CPP_HEAD = "source_cpp_head";

   // CPP体文件的名称定义
   public static final String PTY_SOURCE_CPP_BODY = "source_cpp_body";

   // 包含头文件的名称定义
   public static final String PTY_INCLUDE_HEADS = "include_heads";

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

   // 服务器的定义
   @AName("is_server")
   protected String _isServer;

   // 服务器CPP代码的定义
   @AName("is_server_cpp")
   protected String _isServerCpp;

   // 服务器JAVA代码的定义
   @AName("is_server_java")
   protected String _isServerJava;

   // 客户端的定义
   @AName("is_client")
   protected String _isClient;

   // 客户端CPP代码的定义
   @AName("is_client_cpp")
   protected String _isClientCpp;

   // 客户端AS代码的定义
   @AName("is_client_as")
   protected String _isClientAs;

   // 客户端CS代码的定义
   @AName("is_client_cs")
   protected String _isClientCs;

   // 客户端JAVA代码的定义
   @AName("is_client_java")
   protected String _isClientJava;

   // 代码名称的定义
   @AName("source_name")
   protected String _sourceName;

   // 代码的定义
   @AName("code")
   protected String _code;

   // 代码名称的定义
   @AName("code_name")
   protected String _codeName;

   // 代码全称的定义
   @AName("full_code")
   protected String _fullCode;

   // 显示顺序的定义
   @AName("display_index")
   protected String _displayIndex;

   // 项目名称的定义
   @AName("project_name")
   protected String _projectName;

   // 项目代码的定义
   @AName("project_code")
   protected String _projectCode;

   // 进入包含的定义
   @AName("is_include")
   protected String _isInclude;

   // 代码前缀的定义
   @AName("code_prefix")
   protected String _codePrefix;

   // 代码后缀的定义
   @AName("code_aftfix")
   protected String _codeAftfix;

   // 代码分组的定义
   @AName("code_group")
   protected String _codeGroup;

   // CPP头文件的定义
   @AName("source_cpp_head")
   protected String _sourceCppHead;

   // CPP体文件的定义
   @AName("source_cpp_body")
   protected String _sourceCppBody;

   // 包含头文件的定义
   @AName("include_heads")
   protected String _includeHeads;

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
   // <T>获得服务器的内容。</T>
   //
   // @return 服务器
   //============================================================
   public String getIsServer(){
      return _isServer;
   }

   //============================================================
   // <T>设置服务器的内容。</T>
   //
   // @param value 服务器
   //============================================================
   public void setIsServer(String value){
      _isServer = value;
   }

   //============================================================
   // <T>获得服务器CPP代码的内容。</T>
   //
   // @return 服务器CPP代码
   //============================================================
   public String getIsServerCpp(){
      return _isServerCpp;
   }

   //============================================================
   // <T>设置服务器CPP代码的内容。</T>
   //
   // @param value 服务器CPP代码
   //============================================================
   public void setIsServerCpp(String value){
      _isServerCpp = value;
   }

   //============================================================
   // <T>获得服务器JAVA代码的内容。</T>
   //
   // @return 服务器JAVA代码
   //============================================================
   public String getIsServerJava(){
      return _isServerJava;
   }

   //============================================================
   // <T>设置服务器JAVA代码的内容。</T>
   //
   // @param value 服务器JAVA代码
   //============================================================
   public void setIsServerJava(String value){
      _isServerJava = value;
   }

   //============================================================
   // <T>获得客户端的内容。</T>
   //
   // @return 客户端
   //============================================================
   public String getIsClient(){
      return _isClient;
   }

   //============================================================
   // <T>设置客户端的内容。</T>
   //
   // @param value 客户端
   //============================================================
   public void setIsClient(String value){
      _isClient = value;
   }

   //============================================================
   // <T>获得客户端CPP代码的内容。</T>
   //
   // @return 客户端CPP代码
   //============================================================
   public String getIsClientCpp(){
      return _isClientCpp;
   }

   //============================================================
   // <T>设置客户端CPP代码的内容。</T>
   //
   // @param value 客户端CPP代码
   //============================================================
   public void setIsClientCpp(String value){
      _isClientCpp = value;
   }

   //============================================================
   // <T>获得客户端AS代码的内容。</T>
   //
   // @return 客户端AS代码
   //============================================================
   public String getIsClientAs(){
      return _isClientAs;
   }

   //============================================================
   // <T>设置客户端AS代码的内容。</T>
   //
   // @param value 客户端AS代码
   //============================================================
   public void setIsClientAs(String value){
      _isClientAs = value;
   }

   //============================================================
   // <T>获得客户端CS代码的内容。</T>
   //
   // @return 客户端CS代码
   //============================================================
   public String getIsClientCs(){
      return _isClientCs;
   }

   //============================================================
   // <T>设置客户端CS代码的内容。</T>
   //
   // @param value 客户端CS代码
   //============================================================
   public void setIsClientCs(String value){
      _isClientCs = value;
   }

   //============================================================
   // <T>获得客户端JAVA代码的内容。</T>
   //
   // @return 客户端JAVA代码
   //============================================================
   public String getIsClientJava(){
      return _isClientJava;
   }

   //============================================================
   // <T>设置客户端JAVA代码的内容。</T>
   //
   // @param value 客户端JAVA代码
   //============================================================
   public void setIsClientJava(String value){
      _isClientJava = value;
   }

   //============================================================
   // <T>获得代码名称的内容。</T>
   //
   // @return 代码名称
   //============================================================
   public String getSourceName(){
      return _sourceName;
   }

   //============================================================
   // <T>设置代码名称的内容。</T>
   //
   // @param value 代码名称
   //============================================================
   public void setSourceName(String value){
      _sourceName = value;
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
   public String getCodeName(){
      return _codeName;
   }

   //============================================================
   // <T>设置代码名称的内容。</T>
   //
   // @param value 代码名称
   //============================================================
   public void setCodeName(String value){
      _codeName = value;
   }

   //============================================================
   // <T>获得代码全称的内容。</T>
   //
   // @return 代码全称
   //============================================================
   public String getFullCode(){
      return _fullCode;
   }

   //============================================================
   // <T>设置代码全称的内容。</T>
   //
   // @param value 代码全称
   //============================================================
   public void setFullCode(String value){
      _fullCode = value;
   }

   //============================================================
   // <T>获得显示顺序的内容。</T>
   //
   // @return 显示顺序
   //============================================================
   public String getDisplayIndex(){
      return _displayIndex;
   }

   //============================================================
   // <T>设置显示顺序的内容。</T>
   //
   // @param value 显示顺序
   //============================================================
   public void setDisplayIndex(String value){
      _displayIndex = value;
   }

   //============================================================
   // <T>获得项目名称的内容。</T>
   //
   // @return 项目名称
   //============================================================
   public String getProjectName(){
      return _projectName;
   }

   //============================================================
   // <T>设置项目名称的内容。</T>
   //
   // @param value 项目名称
   //============================================================
   public void setProjectName(String value){
      _projectName = value;
   }

   //============================================================
   // <T>获得项目代码的内容。</T>
   //
   // @return 项目代码
   //============================================================
   public String getProjectCode(){
      return _projectCode;
   }

   //============================================================
   // <T>设置项目代码的内容。</T>
   //
   // @param value 项目代码
   //============================================================
   public void setProjectCode(String value){
      _projectCode = value;
   }

   //============================================================
   // <T>获得进入包含的内容。</T>
   //
   // @return 进入包含
   //============================================================
   public String getIsInclude(){
      return _isInclude;
   }

   //============================================================
   // <T>设置进入包含的内容。</T>
   //
   // @param value 进入包含
   //============================================================
   public void setIsInclude(String value){
      _isInclude = value;
   }

   //============================================================
   // <T>获得代码前缀的内容。</T>
   //
   // @return 代码前缀
   //============================================================
   public String getCodePrefix(){
      return _codePrefix;
   }

   //============================================================
   // <T>设置代码前缀的内容。</T>
   //
   // @param value 代码前缀
   //============================================================
   public void setCodePrefix(String value){
      _codePrefix = value;
   }

   //============================================================
   // <T>获得代码后缀的内容。</T>
   //
   // @return 代码后缀
   //============================================================
   public String getCodeAftfix(){
      return _codeAftfix;
   }

   //============================================================
   // <T>设置代码后缀的内容。</T>
   //
   // @param value 代码后缀
   //============================================================
   public void setCodeAftfix(String value){
      _codeAftfix = value;
   }

   //============================================================
   // <T>获得代码分组的内容。</T>
   //
   // @return 代码分组
   //============================================================
   public String getCodeGroup(){
      return _codeGroup;
   }

   //============================================================
   // <T>设置代码分组的内容。</T>
   //
   // @param value 代码分组
   //============================================================
   public void setCodeGroup(String value){
      _codeGroup = value;
   }

   //============================================================
   // <T>获得CPP头文件的内容。</T>
   //
   // @return CPP头文件
   //============================================================
   public String getSourceCppHead(){
      return _sourceCppHead;
   }

   //============================================================
   // <T>设置CPP头文件的内容。</T>
   //
   // @param value CPP头文件
   //============================================================
   public void setSourceCppHead(String value){
      _sourceCppHead = value;
   }

   //============================================================
   // <T>获得CPP体文件的内容。</T>
   //
   // @return CPP体文件
   //============================================================
   public String getSourceCppBody(){
      return _sourceCppBody;
   }

   //============================================================
   // <T>设置CPP体文件的内容。</T>
   //
   // @param value CPP体文件
   //============================================================
   public void setSourceCppBody(String value){
      _sourceCppBody = value;
   }

   //============================================================
   // <T>获得包含头文件的内容。</T>
   //
   // @return 包含头文件
   //============================================================
   public String getIncludeHeads(){
      return _includeHeads;
   }

   //============================================================
   // <T>设置包含头文件的内容。</T>
   //
   // @param value 包含头文件
   //============================================================
   public void setIncludeHeads(String value){
      _includeHeads = value;
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
      }else if(PTY_IS_SERVER.equalsIgnoreCase(name)){
         return getIsServer();
      }else if(PTY_IS_SERVER_CPP.equalsIgnoreCase(name)){
         return getIsServerCpp();
      }else if(PTY_IS_SERVER_JAVA.equalsIgnoreCase(name)){
         return getIsServerJava();
      }else if(PTY_IS_CLIENT.equalsIgnoreCase(name)){
         return getIsClient();
      }else if(PTY_IS_CLIENT_CPP.equalsIgnoreCase(name)){
         return getIsClientCpp();
      }else if(PTY_IS_CLIENT_AS.equalsIgnoreCase(name)){
         return getIsClientAs();
      }else if(PTY_IS_CLIENT_CS.equalsIgnoreCase(name)){
         return getIsClientCs();
      }else if(PTY_IS_CLIENT_JAVA.equalsIgnoreCase(name)){
         return getIsClientJava();
      }else if(PTY_SOURCE_NAME.equalsIgnoreCase(name)){
         return getSourceName();
      }else if(PTY_CODE.equalsIgnoreCase(name)){
         return getCode();
      }else if(PTY_CODE_NAME.equalsIgnoreCase(name)){
         return getCodeName();
      }else if(PTY_FULL_CODE.equalsIgnoreCase(name)){
         return getFullCode();
      }else if(PTY_DISPLAY_INDEX.equalsIgnoreCase(name)){
         return getDisplayIndex();
      }else if(PTY_PROJECT_NAME.equalsIgnoreCase(name)){
         return getProjectName();
      }else if(PTY_PROJECT_CODE.equalsIgnoreCase(name)){
         return getProjectCode();
      }else if(PTY_IS_INCLUDE.equalsIgnoreCase(name)){
         return getIsInclude();
      }else if(PTY_CODE_PREFIX.equalsIgnoreCase(name)){
         return getCodePrefix();
      }else if(PTY_CODE_AFTFIX.equalsIgnoreCase(name)){
         return getCodeAftfix();
      }else if(PTY_CODE_GROUP.equalsIgnoreCase(name)){
         return getCodeGroup();
      }else if(PTY_SOURCE_CPP_HEAD.equalsIgnoreCase(name)){
         return getSourceCppHead();
      }else if(PTY_SOURCE_CPP_BODY.equalsIgnoreCase(name)){
         return getSourceCppBody();
      }else if(PTY_INCLUDE_HEADS.equalsIgnoreCase(name)){
         return getIncludeHeads();
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
      }else if(PTY_IS_SERVER.equalsIgnoreCase(name)){
         setIsServer(value);
      }else if(PTY_IS_SERVER_CPP.equalsIgnoreCase(name)){
         setIsServerCpp(value);
      }else if(PTY_IS_SERVER_JAVA.equalsIgnoreCase(name)){
         setIsServerJava(value);
      }else if(PTY_IS_CLIENT.equalsIgnoreCase(name)){
         setIsClient(value);
      }else if(PTY_IS_CLIENT_CPP.equalsIgnoreCase(name)){
         setIsClientCpp(value);
      }else if(PTY_IS_CLIENT_AS.equalsIgnoreCase(name)){
         setIsClientAs(value);
      }else if(PTY_IS_CLIENT_CS.equalsIgnoreCase(name)){
         setIsClientCs(value);
      }else if(PTY_IS_CLIENT_JAVA.equalsIgnoreCase(name)){
         setIsClientJava(value);
      }else if(PTY_SOURCE_NAME.equalsIgnoreCase(name)){
         setSourceName(value);
      }else if(PTY_CODE.equalsIgnoreCase(name)){
         setCode(value);
      }else if(PTY_CODE_NAME.equalsIgnoreCase(name)){
         setCodeName(value);
      }else if(PTY_FULL_CODE.equalsIgnoreCase(name)){
         setFullCode(value);
      }else if(PTY_DISPLAY_INDEX.equalsIgnoreCase(name)){
         setDisplayIndex(value);
      }else if(PTY_PROJECT_NAME.equalsIgnoreCase(name)){
         setProjectName(value);
      }else if(PTY_PROJECT_CODE.equalsIgnoreCase(name)){
         setProjectCode(value);
      }else if(PTY_IS_INCLUDE.equalsIgnoreCase(name)){
         setIsInclude(value);
      }else if(PTY_CODE_PREFIX.equalsIgnoreCase(name)){
         setCodePrefix(value);
      }else if(PTY_CODE_AFTFIX.equalsIgnoreCase(name)){
         setCodeAftfix(value);
      }else if(PTY_CODE_GROUP.equalsIgnoreCase(name)){
         setCodeGroup(value);
      }else if(PTY_SOURCE_CPP_HEAD.equalsIgnoreCase(name)){
         setSourceCppHead(value);
      }else if(PTY_SOURCE_CPP_BODY.equalsIgnoreCase(name)){
         setSourceCppBody(value);
      }else if(PTY_INCLUDE_HEADS.equalsIgnoreCase(name)){
         setIncludeHeads(value);
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
         if(config.contains("is_server")){
            setIsServer(config.get(PTY_IS_SERVER));
         }
         if(config.contains("is_server_cpp")){
            setIsServerCpp(config.get(PTY_IS_SERVER_CPP));
         }
         if(config.contains("is_server_java")){
            setIsServerJava(config.get(PTY_IS_SERVER_JAVA));
         }
         if(config.contains("is_client")){
            setIsClient(config.get(PTY_IS_CLIENT));
         }
         if(config.contains("is_client_cpp")){
            setIsClientCpp(config.get(PTY_IS_CLIENT_CPP));
         }
         if(config.contains("is_client_as")){
            setIsClientAs(config.get(PTY_IS_CLIENT_AS));
         }
         if(config.contains("is_client_cs")){
            setIsClientCs(config.get(PTY_IS_CLIENT_CS));
         }
         if(config.contains("is_client_java")){
            setIsClientJava(config.get(PTY_IS_CLIENT_JAVA));
         }
         if(config.contains("source_name")){
            setSourceName(config.get(PTY_SOURCE_NAME));
         }
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
         if(config.contains("code_name")){
            setCodeName(config.get(PTY_CODE_NAME));
         }
         if(config.contains("full_code")){
            setFullCode(config.get(PTY_FULL_CODE));
         }
         if(config.contains("display_index")){
            setDisplayIndex(config.get(PTY_DISPLAY_INDEX));
         }
         if(config.contains("project_name")){
            setProjectName(config.get(PTY_PROJECT_NAME));
         }
         if(config.contains("project_code")){
            setProjectCode(config.get(PTY_PROJECT_CODE));
         }
         if(config.contains("is_include")){
            setIsInclude(config.get(PTY_IS_INCLUDE));
         }
         if(config.contains("code_prefix")){
            setCodePrefix(config.get(PTY_CODE_PREFIX));
         }
         if(config.contains("code_aftfix")){
            setCodeAftfix(config.get(PTY_CODE_AFTFIX));
         }
         if(config.contains("code_group")){
            setCodeGroup(config.get(PTY_CODE_GROUP));
         }
         if(config.contains("source_cpp_head")){
            setSourceCppHead(config.get(PTY_SOURCE_CPP_HEAD));
         }
         if(config.contains("source_cpp_body")){
            setSourceCppBody(config.get(PTY_SOURCE_CPP_BODY));
         }
         if(config.contains("include_heads")){
            setIncludeHeads(config.get(PTY_INCLUDE_HEADS));
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
         if(config.contains("is_server")){
            setIsServer(config.get(PTY_IS_SERVER));
         }
         if(config.contains("is_server_cpp")){
            setIsServerCpp(config.get(PTY_IS_SERVER_CPP));
         }
         if(config.contains("is_server_java")){
            setIsServerJava(config.get(PTY_IS_SERVER_JAVA));
         }
         if(config.contains("is_client")){
            setIsClient(config.get(PTY_IS_CLIENT));
         }
         if(config.contains("is_client_cpp")){
            setIsClientCpp(config.get(PTY_IS_CLIENT_CPP));
         }
         if(config.contains("is_client_as")){
            setIsClientAs(config.get(PTY_IS_CLIENT_AS));
         }
         if(config.contains("is_client_cs")){
            setIsClientCs(config.get(PTY_IS_CLIENT_CS));
         }
         if(config.contains("is_client_java")){
            setIsClientJava(config.get(PTY_IS_CLIENT_JAVA));
         }
         if(config.contains("source_name")){
            setSourceName(config.get(PTY_SOURCE_NAME));
         }
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
         if(config.contains("code_name")){
            setCodeName(config.get(PTY_CODE_NAME));
         }
         if(config.contains("full_code")){
            setFullCode(config.get(PTY_FULL_CODE));
         }
         if(config.contains("display_index")){
            setDisplayIndex(config.get(PTY_DISPLAY_INDEX));
         }
         if(config.contains("project_name")){
            setProjectName(config.get(PTY_PROJECT_NAME));
         }
         if(config.contains("project_code")){
            setProjectCode(config.get(PTY_PROJECT_CODE));
         }
         if(config.contains("is_include")){
            setIsInclude(config.get(PTY_IS_INCLUDE));
         }
         if(config.contains("code_prefix")){
            setCodePrefix(config.get(PTY_CODE_PREFIX));
         }
         if(config.contains("code_aftfix")){
            setCodeAftfix(config.get(PTY_CODE_AFTFIX));
         }
         if(config.contains("code_group")){
            setCodeGroup(config.get(PTY_CODE_GROUP));
         }
         if(config.contains("source_cpp_head")){
            setSourceCppHead(config.get(PTY_SOURCE_CPP_HEAD));
         }
         if(config.contains("source_cpp_body")){
            setSourceCppBody(config.get(PTY_SOURCE_CPP_BODY));
         }
         if(config.contains("include_heads")){
            setIncludeHeads(config.get(PTY_INCLUDE_HEADS));
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
         if(config.contains("is_server")){
            setIsServer(config.get(PTY_IS_SERVER));
         }
         if(config.contains("is_server_cpp")){
            setIsServerCpp(config.get(PTY_IS_SERVER_CPP));
         }
         if(config.contains("is_server_java")){
            setIsServerJava(config.get(PTY_IS_SERVER_JAVA));
         }
         if(config.contains("is_client")){
            setIsClient(config.get(PTY_IS_CLIENT));
         }
         if(config.contains("is_client_cpp")){
            setIsClientCpp(config.get(PTY_IS_CLIENT_CPP));
         }
         if(config.contains("is_client_as")){
            setIsClientAs(config.get(PTY_IS_CLIENT_AS));
         }
         if(config.contains("is_client_cs")){
            setIsClientCs(config.get(PTY_IS_CLIENT_CS));
         }
         if(config.contains("is_client_java")){
            setIsClientJava(config.get(PTY_IS_CLIENT_JAVA));
         }
         if(config.contains("source_name")){
            setSourceName(config.get(PTY_SOURCE_NAME));
         }
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
         if(config.contains("code_name")){
            setCodeName(config.get(PTY_CODE_NAME));
         }
         if(config.contains("full_code")){
            setFullCode(config.get(PTY_FULL_CODE));
         }
         if(config.contains("display_index")){
            setDisplayIndex(config.get(PTY_DISPLAY_INDEX));
         }
         if(config.contains("project_name")){
            setProjectName(config.get(PTY_PROJECT_NAME));
         }
         if(config.contains("project_code")){
            setProjectCode(config.get(PTY_PROJECT_CODE));
         }
         if(config.contains("is_include")){
            setIsInclude(config.get(PTY_IS_INCLUDE));
         }
         if(config.contains("code_prefix")){
            setCodePrefix(config.get(PTY_CODE_PREFIX));
         }
         if(config.contains("code_aftfix")){
            setCodeAftfix(config.get(PTY_CODE_AFTFIX));
         }
         if(config.contains("code_group")){
            setCodeGroup(config.get(PTY_CODE_GROUP));
         }
         if(config.contains("source_cpp_head")){
            setSourceCppHead(config.get(PTY_SOURCE_CPP_HEAD));
         }
         if(config.contains("source_cpp_body")){
            setSourceCppBody(config.get(PTY_SOURCE_CPP_BODY));
         }
         if(config.contains("include_heads")){
            setIncludeHeads(config.get(PTY_INCLUDE_HEADS));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getIsServer())){
            if(config.contains("is_server")){
               setIsServer(config.get(PTY_IS_SERVER));
            }
         }
         if(RString.isEmpty(getIsServerCpp())){
            if(config.contains("is_server_cpp")){
               setIsServerCpp(config.get(PTY_IS_SERVER_CPP));
            }
         }
         if(RString.isEmpty(getIsServerJava())){
            if(config.contains("is_server_java")){
               setIsServerJava(config.get(PTY_IS_SERVER_JAVA));
            }
         }
         if(RString.isEmpty(getIsClient())){
            if(config.contains("is_client")){
               setIsClient(config.get(PTY_IS_CLIENT));
            }
         }
         if(RString.isEmpty(getIsClientCpp())){
            if(config.contains("is_client_cpp")){
               setIsClientCpp(config.get(PTY_IS_CLIENT_CPP));
            }
         }
         if(RString.isEmpty(getIsClientAs())){
            if(config.contains("is_client_as")){
               setIsClientAs(config.get(PTY_IS_CLIENT_AS));
            }
         }
         if(RString.isEmpty(getIsClientCs())){
            if(config.contains("is_client_cs")){
               setIsClientCs(config.get(PTY_IS_CLIENT_CS));
            }
         }
         if(RString.isEmpty(getIsClientJava())){
            if(config.contains("is_client_java")){
               setIsClientJava(config.get(PTY_IS_CLIENT_JAVA));
            }
         }
         if(RString.isEmpty(getSourceName())){
            if(config.contains("source_name")){
               setSourceName(config.get(PTY_SOURCE_NAME));
            }
         }
         if(RString.isEmpty(getCode())){
            if(config.contains("code")){
               setCode(config.get(PTY_CODE));
            }
         }
         if(RString.isEmpty(getCodeName())){
            if(config.contains("code_name")){
               setCodeName(config.get(PTY_CODE_NAME));
            }
         }
         if(RString.isEmpty(getFullCode())){
            if(config.contains("full_code")){
               setFullCode(config.get(PTY_FULL_CODE));
            }
         }
         if(RString.isEmpty(getDisplayIndex())){
            if(config.contains("display_index")){
               setDisplayIndex(config.get(PTY_DISPLAY_INDEX));
            }
         }
         if(RString.isEmpty(getProjectName())){
            if(config.contains("project_name")){
               setProjectName(config.get(PTY_PROJECT_NAME));
            }
         }
         if(RString.isEmpty(getProjectCode())){
            if(config.contains("project_code")){
               setProjectCode(config.get(PTY_PROJECT_CODE));
            }
         }
         if(RString.isEmpty(getIsInclude())){
            if(config.contains("is_include")){
               setIsInclude(config.get(PTY_IS_INCLUDE));
            }
         }
         if(RString.isEmpty(getCodePrefix())){
            if(config.contains("code_prefix")){
               setCodePrefix(config.get(PTY_CODE_PREFIX));
            }
         }
         if(RString.isEmpty(getCodeAftfix())){
            if(config.contains("code_aftfix")){
               setCodeAftfix(config.get(PTY_CODE_AFTFIX));
            }
         }
         if(RString.isEmpty(getCodeGroup())){
            if(config.contains("code_group")){
               setCodeGroup(config.get(PTY_CODE_GROUP));
            }
         }
         if(RString.isEmpty(getSourceCppHead())){
            if(config.contains("source_cpp_head")){
               setSourceCppHead(config.get(PTY_SOURCE_CPP_HEAD));
            }
         }
         if(RString.isEmpty(getSourceCppBody())){
            if(config.contains("source_cpp_body")){
               setSourceCppBody(config.get(PTY_SOURCE_CPP_BODY));
            }
         }
         if(RString.isEmpty(getIncludeHeads())){
            if(config.contains("include_heads")){
               setIncludeHeads(config.get(PTY_INCLUDE_HEADS));
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
         if(RString.isNotEmpty(getIsServer())){
            config.set(PTY_IS_SERVER, getIsServer());
         }
         if(RString.isNotEmpty(getIsServerCpp())){
            config.set(PTY_IS_SERVER_CPP, getIsServerCpp());
         }
         if(RString.isNotEmpty(getIsServerJava())){
            config.set(PTY_IS_SERVER_JAVA, getIsServerJava());
         }
         if(RString.isNotEmpty(getIsClient())){
            config.set(PTY_IS_CLIENT, getIsClient());
         }
         if(RString.isNotEmpty(getIsClientCpp())){
            config.set(PTY_IS_CLIENT_CPP, getIsClientCpp());
         }
         if(RString.isNotEmpty(getIsClientAs())){
            config.set(PTY_IS_CLIENT_AS, getIsClientAs());
         }
         if(RString.isNotEmpty(getIsClientCs())){
            config.set(PTY_IS_CLIENT_CS, getIsClientCs());
         }
         if(RString.isNotEmpty(getIsClientJava())){
            config.set(PTY_IS_CLIENT_JAVA, getIsClientJava());
         }
         if(RString.isNotEmpty(getSourceName())){
            config.set(PTY_SOURCE_NAME, getSourceName());
         }
         if(RString.isNotEmpty(getCode())){
            config.set(PTY_CODE, getCode());
         }
         if(RString.isNotEmpty(getCodeName())){
            config.set(PTY_CODE_NAME, getCodeName());
         }
         if(RString.isNotEmpty(getFullCode())){
            config.set(PTY_FULL_CODE, getFullCode());
         }
         if(RString.isNotEmpty(getDisplayIndex())){
            config.set(PTY_DISPLAY_INDEX, getDisplayIndex());
         }
         if(RString.isNotEmpty(getProjectName())){
            config.set(PTY_PROJECT_NAME, getProjectName());
         }
         if(RString.isNotEmpty(getProjectCode())){
            config.set(PTY_PROJECT_CODE, getProjectCode());
         }
         if(RString.isNotEmpty(getIsInclude())){
            config.set(PTY_IS_INCLUDE, getIsInclude());
         }
         if(RString.isNotEmpty(getCodePrefix())){
            config.set(PTY_CODE_PREFIX, getCodePrefix());
         }
         if(RString.isNotEmpty(getCodeAftfix())){
            config.set(PTY_CODE_AFTFIX, getCodeAftfix());
         }
         if(RString.isNotEmpty(getCodeGroup())){
            config.set(PTY_CODE_GROUP, getCodeGroup());
         }
         if(RString.isNotEmpty(getSourceCppHead())){
            config.set(PTY_SOURCE_CPP_HEAD, getSourceCppHead());
         }
         if(RString.isNotEmpty(getSourceCppBody())){
            config.set(PTY_SOURCE_CPP_BODY, getSourceCppBody());
         }
         if(RString.isNotEmpty(getIncludeHeads())){
            config.set(PTY_INCLUDE_HEADS, getIncludeHeads());
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
         if(RString.isNotEmpty(getIsServer())){
            config.set(PTY_IS_SERVER, getIsServer());
         }
         if(RString.isNotEmpty(getIsServerCpp())){
            config.set(PTY_IS_SERVER_CPP, getIsServerCpp());
         }
         if(RString.isNotEmpty(getIsServerJava())){
            config.set(PTY_IS_SERVER_JAVA, getIsServerJava());
         }
         if(RString.isNotEmpty(getIsClient())){
            config.set(PTY_IS_CLIENT, getIsClient());
         }
         if(RString.isNotEmpty(getIsClientCpp())){
            config.set(PTY_IS_CLIENT_CPP, getIsClientCpp());
         }
         if(RString.isNotEmpty(getIsClientAs())){
            config.set(PTY_IS_CLIENT_AS, getIsClientAs());
         }
         if(RString.isNotEmpty(getIsClientCs())){
            config.set(PTY_IS_CLIENT_CS, getIsClientCs());
         }
         if(RString.isNotEmpty(getIsClientJava())){
            config.set(PTY_IS_CLIENT_JAVA, getIsClientJava());
         }
         if(RString.isNotEmpty(getSourceName())){
            config.set(PTY_SOURCE_NAME, getSourceName());
         }
         if(RString.isNotEmpty(getCode())){
            config.set(PTY_CODE, getCode());
         }
         if(RString.isNotEmpty(getCodeName())){
            config.set(PTY_CODE_NAME, getCodeName());
         }
         if(RString.isNotEmpty(getFullCode())){
            config.set(PTY_FULL_CODE, getFullCode());
         }
         if(RString.isNotEmpty(getDisplayIndex())){
            config.set(PTY_DISPLAY_INDEX, getDisplayIndex());
         }
         if(RString.isNotEmpty(getProjectName())){
            config.set(PTY_PROJECT_NAME, getProjectName());
         }
         if(RString.isNotEmpty(getProjectCode())){
            config.set(PTY_PROJECT_CODE, getProjectCode());
         }
         if(RString.isNotEmpty(getIsInclude())){
            config.set(PTY_IS_INCLUDE, getIsInclude());
         }
         if(RString.isNotEmpty(getCodePrefix())){
            config.set(PTY_CODE_PREFIX, getCodePrefix());
         }
         if(RString.isNotEmpty(getCodeAftfix())){
            config.set(PTY_CODE_AFTFIX, getCodeAftfix());
         }
         if(RString.isNotEmpty(getCodeGroup())){
            config.set(PTY_CODE_GROUP, getCodeGroup());
         }
         if(RString.isNotEmpty(getSourceCppHead())){
            config.set(PTY_SOURCE_CPP_HEAD, getSourceCppHead());
         }
         if(RString.isNotEmpty(getSourceCppBody())){
            config.set(PTY_SOURCE_CPP_BODY, getSourceCppBody());
         }
         if(RString.isNotEmpty(getIncludeHeads())){
            config.set(PTY_INCLUDE_HEADS, getIncludeHeads());
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
         String sIsServer = getIsServer();
         if(RString.isNotEmpty(sIsServer)){
            config.set(PTY_IS_SERVER, sIsServer);
         }
         String sIsServerCpp = getIsServerCpp();
         if(RString.isNotEmpty(sIsServerCpp)){
            config.set(PTY_IS_SERVER_CPP, sIsServerCpp);
         }
         String sIsServerJava = getIsServerJava();
         if(RString.isNotEmpty(sIsServerJava)){
            config.set(PTY_IS_SERVER_JAVA, sIsServerJava);
         }
         String sIsClient = getIsClient();
         if(RString.isNotEmpty(sIsClient)){
            config.set(PTY_IS_CLIENT, sIsClient);
         }
         String sIsClientCpp = getIsClientCpp();
         if(RString.isNotEmpty(sIsClientCpp)){
            config.set(PTY_IS_CLIENT_CPP, sIsClientCpp);
         }
         String sIsClientAs = getIsClientAs();
         if(RString.isNotEmpty(sIsClientAs)){
            config.set(PTY_IS_CLIENT_AS, sIsClientAs);
         }
         String sIsClientCs = getIsClientCs();
         if(RString.isNotEmpty(sIsClientCs)){
            config.set(PTY_IS_CLIENT_CS, sIsClientCs);
         }
         String sIsClientJava = getIsClientJava();
         if(RString.isNotEmpty(sIsClientJava)){
            config.set(PTY_IS_CLIENT_JAVA, sIsClientJava);
         }
         String sSourceName = getSourceName();
         if(RString.isNotEmpty(sSourceName)){
            config.set(PTY_SOURCE_NAME, sSourceName);
         }
         String sCode = getCode();
         if(RString.isNotEmpty(sCode)){
            config.set(PTY_CODE, sCode);
         }
         String sCodeName = getCodeName();
         if(RString.isNotEmpty(sCodeName)){
            config.set(PTY_CODE_NAME, sCodeName);
         }
         String sFullCode = getFullCode();
         if(RString.isNotEmpty(sFullCode)){
            config.set(PTY_FULL_CODE, sFullCode);
         }
         String sDisplayIndex = getDisplayIndex();
         if(RString.isNotEmpty(sDisplayIndex)){
            config.set(PTY_DISPLAY_INDEX, sDisplayIndex);
         }
         String sProjectName = getProjectName();
         if(RString.isNotEmpty(sProjectName)){
            config.set(PTY_PROJECT_NAME, sProjectName);
         }
         String sProjectCode = getProjectCode();
         if(RString.isNotEmpty(sProjectCode)){
            config.set(PTY_PROJECT_CODE, sProjectCode);
         }
         String sIsInclude = getIsInclude();
         if(RString.isNotEmpty(sIsInclude)){
            config.set(PTY_IS_INCLUDE, sIsInclude);
         }
         String sCodePrefix = getCodePrefix();
         if(RString.isNotEmpty(sCodePrefix)){
            config.set(PTY_CODE_PREFIX, sCodePrefix);
         }
         String sCodeAftfix = getCodeAftfix();
         if(RString.isNotEmpty(sCodeAftfix)){
            config.set(PTY_CODE_AFTFIX, sCodeAftfix);
         }
         String sCodeGroup = getCodeGroup();
         if(RString.isNotEmpty(sCodeGroup)){
            config.set(PTY_CODE_GROUP, sCodeGroup);
         }
         String sSourceCppHead = getSourceCppHead();
         if(RString.isNotEmpty(sSourceCppHead)){
            config.set(PTY_SOURCE_CPP_HEAD, sSourceCppHead);
         }
         String sSourceCppBody = getSourceCppBody();
         if(RString.isNotEmpty(sSourceCppBody)){
            config.set(PTY_SOURCE_CPP_BODY, sSourceCppBody);
         }
         String sIncludeHeads = getIncludeHeads();
         if(RString.isNotEmpty(sIncludeHeads)){
            config.set(PTY_INCLUDE_HEADS, sIncludeHeads);
         }
      }else if(EXmlConfig.Default == type){
         String sIsServer = getIsServer();
         if(RString.isNotEmpty(sIsServer)){
            config.set(PTY_IS_SERVER, sIsServer);
         }
         String sIsServerCpp = getIsServerCpp();
         if(RString.isNotEmpty(sIsServerCpp)){
            config.set(PTY_IS_SERVER_CPP, sIsServerCpp);
         }
         String sIsServerJava = getIsServerJava();
         if(RString.isNotEmpty(sIsServerJava)){
            config.set(PTY_IS_SERVER_JAVA, sIsServerJava);
         }
         String sIsClient = getIsClient();
         if(RString.isNotEmpty(sIsClient)){
            config.set(PTY_IS_CLIENT, sIsClient);
         }
         String sIsClientCpp = getIsClientCpp();
         if(RString.isNotEmpty(sIsClientCpp)){
            config.set(PTY_IS_CLIENT_CPP, sIsClientCpp);
         }
         String sIsClientAs = getIsClientAs();
         if(RString.isNotEmpty(sIsClientAs)){
            config.set(PTY_IS_CLIENT_AS, sIsClientAs);
         }
         String sIsClientCs = getIsClientCs();
         if(RString.isNotEmpty(sIsClientCs)){
            config.set(PTY_IS_CLIENT_CS, sIsClientCs);
         }
         String sIsClientJava = getIsClientJava();
         if(RString.isNotEmpty(sIsClientJava)){
            config.set(PTY_IS_CLIENT_JAVA, sIsClientJava);
         }
         String sSourceName = getSourceName();
         if(RString.isNotEmpty(sSourceName)){
            config.set(PTY_SOURCE_NAME, sSourceName);
         }
         String sCode = getCode();
         if(RString.isNotEmpty(sCode)){
            config.set(PTY_CODE, sCode);
         }
         String sCodeName = getCodeName();
         if(RString.isNotEmpty(sCodeName)){
            config.set(PTY_CODE_NAME, sCodeName);
         }
         String sFullCode = getFullCode();
         if(RString.isNotEmpty(sFullCode)){
            config.set(PTY_FULL_CODE, sFullCode);
         }
         String sDisplayIndex = getDisplayIndex();
         if(RString.isNotEmpty(sDisplayIndex)){
            config.set(PTY_DISPLAY_INDEX, sDisplayIndex);
         }
         String sProjectName = getProjectName();
         if(RString.isNotEmpty(sProjectName)){
            config.set(PTY_PROJECT_NAME, sProjectName);
         }
         String sProjectCode = getProjectCode();
         if(RString.isNotEmpty(sProjectCode)){
            config.set(PTY_PROJECT_CODE, sProjectCode);
         }
         String sIsInclude = getIsInclude();
         if(RString.isNotEmpty(sIsInclude)){
            config.set(PTY_IS_INCLUDE, sIsInclude);
         }
         String sCodePrefix = getCodePrefix();
         if(RString.isNotEmpty(sCodePrefix)){
            config.set(PTY_CODE_PREFIX, sCodePrefix);
         }
         String sCodeAftfix = getCodeAftfix();
         if(RString.isNotEmpty(sCodeAftfix)){
            config.set(PTY_CODE_AFTFIX, sCodeAftfix);
         }
         String sCodeGroup = getCodeGroup();
         if(RString.isNotEmpty(sCodeGroup)){
            config.set(PTY_CODE_GROUP, sCodeGroup);
         }
         String sSourceCppHead = getSourceCppHead();
         if(RString.isNotEmpty(sSourceCppHead)){
            config.set(PTY_SOURCE_CPP_HEAD, sSourceCppHead);
         }
         String sSourceCppBody = getSourceCppBody();
         if(RString.isNotEmpty(sSourceCppBody)){
            config.set(PTY_SOURCE_CPP_BODY, sSourceCppBody);
         }
         String sIncludeHeads = getIncludeHeads();
         if(RString.isNotEmpty(sIncludeHeads)){
            config.set(PTY_INCLUDE_HEADS, sIncludeHeads);
         }
      }
   }
}