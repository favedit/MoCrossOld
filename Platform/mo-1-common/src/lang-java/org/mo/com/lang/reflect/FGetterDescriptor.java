package org.mo.com.lang.reflect;

import java.lang.reflect.Method;
import org.mo.com.lang.FDictionary;

//============================================================
// <T>获得描述器。</T>
//============================================================
public class FGetterDescriptor
      extends MDescriptor
{
   //============================================================
   // <T>构造获得描述器。</T>
   //============================================================
   public FGetterDescriptor(){
   }

   //============================================================
   // <T>构造获得描述器。</T>
   //
   // @param item 对象
   //============================================================
   public FGetterDescriptor(Object item){
      super(item);
   }

   //============================================================
   // <T>建立函数集合。</T>
   //============================================================
   protected void buildMethods(){
      // 获得函数集合
      Method[] methods = _class.getMethods();
      // 查找设置器集合
      FDictionary<Method> methodMap = new FDictionary<Method>(Method.class, methods.length);
      for(Method method : methods){
         String name = method.getName().toLowerCase();
         Class<?>[] types = method.getParameterTypes();
         if(name.startsWith(SETTER) && (1 == types.length)){
            methodMap.set(name, method);
         }
      }
      // 查找获得器集合
      for(Method method : methods){
         String name = method.getName();
         if(name.startsWith(GETTER)){
            // 格式：getXXX / setXXX
            String property = name.substring(GETTER_LENGTH);
            Method setter = methodMap.get(SETTER + property.toLowerCase());
            FInvokeMethod invokeMethod = createMethod(property, method, setter);
            registerMethod(property, invokeMethod);
         }else{
            Method setter = methodMap.get(SETTER + name.toLowerCase());
            if(setter != null){
               // 格式：XXX / setXXX
               FInvokeMethod invokeMethod = createMethod(name, method, setter);
               registerMethod(name, invokeMethod);
            }
         }
      }
   }
}
