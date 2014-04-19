using MO.Common.Content;
using MO.Common.Lang;
using MO.Common.System;

namespace MO.Core.Aop.Config {

   public class XAopDefine : XAopNode {

      private static ILogger _logger = RLogger.Find(typeof(XAopDefine));

      public const string TAG = "Define";

      public const string PTY_NAME = "name";

      public const string PTY_VALUE = "value";

      public const string PTY_FORMATOR = "formator";

      private string _name;

      private string _value;

      public override string Id {
         get { return _name; }
      }

      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      public string Value {
         get { return _value; }
         set { _value = value; }
      }

      public XAopDefine() {
      }

      public override void LoadConfig(FXmlNode config) {
         _name = config[PTY_NAME];
         RString.CheckEmpty(_name, "name");
         _value = config[PTY_VALUE];
         RString.CheckEmpty(_value, "value");
         // Load formator
         if (config.Contains(PTY_FORMATOR)) {
            /*string formator = config[PTY_FORMATOR];
            _formator = (IFormator)RClass.CreateInstance(formator);
            _formator.LoadConfig(config);
            // Load formator value
            FFormatorParameters parameters = new FFormatorParameters();
            parameters.RaiseException = true;
            parameters.Value = Value;
            if (_formator.Format(parameters)) {
               _value = RString.Nvl(parameters.Value);
            }*/
         }
         // Dump
         if (_logger.DebugAble) {
            _logger.Debug(this, "LoadConfig", "Define {0}=[{1}]", _name, _value);
         }
      }

   }

}
