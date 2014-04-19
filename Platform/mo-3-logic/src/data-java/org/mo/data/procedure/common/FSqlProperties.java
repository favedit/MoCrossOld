/*
 * @(#)FSqlProperties.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.data.procedure.common;

import org.mo.com.lang.FDictionary;

/**
 * <T>解析所有属性的集合类</T>
 *
 * @author ZENGD
 * @version 1.0.1
 */
public class FSqlProperties
      extends FDictionary<FSqlProperty>
{
   /**
    * <T>将属性对象增加到数据字典中</T>
    * 
    * @param property
    */
   public void push(FSqlProperty property){
      set(property.name(), property);
   }
}
