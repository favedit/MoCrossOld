using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   /* typedef struct tagRECT{
    *    LONG left;
    *    LONG top;
    *    LONG right;
    *    LONG bottom;
    * } */
   [StructLayout(LayoutKind.Sequential)]
   public struct SRect {

      public uint left;

      public uint top;

      public uint right;

      public uint bottom;
   }

}
