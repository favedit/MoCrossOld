package org.mo.core.aop.face;

import java.lang.reflect.Method;
import org.mo.com.lang.FAttributes;

//============================================================
// <T>类代理接口。</T>
//============================================================
public interface IClassProxy
{
   //============================================================
   // <T>代理处理。</T>
   //
   // @param clazz 类对象
   // @param method 函数
   // @param attributes 属性集合
   // @param names 名称集合
   // @param values 内容集合
   // @return 处理结果
   //============================================================
   Object proxy(Class<?> clazz,
                Method method,
                FAttributes attributes,
                String[] names,
                Object[] values);
}
