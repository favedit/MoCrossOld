package org.mo.eng.mail;

import org.mo.com.console.FConsole;
import org.mo.com.lang.FDictionary;
import org.mo.com.net.mail.FMail;
import org.mo.com.net.mail.FMailSession;
import org.mo.com.net.mail.RMail;
import org.mo.com.net.mail.TMailProperties;
import org.mo.core.aop.face.AProperty;

//============================================================
// <T>邮件控制台。</T>
//============================================================ 
public class FMailConsole
      extends FConsole
      implements
         IMailConsole
{
   // 邮件主机
   @AProperty
   protected String _smtpHost;

   // 邮件端口
   @AProperty
   protected int _smtpPort;

   // 邮件认证
   @AProperty
   protected boolean _smtpAuthentic;

   // 邮件登录账号
   @AProperty
   protected String _smtpPassport;

   // 邮件登录密码
   @AProperty
   protected String _smtpPassword;

   // 邮件会话
   protected FMailSession _session;

   // 邮件组
   protected FDictionary<FMailGroup> _groups = new FDictionary<FMailGroup>(FMailGroup.class);

   //============================================================
   // <T>发送邮件。</T>
   //
   // @return 处理结果
   //============================================================
   public void initialize(){
      TMailProperties properties = new TMailProperties();
      // 服务器地址
      properties.setHost(_smtpHost);
      // 发送端口
      properties.setPort(_smtpPort);
      // 是否需要身份认证
      properties.setAuthentic(_smtpAuthentic);
      // 用户名
      properties.setLoginPassport(_smtpPassport);
      // 密码
      properties.setLoginPassword(_smtpPassword);
      // 创建会话
      _session = RMail.findSession(properties);
   }

   //============================================================
   // <T>根据名称获得邮件组。</T>
   //
   // @param name 名称
   // @return 邮件组
   //============================================================
   public FMailGroup findGroup(String name){
      return _groups.get(name);
   }

   //============================================================
   // <T>注册邮件组。</T>
   //
   // @param group 邮件组
   //============================================================
   public void registerGroup(FMailGroup group){
      _groups.set(group.name(), group);
   }

   //============================================================
   // <T>发送邮件。</T>
   //
   // @param mail 邮件
   // @return 处理结果
   //============================================================
   public boolean send(FMail mail){
      return _session.send(mail);
   }

   //============================================================
   // <T>发送邮件组。</T>
   //
   // @param groupName 邮件组
   // @param mail 邮件
   // @return 处理结果
   //============================================================
   public boolean sendGroup(String groupName,
                            FMail mail){
      FMailGroup group = findGroup(groupName);
      mail.addTo(group.to());
      mail.addCc(group.cc());
      mail.addBcc(group.bcc());
      return _session.send(mail);
   }
}
