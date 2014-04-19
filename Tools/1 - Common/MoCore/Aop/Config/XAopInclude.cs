using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Content;
using MO.Common.Lang;
using System.IO;
using MO.Common.System;

namespace MO.Core.Aop.Config {

   public class XAopInclude : XAopNode {

      private static ILogger _logger = RLogger.Find(typeof(XAopInclude));

      public const string TAG = "Include";

      private string _fileName;

      public override string Id {
         get { return _fileName; }
      }

      public string FileName {
         get { return _fileName; }
         set { _fileName = value; }
      }

      public XAopInclude() {
      }

      public override void LoadConfig(FXmlNode config) {
         _fileName = config.Text;
         RString.CheckEmpty(_fileName, "fileName");
         _fileName = RSystem.Location(_fileName);
         if (!File.Exists(_fileName)) {
            throw new FFatalException("Include file is not exists. (file={0})", _fileName);
         }
         if (_logger.DebugAble) {
            _logger.Debug(this, "LoadConfig", "Include file {0}", _fileName);
         }
         // Load include
         _console.LoadDocument(_fileName);
      }
   }

}
