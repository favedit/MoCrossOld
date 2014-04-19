package org.mo.eng.persistence.common;

import org.mo.com.lang.FObject;
import org.mo.com.lang.RDump;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;

//============================================================
// <T>持久化配置。</T>
//============================================================
public class FXmlPersistenceConfig
      extends FObject
{
   // 名称
   protected String _name;

   // 类名称
   protected String _className;

   // 类对象
   protected Class<?> _clazz;

   // 配置节点
   protected FXmlNode _config;

   //============================================================
   // <T>构造持久化配置。</T>
   //
   // @param xconfig 配置节点
   //============================================================
   public FXmlPersistenceConfig(FXmlNode xconfig){
      _config = xconfig;
      _name = xconfig.get("name");
      _className = xconfig.get("class_name");
      _clazz = RClass.findClass(_className);
   }

   //============================================================
   // <T>获得名称。</T>
   //
   // @return 名称
   //============================================================
   public String name(){
      return _name;
   }

   //============================================================
   // <T>获得类对象。</T>
   //
   // @return 类对象
   //============================================================
   public Class<?> clazz(){
      return _clazz;
   }

   //============================================================
   // <T>获得配置节点。</T>
   //
   // @return 配置节点
   //============================================================
   public FXmlNode config(){
      return _config;
   }

   //============================================================
   // <T>创建实例。</T>
   //
   // @return 实例
   //============================================================
   public IXmlObject newInstance(){
      return (IXmlObject)RClass.newInstance(_clazz);
   }

   //============================================================
   // <T>生成运行信息。</T>
   //
   // @param info 运行信息
   // @return 运行信息
   //============================================================
   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.dump(info);
      info.append("name=", _name);
      info.append("class=", _className);
      return info;
   }
}
