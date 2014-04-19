/*
 * @(#)FValidMessage.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.com.message;

/**
 * <p>应用程序检查消息类</p>
 * 
 * @author ALEX
 */
public class FValidMessage
      extends MMessage
{
   private static String NAME = "Valid";

   /**
    * <p>创建应用程序检查消息的实例</p>
    *
    */
   public FValidMessage(){
   }

   /**
    * <p>创建应用程序检查消息的实例</p>
    *
    * @param message 消息内容
    */
   public FValidMessage(String message){
      super(message);
   }

   /**
    * <p>创建应用程序检查消息的实例</p>
    *
    * @param message 消息内容
    * @param params 消息的参数
    */
   public FValidMessage(String message,
                        String... params){
      super(message, params);
   }

   @Override
   public String name(){
      return NAME;
   }
}
