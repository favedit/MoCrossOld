/*
 * @(#)ILogicScheduleConsole.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.schedule;

/**
 * <T>计划控制台接口</T>
 * <P>主要充当控制层，提供程序执行的入口和进行一些初始化设置</P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/30
 */
public interface ILogicScheduleConsole
{
   /**
    * <T>初始化数据线程和执行线程</T>
    * <P>初始化线程的间隔时间，并将计划列表设置到各个线程</P>
    */
   void initializeThreads();

   /**
    * <T>执行最后处理，停止线程。</T>
    * <P>执行正在进行的处理，停止所有线程。</P>
    */
   void releaseThreads();
}
