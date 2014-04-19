package org.mo.com.net.mail;

import java.util.Date;
import javax.activation.FileDataSource;
import javax.mail.util.ByteArrayDataSource;
import org.mo.com.encoding.EEncoding;
import org.mo.com.io.RFile;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;
import org.mo.com.lang.RString;
import org.mo.com.net.EMime;

//============================================================
// <T>邮件信息。</T>
//============================================================ 
public class FMail
      extends FObject
{
   // 目标集合
   protected TMailAddressCollection _to;

   // 抄送集合
   protected TMailAddressCollection _cc;

   // 暗送集合
   protected TMailAddressCollection _bcc;

   // 来源地址
   protected TMailAddress _from;

   // 标题
   protected String _subject;

   // 内容编码
   protected EEncoding _encoding = EEncoding.Utf8;

   // 内容类型
   protected EMailContent _contentType = EMailContent.Text;

   // 内容
   protected String _body;

   // 附件集合
   protected TMailPartCollection _attachments;

   // 发送日期
   protected Date _sendDate;

   //============================================================
   // <T>判断是否含有目标地址。</T>
   //
   // @return 是否含有
   //============================================================ 
   public boolean hasTo(){
      return (null != _to) ? !_to.isEmpty() : false;
   }

   //============================================================
   // <T>获得目标地址集合。</T>
   //
   // @return 邮件地址集合
   //============================================================ 
   public TMailAddressCollection to(){
      if(null == _to){
         _to = new TMailAddressCollection();
      }
      return _to;
   }

   //============================================================
   // <T>增加目标。</T>
   //
   // @param address 邮件地址
   //============================================================ 
   public void addTo(String address){
      if(!RString.isEmpty(address)){
         to().add(address);
      }
   }

   //============================================================
   // <T>判断是否含有抄送地址。</T>
   //
   // @return 是否含有
   //============================================================ 
   public boolean hasCc(){
      return (null != _cc) ? !_cc.isEmpty() : false;
   }

   //============================================================
   // <T>获得抄送地址集合。</T>
   //
   // @return 邮件地址集合
   //============================================================ 
   public TMailAddressCollection cc(){
      if(null == _cc){
         _cc = new TMailAddressCollection();
      }
      return _cc;
   }

   //============================================================
   // <T>增加抄送。</T>
   //
   // @param address 邮件地址
   //============================================================ 
   public void addCc(String address){
      if(!RString.isEmpty(address)){
         cc().add(address);
      }
   }

   //============================================================
   // <T>判断是否含有暗送地址。</T>
   //
   // @return 是否含有
   //============================================================ 
   public boolean hasBcc(){
      return (null != _bcc) ? !_bcc.isEmpty() : false;
   }

   //============================================================
   // <T>获得暗送地址集合。</T>
   //
   // @return 邮件地址集合
   //============================================================ 
   public TMailAddressCollection bcc(){
      if(null == _bcc){
         _bcc = new TMailAddressCollection();
      }
      return _bcc;
   }

   //============================================================
   // <T>增加暗送。</T>
   //
   // @param address 邮件地址
   //============================================================ 
   public void addBcc(String address){
      if(!RString.isEmpty(address)){
         bcc().add(address);
      }
   }

   //============================================================
   // <T>判断是否含有接收地址。</T>
   //
   // @return 是否含有
   //============================================================ 
   public boolean hasRecipient(){
      return hasTo() || hasCc() || hasBcc();
   }

   //============================================================
   // <T>获得来源地址。</T>
   //
   // @return 来源地址
   //============================================================ 
   public TMailAddress from(){
      return _from;
   }

   //============================================================
   // <T>设置来源地址。</T>
   //
   // @param address 来源地址
   //============================================================ 
   public void setFrom(String address){
      _from = new TMailAddress(address);
   }

   //============================================================
   // <T>设置来源地址。</T>
   //
   // @param from 来源地址
   //============================================================ 
   public void setFrom(TMailAddress from){
      _from = from;
   }

   //============================================================
   // <T>判断是否含有标题。</T>
   //
   // @return 是否含有
   //============================================================ 
   public boolean hasSubject(){
      return !RString.isEmpty(_subject);
   }

   //============================================================
   // <T>获得标题。</T>
   //
   // @return 标题
   //============================================================ 
   public String subject(){
      return _subject;
   }

   //============================================================
   // <T>设置标题。</T>
   //
   // @param subject 标题
   //============================================================ 
   public void setSubject(String subject){
      _subject = subject;
   }

   //============================================================
   // <T>获得编码。</T>
   //
   // @return 编码
   //============================================================ 
   public EEncoding encoding(){
      return _encoding;
   }

   //============================================================
   // <T>设置编码。</T>
   //
   // @param encoding 编码
   //============================================================ 
   public void setEncoding(EEncoding encoding){
      _encoding = encoding;
   }

   //============================================================
   // <T>获得内容类型。</T>
   //
   // @return 内容类型
   //============================================================ 
   public EMailContent contentType(){
      return _contentType;
   }

   //============================================================
   // <T>设置内容类型。</T>
   //
   // @param contentType 内容类型
   //============================================================ 
   public void setContentType(EMailContent contentType){
      _contentType = contentType;
   }

   //============================================================
   // <T>判断是否含有内容。</T>
   //
   // @return 是否含有
   //============================================================ 
   public boolean hasBody(){
      return !RString.isEmpty(_body);
   }

   //============================================================
   // <T>获得内容。</T>
   //
   // @return 内容
   //============================================================ 
   public String body(){
      return _body;
   }

   //============================================================
   // <T>设置内容。</T>
   //
   // @param body 内容
   //============================================================ 
   public void setBody(String body){
      _body = body;
   }

   //============================================================
   // <T>判断是否含有附件。</T>
   //
   // @return 是否含有
   //============================================================ 
   public boolean hasAttachment(){
      return (null != attachments()) ? !attachments().isEmpty() : false;
   }

   //============================================================
   // <T>获得邮件附件集合。</T>
   //
   // @return 附件集合
   //============================================================ 
   public TMailPartCollection attachments(){
      if(null == _attachments){
         _attachments = new TMailPartCollection();
      }
      return _attachments;
   }

   //============================================================
   // <T>设置邮件的附件集合。</T>
   //
   // @param attachments 附件集合
   //============================================================ 
   public void setAttachment(TMailPartCollection attachments){
      _attachments = attachments;
   }

   //============================================================
   // <T>增加一个内存数据块。</T>
   //
   // @param data 数据
   // @param mimeCd 数据类型
   //============================================================ 
   public void addMemory(byte[] data,
                         EMime mimeCd){
      attachments().push(new TMailPart(new ByteArrayDataSource(data, mimeCd.toString())));
   }

   //============================================================
   // <T>增加一个文件。</T>
   //
   // @param fileName 文件名称
   // @param displayName 显示名称
   //============================================================ 
   public void attachFile(String fileName,
                          String displayName){
      // 检查文件存在性
      if(!RFile.exists(fileName)){
         throw new FFatalError("File is not exists. (fileName={1})", fileName);
      }
      // 增加数据块
      TMailPart part = new TMailPart(new FileDataSource(fileName));
      part.setName(displayName);
      attachments().push(part);
   }

   //============================================================
   // <T>判断是否含有发送日期。</T>
   //
   // @return 是否含有
   //============================================================ 
   public boolean hasSendDate(){
      return (null != _sendDate);
   }

   //============================================================
   // <T>获得发送日期。</T>
   //
   // @return 附件集合
   //============================================================ 
   public Date sendDate(){
      return _sendDate;
   }

   //============================================================
   // <T>设置发送日期。</T>
   //
   // @param sendDate 发送日期
   //============================================================ 
   public void setSendDate(Date sendDate){
      _sendDate = sendDate;
   }
}
