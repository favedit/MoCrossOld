package org.mo.com.lang.reflect;

import java.lang.reflect.Method;

//============================================================
// <T>设置描述器。</T>
//============================================================
public class FSetterDescriptor
      extends MDescriptor
{
   //============================================================
   // <T>构造设置描述器。</T>
   //============================================================
   public FSetterDescriptor(){
   }

   //============================================================
   // <T>构造设置描述器。</T>
   //
   // @param item 对象
   //============================================================
   public FSetterDescriptor(Object item){
      super(item);
   }

   //============================================================
   // <T>建立函数集合。</T>
   //============================================================
   protected void buildMethods(){
      // 获得函数集合
      Method[] methods = _class.getMethods();
      // 查找设置器集合
      for(Method method : methods){
         String name = method.getName().toLowerCase();
         if(name.startsWith(SETTER)){
            Class<?>[] types = method.getParameterTypes();
            if(1 == types.length){
               String methodName = name.substring(SETTER_LENGTH);
               FInvokeMethod invokeMethod = createMethod(methodName, null, method);
               registerMethod(methodName, invokeMethod);
            }
         }
      }
   }
}
