using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   /* typedef struct IMAGE_DATA_DIRECTORY {
    *    DWORD   VirtualAddress;
    *    DWORD   Size;
    * } */
   [StructLayout(LayoutKind.Sequential)]
   public struct SImageDataDirectory {

      public uint VirtualAddress;

      public uint Size;

   }
   
}
