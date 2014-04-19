package org.mo.eng.transfer.base;

import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.eng.transfer.common.XObjectFace;

//============================================================
// <T>导出数据对象的XML节点基类。</T>
//============================================================
public abstract class XBaseExport
      extends FXmlObject
      implements
         XObjectFace
{
   // 名称定义
   public static final String NAME = "Export";

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

   // 描述信息的名称定义
   public static final String PTY_NOTE = "note";

   // SQL文的名称定义
   public static final String PTY_SQL = "sql";

   // 显示命令的名称定义
   public static final String PTY_DISPLAY_COMMAND = "display_command";

   // 命令标签的名称定义
   public static final String PTY_DISPLAY_COMMAND_TAG = "display_command_tag";

   // 显示字段名的名称定义
   public static final String PTY_DISPLAY_HEAD = "display_head";

   // 头部标签的名称定义
   public static final String PTY_DISPLAY_HEAD_TAG = "display_head_tag";

   // 显示名称的名称定义
   public static final String PTY_DISPLAY_LABEL = "display_label";

   // 名称标签的名称定义
   public static final String PTY_DISPLAY_LABEL_TAG = "display_label_tag";

   // 显示校验的名称定义
   public static final String PTY_DISPLAY_VALID = "display_valid";

   // 校验标签的名称定义
   public static final String PTY_DISPLAY_VALID_TAG = "display_valid_tag";

   // 是否打开的名称定义
   public static final String PTY_IS_OPEN = "is_open";

   // 导入文件名称的名称定义
   public static final String PTY_EXPORT_NAME = "export_name";

   // 导出格式的名称定义
   public static final String PTY_EXPORT_CHARSET = "export_charset";

   // 数据开始标签的名称定义
   public static final String PTY_DISP_DATA_START_TAG = "disp_data_start_tag";

   // 数据结束标签的名称定义
   public static final String PTY_DISP_DATA_END_TAG = "disp_data_end_tag";

   // 数据参数的名称定义
   public static final String PTY_DATASET_PARAMETERS = "dataset_parameters";

   // 名称的定义
   @AName("name")
   protected String _name;

   // 标签的定义
   @AName("label")
   protected String _label;

   // 有效性的定义
   @AName("is_valid")
   protected Boolean _isValid = Boolean.FALSE;

   // 描述信息的定义
   @AName("note")
   protected String _note;

   // SQL文的定义
   @AName("sql")
   protected String _sql;

   // 显示命令的定义
   @AName("display_command")
   protected String _displayCommand;

   // 命令标签的定义
   @AName("display_command_tag")
   protected String _displayCommandTag;

   // 显示字段名的定义
   @AName("display_head")
   protected String _displayHead;

   // 头部标签的定义
   @AName("display_head_tag")
   protected String _displayHeadTag;

   // 显示名称的定义
   @AName("display_label")
   protected String _displayLabel;

   // 名称标签的定义
   @AName("display_label_tag")
   protected String _displayLabelTag;

   // 显示校验的定义
   @AName("display_valid")
   protected String _displayValid;

   // 校验标签的定义
   @AName("display_valid_tag")
   protected String _displayValidTag;

   // 是否打开的定义
   @AName("is_open")
   protected String _isOpen;

   // 导入文件名称的定义
   @AName("export_name")
   protected String _exportName;

   // 导出格式的定义
   @AName("export_charset")
   protected String _exportCharset;

   // 数据开始标签的定义
   @AName("disp_data_start_tag")
   protected String _dispDataStartTag;

   // 数据结束标签的定义
   @AName("disp_data_end_tag")
   protected String _dispDataEndTag;

   // 数据参数的定义
   @AName("dataset_parameters")
   protected String _datasetParameters;

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
      return _label;
   }

   //============================================================
   // <T>设置标签的内容。</T>
   //
   // @param value 标签
   //============================================================
   public void setLabel(String value){
      _label = value;
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
      return _note;
   }

   //============================================================
   // <T>设置描述信息的内容。</T>
   //
   // @param value 描述信息
   //============================================================
   public void setNote(String value){
      _note = value;
   }

   //============================================================
   // <T>获得SQL文的内容。</T>
   //
   // @return SQL文
   //============================================================
   public String getSql(){
      return _sql;
   }

   //============================================================
   // <T>设置SQL文的内容。</T>
   //
   // @param value SQL文
   //============================================================
   public void setSql(String value){
      _sql = value;
   }

   //============================================================
   // <T>获得显示命令的内容。</T>
   //
   // @return 显示命令
   //============================================================
   public String getDisplayCommand(){
      return _displayCommand;
   }

   //============================================================
   // <T>设置显示命令的内容。</T>
   //
   // @param value 显示命令
   //============================================================
   public void setDisplayCommand(String value){
      _displayCommand = value;
   }

   //============================================================
   // <T>获得命令标签的内容。</T>
   //
   // @return 命令标签
   //============================================================
   public String getDisplayCommandTag(){
      return _displayCommandTag;
   }

   //============================================================
   // <T>设置命令标签的内容。</T>
   //
   // @param value 命令标签
   //============================================================
   public void setDisplayCommandTag(String value){
      _displayCommandTag = value;
   }

   //============================================================
   // <T>获得显示字段名的内容。</T>
   //
   // @return 显示字段名
   //============================================================
   public String getDisplayHead(){
      return _displayHead;
   }

   //============================================================
   // <T>设置显示字段名的内容。</T>
   //
   // @param value 显示字段名
   //============================================================
   public void setDisplayHead(String value){
      _displayHead = value;
   }

   //============================================================
   // <T>获得头部标签的内容。</T>
   //
   // @return 头部标签
   //============================================================
   public String getDisplayHeadTag(){
      return _displayHeadTag;
   }

   //============================================================
   // <T>设置头部标签的内容。</T>
   //
   // @param value 头部标签
   //============================================================
   public void setDisplayHeadTag(String value){
      _displayHeadTag = value;
   }

   //============================================================
   // <T>获得显示名称的内容。</T>
   //
   // @return 显示名称
   //============================================================
   public String getDisplayLabel(){
      return _displayLabel;
   }

   //============================================================
   // <T>设置显示名称的内容。</T>
   //
   // @param value 显示名称
   //============================================================
   public void setDisplayLabel(String value){
      _displayLabel = value;
   }

   //============================================================
   // <T>获得名称标签的内容。</T>
   //
   // @return 名称标签
   //============================================================
   public String getDisplayLabelTag(){
      return _displayLabelTag;
   }

   //============================================================
   // <T>设置名称标签的内容。</T>
   //
   // @param value 名称标签
   //============================================================
   public void setDisplayLabelTag(String value){
      _displayLabelTag = value;
   }

   //============================================================
   // <T>获得显示校验的内容。</T>
   //
   // @return 显示校验
   //============================================================
   public String getDisplayValid(){
      return _displayValid;
   }

   //============================================================
   // <T>设置显示校验的内容。</T>
   //
   // @param value 显示校验
   //============================================================
   public void setDisplayValid(String value){
      _displayValid = value;
   }

   //============================================================
   // <T>获得校验标签的内容。</T>
   //
   // @return 校验标签
   //============================================================
   public String getDisplayValidTag(){
      return _displayValidTag;
   }

   //============================================================
   // <T>设置校验标签的内容。</T>
   //
   // @param value 校验标签
   //============================================================
   public void setDisplayValidTag(String value){
      _displayValidTag = value;
   }

   //============================================================
   // <T>获得是否打开的内容。</T>
   //
   // @return 是否打开
   //============================================================
   public String getIsOpen(){
      return _isOpen;
   }

   //============================================================
   // <T>设置是否打开的内容。</T>
   //
   // @param value 是否打开
   //============================================================
   public void setIsOpen(String value){
      _isOpen = value;
   }

   //============================================================
   // <T>获得导入文件名称的内容。</T>
   //
   // @return 导入文件名称
   //============================================================
   public String getExportName(){
      return _exportName;
   }

   //============================================================
   // <T>设置导入文件名称的内容。</T>
   //
   // @param value 导入文件名称
   //============================================================
   public void setExportName(String value){
      _exportName = value;
   }

   //============================================================
   // <T>获得导出格式的内容。</T>
   //
   // @return 导出格式
   //============================================================
   public String getExportCharset(){
      return _exportCharset;
   }

   //============================================================
   // <T>设置导出格式的内容。</T>
   //
   // @param value 导出格式
   //============================================================
   public void setExportCharset(String value){
      _exportCharset = value;
   }

   //============================================================
   // <T>获得数据开始标签的内容。</T>
   //
   // @return 数据开始标签
   //============================================================
   public String getDispDataStartTag(){
      return _dispDataStartTag;
   }

   //============================================================
   // <T>设置数据开始标签的内容。</T>
   //
   // @param value 数据开始标签
   //============================================================
   public void setDispDataStartTag(String value){
      _dispDataStartTag = value;
   }

   //============================================================
   // <T>获得数据结束标签的内容。</T>
   //
   // @return 数据结束标签
   //============================================================
   public String getDispDataEndTag(){
      return _dispDataEndTag;
   }

   //============================================================
   // <T>设置数据结束标签的内容。</T>
   //
   // @param value 数据结束标签
   //============================================================
   public void setDispDataEndTag(String value){
      _dispDataEndTag = value;
   }

   //============================================================
   // <T>获得数据参数的内容。</T>
   //
   // @return 数据参数
   //============================================================
   public String getDatasetParameters(){
      return _datasetParameters;
   }

   //============================================================
   // <T>设置数据参数的内容。</T>
   //
   // @param value 数据参数
   //============================================================
   public void setDatasetParameters(String value){
      _datasetParameters = value;
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
      }else if(PTY_SQL.equalsIgnoreCase(name)){
         return getSql();
      }else if(PTY_DISPLAY_COMMAND.equalsIgnoreCase(name)){
         return getDisplayCommand();
      }else if(PTY_DISPLAY_COMMAND_TAG.equalsIgnoreCase(name)){
         return getDisplayCommandTag();
      }else if(PTY_DISPLAY_HEAD.equalsIgnoreCase(name)){
         return getDisplayHead();
      }else if(PTY_DISPLAY_HEAD_TAG.equalsIgnoreCase(name)){
         return getDisplayHeadTag();
      }else if(PTY_DISPLAY_LABEL.equalsIgnoreCase(name)){
         return getDisplayLabel();
      }else if(PTY_DISPLAY_LABEL_TAG.equalsIgnoreCase(name)){
         return getDisplayLabelTag();
      }else if(PTY_DISPLAY_VALID.equalsIgnoreCase(name)){
         return getDisplayValid();
      }else if(PTY_DISPLAY_VALID_TAG.equalsIgnoreCase(name)){
         return getDisplayValidTag();
      }else if(PTY_IS_OPEN.equalsIgnoreCase(name)){
         return getIsOpen();
      }else if(PTY_EXPORT_NAME.equalsIgnoreCase(name)){
         return getExportName();
      }else if(PTY_EXPORT_CHARSET.equalsIgnoreCase(name)){
         return getExportCharset();
      }else if(PTY_DISP_DATA_START_TAG.equalsIgnoreCase(name)){
         return getDispDataStartTag();
      }else if(PTY_DISP_DATA_END_TAG.equalsIgnoreCase(name)){
         return getDispDataEndTag();
      }else if(PTY_DATASET_PARAMETERS.equalsIgnoreCase(name)){
         return getDatasetParameters();
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
      }else if(PTY_SQL.equalsIgnoreCase(name)){
         setSql(value);
      }else if(PTY_DISPLAY_COMMAND.equalsIgnoreCase(name)){
         setDisplayCommand(value);
      }else if(PTY_DISPLAY_COMMAND_TAG.equalsIgnoreCase(name)){
         setDisplayCommandTag(value);
      }else if(PTY_DISPLAY_HEAD.equalsIgnoreCase(name)){
         setDisplayHead(value);
      }else if(PTY_DISPLAY_HEAD_TAG.equalsIgnoreCase(name)){
         setDisplayHeadTag(value);
      }else if(PTY_DISPLAY_LABEL.equalsIgnoreCase(name)){
         setDisplayLabel(value);
      }else if(PTY_DISPLAY_LABEL_TAG.equalsIgnoreCase(name)){
         setDisplayLabelTag(value);
      }else if(PTY_DISPLAY_VALID.equalsIgnoreCase(name)){
         setDisplayValid(value);
      }else if(PTY_DISPLAY_VALID_TAG.equalsIgnoreCase(name)){
         setDisplayValidTag(value);
      }else if(PTY_IS_OPEN.equalsIgnoreCase(name)){
         setIsOpen(value);
      }else if(PTY_EXPORT_NAME.equalsIgnoreCase(name)){
         setExportName(value);
      }else if(PTY_EXPORT_CHARSET.equalsIgnoreCase(name)){
         setExportCharset(value);
      }else if(PTY_DISP_DATA_START_TAG.equalsIgnoreCase(name)){
         setDispDataStartTag(value);
      }else if(PTY_DISP_DATA_END_TAG.equalsIgnoreCase(name)){
         setDispDataEndTag(value);
      }else if(PTY_DATASET_PARAMETERS.equalsIgnoreCase(name)){
         setDatasetParameters(value);
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
            setLabel(config.get(PTY_LABEL));
         }
         if(config.contains("is_valid")){
            setIsValid(RBoolean.parse(config.get(PTY_IS_VALID)));
         }
         if(config.contains("note")){
            setNote(config.get(PTY_NOTE));
         }
         if(config.contains("sql")){
            setSql(config.get(PTY_SQL));
         }
         if(config.contains("display_command")){
            setDisplayCommand(config.get(PTY_DISPLAY_COMMAND));
         }
         if(config.contains("display_command_tag")){
            setDisplayCommandTag(config.get(PTY_DISPLAY_COMMAND_TAG));
         }
         if(config.contains("display_head")){
            setDisplayHead(config.get(PTY_DISPLAY_HEAD));
         }
         if(config.contains("display_head_tag")){
            setDisplayHeadTag(config.get(PTY_DISPLAY_HEAD_TAG));
         }
         if(config.contains("display_label")){
            setDisplayLabel(config.get(PTY_DISPLAY_LABEL));
         }
         if(config.contains("display_label_tag")){
            setDisplayLabelTag(config.get(PTY_DISPLAY_LABEL_TAG));
         }
         if(config.contains("display_valid")){
            setDisplayValid(config.get(PTY_DISPLAY_VALID));
         }
         if(config.contains("display_valid_tag")){
            setDisplayValidTag(config.get(PTY_DISPLAY_VALID_TAG));
         }
         if(config.contains("is_open")){
            setIsOpen(config.get(PTY_IS_OPEN));
         }
         if(config.contains("export_name")){
            setExportName(config.get(PTY_EXPORT_NAME));
         }
         if(config.contains("export_charset")){
            setExportCharset(config.get(PTY_EXPORT_CHARSET));
         }
         if(config.contains("disp_data_start_tag")){
            setDispDataStartTag(config.get(PTY_DISP_DATA_START_TAG));
         }
         if(config.contains("disp_data_end_tag")){
            setDispDataEndTag(config.get(PTY_DISP_DATA_END_TAG));
         }
         if(config.contains("dataset_parameters")){
            setDatasetParameters(config.get(PTY_DATASET_PARAMETERS));
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
         if(config.contains("sql")){
            setSql(config.get(PTY_SQL));
         }
         if(config.contains("display_command")){
            setDisplayCommand(config.get(PTY_DISPLAY_COMMAND));
         }
         if(config.contains("display_command_tag")){
            setDisplayCommandTag(config.get(PTY_DISPLAY_COMMAND_TAG));
         }
         if(config.contains("display_head")){
            setDisplayHead(config.get(PTY_DISPLAY_HEAD));
         }
         if(config.contains("display_head_tag")){
            setDisplayHeadTag(config.get(PTY_DISPLAY_HEAD_TAG));
         }
         if(config.contains("display_label")){
            setDisplayLabel(config.get(PTY_DISPLAY_LABEL));
         }
         if(config.contains("display_label_tag")){
            setDisplayLabelTag(config.get(PTY_DISPLAY_LABEL_TAG));
         }
         if(config.contains("display_valid")){
            setDisplayValid(config.get(PTY_DISPLAY_VALID));
         }
         if(config.contains("display_valid_tag")){
            setDisplayValidTag(config.get(PTY_DISPLAY_VALID_TAG));
         }
         if(config.contains("is_open")){
            setIsOpen(config.get(PTY_IS_OPEN));
         }
         if(config.contains("export_name")){
            setExportName(config.get(PTY_EXPORT_NAME));
         }
         if(config.contains("export_charset")){
            setExportCharset(config.get(PTY_EXPORT_CHARSET));
         }
         if(config.contains("disp_data_start_tag")){
            setDispDataStartTag(config.get(PTY_DISP_DATA_START_TAG));
         }
         if(config.contains("disp_data_end_tag")){
            setDispDataEndTag(config.get(PTY_DISP_DATA_END_TAG));
         }
         if(config.contains("dataset_parameters")){
            setDatasetParameters(config.get(PTY_DATASET_PARAMETERS));
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
         if(config.contains("sql")){
            setSql(config.get(PTY_SQL));
         }
         if(config.contains("display_command")){
            setDisplayCommand(config.get(PTY_DISPLAY_COMMAND));
         }
         if(config.contains("display_command_tag")){
            setDisplayCommandTag(config.get(PTY_DISPLAY_COMMAND_TAG));
         }
         if(config.contains("display_head")){
            setDisplayHead(config.get(PTY_DISPLAY_HEAD));
         }
         if(config.contains("display_head_tag")){
            setDisplayHeadTag(config.get(PTY_DISPLAY_HEAD_TAG));
         }
         if(config.contains("display_label")){
            setDisplayLabel(config.get(PTY_DISPLAY_LABEL));
         }
         if(config.contains("display_label_tag")){
            setDisplayLabelTag(config.get(PTY_DISPLAY_LABEL_TAG));
         }
         if(config.contains("display_valid")){
            setDisplayValid(config.get(PTY_DISPLAY_VALID));
         }
         if(config.contains("display_valid_tag")){
            setDisplayValidTag(config.get(PTY_DISPLAY_VALID_TAG));
         }
         if(config.contains("is_open")){
            setIsOpen(config.get(PTY_IS_OPEN));
         }
         if(config.contains("export_name")){
            setExportName(config.get(PTY_EXPORT_NAME));
         }
         if(config.contains("export_charset")){
            setExportCharset(config.get(PTY_EXPORT_CHARSET));
         }
         if(config.contains("disp_data_start_tag")){
            setDispDataStartTag(config.get(PTY_DISP_DATA_START_TAG));
         }
         if(config.contains("disp_data_end_tag")){
            setDispDataEndTag(config.get(PTY_DISP_DATA_END_TAG));
         }
         if(config.contains("dataset_parameters")){
            setDatasetParameters(config.get(PTY_DATASET_PARAMETERS));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getSql())){
            if(config.contains("sql")){
               setSql(config.get(PTY_SQL));
            }
         }
         if(RString.isEmpty(getDisplayCommand())){
            if(config.contains("display_command")){
               setDisplayCommand(config.get(PTY_DISPLAY_COMMAND));
            }
         }
         if(RString.isEmpty(getDisplayCommandTag())){
            if(config.contains("display_command_tag")){
               setDisplayCommandTag(config.get(PTY_DISPLAY_COMMAND_TAG));
            }
         }
         if(RString.isEmpty(getDisplayHead())){
            if(config.contains("display_head")){
               setDisplayHead(config.get(PTY_DISPLAY_HEAD));
            }
         }
         if(RString.isEmpty(getDisplayHeadTag())){
            if(config.contains("display_head_tag")){
               setDisplayHeadTag(config.get(PTY_DISPLAY_HEAD_TAG));
            }
         }
         if(RString.isEmpty(getDisplayLabel())){
            if(config.contains("display_label")){
               setDisplayLabel(config.get(PTY_DISPLAY_LABEL));
            }
         }
         if(RString.isEmpty(getDisplayLabelTag())){
            if(config.contains("display_label_tag")){
               setDisplayLabelTag(config.get(PTY_DISPLAY_LABEL_TAG));
            }
         }
         if(RString.isEmpty(getDisplayValid())){
            if(config.contains("display_valid")){
               setDisplayValid(config.get(PTY_DISPLAY_VALID));
            }
         }
         if(RString.isEmpty(getDisplayValidTag())){
            if(config.contains("display_valid_tag")){
               setDisplayValidTag(config.get(PTY_DISPLAY_VALID_TAG));
            }
         }
         if(RString.isEmpty(getIsOpen())){
            if(config.contains("is_open")){
               setIsOpen(config.get(PTY_IS_OPEN));
            }
         }
         if(RString.isEmpty(getExportName())){
            if(config.contains("export_name")){
               setExportName(config.get(PTY_EXPORT_NAME));
            }
         }
         if(RString.isEmpty(getExportCharset())){
            if(config.contains("export_charset")){
               setExportCharset(config.get(PTY_EXPORT_CHARSET));
            }
         }
         if(RString.isEmpty(getDispDataStartTag())){
            if(config.contains("disp_data_start_tag")){
               setDispDataStartTag(config.get(PTY_DISP_DATA_START_TAG));
            }
         }
         if(RString.isEmpty(getDispDataEndTag())){
            if(config.contains("disp_data_end_tag")){
               setDispDataEndTag(config.get(PTY_DISP_DATA_END_TAG));
            }
         }
         if(RString.isEmpty(getDatasetParameters())){
            if(config.contains("dataset_parameters")){
               setDatasetParameters(config.get(PTY_DATASET_PARAMETERS));
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
         if(RString.isNotEmpty(getLabel())){
            config.set(PTY_LABEL, getLabel());
         }
         if(RBoolean.parse(getIsValid())){
            config.set(PTY_IS_VALID, RBoolean.toString(getIsValid()));
         }
         if(RString.isNotEmpty(getNote())){
            config.set(PTY_NOTE, getNote());
         }
         if(RString.isNotEmpty(getSql())){
            config.set(PTY_SQL, getSql());
         }
         if(RString.isNotEmpty(getDisplayCommand())){
            config.set(PTY_DISPLAY_COMMAND, getDisplayCommand());
         }
         if(RString.isNotEmpty(getDisplayCommandTag())){
            config.set(PTY_DISPLAY_COMMAND_TAG, getDisplayCommandTag());
         }
         if(RString.isNotEmpty(getDisplayHead())){
            config.set(PTY_DISPLAY_HEAD, getDisplayHead());
         }
         if(RString.isNotEmpty(getDisplayHeadTag())){
            config.set(PTY_DISPLAY_HEAD_TAG, getDisplayHeadTag());
         }
         if(RString.isNotEmpty(getDisplayLabel())){
            config.set(PTY_DISPLAY_LABEL, getDisplayLabel());
         }
         if(RString.isNotEmpty(getDisplayLabelTag())){
            config.set(PTY_DISPLAY_LABEL_TAG, getDisplayLabelTag());
         }
         if(RString.isNotEmpty(getDisplayValid())){
            config.set(PTY_DISPLAY_VALID, getDisplayValid());
         }
         if(RString.isNotEmpty(getDisplayValidTag())){
            config.set(PTY_DISPLAY_VALID_TAG, getDisplayValidTag());
         }
         if(RString.isNotEmpty(getIsOpen())){
            config.set(PTY_IS_OPEN, getIsOpen());
         }
         if(RString.isNotEmpty(getExportName())){
            config.set(PTY_EXPORT_NAME, getExportName());
         }
         if(RString.isNotEmpty(getExportCharset())){
            config.set(PTY_EXPORT_CHARSET, getExportCharset());
         }
         if(RString.isNotEmpty(getDispDataStartTag())){
            config.set(PTY_DISP_DATA_START_TAG, getDispDataStartTag());
         }
         if(RString.isNotEmpty(getDispDataEndTag())){
            config.set(PTY_DISP_DATA_END_TAG, getDispDataEndTag());
         }
         if(RString.isNotEmpty(getDatasetParameters())){
            config.set(PTY_DATASET_PARAMETERS, getDatasetParameters());
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
         if(RString.isNotEmpty(getSql())){
            config.set(PTY_SQL, getSql());
         }
         if(RString.isNotEmpty(getDisplayCommand())){
            config.set(PTY_DISPLAY_COMMAND, getDisplayCommand());
         }
         if(RString.isNotEmpty(getDisplayCommandTag())){
            config.set(PTY_DISPLAY_COMMAND_TAG, getDisplayCommandTag());
         }
         if(RString.isNotEmpty(getDisplayHead())){
            config.set(PTY_DISPLAY_HEAD, getDisplayHead());
         }
         if(RString.isNotEmpty(getDisplayHeadTag())){
            config.set(PTY_DISPLAY_HEAD_TAG, getDisplayHeadTag());
         }
         if(RString.isNotEmpty(getDisplayLabel())){
            config.set(PTY_DISPLAY_LABEL, getDisplayLabel());
         }
         if(RString.isNotEmpty(getDisplayLabelTag())){
            config.set(PTY_DISPLAY_LABEL_TAG, getDisplayLabelTag());
         }
         if(RString.isNotEmpty(getDisplayValid())){
            config.set(PTY_DISPLAY_VALID, getDisplayValid());
         }
         if(RString.isNotEmpty(getDisplayValidTag())){
            config.set(PTY_DISPLAY_VALID_TAG, getDisplayValidTag());
         }
         if(RString.isNotEmpty(getIsOpen())){
            config.set(PTY_IS_OPEN, getIsOpen());
         }
         if(RString.isNotEmpty(getExportName())){
            config.set(PTY_EXPORT_NAME, getExportName());
         }
         if(RString.isNotEmpty(getExportCharset())){
            config.set(PTY_EXPORT_CHARSET, getExportCharset());
         }
         if(RString.isNotEmpty(getDispDataStartTag())){
            config.set(PTY_DISP_DATA_START_TAG, getDispDataStartTag());
         }
         if(RString.isNotEmpty(getDispDataEndTag())){
            config.set(PTY_DISP_DATA_END_TAG, getDispDataEndTag());
         }
         if(RString.isNotEmpty(getDatasetParameters())){
            config.set(PTY_DATASET_PARAMETERS, getDatasetParameters());
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
         String sSql = getSql();
         if(RString.isNotEmpty(sSql)){
            config.set(PTY_SQL, sSql);
         }
         String sDisplayCommand = getDisplayCommand();
         if(RString.isNotEmpty(sDisplayCommand)){
            config.set(PTY_DISPLAY_COMMAND, sDisplayCommand);
         }
         String sDisplayCommandTag = getDisplayCommandTag();
         if(RString.isNotEmpty(sDisplayCommandTag)){
            config.set(PTY_DISPLAY_COMMAND_TAG, sDisplayCommandTag);
         }
         String sDisplayHead = getDisplayHead();
         if(RString.isNotEmpty(sDisplayHead)){
            config.set(PTY_DISPLAY_HEAD, sDisplayHead);
         }
         String sDisplayHeadTag = getDisplayHeadTag();
         if(RString.isNotEmpty(sDisplayHeadTag)){
            config.set(PTY_DISPLAY_HEAD_TAG, sDisplayHeadTag);
         }
         String sDisplayLabel = getDisplayLabel();
         if(RString.isNotEmpty(sDisplayLabel)){
            config.set(PTY_DISPLAY_LABEL, sDisplayLabel);
         }
         String sDisplayLabelTag = getDisplayLabelTag();
         if(RString.isNotEmpty(sDisplayLabelTag)){
            config.set(PTY_DISPLAY_LABEL_TAG, sDisplayLabelTag);
         }
         String sDisplayValid = getDisplayValid();
         if(RString.isNotEmpty(sDisplayValid)){
            config.set(PTY_DISPLAY_VALID, sDisplayValid);
         }
         String sDisplayValidTag = getDisplayValidTag();
         if(RString.isNotEmpty(sDisplayValidTag)){
            config.set(PTY_DISPLAY_VALID_TAG, sDisplayValidTag);
         }
         String sIsOpen = getIsOpen();
         if(RString.isNotEmpty(sIsOpen)){
            config.set(PTY_IS_OPEN, sIsOpen);
         }
         String sExportName = getExportName();
         if(RString.isNotEmpty(sExportName)){
            config.set(PTY_EXPORT_NAME, sExportName);
         }
         String sExportCharset = getExportCharset();
         if(RString.isNotEmpty(sExportCharset)){
            config.set(PTY_EXPORT_CHARSET, sExportCharset);
         }
         String sDispDataStartTag = getDispDataStartTag();
         if(RString.isNotEmpty(sDispDataStartTag)){
            config.set(PTY_DISP_DATA_START_TAG, sDispDataStartTag);
         }
         String sDispDataEndTag = getDispDataEndTag();
         if(RString.isNotEmpty(sDispDataEndTag)){
            config.set(PTY_DISP_DATA_END_TAG, sDispDataEndTag);
         }
         String sDatasetParameters = getDatasetParameters();
         if(RString.isNotEmpty(sDatasetParameters)){
            config.set(PTY_DATASET_PARAMETERS, sDatasetParameters);
         }
      }else if(EXmlConfig.Default == type){
         String sSql = getSql();
         if(RString.isNotEmpty(sSql)){
            config.set(PTY_SQL, sSql);
         }
         String sDisplayCommand = getDisplayCommand();
         if(RString.isNotEmpty(sDisplayCommand)){
            config.set(PTY_DISPLAY_COMMAND, sDisplayCommand);
         }
         String sDisplayCommandTag = getDisplayCommandTag();
         if(RString.isNotEmpty(sDisplayCommandTag)){
            config.set(PTY_DISPLAY_COMMAND_TAG, sDisplayCommandTag);
         }
         String sDisplayHead = getDisplayHead();
         if(RString.isNotEmpty(sDisplayHead)){
            config.set(PTY_DISPLAY_HEAD, sDisplayHead);
         }
         String sDisplayHeadTag = getDisplayHeadTag();
         if(RString.isNotEmpty(sDisplayHeadTag)){
            config.set(PTY_DISPLAY_HEAD_TAG, sDisplayHeadTag);
         }
         String sDisplayLabel = getDisplayLabel();
         if(RString.isNotEmpty(sDisplayLabel)){
            config.set(PTY_DISPLAY_LABEL, sDisplayLabel);
         }
         String sDisplayLabelTag = getDisplayLabelTag();
         if(RString.isNotEmpty(sDisplayLabelTag)){
            config.set(PTY_DISPLAY_LABEL_TAG, sDisplayLabelTag);
         }
         String sDisplayValid = getDisplayValid();
         if(RString.isNotEmpty(sDisplayValid)){
            config.set(PTY_DISPLAY_VALID, sDisplayValid);
         }
         String sDisplayValidTag = getDisplayValidTag();
         if(RString.isNotEmpty(sDisplayValidTag)){
            config.set(PTY_DISPLAY_VALID_TAG, sDisplayValidTag);
         }
         String sIsOpen = getIsOpen();
         if(RString.isNotEmpty(sIsOpen)){
            config.set(PTY_IS_OPEN, sIsOpen);
         }
         String sExportName = getExportName();
         if(RString.isNotEmpty(sExportName)){
            config.set(PTY_EXPORT_NAME, sExportName);
         }
         String sExportCharset = getExportCharset();
         if(RString.isNotEmpty(sExportCharset)){
            config.set(PTY_EXPORT_CHARSET, sExportCharset);
         }
         String sDispDataStartTag = getDispDataStartTag();
         if(RString.isNotEmpty(sDispDataStartTag)){
            config.set(PTY_DISP_DATA_START_TAG, sDispDataStartTag);
         }
         String sDispDataEndTag = getDispDataEndTag();
         if(RString.isNotEmpty(sDispDataEndTag)){
            config.set(PTY_DISP_DATA_END_TAG, sDispDataEndTag);
         }
         String sDatasetParameters = getDatasetParameters();
         if(RString.isNotEmpty(sDatasetParameters)){
            config.set(PTY_DATASET_PARAMETERS, sDatasetParameters);
         }
      }
   }
}