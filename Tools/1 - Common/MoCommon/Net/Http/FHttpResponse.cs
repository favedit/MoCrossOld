using System;
using System.Text;
using MO.Common.Collection;
using MO.Common.Lang;
using MO.Common.Net.Sockets;

namespace MObj.Net.Http {

   public class FHttpResponse : IDump {

      public static string DEFAULT_AGENT = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; .NET CLR 1.1.4322)";

      private FHttpConnection _connection;

      private string _method;

      private FAttributes _heads;

      private FAttributes _parameters;

      private FAttributes _values;

      public FHttpResponse() {
         Construct();
      }

      public FHttpResponse(FHttpConnection connection) {
         _connection = connection;
         Construct();
      }

      protected void Construct() {
         _heads = new FAttributes();
         _parameters = new FAttributes();
         _values = new FAttributes();
         ResetHeads();
      }

      public string Method {
         get { return _method; }
         set { _method = value; }
      }

      public FAttributes Heads {
         get { return _heads; }
      }

      public FAttributes Values {
         get { return _values; }
      }

      protected void ResetHeads() {
         _heads.Clear();
         _heads[EHttpHead.Accept] = "*/*";
         _heads[EHttpHead.AcceptLanguage] = "ja";
         //_heads[EHttpHead.AcceptEncoding] = "gzip, deflate";
         _heads[EHttpHead.AcceptEncoding] = "deflate";
         int port = _connection.Url.Port;
         if (port != (int)ESocketPort.Http) {
            _heads[EHttpHead.Host] = _connection.Url.Host + ":" + port;
         } else {
            _heads[EHttpHead.Host] = _connection.Url.Host;
         }
         //_heads[EHttpHead.Pragma] = "no-cache";
         _heads[EHttpHead.CacheControl] = "no-cache";
         _heads[EHttpHead.UserAgent] = DEFAULT_AGENT;
         _heads[EHttpHead.Connection] = "Keep-Alive";
         _heads["Content-Type"] = "application/x-www-form-urlencoded";
         _heads["Cookie"] = "JSESSIONID=EE6CE80B85A863B0D8027E0F4AE266C1";
      }

      protected FBytes Build() {
         FBytes bytes = new FBytes();
         FString line = new FString();
         // Protocol
         if (RString.IsEmpty(_method)) {
            if (_values.IsEmpty) {
               line.Append("GET ");
            } else {
               line.Append("POST ");
            }
         } else {
            line.Append(_method);
            line.Append(" ");
         }
         if (_connection.UseProxy) {
            line.Append(_connection.Url.ToString());
         } else {
            if (RString.IsEmpty(_connection.Url.Path)) {
               line.Append("/");
            } else {
               line.Append(_connection.Url.Path);
            }
         }
         line.Append(" HTTP/1.1\r\n");
         // Values
         FString value = new FString();
         if (!_values.IsEmpty) {
            int count = _values.Count;
            for (int n = 0; n < count; n++) {
               if (n != 0) {
                  value.Append('&');
               }
               value.Append(_values.Name(n));
               value.Append('=');
               value.Append(_values.Value(n));
            }
            _heads["Content-Length"] = value.Length.ToString();
         }
         bytes.Append(line.ToBytes(Encoding.ASCII));
         // Heads
         if (!_heads.IsEmpty) {
            int count = _heads.Count;
            for (int n = 0; n < count; n++) {
               line.Clear();
               line.Append(_heads.Name(n));
               line.Append(": ");
               line.Append(_heads.Value(n));
               line.Append("\r\n");
               bytes.Append(line.ToBytes(Encoding.ASCII));
            }
         }
         /*// Host Fix
         if (!_heads.Contains("Host")) {
            line.Clear();
            line.Append("Host: ");
            line.Append(_connection.Url.Host);
            line.Append("\r\n");
            bytes.Append(line.ToBytes(Encoding.ASCII));
         }*/
         // Make
         line.Clear();
         line.Append("\r\n");
         bytes.Append(line.ToBytes(Encoding.ASCII));
         if (!_values.IsEmpty) {
            bytes.Append(value.ToBytes(Encoding.ASCII));
         }
         line.Append("\r\n");
         return bytes;
      }

      public FHttpConnection Connection {
         get { return _connection; }
      }

      public void Send() {
         Console.WriteLine(Dump());
         FBytes bytes = Build();
         _connection._socket.Output.Write(bytes.ToArray());
      }

      #region IDump members

      public FDumpInfo Dump() {
         return Dump(new FDumpInfo(this));
      }

      public virtual FDumpInfo Dump(FDumpInfo info) {
         RDump.StartDump(info);
         FBytes bytes = Build();
         info.Append("Length:");
         info.AppendLine(bytes.Length);
         info.AppendLine(RDump.LINE_L2);
         string value = Encoding.ASCII.GetString(bytes.Memory, 0, bytes.Length);
         char[] chs = value.ToCharArray();
         int count = Math.Min(chs.Length, 2048);
         for (int n = 0; n < count; n++) {
            char ch = chs[n];
            if (ch >= ' ') {
               info.Append(ch);
            } else if (ch == '\n' || ch == '\r') {
               info.Append(ch);
            } else {
               info.Append('.');
            }
         }
         info.AppendLine();
         info.AppendLine(RDump.LINE_L2);
         return info;
      }

      #endregion
   }

}
