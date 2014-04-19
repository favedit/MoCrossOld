package org.mo.web.core.webtools.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.web.core.webtools.common.XObjectFace;
import org.mo.web.core.webtools.common.XIconFace;
import org.mo.web.core.webtools.common.XActionFace;

//============================================================
// <T>按键对象的XML节点基类。</T>
//============================================================
public abstract class XBaseToolButton
      extends FXmlObject
      implements
         XObjectFace,
         XIconFace,
         XActionFace
{
   // 名称定义
   public static final String NAME = "ToolButton";

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

   // 类型的名称定义
   public static final String PTY__TYPE = "_type";

   // 标签的名称定义
   public static final String PTY_LABEL = "label";

   // 有效性的名称定义
   public static final String PTY_IS_VALID = "is_valid";

   // 注释信息的名称定义
   public static final String PTY_NOTE = "note";

   // 图标的名称定义
   public static final String PTY_ICON = "icon";

   // 禁止图标的名称定义
   public static final String PTY_ICON_DISABLE = "icon_disable";

   // 命令的名称定义
   public static final String PTY_ACTION = "action";

   // 页面的名称定义
   public static final String PTY_PAGE = "page";

   // 属性内容的名称定义
   public static final String PTY_ATTRIBUTES = "attributes";

   // 按键类型的名称定义
   public static final String PTY_TYPE = "type";

   // 显示入口的名称定义
   public static final String PTY_DISP_ACCESS = "disp_access";

   // 热键的名称定义
   public static final String PTY_HOTKEY = "hotkey";

   // 名称的定义
   @AName("name")
   protected String _name;

   // 类型的定义
   @AName("_type")
   protected String __type;

   // 标签的定义
   @AName("label")
   protected FMultiString _label = new FMultiString();

   // 有效性的定义
   @AName("is_valid")
   protected Boolean _isValid = Boolean.FALSE;

   // 注释信息的定义
   @AName("note")
   protected FMultiString _note = new FMultiString();

   // 图标的定义
   @AName("icon")
   protected String _icon;

   // 禁止图标的定义
   @AName("icon_disable")
   protected String _iconDisable;

   // 命令的定义
   @AName("action")
   protected String _action;

   // 页面的定义
   @AName("page")
   protected String _page;

   // 属性内容的定义
   @AName("attributes")
   protected String _attributes;

   // 按键类型的定义
   @AName("type")
   protected String _type;

   // 显示入口的定义
   @AName("disp_access")
   protected String _dispAccess;

   // 热键的定义
   @AName("hotkey")
   protected String _hotkey;

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
   // <T>获得类型的内容。</T>
   //
   // @return 类型
   //============================================================
   public String get_type(){
      return __type;
   }

   //============================================================
   // <T>设置类型的内容。</T>
   //
   // @param value 类型
   //============================================================
   public void set_type(String value){
      __type = value;
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
   // <T>获得禁止图标的内容。</T>
   //
   // @return 禁止图标
   //============================================================
   public String getIconDisable(){
      return _iconDisable;
   }

   //============================================================
   // <T>设置禁止图标的内容。</T>
   //
   // @param value 禁止图标
   //============================================================
   public void setIconDisable(String value){
      _iconDisable = value;
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
   // <T>获得页面的内容。</T>
   //
   // @return 页面
   //============================================================
   public String getPage(){
      return _page;
   }

   //============================================================
   // <T>设置页面的内容。</T>
   //
   // @param value 页面
   //============================================================
   public void setPage(String value){
      _page = value;
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
   // <T>获得按键类型的内容。</T>
   //
   // @return 按键类型
   //============================================================
   public String getType(){
      return _type;
   }

   //============================================================
   // <T>设置按键类型的内容。</T>
   //
   // @param value 按键类型
   //============================================================
   public void setType(String value){
      _type = value;
   }

   //============================================================
   // <T>获得显示入口的内容。</T>
   //
   // @return 显示入口
   //============================================================
   public String getDispAccess(){
      return _dispAccess;
   }

   //============================================================
   // <T>设置显示入口的内容。</T>
   //
   // @param value 显示入口
   //============================================================
   public void setDispAccess(String value){
      _dispAccess = value;
   }

   //============================================================
   // <T>获得热键的内容。</T>
   //
   // @return 热键
   //============================================================
   public String getHotkey(){
      return _hotkey;
   }

   //============================================================
   // <T>设置热键的内容。</T>
   //
   // @param value 热键
   //============================================================
   public void setHotkey(String value){
      _hotkey = value;
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
      }else if(PTY__TYPE.equalsIgnoreCase(name)){
         return get_type();
      }else if(PTY_LABEL.equalsIgnoreCase(name)){
         return getLabel();
      }else if(PTY_IS_VALID.equalsIgnoreCase(name)){
         return RBoolean.toString(getIsValid());
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         return getNote();
      }else if(PTY_ICON.equalsIgnoreCase(name)){
         return getIcon();
      }else if(PTY_ICON_DISABLE.equalsIgnoreCase(name)){
         return getIconDisable();
      }else if(PTY_ACTION.equalsIgnoreCase(name)){
         return getAction();
      }else if(PTY_PAGE.equalsIgnoreCase(name)){
         return getPage();
      }else if(PTY_ATTRIBUTES.equalsIgnoreCase(name)){
         return getAttributes();
      }else if(PTY_TYPE.equalsIgnoreCase(name)){
         return getType();
      }else if(PTY_DISP_ACCESS.equalsIgnoreCase(name)){
         return getDispAccess();
      }else if(PTY_HOTKEY.equalsIgnoreCase(name)){
         return getHotkey();
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
      }else if(PTY__TYPE.equalsIgnoreCase(name)){
         set_type(value);
      }else if(PTY_LABEL.equalsIgnoreCase(name)){
         setLabel(value);
      }else if(PTY_IS_VALID.equalsIgnoreCase(name)){
         setIsValid(RBoolean.parse(value));
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         setNote(value);
      }else if(PTY_ICON.equalsIgnoreCase(name)){
         setIcon(value);
      }else if(PTY_ICON_DISABLE.equalsIgnoreCase(name)){
         setIconDisable(value);
      }else if(PTY_ACTION.equalsIgnoreCase(name)){
         setAction(value);
      }else if(PTY_PAGE.equalsIgnoreCase(name)){
         setPage(value);
      }else if(PTY_ATTRIBUTES.equalsIgnoreCase(name)){
         setAttributes(value);
      }else if(PTY_TYPE.equalsIgnoreCase(name)){
         setType(value);
      }else if(PTY_DISP_ACCESS.equalsIgnoreCase(name)){
         setDispAccess(value);
      }else if(PTY_HOTKEY.equalsIgnoreCase(name)){
         setHotkey(value);
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
         if(config.contains("_type")){
            set_type(config.get(PTY__TYPE));
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
         if(config.contains("icon")){
            setIcon(config.get(PTY_ICON));
         }
         if(config.contains("icon_disable")){
            setIconDisable(config.get(PTY_ICON_DISABLE));
         }
         if(config.contains("action")){
            setAction(config.get(PTY_ACTION));
         }
         if(config.contains("page")){
            setPage(config.get(PTY_PAGE));
         }
         if(config.contains("attributes")){
            setAttributes(config.get(PTY_ATTRIBUTES));
         }
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
         }
         if(config.contains("disp_access")){
            setDispAccess(config.get(PTY_DISP_ACCESS));
         }
         if(config.contains("hotkey")){
            setHotkey(config.get(PTY_HOTKEY));
         }
      }else if(EXmlConfig.Simple == type){
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("_type")){
            set_type(config.get(PTY__TYPE));
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
         if(config.contains("icon")){
            setIcon(config.get(PTY_ICON));
         }
         if(config.contains("icon_disable")){
            setIconDisable(config.get(PTY_ICON_DISABLE));
         }
         if(config.contains("action")){
            setAction(config.get(PTY_ACTION));
         }
         if(config.contains("page")){
            setPage(config.get(PTY_PAGE));
         }
         if(config.contains("attributes")){
            setAttributes(config.get(PTY_ATTRIBUTES));
         }
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
         }
         if(config.contains("disp_access")){
            setDispAccess(config.get(PTY_DISP_ACCESS));
         }
         if(config.contains("hotkey")){
            setHotkey(config.get(PTY_HOTKEY));
         }
      }else if(EXmlConfig.Value == type){
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("_type")){
            set_type(config.get(PTY__TYPE));
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
         if(config.contains("icon")){
            setIcon(config.get(PTY_ICON));
         }
         if(config.contains("icon_disable")){
            setIconDisable(config.get(PTY_ICON_DISABLE));
         }
         if(config.contains("action")){
            setAction(config.get(PTY_ACTION));
         }
         if(config.contains("page")){
            setPage(config.get(PTY_PAGE));
         }
         if(config.contains("attributes")){
            setAttributes(config.get(PTY_ATTRIBUTES));
         }
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
         }
         if(config.contains("disp_access")){
            setDispAccess(config.get(PTY_DISP_ACCESS));
         }
         if(config.contains("hotkey")){
            setHotkey(config.get(PTY_HOTKEY));
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
         if(RString.isNotEmpty(get_type())){
            config.set(PTY__TYPE, get_type());
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
         if(RString.isNotEmpty(getIcon())){
            config.set(PTY_ICON, getIcon());
         }
         if(RString.isNotEmpty(getIconDisable())){
            config.set(PTY_ICON_DISABLE, getIconDisable());
         }
         if(RString.isNotEmpty(getAction())){
            config.set(PTY_ACTION, getAction());
         }
         if(RString.isNotEmpty(getPage())){
            config.set(PTY_PAGE, getPage());
         }
         if(RString.isNotEmpty(getAttributes())){
            config.set(PTY_ATTRIBUTES, getAttributes());
         }
         if(RString.isNotEmpty(getType())){
            config.set(PTY_TYPE, getType());
         }
         if(RString.isNotEmpty(getDispAccess())){
            config.set(PTY_DISP_ACCESS, getDispAccess());
         }
         if(RString.isNotEmpty(getHotkey())){
            config.set(PTY_HOTKEY, getHotkey());
         }
      }else if(EXmlConfig.Simple == type){
         if(RString.isNotEmpty(getName())){
            config.set(PTY_NAME, getName());
         }
         if(RString.isNotEmpty(get_type())){
            config.set(PTY__TYPE, get_type());
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
         if(RString.isNotEmpty(getIcon())){
            config.set(PTY_ICON, getIcon());
         }
         if(RString.isNotEmpty(getIconDisable())){
            config.set(PTY_ICON_DISABLE, getIconDisable());
         }
         if(RString.isNotEmpty(getAction())){
            config.set(PTY_ACTION, getAction());
         }
         if(RString.isNotEmpty(getPage())){
            config.set(PTY_PAGE, getPage());
         }
         if(RString.isNotEmpty(getAttributes())){
            config.set(PTY_ATTRIBUTES, getAttributes());
         }
         if(RString.isNotEmpty(getType())){
            config.set(PTY_TYPE, getType());
         }
         if(RString.isNotEmpty(getDispAccess())){
            config.set(PTY_DISP_ACCESS, getDispAccess());
         }
         if(RString.isNotEmpty(getHotkey())){
            config.set(PTY_HOTKEY, getHotkey());
         }
      }else if(EXmlConfig.Value == type){
         String sName = getName();
         if(RString.isNotEmpty(sName)){
            config.set(PTY_NAME, sName);
         }
         String s_type = get_type();
         if(RString.isNotEmpty(s_type)){
            config.set(PTY__TYPE, s_type);
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
         String sIcon = getIcon();
         if(RString.isNotEmpty(sIcon)){
            config.set(PTY_ICON, sIcon);
         }
         String sIconDisable = getIconDisable();
         if(RString.isNotEmpty(sIconDisable)){
            config.set(PTY_ICON_DISABLE, sIconDisable);
         }
         String sAction = getAction();
         if(RString.isNotEmpty(sAction)){
            config.set(PTY_ACTION, sAction);
         }
         String sPage = getPage();
         if(RString.isNotEmpty(sPage)){
            config.set(PTY_PAGE, sPage);
         }
         String sAttributes = getAttributes();
         if(RString.isNotEmpty(sAttributes)){
            config.set(PTY_ATTRIBUTES, sAttributes);
         }
         String sType = getType();
         if(RString.isNotEmpty(sType)){
            config.set(PTY_TYPE, sType);
         }
         String sDispAccess = getDispAccess();
         if(RString.isNotEmpty(sDispAccess)){
            config.set(PTY_DISP_ACCESS, sDispAccess);
         }
         String sHotkey = getHotkey();
         if(RString.isNotEmpty(sHotkey)){
            config.set(PTY_HOTKEY, sHotkey);
         }
      }else if(EXmlConfig.Default == type){
      }
   }
}