using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Api {

   public enum EImageSignature {
      Dos = 0x5A4D,
      Os2 = 0x454E,
      Os2Le = 0x454C,
      Vxd = 0x454C,
      Nt = 0x00004550
   }

}
