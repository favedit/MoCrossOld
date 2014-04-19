/*
 * @(#)XBaseLabelGroup.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.data.chart.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.data.chart.common.XObjectFace;

/**
 * <p>注释对象的XML节点基类</p>
 *
 * @author system
 */
public abstract class XBaseLabelGroup
      extends FXmlObject
      implements
         XObjectFace
{
   public static final String NAME = "LabelGroup";

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
    * 线条宽度的名称定义
    */
   public static final String PTY_LINE_WIDTH = "line_width";

   /**
    * 线条颜色的名称定义
    */
   public static final String PTY_LINE_COLOR = "line_color";

   /**
    * 背景颜色的名称定义
    */
   public static final String PTY_BACKGROUND_COLOR = "background_color";

   /**
    * 位置的名称定义
    */
   public static final String PTY_POSITION = "position";

   /**
    * 类型的名称定义
    */
   public static final String PTY_TYPE = "type";

   /**
    * 字体颜色的名称定义
    */
   public static final String PTY_FONT_COLOR = "font_color";

   /**
    * 字体大小的名称定义
    */
   public static final String PTY_FONT_SIZE = "font_size";

   /**
    * 字体颜色的名称定义
    */
   public static final String PTY_FONT_NAME = "font_name";

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
    * 线条宽度的定义
    */
   @AName("line_width")
   protected String _lineWidth;

   /**
    * 线条颜色的定义
    */
   @AName("line_color")
   protected String _lineColor;

   /**
    * 背景颜色的定义
    */
   @AName("background_color")
   protected String _backgroundColor;

   /**
    * 位置的定义
    */
   @AName("position")
   protected String _position;

   /**
    * 类型的定义
    */
   @AName("type")
   protected String _type;

   /**
    * 字体颜色的定义
    */
   @AName("font_color")
   protected String _fontColor;

   /**
    * 字体大小的定义
    */
   @AName("font_size")
   protected String _fontSize;

   /**
    * 字体颜色的定义
    */
   @AName("font_name")
   protected String _fontName;

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
    * 获得线条宽度的内容。
    *
    * @return 线条宽度
    */
   public String getLineWidth(){
      return _lineWidth;
   }

   /**
    * 设置线条宽度的内容。
    *
    * @param value 线条宽度
    */
   public void setLineWidth(String value){
      _lineWidth = value;
   }

   /**
    * 获得线条颜色的内容。
    *
    * @return 线条颜色
    */
   public String getLineColor(){
      return _lineColor;
   }

   /**
    * 设置线条颜色的内容。
    *
    * @param value 线条颜色
    */
   public void setLineColor(String value){
      _lineColor = value;
   }

   /**
    * 获得背景颜色的内容。
    *
    * @return 背景颜色
    */
   public String getBackgroundColor(){
      return _backgroundColor;
   }

   /**
    * 设置背景颜色的内容。
    *
    * @param value 背景颜色
    */
   public void setBackgroundColor(String value){
      _backgroundColor = value;
   }

   /**
    * 获得位置的内容。
    *
    * @return 位置
    */
   public String getPosition(){
      return _position;
   }

   /**
    * 设置位置的内容。
    *
    * @param value 位置
    */
   public void setPosition(String value){
      _position = value;
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
    * 获得字体颜色的内容。
    *
    * @return 字体颜色
    */
   public String getFontColor(){
      return _fontColor;
   }

   /**
    * 设置字体颜色的内容。
    *
    * @param value 字体颜色
    */
   public void setFontColor(String value){
      _fontColor = value;
   }

   /**
    * 获得字体大小的内容。
    *
    * @return 字体大小
    */
   public String getFontSize(){
      return _fontSize;
   }

   /**
    * 设置字体大小的内容。
    *
    * @param value 字体大小
    */
   public void setFontSize(String value){
      _fontSize = value;
   }

   /**
    * 获得字体颜色的内容。
    *
    * @return 字体颜色
    */
   public String getFontName(){
      return _fontName;
   }

   /**
    * 设置字体颜色的内容。
    *
    * @param value 字体颜色
    */
   public void setFontName(String value){
      _fontName = value;
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
         if(config.contains("line_width")){
            setLineWidth(config.get(PTY_LINE_WIDTH));
         }
         if(config.contains("line_color")){
            setLineColor(config.get(PTY_LINE_COLOR));
         }
         if(config.contains("background_color")){
            setBackgroundColor(config.get(PTY_BACKGROUND_COLOR));
         }
         if(config.contains("position")){
            setPosition(config.get(PTY_POSITION));
         }
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
         }
         if(config.contains("font_color")){
            setFontColor(config.get(PTY_FONT_COLOR));
         }
         if(config.contains("font_size")){
            setFontSize(config.get(PTY_FONT_SIZE));
         }
         if(config.contains("font_name")){
            setFontName(config.get(PTY_FONT_NAME));
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
         if(config.contains("line_width")){
            setLineWidth(config.get(PTY_LINE_WIDTH));
         }
         if(config.contains("line_color")){
            setLineColor(config.get(PTY_LINE_COLOR));
         }
         if(config.contains("background_color")){
            setBackgroundColor(config.get(PTY_BACKGROUND_COLOR));
         }
         if(config.contains("position")){
            setPosition(config.get(PTY_POSITION));
         }
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
         }
         if(config.contains("font_color")){
            setFontColor(config.get(PTY_FONT_COLOR));
         }
         if(config.contains("font_size")){
            setFontSize(config.get(PTY_FONT_SIZE));
         }
         if(config.contains("font_name")){
            setFontName(config.get(PTY_FONT_NAME));
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
         if(config.contains("line_width")){
            setLineWidth(config.get(PTY_LINE_WIDTH));
         }
         if(config.contains("line_color")){
            setLineColor(config.get(PTY_LINE_COLOR));
         }
         if(config.contains("background_color")){
            setBackgroundColor(config.get(PTY_BACKGROUND_COLOR));
         }
         if(config.contains("position")){
            setPosition(config.get(PTY_POSITION));
         }
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
         }
         if(config.contains("font_color")){
            setFontColor(config.get(PTY_FONT_COLOR));
         }
         if(config.contains("font_size")){
            setFontSize(config.get(PTY_FONT_SIZE));
         }
         if(config.contains("font_name")){
            setFontName(config.get(PTY_FONT_NAME));
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
         if(RString.isNotEmpty(getLineWidth())){
            config.set(PTY_LINE_WIDTH, getLineWidth());
         }
         if(RString.isNotEmpty(getLineColor())){
            config.set(PTY_LINE_COLOR, getLineColor());
         }
         if(RString.isNotEmpty(getBackgroundColor())){
            config.set(PTY_BACKGROUND_COLOR, getBackgroundColor());
         }
         if(RString.isNotEmpty(getPosition())){
            config.set(PTY_POSITION, getPosition());
         }
         if(RString.isNotEmpty(getType())){
            config.set(PTY_TYPE, getType());
         }
         if(RString.isNotEmpty(getFontColor())){
            config.set(PTY_FONT_COLOR, getFontColor());
         }
         if(RString.isNotEmpty(getFontSize())){
            config.set(PTY_FONT_SIZE, getFontSize());
         }
         if(RString.isNotEmpty(getFontName())){
            config.set(PTY_FONT_NAME, getFontName());
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
         if(RString.isNotEmpty(getLineWidth())){
            config.set(PTY_LINE_WIDTH, getLineWidth());
         }
         if(RString.isNotEmpty(getLineColor())){
            config.set(PTY_LINE_COLOR, getLineColor());
         }
         if(RString.isNotEmpty(getBackgroundColor())){
            config.set(PTY_BACKGROUND_COLOR, getBackgroundColor());
         }
         if(RString.isNotEmpty(getPosition())){
            config.set(PTY_POSITION, getPosition());
         }
         if(RString.isNotEmpty(getType())){
            config.set(PTY_TYPE, getType());
         }
         if(RString.isNotEmpty(getFontColor())){
            config.set(PTY_FONT_COLOR, getFontColor());
         }
         if(RString.isNotEmpty(getFontSize())){
            config.set(PTY_FONT_SIZE, getFontSize());
         }
         if(RString.isNotEmpty(getFontName())){
            config.set(PTY_FONT_NAME, getFontName());
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
         String sLineWidth = getLineWidth();
         if(RString.isNotEmpty(sLineWidth)){
            config.set(PTY_LINE_WIDTH, sLineWidth);
         }
         String sLineColor = getLineColor();
         if(RString.isNotEmpty(sLineColor)){
            config.set(PTY_LINE_COLOR, sLineColor);
         }
         String sBackgroundColor = getBackgroundColor();
         if(RString.isNotEmpty(sBackgroundColor)){
            config.set(PTY_BACKGROUND_COLOR, sBackgroundColor);
         }
         String sPosition = getPosition();
         if(RString.isNotEmpty(sPosition)){
            config.set(PTY_POSITION, sPosition);
         }
         String sType = getType();
         if(RString.isNotEmpty(sType)){
            config.set(PTY_TYPE, sType);
         }
         String sFontColor = getFontColor();
         if(RString.isNotEmpty(sFontColor)){
            config.set(PTY_FONT_COLOR, sFontColor);
         }
         String sFontSize = getFontSize();
         if(RString.isNotEmpty(sFontSize)){
            config.set(PTY_FONT_SIZE, sFontSize);
         }
         String sFontName = getFontName();
         if(RString.isNotEmpty(sFontName)){
            config.set(PTY_FONT_NAME, sFontName);
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
      }else if(PTY_LINE_WIDTH.equalsIgnoreCase(name)){
         return getLineWidth();
      }else if(PTY_LINE_COLOR.equalsIgnoreCase(name)){
         return getLineColor();
      }else if(PTY_BACKGROUND_COLOR.equalsIgnoreCase(name)){
         return getBackgroundColor();
      }else if(PTY_POSITION.equalsIgnoreCase(name)){
         return getPosition();
      }else if(PTY_TYPE.equalsIgnoreCase(name)){
         return getType();
      }else if(PTY_FONT_COLOR.equalsIgnoreCase(name)){
         return getFontColor();
      }else if(PTY_FONT_SIZE.equalsIgnoreCase(name)){
         return getFontSize();
      }else if(PTY_FONT_NAME.equalsIgnoreCase(name)){
         return getFontName();
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
      }else if(PTY_LINE_WIDTH.equalsIgnoreCase(name)){
         setLineWidth(value);
      }else if(PTY_LINE_COLOR.equalsIgnoreCase(name)){
         setLineColor(value);
      }else if(PTY_BACKGROUND_COLOR.equalsIgnoreCase(name)){
         setBackgroundColor(value);
      }else if(PTY_POSITION.equalsIgnoreCase(name)){
         setPosition(value);
      }else if(PTY_TYPE.equalsIgnoreCase(name)){
         setType(value);
      }else if(PTY_FONT_COLOR.equalsIgnoreCase(name)){
         setFontColor(value);
      }else if(PTY_FONT_SIZE.equalsIgnoreCase(name)){
         setFontSize(value);
      }else if(PTY_FONT_NAME.equalsIgnoreCase(name)){
         setFontName(value);
      }
   }
}
