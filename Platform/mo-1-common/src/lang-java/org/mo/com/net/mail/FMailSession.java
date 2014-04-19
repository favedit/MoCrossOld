package org.mo.com.net.mail;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;
import org.mo.com.lang.RString;

//============================================================
// <T>邮件会话。</T>
//============================================================ 
public class FMailSession
      extends FObject
{
   // 会话
   protected Session _session;

   //============================================================
   // <T>发送邮件对象。</T>
   //
   // @param mail 邮件对象
   //============================================================ 
   public boolean send(FMail mail){
      try{
         // 创建邮件消息对象 
         MimeMessage message = new MimeMessage(_session);
         String encoding = mail.encoding().toString();
         // 设置发信人
         if(null == mail.from()){
            throw new FFatalError("Mail from is empty.");
         }
         message.setFrom(mail.from().address());
         // 设置所有收信人 
         if(!mail.hasRecipient()){
            throw new FFatalError("Mail recipient is empty.");
         }
         if(mail.hasTo()){
            message.setRecipients(Message.RecipientType.TO, mail.to().toAddresses());
         }
         if(mail.hasCc()){
            message.setRecipients(Message.RecipientType.CC, mail.cc().toAddresses());
         }
         if(mail.hasBcc()){
            message.setRecipients(Message.RecipientType.BCC, mail.bcc().toAddresses());
         }
         // 邮件标题
         if(!mail.hasSubject()){
            throw new FFatalError("Mail subject is empty.");
         }
         message.setSubject(mail.subject());
         if(!mail.hasBody()){
            throw new FFatalError("Mail body is empty.");
         }
         // 设置邮件发送时间
         if(mail.hasSendDate()){
            message.setSentDate(mail.sendDate());
         }
         // 根据附件类型判断发送方式
         if((EMailContent.Html == mail.contentType()) || mail.hasAttachment()){
            MimeMultipart multipart = new MimeMultipart();
            multipart.setSubType("related");
            // 设置邮件文本内容 
            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent(mail.body(), "text/html; charset=" + encoding);
            multipart.addBodyPart(bodyPart);
            // 放入所有的数据部分
            if(mail.hasAttachment()){
               for(TMailPart part : mail.attachments()){
                  // 编码邮件附件部分的名称
                  String partName = MimeUtility.encodeWord(part.name(), encoding, null);
                  // 创建邮件附件部分
                  BodyPart mailPart = new MimeBodyPart();
                  DataHandler dataHandler = null;
                  if(RString.isEmpty(part.mimeType())){
                     dataHandler = new DataHandler(part.source());
                  }else{
                     dataHandler = new DataHandler(part.source(), part.mimeType());
                  }
                  mailPart.setDataHandler(dataHandler);
                  mailPart.setFileName(partName);
                  if(part.hasContentId()){
                     mailPart.setHeader("Content-ID", part.contentId());
                  }
                  multipart.addBodyPart(mailPart);
               }
            }
            // 加入附件 
            message.setContent(multipart);
         }else{
            // 设置邮件文本内容 
            message.setText(mail.body());
         }
         // 保存以上所有内容 
         message.saveChanges();
         // 用SMTP的邮件发送协议
         //Transport transport = _session.getTransport("smtp");
         //transport.connect(smtpHost, name, password);
         //transport.sendMessage(message, message.getAllRecipients());
         //transport.close();
         Transport.send(message);
      }catch(Exception e){
         throw new FFatalError(e);
      }
      return true;
   }
}
