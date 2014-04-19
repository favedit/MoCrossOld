using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;
using MO.Common.Content;

namespace MO.Core.Window.Context.Asm {

   public class FAsmOpCodes : FObjects<FAsmOpCode> {

      public FAsmOpCodes() {
      }

      public FAsmOpCode CreateCode(FXmlNode config) {
         string name = config[FAsmOpCode.PTY_NAME];
         string clazz = "DSCSVImpMO.Core.Window.Context.Asm.Code.FAsm" + name;
         FAsmOpCode code = (FAsmOpCode)Activator.CreateInstance(Type.GetType(clazz));
         code.LoadConfig(config);
         Push(code);
         return code;
      }
   }
}
