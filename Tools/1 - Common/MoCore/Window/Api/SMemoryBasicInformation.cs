using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   /* typedef struct MEMORY_BASIC_INFORMATION {
    *    PVOID BaseAddress;
    *    PVOID AllocationBase;
    *    DWORD AllocationProtect;
    *    SIZE_T RegionSize;
    *    DWORD State;
    *    DWORD Protect;
    *    DWORD Type;
    * } */
   [StructLayout(LayoutKind.Sequential)]
   public struct SMemoryBasicInformation {
      public uint BaseAddress;
      public uint AllocationBase;
      public int AllocationProtect;
      public uint RegionSize;
      public int State;
      public int Protect;
      public int Type;
   }

}
