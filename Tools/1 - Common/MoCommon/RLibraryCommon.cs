using MO.Common.Lang;
using MO.Common.System;

namespace MO.Common
{
   public class RLibraryCommon
   {
      //============================================================
      public static void Initialize() {
         FLoggerConsole loggerConsole = new FLoggerConsole();
         loggerConsole.Listeners.Push(new FLoggerCommandListener());
         RLogger.LoggerConsole = loggerConsole;
      }

      //============================================================
      public static void Release() {
      }
   }
}
