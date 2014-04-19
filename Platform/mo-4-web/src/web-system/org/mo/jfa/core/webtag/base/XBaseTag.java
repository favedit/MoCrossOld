/*
 * @(#)XBaseTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.core.webtag.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.jfa.core.webtag.common.XObjectFace;

/**
 * <p>tag对象的XML节点基类</p>
 *
 * @author system
 */
public abstract class XBaseTag
      extends FXmlObject
      implements
         XObjectFace
{
   public static final String NAME = "tag";

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
    * 注释的名称定义
    */
   public static final String PTY_NOTE = "note";

   /**
    * 类型的名称定义
    */
   public static final String PTY__TYPE = "_type";

   /**
    * 有效性的名称定义
    */
   public static final String PTY_IS_VALID = "is_valid";

   /**
    * 类名称的名称定义
    */
   public static final String PTY_CLASS_NAME = "class_name";

   /**
    * 基类名称的名称定义
    */
   public static final String PTY_BASE_NAME = "base_name";

   /**
    * tag接口的名称定义
    */
   public static final String PTY_TAG_FACE = "tag_face";

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
   protected FMultiString _label = new FMultiString();

   /**
    * 注释的定义
    */
   @AName("note")
   protected FMultiString _note = new FMultiString();

   /**
    * 类型的定义
    */
   @AName("_type")
   protected String __type;

   /**
    * 有效性的定义
    */
   @AName("is_valid")
   protected String _isValid;

   /**
    * 类名称的定义
    */
   @AName("class_name")
   protected String _className;

   /**
    * 基类名称的定义
    */
   @AName("base_name")
   protected String _baseName;

   /**
    * tag接口的定义
    */
   @AName("tag_face")
   protected String _tagFace;

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
      return _label.get();
   }

   /**
    * 设置标签的内容。
    *
    * @param value 标签
    */
   @Override
   public void setLabel(String value){
      _label.set(value);
   }

   /**
    * 获得注释的内容。
    *
    * @return 注释
    */
   @Override
   public String getNote(){
      return _note.get();
   }

   /**
    * 设置注释的内容。
    *
    * @param value 注释
    */
   @Override
   public void setNote(String value){
      _note.set(value);
   }

   /**
    * 获得类型的内容。
    *
    * @return 类型
    */
   @Override
   public String get_type(){
      return __type;
   }

   /**
    * 设置类型的内容。
    *
    * @param value 类型
    */
   @Override
   public void set_type(String value){
      __type = value;
   }

   /**
    * 获得有效性的内容。
    *
    * @return 有效性
    */
   @Override
   public String getIsValid(){
      return _isValid;
   }

   /**
    * 设置有效性的内容。
    *
    * @param value 有效性
    */
   @Override
   public void setIsValid(String value){
      _isValid = value;
   }

   /**
    * 获得类名称的内容。
    *
    * @return 类名称
    */
   public String getClassName(){
      return _className;
   }

   /**
    * 设置类名称的内容。
    *
    * @param value 类名称
    */
   public void setClassName(String value){
      _className = value;
   }

   /**
    * 获得基类名称的内容。
    *
    * @return 基类名称
    */
   public String getBaseName(){
      return _baseName;
   }

   /**
    * 设置基类名称的内容。
    *
    * @param value 基类名称
    */
   public void setBaseName(String value){
      _baseName = value;
   }

   /**
    * 获得tag接口的内容。
    *
    * @return tag接口
    */
   public String getTagFace(){
      return _tagFace;
   }

   /**
    * 设置tag接口的内容。
    *
    * @param value tag接口
    */
   public void setTagFace(String value){
      _tagFace = value;
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
         if(config.contains("class_name")){
            setClassName(config.get(PTY_CLASS_NAME));
         }
         if(config.contains("base_name")){
            setBaseName(config.get(PTY_BASE_NAME));
         }
         if(config.contains("tag_face")){
            setTagFace(config.get(PTY_TAG_FACE));
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
         if(config.contains("class_name")){
            setClassName(config.get(PTY_CLASS_NAME));
         }
         if(config.contains("base_name")){
            setBaseName(config.get(PTY_BASE_NAME));
         }
         if(config.contains("tag_face")){
            setTagFace(config.get(PTY_TAG_FACE));
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
         if(config.contains("class_name")){
            setClassName(config.get(PTY_CLASS_NAME));
         }
         if(config.contains("base_name")){
            setBaseName(config.get(PTY_BASE_NAME));
         }
         if(config.contains("tag_face")){
            setTagFace(config.get(PTY_TAG_FACE));
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
         if(RString.isEmpty(getClassName())){
            if(config.contains("class_name")){
               setClassName(config.get(PTY_CLASS_NAME));
            }
         }
         if(RString.isEmpty(getBaseName())){
            if(config.contains("base_name")){
               setBaseName(config.get(PTY_BASE_NAME));
            }
         }
         if(RString.isEmpty(getTagFace())){
            if(config.contains("tag_face")){
               setTagFace(config.get(PTY_TAG_FACE));
            }
         }
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
         if(RString.isNotEmpty(getClassName())){
            config.set(PTY_CLASS_NAME, getClassName());
         }
         if(RString.isNotEmpty(getBaseName())){
            config.set(PTY_BASE_NAME, getBaseName());
         }
         if(RString.isNotEmpty(getTagFace())){
            config.set(PTY_TAG_FACE, getTagFace());
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
         if(RString.isNotEmpty(getClassName())){
            config.set(PTY_CLASS_NAME, getClassName());
         }
         if(RString.isNotEmpty(getBaseName())){
            config.set(PTY_BASE_NAME, getBaseName());
         }
         if(RString.isNotEmpty(getTagFace())){
            config.set(PTY_TAG_FACE, getTagFace());
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
         String sClassName = getClassName();
         if(RString.isNotEmpty(sClassName)){
            config.set(PTY_CLASS_NAME, sClassName);
         }
         String sBaseName = getBaseName();
         if(RString.isNotEmpty(sBaseName)){
            config.set(PTY_BASE_NAME, sBaseName);
         }
         String sTagFace = getTagFace();
         if(RString.isNotEmpty(sTagFace)){
            config.set(PTY_TAG_FACE, sTagFace);
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
         String sClassName = getClassName();
         if(RString.isNotEmpty(sClassName)){
            config.set(PTY_CLASS_NAME, sClassName);
         }
         String sBaseName = getBaseName();
         if(RString.isNotEmpty(sBaseName)){
            config.set(PTY_BASE_NAME, sBaseName);
         }
         String sTagFace = getTagFace();
         if(RString.isNotEmpty(sTagFace)){
            config.set(PTY_TAG_FACE, sTagFace);
         }
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
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         return getNote();
      }else if(PTY__TYPE.equalsIgnoreCase(name)){
         return get_type();
      }else if(PTY_IS_VALID.equalsIgnoreCase(name)){
         return getIsValid();
      }else if(PTY_CLASS_NAME.equalsIgnoreCase(name)){
         return getClassName();
      }else if(PTY_BASE_NAME.equalsIgnoreCase(name)){
         return getBaseName();
      }else if(PTY_TAG_FACE.equalsIgnoreCase(name)){
         return getTagFace();
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
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         setNote(value);
      }else if(PTY__TYPE.equalsIgnoreCase(name)){
         set_type(value);
      }else if(PTY_IS_VALID.equalsIgnoreCase(name)){
         setIsValid(value);
      }else if(PTY_CLASS_NAME.equalsIgnoreCase(name)){
         setClassName(value);
      }else if(PTY_BASE_NAME.equalsIgnoreCase(name)){
         setBaseName(value);
      }else if(PTY_TAG_FACE.equalsIgnoreCase(name)){
         setTagFace(value);
      }
   }

}
