using MO.Common.Collection;
using MO.Common.Lang;
using MO.Core.Aop.Config;

namespace MO.Core.Aop.Component {

   public class FAopComponentBuilder {

      private static ILogger _logger = RLogger.Find(typeof(FAopComponentBuilder));

      private FDictionary<object> _faces = new FDictionary<object>();

      private XAopComponent _xcomponent;

      private object _instance;

      private object _proxyInstance;

      public FAopComponentBuilder(XAopComponent xcomponent) {
         _xcomponent = xcomponent;
      }

      public FDictionary<object> Faces {
         get { return _faces; }
         set { _faces = value; }
      }

      public XAopComponent Component {
         get { return _xcomponent; }
         set { _xcomponent = value; }
      }

      public object Instance {
         get { return _instance; }
         set { _instance = value; }
      }

      public object ProxyInstance {
         get { return _proxyInstance; }
         set { _proxyInstance = value; }
      }

   }

}
