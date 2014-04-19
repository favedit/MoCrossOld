package org.mo.logic.property.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.logic.property.common.XObjectFace;

//============================================================
// <T>属性类型对象的XML节点基类。</T>
//============================================================
public abstract class XBaseType
      extends FXmlObject
      implements
         XObjectFace
{
   // 名称定义
   public static final String NAME = "Type";

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
   public static final String PTY_OVLD = "ovld";

   // 注释的名称定义
   public static final String PTY_NOTE = "note";

   // 类型的名称定义
   public static final String PTY__TYPE = "_type";

   // 标识的名称定义
   public static final String PTY_OUID = "ouid";

   // 数据有效性的名称定义
   public static final String PTY_OBJECT_VALID = "object_valid";

   // 创建时间的名称定义
   public static final String PTY_CREATE_DATE = "create_date";

   // 名称的定义
   @AName("name")
   protected String _name;

   // 标签的定义
   @AName("label")
   protected FMultiString _label = new FMultiString();

   // 有效性的定义
   @AName("ovld")
   protected Boolean _ovld = Boolean.FALSE;

   // 注释的定义
   @AName("note")
   protected FMultiString _note = new FMultiString();

   // 类型的定义
   @AName("_type")
   protected String __type;

   // 标识的定义
   @AName("ouid")
   protected String _ouid;

   // 数据有效性的定义
   @AName("object_valid")
   protected String _objectValid;

   // 创建时间的定义
   @AName("create_date")
   protected String _createDate;

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
   public Boolean getOvld(){
      return _ovld;
   }

   //============================================================
   // <T>设置有效性的内容。</T>
   //
   // @param value 有效性
   //============================================================
   public void setOvld(Boolean value){
      _ovld = value;
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
   // <T>获得标识的内容。</T>
   //
   // @return 标识
   //============================================================
   public String getOuid(){
      return _ouid;
   }

   //============================================================
   // <T>设置标识的内容。</T>
   //
   // @param value 标识
   //============================================================
   public void setOuid(String value){
      _ouid = value;
   }

   //============================================================
   // <T>获得数据有效性的内容。</T>
   //
   // @return 数据有效性
   //============================================================
   public String getObjectValid(){
      return _objectValid;
   }

   //============================================================
   // <T>设置数据有效性的内容。</T>
   //
   // @param value 数据有效性
   //============================================================
   public void setObjectValid(String value){
      _objectValid = value;
   }

   //============================================================
   // <T>获得创建时间的内容。</T>
   //
   // @return 创建时间
   //============================================================
   public String getCreateDate(){
      return _createDate;
   }

   //============================================================
   // <T>设置创建时间的内容。</T>
   //
   // @param value 创建时间
   //============================================================
   public void setCreateDate(String value){
      _createDate = value;
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
      }else if(PTY_OVLD.equalsIgnoreCase(name)){
         return RBoolean.toString(getOvld());
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         return getNote();
      }else if(PTY__TYPE.equalsIgnoreCase(name)){
         return get_type();
      }else if(PTY_OUID.equalsIgnoreCase(name)){
         return getOuid();
      }else if(PTY_OBJECT_VALID.equalsIgnoreCase(name)){
         return getObjectValid();
      }else if(PTY_CREATE_DATE.equalsIgnoreCase(name)){
         return getCreateDate();
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
      }else if(PTY_OVLD.equalsIgnoreCase(name)){
         setOvld(RBoolean.parse(value));
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         setNote(value);
      }else if(PTY__TYPE.equalsIgnoreCase(name)){
         set_type(value);
      }else if(PTY_OUID.equalsIgnoreCase(name)){
         setOuid(value);
      }else if(PTY_OBJECT_VALID.equalsIgnoreCase(name)){
         setObjectValid(value);
      }else if(PTY_CREATE_DATE.equalsIgnoreCase(name)){
         setCreateDate(value);
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
         if(config.contains("ovld")){
            setOvld(RBoolean.parse(config.get(PTY_OVLD)));
         }
         if(config.contains("note")){
            _note.unpack(config.get(PTY_NOTE));
         }
         if(config.contains("_type")){
            set_type(config.get(PTY__TYPE));
         }
         if(config.contains("ouid")){
            setOuid(config.get(PTY_OUID));
         }
         if(config.contains("object_valid")){
            setObjectValid(config.get(PTY_OBJECT_VALID));
         }
         if(config.contains("create_date")){
            setCreateDate(config.get(PTY_CREATE_DATE));
         }
      }else if(EXmlConfig.Simple == type){
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("label")){
            setLabel(config.get(PTY_LABEL));
         }
         if(config.contains("ovld")){
            setOvld(RBoolean.parse(config.get(PTY_OVLD)));
         }
         if(config.contains("note")){
            setNote(config.get(PTY_NOTE));
         }
         if(config.contains("_type")){
            set_type(config.get(PTY__TYPE));
         }
         if(config.contains("ouid")){
            setOuid(config.get(PTY_OUID));
         }
         if(config.contains("object_valid")){
            setObjectValid(config.get(PTY_OBJECT_VALID));
         }
         if(config.contains("create_date")){
            setCreateDate(config.get(PTY_CREATE_DATE));
         }
      }else if(EXmlConfig.Value == type){
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("label")){
            setLabel(config.get(PTY_LABEL));
         }
         if(config.contains("ovld")){
            setOvld(RBoolean.parse(config.get(PTY_OVLD)));
         }
         if(config.contains("note")){
            setNote(config.get(PTY_NOTE));
         }
         if(config.contains("_type")){
            set_type(config.get(PTY__TYPE));
         }
         if(config.contains("ouid")){
            setOuid(config.get(PTY_OUID));
         }
         if(config.contains("object_valid")){
            setObjectValid(config.get(PTY_OBJECT_VALID));
         }
         if(config.contains("create_date")){
            setCreateDate(config.get(PTY_CREATE_DATE));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(get_type())){
            if(config.contains("_type")){
               set_type(config.get(PTY__TYPE));
            }
         }
         if(RString.isEmpty(getOuid())){
            if(config.contains("ouid")){
               setOuid(config.get(PTY_OUID));
            }
         }
         if(RString.isEmpty(getObjectValid())){
            if(config.contains("object_valid")){
               setObjectValid(config.get(PTY_OBJECT_VALID));
            }
         }
         if(RString.isEmpty(getCreateDate())){
            if(config.contains("create_date")){
               setCreateDate(config.get(PTY_CREATE_DATE));
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
         if(RBoolean.parse(getOvld())){
            config.set(PTY_OVLD, RBoolean.toString(getOvld()));
         }
         String note = _note.pack().toString();
         if(RString.isNotEmpty(note)){
            config.set(PTY_NOTE, note);
         }
         if(RString.isNotEmpty(get_type())){
            config.set(PTY__TYPE, get_type());
         }
         if(RString.isNotEmpty(getOuid())){
            config.set(PTY_OUID, getOuid());
         }
         if(RString.isNotEmpty(getObjectValid())){
            config.set(PTY_OBJECT_VALID, getObjectValid());
         }
         if(RString.isNotEmpty(getCreateDate())){
            config.set(PTY_CREATE_DATE, getCreateDate());
         }
      }else if(EXmlConfig.Simple == type){
         if(RString.isNotEmpty(getName())){
            config.set(PTY_NAME, getName());
         }
         if(RString.isNotEmpty(getLabel())){
            config.set(PTY_LABEL, getLabel());
         }
         if(RBoolean.parse(getOvld())){
            config.set(PTY_OVLD, RBoolean.toString(getOvld()));
         }
         if(RString.isNotEmpty(getNote())){
            config.set(PTY_NOTE, getNote());
         }
         if(RString.isNotEmpty(get_type())){
            config.set(PTY__TYPE, get_type());
         }
         if(RString.isNotEmpty(getOuid())){
            config.set(PTY_OUID, getOuid());
         }
         if(RString.isNotEmpty(getObjectValid())){
            config.set(PTY_OBJECT_VALID, getObjectValid());
         }
         if(RString.isNotEmpty(getCreateDate())){
            config.set(PTY_CREATE_DATE, getCreateDate());
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
         Boolean bOvld = getOvld();
         if(RBoolean.parse(bOvld)){
            config.set(PTY_OVLD, RBoolean.toString(bOvld));
         }
         String sNote = getNote();
         if(RString.isNotEmpty(sNote)){
            config.set(PTY_NOTE, sNote);
         }
         String s_type = get_type();
         if(RString.isNotEmpty(s_type)){
            config.set(PTY__TYPE, s_type);
         }
         String sOuid = getOuid();
         if(RString.isNotEmpty(sOuid)){
            config.set(PTY_OUID, sOuid);
         }
         String sObjectValid = getObjectValid();
         if(RString.isNotEmpty(sObjectValid)){
            config.set(PTY_OBJECT_VALID, sObjectValid);
         }
         String sCreateDate = getCreateDate();
         if(RString.isNotEmpty(sCreateDate)){
            config.set(PTY_CREATE_DATE, sCreateDate);
         }
      }else if(EXmlConfig.Default == type){
         String s_type = get_type();
         if(RString.isNotEmpty(s_type)){
            config.set(PTY__TYPE, s_type);
         }
         String sOuid = getOuid();
         if(RString.isNotEmpty(sOuid)){
            config.set(PTY_OUID, sOuid);
         }
         String sObjectValid = getObjectValid();
         if(RString.isNotEmpty(sObjectValid)){
            config.set(PTY_OBJECT_VALID, sObjectValid);
         }
         String sCreateDate = getCreateDate();
         if(RString.isNotEmpty(sCreateDate)){
            config.set(PTY_CREATE_DATE, sCreateDate);
         }
      }
   }
}