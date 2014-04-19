using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Api {

   public enum EMem {
      None = 0x0000,
      Commit = 0x1000,
      Reserve = 0x2000,
      Reset = 0x80000,
      LargePages = 0x20000000,
      Physical = 0x400000,
      TopDown = 0x100000
   }

}
