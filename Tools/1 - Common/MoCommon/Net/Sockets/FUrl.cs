using System;
using MO.Common.Lang;

namespace MO.Common.Net.Sockets
{
   //============================================================
   public class FUrl
   {
      private string _protocol;

      private string _host;
      
      private int _port = 80;
      
      private string _path;

      //============================================================
      public FUrl() {
      }

      //============================================================
      public FUrl(string url) {
         Parse(url);
      }

      //============================================================
      public string Protocol {
         get { return _protocol; }
         set { _protocol = value; }
      }

      //============================================================
      public string Host {
         get { return _host; }
         set { _host = value; }
      }

      //============================================================
      public int Port {
         get { return _port; }
         set { _port = value; }
      }

      //============================================================
      public string Path {
         get { return _path; }
         set { _path = value; }
      }

      //============================================================
      protected void Parse(string url) {
         int start = 0;
         // Protocol
         int find = url.IndexOf("://", start);
         if (find != -1) {
            _protocol = url.Substring(start, find - start);
            start = find + 3;
         }
         // Host
         find = url.IndexOf("/", start);
         if (find != -1) {
            _host = url.Substring(start, find - start);
            start = find;
            // Port
            find = _host.IndexOf(':');
            if (find != -1) {
               _port = RInt.Parse(_host.Substring(find + 1), _port);
               _host = _host.Substring(0, find);
            }
            // Path
            find = url.IndexOf("/", start + 1);
            _path = url.Substring(Math.Max(find, start));
         } else {
            _host = url.Substring(start);
            // Port
            find = _host.IndexOf(':');
            if (find != -1) {
               _port = RInt.Parse(_host.Substring(find + 1), _port);
               _host = _host.Substring(0, find);
            }
            // Path
            _path = "/";
         }
      }

      //============================================================
      public FString Dump() {
         return null;
      }
   }
}
