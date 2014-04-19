/*
 * @(#)IWebMessageAction.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.game.editor.face.apl.message;

import org.mo.com.message.FErrorMessage;
import org.mo.com.message.FFatalMessage;
import org.mo.com.message.FMessages;
import org.mo.com.message.IMessage;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.face.ALink;
import org.mo.web.core.action.common.FWebMessagePage;
import org.mo.web.core.webform.IWebFormConsole;
import org.mo.web.protocol.context.IWebContext;

public class FWebMessageAction
      implements
         IWebMessageAction{

   public static String PAGE_ERROR = "LogicError";

   public static String PAGE_FATAL = "LogicFatal";

   @ALink
   protected IWebFormConsole _webformConsole;

   @Override
   public String error(IWebContext context,
                       FWebMessagePage page){
      // 获得错误信息
      FErrorMessage errorMessage = context.messages().message(FErrorMessage.class);
      // 设置页面信息
      page.setType("E");
      page.setCode(errorMessage.code());
      page.setMessage(errorMessage.message());
      page.setDescription(errorMessage.description());
      // 清除消息信息
      context.messages().clear();
      // 显示页面
      return PAGE_ERROR;
   }

   @Override
   public String fatal(IWebContext context,
                       FWebMessagePage page){
      // 获得错误信息
      FFatalMessage fatalMessage = context.messages().message(FFatalMessage.class);
      // 设置页面信息
      page.setType("F");
      page.setCode(fatalMessage.code());
      page.setMessage(fatalMessage.message());
      page.setDescription(fatalMessage.description());
      // 清除消息信息
      context.messages().clear();
      // 显示页面
      return PAGE_FATAL;
   }

   @Override
   public String info(IWebContext context,
                      FWebMessagePage page){
      FXmlNode message = new FXmlNode("Info");
      FMessages messages = context.messages();
      for(IMessage item : messages){
         message.push(item.convertToNode());
      }
      page.setMessages(message);
      context.messages().clear();
      return "Information";
   }

   @Override
   public String timeout(IWebContext context,
                         FWebMessagePage page){
      context.messages().clear();
      return "Timeout";
   }

   @Override
   public String warn(IWebContext context,
                      FWebMessagePage page){
      FXmlNode message = new FXmlNode("Warn");
      FMessages messages = context.messages();
      for(IMessage item : messages){
         message.push(item.convertToNode());
      }
      page.setMessages(message);
      context.messages().clear();
      return "Warning";
   }
}
