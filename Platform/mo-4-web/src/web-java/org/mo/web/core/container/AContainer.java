package org.mo.web.core.container;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//============================================================
// <T>容器。</T>
//============================================================
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface AContainer{
   //------------------------------------------------------------
   // <T>名称。</T>
   String name() default "container";

   //------------------------------------------------------------
   // <T>范围。</T>
   EContainerScope scope() default EContainerScope.Session;

   //------------------------------------------------------------
   // <T>是否填充。</T>
   boolean fill() default false;

   //------------------------------------------------------------
   // <T>是否清空。</T>
   boolean clear() default false;
}
