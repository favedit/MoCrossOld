using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   /* typedef struct SECURITY_ATTRIBUTES {
    *    DWORD nLength;
    *    LPVOID lpSecurityDescriptor;
    *    BOOL bInheritHandle;
    * } */
   [StructLayout(LayoutKind.Sequential)]
   public struct SSecurityAttributes {
      public int nLength;
      public uint lpSecurityDescriptor;
      public bool bInheritHandle;
   }

}
