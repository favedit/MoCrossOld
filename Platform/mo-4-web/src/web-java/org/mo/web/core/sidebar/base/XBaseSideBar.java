/*
 * @(#)XBaseSideBar.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.core.sidebar.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.web.core.sidebar.common.XControlFace;

/**
 * <p>分割栏对象的XML节点基类</p>
 *
 * @author system
 */
public abstract class XBaseSideBar
      extends FXmlObject
      implements
         XControlFace
{
   public static final String NAME = "SideBar";

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
    * 颜色的名称定义
    */
   public static final String PTY_COLOR = "color";

   /**
    * 状态栏提示的名称定义
    */
   public static final String PTY_HINT = "hint";

   /**
    * 控件样式的名称定义
    */
   public static final String PTY_STYLE_REFER = "style_refer";

   /**
    * 标签样式的名称定义
    */
   public static final String PTY_STYLE_LABEL = "style_label";

   /**
    * 编辑样式的名称定义
    */
   public static final String PTY_STYLE_EDIT = "style_edit";

   /**
    * 高度的名称定义
    */
   public static final String PTY_HEIGH = "heigh";

   /**
    * 宽度的名称定义
    */
   public static final String PTY_WIDTH = "width";

   /**
    * 图片的名称定义
    */
   public static final String PTY_ICON = "icon";

   public static boolean isInstance(IXmlObject xobject){
      return NAME.equals(xobject.name());
   }

   public static boolean isName(String name){
      return NAME.equals(name);
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
    * 颜色的定义
    */
   @AName("color")
   protected String _color;

   /**
    * 状态栏提示的定义
    */
   @AName("hint")
   protected String _hint;

   /**
    * 控件样式的定义
    */
   @AName("style_refer")
   protected String _styleRefer;

   /**
    * 标签样式的定义
    */
   @AName("style_label")
   protected String _styleLabel;

   /**
    * 编辑样式的定义
    */
   @AName("style_edit")
   protected String _styleEdit;

   /**
    * 高度的定义
    */
   @AName("heigh")
   protected String _heigh;

   /**
    * 宽度的定义
    */
   @AName("width")
   protected String _width;

   /**
    * 图片的定义
    */
   @AName("icon")
   protected String _icon;

   /**
    * 获得颜色的内容。
    *
    * @return 颜色
    */
   @Override
   public String getColor(){
      return _color;
   }

   /**
    * 获得高度的内容。
    *
    * @return 高度
    */
   @Override
   public String getHeigh(){
      return _heigh;
   }

   /**
    * 获得状态栏提示的内容。
    *
    * @return 状态栏提示
    */
   @Override
   public String getHint(){
      return _hint;
   }

   /**
    * 获得图片的内容。
    *
    * @return 图片
    */
   @Override
   public String getIcon(){
      return _icon;
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
    * 获得标签的内容。
    *
    * @return 标签
    */
   @Override
   public String getLabel(){
      return _label.get();
   }

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
    * 获得注释的内容。
    *
    * @return 注释
    */
   @Override
   public String getNote(){
      return _note.get();
   }

   /**
    * 获得编辑样式的内容。
    *
    * @return 编辑样式
    */
   @Override
   public String getStyleEdit(){
      return _styleEdit;
   }

   /**
    * 获得标签样式的内容。
    *
    * @return 标签样式
    */
   @Override
   public String getStyleLabel(){
      return _styleLabel;
   }

   /**
    * 获得控件样式的内容。
    *
    * @return 控件样式
    */
   @Override
   public String getStyleRefer(){
      return _styleRefer;
   }

   /**
    * 获得宽度的内容。
    *
    * @return 宽度
    */
   @Override
   public String getWidth(){
      return _width;
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
      }else if(PTY_COLOR.equalsIgnoreCase(name)){
         return getColor();
      }else if(PTY_HINT.equalsIgnoreCase(name)){
         return getHint();
      }else if(PTY_STYLE_REFER.equalsIgnoreCase(name)){
         return getStyleRefer();
      }else if(PTY_STYLE_LABEL.equalsIgnoreCase(name)){
         return getStyleLabel();
      }else if(PTY_STYLE_EDIT.equalsIgnoreCase(name)){
         return getStyleEdit();
      }else if(PTY_HEIGH.equalsIgnoreCase(name)){
         return getHeigh();
      }else if(PTY_WIDTH.equalsIgnoreCase(name)){
         return getWidth();
      }else if(PTY_ICON.equalsIgnoreCase(name)){
         return getIcon();
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
      }else if(PTY_COLOR.equalsIgnoreCase(name)){
         setColor(value);
      }else if(PTY_HINT.equalsIgnoreCase(name)){
         setHint(value);
      }else if(PTY_STYLE_REFER.equalsIgnoreCase(name)){
         setStyleRefer(value);
      }else if(PTY_STYLE_LABEL.equalsIgnoreCase(name)){
         setStyleLabel(value);
      }else if(PTY_STYLE_EDIT.equalsIgnoreCase(name)){
         setStyleEdit(value);
      }else if(PTY_HEIGH.equalsIgnoreCase(name)){
         setHeigh(value);
      }else if(PTY_WIDTH.equalsIgnoreCase(name)){
         setWidth(value);
      }else if(PTY_ICON.equalsIgnoreCase(name)){
         setIcon(value);
      }
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
         if(config.contains("is_valid")){
            setIsValid(RBoolean.parse(config.get(PTY_IS_VALID)));
         }
         if(config.contains("note")){
            _note.unpack(config.get(PTY_NOTE));
         }
         if(config.contains("color")){
            setColor(config.get(PTY_COLOR));
         }
         if(config.contains("hint")){
            setHint(config.get(PTY_HINT));
         }
         if(config.contains("style_refer")){
            setStyleRefer(config.get(PTY_STYLE_REFER));
         }
         if(config.contains("style_label")){
            setStyleLabel(config.get(PTY_STYLE_LABEL));
         }
         if(config.contains("style_edit")){
            setStyleEdit(config.get(PTY_STYLE_EDIT));
         }
         if(config.contains("heigh")){
            setHeigh(config.get(PTY_HEIGH));
         }
         if(config.contains("width")){
            setWidth(config.get(PTY_WIDTH));
         }
         if(config.contains("icon")){
            setIcon(config.get(PTY_ICON));
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
         if(config.contains("color")){
            setColor(config.get(PTY_COLOR));
         }
         if(config.contains("hint")){
            setHint(config.get(PTY_HINT));
         }
         if(config.contains("style_label")){
            setStyleLabel(config.get(PTY_STYLE_LABEL));
         }
         if(config.contains("style_edit")){
            setStyleEdit(config.get(PTY_STYLE_EDIT));
         }
         if(config.contains("heigh")){
            setHeigh(config.get(PTY_HEIGH));
         }
         if(config.contains("width")){
            setWidth(config.get(PTY_WIDTH));
         }
         if(config.contains("icon")){
            setIcon(config.get(PTY_ICON));
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
         if(config.contains("color")){
            setColor(config.get(PTY_COLOR));
         }
         if(config.contains("hint")){
            setHint(config.get(PTY_HINT));
         }
         if(config.contains("style_refer")){
            setStyleRefer(config.get(PTY_STYLE_REFER));
         }
         if(config.contains("style_label")){
            setStyleLabel(config.get(PTY_STYLE_LABEL));
         }
         if(config.contains("style_edit")){
            setStyleEdit(config.get(PTY_STYLE_EDIT));
         }
         if(config.contains("heigh")){
            setHeigh(config.get(PTY_HEIGH));
         }
         if(config.contains("width")){
            setWidth(config.get(PTY_WIDTH));
         }
         if(config.contains("icon")){
            setIcon(config.get(PTY_ICON));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getStyleLabel())){
            if(config.contains("style_label")){
               setStyleLabel(config.get(PTY_STYLE_LABEL));
            }
         }
         if(RString.isEmpty(getStyleEdit())){
            if(config.contains("style_edit")){
               setStyleEdit(config.get(PTY_STYLE_EDIT));
            }
         }
         if(RString.isEmpty(getHeigh())){
            if(config.contains("heigh")){
               setHeigh(config.get(PTY_HEIGH));
            }
         }
         if(RString.isEmpty(getWidth())){
            if(config.contains("width")){
               setWidth(config.get(PTY_WIDTH));
            }
         }
         if(RString.isEmpty(getIcon())){
            if(config.contains("icon")){
               setIcon(config.get(PTY_ICON));
            }
         }
      }
   }

   @Override
   public String name(){
      return NAME;
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
         if(RBoolean.parse(getIsValid())){
            config.set(PTY_IS_VALID, RBoolean.toString(getIsValid()));
         }
         String note = _note.pack().toString();
         if(RString.isNotEmpty(note)){
            config.set(PTY_NOTE, note);
         }
         if(RString.isNotEmpty(getColor())){
            config.set(PTY_COLOR, getColor());
         }
         if(RString.isNotEmpty(getHint())){
            config.set(PTY_HINT, getHint());
         }
         if(RString.isNotEmpty(getStyleRefer())){
            config.set(PTY_STYLE_REFER, getStyleRefer());
         }
         if(RString.isNotEmpty(getStyleLabel())){
            config.set(PTY_STYLE_LABEL, getStyleLabel());
         }
         if(RString.isNotEmpty(getStyleEdit())){
            config.set(PTY_STYLE_EDIT, getStyleEdit());
         }
         if(RString.isNotEmpty(getHeigh())){
            config.set(PTY_HEIGH, getHeigh());
         }
         if(RString.isNotEmpty(getWidth())){
            config.set(PTY_WIDTH, getWidth());
         }
         if(RString.isNotEmpty(getIcon())){
            config.set(PTY_ICON, getIcon());
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
         if(RString.isNotEmpty(getColor())){
            config.set(PTY_COLOR, getColor());
         }
         if(RString.isNotEmpty(getHint())){
            config.set(PTY_HINT, getHint());
         }
         if(RString.isNotEmpty(getStyleLabel())){
            config.set(PTY_STYLE_LABEL, getStyleLabel());
         }
         if(RString.isNotEmpty(getStyleEdit())){
            config.set(PTY_STYLE_EDIT, getStyleEdit());
         }
         if(RString.isNotEmpty(getHeigh())){
            config.set(PTY_HEIGH, getHeigh());
         }
         if(RString.isNotEmpty(getWidth())){
            config.set(PTY_WIDTH, getWidth());
         }
         if(RString.isNotEmpty(getIcon())){
            config.set(PTY_ICON, getIcon());
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
         String sColor = getColor();
         if(RString.isNotEmpty(sColor)){
            config.set(PTY_COLOR, sColor);
         }
         String sHint = getHint();
         if(RString.isNotEmpty(sHint)){
            config.set(PTY_HINT, sHint);
         }
         String sStyleRefer = getStyleRefer();
         if(RString.isNotEmpty(sStyleRefer)){
            config.set(PTY_STYLE_REFER, sStyleRefer);
         }
         String sStyleLabel = getStyleLabel();
         if(RString.isNotEmpty(sStyleLabel)){
            config.set(PTY_STYLE_LABEL, sStyleLabel);
         }
         String sStyleEdit = getStyleEdit();
         if(RString.isNotEmpty(sStyleEdit)){
            config.set(PTY_STYLE_EDIT, sStyleEdit);
         }
         String sHeigh = getHeigh();
         if(RString.isNotEmpty(sHeigh)){
            config.set(PTY_HEIGH, sHeigh);
         }
         String sWidth = getWidth();
         if(RString.isNotEmpty(sWidth)){
            config.set(PTY_WIDTH, sWidth);
         }
         String sIcon = getIcon();
         if(RString.isNotEmpty(sIcon)){
            config.set(PTY_ICON, sIcon);
         }
      }else if(EXmlConfig.Default == type){
         String sStyleLabel = getStyleLabel();
         if(RString.isNotEmpty(sStyleLabel)){
            config.set(PTY_STYLE_LABEL, sStyleLabel);
         }
         String sStyleEdit = getStyleEdit();
         if(RString.isNotEmpty(sStyleEdit)){
            config.set(PTY_STYLE_EDIT, sStyleEdit);
         }
         String sHeigh = getHeigh();
         if(RString.isNotEmpty(sHeigh)){
            config.set(PTY_HEIGH, sHeigh);
         }
         String sWidth = getWidth();
         if(RString.isNotEmpty(sWidth)){
            config.set(PTY_WIDTH, sWidth);
         }
         String sIcon = getIcon();
         if(RString.isNotEmpty(sIcon)){
            config.set(PTY_ICON, sIcon);
         }
      }
   }

   /**
    * 设置颜色的内容。
    *
    * @param value 颜色
    */
   @Override
   public void setColor(String value){
      _color = value;
   }

   /**
    * 设置高度的内容。
    *
    * @param value 高度
    */
   @Override
   public void setHeigh(String value){
      _heigh = value;
   }

   /**
    * 设置状态栏提示的内容。
    *
    * @param value 状态栏提示
    */
   @Override
   public void setHint(String value){
      _hint = value;
   }

   /**
    * 设置图片的内容。
    *
    * @param value 图片
    */
   @Override
   public void setIcon(String value){
      _icon = value;
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
    * 设置标签的内容。
    *
    * @param value 标签
    */
   @Override
   public void setLabel(String value){
      _label.set(value);
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
    * 设置注释的内容。
    *
    * @param value 注释
    */
   @Override
   public void setNote(String value){
      _note.set(value);
   }

   /**
    * 设置编辑样式的内容。
    *
    * @param value 编辑样式
    */
   @Override
   public void setStyleEdit(String value){
      _styleEdit = value;
   }

   /**
    * 设置标签样式的内容。
    *
    * @param value 标签样式
    */
   @Override
   public void setStyleLabel(String value){
      _styleLabel = value;
   }

   /**
    * 设置控件样式的内容。
    *
    * @param value 控件样式
    */
   @Override
   public void setStyleRefer(String value){
      _styleRefer = value;
   }

   /**
    * 设置宽度的内容。
    *
    * @param value 宽度
    */
   @Override
   public void setWidth(String value){
      _width = value;
   }

}
