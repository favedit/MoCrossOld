package org.mo.web.core.webform.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.web.core.webform.control.XComponentFace;

//============================================================
// <T>数据命令对象的XML节点基类。</T>
//============================================================
public abstract class XBaseDataAction
      extends FXmlObject
      implements
         XComponentFace
{
   // 名称定义
   public static final String NAME = "DataAction";

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

   // 数据服务的名称定义
   public static final String PTY_SERVICE = "service";

   // 图片路径的名称定义
   public static final String PTY_ICON_PATH = "icon_path";

   // 执行操作的名称定义
   public static final String PTY_ACTION = "action";

   // 回调函数的名称定义
   public static final String PTY_INVOKE_FUNCTION = "invoke_function";

   // 执行命令的名称定义
   public static final String PTY_EXECUTE_ACTION = "execute_action";

   // 最终命令的名称定义
   public static final String PTY_EXECUTE_FINAL_ACTION = "execute_final_action";

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

   // 数据服务的定义
   @AName("service")
   protected String _service;

   // 图片路径的定义
   @AName("icon_path")
   protected String _iconPath;

   // 执行操作的定义
   @AName("action")
   protected String _action;

   // 回调函数的定义
   @AName("invoke_function")
   protected String _invokeFunction;

   // 执行命令的定义
   @AName("execute_action")
   protected String _executeAction;

   // 最终命令的定义
   @AName("execute_final_action")
   protected String _executeFinalAction;

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
   // <T>获得数据服务的内容。</T>
   //
   // @return 数据服务
   //============================================================
   public String getService(){
      return _service;
   }

   //============================================================
   // <T>设置数据服务的内容。</T>
   //
   // @param value 数据服务
   //============================================================
   public void setService(String value){
      _service = value;
   }

   //============================================================
   // <T>获得图片路径的内容。</T>
   //
   // @return 图片路径
   //============================================================
   public String getIconPath(){
      return _iconPath;
   }

   //============================================================
   // <T>设置图片路径的内容。</T>
   //
   // @param value 图片路径
   //============================================================
   public void setIconPath(String value){
      _iconPath = value;
   }

   //============================================================
   // <T>获得执行操作的内容。</T>
   //
   // @return 执行操作
   //============================================================
   public String getAction(){
      return _action;
   }

   //============================================================
   // <T>设置执行操作的内容。</T>
   //
   // @param value 执行操作
   //============================================================
   public void setAction(String value){
      _action = value;
   }

   //============================================================
   // <T>获得回调函数的内容。</T>
   //
   // @return 回调函数
   //============================================================
   public String getInvokeFunction(){
      return _invokeFunction;
   }

   //============================================================
   // <T>设置回调函数的内容。</T>
   //
   // @param value 回调函数
   //============================================================
   public void setInvokeFunction(String value){
      _invokeFunction = value;
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
   // <T>获得最终命令的内容。</T>
   //
   // @return 最终命令
   //============================================================
   public String getExecuteFinalAction(){
      return _executeFinalAction;
   }

   //============================================================
   // <T>设置最终命令的内容。</T>
   //
   // @param value 最终命令
   //============================================================
   public void setExecuteFinalAction(String value){
      _executeFinalAction = value;
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
      }else if(PTY_SERVICE.equalsIgnoreCase(name)){
         return getService();
      }else if(PTY_ICON_PATH.equalsIgnoreCase(name)){
         return getIconPath();
      }else if(PTY_ACTION.equalsIgnoreCase(name)){
         return getAction();
      }else if(PTY_INVOKE_FUNCTION.equalsIgnoreCase(name)){
         return getInvokeFunction();
      }else if(PTY_EXECUTE_ACTION.equalsIgnoreCase(name)){
         return getExecuteAction();
      }else if(PTY_EXECUTE_FINAL_ACTION.equalsIgnoreCase(name)){
         return getExecuteFinalAction();
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
      }else if(PTY_SERVICE.equalsIgnoreCase(name)){
         setService(value);
      }else if(PTY_ICON_PATH.equalsIgnoreCase(name)){
         setIconPath(value);
      }else if(PTY_ACTION.equalsIgnoreCase(name)){
         setAction(value);
      }else if(PTY_INVOKE_FUNCTION.equalsIgnoreCase(name)){
         setInvokeFunction(value);
      }else if(PTY_EXECUTE_ACTION.equalsIgnoreCase(name)){
         setExecuteAction(value);
      }else if(PTY_EXECUTE_FINAL_ACTION.equalsIgnoreCase(name)){
         setExecuteFinalAction(value);
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
         if(config.contains("service")){
            setService(config.get(PTY_SERVICE));
         }
         if(config.contains("icon_path")){
            setIconPath(config.get(PTY_ICON_PATH));
         }
         if(config.contains("action")){
            setAction(config.get(PTY_ACTION));
         }
         if(config.contains("invoke_function")){
            setInvokeFunction(config.get(PTY_INVOKE_FUNCTION));
         }
         if(config.contains("execute_action")){
            setExecuteAction(config.get(PTY_EXECUTE_ACTION));
         }
         if(config.contains("execute_final_action")){
            setExecuteFinalAction(config.get(PTY_EXECUTE_FINAL_ACTION));
         }
      }else if(EXmlConfig.Simple == type){
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("label")){
            setLabel(config.get(PTY_LABEL));
         }
         if(config.contains("note")){
            setNote(config.get(PTY_NOTE));
         }
         if(config.contains("service")){
            setService(config.get(PTY_SERVICE));
         }
         if(config.contains("icon_path")){
            setIconPath(config.get(PTY_ICON_PATH));
         }
         if(config.contains("action")){
            setAction(config.get(PTY_ACTION));
         }
         if(config.contains("invoke_function")){
            setInvokeFunction(config.get(PTY_INVOKE_FUNCTION));
         }
         if(config.contains("execute_action")){
            setExecuteAction(config.get(PTY_EXECUTE_ACTION));
         }
         if(config.contains("execute_final_action")){
            setExecuteFinalAction(config.get(PTY_EXECUTE_FINAL_ACTION));
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
         if(config.contains("service")){
            setService(config.get(PTY_SERVICE));
         }
         if(config.contains("icon_path")){
            setIconPath(config.get(PTY_ICON_PATH));
         }
         if(config.contains("action")){
            setAction(config.get(PTY_ACTION));
         }
         if(config.contains("invoke_function")){
            setInvokeFunction(config.get(PTY_INVOKE_FUNCTION));
         }
         if(config.contains("execute_action")){
            setExecuteAction(config.get(PTY_EXECUTE_ACTION));
         }
         if(config.contains("execute_final_action")){
            setExecuteFinalAction(config.get(PTY_EXECUTE_FINAL_ACTION));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getIconPath())){
            if(config.contains("icon_path")){
               setIconPath(config.get(PTY_ICON_PATH));
            }
         }
         if(RString.isEmpty(getAction())){
            if(config.contains("action")){
               setAction(config.get(PTY_ACTION));
            }
         }
         if(RString.isEmpty(getInvokeFunction())){
            if(config.contains("invoke_function")){
               setInvokeFunction(config.get(PTY_INVOKE_FUNCTION));
            }
         }
         if(RString.isEmpty(getExecuteFinalAction())){
            if(config.contains("execute_final_action")){
               setExecuteFinalAction(config.get(PTY_EXECUTE_FINAL_ACTION));
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
         if(RString.isNotEmpty(getService())){
            config.set(PTY_SERVICE, getService());
         }
         if(RString.isNotEmpty(getIconPath())){
            config.set(PTY_ICON_PATH, getIconPath());
         }
         if(RString.isNotEmpty(getAction())){
            config.set(PTY_ACTION, getAction());
         }
         if(RString.isNotEmpty(getInvokeFunction())){
            config.set(PTY_INVOKE_FUNCTION, getInvokeFunction());
         }
         if(RString.isNotEmpty(getExecuteAction())){
            config.set(PTY_EXECUTE_ACTION, getExecuteAction());
         }
         if(RString.isNotEmpty(getExecuteFinalAction())){
            config.set(PTY_EXECUTE_FINAL_ACTION, getExecuteFinalAction());
         }
      }else if(EXmlConfig.Simple == type){
         if(RString.isNotEmpty(getName())){
            config.set(PTY_NAME, getName());
         }
         if(RString.isNotEmpty(getLabel())){
            config.set(PTY_LABEL, getLabel());
         }
         if(RString.isNotEmpty(getNote())){
            config.set(PTY_NOTE, getNote());
         }
         if(RString.isNotEmpty(getService())){
            config.set(PTY_SERVICE, getService());
         }
         if(RString.isNotEmpty(getIconPath())){
            config.set(PTY_ICON_PATH, getIconPath());
         }
         if(RString.isNotEmpty(getAction())){
            config.set(PTY_ACTION, getAction());
         }
         if(RString.isNotEmpty(getInvokeFunction())){
            config.set(PTY_INVOKE_FUNCTION, getInvokeFunction());
         }
         if(RString.isNotEmpty(getExecuteAction())){
            config.set(PTY_EXECUTE_ACTION, getExecuteAction());
         }
         if(RString.isNotEmpty(getExecuteFinalAction())){
            config.set(PTY_EXECUTE_FINAL_ACTION, getExecuteFinalAction());
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
         String sService = getService();
         if(RString.isNotEmpty(sService)){
            config.set(PTY_SERVICE, sService);
         }
         String sIconPath = getIconPath();
         if(RString.isNotEmpty(sIconPath)){
            config.set(PTY_ICON_PATH, sIconPath);
         }
         String sAction = getAction();
         if(RString.isNotEmpty(sAction)){
            config.set(PTY_ACTION, sAction);
         }
         String sInvokeFunction = getInvokeFunction();
         if(RString.isNotEmpty(sInvokeFunction)){
            config.set(PTY_INVOKE_FUNCTION, sInvokeFunction);
         }
         String sExecuteAction = getExecuteAction();
         if(RString.isNotEmpty(sExecuteAction)){
            config.set(PTY_EXECUTE_ACTION, sExecuteAction);
         }
         String sExecuteFinalAction = getExecuteFinalAction();
         if(RString.isNotEmpty(sExecuteFinalAction)){
            config.set(PTY_EXECUTE_FINAL_ACTION, sExecuteFinalAction);
         }
      }else if(EXmlConfig.Default == type){
         String sIconPath = getIconPath();
         if(RString.isNotEmpty(sIconPath)){
            config.set(PTY_ICON_PATH, sIconPath);
         }
         String sAction = getAction();
         if(RString.isNotEmpty(sAction)){
            config.set(PTY_ACTION, sAction);
         }
         String sInvokeFunction = getInvokeFunction();
         if(RString.isNotEmpty(sInvokeFunction)){
            config.set(PTY_INVOKE_FUNCTION, sInvokeFunction);
         }
         String sExecuteFinalAction = getExecuteFinalAction();
         if(RString.isNotEmpty(sExecuteFinalAction)){
            config.set(PTY_EXECUTE_FINAL_ACTION, sExecuteFinalAction);
         }
      }
   }
}