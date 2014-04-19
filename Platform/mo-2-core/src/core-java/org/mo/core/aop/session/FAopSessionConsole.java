package org.mo.core.aop.session;

import org.mo.com.console.FConsole;

//============================================================
// <T>AOP会话集合。</T>
//============================================================
public class FAopSessionConsole
      extends FConsole
{
   // 会话集合
   protected FAopSessions _sessions = new FAopSessions();

   //============================================================
   // <T>构造AOP会话集合。</T>
   //============================================================
   public FAopSessionConsole(){
   }

   //============================================================
   // <T>根据代码查找会话。</T>
   //
   // @param code 代码
   // @return 会话
   //============================================================
   public FAopSession find(String code){
      FAopSession session = _sessions.get(code);
      if(null == session){
         session = new FAopSession();
         _sessions.set(code, session);
      }
      return session;
   }
}
