/*
 * @(#)FLogicEventError.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.event;

/**
 * <T>结果（错误）对象</T>
 * <P>该类提供了对事件执行过程中产生了错误的处理方法</P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/27
 */
public class FLogicEventError
      extends FAbstractLogicEventResult
{
   // 定义枚举类型的结果类型值
   protected static ELogicEventResult _resultType = ELogicEventResult.Error;

   /** 
    * <T>获得结果类型</T>
    * 
    * @return 结果类型（错误）
    */
   public ELogicEventResult resultType(){
      return _resultType;
   }
}
