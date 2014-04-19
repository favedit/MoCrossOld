package org.mo.data.synchronizer.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.data.synchronizer.common.XObjectFace;

//============================================================
// <T>分组对象的XML节点基类。</T>
//============================================================
public abstract class XBaseSynchronizer
      extends FXmlObject
      implements
         XObjectFace
{
   // 名称定义
   public static final String NAME = "Synchronizer";

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

   // 代码的名称定义
   public static final String PTY_CODE = "code";

   // 名称的名称定义
   public static final String PTY_NAME = "name";

   // 标签的名称定义
   public static final String PTY_LABEL = "label";

   // 有效性的名称定义
   public static final String PTY_IS_VALID = "is_valid";

   // 注释的名称定义
   public static final String PTY_NOTE = "note";

   // 主域链接名称的名称定义
   public static final String PTY_DOMAIN_CONNECTION_NAME = "domain_connection_name";

   // 来源链接名称的名称定义
   public static final String PTY_SOURCE_CONNECTION_NAME = "source_connection_name";

   // 目标链接名称的名称定义
   public static final String PTY_TARGET_CONNECTION_NAME = "target_connection_name";

   // 代码的定义
   @AName("code")
   protected String _code;

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

   // 主域链接名称的定义
   @AName("domain_connection_name")
   protected String _domainConnectionName;

   // 来源链接名称的定义
   @AName("source_connection_name")
   protected String _sourceConnectionName;

   // 目标链接名称的定义
   @AName("target_connection_name")
   protected String _targetConnectionName;

   //============================================================
   // <T>获得代码的内容。</T>
   //
   // @return 代码
   //============================================================
   public String getCode(){
      return _code;
   }

   //============================================================
   // <T>设置代码的内容。</T>
   //
   // @param value 代码
   //============================================================
   public void setCode(String value){
      _code = value;
   }

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
   // <T>获得主域链接名称的内容。</T>
   //
   // @return 主域链接名称
   //============================================================
   public String getDomainConnectionName(){
      return _domainConnectionName;
   }

   //============================================================
   // <T>设置主域链接名称的内容。</T>
   //
   // @param value 主域链接名称
   //============================================================
   public void setDomainConnectionName(String value){
      _domainConnectionName = value;
   }

   //============================================================
   // <T>获得来源链接名称的内容。</T>
   //
   // @return 来源链接名称
   //============================================================
   public String getSourceConnectionName(){
      return _sourceConnectionName;
   }

   //============================================================
   // <T>设置来源链接名称的内容。</T>
   //
   // @param value 来源链接名称
   //============================================================
   public void setSourceConnectionName(String value){
      _sourceConnectionName = value;
   }

   //============================================================
   // <T>获得目标链接名称的内容。</T>
   //
   // @return 目标链接名称
   //============================================================
   public String getTargetConnectionName(){
      return _targetConnectionName;
   }

   //============================================================
   // <T>设置目标链接名称的内容。</T>
   //
   // @param value 目标链接名称
   //============================================================
   public void setTargetConnectionName(String value){
      _targetConnectionName = value;
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
      }else if(PTY_CODE.equalsIgnoreCase(name)){
         return getCode();
      }else if(PTY_NAME.equalsIgnoreCase(name)){
         return getName();
      }else if(PTY_LABEL.equalsIgnoreCase(name)){
         return getLabel();
      }else if(PTY_IS_VALID.equalsIgnoreCase(name)){
         return RBoolean.toString(getIsValid());
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         return getNote();
      }else if(PTY_DOMAIN_CONNECTION_NAME.equalsIgnoreCase(name)){
         return getDomainConnectionName();
      }else if(PTY_SOURCE_CONNECTION_NAME.equalsIgnoreCase(name)){
         return getSourceConnectionName();
      }else if(PTY_TARGET_CONNECTION_NAME.equalsIgnoreCase(name)){
         return getTargetConnectionName();
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
      }else if(PTY_CODE.equalsIgnoreCase(name)){
         setCode(value);
      }else if(PTY_NAME.equalsIgnoreCase(name)){
         setName(value);
      }else if(PTY_LABEL.equalsIgnoreCase(name)){
         setLabel(value);
      }else if(PTY_IS_VALID.equalsIgnoreCase(name)){
         setIsValid(RBoolean.parse(value));
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         setNote(value);
      }else if(PTY_DOMAIN_CONNECTION_NAME.equalsIgnoreCase(name)){
         setDomainConnectionName(value);
      }else if(PTY_SOURCE_CONNECTION_NAME.equalsIgnoreCase(name)){
         setSourceConnectionName(value);
      }else if(PTY_TARGET_CONNECTION_NAME.equalsIgnoreCase(name)){
         setTargetConnectionName(value);
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
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
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
         if(config.contains("domain_connection_name")){
            setDomainConnectionName(config.get(PTY_DOMAIN_CONNECTION_NAME));
         }
         if(config.contains("source_connection_name")){
            setSourceConnectionName(config.get(PTY_SOURCE_CONNECTION_NAME));
         }
         if(config.contains("target_connection_name")){
            setTargetConnectionName(config.get(PTY_TARGET_CONNECTION_NAME));
         }
      }else if(EXmlConfig.Simple == type){
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
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
         if(config.contains("domain_connection_name")){
            setDomainConnectionName(config.get(PTY_DOMAIN_CONNECTION_NAME));
         }
         if(config.contains("source_connection_name")){
            setSourceConnectionName(config.get(PTY_SOURCE_CONNECTION_NAME));
         }
         if(config.contains("target_connection_name")){
            setTargetConnectionName(config.get(PTY_TARGET_CONNECTION_NAME));
         }
      }else if(EXmlConfig.Value == type){
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
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
         if(config.contains("domain_connection_name")){
            setDomainConnectionName(config.get(PTY_DOMAIN_CONNECTION_NAME));
         }
         if(config.contains("source_connection_name")){
            setSourceConnectionName(config.get(PTY_SOURCE_CONNECTION_NAME));
         }
         if(config.contains("target_connection_name")){
            setTargetConnectionName(config.get(PTY_TARGET_CONNECTION_NAME));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getCode())){
            if(config.contains("code")){
               setCode(config.get(PTY_CODE));
            }
         }
         if(RString.isEmpty(getDomainConnectionName())){
            if(config.contains("domain_connection_name")){
               setDomainConnectionName(config.get(PTY_DOMAIN_CONNECTION_NAME));
            }
         }
         if(RString.isEmpty(getSourceConnectionName())){
            if(config.contains("source_connection_name")){
               setSourceConnectionName(config.get(PTY_SOURCE_CONNECTION_NAME));
            }
         }
         if(RString.isEmpty(getTargetConnectionName())){
            if(config.contains("target_connection_name")){
               setTargetConnectionName(config.get(PTY_TARGET_CONNECTION_NAME));
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
         if(RString.isNotEmpty(getCode())){
            config.set(PTY_CODE, getCode());
         }
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
         if(RString.isNotEmpty(getDomainConnectionName())){
            config.set(PTY_DOMAIN_CONNECTION_NAME, getDomainConnectionName());
         }
         if(RString.isNotEmpty(getSourceConnectionName())){
            config.set(PTY_SOURCE_CONNECTION_NAME, getSourceConnectionName());
         }
         if(RString.isNotEmpty(getTargetConnectionName())){
            config.set(PTY_TARGET_CONNECTION_NAME, getTargetConnectionName());
         }
      }else if(EXmlConfig.Simple == type){
         if(RString.isNotEmpty(getCode())){
            config.set(PTY_CODE, getCode());
         }
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
         if(RString.isNotEmpty(getDomainConnectionName())){
            config.set(PTY_DOMAIN_CONNECTION_NAME, getDomainConnectionName());
         }
         if(RString.isNotEmpty(getSourceConnectionName())){
            config.set(PTY_SOURCE_CONNECTION_NAME, getSourceConnectionName());
         }
         if(RString.isNotEmpty(getTargetConnectionName())){
            config.set(PTY_TARGET_CONNECTION_NAME, getTargetConnectionName());
         }
      }else if(EXmlConfig.Value == type){
         String sCode = getCode();
         if(RString.isNotEmpty(sCode)){
            config.set(PTY_CODE, sCode);
         }
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
         String sDomainConnectionName = getDomainConnectionName();
         if(RString.isNotEmpty(sDomainConnectionName)){
            config.set(PTY_DOMAIN_CONNECTION_NAME, sDomainConnectionName);
         }
         String sSourceConnectionName = getSourceConnectionName();
         if(RString.isNotEmpty(sSourceConnectionName)){
            config.set(PTY_SOURCE_CONNECTION_NAME, sSourceConnectionName);
         }
         String sTargetConnectionName = getTargetConnectionName();
         if(RString.isNotEmpty(sTargetConnectionName)){
            config.set(PTY_TARGET_CONNECTION_NAME, sTargetConnectionName);
         }
      }else if(EXmlConfig.Default == type){
         String sCode = getCode();
         if(RString.isNotEmpty(sCode)){
            config.set(PTY_CODE, sCode);
         }
         String sDomainConnectionName = getDomainConnectionName();
         if(RString.isNotEmpty(sDomainConnectionName)){
            config.set(PTY_DOMAIN_CONNECTION_NAME, sDomainConnectionName);
         }
         String sSourceConnectionName = getSourceConnectionName();
         if(RString.isNotEmpty(sSourceConnectionName)){
            config.set(PTY_SOURCE_CONNECTION_NAME, sSourceConnectionName);
         }
         String sTargetConnectionName = getTargetConnectionName();
         if(RString.isNotEmpty(sTargetConnectionName)){
            config.set(PTY_TARGET_CONNECTION_NAME, sTargetConnectionName);
         }
      }
   }
}