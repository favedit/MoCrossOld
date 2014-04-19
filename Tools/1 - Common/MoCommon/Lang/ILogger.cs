using System;

namespace MO.Common.Lang
{
   //============================================================
   // <T>日志接口。</T>
   //============================================================
   public interface ILogger
   {
      //============================================================
      // <T>获得是否可以输出调试。</T>
      //
      // @return 是否输出
      //============================================================
      bool DebugAble { get; }

      //============================================================
      // <T>输出调试。</T>
      //
      // @param refer 引用对象
      // @param method 函数名称
      // @param message 消息内容
      // @param parameters 消息参数
      //============================================================
      void Debug(object refer, string method, string message, params object[] parameters);

      //============================================================
      // <T>获得是否输出信息。</T>
      //
      // @return 是否输出
      //============================================================
      bool InfoAble { get; }

      //============================================================
      // <T>输出信息。</T>
      //
      // @param refer 引用对象
      // @param method 函数名称
      // @param message 消息内容
      // @param parameters 消息参数
      //============================================================
      void Info(object refer, string method, string message, params object[] parameters);

      //============================================================
      // <T>获得是否输出警告。</T>
      //
      // @return 是否输出
      //============================================================
      bool WarnAble { get; }

      //============================================================
      // <T>输出警告。</T>
      //
      // @param refer 引用对象
      // @param method 函数名称
      // @param message 消息内容
      // @param parameters 消息参数
      //============================================================
      void Warn(object refer, string method, string message, params object[] parameters);

      //============================================================
      // <T>获得是否输出错误。</T>
      //
      // @return 是否输出
      //============================================================
      bool ErrorAble { get; }

      //============================================================
      // <T>输出错误。</T>
      //
      // @param refer 引用对象
      // @param method 函数名称
      // @param exception 例外对象
      //============================================================
      void Error(object refer, string method, Exception exception);

      //============================================================
      // <T>输出错误。</T>
      //
      // @param refer 引用对象
      // @param method 函数名称
      // @param exception 例外对象
      // @param message 消息内容
      // @param parameters 消息参数
      //============================================================
      void Error(object refer, string method, Exception exception, string message, params object[] parameters);

      //============================================================
      // <T>获得是否输出致命错误。</T>
      //
      // @return 是否输出
      //============================================================
      bool FatalAble { get; }

      //============================================================
      // <T>输出致命错误。</T>
      //
      // @param refer 引用对象
      // @param method 函数名称
      // @param exception 例外对象
      //============================================================
      void Fatal(object refer, string method, Exception exception);

      //============================================================
      // <T>输出致命错误。</T>
      //
      // @param refer 引用对象
      // @param method 函数名称
      // @param exception 例外对象
      // @param message 消息内容
      // @param parameters 消息参数
      //============================================================
      void Fatal(object refer, string method, Exception exception, string message, params object[] parameters);
   }
}
