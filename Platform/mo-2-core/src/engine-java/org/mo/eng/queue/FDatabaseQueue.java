/*
 * @(#)FDatabaseQueue.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.queue;

import org.mo.com.lang.FError;

/**
 * <p>事件基类</p>
 * <p>1. 执行事件逻辑</p>
 *
 * @author ALEX
 * @version 1.00 - 2005/10/26
 */
public abstract class FDatabaseQueue
      extends FQueue
{
   /**
    * <p>执行事件的逻辑</p>
    * <p>create date:2005/02/18</p>
    *
    * @return TRUE：成功<br>FALSE：失败
    * @exception FError 应用程序例外
    */
   @Override
   public boolean execute() throws FError{
      return true;
   }
   /**
    * <p>执行事件的逻辑</p>
    * <p>create date:2005/02/18</p>
    *
    * @param oProperty 属性列表
    * @param oMessage 执行消息列表
    * @param sContent 执行内容
    * @return TRUE：成功<br>FALSE：失败
    * @exception FError 应用程序例外
    */
   //   public abstract boolean execute(FStringList oProperty, FStringList oMessage, String sContent) throws FError;
}
