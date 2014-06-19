using MS.Common.System;

namespace MS.Common
{
   //============================================================
   // <T>共同库管理类。</T>
   //============================================================
   public class RCommon
   {
      //============================================================
      // <T>初始化处理。</T>
      //============================================================
      public static void Initialize() {
         FLoggerConsole loggerConsole = new FLoggerConsole();
         loggerConsole.Listeners.Push(new FLoggerCommandListener());
         RLogger.LoggerConsole = loggerConsole;
      }

      //============================================================
      // <T>释放处理。</T>
      //============================================================
      public static void Release() {
      }
   }
}
