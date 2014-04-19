package org.mo.core.aop.descriptor;

import org.mo.com.console.FConsole;

//============================================================
// <T>AOP描述器控制台。</T>
//============================================================
public class FAopDescriptorConsole
      extends FConsole
{
   // 描述器集合
   protected FAopDescriptors _descriptors = new FAopDescriptors();

   //============================================================
   // <T>构造AOP描述器控制台。</T>
   //============================================================
   @SuppressWarnings("unchecked")
   public <V extends IAopDescriptor> V find(Class<V> clazz,
                                            String className){
      IAopDescriptor descriptor = _descriptors.find(className);
      if(null == descriptor){
         // 设置组件描述器
         synchronized(clazz){
            if(!_descriptors.contains(className)){
               // 创建组件描述器
               FAopComponentDescriptor componentDescriptor = new FAopComponentDescriptor();
               componentDescriptor.loadClass(className);
               descriptor = componentDescriptor;
               // 存储组件描述器
               _descriptors.set(className, descriptor);
            }
         }
      }
      return (V)descriptor;
   }
}
