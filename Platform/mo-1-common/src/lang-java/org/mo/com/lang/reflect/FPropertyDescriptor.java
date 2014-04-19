package org.mo.com.lang.reflect;

import java.lang.reflect.Method;
import org.mo.com.lang.FDictionary;

//============================================================
// <T>属性设置描述器。</T>
//============================================================
public class FPropertyDescriptor
      extends MDescriptor
{
   //============================================================
   // <T>构造属性设置描述器。</T>
   //============================================================
   public FPropertyDescriptor(){
   }

   //============================================================
   // <T>构造属性描述器。</T>
   //
   // @param item 对象
   //============================================================
   public FPropertyDescriptor(Object item){
      super(item);
   }

   //============================================================
   // <T>建立函数集合。</T>
   //============================================================
   @Override
   protected void buildMethods(){
      // 获得函数集合
      Method[] methods = _class.getMethods();
      // 查找设置器集合
      FDictionary<Method> methodMap = new FDictionary<Method>(Method.class, methods.length);
      for(Method method : methods){
         String name = method.getName().toLowerCase();
         Class<?>[] types = method.getParameterTypes();
         if(name.startsWith(SETTER) && types.length == 1){
            methodMap.set(name, method);
         }
      }
      // 查找获得器集合
      for(Method method : methods){
         String name = method.getName().toLowerCase();
         if(name.startsWith(GETTER)){
            String property = name.substring(GETTER_LENGTH);
            Method setter = methodMap.find(SETTER + property);
            if(setter != null){
               // 格式：getXXX / setXXX
               FInvokeMethod invokeMethod = createMethod(property, method, setter);
               registerMethod(property, invokeMethod);
            }
         }else{
            Method setter = methodMap.find(SETTER + name);
            if(setter != null){
               // 格式：XXX / setXXX
               FInvokeMethod invokeMethod = createMethod(name, method, setter);
               registerMethod(name, invokeMethod);
            }
         }
      }
   }
}
