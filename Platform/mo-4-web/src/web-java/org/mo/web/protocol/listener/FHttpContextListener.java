package org.mo.web.protocol.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.mo.com.io.RFile;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.system.RSystem;
import org.mo.core.aop.RAop;

//============================================================
// <T>环境内容变化监听类。</T>
// <P>环境内容变化时触发开始、结束事件</P>
//============================================================
public class FHttpContextListener
      implements
         ServletContextListener
{
   // 类目录
   private static String PATH_ROOT = "/WEB-INF/classes";

   //============================================================
   // <T>初始化环境内容。</T>
   //
   // @param event 事件对象
   //============================================================
   public void contextInitialized(ServletContextEvent event){
      // 获得启动设置文件名称
      String mode = RSystem.property("user.mobj.config");
      ServletContext context = event.getServletContext();
      String path = context.getRealPath(PATH_ROOT);
      String configFileName = RFile.makeFilename(path, mode + ".xml");
      // 设置类加载器
      ClassLoader loader = getClass().getClassLoader();
      RClass.setupClassLoader(loader);
      // 加载启动信息
      RAop.initialize(configFileName);
   }

   //============================================================
   // <T>释放环境内容。</T>
   //
   // @param event 事件对象
   //============================================================
   public void contextDestroyed(ServletContextEvent event){
      RAop.release();
   }
}
