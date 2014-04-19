package org.mo.data.datainfo.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.data.datainfo.common.XObject;

//============================================================
// <T>对象的XML节点基类。</T>
//============================================================
public abstract class XBasePackage
      extends FXmlObject
      implements
         XObject
{
   // 名称定义
   public static final String NAME = "Package";

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

   // 的名称定义
   public static final String PTY_NAME = "name";

   // 的名称定义
   public static final String PTY_LABEL = "label";

   // 的名称定义
   public static final String PTY_NOTE = "note";

   // 的名称定义
   public static final String PTY_DATA_NAME = "data_name";

   // 的名称定义
   public static final String PTY_LOGIC = "logic";

   // 的名称定义
   public static final String PTY_EDIT_INSERT = "edit_insert";

   // 的名称定义
   public static final String PTY_EDIT_UPDATE = "edit_update";

   // 的名称定义
   public static final String PTY_EDIT_DELETE = "edit_delete";

   // 的定义
   @AName("name")
   protected String _name;

   // 的定义
   @AName("label")
   protected String _label;

   // 的定义
   @AName("note")
   protected FMultiString _note = new FMultiString();

   // 的定义
   @AName("data_name")
   protected String _dataName;

   // 的定义
   @AName("logic")
   protected String _logic;

   // 的定义
   @AName("edit_insert")
   protected Boolean _editInsert = Boolean.FALSE;

   // 的定义
   @AName("edit_update")
   protected Boolean _editUpdate = Boolean.FALSE;

   // 的定义
   @AName("edit_delete")
   protected Boolean _editDelete = Boolean.FALSE;

   //============================================================
   // <T>获得的内容。</T>
   //
   // @return 
   //============================================================
   public String getName(){
      return _name;
   }

   //============================================================
   // <T>设置的内容。</T>
   //
   // @param value 
   //============================================================
   public void setName(String value){
      _name = value;
   }

   //============================================================
   // <T>获得的内容。</T>
   //
   // @return 
   //============================================================
   public String getLabel(){
      return _label;
   }

   //============================================================
   // <T>设置的内容。</T>
   //
   // @param value 
   //============================================================
   public void setLabel(String value){
      _label = value;
   }

   //============================================================
   // <T>获得的内容。</T>
   //
   // @return 
   //============================================================
   public String getNote(){
      return _note.get();
   }

   //============================================================
   // <T>设置的内容。</T>
   //
   // @param value 
   //============================================================
   public void setNote(String value){
      _note.set(value);
   }

   //============================================================
   // <T>获得的内容。</T>
   //
   // @return 
   //============================================================
   public String getDataName(){
      return _dataName;
   }

   //============================================================
   // <T>设置的内容。</T>
   //
   // @param value 
   //============================================================
   public void setDataName(String value){
      _dataName = value;
   }

   //============================================================
   // <T>获得的内容。</T>
   //
   // @return 
   //============================================================
   public String getLogic(){
      return _logic;
   }

   //============================================================
   // <T>设置的内容。</T>
   //
   // @param value 
   //============================================================
   public void setLogic(String value){
      _logic = value;
   }

   //============================================================
   // <T>获得的内容。</T>
   //
   // @return 
   //============================================================
   public Boolean getEditInsert(){
      return _editInsert;
   }

   //============================================================
   // <T>设置的内容。</T>
   //
   // @param value 
   //============================================================
   public void setEditInsert(Boolean value){
      _editInsert = value;
   }

   //============================================================
   // <T>获得的内容。</T>
   //
   // @return 
   //============================================================
   public Boolean getEditUpdate(){
      return _editUpdate;
   }

   //============================================================
   // <T>设置的内容。</T>
   //
   // @param value 
   //============================================================
   public void setEditUpdate(Boolean value){
      _editUpdate = value;
   }

   //============================================================
   // <T>获得的内容。</T>
   //
   // @return 
   //============================================================
   public Boolean getEditDelete(){
      return _editDelete;
   }

   //============================================================
   // <T>设置的内容。</T>
   //
   // @param value 
   //============================================================
   public void setEditDelete(Boolean value){
      _editDelete = value;
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
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         return getNote();
      }else if(PTY_DATA_NAME.equalsIgnoreCase(name)){
         return getDataName();
      }else if(PTY_LOGIC.equalsIgnoreCase(name)){
         return getLogic();
      }else if(PTY_EDIT_INSERT.equalsIgnoreCase(name)){
         return RBoolean.toString(getEditInsert());
      }else if(PTY_EDIT_UPDATE.equalsIgnoreCase(name)){
         return RBoolean.toString(getEditUpdate());
      }else if(PTY_EDIT_DELETE.equalsIgnoreCase(name)){
         return RBoolean.toString(getEditDelete());
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
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         setNote(value);
      }else if(PTY_DATA_NAME.equalsIgnoreCase(name)){
         setDataName(value);
      }else if(PTY_LOGIC.equalsIgnoreCase(name)){
         setLogic(value);
      }else if(PTY_EDIT_INSERT.equalsIgnoreCase(name)){
         setEditInsert(RBoolean.parse(value));
      }else if(PTY_EDIT_UPDATE.equalsIgnoreCase(name)){
         setEditUpdate(RBoolean.parse(value));
      }else if(PTY_EDIT_DELETE.equalsIgnoreCase(name)){
         setEditDelete(RBoolean.parse(value));
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
         if(config.contains("note")){
            _note.unpack(config.get(PTY_NOTE));
         }
         if(config.contains("data_name")){
            setDataName(config.get(PTY_DATA_NAME));
         }
         if(config.contains("logic")){
            setLogic(config.get(PTY_LOGIC));
         }
         if(config.contains("edit_insert")){
            setEditInsert(RBoolean.parse(config.get(PTY_EDIT_INSERT)));
         }
         if(config.contains("edit_update")){
            setEditUpdate(RBoolean.parse(config.get(PTY_EDIT_UPDATE)));
         }
         if(config.contains("edit_delete")){
            setEditDelete(RBoolean.parse(config.get(PTY_EDIT_DELETE)));
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
         if(config.contains("data_name")){
            setDataName(config.get(PTY_DATA_NAME));
         }
         if(config.contains("logic")){
            setLogic(config.get(PTY_LOGIC));
         }
         if(config.contains("edit_insert")){
            setEditInsert(RBoolean.parse(config.get(PTY_EDIT_INSERT)));
         }
         if(config.contains("edit_update")){
            setEditUpdate(RBoolean.parse(config.get(PTY_EDIT_UPDATE)));
         }
         if(config.contains("edit_delete")){
            setEditDelete(RBoolean.parse(config.get(PTY_EDIT_DELETE)));
         }
      }else if(EXmlConfig.Value == type){
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("label")){
            setLabel(config.get(PTY_LABEL));
         }
         if(config.contains("note")){
            setNote(config.get(PTY_NOTE));
         }
         if(config.contains("data_name")){
            setDataName(config.get(PTY_DATA_NAME));
         }
         if(config.contains("logic")){
            setLogic(config.get(PTY_LOGIC));
         }
         if(config.contains("edit_insert")){
            setEditInsert(RBoolean.parse(config.get(PTY_EDIT_INSERT)));
         }
         if(config.contains("edit_update")){
            setEditUpdate(RBoolean.parse(config.get(PTY_EDIT_UPDATE)));
         }
         if(config.contains("edit_delete")){
            setEditDelete(RBoolean.parse(config.get(PTY_EDIT_DELETE)));
         }
      }else if(EXmlConfig.Default == type){
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
         String note = _note.pack().toString();
         if(RString.isNotEmpty(note)){
            config.set(PTY_NOTE, note);
         }
         if(RString.isNotEmpty(getDataName())){
            config.set(PTY_DATA_NAME, getDataName());
         }
         if(RString.isNotEmpty(getLogic())){
            config.set(PTY_LOGIC, getLogic());
         }
         if(RBoolean.parse(getEditInsert())){
            config.set(PTY_EDIT_INSERT, RBoolean.toString(getEditInsert()));
         }
         if(RBoolean.parse(getEditUpdate())){
            config.set(PTY_EDIT_UPDATE, RBoolean.toString(getEditUpdate()));
         }
         if(RBoolean.parse(getEditDelete())){
            config.set(PTY_EDIT_DELETE, RBoolean.toString(getEditDelete()));
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
         if(RString.isNotEmpty(getDataName())){
            config.set(PTY_DATA_NAME, getDataName());
         }
         if(RString.isNotEmpty(getLogic())){
            config.set(PTY_LOGIC, getLogic());
         }
         if(RBoolean.parse(getEditInsert())){
            config.set(PTY_EDIT_INSERT, RBoolean.toString(getEditInsert()));
         }
         if(RBoolean.parse(getEditUpdate())){
            config.set(PTY_EDIT_UPDATE, RBoolean.toString(getEditUpdate()));
         }
         if(RBoolean.parse(getEditDelete())){
            config.set(PTY_EDIT_DELETE, RBoolean.toString(getEditDelete()));
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
         String sNote = getNote();
         if(RString.isNotEmpty(sNote)){
            config.set(PTY_NOTE, sNote);
         }
         String sDataName = getDataName();
         if(RString.isNotEmpty(sDataName)){
            config.set(PTY_DATA_NAME, sDataName);
         }
         String sLogic = getLogic();
         if(RString.isNotEmpty(sLogic)){
            config.set(PTY_LOGIC, sLogic);
         }
         Boolean bEditInsert = getEditInsert();
         if(RBoolean.parse(bEditInsert)){
            config.set(PTY_EDIT_INSERT, RBoolean.toString(bEditInsert));
         }
         Boolean bEditUpdate = getEditUpdate();
         if(RBoolean.parse(bEditUpdate)){
            config.set(PTY_EDIT_UPDATE, RBoolean.toString(bEditUpdate));
         }
         Boolean bEditDelete = getEditDelete();
         if(RBoolean.parse(bEditDelete)){
            config.set(PTY_EDIT_DELETE, RBoolean.toString(bEditDelete));
         }
      }else if(EXmlConfig.Default == type){
      }
   }
}