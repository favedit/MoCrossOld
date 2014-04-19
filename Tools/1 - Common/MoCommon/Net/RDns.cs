using System;
using System.Collections.Generic;
using System.Text;
using System.Net;

namespace MObj.Net {

   public static class RDns {

      public static string Host() {
         return Dns.GetHostName();
      }

      public static IPAddress[] Ip() {
         string name = Dns.GetHostName();
         IPHostEntry ipHost = Dns.GetHostEntry(name);
         return ipHost.AddressList;
      }

   }

}
