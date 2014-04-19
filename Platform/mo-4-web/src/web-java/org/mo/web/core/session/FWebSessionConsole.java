package org.mo.web.core.session;

import org.mo.core.aop.face.ALink;
import org.mo.eng.culture.ICultureConsole;
import org.mo.eng.security.IPermissionConsole;
import org.mo.eng.session.FSessionConsole;
import org.mo.eng.session.common.FSession;
import org.mo.eng.session.common.FSessionWorker;
import org.mo.web.core.container.IWebContainerConsole;

//============================================================
// <T>网络线程控制台。</T>
//============================================================
public class FWebSessionConsole
      extends FSessionConsole
      implements
         IWebSessionConsole
{
   // 文化控制台
   @ALink
   protected ICultureConsole _cultureConsole;

   // 表单控制台
   @ALink
   protected IWebContainerConsole _formConsole;

   // 权限控制台
   @ALink
   protected IPermissionConsole _permissionConsole;

   // 线程标识的字段列表
   protected String[] _fields;

   // 线程信息的标志
   protected boolean _uriFlag = false;

   //============================================================
   // <T>创建会话对象。</T>
   //
   // @param worker 会话工作器
   // @return 会话对象
   //============================================================
   @Override
   protected FSession createSession(FSessionWorker worker){
      FWebSession session = (FWebSession)super.createSession(worker);
      session.setPermission(_permissionConsole.createPermission());
      session.culture().assign(_cultureConsole.culture());
      return session;
   }

   //============================================================
   // <T>建立线程对象。</T>
   //
   // @param sessionId 线程标识
   // @return 线程对象
   //============================================================
   @Override
   public IWebSession build(String sessionId){
      return sync(IWebSession.class, sessionId);
   }
}
