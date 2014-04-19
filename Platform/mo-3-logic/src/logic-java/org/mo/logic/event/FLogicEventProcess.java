/*
 * @(#)FLogicEventProcess.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.event;

import org.mo.com.system.FProcess;

/**
 * <T>事件处理类</T>
 * <P>该类继承了<R link='jv:org.mo.com.system|FProcess'>FProcess处理类</R>主要用来<B/>
 * 处理命令行调用</P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/27
 */
public class FLogicEventProcess
      extends FProcess
{
   // 事件运行线程
   private FLogicEventRunner _runner;

   // 事件对象
   private ILogicEvent _event;

   /* (non-Javadoc)
    * @see org.mo.com.system.FProcess#onRelease()
    */
   @Override
   public void onRelease(){
      _runner.doEventStop(_event, null);
   }

   /**
    * <T>设置事件对象</T>
    * 
    * @param event 事件对象
    */
   public void setEvent(ILogicEvent event){
      _event = event;
   }

   /**
    * <T>设置事件运行线程</T>
    * 
    * @param runner 事件运行线程
    */
   public void setRunner(FLogicEventRunner runner){
      _runner = runner;
   }
}
