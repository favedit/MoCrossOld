package org.mo.core.aop.face;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//============================================================
// <T>代理描述器。</T>
//============================================================

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AProxy{
   //============================================================
   // <T>工厂类描述。</T>
   //============================================================
   Class<?> factoryClass() default Object.class;
}
