/*
 * @(#)FSessionWorkers.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.session.common;

import org.mo.com.lang.FDictionary;
import org.mo.com.lang.RHex;

/**
 * <p>线程托管对象的字典集合。</p>
 * 
 * @author maocy
 * @version 1.00 - 2008/11/18
 * @see org.mo.com.lang.FDictionary
 */
public class FSessionWorkers
      extends FDictionary<FSessionWorker>
{
   // 存储标识
   protected int _storeId = 0;

   /**
    * 构建字典集合对象
    */
   public FSessionWorkers(){
      super(FSessionWorker.class);
   }

   /**
    * <T>获得下一个存储标识。</T>
    * 
    * @return
    */
   public String nextStoreId(){
      return RHex.toString(_storeId++, 8);
   }
}
