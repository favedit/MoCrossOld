package org.mo.web.protocol.common;

import javax.servlet.ServletConfig;
import org.mo.web.protocol.context.IWebContext;

//============================================================
// <T>页面处理接口。</T>
//============================================================
public interface IWebServlet
{
   // 获得方法
   String METHOD_GET = "Get";

   // 提交方法
   String METHOD_POST = "Post";

   //============================================================
   // <T>初始化数据，创建全局应用管理对象。</T>
   //
   // @param config 页面设置对象
   //============================================================
   void initialize(ServletConfig config);

   //============================================================
   // <T>网页请求逻辑对象执行开始。</T>
   //
   // @param context 网页环境
   //============================================================
   String process(IWebContext context);
}
