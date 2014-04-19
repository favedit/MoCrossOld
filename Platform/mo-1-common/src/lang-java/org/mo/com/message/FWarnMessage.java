/*
 * @(#)FWarnMessage.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.com.message;

/**
 * <p>应用程序警告消息�?</p>
 * 
 * @author ALEX
 */
public class FWarnMessage
      extends MMessage
{
   private static String NAME = "Warn";

   /**
    * <p>创建应用程序警告消息的实�?</p>
    *
    */
   public FWarnMessage(){
   }

   /**
    * <p>创建应用程序警告消息的实�?</p>
    *
    * @param message 消息内容
    */
   public FWarnMessage(String message){
      super(message);
   }

   /**
    * <p>创建应用程序警告消息的实�?</p>
    *
    * @param message 消息内容
    * @param params 消息
    */
   public FWarnMessage(String message,
                       String... params){
      super(message, params);
   }

   @Override
   public String name(){
      return NAME;
   }
}
