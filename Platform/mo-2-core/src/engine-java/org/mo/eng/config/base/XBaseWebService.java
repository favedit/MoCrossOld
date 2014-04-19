package org.mo.eng.config.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.eng.config.common.XObjectFace;
import org.mo.eng.config.common.XComponentFace;

//============================================================
// <T>WEB服务对象的XML节点基类。</T>
//============================================================
public abstract class XBaseWebService
      extends FXmlObject
      implements
         XObjectFace,
         XComponentFace
{
   // 名称定义
   public static final String NAME = "WebService";

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

   // 接口名称的名称定义
   public static final String PTY_FACE = "face";

   // 实体名称的名称定义
   public static final String PTY_INSTANCE = "instance";

   // 有效范围的名称定义
   public static final String PTY_SCOPE = "scope";

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

   // 接口名称的定义
   @AName("face")
   protected String _face;

   // 实体名称的定义
   @AName("instance")
   protected String _instance;

   // 有效范围的定义
   @AName("scope")
   protected String _scope;

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
   // <T>获得接口名称的内容。</T>
   //
   // @return 接口名称
   //============================================================
   public String getFace(){
      return _face;
   }

   //============================================================
   // <T>设置接口名称的内容。</T>
   //
   // @param value 接口名称
   //============================================================
   public void setFace(String value){
      _face = value;
   }

   //============================================================
   // <T>获得实体名称的内容。</T>
   //
   // @return 实体名称
   //============================================================
   public String getInstance(){
      return _instance;
   }

   //============================================================
   // <T>设置实体名称的内容。</T>
   //
   // @param value 实体名称
   //============================================================
   public void setInstance(String value){
      _instance = value;
   }

   //============================================================
   // <T>获得有效范围的内容。</T>
   //
   // @return 有效范围
   //============================================================
   public String getScope(){
      return _scope;
   }

   //============================================================
   // <T>设置有效范围的内容。</T>
   //
   // @param value 有效范围
   //============================================================
   public void setScope(String value){
      _scope = value;
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
      }else if(PTY_FACE.equalsIgnoreCase(name)){
         return getFace();
      }else if(PTY_INSTANCE.equalsIgnoreCase(name)){
         return getInstance();
      }else if(PTY_SCOPE.equalsIgnoreCase(name)){
         return getScope();
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
      }else if(PTY_FACE.equalsIgnoreCase(name)){
         setFace(value);
      }else if(PTY_INSTANCE.equalsIgnoreCase(name)){
         setInstance(value);
      }else if(PTY_SCOPE.equalsIgnoreCase(name)){
         setScope(value);
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
         if(config.contains("face")){
            setFace(config.get(PTY_FACE));
         }
         if(config.contains("instance")){
            setInstance(config.get(PTY_INSTANCE));
         }
         if(config.contains("scope")){
            setScope(config.get(PTY_SCOPE));
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
         if(config.contains("face")){
            setFace(config.get(PTY_FACE));
         }
         if(config.contains("instance")){
            setInstance(config.get(PTY_INSTANCE));
         }
         if(config.contains("scope")){
            setScope(config.get(PTY_SCOPE));
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
         if(config.contains("face")){
            setFace(config.get(PTY_FACE));
         }
         if(config.contains("instance")){
            setInstance(config.get(PTY_INSTANCE));
         }
         if(config.contains("scope")){
            setScope(config.get(PTY_SCOPE));
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
         if(RString.isNotEmpty(getFace())){
            config.set(PTY_FACE, getFace());
         }
         if(RString.isNotEmpty(getInstance())){
            config.set(PTY_INSTANCE, getInstance());
         }
         if(RString.isNotEmpty(getScope())){
            config.set(PTY_SCOPE, getScope());
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
         if(RString.isNotEmpty(getFace())){
            config.set(PTY_FACE, getFace());
         }
         if(RString.isNotEmpty(getInstance())){
            config.set(PTY_INSTANCE, getInstance());
         }
         if(RString.isNotEmpty(getScope())){
            config.set(PTY_SCOPE, getScope());
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
         String sFace = getFace();
         if(RString.isNotEmpty(sFace)){
            config.set(PTY_FACE, sFace);
         }
         String sInstance = getInstance();
         if(RString.isNotEmpty(sInstance)){
            config.set(PTY_INSTANCE, sInstance);
         }
         String sScope = getScope();
         if(RString.isNotEmpty(sScope)){
            config.set(PTY_SCOPE, sScope);
         }
      }else if(EXmlConfig.Default == type){
      }
   }
}