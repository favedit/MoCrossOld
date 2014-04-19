package org.mo.com.lang.face;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//============================================================
// <T>可获得名称描述。</T>
//============================================================

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER})
public @interface AName{
   //============================================================
   // <T>获得名称。</T>
   //============================================================
   String value();
}
