using System;
using System.Collections.Generic;
using System.Text;
using System.Reflection;
using MO.Common.Lang;

namespace MO.Core.Aop.Component {

   public class FAopMethod {

      protected string _name;

      protected MethodInfo _method;

      protected Type _face;

      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      public MethodInfo Method {
         get { return _method; }
         set { _method = value; }
      }

      public void Link(MethodInfo method) {
         _name = method.Name;
         _method = method;
      }

      public void Invoke(object instance, params object[] args) {
         _method.Invoke(instance, args);
      }

   }

}
