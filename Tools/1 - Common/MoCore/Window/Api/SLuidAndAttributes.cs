using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {
   /* typedef struct _LUID_AND_ATTRIBUTES {
    *    LUID Luid;
    *    DWORD Attributes;
    * } LUID_AND_ATTRIBUTES, * PLUID_AND_ATTRIBUTES; */
   [StructLayout(LayoutKind.Sequential)]
   public struct SLuidAndAttributes {
      public SLuid Luid;
      public ESePrivilege Attributes;
   }
}
