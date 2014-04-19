using System;
using MO.Common.Lang;

namespace MO.Common.System
{
   //============================================================
   // <T>监听器列表。</T>
   //============================================================
   public class FCallers : FObject, IDisposable
   {
      // 监听器列表名称
      public const string NAME = "Listeners";

      protected FObjects<FCaller> listeners = new FObjects<FCaller>();

      //============================================================
      // <T>执行处理。</T>
      //
      // @param sender 发出者
      // @param args 参数列表
      //============================================================
      public void Register(HCaller method) {
         listeners.Push(new FCaller(method));
      }

      //============================================================
      // <T>执行处理。</T>
      //
      // @param sender 发出者
      // @param args 参数列表
      //============================================================
      public void Process(object sender, params object[] args) {
         foreach (IListener listener in listeners) {
            listener.Process(sender, args);
         }
      }

      #region IDisposable implements

      //============================================================
      // <T>释放处理。</T>
      //============================================================
      public void Dispose() {
         foreach(FCaller listener in listeners) {
            listener.Dispose();
         }
      }

      #endregion
   }
}
