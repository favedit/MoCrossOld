package org.mo.game.editor.core.dataset.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.game.editor.core.dataset.common.XFieldPropertyFace;

//============================================================
// <T>字符字段(属性)对象的XML节点基类。</T>
//============================================================
public abstract class XBaseFieldCharProperty
      extends FXmlObject
      implements
         XFieldPropertyFace
{
   // 名称定义
   public static final String NAME = "FieldCharProperty";

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

   // 主键的名称定义
   public static final String PTY_IS_KEY = "is_key";

   // 空值的名称定义
   public static final String PTY_IS_NULL = "is_null";

   // 唯一值的名称定义
   public static final String PTY_IS_UNIQUE = "is_unique";

   // 可存储的名称定义
   public static final String PTY_IS_STORE = "is_store";

   // 虚字段的名称定义
   public static final String PTY_IS_ABSTRACT = "is_abstract";

   // 可加密的名称定义
   public static final String PTY_IS_ENCRYPT = "is_encrypt";

   // 数据名称的名称定义
   public static final String PTY_DATA_NAME = "data_name";

   // 数据格式的名称定义
   public static final String PTY_DATA_FORMAT = "data_format";

   // 数据别称的名称定义
   public static final String PTY_DATA_ALIAS = "data_alias";

   // 数据类型的名称定义
   public static final String PTY_DATA_TYPE = "data_type";

   // 数据大小的名称定义
   public static final String PTY_DATA_SIZE = "data_size";

   // 数据引用的名称定义
   public static final String PTY_DATA_REFER = "data_refer";

   // 数据编号的名称定义
   public static final String PTY_DATA_CODE = "data_code";

   // 唯一名称集的名称定义
   public static final String PTY_DATA_UNIQUES = "data_uniques";

   // 分组类型的名称定义
   public static final String PTY_GROUP = "group";

   // 新建缺省的名称定义
   public static final String PTY_DATA_DEFAULT = "data_default";

   // 新建数据的名称定义
   public static final String PTY_DATA_INSERT = "data_insert";

   // 更新数据的名称定义
   public static final String PTY_DATA_UPDATE = "data_update";

   // 索引名称的名称定义
   public static final String PTY_INDEX_NAMES = "index_names";

   // 属性代码号的名称定义
   public static final String PTY_PROPERTY_ID = "property_id";

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

   // 主键的定义
   @AName("is_key")
   protected Boolean _isKey = Boolean.FALSE;

   // 空值的定义
   @AName("is_null")
   protected Boolean _isNull = Boolean.FALSE;

   // 唯一值的定义
   @AName("is_unique")
   protected Boolean _isUnique = Boolean.FALSE;

   // 可存储的定义
   @AName("is_store")
   protected String _isStore;

   // 虚字段的定义
   @AName("is_abstract")
   protected String _isAbstract;

   // 可加密的定义
   @AName("is_encrypt")
   protected String _isEncrypt;

   // 数据名称的定义
   @AName("data_name")
   protected String _dataName;

   // 数据格式的定义
   @AName("data_format")
   protected String _dataFormat;

   // 数据别称的定义
   @AName("data_alias")
   protected String _dataAlias;

   // 数据类型的定义
   @AName("data_type")
   protected String _dataType;

   // 数据大小的定义
   @AName("data_size")
   protected String _dataSize;

   // 数据引用的定义
   @AName("data_refer")
   protected String _dataRefer;

   // 数据编号的定义
   @AName("data_code")
   protected String _dataCode;

   // 唯一名称集的定义
   @AName("data_uniques")
   protected String _dataUniques;

   // 分组类型的定义
   @AName("group")
   protected String _group;

   // 新建缺省的定义
   @AName("data_default")
   protected String _dataDefault;

   // 新建数据的定义
   @AName("data_insert")
   protected String _dataInsert;

   // 更新数据的定义
   @AName("data_update")
   protected String _dataUpdate;

   // 索引名称的定义
   @AName("index_names")
   protected String _indexNames;

   // 属性代码号的定义
   @AName("property_id")
   protected String _propertyId;

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
   // <T>获得主键的内容。</T>
   //
   // @return 主键
   //============================================================
   public Boolean getIsKey(){
      return _isKey;
   }

   //============================================================
   // <T>设置主键的内容。</T>
   //
   // @param value 主键
   //============================================================
   public void setIsKey(Boolean value){
      _isKey = value;
   }

   //============================================================
   // <T>获得空值的内容。</T>
   //
   // @return 空值
   //============================================================
   public Boolean getIsNull(){
      return _isNull;
   }

   //============================================================
   // <T>设置空值的内容。</T>
   //
   // @param value 空值
   //============================================================
   public void setIsNull(Boolean value){
      _isNull = value;
   }

   //============================================================
   // <T>获得唯一值的内容。</T>
   //
   // @return 唯一值
   //============================================================
   public Boolean getIsUnique(){
      return _isUnique;
   }

   //============================================================
   // <T>设置唯一值的内容。</T>
   //
   // @param value 唯一值
   //============================================================
   public void setIsUnique(Boolean value){
      _isUnique = value;
   }

   //============================================================
   // <T>获得可存储的内容。</T>
   //
   // @return 可存储
   //============================================================
   public String getIsStore(){
      return _isStore;
   }

   //============================================================
   // <T>设置可存储的内容。</T>
   //
   // @param value 可存储
   //============================================================
   public void setIsStore(String value){
      _isStore = value;
   }

   //============================================================
   // <T>获得虚字段的内容。</T>
   //
   // @return 虚字段
   //============================================================
   public String getIsAbstract(){
      return _isAbstract;
   }

   //============================================================
   // <T>设置虚字段的内容。</T>
   //
   // @param value 虚字段
   //============================================================
   public void setIsAbstract(String value){
      _isAbstract = value;
   }

   //============================================================
   // <T>获得可加密的内容。</T>
   //
   // @return 可加密
   //============================================================
   public String getIsEncrypt(){
      return _isEncrypt;
   }

   //============================================================
   // <T>设置可加密的内容。</T>
   //
   // @param value 可加密
   //============================================================
   public void setIsEncrypt(String value){
      _isEncrypt = value;
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
   // <T>获得数据格式的内容。</T>
   //
   // @return 数据格式
   //============================================================
   public String getDataFormat(){
      return _dataFormat;
   }

   //============================================================
   // <T>设置数据格式的内容。</T>
   //
   // @param value 数据格式
   //============================================================
   public void setDataFormat(String value){
      _dataFormat = value;
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
   // <T>获得数据大小的内容。</T>
   //
   // @return 数据大小
   //============================================================
   public String getDataSize(){
      return _dataSize;
   }

   //============================================================
   // <T>设置数据大小的内容。</T>
   //
   // @param value 数据大小
   //============================================================
   public void setDataSize(String value){
      _dataSize = value;
   }

   //============================================================
   // <T>获得数据引用的内容。</T>
   //
   // @return 数据引用
   //============================================================
   public String getDataRefer(){
      return _dataRefer;
   }

   //============================================================
   // <T>设置数据引用的内容。</T>
   //
   // @param value 数据引用
   //============================================================
   public void setDataRefer(String value){
      _dataRefer = value;
   }

   //============================================================
   // <T>获得数据编号的内容。</T>
   //
   // @return 数据编号
   //============================================================
   public String getDataCode(){
      return _dataCode;
   }

   //============================================================
   // <T>设置数据编号的内容。</T>
   //
   // @param value 数据编号
   //============================================================
   public void setDataCode(String value){
      _dataCode = value;
   }

   //============================================================
   // <T>获得唯一名称集的内容。</T>
   //
   // @return 唯一名称集
   //============================================================
   public String getDataUniques(){
      return _dataUniques;
   }

   //============================================================
   // <T>设置唯一名称集的内容。</T>
   //
   // @param value 唯一名称集
   //============================================================
   public void setDataUniques(String value){
      _dataUniques = value;
   }

   //============================================================
   // <T>获得分组类型的内容。</T>
   //
   // @return 分组类型
   //============================================================
   public String getGroup(){
      return _group;
   }

   //============================================================
   // <T>设置分组类型的内容。</T>
   //
   // @param value 分组类型
   //============================================================
   public void setGroup(String value){
      _group = value;
   }

   //============================================================
   // <T>获得新建缺省的内容。</T>
   //
   // @return 新建缺省
   //============================================================
   public String getDataDefault(){
      return _dataDefault;
   }

   //============================================================
   // <T>设置新建缺省的内容。</T>
   //
   // @param value 新建缺省
   //============================================================
   public void setDataDefault(String value){
      _dataDefault = value;
   }

   //============================================================
   // <T>获得新建数据的内容。</T>
   //
   // @return 新建数据
   //============================================================
   public String getDataInsert(){
      return _dataInsert;
   }

   //============================================================
   // <T>设置新建数据的内容。</T>
   //
   // @param value 新建数据
   //============================================================
   public void setDataInsert(String value){
      _dataInsert = value;
   }

   //============================================================
   // <T>获得更新数据的内容。</T>
   //
   // @return 更新数据
   //============================================================
   public String getDataUpdate(){
      return _dataUpdate;
   }

   //============================================================
   // <T>设置更新数据的内容。</T>
   //
   // @param value 更新数据
   //============================================================
   public void setDataUpdate(String value){
      _dataUpdate = value;
   }

   //============================================================
   // <T>获得索引名称的内容。</T>
   //
   // @return 索引名称
   //============================================================
   public String getIndexNames(){
      return _indexNames;
   }

   //============================================================
   // <T>设置索引名称的内容。</T>
   //
   // @param value 索引名称
   //============================================================
   public void setIndexNames(String value){
      _indexNames = value;
   }

   //============================================================
   // <T>获得属性代码号的内容。</T>
   //
   // @return 属性代码号
   //============================================================
   public String getPropertyId(){
      return _propertyId;
   }

   //============================================================
   // <T>设置属性代码号的内容。</T>
   //
   // @param value 属性代码号
   //============================================================
   public void setPropertyId(String value){
      _propertyId = value;
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
      }else if(PTY_IS_KEY.equalsIgnoreCase(name)){
         return RBoolean.toString(getIsKey());
      }else if(PTY_IS_NULL.equalsIgnoreCase(name)){
         return RBoolean.toString(getIsNull());
      }else if(PTY_IS_UNIQUE.equalsIgnoreCase(name)){
         return RBoolean.toString(getIsUnique());
      }else if(PTY_IS_STORE.equalsIgnoreCase(name)){
         return getIsStore();
      }else if(PTY_IS_ABSTRACT.equalsIgnoreCase(name)){
         return getIsAbstract();
      }else if(PTY_IS_ENCRYPT.equalsIgnoreCase(name)){
         return getIsEncrypt();
      }else if(PTY_DATA_NAME.equalsIgnoreCase(name)){
         return getDataName();
      }else if(PTY_DATA_FORMAT.equalsIgnoreCase(name)){
         return getDataFormat();
      }else if(PTY_DATA_ALIAS.equalsIgnoreCase(name)){
         return getDataAlias();
      }else if(PTY_DATA_TYPE.equalsIgnoreCase(name)){
         return getDataType();
      }else if(PTY_DATA_SIZE.equalsIgnoreCase(name)){
         return getDataSize();
      }else if(PTY_DATA_REFER.equalsIgnoreCase(name)){
         return getDataRefer();
      }else if(PTY_DATA_CODE.equalsIgnoreCase(name)){
         return getDataCode();
      }else if(PTY_DATA_UNIQUES.equalsIgnoreCase(name)){
         return getDataUniques();
      }else if(PTY_GROUP.equalsIgnoreCase(name)){
         return getGroup();
      }else if(PTY_DATA_DEFAULT.equalsIgnoreCase(name)){
         return getDataDefault();
      }else if(PTY_DATA_INSERT.equalsIgnoreCase(name)){
         return getDataInsert();
      }else if(PTY_DATA_UPDATE.equalsIgnoreCase(name)){
         return getDataUpdate();
      }else if(PTY_INDEX_NAMES.equalsIgnoreCase(name)){
         return getIndexNames();
      }else if(PTY_PROPERTY_ID.equalsIgnoreCase(name)){
         return getPropertyId();
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
      }else if(PTY_IS_KEY.equalsIgnoreCase(name)){
         setIsKey(RBoolean.parse(value));
      }else if(PTY_IS_NULL.equalsIgnoreCase(name)){
         setIsNull(RBoolean.parse(value));
      }else if(PTY_IS_UNIQUE.equalsIgnoreCase(name)){
         setIsUnique(RBoolean.parse(value));
      }else if(PTY_IS_STORE.equalsIgnoreCase(name)){
         setIsStore(value);
      }else if(PTY_IS_ABSTRACT.equalsIgnoreCase(name)){
         setIsAbstract(value);
      }else if(PTY_IS_ENCRYPT.equalsIgnoreCase(name)){
         setIsEncrypt(value);
      }else if(PTY_DATA_NAME.equalsIgnoreCase(name)){
         setDataName(value);
      }else if(PTY_DATA_FORMAT.equalsIgnoreCase(name)){
         setDataFormat(value);
      }else if(PTY_DATA_ALIAS.equalsIgnoreCase(name)){
         setDataAlias(value);
      }else if(PTY_DATA_TYPE.equalsIgnoreCase(name)){
         setDataType(value);
      }else if(PTY_DATA_SIZE.equalsIgnoreCase(name)){
         setDataSize(value);
      }else if(PTY_DATA_REFER.equalsIgnoreCase(name)){
         setDataRefer(value);
      }else if(PTY_DATA_CODE.equalsIgnoreCase(name)){
         setDataCode(value);
      }else if(PTY_DATA_UNIQUES.equalsIgnoreCase(name)){
         setDataUniques(value);
      }else if(PTY_GROUP.equalsIgnoreCase(name)){
         setGroup(value);
      }else if(PTY_DATA_DEFAULT.equalsIgnoreCase(name)){
         setDataDefault(value);
      }else if(PTY_DATA_INSERT.equalsIgnoreCase(name)){
         setDataInsert(value);
      }else if(PTY_DATA_UPDATE.equalsIgnoreCase(name)){
         setDataUpdate(value);
      }else if(PTY_INDEX_NAMES.equalsIgnoreCase(name)){
         setIndexNames(value);
      }else if(PTY_PROPERTY_ID.equalsIgnoreCase(name)){
         setPropertyId(value);
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
         if(config.contains("is_key")){
            setIsKey(RBoolean.parse(config.get(PTY_IS_KEY)));
         }
         if(config.contains("is_null")){
            setIsNull(RBoolean.parse(config.get(PTY_IS_NULL)));
         }
         if(config.contains("is_unique")){
            setIsUnique(RBoolean.parse(config.get(PTY_IS_UNIQUE)));
         }
         if(config.contains("is_store")){
            setIsStore(config.get(PTY_IS_STORE));
         }
         if(config.contains("is_abstract")){
            setIsAbstract(config.get(PTY_IS_ABSTRACT));
         }
         if(config.contains("is_encrypt")){
            setIsEncrypt(config.get(PTY_IS_ENCRYPT));
         }
         if(config.contains("data_name")){
            setDataName(config.get(PTY_DATA_NAME));
         }
         if(config.contains("data_format")){
            setDataFormat(config.get(PTY_DATA_FORMAT));
         }
         if(config.contains("data_alias")){
            setDataAlias(config.get(PTY_DATA_ALIAS));
         }
         if(config.contains("data_type")){
            setDataType(config.get(PTY_DATA_TYPE));
         }
         if(config.contains("data_size")){
            setDataSize(config.get(PTY_DATA_SIZE));
         }
         if(config.contains("data_refer")){
            setDataRefer(config.get(PTY_DATA_REFER));
         }
         if(config.contains("data_code")){
            setDataCode(config.get(PTY_DATA_CODE));
         }
         if(config.contains("data_uniques")){
            setDataUniques(config.get(PTY_DATA_UNIQUES));
         }
         if(config.contains("group")){
            setGroup(config.get(PTY_GROUP));
         }
         if(config.contains("data_default")){
            setDataDefault(config.get(PTY_DATA_DEFAULT));
         }
         if(config.contains("data_insert")){
            setDataInsert(config.get(PTY_DATA_INSERT));
         }
         if(config.contains("data_update")){
            setDataUpdate(config.get(PTY_DATA_UPDATE));
         }
         if(config.contains("index_names")){
            setIndexNames(config.get(PTY_INDEX_NAMES));
         }
         if(config.contains("property_id")){
            setPropertyId(config.get(PTY_PROPERTY_ID));
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
         if(config.contains("is_key")){
            setIsKey(RBoolean.parse(config.get(PTY_IS_KEY)));
         }
         if(config.contains("is_null")){
            setIsNull(RBoolean.parse(config.get(PTY_IS_NULL)));
         }
         if(config.contains("is_unique")){
            setIsUnique(RBoolean.parse(config.get(PTY_IS_UNIQUE)));
         }
         if(config.contains("is_store")){
            setIsStore(config.get(PTY_IS_STORE));
         }
         if(config.contains("is_abstract")){
            setIsAbstract(config.get(PTY_IS_ABSTRACT));
         }
         if(config.contains("is_encrypt")){
            setIsEncrypt(config.get(PTY_IS_ENCRYPT));
         }
         if(config.contains("data_name")){
            setDataName(config.get(PTY_DATA_NAME));
         }
         if(config.contains("data_format")){
            setDataFormat(config.get(PTY_DATA_FORMAT));
         }
         if(config.contains("data_alias")){
            setDataAlias(config.get(PTY_DATA_ALIAS));
         }
         if(config.contains("data_type")){
            setDataType(config.get(PTY_DATA_TYPE));
         }
         if(config.contains("data_size")){
            setDataSize(config.get(PTY_DATA_SIZE));
         }
         if(config.contains("data_refer")){
            setDataRefer(config.get(PTY_DATA_REFER));
         }
         if(config.contains("data_code")){
            setDataCode(config.get(PTY_DATA_CODE));
         }
         if(config.contains("data_uniques")){
            setDataUniques(config.get(PTY_DATA_UNIQUES));
         }
         if(config.contains("group")){
            setGroup(config.get(PTY_GROUP));
         }
         if(config.contains("data_default")){
            setDataDefault(config.get(PTY_DATA_DEFAULT));
         }
         if(config.contains("data_insert")){
            setDataInsert(config.get(PTY_DATA_INSERT));
         }
         if(config.contains("data_update")){
            setDataUpdate(config.get(PTY_DATA_UPDATE));
         }
         if(config.contains("index_names")){
            setIndexNames(config.get(PTY_INDEX_NAMES));
         }
         if(config.contains("property_id")){
            setPropertyId(config.get(PTY_PROPERTY_ID));
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
         if(config.contains("is_key")){
            setIsKey(RBoolean.parse(config.get(PTY_IS_KEY)));
         }
         if(config.contains("is_null")){
            setIsNull(RBoolean.parse(config.get(PTY_IS_NULL)));
         }
         if(config.contains("is_unique")){
            setIsUnique(RBoolean.parse(config.get(PTY_IS_UNIQUE)));
         }
         if(config.contains("is_store")){
            setIsStore(config.get(PTY_IS_STORE));
         }
         if(config.contains("is_abstract")){
            setIsAbstract(config.get(PTY_IS_ABSTRACT));
         }
         if(config.contains("is_encrypt")){
            setIsEncrypt(config.get(PTY_IS_ENCRYPT));
         }
         if(config.contains("data_name")){
            setDataName(config.get(PTY_DATA_NAME));
         }
         if(config.contains("data_format")){
            setDataFormat(config.get(PTY_DATA_FORMAT));
         }
         if(config.contains("data_alias")){
            setDataAlias(config.get(PTY_DATA_ALIAS));
         }
         if(config.contains("data_type")){
            setDataType(config.get(PTY_DATA_TYPE));
         }
         if(config.contains("data_size")){
            setDataSize(config.get(PTY_DATA_SIZE));
         }
         if(config.contains("data_refer")){
            setDataRefer(config.get(PTY_DATA_REFER));
         }
         if(config.contains("data_code")){
            setDataCode(config.get(PTY_DATA_CODE));
         }
         if(config.contains("data_uniques")){
            setDataUniques(config.get(PTY_DATA_UNIQUES));
         }
         if(config.contains("group")){
            setGroup(config.get(PTY_GROUP));
         }
         if(config.contains("data_default")){
            setDataDefault(config.get(PTY_DATA_DEFAULT));
         }
         if(config.contains("data_insert")){
            setDataInsert(config.get(PTY_DATA_INSERT));
         }
         if(config.contains("data_update")){
            setDataUpdate(config.get(PTY_DATA_UPDATE));
         }
         if(config.contains("index_names")){
            setIndexNames(config.get(PTY_INDEX_NAMES));
         }
         if(config.contains("property_id")){
            setPropertyId(config.get(PTY_PROPERTY_ID));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getDataFormat())){
            if(config.contains("data_format")){
               setDataFormat(config.get(PTY_DATA_FORMAT));
            }
         }
         if(RString.isEmpty(getGroup())){
            if(config.contains("group")){
               setGroup(config.get(PTY_GROUP));
            }
         }
         if(RString.isEmpty(getDataInsert())){
            if(config.contains("data_insert")){
               setDataInsert(config.get(PTY_DATA_INSERT));
            }
         }
         if(RString.isEmpty(getIndexNames())){
            if(config.contains("index_names")){
               setIndexNames(config.get(PTY_INDEX_NAMES));
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
         if(RBoolean.parse(getIsKey())){
            config.set(PTY_IS_KEY, RBoolean.toString(getIsKey()));
         }
         if(RBoolean.parse(getIsNull())){
            config.set(PTY_IS_NULL, RBoolean.toString(getIsNull()));
         }
         if(RBoolean.parse(getIsUnique())){
            config.set(PTY_IS_UNIQUE, RBoolean.toString(getIsUnique()));
         }
         if(RString.isNotEmpty(getIsStore())){
            config.set(PTY_IS_STORE, getIsStore());
         }
         if(RString.isNotEmpty(getIsAbstract())){
            config.set(PTY_IS_ABSTRACT, getIsAbstract());
         }
         if(RString.isNotEmpty(getIsEncrypt())){
            config.set(PTY_IS_ENCRYPT, getIsEncrypt());
         }
         if(RString.isNotEmpty(getDataName())){
            config.set(PTY_DATA_NAME, getDataName());
         }
         if(RString.isNotEmpty(getDataFormat())){
            config.set(PTY_DATA_FORMAT, getDataFormat());
         }
         if(RString.isNotEmpty(getDataAlias())){
            config.set(PTY_DATA_ALIAS, getDataAlias());
         }
         if(RString.isNotEmpty(getDataType())){
            config.set(PTY_DATA_TYPE, getDataType());
         }
         if(RString.isNotEmpty(getDataSize())){
            config.set(PTY_DATA_SIZE, getDataSize());
         }
         if(RString.isNotEmpty(getDataRefer())){
            config.set(PTY_DATA_REFER, getDataRefer());
         }
         if(RString.isNotEmpty(getDataCode())){
            config.set(PTY_DATA_CODE, getDataCode());
         }
         if(RString.isNotEmpty(getDataUniques())){
            config.set(PTY_DATA_UNIQUES, getDataUniques());
         }
         if(RString.isNotEmpty(getGroup())){
            config.set(PTY_GROUP, getGroup());
         }
         if(RString.isNotEmpty(getDataDefault())){
            config.set(PTY_DATA_DEFAULT, getDataDefault());
         }
         if(RString.isNotEmpty(getDataInsert())){
            config.set(PTY_DATA_INSERT, getDataInsert());
         }
         if(RString.isNotEmpty(getDataUpdate())){
            config.set(PTY_DATA_UPDATE, getDataUpdate());
         }
         if(RString.isNotEmpty(getIndexNames())){
            config.set(PTY_INDEX_NAMES, getIndexNames());
         }
         if(RString.isNotEmpty(getPropertyId())){
            config.set(PTY_PROPERTY_ID, getPropertyId());
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
         if(RBoolean.parse(getIsKey())){
            config.set(PTY_IS_KEY, RBoolean.toString(getIsKey()));
         }
         if(RBoolean.parse(getIsNull())){
            config.set(PTY_IS_NULL, RBoolean.toString(getIsNull()));
         }
         if(RBoolean.parse(getIsUnique())){
            config.set(PTY_IS_UNIQUE, RBoolean.toString(getIsUnique()));
         }
         if(RString.isNotEmpty(getIsStore())){
            config.set(PTY_IS_STORE, getIsStore());
         }
         if(RString.isNotEmpty(getIsAbstract())){
            config.set(PTY_IS_ABSTRACT, getIsAbstract());
         }
         if(RString.isNotEmpty(getIsEncrypt())){
            config.set(PTY_IS_ENCRYPT, getIsEncrypt());
         }
         if(RString.isNotEmpty(getDataName())){
            config.set(PTY_DATA_NAME, getDataName());
         }
         if(RString.isNotEmpty(getDataFormat())){
            config.set(PTY_DATA_FORMAT, getDataFormat());
         }
         if(RString.isNotEmpty(getDataAlias())){
            config.set(PTY_DATA_ALIAS, getDataAlias());
         }
         if(RString.isNotEmpty(getDataType())){
            config.set(PTY_DATA_TYPE, getDataType());
         }
         if(RString.isNotEmpty(getDataSize())){
            config.set(PTY_DATA_SIZE, getDataSize());
         }
         if(RString.isNotEmpty(getDataRefer())){
            config.set(PTY_DATA_REFER, getDataRefer());
         }
         if(RString.isNotEmpty(getDataCode())){
            config.set(PTY_DATA_CODE, getDataCode());
         }
         if(RString.isNotEmpty(getDataUniques())){
            config.set(PTY_DATA_UNIQUES, getDataUniques());
         }
         if(RString.isNotEmpty(getGroup())){
            config.set(PTY_GROUP, getGroup());
         }
         if(RString.isNotEmpty(getDataDefault())){
            config.set(PTY_DATA_DEFAULT, getDataDefault());
         }
         if(RString.isNotEmpty(getDataInsert())){
            config.set(PTY_DATA_INSERT, getDataInsert());
         }
         if(RString.isNotEmpty(getDataUpdate())){
            config.set(PTY_DATA_UPDATE, getDataUpdate());
         }
         if(RString.isNotEmpty(getIndexNames())){
            config.set(PTY_INDEX_NAMES, getIndexNames());
         }
         if(RString.isNotEmpty(getPropertyId())){
            config.set(PTY_PROPERTY_ID, getPropertyId());
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
         Boolean bIsKey = getIsKey();
         if(RBoolean.parse(bIsKey)){
            config.set(PTY_IS_KEY, RBoolean.toString(bIsKey));
         }
         Boolean bIsNull = getIsNull();
         if(RBoolean.parse(bIsNull)){
            config.set(PTY_IS_NULL, RBoolean.toString(bIsNull));
         }
         Boolean bIsUnique = getIsUnique();
         if(RBoolean.parse(bIsUnique)){
            config.set(PTY_IS_UNIQUE, RBoolean.toString(bIsUnique));
         }
         String sIsStore = getIsStore();
         if(RString.isNotEmpty(sIsStore)){
            config.set(PTY_IS_STORE, sIsStore);
         }
         String sIsAbstract = getIsAbstract();
         if(RString.isNotEmpty(sIsAbstract)){
            config.set(PTY_IS_ABSTRACT, sIsAbstract);
         }
         String sIsEncrypt = getIsEncrypt();
         if(RString.isNotEmpty(sIsEncrypt)){
            config.set(PTY_IS_ENCRYPT, sIsEncrypt);
         }
         String sDataName = getDataName();
         if(RString.isNotEmpty(sDataName)){
            config.set(PTY_DATA_NAME, sDataName);
         }
         String sDataFormat = getDataFormat();
         if(RString.isNotEmpty(sDataFormat)){
            config.set(PTY_DATA_FORMAT, sDataFormat);
         }
         String sDataAlias = getDataAlias();
         if(RString.isNotEmpty(sDataAlias)){
            config.set(PTY_DATA_ALIAS, sDataAlias);
         }
         String sDataType = getDataType();
         if(RString.isNotEmpty(sDataType)){
            config.set(PTY_DATA_TYPE, sDataType);
         }
         String sDataSize = getDataSize();
         if(RString.isNotEmpty(sDataSize)){
            config.set(PTY_DATA_SIZE, sDataSize);
         }
         String sDataRefer = getDataRefer();
         if(RString.isNotEmpty(sDataRefer)){
            config.set(PTY_DATA_REFER, sDataRefer);
         }
         String sDataCode = getDataCode();
         if(RString.isNotEmpty(sDataCode)){
            config.set(PTY_DATA_CODE, sDataCode);
         }
         String sDataUniques = getDataUniques();
         if(RString.isNotEmpty(sDataUniques)){
            config.set(PTY_DATA_UNIQUES, sDataUniques);
         }
         String sGroup = getGroup();
         if(RString.isNotEmpty(sGroup)){
            config.set(PTY_GROUP, sGroup);
         }
         String sDataDefault = getDataDefault();
         if(RString.isNotEmpty(sDataDefault)){
            config.set(PTY_DATA_DEFAULT, sDataDefault);
         }
         String sDataInsert = getDataInsert();
         if(RString.isNotEmpty(sDataInsert)){
            config.set(PTY_DATA_INSERT, sDataInsert);
         }
         String sDataUpdate = getDataUpdate();
         if(RString.isNotEmpty(sDataUpdate)){
            config.set(PTY_DATA_UPDATE, sDataUpdate);
         }
         String sIndexNames = getIndexNames();
         if(RString.isNotEmpty(sIndexNames)){
            config.set(PTY_INDEX_NAMES, sIndexNames);
         }
         String sPropertyId = getPropertyId();
         if(RString.isNotEmpty(sPropertyId)){
            config.set(PTY_PROPERTY_ID, sPropertyId);
         }
      }else if(EXmlConfig.Default == type){
         String sDataFormat = getDataFormat();
         if(RString.isNotEmpty(sDataFormat)){
            config.set(PTY_DATA_FORMAT, sDataFormat);
         }
         String sGroup = getGroup();
         if(RString.isNotEmpty(sGroup)){
            config.set(PTY_GROUP, sGroup);
         }
         String sDataInsert = getDataInsert();
         if(RString.isNotEmpty(sDataInsert)){
            config.set(PTY_DATA_INSERT, sDataInsert);
         }
         String sIndexNames = getIndexNames();
         if(RString.isNotEmpty(sIndexNames)){
            config.set(PTY_INDEX_NAMES, sIndexNames);
         }
      }
   }
}