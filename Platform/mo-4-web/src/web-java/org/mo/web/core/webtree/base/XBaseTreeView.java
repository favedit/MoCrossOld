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

//============================================================
// <T>目录树对象的XML节点基类。</T>
//============================================================
public abstract class XBaseTreeView
      extends FXmlObject
      implements
         XObjectFace
{
   // 名称定义
   public static final String NAME = "TreeView";

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

   // 可配置的名称定义
   public static final String PTY_IS_CONFIG = "is_config";

   // 显示复选框的名称定义
   public static final String PTY_DISP_CHECKED = "disp_checked";

   // 服务名称的名称定义
   public static final String PTY_SERVICE = "service";

   // 父名称的名称定义
   public static final String PTY_PARENT_NAME = "parent_name";

   // 查询服务的名称定义
   public static final String PTY_QUERY_SERVICE = "query_service";

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

   // 可配置的定义
   @AName("is_config")
   protected String _isConfig;

   // 显示复选框的定义
   @AName("disp_checked")
   protected String _dispChecked;

   // 服务名称的定义
   @AName("service")
   protected String _service;

   // 父名称的定义
   @AName("parent_name")
   protected String _parentName;

   // 查询服务的定义
   @AName("query_service")
   protected String _queryService;

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
   // <T>获得显示复选框的内容。</T>
   //
   // @return 显示复选框
   //============================================================
   public String getDispChecked(){
      return _dispChecked;
   }

   //============================================================
   // <T>设置显示复选框的内容。</T>
   //
   // @param value 显示复选框
   //============================================================
   public void setDispChecked(String value){
      _dispChecked = value;
   }

   //============================================================
   // <T>获得服务名称的内容。</T>
   //
   // @return 服务名称
   //============================================================
   public String getService(){
      return _service;
   }

   //============================================================
   // <T>设置服务名称的内容。</T>
   //
   // @param value 服务名称
   //============================================================
   public void setService(String value){
      _service = value;
   }

   //============================================================
   // <T>获得父名称的内容。</T>
   //
   // @return 父名称
   //============================================================
   public String getParentName(){
      return _parentName;
   }

   //============================================================
   // <T>设置父名称的内容。</T>
   //
   // @param value 父名称
   //============================================================
   public void setParentName(String value){
      _parentName = value;
   }

   //============================================================
   // <T>获得查询服务的内容。</T>
   //
   // @return 查询服务
   //============================================================
   public String getQueryService(){
      return _queryService;
   }

   //============================================================
   // <T>设置查询服务的内容。</T>
   //
   // @param value 查询服务
   //============================================================
   public void setQueryService(String value){
      _queryService = value;
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
      }else if(PTY_IS_CONFIG.equalsIgnoreCase(name)){
         return getIsConfig();
      }else if(PTY_DISP_CHECKED.equalsIgnoreCase(name)){
         return getDispChecked();
      }else if(PTY_SERVICE.equalsIgnoreCase(name)){
         return getService();
      }else if(PTY_PARENT_NAME.equalsIgnoreCase(name)){
         return getParentName();
      }else if(PTY_QUERY_SERVICE.equalsIgnoreCase(name)){
         return getQueryService();
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
      }else if(PTY_IS_CONFIG.equalsIgnoreCase(name)){
         setIsConfig(value);
      }else if(PTY_DISP_CHECKED.equalsIgnoreCase(name)){
         setDispChecked(value);
      }else if(PTY_SERVICE.equalsIgnoreCase(name)){
         setService(value);
      }else if(PTY_PARENT_NAME.equalsIgnoreCase(name)){
         setParentName(value);
      }else if(PTY_QUERY_SERVICE.equalsIgnoreCase(name)){
         setQueryService(value);
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
         if(config.contains("is_config")){
            setIsConfig(config.get(PTY_IS_CONFIG));
         }
         if(config.contains("disp_checked")){
            setDispChecked(config.get(PTY_DISP_CHECKED));
         }
         if(config.contains("service")){
            setService(config.get(PTY_SERVICE));
         }
         if(config.contains("parent_name")){
            setParentName(config.get(PTY_PARENT_NAME));
         }
         if(config.contains("query_service")){
            setQueryService(config.get(PTY_QUERY_SERVICE));
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
         if(config.contains("is_config")){
            setIsConfig(config.get(PTY_IS_CONFIG));
         }
         if(config.contains("disp_checked")){
            setDispChecked(config.get(PTY_DISP_CHECKED));
         }
         if(config.contains("service")){
            setService(config.get(PTY_SERVICE));
         }
         if(config.contains("query_service")){
            setQueryService(config.get(PTY_QUERY_SERVICE));
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
         if(config.contains("is_config")){
            setIsConfig(config.get(PTY_IS_CONFIG));
         }
         if(config.contains("disp_checked")){
            setDispChecked(config.get(PTY_DISP_CHECKED));
         }
         if(config.contains("service")){
            setService(config.get(PTY_SERVICE));
         }
         if(config.contains("parent_name")){
            setParentName(config.get(PTY_PARENT_NAME));
         }
         if(config.contains("query_service")){
            setQueryService(config.get(PTY_QUERY_SERVICE));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getIsConfig())){
            if(config.contains("is_config")){
               setIsConfig(config.get(PTY_IS_CONFIG));
            }
         }
         if(RString.isEmpty(getDispChecked())){
            if(config.contains("disp_checked")){
               setDispChecked(config.get(PTY_DISP_CHECKED));
            }
         }
         if(RString.isEmpty(getQueryService())){
            if(config.contains("query_service")){
               setQueryService(config.get(PTY_QUERY_SERVICE));
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
         if(RString.isNotEmpty(getIsConfig())){
            config.set(PTY_IS_CONFIG, getIsConfig());
         }
         if(RString.isNotEmpty(getDispChecked())){
            config.set(PTY_DISP_CHECKED, getDispChecked());
         }
         if(RString.isNotEmpty(getService())){
            config.set(PTY_SERVICE, getService());
         }
         if(RString.isNotEmpty(getParentName())){
            config.set(PTY_PARENT_NAME, getParentName());
         }
         if(RString.isNotEmpty(getQueryService())){
            config.set(PTY_QUERY_SERVICE, getQueryService());
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
         if(RString.isNotEmpty(getIsConfig())){
            config.set(PTY_IS_CONFIG, getIsConfig());
         }
         if(RString.isNotEmpty(getDispChecked())){
            config.set(PTY_DISP_CHECKED, getDispChecked());
         }
         if(RString.isNotEmpty(getService())){
            config.set(PTY_SERVICE, getService());
         }
         if(RString.isNotEmpty(getQueryService())){
            config.set(PTY_QUERY_SERVICE, getQueryService());
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
         String sIsConfig = getIsConfig();
         if(RString.isNotEmpty(sIsConfig)){
            config.set(PTY_IS_CONFIG, sIsConfig);
         }
         String sDispChecked = getDispChecked();
         if(RString.isNotEmpty(sDispChecked)){
            config.set(PTY_DISP_CHECKED, sDispChecked);
         }
         String sService = getService();
         if(RString.isNotEmpty(sService)){
            config.set(PTY_SERVICE, sService);
         }
         String sParentName = getParentName();
         if(RString.isNotEmpty(sParentName)){
            config.set(PTY_PARENT_NAME, sParentName);
         }
         String sQueryService = getQueryService();
         if(RString.isNotEmpty(sQueryService)){
            config.set(PTY_QUERY_SERVICE, sQueryService);
         }
      }else if(EXmlConfig.Default == type){
         String sIsConfig = getIsConfig();
         if(RString.isNotEmpty(sIsConfig)){
            config.set(PTY_IS_CONFIG, sIsConfig);
         }
         String sDispChecked = getDispChecked();
         if(RString.isNotEmpty(sDispChecked)){
            config.set(PTY_DISP_CHECKED, sDispChecked);
         }
         String sQueryService = getQueryService();
         if(RString.isNotEmpty(sQueryService)){
            config.set(PTY_QUERY_SERVICE, sQueryService);
         }
      }
   }
}