/*
 * @(#)FLogicScheduleTimes.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.schedule;

import org.mo.com.lang.FDictionary;

/**
 * <T>计划时间点对象容器</T>
 * <P>主要用于设置多组的计划时间点对象</P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/30
 */
public class FLogicScheduleTimes
      extends FDictionary<ILogicScheduleTime>
{
   /**
    * <T>构造函数</T>
    * <P>super父类方法，访问父类接口</P>
    */
   public FLogicScheduleTimes(){
      super(ILogicScheduleTime.class);
   }
}
