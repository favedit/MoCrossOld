/*
 * @(#)FLogicEventInfo.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.event;

/**
 * <T>结果（信息）对象</T>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/27
 */
public class FLogicEventInfo
      extends FAbstractLogicEventResult
{
   // 定义枚举类型的结果类型值
   protected static ELogicEventResult _resultType = ELogicEventResult.Info;

   /** 
    * <T>获得结果类型</T>
    * <P>该类提供了对事件结果中信息的处理方法</P>
    * 
    * @return 结果类型（信息）
    */
   public ELogicEventResult resultType(){
      return _resultType;
   }
}
