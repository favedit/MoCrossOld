using System;
using MO.Common.Lang;

namespace MO.Common.System
{
   ///============================================================
   /// <summary>日志命令监听器。</summary>
   ///============================================================
   public class FLoggerCommandListener : FLoggerListener
   {
      //============================================================
      // <T>输出信息。</T>
      //
      // @param level 级别
      // @param refer 引用对象
      // @param method 函数名称
      // @param exception 例外对象
      // @param message 消息内容
      // @param parameters 参数集合
      //============================================================
      public override void Output(ELoggerLevel level, object refer, string method, Exception exception, string message, params object[] parameters) {
         FString format = new FString();
         Format(format, level, refer, method, exception, message, parameters);
         Console.WriteLine(format);
      }
   }
}
