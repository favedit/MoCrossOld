using System;
using System.Threading;
using MO.Common.Lang;

namespace MO.Common.System
{
   //============================================================
   // <T>日志监听器。</T>
   //============================================================
   public abstract class FLoggerListener : FListener
   {
      // 宽度
      protected const int LOGGER_SPACE = 70;

      // 时间格式
      protected string _dateFormat = "yyyymmdd-hh24miss";

      //============================================================
      // <T>构造日志监听器。</T>
      //============================================================
      public FLoggerListener() {
      }

      //============================================================
      // <T>获得或设置日期时间格式。</T>
      //
      // @param value 日期时间格式
      // @return 日期时间格式
      //============================================================
      public string DateFormat {
         get { return _dateFormat; }
         set { _dateFormat = value; }
      }

      //============================================================
      // <T>追加级别。</T>
      //
      // @param buffer 字符串对象
      // @param level 级别
      //============================================================
      protected void AppendLevel(FString buffer, ELoggerLevel level) {
         switch (level) {
            case ELoggerLevel.Debug:
               buffer.Append('D');
               break;
            case ELoggerLevel.Info:
               buffer.Append('I');
               break;
            case ELoggerLevel.Warn:
               buffer.Append('W');
               break;
            case ELoggerLevel.Error:
               buffer.Append('E');
               break;
            case ELoggerLevel.Fatal:
               buffer.Append('F');
               break;
         }
      }

      //============================================================
      // <T>格式化。</T>
      //
      // @param buffer 字符串对象
      // @param level 级别
      //============================================================
      public virtual void Format(FString format, ELoggerLevel level, object refer, string method, Exception exception, string message, object[] parameters) {
         // 追加时间
         format += RDate.Format(_dateFormat) + ' ';
         // 追加级别
         AppendLevel(format, level);
         // 追加线程编号
         format += ':' + Thread.CurrentThread.ManagedThreadId.ToString("X2");
         // 追加函数信息
         if (refer is Type) {
            format += "-<static> [";
            format += ((Type)refer).Name + '.' + method;
         } else {
            format += "-" + refer.GetHashCode().ToString("X8") + " [";
            format += refer.GetType().Name + '.' + method;
         }
         format.AppendRepeat(' ', LOGGER_SPACE - format.Length);
         format += "] ";
         // 追加信息内容
         if (null != parameters) {
            format += String.Format(message, parameters);
         }
         // 追加例外内容
         if (null != exception) {
            format.AppendLine();
            RException.MakeMessage(format, exception);
         }
      }

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
      public abstract void Output(ELoggerLevel level, object refer, string method, Exception exception, string message, params object[] parameters);

      //============================================================
      // <T>监听处理。</T>
      //
      // @param buffer 字符串对象
      // @param level 级别
      //============================================================
      public override void Process(object sender, params object[] args) {
         // 获得参数
         ELoggerLevel level = (ELoggerLevel)args[0];
         object refer = args[1];
         string method = (string)args[2];
         Exception exception = (Exception)args[3];
         string message = (string)args[4];
         object[] parameters = (object[])args[5];
         // 输出信息
         Output(level, refer, method, exception, message, parameters);
      }

      //============================================================
      // <T>关闭处理。</T>
      //============================================================
      public virtual void Close() {
      }

      #region IDisposable implements

      //============================================================
      // <T>释放处理。</T>
      //============================================================
      public override void Dispose() {
         Close();
      }

      #endregion
   }
}
