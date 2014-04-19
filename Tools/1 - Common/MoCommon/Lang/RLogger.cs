using System;
using System.Collections.Generic;
using MO.Common.System;

namespace MO.Common.Lang
{
   //============================================================
   // <T>日志工具类。</T>
   //============================================================
   public class RLogger
   {
      // 日志管理对象
      protected static ILoggerConsole _console;

      // 日志对象字典集合
      protected static Dictionary<string, ILogger> _loggers = new Dictionary<string, ILogger>();

      //============================================================
      public static ILoggerConsole LoggerConsole {
         get { return _console; }
         set { _console = value; }
      }

      //============================================================
      // <T>获得当前日志级别。</T>
      //
      // @return 日志级别
      //============================================================
      public static ELoggerLevel Level {
         get { return _console.Level; }
      }

      //============================================================
      // <T>根据类型找到日志对象。</T>
      //
      // @param type 引用对象
      // @return 日志对象
      //============================================================
      public static ILogger Find(Type type) {
         ILogger logger = null;
         string name = type.FullName;
         // 查找是否有缓冲对象
         if (_loggers.ContainsKey(name)) {
            logger = _loggers[name];
         } else {
            // 创建日志对象
            if (null != _console) {
               logger = _console.CreateLogger(type);
            } else {
               logger = new FLogger(type);
            }
            _loggers[name] = logger;
         }
         return logger;
      }

      //============================================================
      // <T>输出消息。</T>
      //
      // @param refer 引用对象
      // @param method 函数名称
      // @param exception 例外对象
      // @param message 消息内容
      // @param parameters 消息参数
      //============================================================
      public static void Output(ELoggerLevel level, object refer, string method, Exception exception, string message, params object[] parameters) {
         if (null != _console) {
            _console.Write(level, refer, method, exception, message, parameters);
         }
      }
   }
}
