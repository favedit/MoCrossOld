/*
 * @(#)IEvent.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.event;

import org.mo.com.lang.FError;

/**
 * <p>事件对象接口</p>
 * 
 * @author ALEX
 * @version 1.00 - 2005/10/24
 */
public interface IEvent
{
   /**
    * <p>执行事件的逻辑</p>
    * <p>create date:2005/10/24</p>
    *
    * @exception FError 应用程序例外
    */
   void execute();
}
