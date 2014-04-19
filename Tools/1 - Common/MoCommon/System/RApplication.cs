using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MO.Common.Lang;

namespace MO.Common.System
{
   public class RApplication
   {
      //============================================================
      public static void Startup() {
         FLoggerConsole loggerConsole = new FLoggerConsole();
         loggerConsole.Listeners.Push(new FLoggerCommandListener());
         RLogger.LoggerConsole = loggerConsole;
      }

      //============================================================
      public static void Shutdown() {
      }
   }
}
