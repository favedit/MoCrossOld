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
// <T>文件导入对象的XML节点基类。</T>
//============================================================
public abstract class XBaseCsvImport
      extends FXmlObject
      implements
         XObjectFace
{
   // 名称定义
   public static final String NAME = "CsvImport";

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

   // 表头行数的名称定义
   public static final String PTY_HEAD_LINES = "head_lines";

   // 数据命令的名称定义
   public static final String PTY_EXECUTE_PLSQL = "execute_plsql";

   // 执行命令的名称定义
   public static final String PTY_EXECUTE_ACTION = "execute_action";

   // 执行前命令的名称定义
   public static final String PTY_EXECUTE_PLSQL_BEFORE = "execute_plsql_Before";

   // 执行后命令的名称定义
   public static final String PTY_EXECUTE_PLSQL_AFTER = "execute_plsql_after";

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

   // 表头行数的定义
   @AName("head_lines")
   protected String _headLines;

   // 数据命令的定义
   @AName("execute_plsql")
   protected String _executePlsql;

   // 执行命令的定义
   @AName("execute_action")
   protected String _executeAction;

   // 执行前命令的定义
   @AName("execute_plsql_before")
   protected String _executePlsqlBefore;

   // 执行后命令的定义
   @AName("execute_plsql_after")
   protected String _executePlsqlAfter;

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
   // <T>获得表头行数的内容。</T>
   //
   // @return 表头行数
   //============================================================
   public String getHeadLines(){
      return _headLines;
   }

   //============================================================
   // <T>设置表头行数的内容。</T>
   //
   // @param value 表头行数
   //============================================================
   public void setHeadLines(String value){
      _headLines = value;
   }

   //============================================================
   // <T>获得数据命令的内容。</T>
   //
   // @return 数据命令
   //============================================================
   public String getExecutePlsql(){
      return _executePlsql;
   }

   //============================================================
   // <T>设置数据命令的内容。</T>
   //
   // @param value 数据命令
   //============================================================
   public void setExecutePlsql(String value){
      _executePlsql = value;
   }

   //============================================================
   // <T>获得执行命令的内容。</T>
   //
   // @return 执行命令
   //============================================================
   public String getExecuteAction(){
      return _executeAction;
   }

   //============================================================
   // <T>设置执行命令的内容。</T>
   //
   // @param value 执行命令
   //============================================================
   public void setExecuteAction(String value){
      _executeAction = value;
   }

   //============================================================
   // <T>获得执行前命令的内容。</T>
   //
   // @return 执行前命令
   //============================================================
   public String getExecutePlsqlBefore(){
      return _executePlsqlBefore;
   }

   //============================================================
   // <T>设置执行前命令的内容。</T>
   //
   // @param value 执行前命令
   //============================================================
   public void setExecutePlsqlBefore(String value){
      _executePlsqlBefore = value;
   }

   //============================================================
   // <T>获得执行后命令的内容。</T>
   //
   // @return 执行后命令
   //============================================================
   public String getExecutePlsqlAfter(){
      return _executePlsqlAfter;
   }

   //============================================================
   // <T>设置执行后命令的内容。</T>
   //
   // @param value 执行后命令
   //============================================================
   public void setExecutePlsqlAfter(String value){
      _executePlsqlAfter = value;
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
      }else if(PTY_HEAD_LINES.equalsIgnoreCase(name)){
         return getHeadLines();
      }else if(PTY_EXECUTE_PLSQL.equalsIgnoreCase(name)){
         return getExecutePlsql();
      }else if(PTY_EXECUTE_ACTION.equalsIgnoreCase(name)){
         return getExecuteAction();
      }else if(PTY_EXECUTE_PLSQL_BEFORE.equalsIgnoreCase(name)){
         return getExecutePlsqlBefore();
      }else if(PTY_EXECUTE_PLSQL_AFTER.equalsIgnoreCase(name)){
         return getExecutePlsqlAfter();
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
      }else if(PTY_HEAD_LINES.equalsIgnoreCase(name)){
         setHeadLines(value);
      }else if(PTY_EXECUTE_PLSQL.equalsIgnoreCase(name)){
         setExecutePlsql(value);
      }else if(PTY_EXECUTE_ACTION.equalsIgnoreCase(name)){
         setExecuteAction(value);
      }else if(PTY_EXECUTE_PLSQL_BEFORE.equalsIgnoreCase(name)){
         setExecutePlsqlBefore(value);
      }else if(PTY_EXECUTE_PLSQL_AFTER.equalsIgnoreCase(name)){
         setExecutePlsqlAfter(value);
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
         if(config.contains("head_lines")){
            setHeadLines(config.get(PTY_HEAD_LINES));
         }
         if(config.contains("execute_plsql")){
            setExecutePlsql(config.get(PTY_EXECUTE_PLSQL));
         }
         if(config.contains("execute_action")){
            setExecuteAction(config.get(PTY_EXECUTE_ACTION));
         }
         if(config.contains("execute_plsql_before")){
            setExecutePlsqlBefore(config.get(PTY_EXECUTE_PLSQL_BEFORE));
         }
         if(config.contains("execute_plsql_after")){
            setExecutePlsqlAfter(config.get(PTY_EXECUTE_PLSQL_AFTER));
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
         if(config.contains("head_lines")){
            setHeadLines(config.get(PTY_HEAD_LINES));
         }
         if(config.contains("execute_plsql")){
            setExecutePlsql(config.get(PTY_EXECUTE_PLSQL));
         }
         if(config.contains("execute_action")){
            setExecuteAction(config.get(PTY_EXECUTE_ACTION));
         }
         if(config.contains("execute_plsql_before")){
            setExecutePlsqlBefore(config.get(PTY_EXECUTE_PLSQL_BEFORE));
         }
         if(config.contains("execute_plsql_after")){
            setExecutePlsqlAfter(config.get(PTY_EXECUTE_PLSQL_AFTER));
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
         if(config.contains("head_lines")){
            setHeadLines(config.get(PTY_HEAD_LINES));
         }
         if(config.contains("execute_plsql")){
            setExecutePlsql(config.get(PTY_EXECUTE_PLSQL));
         }
         if(config.contains("execute_action")){
            setExecuteAction(config.get(PTY_EXECUTE_ACTION));
         }
         if(config.contains("execute_plsql_before")){
            setExecutePlsqlBefore(config.get(PTY_EXECUTE_PLSQL_BEFORE));
         }
         if(config.contains("execute_plsql_after")){
            setExecutePlsqlAfter(config.get(PTY_EXECUTE_PLSQL_AFTER));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getExecutePlsql())){
            if(config.contains("execute_plsql")){
               setExecutePlsql(config.get(PTY_EXECUTE_PLSQL));
            }
         }
         if(RString.isEmpty(getExecutePlsqlBefore())){
            if(config.contains("execute_plsql_before")){
               setExecutePlsqlBefore(config.get(PTY_EXECUTE_PLSQL_BEFORE));
            }
         }
         if(RString.isEmpty(getExecutePlsqlAfter())){
            if(config.contains("execute_plsql_after")){
               setExecutePlsqlAfter(config.get(PTY_EXECUTE_PLSQL_AFTER));
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
         if(RString.isNotEmpty(getHeadLines())){
            config.set(PTY_HEAD_LINES, getHeadLines());
         }
         if(RString.isNotEmpty(getExecutePlsql())){
            config.set(PTY_EXECUTE_PLSQL, getExecutePlsql());
         }
         if(RString.isNotEmpty(getExecuteAction())){
            config.set(PTY_EXECUTE_ACTION, getExecuteAction());
         }
         if(RString.isNotEmpty(getExecutePlsqlBefore())){
            config.set(PTY_EXECUTE_PLSQL_BEFORE, getExecutePlsqlBefore());
         }
         if(RString.isNotEmpty(getExecutePlsqlAfter())){
            config.set(PTY_EXECUTE_PLSQL_AFTER, getExecutePlsqlAfter());
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
         if(RString.isNotEmpty(getHeadLines())){
            config.set(PTY_HEAD_LINES, getHeadLines());
         }
         if(RString.isNotEmpty(getExecutePlsql())){
            config.set(PTY_EXECUTE_PLSQL, getExecutePlsql());
         }
         if(RString.isNotEmpty(getExecuteAction())){
            config.set(PTY_EXECUTE_ACTION, getExecuteAction());
         }
         if(RString.isNotEmpty(getExecutePlsqlBefore())){
            config.set(PTY_EXECUTE_PLSQL_BEFORE, getExecutePlsqlBefore());
         }
         if(RString.isNotEmpty(getExecutePlsqlAfter())){
            config.set(PTY_EXECUTE_PLSQL_AFTER, getExecutePlsqlAfter());
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
         String sHeadLines = getHeadLines();
         if(RString.isNotEmpty(sHeadLines)){
            config.set(PTY_HEAD_LINES, sHeadLines);
         }
         String sExecutePlsql = getExecutePlsql();
         if(RString.isNotEmpty(sExecutePlsql)){
            config.set(PTY_EXECUTE_PLSQL, sExecutePlsql);
         }
         String sExecuteAction = getExecuteAction();
         if(RString.isNotEmpty(sExecuteAction)){
            config.set(PTY_EXECUTE_ACTION, sExecuteAction);
         }
         String sExecutePlsqlBefore = getExecutePlsqlBefore();
         if(RString.isNotEmpty(sExecutePlsqlBefore)){
            config.set(PTY_EXECUTE_PLSQL_BEFORE, sExecutePlsqlBefore);
         }
         String sExecutePlsqlAfter = getExecutePlsqlAfter();
         if(RString.isNotEmpty(sExecutePlsqlAfter)){
            config.set(PTY_EXECUTE_PLSQL_AFTER, sExecutePlsqlAfter);
         }
      }else if(EXmlConfig.Default == type){
         String sExecutePlsql = getExecutePlsql();
         if(RString.isNotEmpty(sExecutePlsql)){
            config.set(PTY_EXECUTE_PLSQL, sExecutePlsql);
         }
         String sExecutePlsqlBefore = getExecutePlsqlBefore();
         if(RString.isNotEmpty(sExecutePlsqlBefore)){
            config.set(PTY_EXECUTE_PLSQL_BEFORE, sExecutePlsqlBefore);
         }
         String sExecutePlsqlAfter = getExecutePlsqlAfter();
         if(RString.isNotEmpty(sExecutePlsqlAfter)){
            config.set(PTY_EXECUTE_PLSQL_AFTER, sExecutePlsqlAfter);
         }
      }
   }
}