package org.mo.web.core.redirect;

import org.mo.com.lang.FError;
import org.mo.core.monitor.common.FFileMonitor;

public class FWebRedirectMonitor
      extends FFileMonitor
{
   // 关联的请求转向控制台
   @SuppressWarnings("unused")
   private FWebRedirectConsole m_oConsole = null;

   /**
    * <p>创建请求转向监视器的实例</p>
    * <p>create date:2005/02/18</p>
    *
    * @param oAliasConsole 别称控制台
    */
   public FWebRedirectMonitor(FWebRedirectConsole oConsole,
                              String sFileName){
      super(sFileName);
      m_oConsole = oConsole;
   }

   /**
    * <p>响应发生变动的文档</p>
    * <p>create date:2005/02/18</p>
    *
    * @param sFileName 文件名称
    * @return TRUE：成功<br>FALSE：失败
    * @exception FError 应用程序例外
    */
   @Override
   public void onFileChanged() throws FError{
      //m_oConsole.loadRedirect();
   }
}
