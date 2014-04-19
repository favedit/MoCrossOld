package org.mo.com.data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//============================================================
// <T>数据库定义。</T>
//============================================================
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ASqlConnect{
   String name() default "page";

   boolean transaction() default true;
}
