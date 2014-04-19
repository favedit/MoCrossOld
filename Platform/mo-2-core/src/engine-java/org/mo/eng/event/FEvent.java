/*
 * @(#)FEvent.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.event;

import org.mo.com.lang.FError;

/**
 * <p>事件基类</p>
 * <p>1. 执行事件逻辑</p>
 *
 * @author ALEX
 * @version 1.00 - 2005/10/24
 */
public abstract class FEvent
      implements
         IEvent
{
   /**
    * <p>事件是否可以被缓冲</p>
    * <p>create date:2005/10/24</p>
    *
    * @return TRUE：是<br>FALSE：否
    */
   public boolean useCache(){
      return false;
   }

   /**
    * <p>执行事件的逻辑</p>
    * <p>create date:2005/10/24</p>
    *
    * @exception FError 应用程序例外
    */
   @Override
   public abstract void execute();
}
