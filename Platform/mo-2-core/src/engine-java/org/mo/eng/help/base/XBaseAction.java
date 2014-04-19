package org.mo.eng.help.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.eng.help.common.XObject;

//============================================================
// <T>命令对象的XML节点基类。</T>
//============================================================
public abstract class XBaseAction
      extends FXmlObject
      implements
         XObject
{
   // 名称定义
   public static final String NAME = "Action";

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

   // 调用函数的名称定义
   public static final String PTY_PROCESS_INVOKE = "process_invoke";

   // 路径定义的名称定义
   public static final String PTY_PROCESS_PATH = "process_path";

   // 输出文件的名称定义
   public static final String PTY_OUTPUT_FILE = "output_file";

   // 输出路径的名称定义
   public static final String PTY_OUTPUT_PATH = "output_path";

   // 模板名称的名称定义
   public static final String PTY_TEMPLATE_NAME = "template_name";

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

   // 调用函数的定义
   @AName("process_invoke")
   protected String _processInvoke;

   // 路径定义的定义
   @AName("process_path")
   protected String _processPath;

   // 输出文件的定义
   @AName("output_file")
   protected String _outputFile;

   // 输出路径的定义
   @AName("output_path")
   protected String _outputPath;

   // 模板名称的定义
   @AName("template_name")
   protected String _templateName;

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
   // <T>获得调用函数的内容。</T>
   //
   // @return 调用函数
   //============================================================
   public String getProcessInvoke(){
      return _processInvoke;
   }

   //============================================================
   // <T>设置调用函数的内容。</T>
   //
   // @param value 调用函数
   //============================================================
   public void setProcessInvoke(String value){
      _processInvoke = value;
   }

   //============================================================
   // <T>获得路径定义的内容。</T>
   //
   // @return 路径定义
   //============================================================
   public String getProcessPath(){
      return _processPath;
   }

   //============================================================
   // <T>设置路径定义的内容。</T>
   //
   // @param value 路径定义
   //============================================================
   public void setProcessPath(String value){
      _processPath = value;
   }

   //============================================================
   // <T>获得输出文件的内容。</T>
   //
   // @return 输出文件
   //============================================================
   public String getOutputFile(){
      return _outputFile;
   }

   //============================================================
   // <T>设置输出文件的内容。</T>
   //
   // @param value 输出文件
   //============================================================
   public void setOutputFile(String value){
      _outputFile = value;
   }

   //============================================================
   // <T>获得输出路径的内容。</T>
   //
   // @return 输出路径
   //============================================================
   public String getOutputPath(){
      return _outputPath;
   }

   //============================================================
   // <T>设置输出路径的内容。</T>
   //
   // @param value 输出路径
   //============================================================
   public void setOutputPath(String value){
      _outputPath = value;
   }

   //============================================================
   // <T>获得模板名称的内容。</T>
   //
   // @return 模板名称
   //============================================================
   public String getTemplateName(){
      return _templateName;
   }

   //============================================================
   // <T>设置模板名称的内容。</T>
   //
   // @param value 模板名称
   //============================================================
   public void setTemplateName(String value){
      _templateName = value;
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
      }else if(PTY_PROCESS_INVOKE.equalsIgnoreCase(name)){
         return getProcessInvoke();
      }else if(PTY_PROCESS_PATH.equalsIgnoreCase(name)){
         return getProcessPath();
      }else if(PTY_OUTPUT_FILE.equalsIgnoreCase(name)){
         return getOutputFile();
      }else if(PTY_OUTPUT_PATH.equalsIgnoreCase(name)){
         return getOutputPath();
      }else if(PTY_TEMPLATE_NAME.equalsIgnoreCase(name)){
         return getTemplateName();
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
      }else if(PTY_PROCESS_INVOKE.equalsIgnoreCase(name)){
         setProcessInvoke(value);
      }else if(PTY_PROCESS_PATH.equalsIgnoreCase(name)){
         setProcessPath(value);
      }else if(PTY_OUTPUT_FILE.equalsIgnoreCase(name)){
         setOutputFile(value);
      }else if(PTY_OUTPUT_PATH.equalsIgnoreCase(name)){
         setOutputPath(value);
      }else if(PTY_TEMPLATE_NAME.equalsIgnoreCase(name)){
         setTemplateName(value);
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
         if(config.contains("process_invoke")){
            setProcessInvoke(config.get(PTY_PROCESS_INVOKE));
         }
         if(config.contains("process_path")){
            setProcessPath(config.get(PTY_PROCESS_PATH));
         }
         if(config.contains("output_file")){
            setOutputFile(config.get(PTY_OUTPUT_FILE));
         }
         if(config.contains("output_path")){
            setOutputPath(config.get(PTY_OUTPUT_PATH));
         }
         if(config.contains("template_name")){
            setTemplateName(config.get(PTY_TEMPLATE_NAME));
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
         if(config.contains("process_invoke")){
            setProcessInvoke(config.get(PTY_PROCESS_INVOKE));
         }
         if(config.contains("process_path")){
            setProcessPath(config.get(PTY_PROCESS_PATH));
         }
         if(config.contains("output_file")){
            setOutputFile(config.get(PTY_OUTPUT_FILE));
         }
         if(config.contains("output_path")){
            setOutputPath(config.get(PTY_OUTPUT_PATH));
         }
         if(config.contains("template_name")){
            setTemplateName(config.get(PTY_TEMPLATE_NAME));
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
         if(config.contains("process_invoke")){
            setProcessInvoke(config.get(PTY_PROCESS_INVOKE));
         }
         if(config.contains("process_path")){
            setProcessPath(config.get(PTY_PROCESS_PATH));
         }
         if(config.contains("output_file")){
            setOutputFile(config.get(PTY_OUTPUT_FILE));
         }
         if(config.contains("output_path")){
            setOutputPath(config.get(PTY_OUTPUT_PATH));
         }
         if(config.contains("template_name")){
            setTemplateName(config.get(PTY_TEMPLATE_NAME));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getProcessInvoke())){
            if(config.contains("process_invoke")){
               setProcessInvoke(config.get(PTY_PROCESS_INVOKE));
            }
         }
         if(RString.isEmpty(getProcessPath())){
            if(config.contains("process_path")){
               setProcessPath(config.get(PTY_PROCESS_PATH));
            }
         }
         if(RString.isEmpty(getOutputFile())){
            if(config.contains("output_file")){
               setOutputFile(config.get(PTY_OUTPUT_FILE));
            }
         }
         if(RString.isEmpty(getOutputPath())){
            if(config.contains("output_path")){
               setOutputPath(config.get(PTY_OUTPUT_PATH));
            }
         }
         if(RString.isEmpty(getTemplateName())){
            if(config.contains("template_name")){
               setTemplateName(config.get(PTY_TEMPLATE_NAME));
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
         if(RString.isNotEmpty(getProcessInvoke())){
            config.set(PTY_PROCESS_INVOKE, getProcessInvoke());
         }
         if(RString.isNotEmpty(getProcessPath())){
            config.set(PTY_PROCESS_PATH, getProcessPath());
         }
         if(RString.isNotEmpty(getOutputFile())){
            config.set(PTY_OUTPUT_FILE, getOutputFile());
         }
         if(RString.isNotEmpty(getOutputPath())){
            config.set(PTY_OUTPUT_PATH, getOutputPath());
         }
         if(RString.isNotEmpty(getTemplateName())){
            config.set(PTY_TEMPLATE_NAME, getTemplateName());
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
         if(RString.isNotEmpty(getProcessInvoke())){
            config.set(PTY_PROCESS_INVOKE, getProcessInvoke());
         }
         if(RString.isNotEmpty(getProcessPath())){
            config.set(PTY_PROCESS_PATH, getProcessPath());
         }
         if(RString.isNotEmpty(getOutputFile())){
            config.set(PTY_OUTPUT_FILE, getOutputFile());
         }
         if(RString.isNotEmpty(getOutputPath())){
            config.set(PTY_OUTPUT_PATH, getOutputPath());
         }
         if(RString.isNotEmpty(getTemplateName())){
            config.set(PTY_TEMPLATE_NAME, getTemplateName());
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
         String sProcessInvoke = getProcessInvoke();
         if(RString.isNotEmpty(sProcessInvoke)){
            config.set(PTY_PROCESS_INVOKE, sProcessInvoke);
         }
         String sProcessPath = getProcessPath();
         if(RString.isNotEmpty(sProcessPath)){
            config.set(PTY_PROCESS_PATH, sProcessPath);
         }
         String sOutputFile = getOutputFile();
         if(RString.isNotEmpty(sOutputFile)){
            config.set(PTY_OUTPUT_FILE, sOutputFile);
         }
         String sOutputPath = getOutputPath();
         if(RString.isNotEmpty(sOutputPath)){
            config.set(PTY_OUTPUT_PATH, sOutputPath);
         }
         String sTemplateName = getTemplateName();
         if(RString.isNotEmpty(sTemplateName)){
            config.set(PTY_TEMPLATE_NAME, sTemplateName);
         }
      }else if(EXmlConfig.Default == type){
         String sProcessInvoke = getProcessInvoke();
         if(RString.isNotEmpty(sProcessInvoke)){
            config.set(PTY_PROCESS_INVOKE, sProcessInvoke);
         }
         String sProcessPath = getProcessPath();
         if(RString.isNotEmpty(sProcessPath)){
            config.set(PTY_PROCESS_PATH, sProcessPath);
         }
         String sOutputFile = getOutputFile();
         if(RString.isNotEmpty(sOutputFile)){
            config.set(PTY_OUTPUT_FILE, sOutputFile);
         }
         String sOutputPath = getOutputPath();
         if(RString.isNotEmpty(sOutputPath)){
            config.set(PTY_OUTPUT_PATH, sOutputPath);
         }
         String sTemplateName = getTemplateName();
         if(RString.isNotEmpty(sTemplateName)){
            config.set(PTY_TEMPLATE_NAME, sTemplateName);
         }
      }
   }
}