using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Content;

namespace MObj.Util.Config {

   public class XPropertyObject : FXmlNode{

      public const string PTY_NAME = "name";

      public const string PTY_TEXT = "text";

      public string PropertyName {
         get { return this[PTY_NAME]; }
         set { this[PTY_NAME] = value; }
      }

      public string PropertyText {
         get { return this[PTY_TEXT]; }
         set { this[PTY_TEXT] = value; }
      }

   }

}
