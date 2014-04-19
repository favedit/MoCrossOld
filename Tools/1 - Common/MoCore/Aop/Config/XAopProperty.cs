using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;
using MO.Common.Content;

namespace MO.Core.Aop.Config {

   public class XAopProperty : XAopNode {

      public const string TAG = "Property";

      public const string PTY_NAME = "name";

      public const string PTY_TYPE = "type";

      private string _name;

      private string _type;

      private string _value;

      public override string Id {
         get { return _name; }
      }

      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      public string Type {
         get { return _type; }
         set { _type = value; }
      }

      public string Value {
         get { return _value; }
         set { _value = value; }
      }

      public override void LoadConfig(FXmlNode config) {
         _name = config[PTY_NAME];
         RString.CheckEmpty(_name, "name");
         _type = config[PTY_TYPE];
         _value = config.Text;
      }

   }

}
