package org.mo.game.editor.core.instance.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.game.editor.core.instance.common.XObjectFace;

//============================================================
// <T>实体分组对象的XML节点基类。</T>
//============================================================
public abstract class XBaseInstanceGroup
      extends FXmlObject
      implements
         XObjectFace
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

   // 代码名称的名称定义
   public static final String PTY_SOURCE_NAME = "source_name";

   // 代码的名称定义
   public static final String PTY_CODE = "code";

   // 代码全称的名称定义
   public static final String PTY_FULL_CODE = "full_code";

   // 显示顺序的名称定义
   public static final String PTY_DISPLAY_INDEX = "display_index";

   // 包含文件的名称定义
   public static final String PTY_INCLUDE_HEADS = "include_heads";

   // 代码前缀的名称定义
   public static final String PTY_CODE_PREFIX = "code_prefix";

   // 代码后缀的名称定义
   public static final String PTY_CODE_AFTFIX = "code_aftfix";

   // 项目名称的名称定义
   public static final String PTY_PROJECT_NAME = "project_name";

   // 项目代码的名称定义
   public static final String PTY_PROJECT_CODE = "project_code";

   // 代码分组的名称定义
   public static final String PTY_CODE_GROUP = "code_group";

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

   // 代码名称的定义
   @AName("source_name")
   protected String _sourceName;

   // 代码的定义
   @AName("code")
   protected String _code;

   // 代码全称的定义
   @AName("full_code")
   protected String _fullCode;

   // 显示顺序的定义
   @AName("display_index")
   protected String _displayIndex;

   // 包含文件的定义
   @AName("include_heads")
   protected String _includeHeads;

   // 代码前缀的定义
   @AName("code_prefix")
   protected String _codePrefix;

   // 代码后缀的定义
   @AName("code_aftfix")
   protected String _codeAftfix;

   // 项目名称的定义
   @AName("project_name")
   protected String _projectName;

   // 项目代码的定义
   @AName("project_code")
   protected String _projectCode;

   // 代码分组的定义
   @AName("code_group")
   protected String _codeGroup;

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
   // <T>获得包含文件的内容。</T>
   //
   // @return 包含文件
   //============================================================
   public String getIncludeHeads(){
      return _includeHeads;
   }

   //============================================================
   // <T>设置包含文件的内容。</T>
   //
   // @param value 包含文件
   //============================================================
   public void setIncludeHeads(String value){
      _includeHeads = value;
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
      }else if(PTY_SOURCE_NAME.equalsIgnoreCase(name)){
         return getSourceName();
      }else if(PTY_CODE.equalsIgnoreCase(name)){
         return getCode();
      }else if(PTY_FULL_CODE.equalsIgnoreCase(name)){
         return getFullCode();
      }else if(PTY_DISPLAY_INDEX.equalsIgnoreCase(name)){
         return getDisplayIndex();
      }else if(PTY_INCLUDE_HEADS.equalsIgnoreCase(name)){
         return getIncludeHeads();
      }else if(PTY_CODE_PREFIX.equalsIgnoreCase(name)){
         return getCodePrefix();
      }else if(PTY_CODE_AFTFIX.equalsIgnoreCase(name)){
         return getCodeAftfix();
      }else if(PTY_PROJECT_NAME.equalsIgnoreCase(name)){
         return getProjectName();
      }else if(PTY_PROJECT_CODE.equalsIgnoreCase(name)){
         return getProjectCode();
      }else if(PTY_CODE_GROUP.equalsIgnoreCase(name)){
         return getCodeGroup();
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
      }else if(PTY_SOURCE_NAME.equalsIgnoreCase(name)){
         setSourceName(value);
      }else if(PTY_CODE.equalsIgnoreCase(name)){
         setCode(value);
      }else if(PTY_FULL_CODE.equalsIgnoreCase(name)){
         setFullCode(value);
      }else if(PTY_DISPLAY_INDEX.equalsIgnoreCase(name)){
         setDisplayIndex(value);
      }else if(PTY_INCLUDE_HEADS.equalsIgnoreCase(name)){
         setIncludeHeads(value);
      }else if(PTY_CODE_PREFIX.equalsIgnoreCase(name)){
         setCodePrefix(value);
      }else if(PTY_CODE_AFTFIX.equalsIgnoreCase(name)){
         setCodeAftfix(value);
      }else if(PTY_PROJECT_NAME.equalsIgnoreCase(name)){
         setProjectName(value);
      }else if(PTY_PROJECT_CODE.equalsIgnoreCase(name)){
         setProjectCode(value);
      }else if(PTY_CODE_GROUP.equalsIgnoreCase(name)){
         setCodeGroup(value);
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
         if(config.contains("source_name")){
            setSourceName(config.get(PTY_SOURCE_NAME));
         }
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
         if(config.contains("full_code")){
            setFullCode(config.get(PTY_FULL_CODE));
         }
         if(config.contains("display_index")){
            setDisplayIndex(config.get(PTY_DISPLAY_INDEX));
         }
         if(config.contains("include_heads")){
            setIncludeHeads(config.get(PTY_INCLUDE_HEADS));
         }
         if(config.contains("code_prefix")){
            setCodePrefix(config.get(PTY_CODE_PREFIX));
         }
         if(config.contains("code_aftfix")){
            setCodeAftfix(config.get(PTY_CODE_AFTFIX));
         }
         if(config.contains("project_name")){
            setProjectName(config.get(PTY_PROJECT_NAME));
         }
         if(config.contains("project_code")){
            setProjectCode(config.get(PTY_PROJECT_CODE));
         }
         if(config.contains("code_group")){
            setCodeGroup(config.get(PTY_CODE_GROUP));
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
         if(config.contains("source_name")){
            setSourceName(config.get(PTY_SOURCE_NAME));
         }
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
         if(config.contains("full_code")){
            setFullCode(config.get(PTY_FULL_CODE));
         }
         if(config.contains("display_index")){
            setDisplayIndex(config.get(PTY_DISPLAY_INDEX));
         }
         if(config.contains("include_heads")){
            setIncludeHeads(config.get(PTY_INCLUDE_HEADS));
         }
         if(config.contains("code_prefix")){
            setCodePrefix(config.get(PTY_CODE_PREFIX));
         }
         if(config.contains("code_aftfix")){
            setCodeAftfix(config.get(PTY_CODE_AFTFIX));
         }
         if(config.contains("project_name")){
            setProjectName(config.get(PTY_PROJECT_NAME));
         }
         if(config.contains("project_code")){
            setProjectCode(config.get(PTY_PROJECT_CODE));
         }
         if(config.contains("code_group")){
            setCodeGroup(config.get(PTY_CODE_GROUP));
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
         if(config.contains("source_name")){
            setSourceName(config.get(PTY_SOURCE_NAME));
         }
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
         if(config.contains("full_code")){
            setFullCode(config.get(PTY_FULL_CODE));
         }
         if(config.contains("display_index")){
            setDisplayIndex(config.get(PTY_DISPLAY_INDEX));
         }
         if(config.contains("include_heads")){
            setIncludeHeads(config.get(PTY_INCLUDE_HEADS));
         }
         if(config.contains("code_prefix")){
            setCodePrefix(config.get(PTY_CODE_PREFIX));
         }
         if(config.contains("code_aftfix")){
            setCodeAftfix(config.get(PTY_CODE_AFTFIX));
         }
         if(config.contains("project_name")){
            setProjectName(config.get(PTY_PROJECT_NAME));
         }
         if(config.contains("project_code")){
            setProjectCode(config.get(PTY_PROJECT_CODE));
         }
         if(config.contains("code_group")){
            setCodeGroup(config.get(PTY_CODE_GROUP));
         }
      }else if(EXmlConfig.Default == type){
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
         if(RString.isEmpty(getIncludeHeads())){
            if(config.contains("include_heads")){
               setIncludeHeads(config.get(PTY_INCLUDE_HEADS));
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
         if(RString.isEmpty(getCodeGroup())){
            if(config.contains("code_group")){
               setCodeGroup(config.get(PTY_CODE_GROUP));
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
         if(RString.isNotEmpty(getSourceName())){
            config.set(PTY_SOURCE_NAME, getSourceName());
         }
         if(RString.isNotEmpty(getCode())){
            config.set(PTY_CODE, getCode());
         }
         if(RString.isNotEmpty(getFullCode())){
            config.set(PTY_FULL_CODE, getFullCode());
         }
         if(RString.isNotEmpty(getDisplayIndex())){
            config.set(PTY_DISPLAY_INDEX, getDisplayIndex());
         }
         if(RString.isNotEmpty(getIncludeHeads())){
            config.set(PTY_INCLUDE_HEADS, getIncludeHeads());
         }
         if(RString.isNotEmpty(getCodePrefix())){
            config.set(PTY_CODE_PREFIX, getCodePrefix());
         }
         if(RString.isNotEmpty(getCodeAftfix())){
            config.set(PTY_CODE_AFTFIX, getCodeAftfix());
         }
         if(RString.isNotEmpty(getProjectName())){
            config.set(PTY_PROJECT_NAME, getProjectName());
         }
         if(RString.isNotEmpty(getProjectCode())){
            config.set(PTY_PROJECT_CODE, getProjectCode());
         }
         if(RString.isNotEmpty(getCodeGroup())){
            config.set(PTY_CODE_GROUP, getCodeGroup());
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
         if(RString.isNotEmpty(getSourceName())){
            config.set(PTY_SOURCE_NAME, getSourceName());
         }
         if(RString.isNotEmpty(getCode())){
            config.set(PTY_CODE, getCode());
         }
         if(RString.isNotEmpty(getFullCode())){
            config.set(PTY_FULL_CODE, getFullCode());
         }
         if(RString.isNotEmpty(getDisplayIndex())){
            config.set(PTY_DISPLAY_INDEX, getDisplayIndex());
         }
         if(RString.isNotEmpty(getIncludeHeads())){
            config.set(PTY_INCLUDE_HEADS, getIncludeHeads());
         }
         if(RString.isNotEmpty(getCodePrefix())){
            config.set(PTY_CODE_PREFIX, getCodePrefix());
         }
         if(RString.isNotEmpty(getCodeAftfix())){
            config.set(PTY_CODE_AFTFIX, getCodeAftfix());
         }
         if(RString.isNotEmpty(getProjectName())){
            config.set(PTY_PROJECT_NAME, getProjectName());
         }
         if(RString.isNotEmpty(getProjectCode())){
            config.set(PTY_PROJECT_CODE, getProjectCode());
         }
         if(RString.isNotEmpty(getCodeGroup())){
            config.set(PTY_CODE_GROUP, getCodeGroup());
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
         String sSourceName = getSourceName();
         if(RString.isNotEmpty(sSourceName)){
            config.set(PTY_SOURCE_NAME, sSourceName);
         }
         String sCode = getCode();
         if(RString.isNotEmpty(sCode)){
            config.set(PTY_CODE, sCode);
         }
         String sFullCode = getFullCode();
         if(RString.isNotEmpty(sFullCode)){
            config.set(PTY_FULL_CODE, sFullCode);
         }
         String sDisplayIndex = getDisplayIndex();
         if(RString.isNotEmpty(sDisplayIndex)){
            config.set(PTY_DISPLAY_INDEX, sDisplayIndex);
         }
         String sIncludeHeads = getIncludeHeads();
         if(RString.isNotEmpty(sIncludeHeads)){
            config.set(PTY_INCLUDE_HEADS, sIncludeHeads);
         }
         String sCodePrefix = getCodePrefix();
         if(RString.isNotEmpty(sCodePrefix)){
            config.set(PTY_CODE_PREFIX, sCodePrefix);
         }
         String sCodeAftfix = getCodeAftfix();
         if(RString.isNotEmpty(sCodeAftfix)){
            config.set(PTY_CODE_AFTFIX, sCodeAftfix);
         }
         String sProjectName = getProjectName();
         if(RString.isNotEmpty(sProjectName)){
            config.set(PTY_PROJECT_NAME, sProjectName);
         }
         String sProjectCode = getProjectCode();
         if(RString.isNotEmpty(sProjectCode)){
            config.set(PTY_PROJECT_CODE, sProjectCode);
         }
         String sCodeGroup = getCodeGroup();
         if(RString.isNotEmpty(sCodeGroup)){
            config.set(PTY_CODE_GROUP, sCodeGroup);
         }
      }else if(EXmlConfig.Default == type){
         String sSourceName = getSourceName();
         if(RString.isNotEmpty(sSourceName)){
            config.set(PTY_SOURCE_NAME, sSourceName);
         }
         String sCode = getCode();
         if(RString.isNotEmpty(sCode)){
            config.set(PTY_CODE, sCode);
         }
         String sFullCode = getFullCode();
         if(RString.isNotEmpty(sFullCode)){
            config.set(PTY_FULL_CODE, sFullCode);
         }
         String sDisplayIndex = getDisplayIndex();
         if(RString.isNotEmpty(sDisplayIndex)){
            config.set(PTY_DISPLAY_INDEX, sDisplayIndex);
         }
         String sIncludeHeads = getIncludeHeads();
         if(RString.isNotEmpty(sIncludeHeads)){
            config.set(PTY_INCLUDE_HEADS, sIncludeHeads);
         }
         String sCodePrefix = getCodePrefix();
         if(RString.isNotEmpty(sCodePrefix)){
            config.set(PTY_CODE_PREFIX, sCodePrefix);
         }
         String sCodeAftfix = getCodeAftfix();
         if(RString.isNotEmpty(sCodeAftfix)){
            config.set(PTY_CODE_AFTFIX, sCodeAftfix);
         }
         String sProjectName = getProjectName();
         if(RString.isNotEmpty(sProjectName)){
            config.set(PTY_PROJECT_NAME, sProjectName);
         }
         String sProjectCode = getProjectCode();
         if(RString.isNotEmpty(sProjectCode)){
            config.set(PTY_PROJECT_CODE, sProjectCode);
         }
         String sCodeGroup = getCodeGroup();
         if(RString.isNotEmpty(sCodeGroup)){
            config.set(PTY_CODE_GROUP, sCodeGroup);
         }
      }
   }
}