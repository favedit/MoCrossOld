using System;
using MO.Common.System;

namespace MO.Common.Lang
{
   //============================================================
   // <T>日志管理接口。</T>
   //============================================================
   public interface ILoggerConsole
   {
      //============================================================
      FListeners Listeners { get; }

      //============================================================
      // <T>创建日志对象。</T>
      //
      // @return 日志对象
      //============================================================
      ILogger CreateLogger(Type type);

      //============================================================
      // <T>获得当前日志级别。</T>
      //
      // @return 日志级别
      //============================================================
      ELoggerLevel Level { get; }

      //============================================================
      // <T>输出消息。</T>
      //
      // @param refer 引用对象
      // @param method 函数名称
      // @param exception 例外对象
      // @param message 消息内容
      // @param parameters 消息参数
      //============================================================
      void Write(ELoggerLevel level, object refer, string method, Exception exception, string message, params object[] parameters);
   }
}
