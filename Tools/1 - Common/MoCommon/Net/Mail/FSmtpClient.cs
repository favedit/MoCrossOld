using System;
using System.Collections.Generic;
using System.Text;
using System.Net;
using System.Net.Mail;
using MO.Common.Content;
using MO.Common.Lang;
using System.ComponentModel;

namespace MObj.Net.Mail {

   public class FSmtpClient {

      public const string TAG_SERVER = "Server";

      public const string PTY_SEND = "Send";

      public const string TAG_HOST = "Host";

      public const string TAG_PORT = "Port";

      public const string PTY_LOGINID = "LoginId";

      public const string PTY_LOGINPW = "LoginPw";

      private ILogger _logger = RLogger.Find(typeof(FSmtpClient));

      private SmtpClient _nativeSmtp;

      // 服务器地址。
      private string _host;

      // 端口。
      private int _port;

      // 发件人账号。
      private string _loginId;

      // 发件人密码。
      private string _loginPw;
      //============================================================
      // <T>构造。<T>
      //============================================================
      public FSmtpClient() {
         _nativeSmtp = new SmtpClient();
         _nativeSmtp.SendCompleted += OnSendCompleted;
      }

      //============================================================
      // <T>获取或得到端口。</T>
      //
      // @param value 端口。
      // @return 端口。
      //============================================================
      public string Host {
         get { return _host; }
         set {
            _host = value;
            _nativeSmtp.Host = value;
         }
      }

      //============================================================
      // <T>获取或得到端口。</T>
      //
      // @param value 端口。
      // @return 端口。
      //============================================================
      public int Port {
         get { return _port; }
         set {
            _port = value;
            _nativeSmtp.Port = value;
         }
      }

      //============================================================
      // <T>获取或得到发件人账号。</T>
      //
      // @param value 发件人账号。
      // @return 发件人账号。
      //============================================================
      public string LoginId {
         get { return _loginId; }
         set { _loginId = value; }
      }

      //============================================================
      // <T>获取或得到发件人密码。</T>
      //
      // @param value 发件人密码。
      // @return 发件人密码。
      //============================================================
      public string LoginPw {
         get { return _loginPw; }
         set { _loginPw = value; }
      }

      //============================================================
      // <T>得到发送方式。</T>
      //
      // @param value 发送方式。
      // @return 发送方式。
      //============================================================
      public SmtpClient NativeSmtp {
         get { return _nativeSmtp; }
      }

      //============================================================
      // <T>发件。</T>
      //============================================================
      public void Send(FMail mail) {
         //_nativeSmtp.EnableSsl = true;
         _nativeSmtp.Timeout = 9999;
         _nativeSmtp.UseDefaultCredentials = false;
         if (!RString.IsEmpty(_loginId) && !RString.IsEmpty(_loginPw)) {
            _nativeSmtp.Credentials = new NetworkCredential(_loginId, _loginPw);
         }
         _nativeSmtp.DeliveryMethod = SmtpDeliveryMethod.Network;
         _nativeSmtp.Send(mail._nativeMail);
      }

      //============================================================
      // <T>发送情况。</T>
      //============================================================
      protected void OnSendCompleted(object sender, AsyncCompletedEventArgs e) {
         _logger.Debug(this, "OnSendCompleted", "Send mail success.");

      }

   }

}
