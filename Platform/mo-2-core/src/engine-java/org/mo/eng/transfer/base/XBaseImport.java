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
// <T>导入数据对象的XML节点基类。</T>
//============================================================
public abstract class XBaseImport
      extends FXmlObject
      implements
         XObjectFace
{
   // 名称定义
   public static final String NAME = "Import";

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

   // 显示字段名的名称定义
   public static final String PTY_DISPLAY_HEAD = "display_head";

   // 显示名称的名称定义
   public static final String PTY_DISPLAY_LABEL = "display_label";

   // 显示校验的名称定义
   public static final String PTY_DISPLAY_VALID = "display_valid";

   // 是否打开的名称定义
   public static final String PTY_IS_OPEN = "is_open";

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

   // 显示字段名的定义
   @AName("display_head")
   protected String _displayHead;

   // 显示名称的定义
   @AName("display_label")
   protected String _displayLabel;

   // 显示校验的定义
   @AName("display_valid")
   protected String _displayValid;

   // 是否打开的定义
   @AName("is_open")
   protected String _isOpen;

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
      }else if(PTY_DISPLAY_HEAD.equalsIgnoreCase(name)){
         return getDisplayHead();
      }else if(PTY_DISPLAY_LABEL.equalsIgnoreCase(name)){
         return getDisplayLabel();
      }else if(PTY_DISPLAY_VALID.equalsIgnoreCase(name)){
         return getDisplayValid();
      }else if(PTY_IS_OPEN.equalsIgnoreCase(name)){
         return getIsOpen();
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
      }else if(PTY_DISPLAY_HEAD.equalsIgnoreCase(name)){
         setDisplayHead(value);
      }else if(PTY_DISPLAY_LABEL.equalsIgnoreCase(name)){
         setDisplayLabel(value);
      }else if(PTY_DISPLAY_VALID.equalsIgnoreCase(name)){
         setDisplayValid(value);
      }else if(PTY_IS_OPEN.equalsIgnoreCase(name)){
         setIsOpen(value);
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
         if(config.contains("display_head")){
            setDisplayHead(config.get(PTY_DISPLAY_HEAD));
         }
         if(config.contains("display_label")){
            setDisplayLabel(config.get(PTY_DISPLAY_LABEL));
         }
         if(config.contains("display_valid")){
            setDisplayValid(config.get(PTY_DISPLAY_VALID));
         }
         if(config.contains("is_open")){
            setIsOpen(config.get(PTY_IS_OPEN));
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
         if(config.contains("display_head")){
            setDisplayHead(config.get(PTY_DISPLAY_HEAD));
         }
         if(config.contains("display_label")){
            setDisplayLabel(config.get(PTY_DISPLAY_LABEL));
         }
         if(config.contains("display_valid")){
            setDisplayValid(config.get(PTY_DISPLAY_VALID));
         }
         if(config.contains("is_open")){
            setIsOpen(config.get(PTY_IS_OPEN));
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
         if(config.contains("display_head")){
            setDisplayHead(config.get(PTY_DISPLAY_HEAD));
         }
         if(config.contains("display_label")){
            setDisplayLabel(config.get(PTY_DISPLAY_LABEL));
         }
         if(config.contains("display_valid")){
            setDisplayValid(config.get(PTY_DISPLAY_VALID));
         }
         if(config.contains("is_open")){
            setIsOpen(config.get(PTY_IS_OPEN));
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
         if(RString.isEmpty(getDisplayHead())){
            if(config.contains("display_head")){
               setDisplayHead(config.get(PTY_DISPLAY_HEAD));
            }
         }
         if(RString.isEmpty(getDisplayLabel())){
            if(config.contains("display_label")){
               setDisplayLabel(config.get(PTY_DISPLAY_LABEL));
            }
         }
         if(RString.isEmpty(getDisplayValid())){
            if(config.contains("display_valid")){
               setDisplayValid(config.get(PTY_DISPLAY_VALID));
            }
         }
         if(RString.isEmpty(getIsOpen())){
            if(config.contains("is_open")){
               setIsOpen(config.get(PTY_IS_OPEN));
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
         if(RString.isNotEmpty(getDisplayHead())){
            config.set(PTY_DISPLAY_HEAD, getDisplayHead());
         }
         if(RString.isNotEmpty(getDisplayLabel())){
            config.set(PTY_DISPLAY_LABEL, getDisplayLabel());
         }
         if(RString.isNotEmpty(getDisplayValid())){
            config.set(PTY_DISPLAY_VALID, getDisplayValid());
         }
         if(RString.isNotEmpty(getIsOpen())){
            config.set(PTY_IS_OPEN, getIsOpen());
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
         if(RString.isNotEmpty(getDisplayHead())){
            config.set(PTY_DISPLAY_HEAD, getDisplayHead());
         }
         if(RString.isNotEmpty(getDisplayLabel())){
            config.set(PTY_DISPLAY_LABEL, getDisplayLabel());
         }
         if(RString.isNotEmpty(getDisplayValid())){
            config.set(PTY_DISPLAY_VALID, getDisplayValid());
         }
         if(RString.isNotEmpty(getIsOpen())){
            config.set(PTY_IS_OPEN, getIsOpen());
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
         String sDisplayHead = getDisplayHead();
         if(RString.isNotEmpty(sDisplayHead)){
            config.set(PTY_DISPLAY_HEAD, sDisplayHead);
         }
         String sDisplayLabel = getDisplayLabel();
         if(RString.isNotEmpty(sDisplayLabel)){
            config.set(PTY_DISPLAY_LABEL, sDisplayLabel);
         }
         String sDisplayValid = getDisplayValid();
         if(RString.isNotEmpty(sDisplayValid)){
            config.set(PTY_DISPLAY_VALID, sDisplayValid);
         }
         String sIsOpen = getIsOpen();
         if(RString.isNotEmpty(sIsOpen)){
            config.set(PTY_IS_OPEN, sIsOpen);
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
         String sDisplayHead = getDisplayHead();
         if(RString.isNotEmpty(sDisplayHead)){
            config.set(PTY_DISPLAY_HEAD, sDisplayHead);
         }
         String sDisplayLabel = getDisplayLabel();
         if(RString.isNotEmpty(sDisplayLabel)){
            config.set(PTY_DISPLAY_LABEL, sDisplayLabel);
         }
         String sDisplayValid = getDisplayValid();
         if(RString.isNotEmpty(sDisplayValid)){
            config.set(PTY_DISPLAY_VALID, sDisplayValid);
         }
         String sIsOpen = getIsOpen();
         if(RString.isNotEmpty(sIsOpen)){
            config.set(PTY_IS_OPEN, sIsOpen);
         }
      }
   }
}