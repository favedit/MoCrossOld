/*
 * @(#)IWebMessageAction.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.game.editor.face.apl.message;

import org.mo.web.core.container.AContainer;

import org.mo.web.core.action.common.FWebMessagePage;
import org.mo.web.protocol.context.IWebContext;

/**
 * <T>出错消息的处理页面。</T>
 * 
 * @author maocy
 * @version 1.00 - 2008/12/19
 */
public interface IWebMessageAction{

   /**
    * <T>转向到业务错误处理页面。</T>
    * 
    * @return 业务错误处理页面
    */
   String error(IWebContext context,
                @AContainer(name = "message") FWebMessagePage page);

   /**
    * <T>转向到系统错误处理页面。</T>
    * 
    * @return 系统错误处理页面
    */
   String fatal(IWebContext context,
                @AContainer(name = "message") FWebMessagePage page);

   /**
    * <T>转向到提示信息处理页面。</T>
    * 
    * @return 提示信息处理页面
    */
   String info(IWebContext context,
               @AContainer(name = "message") FWebMessagePage page);

   /**
    * <T>转向到超时错误处理页面。</T>
    * 
    * @return 超时错误处理页面
    */
   String timeout(IWebContext context,
                  @AContainer(name = "message") FWebMessagePage page);

   /**
    * <T>转向到警告信息处理页面。</T>
    * 
    * @return 警告信息处理页面
    */
   String warn(IWebContext context,
               @AContainer(name = "message") FWebMessagePage page);

}
