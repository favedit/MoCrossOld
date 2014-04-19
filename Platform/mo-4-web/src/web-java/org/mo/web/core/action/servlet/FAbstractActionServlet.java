package org.mo.web.core.action.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RString;
import org.mo.com.lang.cultrue.FCulture;
import org.mo.com.lang.cultrue.RCulture;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.RAop;
import org.mo.core.bind.IBindConsole;
import org.mo.web.core.action.IActionConsole;
import org.mo.web.core.session.IWebSession;
import org.mo.web.core.session.IWebSessionConsole;
import org.mo.web.protocol.common.IWebServlet;
import org.mo.web.protocol.context.FWebContext;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebResponse;

//============================================================
// <T>页面处理模块。</T>
// <P>1. 初始化数据，创建全局应用管理对象。</P>
// <P>2. 根据页面请求，执行相应业务处理。</P>
//============================================================
public abstract class FAbstractActionServlet
      extends HttpServlet
      implements
         IWebServlet
{
   // 日志输出接口
   private static final ILogger _logger = RLogger.find(FAbstractActionServlet.class);

   // 序列化标识
   private static final long serialVersionUID = 1L;

   // 页面命令管理器
   protected IActionConsole _actionConsole;

   // 数据绑定管理器
   protected IBindConsole _bindConsole;

   // 页面线程管理器
   protected IWebSessionConsole _sessionConsole;

   //============================================================
   // <T>初始化网络应用程序。</T>
   //
   // @param config 页面设置对象
   //============================================================
   @Override
   public void init(ServletConfig config) throws ServletException{
      super.init(config);
      try{
         // 设置关联
         _bindConsole = RAop.find(IBindConsole.class);
         _actionConsole = RAop.find(IActionConsole.class);
         _sessionConsole = RAop.find(IWebSessionConsole.class);
         // 初始化设置
         initialize(config);
      }catch(Exception e){
         _logger.error(this, "init", e);
      }
   }

   //============================================================
   // <T>响应网页的Get访问请求。</T>
   //
   // @param request 页面请求对象
   // @param response 页面响应对象
   //============================================================
   @Override
   protected void doGet(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException{
      process(IWebServlet.METHOD_GET, request, response);
   }

   //============================================================
   // <T>响应网页的Post访问请求。</T>
   //
   // @param request 页面请求对象
   // @param response 页面响应对象
   //============================================================
   @Override
   protected void doPost(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException{
      process(IWebServlet.METHOD_POST, request, response);
   }

   //============================================================
   // <T>网页请求逻辑对象执行开始。</T>
   //
   // @param httpRequest 页面请求对象
   // @param httpResponse 页面响应对象
   //============================================================
   public void process(String type,
                       HttpServletRequest httpRequest,
                       HttpServletResponse httpResponse){

      String redirect = null;
      FWebContext context = null;
      IWebSession session = null;
      FCulture culture = null;
      Exception exception = null;
      long forwardBegin = 0;
      long forwardEnd = 0;
      long startTime = System.currentTimeMillis();
      try{
         // 建立会话对象
         String httpSessionId = httpRequest.getSession().getId();
         session = _sessionConsole.build(httpSessionId);
         session.referIncrease();
         // 设置语言编码
         culture = session.culture();
         String language = culture.countryLanguage();
         String encoding = culture.countryEncoding();
         RCulture.link(culture);
         if(_logger.debugAble()){
            _logger.debug(this, "process", "Do{1} Begin - [{2}] (lang={3}, charset={4})", type, httpRequest.getRequestURI(), language, encoding);
         }
         httpRequest.setCharacterEncoding(encoding);
         httpResponse.setCharacterEncoding(encoding);
         httpResponse.setContentType("text/html; charset=" + encoding);
         // 绑定线程对象
         context = new FWebContext(session, httpRequest, httpResponse);
         if(_logger.debugAble()){
            _logger.debug(this, "process", "Build context. (context={1})", context.dump());
         }
         _bindConsole.bind(IWebContext.class, context);
         _bindConsole.bind(IWebSession.class, session);
         redirect = process(context);
         // 建立输出内容
         IWebResponse response = context.response();
         FAttributes heads = response.heads();
         int count = heads.count();
         for(int n = 0; n < count; n++){
            httpResponse.setHeader(heads.name(n), heads.value(n));
         }
         // 设置Cookie
         if(context.testCookieChanged()){
            IAttributes cookies = context.cookies();
            if(_logger.debugAble()){
               _logger.debug(this, "process", "Set cookies. {cookies={1}}", cookies.dump());
            }
            Cookie cookie = new Cookie("MOCK", cookies.pack().toString());
            cookie.setMaxAge(60 * 60 * 24 * 365);
            httpResponse.addCookie(cookie);
         }
         // 画面转向
         if(!IActionResult.NO_REDIRECT.equals(redirect)){
            String uri = RString.nvl(redirect, context.requestUri());
            if(uri.endsWith(IActionConstant.WEB_ACTION)){
               uri = uri.substring(0, uri.length() - IActionConstant.WEB_ACTION_LEN);
            }else if(uri.endsWith(IActionConstant.WEB_PAGE)){
               uri = uri.substring(0, uri.length() - IActionConstant.WEB_PAGE_LEN);
            }
            if(!uri.endsWith(IActionConstant.JSP_PAGE)){
               uri += IActionConstant.JSP_PAGE;
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(uri);
            if(null != dispatcher){
               // 画面转向
               forwardBegin = System.currentTimeMillis();
               dispatcher.forward(httpRequest, httpResponse);
               forwardEnd = System.currentTimeMillis();
               redirect = uri;
            }
         }
      }catch(Exception e){
         exception = e;
      }finally{
         // 清空关联信息
         _bindConsole.clear();
         // 释放线程绑定对象
         if(null != culture){
            RCulture.unlink(culture);
         }
         // 释放会话引用
         if(null != session){
            session.referDecrease();
         }
         // 执行日志输出
         long endTime = System.currentTimeMillis();
         if(null != exception){
            _logger.error(this, "process", exception);
         }else if(_logger.debugAble()){
            _logger.debug(this, "process", endTime - startTime, "Do{1} End [{2}ms] forward={3}", type, forwardEnd - forwardBegin, redirect);
         }
      }
   }
}
