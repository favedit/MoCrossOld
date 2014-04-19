using System;
using System.Runtime.Remoting.Proxies;
using MO.Common.Lang.Reflection;

namespace MO.Core.Aop {

   [AttributeUsage(AttributeTargets.Interface, AllowMultiple = false)]
   public class AProxyFaceAttribute : ProxyAttribute {

      private bool _proxy;

      private IAopProxyFactory _factory;

      public AProxyFaceAttribute() {
      }

      public AProxyFaceAttribute(bool proxy) {
         _proxy = proxy;
      }

      public AProxyFaceAttribute(Type factoryType) {
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

