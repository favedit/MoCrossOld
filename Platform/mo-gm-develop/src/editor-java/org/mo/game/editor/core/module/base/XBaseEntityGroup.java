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
// <T>实体分组对象的XML节点基类。</T>
//============================================================
public abstract class XBaseEntityGroup
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

   // 组名的名称定义
   public static final String PTY_GROUP = "group";

   // 项目名称的名称定义
   public static final String PTY_PROJECT = "project";

   // 代码的名称定义
   public static final String PTY_CODE = "code";

   // 代码名称的名称定义
   public static final String PTY_CODE_NAME = "code_name";

   // 代码全称的名称定义
   public static final String PTY_CODE_FULL = "code_full";

   // 头名称的名称定义
   public static final String PTY_HEAD_NAME = "head_name";

   // 代码名称的名称定义
   public static final String PTY_SOURCE_NAME = "source_name";

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

   // 组名的定义
   @AName("group")
   protected String _group;

   // 项目名称的定义
   @AName("project")
   protected String _project;

   // 代码的定义
   @AName("code")
   protected String _code;

   // 代码名称的定义
   @AName("code_name")
   protected String _codeName;

   // 代码全称的定义
   @AName("code_full")
   protected String _codeFull;

   // 头名称的定义
   @AName("head_name")
   protected String _headName;

   // 代码名称的定义
   @AName("source_name")
   protected String _sourceName;

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
   // <T>获得组名的内容。</T>
   //
   // @return 组名
   //============================================================
   public String getGroup(){
      return _group;
   }

   //============================================================
   // <T>设置组名的内容。</T>
   //
   // @param value 组名
   //============================================================
   public void setGroup(String value){
      _group = value;
   }

   //============================================================
   // <T>获得项目名称的内容。</T>
   //
   // @return 项目名称
   //============================================================
   public String getProject(){
      return _project;
   }

   //============================================================
   // <T>设置项目名称的内容。</T>
   //
   // @param value 项目名称
   //============================================================
   public void setProject(String value){
      _project = value;
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
      }else if(PTY_GROUP.equalsIgnoreCase(name)){
         return getGroup();
      }else if(PTY_PROJECT.equalsIgnoreCase(name)){
         return getProject();
      }else if(PTY_CODE.equalsIgnoreCase(name)){
         return getCode();
      }else if(PTY_CODE_NAME.equalsIgnoreCase(name)){
         return getCodeName();
      }else if(PTY_CODE_FULL.equalsIgnoreCase(name)){
         return getCodeFull();
      }else if(PTY_HEAD_NAME.equalsIgnoreCase(name)){
         return getHeadName();
      }else if(PTY_SOURCE_NAME.equalsIgnoreCase(name)){
         return getSourceName();
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
      }else if(PTY_GROUP.equalsIgnoreCase(name)){
         setGroup(value);
      }else if(PTY_PROJECT.equalsIgnoreCase(name)){
         setProject(value);
      }else if(PTY_CODE.equalsIgnoreCase(name)){
         setCode(value);
      }else if(PTY_CODE_NAME.equalsIgnoreCase(name)){
         setCodeName(value);
      }else if(PTY_CODE_FULL.equalsIgnoreCase(name)){
         setCodeFull(value);
      }else if(PTY_HEAD_NAME.equalsIgnoreCase(name)){
         setHeadName(value);
      }else if(PTY_SOURCE_NAME.equalsIgnoreCase(name)){
         setSourceName(value);
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
         if(config.contains("group")){
            setGroup(config.get(PTY_GROUP));
         }
         if(config.contains("project")){
            setProject(config.get(PTY_PROJECT));
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
         if(config.contains("head_name")){
            setHeadName(config.get(PTY_HEAD_NAME));
         }
         if(config.contains("source_name")){
            setSourceName(config.get(PTY_SOURCE_NAME));
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
         if(config.contains("group")){
            setGroup(config.get(PTY_GROUP));
         }
         if(config.contains("project")){
            setProject(config.get(PTY_PROJECT));
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
         if(config.contains("head_name")){
            setHeadName(config.get(PTY_HEAD_NAME));
         }
         if(config.contains("source_name")){
            setSourceName(config.get(PTY_SOURCE_NAME));
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
         if(config.contains("group")){
            setGroup(config.get(PTY_GROUP));
         }
         if(config.contains("project")){
            setProject(config.get(PTY_PROJECT));
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
         if(config.contains("head_name")){
            setHeadName(config.get(PTY_HEAD_NAME));
         }
         if(config.contains("source_name")){
            setSourceName(config.get(PTY_SOURCE_NAME));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getGroup())){
            if(config.contains("group")){
               setGroup(config.get(PTY_GROUP));
            }
         }
         if(RString.isEmpty(getProject())){
            if(config.contains("project")){
               setProject(config.get(PTY_PROJECT));
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
         if(RString.isEmpty(getCodeFull())){
            if(config.contains("code_full")){
               setCodeFull(config.get(PTY_CODE_FULL));
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
         if(RString.isNotEmpty(getGroup())){
            config.set(PTY_GROUP, getGroup());
         }
         if(RString.isNotEmpty(getProject())){
            config.set(PTY_PROJECT, getProject());
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
         if(RString.isNotEmpty(getHeadName())){
            config.set(PTY_HEAD_NAME, getHeadName());
         }
         if(RString.isNotEmpty(getSourceName())){
            config.set(PTY_SOURCE_NAME, getSourceName());
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
         if(RString.isNotEmpty(getGroup())){
            config.set(PTY_GROUP, getGroup());
         }
         if(RString.isNotEmpty(getProject())){
            config.set(PTY_PROJECT, getProject());
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
         if(RString.isNotEmpty(getHeadName())){
            config.set(PTY_HEAD_NAME, getHeadName());
         }
         if(RString.isNotEmpty(getSourceName())){
            config.set(PTY_SOURCE_NAME, getSourceName());
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
         String sGroup = getGroup();
         if(RString.isNotEmpty(sGroup)){
            config.set(PTY_GROUP, sGroup);
         }
         String sProject = getProject();
         if(RString.isNotEmpty(sProject)){
            config.set(PTY_PROJECT, sProject);
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
         String sHeadName = getHeadName();
         if(RString.isNotEmpty(sHeadName)){
            config.set(PTY_HEAD_NAME, sHeadName);
         }
         String sSourceName = getSourceName();
         if(RString.isNotEmpty(sSourceName)){
            config.set(PTY_SOURCE_NAME, sSourceName);
         }
      }else if(EXmlConfig.Default == type){
         String sGroup = getGroup();
         if(RString.isNotEmpty(sGroup)){
            config.set(PTY_GROUP, sGroup);
         }
         String sProject = getProject();
         if(RString.isNotEmpty(sProject)){
            config.set(PTY_PROJECT, sProject);
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
         String sHeadName = getHeadName();
         if(RString.isNotEmpty(sHeadName)){
            config.set(PTY_HEAD_NAME, sHeadName);
         }
         String sSourceName = getSourceName();
         if(RString.isNotEmpty(sSourceName)){
            config.set(PTY_SOURCE_NAME, sSourceName);
         }
      }
   }
}