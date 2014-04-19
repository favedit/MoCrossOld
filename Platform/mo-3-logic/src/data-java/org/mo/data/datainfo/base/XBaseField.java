package org.mo.data.datainfo.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.data.datainfo.common.XObject;

//============================================================
// <T>对象的XML节点基类。</T>
//============================================================
public abstract class XBaseField
      extends FXmlObject
      implements
         XObject
{
   // 名称定义
   public static final String NAME = "Field";

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

   // 的名称定义
   public static final String PTY_NAME = "name";

   // 的名称定义
   public static final String PTY_LABEL = "label";

   // 的名称定义
   public static final String PTY_NOTE = "note";

   // 的名称定义
   public static final String PTY_DATA_NAME = "data_name";

   // 的名称定义
   public static final String PTY_TYPE = "type";

   // 的名称定义
   public static final String PTY_IS_KEY = "is_key";

   // 的名称定义
   public static final String PTY_IS_NULL = "is_null";

   // 的名称定义
   public static final String PTY_SIZE = "size";

   // 的定义
   @AName("name")
   protected String _name;

   // 的定义
   @AName("label")
   protected String _label;

   // 的定义
   @AName("note")
   protected FMultiString _note = new FMultiString();

   // 的定义
   @AName("data_name")
   protected String _dataName;

   // 的定义
   @AName("type")
   protected String _type;

   // 的定义
   @AName("is_key")
   protected Boolean _isKey = Boolean.FALSE;

   // 的定义
   @AName("is_null")
   protected Boolean _isNull = Boolean.FALSE;

   // 的定义
   @AName("size")
   protected String _size;

   //============================================================
   // <T>获得的内容。</T>
   //
   // @return 
   //============================================================
   public String getName(){
      return _name;
   }

   //============================================================
   // <T>设置的内容。</T>
   //
   // @param value 
   //============================================================
   public void setName(String value){
      _name = value;
   }

   //============================================================
   // <T>获得的内容。</T>
   //
   // @return 
   //============================================================
   public String getLabel(){
      return _label;
   }

   //============================================================
   // <T>设置的内容。</T>
   //
   // @param value 
   //============================================================
   public void setLabel(String value){
      _label = value;
   }

   //============================================================
   // <T>获得的内容。</T>
   //
   // @return 
   //============================================================
   public String getNote(){
      return _note.get();
   }

   //============================================================
   // <T>设置的内容。</T>
   //
   // @param value 
   //============================================================
   public void setNote(String value){
      _note.set(value);
   }

   //============================================================
   // <T>获得的内容。</T>
   //
   // @return 
   //============================================================
   public String getDataName(){
      return _dataName;
   }

   //============================================================
   // <T>设置的内容。</T>
   //
   // @param value 
   //============================================================
   public void setDataName(String value){
      _dataName = value;
   }

   //============================================================
   // <T>获得的内容。</T>
   //
   // @return 
   //============================================================
   public String getType(){
      return _type;
   }

   //============================================================
   // <T>设置的内容。</T>
   //
   // @param value 
   //============================================================
   public void setType(String value){
      _type = value;
   }

   //============================================================
   // <T>获得的内容。</T>
   //
   // @return 
   //============================================================
   public Boolean getIsKey(){
      return _isKey;
   }

   //============================================================
   // <T>设置的内容。</T>
   //
   // @param value 
   //============================================================
   public void setIsKey(Boolean value){
      _isKey = value;
   }

   //============================================================
   // <T>获得的内容。</T>
   //
   // @return 
   //============================================================
   public Boolean getIsNull(){
      return _isNull;
   }

   //============================================================
   // <T>设置的内容。</T>
   //
   // @param value 
   //============================================================
   public void setIsNull(Boolean value){
      _isNull = value;
   }

   //============================================================
   // <T>获得的内容。</T>
   //
   // @return 
   //============================================================
   public String getSize(){
      return _size;
   }

   //============================================================
   // <T>设置的内容。</T>
   //
   // @param value 
   //============================================================
   public void setSize(String value){
      _size = value;
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
      }else if(PTY_DATA_NAME.equalsIgnoreCase(name)){
         return getDataName();
      }else if(PTY_TYPE.equalsIgnoreCase(name)){
         return getType();
      }else if(PTY_IS_KEY.equalsIgnoreCase(name)){
         return RBoolean.toString(getIsKey());
      }else if(PTY_IS_NULL.equalsIgnoreCase(name)){
         return RBoolean.toString(getIsNull());
      }else if(PTY_SIZE.equalsIgnoreCase(name)){
         return getSize();
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
      }else if(PTY_DATA_NAME.equalsIgnoreCase(name)){
         setDataName(value);
      }else if(PTY_TYPE.equalsIgnoreCase(name)){
         setType(value);
      }else if(PTY_IS_KEY.equalsIgnoreCase(name)){
         setIsKey(RBoolean.parse(value));
      }else if(PTY_IS_NULL.equalsIgnoreCase(name)){
         setIsNull(RBoolean.parse(value));
      }else if(PTY_SIZE.equalsIgnoreCase(name)){
         setSize(value);
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
         if(config.contains("note")){
            _note.unpack(config.get(PTY_NOTE));
         }
         if(config.contains("data_name")){
            setDataName(config.get(PTY_DATA_NAME));
         }
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
         }
         if(config.contains("is_key")){
            setIsKey(RBoolean.parse(config.get(PTY_IS_KEY)));
         }
         if(config.contains("is_null")){
            setIsNull(RBoolean.parse(config.get(PTY_IS_NULL)));
         }
         if(config.contains("size")){
            setSize(config.get(PTY_SIZE));
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
         if(config.contains("data_name")){
            setDataName(config.get(PTY_DATA_NAME));
         }
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
         }
         if(config.contains("is_key")){
            setIsKey(RBoolean.parse(config.get(PTY_IS_KEY)));
         }
         if(config.contains("is_null")){
            setIsNull(RBoolean.parse(config.get(PTY_IS_NULL)));
         }
         if(config.contains("size")){
            setSize(config.get(PTY_SIZE));
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
         if(config.contains("data_name")){
            setDataName(config.get(PTY_DATA_NAME));
         }
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
         }
         if(config.contains("is_key")){
            setIsKey(RBoolean.parse(config.get(PTY_IS_KEY)));
         }
         if(config.contains("is_null")){
            setIsNull(RBoolean.parse(config.get(PTY_IS_NULL)));
         }
         if(config.contains("size")){
            setSize(config.get(PTY_SIZE));
         }
      }else if(EXmlConfig.Default == type){
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
         String note = _note.pack().toString();
         if(RString.isNotEmpty(note)){
            config.set(PTY_NOTE, note);
         }
         if(RString.isNotEmpty(getDataName())){
            config.set(PTY_DATA_NAME, getDataName());
         }
         if(RString.isNotEmpty(getType())){
            config.set(PTY_TYPE, getType());
         }
         if(RBoolean.parse(getIsKey())){
            config.set(PTY_IS_KEY, RBoolean.toString(getIsKey()));
         }
         if(RBoolean.parse(getIsNull())){
            config.set(PTY_IS_NULL, RBoolean.toString(getIsNull()));
         }
         if(RString.isNotEmpty(getSize())){
            config.set(PTY_SIZE, getSize());
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
         if(RString.isNotEmpty(getDataName())){
            config.set(PTY_DATA_NAME, getDataName());
         }
         if(RString.isNotEmpty(getType())){
            config.set(PTY_TYPE, getType());
         }
         if(RBoolean.parse(getIsKey())){
            config.set(PTY_IS_KEY, RBoolean.toString(getIsKey()));
         }
         if(RBoolean.parse(getIsNull())){
            config.set(PTY_IS_NULL, RBoolean.toString(getIsNull()));
         }
         if(RString.isNotEmpty(getSize())){
            config.set(PTY_SIZE, getSize());
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
         String sDataName = getDataName();
         if(RString.isNotEmpty(sDataName)){
            config.set(PTY_DATA_NAME, sDataName);
         }
         String sType = getType();
         if(RString.isNotEmpty(sType)){
            config.set(PTY_TYPE, sType);
         }
         Boolean bIsKey = getIsKey();
         if(RBoolean.parse(bIsKey)){
            config.set(PTY_IS_KEY, RBoolean.toString(bIsKey));
         }
         Boolean bIsNull = getIsNull();
         if(RBoolean.parse(bIsNull)){
            config.set(PTY_IS_NULL, RBoolean.toString(bIsNull));
         }
         String sSize = getSize();
         if(RString.isNotEmpty(sSize)){
            config.set(PTY_SIZE, sSize);
         }
      }else if(EXmlConfig.Default == type){
      }
   }
}