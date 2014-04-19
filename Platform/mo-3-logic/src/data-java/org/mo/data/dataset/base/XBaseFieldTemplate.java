package org.mo.data.dataset.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.data.dataset.common.XObjectFace;

//============================================================
// <T>字段模板对象的XML节点基类。</T>
//============================================================
public abstract class XBaseFieldTemplate
      extends FXmlObject
      implements
         XObjectFace
{
   // 名称定义
   public static final String NAME = "FieldTemplate";

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

   // 简称的名称定义
   public static final String PTY_ALIAS = "alias";

   // 标签的名称定义
   public static final String PTY_LABEL = "label";

   // 有效性的名称定义
   public static final String PTY_IS_VALID = "is_valid";

   // 属性列表的名称定义
   public static final String PTY_ATTRIBUTES = "attributes";

   // 注释信息的名称定义
   public static final String PTY_NOTE = "note";

   // 模板名称的名称定义
   public static final String PTY_TEMPLATE_NAME = "template_name";

   // 名称的定义
   @AName("name")
   protected String _name;

   // 简称的定义
   @AName("alias")
   protected String _alias;

   // 标签的定义
   @AName("label")
   protected FMultiString _label = new FMultiString();

   // 有效性的定义
   @AName("is_valid")
   protected Boolean _isValid = Boolean.FALSE;

   // 属性列表的定义
   @AName("attributes")
   protected String _attributes;

   // 注释信息的定义
   @AName("note")
   protected FMultiString _note = new FMultiString();

   // 模板名称的定义
   @AName("template_name")
   protected String _templateName;

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
   // <T>获得简称的内容。</T>
   //
   // @return 简称
   //============================================================
   public String getAlias(){
      return _alias;
   }

   //============================================================
   // <T>设置简称的内容。</T>
   //
   // @param value 简称
   //============================================================
   public void setAlias(String value){
      _alias = value;
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
   // <T>获得属性列表的内容。</T>
   //
   // @return 属性列表
   //============================================================
   public String getAttributes(){
      return _attributes;
   }

   //============================================================
   // <T>设置属性列表的内容。</T>
   //
   // @param value 属性列表
   //============================================================
   public void setAttributes(String value){
      _attributes = value;
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
   // <T>获得模板名称的内容。</T>
   //
   // @return 模板名称
   //============================================================
   public String getTemplateName(){
      return _templateName;
   }

   //============================================================
   // <T>设置模板名称的内容。</T>
   //
   // @param value 模板名称
   //============================================================
   public void setTemplateName(String value){
      _templateName = value;
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
      }else if(PTY_ALIAS.equalsIgnoreCase(name)){
         return getAlias();
      }else if(PTY_LABEL.equalsIgnoreCase(name)){
         return getLabel();
      }else if(PTY_IS_VALID.equalsIgnoreCase(name)){
         return RBoolean.toString(getIsValid());
      }else if(PTY_ATTRIBUTES.equalsIgnoreCase(name)){
         return getAttributes();
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         return getNote();
      }else if(PTY_TEMPLATE_NAME.equalsIgnoreCase(name)){
         return getTemplateName();
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
      }else if(PTY_ALIAS.equalsIgnoreCase(name)){
         setAlias(value);
      }else if(PTY_LABEL.equalsIgnoreCase(name)){
         setLabel(value);
      }else if(PTY_IS_VALID.equalsIgnoreCase(name)){
         setIsValid(RBoolean.parse(value));
      }else if(PTY_ATTRIBUTES.equalsIgnoreCase(name)){
         setAttributes(value);
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         setNote(value);
      }else if(PTY_TEMPLATE_NAME.equalsIgnoreCase(name)){
         setTemplateName(value);
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
         if(config.contains("alias")){
            setAlias(config.get(PTY_ALIAS));
         }
         if(config.contains("label")){
            _label.unpack(config.get(PTY_LABEL));
         }
         if(config.contains("is_valid")){
            setIsValid(RBoolean.parse(config.get(PTY_IS_VALID)));
         }
         if(config.contains("attributes")){
            setAttributes(config.get(PTY_ATTRIBUTES));
         }
         if(config.contains("note")){
            _note.unpack(config.get(PTY_NOTE));
         }
         if(config.contains("template_name")){
            setTemplateName(config.get(PTY_TEMPLATE_NAME));
         }
      }else if(EXmlConfig.Simple == type){
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("alias")){
            setAlias(config.get(PTY_ALIAS));
         }
         if(config.contains("label")){
            setLabel(config.get(PTY_LABEL));
         }
         if(config.contains("is_valid")){
            setIsValid(RBoolean.parse(config.get(PTY_IS_VALID)));
         }
         if(config.contains("attributes")){
            setAttributes(config.get(PTY_ATTRIBUTES));
         }
         if(config.contains("note")){
            setNote(config.get(PTY_NOTE));
         }
         if(config.contains("template_name")){
            setTemplateName(config.get(PTY_TEMPLATE_NAME));
         }
      }else if(EXmlConfig.Value == type){
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("alias")){
            setAlias(config.get(PTY_ALIAS));
         }
         if(config.contains("label")){
            setLabel(config.get(PTY_LABEL));
         }
         if(config.contains("is_valid")){
            setIsValid(RBoolean.parse(config.get(PTY_IS_VALID)));
         }
         if(config.contains("attributes")){
            setAttributes(config.get(PTY_ATTRIBUTES));
         }
         if(config.contains("note")){
            setNote(config.get(PTY_NOTE));
         }
         if(config.contains("template_name")){
            setTemplateName(config.get(PTY_TEMPLATE_NAME));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getTemplateName())){
            if(config.contains("template_name")){
               setTemplateName(config.get(PTY_TEMPLATE_NAME));
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
         if(RString.isNotEmpty(getAlias())){
            config.set(PTY_ALIAS, getAlias());
         }
         String label = _label.pack().toString();
         if(RString.isNotEmpty(label)){
            config.set(PTY_LABEL, label);
         }
         if(RBoolean.parse(getIsValid())){
            config.set(PTY_IS_VALID, RBoolean.toString(getIsValid()));
         }
         if(RString.isNotEmpty(getAttributes())){
            config.set(PTY_ATTRIBUTES, getAttributes());
         }
         String note = _note.pack().toString();
         if(RString.isNotEmpty(note)){
            config.set(PTY_NOTE, note);
         }
         if(RString.isNotEmpty(getTemplateName())){
            config.set(PTY_TEMPLATE_NAME, getTemplateName());
         }
      }else if(EXmlConfig.Simple == type){
         if(RString.isNotEmpty(getName())){
            config.set(PTY_NAME, getName());
         }
         if(RString.isNotEmpty(getAlias())){
            config.set(PTY_ALIAS, getAlias());
         }
         if(RString.isNotEmpty(getLabel())){
            config.set(PTY_LABEL, getLabel());
         }
         if(RBoolean.parse(getIsValid())){
            config.set(PTY_IS_VALID, RBoolean.toString(getIsValid()));
         }
         if(RString.isNotEmpty(getAttributes())){
            config.set(PTY_ATTRIBUTES, getAttributes());
         }
         if(RString.isNotEmpty(getNote())){
            config.set(PTY_NOTE, getNote());
         }
         if(RString.isNotEmpty(getTemplateName())){
            config.set(PTY_TEMPLATE_NAME, getTemplateName());
         }
      }else if(EXmlConfig.Value == type){
         String sName = getName();
         if(RString.isNotEmpty(sName)){
            config.set(PTY_NAME, sName);
         }
         String sAlias = getAlias();
         if(RString.isNotEmpty(sAlias)){
            config.set(PTY_ALIAS, sAlias);
         }
         String sLabel = getLabel();
         if(RString.isNotEmpty(sLabel)){
            config.set(PTY_LABEL, sLabel);
         }
         Boolean bIsValid = getIsValid();
         if(RBoolean.parse(bIsValid)){
            config.set(PTY_IS_VALID, RBoolean.toString(bIsValid));
         }
         String sAttributes = getAttributes();
         if(RString.isNotEmpty(sAttributes)){
            config.set(PTY_ATTRIBUTES, sAttributes);
         }
         String sNote = getNote();
         if(RString.isNotEmpty(sNote)){
            config.set(PTY_NOTE, sNote);
         }
         String sTemplateName = getTemplateName();
         if(RString.isNotEmpty(sTemplateName)){
            config.set(PTY_TEMPLATE_NAME, sTemplateName);
         }
      }else if(EXmlConfig.Default == type){
         String sTemplateName = getTemplateName();
         if(RString.isNotEmpty(sTemplateName)){
            config.set(PTY_TEMPLATE_NAME, sTemplateName);
         }
      }
   }
}