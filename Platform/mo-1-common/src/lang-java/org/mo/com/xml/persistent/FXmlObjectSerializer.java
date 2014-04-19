package org.mo.com.xml.persistent;

import org.mo.com.collections.FObjectSet;
import org.mo.com.lang.FString;
import org.mo.com.lang.IPersistent;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;

public class FXmlObjectSerializer
{
   private FObjectSet _classSet = new FObjectSet();

   private FXmlNode _config = new FXmlNode("Persistent");

   private FXmlNode _configClasses;

   private FXmlDocument _document = new FXmlDocument();;

   private FObjectSet _stores = new FObjectSet();

   public FXmlObjectSerializer(){
      _config = _document.root();
   }

   public int findClassId(FXmlClass clazz){
      Class<?> type = clazz.clazz();
      if(type.isArray()){
         type = type.getComponentType();
      }
      int id = _classSet.indexOf(type);
      if(id == -1){
         _classSet.push(type);
         id = _classSet.indexOf(type);
         // Store class info
         FXmlNode clsNode = _configClasses.createNode("Class");
         clsNode.set("id", id);
         clsNode.set("name", type.getName());
      }
      return id;
   }

   public void invokeEvent(Object value,
                           FXmlNode config){
      if(value instanceof IPersistent){
         FXmlClass clazz = RXmlPersistent.findClass(value.getClass());
         if(clazz.serializeMethod() != null){
            clazz.serializeMethod().invoke(value, config);
         }
      }
   }

   public void saveArray(FXmlNode config,
                         FXmlClass clazz,
                         Object item){
      Object[] objects = (Object[])item;
      int count = objects.length;
      config.setName("Array");
      config.set("class", findClassId(clazz));
      config.set("count", count);
      for(int n = 0; n < count; n++){
         Object value = objects[n];
         if(value != null){
            FXmlNode node = config.createNode("Object");
            node.set("index", n);
            if(!saveValue(node, value)){
               int id = _stores.indexOf(value);
               if(id == -1){
                  saveConfig(node, Integer.toString(n), value);
                  invokeEvent(value, node);
               }else{
                  node.set("refer", id);
               }
            }
         }
      }
   }

   public void saveConfig(FXmlNode config,
                          String name,
                          Object item){
      if(_stores.contains(item)){
         return;
      }
      _stores.push(item);
      // Item
      FXmlClass clazz = RXmlPersistent.findClass(item.getClass());
      int classId = findClassId(clazz);
      config.set("id", _stores.indexOf(item));
      config.set("name", name);
      if(clazz.isArray()){
         if(!saveValueArray(config, item)){
            saveArray(config, clazz, item);
         }
      }else if(!saveValue(config, item)){
         config.set("class", classId);
         // Fields
         for(FXmlField field : clazz.fields().values()){
            Object value = field.get(item);
            if(value != null){
               FXmlNode node = config.createNode("Object");
               String fname = field.name();
               node.set("name", fname);
               if(!saveValue(node, value)){
                  int find = _stores.indexOf(value);
                  if(find == -1){
                     saveConfig(node, fname, value);
                     invokeEvent(value, node);
                  }else{
                     node.set("refer", find);
                  }
               }
            }
         }
      }
   }

   public FXmlNode saveNode(Object instance){
      _configClasses = _config.createNode("Classes");
      FXmlNode instanceConfig = _config.createNode("Instance");
      if(instance != null){
         saveConfig(instanceConfig, null, instance);
         invokeEvent(instance, instanceConfig);
      }
      return _config;
   }

   public boolean saveValue(FXmlNode config,
                            Object value){
      Class<?> type = value.getClass();
      if(type == Object.class){
         config.setName("Nothing");
         return true;
      }else if(type == String.class){
         config.setName("String");
         config.setText((String)value);
         return true;
      }else if(type == FString.class){
         config.setName("FString");
         config.setText(((FString)value).toString());
         return true;
      }else if(type == Boolean.class || type == boolean.class){
         config.setName("Boolean");
         config.set("value", Boolean.toString((Boolean)value));
         return true;
      }else if(type == Character.class || type == char.class){
         config.setName("Character");
         config.set("value", Character.toString((Character)value));
         return true;
      }else if(type == Byte.class || type == byte.class){
         config.setName("Byte");
         config.set("value", Byte.toString((Byte)value));
         return true;
      }else if(type == Short.class || type == short.class){
         config.setName("Short");
         config.set("value", Short.toString((Short)value));
         return true;
      }else if(type == Integer.class || type == int.class){
         config.setName("Integer");
         config.set("value", Integer.toString((Integer)value));
         return true;
      }else if(type == Long.class || type == long.class){
         config.setName("Long");
         config.set("value", Long.toString((Long)value));
         return true;
      }else if(type == Float.class || type == float.class){
         config.setName("Float");
         config.set("value", Float.toString((Float)value));
         return true;
      }else if(type == Double.class || type == double.class){
         config.setName("Double");
         config.set("value", Double.toString((Double)value));
         return true;
      }else if(type == Class.class){
         config.setName("Class");
         config.set("value", ((Class<?>)value).getName());
         return true;
      }
      return false;
   }

   public boolean saveValueArray(FXmlNode config,
                                 Object value){
      Class<?> type = value.getClass().getComponentType();
      if(type == Character.class || type == char.class){
         config.setText(String.valueOf(value));
         return true;
         /*}else if(type == Byte.class || type == byte.class){
         config.setName("Boolean");
         config.set("value", Boolean.toString((Boolean) value));
         return true;
         }else if(type == Integer.class || type == int.class){
         config.setName("Integer");
         config.set("value", Integer.toString((Integer) value));
         return true;
         }else if(type == Long.class || type == long.class){
         config.setName("Long");
         config.set("value", Long.toString((Long) value));
         return true;
         }else if(type == Float.class || type == float.class){
         config.setName("Float");
         config.set("value", Float.toString((Float) value));
         return true;
         }else if(type == Double.class || type == double.class){
         config.setName("Double");
         config.set("value", Double.toString((Double) value));
         return true;
         }else if(type == Class.class){
         config.setName("Class");
         config.set("value", ((Class<?>) value).getName());
         return true;*/
      }
      return false;
   }
}
