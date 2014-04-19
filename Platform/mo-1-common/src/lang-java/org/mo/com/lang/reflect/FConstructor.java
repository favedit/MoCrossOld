package org.mo.com.lang.reflect;

import java.lang.reflect.Constructor;

//============================================================
// <T>构造函数对象。</T>
//============================================================
public class FConstructor
{
   // 构造函数对象
   protected Constructor<?> _constructor;

   //============================================================
   // <T>构造构造函数对象。</T>
   //============================================================
   public FConstructor(){
   }

   //============================================================
   // <T>构造构造函数对象。</T>
   //============================================================
   //   public FConstructor(FClass<?> clazz,
   //                       Class<?>[] params){
   //      // _constructor = new CtConstructor(new CtClass[0],
   //      // clazz.nativeClass());
   //   }
   //============================================================
   // <T>获得本地对象。</T>
   //
   // @return 本地对象
   //============================================================
   public Constructor<?> nativeObject(){
      return _constructor;
   }
}
