package org.mo.eng.persistence.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.eng.persistence.common.XObjectFace;

//============================================================
// <T>持久化集合对象的XML节点基类。</T>
//
// @author system
//============================================================
public abstract class XBasePersistence
      extends FXmlObject
      implements
         XObjectFace
{
   // 名称
   public static final String NAME = "Persistence";

   // 名称的定义
   public static final String PTY_NAME = "name";

   // 标签的定义
   public static final String PTY_LABEL = "label";

   // 有效性的定义
   public static final String PTY_IS_VALID = "is_valid";

   // 描述信息的定义
   public static final String PTY_NOTE = "note";

   // 附加属性的定义
   public static final String PTY_ATTRIBUTES = "attributes";

   // 代码模板的定义
   public static final String PTY_TEMPLATE = "template";

   // 命名空间的定义
   public static final String PTY_NAMESPACE = "namespace";

   // 代码位置的定义
   public static final String PTY_SOURCE = "source";

   //============================================================
   // <T>是否指定名称。</T>
   //
   // @param name 名称
   // @return 是否指定名称
   //============================================================
   public static boolean isName(String name){
      return NAME.equals(name);
   }

   //============================================================
   // <T>是否指定接口。</T>
   //
   // @param name 接口
   // @return 是否指定接口
   //============================================================
   public static boolean isInstance(IXmlObject xobject){
      return NAME.equals(xobject.name());
   }

   //============================================================
   // <T>获得名称。</T>
   //
   // @return 名称
   //============================================================
   public String name(){
      return NAME;
   }

   // 名称的定义
   @AName("name")
   protected String _name;

   // 标签的定义
   @AName("label")
   protected FMultiString _label = new FMultiString();

   // 有效性的定义
   @AName("is_valid")
   protected Boolean _isValid = Boolean.FALSE;

   // 描述信息的定义
   @AName("note")
   protected FMultiString _note = new FMultiString();

   // 附加属性的定义
   @AName("attributes")
   protected String _attributes;

   // 代码模板的定义
   @AName("template")
   protected String _template;

   // 命名空间的定义
   @AName("namespace")
   protected String _namespace;

   // 代码位置的定义
   @AName("source")
   protected String _source;

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
   // <T>获得描述信息的内容。</T>
   //
   // @return 描述信息
   //============================================================
   public String getNote(){
      return _note.get();
   }

   //============================================================
   // <T>设置描述信息的内容。</T>
   //
   // @param value 描述信息
   //============================================================
   public void setNote(String value){
      _note.set(value);
   }

   //============================================================
   // <T>获得附加属性的内容。</T>
   //
   // @return 附加属性
   //============================================================
   public String getAttributes(){
      return _attributes;
   }

   //============================================================
   // <T>设置附加属性的内容。</T>
   //
   // @param value 附加属性
   //============================================================
   public void setAttributes(String value){
      _attributes = value;
   }

   //============================================================
   // <T>获得代码模板的内容。</T>
   //
   // @return 代码模板
   //============================================================
   public String getTemplate(){
      return _template;
   }

   //============================================================
   // <T>设置代码模板的内容。</T>
   //
   // @param value 代码模板
   //============================================================
   public void setTemplate(String value){
      _template = value;
   }

   //============================================================
   // <T>获得命名空间的内容。</T>
   //
   // @return 命名空间
   //============================================================
   public String getNamespace(){
      return _namespace;
   }

   //============================================================
   // <T>设置命名空间的内容。</T>
   //
   // @param value 命名空间
   //============================================================
   public void setNamespace(String value){
      _namespace = value;
   }

   //============================================================
   // <T>获得代码位置的内容。</T>
   //
   // @return 代码位置
   //============================================================
   public String getSource(){
      return _source;
   }

   //============================================================
   // <T>设置代码位置的内容。</T>
   //
   // @param value 代码位置
   //============================================================
   public void setSource(String value){
      _source = value;
   }

   //============================================================
   // <T>根据名称获得内容。</T>
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
      }else if(PTY_ATTRIBUTES.equalsIgnoreCase(name)){
         return getAttributes();
      }else if(PTY_TEMPLATE.equalsIgnoreCase(name)){
         return getTemplate();
      }else if(PTY_NAMESPACE.equalsIgnoreCase(name)){
         return getNamespace();
      }else if(PTY_SOURCE.equalsIgnoreCase(name)){
         return getSource();
      }
      return null;
   }

   //============================================================
   // <T>根据名称设置内容。</T>
   //
   // @param name 名称
   // @param value 内容
   //============================================================
   public void innerSet(String name,
                        String value){
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
      }else if(PTY_ATTRIBUTES.equalsIgnoreCase(name)){
         setAttributes(value);
      }else if(PTY_TEMPLATE.equalsIgnoreCase(name)){
         setTemplate(value);
      }else if(PTY_NAMESPACE.equalsIgnoreCase(name)){
         setNamespace(value);
      }else if(PTY_SOURCE.equalsIgnoreCase(name)){
         setSource(value);
      }
   }

   //============================================================
   // <T>从节点中加载设置信息。</T>
   //
   // @param config 节点
   // @param typeCd 类型
   //============================================================
   public void loadConfig(FXmlNode config,
                          EXmlConfig typeCd){
      super.loadConfig(config, typeCd);
      if(EXmlConfig.Full == typeCd){
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
         if(config.contains("attributes")){
            setAttributes(config.get(PTY_ATTRIBUTES));
         }
         if(config.contains("template")){
            setTemplate(config.get(PTY_TEMPLATE));
         }
         if(config.contains("namespace")){
            setNamespace(config.get(PTY_NAMESPACE));
         }
         if(config.contains("source")){
            setSource(config.get(PTY_SOURCE));
         }
      }else if(EXmlConfig.Simple == typeCd){
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
         if(config.contains("attributes")){
            setAttributes(config.get(PTY_ATTRIBUTES));
         }
         if(config.contains("template")){
            setTemplate(config.get(PTY_TEMPLATE));
         }
         if(config.contains("namespace")){
            setNamespace(config.get(PTY_NAMESPACE));
         }
         if(config.contains("source")){
            setSource(config.get(PTY_SOURCE));
         }
      }else if(EXmlConfig.Value == typeCd){
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
         if(config.contains("attributes")){
            setAttributes(config.get(PTY_ATTRIBUTES));
         }
         if(config.contains("template")){
            setTemplate(config.get(PTY_TEMPLATE));
         }
         if(config.contains("namespace")){
            setNamespace(config.get(PTY_NAMESPACE));
         }
         if(config.contains("source")){
            setSource(config.get(PTY_SOURCE));
         }
      }else if(EXmlConfig.Default == typeCd){
         if(RString.isEmpty(getNamespace())){
            if(config.contains("namespace")){
               setNamespace(config.get(PTY_NAMESPACE));
            }
         }
      }
   }

   //============================================================
   // <T>存储设置信息到节点中加载。</T>
   //
   // @param config 节点
   // @param typeCd 类型
   //============================================================
   public void saveConfig(FXmlNode config,
                          EXmlConfig typeCd){
      config.setName(NAME);
      super.saveConfig(config, typeCd);
      if(EXmlConfig.Full == typeCd){
         if(!RString.isEmpty(getName())){
            config.set(PTY_NAME, getName());
         }
         String label = _label.pack().toString();
         if(!RString.isEmpty(label)){
            config.set(PTY_LABEL, label);
         }
         if(RBoolean.parse(getIsValid())){
            config.set(PTY_IS_VALID, RBoolean.toString(getIsValid()));
         }
         String note = _note.pack().toString();
         if(!RString.isEmpty(note)){
            config.set(PTY_NOTE, note);
         }
         if(!RString.isEmpty(getAttributes())){
            config.set(PTY_ATTRIBUTES, getAttributes());
         }
         if(!RString.isEmpty(getTemplate())){
            config.set(PTY_TEMPLATE, getTemplate());
         }
         if(!RString.isEmpty(getNamespace())){
            config.set(PTY_NAMESPACE, getNamespace());
         }
         if(!RString.isEmpty(getSource())){
            config.set(PTY_SOURCE, getSource());
         }
      }else if(EXmlConfig.Simple == typeCd){
         if(!RString.isEmpty(getName())){
            config.set(PTY_NAME, getName());
         }
         if(!RString.isEmpty(getLabel())){
            config.set(PTY_LABEL, getLabel());
         }
         if(RBoolean.parse(getIsValid())){
            config.set(PTY_IS_VALID, RBoolean.toString(getIsValid()));
         }
         if(!RString.isEmpty(getNote())){
            config.set(PTY_NOTE, getNote());
         }
         if(!RString.isEmpty(getAttributes())){
            config.set(PTY_ATTRIBUTES, getAttributes());
         }
         if(!RString.isEmpty(getTemplate())){
            config.set(PTY_TEMPLATE, getTemplate());
         }
         if(!RString.isEmpty(getNamespace())){
            config.set(PTY_NAMESPACE, getNamespace());
         }
         if(!RString.isEmpty(getSource())){
            config.set(PTY_SOURCE, getSource());
         }
      }else if(EXmlConfig.Value == typeCd){
         String sName = getName();
         if(!RString.isEmpty(sName)){
            config.set(PTY_NAME, sName);
         }
         String sLabel = getLabel();
         if(!RString.isEmpty(sLabel)){
            config.set(PTY_LABEL, sLabel);
         }
         Boolean bIsValid = getIsValid();
         if(RBoolean.parse(bIsValid)){
            config.set(PTY_IS_VALID, RBoolean.toString(bIsValid));
         }
         String sNote = getNote();
         if(!RString.isEmpty(sNote)){
            config.set(PTY_NOTE, sNote);
         }
         String sAttributes = getAttributes();
         if(!RString.isEmpty(sAttributes)){
            config.set(PTY_ATTRIBUTES, sAttributes);
         }
         String sTemplate = getTemplate();
         if(!RString.isEmpty(sTemplate)){
            config.set(PTY_TEMPLATE, sTemplate);
         }
         String sNamespace = getNamespace();
         if(!RString.isEmpty(sNamespace)){
            config.set(PTY_NAMESPACE, sNamespace);
         }
         String sSource = getSource();
         if(!RString.isEmpty(sSource)){
            config.set(PTY_SOURCE, sSource);
         }
      }else if(EXmlConfig.Default == typeCd){
         String sNamespace = getNamespace();
         if(!RString.isEmpty(sNamespace)){
            config.set(PTY_NAMESPACE, sNamespace);
         }
      }
   }
}
