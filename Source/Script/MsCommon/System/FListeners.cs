using System;
using MS.Common.Lang;

namespace MS.Common.System
{
   //============================================================
   // <T>监听器列表。</T>
   //
   // @history MAOCY 140414
   //============================================================
   public class FListeners : FObjects<IListener>, IDisposable
   {
      // 监听器列表名称
      public const string NAME = "Listeners";

      //============================================================
      // <T>执行处理。</T>
      //
      // @param sender 发出者
      // @param args 参数列表
      //============================================================
      public void Process(object sender, params object[] args) {
         foreach(IListener listener in this) {
            listener.Process(args);
         }
      }

      #region IDisposable implements

      //============================================================
      // <T>释放处理。</T>
      //============================================================
      public void Dispose() {
         foreach(FLoggerListener listener in this) {
            listener.Dispose();
         }
      }

      #endregion
   }
}
