using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Services {

   public class FIisConnection : FAdConnection {

      public static string TYPE_W3SVC = "w3svc";

      public static string TYPE_FTP = "ftp";

      private FIisServerCollection _servers;

      private FIisAppPoolCollection _pools;

      private string _type = TYPE_W3SVC;

      public FIisConnection() {
         //Protocol = "IIS";
      }

      /// <summary>
      /// 僞僀僾
      /// </summary>
      /*public string Type {
         get {
            return _type;
         }
         set {
            _type = value;
            RootPath = value;
         }
      }*/

      public FIisServerCollection Servers {
         get {
            return GetServers();
         }
      }

      public FIisServerCollection GetServers() {
         if (_servers == null) {
            _servers = new FIisServerCollection();
            _servers.Connection = this;
         }
         return _servers;
      }

      public FIisAppPoolCollection Pools {
         get {
            return GetPools();
         }
      }

      public FIisAppPoolCollection GetPools() {
         if (_pools == null) {
            _pools = new FIisAppPoolCollection();
            _pools.Connection = this;
         }
         return _pools;
      }

   }

}
