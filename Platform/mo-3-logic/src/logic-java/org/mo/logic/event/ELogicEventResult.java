/*
 * @(#)ELogicEventResult.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.event;

/**
 * <T>事件结果的枚举类型</T>
 * <P>根据事件结果类型可设置为以下几种：<B/>
 * <UL>
 *    <L value='Warning'>警告</L>
 *    <L value='Error'>错误</L>
 *    <L value='Info'>信息</L>
 *    <L value='Success'>成功</L>
 *    <L value='Timeout'>超时</L>
 *    </UL>
 * </P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/27
 */
public enum ELogicEventResult{
   /**
    * <T>警告</T>
    */
   Warning("W"),
   /**
    * <T>错误</T>
    */
   Error("E"),
   /**
    * <T>信息</T>
    */
   Info("I"),
   /**
    * <T>成功</T>
    */
   Success("S"),
   /**
    * <T>超时</T>
    */
   Timeout("T");
   // 事件结果值
   private String _value;

   /**
    * <T>构造函数</T>
    * <P>根据字符串值（简称）构造事件结果，其中：<B/>
    * <UL>
    *    <L value='W'>Warning</L>
    *    <L value='E'>Error</L>
    *    <L value='I'>Info</L>
    *    <L value='S'>Success</L>
    *    <L value='T'>Timeout</L>
    * </UL>
    * </P>
    * 
    * @param value 用于构造处理类型的字符串(简称)
    */
   private ELogicEventResult(String value){
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
