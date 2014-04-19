package org.mo.web.core.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mo.com.lang.cultrue.RCulture;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.RAop;
import org.mo.core.bind.IBindConsole;
import org.mo.web.core.servlet.common.FWebServletResponse;
import org.mo.web.core.servlet.common.IWebServletResponse;
import org.mo.web.core.session.IWebSession;
import org.mo.web.core.session.IWebSessionConsole;
import org.mo.web.protocol.common.IWebServlet;
import org.mo.web.protocol.context.FWebContext;
import org.mo.web.protocol.context.IWebContext;

/**
 * <p>页面处理模块</p>
 * <p>1. 初始化数据，创建全局应用管理对象</p>
 * <p>2. 根据页面请求，执行相应业务处理</p>
 *
 * @author maocy
 */
public abstract class FAbstractWebServletProcess
      extends HttpServlet
{
   private static ILogger _logger = RLogger.find(FAbstractWebServletProcess.class);

   private static final long serialVersionUID = 1L;

   protected IBindConsole _bindConsole;

   protected IWebServletConsole _servletConsole;

   protected IWebSessionConsole _sessionConsole;

   /**
   * <p>响应网页的Get请求。</p>
   *
   * @param request 网络请求对象
   * @param response 网络响应对象
   */
   @Override
   protected void doGet(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException,
                                                     IOException{
      process(IWebServlet.METHOD_GET, request, response);
   }

   /**
   * <p>响应网页的Post请求。</p>
   *
   * @param request 网络请求对象
   * @param response 网络响应对象
   */
   @Override
   protected void doPost(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException,
                                                      IOException{
      process(IWebServlet.METHOD_POST, request, response);
   }

   /**
   * <p>初始化网络应用程序。</p>
   *
   * @param config 网络设置对象
   */
   @Override
   public void init(ServletConfig config) throws ServletException{
      super.init(config);
      try{
         _bindConsole = RAop.find(IBindConsole.class);
         _sessionConsole = RAop.find(IWebSessionConsole.class);
         _servletConsole = RAop.find(IWebServletConsole.class);
         initialize(config);
      }catch(Exception e){
         _logger.error(this, "init", e);
      }
   }

   public void initialize(ServletConfig config){
   }

   /**
   * <p>网页请求逻辑对象执行开始。</p>
   *
   * @param httpRequest 网络请求对象
   * @param httpResponse 网络响应对象
   */
   public void process(String type,
                       HttpServletRequest httpRequest,
                       HttpServletResponse httpResponse){
      String redirect = null;
      FWebContext context = null;
      IWebSession session = null;
      long startTime = System.currentTimeMillis();
      try{
         // Session
         session = _sessionConsole.build(httpRequest.getSession().getId());
         session.referIncrease();
         // 设置语言编码
         String language = session.culture().countryLanguage();
         String encoding = session.culture().countryEncoding();
         RCulture.link(session.culture());
         if(_logger.debugAble()){
            _logger.debug(this, "process", "Do{0} Begin - [{1}] (lang={2}, charset={3})", type, httpRequest.getRequestURI(), language, encoding);
         }
         // Build context
         httpRequest.setCharacterEncoding(encoding);
         context = new FWebContext(session, httpRequest, httpResponse);
         httpResponse.setCharacterEncoding(encoding);
         if(_logger.debugAble()){
            _logger.debug(this, "process", "Build context: {0}", context.dump());
         }
         _bindConsole.bind(IWebContext.class, context);
         _bindConsole.bind(IWebSession.class, session);
         // 查找服务类型
         String uri = context.requestUri();
         if(uri.endsWith(IWebServletConstant.WEB_SERVLET)){
            uri = uri.substring(0, uri.length() - 3);
            int find = uri.lastIndexOf('/');
            if(find != -1){
               uri = uri.substring(find + 1);
            }
            // 执行逻辑过程
            FWebServletResponse webResponse = new FWebServletResponse(httpResponse);
            process(uri, context, webResponse);
            httpResponse.flushBuffer();
         }
      }catch(Exception e){
         _logger.error(this, "process", e);
      }finally{
         if(session != null){
            session.referDecrease();
         }
         try{
            RCulture.unlink();
            _bindConsole.clear();
         }catch(Exception e){
            _logger.error(this, "process", e);
         }
         long endTime = System.currentTimeMillis();
         if(_logger.debugAble()){
            _logger.debug(this, "process", "Do{0} End - [{1}ms] {2}", type, endTime - startTime, redirect);
         }
      }
   }

   public abstract void process(String name,
                                IWebContext context,
                                IWebServletResponse response);
}
