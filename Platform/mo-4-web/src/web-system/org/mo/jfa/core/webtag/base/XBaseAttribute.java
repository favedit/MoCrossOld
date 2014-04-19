/*
 * @(#)XBaseAttribute.java
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
 * <p>属性对象的XML节点基类</p>
 *
 * @author system
 */
public abstract class XBaseAttribute
      extends FXmlObject
      implements
         XObjectFace
{
   public static final String NAME = "attribute";

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
    * required的名称定义
    */
   public static final String PTY_REQUIRED = "required";

   /**
    * html的名称定义
    */
   public static final String PTY_HTML = "html";

   /**
    * 默认数据的名称定义
    */
   public static final String PTY_DEFAULT_DATA = "default_data";

   /**
    * 页面名称的名称定义
    */
   public static final String PTY_HTML_NAME = "html_name";

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
    * required的定义
    */
   @AName("required")
   protected String _required;

   /**
    * html的定义
    */
   @AName("html")
   protected String _html;

   /**
    * 默认数据的定义
    */
   @AName("default_data")
   protected String _defaultData;

   /**
    * 页面名称的定义
    */
   @AName("html_name")
   protected String _htmlName;

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
    * 获得required的内容。
    *
    * @return required
    */
   public String getRequired(){
      return _required;
   }

   /**
    * 设置required的内容。
    *
    * @param value required
    */
   public void setRequired(String value){
      _required = value;
   }

   /**
    * 获得html的内容。
    *
    * @return html
    */
   public String getHtml(){
      return _html;
   }

   /**
    * 设置html的内容。
    *
    * @param value html
    */
   public void setHtml(String value){
      _html = value;
   }

   /**
    * 获得默认数据的内容。
    *
    * @return 默认数据
    */
   public String getDefaultData(){
      return _defaultData;
   }

   /**
    * 设置默认数据的内容。
    *
    * @param value 默认数据
    */
   public void setDefaultData(String value){
      _defaultData = value;
   }

   /**
    * 获得页面名称的内容。
    *
    * @return 页面名称
    */
   public String getHtmlName(){
      return _htmlName;
   }

   /**
    * 设置页面名称的内容。
    *
    * @param value 页面名称
    */
   public void setHtmlName(String value){
      _htmlName = value;
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
         if(config.contains("required")){
            setRequired(config.get(PTY_REQUIRED));
         }
         if(config.contains("html")){
            setHtml(config.get(PTY_HTML));
         }
         if(config.contains("default_data")){
            setDefaultData(config.get(PTY_DEFAULT_DATA));
         }
         if(config.contains("html_name")){
            setHtmlName(config.get(PTY_HTML_NAME));
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
         if(config.contains("required")){
            setRequired(config.get(PTY_REQUIRED));
         }
         if(config.contains("html")){
            setHtml(config.get(PTY_HTML));
         }
         if(config.contains("default_data")){
            setDefaultData(config.get(PTY_DEFAULT_DATA));
         }
         if(config.contains("html_name")){
            setHtmlName(config.get(PTY_HTML_NAME));
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
         if(config.contains("required")){
            setRequired(config.get(PTY_REQUIRED));
         }
         if(config.contains("html")){
            setHtml(config.get(PTY_HTML));
         }
         if(config.contains("default_data")){
            setDefaultData(config.get(PTY_DEFAULT_DATA));
         }
         if(config.contains("html_name")){
            setHtmlName(config.get(PTY_HTML_NAME));
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
         if(RString.isEmpty(getRequired())){
            if(config.contains("required")){
               setRequired(config.get(PTY_REQUIRED));
            }
         }
         if(RString.isEmpty(getHtml())){
            if(config.contains("html")){
               setHtml(config.get(PTY_HTML));
            }
         }
         if(RString.isEmpty(getDefaultData())){
            if(config.contains("default_data")){
               setDefaultData(config.get(PTY_DEFAULT_DATA));
            }
         }
         if(RString.isEmpty(getHtmlName())){
            if(config.contains("html_name")){
               setHtmlName(config.get(PTY_HTML_NAME));
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
         if(RString.isNotEmpty(getRequired())){
            config.set(PTY_REQUIRED, getRequired());
         }
         if(RString.isNotEmpty(getHtml())){
            config.set(PTY_HTML, getHtml());
         }
         if(RString.isNotEmpty(getDefaultData())){
            config.set(PTY_DEFAULT_DATA, getDefaultData());
         }
         if(RString.isNotEmpty(getHtmlName())){
            config.set(PTY_HTML_NAME, getHtmlName());
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
         if(RString.isNotEmpty(getRequired())){
            config.set(PTY_REQUIRED, getRequired());
         }
         if(RString.isNotEmpty(getHtml())){
            config.set(PTY_HTML, getHtml());
         }
         if(RString.isNotEmpty(getDefaultData())){
            config.set(PTY_DEFAULT_DATA, getDefaultData());
         }
         if(RString.isNotEmpty(getHtmlName())){
            config.set(PTY_HTML_NAME, getHtmlName());
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
         String sRequired = getRequired();
         if(RString.isNotEmpty(sRequired)){
            config.set(PTY_REQUIRED, sRequired);
         }
         String sHtml = getHtml();
         if(RString.isNotEmpty(sHtml)){
            config.set(PTY_HTML, sHtml);
         }
         String sDefaultData = getDefaultData();
         if(RString.isNotEmpty(sDefaultData)){
            config.set(PTY_DEFAULT_DATA, sDefaultData);
         }
         String sHtmlName = getHtmlName();
         if(RString.isNotEmpty(sHtmlName)){
            config.set(PTY_HTML_NAME, sHtmlName);
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
         String sRequired = getRequired();
         if(RString.isNotEmpty(sRequired)){
            config.set(PTY_REQUIRED, sRequired);
         }
         String sHtml = getHtml();
         if(RString.isNotEmpty(sHtml)){
            config.set(PTY_HTML, sHtml);
         }
         String sDefaultData = getDefaultData();
         if(RString.isNotEmpty(sDefaultData)){
            config.set(PTY_DEFAULT_DATA, sDefaultData);
         }
         String sHtmlName = getHtmlName();
         if(RString.isNotEmpty(sHtmlName)){
            config.set(PTY_HTML_NAME, sHtmlName);
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
      }else if(PTY_REQUIRED.equalsIgnoreCase(name)){
         return getRequired();
      }else if(PTY_HTML.equalsIgnoreCase(name)){
         return getHtml();
      }else if(PTY_DEFAULT_DATA.equalsIgnoreCase(name)){
         return getDefaultData();
      }else if(PTY_HTML_NAME.equalsIgnoreCase(name)){
         return getHtmlName();
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
      }else if(PTY_REQUIRED.equalsIgnoreCase(name)){
         setRequired(value);
      }else if(PTY_HTML.equalsIgnoreCase(name)){
         setHtml(value);
      }else if(PTY_DEFAULT_DATA.equalsIgnoreCase(name)){
         setDefaultData(value);
      }else if(PTY_HTML_NAME.equalsIgnoreCase(name)){
         setHtmlName(value);
      }
   }

}
