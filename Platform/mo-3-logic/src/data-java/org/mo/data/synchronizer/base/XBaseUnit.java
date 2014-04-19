package org.mo.data.synchronizer.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.data.synchronizer.common.XObjectFace;

//============================================================
// <T>数据单元对象的XML节点基类。</T>
//============================================================
public abstract class XBaseUnit
      extends FXmlObject
      implements
         XObjectFace
{
   // 名称定义
   public static final String NAME = "Unit";

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

   // 来源数据名称的名称定义
   public static final String PTY_SOURCE_DATA_NAME = "source_data_name";

   // 目标数据名称的名称定义
   public static final String PTY_TARGET_DATA_NAME = "target_data_name";

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

   // 来源数据名称的定义
   @AName("source_data_name")
   protected String _sourceDataName;

   // 目标数据名称的定义
   @AName("target_data_name")
   protected String _targetDataName;

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
   // <T>获得来源数据名称的内容。</T>
   //
   // @return 来源数据名称
   //============================================================
   public String getSourceDataName(){
      return _sourceDataName;
   }

   //============================================================
   // <T>设置来源数据名称的内容。</T>
   //
   // @param value 来源数据名称
   //============================================================
   public void setSourceDataName(String value){
      _sourceDataName = value;
   }

   //============================================================
   // <T>获得目标数据名称的内容。</T>
   //
   // @return 目标数据名称
   //============================================================
   public String getTargetDataName(){
      return _targetDataName;
   }

   //============================================================
   // <T>设置目标数据名称的内容。</T>
   //
   // @param value 目标数据名称
   //============================================================
   public void setTargetDataName(String value){
      _targetDataName = value;
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
      }else if(PTY_SOURCE_DATA_NAME.equalsIgnoreCase(name)){
         return getSourceDataName();
      }else if(PTY_TARGET_DATA_NAME.equalsIgnoreCase(name)){
         return getTargetDataName();
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
      }else if(PTY_SOURCE_DATA_NAME.equalsIgnoreCase(name)){
         setSourceDataName(value);
      }else if(PTY_TARGET_DATA_NAME.equalsIgnoreCase(name)){
         setTargetDataName(value);
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
         if(config.contains("source_data_name")){
            setSourceDataName(config.get(PTY_SOURCE_DATA_NAME));
         }
         if(config.contains("target_data_name")){
            setTargetDataName(config.get(PTY_TARGET_DATA_NAME));
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
         if(config.contains("source_data_name")){
            setSourceDataName(config.get(PTY_SOURCE_DATA_NAME));
         }
         if(config.contains("target_data_name")){
            setTargetDataName(config.get(PTY_TARGET_DATA_NAME));
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
         if(config.contains("source_data_name")){
            setSourceDataName(config.get(PTY_SOURCE_DATA_NAME));
         }
         if(config.contains("target_data_name")){
            setTargetDataName(config.get(PTY_TARGET_DATA_NAME));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getCode())){
            if(config.contains("code")){
               setCode(config.get(PTY_CODE));
            }
         }
         if(RString.isEmpty(getSourceDataName())){
            if(config.contains("source_data_name")){
               setSourceDataName(config.get(PTY_SOURCE_DATA_NAME));
            }
         }
         if(RString.isEmpty(getTargetDataName())){
            if(config.contains("target_data_name")){
               setTargetDataName(config.get(PTY_TARGET_DATA_NAME));
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
         if(RString.isNotEmpty(getSourceDataName())){
            config.set(PTY_SOURCE_DATA_NAME, getSourceDataName());
         }
         if(RString.isNotEmpty(getTargetDataName())){
            config.set(PTY_TARGET_DATA_NAME, getTargetDataName());
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
         if(RString.isNotEmpty(getSourceDataName())){
            config.set(PTY_SOURCE_DATA_NAME, getSourceDataName());
         }
         if(RString.isNotEmpty(getTargetDataName())){
            config.set(PTY_TARGET_DATA_NAME, getTargetDataName());
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
         String sSourceDataName = getSourceDataName();
         if(RString.isNotEmpty(sSourceDataName)){
            config.set(PTY_SOURCE_DATA_NAME, sSourceDataName);
         }
         String sTargetDataName = getTargetDataName();
         if(RString.isNotEmpty(sTargetDataName)){
            config.set(PTY_TARGET_DATA_NAME, sTargetDataName);
         }
      }else if(EXmlConfig.Default == type){
         String sCode = getCode();
         if(RString.isNotEmpty(sCode)){
            config.set(PTY_CODE, sCode);
         }
         String sSourceDataName = getSourceDataName();
         if(RString.isNotEmpty(sSourceDataName)){
            config.set(PTY_SOURCE_DATA_NAME, sSourceDataName);
         }
         String sTargetDataName = getTargetDataName();
         if(RString.isNotEmpty(sTargetDataName)){
            config.set(PTY_TARGET_DATA_NAME, sTargetDataName);
         }
      }
   }
}