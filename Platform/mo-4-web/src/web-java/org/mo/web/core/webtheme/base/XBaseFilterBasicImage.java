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
// <T>过滤器-图片效果对象的XML节点基类。</T>
//============================================================
public abstract class XBaseFilterBasicImage
      extends FXmlObject
      implements
         XFilterFace
{
   // 名称定义
   public static final String NAME = "FilterBasicImage";

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

   // 灰度显示的名称定义
   public static final String PTY_GRAYSCALE = "grayscale";

   // 反相显示的名称定义
   public static final String PTY_INVERT = "invert";

   // 添加遮罩的名称定义
   public static final String PTY_MASK = "mask";

   // 遮罩颜色的名称定义
   public static final String PTY_MASKCOLOR = "maskcolor";

   // 反转显示的名称定义
   public static final String PTY_MIRROR = "mirror";

   // 透明度的名称定义
   public static final String PTY_OPACITY = "opacity";

   // 旋转的名称定义
   public static final String PTY_ROTATION = "rotation";

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

   // 灰度显示的定义
   @AName("grayscale")
   protected String _grayscale;

   // 反相显示的定义
   @AName("invert")
   protected String _invert;

   // 添加遮罩的定义
   @AName("mask")
   protected String _mask;

   // 遮罩颜色的定义
   @AName("maskcolor")
   protected String _maskcolor;

   // 反转显示的定义
   @AName("mirror")
   protected String _mirror;

   // 透明度的定义
   @AName("opacity")
   protected String _opacity;

   // 旋转的定义
   @AName("rotation")
   protected String _rotation;

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
   // <T>获得灰度显示的内容。</T>
   //
   // @return 灰度显示
   //============================================================
   public String getGrayscale(){
      return _grayscale;
   }

   //============================================================
   // <T>设置灰度显示的内容。</T>
   //
   // @param value 灰度显示
   //============================================================
   public void setGrayscale(String value){
      _grayscale = value;
   }

   //============================================================
   // <T>获得反相显示的内容。</T>
   //
   // @return 反相显示
   //============================================================
   public String getInvert(){
      return _invert;
   }

   //============================================================
   // <T>设置反相显示的内容。</T>
   //
   // @param value 反相显示
   //============================================================
   public void setInvert(String value){
      _invert = value;
   }

   //============================================================
   // <T>获得添加遮罩的内容。</T>
   //
   // @return 添加遮罩
   //============================================================
   public String getMask(){
      return _mask;
   }

   //============================================================
   // <T>设置添加遮罩的内容。</T>
   //
   // @param value 添加遮罩
   //============================================================
   public void setMask(String value){
      _mask = value;
   }

   //============================================================
   // <T>获得遮罩颜色的内容。</T>
   //
   // @return 遮罩颜色
   //============================================================
   public String getMaskcolor(){
      return _maskcolor;
   }

   //============================================================
   // <T>设置遮罩颜色的内容。</T>
   //
   // @param value 遮罩颜色
   //============================================================
   public void setMaskcolor(String value){
      _maskcolor = value;
   }

   //============================================================
   // <T>获得反转显示的内容。</T>
   //
   // @return 反转显示
   //============================================================
   public String getMirror(){
      return _mirror;
   }

   //============================================================
   // <T>设置反转显示的内容。</T>
   //
   // @param value 反转显示
   //============================================================
   public void setMirror(String value){
      _mirror = value;
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
   // <T>获得旋转的内容。</T>
   //
   // @return 旋转
   //============================================================
   public String getRotation(){
      return _rotation;
   }

   //============================================================
   // <T>设置旋转的内容。</T>
   //
   // @param value 旋转
   //============================================================
   public void setRotation(String value){
      _rotation = value;
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
      }else if(PTY_GRAYSCALE.equalsIgnoreCase(name)){
         return getGrayscale();
      }else if(PTY_INVERT.equalsIgnoreCase(name)){
         return getInvert();
      }else if(PTY_MASK.equalsIgnoreCase(name)){
         return getMask();
      }else if(PTY_MASKCOLOR.equalsIgnoreCase(name)){
         return getMaskcolor();
      }else if(PTY_MIRROR.equalsIgnoreCase(name)){
         return getMirror();
      }else if(PTY_OPACITY.equalsIgnoreCase(name)){
         return getOpacity();
      }else if(PTY_ROTATION.equalsIgnoreCase(name)){
         return getRotation();
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
      }else if(PTY_GRAYSCALE.equalsIgnoreCase(name)){
         setGrayscale(value);
      }else if(PTY_INVERT.equalsIgnoreCase(name)){
         setInvert(value);
      }else if(PTY_MASK.equalsIgnoreCase(name)){
         setMask(value);
      }else if(PTY_MASKCOLOR.equalsIgnoreCase(name)){
         setMaskcolor(value);
      }else if(PTY_MIRROR.equalsIgnoreCase(name)){
         setMirror(value);
      }else if(PTY_OPACITY.equalsIgnoreCase(name)){
         setOpacity(value);
      }else if(PTY_ROTATION.equalsIgnoreCase(name)){
         setRotation(value);
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
         if(config.contains("grayscale")){
            setGrayscale(config.get(PTY_GRAYSCALE));
         }
         if(config.contains("invert")){
            setInvert(config.get(PTY_INVERT));
         }
         if(config.contains("mask")){
            setMask(config.get(PTY_MASK));
         }
         if(config.contains("maskcolor")){
            setMaskcolor(config.get(PTY_MASKCOLOR));
         }
         if(config.contains("mirror")){
            setMirror(config.get(PTY_MIRROR));
         }
         if(config.contains("opacity")){
            setOpacity(config.get(PTY_OPACITY));
         }
         if(config.contains("rotation")){
            setRotation(config.get(PTY_ROTATION));
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
         if(config.contains("grayscale")){
            setGrayscale(config.get(PTY_GRAYSCALE));
         }
         if(config.contains("invert")){
            setInvert(config.get(PTY_INVERT));
         }
         if(config.contains("mask")){
            setMask(config.get(PTY_MASK));
         }
         if(config.contains("maskcolor")){
            setMaskcolor(config.get(PTY_MASKCOLOR));
         }
         if(config.contains("mirror")){
            setMirror(config.get(PTY_MIRROR));
         }
         if(config.contains("opacity")){
            setOpacity(config.get(PTY_OPACITY));
         }
         if(config.contains("rotation")){
            setRotation(config.get(PTY_ROTATION));
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
         if(config.contains("grayscale")){
            setGrayscale(config.get(PTY_GRAYSCALE));
         }
         if(config.contains("invert")){
            setInvert(config.get(PTY_INVERT));
         }
         if(config.contains("mask")){
            setMask(config.get(PTY_MASK));
         }
         if(config.contains("maskcolor")){
            setMaskcolor(config.get(PTY_MASKCOLOR));
         }
         if(config.contains("mirror")){
            setMirror(config.get(PTY_MIRROR));
         }
         if(config.contains("opacity")){
            setOpacity(config.get(PTY_OPACITY));
         }
         if(config.contains("rotation")){
            setRotation(config.get(PTY_ROTATION));
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
         if(RString.isEmpty(getGrayscale())){
            if(config.contains("grayscale")){
               setGrayscale(config.get(PTY_GRAYSCALE));
            }
         }
         if(RString.isEmpty(getInvert())){
            if(config.contains("invert")){
               setInvert(config.get(PTY_INVERT));
            }
         }
         if(RString.isEmpty(getMask())){
            if(config.contains("mask")){
               setMask(config.get(PTY_MASK));
            }
         }
         if(RString.isEmpty(getMaskcolor())){
            if(config.contains("maskcolor")){
               setMaskcolor(config.get(PTY_MASKCOLOR));
            }
         }
         if(RString.isEmpty(getMirror())){
            if(config.contains("mirror")){
               setMirror(config.get(PTY_MIRROR));
            }
         }
         if(RString.isEmpty(getOpacity())){
            if(config.contains("opacity")){
               setOpacity(config.get(PTY_OPACITY));
            }
         }
         if(RString.isEmpty(getRotation())){
            if(config.contains("rotation")){
               setRotation(config.get(PTY_ROTATION));
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
         if(RString.isNotEmpty(getGrayscale())){
            config.set(PTY_GRAYSCALE, getGrayscale());
         }
         if(RString.isNotEmpty(getInvert())){
            config.set(PTY_INVERT, getInvert());
         }
         if(RString.isNotEmpty(getMask())){
            config.set(PTY_MASK, getMask());
         }
         if(RString.isNotEmpty(getMaskcolor())){
            config.set(PTY_MASKCOLOR, getMaskcolor());
         }
         if(RString.isNotEmpty(getMirror())){
            config.set(PTY_MIRROR, getMirror());
         }
         if(RString.isNotEmpty(getOpacity())){
            config.set(PTY_OPACITY, getOpacity());
         }
         if(RString.isNotEmpty(getRotation())){
            config.set(PTY_ROTATION, getRotation());
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
         if(RString.isNotEmpty(getGrayscale())){
            config.set(PTY_GRAYSCALE, getGrayscale());
         }
         if(RString.isNotEmpty(getInvert())){
            config.set(PTY_INVERT, getInvert());
         }
         if(RString.isNotEmpty(getMask())){
            config.set(PTY_MASK, getMask());
         }
         if(RString.isNotEmpty(getMaskcolor())){
            config.set(PTY_MASKCOLOR, getMaskcolor());
         }
         if(RString.isNotEmpty(getMirror())){
            config.set(PTY_MIRROR, getMirror());
         }
         if(RString.isNotEmpty(getOpacity())){
            config.set(PTY_OPACITY, getOpacity());
         }
         if(RString.isNotEmpty(getRotation())){
            config.set(PTY_ROTATION, getRotation());
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
         String sGrayscale = getGrayscale();
         if(RString.isNotEmpty(sGrayscale)){
            config.set(PTY_GRAYSCALE, sGrayscale);
         }
         String sInvert = getInvert();
         if(RString.isNotEmpty(sInvert)){
            config.set(PTY_INVERT, sInvert);
         }
         String sMask = getMask();
         if(RString.isNotEmpty(sMask)){
            config.set(PTY_MASK, sMask);
         }
         String sMaskcolor = getMaskcolor();
         if(RString.isNotEmpty(sMaskcolor)){
            config.set(PTY_MASKCOLOR, sMaskcolor);
         }
         String sMirror = getMirror();
         if(RString.isNotEmpty(sMirror)){
            config.set(PTY_MIRROR, sMirror);
         }
         String sOpacity = getOpacity();
         if(RString.isNotEmpty(sOpacity)){
            config.set(PTY_OPACITY, sOpacity);
         }
         String sRotation = getRotation();
         if(RString.isNotEmpty(sRotation)){
            config.set(PTY_ROTATION, sRotation);
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
         String sGrayscale = getGrayscale();
         if(RString.isNotEmpty(sGrayscale)){
            config.set(PTY_GRAYSCALE, sGrayscale);
         }
         String sInvert = getInvert();
         if(RString.isNotEmpty(sInvert)){
            config.set(PTY_INVERT, sInvert);
         }
         String sMask = getMask();
         if(RString.isNotEmpty(sMask)){
            config.set(PTY_MASK, sMask);
         }
         String sMaskcolor = getMaskcolor();
         if(RString.isNotEmpty(sMaskcolor)){
            config.set(PTY_MASKCOLOR, sMaskcolor);
         }
         String sMirror = getMirror();
         if(RString.isNotEmpty(sMirror)){
            config.set(PTY_MIRROR, sMirror);
         }
         String sOpacity = getOpacity();
         if(RString.isNotEmpty(sOpacity)){
            config.set(PTY_OPACITY, sOpacity);
         }
         String sRotation = getRotation();
         if(RString.isNotEmpty(sRotation)){
            config.set(PTY_ROTATION, sRotation);
         }
      }
   }
}