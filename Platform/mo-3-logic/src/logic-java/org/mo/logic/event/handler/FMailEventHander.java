package org.mo.logic.event.handler;

import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.net.mail.EMailContent;
import org.mo.com.net.mail.FMail;
import org.mo.com.net.mail.FMailSession;
import org.mo.com.net.mail.RMail;
import org.mo.com.net.mail.TMailProperties;
import org.mo.com.validator.RStringValidator;
import org.mo.core.aop.RAop;
import org.mo.core.aop.face.ALink;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.environment.IEnvironmentConsole;
import org.mo.eng.mail.IMailConsole;
import org.mo.eng.template.ITemplateConsole;
import org.mo.eng.template.ITemplateParser;
import org.mo.logic.event.ILogicEvent;
import org.mo.logic.store.IStoreConsole;

public class FMailEventHander
      implements
         IMailEventHander
{
   private static ILogger _logger = RLogger.find(FMailEventHander.class);

   public final static String PTY_BCC_USERS = "bcc_users";

   public final static String PTY_CC_USERS = "cc_users";

   public final static String PTY_CONFIG = "config";

   public final static String PTY_EVENT = "event";

   public final static String PTY_FROM_USER = "from_user";

   public final static String PTY_ATTACHMENT = "attachment_list";

   public final static String PTY_GROUP = "group";

   /**
    * <T>建立邮件信息的对象接口。</T>
    */
   public final static String PTY_MAIL_BUILDER = "mail_builder";

   public final static String PTY_PARAMETER = "parameter";

   public final static String PTY_SMTP_AUTHENTIC = "smtp_authentic";

   public final static String PTY_SMTP_HOST = "smtp_host";

   public final static String PTY_SMTP_PASSWORD = "smtp_password";

   public final static String PTY_SMTP_PORT = "smtp_port";

   public final static String PTY_SMTP_USER = "smtp_user";

   public final static String PTY_TEMPLATE_BODY = "template_body";

   public final static String PTY_TEMPLATE_HEAD = "template_head";

   public final static String PTY_TO_USERS = "to_users";

   public final static String PTY_TYPE = "type";

   // 数据服务对象
   protected IDatabaseConsole _databaseConsole;

   @ALink
   protected IEnvironmentConsole _environmentConsole;

   @ALink
   protected IMailConsole _mailConsole;

   @ALink
   protected IStoreConsole _storeConsole;

   @Override
   public void process(ILogicEvent event){
      // 输出调试信息
      String server = _environmentConsole.registers().get("server_process");
      if(_logger.debugAble()){
         _logger.debug(this, "process", "Event server :{0}", server);
         //         if(!event.groupConfig().isEmpty()){
         //            _logger.debug(this, "process", "Group config :{0}", event.groupConfig().dump());
         //         }
         //         if(!event.typeConfig().isEmpty()){
         //            _logger.debug(this, "process", "Type config  :{0}", event.typeConfig().dump());
         //         }
         //         if(!event.eventConfig().isEmpty()){
         //            _logger.debug(this, "process", "Event config :{0}", event.eventConfig().dump());
         //         }
         //         if(!event.parameters().isEmpty()){
         //            _logger.debug(this, "process", "Parameters   :{0}", event.parameters().dump());
         //         }
      }
      // 设置邮件服务器信息
      TMailProperties properties = new TMailProperties();
      String smtpHost = _environmentConsole.parseServer(server, event.parameter(PTY_SMTP_HOST));
      RStringValidator.checkEmpty(smtpHost, PTY_SMTP_HOST);
      properties.setHost(smtpHost);
      // 设置邮件服务器端口号
      String smtpPort = _environmentConsole.parseServer(server, event.parameter(PTY_SMTP_PORT));
      if(RString.isNotEmpty(smtpPort)){
         properties.setPort(Integer.parseInt(smtpPort));
      }
      // 设置邮件服务器的认证信息 
      String smtpAuthentic = _environmentConsole.parseServer(server, event.parameter(PTY_SMTP_AUTHENTIC));
      if(RBoolean.parse(smtpAuthentic)){
         properties.setAuthentic(true);
         // 设置邮件服务器的登录帐户
         String smtpUser = _environmentConsole.parseServer(server, event.parameter(PTY_SMTP_USER));
         RStringValidator.checkEmpty(smtpUser, PTY_SMTP_USER);
         properties.setLoginPassport(smtpUser);
         // 设置邮件服务器的登录密码 
         String smtpPassword = _environmentConsole.parseServer(server, event.parameter(PTY_SMTP_PASSWORD));
         RStringValidator.checkEmpty(smtpPassword, PTY_SMTP_PASSWORD);
         properties.setLoginPassword(smtpPassword);
      }else{
         properties.setAuthentic(false);
      }
      // 获得邮件服务线程
      FMailSession session = RMail.findSession(properties);
      // 创建邮件信息
      FMail mail = new FMail();
      // 设置邮件的发送者
      String fromUser = _environmentConsole.parseServer(server, event.parameter(PTY_FROM_USER));
      RStringValidator.checkEmpty(fromUser, PTY_FROM_USER);
      mail.setFrom(fromUser);
      // 设置邮件的收件人列表
      String toUsers = _environmentConsole.parseServer(server, event.parameter(PTY_TO_USERS));
      RStringValidator.checkEmpty(toUsers, PTY_TO_USERS);
      mail.addTo(toUsers);
      // 设置邮件的抄送人列表
      String ccUsers = _environmentConsole.parseServer(server, event.parameter(PTY_CC_USERS));
      if(RString.isNotEmpty(ccUsers)){
         mail.addCc(toUsers);
      }
      // 设置邮件的暗送人列表
      String bccUsers = _environmentConsole.parseServer(server, event.parameter(PTY_BCC_USERS));
      if(RString.isNotEmpty(bccUsers)){
         mail.addBcc(bccUsers);
      }
      // 设置邮件的附件
      String attachments = _environmentConsole.parseServer(server, event.parameter(PTY_ATTACHMENT));
      if(RString.isNotEmpty(attachments)){
         FStrings paths = new FStrings();
         paths.unpack(attachments);
         for(int n = 0; n < paths.count(); n++){
            FAttributes atts = new FAttributes();
            atts.unpack(paths.get(n));
            mail.attachFile(_storeConsole.storeHomePath() + atts.get("PATH"), atts.get("FILE_NAME"));
         }
      }
      // 获得模板控制台
      ITemplateConsole tplConsole = RAop.find(ITemplateConsole.class);
      // 获得邮件创建器
      String mailBuilderName = _environmentConsole.parseServer(server, event.parameter(PTY_MAIL_BUILDER));
      IMailEventBuilder mailBuilder = null;
      if(RString.isNotEmpty(mailBuilderName)){
         mailBuilder = RClass.newInstance(mailBuilderName);
      }
      // 根据邮件标题模板生成邮件标题
      String templateHead = _environmentConsole.parseServer(server, event.parameter(PTY_TEMPLATE_HEAD));
      RStringValidator.checkEmpty(templateHead, PTY_TEMPLATE_HEAD);
      ITemplateParser headParser = tplConsole.findParser(templateHead);
      if(null == headParser){
         throw new FFatalError("Template head is not exists. (name={0})", templateHead);
      }
      headParser.define(PTY_GROUP, event.groupConfig());
      headParser.define(PTY_TYPE, event.typeConfig());
      headParser.define(PTY_EVENT, event.eventConfig());
      headParser.define(PTY_CONFIG, event.parameters());
      headParser.define(PTY_PARAMETER, event.eventParameters());
      if(null != mailBuilder){
         mailBuilder.buildHead(event, headParser);
      }
      String mailHead = headParser.parse(templateHead).toString();
      mail.setSubject(mailHead);
      // 根据邮件内容模板名称生成邮件内容
      String templateBody = _environmentConsole.parseServer(server, event.parameter(PTY_TEMPLATE_BODY));
      RStringValidator.checkEmpty(templateBody, PTY_TEMPLATE_BODY);
      ITemplateParser bodyParser = tplConsole.findParser(templateBody);
      if(null == bodyParser){
         throw new FFatalError("Template body is not exists. (name={0})", templateBody);
      }
      bodyParser.define(PTY_GROUP, event.groupConfig());
      bodyParser.define(PTY_TYPE, event.typeConfig());
      bodyParser.define(PTY_EVENT, event.eventConfig());
      bodyParser.define(PTY_CONFIG, event.parameters());
      bodyParser.define(PTY_PARAMETER, event.eventParameters());
      if(null != mailBuilder){
         mailBuilder.buildBody(event, bodyParser);
      }
      String mailBody = bodyParser.parse(templateBody).toString();
      mail.setContentType(EMailContent.Html);
      mail.setBody(mailBody);
      // 发送邮件
      session.send(mail);
   }
}
