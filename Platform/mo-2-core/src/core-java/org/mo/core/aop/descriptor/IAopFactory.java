package org.mo.core.aop.descriptor;

import org.mo.com.lang.reflect.FClass;

//============================================================
// <T>AOP组件工厂接口。</T>
//============================================================
public interface IAopFactory
{
   //============================================================
   // <T>创建对象实例。</T>
   //
   // @param clazz 类对象
   // @return 实例
   //============================================================
   <V> V createInstance(FClass<?> clazz);
}
