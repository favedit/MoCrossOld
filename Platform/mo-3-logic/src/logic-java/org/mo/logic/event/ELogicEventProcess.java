/*
 * @(#)ELogicEventProcess.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.event;

/**
 * <T>事件处理类型枚举类</T>
 * <P>根据事件处理类型设定为以下几类：<B/>
 * <UL>
 *    <L value='Java'>java端处理</L>
 *    <L value='PlSql'>PlSql端处理</L>
 *    <L value='Execute'>Execute处理</L>
 * </UL>
 * </P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/2
 */
public enum ELogicEventProcess{
   /**
    * <T>java端处理</T>
    */
   Java("J"),
   /**
    * <T>PlSql端处理</T>
    */
   PlSql("P"),
   /**
    * <T>Execute可执行文件</T>
    */
   Execute("E");
   // 事件处理整数值
   private String _value;

   /**
    * <T>构造函数</T>
    * <P>根据字符串值构造事件处理，其中：<B/>
    * <UL>
    *    <L value='J'>java</L>
    *    <L value='P'>plsql</L>
    *    <L value='E'>execute</L>
    * </UL>
    * </P>
    * 
    * @param value 用于构造处理类型的字符串
    */
   private ELogicEventProcess(String value){
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
