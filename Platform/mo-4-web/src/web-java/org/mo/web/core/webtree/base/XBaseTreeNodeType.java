package org.mo.web.core.webtree.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.web.core.webtree.common.XObjectFace;
import org.mo.web.core.webtree.common.XStyleFace;

//============================================================
// <T>节点类型对象的XML节点基类。</T>
//============================================================
public abstract class XBaseTreeNodeType
      extends FXmlObject
      implements
         XObjectFace,
         XStyleFace
{
   // 名称定义
   public static final String NAME = "TreeNodeType";

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

   // 注释信息的名称定义
   public static final String PTY_NOTE = "note";

   // 字体颜色的名称定义
   public static final String PTY_COLOR = "color";

   // 背景颜色的名称定义
   public static final String PTY_BG_COLOR = "bg_color";

   // 类型的名称定义
   public static final String PTY_TYPE = "type";

   // 类型名称的名称定义
   public static final String PTY_TYPE_NAME = "type_name";

   // 图标的名称定义
   public static final String PTY_ICON = "icon";

   // 命令的名称定义
   public static final String PTY_ACTION = "action";

   // 服务的名称定义
   public static final String PTY_SERVICE = "service";

   // 排序方式的名称定义
   public static final String PTY_ORDER_TYPE = "order_type";

   // 属性内容的名称定义
   public static final String PTY_ATTRIBUTES = "attributes";

   // 名称的定义
   @AName("name")
   protected String _name;

   // 标签的定义
   @AName("label")
   protected FMultiString _label = new FMultiString();

   // 有效性的定义
   @AName("is_valid")
   protected Boolean _isValid = Boolean.FALSE;

   // 注释信息的定义
   @AName("note")
   protected FMultiString _note = new FMultiString();

   // 字体颜色的定义
   @AName("color")
   protected String _color;

   // 背景颜色的定义
   @AName("bg_color")
   protected String _bgColor;

   // 类型的定义
   @AName("type")
   protected String _type;

   // 类型名称的定义
   @AName("type_name")
   protected String _typeName;

   // 图标的定义
   @AName("icon")
   protected String _icon;

   // 命令的定义
   @AName("action")
   protected String _action;

   // 服务的定义
   @AName("service")
   protected String _service;

   // 排序方式的定义
   @AName("order_type")
   protected String _orderType;

   // 属性内容的定义
   @AName("attributes")
   protected String _attributes;

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
   // <T>获得字体颜色的内容。</T>
   //
   // @return 字体颜色
   //============================================================
   public String getColor(){
      return _color;
   }

   //============================================================
   // <T>设置字体颜色的内容。</T>
   //
   // @param value 字体颜色
   //============================================================
   public void setColor(String value){
      _color = value;
   }

   //============================================================
   // <T>获得背景颜色的内容。</T>
   //
   // @return 背景颜色
   //============================================================
   public String getBgColor(){
      return _bgColor;
   }

   //============================================================
   // <T>设置背景颜色的内容。</T>
   //
   // @param value 背景颜色
   //============================================================
   public void setBgColor(String value){
      _bgColor = value;
   }

   //============================================================
   // <T>获得类型的内容。</T>
   //
   // @return 类型
   //============================================================
   public String getType(){
      return _type;
   }

   //============================================================
   // <T>设置类型的内容。</T>
   //
   // @param value 类型
   //============================================================
   public void setType(String value){
      _type = value;
   }

   //============================================================
   // <T>获得类型名称的内容。</T>
   //
   // @return 类型名称
   //============================================================
   public String getTypeName(){
      return _typeName;
   }

   //============================================================
   // <T>设置类型名称的内容。</T>
   //
   // @param value 类型名称
   //============================================================
   public void setTypeName(String value){
      _typeName = value;
   }

   //============================================================
   // <T>获得图标的内容。</T>
   //
   // @return 图标
   //============================================================
   public String getIcon(){
      return _icon;
   }

   //============================================================
   // <T>设置图标的内容。</T>
   //
   // @param value 图标
   //============================================================
   public void setIcon(String value){
      _icon = value;
   }

   //============================================================
   // <T>获得命令的内容。</T>
   //
   // @return 命令
   //============================================================
   public String getAction(){
      return _action;
   }

   //============================================================
   // <T>设置命令的内容。</T>
   //
   // @param value 命令
   //============================================================
   public void setAction(String value){
      _action = value;
   }

   //============================================================
   // <T>获得服务的内容。</T>
   //
   // @return 服务
   //============================================================
   public String getService(){
      return _service;
   }

   //============================================================
   // <T>设置服务的内容。</T>
   //
   // @param value 服务
   //============================================================
   public void setService(String value){
      _service = value;
   }

   //============================================================
   // <T>获得排序方式的内容。</T>
   //
   // @return 排序方式
   //============================================================
   public String getOrderType(){
      return _orderType;
   }

   //============================================================
   // <T>设置排序方式的内容。</T>
   //
   // @param value 排序方式
   //============================================================
   public void setOrderType(String value){
      _orderType = value;
   }

   //============================================================
   // <T>获得属性内容的内容。</T>
   //
   // @return 属性内容
   //============================================================
   public String getAttributes(){
      return _attributes;
   }

   //============================================================
   // <T>设置属性内容的内容。</T>
   //
   // @param value 属性内容
   //============================================================
   public void setAttributes(String value){
      _attributes = value;
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
      }else if(PTY_COLOR.equalsIgnoreCase(name)){
         return getColor();
      }else if(PTY_BG_COLOR.equalsIgnoreCase(name)){
         return getBgColor();
      }else if(PTY_TYPE.equalsIgnoreCase(name)){
         return getType();
      }else if(PTY_TYPE_NAME.equalsIgnoreCase(name)){
         return getTypeName();
      }else if(PTY_ICON.equalsIgnoreCase(name)){
         return getIcon();
      }else if(PTY_ACTION.equalsIgnoreCase(name)){
         return getAction();
      }else if(PTY_SERVICE.equalsIgnoreCase(name)){
         return getService();
      }else if(PTY_ORDER_TYPE.equalsIgnoreCase(name)){
         return getOrderType();
      }else if(PTY_ATTRIBUTES.equalsIgnoreCase(name)){
         return getAttributes();
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
      }else if(PTY_COLOR.equalsIgnoreCase(name)){
         setColor(value);
      }else if(PTY_BG_COLOR.equalsIgnoreCase(name)){
         setBgColor(value);
      }else if(PTY_TYPE.equalsIgnoreCase(name)){
         setType(value);
      }else if(PTY_TYPE_NAME.equalsIgnoreCase(name)){
         setTypeName(value);
      }else if(PTY_ICON.equalsIgnoreCase(name)){
         setIcon(value);
      }else if(PTY_ACTION.equalsIgnoreCase(name)){
         setAction(value);
      }else if(PTY_SERVICE.equalsIgnoreCase(name)){
         setService(value);
      }else if(PTY_ORDER_TYPE.equalsIgnoreCase(name)){
         setOrderType(value);
      }else if(PTY_ATTRIBUTES.equalsIgnoreCase(name)){
         setAttributes(value);
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
         if(config.contains("color")){
            setColor(config.get(PTY_COLOR));
         }
         if(config.contains("bg_color")){
            setBgColor(config.get(PTY_BG_COLOR));
         }
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
         }
         if(config.contains("type_name")){
            setTypeName(config.get(PTY_TYPE_NAME));
         }
         if(config.contains("icon")){
            setIcon(config.get(PTY_ICON));
         }
         if(config.contains("action")){
            setAction(config.get(PTY_ACTION));
         }
         if(config.contains("service")){
            setService(config.get(PTY_SERVICE));
         }
         if(config.contains("order_type")){
            setOrderType(config.get(PTY_ORDER_TYPE));
         }
         if(config.contains("attributes")){
            setAttributes(config.get(PTY_ATTRIBUTES));
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
         if(config.contains("note")){
            setNote(config.get(PTY_NOTE));
         }
         if(config.contains("color")){
            setColor(config.get(PTY_COLOR));
         }
         if(config.contains("bg_color")){
            setBgColor(config.get(PTY_BG_COLOR));
         }
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
         }
         if(config.contains("type_name")){
            setTypeName(config.get(PTY_TYPE_NAME));
         }
         if(config.contains("icon")){
            setIcon(config.get(PTY_ICON));
         }
         if(config.contains("action")){
            setAction(config.get(PTY_ACTION));
         }
         if(config.contains("service")){
            setService(config.get(PTY_SERVICE));
         }
         if(config.contains("order_type")){
            setOrderType(config.get(PTY_ORDER_TYPE));
         }
         if(config.contains("attributes")){
            setAttributes(config.get(PTY_ATTRIBUTES));
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
         if(config.contains("bg_color")){
            setBgColor(config.get(PTY_BG_COLOR));
         }
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
         }
         if(config.contains("type_name")){
            setTypeName(config.get(PTY_TYPE_NAME));
         }
         if(config.contains("icon")){
            setIcon(config.get(PTY_ICON));
         }
         if(config.contains("action")){
            setAction(config.get(PTY_ACTION));
         }
         if(config.contains("service")){
            setService(config.get(PTY_SERVICE));
         }
         if(config.contains("order_type")){
            setOrderType(config.get(PTY_ORDER_TYPE));
         }
         if(config.contains("attributes")){
            setAttributes(config.get(PTY_ATTRIBUTES));
         }
      }else if(EXmlConfig.Default == type){
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
         if(RString.isNotEmpty(getColor())){
            config.set(PTY_COLOR, getColor());
         }
         if(RString.isNotEmpty(getBgColor())){
            config.set(PTY_BG_COLOR, getBgColor());
         }
         if(RString.isNotEmpty(getType())){
            config.set(PTY_TYPE, getType());
         }
         if(RString.isNotEmpty(getTypeName())){
            config.set(PTY_TYPE_NAME, getTypeName());
         }
         if(RString.isNotEmpty(getIcon())){
            config.set(PTY_ICON, getIcon());
         }
         if(RString.isNotEmpty(getAction())){
            config.set(PTY_ACTION, getAction());
         }
         if(RString.isNotEmpty(getService())){
            config.set(PTY_SERVICE, getService());
         }
         if(RString.isNotEmpty(getOrderType())){
            config.set(PTY_ORDER_TYPE, getOrderType());
         }
         if(RString.isNotEmpty(getAttributes())){
            config.set(PTY_ATTRIBUTES, getAttributes());
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
         if(RString.isNotEmpty(getNote())){
            config.set(PTY_NOTE, getNote());
         }
         if(RString.isNotEmpty(getColor())){
            config.set(PTY_COLOR, getColor());
         }
         if(RString.isNotEmpty(getBgColor())){
            config.set(PTY_BG_COLOR, getBgColor());
         }
         if(RString.isNotEmpty(getType())){
            config.set(PTY_TYPE, getType());
         }
         if(RString.isNotEmpty(getTypeName())){
            config.set(PTY_TYPE_NAME, getTypeName());
         }
         if(RString.isNotEmpty(getIcon())){
            config.set(PTY_ICON, getIcon());
         }
         if(RString.isNotEmpty(getAction())){
            config.set(PTY_ACTION, getAction());
         }
         if(RString.isNotEmpty(getService())){
            config.set(PTY_SERVICE, getService());
         }
         if(RString.isNotEmpty(getOrderType())){
            config.set(PTY_ORDER_TYPE, getOrderType());
         }
         if(RString.isNotEmpty(getAttributes())){
            config.set(PTY_ATTRIBUTES, getAttributes());
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
         String sBgColor = getBgColor();
         if(RString.isNotEmpty(sBgColor)){
            config.set(PTY_BG_COLOR, sBgColor);
         }
         String sType = getType();
         if(RString.isNotEmpty(sType)){
            config.set(PTY_TYPE, sType);
         }
         String sTypeName = getTypeName();
         if(RString.isNotEmpty(sTypeName)){
            config.set(PTY_TYPE_NAME, sTypeName);
         }
         String sIcon = getIcon();
         if(RString.isNotEmpty(sIcon)){
            config.set(PTY_ICON, sIcon);
         }
         String sAction = getAction();
         if(RString.isNotEmpty(sAction)){
            config.set(PTY_ACTION, sAction);
         }
         String sService = getService();
         if(RString.isNotEmpty(sService)){
            config.set(PTY_SERVICE, sService);
         }
         String sOrderType = getOrderType();
         if(RString.isNotEmpty(sOrderType)){
            config.set(PTY_ORDER_TYPE, sOrderType);
         }
         String sAttributes = getAttributes();
         if(RString.isNotEmpty(sAttributes)){
            config.set(PTY_ATTRIBUTES, sAttributes);
         }
      }else if(EXmlConfig.Default == type){
      }
   }
}