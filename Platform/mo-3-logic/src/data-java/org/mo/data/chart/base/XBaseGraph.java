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

//============================================================
// <T>图表1对象的XML节点基类。</T>
//============================================================
public abstract class XBaseGraph
      extends FXmlObject
      implements
         XObjectFace,
         XPadFace
{
   // 名称定义
   public static final String NAME = "Graph";

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

   // 标题的名称定义
   public static final String PTY_CAPTION = "caption";

   // 图表字体的名称定义
   public static final String PTY_BASEFONT = "baseFont";

   // 图表字号的名称定义
   public static final String PTY_BASEFONTSIZE = "baseFontSize";

   // 子标题的名称定义
   public static final String PTY_SUBCAPTION = "subCaption";

   // x轴名称的名称定义
   public static final String PTY_XAXISNAME = "xAxisName";

   // y轴名称的名称定义
   public static final String PTY_YAXISNAME = "yAxisName";

   // y轴最小值的名称定义
   public static final String PTY_YAXISMINVALUE = "yAxisMinValue";

   // y轴最大值的名称定义
   public static final String PTY_YAXISMAXVALUE = "yAxisMaxValue";

   // 数值精度的名称定义
   public static final String PTY_DECIMALPRECISION = "decimalPrecision";

   // 数字格式化的名称定义
   public static final String PTY_FORMATNUMBER = "formatNumber";

   // 显示名称的名称定义
   public static final String PTY_SHOWNAMES = "showNames";

   // 显示数值的名称定义
   public static final String PTY_SHOWVALUES = "showValues";

   // 的名称定义
   public static final String PTY_FORMATNUMBERSCALE = "formatNumberScale";

   // 鼠标悬停背景色的名称定义
   public static final String PTY_HOVERCAPBG = "hovercapbg";

   // 鼠标悬停边框色的名称定义
   public static final String PTY_HOVERCAPBORDER = "hovercapborder";

   // 水平线条数的名称定义
   public static final String PTY_NUMDIVLINES = "numdivlines";

   // 垂直线条数的名称定义
   public static final String PTY_NUMVDIVLINES = "numVdivlines";

   // 数据逻辑的名称定义
   public static final String PTY_DATA_LOGIC = "data_logic";

   // 背景颜色的名称定义
   public static final String PTY_BGCOLOR = "bgColor";

   // 画布背景色的名称定义
   public static final String PTY_CANVASBGCOLOR = "canvasBgColor";

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

   // 标题的定义
   @AName("caption")
   protected String _caption;

   // 图表字体的定义
   @AName("basefont")
   protected String _basefont;

   // 图表字号的定义
   @AName("basefontsize")
   protected String _basefontsize;

   // 子标题的定义
   @AName("subcaption")
   protected String _subcaption;

   // x轴名称的定义
   @AName("xaxisname")
   protected String _xaxisname;

   // y轴名称的定义
   @AName("yaxisname")
   protected String _yaxisname;

   // y轴最小值的定义
   @AName("yaxisminvalue")
   protected String _yaxisminvalue;

   // y轴最大值的定义
   @AName("yaxismaxvalue")
   protected String _yaxismaxvalue;

   // 数值精度的定义
   @AName("decimalprecision")
   protected String _decimalprecision;

   // 数字格式化的定义
   @AName("formatnumber")
   protected String _formatnumber;

   // 显示名称的定义
   @AName("shownames")
   protected String _shownames;

   // 显示数值的定义
   @AName("showvalues")
   protected String _showvalues;

   // 的定义
   @AName("formatnumberscale")
   protected String _formatnumberscale;

   // 鼠标悬停背景色的定义
   @AName("hovercapbg")
   protected String _hovercapbg;

   // 鼠标悬停边框色的定义
   @AName("hovercapborder")
   protected String _hovercapborder;

   // 水平线条数的定义
   @AName("numdivlines")
   protected String _numdivlines;

   // 垂直线条数的定义
   @AName("numvdivlines")
   protected String _numvdivlines;

   // 数据逻辑的定义
   @AName("data_logic")
   protected String _dataLogic;

   // 背景颜色的定义
   @AName("bgcolor")
   protected String _bgcolor;

   // 画布背景色的定义
   @AName("canvasbgcolor")
   protected String _canvasbgcolor;

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
   // <T>获得标题的内容。</T>
   //
   // @return 标题
   //============================================================
   public String getCaption(){
      return _caption;
   }

   //============================================================
   // <T>设置标题的内容。</T>
   //
   // @param value 标题
   //============================================================
   public void setCaption(String value){
      _caption = value;
   }

   //============================================================
   // <T>获得图表字体的内容。</T>
   //
   // @return 图表字体
   //============================================================
   public String getBasefont(){
      return _basefont;
   }

   //============================================================
   // <T>设置图表字体的内容。</T>
   //
   // @param value 图表字体
   //============================================================
   public void setBasefont(String value){
      _basefont = value;
   }

   //============================================================
   // <T>获得图表字号的内容。</T>
   //
   // @return 图表字号
   //============================================================
   public String getBasefontsize(){
      return _basefontsize;
   }

   //============================================================
   // <T>设置图表字号的内容。</T>
   //
   // @param value 图表字号
   //============================================================
   public void setBasefontsize(String value){
      _basefontsize = value;
   }

   //============================================================
   // <T>获得子标题的内容。</T>
   //
   // @return 子标题
   //============================================================
   public String getSubcaption(){
      return _subcaption;
   }

   //============================================================
   // <T>设置子标题的内容。</T>
   //
   // @param value 子标题
   //============================================================
   public void setSubcaption(String value){
      _subcaption = value;
   }

   //============================================================
   // <T>获得x轴名称的内容。</T>
   //
   // @return x轴名称
   //============================================================
   public String getXaxisname(){
      return _xaxisname;
   }

   //============================================================
   // <T>设置x轴名称的内容。</T>
   //
   // @param value x轴名称
   //============================================================
   public void setXaxisname(String value){
      _xaxisname = value;
   }

   //============================================================
   // <T>获得y轴名称的内容。</T>
   //
   // @return y轴名称
   //============================================================
   public String getYaxisname(){
      return _yaxisname;
   }

   //============================================================
   // <T>设置y轴名称的内容。</T>
   //
   // @param value y轴名称
   //============================================================
   public void setYaxisname(String value){
      _yaxisname = value;
   }

   //============================================================
   // <T>获得y轴最小值的内容。</T>
   //
   // @return y轴最小值
   //============================================================
   public String getYaxisminvalue(){
      return _yaxisminvalue;
   }

   //============================================================
   // <T>设置y轴最小值的内容。</T>
   //
   // @param value y轴最小值
   //============================================================
   public void setYaxisminvalue(String value){
      _yaxisminvalue = value;
   }

   //============================================================
   // <T>获得y轴最大值的内容。</T>
   //
   // @return y轴最大值
   //============================================================
   public String getYaxismaxvalue(){
      return _yaxismaxvalue;
   }

   //============================================================
   // <T>设置y轴最大值的内容。</T>
   //
   // @param value y轴最大值
   //============================================================
   public void setYaxismaxvalue(String value){
      _yaxismaxvalue = value;
   }

   //============================================================
   // <T>获得数值精度的内容。</T>
   //
   // @return 数值精度
   //============================================================
   public String getDecimalprecision(){
      return _decimalprecision;
   }

   //============================================================
   // <T>设置数值精度的内容。</T>
   //
   // @param value 数值精度
   //============================================================
   public void setDecimalprecision(String value){
      _decimalprecision = value;
   }

   //============================================================
   // <T>获得数字格式化的内容。</T>
   //
   // @return 数字格式化
   //============================================================
   public String getFormatnumber(){
      return _formatnumber;
   }

   //============================================================
   // <T>设置数字格式化的内容。</T>
   //
   // @param value 数字格式化
   //============================================================
   public void setFormatnumber(String value){
      _formatnumber = value;
   }

   //============================================================
   // <T>获得显示名称的内容。</T>
   //
   // @return 显示名称
   //============================================================
   public String getShownames(){
      return _shownames;
   }

   //============================================================
   // <T>设置显示名称的内容。</T>
   //
   // @param value 显示名称
   //============================================================
   public void setShownames(String value){
      _shownames = value;
   }

   //============================================================
   // <T>获得显示数值的内容。</T>
   //
   // @return 显示数值
   //============================================================
   public String getShowvalues(){
      return _showvalues;
   }

   //============================================================
   // <T>设置显示数值的内容。</T>
   //
   // @param value 显示数值
   //============================================================
   public void setShowvalues(String value){
      _showvalues = value;
   }

   //============================================================
   // <T>获得的内容。</T>
   //
   // @return 
   //============================================================
   public String getFormatnumberscale(){
      return _formatnumberscale;
   }

   //============================================================
   // <T>设置的内容。</T>
   //
   // @param value 
   //============================================================
   public void setFormatnumberscale(String value){
      _formatnumberscale = value;
   }

   //============================================================
   // <T>获得鼠标悬停背景色的内容。</T>
   //
   // @return 鼠标悬停背景色
   //============================================================
   public String getHovercapbg(){
      return _hovercapbg;
   }

   //============================================================
   // <T>设置鼠标悬停背景色的内容。</T>
   //
   // @param value 鼠标悬停背景色
   //============================================================
   public void setHovercapbg(String value){
      _hovercapbg = value;
   }

   //============================================================
   // <T>获得鼠标悬停边框色的内容。</T>
   //
   // @return 鼠标悬停边框色
   //============================================================
   public String getHovercapborder(){
      return _hovercapborder;
   }

   //============================================================
   // <T>设置鼠标悬停边框色的内容。</T>
   //
   // @param value 鼠标悬停边框色
   //============================================================
   public void setHovercapborder(String value){
      _hovercapborder = value;
   }

   //============================================================
   // <T>获得水平线条数的内容。</T>
   //
   // @return 水平线条数
   //============================================================
   public String getNumdivlines(){
      return _numdivlines;
   }

   //============================================================
   // <T>设置水平线条数的内容。</T>
   //
   // @param value 水平线条数
   //============================================================
   public void setNumdivlines(String value){
      _numdivlines = value;
   }

   //============================================================
   // <T>获得垂直线条数的内容。</T>
   //
   // @return 垂直线条数
   //============================================================
   public String getNumvdivlines(){
      return _numvdivlines;
   }

   //============================================================
   // <T>设置垂直线条数的内容。</T>
   //
   // @param value 垂直线条数
   //============================================================
   public void setNumvdivlines(String value){
      _numvdivlines = value;
   }

   //============================================================
   // <T>获得数据逻辑的内容。</T>
   //
   // @return 数据逻辑
   //============================================================
   public String getDataLogic(){
      return _dataLogic;
   }

   //============================================================
   // <T>设置数据逻辑的内容。</T>
   //
   // @param value 数据逻辑
   //============================================================
   public void setDataLogic(String value){
      _dataLogic = value;
   }

   //============================================================
   // <T>获得背景颜色的内容。</T>
   //
   // @return 背景颜色
   //============================================================
   public String getBgcolor(){
      return _bgcolor;
   }

   //============================================================
   // <T>设置背景颜色的内容。</T>
   //
   // @param value 背景颜色
   //============================================================
   public void setBgcolor(String value){
      _bgcolor = value;
   }

   //============================================================
   // <T>获得画布背景色的内容。</T>
   //
   // @return 画布背景色
   //============================================================
   public String getCanvasbgcolor(){
      return _canvasbgcolor;
   }

   //============================================================
   // <T>设置画布背景色的内容。</T>
   //
   // @param value 画布背景色
   //============================================================
   public void setCanvasbgcolor(String value){
      _canvasbgcolor = value;
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
      }else if(PTY_CAPTION.equalsIgnoreCase(name)){
         return getCaption();
      }else if(PTY_BASEFONT.equalsIgnoreCase(name)){
         return getBasefont();
      }else if(PTY_BASEFONTSIZE.equalsIgnoreCase(name)){
         return getBasefontsize();
      }else if(PTY_SUBCAPTION.equalsIgnoreCase(name)){
         return getSubcaption();
      }else if(PTY_XAXISNAME.equalsIgnoreCase(name)){
         return getXaxisname();
      }else if(PTY_YAXISNAME.equalsIgnoreCase(name)){
         return getYaxisname();
      }else if(PTY_YAXISMINVALUE.equalsIgnoreCase(name)){
         return getYaxisminvalue();
      }else if(PTY_YAXISMAXVALUE.equalsIgnoreCase(name)){
         return getYaxismaxvalue();
      }else if(PTY_DECIMALPRECISION.equalsIgnoreCase(name)){
         return getDecimalprecision();
      }else if(PTY_FORMATNUMBER.equalsIgnoreCase(name)){
         return getFormatnumber();
      }else if(PTY_SHOWNAMES.equalsIgnoreCase(name)){
         return getShownames();
      }else if(PTY_SHOWVALUES.equalsIgnoreCase(name)){
         return getShowvalues();
      }else if(PTY_FORMATNUMBERSCALE.equalsIgnoreCase(name)){
         return getFormatnumberscale();
      }else if(PTY_HOVERCAPBG.equalsIgnoreCase(name)){
         return getHovercapbg();
      }else if(PTY_HOVERCAPBORDER.equalsIgnoreCase(name)){
         return getHovercapborder();
      }else if(PTY_NUMDIVLINES.equalsIgnoreCase(name)){
         return getNumdivlines();
      }else if(PTY_NUMVDIVLINES.equalsIgnoreCase(name)){
         return getNumvdivlines();
      }else if(PTY_DATA_LOGIC.equalsIgnoreCase(name)){
         return getDataLogic();
      }else if(PTY_BGCOLOR.equalsIgnoreCase(name)){
         return getBgcolor();
      }else if(PTY_CANVASBGCOLOR.equalsIgnoreCase(name)){
         return getCanvasbgcolor();
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
      }else if(PTY_CAPTION.equalsIgnoreCase(name)){
         setCaption(value);
      }else if(PTY_BASEFONT.equalsIgnoreCase(name)){
         setBasefont(value);
      }else if(PTY_BASEFONTSIZE.equalsIgnoreCase(name)){
         setBasefontsize(value);
      }else if(PTY_SUBCAPTION.equalsIgnoreCase(name)){
         setSubcaption(value);
      }else if(PTY_XAXISNAME.equalsIgnoreCase(name)){
         setXaxisname(value);
      }else if(PTY_YAXISNAME.equalsIgnoreCase(name)){
         setYaxisname(value);
      }else if(PTY_YAXISMINVALUE.equalsIgnoreCase(name)){
         setYaxisminvalue(value);
      }else if(PTY_YAXISMAXVALUE.equalsIgnoreCase(name)){
         setYaxismaxvalue(value);
      }else if(PTY_DECIMALPRECISION.equalsIgnoreCase(name)){
         setDecimalprecision(value);
      }else if(PTY_FORMATNUMBER.equalsIgnoreCase(name)){
         setFormatnumber(value);
      }else if(PTY_SHOWNAMES.equalsIgnoreCase(name)){
         setShownames(value);
      }else if(PTY_SHOWVALUES.equalsIgnoreCase(name)){
         setShowvalues(value);
      }else if(PTY_FORMATNUMBERSCALE.equalsIgnoreCase(name)){
         setFormatnumberscale(value);
      }else if(PTY_HOVERCAPBG.equalsIgnoreCase(name)){
         setHovercapbg(value);
      }else if(PTY_HOVERCAPBORDER.equalsIgnoreCase(name)){
         setHovercapborder(value);
      }else if(PTY_NUMDIVLINES.equalsIgnoreCase(name)){
         setNumdivlines(value);
      }else if(PTY_NUMVDIVLINES.equalsIgnoreCase(name)){
         setNumvdivlines(value);
      }else if(PTY_DATA_LOGIC.equalsIgnoreCase(name)){
         setDataLogic(value);
      }else if(PTY_BGCOLOR.equalsIgnoreCase(name)){
         setBgcolor(value);
      }else if(PTY_CANVASBGCOLOR.equalsIgnoreCase(name)){
         setCanvasbgcolor(value);
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
         if(config.contains("caption")){
            setCaption(config.get(PTY_CAPTION));
         }
         if(config.contains("basefont")){
            setBasefont(config.get(PTY_BASEFONT));
         }
         if(config.contains("basefontsize")){
            setBasefontsize(config.get(PTY_BASEFONTSIZE));
         }
         if(config.contains("subcaption")){
            setSubcaption(config.get(PTY_SUBCAPTION));
         }
         if(config.contains("xaxisname")){
            setXaxisname(config.get(PTY_XAXISNAME));
         }
         if(config.contains("yaxisname")){
            setYaxisname(config.get(PTY_YAXISNAME));
         }
         if(config.contains("yaxisminvalue")){
            setYaxisminvalue(config.get(PTY_YAXISMINVALUE));
         }
         if(config.contains("yaxismaxvalue")){
            setYaxismaxvalue(config.get(PTY_YAXISMAXVALUE));
         }
         if(config.contains("decimalprecision")){
            setDecimalprecision(config.get(PTY_DECIMALPRECISION));
         }
         if(config.contains("formatnumber")){
            setFormatnumber(config.get(PTY_FORMATNUMBER));
         }
         if(config.contains("shownames")){
            setShownames(config.get(PTY_SHOWNAMES));
         }
         if(config.contains("showvalues")){
            setShowvalues(config.get(PTY_SHOWVALUES));
         }
         if(config.contains("formatnumberscale")){
            setFormatnumberscale(config.get(PTY_FORMATNUMBERSCALE));
         }
         if(config.contains("hovercapbg")){
            setHovercapbg(config.get(PTY_HOVERCAPBG));
         }
         if(config.contains("hovercapborder")){
            setHovercapborder(config.get(PTY_HOVERCAPBORDER));
         }
         if(config.contains("numdivlines")){
            setNumdivlines(config.get(PTY_NUMDIVLINES));
         }
         if(config.contains("numvdivlines")){
            setNumvdivlines(config.get(PTY_NUMVDIVLINES));
         }
         if(config.contains("data_logic")){
            setDataLogic(config.get(PTY_DATA_LOGIC));
         }
         if(config.contains("bgcolor")){
            setBgcolor(config.get(PTY_BGCOLOR));
         }
         if(config.contains("canvasbgcolor")){
            setCanvasbgcolor(config.get(PTY_CANVASBGCOLOR));
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
         if(config.contains("caption")){
            setCaption(config.get(PTY_CAPTION));
         }
         if(config.contains("basefont")){
            setBasefont(config.get(PTY_BASEFONT));
         }
         if(config.contains("basefontsize")){
            setBasefontsize(config.get(PTY_BASEFONTSIZE));
         }
         if(config.contains("subcaption")){
            setSubcaption(config.get(PTY_SUBCAPTION));
         }
         if(config.contains("xaxisname")){
            setXaxisname(config.get(PTY_XAXISNAME));
         }
         if(config.contains("yaxisname")){
            setYaxisname(config.get(PTY_YAXISNAME));
         }
         if(config.contains("yaxisminvalue")){
            setYaxisminvalue(config.get(PTY_YAXISMINVALUE));
         }
         if(config.contains("yaxismaxvalue")){
            setYaxismaxvalue(config.get(PTY_YAXISMAXVALUE));
         }
         if(config.contains("decimalprecision")){
            setDecimalprecision(config.get(PTY_DECIMALPRECISION));
         }
         if(config.contains("formatnumber")){
            setFormatnumber(config.get(PTY_FORMATNUMBER));
         }
         if(config.contains("shownames")){
            setShownames(config.get(PTY_SHOWNAMES));
         }
         if(config.contains("showvalues")){
            setShowvalues(config.get(PTY_SHOWVALUES));
         }
         if(config.contains("formatnumberscale")){
            setFormatnumberscale(config.get(PTY_FORMATNUMBERSCALE));
         }
         if(config.contains("hovercapbg")){
            setHovercapbg(config.get(PTY_HOVERCAPBG));
         }
         if(config.contains("hovercapborder")){
            setHovercapborder(config.get(PTY_HOVERCAPBORDER));
         }
         if(config.contains("numdivlines")){
            setNumdivlines(config.get(PTY_NUMDIVLINES));
         }
         if(config.contains("numvdivlines")){
            setNumvdivlines(config.get(PTY_NUMVDIVLINES));
         }
         if(config.contains("data_logic")){
            setDataLogic(config.get(PTY_DATA_LOGIC));
         }
         if(config.contains("bgcolor")){
            setBgcolor(config.get(PTY_BGCOLOR));
         }
         if(config.contains("canvasbgcolor")){
            setCanvasbgcolor(config.get(PTY_CANVASBGCOLOR));
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
         if(config.contains("caption")){
            setCaption(config.get(PTY_CAPTION));
         }
         if(config.contains("basefont")){
            setBasefont(config.get(PTY_BASEFONT));
         }
         if(config.contains("basefontsize")){
            setBasefontsize(config.get(PTY_BASEFONTSIZE));
         }
         if(config.contains("subcaption")){
            setSubcaption(config.get(PTY_SUBCAPTION));
         }
         if(config.contains("xaxisname")){
            setXaxisname(config.get(PTY_XAXISNAME));
         }
         if(config.contains("yaxisname")){
            setYaxisname(config.get(PTY_YAXISNAME));
         }
         if(config.contains("yaxisminvalue")){
            setYaxisminvalue(config.get(PTY_YAXISMINVALUE));
         }
         if(config.contains("yaxismaxvalue")){
            setYaxismaxvalue(config.get(PTY_YAXISMAXVALUE));
         }
         if(config.contains("decimalprecision")){
            setDecimalprecision(config.get(PTY_DECIMALPRECISION));
         }
         if(config.contains("formatnumber")){
            setFormatnumber(config.get(PTY_FORMATNUMBER));
         }
         if(config.contains("shownames")){
            setShownames(config.get(PTY_SHOWNAMES));
         }
         if(config.contains("showvalues")){
            setShowvalues(config.get(PTY_SHOWVALUES));
         }
         if(config.contains("formatnumberscale")){
            setFormatnumberscale(config.get(PTY_FORMATNUMBERSCALE));
         }
         if(config.contains("hovercapbg")){
            setHovercapbg(config.get(PTY_HOVERCAPBG));
         }
         if(config.contains("hovercapborder")){
            setHovercapborder(config.get(PTY_HOVERCAPBORDER));
         }
         if(config.contains("numdivlines")){
            setNumdivlines(config.get(PTY_NUMDIVLINES));
         }
         if(config.contains("numvdivlines")){
            setNumvdivlines(config.get(PTY_NUMVDIVLINES));
         }
         if(config.contains("data_logic")){
            setDataLogic(config.get(PTY_DATA_LOGIC));
         }
         if(config.contains("bgcolor")){
            setBgcolor(config.get(PTY_BGCOLOR));
         }
         if(config.contains("canvasbgcolor")){
            setCanvasbgcolor(config.get(PTY_CANVASBGCOLOR));
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
         if(RString.isEmpty(getCaption())){
            if(config.contains("caption")){
               setCaption(config.get(PTY_CAPTION));
            }
         }
         if(RString.isEmpty(getBasefont())){
            if(config.contains("basefont")){
               setBasefont(config.get(PTY_BASEFONT));
            }
         }
         if(RString.isEmpty(getBasefontsize())){
            if(config.contains("basefontsize")){
               setBasefontsize(config.get(PTY_BASEFONTSIZE));
            }
         }
         if(RString.isEmpty(getSubcaption())){
            if(config.contains("subcaption")){
               setSubcaption(config.get(PTY_SUBCAPTION));
            }
         }
         if(RString.isEmpty(getXaxisname())){
            if(config.contains("xaxisname")){
               setXaxisname(config.get(PTY_XAXISNAME));
            }
         }
         if(RString.isEmpty(getYaxisname())){
            if(config.contains("yaxisname")){
               setYaxisname(config.get(PTY_YAXISNAME));
            }
         }
         if(RString.isEmpty(getYaxisminvalue())){
            if(config.contains("yaxisminvalue")){
               setYaxisminvalue(config.get(PTY_YAXISMINVALUE));
            }
         }
         if(RString.isEmpty(getYaxismaxvalue())){
            if(config.contains("yaxismaxvalue")){
               setYaxismaxvalue(config.get(PTY_YAXISMAXVALUE));
            }
         }
         if(RString.isEmpty(getDecimalprecision())){
            if(config.contains("decimalprecision")){
               setDecimalprecision(config.get(PTY_DECIMALPRECISION));
            }
         }
         if(RString.isEmpty(getFormatnumber())){
            if(config.contains("formatnumber")){
               setFormatnumber(config.get(PTY_FORMATNUMBER));
            }
         }
         if(RString.isEmpty(getShownames())){
            if(config.contains("shownames")){
               setShownames(config.get(PTY_SHOWNAMES));
            }
         }
         if(RString.isEmpty(getShowvalues())){
            if(config.contains("showvalues")){
               setShowvalues(config.get(PTY_SHOWVALUES));
            }
         }
         if(RString.isEmpty(getFormatnumberscale())){
            if(config.contains("formatnumberscale")){
               setFormatnumberscale(config.get(PTY_FORMATNUMBERSCALE));
            }
         }
         if(RString.isEmpty(getHovercapbg())){
            if(config.contains("hovercapbg")){
               setHovercapbg(config.get(PTY_HOVERCAPBG));
            }
         }
         if(RString.isEmpty(getHovercapborder())){
            if(config.contains("hovercapborder")){
               setHovercapborder(config.get(PTY_HOVERCAPBORDER));
            }
         }
         if(RString.isEmpty(getNumdivlines())){
            if(config.contains("numdivlines")){
               setNumdivlines(config.get(PTY_NUMDIVLINES));
            }
         }
         if(RString.isEmpty(getNumvdivlines())){
            if(config.contains("numvdivlines")){
               setNumvdivlines(config.get(PTY_NUMVDIVLINES));
            }
         }
         if(RString.isEmpty(getDataLogic())){
            if(config.contains("data_logic")){
               setDataLogic(config.get(PTY_DATA_LOGIC));
            }
         }
         if(RString.isEmpty(getBgcolor())){
            if(config.contains("bgcolor")){
               setBgcolor(config.get(PTY_BGCOLOR));
            }
         }
         if(RString.isEmpty(getCanvasbgcolor())){
            if(config.contains("canvasbgcolor")){
               setCanvasbgcolor(config.get(PTY_CANVASBGCOLOR));
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
         if(RString.isNotEmpty(getCaption())){
            config.set(PTY_CAPTION, getCaption());
         }
         if(RString.isNotEmpty(getBasefont())){
            config.set(PTY_BASEFONT, getBasefont());
         }
         if(RString.isNotEmpty(getBasefontsize())){
            config.set(PTY_BASEFONTSIZE, getBasefontsize());
         }
         if(RString.isNotEmpty(getSubcaption())){
            config.set(PTY_SUBCAPTION, getSubcaption());
         }
         if(RString.isNotEmpty(getXaxisname())){
            config.set(PTY_XAXISNAME, getXaxisname());
         }
         if(RString.isNotEmpty(getYaxisname())){
            config.set(PTY_YAXISNAME, getYaxisname());
         }
         if(RString.isNotEmpty(getYaxisminvalue())){
            config.set(PTY_YAXISMINVALUE, getYaxisminvalue());
         }
         if(RString.isNotEmpty(getYaxismaxvalue())){
            config.set(PTY_YAXISMAXVALUE, getYaxismaxvalue());
         }
         if(RString.isNotEmpty(getDecimalprecision())){
            config.set(PTY_DECIMALPRECISION, getDecimalprecision());
         }
         if(RString.isNotEmpty(getFormatnumber())){
            config.set(PTY_FORMATNUMBER, getFormatnumber());
         }
         if(RString.isNotEmpty(getShownames())){
            config.set(PTY_SHOWNAMES, getShownames());
         }
         if(RString.isNotEmpty(getShowvalues())){
            config.set(PTY_SHOWVALUES, getShowvalues());
         }
         if(RString.isNotEmpty(getFormatnumberscale())){
            config.set(PTY_FORMATNUMBERSCALE, getFormatnumberscale());
         }
         if(RString.isNotEmpty(getHovercapbg())){
            config.set(PTY_HOVERCAPBG, getHovercapbg());
         }
         if(RString.isNotEmpty(getHovercapborder())){
            config.set(PTY_HOVERCAPBORDER, getHovercapborder());
         }
         if(RString.isNotEmpty(getNumdivlines())){
            config.set(PTY_NUMDIVLINES, getNumdivlines());
         }
         if(RString.isNotEmpty(getNumvdivlines())){
            config.set(PTY_NUMVDIVLINES, getNumvdivlines());
         }
         if(RString.isNotEmpty(getDataLogic())){
            config.set(PTY_DATA_LOGIC, getDataLogic());
         }
         if(RString.isNotEmpty(getBgcolor())){
            config.set(PTY_BGCOLOR, getBgcolor());
         }
         if(RString.isNotEmpty(getCanvasbgcolor())){
            config.set(PTY_CANVASBGCOLOR, getCanvasbgcolor());
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
         if(RString.isNotEmpty(getCaption())){
            config.set(PTY_CAPTION, getCaption());
         }
         if(RString.isNotEmpty(getBasefont())){
            config.set(PTY_BASEFONT, getBasefont());
         }
         if(RString.isNotEmpty(getBasefontsize())){
            config.set(PTY_BASEFONTSIZE, getBasefontsize());
         }
         if(RString.isNotEmpty(getSubcaption())){
            config.set(PTY_SUBCAPTION, getSubcaption());
         }
         if(RString.isNotEmpty(getXaxisname())){
            config.set(PTY_XAXISNAME, getXaxisname());
         }
         if(RString.isNotEmpty(getYaxisname())){
            config.set(PTY_YAXISNAME, getYaxisname());
         }
         if(RString.isNotEmpty(getYaxisminvalue())){
            config.set(PTY_YAXISMINVALUE, getYaxisminvalue());
         }
         if(RString.isNotEmpty(getYaxismaxvalue())){
            config.set(PTY_YAXISMAXVALUE, getYaxismaxvalue());
         }
         if(RString.isNotEmpty(getDecimalprecision())){
            config.set(PTY_DECIMALPRECISION, getDecimalprecision());
         }
         if(RString.isNotEmpty(getFormatnumber())){
            config.set(PTY_FORMATNUMBER, getFormatnumber());
         }
         if(RString.isNotEmpty(getShownames())){
            config.set(PTY_SHOWNAMES, getShownames());
         }
         if(RString.isNotEmpty(getShowvalues())){
            config.set(PTY_SHOWVALUES, getShowvalues());
         }
         if(RString.isNotEmpty(getFormatnumberscale())){
            config.set(PTY_FORMATNUMBERSCALE, getFormatnumberscale());
         }
         if(RString.isNotEmpty(getHovercapbg())){
            config.set(PTY_HOVERCAPBG, getHovercapbg());
         }
         if(RString.isNotEmpty(getHovercapborder())){
            config.set(PTY_HOVERCAPBORDER, getHovercapborder());
         }
         if(RString.isNotEmpty(getNumdivlines())){
            config.set(PTY_NUMDIVLINES, getNumdivlines());
         }
         if(RString.isNotEmpty(getNumvdivlines())){
            config.set(PTY_NUMVDIVLINES, getNumvdivlines());
         }
         if(RString.isNotEmpty(getDataLogic())){
            config.set(PTY_DATA_LOGIC, getDataLogic());
         }
         if(RString.isNotEmpty(getBgcolor())){
            config.set(PTY_BGCOLOR, getBgcolor());
         }
         if(RString.isNotEmpty(getCanvasbgcolor())){
            config.set(PTY_CANVASBGCOLOR, getCanvasbgcolor());
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
         String sCaption = getCaption();
         if(RString.isNotEmpty(sCaption)){
            config.set(PTY_CAPTION, sCaption);
         }
         String sBasefont = getBasefont();
         if(RString.isNotEmpty(sBasefont)){
            config.set(PTY_BASEFONT, sBasefont);
         }
         String sBasefontsize = getBasefontsize();
         if(RString.isNotEmpty(sBasefontsize)){
            config.set(PTY_BASEFONTSIZE, sBasefontsize);
         }
         String sSubcaption = getSubcaption();
         if(RString.isNotEmpty(sSubcaption)){
            config.set(PTY_SUBCAPTION, sSubcaption);
         }
         String sXaxisname = getXaxisname();
         if(RString.isNotEmpty(sXaxisname)){
            config.set(PTY_XAXISNAME, sXaxisname);
         }
         String sYaxisname = getYaxisname();
         if(RString.isNotEmpty(sYaxisname)){
            config.set(PTY_YAXISNAME, sYaxisname);
         }
         String sYaxisminvalue = getYaxisminvalue();
         if(RString.isNotEmpty(sYaxisminvalue)){
            config.set(PTY_YAXISMINVALUE, sYaxisminvalue);
         }
         String sYaxismaxvalue = getYaxismaxvalue();
         if(RString.isNotEmpty(sYaxismaxvalue)){
            config.set(PTY_YAXISMAXVALUE, sYaxismaxvalue);
         }
         String sDecimalprecision = getDecimalprecision();
         if(RString.isNotEmpty(sDecimalprecision)){
            config.set(PTY_DECIMALPRECISION, sDecimalprecision);
         }
         String sFormatnumber = getFormatnumber();
         if(RString.isNotEmpty(sFormatnumber)){
            config.set(PTY_FORMATNUMBER, sFormatnumber);
         }
         String sShownames = getShownames();
         if(RString.isNotEmpty(sShownames)){
            config.set(PTY_SHOWNAMES, sShownames);
         }
         String sShowvalues = getShowvalues();
         if(RString.isNotEmpty(sShowvalues)){
            config.set(PTY_SHOWVALUES, sShowvalues);
         }
         String sFormatnumberscale = getFormatnumberscale();
         if(RString.isNotEmpty(sFormatnumberscale)){
            config.set(PTY_FORMATNUMBERSCALE, sFormatnumberscale);
         }
         String sHovercapbg = getHovercapbg();
         if(RString.isNotEmpty(sHovercapbg)){
            config.set(PTY_HOVERCAPBG, sHovercapbg);
         }
         String sHovercapborder = getHovercapborder();
         if(RString.isNotEmpty(sHovercapborder)){
            config.set(PTY_HOVERCAPBORDER, sHovercapborder);
         }
         String sNumdivlines = getNumdivlines();
         if(RString.isNotEmpty(sNumdivlines)){
            config.set(PTY_NUMDIVLINES, sNumdivlines);
         }
         String sNumvdivlines = getNumvdivlines();
         if(RString.isNotEmpty(sNumvdivlines)){
            config.set(PTY_NUMVDIVLINES, sNumvdivlines);
         }
         String sDataLogic = getDataLogic();
         if(RString.isNotEmpty(sDataLogic)){
            config.set(PTY_DATA_LOGIC, sDataLogic);
         }
         String sBgcolor = getBgcolor();
         if(RString.isNotEmpty(sBgcolor)){
            config.set(PTY_BGCOLOR, sBgcolor);
         }
         String sCanvasbgcolor = getCanvasbgcolor();
         if(RString.isNotEmpty(sCanvasbgcolor)){
            config.set(PTY_CANVASBGCOLOR, sCanvasbgcolor);
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
         String sCaption = getCaption();
         if(RString.isNotEmpty(sCaption)){
            config.set(PTY_CAPTION, sCaption);
         }
         String sBasefont = getBasefont();
         if(RString.isNotEmpty(sBasefont)){
            config.set(PTY_BASEFONT, sBasefont);
         }
         String sBasefontsize = getBasefontsize();
         if(RString.isNotEmpty(sBasefontsize)){
            config.set(PTY_BASEFONTSIZE, sBasefontsize);
         }
         String sSubcaption = getSubcaption();
         if(RString.isNotEmpty(sSubcaption)){
            config.set(PTY_SUBCAPTION, sSubcaption);
         }
         String sXaxisname = getXaxisname();
         if(RString.isNotEmpty(sXaxisname)){
            config.set(PTY_XAXISNAME, sXaxisname);
         }
         String sYaxisname = getYaxisname();
         if(RString.isNotEmpty(sYaxisname)){
            config.set(PTY_YAXISNAME, sYaxisname);
         }
         String sYaxisminvalue = getYaxisminvalue();
         if(RString.isNotEmpty(sYaxisminvalue)){
            config.set(PTY_YAXISMINVALUE, sYaxisminvalue);
         }
         String sYaxismaxvalue = getYaxismaxvalue();
         if(RString.isNotEmpty(sYaxismaxvalue)){
            config.set(PTY_YAXISMAXVALUE, sYaxismaxvalue);
         }
         String sDecimalprecision = getDecimalprecision();
         if(RString.isNotEmpty(sDecimalprecision)){
            config.set(PTY_DECIMALPRECISION, sDecimalprecision);
         }
         String sFormatnumber = getFormatnumber();
         if(RString.isNotEmpty(sFormatnumber)){
            config.set(PTY_FORMATNUMBER, sFormatnumber);
         }
         String sShownames = getShownames();
         if(RString.isNotEmpty(sShownames)){
            config.set(PTY_SHOWNAMES, sShownames);
         }
         String sShowvalues = getShowvalues();
         if(RString.isNotEmpty(sShowvalues)){
            config.set(PTY_SHOWVALUES, sShowvalues);
         }
         String sFormatnumberscale = getFormatnumberscale();
         if(RString.isNotEmpty(sFormatnumberscale)){
            config.set(PTY_FORMATNUMBERSCALE, sFormatnumberscale);
         }
         String sHovercapbg = getHovercapbg();
         if(RString.isNotEmpty(sHovercapbg)){
            config.set(PTY_HOVERCAPBG, sHovercapbg);
         }
         String sHovercapborder = getHovercapborder();
         if(RString.isNotEmpty(sHovercapborder)){
            config.set(PTY_HOVERCAPBORDER, sHovercapborder);
         }
         String sNumdivlines = getNumdivlines();
         if(RString.isNotEmpty(sNumdivlines)){
            config.set(PTY_NUMDIVLINES, sNumdivlines);
         }
         String sNumvdivlines = getNumvdivlines();
         if(RString.isNotEmpty(sNumvdivlines)){
            config.set(PTY_NUMVDIVLINES, sNumvdivlines);
         }
         String sDataLogic = getDataLogic();
         if(RString.isNotEmpty(sDataLogic)){
            config.set(PTY_DATA_LOGIC, sDataLogic);
         }
         String sBgcolor = getBgcolor();
         if(RString.isNotEmpty(sBgcolor)){
            config.set(PTY_BGCOLOR, sBgcolor);
         }
         String sCanvasbgcolor = getCanvasbgcolor();
         if(RString.isNotEmpty(sCanvasbgcolor)){
            config.set(PTY_CANVASBGCOLOR, sCanvasbgcolor);
         }
      }
   }
}