package org.mo.web.core.webtheme.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.web.core.webtheme.common.XObjectFace;
import org.mo.web.core.webtheme.common.XStyleFace;

//============================================================
// <T>控件对象的XML节点基类。</T>
//============================================================
public abstract class XBaseControl
      extends FXmlObject
      implements
         XObjectFace,
         XStyleFace
{
   // 名称定义
   public static final String NAME = "Control";

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

   // 父主题的名称定义
   public static final String PTY_PARENT_STYLE = "parent_style";

   // 注释信息的名称定义
   public static final String PTY_NOTE = "note";

   // 颜色的名称定义
   public static final String PTY_COLOR = "color";

   // 背景色的名称定义
   public static final String PTY_BACKGROUND_COLOR = "background_color";

   // 背景图片的名称定义
   public static final String PTY_BACKGROUND_IMAGE = "background_image";

   // 背景图片位置的名称定义
   public static final String PTY_BACKGROUND_POSITION = "background_position";

   // 背景图片位置X的名称定义
   public static final String PTY_BACKGROUND_POSITION_X = "background_position_x";

   // 背景图片位置Y的名称定义
   public static final String PTY_BACKGROUND_POSITION_Y = "background_position_y";

   // 背景图片重复的名称定义
   public static final String PTY_BACKGROUND_REPEAT = "background_repeat";

   // 光标的名称定义
   public static final String PTY_CURSOR = "cursor";

   // 垂直对齐的名称定义
   public static final String PTY_VERTICAL_ALIGN = "vertical_align";

   // 宽度的名称定义
   public static final String PTY_WIDTH = "width";

   // 高度的名称定义
   public static final String PTY_HEIGHT = "height";

   // 位置的名称定义
   public static final String PTY_POSITION = "position";

   // 左坐标的名称定义
   public static final String PTY_LEFT = "left";

   // 上坐标的名称定义
   public static final String PTY_TOP = "top";

   // 边框的名称定义
   public static final String PTY_BORDER = "border";

   // 边框宽度的名称定义
   public static final String PTY_BORDER_WIDTH = "border_width";

   // 边框样式的名称定义
   public static final String PTY_BORDER_STYLE = "border_style";

   // 边框颜色的名称定义
   public static final String PTY_BORDER_COLOR = "border_color";

   // 左边框的名称定义
   public static final String PTY_BORDER_LEFT = "border_left";

   // 左边框宽度的名称定义
   public static final String PTY_BORDER_LEFT_WIDTH = "border_left_width";

   // 左边框样式的名称定义
   public static final String PTY_BORDER_LEFT_STYLE = "border_left_style";

   // 左边框颜色的名称定义
   public static final String PTY_BORDER_LEFT_COLOR = "border_left_color";

   // 上边框的名称定义
   public static final String PTY_BORDER_TOP = "border_top";

   // 上边框宽度的名称定义
   public static final String PTY_BORDER_TOP_WIDTH = "border_top_width";

   // 上边框样式的名称定义
   public static final String PTY_BORDER_TOP_STYLE = "border_top_style";

   // 上边框颜色的名称定义
   public static final String PTY_BORDER_TOP_COLOR = "border_top_color";

   // 右边框的名称定义
   public static final String PTY_BORDER_RIGHT = "border_right";

   // 右边框宽度的名称定义
   public static final String PTY_BORDER_RIGHT_WIDTH = "border_right_width";

   // 右边框样式的名称定义
   public static final String PTY_BORDER_RIGHT_STYLE = "border_right_style";

   // 右边框颜色的名称定义
   public static final String PTY_BORDER_RIGHT_COLOR = "border_right_color";

   // 下边框的名称定义
   public static final String PTY_BORDER_BOTTOM = "border_bottom";

   // 下边框宽度的名称定义
   public static final String PTY_BORDER_BOTTOM_WIDTH = "border_bottom_width";

   // 下边框样式的名称定义
   public static final String PTY_BORDER_BOTTOM_STYLE = "border_bottom_style";

   // 下边框颜色的名称定义
   public static final String PTY_BORDER_BOTTOM_COLOR = "border_bottom_color";

   // 余白设定的名称定义
   public static final String PTY_PADDING = "padding";

   // 左余白的名称定义
   public static final String PTY_PADDING_LEFT = "padding_left";

   // 上余白的名称定义
   public static final String PTY_PADDING_TOP = "padding_top";

   // 右余白的名称定义
   public static final String PTY_PADDING_RIGHT = "padding_right";

   // 下余白的名称定义
   public static final String PTY_PADDING_BOTTOM = "padding_bottom";

   // 合并设定的名称定义
   public static final String PTY_MARGIN = "margin";

   // 左合并的名称定义
   public static final String PTY_MARGIN_LEFT = "margin_left";

   // 上合并的名称定义
   public static final String PTY_MARGIN_TOP = "margin_top";

   // 右合并的名称定义
   public static final String PTY_MARGIN_RIGHT = "margin_right";

   // 下合并的名称定义
   public static final String PTY_MARGIN_BOTTOM = "margin_bottom";

   // 空白处理的名称定义
   public static final String PTY_WHITE_SPACE = "white_space";

   // 行高的名称定义
   public static final String PTY_LINE_HEIGHT = "line_height";

   // 字体族的名称定义
   public static final String PTY_FONT_FAMILY = "font_family";

   // 字体大小的名称定义
   public static final String PTY_FONT_SIZE = "font_size";

   // 字体粗细的名称定义
   public static final String PTY_FONT_WEIGHT = "font_weight";

   // 溢出设定的名称定义
   public static final String PTY_OVERFLOW = "overflow";

   // X轴溢出的名称定义
   public static final String PTY_OVERFLOW_X = "overflow_x";

   // Y轴溢出的名称定义
   public static final String PTY_OVERFLOW_Y = "overflow_y";

   // 下划线位置的名称定义
   public static final String PTY_UNDERLINE_POSITION = "underline_position";

   // 文本对齐方式的名称定义
   public static final String PTY_TEXT_ALIGN = "text_align";

   // 大小写控制的名称定义
   public static final String PTY_TEXT_TRANSFORM = "text_transform";

   // 表格布局的名称定义
   public static final String PTY_TABLE_LAYOUT = "table_layout";

   // 纵深的名称定义
   public static final String PTY_Z_INDEX = "z_index";

   // 父控件名称的名称定义
   public static final String PTY_PARENT_NAME = "parent_name";

   // 名称的定义
   @AName("name")
   protected String _name;

   // 标签的定义
   @AName("label")
   protected FMultiString _label = new FMultiString();

   // 有效性的定义
   @AName("is_valid")
   protected Boolean _isValid = Boolean.FALSE;

   // 父主题的定义
   @AName("parent_style")
   protected String _parentStyle;

   // 注释信息的定义
   @AName("note")
   protected FMultiString _note = new FMultiString();

   // 颜色的定义
   @AName("color")
   protected String _color;

   // 背景色的定义
   @AName("background_color")
   protected String _backgroundColor;

   // 背景图片的定义
   @AName("background_image")
   protected String _backgroundImage;

   // 背景图片位置的定义
   @AName("background_position")
   protected String _backgroundPosition;

   // 背景图片位置X的定义
   @AName("background_position_x")
   protected String _backgroundPositionX;

   // 背景图片位置Y的定义
   @AName("background_position_y")
   protected String _backgroundPositionY;

   // 背景图片重复的定义
   @AName("background_repeat")
   protected String _backgroundRepeat;

   // 光标的定义
   @AName("cursor")
   protected String _cursor;

   // 垂直对齐的定义
   @AName("vertical_align")
   protected String _verticalAlign;

   // 宽度的定义
   @AName("width")
   protected String _width;

   // 高度的定义
   @AName("height")
   protected String _height;

   // 位置的定义
   @AName("position")
   protected String _position;

   // 左坐标的定义
   @AName("left")
   protected String _left;

   // 上坐标的定义
   @AName("top")
   protected String _top;

   // 边框宽度的定义
   @AName("border_width")
   protected String _borderWidth;

   // 边框样式的定义
   @AName("border_style")
   protected String _borderStyle;

   // 边框颜色的定义
   @AName("border_color")
   protected String _borderColor;

   // 左边框宽度的定义
   @AName("border_left_width")
   protected String _borderLeftWidth;

   // 左边框样式的定义
   @AName("border_left_style")
   protected String _borderLeftStyle;

   // 左边框颜色的定义
   @AName("border_left_color")
   protected String _borderLeftColor;

   // 上边框宽度的定义
   @AName("border_top_width")
   protected String _borderTopWidth;

   // 上边框样式的定义
   @AName("border_top_style")
   protected String _borderTopStyle;

   // 上边框颜色的定义
   @AName("border_top_color")
   protected String _borderTopColor;

   // 右边框宽度的定义
   @AName("border_right_width")
   protected String _borderRightWidth;

   // 右边框样式的定义
   @AName("border_right_style")
   protected String _borderRightStyle;

   // 右边框颜色的定义
   @AName("border_right_color")
   protected String _borderRightColor;

   // 下边框宽度的定义
   @AName("border_bottom_width")
   protected String _borderBottomWidth;

   // 下边框样式的定义
   @AName("border_bottom_style")
   protected String _borderBottomStyle;

   // 下边框颜色的定义
   @AName("border_bottom_color")
   protected String _borderBottomColor;

   // 余白设定的定义
   @AName("padding")
   protected String _padding;

   // 左余白的定义
   @AName("padding_left")
   protected String _paddingLeft;

   // 上余白的定义
   @AName("padding_top")
   protected String _paddingTop;

   // 右余白的定义
   @AName("padding_right")
   protected String _paddingRight;

   // 下余白的定义
   @AName("padding_bottom")
   protected String _paddingBottom;

   // 合并设定的定义
   @AName("margin")
   protected String _margin;

   // 左合并的定义
   @AName("margin_left")
   protected String _marginLeft;

   // 上合并的定义
   @AName("margin_top")
   protected String _marginTop;

   // 右合并的定义
   @AName("margin_right")
   protected String _marginRight;

   // 下合并的定义
   @AName("margin_bottom")
   protected String _marginBottom;

   // 空白处理的定义
   @AName("white_space")
   protected String _whiteSpace;

   // 行高的定义
   @AName("line_height")
   protected String _lineHeight;

   // 字体族的定义
   @AName("font_family")
   protected String _fontFamily;

   // 字体大小的定义
   @AName("font_size")
   protected String _fontSize;

   // 字体粗细的定义
   @AName("font_weight")
   protected String _fontWeight;

   // 溢出设定的定义
   @AName("overflow")
   protected String _overflow;

   // X轴溢出的定义
   @AName("overflow_x")
   protected String _overflowX;

   // Y轴溢出的定义
   @AName("overflow_y")
   protected String _overflowY;

   // 下划线位置的定义
   @AName("underline_position")
   protected String _underlinePosition;

   // 文本对齐方式的定义
   @AName("text_align")
   protected String _textAlign;

   // 大小写控制的定义
   @AName("text_transform")
   protected String _textTransform;

   // 表格布局的定义
   @AName("table_layout")
   protected String _tableLayout;

   // 纵深的定义
   @AName("z_index")
   protected String _zIndex;

   // 父控件名称的定义
   @AName("parent_name")
   protected String _parentName;

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
   // <T>获得父主题的内容。</T>
   //
   // @return 父主题
   //============================================================
   public String getParentStyle(){
      return _parentStyle;
   }

   //============================================================
   // <T>设置父主题的内容。</T>
   //
   // @param value 父主题
   //============================================================
   public void setParentStyle(String value){
      _parentStyle = value;
   }

   //============================================================
   // <T>获得注释信息的内容。</T>
   //
   // @return 注释信息
   //============================================================
   public String getNote(){
      return _note.get();
   }

   //============================================================
   // <T>设置注释信息的内容。</T>
   //
   // @param value 注释信息
   //============================================================
   public void setNote(String value){
      _note.set(value);
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
   // <T>获得背景色的内容。</T>
   //
   // @return 背景色
   //============================================================
   public String getBackgroundColor(){
      return _backgroundColor;
   }

   //============================================================
   // <T>设置背景色的内容。</T>
   //
   // @param value 背景色
   //============================================================
   public void setBackgroundColor(String value){
      _backgroundColor = value;
   }

   //============================================================
   // <T>获得背景图片的内容。</T>
   //
   // @return 背景图片
   //============================================================
   public String getBackgroundImage(){
      return _backgroundImage;
   }

   //============================================================
   // <T>设置背景图片的内容。</T>
   //
   // @param value 背景图片
   //============================================================
   public void setBackgroundImage(String value){
      _backgroundImage = value;
   }

   //============================================================
   // <T>获得背景图片位置的内容。</T>
   //
   // @return 背景图片位置
   //============================================================
   public String getBackgroundPosition(){
      return _backgroundPosition;
   }

   //============================================================
   // <T>设置背景图片位置的内容。</T>
   //
   // @param value 背景图片位置
   //============================================================
   public void setBackgroundPosition(String value){
      _backgroundPosition = value;
   }

   //============================================================
   // <T>获得背景图片位置X的内容。</T>
   //
   // @return 背景图片位置X
   //============================================================
   public String getBackgroundPositionX(){
      return _backgroundPositionX;
   }

   //============================================================
   // <T>设置背景图片位置X的内容。</T>
   //
   // @param value 背景图片位置X
   //============================================================
   public void setBackgroundPositionX(String value){
      _backgroundPositionX = value;
   }

   //============================================================
   // <T>获得背景图片位置Y的内容。</T>
   //
   // @return 背景图片位置Y
   //============================================================
   public String getBackgroundPositionY(){
      return _backgroundPositionY;
   }

   //============================================================
   // <T>设置背景图片位置Y的内容。</T>
   //
   // @param value 背景图片位置Y
   //============================================================
   public void setBackgroundPositionY(String value){
      _backgroundPositionY = value;
   }

   //============================================================
   // <T>获得背景图片重复的内容。</T>
   //
   // @return 背景图片重复
   //============================================================
   public String getBackgroundRepeat(){
      return _backgroundRepeat;
   }

   //============================================================
   // <T>设置背景图片重复的内容。</T>
   //
   // @param value 背景图片重复
   //============================================================
   public void setBackgroundRepeat(String value){
      _backgroundRepeat = value;
   }

   //============================================================
   // <T>获得光标的内容。</T>
   //
   // @return 光标
   //============================================================
   public String getCursor(){
      return _cursor;
   }

   //============================================================
   // <T>设置光标的内容。</T>
   //
   // @param value 光标
   //============================================================
   public void setCursor(String value){
      _cursor = value;
   }

   //============================================================
   // <T>获得垂直对齐的内容。</T>
   //
   // @return 垂直对齐
   //============================================================
   public String getVerticalAlign(){
      return _verticalAlign;
   }

   //============================================================
   // <T>设置垂直对齐的内容。</T>
   //
   // @param value 垂直对齐
   //============================================================
   public void setVerticalAlign(String value){
      _verticalAlign = value;
   }

   //============================================================
   // <T>获得宽度的内容。</T>
   //
   // @return 宽度
   //============================================================
   public String getWidth(){
      return _width;
   }

   //============================================================
   // <T>设置宽度的内容。</T>
   //
   // @param value 宽度
   //============================================================
   public void setWidth(String value){
      _width = value;
   }

   //============================================================
   // <T>获得高度的内容。</T>
   //
   // @return 高度
   //============================================================
   public String getHeight(){
      return _height;
   }

   //============================================================
   // <T>设置高度的内容。</T>
   //
   // @param value 高度
   //============================================================
   public void setHeight(String value){
      _height = value;
   }

   //============================================================
   // <T>获得位置的内容。</T>
   //
   // @return 位置
   //============================================================
   public String getPosition(){
      return _position;
   }

   //============================================================
   // <T>设置位置的内容。</T>
   //
   // @param value 位置
   //============================================================
   public void setPosition(String value){
      _position = value;
   }

   //============================================================
   // <T>获得左坐标的内容。</T>
   //
   // @return 左坐标
   //============================================================
   public String getLeft(){
      return _left;
   }

   //============================================================
   // <T>设置左坐标的内容。</T>
   //
   // @param value 左坐标
   //============================================================
   public void setLeft(String value){
      _left = value;
   }

   //============================================================
   // <T>获得上坐标的内容。</T>
   //
   // @return 上坐标
   //============================================================
   public String getTop(){
      return _top;
   }

   //============================================================
   // <T>设置上坐标的内容。</T>
   //
   // @param value 上坐标
   //============================================================
   public void setTop(String value){
      _top = value;
   }

   //============================================================
   // <T>获得边框的内容。</T>
   //
   // @return 边框
   //============================================================
   public abstract String getBorder();

   //============================================================
   // <T>设置边框的内容。</T>
   //
   // @param value 边框
   //============================================================
   public abstract void setBorder(String value);

   //============================================================
   // <T>获得边框宽度的内容。</T>
   //
   // @return 边框宽度
   //============================================================
   public String getBorderWidth(){
      return _borderWidth;
   }

   //============================================================
   // <T>设置边框宽度的内容。</T>
   //
   // @param value 边框宽度
   //============================================================
   public void setBorderWidth(String value){
      _borderWidth = value;
   }

   //============================================================
   // <T>获得边框样式的内容。</T>
   //
   // @return 边框样式
   //============================================================
   public String getBorderStyle(){
      return _borderStyle;
   }

   //============================================================
   // <T>设置边框样式的内容。</T>
   //
   // @param value 边框样式
   //============================================================
   public void setBorderStyle(String value){
      _borderStyle = value;
   }

   //============================================================
   // <T>获得边框颜色的内容。</T>
   //
   // @return 边框颜色
   //============================================================
   public String getBorderColor(){
      return _borderColor;
   }

   //============================================================
   // <T>设置边框颜色的内容。</T>
   //
   // @param value 边框颜色
   //============================================================
   public void setBorderColor(String value){
      _borderColor = value;
   }

   //============================================================
   // <T>获得左边框的内容。</T>
   //
   // @return 左边框
   //============================================================
   public abstract String getBorderLeft();

   //============================================================
   // <T>设置左边框的内容。</T>
   //
   // @param value 左边框
   //============================================================
   public abstract void setBorderLeft(String value);

   //============================================================
   // <T>获得左边框宽度的内容。</T>
   //
   // @return 左边框宽度
   //============================================================
   public String getBorderLeftWidth(){
      return _borderLeftWidth;
   }

   //============================================================
   // <T>设置左边框宽度的内容。</T>
   //
   // @param value 左边框宽度
   //============================================================
   public void setBorderLeftWidth(String value){
      _borderLeftWidth = value;
   }

   //============================================================
   // <T>获得左边框样式的内容。</T>
   //
   // @return 左边框样式
   //============================================================
   public String getBorderLeftStyle(){
      return _borderLeftStyle;
   }

   //============================================================
   // <T>设置左边框样式的内容。</T>
   //
   // @param value 左边框样式
   //============================================================
   public void setBorderLeftStyle(String value){
      _borderLeftStyle = value;
   }

   //============================================================
   // <T>获得左边框颜色的内容。</T>
   //
   // @return 左边框颜色
   //============================================================
   public String getBorderLeftColor(){
      return _borderLeftColor;
   }

   //============================================================
   // <T>设置左边框颜色的内容。</T>
   //
   // @param value 左边框颜色
   //============================================================
   public void setBorderLeftColor(String value){
      _borderLeftColor = value;
   }

   //============================================================
   // <T>获得上边框的内容。</T>
   //
   // @return 上边框
   //============================================================
   public abstract String getBorderTop();

   //============================================================
   // <T>设置上边框的内容。</T>
   //
   // @param value 上边框
   //============================================================
   public abstract void setBorderTop(String value);

   //============================================================
   // <T>获得上边框宽度的内容。</T>
   //
   // @return 上边框宽度
   //============================================================
   public String getBorderTopWidth(){
      return _borderTopWidth;
   }

   //============================================================
   // <T>设置上边框宽度的内容。</T>
   //
   // @param value 上边框宽度
   //============================================================
   public void setBorderTopWidth(String value){
      _borderTopWidth = value;
   }

   //============================================================
   // <T>获得上边框样式的内容。</T>
   //
   // @return 上边框样式
   //============================================================
   public String getBorderTopStyle(){
      return _borderTopStyle;
   }

   //============================================================
   // <T>设置上边框样式的内容。</T>
   //
   // @param value 上边框样式
   //============================================================
   public void setBorderTopStyle(String value){
      _borderTopStyle = value;
   }

   //============================================================
   // <T>获得上边框颜色的内容。</T>
   //
   // @return 上边框颜色
   //============================================================
   public String getBorderTopColor(){
      return _borderTopColor;
   }

   //============================================================
   // <T>设置上边框颜色的内容。</T>
   //
   // @param value 上边框颜色
   //============================================================
   public void setBorderTopColor(String value){
      _borderTopColor = value;
   }

   //============================================================
   // <T>获得右边框的内容。</T>
   //
   // @return 右边框
   //============================================================
   public abstract String getBorderRight();

   //============================================================
   // <T>设置右边框的内容。</T>
   //
   // @param value 右边框
   //============================================================
   public abstract void setBorderRight(String value);

   //============================================================
   // <T>获得右边框宽度的内容。</T>
   //
   // @return 右边框宽度
   //============================================================
   public String getBorderRightWidth(){
      return _borderRightWidth;
   }

   //============================================================
   // <T>设置右边框宽度的内容。</T>
   //
   // @param value 右边框宽度
   //============================================================
   public void setBorderRightWidth(String value){
      _borderRightWidth = value;
   }

   //============================================================
   // <T>获得右边框样式的内容。</T>
   //
   // @return 右边框样式
   //============================================================
   public String getBorderRightStyle(){
      return _borderRightStyle;
   }

   //============================================================
   // <T>设置右边框样式的内容。</T>
   //
   // @param value 右边框样式
   //============================================================
   public void setBorderRightStyle(String value){
      _borderRightStyle = value;
   }

   //============================================================
   // <T>获得右边框颜色的内容。</T>
   //
   // @return 右边框颜色
   //============================================================
   public String getBorderRightColor(){
      return _borderRightColor;
   }

   //============================================================
   // <T>设置右边框颜色的内容。</T>
   //
   // @param value 右边框颜色
   //============================================================
   public void setBorderRightColor(String value){
      _borderRightColor = value;
   }

   //============================================================
   // <T>获得下边框的内容。</T>
   //
   // @return 下边框
   //============================================================
   public abstract String getBorderBottom();

   //============================================================
   // <T>设置下边框的内容。</T>
   //
   // @param value 下边框
   //============================================================
   public abstract void setBorderBottom(String value);

   //============================================================
   // <T>获得下边框宽度的内容。</T>
   //
   // @return 下边框宽度
   //============================================================
   public String getBorderBottomWidth(){
      return _borderBottomWidth;
   }

   //============================================================
   // <T>设置下边框宽度的内容。</T>
   //
   // @param value 下边框宽度
   //============================================================
   public void setBorderBottomWidth(String value){
      _borderBottomWidth = value;
   }

   //============================================================
   // <T>获得下边框样式的内容。</T>
   //
   // @return 下边框样式
   //============================================================
   public String getBorderBottomStyle(){
      return _borderBottomStyle;
   }

   //============================================================
   // <T>设置下边框样式的内容。</T>
   //
   // @param value 下边框样式
   //============================================================
   public void setBorderBottomStyle(String value){
      _borderBottomStyle = value;
   }

   //============================================================
   // <T>获得下边框颜色的内容。</T>
   //
   // @return 下边框颜色
   //============================================================
   public String getBorderBottomColor(){
      return _borderBottomColor;
   }

   //============================================================
   // <T>设置下边框颜色的内容。</T>
   //
   // @param value 下边框颜色
   //============================================================
   public void setBorderBottomColor(String value){
      _borderBottomColor = value;
   }

   //============================================================
   // <T>获得余白设定的内容。</T>
   //
   // @return 余白设定
   //============================================================
   public String getPadding(){
      return _padding;
   }

   //============================================================
   // <T>设置余白设定的内容。</T>
   //
   // @param value 余白设定
   //============================================================
   public void setPadding(String value){
      _padding = value;
   }

   //============================================================
   // <T>获得左余白的内容。</T>
   //
   // @return 左余白
   //============================================================
   public String getPaddingLeft(){
      return _paddingLeft;
   }

   //============================================================
   // <T>设置左余白的内容。</T>
   //
   // @param value 左余白
   //============================================================
   public void setPaddingLeft(String value){
      _paddingLeft = value;
   }

   //============================================================
   // <T>获得上余白的内容。</T>
   //
   // @return 上余白
   //============================================================
   public String getPaddingTop(){
      return _paddingTop;
   }

   //============================================================
   // <T>设置上余白的内容。</T>
   //
   // @param value 上余白
   //============================================================
   public void setPaddingTop(String value){
      _paddingTop = value;
   }

   //============================================================
   // <T>获得右余白的内容。</T>
   //
   // @return 右余白
   //============================================================
   public String getPaddingRight(){
      return _paddingRight;
   }

   //============================================================
   // <T>设置右余白的内容。</T>
   //
   // @param value 右余白
   //============================================================
   public void setPaddingRight(String value){
      _paddingRight = value;
   }

   //============================================================
   // <T>获得下余白的内容。</T>
   //
   // @return 下余白
   //============================================================
   public String getPaddingBottom(){
      return _paddingBottom;
   }

   //============================================================
   // <T>设置下余白的内容。</T>
   //
   // @param value 下余白
   //============================================================
   public void setPaddingBottom(String value){
      _paddingBottom = value;
   }

   //============================================================
   // <T>获得合并设定的内容。</T>
   //
   // @return 合并设定
   //============================================================
   public String getMargin(){
      return _margin;
   }

   //============================================================
   // <T>设置合并设定的内容。</T>
   //
   // @param value 合并设定
   //============================================================
   public void setMargin(String value){
      _margin = value;
   }

   //============================================================
   // <T>获得左合并的内容。</T>
   //
   // @return 左合并
   //============================================================
   public String getMarginLeft(){
      return _marginLeft;
   }

   //============================================================
   // <T>设置左合并的内容。</T>
   //
   // @param value 左合并
   //============================================================
   public void setMarginLeft(String value){
      _marginLeft = value;
   }

   //============================================================
   // <T>获得上合并的内容。</T>
   //
   // @return 上合并
   //============================================================
   public String getMarginTop(){
      return _marginTop;
   }

   //============================================================
   // <T>设置上合并的内容。</T>
   //
   // @param value 上合并
   //============================================================
   public void setMarginTop(String value){
      _marginTop = value;
   }

   //============================================================
   // <T>获得右合并的内容。</T>
   //
   // @return 右合并
   //============================================================
   public String getMarginRight(){
      return _marginRight;
   }

   //============================================================
   // <T>设置右合并的内容。</T>
   //
   // @param value 右合并
   //============================================================
   public void setMarginRight(String value){
      _marginRight = value;
   }

   //============================================================
   // <T>获得下合并的内容。</T>
   //
   // @return 下合并
   //============================================================
   public String getMarginBottom(){
      return _marginBottom;
   }

   //============================================================
   // <T>设置下合并的内容。</T>
   //
   // @param value 下合并
   //============================================================
   public void setMarginBottom(String value){
      _marginBottom = value;
   }

   //============================================================
   // <T>获得空白处理的内容。</T>
   //
   // @return 空白处理
   //============================================================
   public String getWhiteSpace(){
      return _whiteSpace;
   }

   //============================================================
   // <T>设置空白处理的内容。</T>
   //
   // @param value 空白处理
   //============================================================
   public void setWhiteSpace(String value){
      _whiteSpace = value;
   }

   //============================================================
   // <T>获得行高的内容。</T>
   //
   // @return 行高
   //============================================================
   public String getLineHeight(){
      return _lineHeight;
   }

   //============================================================
   // <T>设置行高的内容。</T>
   //
   // @param value 行高
   //============================================================
   public void setLineHeight(String value){
      _lineHeight = value;
   }

   //============================================================
   // <T>获得字体族的内容。</T>
   //
   // @return 字体族
   //============================================================
   public String getFontFamily(){
      return _fontFamily;
   }

   //============================================================
   // <T>设置字体族的内容。</T>
   //
   // @param value 字体族
   //============================================================
   public void setFontFamily(String value){
      _fontFamily = value;
   }

   //============================================================
   // <T>获得字体大小的内容。</T>
   //
   // @return 字体大小
   //============================================================
   public String getFontSize(){
      return _fontSize;
   }

   //============================================================
   // <T>设置字体大小的内容。</T>
   //
   // @param value 字体大小
   //============================================================
   public void setFontSize(String value){
      _fontSize = value;
   }

   //============================================================
   // <T>获得字体粗细的内容。</T>
   //
   // @return 字体粗细
   //============================================================
   public String getFontWeight(){
      return _fontWeight;
   }

   //============================================================
   // <T>设置字体粗细的内容。</T>
   //
   // @param value 字体粗细
   //============================================================
   public void setFontWeight(String value){
      _fontWeight = value;
   }

   //============================================================
   // <T>获得溢出设定的内容。</T>
   //
   // @return 溢出设定
   //============================================================
   public String getOverflow(){
      return _overflow;
   }

   //============================================================
   // <T>设置溢出设定的内容。</T>
   //
   // @param value 溢出设定
   //============================================================
   public void setOverflow(String value){
      _overflow = value;
   }

   //============================================================
   // <T>获得X轴溢出的内容。</T>
   //
   // @return X轴溢出
   //============================================================
   public String getOverflowX(){
      return _overflowX;
   }

   //============================================================
   // <T>设置X轴溢出的内容。</T>
   //
   // @param value X轴溢出
   //============================================================
   public void setOverflowX(String value){
      _overflowX = value;
   }

   //============================================================
   // <T>获得Y轴溢出的内容。</T>
   //
   // @return Y轴溢出
   //============================================================
   public String getOverflowY(){
      return _overflowY;
   }

   //============================================================
   // <T>设置Y轴溢出的内容。</T>
   //
   // @param value Y轴溢出
   //============================================================
   public void setOverflowY(String value){
      _overflowY = value;
   }

   //============================================================
   // <T>获得下划线位置的内容。</T>
   //
   // @return 下划线位置
   //============================================================
   public String getUnderlinePosition(){
      return _underlinePosition;
   }

   //============================================================
   // <T>设置下划线位置的内容。</T>
   //
   // @param value 下划线位置
   //============================================================
   public void setUnderlinePosition(String value){
      _underlinePosition = value;
   }

   //============================================================
   // <T>获得文本对齐方式的内容。</T>
   //
   // @return 文本对齐方式
   //============================================================
   public String getTextAlign(){
      return _textAlign;
   }

   //============================================================
   // <T>设置文本对齐方式的内容。</T>
   //
   // @param value 文本对齐方式
   //============================================================
   public void setTextAlign(String value){
      _textAlign = value;
   }

   //============================================================
   // <T>获得大小写控制的内容。</T>
   //
   // @return 大小写控制
   //============================================================
   public String getTextTransform(){
      return _textTransform;
   }

   //============================================================
   // <T>设置大小写控制的内容。</T>
   //
   // @param value 大小写控制
   //============================================================
   public void setTextTransform(String value){
      _textTransform = value;
   }

   //============================================================
   // <T>获得表格布局的内容。</T>
   //
   // @return 表格布局
   //============================================================
   public String getTableLayout(){
      return _tableLayout;
   }

   //============================================================
   // <T>设置表格布局的内容。</T>
   //
   // @param value 表格布局
   //============================================================
   public void setTableLayout(String value){
      _tableLayout = value;
   }

   //============================================================
   // <T>获得纵深的内容。</T>
   //
   // @return 纵深
   //============================================================
   public String getZIndex(){
      return _zIndex;
   }

   //============================================================
   // <T>设置纵深的内容。</T>
   //
   // @param value 纵深
   //============================================================
   public void setZIndex(String value){
      _zIndex = value;
   }

   //============================================================
   // <T>获得父控件名称的内容。</T>
   //
   // @return 父控件名称
   //============================================================
   public String getParentName(){
      return _parentName;
   }

   //============================================================
   // <T>设置父控件名称的内容。</T>
   //
   // @param value 父控件名称
   //============================================================
   public void setParentName(String value){
      _parentName = value;
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
      }else if(PTY_PARENT_STYLE.equalsIgnoreCase(name)){
         return getParentStyle();
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         return getNote();
      }else if(PTY_COLOR.equalsIgnoreCase(name)){
         return getColor();
      }else if(PTY_BACKGROUND_COLOR.equalsIgnoreCase(name)){
         return getBackgroundColor();
      }else if(PTY_BACKGROUND_IMAGE.equalsIgnoreCase(name)){
         return getBackgroundImage();
      }else if(PTY_BACKGROUND_POSITION.equalsIgnoreCase(name)){
         return getBackgroundPosition();
      }else if(PTY_BACKGROUND_POSITION_X.equalsIgnoreCase(name)){
         return getBackgroundPositionX();
      }else if(PTY_BACKGROUND_POSITION_Y.equalsIgnoreCase(name)){
         return getBackgroundPositionY();
      }else if(PTY_BACKGROUND_REPEAT.equalsIgnoreCase(name)){
         return getBackgroundRepeat();
      }else if(PTY_CURSOR.equalsIgnoreCase(name)){
         return getCursor();
      }else if(PTY_VERTICAL_ALIGN.equalsIgnoreCase(name)){
         return getVerticalAlign();
      }else if(PTY_WIDTH.equalsIgnoreCase(name)){
         return getWidth();
      }else if(PTY_HEIGHT.equalsIgnoreCase(name)){
         return getHeight();
      }else if(PTY_POSITION.equalsIgnoreCase(name)){
         return getPosition();
      }else if(PTY_LEFT.equalsIgnoreCase(name)){
         return getLeft();
      }else if(PTY_TOP.equalsIgnoreCase(name)){
         return getTop();
      }else if(PTY_BORDER.equalsIgnoreCase(name)){
         return getBorder();
      }else if(PTY_BORDER_WIDTH.equalsIgnoreCase(name)){
         return getBorderWidth();
      }else if(PTY_BORDER_STYLE.equalsIgnoreCase(name)){
         return getBorderStyle();
      }else if(PTY_BORDER_COLOR.equalsIgnoreCase(name)){
         return getBorderColor();
      }else if(PTY_BORDER_LEFT.equalsIgnoreCase(name)){
         return getBorderLeft();
      }else if(PTY_BORDER_LEFT_WIDTH.equalsIgnoreCase(name)){
         return getBorderLeftWidth();
      }else if(PTY_BORDER_LEFT_STYLE.equalsIgnoreCase(name)){
         return getBorderLeftStyle();
      }else if(PTY_BORDER_LEFT_COLOR.equalsIgnoreCase(name)){
         return getBorderLeftColor();
      }else if(PTY_BORDER_TOP.equalsIgnoreCase(name)){
         return getBorderTop();
      }else if(PTY_BORDER_TOP_WIDTH.equalsIgnoreCase(name)){
         return getBorderTopWidth();
      }else if(PTY_BORDER_TOP_STYLE.equalsIgnoreCase(name)){
         return getBorderTopStyle();
      }else if(PTY_BORDER_TOP_COLOR.equalsIgnoreCase(name)){
         return getBorderTopColor();
      }else if(PTY_BORDER_RIGHT.equalsIgnoreCase(name)){
         return getBorderRight();
      }else if(PTY_BORDER_RIGHT_WIDTH.equalsIgnoreCase(name)){
         return getBorderRightWidth();
      }else if(PTY_BORDER_RIGHT_STYLE.equalsIgnoreCase(name)){
         return getBorderRightStyle();
      }else if(PTY_BORDER_RIGHT_COLOR.equalsIgnoreCase(name)){
         return getBorderRightColor();
      }else if(PTY_BORDER_BOTTOM.equalsIgnoreCase(name)){
         return getBorderBottom();
      }else if(PTY_BORDER_BOTTOM_WIDTH.equalsIgnoreCase(name)){
         return getBorderBottomWidth();
      }else if(PTY_BORDER_BOTTOM_STYLE.equalsIgnoreCase(name)){
         return getBorderBottomStyle();
      }else if(PTY_BORDER_BOTTOM_COLOR.equalsIgnoreCase(name)){
         return getBorderBottomColor();
      }else if(PTY_PADDING.equalsIgnoreCase(name)){
         return getPadding();
      }else if(PTY_PADDING_LEFT.equalsIgnoreCase(name)){
         return getPaddingLeft();
      }else if(PTY_PADDING_TOP.equalsIgnoreCase(name)){
         return getPaddingTop();
      }else if(PTY_PADDING_RIGHT.equalsIgnoreCase(name)){
         return getPaddingRight();
      }else if(PTY_PADDING_BOTTOM.equalsIgnoreCase(name)){
         return getPaddingBottom();
      }else if(PTY_MARGIN.equalsIgnoreCase(name)){
         return getMargin();
      }else if(PTY_MARGIN_LEFT.equalsIgnoreCase(name)){
         return getMarginLeft();
      }else if(PTY_MARGIN_TOP.equalsIgnoreCase(name)){
         return getMarginTop();
      }else if(PTY_MARGIN_RIGHT.equalsIgnoreCase(name)){
         return getMarginRight();
      }else if(PTY_MARGIN_BOTTOM.equalsIgnoreCase(name)){
         return getMarginBottom();
      }else if(PTY_WHITE_SPACE.equalsIgnoreCase(name)){
         return getWhiteSpace();
      }else if(PTY_LINE_HEIGHT.equalsIgnoreCase(name)){
         return getLineHeight();
      }else if(PTY_FONT_FAMILY.equalsIgnoreCase(name)){
         return getFontFamily();
      }else if(PTY_FONT_SIZE.equalsIgnoreCase(name)){
         return getFontSize();
      }else if(PTY_FONT_WEIGHT.equalsIgnoreCase(name)){
         return getFontWeight();
      }else if(PTY_OVERFLOW.equalsIgnoreCase(name)){
         return getOverflow();
      }else if(PTY_OVERFLOW_X.equalsIgnoreCase(name)){
         return getOverflowX();
      }else if(PTY_OVERFLOW_Y.equalsIgnoreCase(name)){
         return getOverflowY();
      }else if(PTY_UNDERLINE_POSITION.equalsIgnoreCase(name)){
         return getUnderlinePosition();
      }else if(PTY_TEXT_ALIGN.equalsIgnoreCase(name)){
         return getTextAlign();
      }else if(PTY_TEXT_TRANSFORM.equalsIgnoreCase(name)){
         return getTextTransform();
      }else if(PTY_TABLE_LAYOUT.equalsIgnoreCase(name)){
         return getTableLayout();
      }else if(PTY_Z_INDEX.equalsIgnoreCase(name)){
         return getZIndex();
      }else if(PTY_PARENT_NAME.equalsIgnoreCase(name)){
         return getParentName();
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
      }else if(PTY_PARENT_STYLE.equalsIgnoreCase(name)){
         setParentStyle(value);
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         setNote(value);
      }else if(PTY_COLOR.equalsIgnoreCase(name)){
         setColor(value);
      }else if(PTY_BACKGROUND_COLOR.equalsIgnoreCase(name)){
         setBackgroundColor(value);
      }else if(PTY_BACKGROUND_IMAGE.equalsIgnoreCase(name)){
         setBackgroundImage(value);
      }else if(PTY_BACKGROUND_POSITION.equalsIgnoreCase(name)){
         setBackgroundPosition(value);
      }else if(PTY_BACKGROUND_POSITION_X.equalsIgnoreCase(name)){
         setBackgroundPositionX(value);
      }else if(PTY_BACKGROUND_POSITION_Y.equalsIgnoreCase(name)){
         setBackgroundPositionY(value);
      }else if(PTY_BACKGROUND_REPEAT.equalsIgnoreCase(name)){
         setBackgroundRepeat(value);
      }else if(PTY_CURSOR.equalsIgnoreCase(name)){
         setCursor(value);
      }else if(PTY_VERTICAL_ALIGN.equalsIgnoreCase(name)){
         setVerticalAlign(value);
      }else if(PTY_WIDTH.equalsIgnoreCase(name)){
         setWidth(value);
      }else if(PTY_HEIGHT.equalsIgnoreCase(name)){
         setHeight(value);
      }else if(PTY_POSITION.equalsIgnoreCase(name)){
         setPosition(value);
      }else if(PTY_LEFT.equalsIgnoreCase(name)){
         setLeft(value);
      }else if(PTY_TOP.equalsIgnoreCase(name)){
         setTop(value);
      }else if(PTY_BORDER.equalsIgnoreCase(name)){
         setBorder(value);
      }else if(PTY_BORDER_WIDTH.equalsIgnoreCase(name)){
         setBorderWidth(value);
      }else if(PTY_BORDER_STYLE.equalsIgnoreCase(name)){
         setBorderStyle(value);
      }else if(PTY_BORDER_COLOR.equalsIgnoreCase(name)){
         setBorderColor(value);
      }else if(PTY_BORDER_LEFT.equalsIgnoreCase(name)){
         setBorderLeft(value);
      }else if(PTY_BORDER_LEFT_WIDTH.equalsIgnoreCase(name)){
         setBorderLeftWidth(value);
      }else if(PTY_BORDER_LEFT_STYLE.equalsIgnoreCase(name)){
         setBorderLeftStyle(value);
      }else if(PTY_BORDER_LEFT_COLOR.equalsIgnoreCase(name)){
         setBorderLeftColor(value);
      }else if(PTY_BORDER_TOP.equalsIgnoreCase(name)){
         setBorderTop(value);
      }else if(PTY_BORDER_TOP_WIDTH.equalsIgnoreCase(name)){
         setBorderTopWidth(value);
      }else if(PTY_BORDER_TOP_STYLE.equalsIgnoreCase(name)){
         setBorderTopStyle(value);
      }else if(PTY_BORDER_TOP_COLOR.equalsIgnoreCase(name)){
         setBorderTopColor(value);
      }else if(PTY_BORDER_RIGHT.equalsIgnoreCase(name)){
         setBorderRight(value);
      }else if(PTY_BORDER_RIGHT_WIDTH.equalsIgnoreCase(name)){
         setBorderRightWidth(value);
      }else if(PTY_BORDER_RIGHT_STYLE.equalsIgnoreCase(name)){
         setBorderRightStyle(value);
      }else if(PTY_BORDER_RIGHT_COLOR.equalsIgnoreCase(name)){
         setBorderRightColor(value);
      }else if(PTY_BORDER_BOTTOM.equalsIgnoreCase(name)){
         setBorderBottom(value);
      }else if(PTY_BORDER_BOTTOM_WIDTH.equalsIgnoreCase(name)){
         setBorderBottomWidth(value);
      }else if(PTY_BORDER_BOTTOM_STYLE.equalsIgnoreCase(name)){
         setBorderBottomStyle(value);
      }else if(PTY_BORDER_BOTTOM_COLOR.equalsIgnoreCase(name)){
         setBorderBottomColor(value);
      }else if(PTY_PADDING.equalsIgnoreCase(name)){
         setPadding(value);
      }else if(PTY_PADDING_LEFT.equalsIgnoreCase(name)){
         setPaddingLeft(value);
      }else if(PTY_PADDING_TOP.equalsIgnoreCase(name)){
         setPaddingTop(value);
      }else if(PTY_PADDING_RIGHT.equalsIgnoreCase(name)){
         setPaddingRight(value);
      }else if(PTY_PADDING_BOTTOM.equalsIgnoreCase(name)){
         setPaddingBottom(value);
      }else if(PTY_MARGIN.equalsIgnoreCase(name)){
         setMargin(value);
      }else if(PTY_MARGIN_LEFT.equalsIgnoreCase(name)){
         setMarginLeft(value);
      }else if(PTY_MARGIN_TOP.equalsIgnoreCase(name)){
         setMarginTop(value);
      }else if(PTY_MARGIN_RIGHT.equalsIgnoreCase(name)){
         setMarginRight(value);
      }else if(PTY_MARGIN_BOTTOM.equalsIgnoreCase(name)){
         setMarginBottom(value);
      }else if(PTY_WHITE_SPACE.equalsIgnoreCase(name)){
         setWhiteSpace(value);
      }else if(PTY_LINE_HEIGHT.equalsIgnoreCase(name)){
         setLineHeight(value);
      }else if(PTY_FONT_FAMILY.equalsIgnoreCase(name)){
         setFontFamily(value);
      }else if(PTY_FONT_SIZE.equalsIgnoreCase(name)){
         setFontSize(value);
      }else if(PTY_FONT_WEIGHT.equalsIgnoreCase(name)){
         setFontWeight(value);
      }else if(PTY_OVERFLOW.equalsIgnoreCase(name)){
         setOverflow(value);
      }else if(PTY_OVERFLOW_X.equalsIgnoreCase(name)){
         setOverflowX(value);
      }else if(PTY_OVERFLOW_Y.equalsIgnoreCase(name)){
         setOverflowY(value);
      }else if(PTY_UNDERLINE_POSITION.equalsIgnoreCase(name)){
         setUnderlinePosition(value);
      }else if(PTY_TEXT_ALIGN.equalsIgnoreCase(name)){
         setTextAlign(value);
      }else if(PTY_TEXT_TRANSFORM.equalsIgnoreCase(name)){
         setTextTransform(value);
      }else if(PTY_TABLE_LAYOUT.equalsIgnoreCase(name)){
         setTableLayout(value);
      }else if(PTY_Z_INDEX.equalsIgnoreCase(name)){
         setZIndex(value);
      }else if(PTY_PARENT_NAME.equalsIgnoreCase(name)){
         setParentName(value);
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
         if(config.contains("parent_style")){
            setParentStyle(config.get(PTY_PARENT_STYLE));
         }
         if(config.contains("note")){
            _note.unpack(config.get(PTY_NOTE));
         }
         if(config.contains("color")){
            setColor(config.get(PTY_COLOR));
         }
         if(config.contains("background_color")){
            setBackgroundColor(config.get(PTY_BACKGROUND_COLOR));
         }
         if(config.contains("background_image")){
            setBackgroundImage(config.get(PTY_BACKGROUND_IMAGE));
         }
         if(config.contains("background_position")){
            setBackgroundPosition(config.get(PTY_BACKGROUND_POSITION));
         }
         if(config.contains("background_position_x")){
            setBackgroundPositionX(config.get(PTY_BACKGROUND_POSITION_X));
         }
         if(config.contains("background_position_y")){
            setBackgroundPositionY(config.get(PTY_BACKGROUND_POSITION_Y));
         }
         if(config.contains("background_repeat")){
            setBackgroundRepeat(config.get(PTY_BACKGROUND_REPEAT));
         }
         if(config.contains("cursor")){
            setCursor(config.get(PTY_CURSOR));
         }
         if(config.contains("vertical_align")){
            setVerticalAlign(config.get(PTY_VERTICAL_ALIGN));
         }
         if(config.contains("width")){
            setWidth(config.get(PTY_WIDTH));
         }
         if(config.contains("height")){
            setHeight(config.get(PTY_HEIGHT));
         }
         if(config.contains("position")){
            setPosition(config.get(PTY_POSITION));
         }
         if(config.contains("left")){
            setLeft(config.get(PTY_LEFT));
         }
         if(config.contains("top")){
            setTop(config.get(PTY_TOP));
         }
         if(config.contains("border_width")){
            setBorderWidth(config.get(PTY_BORDER_WIDTH));
         }
         if(config.contains("border_style")){
            setBorderStyle(config.get(PTY_BORDER_STYLE));
         }
         if(config.contains("border_color")){
            setBorderColor(config.get(PTY_BORDER_COLOR));
         }
         if(config.contains("border_left_width")){
            setBorderLeftWidth(config.get(PTY_BORDER_LEFT_WIDTH));
         }
         if(config.contains("border_left_style")){
            setBorderLeftStyle(config.get(PTY_BORDER_LEFT_STYLE));
         }
         if(config.contains("border_left_color")){
            setBorderLeftColor(config.get(PTY_BORDER_LEFT_COLOR));
         }
         if(config.contains("border_top_width")){
            setBorderTopWidth(config.get(PTY_BORDER_TOP_WIDTH));
         }
         if(config.contains("border_top_style")){
            setBorderTopStyle(config.get(PTY_BORDER_TOP_STYLE));
         }
         if(config.contains("border_top_color")){
            setBorderTopColor(config.get(PTY_BORDER_TOP_COLOR));
         }
         if(config.contains("border_right_width")){
            setBorderRightWidth(config.get(PTY_BORDER_RIGHT_WIDTH));
         }
         if(config.contains("border_right_style")){
            setBorderRightStyle(config.get(PTY_BORDER_RIGHT_STYLE));
         }
         if(config.contains("border_right_color")){
            setBorderRightColor(config.get(PTY_BORDER_RIGHT_COLOR));
         }
         if(config.contains("border_bottom_width")){
            setBorderBottomWidth(config.get(PTY_BORDER_BOTTOM_WIDTH));
         }
         if(config.contains("border_bottom_style")){
            setBorderBottomStyle(config.get(PTY_BORDER_BOTTOM_STYLE));
         }
         if(config.contains("border_bottom_color")){
            setBorderBottomColor(config.get(PTY_BORDER_BOTTOM_COLOR));
         }
         if(config.contains("padding")){
            setPadding(config.get(PTY_PADDING));
         }
         if(config.contains("padding_left")){
            setPaddingLeft(config.get(PTY_PADDING_LEFT));
         }
         if(config.contains("padding_top")){
            setPaddingTop(config.get(PTY_PADDING_TOP));
         }
         if(config.contains("padding_right")){
            setPaddingRight(config.get(PTY_PADDING_RIGHT));
         }
         if(config.contains("padding_bottom")){
            setPaddingBottom(config.get(PTY_PADDING_BOTTOM));
         }
         if(config.contains("margin")){
            setMargin(config.get(PTY_MARGIN));
         }
         if(config.contains("margin_left")){
            setMarginLeft(config.get(PTY_MARGIN_LEFT));
         }
         if(config.contains("margin_top")){
            setMarginTop(config.get(PTY_MARGIN_TOP));
         }
         if(config.contains("margin_right")){
            setMarginRight(config.get(PTY_MARGIN_RIGHT));
         }
         if(config.contains("margin_bottom")){
            setMarginBottom(config.get(PTY_MARGIN_BOTTOM));
         }
         if(config.contains("white_space")){
            setWhiteSpace(config.get(PTY_WHITE_SPACE));
         }
         if(config.contains("line_height")){
            setLineHeight(config.get(PTY_LINE_HEIGHT));
         }
         if(config.contains("font_family")){
            setFontFamily(config.get(PTY_FONT_FAMILY));
         }
         if(config.contains("font_size")){
            setFontSize(config.get(PTY_FONT_SIZE));
         }
         if(config.contains("font_weight")){
            setFontWeight(config.get(PTY_FONT_WEIGHT));
         }
         if(config.contains("overflow")){
            setOverflow(config.get(PTY_OVERFLOW));
         }
         if(config.contains("overflow_x")){
            setOverflowX(config.get(PTY_OVERFLOW_X));
         }
         if(config.contains("overflow_y")){
            setOverflowY(config.get(PTY_OVERFLOW_Y));
         }
         if(config.contains("underline_position")){
            setUnderlinePosition(config.get(PTY_UNDERLINE_POSITION));
         }
         if(config.contains("text_align")){
            setTextAlign(config.get(PTY_TEXT_ALIGN));
         }
         if(config.contains("text_transform")){
            setTextTransform(config.get(PTY_TEXT_TRANSFORM));
         }
         if(config.contains("table_layout")){
            setTableLayout(config.get(PTY_TABLE_LAYOUT));
         }
         if(config.contains("z_index")){
            setZIndex(config.get(PTY_Z_INDEX));
         }
         if(config.contains("parent_name")){
            setParentName(config.get(PTY_PARENT_NAME));
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
         if(config.contains("parent_style")){
            setParentStyle(config.get(PTY_PARENT_STYLE));
         }
         if(config.contains("note")){
            setNote(config.get(PTY_NOTE));
         }
         if(config.contains("color")){
            setColor(config.get(PTY_COLOR));
         }
         if(config.contains("background_color")){
            setBackgroundColor(config.get(PTY_BACKGROUND_COLOR));
         }
         if(config.contains("background_image")){
            setBackgroundImage(config.get(PTY_BACKGROUND_IMAGE));
         }
         if(config.contains("background_position")){
            setBackgroundPosition(config.get(PTY_BACKGROUND_POSITION));
         }
         if(config.contains("background_position_x")){
            setBackgroundPositionX(config.get(PTY_BACKGROUND_POSITION_X));
         }
         if(config.contains("background_position_y")){
            setBackgroundPositionY(config.get(PTY_BACKGROUND_POSITION_Y));
         }
         if(config.contains("background_repeat")){
            setBackgroundRepeat(config.get(PTY_BACKGROUND_REPEAT));
         }
         if(config.contains("cursor")){
            setCursor(config.get(PTY_CURSOR));
         }
         if(config.contains("vertical_align")){
            setVerticalAlign(config.get(PTY_VERTICAL_ALIGN));
         }
         if(config.contains("width")){
            setWidth(config.get(PTY_WIDTH));
         }
         if(config.contains("height")){
            setHeight(config.get(PTY_HEIGHT));
         }
         if(config.contains("position")){
            setPosition(config.get(PTY_POSITION));
         }
         if(config.contains("left")){
            setLeft(config.get(PTY_LEFT));
         }
         if(config.contains("top")){
            setTop(config.get(PTY_TOP));
         }
         if(config.contains("border")){
            setBorder(config.get(PTY_BORDER));
         }
         if(config.contains("border_width")){
            setBorderWidth(config.get(PTY_BORDER_WIDTH));
         }
         if(config.contains("border_style")){
            setBorderStyle(config.get(PTY_BORDER_STYLE));
         }
         if(config.contains("border_color")){
            setBorderColor(config.get(PTY_BORDER_COLOR));
         }
         if(config.contains("border_left")){
            setBorderLeft(config.get(PTY_BORDER_LEFT));
         }
         if(config.contains("border_left_width")){
            setBorderLeftWidth(config.get(PTY_BORDER_LEFT_WIDTH));
         }
         if(config.contains("border_left_style")){
            setBorderLeftStyle(config.get(PTY_BORDER_LEFT_STYLE));
         }
         if(config.contains("border_left_color")){
            setBorderLeftColor(config.get(PTY_BORDER_LEFT_COLOR));
         }
         if(config.contains("border_top")){
            setBorderTop(config.get(PTY_BORDER_TOP));
         }
         if(config.contains("border_top_width")){
            setBorderTopWidth(config.get(PTY_BORDER_TOP_WIDTH));
         }
         if(config.contains("border_top_style")){
            setBorderTopStyle(config.get(PTY_BORDER_TOP_STYLE));
         }
         if(config.contains("border_top_color")){
            setBorderTopColor(config.get(PTY_BORDER_TOP_COLOR));
         }
         if(config.contains("border_right")){
            setBorderRight(config.get(PTY_BORDER_RIGHT));
         }
         if(config.contains("border_right_width")){
            setBorderRightWidth(config.get(PTY_BORDER_RIGHT_WIDTH));
         }
         if(config.contains("border_right_style")){
            setBorderRightStyle(config.get(PTY_BORDER_RIGHT_STYLE));
         }
         if(config.contains("border_right_color")){
            setBorderRightColor(config.get(PTY_BORDER_RIGHT_COLOR));
         }
         if(config.contains("border_bottom")){
            setBorderBottom(config.get(PTY_BORDER_BOTTOM));
         }
         if(config.contains("border_bottom_width")){
            setBorderBottomWidth(config.get(PTY_BORDER_BOTTOM_WIDTH));
         }
         if(config.contains("border_bottom_style")){
            setBorderBottomStyle(config.get(PTY_BORDER_BOTTOM_STYLE));
         }
         if(config.contains("border_bottom_color")){
            setBorderBottomColor(config.get(PTY_BORDER_BOTTOM_COLOR));
         }
         if(config.contains("padding")){
            setPadding(config.get(PTY_PADDING));
         }
         if(config.contains("padding_left")){
            setPaddingLeft(config.get(PTY_PADDING_LEFT));
         }
         if(config.contains("padding_top")){
            setPaddingTop(config.get(PTY_PADDING_TOP));
         }
         if(config.contains("padding_right")){
            setPaddingRight(config.get(PTY_PADDING_RIGHT));
         }
         if(config.contains("padding_bottom")){
            setPaddingBottom(config.get(PTY_PADDING_BOTTOM));
         }
         if(config.contains("margin")){
            setMargin(config.get(PTY_MARGIN));
         }
         if(config.contains("margin_left")){
            setMarginLeft(config.get(PTY_MARGIN_LEFT));
         }
         if(config.contains("margin_top")){
            setMarginTop(config.get(PTY_MARGIN_TOP));
         }
         if(config.contains("margin_right")){
            setMarginRight(config.get(PTY_MARGIN_RIGHT));
         }
         if(config.contains("margin_bottom")){
            setMarginBottom(config.get(PTY_MARGIN_BOTTOM));
         }
         if(config.contains("white_space")){
            setWhiteSpace(config.get(PTY_WHITE_SPACE));
         }
         if(config.contains("line_height")){
            setLineHeight(config.get(PTY_LINE_HEIGHT));
         }
         if(config.contains("font_family")){
            setFontFamily(config.get(PTY_FONT_FAMILY));
         }
         if(config.contains("font_size")){
            setFontSize(config.get(PTY_FONT_SIZE));
         }
         if(config.contains("font_weight")){
            setFontWeight(config.get(PTY_FONT_WEIGHT));
         }
         if(config.contains("overflow")){
            setOverflow(config.get(PTY_OVERFLOW));
         }
         if(config.contains("overflow_x")){
            setOverflowX(config.get(PTY_OVERFLOW_X));
         }
         if(config.contains("overflow_y")){
            setOverflowY(config.get(PTY_OVERFLOW_Y));
         }
         if(config.contains("underline_position")){
            setUnderlinePosition(config.get(PTY_UNDERLINE_POSITION));
         }
         if(config.contains("text_align")){
            setTextAlign(config.get(PTY_TEXT_ALIGN));
         }
         if(config.contains("text_transform")){
            setTextTransform(config.get(PTY_TEXT_TRANSFORM));
         }
         if(config.contains("table_layout")){
            setTableLayout(config.get(PTY_TABLE_LAYOUT));
         }
         if(config.contains("z_index")){
            setZIndex(config.get(PTY_Z_INDEX));
         }
         if(config.contains("parent_name")){
            setParentName(config.get(PTY_PARENT_NAME));
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
         if(config.contains("parent_style")){
            setParentStyle(config.get(PTY_PARENT_STYLE));
         }
         if(config.contains("note")){
            setNote(config.get(PTY_NOTE));
         }
         if(config.contains("color")){
            setColor(config.get(PTY_COLOR));
         }
         if(config.contains("background_color")){
            setBackgroundColor(config.get(PTY_BACKGROUND_COLOR));
         }
         if(config.contains("background_image")){
            setBackgroundImage(config.get(PTY_BACKGROUND_IMAGE));
         }
         if(config.contains("background_position")){
            setBackgroundPosition(config.get(PTY_BACKGROUND_POSITION));
         }
         if(config.contains("background_position_x")){
            setBackgroundPositionX(config.get(PTY_BACKGROUND_POSITION_X));
         }
         if(config.contains("background_position_y")){
            setBackgroundPositionY(config.get(PTY_BACKGROUND_POSITION_Y));
         }
         if(config.contains("background_repeat")){
            setBackgroundRepeat(config.get(PTY_BACKGROUND_REPEAT));
         }
         if(config.contains("cursor")){
            setCursor(config.get(PTY_CURSOR));
         }
         if(config.contains("vertical_align")){
            setVerticalAlign(config.get(PTY_VERTICAL_ALIGN));
         }
         if(config.contains("width")){
            setWidth(config.get(PTY_WIDTH));
         }
         if(config.contains("height")){
            setHeight(config.get(PTY_HEIGHT));
         }
         if(config.contains("position")){
            setPosition(config.get(PTY_POSITION));
         }
         if(config.contains("left")){
            setLeft(config.get(PTY_LEFT));
         }
         if(config.contains("top")){
            setTop(config.get(PTY_TOP));
         }
         if(config.contains("border")){
            setBorder(config.get(PTY_BORDER));
         }
         if(config.contains("border_width")){
            setBorderWidth(config.get(PTY_BORDER_WIDTH));
         }
         if(config.contains("border_style")){
            setBorderStyle(config.get(PTY_BORDER_STYLE));
         }
         if(config.contains("border_color")){
            setBorderColor(config.get(PTY_BORDER_COLOR));
         }
         if(config.contains("border_left")){
            setBorderLeft(config.get(PTY_BORDER_LEFT));
         }
         if(config.contains("border_left_width")){
            setBorderLeftWidth(config.get(PTY_BORDER_LEFT_WIDTH));
         }
         if(config.contains("border_left_style")){
            setBorderLeftStyle(config.get(PTY_BORDER_LEFT_STYLE));
         }
         if(config.contains("border_left_color")){
            setBorderLeftColor(config.get(PTY_BORDER_LEFT_COLOR));
         }
         if(config.contains("border_top")){
            setBorderTop(config.get(PTY_BORDER_TOP));
         }
         if(config.contains("border_top_width")){
            setBorderTopWidth(config.get(PTY_BORDER_TOP_WIDTH));
         }
         if(config.contains("border_top_style")){
            setBorderTopStyle(config.get(PTY_BORDER_TOP_STYLE));
         }
         if(config.contains("border_top_color")){
            setBorderTopColor(config.get(PTY_BORDER_TOP_COLOR));
         }
         if(config.contains("border_right")){
            setBorderRight(config.get(PTY_BORDER_RIGHT));
         }
         if(config.contains("border_right_width")){
            setBorderRightWidth(config.get(PTY_BORDER_RIGHT_WIDTH));
         }
         if(config.contains("border_right_style")){
            setBorderRightStyle(config.get(PTY_BORDER_RIGHT_STYLE));
         }
         if(config.contains("border_right_color")){
            setBorderRightColor(config.get(PTY_BORDER_RIGHT_COLOR));
         }
         if(config.contains("border_bottom")){
            setBorderBottom(config.get(PTY_BORDER_BOTTOM));
         }
         if(config.contains("border_bottom_width")){
            setBorderBottomWidth(config.get(PTY_BORDER_BOTTOM_WIDTH));
         }
         if(config.contains("border_bottom_style")){
            setBorderBottomStyle(config.get(PTY_BORDER_BOTTOM_STYLE));
         }
         if(config.contains("border_bottom_color")){
            setBorderBottomColor(config.get(PTY_BORDER_BOTTOM_COLOR));
         }
         if(config.contains("padding")){
            setPadding(config.get(PTY_PADDING));
         }
         if(config.contains("padding_left")){
            setPaddingLeft(config.get(PTY_PADDING_LEFT));
         }
         if(config.contains("padding_top")){
            setPaddingTop(config.get(PTY_PADDING_TOP));
         }
         if(config.contains("padding_right")){
            setPaddingRight(config.get(PTY_PADDING_RIGHT));
         }
         if(config.contains("padding_bottom")){
            setPaddingBottom(config.get(PTY_PADDING_BOTTOM));
         }
         if(config.contains("margin")){
            setMargin(config.get(PTY_MARGIN));
         }
         if(config.contains("margin_left")){
            setMarginLeft(config.get(PTY_MARGIN_LEFT));
         }
         if(config.contains("margin_top")){
            setMarginTop(config.get(PTY_MARGIN_TOP));
         }
         if(config.contains("margin_right")){
            setMarginRight(config.get(PTY_MARGIN_RIGHT));
         }
         if(config.contains("margin_bottom")){
            setMarginBottom(config.get(PTY_MARGIN_BOTTOM));
         }
         if(config.contains("white_space")){
            setWhiteSpace(config.get(PTY_WHITE_SPACE));
         }
         if(config.contains("line_height")){
            setLineHeight(config.get(PTY_LINE_HEIGHT));
         }
         if(config.contains("font_family")){
            setFontFamily(config.get(PTY_FONT_FAMILY));
         }
         if(config.contains("font_size")){
            setFontSize(config.get(PTY_FONT_SIZE));
         }
         if(config.contains("font_weight")){
            setFontWeight(config.get(PTY_FONT_WEIGHT));
         }
         if(config.contains("overflow")){
            setOverflow(config.get(PTY_OVERFLOW));
         }
         if(config.contains("overflow_x")){
            setOverflowX(config.get(PTY_OVERFLOW_X));
         }
         if(config.contains("overflow_y")){
            setOverflowY(config.get(PTY_OVERFLOW_Y));
         }
         if(config.contains("underline_position")){
            setUnderlinePosition(config.get(PTY_UNDERLINE_POSITION));
         }
         if(config.contains("text_align")){
            setTextAlign(config.get(PTY_TEXT_ALIGN));
         }
         if(config.contains("text_transform")){
            setTextTransform(config.get(PTY_TEXT_TRANSFORM));
         }
         if(config.contains("table_layout")){
            setTableLayout(config.get(PTY_TABLE_LAYOUT));
         }
         if(config.contains("z_index")){
            setZIndex(config.get(PTY_Z_INDEX));
         }
         if(config.contains("parent_name")){
            setParentName(config.get(PTY_PARENT_NAME));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getParentStyle())){
            if(config.contains("parent_style")){
               setParentStyle(config.get(PTY_PARENT_STYLE));
            }
         }
         if(RString.isEmpty(getFontWeight())){
            if(config.contains("font_weight")){
               setFontWeight(config.get(PTY_FONT_WEIGHT));
            }
         }
         if(RString.isEmpty(getOverflowX())){
            if(config.contains("overflow_x")){
               setOverflowX(config.get(PTY_OVERFLOW_X));
            }
         }
         if(RString.isEmpty(getOverflowY())){
            if(config.contains("overflow_y")){
               setOverflowY(config.get(PTY_OVERFLOW_Y));
            }
         }
         if(RString.isEmpty(getTableLayout())){
            if(config.contains("table_layout")){
               setTableLayout(config.get(PTY_TABLE_LAYOUT));
            }
         }
         if(RString.isEmpty(getZIndex())){
            if(config.contains("z_index")){
               setZIndex(config.get(PTY_Z_INDEX));
            }
         }
         if(RString.isEmpty(getParentName())){
            if(config.contains("parent_name")){
               setParentName(config.get(PTY_PARENT_NAME));
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
         if(RString.isNotEmpty(getParentStyle())){
            config.set(PTY_PARENT_STYLE, getParentStyle());
         }
         String note = _note.pack().toString();
         if(RString.isNotEmpty(note)){
            config.set(PTY_NOTE, note);
         }
         if(RString.isNotEmpty(getColor())){
            config.set(PTY_COLOR, getColor());
         }
         if(RString.isNotEmpty(getBackgroundColor())){
            config.set(PTY_BACKGROUND_COLOR, getBackgroundColor());
         }
         if(RString.isNotEmpty(getBackgroundImage())){
            config.set(PTY_BACKGROUND_IMAGE, getBackgroundImage());
         }
         if(RString.isNotEmpty(getBackgroundPosition())){
            config.set(PTY_BACKGROUND_POSITION, getBackgroundPosition());
         }
         if(RString.isNotEmpty(getBackgroundPositionX())){
            config.set(PTY_BACKGROUND_POSITION_X, getBackgroundPositionX());
         }
         if(RString.isNotEmpty(getBackgroundPositionY())){
            config.set(PTY_BACKGROUND_POSITION_Y, getBackgroundPositionY());
         }
         if(RString.isNotEmpty(getBackgroundRepeat())){
            config.set(PTY_BACKGROUND_REPEAT, getBackgroundRepeat());
         }
         if(RString.isNotEmpty(getCursor())){
            config.set(PTY_CURSOR, getCursor());
         }
         if(RString.isNotEmpty(getVerticalAlign())){
            config.set(PTY_VERTICAL_ALIGN, getVerticalAlign());
         }
         if(RString.isNotEmpty(getWidth())){
            config.set(PTY_WIDTH, getWidth());
         }
         if(RString.isNotEmpty(getHeight())){
            config.set(PTY_HEIGHT, getHeight());
         }
         if(RString.isNotEmpty(getPosition())){
            config.set(PTY_POSITION, getPosition());
         }
         if(RString.isNotEmpty(getLeft())){
            config.set(PTY_LEFT, getLeft());
         }
         if(RString.isNotEmpty(getTop())){
            config.set(PTY_TOP, getTop());
         }
         if(RString.isNotEmpty(getBorderWidth())){
            config.set(PTY_BORDER_WIDTH, getBorderWidth());
         }
         if(RString.isNotEmpty(getBorderStyle())){
            config.set(PTY_BORDER_STYLE, getBorderStyle());
         }
         if(RString.isNotEmpty(getBorderColor())){
            config.set(PTY_BORDER_COLOR, getBorderColor());
         }
         if(RString.isNotEmpty(getBorderLeftWidth())){
            config.set(PTY_BORDER_LEFT_WIDTH, getBorderLeftWidth());
         }
         if(RString.isNotEmpty(getBorderLeftStyle())){
            config.set(PTY_BORDER_LEFT_STYLE, getBorderLeftStyle());
         }
         if(RString.isNotEmpty(getBorderLeftColor())){
            config.set(PTY_BORDER_LEFT_COLOR, getBorderLeftColor());
         }
         if(RString.isNotEmpty(getBorderTopWidth())){
            config.set(PTY_BORDER_TOP_WIDTH, getBorderTopWidth());
         }
         if(RString.isNotEmpty(getBorderTopStyle())){
            config.set(PTY_BORDER_TOP_STYLE, getBorderTopStyle());
         }
         if(RString.isNotEmpty(getBorderTopColor())){
            config.set(PTY_BORDER_TOP_COLOR, getBorderTopColor());
         }
         if(RString.isNotEmpty(getBorderRightWidth())){
            config.set(PTY_BORDER_RIGHT_WIDTH, getBorderRightWidth());
         }
         if(RString.isNotEmpty(getBorderRightStyle())){
            config.set(PTY_BORDER_RIGHT_STYLE, getBorderRightStyle());
         }
         if(RString.isNotEmpty(getBorderRightColor())){
            config.set(PTY_BORDER_RIGHT_COLOR, getBorderRightColor());
         }
         if(RString.isNotEmpty(getBorderBottomWidth())){
            config.set(PTY_BORDER_BOTTOM_WIDTH, getBorderBottomWidth());
         }
         if(RString.isNotEmpty(getBorderBottomStyle())){
            config.set(PTY_BORDER_BOTTOM_STYLE, getBorderBottomStyle());
         }
         if(RString.isNotEmpty(getBorderBottomColor())){
            config.set(PTY_BORDER_BOTTOM_COLOR, getBorderBottomColor());
         }
         if(RString.isNotEmpty(getPadding())){
            config.set(PTY_PADDING, getPadding());
         }
         if(RString.isNotEmpty(getPaddingLeft())){
            config.set(PTY_PADDING_LEFT, getPaddingLeft());
         }
         if(RString.isNotEmpty(getPaddingTop())){
            config.set(PTY_PADDING_TOP, getPaddingTop());
         }
         if(RString.isNotEmpty(getPaddingRight())){
            config.set(PTY_PADDING_RIGHT, getPaddingRight());
         }
         if(RString.isNotEmpty(getPaddingBottom())){
            config.set(PTY_PADDING_BOTTOM, getPaddingBottom());
         }
         if(RString.isNotEmpty(getMargin())){
            config.set(PTY_MARGIN, getMargin());
         }
         if(RString.isNotEmpty(getMarginLeft())){
            config.set(PTY_MARGIN_LEFT, getMarginLeft());
         }
         if(RString.isNotEmpty(getMarginTop())){
            config.set(PTY_MARGIN_TOP, getMarginTop());
         }
         if(RString.isNotEmpty(getMarginRight())){
            config.set(PTY_MARGIN_RIGHT, getMarginRight());
         }
         if(RString.isNotEmpty(getMarginBottom())){
            config.set(PTY_MARGIN_BOTTOM, getMarginBottom());
         }
         if(RString.isNotEmpty(getWhiteSpace())){
            config.set(PTY_WHITE_SPACE, getWhiteSpace());
         }
         if(RString.isNotEmpty(getLineHeight())){
            config.set(PTY_LINE_HEIGHT, getLineHeight());
         }
         if(RString.isNotEmpty(getFontFamily())){
            config.set(PTY_FONT_FAMILY, getFontFamily());
         }
         if(RString.isNotEmpty(getFontSize())){
            config.set(PTY_FONT_SIZE, getFontSize());
         }
         if(RString.isNotEmpty(getFontWeight())){
            config.set(PTY_FONT_WEIGHT, getFontWeight());
         }
         if(RString.isNotEmpty(getOverflow())){
            config.set(PTY_OVERFLOW, getOverflow());
         }
         if(RString.isNotEmpty(getOverflowX())){
            config.set(PTY_OVERFLOW_X, getOverflowX());
         }
         if(RString.isNotEmpty(getOverflowY())){
            config.set(PTY_OVERFLOW_Y, getOverflowY());
         }
         if(RString.isNotEmpty(getUnderlinePosition())){
            config.set(PTY_UNDERLINE_POSITION, getUnderlinePosition());
         }
         if(RString.isNotEmpty(getTextAlign())){
            config.set(PTY_TEXT_ALIGN, getTextAlign());
         }
         if(RString.isNotEmpty(getTextTransform())){
            config.set(PTY_TEXT_TRANSFORM, getTextTransform());
         }
         if(RString.isNotEmpty(getTableLayout())){
            config.set(PTY_TABLE_LAYOUT, getTableLayout());
         }
         if(RString.isNotEmpty(getZIndex())){
            config.set(PTY_Z_INDEX, getZIndex());
         }
         if(RString.isNotEmpty(getParentName())){
            config.set(PTY_PARENT_NAME, getParentName());
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
         if(RString.isNotEmpty(getParentStyle())){
            config.set(PTY_PARENT_STYLE, getParentStyle());
         }
         if(RString.isNotEmpty(getNote())){
            config.set(PTY_NOTE, getNote());
         }
         if(RString.isNotEmpty(getColor())){
            config.set(PTY_COLOR, getColor());
         }
         if(RString.isNotEmpty(getBackgroundColor())){
            config.set(PTY_BACKGROUND_COLOR, getBackgroundColor());
         }
         if(RString.isNotEmpty(getBackgroundImage())){
            config.set(PTY_BACKGROUND_IMAGE, getBackgroundImage());
         }
         if(RString.isNotEmpty(getBackgroundPosition())){
            config.set(PTY_BACKGROUND_POSITION, getBackgroundPosition());
         }
         if(RString.isNotEmpty(getBackgroundPositionX())){
            config.set(PTY_BACKGROUND_POSITION_X, getBackgroundPositionX());
         }
         if(RString.isNotEmpty(getBackgroundPositionY())){
            config.set(PTY_BACKGROUND_POSITION_Y, getBackgroundPositionY());
         }
         if(RString.isNotEmpty(getBackgroundRepeat())){
            config.set(PTY_BACKGROUND_REPEAT, getBackgroundRepeat());
         }
         if(RString.isNotEmpty(getCursor())){
            config.set(PTY_CURSOR, getCursor());
         }
         if(RString.isNotEmpty(getVerticalAlign())){
            config.set(PTY_VERTICAL_ALIGN, getVerticalAlign());
         }
         if(RString.isNotEmpty(getWidth())){
            config.set(PTY_WIDTH, getWidth());
         }
         if(RString.isNotEmpty(getHeight())){
            config.set(PTY_HEIGHT, getHeight());
         }
         if(RString.isNotEmpty(getPosition())){
            config.set(PTY_POSITION, getPosition());
         }
         if(RString.isNotEmpty(getLeft())){
            config.set(PTY_LEFT, getLeft());
         }
         if(RString.isNotEmpty(getTop())){
            config.set(PTY_TOP, getTop());
         }
         if(RString.isNotEmpty(getBorder())){
            config.set(PTY_BORDER, getBorder());
         }
         if(RString.isNotEmpty(getBorderWidth())){
            config.set(PTY_BORDER_WIDTH, getBorderWidth());
         }
         if(RString.isNotEmpty(getBorderStyle())){
            config.set(PTY_BORDER_STYLE, getBorderStyle());
         }
         if(RString.isNotEmpty(getBorderColor())){
            config.set(PTY_BORDER_COLOR, getBorderColor());
         }
         if(RString.isNotEmpty(getBorderLeft())){
            config.set(PTY_BORDER_LEFT, getBorderLeft());
         }
         if(RString.isNotEmpty(getBorderLeftWidth())){
            config.set(PTY_BORDER_LEFT_WIDTH, getBorderLeftWidth());
         }
         if(RString.isNotEmpty(getBorderLeftStyle())){
            config.set(PTY_BORDER_LEFT_STYLE, getBorderLeftStyle());
         }
         if(RString.isNotEmpty(getBorderLeftColor())){
            config.set(PTY_BORDER_LEFT_COLOR, getBorderLeftColor());
         }
         if(RString.isNotEmpty(getBorderTop())){
            config.set(PTY_BORDER_TOP, getBorderTop());
         }
         if(RString.isNotEmpty(getBorderTopWidth())){
            config.set(PTY_BORDER_TOP_WIDTH, getBorderTopWidth());
         }
         if(RString.isNotEmpty(getBorderTopStyle())){
            config.set(PTY_BORDER_TOP_STYLE, getBorderTopStyle());
         }
         if(RString.isNotEmpty(getBorderTopColor())){
            config.set(PTY_BORDER_TOP_COLOR, getBorderTopColor());
         }
         if(RString.isNotEmpty(getBorderRight())){
            config.set(PTY_BORDER_RIGHT, getBorderRight());
         }
         if(RString.isNotEmpty(getBorderRightWidth())){
            config.set(PTY_BORDER_RIGHT_WIDTH, getBorderRightWidth());
         }
         if(RString.isNotEmpty(getBorderRightStyle())){
            config.set(PTY_BORDER_RIGHT_STYLE, getBorderRightStyle());
         }
         if(RString.isNotEmpty(getBorderRightColor())){
            config.set(PTY_BORDER_RIGHT_COLOR, getBorderRightColor());
         }
         if(RString.isNotEmpty(getBorderBottom())){
            config.set(PTY_BORDER_BOTTOM, getBorderBottom());
         }
         if(RString.isNotEmpty(getBorderBottomWidth())){
            config.set(PTY_BORDER_BOTTOM_WIDTH, getBorderBottomWidth());
         }
         if(RString.isNotEmpty(getBorderBottomStyle())){
            config.set(PTY_BORDER_BOTTOM_STYLE, getBorderBottomStyle());
         }
         if(RString.isNotEmpty(getBorderBottomColor())){
            config.set(PTY_BORDER_BOTTOM_COLOR, getBorderBottomColor());
         }
         if(RString.isNotEmpty(getPadding())){
            config.set(PTY_PADDING, getPadding());
         }
         if(RString.isNotEmpty(getPaddingLeft())){
            config.set(PTY_PADDING_LEFT, getPaddingLeft());
         }
         if(RString.isNotEmpty(getPaddingTop())){
            config.set(PTY_PADDING_TOP, getPaddingTop());
         }
         if(RString.isNotEmpty(getPaddingRight())){
            config.set(PTY_PADDING_RIGHT, getPaddingRight());
         }
         if(RString.isNotEmpty(getPaddingBottom())){
            config.set(PTY_PADDING_BOTTOM, getPaddingBottom());
         }
         if(RString.isNotEmpty(getMargin())){
            config.set(PTY_MARGIN, getMargin());
         }
         if(RString.isNotEmpty(getMarginLeft())){
            config.set(PTY_MARGIN_LEFT, getMarginLeft());
         }
         if(RString.isNotEmpty(getMarginTop())){
            config.set(PTY_MARGIN_TOP, getMarginTop());
         }
         if(RString.isNotEmpty(getMarginRight())){
            config.set(PTY_MARGIN_RIGHT, getMarginRight());
         }
         if(RString.isNotEmpty(getMarginBottom())){
            config.set(PTY_MARGIN_BOTTOM, getMarginBottom());
         }
         if(RString.isNotEmpty(getWhiteSpace())){
            config.set(PTY_WHITE_SPACE, getWhiteSpace());
         }
         if(RString.isNotEmpty(getLineHeight())){
            config.set(PTY_LINE_HEIGHT, getLineHeight());
         }
         if(RString.isNotEmpty(getFontFamily())){
            config.set(PTY_FONT_FAMILY, getFontFamily());
         }
         if(RString.isNotEmpty(getFontSize())){
            config.set(PTY_FONT_SIZE, getFontSize());
         }
         if(RString.isNotEmpty(getFontWeight())){
            config.set(PTY_FONT_WEIGHT, getFontWeight());
         }
         if(RString.isNotEmpty(getOverflow())){
            config.set(PTY_OVERFLOW, getOverflow());
         }
         if(RString.isNotEmpty(getOverflowX())){
            config.set(PTY_OVERFLOW_X, getOverflowX());
         }
         if(RString.isNotEmpty(getOverflowY())){
            config.set(PTY_OVERFLOW_Y, getOverflowY());
         }
         if(RString.isNotEmpty(getUnderlinePosition())){
            config.set(PTY_UNDERLINE_POSITION, getUnderlinePosition());
         }
         if(RString.isNotEmpty(getTextAlign())){
            config.set(PTY_TEXT_ALIGN, getTextAlign());
         }
         if(RString.isNotEmpty(getTextTransform())){
            config.set(PTY_TEXT_TRANSFORM, getTextTransform());
         }
         if(RString.isNotEmpty(getTableLayout())){
            config.set(PTY_TABLE_LAYOUT, getTableLayout());
         }
         if(RString.isNotEmpty(getZIndex())){
            config.set(PTY_Z_INDEX, getZIndex());
         }
         if(RString.isNotEmpty(getParentName())){
            config.set(PTY_PARENT_NAME, getParentName());
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
         String sParentStyle = getParentStyle();
         if(RString.isNotEmpty(sParentStyle)){
            config.set(PTY_PARENT_STYLE, sParentStyle);
         }
         String sNote = getNote();
         if(RString.isNotEmpty(sNote)){
            config.set(PTY_NOTE, sNote);
         }
         String sColor = getColor();
         if(RString.isNotEmpty(sColor)){
            config.set(PTY_COLOR, sColor);
         }
         String sBackgroundColor = getBackgroundColor();
         if(RString.isNotEmpty(sBackgroundColor)){
            config.set(PTY_BACKGROUND_COLOR, sBackgroundColor);
         }
         String sBackgroundImage = getBackgroundImage();
         if(RString.isNotEmpty(sBackgroundImage)){
            config.set(PTY_BACKGROUND_IMAGE, sBackgroundImage);
         }
         String sBackgroundPosition = getBackgroundPosition();
         if(RString.isNotEmpty(sBackgroundPosition)){
            config.set(PTY_BACKGROUND_POSITION, sBackgroundPosition);
         }
         String sBackgroundPositionX = getBackgroundPositionX();
         if(RString.isNotEmpty(sBackgroundPositionX)){
            config.set(PTY_BACKGROUND_POSITION_X, sBackgroundPositionX);
         }
         String sBackgroundPositionY = getBackgroundPositionY();
         if(RString.isNotEmpty(sBackgroundPositionY)){
            config.set(PTY_BACKGROUND_POSITION_Y, sBackgroundPositionY);
         }
         String sBackgroundRepeat = getBackgroundRepeat();
         if(RString.isNotEmpty(sBackgroundRepeat)){
            config.set(PTY_BACKGROUND_REPEAT, sBackgroundRepeat);
         }
         String sCursor = getCursor();
         if(RString.isNotEmpty(sCursor)){
            config.set(PTY_CURSOR, sCursor);
         }
         String sVerticalAlign = getVerticalAlign();
         if(RString.isNotEmpty(sVerticalAlign)){
            config.set(PTY_VERTICAL_ALIGN, sVerticalAlign);
         }
         String sWidth = getWidth();
         if(RString.isNotEmpty(sWidth)){
            config.set(PTY_WIDTH, sWidth);
         }
         String sHeight = getHeight();
         if(RString.isNotEmpty(sHeight)){
            config.set(PTY_HEIGHT, sHeight);
         }
         String sPosition = getPosition();
         if(RString.isNotEmpty(sPosition)){
            config.set(PTY_POSITION, sPosition);
         }
         String sLeft = getLeft();
         if(RString.isNotEmpty(sLeft)){
            config.set(PTY_LEFT, sLeft);
         }
         String sTop = getTop();
         if(RString.isNotEmpty(sTop)){
            config.set(PTY_TOP, sTop);
         }
         String sBorder = getBorder();
         if(RString.isNotEmpty(sBorder)){
            config.set(PTY_BORDER, sBorder);
         }
         String sBorderWidth = getBorderWidth();
         if(RString.isNotEmpty(sBorderWidth)){
            config.set(PTY_BORDER_WIDTH, sBorderWidth);
         }
         String sBorderStyle = getBorderStyle();
         if(RString.isNotEmpty(sBorderStyle)){
            config.set(PTY_BORDER_STYLE, sBorderStyle);
         }
         String sBorderColor = getBorderColor();
         if(RString.isNotEmpty(sBorderColor)){
            config.set(PTY_BORDER_COLOR, sBorderColor);
         }
         String sBorderLeft = getBorderLeft();
         if(RString.isNotEmpty(sBorderLeft)){
            config.set(PTY_BORDER_LEFT, sBorderLeft);
         }
         String sBorderLeftWidth = getBorderLeftWidth();
         if(RString.isNotEmpty(sBorderLeftWidth)){
            config.set(PTY_BORDER_LEFT_WIDTH, sBorderLeftWidth);
         }
         String sBorderLeftStyle = getBorderLeftStyle();
         if(RString.isNotEmpty(sBorderLeftStyle)){
            config.set(PTY_BORDER_LEFT_STYLE, sBorderLeftStyle);
         }
         String sBorderLeftColor = getBorderLeftColor();
         if(RString.isNotEmpty(sBorderLeftColor)){
            config.set(PTY_BORDER_LEFT_COLOR, sBorderLeftColor);
         }
         String sBorderTop = getBorderTop();
         if(RString.isNotEmpty(sBorderTop)){
            config.set(PTY_BORDER_TOP, sBorderTop);
         }
         String sBorderTopWidth = getBorderTopWidth();
         if(RString.isNotEmpty(sBorderTopWidth)){
            config.set(PTY_BORDER_TOP_WIDTH, sBorderTopWidth);
         }
         String sBorderTopStyle = getBorderTopStyle();
         if(RString.isNotEmpty(sBorderTopStyle)){
            config.set(PTY_BORDER_TOP_STYLE, sBorderTopStyle);
         }
         String sBorderTopColor = getBorderTopColor();
         if(RString.isNotEmpty(sBorderTopColor)){
            config.set(PTY_BORDER_TOP_COLOR, sBorderTopColor);
         }
         String sBorderRight = getBorderRight();
         if(RString.isNotEmpty(sBorderRight)){
            config.set(PTY_BORDER_RIGHT, sBorderRight);
         }
         String sBorderRightWidth = getBorderRightWidth();
         if(RString.isNotEmpty(sBorderRightWidth)){
            config.set(PTY_BORDER_RIGHT_WIDTH, sBorderRightWidth);
         }
         String sBorderRightStyle = getBorderRightStyle();
         if(RString.isNotEmpty(sBorderRightStyle)){
            config.set(PTY_BORDER_RIGHT_STYLE, sBorderRightStyle);
         }
         String sBorderRightColor = getBorderRightColor();
         if(RString.isNotEmpty(sBorderRightColor)){
            config.set(PTY_BORDER_RIGHT_COLOR, sBorderRightColor);
         }
         String sBorderBottom = getBorderBottom();
         if(RString.isNotEmpty(sBorderBottom)){
            config.set(PTY_BORDER_BOTTOM, sBorderBottom);
         }
         String sBorderBottomWidth = getBorderBottomWidth();
         if(RString.isNotEmpty(sBorderBottomWidth)){
            config.set(PTY_BORDER_BOTTOM_WIDTH, sBorderBottomWidth);
         }
         String sBorderBottomStyle = getBorderBottomStyle();
         if(RString.isNotEmpty(sBorderBottomStyle)){
            config.set(PTY_BORDER_BOTTOM_STYLE, sBorderBottomStyle);
         }
         String sBorderBottomColor = getBorderBottomColor();
         if(RString.isNotEmpty(sBorderBottomColor)){
            config.set(PTY_BORDER_BOTTOM_COLOR, sBorderBottomColor);
         }
         String sPadding = getPadding();
         if(RString.isNotEmpty(sPadding)){
            config.set(PTY_PADDING, sPadding);
         }
         String sPaddingLeft = getPaddingLeft();
         if(RString.isNotEmpty(sPaddingLeft)){
            config.set(PTY_PADDING_LEFT, sPaddingLeft);
         }
         String sPaddingTop = getPaddingTop();
         if(RString.isNotEmpty(sPaddingTop)){
            config.set(PTY_PADDING_TOP, sPaddingTop);
         }
         String sPaddingRight = getPaddingRight();
         if(RString.isNotEmpty(sPaddingRight)){
            config.set(PTY_PADDING_RIGHT, sPaddingRight);
         }
         String sPaddingBottom = getPaddingBottom();
         if(RString.isNotEmpty(sPaddingBottom)){
            config.set(PTY_PADDING_BOTTOM, sPaddingBottom);
         }
         String sMargin = getMargin();
         if(RString.isNotEmpty(sMargin)){
            config.set(PTY_MARGIN, sMargin);
         }
         String sMarginLeft = getMarginLeft();
         if(RString.isNotEmpty(sMarginLeft)){
            config.set(PTY_MARGIN_LEFT, sMarginLeft);
         }
         String sMarginTop = getMarginTop();
         if(RString.isNotEmpty(sMarginTop)){
            config.set(PTY_MARGIN_TOP, sMarginTop);
         }
         String sMarginRight = getMarginRight();
         if(RString.isNotEmpty(sMarginRight)){
            config.set(PTY_MARGIN_RIGHT, sMarginRight);
         }
         String sMarginBottom = getMarginBottom();
         if(RString.isNotEmpty(sMarginBottom)){
            config.set(PTY_MARGIN_BOTTOM, sMarginBottom);
         }
         String sWhiteSpace = getWhiteSpace();
         if(RString.isNotEmpty(sWhiteSpace)){
            config.set(PTY_WHITE_SPACE, sWhiteSpace);
         }
         String sLineHeight = getLineHeight();
         if(RString.isNotEmpty(sLineHeight)){
            config.set(PTY_LINE_HEIGHT, sLineHeight);
         }
         String sFontFamily = getFontFamily();
         if(RString.isNotEmpty(sFontFamily)){
            config.set(PTY_FONT_FAMILY, sFontFamily);
         }
         String sFontSize = getFontSize();
         if(RString.isNotEmpty(sFontSize)){
            config.set(PTY_FONT_SIZE, sFontSize);
         }
         String sFontWeight = getFontWeight();
         if(RString.isNotEmpty(sFontWeight)){
            config.set(PTY_FONT_WEIGHT, sFontWeight);
         }
         String sOverflow = getOverflow();
         if(RString.isNotEmpty(sOverflow)){
            config.set(PTY_OVERFLOW, sOverflow);
         }
         String sOverflowX = getOverflowX();
         if(RString.isNotEmpty(sOverflowX)){
            config.set(PTY_OVERFLOW_X, sOverflowX);
         }
         String sOverflowY = getOverflowY();
         if(RString.isNotEmpty(sOverflowY)){
            config.set(PTY_OVERFLOW_Y, sOverflowY);
         }
         String sUnderlinePosition = getUnderlinePosition();
         if(RString.isNotEmpty(sUnderlinePosition)){
            config.set(PTY_UNDERLINE_POSITION, sUnderlinePosition);
         }
         String sTextAlign = getTextAlign();
         if(RString.isNotEmpty(sTextAlign)){
            config.set(PTY_TEXT_ALIGN, sTextAlign);
         }
         String sTextTransform = getTextTransform();
         if(RString.isNotEmpty(sTextTransform)){
            config.set(PTY_TEXT_TRANSFORM, sTextTransform);
         }
         String sTableLayout = getTableLayout();
         if(RString.isNotEmpty(sTableLayout)){
            config.set(PTY_TABLE_LAYOUT, sTableLayout);
         }
         String sZIndex = getZIndex();
         if(RString.isNotEmpty(sZIndex)){
            config.set(PTY_Z_INDEX, sZIndex);
         }
         String sParentName = getParentName();
         if(RString.isNotEmpty(sParentName)){
            config.set(PTY_PARENT_NAME, sParentName);
         }
      }else if(EXmlConfig.Default == type){
         String sParentStyle = getParentStyle();
         if(RString.isNotEmpty(sParentStyle)){
            config.set(PTY_PARENT_STYLE, sParentStyle);
         }
         String sFontWeight = getFontWeight();
         if(RString.isNotEmpty(sFontWeight)){
            config.set(PTY_FONT_WEIGHT, sFontWeight);
         }
         String sOverflowX = getOverflowX();
         if(RString.isNotEmpty(sOverflowX)){
            config.set(PTY_OVERFLOW_X, sOverflowX);
         }
         String sOverflowY = getOverflowY();
         if(RString.isNotEmpty(sOverflowY)){
            config.set(PTY_OVERFLOW_Y, sOverflowY);
         }
         String sTableLayout = getTableLayout();
         if(RString.isNotEmpty(sTableLayout)){
            config.set(PTY_TABLE_LAYOUT, sTableLayout);
         }
         String sZIndex = getZIndex();
         if(RString.isNotEmpty(sZIndex)){
            config.set(PTY_Z_INDEX, sZIndex);
         }
         String sParentName = getParentName();
         if(RString.isNotEmpty(sParentName)){
            config.set(PTY_PARENT_NAME, sParentName);
         }
      }
   }
}