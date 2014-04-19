package org.mo.com.lang.reflect;

import java.lang.reflect.Method;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;
import org.mo.com.lang.FObjects;

//============================================================
// <T>函数对象。</T>
//============================================================
public class FMethod
      extends FObject
{
   // 函数对象
   protected Method _method;

   //============================================================
   // <T>构造函数对象。</T>
   //============================================================
   public FMethod(){
   }

   //============================================================
   // <T>构造函数对象。</T>
   //
   // @param method 函数对象
   //============================================================
   public FMethod(Method method){
      _method = method;
   }

   //============================================================
   // <T>获得本地对象。</T>
   //
   // @return 本地对象
   //============================================================
   public Method nativeObject(){
      return _method;
   }

   //============================================================
   // <T>获得名称。</T>
   //
   // @return 名称
   //============================================================
   public String name(){
      if(null != _method){
         return _method.getName();
      }
      throw new FFatalError("Invalid method.");
   }

   //============================================================
   // <T>获得类型名称。</T>
   //
   // @return 类型名称
   //============================================================
   public String className(){
      return _method.getDeclaringClass().getName();
   }

   //============================================================
   // <T>获得类型名称。</T>
   //
   // @return 类型名称
   //============================================================
   @SuppressWarnings({"unchecked", "rawtypes"})
   public FClass<?>[] parameterTypes(){
      try{
         FObjects<FClass> classes = new FObjects<FClass>(FClass.class);
         for(Class type : _method.getParameterTypes()){
            classes.push(new FClass(type));
         }
         return classes.toObjects();
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>获得返回类型名称。</T>
   //
   // @return 类型名称
   //============================================================
   public String returnClassname(){
      try{
         return _method.getReturnType().getName();
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>获得字符串。</T>
   //
   // @return 字符串
   //============================================================
   @Override
   public String toString(){
      return _method.toString();
   }
}
