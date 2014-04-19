namespace MO.Common.System
{
   public delegate void HCaller(object sender, params object[] args);

   //============================================================
   // <T>监听器接口。</T>
   //============================================================
   public class FCaller : IListener
   {
      // 监听器名称
      public const string NAME = "Listener";

      // 拥有对象
      protected object _owner;

      protected HCaller _method;

      //============================================================
      // <T>获得或设置拥有对象。</T>
      //
      // @param value 拥有对象
      // @return 拥有对象
      //============================================================
      public FCaller(HCaller method) {
         _method = method;
      }

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
      public void Process(object sender, params object[] args) {
         _method(sender, args);
      }

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
