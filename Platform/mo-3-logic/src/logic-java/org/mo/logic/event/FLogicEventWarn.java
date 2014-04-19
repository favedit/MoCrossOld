/*
 * @(#)FLogicEventWarn.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.event;

/**
 * <T>事件警告信息容器</T>
 * <P>记录事件警告类信息</P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/27
 */
public class FLogicEventWarn
      extends FAbstractLogicEventResult
{
   // 定义枚举类型的结果类型值
   protected static ELogicEventResult _resultType = ELogicEventResult.Warning;

   /** 
    * <T>获得结果类型的值</T>
    * <P>该结果类型表示事件在执行的过程中出现了警告信息</P>
    * 
    * @return 返回结果类型
    */
   public ELogicEventResult resultType(){
      return _resultType;
   }
}
