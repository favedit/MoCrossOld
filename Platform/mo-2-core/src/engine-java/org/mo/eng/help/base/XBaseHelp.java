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
// <T>帮助对象的XML节点基类。</T>
//============================================================
public abstract class XBaseHelp
      extends FXmlObject
      implements
         XObject
{
   // 名称定义
   public static final String NAME = "Help";

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

   // 处理接口的名称定义
   public static final String PTY_PROCESS_FACE = "process_face";

   // 文字编码的名称定义
   public static final String PTY_PROCESS_ENCODING = "process_encoding";

   // 输出根路径的名称定义
   public static final String PTY_OUTPUT_ROOT = "output_root";

   // 输出路径的名称定义
   public static final String PTY_OUTPUT_PATH = "output_path";

   // 项目模板的名称定义
   public static final String PTY_TEMPLATE_PROJECT = "template_project";

   // 索引模板的名称定义
   public static final String PTY_TEMPLATE_INDEX = "template_index";

   // 目录模板的名称定义
   public static final String PTY_TEMPLATE_CATALOG = "template_catalog";

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

   // 处理接口的定义
   @AName("process_face")
   protected String _processFace;

   // 文字编码的定义
   @AName("process_encoding")
   protected String _processEncoding;

   // 输出根路径的定义
   @AName("output_root")
   protected String _outputRoot;

   // 输出路径的定义
   @AName("output_path")
   protected String _outputPath;

   // 项目模板的定义
   @AName("template_project")
   protected String _templateProject;

   // 索引模板的定义
   @AName("template_index")
   protected String _templateIndex;

   // 目录模板的定义
   @AName("template_catalog")
   protected String _templateCatalog;

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
   // <T>获得处理接口的内容。</T>
   //
   // @return 处理接口
   //============================================================
   public String getProcessFace(){
      return _processFace;
   }

   //============================================================
   // <T>设置处理接口的内容。</T>
   //
   // @param value 处理接口
   //============================================================
   public void setProcessFace(String value){
      _processFace = value;
   }

   //============================================================
   // <T>获得文字编码的内容。</T>
   //
   // @return 文字编码
   //============================================================
   public String getProcessEncoding(){
      return _processEncoding;
   }

   //============================================================
   // <T>设置文字编码的内容。</T>
   //
   // @param value 文字编码
   //============================================================
   public void setProcessEncoding(String value){
      _processEncoding = value;
   }

   //============================================================
   // <T>获得输出根路径的内容。</T>
   //
   // @return 输出根路径
   //============================================================
   public String getOutputRoot(){
      return _outputRoot;
   }

   //============================================================
   // <T>设置输出根路径的内容。</T>
   //
   // @param value 输出根路径
   //============================================================
   public void setOutputRoot(String value){
      _outputRoot = value;
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
   // <T>获得项目模板的内容。</T>
   //
   // @return 项目模板
   //============================================================
   public String getTemplateProject(){
      return _templateProject;
   }

   //============================================================
   // <T>设置项目模板的内容。</T>
   //
   // @param value 项目模板
   //============================================================
   public void setTemplateProject(String value){
      _templateProject = value;
   }

   //============================================================
   // <T>获得索引模板的内容。</T>
   //
   // @return 索引模板
   //============================================================
   public String getTemplateIndex(){
      return _templateIndex;
   }

   //============================================================
   // <T>设置索引模板的内容。</T>
   //
   // @param value 索引模板
   //============================================================
   public void setTemplateIndex(String value){
      _templateIndex = value;
   }

   //============================================================
   // <T>获得目录模板的内容。</T>
   //
   // @return 目录模板
   //============================================================
   public String getTemplateCatalog(){
      return _templateCatalog;
   }

   //============================================================
   // <T>设置目录模板的内容。</T>
   //
   // @param value 目录模板
   //============================================================
   public void setTemplateCatalog(String value){
      _templateCatalog = value;
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
      }else if(PTY_PROCESS_FACE.equalsIgnoreCase(name)){
         return getProcessFace();
      }else if(PTY_PROCESS_ENCODING.equalsIgnoreCase(name)){
         return getProcessEncoding();
      }else if(PTY_OUTPUT_ROOT.equalsIgnoreCase(name)){
         return getOutputRoot();
      }else if(PTY_OUTPUT_PATH.equalsIgnoreCase(name)){
         return getOutputPath();
      }else if(PTY_TEMPLATE_PROJECT.equalsIgnoreCase(name)){
         return getTemplateProject();
      }else if(PTY_TEMPLATE_INDEX.equalsIgnoreCase(name)){
         return getTemplateIndex();
      }else if(PTY_TEMPLATE_CATALOG.equalsIgnoreCase(name)){
         return getTemplateCatalog();
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
      }else if(PTY_PROCESS_FACE.equalsIgnoreCase(name)){
         setProcessFace(value);
      }else if(PTY_PROCESS_ENCODING.equalsIgnoreCase(name)){
         setProcessEncoding(value);
      }else if(PTY_OUTPUT_ROOT.equalsIgnoreCase(name)){
         setOutputRoot(value);
      }else if(PTY_OUTPUT_PATH.equalsIgnoreCase(name)){
         setOutputPath(value);
      }else if(PTY_TEMPLATE_PROJECT.equalsIgnoreCase(name)){
         setTemplateProject(value);
      }else if(PTY_TEMPLATE_INDEX.equalsIgnoreCase(name)){
         setTemplateIndex(value);
      }else if(PTY_TEMPLATE_CATALOG.equalsIgnoreCase(name)){
         setTemplateCatalog(value);
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
         if(config.contains("process_face")){
            setProcessFace(config.get(PTY_PROCESS_FACE));
         }
         if(config.contains("process_encoding")){
            setProcessEncoding(config.get(PTY_PROCESS_ENCODING));
         }
         if(config.contains("output_root")){
            setOutputRoot(config.get(PTY_OUTPUT_ROOT));
         }
         if(config.contains("output_path")){
            setOutputPath(config.get(PTY_OUTPUT_PATH));
         }
         if(config.contains("template_project")){
            setTemplateProject(config.get(PTY_TEMPLATE_PROJECT));
         }
         if(config.contains("template_index")){
            setTemplateIndex(config.get(PTY_TEMPLATE_INDEX));
         }
         if(config.contains("template_catalog")){
            setTemplateCatalog(config.get(PTY_TEMPLATE_CATALOG));
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
         if(config.contains("process_face")){
            setProcessFace(config.get(PTY_PROCESS_FACE));
         }
         if(config.contains("process_encoding")){
            setProcessEncoding(config.get(PTY_PROCESS_ENCODING));
         }
         if(config.contains("output_root")){
            setOutputRoot(config.get(PTY_OUTPUT_ROOT));
         }
         if(config.contains("output_path")){
            setOutputPath(config.get(PTY_OUTPUT_PATH));
         }
         if(config.contains("template_project")){
            setTemplateProject(config.get(PTY_TEMPLATE_PROJECT));
         }
         if(config.contains("template_index")){
            setTemplateIndex(config.get(PTY_TEMPLATE_INDEX));
         }
         if(config.contains("template_catalog")){
            setTemplateCatalog(config.get(PTY_TEMPLATE_CATALOG));
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
         if(config.contains("process_face")){
            setProcessFace(config.get(PTY_PROCESS_FACE));
         }
         if(config.contains("process_encoding")){
            setProcessEncoding(config.get(PTY_PROCESS_ENCODING));
         }
         if(config.contains("output_root")){
            setOutputRoot(config.get(PTY_OUTPUT_ROOT));
         }
         if(config.contains("output_path")){
            setOutputPath(config.get(PTY_OUTPUT_PATH));
         }
         if(config.contains("template_project")){
            setTemplateProject(config.get(PTY_TEMPLATE_PROJECT));
         }
         if(config.contains("template_index")){
            setTemplateIndex(config.get(PTY_TEMPLATE_INDEX));
         }
         if(config.contains("template_catalog")){
            setTemplateCatalog(config.get(PTY_TEMPLATE_CATALOG));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getProcessFace())){
            if(config.contains("process_face")){
               setProcessFace(config.get(PTY_PROCESS_FACE));
            }
         }
         if(RString.isEmpty(getProcessEncoding())){
            if(config.contains("process_encoding")){
               setProcessEncoding(config.get(PTY_PROCESS_ENCODING));
            }
         }
         if(RString.isEmpty(getOutputRoot())){
            if(config.contains("output_root")){
               setOutputRoot(config.get(PTY_OUTPUT_ROOT));
            }
         }
         if(RString.isEmpty(getOutputPath())){
            if(config.contains("output_path")){
               setOutputPath(config.get(PTY_OUTPUT_PATH));
            }
         }
         if(RString.isEmpty(getTemplateProject())){
            if(config.contains("template_project")){
               setTemplateProject(config.get(PTY_TEMPLATE_PROJECT));
            }
         }
         if(RString.isEmpty(getTemplateIndex())){
            if(config.contains("template_index")){
               setTemplateIndex(config.get(PTY_TEMPLATE_INDEX));
            }
         }
         if(RString.isEmpty(getTemplateCatalog())){
            if(config.contains("template_catalog")){
               setTemplateCatalog(config.get(PTY_TEMPLATE_CATALOG));
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
         if(RString.isNotEmpty(getProcessFace())){
            config.set(PTY_PROCESS_FACE, getProcessFace());
         }
         if(RString.isNotEmpty(getProcessEncoding())){
            config.set(PTY_PROCESS_ENCODING, getProcessEncoding());
         }
         if(RString.isNotEmpty(getOutputRoot())){
            config.set(PTY_OUTPUT_ROOT, getOutputRoot());
         }
         if(RString.isNotEmpty(getOutputPath())){
            config.set(PTY_OUTPUT_PATH, getOutputPath());
         }
         if(RString.isNotEmpty(getTemplateProject())){
            config.set(PTY_TEMPLATE_PROJECT, getTemplateProject());
         }
         if(RString.isNotEmpty(getTemplateIndex())){
            config.set(PTY_TEMPLATE_INDEX, getTemplateIndex());
         }
         if(RString.isNotEmpty(getTemplateCatalog())){
            config.set(PTY_TEMPLATE_CATALOG, getTemplateCatalog());
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
         if(RString.isNotEmpty(getProcessFace())){
            config.set(PTY_PROCESS_FACE, getProcessFace());
         }
         if(RString.isNotEmpty(getProcessEncoding())){
            config.set(PTY_PROCESS_ENCODING, getProcessEncoding());
         }
         if(RString.isNotEmpty(getOutputRoot())){
            config.set(PTY_OUTPUT_ROOT, getOutputRoot());
         }
         if(RString.isNotEmpty(getOutputPath())){
            config.set(PTY_OUTPUT_PATH, getOutputPath());
         }
         if(RString.isNotEmpty(getTemplateProject())){
            config.set(PTY_TEMPLATE_PROJECT, getTemplateProject());
         }
         if(RString.isNotEmpty(getTemplateIndex())){
            config.set(PTY_TEMPLATE_INDEX, getTemplateIndex());
         }
         if(RString.isNotEmpty(getTemplateCatalog())){
            config.set(PTY_TEMPLATE_CATALOG, getTemplateCatalog());
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
         String sProcessFace = getProcessFace();
         if(RString.isNotEmpty(sProcessFace)){
            config.set(PTY_PROCESS_FACE, sProcessFace);
         }
         String sProcessEncoding = getProcessEncoding();
         if(RString.isNotEmpty(sProcessEncoding)){
            config.set(PTY_PROCESS_ENCODING, sProcessEncoding);
         }
         String sOutputRoot = getOutputRoot();
         if(RString.isNotEmpty(sOutputRoot)){
            config.set(PTY_OUTPUT_ROOT, sOutputRoot);
         }
         String sOutputPath = getOutputPath();
         if(RString.isNotEmpty(sOutputPath)){
            config.set(PTY_OUTPUT_PATH, sOutputPath);
         }
         String sTemplateProject = getTemplateProject();
         if(RString.isNotEmpty(sTemplateProject)){
            config.set(PTY_TEMPLATE_PROJECT, sTemplateProject);
         }
         String sTemplateIndex = getTemplateIndex();
         if(RString.isNotEmpty(sTemplateIndex)){
            config.set(PTY_TEMPLATE_INDEX, sTemplateIndex);
         }
         String sTemplateCatalog = getTemplateCatalog();
         if(RString.isNotEmpty(sTemplateCatalog)){
            config.set(PTY_TEMPLATE_CATALOG, sTemplateCatalog);
         }
      }else if(EXmlConfig.Default == type){
         String sProcessFace = getProcessFace();
         if(RString.isNotEmpty(sProcessFace)){
            config.set(PTY_PROCESS_FACE, sProcessFace);
         }
         String sProcessEncoding = getProcessEncoding();
         if(RString.isNotEmpty(sProcessEncoding)){
            config.set(PTY_PROCESS_ENCODING, sProcessEncoding);
         }
         String sOutputRoot = getOutputRoot();
         if(RString.isNotEmpty(sOutputRoot)){
            config.set(PTY_OUTPUT_ROOT, sOutputRoot);
         }
         String sOutputPath = getOutputPath();
         if(RString.isNotEmpty(sOutputPath)){
            config.set(PTY_OUTPUT_PATH, sOutputPath);
         }
         String sTemplateProject = getTemplateProject();
         if(RString.isNotEmpty(sTemplateProject)){
            config.set(PTY_TEMPLATE_PROJECT, sTemplateProject);
         }
         String sTemplateIndex = getTemplateIndex();
         if(RString.isNotEmpty(sTemplateIndex)){
            config.set(PTY_TEMPLATE_INDEX, sTemplateIndex);
         }
         String sTemplateCatalog = getTemplateCatalog();
         if(RString.isNotEmpty(sTemplateCatalog)){
            config.set(PTY_TEMPLATE_CATALOG, sTemplateCatalog);
         }
      }
   }
}