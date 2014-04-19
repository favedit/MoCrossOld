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
import org.mo.data.chart.common.XPadFace;
import org.mo.data.chart.common.XMarginFace;

//============================================================
// <T>注脚对象的XML节点基类。</T>
//============================================================
public abstract class XBaseFooter
      extends FXmlObject
      implements
         XObjectFace,
         XPadFace,
         XMarginFace
{
   // 名称定义
   public static final String NAME = "Footer";

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

   // 左空余的名称定义
   public static final String PTY_PAD_LEFT = "pad_left";

   // 右空余的名称定义
   public static final String PTY_PAD_RIGHT = "pad_right";

   // 上空余的名称定义
   public static final String PTY_PAD_TOP = "pad_top";

   // 下空余的名称定义
   public static final String PTY_PAD_BOTTOM = "pad_bottom";

   // 左边距的名称定义
   public static final String PTY_MARGIN_LEFT = "margin_left";

   // 右边距的名称定义
   public static final String PTY_MARGIN_RIGHT = "margin_right";

   // 上边距的名称定义
   public static final String PTY_MARGIN_TOP = "margin_top";

   // 底边距的名称定义
   public static final String PTY_MARGIN_BOTTOM = "margin_bottom";

   // 线条颜色的名称定义
   public static final String PTY_LINE_COLOR = "line_color";

   // 背景颜色的名称定义
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

   // 左空余的定义
   @AName("pad_left")
   protected String _padLeft;

   // 右空余的定义
   @AName("pad_right")
   protected String _padRight;

   // 上空余的定义
   @AName("pad_top")
   protected String _padTop;

   // 下空余的定义
   @AName("pad_bottom")
   protected String _padBottom;

   // 左边距的定义
   @AName("margin_left")
   protected String _marginLeft;

   // 右边距的定义
   @AName("margin_right")
   protected String _marginRight;

   // 上边距的定义
   @AName("margin_top")
   protected String _marginTop;

   // 底边距的定义
   @AName("margin_bottom")
   protected String _marginBottom;

   // 线条颜色的定义
   @AName("line_color")
   protected String _lineColor;

   // 背景颜色的定义
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
   // <T>获得左空余的内容。</T>
   //
   // @return 左空余
   //============================================================
   public String getPadLeft(){
      return _padLeft;
   }

   //============================================================
   // <T>设置左空余的内容。</T>
   //
   // @param value 左空余
   //============================================================
   public void setPadLeft(String value){
      _padLeft = value;
   }

   //============================================================
   // <T>获得右空余的内容。</T>
   //
   // @return 右空余
   //============================================================
   public String getPadRight(){
      return _padRight;
   }

   //============================================================
   // <T>设置右空余的内容。</T>
   //
   // @param value 右空余
   //============================================================
   public void setPadRight(String value){
      _padRight = value;
   }

   //============================================================
   // <T>获得上空余的内容。</T>
   //
   // @return 上空余
   //============================================================
   public String getPadTop(){
      return _padTop;
   }

   //============================================================
   // <T>设置上空余的内容。</T>
   //
   // @param value 上空余
   //============================================================
   public void setPadTop(String value){
      _padTop = value;
   }

   //============================================================
   // <T>获得下空余的内容。</T>
   //
   // @return 下空余
   //============================================================
   public String getPadBottom(){
      return _padBottom;
   }

   //============================================================
   // <T>设置下空余的内容。</T>
   //
   // @param value 下空余
   //============================================================
   public void setPadBottom(String value){
      _padBottom = value;
   }

   //============================================================
   // <T>获得左边距的内容。</T>
   //
   // @return 左边距
   //============================================================
   public String getMarginLeft(){
      return _marginLeft;
   }

   //============================================================
   // <T>设置左边距的内容。</T>
   //
   // @param value 左边距
   //============================================================
   public void setMarginLeft(String value){
      _marginLeft = value;
   }

   //============================================================
   // <T>获得右边距的内容。</T>
   //
   // @return 右边距
   //============================================================
   public String getMarginRight(){
      return _marginRight;
   }

   //============================================================
   // <T>设置右边距的内容。</T>
   //
   // @param value 右边距
   //============================================================
   public void setMarginRight(String value){
      _marginRight = value;
   }

   //============================================================
   // <T>获得上边距的内容。</T>
   //
   // @return 上边距
   //============================================================
   public String getMarginTop(){
      return _marginTop;
   }

   //============================================================
   // <T>设置上边距的内容。</T>
   //
   // @param value 上边距
   //============================================================
   public void setMarginTop(String value){
      _marginTop = value;
   }

   //============================================================
   // <T>获得底边距的内容。</T>
   //
   // @return 底边距
   //============================================================
   public String getMarginBottom(){
      return _marginBottom;
   }

   //============================================================
   // <T>设置底边距的内容。</T>
   //
   // @param value 底边距
   //============================================================
   public void setMarginBottom(String value){
      _marginBottom = value;
   }

   //============================================================
   // <T>获得线条颜色的内容。</T>
   //
   // @return 线条颜色
   //============================================================
   public String getLineColor(){
      return _lineColor;
   }

   //============================================================
   // <T>设置线条颜色的内容。</T>
   //
   // @param value 线条颜色
   //============================================================
   public void setLineColor(String value){
      _lineColor = value;
   }

   //============================================================
   // <T>获得背景颜色的内容。</T>
   //
   // @return 背景颜色
   //============================================================
   public String getBackgroundColor(){
      return _backgroundColor;
   }

   //============================================================
   // <T>设置背景颜色的内容。</T>
   //
   // @param value 背景颜色
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
      }else if(PTY_PAD_LEFT.equalsIgnoreCase(name)){
         return getPadLeft();
      }else if(PTY_PAD_RIGHT.equalsIgnoreCase(name)){
         return getPadRight();
      }else if(PTY_PAD_TOP.equalsIgnoreCase(name)){
         return getPadTop();
      }else if(PTY_PAD_BOTTOM.equalsIgnoreCase(name)){
         return getPadBottom();
      }else if(PTY_MARGIN_LEFT.equalsIgnoreCase(name)){
         return getMarginLeft();
      }else if(PTY_MARGIN_RIGHT.equalsIgnoreCase(name)){
         return getMarginRight();
      }else if(PTY_MARGIN_TOP.equalsIgnoreCase(name)){
         return getMarginTop();
      }else if(PTY_MARGIN_BOTTOM.equalsIgnoreCase(name)){
         return getMarginBottom();
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
      }else if(PTY_PAD_LEFT.equalsIgnoreCase(name)){
         setPadLeft(value);
      }else if(PTY_PAD_RIGHT.equalsIgnoreCase(name)){
         setPadRight(value);
      }else if(PTY_PAD_TOP.equalsIgnoreCase(name)){
         setPadTop(value);
      }else if(PTY_PAD_BOTTOM.equalsIgnoreCase(name)){
         setPadBottom(value);
      }else if(PTY_MARGIN_LEFT.equalsIgnoreCase(name)){
         setMarginLeft(value);
      }else if(PTY_MARGIN_RIGHT.equalsIgnoreCase(name)){
         setMarginRight(value);
      }else if(PTY_MARGIN_TOP.equalsIgnoreCase(name)){
         setMarginTop(value);
      }else if(PTY_MARGIN_BOTTOM.equalsIgnoreCase(name)){
         setMarginBottom(value);
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
         if(config.contains("pad_left")){
            setPadLeft(config.get(PTY_PAD_LEFT));
         }
         if(config.contains("pad_right")){
            setPadRight(config.get(PTY_PAD_RIGHT));
         }
         if(config.contains("pad_top")){
            setPadTop(config.get(PTY_PAD_TOP));
         }
         if(config.contains("pad_bottom")){
            setPadBottom(config.get(PTY_PAD_BOTTOM));
         }
         if(config.contains("margin_left")){
            setMarginLeft(config.get(PTY_MARGIN_LEFT));
         }
         if(config.contains("margin_right")){
            setMarginRight(config.get(PTY_MARGIN_RIGHT));
         }
         if(config.contains("margin_top")){
            setMarginTop(config.get(PTY_MARGIN_TOP));
         }
         if(config.contains("margin_bottom")){
            setMarginBottom(config.get(PTY_MARGIN_BOTTOM));
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
         if(config.contains("pad_left")){
            setPadLeft(config.get(PTY_PAD_LEFT));
         }
         if(config.contains("pad_right")){
            setPadRight(config.get(PTY_PAD_RIGHT));
         }
         if(config.contains("pad_top")){
            setPadTop(config.get(PTY_PAD_TOP));
         }
         if(config.contains("pad_bottom")){
            setPadBottom(config.get(PTY_PAD_BOTTOM));
         }
         if(config.contains("margin_left")){
            setMarginLeft(config.get(PTY_MARGIN_LEFT));
         }
         if(config.contains("margin_right")){
            setMarginRight(config.get(PTY_MARGIN_RIGHT));
         }
         if(config.contains("margin_top")){
            setMarginTop(config.get(PTY_MARGIN_TOP));
         }
         if(config.contains("margin_bottom")){
            setMarginBottom(config.get(PTY_MARGIN_BOTTOM));
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
         if(config.contains("pad_left")){
            setPadLeft(config.get(PTY_PAD_LEFT));
         }
         if(config.contains("pad_right")){
            setPadRight(config.get(PTY_PAD_RIGHT));
         }
         if(config.contains("pad_top")){
            setPadTop(config.get(PTY_PAD_TOP));
         }
         if(config.contains("pad_bottom")){
            setPadBottom(config.get(PTY_PAD_BOTTOM));
         }
         if(config.contains("margin_left")){
            setMarginLeft(config.get(PTY_MARGIN_LEFT));
         }
         if(config.contains("margin_right")){
            setMarginRight(config.get(PTY_MARGIN_RIGHT));
         }
         if(config.contains("margin_top")){
            setMarginTop(config.get(PTY_MARGIN_TOP));
         }
         if(config.contains("margin_bottom")){
            setMarginBottom(config.get(PTY_MARGIN_BOTTOM));
         }
         if(config.contains("line_color")){
            setLineColor(config.get(PTY_LINE_COLOR));
         }
         if(config.contains("background_color")){
            setBackgroundColor(config.get(PTY_BACKGROUND_COLOR));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getPadLeft())){
            if(config.contains("pad_left")){
               setPadLeft(config.get(PTY_PAD_LEFT));
            }
         }
         if(RString.isEmpty(getPadRight())){
            if(config.contains("pad_right")){
               setPadRight(config.get(PTY_PAD_RIGHT));
            }
         }
         if(RString.isEmpty(getPadTop())){
            if(config.contains("pad_top")){
               setPadTop(config.get(PTY_PAD_TOP));
            }
         }
         if(RString.isEmpty(getPadBottom())){
            if(config.contains("pad_bottom")){
               setPadBottom(config.get(PTY_PAD_BOTTOM));
            }
         }
         if(RString.isEmpty(getMarginLeft())){
            if(config.contains("margin_left")){
               setMarginLeft(config.get(PTY_MARGIN_LEFT));
            }
         }
         if(RString.isEmpty(getMarginRight())){
            if(config.contains("margin_right")){
               setMarginRight(config.get(PTY_MARGIN_RIGHT));
            }
         }
         if(RString.isEmpty(getMarginTop())){
            if(config.contains("margin_top")){
               setMarginTop(config.get(PTY_MARGIN_TOP));
            }
         }
         if(RString.isEmpty(getMarginBottom())){
            if(config.contains("margin_bottom")){
               setMarginBottom(config.get(PTY_MARGIN_BOTTOM));
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
         if(RString.isNotEmpty(getPadLeft())){
            config.set(PTY_PAD_LEFT, getPadLeft());
         }
         if(RString.isNotEmpty(getPadRight())){
            config.set(PTY_PAD_RIGHT, getPadRight());
         }
         if(RString.isNotEmpty(getPadTop())){
            config.set(PTY_PAD_TOP, getPadTop());
         }
         if(RString.isNotEmpty(getPadBottom())){
            config.set(PTY_PAD_BOTTOM, getPadBottom());
         }
         if(RString.isNotEmpty(getMarginLeft())){
            config.set(PTY_MARGIN_LEFT, getMarginLeft());
         }
         if(RString.isNotEmpty(getMarginRight())){
            config.set(PTY_MARGIN_RIGHT, getMarginRight());
         }
         if(RString.isNotEmpty(getMarginTop())){
            config.set(PTY_MARGIN_TOP, getMarginTop());
         }
         if(RString.isNotEmpty(getMarginBottom())){
            config.set(PTY_MARGIN_BOTTOM, getMarginBottom());
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
         if(RString.isNotEmpty(getPadLeft())){
            config.set(PTY_PAD_LEFT, getPadLeft());
         }
         if(RString.isNotEmpty(getPadRight())){
            config.set(PTY_PAD_RIGHT, getPadRight());
         }
         if(RString.isNotEmpty(getPadTop())){
            config.set(PTY_PAD_TOP, getPadTop());
         }
         if(RString.isNotEmpty(getPadBottom())){
            config.set(PTY_PAD_BOTTOM, getPadBottom());
         }
         if(RString.isNotEmpty(getMarginLeft())){
            config.set(PTY_MARGIN_LEFT, getMarginLeft());
         }
         if(RString.isNotEmpty(getMarginRight())){
            config.set(PTY_MARGIN_RIGHT, getMarginRight());
         }
         if(RString.isNotEmpty(getMarginTop())){
            config.set(PTY_MARGIN_TOP, getMarginTop());
         }
         if(RString.isNotEmpty(getMarginBottom())){
            config.set(PTY_MARGIN_BOTTOM, getMarginBottom());
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
         String sPadLeft = getPadLeft();
         if(RString.isNotEmpty(sPadLeft)){
            config.set(PTY_PAD_LEFT, sPadLeft);
         }
         String sPadRight = getPadRight();
         if(RString.isNotEmpty(sPadRight)){
            config.set(PTY_PAD_RIGHT, sPadRight);
         }
         String sPadTop = getPadTop();
         if(RString.isNotEmpty(sPadTop)){
            config.set(PTY_PAD_TOP, sPadTop);
         }
         String sPadBottom = getPadBottom();
         if(RString.isNotEmpty(sPadBottom)){
            config.set(PTY_PAD_BOTTOM, sPadBottom);
         }
         String sMarginLeft = getMarginLeft();
         if(RString.isNotEmpty(sMarginLeft)){
            config.set(PTY_MARGIN_LEFT, sMarginLeft);
         }
         String sMarginRight = getMarginRight();
         if(RString.isNotEmpty(sMarginRight)){
            config.set(PTY_MARGIN_RIGHT, sMarginRight);
         }
         String sMarginTop = getMarginTop();
         if(RString.isNotEmpty(sMarginTop)){
            config.set(PTY_MARGIN_TOP, sMarginTop);
         }
         String sMarginBottom = getMarginBottom();
         if(RString.isNotEmpty(sMarginBottom)){
            config.set(PTY_MARGIN_BOTTOM, sMarginBottom);
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
         String sPadLeft = getPadLeft();
         if(RString.isNotEmpty(sPadLeft)){
            config.set(PTY_PAD_LEFT, sPadLeft);
         }
         String sPadRight = getPadRight();
         if(RString.isNotEmpty(sPadRight)){
            config.set(PTY_PAD_RIGHT, sPadRight);
         }
         String sPadTop = getPadTop();
         if(RString.isNotEmpty(sPadTop)){
            config.set(PTY_PAD_TOP, sPadTop);
         }
         String sPadBottom = getPadBottom();
         if(RString.isNotEmpty(sPadBottom)){
            config.set(PTY_PAD_BOTTOM, sPadBottom);
         }
         String sMarginLeft = getMarginLeft();
         if(RString.isNotEmpty(sMarginLeft)){
            config.set(PTY_MARGIN_LEFT, sMarginLeft);
         }
         String sMarginRight = getMarginRight();
         if(RString.isNotEmpty(sMarginRight)){
            config.set(PTY_MARGIN_RIGHT, sMarginRight);
         }
         String sMarginTop = getMarginTop();
         if(RString.isNotEmpty(sMarginTop)){
            config.set(PTY_MARGIN_TOP, sMarginTop);
         }
         String sMarginBottom = getMarginBottom();
         if(RString.isNotEmpty(sMarginBottom)){
            config.set(PTY_MARGIN_BOTTOM, sMarginBottom);
         }
      }
   }
}