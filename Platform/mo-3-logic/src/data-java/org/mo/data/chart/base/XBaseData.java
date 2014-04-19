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
// <T>数据对象的XML节点基类。</T>
//============================================================
public abstract class XBaseData
      extends FXmlObject
      implements
         XObjectFace
{
   // 名称定义
   public static final String NAME = "Data";

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

   // 数据名称的名称定义
   public static final String PTY_DATA_NAME = "data_name";

   // 颜色的名称定义
   public static final String PTY_COLOR = "color";

   // 激活颜色的名称定义
   public static final String PTY_HOVER_COLOR = "hover_color";

   // 激活背景色的名称定义
   public static final String PTY_HOVER_BACKGROUND_COLOR = "hover_background_color";

   // 线条颜色的名称定义
   public static final String PTY_LINE_COLOR = "line_color";

   // 线条宽度的名称定义
   public static final String PTY_LINE_WIDTH = "line_width";

   // 节点宽度的名称定义
   public static final String PTY_POINT_WIDTH = "point_width";

   // 节点颜色的名称定义
   public static final String PTY_POINT_COLOR = "point_color";

   // 节点背景色的名称定义
   public static final String PTY_POINT_BACKGROUND_COLOR = "point_background_color";

   // 节点半径的名称定义
   public static final String PTY_POINT_RADIUS = "point_radius";

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

   // 数据名称的定义
   @AName("data_name")
   protected String _dataName;

   // 颜色的定义
   @AName("color")
   protected String _color;

   // 激活颜色的定义
   @AName("hover_color")
   protected String _hoverColor;

   // 激活背景色的定义
   @AName("hover_background_color")
   protected String _hoverBackgroundColor;

   // 线条颜色的定义
   @AName("line_color")
   protected String _lineColor;

   // 线条宽度的定义
   @AName("line_width")
   protected String _lineWidth;

   // 节点宽度的定义
   @AName("point_width")
   protected String _pointWidth;

   // 节点颜色的定义
   @AName("point_color")
   protected String _pointColor;

   // 节点背景色的定义
   @AName("point_background_color")
   protected String _pointBackgroundColor;

   // 节点半径的定义
   @AName("point_radius")
   protected String _pointRadius;

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
   // <T>获得数据名称的内容。</T>
   //
   // @return 数据名称
   //============================================================
   public String getDataName(){
      return _dataName;
   }

   //============================================================
   // <T>设置数据名称的内容。</T>
   //
   // @param value 数据名称
   //============================================================
   public void setDataName(String value){
      _dataName = value;
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
   // <T>获得激活颜色的内容。</T>
   //
   // @return 激活颜色
   //============================================================
   public String getHoverColor(){
      return _hoverColor;
   }

   //============================================================
   // <T>设置激活颜色的内容。</T>
   //
   // @param value 激活颜色
   //============================================================
   public void setHoverColor(String value){
      _hoverColor = value;
   }

   //============================================================
   // <T>获得激活背景色的内容。</T>
   //
   // @return 激活背景色
   //============================================================
   public String getHoverBackgroundColor(){
      return _hoverBackgroundColor;
   }

   //============================================================
   // <T>设置激活背景色的内容。</T>
   //
   // @param value 激活背景色
   //============================================================
   public void setHoverBackgroundColor(String value){
      _hoverBackgroundColor = value;
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
   // <T>获得线条宽度的内容。</T>
   //
   // @return 线条宽度
   //============================================================
   public String getLineWidth(){
      return _lineWidth;
   }

   //============================================================
   // <T>设置线条宽度的内容。</T>
   //
   // @param value 线条宽度
   //============================================================
   public void setLineWidth(String value){
      _lineWidth = value;
   }

   //============================================================
   // <T>获得节点宽度的内容。</T>
   //
   // @return 节点宽度
   //============================================================
   public String getPointWidth(){
      return _pointWidth;
   }

   //============================================================
   // <T>设置节点宽度的内容。</T>
   //
   // @param value 节点宽度
   //============================================================
   public void setPointWidth(String value){
      _pointWidth = value;
   }

   //============================================================
   // <T>获得节点颜色的内容。</T>
   //
   // @return 节点颜色
   //============================================================
   public String getPointColor(){
      return _pointColor;
   }

   //============================================================
   // <T>设置节点颜色的内容。</T>
   //
   // @param value 节点颜色
   //============================================================
   public void setPointColor(String value){
      _pointColor = value;
   }

   //============================================================
   // <T>获得节点背景色的内容。</T>
   //
   // @return 节点背景色
   //============================================================
   public String getPointBackgroundColor(){
      return _pointBackgroundColor;
   }

   //============================================================
   // <T>设置节点背景色的内容。</T>
   //
   // @param value 节点背景色
   //============================================================
   public void setPointBackgroundColor(String value){
      _pointBackgroundColor = value;
   }

   //============================================================
   // <T>获得节点半径的内容。</T>
   //
   // @return 节点半径
   //============================================================
   public String getPointRadius(){
      return _pointRadius;
   }

   //============================================================
   // <T>设置节点半径的内容。</T>
   //
   // @param value 节点半径
   //============================================================
   public void setPointRadius(String value){
      _pointRadius = value;
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
      }else if(PTY_DATA_NAME.equalsIgnoreCase(name)){
         return getDataName();
      }else if(PTY_COLOR.equalsIgnoreCase(name)){
         return getColor();
      }else if(PTY_HOVER_COLOR.equalsIgnoreCase(name)){
         return getHoverColor();
      }else if(PTY_HOVER_BACKGROUND_COLOR.equalsIgnoreCase(name)){
         return getHoverBackgroundColor();
      }else if(PTY_LINE_COLOR.equalsIgnoreCase(name)){
         return getLineColor();
      }else if(PTY_LINE_WIDTH.equalsIgnoreCase(name)){
         return getLineWidth();
      }else if(PTY_POINT_WIDTH.equalsIgnoreCase(name)){
         return getPointWidth();
      }else if(PTY_POINT_COLOR.equalsIgnoreCase(name)){
         return getPointColor();
      }else if(PTY_POINT_BACKGROUND_COLOR.equalsIgnoreCase(name)){
         return getPointBackgroundColor();
      }else if(PTY_POINT_RADIUS.equalsIgnoreCase(name)){
         return getPointRadius();
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
      }else if(PTY_DATA_NAME.equalsIgnoreCase(name)){
         setDataName(value);
      }else if(PTY_COLOR.equalsIgnoreCase(name)){
         setColor(value);
      }else if(PTY_HOVER_COLOR.equalsIgnoreCase(name)){
         setHoverColor(value);
      }else if(PTY_HOVER_BACKGROUND_COLOR.equalsIgnoreCase(name)){
         setHoverBackgroundColor(value);
      }else if(PTY_LINE_COLOR.equalsIgnoreCase(name)){
         setLineColor(value);
      }else if(PTY_LINE_WIDTH.equalsIgnoreCase(name)){
         setLineWidth(value);
      }else if(PTY_POINT_WIDTH.equalsIgnoreCase(name)){
         setPointWidth(value);
      }else if(PTY_POINT_COLOR.equalsIgnoreCase(name)){
         setPointColor(value);
      }else if(PTY_POINT_BACKGROUND_COLOR.equalsIgnoreCase(name)){
         setPointBackgroundColor(value);
      }else if(PTY_POINT_RADIUS.equalsIgnoreCase(name)){
         setPointRadius(value);
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
         if(config.contains("data_name")){
            setDataName(config.get(PTY_DATA_NAME));
         }
         if(config.contains("color")){
            setColor(config.get(PTY_COLOR));
         }
         if(config.contains("hover_color")){
            setHoverColor(config.get(PTY_HOVER_COLOR));
         }
         if(config.contains("hover_background_color")){
            setHoverBackgroundColor(config.get(PTY_HOVER_BACKGROUND_COLOR));
         }
         if(config.contains("line_color")){
            setLineColor(config.get(PTY_LINE_COLOR));
         }
         if(config.contains("line_width")){
            setLineWidth(config.get(PTY_LINE_WIDTH));
         }
         if(config.contains("point_width")){
            setPointWidth(config.get(PTY_POINT_WIDTH));
         }
         if(config.contains("point_color")){
            setPointColor(config.get(PTY_POINT_COLOR));
         }
         if(config.contains("point_background_color")){
            setPointBackgroundColor(config.get(PTY_POINT_BACKGROUND_COLOR));
         }
         if(config.contains("point_radius")){
            setPointRadius(config.get(PTY_POINT_RADIUS));
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
         if(config.contains("data_name")){
            setDataName(config.get(PTY_DATA_NAME));
         }
         if(config.contains("color")){
            setColor(config.get(PTY_COLOR));
         }
         if(config.contains("hover_color")){
            setHoverColor(config.get(PTY_HOVER_COLOR));
         }
         if(config.contains("hover_background_color")){
            setHoverBackgroundColor(config.get(PTY_HOVER_BACKGROUND_COLOR));
         }
         if(config.contains("line_color")){
            setLineColor(config.get(PTY_LINE_COLOR));
         }
         if(config.contains("line_width")){
            setLineWidth(config.get(PTY_LINE_WIDTH));
         }
         if(config.contains("point_width")){
            setPointWidth(config.get(PTY_POINT_WIDTH));
         }
         if(config.contains("point_color")){
            setPointColor(config.get(PTY_POINT_COLOR));
         }
         if(config.contains("point_background_color")){
            setPointBackgroundColor(config.get(PTY_POINT_BACKGROUND_COLOR));
         }
         if(config.contains("point_radius")){
            setPointRadius(config.get(PTY_POINT_RADIUS));
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
         if(config.contains("data_name")){
            setDataName(config.get(PTY_DATA_NAME));
         }
         if(config.contains("color")){
            setColor(config.get(PTY_COLOR));
         }
         if(config.contains("hover_color")){
            setHoverColor(config.get(PTY_HOVER_COLOR));
         }
         if(config.contains("hover_background_color")){
            setHoverBackgroundColor(config.get(PTY_HOVER_BACKGROUND_COLOR));
         }
         if(config.contains("line_color")){
            setLineColor(config.get(PTY_LINE_COLOR));
         }
         if(config.contains("line_width")){
            setLineWidth(config.get(PTY_LINE_WIDTH));
         }
         if(config.contains("point_width")){
            setPointWidth(config.get(PTY_POINT_WIDTH));
         }
         if(config.contains("point_color")){
            setPointColor(config.get(PTY_POINT_COLOR));
         }
         if(config.contains("point_background_color")){
            setPointBackgroundColor(config.get(PTY_POINT_BACKGROUND_COLOR));
         }
         if(config.contains("point_radius")){
            setPointRadius(config.get(PTY_POINT_RADIUS));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getColor())){
            if(config.contains("color")){
               setColor(config.get(PTY_COLOR));
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
         if(RString.isNotEmpty(getDataName())){
            config.set(PTY_DATA_NAME, getDataName());
         }
         if(RString.isNotEmpty(getColor())){
            config.set(PTY_COLOR, getColor());
         }
         if(RString.isNotEmpty(getHoverColor())){
            config.set(PTY_HOVER_COLOR, getHoverColor());
         }
         if(RString.isNotEmpty(getHoverBackgroundColor())){
            config.set(PTY_HOVER_BACKGROUND_COLOR, getHoverBackgroundColor());
         }
         if(RString.isNotEmpty(getLineColor())){
            config.set(PTY_LINE_COLOR, getLineColor());
         }
         if(RString.isNotEmpty(getLineWidth())){
            config.set(PTY_LINE_WIDTH, getLineWidth());
         }
         if(RString.isNotEmpty(getPointWidth())){
            config.set(PTY_POINT_WIDTH, getPointWidth());
         }
         if(RString.isNotEmpty(getPointColor())){
            config.set(PTY_POINT_COLOR, getPointColor());
         }
         if(RString.isNotEmpty(getPointBackgroundColor())){
            config.set(PTY_POINT_BACKGROUND_COLOR, getPointBackgroundColor());
         }
         if(RString.isNotEmpty(getPointRadius())){
            config.set(PTY_POINT_RADIUS, getPointRadius());
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
         if(RString.isNotEmpty(getDataName())){
            config.set(PTY_DATA_NAME, getDataName());
         }
         if(RString.isNotEmpty(getColor())){
            config.set(PTY_COLOR, getColor());
         }
         if(RString.isNotEmpty(getHoverColor())){
            config.set(PTY_HOVER_COLOR, getHoverColor());
         }
         if(RString.isNotEmpty(getHoverBackgroundColor())){
            config.set(PTY_HOVER_BACKGROUND_COLOR, getHoverBackgroundColor());
         }
         if(RString.isNotEmpty(getLineColor())){
            config.set(PTY_LINE_COLOR, getLineColor());
         }
         if(RString.isNotEmpty(getLineWidth())){
            config.set(PTY_LINE_WIDTH, getLineWidth());
         }
         if(RString.isNotEmpty(getPointWidth())){
            config.set(PTY_POINT_WIDTH, getPointWidth());
         }
         if(RString.isNotEmpty(getPointColor())){
            config.set(PTY_POINT_COLOR, getPointColor());
         }
         if(RString.isNotEmpty(getPointBackgroundColor())){
            config.set(PTY_POINT_BACKGROUND_COLOR, getPointBackgroundColor());
         }
         if(RString.isNotEmpty(getPointRadius())){
            config.set(PTY_POINT_RADIUS, getPointRadius());
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
         String sDataName = getDataName();
         if(RString.isNotEmpty(sDataName)){
            config.set(PTY_DATA_NAME, sDataName);
         }
         String sColor = getColor();
         if(RString.isNotEmpty(sColor)){
            config.set(PTY_COLOR, sColor);
         }
         String sHoverColor = getHoverColor();
         if(RString.isNotEmpty(sHoverColor)){
            config.set(PTY_HOVER_COLOR, sHoverColor);
         }
         String sHoverBackgroundColor = getHoverBackgroundColor();
         if(RString.isNotEmpty(sHoverBackgroundColor)){
            config.set(PTY_HOVER_BACKGROUND_COLOR, sHoverBackgroundColor);
         }
         String sLineColor = getLineColor();
         if(RString.isNotEmpty(sLineColor)){
            config.set(PTY_LINE_COLOR, sLineColor);
         }
         String sLineWidth = getLineWidth();
         if(RString.isNotEmpty(sLineWidth)){
            config.set(PTY_LINE_WIDTH, sLineWidth);
         }
         String sPointWidth = getPointWidth();
         if(RString.isNotEmpty(sPointWidth)){
            config.set(PTY_POINT_WIDTH, sPointWidth);
         }
         String sPointColor = getPointColor();
         if(RString.isNotEmpty(sPointColor)){
            config.set(PTY_POINT_COLOR, sPointColor);
         }
         String sPointBackgroundColor = getPointBackgroundColor();
         if(RString.isNotEmpty(sPointBackgroundColor)){
            config.set(PTY_POINT_BACKGROUND_COLOR, sPointBackgroundColor);
         }
         String sPointRadius = getPointRadius();
         if(RString.isNotEmpty(sPointRadius)){
            config.set(PTY_POINT_RADIUS, sPointRadius);
         }
      }else if(EXmlConfig.Default == type){
         String sColor = getColor();
         if(RString.isNotEmpty(sColor)){
            config.set(PTY_COLOR, sColor);
         }
      }
   }
}