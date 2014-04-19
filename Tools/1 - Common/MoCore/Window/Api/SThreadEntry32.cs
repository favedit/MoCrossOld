using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   /* typedef struct THREADENTRY32 {
    *    DWORD dwSize;
    *    DWORD cntUsage;
    *    DWORD th32ThreadID;
    *    DWORD th32OwnerProcessID;
    *    LONG tpBasePri;
    *    LONG tpDeltaPri;
    *    DWORD dwFlags;
    * } */
   [StructLayout(LayoutKind.Sequential)]
   public struct SThreadEntry32 {
      public int dwSize;
      public int cntUsage;
      public int th32ThreadID;
      public int th32OwnerProcessID;
      public int tpBasePri;
      public int tpDeltaPri;
      public int dwFlags;
   }
}
