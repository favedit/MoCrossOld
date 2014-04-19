/*
 * @(#)FSqlParameters.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.data.procedure.common;

import org.mo.com.lang.FDictionary;

/**
 * <T>解析所有参数的集合类</T>
 *
 * @author ZENGD
 * @version 1.0.1
 */
public class FSqlParameters
      extends FDictionary<FSqlParameter>
{
   /**
    * <T>将参数对象增加到数据字典中</T>
    * 
    * @param parameter 参数对象实体
    */
   public void push(FSqlParameter parameter){
      set(parameter.name(), parameter);
   }
}
