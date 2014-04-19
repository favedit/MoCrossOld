package org.mo.eng.persistence.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.eng.persistence.common.XObjectFace;

//============================================================
// <T>属性对象的XML节点基类。</T>
//
// @author system
//============================================================
public abstract class XBaseAttribute
      extends FXmlObject
      implements
         XObjectFace
{
   // 名称
   public static final String NAME = "Attribute";

   // 名称的定义
   public static final String PTY_NAME = "name";

   // 标签的定义
   public static final String PTY_LABEL = "label";

   // 有效性的定义
   public static final String PTY_IS_VALID = "is_valid";

   // 描述信息的定义
   public static final String PTY_NOTE = "note";

   // 附加属性的定义
   public static final String PTY_ATTRIBUTES = "attributes";

   // 类型的定义
   public static final String PTY_TYPE = "type";

   // 可存储的定义
   public static final String PTY_IS_STORE = "is_store";

   // 可配置的定义
   public static final String PTY_IS_CONFIG = "is_config";

   // 虚属性的定义
   public static final String PTY_IS_ABSTRACT = "is_abstract";

   // 可默认的定义
   public static final String PTY_IS_DEFAULT = "is_default";

   // 默认值的定义
   public static final String PTY_DATA_DEFAULT = "data_default";

   //============================================================
   // <T>是否指定名称。</T>
   //
   // @param name 名称
   // @return 是否指定名称
   //============================================================
   public static boolean isName(String name){
      return NAME.equals(name);
   }

   //============================================================
   // <T>是否指定接口。</T>
   //
   // @param name 接口
   // @return 是否指定接口
   //============================================================
   public static boolean isInstance(IXmlObject xobject){
      return NAME.equals(xobject.name());
   }

   //============================================================
   // <T>获得名称。</T>
   //
   // @return 名称
   //============================================================
   public String name(){
      return NAME;
   }

   // 名称的定义
   @AName("name")
   protected String _name;

   // 标签的定义
   @AName("label")
   protected FMultiString _label = new FMultiString();

   // 有效性的定义
   @AName("is_valid")
   protected Boolean _isValid = Boolean.FALSE;

   // 描述信息的定义
   @AName("note")
   protected FMultiString _note = new FMultiString();

   // 附加属性的定义
   @AName("attributes")
   protected String _attributes;

   // 类型的定义
   @AName("type")
   protected String _type;

   // 可存储的定义
   @AName("is_store")
   protected Boolean _isStore = Boolean.FALSE;

   // 可配置的定义
   @AName("is_config")
   protected Boolean _isConfig = Boolean.FALSE;

   // 虚属性的定义
   @AName("is_abstract")
   protected Boolean _isAbstract = Boolean.FALSE;

   // 可默认的定义
   @AName("is_default")
   protected String _isDefault;

   // 默认值的定义
   @AName("data_default")
   protected String _dataDefault;

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
   // <T>获得描述信息的内容。</T>
   //
   // @return 描述信息
   //============================================================
   public String getNote(){
      return _note.get();
   }

   //============================================================
   // <T>设置描述信息的内容。</T>
   //
   // @param value 描述信息
   //============================================================
   public void setNote(String value){
      _note.set(value);
   }

   //============================================================
   // <T>获得附加属性的内容。</T>
   //
   // @return 附加属性
   //============================================================
   public String getAttributes(){
      return _attributes;
   }

   //============================================================
   // <T>设置附加属性的内容。</T>
   //
   // @param value 附加属性
   //============================================================
   public void setAttributes(String value){
      _attributes = value;
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
   // <T>获得可存储的内容。</T>
   //
   // @return 可存储
   //============================================================
   public Boolean getIsStore(){
      return _isStore;
   }

   //============================================================
   // <T>设置可存储的内容。</T>
   //
   // @param value 可存储
   //============================================================
   public void setIsStore(Boolean value){
      _isStore = value;
   }

   //============================================================
   // <T>获得可配置的内容。</T>
   //
   // @return 可配置
   //============================================================
   public Boolean getIsConfig(){
      return _isConfig;
   }

   //============================================================
   // <T>设置可配置的内容。</T>
   //
   // @param value 可配置
   //============================================================
   public void setIsConfig(Boolean value){
      _isConfig = value;
   }

   //============================================================
   // <T>获得虚属性的内容。</T>
   //
   // @return 虚属性
   //============================================================
   public Boolean getIsAbstract(){
      return _isAbstract;
   }

   //============================================================
   // <T>设置虚属性的内容。</T>
   //
   // @param value 虚属性
   //============================================================
   public void setIsAbstract(Boolean value){
      _isAbstract = value;
   }

   //============================================================
   // <T>获得可默认的内容。</T>
   //
   // @return 可默认
   //============================================================
   public String getIsDefault(){
      return _isDefault;
   }

   //============================================================
   // <T>设置可默认的内容。</T>
   //
   // @param value 可默认
   //============================================================
   public void setIsDefault(String value){
      _isDefault = value;
   }

   //============================================================
   // <T>获得默认值的内容。</T>
   //
   // @return 默认值
   //============================================================
   public String getDataDefault(){
      return _dataDefault;
   }

   //============================================================
   // <T>设置默认值的内容。</T>
   //
   // @param value 默认值
   //============================================================
   public void setDataDefault(String value){
      _dataDefault = value;
   }

   //============================================================
   // <T>根据名称获得内容。</T>
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
      }else if(PTY_ATTRIBUTES.equalsIgnoreCase(name)){
         return getAttributes();
      }else if(PTY_TYPE.equalsIgnoreCase(name)){
         return getType();
      }else if(PTY_IS_STORE.equalsIgnoreCase(name)){
         return RBoolean.toString(getIsStore());
      }else if(PTY_IS_CONFIG.equalsIgnoreCase(name)){
         return RBoolean.toString(getIsConfig());
      }else if(PTY_IS_ABSTRACT.equalsIgnoreCase(name)){
         return RBoolean.toString(getIsAbstract());
      }else if(PTY_IS_DEFAULT.equalsIgnoreCase(name)){
         return getIsDefault();
      }else if(PTY_DATA_DEFAULT.equalsIgnoreCase(name)){
         return getDataDefault();
      }
      return null;
   }

   //============================================================
   // <T>根据名称设置内容。</T>
   //
   // @param name 名称
   // @param value 内容
   //============================================================
   public void innerSet(String name,
                        String value){
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
      }else if(PTY_ATTRIBUTES.equalsIgnoreCase(name)){
         setAttributes(value);
      }else if(PTY_TYPE.equalsIgnoreCase(name)){
         setType(value);
      }else if(PTY_IS_STORE.equalsIgnoreCase(name)){
         setIsStore(RBoolean.parse(value));
      }else if(PTY_IS_CONFIG.equalsIgnoreCase(name)){
         setIsConfig(RBoolean.parse(value));
      }else if(PTY_IS_ABSTRACT.equalsIgnoreCase(name)){
         setIsAbstract(RBoolean.parse(value));
      }else if(PTY_IS_DEFAULT.equalsIgnoreCase(name)){
         setIsDefault(value);
      }else if(PTY_DATA_DEFAULT.equalsIgnoreCase(name)){
         setDataDefault(value);
      }
   }

   //============================================================
   // <T>从节点中加载设置信息。</T>
   //
   // @param config 节点
   // @param typeCd 类型
   //============================================================
   public void loadConfig(FXmlNode config,
                          EXmlConfig typeCd){
      super.loadConfig(config, typeCd);
      if(EXmlConfig.Full == typeCd){
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
         if(config.contains("attributes")){
            setAttributes(config.get(PTY_ATTRIBUTES));
         }
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
         }
         if(config.contains("is_store")){
            setIsStore(RBoolean.parse(config.get(PTY_IS_STORE)));
         }
         if(config.contains("is_config")){
            setIsConfig(RBoolean.parse(config.get(PTY_IS_CONFIG)));
         }
         if(config.contains("is_abstract")){
            setIsAbstract(RBoolean.parse(config.get(PTY_IS_ABSTRACT)));
         }
         if(config.contains("is_default")){
            setIsDefault(config.get(PTY_IS_DEFAULT));
         }
         if(config.contains("data_default")){
            setDataDefault(config.get(PTY_DATA_DEFAULT));
         }
      }else if(EXmlConfig.Simple == typeCd){
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
         if(config.contains("attributes")){
            setAttributes(config.get(PTY_ATTRIBUTES));
         }
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
         }
         if(config.contains("is_store")){
            setIsStore(RBoolean.parse(config.get(PTY_IS_STORE)));
         }
         if(config.contains("is_config")){
            setIsConfig(RBoolean.parse(config.get(PTY_IS_CONFIG)));
         }
         if(config.contains("is_abstract")){
            setIsAbstract(RBoolean.parse(config.get(PTY_IS_ABSTRACT)));
         }
         if(config.contains("is_default")){
            setIsDefault(config.get(PTY_IS_DEFAULT));
         }
         if(config.contains("data_default")){
            setDataDefault(config.get(PTY_DATA_DEFAULT));
         }
      }else if(EXmlConfig.Value == typeCd){
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
         if(config.contains("attributes")){
            setAttributes(config.get(PTY_ATTRIBUTES));
         }
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
         }
         if(config.contains("is_store")){
            setIsStore(RBoolean.parse(config.get(PTY_IS_STORE)));
         }
         if(config.contains("is_config")){
            setIsConfig(RBoolean.parse(config.get(PTY_IS_CONFIG)));
         }
         if(config.contains("is_abstract")){
            setIsAbstract(RBoolean.parse(config.get(PTY_IS_ABSTRACT)));
         }
         if(config.contains("is_default")){
            setIsDefault(config.get(PTY_IS_DEFAULT));
         }
         if(config.contains("data_default")){
            setDataDefault(config.get(PTY_DATA_DEFAULT));
         }
      }else if(EXmlConfig.Default == typeCd){
      }
   }

   //============================================================
   // <T>存储设置信息到节点中加载。</T>
   //
   // @param config 节点
   // @param typeCd 类型
   //============================================================
   public void saveConfig(FXmlNode config,
                          EXmlConfig typeCd){
      config.setName(NAME);
      super.saveConfig(config, typeCd);
      if(EXmlConfig.Full == typeCd){
         if(!RString.isEmpty(getName())){
            config.set(PTY_NAME, getName());
         }
         String label = _label.pack().toString();
         if(!RString.isEmpty(label)){
            config.set(PTY_LABEL, label);
         }
         if(RBoolean.parse(getIsValid())){
            config.set(PTY_IS_VALID, RBoolean.toString(getIsValid()));
         }
         String note = _note.pack().toString();
         if(!RString.isEmpty(note)){
            config.set(PTY_NOTE, note);
         }
         if(!RString.isEmpty(getAttributes())){
            config.set(PTY_ATTRIBUTES, getAttributes());
         }
         if(!RString.isEmpty(getType())){
            config.set(PTY_TYPE, getType());
         }
         if(RBoolean.parse(getIsStore())){
            config.set(PTY_IS_STORE, RBoolean.toString(getIsStore()));
         }
         if(RBoolean.parse(getIsConfig())){
            config.set(PTY_IS_CONFIG, RBoolean.toString(getIsConfig()));
         }
         if(RBoolean.parse(getIsAbstract())){
            config.set(PTY_IS_ABSTRACT, RBoolean.toString(getIsAbstract()));
         }
         if(!RString.isEmpty(getIsDefault())){
            config.set(PTY_IS_DEFAULT, getIsDefault());
         }
         if(!RString.isEmpty(getDataDefault())){
            config.set(PTY_DATA_DEFAULT, getDataDefault());
         }
      }else if(EXmlConfig.Simple == typeCd){
         if(!RString.isEmpty(getName())){
            config.set(PTY_NAME, getName());
         }
         if(!RString.isEmpty(getLabel())){
            config.set(PTY_LABEL, getLabel());
         }
         if(RBoolean.parse(getIsValid())){
            config.set(PTY_IS_VALID, RBoolean.toString(getIsValid()));
         }
         if(!RString.isEmpty(getNote())){
            config.set(PTY_NOTE, getNote());
         }
         if(!RString.isEmpty(getAttributes())){
            config.set(PTY_ATTRIBUTES, getAttributes());
         }
         if(!RString.isEmpty(getType())){
            config.set(PTY_TYPE, getType());
         }
         if(RBoolean.parse(getIsStore())){
            config.set(PTY_IS_STORE, RBoolean.toString(getIsStore()));
         }
         if(RBoolean.parse(getIsConfig())){
            config.set(PTY_IS_CONFIG, RBoolean.toString(getIsConfig()));
         }
         if(RBoolean.parse(getIsAbstract())){
            config.set(PTY_IS_ABSTRACT, RBoolean.toString(getIsAbstract()));
         }
         if(!RString.isEmpty(getIsDefault())){
            config.set(PTY_IS_DEFAULT, getIsDefault());
         }
         if(!RString.isEmpty(getDataDefault())){
            config.set(PTY_DATA_DEFAULT, getDataDefault());
         }
      }else if(EXmlConfig.Value == typeCd){
         String sName = getName();
         if(!RString.isEmpty(sName)){
            config.set(PTY_NAME, sName);
         }
         String sLabel = getLabel();
         if(!RString.isEmpty(sLabel)){
            config.set(PTY_LABEL, sLabel);
         }
         Boolean bIsValid = getIsValid();
         if(RBoolean.parse(bIsValid)){
            config.set(PTY_IS_VALID, RBoolean.toString(bIsValid));
         }
         String sNote = getNote();
         if(!RString.isEmpty(sNote)){
            config.set(PTY_NOTE, sNote);
         }
         String sAttributes = getAttributes();
         if(!RString.isEmpty(sAttributes)){
            config.set(PTY_ATTRIBUTES, sAttributes);
         }
         String sType = getType();
         if(!RString.isEmpty(sType)){
            config.set(PTY_TYPE, sType);
         }
         Boolean bIsStore = getIsStore();
         if(RBoolean.parse(bIsStore)){
            config.set(PTY_IS_STORE, RBoolean.toString(bIsStore));
         }
         Boolean bIsConfig = getIsConfig();
         if(RBoolean.parse(bIsConfig)){
            config.set(PTY_IS_CONFIG, RBoolean.toString(bIsConfig));
         }
         Boolean bIsAbstract = getIsAbstract();
         if(RBoolean.parse(bIsAbstract)){
            config.set(PTY_IS_ABSTRACT, RBoolean.toString(bIsAbstract));
         }
         String sIsDefault = getIsDefault();
         if(!RString.isEmpty(sIsDefault)){
            config.set(PTY_IS_DEFAULT, sIsDefault);
         }
         String sDataDefault = getDataDefault();
         if(!RString.isEmpty(sDataDefault)){
            config.set(PTY_DATA_DEFAULT, sDataDefault);
         }
      }else if(EXmlConfig.Default == typeCd){
      }
   }
}
