package org.mo.data.codelist.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.data.codelist.common.XObject;

//============================================================
// <T>代码对象的XML节点基类。</T>
//============================================================
public abstract class XBaseCode
      extends FXmlObject
      implements
         XObject
{
   // 名称定义
   public static final String NAME = "Code";

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

   // 注释的名称定义
   public static final String PTY_NOTE = "note";

   // 类型的名称定义
   public static final String PTY_TYPE = "type";

   // 数据名称的名称定义
   public static final String PTY_DATA_NAME = "data_name";

   // 数据类型的名称定义
   public static final String PTY_DATA_TYPE = "data_type";

   // 数据内容的名称定义
   public static final String PTY_DATA_VALUE = "data_value";

   // 显示顺序的名称定义
   public static final String PTY_DISP_ORDER = "disp_order";

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

   // 类型的定义
   @AName("type")
   protected String _type;

   // 数据名称的定义
   @AName("data_name")
   protected String _dataName;

   // 数据类型的定义
   @AName("data_type")
   protected String _dataType;

   // 数据内容的定义
   @AName("data_value")
   protected String _dataValue;

   // 显示顺序的定义
   @AName("disp_order")
   protected String _dispOrder;

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
   // <T>获得数据名称的内容。</T>
   //
   // @return 数据名称
   //============================================================
   public String getDataName(){
      return _dataName;
   }

   //============================================================
   // <T>设置数据名称的内容。</T>
   //
   // @param value 数据名称
   //============================================================
   public void setDataName(String value){
      _dataName = value;
   }

   //============================================================
   // <T>获得数据类型的内容。</T>
   //
   // @return 数据类型
   //============================================================
   public String getDataType(){
      return _dataType;
   }

   //============================================================
   // <T>设置数据类型的内容。</T>
   //
   // @param value 数据类型
   //============================================================
   public void setDataType(String value){
      _dataType = value;
   }

   //============================================================
   // <T>获得数据内容的内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String getDataValue(){
      return _dataValue;
   }

   //============================================================
   // <T>设置数据内容的内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDataValue(String value){
      _dataValue = value;
   }

   //============================================================
   // <T>获得显示顺序的内容。</T>
   //
   // @return 显示顺序
   //============================================================
   public String getDispOrder(){
      return _dispOrder;
   }

   //============================================================
   // <T>设置显示顺序的内容。</T>
   //
   // @param value 显示顺序
   //============================================================
   public void setDispOrder(String value){
      _dispOrder = value;
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
      }else if(PTY_TYPE.equalsIgnoreCase(name)){
         return getType();
      }else if(PTY_DATA_NAME.equalsIgnoreCase(name)){
         return getDataName();
      }else if(PTY_DATA_TYPE.equalsIgnoreCase(name)){
         return getDataType();
      }else if(PTY_DATA_VALUE.equalsIgnoreCase(name)){
         return getDataValue();
      }else if(PTY_DISP_ORDER.equalsIgnoreCase(name)){
         return getDispOrder();
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
      }else if(PTY_TYPE.equalsIgnoreCase(name)){
         setType(value);
      }else if(PTY_DATA_NAME.equalsIgnoreCase(name)){
         setDataName(value);
      }else if(PTY_DATA_TYPE.equalsIgnoreCase(name)){
         setDataType(value);
      }else if(PTY_DATA_VALUE.equalsIgnoreCase(name)){
         setDataValue(value);
      }else if(PTY_DISP_ORDER.equalsIgnoreCase(name)){
         setDispOrder(value);
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
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
         }
         if(config.contains("data_name")){
            setDataName(config.get(PTY_DATA_NAME));
         }
         if(config.contains("data_type")){
            setDataType(config.get(PTY_DATA_TYPE));
         }
         if(config.contains("data_value")){
            setDataValue(config.get(PTY_DATA_VALUE));
         }
         if(config.contains("disp_order")){
            setDispOrder(config.get(PTY_DISP_ORDER));
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
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
         }
         if(config.contains("data_name")){
            setDataName(config.get(PTY_DATA_NAME));
         }
         if(config.contains("data_type")){
            setDataType(config.get(PTY_DATA_TYPE));
         }
         if(config.contains("data_value")){
            setDataValue(config.get(PTY_DATA_VALUE));
         }
         if(config.contains("disp_order")){
            setDispOrder(config.get(PTY_DISP_ORDER));
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
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
         }
         if(config.contains("data_name")){
            setDataName(config.get(PTY_DATA_NAME));
         }
         if(config.contains("data_type")){
            setDataType(config.get(PTY_DATA_TYPE));
         }
         if(config.contains("data_value")){
            setDataValue(config.get(PTY_DATA_VALUE));
         }
         if(config.contains("disp_order")){
            setDispOrder(config.get(PTY_DISP_ORDER));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getDataName())){
            if(config.contains("data_name")){
               setDataName(config.get(PTY_DATA_NAME));
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
         if(RString.isNotEmpty(getType())){
            config.set(PTY_TYPE, getType());
         }
         if(RString.isNotEmpty(getDataName())){
            config.set(PTY_DATA_NAME, getDataName());
         }
         if(RString.isNotEmpty(getDataType())){
            config.set(PTY_DATA_TYPE, getDataType());
         }
         if(RString.isNotEmpty(getDataValue())){
            config.set(PTY_DATA_VALUE, getDataValue());
         }
         if(RString.isNotEmpty(getDispOrder())){
            config.set(PTY_DISP_ORDER, getDispOrder());
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
         if(RString.isNotEmpty(getType())){
            config.set(PTY_TYPE, getType());
         }
         if(RString.isNotEmpty(getDataName())){
            config.set(PTY_DATA_NAME, getDataName());
         }
         if(RString.isNotEmpty(getDataType())){
            config.set(PTY_DATA_TYPE, getDataType());
         }
         if(RString.isNotEmpty(getDataValue())){
            config.set(PTY_DATA_VALUE, getDataValue());
         }
         if(RString.isNotEmpty(getDispOrder())){
            config.set(PTY_DISP_ORDER, getDispOrder());
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
         String sType = getType();
         if(RString.isNotEmpty(sType)){
            config.set(PTY_TYPE, sType);
         }
         String sDataName = getDataName();
         if(RString.isNotEmpty(sDataName)){
            config.set(PTY_DATA_NAME, sDataName);
         }
         String sDataType = getDataType();
         if(RString.isNotEmpty(sDataType)){
            config.set(PTY_DATA_TYPE, sDataType);
         }
         String sDataValue = getDataValue();
         if(RString.isNotEmpty(sDataValue)){
            config.set(PTY_DATA_VALUE, sDataValue);
         }
         String sDispOrder = getDispOrder();
         if(RString.isNotEmpty(sDispOrder)){
            config.set(PTY_DISP_ORDER, sDispOrder);
         }
      }else if(EXmlConfig.Default == type){
         String sDataName = getDataName();
         if(RString.isNotEmpty(sDataName)){
            config.set(PTY_DATA_NAME, sDataName);
         }
      }
   }
}