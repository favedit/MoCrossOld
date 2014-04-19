package org.mo.com.lang.reflect;

import java.lang.reflect.Method;
import org.mo.com.lang.RString;
import org.mo.com.lang.RStringFormat;

//============================================================
// <T>获得描述器。</T>
//============================================================
public class FGetDescriptor
      extends MDescriptor
{
   //============================================================
   // <T>构造获得描述器。</T>
   //============================================================
   public FGetDescriptor(){
   }

   //============================================================
   // <T>构造获得描述器。</T>
   //
   // @param item 对象
   //============================================================
   public FGetDescriptor(Object item){
      super(item);
   }

   //============================================================
   // <T>建立函数集合。</T>
   //============================================================
   @Override
   protected void buildMethods(){
      Method[] methods = _class.getMethods();
      for(Method method : methods){
         if(0 == method.getParameterTypes().length){
            String typeName = method.getReturnType().getName();
            if(!"void".equals(typeName)){
               // 建立函数
               String name = method.getName();
               if(name.startsWith(GETTER)){
                  name = RString.firstLower(name.substring(GETTER_LENGTH));
               }
               FInvokeMethod invokeMethod = createMethod(name, method, null);
               // 建立下划线名称
               registerMethod(name.toLowerCase(), invokeMethod);
               registerMethod(RStringFormat.formatLineWordsLower(name, null, null), invokeMethod);
            }
         }
      }
   }
}
