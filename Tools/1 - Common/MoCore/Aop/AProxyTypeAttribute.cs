using System;
using System.Runtime.Remoting.Proxies;
using MO.Common.Lang.Reflection;

namespace MO.Core.Aop {

   [AttributeUsage(AttributeTargets.Class, AllowMultiple = false)]
   public class AProxyTypeAttribute : ProxyAttribute {

      private bool _proxy;

      private IAopProxyFactory _factory;

      public AProxyTypeAttribute() {
      }

      public AProxyTypeAttribute(bool proxy) {
         _proxy = proxy;
      }

      public AProxyTypeAttribute(Type factoryType) {
         _factory = RClass.CreateInstance<IAopProxyFactory>(factoryType);
      }

      public bool IsProxy {
         get { return _proxy; }
      }

      public IAopProxyFactory Factory {
         get {
            if (_factory == null) {
               _factory = RAop.ProxyFactory;
            }
            return _factory;
         }
      }

      public override MarshalByRefObject CreateInstance(Type serverType) {
         MarshalByRefObject target = base.CreateInstance(serverType);
         IAopProxy proxy = Factory.CreateProxyInstance(target, serverType);
         proxy.IsProxy = _proxy;
         return proxy.ProxyObject;
      }
   }

}

