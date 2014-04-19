using System.Reflection;
using MO.Common.Content;
using MO.Common.Lang;

namespace MO.Core.Aop.Config
{
   public class XAopReference : XAopNode
   {
      private static ILogger _logger = RLogger.Find(typeof(XAopReference));

      public const string TAG = "Reference";

      public const string PTY_NAME = "name";

      protected string _name;

      protected Assembly _linkAssembly;

      //============================================================
      public XAopReference() {
      }

      //============================================================
      public override string Id {
         get { return _name; }
      }

      //============================================================
      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      //============================================================
      public Assembly LinkAssembly {
         get { return _linkAssembly; }
      }

      //============================================================
      public override void LoadConfig(FXmlNode config) {
         _name = config[PTY_NAME];
         RString.CheckEmpty(_name, PTY_NAME);
         _linkAssembly = Assembly.Load(_name);
         if(_logger.DebugAble) {
            _logger.Debug(this, "LoadConfig", "Include reference {0}", _linkAssembly);
         }
      }
   }
}
