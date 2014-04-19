package org.mo.com.lang.reflect;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;

//============================================================
// <T>类对象描述器。</T>
//============================================================
public class FClassDescriptor
      extends FObject
{
   // 类对象
   private FClass<?> _clazz;

   // 函数描述器集合
   private static FMethodDescriptors _method = new FMethodDescriptors();

   //============================================================
   // <T>构造类对象描述器。</T>
   //============================================================
   public FClassDescriptor(){
   }

   //============================================================
   // <T>关联类对象。</T>
   //
   // @param clazz 类对象
   //============================================================
   public void link(Class<?> clazz){
      _clazz = RClass.find(clazz);
   }

   //============================================================
   // <T>根据名称查找函数。</T>
   //
   // @param name 名称
   // @return 
   //============================================================
   public FMethodDescriptor findMethod(String name){
      FMethodDescriptor descriptor = _method.get(name);
      if(null == descriptor){
         for(FMethod method : _clazz.allMethods()){
            if(name.equalsIgnoreCase(method.name())){
               descriptor = new FMethodDescriptor();
               descriptor.link(method);
               _method.set(name, descriptor);
               break;
            }
         }
         if(null == descriptor){
            throw new FFatalError("Not found method. (method={0})", name);
         }
      }
      return descriptor;
   }
}
