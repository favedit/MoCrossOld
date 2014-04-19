using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Core {
   
   public class FShellFile : FShellObject{

      public FShellFile() {
      }

      public FShellFile(IntPtr pidl)
         : base(pidl) {
      }

   }

}
