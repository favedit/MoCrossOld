package org.mo.web.core.servlet;

import javax.servlet.ServletConfig;
import org.mo.web.core.servlet.common.IWebServletResponse;
import org.mo.web.protocol.context.IWebContext;

/**
 * <p>页面处理模块</p>
 * <p>1. 初始化数据，创建全局应用管理对象</p>
 * <p>2. 根据页面请求，执行相应业务处理</p>
 *
 * @author ALEX
 */
public class FWebServletProcess
      extends FAbstractWebServletProcess
{

   // 序列化标志
   private static final long serialVersionUID = 1L;

   @Override
   public void initialize(ServletConfig config){
   }

   /**
    * <p>网页请求逻辑对象执行开始。</p>
    *
    * @param request 网络请求对象
    * @param response 网络响应对象
    */
   @Override
   public void process(String name,
                       IWebContext context,
                       IWebServletResponse response){
      _servletConsole.execute(name, context, response);
   }
}
