/*
 * @(#)AWebLogin.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.core.action;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <T>描述指定类或函数是否需要用户登录验证的描述器。</T>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/12/19
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface AWebLogin{

   /**
    * <T>是否需要用户登录校验。</T>
    * 
    * @default 需要校验
    * @return 是否需要校验
    */
   boolean require() default true;

}
