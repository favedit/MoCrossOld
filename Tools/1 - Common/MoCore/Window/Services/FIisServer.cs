using System;
using System.Collections.Generic;
using System.Text;
using System.Diagnostics;
using MO.Common.Lang;

namespace MO.Core.Window.Services {

   public class FIisServer : FAdDirectory {

      private static ILogger _logger = RLogger.Find(typeof(FIisServer));

      public static string CLASS_NAME = "IIsWebServer";

      public static string PTY_SERVER_COMMENT = "ServerComment";

      private string _site;

      private string _ipAddress;

      private string _port;

      private string _description;

      private string _comment;

      private string _webPath;

      private string _poolName;

      public FIisServer() {
      }

      public string BindString {
         get {
            return String.Format("{0}:{1}:{2}", _ipAddress, _port, _description);
         }
      }

      public string Site {
         get {
            return _site;
         }
         set {
            _site = value;
         }
      }

      public string IpAddress {
         get {
            return _ipAddress;
         }
         set {
            _ipAddress = value;
         }
      }

      public string Port {
         get {
            return _port;
         }
         set {
            _port = value;
         }
      }

      public string WebPath {
         get {
            if (_webPath.StartsWith(@"\")) {
               return _webPath.Substring(1);
            }
            return _webPath;
         }
         set {
            _webPath = value;
         }
      }

      public string Description {
         get {
            return _description;
         }
         set {
            _description = value;
         }
      }

      public string Comment {
         get {
            return _comment;
         }
         set {
            _comment = value;
         }
      }

      public string PoolName {
         get {
            return _poolName;
         }
         set {
            _poolName = value;
         }
      }

      public void setAspnetVersion(string version) {
         string exename = Environment.GetEnvironmentVariable("windir") + @"\Microsoft.NET\Framework\" + version + @"\aspnet_regiis.exe";
         _logger.Debug(this, "setAspnetVersion", "Execute " + exename);
         ProcessStartInfo startInfo = new ProcessStartInfo(exename);
         string path = String.Format("/w3svc/{0}/root", Entry.Name);
         startInfo.Arguments = "-s " + path;
         startInfo.WindowStyle = ProcessWindowStyle.Hidden;
         startInfo.UseShellExecute = false;
         startInfo.CreateNoWindow = true;
         startInfo.RedirectStandardOutput = true;
         startInfo.RedirectStandardError = true;
         Process process = new Process();
         process.StartInfo = startInfo;
         process.Start();
         process.WaitForExit();
         string errors = process.StandardError.ReadToEnd();
         if (errors != string.Empty) {
            _logger.Debug(this, "setAspnetVersion", "Has errors: " + errors);
            throw new Exception(errors);
         }
         _logger.Debug(this, "setAspnetVersion", process.StandardOutput.ReadToEnd());
      }

   }

}
