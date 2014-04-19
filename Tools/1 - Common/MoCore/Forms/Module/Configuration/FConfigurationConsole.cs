using MO.Common.Lang;
using MO.Common.System;

namespace MO.Core.Forms.Module.Configuration
{
   public class FConfigurationConsole 
   {
      private static ILogger _logger = RLogger.Find(typeof(FConfigurationConsole));

      //private FApplicationConfig _config;

      //private FApplicationConfigs _configs;

      //private FApplicationRunConfig _runConfig;

      //public event HSystemEnding Exit;

      //============================================================
      public FConfigurationConsole() {
         // Application.ApplicationExit += new EventHandler(OnApplicationExit);
      }

      ////============================================================
      //public static FApplicationConfig AppConfig {
      //   get {
      //      if (_config == null) {
      //         _config = new FApplicationConfig();
      //      }
      //      return _config;
      //   }
      //}

      ////============================================================
      //public static FApplicationConfigs Config {
      //   get {
      //      if (_configs == null) {
      //         _configs = new FApplicationConfigs();
      //      }
      //      return _configs;
      //   }
      //}

      ////============================================================
      //public static FApplicationRunConfig RunConfig {
      //   get {
      //      if (_runConfig == null) {
      //         _runConfig = new FApplicationRunConfig();
      //      }
      //      return _runConfig;
      //   }
      //}

      ////============================================================
      //public static void OnApplicationExit(object sender, EventArgs e) {
      //   if (ApplicationExit != null) {
      //      ApplicationExit();
      //   }
      //}
   }
}
