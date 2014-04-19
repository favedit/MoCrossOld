package org.mo.web.core.webtheme.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.web.core.webtheme.common.XFilterFace;

//============================================================
// <T>过滤器-透明对象的XML节点基类。</T>
//============================================================
public abstract class XBaseFilterAlpha
      extends FXmlObject
      implements
         XFilterFace
{
   // 名称定义
   public static final String NAME = "FilterAlpha";

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

   // 激活过滤器的名称定义
   public static final String PTY_ENABLE = "enable";

   // 透明度的名称定义
   public static final String PTY_OPACITY = "opacity";

   // 目标值的名称定义
   public static final String PTY_FINISHOPACITY = "finishopacity";

   // 渐变方式的名称定义
   public static final String PTY_STYLE = "style";

   // 起始X的名称定义
   public static final String PTY_STARTX = "startx";

   // 起始Y的名称定义
   public static final String PTY_STARTY = "starty";

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

   // 激活过滤器的定义
   @AName("enable")
   protected String _enable;

   // 透明度的定义
   @AName("opacity")
   protected String _opacity;

   // 目标值的定义
   @AName("finishopacity")
   protected String _finishopacity;

   // 渐变方式的定义
   @AName("style")
   protected String _style;

   // 起始X的定义
   @AName("startx")
   protected String _startx;

   // 起始Y的定义
   @AName("starty")
   protected String _starty;

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
   // <T>获得激活过滤器的内容。</T>
   //
   // @return 激活过滤器
   //============================================================
   public String getEnable(){
      return _enable;
   }

   //============================================================
   // <T>设置激活过滤器的内容。</T>
   //
   // @param value 激活过滤器
   //============================================================
   public void setEnable(String value){
      _enable = value;
   }

   //============================================================
   // <T>获得透明度的内容。</T>
   //
   // @return 透明度
   //============================================================
   public String getOpacity(){
      return _opacity;
   }

   //============================================================
   // <T>设置透明度的内容。</T>
   //
   // @param value 透明度
   //============================================================
   public void setOpacity(String value){
      _opacity = value;
   }

   //============================================================
   // <T>获得目标值的内容。</T>
   //
   // @return 目标值
   //============================================================
   public String getFinishopacity(){
      return _finishopacity;
   }

   //============================================================
   // <T>设置目标值的内容。</T>
   //
   // @param value 目标值
   //============================================================
   public void setFinishopacity(String value){
      _finishopacity = value;
   }

   //============================================================
   // <T>获得渐变方式的内容。</T>
   //
   // @return 渐变方式
   //============================================================
   public String getStyle(){
      return _style;
   }

   //============================================================
   // <T>设置渐变方式的内容。</T>
   //
   // @param value 渐变方式
   //============================================================
   public void setStyle(String value){
      _style = value;
   }

   //============================================================
   // <T>获得起始X的内容。</T>
   //
   // @return 起始X
   //============================================================
   public String getStartx(){
      return _startx;
   }

   //============================================================
   // <T>设置起始X的内容。</T>
   //
   // @param value 起始X
   //============================================================
   public void setStartx(String value){
      _startx = value;
   }

   //============================================================
   // <T>获得起始Y的内容。</T>
   //
   // @return 起始Y
   //============================================================
   public String getStarty(){
      return _starty;
   }

   //============================================================
   // <T>设置起始Y的内容。</T>
   //
   // @param value 起始Y
   //============================================================
   public void setStarty(String value){
      _starty = value;
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
      }else if(PTY_ENABLE.equalsIgnoreCase(name)){
         return getEnable();
      }else if(PTY_OPACITY.equalsIgnoreCase(name)){
         return getOpacity();
      }else if(PTY_FINISHOPACITY.equalsIgnoreCase(name)){
         return getFinishopacity();
      }else if(PTY_STYLE.equalsIgnoreCase(name)){
         return getStyle();
      }else if(PTY_STARTX.equalsIgnoreCase(name)){
         return getStartx();
      }else if(PTY_STARTY.equalsIgnoreCase(name)){
         return getStarty();
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
      }else if(PTY_ENABLE.equalsIgnoreCase(name)){
         setEnable(value);
      }else if(PTY_OPACITY.equalsIgnoreCase(name)){
         setOpacity(value);
      }else if(PTY_FINISHOPACITY.equalsIgnoreCase(name)){
         setFinishopacity(value);
      }else if(PTY_STYLE.equalsIgnoreCase(name)){
         setStyle(value);
      }else if(PTY_STARTX.equalsIgnoreCase(name)){
         setStartx(value);
      }else if(PTY_STARTY.equalsIgnoreCase(name)){
         setStarty(value);
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
         if(config.contains("enable")){
            setEnable(config.get(PTY_ENABLE));
         }
         if(config.contains("opacity")){
            setOpacity(config.get(PTY_OPACITY));
         }
         if(config.contains("finishopacity")){
            setFinishopacity(config.get(PTY_FINISHOPACITY));
         }
         if(config.contains("style")){
            setStyle(config.get(PTY_STYLE));
         }
         if(config.contains("startx")){
            setStartx(config.get(PTY_STARTX));
         }
         if(config.contains("starty")){
            setStarty(config.get(PTY_STARTY));
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
         if(config.contains("enable")){
            setEnable(config.get(PTY_ENABLE));
         }
         if(config.contains("opacity")){
            setOpacity(config.get(PTY_OPACITY));
         }
         if(config.contains("finishopacity")){
            setFinishopacity(config.get(PTY_FINISHOPACITY));
         }
         if(config.contains("style")){
            setStyle(config.get(PTY_STYLE));
         }
         if(config.contains("startx")){
            setStartx(config.get(PTY_STARTX));
         }
         if(config.contains("starty")){
            setStarty(config.get(PTY_STARTY));
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
         if(config.contains("enable")){
            setEnable(config.get(PTY_ENABLE));
         }
         if(config.contains("opacity")){
            setOpacity(config.get(PTY_OPACITY));
         }
         if(config.contains("finishopacity")){
            setFinishopacity(config.get(PTY_FINISHOPACITY));
         }
         if(config.contains("style")){
            setStyle(config.get(PTY_STYLE));
         }
         if(config.contains("startx")){
            setStartx(config.get(PTY_STARTX));
         }
         if(config.contains("starty")){
            setStarty(config.get(PTY_STARTY));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getParentStyle())){
            if(config.contains("parent_style")){
               setParentStyle(config.get(PTY_PARENT_STYLE));
            }
         }
         if(RString.isEmpty(getEnable())){
            if(config.contains("enable")){
               setEnable(config.get(PTY_ENABLE));
            }
         }
         if(RString.isEmpty(getFinishopacity())){
            if(config.contains("finishopacity")){
               setFinishopacity(config.get(PTY_FINISHOPACITY));
            }
         }
         if(RString.isEmpty(getStyle())){
            if(config.contains("style")){
               setStyle(config.get(PTY_STYLE));
            }
         }
         if(RString.isEmpty(getStartx())){
            if(config.contains("startx")){
               setStartx(config.get(PTY_STARTX));
            }
         }
         if(RString.isEmpty(getStarty())){
            if(config.contains("starty")){
               setStarty(config.get(PTY_STARTY));
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
         if(RString.isNotEmpty(getEnable())){
            config.set(PTY_ENABLE, getEnable());
         }
         if(RString.isNotEmpty(getOpacity())){
            config.set(PTY_OPACITY, getOpacity());
         }
         if(RString.isNotEmpty(getFinishopacity())){
            config.set(PTY_FINISHOPACITY, getFinishopacity());
         }
         if(RString.isNotEmpty(getStyle())){
            config.set(PTY_STYLE, getStyle());
         }
         if(RString.isNotEmpty(getStartx())){
            config.set(PTY_STARTX, getStartx());
         }
         if(RString.isNotEmpty(getStarty())){
            config.set(PTY_STARTY, getStarty());
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
         if(RString.isNotEmpty(getEnable())){
            config.set(PTY_ENABLE, getEnable());
         }
         if(RString.isNotEmpty(getOpacity())){
            config.set(PTY_OPACITY, getOpacity());
         }
         if(RString.isNotEmpty(getFinishopacity())){
            config.set(PTY_FINISHOPACITY, getFinishopacity());
         }
         if(RString.isNotEmpty(getStyle())){
            config.set(PTY_STYLE, getStyle());
         }
         if(RString.isNotEmpty(getStartx())){
            config.set(PTY_STARTX, getStartx());
         }
         if(RString.isNotEmpty(getStarty())){
            config.set(PTY_STARTY, getStarty());
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
         String sEnable = getEnable();
         if(RString.isNotEmpty(sEnable)){
            config.set(PTY_ENABLE, sEnable);
         }
         String sOpacity = getOpacity();
         if(RString.isNotEmpty(sOpacity)){
            config.set(PTY_OPACITY, sOpacity);
         }
         String sFinishopacity = getFinishopacity();
         if(RString.isNotEmpty(sFinishopacity)){
            config.set(PTY_FINISHOPACITY, sFinishopacity);
         }
         String sStyle = getStyle();
         if(RString.isNotEmpty(sStyle)){
            config.set(PTY_STYLE, sStyle);
         }
         String sStartx = getStartx();
         if(RString.isNotEmpty(sStartx)){
            config.set(PTY_STARTX, sStartx);
         }
         String sStarty = getStarty();
         if(RString.isNotEmpty(sStarty)){
            config.set(PTY_STARTY, sStarty);
         }
      }else if(EXmlConfig.Default == type){
         String sParentStyle = getParentStyle();
         if(RString.isNotEmpty(sParentStyle)){
            config.set(PTY_PARENT_STYLE, sParentStyle);
         }
         String sEnable = getEnable();
         if(RString.isNotEmpty(sEnable)){
            config.set(PTY_ENABLE, sEnable);
         }
         String sFinishopacity = getFinishopacity();
         if(RString.isNotEmpty(sFinishopacity)){
            config.set(PTY_FINISHOPACITY, sFinishopacity);
         }
         String sStyle = getStyle();
         if(RString.isNotEmpty(sStyle)){
            config.set(PTY_STYLE, sStyle);
         }
         String sStartx = getStartx();
         if(RString.isNotEmpty(sStartx)){
            config.set(PTY_STARTX, sStartx);
         }
         String sStarty = getStarty();
         if(RString.isNotEmpty(sStarty)){
            config.set(PTY_STARTY, sStarty);
         }
      }
   }
}