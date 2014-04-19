/*
 * @(#)FLogicEventConsole.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.event;

import org.mo.core.aop.face.AProperty;

/**
 * <T>事件服务类</T>
 * <P>该类主要为系统提供事件机制的入口,并提供了初始化取数据线程的方法</P>
 * 
 * @history 090618 YANGH  添加release()函数，收到停止命令后停止运行。
 * @author HYKUN
 * @version 1.00 - 2008/10/27
 */
public class FLogicEventConsole
      implements
         ILogicEventConsole
{
   // 从配置文件获取属性值
   @AProperty
   protected int _interval;

   // 线程池对象
   protected FLogicEventThreadPool _pool;

   // 设置查询线程
   protected FLogicEventQueryThread _fetchEvents;

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEventConsole#initialize()
    */
   @Override
   public void initialize(){
      _fetchEvents = new FLogicEventQueryThread();
      _pool = new FLogicEventThreadPool();
      /// 设置线程的起调间隔
      _fetchEvents.setInterval(_interval);
      /// 启动线程
      _fetchEvents.start();
      /// 启动线程池
      _fetchEvents.startPool(_pool);
   }

   /* (non-Javadoc)
   * @see org.mo.logic.event.ILogicEventConsole#release()
   */
   @Override
   public void release(){
      // 设置线程停止
      _fetchEvents.notifyStop();
      // 停止线程池
      _pool.waitStop();
   }
}
