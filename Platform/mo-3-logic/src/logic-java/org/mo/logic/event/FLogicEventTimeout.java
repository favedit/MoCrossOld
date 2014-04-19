/*
 * @(#)FLogicEventTimeout.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.event;

/**
 * <T>事件线程运行时间超出</T>
 * <P>记录事件事件超出信息</P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/27
 */
public class FLogicEventTimeout
      extends FAbstractLogicEventResult
{
   // 定义枚举类型的结果类型值
   protected static ELogicEventResult _resultType = ELogicEventResult.Timeout;

   /** 
    * <T>获得结果类型的值</T>
    * <P>该结果类型表示事件执行失败，出现了超时的情况</P>
    * 
    * @return 返回结果类型
    */
   public ELogicEventResult resultType(){
      return _resultType;
   }
}
