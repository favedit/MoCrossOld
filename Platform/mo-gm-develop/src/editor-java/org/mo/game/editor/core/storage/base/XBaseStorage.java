/*
 * @(#)XBaseStorage.java
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
 * <p>实体对象的XML节点基类</p>
 *
 * @author system
 */
public abstract class XBaseStorage
      extends FXmlObject
      implements
         XObjectFace{

   private static final long serialVersionUID = 1L;

   public static final String NAME = "Storage";

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
    * 可缓冲的名称定义
    */
   public static final String PTY_CACHE = "cache";

   /**
    * 代码号的名称定义
    */
   public static final String PTY_CODE = "code";

   /**
    * 衍生类的名称定义
    */
   public static final String PTY_IS_CLASS = "is_class";

   /**
    * 源名称的名称定义
    */
   public static final String PTY_SOURCE_NAME = "source_name";

   /**
    * 父名称的名称定义
    */
   public static final String PTY_PARENT_NAME = "parent_name";

   /**
    * 实体类型的名称定义
    */
   public static final String PTY_STORAGE_TYPE = "storage_type";

   /**
    * 代码类型的名称定义
    */
   public static final String PTY_SOURCE_TYPE = "source_type";

   /**
    * 目标类型的名称定义
    */
   public static final String PTY_TARGET_TYPE = "target_type";

   /**
    * 代码值的名称定义
    */
   public static final String PTY_CODE_VALUE = "code_value";


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
    * 可缓冲的定义
    */
   @AName("cache")
   protected Boolean _cache = Boolean.FALSE;

   /**
    * 代码号的定义
    */
   @AName("code")
   protected String _code;

   /**
    * 衍生类的定义
    */
   @AName("is_class")
   protected String _isClass;

   /**
    * 源名称的定义
    */
   @AName("source_name")
   protected String _sourceName;

   /**
    * 父名称的定义
    */
   @AName("parent_name")
   protected String _parentName;

   /**
    * 实体类型的定义
    */
   @AName("storage_type")
   protected String _storageType;

   /**
    * 代码类型的定义
    */
   @AName("source_type")
   protected String _sourceType;

   /**
    * 目标类型的定义
    */
   @AName("target_type")
   protected String _targetType;

   /**
    * 代码值的定义
    */
   @AName("code_value")
   protected String _codeValue;


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
    * 获得可缓冲的内容。
    *
    * @return 可缓冲
    */
   public Boolean getCache(){
      return _cache;
   }

   /**
    * 设置可缓冲的内容。
    *
    * @param value 可缓冲
    */
   public void setCache(Boolean value){
      _cache = value;
   }

   /**
    * 获得代码号的内容。
    *
    * @return 代码号
    */
   public String getCode(){
      return _code;
   }

   /**
    * 设置代码号的内容。
    *
    * @param value 代码号
    */
   public void setCode(String value){
      _code = value;
   }

   /**
    * 获得衍生类的内容。
    *
    * @return 衍生类
    */
   public String getIsClass(){
      return _isClass;
   }

   /**
    * 设置衍生类的内容。
    *
    * @param value 衍生类
    */
   public void setIsClass(String value){
      _isClass = value;
   }

   /**
    * 获得源名称的内容。
    *
    * @return 源名称
    */
   public String getSourceName(){
      return _sourceName;
   }

   /**
    * 设置源名称的内容。
    *
    * @param value 源名称
    */
   public void setSourceName(String value){
      _sourceName = value;
   }

   /**
    * 获得父名称的内容。
    *
    * @return 父名称
    */
   public String getParentName(){
      return _parentName;
   }

   /**
    * 设置父名称的内容。
    *
    * @param value 父名称
    */
   public void setParentName(String value){
      _parentName = value;
   }

   /**
    * 获得实体类型的内容。
    *
    * @return 实体类型
    */
   public String getStorageType(){
      return _storageType;
   }

   /**
    * 设置实体类型的内容。
    *
    * @param value 实体类型
    */
   public void setStorageType(String value){
      _storageType = value;
   }

   /**
    * 获得代码类型的内容。
    *
    * @return 代码类型
    */
   public String getSourceType(){
      return _sourceType;
   }

   /**
    * 设置代码类型的内容。
    *
    * @param value 代码类型
    */
   public void setSourceType(String value){
      _sourceType = value;
   }

   /**
    * 获得目标类型的内容。
    *
    * @return 目标类型
    */
   public String getTargetType(){
      return _targetType;
   }

   /**
    * 设置目标类型的内容。
    *
    * @param value 目标类型
    */
   public void setTargetType(String value){
      _targetType = value;
   }

   /**
    * 获得代码值的内容。
    *
    * @return 代码值
    */
   public String getCodeValue(){
      return _codeValue;
   }

   /**
    * 设置代码值的内容。
    *
    * @param value 代码值
    */
   public void setCodeValue(String value){
      _codeValue = value;
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
         if(config.contains("cache")){
            setCache(RBoolean.parse(config.get(PTY_CACHE)));
         }
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
         if(config.contains("is_class")){
            setIsClass(config.get(PTY_IS_CLASS));
         }
         if(config.contains("source_name")){
            setSourceName(config.get(PTY_SOURCE_NAME));
         }
         if(config.contains("parent_name")){
            setParentName(config.get(PTY_PARENT_NAME));
         }
         if(config.contains("storage_type")){
            setStorageType(config.get(PTY_STORAGE_TYPE));
         }
         if(config.contains("source_type")){
            setSourceType(config.get(PTY_SOURCE_TYPE));
         }
         if(config.contains("target_type")){
            setTargetType(config.get(PTY_TARGET_TYPE));
         }
         if(config.contains("code_value")){
            setCodeValue(config.get(PTY_CODE_VALUE));
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
         if(config.contains("cache")){
            setCache(RBoolean.parse(config.get(PTY_CACHE)));
         }
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
         if(config.contains("is_class")){
            setIsClass(config.get(PTY_IS_CLASS));
         }
         if(config.contains("source_name")){
            setSourceName(config.get(PTY_SOURCE_NAME));
         }
         if(config.contains("parent_name")){
            setParentName(config.get(PTY_PARENT_NAME));
         }
         if(config.contains("storage_type")){
            setStorageType(config.get(PTY_STORAGE_TYPE));
         }
         if(config.contains("source_type")){
            setSourceType(config.get(PTY_SOURCE_TYPE));
         }
         if(config.contains("target_type")){
            setTargetType(config.get(PTY_TARGET_TYPE));
         }
         if(config.contains("code_value")){
            setCodeValue(config.get(PTY_CODE_VALUE));
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
         if(config.contains("cache")){
            setCache(RBoolean.parse(config.get(PTY_CACHE)));
         }
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
         if(config.contains("is_class")){
            setIsClass(config.get(PTY_IS_CLASS));
         }
         if(config.contains("source_name")){
            setSourceName(config.get(PTY_SOURCE_NAME));
         }
         if(config.contains("parent_name")){
            setParentName(config.get(PTY_PARENT_NAME));
         }
         if(config.contains("storage_type")){
            setStorageType(config.get(PTY_STORAGE_TYPE));
         }
         if(config.contains("source_type")){
            setSourceType(config.get(PTY_SOURCE_TYPE));
         }
         if(config.contains("target_type")){
            setTargetType(config.get(PTY_TARGET_TYPE));
         }
         if(config.contains("code_value")){
            setCodeValue(config.get(PTY_CODE_VALUE));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getCode())){
            if(config.contains("code")){
               setCode(config.get(PTY_CODE));
            }
         }
         if(RString.isEmpty(getIsClass())){
            if(config.contains("is_class")){
               setIsClass(config.get(PTY_IS_CLASS));
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
         if(RString.isEmpty(getStorageType())){
            if(config.contains("storage_type")){
               setStorageType(config.get(PTY_STORAGE_TYPE));
            }
         }
         if(RString.isEmpty(getSourceType())){
            if(config.contains("source_type")){
               setSourceType(config.get(PTY_SOURCE_TYPE));
            }
         }
         if(RString.isEmpty(getTargetType())){
            if(config.contains("target_type")){
               setTargetType(config.get(PTY_TARGET_TYPE));
            }
         }
         if(RString.isEmpty(getCodeValue())){
            if(config.contains("code_value")){
               setCodeValue(config.get(PTY_CODE_VALUE));
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
         if(RBoolean.parse(getCache())){
            config.set(PTY_CACHE, RBoolean.toString(getCache()));
         }
         if(RString.isNotEmpty(getCode())){
            config.set(PTY_CODE, getCode());
         }
         if(RString.isNotEmpty(getIsClass())){
            config.set(PTY_IS_CLASS, getIsClass());
         }
         if(RString.isNotEmpty(getSourceName())){
            config.set(PTY_SOURCE_NAME, getSourceName());
         }
         if(RString.isNotEmpty(getParentName())){
            config.set(PTY_PARENT_NAME, getParentName());
         }
         if(RString.isNotEmpty(getStorageType())){
            config.set(PTY_STORAGE_TYPE, getStorageType());
         }
         if(RString.isNotEmpty(getSourceType())){
            config.set(PTY_SOURCE_TYPE, getSourceType());
         }
         if(RString.isNotEmpty(getTargetType())){
            config.set(PTY_TARGET_TYPE, getTargetType());
         }
         if(RString.isNotEmpty(getCodeValue())){
            config.set(PTY_CODE_VALUE, getCodeValue());
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
         if(RBoolean.parse(getCache())){
            config.set(PTY_CACHE, RBoolean.toString(getCache()));
         }
         if(RString.isNotEmpty(getCode())){
            config.set(PTY_CODE, getCode());
         }
         if(RString.isNotEmpty(getIsClass())){
            config.set(PTY_IS_CLASS, getIsClass());
         }
         if(RString.isNotEmpty(getSourceName())){
            config.set(PTY_SOURCE_NAME, getSourceName());
         }
         if(RString.isNotEmpty(getParentName())){
            config.set(PTY_PARENT_NAME, getParentName());
         }
         if(RString.isNotEmpty(getStorageType())){
            config.set(PTY_STORAGE_TYPE, getStorageType());
         }
         if(RString.isNotEmpty(getSourceType())){
            config.set(PTY_SOURCE_TYPE, getSourceType());
         }
         if(RString.isNotEmpty(getTargetType())){
            config.set(PTY_TARGET_TYPE, getTargetType());
         }
         if(RString.isNotEmpty(getCodeValue())){
            config.set(PTY_CODE_VALUE, getCodeValue());
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
         Boolean bCache = getCache();
         if(RBoolean.parse(bCache)){
            config.set(PTY_CACHE, RBoolean.toString(bCache));
         }
         String sCode = getCode();
         if(RString.isNotEmpty(sCode)){
            config.set(PTY_CODE, sCode);
         }
         String sIsClass = getIsClass();
         if(RString.isNotEmpty(sIsClass)){
            config.set(PTY_IS_CLASS, sIsClass);
         }
         String sSourceName = getSourceName();
         if(RString.isNotEmpty(sSourceName)){
            config.set(PTY_SOURCE_NAME, sSourceName);
         }
         String sParentName = getParentName();
         if(RString.isNotEmpty(sParentName)){
            config.set(PTY_PARENT_NAME, sParentName);
         }
         String sStorageType = getStorageType();
         if(RString.isNotEmpty(sStorageType)){
            config.set(PTY_STORAGE_TYPE, sStorageType);
         }
         String sSourceType = getSourceType();
         if(RString.isNotEmpty(sSourceType)){
            config.set(PTY_SOURCE_TYPE, sSourceType);
         }
         String sTargetType = getTargetType();
         if(RString.isNotEmpty(sTargetType)){
            config.set(PTY_TARGET_TYPE, sTargetType);
         }
         String sCodeValue = getCodeValue();
         if(RString.isNotEmpty(sCodeValue)){
            config.set(PTY_CODE_VALUE, sCodeValue);
         }
      }else if(EXmlConfig.Default == type){
         String sCode = getCode();
         if(RString.isNotEmpty(sCode)){
            config.set(PTY_CODE, sCode);
         }
         String sIsClass = getIsClass();
         if(RString.isNotEmpty(sIsClass)){
            config.set(PTY_IS_CLASS, sIsClass);
         }
         String sSourceName = getSourceName();
         if(RString.isNotEmpty(sSourceName)){
            config.set(PTY_SOURCE_NAME, sSourceName);
         }
         String sParentName = getParentName();
         if(RString.isNotEmpty(sParentName)){
            config.set(PTY_PARENT_NAME, sParentName);
         }
         String sStorageType = getStorageType();
         if(RString.isNotEmpty(sStorageType)){
            config.set(PTY_STORAGE_TYPE, sStorageType);
         }
         String sSourceType = getSourceType();
         if(RString.isNotEmpty(sSourceType)){
            config.set(PTY_SOURCE_TYPE, sSourceType);
         }
         String sTargetType = getTargetType();
         if(RString.isNotEmpty(sTargetType)){
            config.set(PTY_TARGET_TYPE, sTargetType);
         }
         String sCodeValue = getCodeValue();
         if(RString.isNotEmpty(sCodeValue)){
            config.set(PTY_CODE_VALUE, sCodeValue);
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
      }else if(PTY_CACHE.equalsIgnoreCase(name)){
         return RBoolean.toString(getCache());
      }else if(PTY_CODE.equalsIgnoreCase(name)){
         return getCode();
      }else if(PTY_IS_CLASS.equalsIgnoreCase(name)){
         return getIsClass();
      }else if(PTY_SOURCE_NAME.equalsIgnoreCase(name)){
         return getSourceName();
      }else if(PTY_PARENT_NAME.equalsIgnoreCase(name)){
         return getParentName();
      }else if(PTY_STORAGE_TYPE.equalsIgnoreCase(name)){
         return getStorageType();
      }else if(PTY_SOURCE_TYPE.equalsIgnoreCase(name)){
         return getSourceType();
      }else if(PTY_TARGET_TYPE.equalsIgnoreCase(name)){
         return getTargetType();
      }else if(PTY_CODE_VALUE.equalsIgnoreCase(name)){
         return getCodeValue();
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
      }else if(PTY_CACHE.equalsIgnoreCase(name)){
         setCache(RBoolean.parse(value));
      }else if(PTY_CODE.equalsIgnoreCase(name)){
         setCode(value);
      }else if(PTY_IS_CLASS.equalsIgnoreCase(name)){
         setIsClass(value);
      }else if(PTY_SOURCE_NAME.equalsIgnoreCase(name)){
         setSourceName(value);
      }else if(PTY_PARENT_NAME.equalsIgnoreCase(name)){
         setParentName(value);
      }else if(PTY_STORAGE_TYPE.equalsIgnoreCase(name)){
         setStorageType(value);
      }else if(PTY_SOURCE_TYPE.equalsIgnoreCase(name)){
         setSourceType(value);
      }else if(PTY_TARGET_TYPE.equalsIgnoreCase(name)){
         setTargetType(value);
      }else if(PTY_CODE_VALUE.equalsIgnoreCase(name)){
         setCodeValue(value);
      }
   }

}