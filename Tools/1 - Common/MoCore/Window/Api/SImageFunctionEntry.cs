using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   /* typedef struct IMAGE_FUNCTION_ENTRY {
    *    DWORD StartingAddress;
    *    DWORD EndingAddress;
    *    DWORD EndOfPrologue;
    * } */
   [StructLayout(LayoutKind.Sequential)]
   public struct SImageFunctionEntry {
      Int32 StartingAddress;
      Int32 EndingAddress;
      Int32 EndOfPrologue;
   }

}
