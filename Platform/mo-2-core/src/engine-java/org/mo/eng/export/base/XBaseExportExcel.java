package org.mo.eng.export.base;

import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.eng.export.common.XObjectFace;

//============================================================
// <T>导出Excel对象的XML节点基类。</T>
//============================================================
public abstract class XBaseExportExcel
      extends FXmlObject
      implements
         XObjectFace
{
   // 名称定义
   public static final String NAME = "ExportExcel";

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

   // 列代码的名称定义
   public static final String PTY_HIDE_CODE = "hide_code";

   // 文件名称的名称定义
   public static final String PTY_FILENAME = "fileName";

   // 模板的名称定义
   public static final String PTY_TEMPLATE = "template";

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

   // 列代码的定义
   @AName("hide_code")
   protected String _hideCode;

   // 文件名称的定义
   @AName("filename")
   protected String _filename;

   // 模板的定义
   @AName("template")
   protected String _template;

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
   // <T>获得列代码的内容。</T>
   //
   // @return 列代码
   //============================================================
   public String getHideCode(){
      return _hideCode;
   }

   //============================================================
   // <T>设置列代码的内容。</T>
   //
   // @param value 列代码
   //============================================================
   public void setHideCode(String value){
      _hideCode = value;
   }

   //============================================================
   // <T>获得文件名称的内容。</T>
   //
   // @return 文件名称
   //============================================================
   public String getFilename(){
      return _filename;
   }

   //============================================================
   // <T>设置文件名称的内容。</T>
   //
   // @param value 文件名称
   //============================================================
   public void setFilename(String value){
      _filename = value;
   }

   //============================================================
   // <T>获得模板的内容。</T>
   //
   // @return 模板
   //============================================================
   public String getTemplate(){
      return _template;
   }

   //============================================================
   // <T>设置模板的内容。</T>
   //
   // @param value 模板
   //============================================================
   public void setTemplate(String value){
      _template = value;
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
      }else if(PTY_HIDE_CODE.equalsIgnoreCase(name)){
         return getHideCode();
      }else if(PTY_FILENAME.equalsIgnoreCase(name)){
         return getFilename();
      }else if(PTY_TEMPLATE.equalsIgnoreCase(name)){
         return getTemplate();
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
      }else if(PTY_HIDE_CODE.equalsIgnoreCase(name)){
         setHideCode(value);
      }else if(PTY_FILENAME.equalsIgnoreCase(name)){
         setFilename(value);
      }else if(PTY_TEMPLATE.equalsIgnoreCase(name)){
         setTemplate(value);
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
         if(config.contains("hide_code")){
            setHideCode(config.get(PTY_HIDE_CODE));
         }
         if(config.contains("filename")){
            setFilename(config.get(PTY_FILENAME));
         }
         if(config.contains("template")){
            setTemplate(config.get(PTY_TEMPLATE));
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
         if(config.contains("hide_code")){
            setHideCode(config.get(PTY_HIDE_CODE));
         }
         if(config.contains("filename")){
            setFilename(config.get(PTY_FILENAME));
         }
         if(config.contains("template")){
            setTemplate(config.get(PTY_TEMPLATE));
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
         if(config.contains("hide_code")){
            setHideCode(config.get(PTY_HIDE_CODE));
         }
         if(config.contains("filename")){
            setFilename(config.get(PTY_FILENAME));
         }
         if(config.contains("template")){
            setTemplate(config.get(PTY_TEMPLATE));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getFilename())){
            if(config.contains("filename")){
               setFilename(config.get(PTY_FILENAME));
            }
         }
         if(RString.isEmpty(getTemplate())){
            if(config.contains("template")){
               setTemplate(config.get(PTY_TEMPLATE));
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
         if(RString.isNotEmpty(getHideCode())){
            config.set(PTY_HIDE_CODE, getHideCode());
         }
         if(RString.isNotEmpty(getFilename())){
            config.set(PTY_FILENAME, getFilename());
         }
         if(RString.isNotEmpty(getTemplate())){
            config.set(PTY_TEMPLATE, getTemplate());
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
         if(RString.isNotEmpty(getHideCode())){
            config.set(PTY_HIDE_CODE, getHideCode());
         }
         if(RString.isNotEmpty(getFilename())){
            config.set(PTY_FILENAME, getFilename());
         }
         if(RString.isNotEmpty(getTemplate())){
            config.set(PTY_TEMPLATE, getTemplate());
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
         String sHideCode = getHideCode();
         if(RString.isNotEmpty(sHideCode)){
            config.set(PTY_HIDE_CODE, sHideCode);
         }
         String sFilename = getFilename();
         if(RString.isNotEmpty(sFilename)){
            config.set(PTY_FILENAME, sFilename);
         }
         String sTemplate = getTemplate();
         if(RString.isNotEmpty(sTemplate)){
            config.set(PTY_TEMPLATE, sTemplate);
         }
      }else if(EXmlConfig.Default == type){
         String sFilename = getFilename();
         if(RString.isNotEmpty(sFilename)){
            config.set(PTY_FILENAME, sFilename);
         }
         String sTemplate = getTemplate();
         if(RString.isNotEmpty(sTemplate)){
            config.set(PTY_TEMPLATE, sTemplate);
         }
      }
   }
}