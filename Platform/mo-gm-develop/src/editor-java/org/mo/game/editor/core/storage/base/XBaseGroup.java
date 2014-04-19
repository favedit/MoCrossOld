/*
 * @(#)XBaseGroup.java
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
 * <p>实体分组对象的XML节点基类</p>
 *
 * @author system
 */
public abstract class XBaseGroup
      extends FXmlObject
      implements
         XObjectFace{

   private static final long serialVersionUID = 1L;

   public static final String NAME = "Group";

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
    * 代码名称的名称定义
    */
   public static final String PTY_SOURCE_NAME = "source_name";

   /**
    * 代码的名称定义
    */
   public static final String PTY_CODE = "code";

   /**
    * 代码全称的名称定义
    */
   public static final String PTY_FULL_CODE = "full_code";


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
    * 代码名称的定义
    */
   @AName("source_name")
   protected String _sourceName;

   /**
    * 代码的定义
    */
   @AName("code")
   protected String _code;

   /**
    * 代码全称的定义
    */
   @AName("full_code")
   protected String _fullCode;


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
    * 获得代码名称的内容。
    *
    * @return 代码名称
    */
   public String getSourceName(){
      return _sourceName;
   }

   /**
    * 设置代码名称的内容。
    *
    * @param value 代码名称
    */
   public void setSourceName(String value){
      _sourceName = value;
   }

   /**
    * 获得代码的内容。
    *
    * @return 代码
    */
   public String getCode(){
      return _code;
   }

   /**
    * 设置代码的内容。
    *
    * @param value 代码
    */
   public void setCode(String value){
      _code = value;
   }

   /**
    * 获得代码全称的内容。
    *
    * @return 代码全称
    */
   public String getFullCode(){
      return _fullCode;
   }

   /**
    * 设置代码全称的内容。
    *
    * @param value 代码全称
    */
   public void setFullCode(String value){
      _fullCode = value;
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
         if(config.contains("source_name")){
            setSourceName(config.get(PTY_SOURCE_NAME));
         }
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
         if(config.contains("full_code")){
            setFullCode(config.get(PTY_FULL_CODE));
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
         if(RString.isNotEmpty(getSourceName())){
            config.set(PTY_SOURCE_NAME, getSourceName());
         }
         if(RString.isNotEmpty(getCode())){
            config.set(PTY_CODE, getCode());
         }
         if(RString.isNotEmpty(getFullCode())){
            config.set(PTY_FULL_CODE, getFullCode());
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
      }else if(PTY_SOURCE_NAME.equalsIgnoreCase(name)){
         return getSourceName();
      }else if(PTY_CODE.equalsIgnoreCase(name)){
         return getCode();
      }else if(PTY_FULL_CODE.equalsIgnoreCase(name)){
         return getFullCode();
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
      }else if(PTY_SOURCE_NAME.equalsIgnoreCase(name)){
         setSourceName(value);
      }else if(PTY_CODE.equalsIgnoreCase(name)){
         setCode(value);
      }else if(PTY_FULL_CODE.equalsIgnoreCase(name)){
         setFullCode(value);
      }
   }

}