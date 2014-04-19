package org.mo.com.data;

import org.mo.com.lang.FObject;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.IStringNamed;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlConfig;

//============================================================
// <T>数据表格。</T>
//============================================================
public class FSqlTable
      extends FObject
      implements
         IStringNamed,
         IXmlConfig
{
   // 名称
   protected String _name;

   // 字段集合
   protected FSqlFields _fields = new FSqlFields();

   //============================================================
   // <T>构造数据表格。</T>
   //============================================================
   public FSqlTable(){
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
   // <T>获得字段集合。</T>
   //
   // @return 字段集合
   //============================================================
   public FSqlFields fields(){
      return _fields;
   }

   //============================================================
   // <T>增加一个字段。</T>
   //
   // @param field 字段
   //============================================================
   public void push(FSqlField field){
      _fields.push(field);
   }

   //============================================================
   // <T>从设置节点中加载设置信息。</T>
   //
   // @param xconfig 设置节点
   //============================================================
   @Override
   public void loadConfig(FXmlNode xconfig){
      if(xconfig.contains("name")){
         _name = xconfig.get("name");
      }
   }

   //============================================================
   // <T>保存设置信息到设置节点中。</T>
   //
   // @param xconfig 设置节点
   //============================================================
   @Override
   public void saveConfig(FXmlNode xconfig){
      xconfig.set("name", _name);
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
      info.append(" ]");
      return info;
   }
}
