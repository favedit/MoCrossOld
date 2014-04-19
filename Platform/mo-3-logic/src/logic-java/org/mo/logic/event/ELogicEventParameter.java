/*
 * @(#)ELogicEventProcess.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.event;

/**
 * <T>事件参数类型枚举类</T>
 * 
 * @author MAOCY
 * @version 1.00 - 2009/06/05
 */
public enum ELogicEventParameter{
   /**
    * <T>数据集类型</T>
    */
   Dataset("T"),
   /**
    * <T>行对象类型</T>
    */
   Row("R"),
   /**
    * <T>字符串</T>
    */
   String("S");
   // 事件处理整数值
   private String _value;

   /**
    * <T>构造函数</T>
    * <P>根据字符串值构造事件参数类型，其中：<B/>
    * <UL>
    *    <L value='S'>string</L>
    *    <L value='R'>row</L>
    *    <L value='T'>dataset</L>
    * </UL>
    * </P>
    * 
    * @param value 用于构造处理类型的字符串
    */
   private ELogicEventParameter(String value){
      _value = value;
   }

   /* (non-Javadoc)
    * @see java.lang.Enum#toString()
    */
   @Override
   public String toString(){
      return _value;
   }
}
