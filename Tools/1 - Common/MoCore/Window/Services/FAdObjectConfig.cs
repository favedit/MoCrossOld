using System;
using System.Collections.Generic;
using System.Text;
using System.DirectoryServices;
using MO.Common.Lang;
using MO.Common.Content;
using MO.Common.Lang.Reflection;

namespace MO.Core.Window.Services {

   public class FAdObjectConfig {

      private static ILogger _logger = RLogger.Find(typeof(FAdObjectConfig));

      public const string TAG = "AdObject";

      public const string PTY_NAME = "name";

      public const string PTY_CLASS = "class";

      private string _name;

      private string _className;

      private FType _class;

      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      public string ClassName {
         get { return _className; }
         set { _className = value; }
      }

      public FAdObjectConfig() {
      }

      public void LoadConfig(FXmlNode config) {
         _name = config[PTY_NAME];
         _className = config[PTY_CLASS];
         _class = RClass.Find(_className);
      }

      public FAdObject CreateInstance() {
         return RClass.CreateInstance<FAdObject>(_class.Type);
      }

   }

}
