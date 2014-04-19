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
// <T>处理器对象的XML节点基类。</T>
//============================================================
public abstract class XBaseProcessor
      extends FXmlObject
      implements
         XObjectFace
{
   // 名称定义
   public static final String NAME = "Processor";

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

   // 逻辑名称的名称定义
   public static final String PTY_LOGIC_NAME = "logic_name";

   // 含有模块的名称定义
   public static final String PTY_HAS_MODULE = "has_module";

   // 代码号的名称定义
   public static final String PTY_CODE = "code";

   // 代码全称的名称定义
   public static final String PTY_FULL_CODE = "full_code";

   // 头名称的名称定义
   public static final String PTY_HEAD_NAME = "head_name";

   // 源名称的名称定义
   public static final String PTY_SOURCE_NAME = "source_name";

   // 父名称的名称定义
   public static final String PTY_PARENT_NAME = "parent_name";

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

   // 逻辑名称的定义
   @AName("logic_name")
   protected String _logicName;

   // 含有模块的定义
   @AName("has_module")
   protected String _hasModule;

   // 代码号的定义
   @AName("code")
   protected String _code;

   // 代码全称的定义
   @AName("full_code")
   protected String _fullCode;

   // 头名称的定义
   @AName("head_name")
   protected String _headName;

   // 源名称的定义
   @AName("source_name")
   protected String _sourceName;

   // 父名称的定义
   @AName("parent_name")
   protected String _parentName;

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
   // <T>获得逻辑名称的内容。</T>
   //
   // @return 逻辑名称
   //============================================================
   public String getLogicName(){
      return _logicName;
   }

   //============================================================
   // <T>设置逻辑名称的内容。</T>
   //
   // @param value 逻辑名称
   //============================================================
   public void setLogicName(String value){
      _logicName = value;
   }

   //============================================================
   // <T>获得含有模块的内容。</T>
   //
   // @return 含有模块
   //============================================================
   public String getHasModule(){
      return _hasModule;
   }

   //============================================================
   // <T>设置含有模块的内容。</T>
   //
   // @param value 含有模块
   //============================================================
   public void setHasModule(String value){
      _hasModule = value;
   }

   //============================================================
   // <T>获得代码号的内容。</T>
   //
   // @return 代码号
   //============================================================
   public String getCode(){
      return _code;
   }

   //============================================================
   // <T>设置代码号的内容。</T>
   //
   // @param value 代码号
   //============================================================
   public void setCode(String value){
      _code = value;
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
   // <T>获得源名称的内容。</T>
   //
   // @return 源名称
   //============================================================
   public String getSourceName(){
      return _sourceName;
   }

   //============================================================
   // <T>设置源名称的内容。</T>
   //
   // @param value 源名称
   //============================================================
   public void setSourceName(String value){
      _sourceName = value;
   }

   //============================================================
   // <T>获得父名称的内容。</T>
   //
   // @return 父名称
   //============================================================
   public String getParentName(){
      return _parentName;
   }

   //============================================================
   // <T>设置父名称的内容。</T>
   //
   // @param value 父名称
   //============================================================
   public void setParentName(String value){
      _parentName = value;
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
      }else if(PTY_LOGIC_NAME.equalsIgnoreCase(name)){
         return getLogicName();
      }else if(PTY_HAS_MODULE.equalsIgnoreCase(name)){
         return getHasModule();
      }else if(PTY_CODE.equalsIgnoreCase(name)){
         return getCode();
      }else if(PTY_FULL_CODE.equalsIgnoreCase(name)){
         return getFullCode();
      }else if(PTY_HEAD_NAME.equalsIgnoreCase(name)){
         return getHeadName();
      }else if(PTY_SOURCE_NAME.equalsIgnoreCase(name)){
         return getSourceName();
      }else if(PTY_PARENT_NAME.equalsIgnoreCase(name)){
         return getParentName();
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
      }else if(PTY_LOGIC_NAME.equalsIgnoreCase(name)){
         setLogicName(value);
      }else if(PTY_HAS_MODULE.equalsIgnoreCase(name)){
         setHasModule(value);
      }else if(PTY_CODE.equalsIgnoreCase(name)){
         setCode(value);
      }else if(PTY_FULL_CODE.equalsIgnoreCase(name)){
         setFullCode(value);
      }else if(PTY_HEAD_NAME.equalsIgnoreCase(name)){
         setHeadName(value);
      }else if(PTY_SOURCE_NAME.equalsIgnoreCase(name)){
         setSourceName(value);
      }else if(PTY_PARENT_NAME.equalsIgnoreCase(name)){
         setParentName(value);
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
         if(config.contains("logic_name")){
            setLogicName(config.get(PTY_LOGIC_NAME));
         }
         if(config.contains("has_module")){
            setHasModule(config.get(PTY_HAS_MODULE));
         }
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
         if(config.contains("full_code")){
            setFullCode(config.get(PTY_FULL_CODE));
         }
         if(config.contains("head_name")){
            setHeadName(config.get(PTY_HEAD_NAME));
         }
         if(config.contains("source_name")){
            setSourceName(config.get(PTY_SOURCE_NAME));
         }
         if(config.contains("parent_name")){
            setParentName(config.get(PTY_PARENT_NAME));
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
         if(config.contains("logic_name")){
            setLogicName(config.get(PTY_LOGIC_NAME));
         }
         if(config.contains("has_module")){
            setHasModule(config.get(PTY_HAS_MODULE));
         }
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
         if(config.contains("full_code")){
            setFullCode(config.get(PTY_FULL_CODE));
         }
         if(config.contains("head_name")){
            setHeadName(config.get(PTY_HEAD_NAME));
         }
         if(config.contains("source_name")){
            setSourceName(config.get(PTY_SOURCE_NAME));
         }
         if(config.contains("parent_name")){
            setParentName(config.get(PTY_PARENT_NAME));
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
         if(config.contains("logic_name")){
            setLogicName(config.get(PTY_LOGIC_NAME));
         }
         if(config.contains("has_module")){
            setHasModule(config.get(PTY_HAS_MODULE));
         }
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
         if(config.contains("full_code")){
            setFullCode(config.get(PTY_FULL_CODE));
         }
         if(config.contains("head_name")){
            setHeadName(config.get(PTY_HEAD_NAME));
         }
         if(config.contains("source_name")){
            setSourceName(config.get(PTY_SOURCE_NAME));
         }
         if(config.contains("parent_name")){
            setParentName(config.get(PTY_PARENT_NAME));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getLogicName())){
            if(config.contains("logic_name")){
               setLogicName(config.get(PTY_LOGIC_NAME));
            }
         }
         if(RString.isEmpty(getHasModule())){
            if(config.contains("has_module")){
               setHasModule(config.get(PTY_HAS_MODULE));
            }
         }
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
         if(RString.isEmpty(getHeadName())){
            if(config.contains("head_name")){
               setHeadName(config.get(PTY_HEAD_NAME));
            }
         }
         if(RString.isEmpty(getSourceName())){
            if(config.contains("source_name")){
               setSourceName(config.get(PTY_SOURCE_NAME));
            }
         }
         if(RString.isEmpty(getParentName())){
            if(config.contains("parent_name")){
               setParentName(config.get(PTY_PARENT_NAME));
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
         if(RString.isNotEmpty(getLogicName())){
            config.set(PTY_LOGIC_NAME, getLogicName());
         }
         if(RString.isNotEmpty(getHasModule())){
            config.set(PTY_HAS_MODULE, getHasModule());
         }
         if(RString.isNotEmpty(getCode())){
            config.set(PTY_CODE, getCode());
         }
         if(RString.isNotEmpty(getFullCode())){
            config.set(PTY_FULL_CODE, getFullCode());
         }
         if(RString.isNotEmpty(getHeadName())){
            config.set(PTY_HEAD_NAME, getHeadName());
         }
         if(RString.isNotEmpty(getSourceName())){
            config.set(PTY_SOURCE_NAME, getSourceName());
         }
         if(RString.isNotEmpty(getParentName())){
            config.set(PTY_PARENT_NAME, getParentName());
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
         if(RString.isNotEmpty(getLogicName())){
            config.set(PTY_LOGIC_NAME, getLogicName());
         }
         if(RString.isNotEmpty(getHasModule())){
            config.set(PTY_HAS_MODULE, getHasModule());
         }
         if(RString.isNotEmpty(getCode())){
            config.set(PTY_CODE, getCode());
         }
         if(RString.isNotEmpty(getFullCode())){
            config.set(PTY_FULL_CODE, getFullCode());
         }
         if(RString.isNotEmpty(getHeadName())){
            config.set(PTY_HEAD_NAME, getHeadName());
         }
         if(RString.isNotEmpty(getSourceName())){
            config.set(PTY_SOURCE_NAME, getSourceName());
         }
         if(RString.isNotEmpty(getParentName())){
            config.set(PTY_PARENT_NAME, getParentName());
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
         String sLogicName = getLogicName();
         if(RString.isNotEmpty(sLogicName)){
            config.set(PTY_LOGIC_NAME, sLogicName);
         }
         String sHasModule = getHasModule();
         if(RString.isNotEmpty(sHasModule)){
            config.set(PTY_HAS_MODULE, sHasModule);
         }
         String sCode = getCode();
         if(RString.isNotEmpty(sCode)){
            config.set(PTY_CODE, sCode);
         }
         String sFullCode = getFullCode();
         if(RString.isNotEmpty(sFullCode)){
            config.set(PTY_FULL_CODE, sFullCode);
         }
         String sHeadName = getHeadName();
         if(RString.isNotEmpty(sHeadName)){
            config.set(PTY_HEAD_NAME, sHeadName);
         }
         String sSourceName = getSourceName();
         if(RString.isNotEmpty(sSourceName)){
            config.set(PTY_SOURCE_NAME, sSourceName);
         }
         String sParentName = getParentName();
         if(RString.isNotEmpty(sParentName)){
            config.set(PTY_PARENT_NAME, sParentName);
         }
      }else if(EXmlConfig.Default == type){
         String sLogicName = getLogicName();
         if(RString.isNotEmpty(sLogicName)){
            config.set(PTY_LOGIC_NAME, sLogicName);
         }
         String sHasModule = getHasModule();
         if(RString.isNotEmpty(sHasModule)){
            config.set(PTY_HAS_MODULE, sHasModule);
         }
         String sCode = getCode();
         if(RString.isNotEmpty(sCode)){
            config.set(PTY_CODE, sCode);
         }
         String sFullCode = getFullCode();
         if(RString.isNotEmpty(sFullCode)){
            config.set(PTY_FULL_CODE, sFullCode);
         }
         String sHeadName = getHeadName();
         if(RString.isNotEmpty(sHeadName)){
            config.set(PTY_HEAD_NAME, sHeadName);
         }
         String sSourceName = getSourceName();
         if(RString.isNotEmpty(sSourceName)){
            config.set(PTY_SOURCE_NAME, sSourceName);
         }
         String sParentName = getParentName();
         if(RString.isNotEmpty(sParentName)){
            config.set(PTY_PARENT_NAME, sParentName);
         }
      }
   }
}