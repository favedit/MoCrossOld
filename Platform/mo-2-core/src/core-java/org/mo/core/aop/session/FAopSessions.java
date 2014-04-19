package org.mo.core.aop.session;

import org.mo.com.lang.FDictionary;

//============================================================
// <T>AOP会话集合。</T>
//============================================================
public class FAopSessions
      extends FDictionary<FAopSession>
{
   //============================================================
   // <T>构造AOP会话集合。</T>
   //============================================================
   public FAopSessions(){
      super(FAopSession.class);
   }
}
