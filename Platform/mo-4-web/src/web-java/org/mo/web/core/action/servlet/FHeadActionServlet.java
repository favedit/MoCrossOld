/*
 * @(#)FUriActionServlet.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.core.action.servlet;

import javax.servlet.ServletConfig;
import org.mo.com.lang.RString;
import org.mo.core.aop.RAop;
import org.mo.web.core.action.IActionConsole;
import org.mo.web.core.service.servlet.FAbstractServiceServlet;
import org.mo.web.protocol.context.IWebContext;

/**
 * <p>页面处理模块</p>
 * <p>1. 初始化数据，创建全局应用管理对象</p>
 * <p>2. 根据页面请求，执行相应业务处理</p>
 *
 */
public class FHeadActionServlet
      extends FAbstractServiceServlet
{

   protected static String HEAD_ACTION = "action";

   // 序列化标志
   private static final long serialVersionUID = 1L;

   protected IActionConsole _console;

   /**
    * <p>初始化网络应用程序。</p>
    *
    * @param config 网络设置对象
    */
   public void initialize(ServletConfig config){
      _console = RAop.find(IActionConsole.class);
   }

   /**
    * <p>网页请求逻辑对象执行开始。</p>
    *
    * @param request 网络请求对象
    * @param response 网络响应对象
    */
   public String process(IWebContext context){
      String redirect = null;
      String action = context.head(HEAD_ACTION);
      if((null != action) && action.endsWith(IActionConstant.WEB_ACTION)){
         action = action.substring(0, action.length() - IActionConstant.WEB_ACTION_LEN);
         String result = _console.execute(context, action);
         if(!IActionResult.NO_REDIRECT.equals(result)){
            if(RString.isNotBlank(result)){
               action = action.substring(0, action.lastIndexOf("/"));
               redirect = action + "/" + result + IActionConstant.JSP_PAGE;
            }else{
               redirect = action + IActionConstant.JSP_PAGE;
            }
         }
      }
      return redirect;
   }
}
