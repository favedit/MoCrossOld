using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Aop.Component {

   public class FAopProxyFactory : IAopProxyFactory {

      #region IAopProxyFactory members

      public IAopProxy CreateProxyInstance(MarshalByRefObject marshalObject, Type type) {
         return new FAopProxy(marshalObject, type);
      }

      #endregion
   }
}
