package org.mo.web.core.session;

import org.mo.eng.session.ISessionConsole;

//============================================================
// <T>网络线程控制台接口。</T>
//============================================================
public interface IWebSessionConsole
      extends
         ISessionConsole
{
   //============================================================
   // <T>建立线程对象。</T>
   //
   // @param sessionId 线程标识
   // @return 线程对象
   //============================================================
   IWebSession build(String sessionId);
}
