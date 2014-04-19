package org.mo.logic.event.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.logic.event.common.XObjectFace;

//============================================================
// <T>属性对象的XML节点基类。</T>
//============================================================
public abstract class XBaseProperty
      extends FXmlObject
      implements
         XObjectFace
{
   // 名称定义
   public static final String NAME = "Property";

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

   // 注释的名称定义
   public static final String PTY_NOTE = "note";

   // 类型的名称定义
   public static final String PTY__TYPE = "_type";

   // 有效性的名称定义
   public static final String PTY_IS_VALID = "is_valid";

   // 数据类型的名称定义
   public static final String PTY_DATA_TYPE_CD = "data_type_cd";

   // 默认值的名称定义
   public static final String PTY_DATA_DEFAULT = "data_default";

   // 数据必须的名称定义
   public static final String PTY_DATA_REQUIRE = "data_require";

   // 名称的定义
   @AName("name")
   protected String _name;

   // 标签的定义
   @AName("label")
   protected FMultiString _label = new FMultiString();

   // 注释的定义
   @AName("note")
   protected FMultiString _note = new FMultiString();

   // 类型的定义
   @AName("_type")
   protected String __type;

   // 有效性的定义
   @AName("is_valid")
   protected String _isValid;

   // 数据类型的定义
   @AName("data_type_cd")
   protected String _dataTypeCd;

   // 默认值的定义
   @AName("data_default")
   protected String _dataDefault;

   // 数据必须的定义
   @AName("data_require")
   protected String _dataRequire;

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
   public String get_type(){
      return __type;
   }

   //============================================================
   // <T>设置类型的内容。</T>
   //
   // @param value 类型
   //============================================================
   public void set_type(String value){
      __type = value;
   }

   //============================================================
   // <T>获得有效性的内容。</T>
   //
   // @return 有效性
   //============================================================
   public String getIsValid(){
      return _isValid;
   }

   //============================================================
   // <T>设置有效性的内容。</T>
   //
   // @param value 有效性
   //============================================================
   public void setIsValid(String value){
      _isValid = value;
   }

   //============================================================
   // <T>获得数据类型的内容。</T>
   //
   // @return 数据类型
   //============================================================
   public String getDataTypeCd(){
      return _dataTypeCd;
   }

   //============================================================
   // <T>设置数据类型的内容。</T>
   //
   // @param value 数据类型
   //============================================================
   public void setDataTypeCd(String value){
      _dataTypeCd = value;
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
   // <T>获得数据必须的内容。</T>
   //
   // @return 数据必须
   //============================================================
   public String getDataRequire(){
      return _dataRequire;
   }

   //============================================================
   // <T>设置数据必须的内容。</T>
   //
   // @param value 数据必须
   //============================================================
   public void setDataRequire(String value){
      _dataRequire = value;
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
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         return getNote();
      }else if(PTY__TYPE.equalsIgnoreCase(name)){
         return get_type();
      }else if(PTY_IS_VALID.equalsIgnoreCase(name)){
         return getIsValid();
      }else if(PTY_DATA_TYPE_CD.equalsIgnoreCase(name)){
         return getDataTypeCd();
      }else if(PTY_DATA_DEFAULT.equalsIgnoreCase(name)){
         return getDataDefault();
      }else if(PTY_DATA_REQUIRE.equalsIgnoreCase(name)){
         return getDataRequire();
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
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         setNote(value);
      }else if(PTY__TYPE.equalsIgnoreCase(name)){
         set_type(value);
      }else if(PTY_IS_VALID.equalsIgnoreCase(name)){
         setIsValid(value);
      }else if(PTY_DATA_TYPE_CD.equalsIgnoreCase(name)){
         setDataTypeCd(value);
      }else if(PTY_DATA_DEFAULT.equalsIgnoreCase(name)){
         setDataDefault(value);
      }else if(PTY_DATA_REQUIRE.equalsIgnoreCase(name)){
         setDataRequire(value);
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
            _label.unpack(config.get(PTY_LABEL));
         }
         if(config.contains("note")){
            _note.unpack(config.get(PTY_NOTE));
         }
         if(config.contains("_type")){
            set_type(config.get(PTY__TYPE));
         }
         if(config.contains("is_valid")){
            setIsValid(config.get(PTY_IS_VALID));
         }
         if(config.contains("data_type_cd")){
            setDataTypeCd(config.get(PTY_DATA_TYPE_CD));
         }
         if(config.contains("data_default")){
            setDataDefault(config.get(PTY_DATA_DEFAULT));
         }
         if(config.contains("data_require")){
            setDataRequire(config.get(PTY_DATA_REQUIRE));
         }
      }else if(EXmlConfig.Simple == type){
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("label")){
            setLabel(config.get(PTY_LABEL));
         }
         if(config.contains("note")){
            setNote(config.get(PTY_NOTE));
         }
         if(config.contains("_type")){
            set_type(config.get(PTY__TYPE));
         }
         if(config.contains("is_valid")){
            setIsValid(config.get(PTY_IS_VALID));
         }
         if(config.contains("data_type_cd")){
            setDataTypeCd(config.get(PTY_DATA_TYPE_CD));
         }
         if(config.contains("data_default")){
            setDataDefault(config.get(PTY_DATA_DEFAULT));
         }
         if(config.contains("data_require")){
            setDataRequire(config.get(PTY_DATA_REQUIRE));
         }
      }else if(EXmlConfig.Value == type){
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("label")){
            setLabel(config.get(PTY_LABEL));
         }
         if(config.contains("note")){
            setNote(config.get(PTY_NOTE));
         }
         if(config.contains("_type")){
            set_type(config.get(PTY__TYPE));
         }
         if(config.contains("is_valid")){
            setIsValid(config.get(PTY_IS_VALID));
         }
         if(config.contains("data_type_cd")){
            setDataTypeCd(config.get(PTY_DATA_TYPE_CD));
         }
         if(config.contains("data_default")){
            setDataDefault(config.get(PTY_DATA_DEFAULT));
         }
         if(config.contains("data_require")){
            setDataRequire(config.get(PTY_DATA_REQUIRE));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(get_type())){
            if(config.contains("_type")){
               set_type(config.get(PTY__TYPE));
            }
         }
         if(RString.isEmpty(getIsValid())){
            if(config.contains("is_valid")){
               setIsValid(config.get(PTY_IS_VALID));
            }
         }
         if(RString.isEmpty(getDataTypeCd())){
            if(config.contains("data_type_cd")){
               setDataTypeCd(config.get(PTY_DATA_TYPE_CD));
            }
         }
         if(RString.isEmpty(getDataDefault())){
            if(config.contains("data_default")){
               setDataDefault(config.get(PTY_DATA_DEFAULT));
            }
         }
         if(RString.isEmpty(getDataRequire())){
            if(config.contains("data_require")){
               setDataRequire(config.get(PTY_DATA_REQUIRE));
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
         String label = _label.pack().toString();
         if(RString.isNotEmpty(label)){
            config.set(PTY_LABEL, label);
         }
         String note = _note.pack().toString();
         if(RString.isNotEmpty(note)){
            config.set(PTY_NOTE, note);
         }
         if(RString.isNotEmpty(get_type())){
            config.set(PTY__TYPE, get_type());
         }
         if(RString.isNotEmpty(getIsValid())){
            config.set(PTY_IS_VALID, getIsValid());
         }
         if(RString.isNotEmpty(getDataTypeCd())){
            config.set(PTY_DATA_TYPE_CD, getDataTypeCd());
         }
         if(RString.isNotEmpty(getDataDefault())){
            config.set(PTY_DATA_DEFAULT, getDataDefault());
         }
         if(RString.isNotEmpty(getDataRequire())){
            config.set(PTY_DATA_REQUIRE, getDataRequire());
         }
      }else if(EXmlConfig.Simple == type){
         if(RString.isNotEmpty(getName())){
            config.set(PTY_NAME, getName());
         }
         if(RString.isNotEmpty(getLabel())){
            config.set(PTY_LABEL, getLabel());
         }
         if(RString.isNotEmpty(getNote())){
            config.set(PTY_NOTE, getNote());
         }
         if(RString.isNotEmpty(get_type())){
            config.set(PTY__TYPE, get_type());
         }
         if(RString.isNotEmpty(getIsValid())){
            config.set(PTY_IS_VALID, getIsValid());
         }
         if(RString.isNotEmpty(getDataTypeCd())){
            config.set(PTY_DATA_TYPE_CD, getDataTypeCd());
         }
         if(RString.isNotEmpty(getDataDefault())){
            config.set(PTY_DATA_DEFAULT, getDataDefault());
         }
         if(RString.isNotEmpty(getDataRequire())){
            config.set(PTY_DATA_REQUIRE, getDataRequire());
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
         String sNote = getNote();
         if(RString.isNotEmpty(sNote)){
            config.set(PTY_NOTE, sNote);
         }
         String s_type = get_type();
         if(RString.isNotEmpty(s_type)){
            config.set(PTY__TYPE, s_type);
         }
         String sIsValid = getIsValid();
         if(RString.isNotEmpty(sIsValid)){
            config.set(PTY_IS_VALID, sIsValid);
         }
         String sDataTypeCd = getDataTypeCd();
         if(RString.isNotEmpty(sDataTypeCd)){
            config.set(PTY_DATA_TYPE_CD, sDataTypeCd);
         }
         String sDataDefault = getDataDefault();
         if(RString.isNotEmpty(sDataDefault)){
            config.set(PTY_DATA_DEFAULT, sDataDefault);
         }
         String sDataRequire = getDataRequire();
         if(RString.isNotEmpty(sDataRequire)){
            config.set(PTY_DATA_REQUIRE, sDataRequire);
         }
      }else if(EXmlConfig.Default == type){
         String s_type = get_type();
         if(RString.isNotEmpty(s_type)){
            config.set(PTY__TYPE, s_type);
         }
         String sIsValid = getIsValid();
         if(RString.isNotEmpty(sIsValid)){
            config.set(PTY_IS_VALID, sIsValid);
         }
         String sDataTypeCd = getDataTypeCd();
         if(RString.isNotEmpty(sDataTypeCd)){
            config.set(PTY_DATA_TYPE_CD, sDataTypeCd);
         }
         String sDataDefault = getDataDefault();
         if(RString.isNotEmpty(sDataDefault)){
            config.set(PTY_DATA_DEFAULT, sDataDefault);
         }
         String sDataRequire = getDataRequire();
         if(RString.isNotEmpty(sDataRequire)){
            config.set(PTY_DATA_REQUIRE, sDataRequire);
         }
      }
   }
}