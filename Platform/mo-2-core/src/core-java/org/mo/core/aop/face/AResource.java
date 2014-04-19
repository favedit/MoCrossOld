package org.mo.core.aop.face;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//============================================================
// <T>资源描述器。</T>
//============================================================

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface AResource{
   //============================================================
   // <T>内容描述。</T>
   //============================================================
   String value();
}
