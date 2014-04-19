using System;
using System.IO;
using System.Collections.Generic;
using System.Text;
using System.Net.Mail;
using MO.Common.Content;
using MO.Common.Lang;
using System.Net.Mime;
using MO.Common.IO;

namespace MObj.Net.Mail {

   public class FMail {

      // SmtpClient发送的电子邮件。
      internal MailMessage _nativeMail = new MailMessage();

      //============================================================
      // <T>构造。<T>
      //============================================================
      public FMail() {
      }

      //============================================================
      // <T>获取或得到发件人。</T>
      //
      // @param value 发件人。
      // @return 发件人。
      //============================================================
      public string From {
         get { return _nativeMail.From.Address; }
         set {
            if (value != null) {
               _nativeMail.From = new MailAddress(value.Trim());
            }
         }
      }

      //============================================================
      // <T>获取或得到接收人。</T>
      //
      // @param value 接收人。
      // @return 接收人。
      //============================================================
      public string To {
         get { return _nativeMail.To.ToString(); }
         set {
            if (value != null) {
               foreach (string id in value.Split('\n')) {
                  string mailid = id.Trim();
                  if (!RString.IsEmpty(mailid)) {
                     _nativeMail.To.Add(new MailAddress(mailid));
                  }
               }
            }
         }
      }

      //============================================================
      // <T>获取或得到标签。</T>
      //
      // @param value 标签。
      // @return 标签。
      //============================================================
      public string Subject {
         get { return _nativeMail.Subject; }
         set { _nativeMail.Subject = value; }
      }

      //============================================================
      // <T>获取或得到邮件内容。</T>
      //
      // @param value 邮件内容。
      // @return 邮件内容。
      //============================================================
      public string Body {
         get { return _nativeMail.Body; }
         set { _nativeMail.Body = value; }
      }

      public bool HasAttach { 
         get{
            return (_nativeMail.Attachments.Count > 0);
         }
      }

      //============================================================
      // <T>添加邮件附件。<T>
      //============================================================
      public void AddFile(string filename) {
         FFileInfo fi = new FFileInfo(filename);
         Attachment attach = new Attachment(filename);
         attach.Name = fi.Name;
         attach.NameEncoding = Encoding.ASCII;
         attach.TransferEncoding = TransferEncoding.Base64;
         _nativeMail.Attachments.Add(attach);
      }

      //============================================================
      // <T>添加邮件附件。<T>
      //============================================================
      public void AddMemoryFile(string filename) {
         byte[] bytes = File.ReadAllBytes(filename);
         FFileInfo fi = new FFileInfo(filename);
         ContentType context = new ContentType();
         context.Name = fi.Name;
         context.MediaType = MediaTypeNames.Application.Octet;
         Attachment attach = new Attachment(new MemoryStream(bytes), context);
         attach.Name = fi.Name;
         attach.NameEncoding = Encoding.ASCII;
         attach.TransferEncoding = TransferEncoding.Base64;
         _nativeMail.Attachments.Add(attach);
      }

      //============================================================
      // <T>添加邮件附件。<T>
      //============================================================
      public void AddMemory(string name, byte[] data) {
         ContentType context = new ContentType();
         context.Name = name;
         context.MediaType = MediaTypeNames.Application.Octet;
         Attachment attach = new Attachment(new MemoryStream(data), context);
         attach.Name = name;
         attach.NameEncoding = Encoding.ASCII;
         attach.TransferEncoding = TransferEncoding.Base64;
         _nativeMail.Attachments.Add(attach);
      }

      //============================================================
      // <T>添加邮件附件。<T>
      //============================================================
      public void AddFile(string name, string filename) {
         Attachment attach = new Attachment(filename);
         attach.Name = name;
         attach.NameEncoding = Encoding.ASCII;
         attach.TransferEncoding = TransferEncoding.Base64;
         _nativeMail.Attachments.Add(attach);
      }

   }

}
