namespace MO.Common.System
{
   //============================================================
   // <T>监听器接口。</T>
   //============================================================
   public abstract class FListener : IListener
   {
      // 监听器名称
      public const string NAME = "Listener";

      // 拥有对象
      protected object _owner;

      #region IListener implements

      //============================================================
      // <T>获得或设置拥有对象。</T>
      //
      // @param value 拥有对象
      // @return 拥有对象
      //============================================================
      public object Owner {
         get { return _owner; }
         set { _owner = value; }
      }

      //============================================================
      // <T>执行处理。</T>
      //
      // @param sender 发出者
      // @param args 参数列表
      //============================================================
      public abstract void Process(object sender, params object[] args);

      #endregion

      #region IDisposable implements

      //============================================================
      // <T>释放处理。</T>
      //============================================================
      public virtual void Dispose() {
      }

      #endregion
   }
}
