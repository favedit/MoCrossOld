using System;
using System.Collections.Generic;
using System.Text;
using System.Reflection;
using MO.Common.Content;
using MO.Common.Lang;
using MO.Common.Lang.Reflection;

namespace MO.Core.Aop.Config {

   public class XAopReferenceCollection : XAopNodeCollection<XAopReference> {

      public const string TAG = "References";

      public XAopReferenceCollection() {
         _duplicate = false;
      }

      public FType FindType(string name) {
         int count = Count;
         for (int n = count - 1; n >= 0; n--) {
            Type type = Get(n).LinkAssembly.GetType(name);
            if (type != null) {
               return RClass.Find(type);
            }
         }
         throw new FFatalException("Can't find reference type [{0}]", name);
      }


   }

}
