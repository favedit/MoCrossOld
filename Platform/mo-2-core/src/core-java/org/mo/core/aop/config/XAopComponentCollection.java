package org.mo.core.aop.config;

import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;

//============================================================
// <T>AOP组件集合。</T>
//============================================================
public class XAopComponentCollection
      extends XAopNodeCollection<XAopComponent>
{
   // 类集合
   protected final FDictionary<XAopComponent> _classes = new FDictionary<XAopComponent>(XAopComponent.class);

   // 名称集合
   protected final FDictionary<XAopComponent> _names = new FDictionary<XAopComponent>(XAopComponent.class);

   //============================================================
   // <T>构造AOP组件集合。</T>
   //============================================================
   public XAopComponentCollection(){
      super(XAopComponent.class);
   }

   //============================================================
   // <T>根据名称获得名称。</T>
   //
   // @param name 名称
   // @return 名称
   //============================================================
   public XAopComponent findByName(String name){
      return _names.get(name);
   }

   //============================================================
   // <T>根据名称获得类对象。</T>
   //
   // @param name 名称
   // @return 类对象
   //============================================================
   public XAopComponent findByClass(String name){
      return _classes.get(name);
   }

   //============================================================
   // <T>追加新节点。</T>
   //
   // @param node 节点
   //============================================================
   @Override
   public void push(XAopComponent xcomponent){
      super.push(xcomponent);
      // 根据名称放入节点
      String name = xcomponent.name();
      if(!RString.isEmpty(name)){
         if(_names.contains(name)){
            throw new FFatalError("Duplicate aop node name. (name={1})", name);
         }
         _names.set(name, xcomponent);
      }
      // 根据类名放入节点
      String className = xcomponent.className();
      if(!RString.isEmpty(className)){
         if(_classes.contains(className)){
            throw new FFatalError("Duplicate aop node class name. (class_name={1})", className);
         }
         _classes.set(className, xcomponent);
      }
   }
}
