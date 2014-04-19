using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;
using MO.Common.Content;

namespace MO.Common.Text.Validator {

   public class FValidatorConfig {

      public const string TAG = "ValidatorConfig";

      public const string PTY_NAME = "name";

      public const string PTY_CLASS = "class";

      private string _name;

      private string _class;

      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      public string Class {
         get { return _class; }
         set { _class = value; }
      }

      public void LoadConfig(FXmlNode config) {
         _name = config[PTY_NAME];
         RString.CheckEmpty(_name, "name");
         _class = config[PTY_CLASS];
         RString.CheckEmpty(_class, "class");
         RClass.Exists(_class);
      }

      public IValidator CreateInstance() {
         return (IValidator)RClass.CreateInstance(_class);
      }

   }

}
