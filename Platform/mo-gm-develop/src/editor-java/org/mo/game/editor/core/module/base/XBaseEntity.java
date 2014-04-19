package org.mo.game.editor.core.module.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.game.editor.core.module.common.XObjectFace;

//============================================================
// <T>实体对象的XML节点基类。</T>
//============================================================
public abstract class XBaseEntity
      extends FXmlObject
      implements
         XObjectFace
{
   // 名称定义
   public static final String NAME = "Entity";

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

   // 代码的名称定义
   public static final String PTY_CODE = "code";

   // 代码名称的名称定义
   public static final String PTY_CODE_NAME = "code_name";

   // 代码全称的名称定义
   public static final String PTY_CODE_FULL = "code_full";

   // 链接关联的名称定义
   public static final String PTY_IS_SOCKET = "is_socket";

   // 缓冲池的名称定义
   public static final String PTY_IS_POOL = "is_pool";

   // 会话的名称定义
   public static final String PTY_IS_SESSION = "is_session";

   // 消息的名称定义
   public static final String PTY_IS_MESSAGE = "is_message";

   // 存储器的名称定义
   public static final String PTY_IS_STORAGE = "is_storage";

   // 头名称的名称定义
   public static final String PTY_HEAD_NAME = "head_name";

   // 实体代码的名称定义
   public static final String PTY_SOURCE_ENTITY = "source_entity";

   // 实体父名称的名称定义
   public static final String PTY_PARENT_ENTITY = "parent_entity";

   // 模块代码的名称定义
   public static final String PTY_SOURCE_MODULE = "source_module";

   // 模块父名称的名称定义
   public static final String PTY_PARENT_MODULE = "parent_module";

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

   // 代码的定义
   @AName("code")
   protected String _code;

   // 代码名称的定义
   @AName("code_name")
   protected String _codeName;

   // 代码全称的定义
   @AName("code_full")
   protected String _codeFull;

   // 链接关联的定义
   @AName("is_socket")
   protected String _isSocket;

   // 缓冲池的定义
   @AName("is_pool")
   protected String _isPool;

   // 会话的定义
   @AName("is_session")
   protected String _isSession;

   // 消息的定义
   @AName("is_message")
   protected String _isMessage;

   // 存储器的定义
   @AName("is_storage")
   protected String _isStorage;

   // 头名称的定义
   @AName("head_name")
   protected String _headName;

   // 实体代码的定义
   @AName("source_entity")
   protected String _sourceEntity;

   // 实体父名称的定义
   @AName("parent_entity")
   protected String _parentEntity;

   // 模块代码的定义
   @AName("source_module")
   protected String _sourceModule;

   // 模块父名称的定义
   @AName("parent_module")
   protected String _parentModule;

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
   public String getCodeFull(){
      return _codeFull;
   }

   //============================================================
   // <T>设置代码全称的内容。</T>
   //
   // @param value 代码全称
   //============================================================
   public void setCodeFull(String value){
      _codeFull = value;
   }

   //============================================================
   // <T>获得链接关联的内容。</T>
   //
   // @return 链接关联
   //============================================================
   public String getIsSocket(){
      return _isSocket;
   }

   //============================================================
   // <T>设置链接关联的内容。</T>
   //
   // @param value 链接关联
   //============================================================
   public void setIsSocket(String value){
      _isSocket = value;
   }

   //============================================================
   // <T>获得缓冲池的内容。</T>
   //
   // @return 缓冲池
   //============================================================
   public String getIsPool(){
      return _isPool;
   }

   //============================================================
   // <T>设置缓冲池的内容。</T>
   //
   // @param value 缓冲池
   //============================================================
   public void setIsPool(String value){
      _isPool = value;
   }

   //============================================================
   // <T>获得会话的内容。</T>
   //
   // @return 会话
   //============================================================
   public String getIsSession(){
      return _isSession;
   }

   //============================================================
   // <T>设置会话的内容。</T>
   //
   // @param value 会话
   //============================================================
   public void setIsSession(String value){
      _isSession = value;
   }

   //============================================================
   // <T>获得消息的内容。</T>
   //
   // @return 消息
   //============================================================
   public String getIsMessage(){
      return _isMessage;
   }

   //============================================================
   // <T>设置消息的内容。</T>
   //
   // @param value 消息
   //============================================================
   public void setIsMessage(String value){
      _isMessage = value;
   }

   //============================================================
   // <T>获得存储器的内容。</T>
   //
   // @return 存储器
   //============================================================
   public String getIsStorage(){
      return _isStorage;
   }

   //============================================================
   // <T>设置存储器的内容。</T>
   //
   // @param value 存储器
   //============================================================
   public void setIsStorage(String value){
      _isStorage = value;
   }

   //============================================================
   // <T>获得头名称的内容。</T>
   //
   // @return 头名称
   //============================================================
   public String getHeadName(){
      return _headName;
   }

   //============================================================
   // <T>设置头名称的内容。</T>
   //
   // @param value 头名称
   //============================================================
   public void setHeadName(String value){
      _headName = value;
   }

   //============================================================
   // <T>获得实体代码的内容。</T>
   //
   // @return 实体代码
   //============================================================
   public String getSourceEntity(){
      return _sourceEntity;
   }

   //============================================================
   // <T>设置实体代码的内容。</T>
   //
   // @param value 实体代码
   //============================================================
   public void setSourceEntity(String value){
      _sourceEntity = value;
   }

   //============================================================
   // <T>获得实体父名称的内容。</T>
   //
   // @return 实体父名称
   //============================================================
   public String getParentEntity(){
      return _parentEntity;
   }

   //============================================================
   // <T>设置实体父名称的内容。</T>
   //
   // @param value 实体父名称
   //============================================================
   public void setParentEntity(String value){
      _parentEntity = value;
   }

   //============================================================
   // <T>获得模块代码的内容。</T>
   //
   // @return 模块代码
   //============================================================
   public String getSourceModule(){
      return _sourceModule;
   }

   //============================================================
   // <T>设置模块代码的内容。</T>
   //
   // @param value 模块代码
   //============================================================
   public void setSourceModule(String value){
      _sourceModule = value;
   }

   //============================================================
   // <T>获得模块父名称的内容。</T>
   //
   // @return 模块父名称
   //============================================================
   public String getParentModule(){
      return _parentModule;
   }

   //============================================================
   // <T>设置模块父名称的内容。</T>
   //
   // @param value 模块父名称
   //============================================================
   public void setParentModule(String value){
      _parentModule = value;
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
      }else if(PTY_CODE.equalsIgnoreCase(name)){
         return getCode();
      }else if(PTY_CODE_NAME.equalsIgnoreCase(name)){
         return getCodeName();
      }else if(PTY_CODE_FULL.equalsIgnoreCase(name)){
         return getCodeFull();
      }else if(PTY_IS_SOCKET.equalsIgnoreCase(name)){
         return getIsSocket();
      }else if(PTY_IS_POOL.equalsIgnoreCase(name)){
         return getIsPool();
      }else if(PTY_IS_SESSION.equalsIgnoreCase(name)){
         return getIsSession();
      }else if(PTY_IS_MESSAGE.equalsIgnoreCase(name)){
         return getIsMessage();
      }else if(PTY_IS_STORAGE.equalsIgnoreCase(name)){
         return getIsStorage();
      }else if(PTY_HEAD_NAME.equalsIgnoreCase(name)){
         return getHeadName();
      }else if(PTY_SOURCE_ENTITY.equalsIgnoreCase(name)){
         return getSourceEntity();
      }else if(PTY_PARENT_ENTITY.equalsIgnoreCase(name)){
         return getParentEntity();
      }else if(PTY_SOURCE_MODULE.equalsIgnoreCase(name)){
         return getSourceModule();
      }else if(PTY_PARENT_MODULE.equalsIgnoreCase(name)){
         return getParentModule();
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
      }else if(PTY_CODE.equalsIgnoreCase(name)){
         setCode(value);
      }else if(PTY_CODE_NAME.equalsIgnoreCase(name)){
         setCodeName(value);
      }else if(PTY_CODE_FULL.equalsIgnoreCase(name)){
         setCodeFull(value);
      }else if(PTY_IS_SOCKET.equalsIgnoreCase(name)){
         setIsSocket(value);
      }else if(PTY_IS_POOL.equalsIgnoreCase(name)){
         setIsPool(value);
      }else if(PTY_IS_SESSION.equalsIgnoreCase(name)){
         setIsSession(value);
      }else if(PTY_IS_MESSAGE.equalsIgnoreCase(name)){
         setIsMessage(value);
      }else if(PTY_IS_STORAGE.equalsIgnoreCase(name)){
         setIsStorage(value);
      }else if(PTY_HEAD_NAME.equalsIgnoreCase(name)){
         setHeadName(value);
      }else if(PTY_SOURCE_ENTITY.equalsIgnoreCase(name)){
         setSourceEntity(value);
      }else if(PTY_PARENT_ENTITY.equalsIgnoreCase(name)){
         setParentEntity(value);
      }else if(PTY_SOURCE_MODULE.equalsIgnoreCase(name)){
         setSourceModule(value);
      }else if(PTY_PARENT_MODULE.equalsIgnoreCase(name)){
         setParentModule(value);
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
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
         if(config.contains("code_name")){
            setCodeName(config.get(PTY_CODE_NAME));
         }
         if(config.contains("code_full")){
            setCodeFull(config.get(PTY_CODE_FULL));
         }
         if(config.contains("is_socket")){
            setIsSocket(config.get(PTY_IS_SOCKET));
         }
         if(config.contains("is_pool")){
            setIsPool(config.get(PTY_IS_POOL));
         }
         if(config.contains("is_session")){
            setIsSession(config.get(PTY_IS_SESSION));
         }
         if(config.contains("is_message")){
            setIsMessage(config.get(PTY_IS_MESSAGE));
         }
         if(config.contains("is_storage")){
            setIsStorage(config.get(PTY_IS_STORAGE));
         }
         if(config.contains("head_name")){
            setHeadName(config.get(PTY_HEAD_NAME));
         }
         if(config.contains("source_entity")){
            setSourceEntity(config.get(PTY_SOURCE_ENTITY));
         }
         if(config.contains("parent_entity")){
            setParentEntity(config.get(PTY_PARENT_ENTITY));
         }
         if(config.contains("source_module")){
            setSourceModule(config.get(PTY_SOURCE_MODULE));
         }
         if(config.contains("parent_module")){
            setParentModule(config.get(PTY_PARENT_MODULE));
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
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
         if(config.contains("code_name")){
            setCodeName(config.get(PTY_CODE_NAME));
         }
         if(config.contains("code_full")){
            setCodeFull(config.get(PTY_CODE_FULL));
         }
         if(config.contains("is_socket")){
            setIsSocket(config.get(PTY_IS_SOCKET));
         }
         if(config.contains("is_pool")){
            setIsPool(config.get(PTY_IS_POOL));
         }
         if(config.contains("is_session")){
            setIsSession(config.get(PTY_IS_SESSION));
         }
         if(config.contains("is_message")){
            setIsMessage(config.get(PTY_IS_MESSAGE));
         }
         if(config.contains("is_storage")){
            setIsStorage(config.get(PTY_IS_STORAGE));
         }
         if(config.contains("head_name")){
            setHeadName(config.get(PTY_HEAD_NAME));
         }
         if(config.contains("source_entity")){
            setSourceEntity(config.get(PTY_SOURCE_ENTITY));
         }
         if(config.contains("parent_entity")){
            setParentEntity(config.get(PTY_PARENT_ENTITY));
         }
         if(config.contains("source_module")){
            setSourceModule(config.get(PTY_SOURCE_MODULE));
         }
         if(config.contains("parent_module")){
            setParentModule(config.get(PTY_PARENT_MODULE));
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
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
         if(config.contains("code_name")){
            setCodeName(config.get(PTY_CODE_NAME));
         }
         if(config.contains("code_full")){
            setCodeFull(config.get(PTY_CODE_FULL));
         }
         if(config.contains("is_socket")){
            setIsSocket(config.get(PTY_IS_SOCKET));
         }
         if(config.contains("is_pool")){
            setIsPool(config.get(PTY_IS_POOL));
         }
         if(config.contains("is_session")){
            setIsSession(config.get(PTY_IS_SESSION));
         }
         if(config.contains("is_message")){
            setIsMessage(config.get(PTY_IS_MESSAGE));
         }
         if(config.contains("is_storage")){
            setIsStorage(config.get(PTY_IS_STORAGE));
         }
         if(config.contains("head_name")){
            setHeadName(config.get(PTY_HEAD_NAME));
         }
         if(config.contains("source_entity")){
            setSourceEntity(config.get(PTY_SOURCE_ENTITY));
         }
         if(config.contains("parent_entity")){
            setParentEntity(config.get(PTY_PARENT_ENTITY));
         }
         if(config.contains("source_module")){
            setSourceModule(config.get(PTY_SOURCE_MODULE));
         }
         if(config.contains("parent_module")){
            setParentModule(config.get(PTY_PARENT_MODULE));
         }
      }else if(EXmlConfig.Default == type){
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
         if(RString.isEmpty(getCodeFull())){
            if(config.contains("code_full")){
               setCodeFull(config.get(PTY_CODE_FULL));
            }
         }
         if(RString.isEmpty(getIsSocket())){
            if(config.contains("is_socket")){
               setIsSocket(config.get(PTY_IS_SOCKET));
            }
         }
         if(RString.isEmpty(getIsPool())){
            if(config.contains("is_pool")){
               setIsPool(config.get(PTY_IS_POOL));
            }
         }
         if(RString.isEmpty(getIsSession())){
            if(config.contains("is_session")){
               setIsSession(config.get(PTY_IS_SESSION));
            }
         }
         if(RString.isEmpty(getIsMessage())){
            if(config.contains("is_message")){
               setIsMessage(config.get(PTY_IS_MESSAGE));
            }
         }
         if(RString.isEmpty(getIsStorage())){
            if(config.contains("is_storage")){
               setIsStorage(config.get(PTY_IS_STORAGE));
            }
         }
         if(RString.isEmpty(getHeadName())){
            if(config.contains("head_name")){
               setHeadName(config.get(PTY_HEAD_NAME));
            }
         }
         if(RString.isEmpty(getSourceEntity())){
            if(config.contains("source_entity")){
               setSourceEntity(config.get(PTY_SOURCE_ENTITY));
            }
         }
         if(RString.isEmpty(getParentEntity())){
            if(config.contains("parent_entity")){
               setParentEntity(config.get(PTY_PARENT_ENTITY));
            }
         }
         if(RString.isEmpty(getSourceModule())){
            if(config.contains("source_module")){
               setSourceModule(config.get(PTY_SOURCE_MODULE));
            }
         }
         if(RString.isEmpty(getParentModule())){
            if(config.contains("parent_module")){
               setParentModule(config.get(PTY_PARENT_MODULE));
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
         if(RString.isNotEmpty(getCode())){
            config.set(PTY_CODE, getCode());
         }
         if(RString.isNotEmpty(getCodeName())){
            config.set(PTY_CODE_NAME, getCodeName());
         }
         if(RString.isNotEmpty(getCodeFull())){
            config.set(PTY_CODE_FULL, getCodeFull());
         }
         if(RString.isNotEmpty(getIsSocket())){
            config.set(PTY_IS_SOCKET, getIsSocket());
         }
         if(RString.isNotEmpty(getIsPool())){
            config.set(PTY_IS_POOL, getIsPool());
         }
         if(RString.isNotEmpty(getIsSession())){
            config.set(PTY_IS_SESSION, getIsSession());
         }
         if(RString.isNotEmpty(getIsMessage())){
            config.set(PTY_IS_MESSAGE, getIsMessage());
         }
         if(RString.isNotEmpty(getIsStorage())){
            config.set(PTY_IS_STORAGE, getIsStorage());
         }
         if(RString.isNotEmpty(getHeadName())){
            config.set(PTY_HEAD_NAME, getHeadName());
         }
         if(RString.isNotEmpty(getSourceEntity())){
            config.set(PTY_SOURCE_ENTITY, getSourceEntity());
         }
         if(RString.isNotEmpty(getParentEntity())){
            config.set(PTY_PARENT_ENTITY, getParentEntity());
         }
         if(RString.isNotEmpty(getSourceModule())){
            config.set(PTY_SOURCE_MODULE, getSourceModule());
         }
         if(RString.isNotEmpty(getParentModule())){
            config.set(PTY_PARENT_MODULE, getParentModule());
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
         if(RString.isNotEmpty(getCode())){
            config.set(PTY_CODE, getCode());
         }
         if(RString.isNotEmpty(getCodeName())){
            config.set(PTY_CODE_NAME, getCodeName());
         }
         if(RString.isNotEmpty(getCodeFull())){
            config.set(PTY_CODE_FULL, getCodeFull());
         }
         if(RString.isNotEmpty(getIsSocket())){
            config.set(PTY_IS_SOCKET, getIsSocket());
         }
         if(RString.isNotEmpty(getIsPool())){
            config.set(PTY_IS_POOL, getIsPool());
         }
         if(RString.isNotEmpty(getIsSession())){
            config.set(PTY_IS_SESSION, getIsSession());
         }
         if(RString.isNotEmpty(getIsMessage())){
            config.set(PTY_IS_MESSAGE, getIsMessage());
         }
         if(RString.isNotEmpty(getIsStorage())){
            config.set(PTY_IS_STORAGE, getIsStorage());
         }
         if(RString.isNotEmpty(getHeadName())){
            config.set(PTY_HEAD_NAME, getHeadName());
         }
         if(RString.isNotEmpty(getSourceEntity())){
            config.set(PTY_SOURCE_ENTITY, getSourceEntity());
         }
         if(RString.isNotEmpty(getParentEntity())){
            config.set(PTY_PARENT_ENTITY, getParentEntity());
         }
         if(RString.isNotEmpty(getSourceModule())){
            config.set(PTY_SOURCE_MODULE, getSourceModule());
         }
         if(RString.isNotEmpty(getParentModule())){
            config.set(PTY_PARENT_MODULE, getParentModule());
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
         String sCode = getCode();
         if(RString.isNotEmpty(sCode)){
            config.set(PTY_CODE, sCode);
         }
         String sCodeName = getCodeName();
         if(RString.isNotEmpty(sCodeName)){
            config.set(PTY_CODE_NAME, sCodeName);
         }
         String sCodeFull = getCodeFull();
         if(RString.isNotEmpty(sCodeFull)){
            config.set(PTY_CODE_FULL, sCodeFull);
         }
         String sIsSocket = getIsSocket();
         if(RString.isNotEmpty(sIsSocket)){
            config.set(PTY_IS_SOCKET, sIsSocket);
         }
         String sIsPool = getIsPool();
         if(RString.isNotEmpty(sIsPool)){
            config.set(PTY_IS_POOL, sIsPool);
         }
         String sIsSession = getIsSession();
         if(RString.isNotEmpty(sIsSession)){
            config.set(PTY_IS_SESSION, sIsSession);
         }
         String sIsMessage = getIsMessage();
         if(RString.isNotEmpty(sIsMessage)){
            config.set(PTY_IS_MESSAGE, sIsMessage);
         }
         String sIsStorage = getIsStorage();
         if(RString.isNotEmpty(sIsStorage)){
            config.set(PTY_IS_STORAGE, sIsStorage);
         }
         String sHeadName = getHeadName();
         if(RString.isNotEmpty(sHeadName)){
            config.set(PTY_HEAD_NAME, sHeadName);
         }
         String sSourceEntity = getSourceEntity();
         if(RString.isNotEmpty(sSourceEntity)){
            config.set(PTY_SOURCE_ENTITY, sSourceEntity);
         }
         String sParentEntity = getParentEntity();
         if(RString.isNotEmpty(sParentEntity)){
            config.set(PTY_PARENT_ENTITY, sParentEntity);
         }
         String sSourceModule = getSourceModule();
         if(RString.isNotEmpty(sSourceModule)){
            config.set(PTY_SOURCE_MODULE, sSourceModule);
         }
         String sParentModule = getParentModule();
         if(RString.isNotEmpty(sParentModule)){
            config.set(PTY_PARENT_MODULE, sParentModule);
         }
      }else if(EXmlConfig.Default == type){
         String sCode = getCode();
         if(RString.isNotEmpty(sCode)){
            config.set(PTY_CODE, sCode);
         }
         String sCodeName = getCodeName();
         if(RString.isNotEmpty(sCodeName)){
            config.set(PTY_CODE_NAME, sCodeName);
         }
         String sCodeFull = getCodeFull();
         if(RString.isNotEmpty(sCodeFull)){
            config.set(PTY_CODE_FULL, sCodeFull);
         }
         String sIsSocket = getIsSocket();
         if(RString.isNotEmpty(sIsSocket)){
            config.set(PTY_IS_SOCKET, sIsSocket);
         }
         String sIsPool = getIsPool();
         if(RString.isNotEmpty(sIsPool)){
            config.set(PTY_IS_POOL, sIsPool);
         }
         String sIsSession = getIsSession();
         if(RString.isNotEmpty(sIsSession)){
            config.set(PTY_IS_SESSION, sIsSession);
         }
         String sIsMessage = getIsMessage();
         if(RString.isNotEmpty(sIsMessage)){
            config.set(PTY_IS_MESSAGE, sIsMessage);
         }
         String sIsStorage = getIsStorage();
         if(RString.isNotEmpty(sIsStorage)){
            config.set(PTY_IS_STORAGE, sIsStorage);
         }
         String sHeadName = getHeadName();
         if(RString.isNotEmpty(sHeadName)){
            config.set(PTY_HEAD_NAME, sHeadName);
         }
         String sSourceEntity = getSourceEntity();
         if(RString.isNotEmpty(sSourceEntity)){
            config.set(PTY_SOURCE_ENTITY, sSourceEntity);
         }
         String sParentEntity = getParentEntity();
         if(RString.isNotEmpty(sParentEntity)){
            config.set(PTY_PARENT_ENTITY, sParentEntity);
         }
         String sSourceModule = getSourceModule();
         if(RString.isNotEmpty(sSourceModule)){
            config.set(PTY_SOURCE_MODULE, sSourceModule);
         }
         String sParentModule = getParentModule();
         if(RString.isNotEmpty(sParentModule)){
            config.set(PTY_PARENT_MODULE, sParentModule);
         }
      }
   }
}