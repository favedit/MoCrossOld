using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Aop {

   [AttributeUsage(AttributeTargets.Field, AllowMultiple = false)]
   public class APropertyAttribute : Attribute {

      public string Name;

   }

}
