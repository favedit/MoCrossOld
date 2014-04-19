using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;
using MO.Common.Content;

namespace MO.Core.Aop.Config {

   public class XAopNode : IAopNode {

      public const string PTY_ID = "id";

      protected IAopConfigConsole _console;

      protected string _id;

      public IAopConfigConsole Console {
         get { return _console; }
         set { _console = value; }
      }

      #region IAopNode members

      public virtual string Id {
         get { return _id; }
         set { _id = value; }
      }

      public virtual void LoadConfig(FXmlNode config) {
         // Id
         if (config.Contains(PTY_ID)) {
            _id = config[PTY_ID];
         }
      }

      #endregion

      #region IDump members

      public FDumpInfo Dump() {
         return Dump(new FDumpInfo(this));
      }

      public virtual FDumpInfo Dump(FDumpInfo info) {
         RDump.StartDump(info);
         info.AppendProperty(PTY_ID, _id, true);
         return info;
      }

      #endregion
   }

}
