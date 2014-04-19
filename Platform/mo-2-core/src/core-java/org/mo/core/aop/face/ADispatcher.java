package org.mo.core.aop.face;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//============================================================
// <T>分发描述器。</T>
//============================================================

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ADispatcher{
}
