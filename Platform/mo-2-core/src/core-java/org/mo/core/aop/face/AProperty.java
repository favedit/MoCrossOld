package org.mo.core.aop.face;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//============================================================
// <T>属性描述器。</T>
//============================================================
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface AProperty
{
   // 是否转换
   boolean convert() default true;
}
