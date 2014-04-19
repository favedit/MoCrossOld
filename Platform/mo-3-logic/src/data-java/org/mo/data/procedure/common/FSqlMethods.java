/*
 * @(#)FSqlMethods.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.data.procedure.common;

import org.mo.com.lang.FDictionary;

/**
 * <T>解析所有方法的集合类</T>
 *
 * @author ZENGD
 * @version 1.0.1
 */
public class FSqlMethods
      extends FDictionary<FSqlMethod>
{
   /**
    * <T>将方法对象增加到数据字典中</T>
    * 
    * @param method 方法对象实体
    */
   public void push(FSqlMethod method){
      set(method.name(), method);
   }
}
