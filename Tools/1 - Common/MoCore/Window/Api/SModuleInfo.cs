using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   /* typedef struct MODULEINFO {
    *    LPVOID lpBaseOfDll;
    *    DWORD  SizeOfImage;
    *    LPVOID EntryPoint;
    * } */
   [StructLayout(LayoutKind.Sequential)]
   public struct SModuleInfo {
      public UInt32 lpBaseOfDll;
      public Int32 SizeOfImage;
      public UInt32 EntryPoint;
   }

}
