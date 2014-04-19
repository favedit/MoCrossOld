using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Content;
using MO.Common.Lang;

namespace MObj.Util.Config {

   public class XPropertyItem : XPropertyObject {

      public const string TAG = "Property";

      public const string PTY_TYPE = "type";

      public const string PTY_ID = "id";

      public const string PTY_VALUE = "value";

      public XPropertyItem() {
      }

      public string PropertyType {
         get { return this[PTY_TYPE]; }
         set { this[PTY_TYPE] = value; }
      }

      public string Id {
         get { return this[PTY_ID]; }
         set { this[PTY_ID] = value; }
      }

      public string Value {
         get { return this[PTY_VALUE]; }
         set { this[PTY_VALUE] = value; }
      }

   }

}
