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
// <T>分支对象的XML节点基类。</T>
//============================================================
public abstract class XBaseStick
      extends FXmlObject
      implements
         XObjectFace
{
   // 名称定义
   public static final String NAME = "Stick";

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

   // 最小值的名称定义
   public static final String PTY_MIN = "min";

   // 最大值的名称定义
   public static final String PTY_MAX = "max";

   // 刻度的名称定义
   public static final String PTY_STEP = "step";

   // 线宽的名称定义
   public static final String PTY_LINE_WIDTH = "line_width";

   // 颜色的名称定义
   public static final String PTY_LINE_COLOR = "line_color";

   // 刻度宽度的名称定义
   public static final String PTY_STEP_WIDTH = "step_width";

   // 刻度高度的名称定义
   public static final String PTY_STEP_HEIGHT = "step_height";

   // 线条样式的名称定义
   public static final String PTY_LINE_STYLE = "line_style";

   // 样式的名称定义
   public static final String PTY_TYPE = "type";

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

   // 最小值的定义
   @AName("min")
   protected String _min;

   // 最大值的定义
   @AName("max")
   protected String _max;

   // 刻度的定义
   @AName("step")
   protected String _step;

   // 线宽的定义
   @AName("line_width")
   protected String _lineWidth;

   // 颜色的定义
   @AName("line_color")
   protected String _lineColor;

   // 刻度宽度的定义
   @AName("step_width")
   protected String _stepWidth;

   // 刻度高度的定义
   @AName("step_height")
   protected String _stepHeight;

   // 线条样式的定义
   @AName("line_style")
   protected String _lineStyle;

   // 样式的定义
   @AName("type")
   protected String _type;

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
   // <T>获得最小值的内容。</T>
   //
   // @return 最小值
   //============================================================
   public String getMin(){
      return _min;
   }

   //============================================================
   // <T>设置最小值的内容。</T>
   //
   // @param value 最小值
   //============================================================
   public void setMin(String value){
      _min = value;
   }

   //============================================================
   // <T>获得最大值的内容。</T>
   //
   // @return 最大值
   //============================================================
   public String getMax(){
      return _max;
   }

   //============================================================
   // <T>设置最大值的内容。</T>
   //
   // @param value 最大值
   //============================================================
   public void setMax(String value){
      _max = value;
   }

   //============================================================
   // <T>获得刻度的内容。</T>
   //
   // @return 刻度
   //============================================================
   public String getStep(){
      return _step;
   }

   //============================================================
   // <T>设置刻度的内容。</T>
   //
   // @param value 刻度
   //============================================================
   public void setStep(String value){
      _step = value;
   }

   //============================================================
   // <T>获得线宽的内容。</T>
   //
   // @return 线宽
   //============================================================
   public String getLineWidth(){
      return _lineWidth;
   }

   //============================================================
   // <T>设置线宽的内容。</T>
   //
   // @param value 线宽
   //============================================================
   public void setLineWidth(String value){
      _lineWidth = value;
   }

   //============================================================
   // <T>获得颜色的内容。</T>
   //
   // @return 颜色
   //============================================================
   public String getLineColor(){
      return _lineColor;
   }

   //============================================================
   // <T>设置颜色的内容。</T>
   //
   // @param value 颜色
   //============================================================
   public void setLineColor(String value){
      _lineColor = value;
   }

   //============================================================
   // <T>获得刻度宽度的内容。</T>
   //
   // @return 刻度宽度
   //============================================================
   public String getStepWidth(){
      return _stepWidth;
   }

   //============================================================
   // <T>设置刻度宽度的内容。</T>
   //
   // @param value 刻度宽度
   //============================================================
   public void setStepWidth(String value){
      _stepWidth = value;
   }

   //============================================================
   // <T>获得刻度高度的内容。</T>
   //
   // @return 刻度高度
   //============================================================
   public String getStepHeight(){
      return _stepHeight;
   }

   //============================================================
   // <T>设置刻度高度的内容。</T>
   //
   // @param value 刻度高度
   //============================================================
   public void setStepHeight(String value){
      _stepHeight = value;
   }

   //============================================================
   // <T>获得线条样式的内容。</T>
   //
   // @return 线条样式
   //============================================================
   public String getLineStyle(){
      return _lineStyle;
   }

   //============================================================
   // <T>设置线条样式的内容。</T>
   //
   // @param value 线条样式
   //============================================================
   public void setLineStyle(String value){
      _lineStyle = value;
   }

   //============================================================
   // <T>获得样式的内容。</T>
   //
   // @return 样式
   //============================================================
   public String getType(){
      return _type;
   }

   //============================================================
   // <T>设置样式的内容。</T>
   //
   // @param value 样式
   //============================================================
   public void setType(String value){
      _type = value;
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
      }else if(PTY_MIN.equalsIgnoreCase(name)){
         return getMin();
      }else if(PTY_MAX.equalsIgnoreCase(name)){
         return getMax();
      }else if(PTY_STEP.equalsIgnoreCase(name)){
         return getStep();
      }else if(PTY_LINE_WIDTH.equalsIgnoreCase(name)){
         return getLineWidth();
      }else if(PTY_LINE_COLOR.equalsIgnoreCase(name)){
         return getLineColor();
      }else if(PTY_STEP_WIDTH.equalsIgnoreCase(name)){
         return getStepWidth();
      }else if(PTY_STEP_HEIGHT.equalsIgnoreCase(name)){
         return getStepHeight();
      }else if(PTY_LINE_STYLE.equalsIgnoreCase(name)){
         return getLineStyle();
      }else if(PTY_TYPE.equalsIgnoreCase(name)){
         return getType();
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
      }else if(PTY_MIN.equalsIgnoreCase(name)){
         setMin(value);
      }else if(PTY_MAX.equalsIgnoreCase(name)){
         setMax(value);
      }else if(PTY_STEP.equalsIgnoreCase(name)){
         setStep(value);
      }else if(PTY_LINE_WIDTH.equalsIgnoreCase(name)){
         setLineWidth(value);
      }else if(PTY_LINE_COLOR.equalsIgnoreCase(name)){
         setLineColor(value);
      }else if(PTY_STEP_WIDTH.equalsIgnoreCase(name)){
         setStepWidth(value);
      }else if(PTY_STEP_HEIGHT.equalsIgnoreCase(name)){
         setStepHeight(value);
      }else if(PTY_LINE_STYLE.equalsIgnoreCase(name)){
         setLineStyle(value);
      }else if(PTY_TYPE.equalsIgnoreCase(name)){
         setType(value);
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
         if(config.contains("min")){
            setMin(config.get(PTY_MIN));
         }
         if(config.contains("max")){
            setMax(config.get(PTY_MAX));
         }
         if(config.contains("step")){
            setStep(config.get(PTY_STEP));
         }
         if(config.contains("line_width")){
            setLineWidth(config.get(PTY_LINE_WIDTH));
         }
         if(config.contains("line_color")){
            setLineColor(config.get(PTY_LINE_COLOR));
         }
         if(config.contains("step_width")){
            setStepWidth(config.get(PTY_STEP_WIDTH));
         }
         if(config.contains("step_height")){
            setStepHeight(config.get(PTY_STEP_HEIGHT));
         }
         if(config.contains("line_style")){
            setLineStyle(config.get(PTY_LINE_STYLE));
         }
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
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
         if(config.contains("min")){
            setMin(config.get(PTY_MIN));
         }
         if(config.contains("max")){
            setMax(config.get(PTY_MAX));
         }
         if(config.contains("step")){
            setStep(config.get(PTY_STEP));
         }
         if(config.contains("line_width")){
            setLineWidth(config.get(PTY_LINE_WIDTH));
         }
         if(config.contains("line_color")){
            setLineColor(config.get(PTY_LINE_COLOR));
         }
         if(config.contains("step_width")){
            setStepWidth(config.get(PTY_STEP_WIDTH));
         }
         if(config.contains("step_height")){
            setStepHeight(config.get(PTY_STEP_HEIGHT));
         }
         if(config.contains("line_style")){
            setLineStyle(config.get(PTY_LINE_STYLE));
         }
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
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
         if(config.contains("min")){
            setMin(config.get(PTY_MIN));
         }
         if(config.contains("max")){
            setMax(config.get(PTY_MAX));
         }
         if(config.contains("step")){
            setStep(config.get(PTY_STEP));
         }
         if(config.contains("line_width")){
            setLineWidth(config.get(PTY_LINE_WIDTH));
         }
         if(config.contains("line_color")){
            setLineColor(config.get(PTY_LINE_COLOR));
         }
         if(config.contains("step_width")){
            setStepWidth(config.get(PTY_STEP_WIDTH));
         }
         if(config.contains("step_height")){
            setStepHeight(config.get(PTY_STEP_HEIGHT));
         }
         if(config.contains("line_style")){
            setLineStyle(config.get(PTY_LINE_STYLE));
         }
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getMin())){
            if(config.contains("min")){
               setMin(config.get(PTY_MIN));
            }
         }
         if(RString.isEmpty(getMax())){
            if(config.contains("max")){
               setMax(config.get(PTY_MAX));
            }
         }
         if(RString.isEmpty(getStep())){
            if(config.contains("step")){
               setStep(config.get(PTY_STEP));
            }
         }
         if(RString.isEmpty(getLineStyle())){
            if(config.contains("line_style")){
               setLineStyle(config.get(PTY_LINE_STYLE));
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
         if(RString.isNotEmpty(getMin())){
            config.set(PTY_MIN, getMin());
         }
         if(RString.isNotEmpty(getMax())){
            config.set(PTY_MAX, getMax());
         }
         if(RString.isNotEmpty(getStep())){
            config.set(PTY_STEP, getStep());
         }
         if(RString.isNotEmpty(getLineWidth())){
            config.set(PTY_LINE_WIDTH, getLineWidth());
         }
         if(RString.isNotEmpty(getLineColor())){
            config.set(PTY_LINE_COLOR, getLineColor());
         }
         if(RString.isNotEmpty(getStepWidth())){
            config.set(PTY_STEP_WIDTH, getStepWidth());
         }
         if(RString.isNotEmpty(getStepHeight())){
            config.set(PTY_STEP_HEIGHT, getStepHeight());
         }
         if(RString.isNotEmpty(getLineStyle())){
            config.set(PTY_LINE_STYLE, getLineStyle());
         }
         if(RString.isNotEmpty(getType())){
            config.set(PTY_TYPE, getType());
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
         if(RString.isNotEmpty(getMin())){
            config.set(PTY_MIN, getMin());
         }
         if(RString.isNotEmpty(getMax())){
            config.set(PTY_MAX, getMax());
         }
         if(RString.isNotEmpty(getStep())){
            config.set(PTY_STEP, getStep());
         }
         if(RString.isNotEmpty(getLineWidth())){
            config.set(PTY_LINE_WIDTH, getLineWidth());
         }
         if(RString.isNotEmpty(getLineColor())){
            config.set(PTY_LINE_COLOR, getLineColor());
         }
         if(RString.isNotEmpty(getStepWidth())){
            config.set(PTY_STEP_WIDTH, getStepWidth());
         }
         if(RString.isNotEmpty(getStepHeight())){
            config.set(PTY_STEP_HEIGHT, getStepHeight());
         }
         if(RString.isNotEmpty(getLineStyle())){
            config.set(PTY_LINE_STYLE, getLineStyle());
         }
         if(RString.isNotEmpty(getType())){
            config.set(PTY_TYPE, getType());
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
         String sMin = getMin();
         if(RString.isNotEmpty(sMin)){
            config.set(PTY_MIN, sMin);
         }
         String sMax = getMax();
         if(RString.isNotEmpty(sMax)){
            config.set(PTY_MAX, sMax);
         }
         String sStep = getStep();
         if(RString.isNotEmpty(sStep)){
            config.set(PTY_STEP, sStep);
         }
         String sLineWidth = getLineWidth();
         if(RString.isNotEmpty(sLineWidth)){
            config.set(PTY_LINE_WIDTH, sLineWidth);
         }
         String sLineColor = getLineColor();
         if(RString.isNotEmpty(sLineColor)){
            config.set(PTY_LINE_COLOR, sLineColor);
         }
         String sStepWidth = getStepWidth();
         if(RString.isNotEmpty(sStepWidth)){
            config.set(PTY_STEP_WIDTH, sStepWidth);
         }
         String sStepHeight = getStepHeight();
         if(RString.isNotEmpty(sStepHeight)){
            config.set(PTY_STEP_HEIGHT, sStepHeight);
         }
         String sLineStyle = getLineStyle();
         if(RString.isNotEmpty(sLineStyle)){
            config.set(PTY_LINE_STYLE, sLineStyle);
         }
         String sType = getType();
         if(RString.isNotEmpty(sType)){
            config.set(PTY_TYPE, sType);
         }
      }else if(EXmlConfig.Default == type){
         String sMin = getMin();
         if(RString.isNotEmpty(sMin)){
            config.set(PTY_MIN, sMin);
         }
         String sMax = getMax();
         if(RString.isNotEmpty(sMax)){
            config.set(PTY_MAX, sMax);
         }
         String sStep = getStep();
         if(RString.isNotEmpty(sStep)){
            config.set(PTY_STEP, sStep);
         }
         String sLineStyle = getLineStyle();
         if(RString.isNotEmpty(sLineStyle)){
            config.set(PTY_LINE_STYLE, sLineStyle);
         }
      }
   }
}