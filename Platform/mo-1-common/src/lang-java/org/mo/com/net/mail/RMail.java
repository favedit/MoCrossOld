package org.mo.com.net.mail;

import javax.mail.Session;

//============================================================
// <T>邮件处理的工具类。</T>
//============================================================ 
public class RMail
{
   //============================================================
   // <T>查找邮件会话。</T>
   //
   // @param properties 邮件属性
   // @return 邮件会话
   //============================================================ 
   public static FMailSession findSession(TMailProperties properties){
      // 创建邮件的线程管理对象 
      FMailSession session = new FMailSession();
      if(properties.isAuthentic()){
         // 如果邮箱是SMTP验证的，需要添加认证方式
         TMailAuthenticator authenticator = new TMailAuthenticator(properties);
         session._session = Session.getInstance(properties.makeProperties(), authenticator);
      }else{
         // 无认证的邮件服务器
         session._session = Session.getInstance(properties.makeProperties());
      }
      return session;
   }
}
