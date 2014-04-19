/*
 * @(#)FEventConsole.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.event;

import org.mo.com.io.FObjectFile;
import org.mo.com.lang.FError;
import org.mo.com.lang.RUuid;
import org.mo.core.aop.face.AProperty;

/**
 * <p>事件控制台</p>
 * <p>1. 执行列表中的事件逻辑，执行完后，从事件列表中删除</p>
 *
 * @author ALEX
 * @version 1.00 - 2005/10/24
 */
public class FEventConsole
      implements
         IEventConsole
{
   // 事件时间间隔
   @AProperty
   private final long _interval = 0;

   // 监视线程优先度
   @AProperty
   private final int _priority = 0;

   // 事件线程
   private FEventThread _thread = null;

   // 工作路径
   @AProperty
   private final String _workpath = null;

   /**
    * <p>初始化操作</p>
    * <p>建立事件线程</p>
    * <p>create date:2004/05/09</p>
    *
    * @return TRUE：成功<br>FALSE：失败
    * @exception FError 应用程序例外
    */
   public void initializeThread() throws FError{
      _thread = new FEventThread();
      _thread.setWorkPath(_workpath);
      _thread.setPriority(_priority);
      _thread.setInterval(_interval);
      _thread.start();
   }

   /**
    * <p>将一个事件加入到事件线程中</p>
    * <p>create date:2004/05/09</p>
    *
    * @param event 事件
    * @return TRUE：成功<br>FALSE：失败
    */
   @SuppressWarnings("resource")
   public void push(FEvent event){
      if(event.useCache()){
         FObjectFile file = new FObjectFile(_workpath + RUuid.makeUuid());
         file.writeObject(event);
      }else{
         _thread.push(event);
      }
   }
}
