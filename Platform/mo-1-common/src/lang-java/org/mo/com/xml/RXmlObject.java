package org.mo.com.xml;

import org.mo.com.lang.FString;
import org.mo.com.lang.IStringPair;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.lang.temp.RMultiString;

public class RXmlObject
{
   public final static String OBJECT_TYPE = "_type";

   public final static String OBJECT_UUID = "_uuid";

   public static IXmlObjects allChildren(IXmlObject instance){
      IXmlObjects objects = new FXmlObjects();
      allChildren(objects, instance);
      return objects;
   }

   protected static void allChildren(IXmlObjects objects,
                                     IXmlObject instance){
      if(null != instance){
         objects.push(instance);
         IXmlObjects children = instance.children();
         for(int n = 0; n < children.count(); n++){
            allChildren(objects, children.get(n));
         }
      }
   }

   public static FXmlObjectDictionary buildDictionary(IXmlObject instance,
                                                      String name){
      FXmlObjectDictionary xstyles = new FXmlObjectDictionary();
      if(instance.hasChild()){
         IXmlObjects xchildren = instance.children();
         int count = xchildren.count();
         for(int n = 0; n < count; n++){
            IXmlObject xchild = xchildren.get(n);
            String value = xchild.innerGet(name);
            if(!RString.isEmpty(value)){
               xstyles.set(value, xchild);
            }
         }
      }
      return xstyles;
   }

   /**
    * 将一个XML对象的实例和子对象全部转换为XML节点对象
    * 
    * @param instance XML对象的实例
    * @param config 转换方式
    * @return XML节点对象
    */
   public static FXmlNode convertDeepToNode(IXmlObject instance,
                                            EXmlConfig config){
      FXmlNode node = new FXmlNode();
      instance.saveConfig(node, config);
      if(instance.hasChild()){
         IXmlObjects xobjects = instance.children();
         int count = xobjects.count();
         for(int n = 0; n < count; n++){
            node.push(convertDeepToNode(xobjects.get(n), config));
         }
      }
      return node;
   }

   public static FXmlNode convertDeepToNodeSkipInvalid(IXmlObject instance,
                                                       EXmlConfig config){
      FXmlNode node = new FXmlNode();
      instance.saveConfig(node, config);
      if(instance.hasChild()){
         IXmlObjects xobjects = instance.children();
         int count = xobjects.count();
         for(int n = 0; n < count; n++){
            IXmlObject child = xobjects.get(n);
            if(RBoolean.parse(child.innerGet("is_valid"))){
               node.push(convertDeepToNodeSkipInvalid(child, config));
            }
         }
      }
      return node;
   }

   /**
    * 将一个XML对象的实例和子对象全部转换为XML字符串
    * 
    * @param instance XML对象的实例
    * @param config 转换方式
    * @return XML字符串
    */
   public static FString convertDeepToXml(IXmlObject instance,
                                          EXmlConfig config){
      return convertDeepToNode(instance, config).xml();
   }

   /**
    * 将一个XML对象的实例转换为XML节点对象
    * 
    * @param instance XML对象的实例
    * @param config 转换方式
    * @return XML节点对象
    */
   public static FXmlNode convertToNode(IXmlObject instance,
                                        EXmlConfig config){
      FXmlNode node = new FXmlNode();
      instance.saveConfig(node, config);
      return node;
   }

   /**
    * 将一个XML对象的实例转换为XML字符串
    * 
    * @param instance XML对象的实例
    * @param config 转换方式
    * @return XML字符串
    */
   public static FString convertToXml(IXmlObject instance,
                                      EXmlConfig config){
      return convertToNode(instance, config).xml();
   }

   public static void copyAttributes(EXmlConfigCopy copyType,
                                     IXmlObject xsource,
                                     IXmlObject xtarget){
      // 保存源节点属性
      FXmlNode sourceNode = new FXmlNode();
      xsource.saveConfig(sourceNode, EXmlConfig.Full);
      // 保存目标节点属性
      FXmlNode targetNode = new FXmlNode();
      xtarget.saveConfig(targetNode, EXmlConfig.Full);
      // 复制节点的属性
      for(IStringPair pair : sourceNode.attributes()){
         // 跳过UUID
         if("_uuid".equals(pair.name())){
            continue;
         }
         // 数据复制
         switch(copyType){
            case All:
               targetNode.set(pair.name(), pair.value());
               break;
            case Empty:
               if(RMultiString.isEmpty(targetNode.get(pair.name()))){
                  targetNode.set(pair.name(), pair.value());
               }
               break;
            case SkipEmpty:
               if(!RMultiString.isEmpty(sourceNode.get(pair.name()))){
                  targetNode.set(pair.name(), pair.value());
               }
               break;
         }
      }
      xtarget.loadConfig(targetNode, EXmlConfig.Full);
   }

   public static IXmlObject copyXmlObject(IXmlObject instance){
      IXmlObject xobject = RClass.newInstance(instance);
      FXmlNode config = new FXmlNode();
      instance.saveConfig(config, EXmlConfig.Full);
      xobject.loadConfig(config, EXmlConfig.Full);
      return xobject;
   }

   public static IXmlObject find(IXmlObject instance,
                                 String name,
                                 String value){
      IXmlObjects objects = instance.children();
      for(int n = 0; n < objects.count(); n++){
         IXmlObject object = objects.get(n);
         if(value.equalsIgnoreCase(object.innerGet(name))){
            return object;
         }
      }
      return null;
   }

   /**
    * 从XML对象中，根据标识查找XML子对象。
    * 
    * @param xobject XML对象
    * @param objectId 对象标识
    * @return XML子对象
    */
   public static IXmlObject findById(IXmlObject xobject,
                                     String objectId){
      return findById(xobject.children(), objectId);
   }

   /**
    * 从XML对象列表中，根据标识查找XML子对象。
    * 
    * @param xobjects XML对象列表
    * @param objectId 对象标识
    * @return XML子对象
    */
   public static IXmlObject findById(IXmlObjects xobjects,
                                     String objectId){
      int count = xobjects.count();
      for(int n = 0; n < count; n++){
         IXmlObject xobject = xobjects.get(n);
         if(objectId.equals(xobjects.get(n).objectId())){
            return xobject;
         }
      }
      return null;
   }
}
