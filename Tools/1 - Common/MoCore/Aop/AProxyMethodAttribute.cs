using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Aop {

   [AttributeUsage(AttributeTargets.Method, AllowMultiple = false)]
   public class AProxyMethodAttribute : Attribute {

      private bool _proxy;

      public AProxyMethodAttribute() {
         _proxy = true;
      }

      public AProxyMethodAttribute(bool proxy) {
         _proxy = proxy;
      }

      public bool IsProxy {
         get { return _proxy; }
      }
   }

}

