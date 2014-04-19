/*
 * @(#)FLogicEvents.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.event;

import org.mo.com.lang.FObjects;

/**
 * <T>事件容器的集合</T>
 * <P>所有事件都放置在该集合里</P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/27
 */
public class FLogicEvents
      extends FObjects<ILogicEvent>
{
   /**
    * <T>super父类的方法</T>
    * <P>通过super可访问父类所有方法</P>
    */
   public FLogicEvents(){
      super(ILogicEvent.class);
   }
}
