using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Core.Hook {
   public struct SHookInfo {
      public string Module;
      public string Function;
      public IntPtr pNewProc;
      public IntPtr pOldProc;
   }
}
