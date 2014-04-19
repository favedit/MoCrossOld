package org.mo.com.net.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

//============================================================
// <T>邮件认证。</T>
//============================================================ 
public class TMailAuthenticator
      extends Authenticator
{
   // 邮件属性
   protected TMailProperties _properties;

   //============================================================
   // <T>构造邮件认证。</T>
   //
   // @param properties 邮件属性
   //============================================================ 
   public TMailAuthenticator(TMailProperties properties){
      _properties = properties;
   }

   //============================================================
   // <T>获得密码认证信息。</T>
   //
   // @return 密码认证信息
   //============================================================ 
   @Override
   public PasswordAuthentication getPasswordAuthentication(){
      return new PasswordAuthentication(_properties.loginPassport(), _properties.loginPassword());
   }
}
