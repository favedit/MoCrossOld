/*
 * @(#)ILogicEventConsole.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.event;

/**
 * <T>事件处理控制处理接口</T>
 * <P>主要充当控制层，提供程序执行的入口和进行一些初始化设置</P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/27
 */
public interface ILogicEventConsole
{
   /**
    * <T>初始化线程</T>
    * <P>取数据线程<R link='jv:org.mo.logic.event|FLogicEventQueryThread'>FLogicEventQueryThread</R></B>
    * 主要从数据库查询事件</P>
    */
   void initialize();

   /**
    * <T>停止线程</T>
    * <P>取数据线程<R link='jv:org.mo.logic.event|FLogicEventQueryThread'>FLogicEventQueryThread</R></B>
    * 主要从数据库查询事件</P>
    */
   void release();
}
