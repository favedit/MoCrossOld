package org.mo.core.aop.container;

import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FObject;
import org.mo.core.aop.RAop;
import org.mo.core.aop.config.XAopComponent;
import org.mo.core.aop.config.XAopComponentCollection;
import org.mo.core.aop.descriptor.FAopComponentBuilder;

//============================================================
// <T>AOP组件集合。</T>
//============================================================
public class FAopComponents
      extends FObject
{
   // 构建器
   protected final FAopComponentBuilder _builder = new FAopComponentBuilder();

   // 组件集合
   protected XAopComponentCollection _collection;

   // 组件名称字典
   protected FDictionary<FAopComponent> _names = new FDictionary<FAopComponent>(FAopComponent.class);

   // 组件类名称字典
   protected FDictionary<FAopComponent> _classes = new FDictionary<FAopComponent>(FAopComponent.class);

   // 组件字典
   protected FDictionary<FAopComponent> _components = new FDictionary<FAopComponent>(FAopComponent.class);

   //============================================================
   // <T>构建AOP组件集合。</T>
   //============================================================
   public FAopComponents(){
   }

   //============================================================
   // <T>获得组件总数。</T>
   //
   // @return 组件总数
   //============================================================
   public int count(){
      return _components.count();
   }

   //============================================================
   // <T>获得组件集合。</T>
   //
   // @return 组件集合
   //============================================================
   public XAopComponentCollection collection(){
      if(null == _collection){
         _collection = RAop.configConsole().componentCollection();
      }
      return _collection;
   }

   //============================================================
   // <T>根据接口名称查找组件对象。</T>
   //
   // @param face 接口名称
   // @return 组件对象
   //============================================================
   public FAopComponent find(String face){
      FAopComponent component = _components.find(face);
      if(null == component){
         XAopComponentCollection xcomponents = collection();
         if(null != xcomponents){
            XAopComponent xcomponent = xcomponents.findByKey(face);
            // 查找定义
            if(null == xcomponent){
               xcomponent = RAop.configConsole().componentsCollection().matchByFace(face);
            }
            // 创建组件
            if(null != xcomponent){
               component = new FAopComponent(xcomponent);
               component.setBuilder(_builder);
               component.initialize();
               _components.set(face, component);
            }
         }
      }
      return component;
   }

   //============================================================
   // <T>根据名称查找组件对象。</T>
   //
   // @param name 名称
   // @return 组件对象
   //============================================================
   public FAopComponent findByName(String name){
      FAopComponent component = _names.get(name);
      if(null == component){
         XAopComponentCollection xcomponents = collection();
         if(null != xcomponents){
            XAopComponent xcomponent = xcomponents.findByName(name);
            if(xcomponent != null){
               component = new FAopComponent(xcomponent);
               component.setBuilder(_builder);
               component.initialize();
               _names.set(name, component);
            }
         }
      }
      return component;
   }

   //============================================================
   // <T>根据类名称查找组件对象。</T>
   //
   // @param className 类名称
   // @return 组件对象
   //============================================================
   public FAopComponent findByClass(String className){
      FAopComponent component = _classes.get(className);
      if(null == component){
         XAopComponentCollection xcomponents = collection();
         if(null != xcomponents){
            XAopComponent xcomponent = xcomponents.findByClass(className);
            if(null != xcomponent){
               component = new FAopComponent(xcomponent);
               component.setBuilder(_builder);
               component.initialize();
               _classes.set(className, component);
            }
         }
      }
      return component;
   }

   //============================================================
   // <T>根据索引位置获得组件对象。</T>
   //
   // @param index 索引位置
   // @return 组件对象
   //============================================================
   public FAopComponent get(int index){
      return _components.value(index);
   }

   //============================================================
   // <T>根据接口名称设置组件对象。</T>
   //
   // @param face 接口名称
   // @param component 组件对象
   //============================================================
   public void set(String face,
                   FAopComponent component){
      _components.set(face, component);
   }

   //============================================================
   // <T>获得组件对象集合。</T>
   //
   // @return 组件对象集合
   //============================================================
   public FAopComponent[] toObjects(){
      return _components.toObjects();
   }
}
