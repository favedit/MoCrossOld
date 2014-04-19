/*
 * @(#)FLogicEventThreadPool.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.event;

import org.mo.com.system.FThreadPool;

/**
 * <T>事件连接池</T>
 * <P>把事件处理放入该连接池</P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/27
 */
public class FLogicEventThreadPool
      extends FThreadPool<FLogicEventRunner>
{
}
