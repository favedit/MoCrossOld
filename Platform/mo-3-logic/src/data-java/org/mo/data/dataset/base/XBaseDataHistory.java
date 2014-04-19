package org.mo.data.dataset.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.data.dataset.common.XDatasetFace;

//============================================================
// <T>数据历史对象的XML节点基类。</T>
//============================================================
public abstract class XBaseDataHistory
      extends FXmlObject
      implements
         XDatasetFace
{
   // 名称定义
   public static final String NAME = "DataHistory";

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

   // 查询可的名称定义
   public static final String PTY_ACCESS_QUERY = "access_query";

   // 新建可的名称定义
   public static final String PTY_ACCESS_INSERT = "access_insert";

   // 更新可的名称定义
   public static final String PTY_ACCESS_UPDATE = "access_update";

   // 删除可的名称定义
   public static final String PTY_ACCESS_DELETE = "access_delete";

   // 数据名称的名称定义
   public static final String PTY_DATA_NAME = "data_name";

   // 数据别称的名称定义
   public static final String PTY_DATA_ALIAS = "data_alias";

   // 数据别称的名称定义
   public static final String PTY_DATA_LOGIC = "data_logic";

   // 逻辑接口的名称定义
   public static final String PTY_LOGIC_NAME = "logic_name";

   // 处理接口的名称定义
   public static final String PTY_LOGIC_FACE = "logic_face";

   // 单元类的名称定义
   public static final String PTY_LOGIC_ENTITY = "logic_entity";

   // 处理类的名称定义
   public static final String PTY_LOGIC_CLASS = "logic_class";

   // 新建逻辑的名称定义
   public static final String PTY_LOGIC_INSERT = "logic_insert";

   // 更新逻辑的名称定义
   public static final String PTY_LOGIC_UPDATE = "logic_update";

   // 删除逻辑的名称定义
   public static final String PTY_LOGIC_DELETE = "logic_delete";

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

   // 查询可的定义
   @AName("access_query")
   protected String _accessQuery;

   // 新建可的定义
   @AName("access_insert")
   protected Boolean _accessInsert = Boolean.FALSE;

   // 更新可的定义
   @AName("access_update")
   protected Boolean _accessUpdate = Boolean.FALSE;

   // 删除可的定义
   @AName("access_delete")
   protected Boolean _accessDelete = Boolean.FALSE;

   // 数据名称的定义
   @AName("data_name")
   protected String _dataName;

   // 数据别称的定义
   @AName("data_alias")
   protected String _dataAlias;

   // 数据别称的定义
   @AName("data_logic")
   protected String _dataLogic;

   // 逻辑接口的定义
   @AName("logic_name")
   protected String _logicName;

   // 处理接口的定义
   @AName("logic_face")
   protected String _logicFace;

   // 单元类的定义
   @AName("logic_entity")
   protected String _logicEntity;

   // 处理类的定义
   @AName("logic_class")
   protected String _logicClass;

   // 新建逻辑的定义
   @AName("logic_insert")
   protected String _logicInsert;

   // 更新逻辑的定义
   @AName("logic_update")
   protected String _logicUpdate;

   // 删除逻辑的定义
   @AName("logic_delete")
   protected String _logicDelete;

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
   // <T>获得查询可的内容。</T>
   //
   // @return 查询可
   //============================================================
   public String getAccessQuery(){
      return _accessQuery;
   }

   //============================================================
   // <T>设置查询可的内容。</T>
   //
   // @param value 查询可
   //============================================================
   public void setAccessQuery(String value){
      _accessQuery = value;
   }

   //============================================================
   // <T>获得新建可的内容。</T>
   //
   // @return 新建可
   //============================================================
   public Boolean getAccessInsert(){
      return _accessInsert;
   }

   //============================================================
   // <T>设置新建可的内容。</T>
   //
   // @param value 新建可
   //============================================================
   public void setAccessInsert(Boolean value){
      _accessInsert = value;
   }

   //============================================================
   // <T>获得更新可的内容。</T>
   //
   // @return 更新可
   //============================================================
   public Boolean getAccessUpdate(){
      return _accessUpdate;
   }

   //============================================================
   // <T>设置更新可的内容。</T>
   //
   // @param value 更新可
   //============================================================
   public void setAccessUpdate(Boolean value){
      _accessUpdate = value;
   }

   //============================================================
   // <T>获得删除可的内容。</T>
   //
   // @return 删除可
   //============================================================
   public Boolean getAccessDelete(){
      return _accessDelete;
   }

   //============================================================
   // <T>设置删除可的内容。</T>
   //
   // @param value 删除可
   //============================================================
   public void setAccessDelete(Boolean value){
      _accessDelete = value;
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
   // <T>获得数据别称的内容。</T>
   //
   // @return 数据别称
   //============================================================
   public String getDataAlias(){
      return _dataAlias;
   }

   //============================================================
   // <T>设置数据别称的内容。</T>
   //
   // @param value 数据别称
   //============================================================
   public void setDataAlias(String value){
      _dataAlias = value;
   }

   //============================================================
   // <T>获得数据别称的内容。</T>
   //
   // @return 数据别称
   //============================================================
   public String getDataLogic(){
      return _dataLogic;
   }

   //============================================================
   // <T>设置数据别称的内容。</T>
   //
   // @param value 数据别称
   //============================================================
   public void setDataLogic(String value){
      _dataLogic = value;
   }

   //============================================================
   // <T>获得逻辑接口的内容。</T>
   //
   // @return 逻辑接口
   //============================================================
   public String getLogicName(){
      return _logicName;
   }

   //============================================================
   // <T>设置逻辑接口的内容。</T>
   //
   // @param value 逻辑接口
   //============================================================
   public void setLogicName(String value){
      _logicName = value;
   }

   //============================================================
   // <T>获得处理接口的内容。</T>
   //
   // @return 处理接口
   //============================================================
   public String getLogicFace(){
      return _logicFace;
   }

   //============================================================
   // <T>设置处理接口的内容。</T>
   //
   // @param value 处理接口
   //============================================================
   public void setLogicFace(String value){
      _logicFace = value;
   }

   //============================================================
   // <T>获得单元类的内容。</T>
   //
   // @return 单元类
   //============================================================
   public String getLogicEntity(){
      return _logicEntity;
   }

   //============================================================
   // <T>设置单元类的内容。</T>
   //
   // @param value 单元类
   //============================================================
   public void setLogicEntity(String value){
      _logicEntity = value;
   }

   //============================================================
   // <T>获得处理类的内容。</T>
   //
   // @return 处理类
   //============================================================
   public String getLogicClass(){
      return _logicClass;
   }

   //============================================================
   // <T>设置处理类的内容。</T>
   //
   // @param value 处理类
   //============================================================
   public void setLogicClass(String value){
      _logicClass = value;
   }

   //============================================================
   // <T>获得新建逻辑的内容。</T>
   //
   // @return 新建逻辑
   //============================================================
   public String getLogicInsert(){
      return _logicInsert;
   }

   //============================================================
   // <T>设置新建逻辑的内容。</T>
   //
   // @param value 新建逻辑
   //============================================================
   public void setLogicInsert(String value){
      _logicInsert = value;
   }

   //============================================================
   // <T>获得更新逻辑的内容。</T>
   //
   // @return 更新逻辑
   //============================================================
   public String getLogicUpdate(){
      return _logicUpdate;
   }

   //============================================================
   // <T>设置更新逻辑的内容。</T>
   //
   // @param value 更新逻辑
   //============================================================
   public void setLogicUpdate(String value){
      _logicUpdate = value;
   }

   //============================================================
   // <T>获得删除逻辑的内容。</T>
   //
   // @return 删除逻辑
   //============================================================
   public String getLogicDelete(){
      return _logicDelete;
   }

   //============================================================
   // <T>设置删除逻辑的内容。</T>
   //
   // @param value 删除逻辑
   //============================================================
   public void setLogicDelete(String value){
      _logicDelete = value;
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
      }else if(PTY_ACCESS_QUERY.equalsIgnoreCase(name)){
         return getAccessQuery();
      }else if(PTY_ACCESS_INSERT.equalsIgnoreCase(name)){
         return RBoolean.toString(getAccessInsert());
      }else if(PTY_ACCESS_UPDATE.equalsIgnoreCase(name)){
         return RBoolean.toString(getAccessUpdate());
      }else if(PTY_ACCESS_DELETE.equalsIgnoreCase(name)){
         return RBoolean.toString(getAccessDelete());
      }else if(PTY_DATA_NAME.equalsIgnoreCase(name)){
         return getDataName();
      }else if(PTY_DATA_ALIAS.equalsIgnoreCase(name)){
         return getDataAlias();
      }else if(PTY_DATA_LOGIC.equalsIgnoreCase(name)){
         return getDataLogic();
      }else if(PTY_LOGIC_NAME.equalsIgnoreCase(name)){
         return getLogicName();
      }else if(PTY_LOGIC_FACE.equalsIgnoreCase(name)){
         return getLogicFace();
      }else if(PTY_LOGIC_ENTITY.equalsIgnoreCase(name)){
         return getLogicEntity();
      }else if(PTY_LOGIC_CLASS.equalsIgnoreCase(name)){
         return getLogicClass();
      }else if(PTY_LOGIC_INSERT.equalsIgnoreCase(name)){
         return getLogicInsert();
      }else if(PTY_LOGIC_UPDATE.equalsIgnoreCase(name)){
         return getLogicUpdate();
      }else if(PTY_LOGIC_DELETE.equalsIgnoreCase(name)){
         return getLogicDelete();
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
      }else if(PTY_ACCESS_QUERY.equalsIgnoreCase(name)){
         setAccessQuery(value);
      }else if(PTY_ACCESS_INSERT.equalsIgnoreCase(name)){
         setAccessInsert(RBoolean.parse(value));
      }else if(PTY_ACCESS_UPDATE.equalsIgnoreCase(name)){
         setAccessUpdate(RBoolean.parse(value));
      }else if(PTY_ACCESS_DELETE.equalsIgnoreCase(name)){
         setAccessDelete(RBoolean.parse(value));
      }else if(PTY_DATA_NAME.equalsIgnoreCase(name)){
         setDataName(value);
      }else if(PTY_DATA_ALIAS.equalsIgnoreCase(name)){
         setDataAlias(value);
      }else if(PTY_DATA_LOGIC.equalsIgnoreCase(name)){
         setDataLogic(value);
      }else if(PTY_LOGIC_NAME.equalsIgnoreCase(name)){
         setLogicName(value);
      }else if(PTY_LOGIC_FACE.equalsIgnoreCase(name)){
         setLogicFace(value);
      }else if(PTY_LOGIC_ENTITY.equalsIgnoreCase(name)){
         setLogicEntity(value);
      }else if(PTY_LOGIC_CLASS.equalsIgnoreCase(name)){
         setLogicClass(value);
      }else if(PTY_LOGIC_INSERT.equalsIgnoreCase(name)){
         setLogicInsert(value);
      }else if(PTY_LOGIC_UPDATE.equalsIgnoreCase(name)){
         setLogicUpdate(value);
      }else if(PTY_LOGIC_DELETE.equalsIgnoreCase(name)){
         setLogicDelete(value);
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
         if(config.contains("access_query")){
            setAccessQuery(config.get(PTY_ACCESS_QUERY));
         }
         if(config.contains("access_insert")){
            setAccessInsert(RBoolean.parse(config.get(PTY_ACCESS_INSERT)));
         }
         if(config.contains("access_update")){
            setAccessUpdate(RBoolean.parse(config.get(PTY_ACCESS_UPDATE)));
         }
         if(config.contains("access_delete")){
            setAccessDelete(RBoolean.parse(config.get(PTY_ACCESS_DELETE)));
         }
         if(config.contains("data_name")){
            setDataName(config.get(PTY_DATA_NAME));
         }
         if(config.contains("data_alias")){
            setDataAlias(config.get(PTY_DATA_ALIAS));
         }
         if(config.contains("data_logic")){
            setDataLogic(config.get(PTY_DATA_LOGIC));
         }
         if(config.contains("logic_name")){
            setLogicName(config.get(PTY_LOGIC_NAME));
         }
         if(config.contains("logic_face")){
            setLogicFace(config.get(PTY_LOGIC_FACE));
         }
         if(config.contains("logic_entity")){
            setLogicEntity(config.get(PTY_LOGIC_ENTITY));
         }
         if(config.contains("logic_class")){
            setLogicClass(config.get(PTY_LOGIC_CLASS));
         }
         if(config.contains("logic_insert")){
            setLogicInsert(config.get(PTY_LOGIC_INSERT));
         }
         if(config.contains("logic_update")){
            setLogicUpdate(config.get(PTY_LOGIC_UPDATE));
         }
         if(config.contains("logic_delete")){
            setLogicDelete(config.get(PTY_LOGIC_DELETE));
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
         if(config.contains("access_query")){
            setAccessQuery(config.get(PTY_ACCESS_QUERY));
         }
         if(config.contains("access_insert")){
            setAccessInsert(RBoolean.parse(config.get(PTY_ACCESS_INSERT)));
         }
         if(config.contains("access_update")){
            setAccessUpdate(RBoolean.parse(config.get(PTY_ACCESS_UPDATE)));
         }
         if(config.contains("access_delete")){
            setAccessDelete(RBoolean.parse(config.get(PTY_ACCESS_DELETE)));
         }
         if(config.contains("data_name")){
            setDataName(config.get(PTY_DATA_NAME));
         }
         if(config.contains("data_alias")){
            setDataAlias(config.get(PTY_DATA_ALIAS));
         }
         if(config.contains("data_logic")){
            setDataLogic(config.get(PTY_DATA_LOGIC));
         }
         if(config.contains("logic_name")){
            setLogicName(config.get(PTY_LOGIC_NAME));
         }
         if(config.contains("logic_face")){
            setLogicFace(config.get(PTY_LOGIC_FACE));
         }
         if(config.contains("logic_class")){
            setLogicClass(config.get(PTY_LOGIC_CLASS));
         }
         if(config.contains("logic_insert")){
            setLogicInsert(config.get(PTY_LOGIC_INSERT));
         }
         if(config.contains("logic_update")){
            setLogicUpdate(config.get(PTY_LOGIC_UPDATE));
         }
         if(config.contains("logic_delete")){
            setLogicDelete(config.get(PTY_LOGIC_DELETE));
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
         if(config.contains("access_query")){
            setAccessQuery(config.get(PTY_ACCESS_QUERY));
         }
         if(config.contains("access_insert")){
            setAccessInsert(RBoolean.parse(config.get(PTY_ACCESS_INSERT)));
         }
         if(config.contains("access_update")){
            setAccessUpdate(RBoolean.parse(config.get(PTY_ACCESS_UPDATE)));
         }
         if(config.contains("access_delete")){
            setAccessDelete(RBoolean.parse(config.get(PTY_ACCESS_DELETE)));
         }
         if(config.contains("data_name")){
            setDataName(config.get(PTY_DATA_NAME));
         }
         if(config.contains("data_alias")){
            setDataAlias(config.get(PTY_DATA_ALIAS));
         }
         if(config.contains("data_logic")){
            setDataLogic(config.get(PTY_DATA_LOGIC));
         }
         if(config.contains("logic_name")){
            setLogicName(config.get(PTY_LOGIC_NAME));
         }
         if(config.contains("logic_face")){
            setLogicFace(config.get(PTY_LOGIC_FACE));
         }
         if(config.contains("logic_entity")){
            setLogicEntity(config.get(PTY_LOGIC_ENTITY));
         }
         if(config.contains("logic_class")){
            setLogicClass(config.get(PTY_LOGIC_CLASS));
         }
         if(config.contains("logic_insert")){
            setLogicInsert(config.get(PTY_LOGIC_INSERT));
         }
         if(config.contains("logic_update")){
            setLogicUpdate(config.get(PTY_LOGIC_UPDATE));
         }
         if(config.contains("logic_delete")){
            setLogicDelete(config.get(PTY_LOGIC_DELETE));
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
         if(RString.isNotEmpty(getAccessQuery())){
            config.set(PTY_ACCESS_QUERY, getAccessQuery());
         }
         if(RBoolean.parse(getAccessInsert())){
            config.set(PTY_ACCESS_INSERT, RBoolean.toString(getAccessInsert()));
         }
         if(RBoolean.parse(getAccessUpdate())){
            config.set(PTY_ACCESS_UPDATE, RBoolean.toString(getAccessUpdate()));
         }
         if(RBoolean.parse(getAccessDelete())){
            config.set(PTY_ACCESS_DELETE, RBoolean.toString(getAccessDelete()));
         }
         if(RString.isNotEmpty(getDataName())){
            config.set(PTY_DATA_NAME, getDataName());
         }
         if(RString.isNotEmpty(getDataAlias())){
            config.set(PTY_DATA_ALIAS, getDataAlias());
         }
         if(RString.isNotEmpty(getDataLogic())){
            config.set(PTY_DATA_LOGIC, getDataLogic());
         }
         if(RString.isNotEmpty(getLogicName())){
            config.set(PTY_LOGIC_NAME, getLogicName());
         }
         if(RString.isNotEmpty(getLogicFace())){
            config.set(PTY_LOGIC_FACE, getLogicFace());
         }
         if(RString.isNotEmpty(getLogicEntity())){
            config.set(PTY_LOGIC_ENTITY, getLogicEntity());
         }
         if(RString.isNotEmpty(getLogicClass())){
            config.set(PTY_LOGIC_CLASS, getLogicClass());
         }
         if(RString.isNotEmpty(getLogicInsert())){
            config.set(PTY_LOGIC_INSERT, getLogicInsert());
         }
         if(RString.isNotEmpty(getLogicUpdate())){
            config.set(PTY_LOGIC_UPDATE, getLogicUpdate());
         }
         if(RString.isNotEmpty(getLogicDelete())){
            config.set(PTY_LOGIC_DELETE, getLogicDelete());
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
         if(RString.isNotEmpty(getAccessQuery())){
            config.set(PTY_ACCESS_QUERY, getAccessQuery());
         }
         if(RBoolean.parse(getAccessInsert())){
            config.set(PTY_ACCESS_INSERT, RBoolean.toString(getAccessInsert()));
         }
         if(RBoolean.parse(getAccessUpdate())){
            config.set(PTY_ACCESS_UPDATE, RBoolean.toString(getAccessUpdate()));
         }
         if(RBoolean.parse(getAccessDelete())){
            config.set(PTY_ACCESS_DELETE, RBoolean.toString(getAccessDelete()));
         }
         if(RString.isNotEmpty(getDataName())){
            config.set(PTY_DATA_NAME, getDataName());
         }
         if(RString.isNotEmpty(getDataAlias())){
            config.set(PTY_DATA_ALIAS, getDataAlias());
         }
         if(RString.isNotEmpty(getDataLogic())){
            config.set(PTY_DATA_LOGIC, getDataLogic());
         }
         if(RString.isNotEmpty(getLogicName())){
            config.set(PTY_LOGIC_NAME, getLogicName());
         }
         if(RString.isNotEmpty(getLogicFace())){
            config.set(PTY_LOGIC_FACE, getLogicFace());
         }
         if(RString.isNotEmpty(getLogicClass())){
            config.set(PTY_LOGIC_CLASS, getLogicClass());
         }
         if(RString.isNotEmpty(getLogicInsert())){
            config.set(PTY_LOGIC_INSERT, getLogicInsert());
         }
         if(RString.isNotEmpty(getLogicUpdate())){
            config.set(PTY_LOGIC_UPDATE, getLogicUpdate());
         }
         if(RString.isNotEmpty(getLogicDelete())){
            config.set(PTY_LOGIC_DELETE, getLogicDelete());
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
         String sAccessQuery = getAccessQuery();
         if(RString.isNotEmpty(sAccessQuery)){
            config.set(PTY_ACCESS_QUERY, sAccessQuery);
         }
         Boolean bAccessInsert = getAccessInsert();
         if(RBoolean.parse(bAccessInsert)){
            config.set(PTY_ACCESS_INSERT, RBoolean.toString(bAccessInsert));
         }
         Boolean bAccessUpdate = getAccessUpdate();
         if(RBoolean.parse(bAccessUpdate)){
            config.set(PTY_ACCESS_UPDATE, RBoolean.toString(bAccessUpdate));
         }
         Boolean bAccessDelete = getAccessDelete();
         if(RBoolean.parse(bAccessDelete)){
            config.set(PTY_ACCESS_DELETE, RBoolean.toString(bAccessDelete));
         }
         String sDataName = getDataName();
         if(RString.isNotEmpty(sDataName)){
            config.set(PTY_DATA_NAME, sDataName);
         }
         String sDataAlias = getDataAlias();
         if(RString.isNotEmpty(sDataAlias)){
            config.set(PTY_DATA_ALIAS, sDataAlias);
         }
         String sDataLogic = getDataLogic();
         if(RString.isNotEmpty(sDataLogic)){
            config.set(PTY_DATA_LOGIC, sDataLogic);
         }
         String sLogicName = getLogicName();
         if(RString.isNotEmpty(sLogicName)){
            config.set(PTY_LOGIC_NAME, sLogicName);
         }
         String sLogicFace = getLogicFace();
         if(RString.isNotEmpty(sLogicFace)){
            config.set(PTY_LOGIC_FACE, sLogicFace);
         }
         String sLogicEntity = getLogicEntity();
         if(RString.isNotEmpty(sLogicEntity)){
            config.set(PTY_LOGIC_ENTITY, sLogicEntity);
         }
         String sLogicClass = getLogicClass();
         if(RString.isNotEmpty(sLogicClass)){
            config.set(PTY_LOGIC_CLASS, sLogicClass);
         }
         String sLogicInsert = getLogicInsert();
         if(RString.isNotEmpty(sLogicInsert)){
            config.set(PTY_LOGIC_INSERT, sLogicInsert);
         }
         String sLogicUpdate = getLogicUpdate();
         if(RString.isNotEmpty(sLogicUpdate)){
            config.set(PTY_LOGIC_UPDATE, sLogicUpdate);
         }
         String sLogicDelete = getLogicDelete();
         if(RString.isNotEmpty(sLogicDelete)){
            config.set(PTY_LOGIC_DELETE, sLogicDelete);
         }
      }else if(EXmlConfig.Default == type){
      }
   }
}