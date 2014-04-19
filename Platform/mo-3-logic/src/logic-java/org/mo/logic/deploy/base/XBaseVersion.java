/*
 * @(#)XBaseVersion.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.deploy.base;

import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.logic.deploy.common.XObjectFace;

/**
 * <p>版本对象的XML节点基类</p>
 *
 * @author system
 */
public abstract class XBaseVersion
      extends FXmlObject
      implements
         XObjectFace
{

   public static final String NAME = "Version";

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
    * 描述信息的名称定义
    */
   public static final String PTY_NOTE = "note";

   @Override
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
    * 描述信息的定义
    */
   @AName("note")
   protected String _note;

   /**
    * 获得名称的内容。
    *
    * @return 名称
    */
   @Override
   public String getName(){
      return _name;
   }

   /**
    * 设置名称的内容。
    *
    * @param value 名称
    */
   @Override
   public void setName(String value){
      _name = value;
   }

   /**
    * 获得标签的内容。
    *
    * @return 标签
    */
   @Override
   public String getLabel(){
      return _label;
   }

   /**
    * 设置标签的内容。
    *
    * @param value 标签
    */
   @Override
   public void setLabel(String value){
      _label = value;
   }

   /**
    * 获得有效性的内容。
    *
    * @return 有效性
    */
   @Override
   public Boolean getIsValid(){
      return _isValid;
   }

   /**
    * 设置有效性的内容。
    *
    * @param value 有效性
    */
   @Override
   public void setIsValid(Boolean value){
      _isValid = value;
   }

   /**
    * 获得描述信息的内容。
    *
    * @return 描述信息
    */
   @Override
   public String getNote(){
      return _note;
   }

   /**
    * 设置描述信息的内容。
    *
    * @param value 描述信息
    */
   @Override
   public void setNote(String value){
      _note = value;
   }

   @Override
   public void loadConfig(FXmlNode config,
                          EXmlConfig type){
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
            setNote(config.get(PTY_NOTE));
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
      }else if(EXmlConfig.Default == type){
      }
   }

   @Override
   public void saveConfig(FXmlNode config,
                          EXmlConfig type){
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
         if(RString.isNotEmpty(getNote())){
            config.set(PTY_NOTE, getNote());
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
      }else if(EXmlConfig.Default == type){
      }
   }

   @Override
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
      }
      return null;
   }

   @Override
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
      }
   }
}
