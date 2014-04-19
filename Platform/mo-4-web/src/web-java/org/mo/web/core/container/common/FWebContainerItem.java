package org.mo.web.core.container.common;

import java.io.Serializable;
import java.lang.reflect.Array;
import org.mo.com.collections.FObjectDictionary;
import org.mo.com.collections.FObjectSet;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.IObjectId;
import org.mo.com.lang.IObjects;
import org.mo.com.lang.IStringPair;
import org.mo.com.lang.RString;
import org.mo.com.lang.reflect.RProperty;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.com.xml.persistent.FXmlClass;
import org.mo.com.xml.persistent.FXmlField;
import org.mo.com.xml.persistent.RXmlPersistent;
import org.mo.web.core.container.RWebContainer;

//============================================================
// <T>表单容器。</T>
//============================================================
public class FWebContainerItem
      extends FObject
      implements
         Serializable
{
   // 序列化标志
   private static final long serialVersionUID = 1L;

   // 日志输出接口
   private static ILogger _logger = RLogger.find(FWebContainerItem.class);

   // 容器对象
   protected Object _container;

   //============================================================
   // <T>构造表单容器。</T>
   //============================================================
   public FWebContainerItem(){
   }

   //============================================================
   // <T>是否含有容器对象。</T>
   //
   // @return 是否含有
   //============================================================
   public boolean hasContainer(){
      return (_container != null);
   }

   //============================================================
   // <T>获得容器对象。</T>
   //
   // @return 容器对象
   //============================================================
   public Object container(){
      return _container;
   }

   //============================================================
   // <T>设置容器对象。</T>
   //
   // @param container 容器对象
   //============================================================
   public void setContainer(Object container){
      _container = container;
   }

   //============================================================
   // <T>判断是否深层对象。</T>
   //
   // @param instance 实例
   // @return 是否深层对象
   //============================================================
   protected boolean isDeepObject(Object instance){
      if(instance == null){
         return false;
      }
      Class<?> clazz = instance.getClass();
      if(clazz.getName().startsWith("java.lang.")){
         return false;
      }
      return true;
   }

   //============================================================
   // <T>构造对照表。</T>
   //
   // @param set 对象集合
   // @param map 对象字典
   // @param instance 实体
   //============================================================
   protected void buildMap(FObjectSet set,
                           FObjectDictionary map,
                           Object instance){
      // 检查是否处理过当前对象，防止递归
      if(set.contains(instance)){
         return;
      }
      set.push(instance);
      //............................................................
      // 如果支持对象标识接口则记录下来
      if(instance instanceof IObjectId){
         IObjectId objectId = (IObjectId)instance;
         map.set(objectId.objectId(), instance);
      }
      // 分解已知的类对象
      if(instance instanceof FXmlNode){
         FXmlNodes nodes = ((FXmlNode)instance).allNodes();
         for(FXmlNode node : nodes){
            map.set(node.objectId(), node);
         }
         return;
      }else if(instance instanceof IObjects<?>){
         Object[] items = ((IObjects<?>)instance).toObjects();
         for(Object item : items){
            if(item != null){
               if(isDeepObject(item)){
                  buildMap(set, map, item);
               }
            }
         }
         return;
      }
      //............................................................
      // 当对象无法识别类型时，使用类的自影射机制强制递归
      Class<?> clazz = instance.getClass();
      if(clazz.isArray()){
         // 递归处理数组内所有子对象
         int count = Array.getLength(instance);
         for(int n = 0; n < count; n++){
            Object child = Array.get(instance, n);
            if(child != null){
               if(isDeepObject(child)){
                  buildMap(set, map, child);
               }
            }
         }
      }else{
         // 递归处理子对象
         FXmlClass xclass = RXmlPersistent.findClass(clazz);
         FXmlField[] xfields = xclass.fields().values();
         for(FXmlField xfield : xfields){
            Object child = xfield.get(instance);
            if(child != null){
               if(isDeepObject(child)){
                  buildMap(set, map, child);
               }
            }
         }
      }
   }

   //============================================================
   // <T>根据数据列表内容，填充数据容器。</T>
   //
   // @param params 参数集合
   //============================================================
   public boolean fill(IAttributes params){
      if(_logger.debugAble()){
         _logger.debug(this, "fillForm", "Fill form. (form={1})", _container);
      }
      if(_container == null){
         return false;
      }
      //............................................................
      synchronized(_container){
         // 建立对象集合
         FObjectDictionary map = new FObjectDictionary();
         FObjectSet set = new FObjectSet();
         buildMap(set, map, _container);
         //............................................................
         // 填充数据
         for(IStringPair pair : params){
            // 填充容器数据
            String name = pair.name();
            String[] items = RWebContainer.parseName(name);
            if(items != null){
               if(items[0] == null){
                  // 设置项目
                  String value = params.get(items[1]);
                  RProperty.set(_container, RString.removeChars(name, "-_"), value);
               }else{
                  Object container = map.get(items[0], null);
                  if(container != null){
                     RProperty.set(container, items[1], pair.value());
                  }
               }
            }
         }
      }
      return true;
   }

   //============================================================
   // <T>清空内容。</T>
   //============================================================
   public void clear(){
      throw new FFatalError("clear");
   }
}
