/*
 * @(#)XBaseItem.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.game.editor.core.storage.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.game.editor.core.storage.common.XObjectFace;

/**
 * <p>数据项对象的XML节点基类</p>
 *
 * @author system
 */
public abstract class XBaseItem
      extends FXmlObject
      implements
         XObjectFace{

   private static final long serialVersionUID = 1L;

   public static final String NAME = "Item";

   public static boolean isName(String name){
      return NAME.equals(name);
   }

   public static boolean isInstance(IXmlObject xobject){
      return NAME.equals(xobject.name());
   }

   /**
    * 名称的名称定义
    */
   public static final String PTY_NAME = "name";

   /**
    * 标签的名称定义
    */
   public static final String PTY_LABEL = "label";

   /**
    * 有效性的名称定义
    */
   public static final String PTY_IS_VALID = "is_valid";

   /**
    * 注释的名称定义
    */
   public static final String PTY_NOTE = "note";

   /**
    * 唯一编号的名称定义
    */
   public static final String PTY_UNIQUE_ID = "unique_id";

   /**
    * 类型的名称定义
    */
   public static final String PTY_TYPE = "type";

   /**
    * 内容的名称定义
    */
   public static final String PTY_VALUE = "value";

   /**
    * 类型的名称定义
    */
   public static final String PTY_TYPE_CD = "type_cd";

   /**
    * 类型名称的名称定义
    */
   public static final String PTY_TYPE_NAME = "type_name";

   /**
    * 集合的名称定义
    */
   public static final String PTY_IS_COLLECTION = "is_collection";

   /**
    * 数据大小的名称定义
    */
   public static final String PTY_DATA_SIZE = "data_size";


   public String name(){
      return NAME;
   }

   /**
    * 名称的定义
    */
   @AName("name")
   protected String _name;

   /**
    * 标签的定义
    */
   @AName("label")
   protected String _label;

   /**
    * 有效性的定义
    */
   @AName("is_valid")
   protected Boolean _isValid = Boolean.FALSE;

   /**
    * 注释的定义
    */
   @AName("note")
   protected FMultiString _note = new FMultiString();

   /**
    * 唯一编号的定义
    */
   @AName("unique_id")
   protected String _uniqueId;

   /**
    * 类型的定义
    */
   @AName("type")
   protected String _type;

   /**
    * 内容的定义
    */
   @AName("value")
   protected String _value;

   /**
    * 类型的定义
    */
   @AName("type_cd")
   protected String _typeCd;

   /**
    * 类型名称的定义
    */
   @AName("type_name")
   protected String _typeName;

   /**
    * 集合的定义
    */
   @AName("is_collection")
   protected String _isCollection;

   /**
    * 数据大小的定义
    */
   @AName("data_size")
   protected String _dataSize;


   /**
    * 获得名称的内容。
    *
    * @return 名称
    */
   public String getName(){
      return _name;
   }

   /**
    * 设置名称的内容。
    *
    * @param value 名称
    */
   public void setName(String value){
      _name = value;
   }

   /**
    * 获得标签的内容。
    *
    * @return 标签
    */
   public String getLabel(){
      return _label;
   }

   /**
    * 设置标签的内容。
    *
    * @param value 标签
    */
   public void setLabel(String value){
      _label = value;
   }

   /**
    * 获得有效性的内容。
    *
    * @return 有效性
    */
   public Boolean getIsValid(){
      return _isValid;
   }

   /**
    * 设置有效性的内容。
    *
    * @param value 有效性
    */
   public void setIsValid(Boolean value){
      _isValid = value;
   }

   /**
    * 获得注释的内容。
    *
    * @return 注释
    */
   public String getNote(){
      return _note.get();
   }

   /**
    * 设置注释的内容。
    *
    * @param value 注释
    */
   public void setNote(String value){
      _note.set(value);
   }

   /**
    * 获得唯一编号的内容。
    *
    * @return 唯一编号
    */
   public String getUniqueId(){
      return _uniqueId;
   }

   /**
    * 设置唯一编号的内容。
    *
    * @param value 唯一编号
    */
   public void setUniqueId(String value){
      _uniqueId = value;
   }

   /**
    * 获得类型的内容。
    *
    * @return 类型
    */
   public String getType(){
      return _type;
   }

   /**
    * 设置类型的内容。
    *
    * @param value 类型
    */
   public void setType(String value){
      _type = value;
   }

   /**
    * 获得内容的内容。
    *
    * @return 内容
    */
   public String getValue(){
      return _value;
   }

   /**
    * 设置内容的内容。
    *
    * @param value 内容
    */
   public void setValue(String value){
      _value = value;
   }

   /**
    * 获得类型的内容。
    *
    * @return 类型
    */
   public String getTypeCd(){
      return _typeCd;
   }

   /**
    * 设置类型的内容。
    *
    * @param value 类型
    */
   public void setTypeCd(String value){
      _typeCd = value;
   }

   /**
    * 获得类型名称的内容。
    *
    * @return 类型名称
    */
   public String getTypeName(){
      return _typeName;
   }

   /**
    * 设置类型名称的内容。
    *
    * @param value 类型名称
    */
   public void setTypeName(String value){
      _typeName = value;
   }

   /**
    * 获得集合的内容。
    *
    * @return 集合
    */
   public String getIsCollection(){
      return _isCollection;
   }

   /**
    * 设置集合的内容。
    *
    * @param value 集合
    */
   public void setIsCollection(String value){
      _isCollection = value;
   }

   /**
    * 获得数据大小的内容。
    *
    * @return 数据大小
    */
   public String getDataSize(){
      return _dataSize;
   }

   /**
    * 设置数据大小的内容。
    *
    * @param value 数据大小
    */
   public void setDataSize(String value){
      _dataSize = value;
   }

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
         if(config.contains("unique_id")){
            setUniqueId(config.get(PTY_UNIQUE_ID));
         }
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
         }
         if(config.contains("value")){
            setValue(config.get(PTY_VALUE));
         }
         if(config.contains("type_cd")){
            setTypeCd(config.get(PTY_TYPE_CD));
         }
         if(config.contains("type_name")){
            setTypeName(config.get(PTY_TYPE_NAME));
         }
         if(config.contains("is_collection")){
            setIsCollection(config.get(PTY_IS_COLLECTION));
         }
         if(config.contains("data_size")){
            setDataSize(config.get(PTY_DATA_SIZE));
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
         if(config.contains("unique_id")){
            setUniqueId(config.get(PTY_UNIQUE_ID));
         }
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
         }
         if(config.contains("value")){
            setValue(config.get(PTY_VALUE));
         }
         if(config.contains("type_cd")){
            setTypeCd(config.get(PTY_TYPE_CD));
         }
         if(config.contains("type_name")){
            setTypeName(config.get(PTY_TYPE_NAME));
         }
         if(config.contains("is_collection")){
            setIsCollection(config.get(PTY_IS_COLLECTION));
         }
         if(config.contains("data_size")){
            setDataSize(config.get(PTY_DATA_SIZE));
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
         if(config.contains("unique_id")){
            setUniqueId(config.get(PTY_UNIQUE_ID));
         }
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
         }
         if(config.contains("value")){
            setValue(config.get(PTY_VALUE));
         }
         if(config.contains("type_cd")){
            setTypeCd(config.get(PTY_TYPE_CD));
         }
         if(config.contains("type_name")){
            setTypeName(config.get(PTY_TYPE_NAME));
         }
         if(config.contains("is_collection")){
            setIsCollection(config.get(PTY_IS_COLLECTION));
         }
         if(config.contains("data_size")){
            setDataSize(config.get(PTY_DATA_SIZE));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getUniqueId())){
            if(config.contains("unique_id")){
               setUniqueId(config.get(PTY_UNIQUE_ID));
            }
         }
         if(RString.isEmpty(getTypeCd())){
            if(config.contains("type_cd")){
               setTypeCd(config.get(PTY_TYPE_CD));
            }
         }
         if(RString.isEmpty(getTypeName())){
            if(config.contains("type_name")){
               setTypeName(config.get(PTY_TYPE_NAME));
            }
         }
         if(RString.isEmpty(getIsCollection())){
            if(config.contains("is_collection")){
               setIsCollection(config.get(PTY_IS_COLLECTION));
            }
         }
         if(RString.isEmpty(getDataSize())){
            if(config.contains("data_size")){
               setDataSize(config.get(PTY_DATA_SIZE));
            }
         }
      }
   }

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
         if(RString.isNotEmpty(getUniqueId())){
            config.set(PTY_UNIQUE_ID, getUniqueId());
         }
         if(RString.isNotEmpty(getType())){
            config.set(PTY_TYPE, getType());
         }
         if(RString.isNotEmpty(getValue())){
            config.set(PTY_VALUE, getValue());
         }
         if(RString.isNotEmpty(getTypeCd())){
            config.set(PTY_TYPE_CD, getTypeCd());
         }
         if(RString.isNotEmpty(getTypeName())){
            config.set(PTY_TYPE_NAME, getTypeName());
         }
         if(RString.isNotEmpty(getIsCollection())){
            config.set(PTY_IS_COLLECTION, getIsCollection());
         }
         if(RString.isNotEmpty(getDataSize())){
            config.set(PTY_DATA_SIZE, getDataSize());
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
         if(RString.isNotEmpty(getUniqueId())){
            config.set(PTY_UNIQUE_ID, getUniqueId());
         }
         if(RString.isNotEmpty(getType())){
            config.set(PTY_TYPE, getType());
         }
         if(RString.isNotEmpty(getValue())){
            config.set(PTY_VALUE, getValue());
         }
         if(RString.isNotEmpty(getTypeCd())){
            config.set(PTY_TYPE_CD, getTypeCd());
         }
         if(RString.isNotEmpty(getTypeName())){
            config.set(PTY_TYPE_NAME, getTypeName());
         }
         if(RString.isNotEmpty(getIsCollection())){
            config.set(PTY_IS_COLLECTION, getIsCollection());
         }
         if(RString.isNotEmpty(getDataSize())){
            config.set(PTY_DATA_SIZE, getDataSize());
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
         String sUniqueId = getUniqueId();
         if(RString.isNotEmpty(sUniqueId)){
            config.set(PTY_UNIQUE_ID, sUniqueId);
         }
         String sType = getType();
         if(RString.isNotEmpty(sType)){
            config.set(PTY_TYPE, sType);
         }
         String sValue = getValue();
         if(RString.isNotEmpty(sValue)){
            config.set(PTY_VALUE, sValue);
         }
         String sTypeCd = getTypeCd();
         if(RString.isNotEmpty(sTypeCd)){
            config.set(PTY_TYPE_CD, sTypeCd);
         }
         String sTypeName = getTypeName();
         if(RString.isNotEmpty(sTypeName)){
            config.set(PTY_TYPE_NAME, sTypeName);
         }
         String sIsCollection = getIsCollection();
         if(RString.isNotEmpty(sIsCollection)){
            config.set(PTY_IS_COLLECTION, sIsCollection);
         }
         String sDataSize = getDataSize();
         if(RString.isNotEmpty(sDataSize)){
            config.set(PTY_DATA_SIZE, sDataSize);
         }
      }else if(EXmlConfig.Default == type){
         String sUniqueId = getUniqueId();
         if(RString.isNotEmpty(sUniqueId)){
            config.set(PTY_UNIQUE_ID, sUniqueId);
         }
         String sTypeCd = getTypeCd();
         if(RString.isNotEmpty(sTypeCd)){
            config.set(PTY_TYPE_CD, sTypeCd);
         }
         String sTypeName = getTypeName();
         if(RString.isNotEmpty(sTypeName)){
            config.set(PTY_TYPE_NAME, sTypeName);
         }
         String sIsCollection = getIsCollection();
         if(RString.isNotEmpty(sIsCollection)){
            config.set(PTY_IS_COLLECTION, sIsCollection);
         }
         String sDataSize = getDataSize();
         if(RString.isNotEmpty(sDataSize)){
            config.set(PTY_DATA_SIZE, sDataSize);
         }
      }
   }

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
      }else if(PTY_UNIQUE_ID.equalsIgnoreCase(name)){
         return getUniqueId();
      }else if(PTY_TYPE.equalsIgnoreCase(name)){
         return getType();
      }else if(PTY_VALUE.equalsIgnoreCase(name)){
         return getValue();
      }else if(PTY_TYPE_CD.equalsIgnoreCase(name)){
         return getTypeCd();
      }else if(PTY_TYPE_NAME.equalsIgnoreCase(name)){
         return getTypeName();
      }else if(PTY_IS_COLLECTION.equalsIgnoreCase(name)){
         return getIsCollection();
      }else if(PTY_DATA_SIZE.equalsIgnoreCase(name)){
         return getDataSize();
      }
      return null;
   }

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
      }else if(PTY_UNIQUE_ID.equalsIgnoreCase(name)){
         setUniqueId(value);
      }else if(PTY_TYPE.equalsIgnoreCase(name)){
         setType(value);
      }else if(PTY_VALUE.equalsIgnoreCase(name)){
         setValue(value);
      }else if(PTY_TYPE_CD.equalsIgnoreCase(name)){
         setTypeCd(value);
      }else if(PTY_TYPE_NAME.equalsIgnoreCase(name)){
         setTypeName(value);
      }else if(PTY_IS_COLLECTION.equalsIgnoreCase(name)){
         setIsCollection(value);
      }else if(PTY_DATA_SIZE.equalsIgnoreCase(name)){
         setDataSize(value);
      }
   }

}