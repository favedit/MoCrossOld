package org.mo.core.aop.face;

import java.lang.reflect.Method;
import org.mo.com.lang.FAttributes;

//============================================================
// <T>函数代理接口。</T>
//============================================================
public interface IMethodProxy
{
   //============================================================
   // <T>代理处理。</T>
   //
   // @param attributes 属性集合
   // @param clazz 类对象
   // @param method 函数
   // @param params 参数集合
   // @return 处理结果
   //============================================================
   Object proxy(FAttributes attributes,
                Class<?> clazz,
                Method method,
                Object[] params);
}
