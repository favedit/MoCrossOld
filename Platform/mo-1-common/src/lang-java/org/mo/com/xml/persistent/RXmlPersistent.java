package org.mo.com.xml.persistent;

import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;

//============================================================
// <T>配置持久化工具类。</T>
//============================================================
public class RXmlPersistent
{
   // 持久化类字典
   private static FXmlClasses _classDictionary = new FXmlClasses();

   //============================================================
   // <T>获得持久化类字典。</T>
   //
   // @return 持久化类字典
   //============================================================
   public static FXmlClasses classDictionary(){
      return _classDictionary;
   }

   //============================================================
   // <T>查找持久化类对象。</T>
   //
   // @return clazz 类对象
   // @return 持久化类对象
   //============================================================
   public static FXmlClass findClass(Class<?> clazz){
      return _classDictionary.sync(clazz);
   }

   //============================================================
   // <T>转换配置节点为配置对象。</T>
   //
   // @return xconfig 配置节点
   // @return 配置对象
   //============================================================
   public static Object toObject(FXmlNode xconfig){
      FXmlObjectUnserializer unserializer = new FXmlObjectUnserializer();
      return unserializer.loadNode(xconfig);
   }

   //============================================================
   // <T>加载配置文件。</T>
   //
   // @return fileName 文件名称
   // @return 配置对象
   //============================================================
   public static Object loadFile(String fileName){
      FXmlDocument xdoc = new FXmlDocument(fileName);
      return toObject(xdoc.root());
   }

   //============================================================
   // <T>转换配置对象为配置节点。</T>
   //
   // @return xobject 配置对象
   // @return 配置节点
   //============================================================
   public static FXmlNode toXml(Object xobject){
      FXmlObjectSerializer serializer = new FXmlObjectSerializer();
      return serializer.saveNode(xobject);
   }

   //============================================================
   // <T>保存配置文件。</T>
   //
   // @return fileName 文件名称
   // @return 配置对象
   //============================================================
   public static void saveFile(Object xobject,
                               String fileName){
      FXmlNode xconfig = toXml(xobject);
      FXmlDocument xdoc = new FXmlDocument(xconfig);
      xdoc.saveFile(fileName);
   }
}
