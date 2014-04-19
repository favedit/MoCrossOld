package org.mo.core.aop.face;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.mo.core.aop.descriptor.FAopComponentFactory;

//============================================================
// <T>工厂描述器。</T>
//============================================================

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AFactory{
   //============================================================
   // <T>类对象描述。</T>
   //============================================================
   Class<?> clazz() default FAopComponentFactory.class;
}
