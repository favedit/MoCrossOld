package org.mo.com.xml.persistent;

import org.mo.com.collections.FObjectSet;
import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FString;
import org.mo.com.lang.IPersistent;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.xml.FXmlNode;

public class FXmlObjectUnserializer
{
   private final FDictionary<FXmlClass> _classDefine = new FDictionary<FXmlClass>(FXmlClass.class);

   @SuppressWarnings("unused")
   private final FObjectSet _classSet = new FObjectSet();

   @SuppressWarnings("unused")
   private final FXmlNode _config = new FXmlNode("Persistent");

   @SuppressWarnings("unused")
   private FXmlNode _configClasses;

   //private final FIntSet<Object> _stores = new FIntSet<Object>(Object.class);
   public FXmlObjectUnserializer(){
   }

   public void invokeEvent(Object value,
                           FXmlNode config){
      if(value instanceof IPersistent){
         FXmlClass clazz = RXmlPersistent.findClass(value.getClass());
         if(clazz.unserializeMethod() != null){
            clazz.unserializeMethod().invoke(value, config);
         }
      }
   }

   public void loadConfig(FXmlClass xclazz,
                          FXmlNode config,
                          Object item){
      //      // Store by id
      //      if(config.contains("id")){
      //         _stores.set(config.getInt("id"), item);
      //      }
      //      // Fields
      //      for(FXmlNode node : config.nodes()){
      //         String name = node.get("name");
      //         FXmlField field = xclazz.findField(name);
      //         if(node.isName("Array")){
      //            // Array
      //            field.set(item, makeArray(node));
      //         }else{
      //            // Object
      //            Object value = null;
      //            if(node.contains("refer")){
      //               int refer = node.getInt("refer");
      //               value = _stores.get(refer);
      //            }else{
      //               value = makeValue(node);
      //               if(value == null){
      //                  String ptyCid = node.get("class");
      //                  FXmlClass xclass = _classDefine.get(ptyCid);
      //                  if(xclass.isEnum()){
      //                     // ToDo
      //                  }else{
      //                     value = xclass.newInstance();
      //                     loadConfig(xclass, node, value);
      //                     invokeEvent(value, node);
      //                  }
      //               }
      //            }
      //            field.set(item, value);
      //         }
      //      }
   }

   public Object loadNode(FXmlNode config){
      FXmlNode classesCfg = config.findNode("Classes");
      if(classesCfg != null){
         for(FXmlNode node : classesCfg.nodes()){
            if(node.isName("Class")){
               String id = node.get("id");
               String name = node.get("name");
               Class<?> clazz = RClass.findClass(name);
               FXmlClass xclass = RXmlPersistent.findClass(clazz);
               _classDefine.set(id, xclass);
            }
         }
      }
      //
      FXmlNode insCfg = config.findNode("Instance");
      if(insCfg.contains("class")){
         String clsId = insCfg.get("class");
         FXmlClass xclazz = _classDefine.get(clsId);
         Object instance = RClass.newInstance(xclazz.clazz());
         loadConfig(xclazz, insCfg, instance);
         invokeEvent(instance, insCfg);
         return instance;
      }
      return null;
   }

   public Object makeArray(FXmlNode config){
      // Array
      int count = config.getInt("count");
      String classId = config.get("class");
      FXmlClass xclass = _classDefine.get(classId);
      Object[] values = xclass.newArrayInstance(count);
      //      if(config.hasNode()){
      //         for(FXmlNode node : config.nodes()){
      //            Object value = null;
      //            int index = node.getInt("index");
      //            if(node.contains("refer")){
      //               value = _stores.get(node.getInt("refer"));
      //            }else{
      //               value = makeValue(node);
      //               if(value == null){
      //                  FXmlClass clazz = xclass;
      //                  if(node.contains("class")){
      //                     clazz = _classDefine.get(node.get("class"));
      //                  }
      //                  value = clazz.newInstance();
      //                  loadConfig(clazz, node, value);
      //                  invokeEvent(value, node);
      //               }
      //            }
      //            values[index] = value;
      //         }
      //      }
      return values;
   }

   public Object makeArrayValue(FXmlNode config){
      // Array
      int count = config.getInt("count");
      String classId = config.get("class");
      FXmlClass xclass = _classDefine.get(classId);
      Object[] values = xclass.newArrayInstance(count);
      //      if(config.hasNode()){
      //         for(FXmlNode node : config.nodes()){
      //            Object value = null;
      //            int index = node.getInt("index");
      //            if(node.contains("refer")){
      //               value = _stores.get(node.getInt("refer"));
      //            }else{
      //               value = makeValue(node);
      //               if(value == null){
      //                  FXmlClass clazz = xclass;
      //                  if(node.contains("class")){
      //                     clazz = _classDefine.get(node.get("class"));
      //                  }
      //                  value = clazz.newInstance();
      //                  loadConfig(clazz, node, value);
      //                  invokeEvent(value, node);
      //               }
      //            }
      //            values[index] = value;
      //         }
      //      }
      return values;
   }

   public Object makeValue(FXmlNode config){
      String name = config.name();
      if("Nothing".equals(name)){
         return new Object();
      }else if("String".equals(name)){
         return config.text();
      }else if("FString".equals(name)){
         return new FString(config.text());
      }else if("Boolean".equals(name)){
         return Boolean.parseBoolean(config.get("value"));
      }else if("Character".equals(name)){
         return (char)Short.parseShort(config.get("value"));
      }else if("Byte".equals(name)){
         return Byte.parseByte(config.get("value"));
      }else if("Short".equals(name)){
         return Short.parseShort(config.get("value"));
      }else if("Integer".equals(name)){
         return Integer.parseInt(config.get("value"));
      }else if("Long".equals(name)){
         return Long.parseLong(config.get("value"));
      }else if("Float".equals(name)){
         return Float.parseFloat(config.get("value"));
      }else if("Double".equals(name)){
         return Double.parseDouble(config.get("value"));
      }else if("Class".equals(name)){
         return RClass.findClass(config.get("value"));
      }
      return null;
   }
}
