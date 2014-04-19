using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Api {

   public enum ETh32cs : uint{
      Inherit = 0x80000000,
      SnapAll = 0x8000001F,
      SnapHeapList = 0x00000001,
      SnapModule = 0x00000008,
      SnapModule32 = 0x00000010,
      SnapProcess = 0x00000002,
      SnapThread = 0x00000004
   }

}
