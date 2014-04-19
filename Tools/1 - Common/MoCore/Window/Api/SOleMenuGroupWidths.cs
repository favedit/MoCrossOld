using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   /* typedef struct OleMenuGroupWidths {
    *    LONG width[ 6 ];
    * } */
   [StructLayout(LayoutKind.Sequential)]
   public struct SOleMenuGroupWidths {

      [MarshalAs(UnmanagedType.ByValArray, SizeConst = 6)]
      uint[] width;

   }

}
