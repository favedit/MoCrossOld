using System;

namespace MO.Common.Lang
{
   //============================================================
   // <T>日志对象。</T>
   //============================================================
   public class FLogger : ILogger
   {
      // 引用对象
      protected Type _reference;

      //============================================================
      // <T>构造日志对象。</T>
      //============================================================
      public FLogger() {
      }

      //============================================================
      // <T>构造日志对象。</T>
      //
      // @param reference 引用对象
      //============================================================
      public FLogger(Type reference) {
         _reference = reference;
      }

      //============================================================
      // <T>获得或设置引用对象。</T>
      //
      // @param value 引用对象
      // @return 引用对象
      //============================================================
      public Type Reference {
         get { return _reference; }
         internal set { _reference = value; }
      }

      //============================================================
      // <T>获得是否可以输出调试。</T>
      //
      // @return 是否输出
      //============================================================
      public bool DebugAble {
         get { return (RLogger.Level & ELoggerLevel.Debug) == ELoggerLevel.Debug; }
      }

      //============================================================
      // <T>输出调试。</T>
      //
      // @param refer 引用对象
      // @param method 函数名称
      // @param message 消息内容
      // @param parameters 消息参数
      //============================================================
      public void Debug(object refer, string method, string message, params object[] parameters) {
         if(DebugAble) {
            RLogger.Output(ELoggerLevel.Debug, RObject.Nvl(refer, _reference), method, null, message, parameters);
         }
      }

      //============================================================
      // <T>获得是否输出信息。</T>
      //
      // @return 是否输出
      //============================================================
      public bool InfoAble {
         get { return (RLogger.Level & ELoggerLevel.Info) == ELoggerLevel.Info; }
      }

      //============================================================
      // <T>输出信息。</T>
      //
      // @param refer 引用对象
      // @param method 函数名称
      // @param message 消息内容
      // @param parameters 消息参数
      //============================================================
      public void Info(object refer, string method, string message, params object[] parameters) {
         if(InfoAble) {
            RLogger.Output(ELoggerLevel.Info, RObject.Nvl(refer, _reference), method, null, message, parameters);
         }
      }

      //============================================================
      // <T>获得是否输出警告。</T>
      //
      // @return 是否输出
      //============================================================
      public bool WarnAble {
         get { return (RLogger.Level & ELoggerLevel.Warn) == ELoggerLevel.Warn; }
      }

      //============================================================
      // <T>输出警告。</T>
      //
      // @param refer 引用对象
      // @param method 函数名称
      // @param message 消息内容
      // @param parameters 消息参数
      //============================================================
      public void Warn(object refer, string method, string message, params object[] parameters) {
         if(WarnAble) {
            RLogger.Output(ELoggerLevel.Warn, RObject.Nvl(refer, _reference), method, null, message, parameters);
         }
      }

      //============================================================
      // <T>获得是否输出错误。</T>
      //
      // @return 是否输出
      //============================================================
      public bool ErrorAble {
         get { return (RLogger.Level & ELoggerLevel.Error) == ELoggerLevel.Error; }
      }

      //============================================================
      // <T>输出错误。</T>
      //
      // @param refer 引用对象
      // @param method 函数名称
      // @param exception 例外对象
      //============================================================
      public void Error(object refer, string method, Exception exception) {
         if(ErrorAble) {
            RLogger.Output(ELoggerLevel.Error, RObject.Nvl(refer, _reference), method, exception, null, null);
         }
      }

      //============================================================
      // <T>输出错误。</T>
      //
      // @param refer 引用对象
      // @param method 函数名称
      // @param exception 例外对象
      // @param message 消息内容
      // @param parameters 消息参数
      //============================================================
      public void Error(object refer, string method, Exception exception, string message, params object[] parameters) {
         if(ErrorAble) {
            RLogger.Output(ELoggerLevel.Error, RObject.Nvl(refer, _reference), method, exception, message, parameters);
         }
      }

      //============================================================
      // <T>获得是否输出致命错误。</T>
      //
      // @return 是否输出
      //============================================================
      public bool FatalAble {
         get { return (RLogger.Level & ELoggerLevel.Fatal) == ELoggerLevel.Fatal; }
      }

      //============================================================
      // <T>输出致命错误。</T>
      //
      // @param refer 引用对象
      // @param method 函数名称
      // @param exception 例外对象
      //============================================================
      public void Fatal(object refer, string method, Exception exception) {
         if(FatalAble) {
            RLogger.Output(ELoggerLevel.Fatal, RObject.Nvl(refer, _reference), method, exception, null, null);
         }
      }

      //============================================================
      // <T>输出致命错误。</T>
      //
      // @param refer 引用对象
      // @param method 函数名称
      // @param exception 例外对象
      // @param message 消息内容
      // @param parameters 消息参数
      //============================================================
      public void Fatal(object refer, string method, Exception exception, string message, params object[] parameters) {
         if(FatalAble) {
            RLogger.Output(ELoggerLevel.Fatal, RObject.Nvl(refer, _reference), method, exception, message, parameters);
         }
      }
   }
}
