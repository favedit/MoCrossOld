using System;
using System.IO;
using System.Collections;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;

namespace MO.Core.Window.DotNet {

   public class RDotNet {

      private static ILogger _logger = RLogger.Find(typeof(RDotNet));

      public static string[] ListAspnetVersion() {
         string installpath = Environment.GetEnvironmentVariable("windir") + @"\Microsoft.NET\Framework\";
         string[] directories = Directory.GetDirectories(installpath);
         ArrayList list = new ArrayList();
         foreach (string directory in directories) {
            string exename = directory + @"\aspnet_regiis.exe";
            if (File.Exists(exename)) {
               int find = directory.LastIndexOf(@"\");
               string version = directory.Substring(find + 1);
               list.Add(version);
               _logger.Debug(null, "ListAspnetVersion", "Find aspnet version({0}) at {1}", version, directory);
            }
         }
         string[] result = new string[list.Count];
         list.Sort();
         list.CopyTo(result);
         return result;
      }

   }
}
