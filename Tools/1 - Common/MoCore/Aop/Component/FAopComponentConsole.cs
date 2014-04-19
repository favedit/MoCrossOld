using System;
using MO.Common.Collection;
using MO.Core.Aop.Config;

namespace MO.Core.Aop.Component {

   public class FAopComponentConsole {

      private XAopComponentCollection _componentCollection;

      private XAopComponentsCollection _componentsCollection;

      private FAopComponentFactory _factory;

      private FAopConfigConsole _configs;

      private FStringSet _contains = new FStringSet();

      public FAopComponentConsole() {
         _factory = new FAopComponentFactory();
         _factory._components = this;
      }

      internal FAopConfigConsole Configs {
         get { return _configs; }
         set {
            _configs = value;
            _factory._configs = value;
         }
      }

      internal XAopComponentCollection ComponentCollection {
         get {
            if (_componentCollection == null) {
               _componentCollection = (XAopComponentCollection)_configs.Find(XAopComponent.TAG);
            }
            return _componentCollection;
         }
      }

      internal XAopComponentsCollection ComponentsCollection {
         get {
            if (_componentsCollection == null) {
               _componentsCollection = (XAopComponentsCollection)_configs.Find(XAopComponents.TAG);
            }
            return _componentsCollection;
         }
      }

      public XAopComponent FindFaceConfig(string face) {
         ComponentCollection.FindByFace(face);
         if (_componentCollection == null) {
            _componentCollection = (XAopComponentCollection)_configs.Find(XAopComponent.TAG);
         }
         return (_componentCollection != null) ? _componentCollection.FindByFace(face) : null;
      }

      public T Find<T>() {
         return (T)Find(typeof(T));
      }

      public object Find(Type type) {
         XAopComponent xcomponent = FindFaceConfig(type.FullName);
         if (xcomponent != null) {
            if (xcomponent.Scope == EScope.Global) {
               if (xcomponent.Instance == null) {
                  xcomponent.Instance = _factory.CreateInstance(xcomponent);
               }
               return xcomponent.Instance;
            } else if (xcomponent.Scope == EScope.Session) {
               return _factory.CreateInstance(xcomponent);
            } else if (xcomponent.Scope == EScope.Local) {
               return _factory.CreateInstance(xcomponent);
            }
         }
         return null;
      }

   }

}
