using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   /* typedef struct POINT{
    *    LONG x;
    *    LONG y;
    * } */
   [StructLayout(LayoutKind.Sequential)]
   public struct SPoint {
      public int x;
      public int y;
   }

}
