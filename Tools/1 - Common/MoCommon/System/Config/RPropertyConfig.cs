using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;
using MO.Common.Content;
using System.Reflection;

namespace MObj.Util.Config {

   public class RPropertyConfig {

      public const string TAG_PROPERTIES = "Properties";

      public static XPropertyDocument LoadResource(Assembly assembly, string name) {
         XPropertyDocument pdoc = new XPropertyDocument();
         pdoc.LoadResource(assembly, name);
         return pdoc;
      }

   }

}
