package org.mo.com.data;

import org.mo.com.lang.FObject;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.IStringNamed;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.xml.FXmlNode;

//============================================================
// <T>数据字段信息。</T>
//============================================================
public class FSqlField
      extends FObject
      implements
         IStringNamed
{
   // 数据名称
   protected String _name;

   // 是否为主键
   protected boolean _isKey;

   // 是否允许为空
   protected boolean _isNull;

   // 数据类型
   protected ESqlDataType _typeCd = ESqlDataType.Unknown;

   // 数据大小
   protected int _size;

   // 数据默认值
   protected String _defaultValue;

   // 注释内容
   protected String _note;

   //============================================================
   // <T>构造数据字段信息。</T>
   //============================================================
   public FSqlField(){
   }

   //============================================================
   // <T>构造数据字段信息。</T>
   //
   // @param name 字段名称
   //============================================================
   public FSqlField(String name){
      _name = name;
   }

   //============================================================
   // <T>获得名称。</T>
   //
   // @return 名称
   //============================================================
   @Override
   public String name(){
      return _name;
   }

   //============================================================
   // <T>设置名称。</T>
   //
   // @param name 名称
   //============================================================
   public void setName(String name){
      _name = name;
   }

   //============================================================
   // <T>获得主键标志。</T>
   //
   // @return 主键标志
   //============================================================
   public boolean isKey(){
      return _isKey;
   }

   //============================================================
   // <T>设置主键标志。</T>
   //
   // @param isKey 主键标志
   //============================================================
   public void setIsKey(boolean isKey){
      _isKey = isKey;
   }

   //============================================================
   // <T>获得为空标志。</T>
   //
   // @return 为空标志
   //============================================================
   public boolean isNull(){
      return _isNull;
   }

   //============================================================
   // <T>设置为空标志。</T>
   //
   // @param isNull 为空标志
   //============================================================
   public void setIsNull(boolean isNull){
      _isNull = isNull;
   }

   //============================================================
   // <T>获得类型。</T>
   //
   // @return 类型
   //============================================================
   public ESqlDataType typeCd(){
      return _typeCd;
   }

   //============================================================
   // <T>设置类型。</T>
   //
   // @param typeCd 类型
   //============================================================
   public void setType(ESqlDataType typeCd){
      _typeCd = typeCd;
   }

   //============================================================
   // <T>获得长度。</T>
   //
   // @return 长度
   //============================================================
   public int size(){
      return _size;
   }

   //============================================================
   // <T>设置长度。</T>
   //
   // @param size 长度
   //============================================================
   public void setSize(int size){
      _size = size;
   }

   //============================================================
   // <T>获得默认值。</T>
   //
   // @return 默认值
   //============================================================
   public String defaultValue(){
      return _defaultValue;
   }

   //============================================================
   // <T>设置默认值。</T>
   //
   // @param dataDefault 默认值
   //============================================================
   public void setDefaultValue(String defaultValue){
      _defaultValue = defaultValue;
   }

   //============================================================
   // <T>获得注释。</T>
   //
   // @return 注释
   //============================================================
   public String note(){
      return _note;
   }

   //============================================================
   // <T>设置注释。</T>
   //
   // @param note 注释
   //============================================================
   public void setNote(String note){
      _note = note;
   }

   //============================================================
   // <T>加载设置信息。</T>
   //
   // @param config 设置信息
   //============================================================
   public void loadConfig(FXmlNode xconfig){
      if(xconfig.contains("name")){
         _name = xconfig.get("name");
      }
      if(xconfig.contains("size")){
         _size = RInteger.parse(xconfig.get("size"));
      }
      if(xconfig.contains("note")){
         _note = xconfig.get("note");
      }
   }

   //============================================================
   // <T>存储设置信息。</T>
   //
   // @param config 设置信息
   //============================================================
   public void saveConfig(FXmlNode xconfig){
      xconfig.set("name", _name);
      xconfig.set("is_key", RBoolean.toString(_isKey));
      xconfig.set("is_null", RBoolean.toString(_isNull));
      if(ESqlDataType.Integer == _typeCd){
         xconfig.set("type", "int");
      }else if(ESqlDataType.String == _typeCd){
         xconfig.set("type", "string");
      }else if(ESqlDataType.DateTime == _typeCd){
         xconfig.set("type", "date");
      }
      xconfig.set("size", Integer.toString(_size));
      xconfig.set("note", _note);
   }

   //============================================================
   // <T>生成运行信息。</T>
   //
   // @param info 运行信息
   // @return 运行信息
   //============================================================
   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.dump(info, this);
      info.append(" [ " + RString.rightPad(name(), 40) + " ");
      info.append(isKey() ? "K" : "_");
      info.append(isNull() ? "_" : "N");
      info.append(" Type:" + _typeCd);
      info.append(" Size:" + _size);
      info.append(" Default:" + _defaultValue);
      info.append(" ]");
      return info;
   }
}
