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
// <T>目录节点对象的XML节点基类。</T>
//============================================================
public abstract class XBaseTreeNode
      extends FXmlObject
      implements
         XObjectFace,
         XStyleFace
{
   // 名称定义
   public static final String NAME = "TreeNode";

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

   // 节点类型的名称定义
   public static final String PTY_TYPE = "type";

   // 唯一标识的名称定义
   public static final String PTY_UUID = "uuid";

   // 图标的名称定义
   public static final String PTY_ICON = "icon";

   // 含有子节点的名称定义
   public static final String PTY_CHILD = "child";

   // 可配置的名称定义
   public static final String PTY_IS_CONFIG = "is_config";

   // 排序方式的名称定义
   public static final String PTY_ORDER_TYPE = "order_type";

   // 属性内容的名称定义
   public static final String PTY_ATTRIBUTES = "attributes";

   // 是否选中的名称定义
   public static final String PTY_CHECKED = "checked";

   // 是否展开的名称定义
   public static final String PTY_EXTENDED = "extended";

   // 关联目录的名称定义
   public static final String PTY_LINK_TREE = "link_tree";

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

   // 节点类型的定义
   @AName("type")
   protected String _type;

   // 唯一标识的定义
   @AName("uuid")
   protected String _uuid;

   // 图标的定义
   @AName("icon")
   protected String _icon;

   // 含有子节点的定义
   @AName("child")
   protected Boolean _child = Boolean.FALSE;

   // 可配置的定义
   @AName("is_config")
   protected String _isConfig;

   // 排序方式的定义
   @AName("order_type")
   protected String _orderType;

   // 属性内容的定义
   @AName("attributes")
   protected String _attributes;

   // 是否选中的定义
   @AName("checked")
   protected Boolean _checked = Boolean.FALSE;

   // 是否展开的定义
   @AName("extended")
   protected Boolean _extended = Boolean.FALSE;

   // 关联目录的定义
   @AName("link_tree")
   protected String _linkTree;

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
   // <T>获得节点类型的内容。</T>
   //
   // @return 节点类型
   //============================================================
   public String getType(){
      return _type;
   }

   //============================================================
   // <T>设置节点类型的内容。</T>
   //
   // @param value 节点类型
   //============================================================
   public void setType(String value){
      _type = value;
   }

   //============================================================
   // <T>获得唯一标识的内容。</T>
   //
   // @return 唯一标识
   //============================================================
   public String getUuid(){
      return _uuid;
   }

   //============================================================
   // <T>设置唯一标识的内容。</T>
   //
   // @param value 唯一标识
   //============================================================
   public void setUuid(String value){
      _uuid = value;
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
   // <T>获得含有子节点的内容。</T>
   //
   // @return 含有子节点
   //============================================================
   public Boolean getChild(){
      return _child;
   }

   //============================================================
   // <T>设置含有子节点的内容。</T>
   //
   // @param value 含有子节点
   //============================================================
   public void setChild(Boolean value){
      _child = value;
   }

   //============================================================
   // <T>获得可配置的内容。</T>
   //
   // @return 可配置
   //============================================================
   public String getIsConfig(){
      return _isConfig;
   }

   //============================================================
   // <T>设置可配置的内容。</T>
   //
   // @param value 可配置
   //============================================================
   public void setIsConfig(String value){
      _isConfig = value;
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
   // <T>获得是否选中的内容。</T>
   //
   // @return 是否选中
   //============================================================
   public Boolean getChecked(){
      return _checked;
   }

   //============================================================
   // <T>设置是否选中的内容。</T>
   //
   // @param value 是否选中
   //============================================================
   public void setChecked(Boolean value){
      _checked = value;
   }

   //============================================================
   // <T>获得是否展开的内容。</T>
   //
   // @return 是否展开
   //============================================================
   public Boolean getExtended(){
      return _extended;
   }

   //============================================================
   // <T>设置是否展开的内容。</T>
   //
   // @param value 是否展开
   //============================================================
   public void setExtended(Boolean value){
      _extended = value;
   }

   //============================================================
   // <T>获得关联目录的内容。</T>
   //
   // @return 关联目录
   //============================================================
   public String getLinkTree(){
      return _linkTree;
   }

   //============================================================
   // <T>设置关联目录的内容。</T>
   //
   // @param value 关联目录
   //============================================================
   public void setLinkTree(String value){
      _linkTree = value;
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
      }else if(PTY_UUID.equalsIgnoreCase(name)){
         return getUuid();
      }else if(PTY_ICON.equalsIgnoreCase(name)){
         return getIcon();
      }else if(PTY_CHILD.equalsIgnoreCase(name)){
         return RBoolean.toString(getChild());
      }else if(PTY_IS_CONFIG.equalsIgnoreCase(name)){
         return getIsConfig();
      }else if(PTY_ORDER_TYPE.equalsIgnoreCase(name)){
         return getOrderType();
      }else if(PTY_ATTRIBUTES.equalsIgnoreCase(name)){
         return getAttributes();
      }else if(PTY_CHECKED.equalsIgnoreCase(name)){
         return RBoolean.toString(getChecked());
      }else if(PTY_EXTENDED.equalsIgnoreCase(name)){
         return RBoolean.toString(getExtended());
      }else if(PTY_LINK_TREE.equalsIgnoreCase(name)){
         return getLinkTree();
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
      }else if(PTY_UUID.equalsIgnoreCase(name)){
         setUuid(value);
      }else if(PTY_ICON.equalsIgnoreCase(name)){
         setIcon(value);
      }else if(PTY_CHILD.equalsIgnoreCase(name)){
         setChild(RBoolean.parse(value));
      }else if(PTY_IS_CONFIG.equalsIgnoreCase(name)){
         setIsConfig(value);
      }else if(PTY_ORDER_TYPE.equalsIgnoreCase(name)){
         setOrderType(value);
      }else if(PTY_ATTRIBUTES.equalsIgnoreCase(name)){
         setAttributes(value);
      }else if(PTY_CHECKED.equalsIgnoreCase(name)){
         setChecked(RBoolean.parse(value));
      }else if(PTY_EXTENDED.equalsIgnoreCase(name)){
         setExtended(RBoolean.parse(value));
      }else if(PTY_LINK_TREE.equalsIgnoreCase(name)){
         setLinkTree(value);
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
         if(config.contains("uuid")){
            setUuid(config.get(PTY_UUID));
         }
         if(config.contains("icon")){
            setIcon(config.get(PTY_ICON));
         }
         if(config.contains("child")){
            setChild(RBoolean.parse(config.get(PTY_CHILD)));
         }
         if(config.contains("is_config")){
            setIsConfig(config.get(PTY_IS_CONFIG));
         }
         if(config.contains("order_type")){
            setOrderType(config.get(PTY_ORDER_TYPE));
         }
         if(config.contains("attributes")){
            setAttributes(config.get(PTY_ATTRIBUTES));
         }
         if(config.contains("checked")){
            setChecked(RBoolean.parse(config.get(PTY_CHECKED)));
         }
         if(config.contains("extended")){
            setExtended(RBoolean.parse(config.get(PTY_EXTENDED)));
         }
         if(config.contains("link_tree")){
            setLinkTree(config.get(PTY_LINK_TREE));
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
         if(config.contains("uuid")){
            setUuid(config.get(PTY_UUID));
         }
         if(config.contains("icon")){
            setIcon(config.get(PTY_ICON));
         }
         if(config.contains("child")){
            setChild(RBoolean.parse(config.get(PTY_CHILD)));
         }
         if(config.contains("is_config")){
            setIsConfig(config.get(PTY_IS_CONFIG));
         }
         if(config.contains("order_type")){
            setOrderType(config.get(PTY_ORDER_TYPE));
         }
         if(config.contains("attributes")){
            setAttributes(config.get(PTY_ATTRIBUTES));
         }
         if(config.contains("checked")){
            setChecked(RBoolean.parse(config.get(PTY_CHECKED)));
         }
         if(config.contains("extended")){
            setExtended(RBoolean.parse(config.get(PTY_EXTENDED)));
         }
         if(config.contains("link_tree")){
            setLinkTree(config.get(PTY_LINK_TREE));
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
         if(config.contains("uuid")){
            setUuid(config.get(PTY_UUID));
         }
         if(config.contains("icon")){
            setIcon(config.get(PTY_ICON));
         }
         if(config.contains("child")){
            setChild(RBoolean.parse(config.get(PTY_CHILD)));
         }
         if(config.contains("is_config")){
            setIsConfig(config.get(PTY_IS_CONFIG));
         }
         if(config.contains("order_type")){
            setOrderType(config.get(PTY_ORDER_TYPE));
         }
         if(config.contains("attributes")){
            setAttributes(config.get(PTY_ATTRIBUTES));
         }
         if(config.contains("checked")){
            setChecked(RBoolean.parse(config.get(PTY_CHECKED)));
         }
         if(config.contains("extended")){
            setExtended(RBoolean.parse(config.get(PTY_EXTENDED)));
         }
         if(config.contains("link_tree")){
            setLinkTree(config.get(PTY_LINK_TREE));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getOrderType())){
            if(config.contains("order_type")){
               setOrderType(config.get(PTY_ORDER_TYPE));
            }
         }
         if(RString.isEmpty(getLinkTree())){
            if(config.contains("link_tree")){
               setLinkTree(config.get(PTY_LINK_TREE));
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
         if(RString.isNotEmpty(getColor())){
            config.set(PTY_COLOR, getColor());
         }
         if(RString.isNotEmpty(getBgColor())){
            config.set(PTY_BG_COLOR, getBgColor());
         }
         if(RString.isNotEmpty(getType())){
            config.set(PTY_TYPE, getType());
         }
         if(RString.isNotEmpty(getUuid())){
            config.set(PTY_UUID, getUuid());
         }
         if(RString.isNotEmpty(getIcon())){
            config.set(PTY_ICON, getIcon());
         }
         if(RBoolean.parse(getChild())){
            config.set(PTY_CHILD, RBoolean.toString(getChild()));
         }
         if(RString.isNotEmpty(getIsConfig())){
            config.set(PTY_IS_CONFIG, getIsConfig());
         }
         if(RString.isNotEmpty(getOrderType())){
            config.set(PTY_ORDER_TYPE, getOrderType());
         }
         if(RString.isNotEmpty(getAttributes())){
            config.set(PTY_ATTRIBUTES, getAttributes());
         }
         if(RBoolean.parse(getChecked())){
            config.set(PTY_CHECKED, RBoolean.toString(getChecked()));
         }
         if(RBoolean.parse(getExtended())){
            config.set(PTY_EXTENDED, RBoolean.toString(getExtended()));
         }
         if(RString.isNotEmpty(getLinkTree())){
            config.set(PTY_LINK_TREE, getLinkTree());
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
         if(RString.isNotEmpty(getUuid())){
            config.set(PTY_UUID, getUuid());
         }
         if(RString.isNotEmpty(getIcon())){
            config.set(PTY_ICON, getIcon());
         }
         if(RBoolean.parse(getChild())){
            config.set(PTY_CHILD, RBoolean.toString(getChild()));
         }
         if(RString.isNotEmpty(getIsConfig())){
            config.set(PTY_IS_CONFIG, getIsConfig());
         }
         if(RString.isNotEmpty(getOrderType())){
            config.set(PTY_ORDER_TYPE, getOrderType());
         }
         if(RString.isNotEmpty(getAttributes())){
            config.set(PTY_ATTRIBUTES, getAttributes());
         }
         if(RBoolean.parse(getChecked())){
            config.set(PTY_CHECKED, RBoolean.toString(getChecked()));
         }
         if(RBoolean.parse(getExtended())){
            config.set(PTY_EXTENDED, RBoolean.toString(getExtended()));
         }
         if(RString.isNotEmpty(getLinkTree())){
            config.set(PTY_LINK_TREE, getLinkTree());
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
         String sUuid = getUuid();
         if(RString.isNotEmpty(sUuid)){
            config.set(PTY_UUID, sUuid);
         }
         String sIcon = getIcon();
         if(RString.isNotEmpty(sIcon)){
            config.set(PTY_ICON, sIcon);
         }
         Boolean bChild = getChild();
         if(RBoolean.parse(bChild)){
            config.set(PTY_CHILD, RBoolean.toString(bChild));
         }
         String sIsConfig = getIsConfig();
         if(RString.isNotEmpty(sIsConfig)){
            config.set(PTY_IS_CONFIG, sIsConfig);
         }
         String sOrderType = getOrderType();
         if(RString.isNotEmpty(sOrderType)){
            config.set(PTY_ORDER_TYPE, sOrderType);
         }
         String sAttributes = getAttributes();
         if(RString.isNotEmpty(sAttributes)){
            config.set(PTY_ATTRIBUTES, sAttributes);
         }
         Boolean bChecked = getChecked();
         if(RBoolean.parse(bChecked)){
            config.set(PTY_CHECKED, RBoolean.toString(bChecked));
         }
         Boolean bExtended = getExtended();
         if(RBoolean.parse(bExtended)){
            config.set(PTY_EXTENDED, RBoolean.toString(bExtended));
         }
         String sLinkTree = getLinkTree();
         if(RString.isNotEmpty(sLinkTree)){
            config.set(PTY_LINK_TREE, sLinkTree);
         }
      }else if(EXmlConfig.Default == type){
         String sOrderType = getOrderType();
         if(RString.isNotEmpty(sOrderType)){
            config.set(PTY_ORDER_TYPE, sOrderType);
         }
         String sLinkTree = getLinkTree();
         if(RString.isNotEmpty(sLinkTree)){
            config.set(PTY_LINK_TREE, sLinkTree);
         }
      }
   }
}