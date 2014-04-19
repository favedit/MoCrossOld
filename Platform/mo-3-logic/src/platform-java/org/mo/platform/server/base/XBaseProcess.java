package org.mo.platform.server.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.platform.server.common.XObjectFace;

//============================================================
// <T>代码列表对象的XML节点基类。</T>
//============================================================
public abstract class XBaseProcess
      extends FXmlObject
      implements
         XObjectFace
{
   // 名称定义
   public static final String NAME = "Process";

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

   // 代码的名称定义
   public static final String PTY_CODE = "code";

   // 名称的名称定义
   public static final String PTY_NAME = "name";

   // 标签的名称定义
   public static final String PTY_LABEL = "label";

   // 有效性的名称定义
   public static final String PTY_IS_VALID = "is_valid";

   // 注释的名称定义
   public static final String PTY_NOTE = "note";

   // 用户的名称定义
   public static final String PTY_USER = "user";

   // 标志的名称定义
   public static final String PTY_FLAG = "flag";

   // 等待设置的名称定义
   public static final String PTY_OPTION_WAIT = "option_wait";

   // 命令的名称定义
   public static final String PTY_COMMAND = "command";

   // 关闭标志的名称定义
   public static final String PTY_STOP_FLAG = "stop_flag";

   // 进程地址的名称定义
   public static final String PTY_HOST = "host";

   // 端口的名称定义
   public static final String PTY_PORT = "port";

   // 代码的定义
   @AName("code")
   protected String _code;

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

   // 用户的定义
   @AName("user")
   protected String _user;

   // 标志的定义
   @AName("flag")
   protected String _flag;

   // 等待设置的定义
   @AName("option_wait")
   protected String _optionWait;

   // 命令的定义
   @AName("command")
   protected String _command;

   // 关闭标志的定义
   @AName("stop_flag")
   protected String _stopFlag;

   // 进程地址的定义
   @AName("host")
   protected String _host;

   // 端口的定义
   @AName("port")
   protected String _port;

   //============================================================
   // <T>获得代码的内容。</T>
   //
   // @return 代码
   //============================================================
   public String getCode(){
      return _code;
   }

   //============================================================
   // <T>设置代码的内容。</T>
   //
   // @param value 代码
   //============================================================
   public void setCode(String value){
      _code = value;
   }

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
   // <T>获得用户的内容。</T>
   //
   // @return 用户
   //============================================================
   public String getUser(){
      return _user;
   }

   //============================================================
   // <T>设置用户的内容。</T>
   //
   // @param value 用户
   //============================================================
   public void setUser(String value){
      _user = value;
   }

   //============================================================
   // <T>获得标志的内容。</T>
   //
   // @return 标志
   //============================================================
   public String getFlag(){
      return _flag;
   }

   //============================================================
   // <T>设置标志的内容。</T>
   //
   // @param value 标志
   //============================================================
   public void setFlag(String value){
      _flag = value;
   }

   //============================================================
   // <T>获得等待设置的内容。</T>
   //
   // @return 等待设置
   //============================================================
   public String getOptionWait(){
      return _optionWait;
   }

   //============================================================
   // <T>设置等待设置的内容。</T>
   //
   // @param value 等待设置
   //============================================================
   public void setOptionWait(String value){
      _optionWait = value;
   }

   //============================================================
   // <T>获得命令的内容。</T>
   //
   // @return 命令
   //============================================================
   public String getCommand(){
      return _command;
   }

   //============================================================
   // <T>设置命令的内容。</T>
   //
   // @param value 命令
   //============================================================
   public void setCommand(String value){
      _command = value;
   }

   //============================================================
   // <T>获得关闭标志的内容。</T>
   //
   // @return 关闭标志
   //============================================================
   public String getStopFlag(){
      return _stopFlag;
   }

   //============================================================
   // <T>设置关闭标志的内容。</T>
   //
   // @param value 关闭标志
   //============================================================
   public void setStopFlag(String value){
      _stopFlag = value;
   }

   //============================================================
   // <T>获得进程地址的内容。</T>
   //
   // @return 进程地址
   //============================================================
   public String getHost(){
      return _host;
   }

   //============================================================
   // <T>设置进程地址的内容。</T>
   //
   // @param value 进程地址
   //============================================================
   public void setHost(String value){
      _host = value;
   }

   //============================================================
   // <T>获得端口的内容。</T>
   //
   // @return 端口
   //============================================================
   public String getPort(){
      return _port;
   }

   //============================================================
   // <T>设置端口的内容。</T>
   //
   // @param value 端口
   //============================================================
   public void setPort(String value){
      _port = value;
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
      }else if(PTY_CODE.equalsIgnoreCase(name)){
         return getCode();
      }else if(PTY_NAME.equalsIgnoreCase(name)){
         return getName();
      }else if(PTY_LABEL.equalsIgnoreCase(name)){
         return getLabel();
      }else if(PTY_IS_VALID.equalsIgnoreCase(name)){
         return RBoolean.toString(getIsValid());
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         return getNote();
      }else if(PTY_USER.equalsIgnoreCase(name)){
         return getUser();
      }else if(PTY_FLAG.equalsIgnoreCase(name)){
         return getFlag();
      }else if(PTY_OPTION_WAIT.equalsIgnoreCase(name)){
         return getOptionWait();
      }else if(PTY_COMMAND.equalsIgnoreCase(name)){
         return getCommand();
      }else if(PTY_STOP_FLAG.equalsIgnoreCase(name)){
         return getStopFlag();
      }else if(PTY_HOST.equalsIgnoreCase(name)){
         return getHost();
      }else if(PTY_PORT.equalsIgnoreCase(name)){
         return getPort();
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
      }else if(PTY_CODE.equalsIgnoreCase(name)){
         setCode(value);
      }else if(PTY_NAME.equalsIgnoreCase(name)){
         setName(value);
      }else if(PTY_LABEL.equalsIgnoreCase(name)){
         setLabel(value);
      }else if(PTY_IS_VALID.equalsIgnoreCase(name)){
         setIsValid(RBoolean.parse(value));
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         setNote(value);
      }else if(PTY_USER.equalsIgnoreCase(name)){
         setUser(value);
      }else if(PTY_FLAG.equalsIgnoreCase(name)){
         setFlag(value);
      }else if(PTY_OPTION_WAIT.equalsIgnoreCase(name)){
         setOptionWait(value);
      }else if(PTY_COMMAND.equalsIgnoreCase(name)){
         setCommand(value);
      }else if(PTY_STOP_FLAG.equalsIgnoreCase(name)){
         setStopFlag(value);
      }else if(PTY_HOST.equalsIgnoreCase(name)){
         setHost(value);
      }else if(PTY_PORT.equalsIgnoreCase(name)){
         setPort(value);
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
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
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
         if(config.contains("user")){
            setUser(config.get(PTY_USER));
         }
         if(config.contains("flag")){
            setFlag(config.get(PTY_FLAG));
         }
         if(config.contains("option_wait")){
            setOptionWait(config.get(PTY_OPTION_WAIT));
         }
         if(config.contains("command")){
            setCommand(config.get(PTY_COMMAND));
         }
         if(config.contains("stop_flag")){
            setStopFlag(config.get(PTY_STOP_FLAG));
         }
         if(config.contains("host")){
            setHost(config.get(PTY_HOST));
         }
         if(config.contains("port")){
            setPort(config.get(PTY_PORT));
         }
      }else if(EXmlConfig.Simple == type){
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
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
         if(config.contains("user")){
            setUser(config.get(PTY_USER));
         }
         if(config.contains("flag")){
            setFlag(config.get(PTY_FLAG));
         }
         if(config.contains("option_wait")){
            setOptionWait(config.get(PTY_OPTION_WAIT));
         }
         if(config.contains("command")){
            setCommand(config.get(PTY_COMMAND));
         }
         if(config.contains("stop_flag")){
            setStopFlag(config.get(PTY_STOP_FLAG));
         }
         if(config.contains("host")){
            setHost(config.get(PTY_HOST));
         }
         if(config.contains("port")){
            setPort(config.get(PTY_PORT));
         }
      }else if(EXmlConfig.Value == type){
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
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
         if(config.contains("user")){
            setUser(config.get(PTY_USER));
         }
         if(config.contains("flag")){
            setFlag(config.get(PTY_FLAG));
         }
         if(config.contains("option_wait")){
            setOptionWait(config.get(PTY_OPTION_WAIT));
         }
         if(config.contains("command")){
            setCommand(config.get(PTY_COMMAND));
         }
         if(config.contains("stop_flag")){
            setStopFlag(config.get(PTY_STOP_FLAG));
         }
         if(config.contains("host")){
            setHost(config.get(PTY_HOST));
         }
         if(config.contains("port")){
            setPort(config.get(PTY_PORT));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getCode())){
            if(config.contains("code")){
               setCode(config.get(PTY_CODE));
            }
         }
         if(RString.isEmpty(getFlag())){
            if(config.contains("flag")){
               setFlag(config.get(PTY_FLAG));
            }
         }
         if(RString.isEmpty(getOptionWait())){
            if(config.contains("option_wait")){
               setOptionWait(config.get(PTY_OPTION_WAIT));
            }
         }
         if(RString.isEmpty(getCommand())){
            if(config.contains("command")){
               setCommand(config.get(PTY_COMMAND));
            }
         }
         if(RString.isEmpty(getStopFlag())){
            if(config.contains("stop_flag")){
               setStopFlag(config.get(PTY_STOP_FLAG));
            }
         }
         if(RString.isEmpty(getHost())){
            if(config.contains("host")){
               setHost(config.get(PTY_HOST));
            }
         }
         if(RString.isEmpty(getPort())){
            if(config.contains("port")){
               setPort(config.get(PTY_PORT));
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
         if(RString.isNotEmpty(getCode())){
            config.set(PTY_CODE, getCode());
         }
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
         if(RString.isNotEmpty(getUser())){
            config.set(PTY_USER, getUser());
         }
         if(RString.isNotEmpty(getFlag())){
            config.set(PTY_FLAG, getFlag());
         }
         if(RString.isNotEmpty(getOptionWait())){
            config.set(PTY_OPTION_WAIT, getOptionWait());
         }
         if(RString.isNotEmpty(getCommand())){
            config.set(PTY_COMMAND, getCommand());
         }
         if(RString.isNotEmpty(getStopFlag())){
            config.set(PTY_STOP_FLAG, getStopFlag());
         }
         if(RString.isNotEmpty(getHost())){
            config.set(PTY_HOST, getHost());
         }
         if(RString.isNotEmpty(getPort())){
            config.set(PTY_PORT, getPort());
         }
      }else if(EXmlConfig.Simple == type){
         if(RString.isNotEmpty(getCode())){
            config.set(PTY_CODE, getCode());
         }
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
         if(RString.isNotEmpty(getUser())){
            config.set(PTY_USER, getUser());
         }
         if(RString.isNotEmpty(getFlag())){
            config.set(PTY_FLAG, getFlag());
         }
         if(RString.isNotEmpty(getOptionWait())){
            config.set(PTY_OPTION_WAIT, getOptionWait());
         }
         if(RString.isNotEmpty(getCommand())){
            config.set(PTY_COMMAND, getCommand());
         }
         if(RString.isNotEmpty(getStopFlag())){
            config.set(PTY_STOP_FLAG, getStopFlag());
         }
         if(RString.isNotEmpty(getHost())){
            config.set(PTY_HOST, getHost());
         }
         if(RString.isNotEmpty(getPort())){
            config.set(PTY_PORT, getPort());
         }
      }else if(EXmlConfig.Value == type){
         String sCode = getCode();
         if(RString.isNotEmpty(sCode)){
            config.set(PTY_CODE, sCode);
         }
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
         String sUser = getUser();
         if(RString.isNotEmpty(sUser)){
            config.set(PTY_USER, sUser);
         }
         String sFlag = getFlag();
         if(RString.isNotEmpty(sFlag)){
            config.set(PTY_FLAG, sFlag);
         }
         String sOptionWait = getOptionWait();
         if(RString.isNotEmpty(sOptionWait)){
            config.set(PTY_OPTION_WAIT, sOptionWait);
         }
         String sCommand = getCommand();
         if(RString.isNotEmpty(sCommand)){
            config.set(PTY_COMMAND, sCommand);
         }
         String sStopFlag = getStopFlag();
         if(RString.isNotEmpty(sStopFlag)){
            config.set(PTY_STOP_FLAG, sStopFlag);
         }
         String sHost = getHost();
         if(RString.isNotEmpty(sHost)){
            config.set(PTY_HOST, sHost);
         }
         String sPort = getPort();
         if(RString.isNotEmpty(sPort)){
            config.set(PTY_PORT, sPort);
         }
      }else if(EXmlConfig.Default == type){
         String sCode = getCode();
         if(RString.isNotEmpty(sCode)){
            config.set(PTY_CODE, sCode);
         }
         String sFlag = getFlag();
         if(RString.isNotEmpty(sFlag)){
            config.set(PTY_FLAG, sFlag);
         }
         String sOptionWait = getOptionWait();
         if(RString.isNotEmpty(sOptionWait)){
            config.set(PTY_OPTION_WAIT, sOptionWait);
         }
         String sCommand = getCommand();
         if(RString.isNotEmpty(sCommand)){
            config.set(PTY_COMMAND, sCommand);
         }
         String sStopFlag = getStopFlag();
         if(RString.isNotEmpty(sStopFlag)){
            config.set(PTY_STOP_FLAG, sStopFlag);
         }
         String sHost = getHost();
         if(RString.isNotEmpty(sHost)){
            config.set(PTY_HOST, sHost);
         }
         String sPort = getPort();
         if(RString.isNotEmpty(sPort)){
            config.set(PTY_PORT, sPort);
         }
      }
   }
}