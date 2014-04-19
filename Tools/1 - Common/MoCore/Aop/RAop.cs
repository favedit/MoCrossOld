using System;
using MO.Common.Lang;
using MO.Common.System;
using MO.Core.Aop.Component;
using MO.Core.Aop.Config;

namespace MO.Core.Aop{

   public class RAop {

      private static FAopConfigConsole _configConsole;

      internal static FAopComponentConsole _components;

      private static IResource _resource = RResource.Find(typeof(RAop));

      private static IAopProxyFactory _proxyFactory = new FAopProxyFactory();

      //============================================================
      static RAop() {
         _configConsole = new FAopConfigConsole();
         //_configConsole.LoadDocument(_resource.Document);
         _components = new FAopComponentConsole();
         _components.Configs = _configConsole;
      }

      //============================================================
      public static IAopProxyFactory ProxyFactory {
         get { return _proxyFactory; }
         set { _proxyFactory = value; }
      }

      //============================================================
      internal static FAopConfigConsole ConfigConsole {
         get { return _configConsole; }
      }

      //============================================================
      public static XAopComponent FindComponent<T>() {
         return _components.FindFaceConfig(typeof(T).FullName);
      }

      //============================================================
      public static object Find(Type type) {
         return _components.Find(type);
      }

      //============================================================
      public static T Find<T>() {
         return _components.Find<T>();
      }

      //============================================================
      public static void LoadConfig(string filename) {
         _configConsole.LoadDocument(RSystem.Location(filename));
      }
   }
}
