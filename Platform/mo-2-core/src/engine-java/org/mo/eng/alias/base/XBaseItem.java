package org.mo.eng.alias.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.eng.alias.common.XObject;

//============================================================
// <T>对象的XML节点基类。</T>
//============================================================
public abstract class XBaseItem
      extends FXmlObject
      implements
         XObject
{
   // 名称定义
   public static final String NAME = "Item";

   //============================================================
   // <T>获得名称定义。</T>
   //
   // @return 名称定义
   //============================================================
   @Override
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
   public static final String PTY_NOTE = "note";

   // 的名称定义
   public static final String PTY_TYPE = "type";

   // 的名称定义
   public static final String PTY_SOURCE = "source";

   // 的定义
   @AName("name")
   protected String _name;

   // 的定义
   @AName("note")
   protected FMultiString _note = new FMultiString();

   // 的定义
   @AName("type")
   protected String _type;

   // 的定义
   @AName("source")
   protected String _source;

   //============================================================
   // <T>获得的内容。</T>
   //
   // @return 
   //============================================================
   @Override
   public String getName(){
      return _name;
   }

   //============================================================
   // <T>设置的内容。</T>
   //
   // @param value 
   //============================================================
   @Override
   public void setName(String value){
      _name = value;
   }

   //============================================================
   // <T>获得的内容。</T>
   //
   // @return 
   //============================================================
   @Override
   public String getNote(){
      return _note.get();
   }

   //============================================================
   // <T>设置的内容。</T>
   //
   // @param value 
   //============================================================
   @Override
   public void setNote(String value){
      _note.set(value);
   }

   //============================================================
   // <T>获得的内容。</T>
   //
   // @return 
   //============================================================
   public String getType(){
      return _type;
   }

   //============================================================
   // <T>设置的内容。</T>
   //
   // @param value 
   //============================================================
   public void setType(String value){
      _type = value;
   }

   //============================================================
   // <T>获得的内容。</T>
   //
   // @return 
   //============================================================
   public String getSource(){
      return _source;
   }

   //============================================================
   // <T>设置的内容。</T>
   //
   // @param value 
   //============================================================
   public void setSource(String value){
      _source = value;
   }

   //============================================================
   // <T>内部获得内容置信息。</T>
   //
   // @param name 名称
   // @return 内容
   //============================================================
   @Override
   public String innerGet(String name){
      if(RString.isEmpty(name)){
         return null;
      }else if(PTY_NAME.equalsIgnoreCase(name)){
         return getName();
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         return getNote();
      }else if(PTY_TYPE.equalsIgnoreCase(name)){
         return getType();
      }else if(PTY_SOURCE.equalsIgnoreCase(name)){
         return getSource();
      }
      return null;
   }

   //============================================================
   // <T>内部设置内容置信息。</T>
   //
   // @param name 名称
   // @param value 内容
   //============================================================
   @Override
   public void innerSet(String name,
                        String value){
      if(RString.isEmpty(name)){
         return;
      }else if(PTY_NAME.equalsIgnoreCase(name)){
         setName(value);
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         setNote(value);
      }else if(PTY_TYPE.equalsIgnoreCase(name)){
         setType(value);
      }else if(PTY_SOURCE.equalsIgnoreCase(name)){
         setSource(value);
      }
   }

   //============================================================
   // <T>加载设置信息。</T>
   //
   // @param config 设置信息
   // @param type 类型
   //============================================================
   @Override
   public void loadConfig(FXmlNode config,
                          EXmlConfig type){
      super.loadConfig(config, type);
      if(EXmlConfig.Full == type){
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("note")){
            _note.unpack(config.get(PTY_NOTE));
         }
      }else if(EXmlConfig.Simple == type){
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("note")){
            setNote(config.get(PTY_NOTE));
         }
      }else if(EXmlConfig.Value == type){
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("note")){
            setNote(config.get(PTY_NOTE));
         }
         if(config.contains("type")){
            setType(config.get(PTY_TYPE));
         }
         if(config.contains("source")){
            setSource(config.get(PTY_SOURCE));
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
   @Override
   public void saveConfig(FXmlNode config,
                          EXmlConfig type){
      config.setName(NAME);
      super.saveConfig(config, type);
      if(EXmlConfig.Full == type){
         if(RString.isNotEmpty(getName())){
            config.set(PTY_NAME, getName());
         }
         String note = _note.pack().toString();
         if(RString.isNotEmpty(note)){
            config.set(PTY_NOTE, note);
         }
      }else if(EXmlConfig.Simple == type){
         if(RString.isNotEmpty(getName())){
            config.set(PTY_NAME, getName());
         }
         if(RString.isNotEmpty(getNote())){
            config.set(PTY_NOTE, getNote());
         }
      }else if(EXmlConfig.Value == type){
         String sName = getName();
         if(RString.isNotEmpty(sName)){
            config.set(PTY_NAME, sName);
         }
         String sNote = getNote();
         if(RString.isNotEmpty(sNote)){
            config.set(PTY_NOTE, sNote);
         }
         String sType = getType();
         if(RString.isNotEmpty(sType)){
            config.set(PTY_TYPE, sType);
         }
         String sSource = getSource();
         if(RString.isNotEmpty(sSource)){
            config.set(PTY_SOURCE, sSource);
         }
      }else if(EXmlConfig.Default == type){
      }
   }
}
