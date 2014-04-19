using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   /* typedef struct _LUID {
    *    DWORD LowPart;
    *    LONG HighPart;
    * } LUID, *PLUID; */
   [StructLayout(LayoutKind.Sequential)]
   public struct SLuid {
      public int LowPart;
      public int HighPart;
   }
}
