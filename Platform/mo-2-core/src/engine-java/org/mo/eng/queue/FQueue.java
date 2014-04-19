/*
 * @(#)FQueue.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.queue;

import org.mo.com.lang.FError;
import org.mo.com.lang.generic.INamed;

/**
 * <p>队列基类</p>
 * <p>1. 执行事件逻辑</p>
 *
 * @author ALEX
 * @version 1.00 - 2005/10/26
 */
public abstract class FQueue
      implements
         INamed<String>
{
   private String _name = null;

   /**
    * <p>创建队列的实例</p>
    * <p>create date:2005/11/03</p>
    *
    */
   public FQueue(){
   }

   /**
    * <p>创建队列的实例</p>
    * <p>create date:2005/11/03</p>
    *
    * @param name 名称
    */
   public FQueue(String name){
      _name = name;
   }

   /**
    * <p>执行事件的逻辑</p>
    * <p>create date:2005/02/18</p>
    *
    * @return TRUE：成功<br>FALSE：失败
    * @exception FError 应用程序例外
    */
   public abstract boolean execute();

   @Override
   public String name(){
      return _name;
   }
}
