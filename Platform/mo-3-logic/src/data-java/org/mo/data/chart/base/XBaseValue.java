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

//============================================================
// <T>数据值对象的XML节点基类。</T>
//============================================================
public abstract class XBaseValue
      extends FXmlObject
      implements
         XObjectFace
{
   // 名称定义
   public static final String NAME = "Value";

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

   // 有效性的名称定义
   public static final String PTY_IS_VALID = "is_valid";

   // 注释的名称定义
   public static final String PTY_NOTE = "note";

   // X轴的名称定义
   public static final String PTY_X = "X";

   // Y轴的名称定义
   public static final String PTY_Y = "Y";

   // 数值的名称定义
   public static final String PTY_VALUE = "value";

   // 颜色的名称定义
   public static final String PTY_COLOR = "color";

   // 的名称定义
   public static final String PTY_LINE_COLOR = "line_color";

   // 的名称定义
   public static final String PTY_BACKGROUND_COLOR = "background_color";

   // 名称的定义
   @AName("name")
   protected String _name;

   // 标签的定义
   @AName("label")
   protected FMultiString _label = new FMultiString();

   // 有效性的定义
   @AName("is_valid")
   protected Boolean _isValid = Boolean.FALSE;

   // 注释的定义
   @AName("note")
   protected FMultiString _note = new FMultiString();

   // X轴的定义
   @AName("x")
   protected String _x;

   // Y轴的定义
   @AName("y")
   protected String _y;

   // 数值的定义
   @AName("value")
   protected String _value;

   // 颜色的定义
   @AName("color")
   protected String _color;

   // 的定义
   @AName("line_color")
   protected String _lineColor;

   // 的定义
   @AName("background_color")
   protected String _backgroundColor;

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
   // <T>获得有效性的内容。</T>
   //
   // @return 有效性
   //============================================================
   public Boolean getIsValid(){
      return _isValid;
   }

   //============================================================
   // <T>设置有效性的内容。</T>
   //
   // @param value 有效性
   //============================================================
   public void setIsValid(Boolean value){
      _isValid = value;
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
   // <T>获得X轴的内容。</T>
   //
   // @return X轴
   //============================================================
   public String getX(){
      return _x;
   }

   //============================================================
   // <T>设置X轴的内容。</T>
   //
   // @param value X轴
   //============================================================
   public void setX(String value){
      _x = value;
   }

   //============================================================
   // <T>获得Y轴的内容。</T>
   //
   // @return Y轴
   //============================================================
   public String getY(){
      return _y;
   }

   //============================================================
   // <T>设置Y轴的内容。</T>
   //
   // @param value Y轴
   //============================================================
   public void setY(String value){
      _y = value;
   }

   //============================================================
   // <T>获得数值的内容。</T>
   //
   // @return 数值
   //============================================================
   public String getValue(){
      return _value;
   }

   //============================================================
   // <T>设置数值的内容。</T>
   //
   // @param value 数值
   //============================================================
   public void setValue(String value){
      _value = value;
   }

   //============================================================
   // <T>获得颜色的内容。</T>
   //
   // @return 颜色
   //============================================================
   public String getColor(){
      return _color;
   }

   //============================================================
   // <T>设置颜色的内容。</T>
   //
   // @param value 颜色
   //============================================================
   public void setColor(String value){
      _color = value;
   }

   //============================================================
   // <T>获得的内容。</T>
   //
   // @return 
   //============================================================
   public String getLineColor(){
      return _lineColor;
   }

   //============================================================
   // <T>设置的内容。</T>
   //
   // @param value 
   //============================================================
   public void setLineColor(String value){
      _lineColor = value;
   }

   //============================================================
   // <T>获得的内容。</T>
   //
   // @return 
   //============================================================
   public String getBackgroundColor(){
      return _backgroundColor;
   }

   //============================================================
   // <T>设置的内容。</T>
   //
   // @param value 
   //============================================================
   public void setBackgroundColor(String value){
      _backgroundColor = value;
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
      }else if(PTY_IS_VALID.equalsIgnoreCase(name)){
         return RBoolean.toString(getIsValid());
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         return getNote();
      }else if(PTY_X.equalsIgnoreCase(name)){
         return getX();
      }else if(PTY_Y.equalsIgnoreCase(name)){
         return getY();
      }else if(PTY_VALUE.equalsIgnoreCase(name)){
         return getValue();
      }else if(PTY_COLOR.equalsIgnoreCase(name)){
         return getColor();
      }else if(PTY_LINE_COLOR.equalsIgnoreCase(name)){
         return getLineColor();
      }else if(PTY_BACKGROUND_COLOR.equalsIgnoreCase(name)){
         return getBackgroundColor();
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
      }else if(PTY_IS_VALID.equalsIgnoreCase(name)){
         setIsValid(RBoolean.parse(value));
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         setNote(value);
      }else if(PTY_X.equalsIgnoreCase(name)){
         setX(value);
      }else if(PTY_Y.equalsIgnoreCase(name)){
         setY(value);
      }else if(PTY_VALUE.equalsIgnoreCase(name)){
         setValue(value);
      }else if(PTY_COLOR.equalsIgnoreCase(name)){
         setColor(value);
      }else if(PTY_LINE_COLOR.equalsIgnoreCase(name)){
         setLineColor(value);
      }else if(PTY_BACKGROUND_COLOR.equalsIgnoreCase(name)){
         setBackgroundColor(value);
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
         if(config.contains("is_valid")){
            setIsValid(RBoolean.parse(config.get(PTY_IS_VALID)));
         }
         if(config.contains("note")){
            _note.unpack(config.get(PTY_NOTE));
         }
         if(config.contains("x")){
            setX(config.get(PTY_X));
         }
         if(config.contains("y")){
            setY(config.get(PTY_Y));
         }
         if(config.contains("value")){
            setValue(config.get(PTY_VALUE));
         }
         if(config.contains("color")){
            setColor(config.get(PTY_COLOR));
         }
         if(config.contains("line_color")){
            setLineColor(config.get(PTY_LINE_COLOR));
         }
         if(config.contains("background_color")){
            setBackgroundColor(config.get(PTY_BACKGROUND_COLOR));
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
         if(config.contains("x")){
            setX(config.get(PTY_X));
         }
         if(config.contains("y")){
            setY(config.get(PTY_Y));
         }
         if(config.contains("value")){
            setValue(config.get(PTY_VALUE));
         }
         if(config.contains("color")){
            setColor(config.get(PTY_COLOR));
         }
         if(config.contains("line_color")){
            setLineColor(config.get(PTY_LINE_COLOR));
         }
         if(config.contains("background_color")){
            setBackgroundColor(config.get(PTY_BACKGROUND_COLOR));
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
         if(config.contains("x")){
            setX(config.get(PTY_X));
         }
         if(config.contains("y")){
            setY(config.get(PTY_Y));
         }
         if(config.contains("value")){
            setValue(config.get(PTY_VALUE));
         }
         if(config.contains("color")){
            setColor(config.get(PTY_COLOR));
         }
         if(config.contains("line_color")){
            setLineColor(config.get(PTY_LINE_COLOR));
         }
         if(config.contains("background_color")){
            setBackgroundColor(config.get(PTY_BACKGROUND_COLOR));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getX())){
            if(config.contains("x")){
               setX(config.get(PTY_X));
            }
         }
         if(RString.isEmpty(getY())){
            if(config.contains("y")){
               setY(config.get(PTY_Y));
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
         if(RBoolean.parse(getIsValid())){
            config.set(PTY_IS_VALID, RBoolean.toString(getIsValid()));
         }
         String note = _note.pack().toString();
         if(RString.isNotEmpty(note)){
            config.set(PTY_NOTE, note);
         }
         if(RString.isNotEmpty(getX())){
            config.set(PTY_X, getX());
         }
         if(RString.isNotEmpty(getY())){
            config.set(PTY_Y, getY());
         }
         if(RString.isNotEmpty(getValue())){
            config.set(PTY_VALUE, getValue());
         }
         if(RString.isNotEmpty(getColor())){
            config.set(PTY_COLOR, getColor());
         }
         if(RString.isNotEmpty(getLineColor())){
            config.set(PTY_LINE_COLOR, getLineColor());
         }
         if(RString.isNotEmpty(getBackgroundColor())){
            config.set(PTY_BACKGROUND_COLOR, getBackgroundColor());
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
         if(RString.isNotEmpty(getX())){
            config.set(PTY_X, getX());
         }
         if(RString.isNotEmpty(getY())){
            config.set(PTY_Y, getY());
         }
         if(RString.isNotEmpty(getValue())){
            config.set(PTY_VALUE, getValue());
         }
         if(RString.isNotEmpty(getColor())){
            config.set(PTY_COLOR, getColor());
         }
         if(RString.isNotEmpty(getLineColor())){
            config.set(PTY_LINE_COLOR, getLineColor());
         }
         if(RString.isNotEmpty(getBackgroundColor())){
            config.set(PTY_BACKGROUND_COLOR, getBackgroundColor());
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
         String sX = getX();
         if(RString.isNotEmpty(sX)){
            config.set(PTY_X, sX);
         }
         String sY = getY();
         if(RString.isNotEmpty(sY)){
            config.set(PTY_Y, sY);
         }
         String sValue = getValue();
         if(RString.isNotEmpty(sValue)){
            config.set(PTY_VALUE, sValue);
         }
         String sColor = getColor();
         if(RString.isNotEmpty(sColor)){
            config.set(PTY_COLOR, sColor);
         }
         String sLineColor = getLineColor();
         if(RString.isNotEmpty(sLineColor)){
            config.set(PTY_LINE_COLOR, sLineColor);
         }
         String sBackgroundColor = getBackgroundColor();
         if(RString.isNotEmpty(sBackgroundColor)){
            config.set(PTY_BACKGROUND_COLOR, sBackgroundColor);
         }
      }else if(EXmlConfig.Default == type){
         String sX = getX();
         if(RString.isNotEmpty(sX)){
            config.set(PTY_X, sX);
         }
         String sY = getY();
         if(RString.isNotEmpty(sY)){
            config.set(PTY_Y, sY);
         }
      }
   }
}