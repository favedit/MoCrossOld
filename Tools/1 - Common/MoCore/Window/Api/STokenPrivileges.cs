using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   /* typedef struct _TOKEN_PRIVILEGES {
    *    DWORD PrivilegeCount;
    *    LUID_AND_ATTRIBUTES Privileges[ANYSIZE_ARRAY];
    * } TOKEN_PRIVILEGES, *PTOKEN_PRIVILEGES; */
   [StructLayout(LayoutKind.Sequential)]
   public struct STokenPrivileges {
      public int PrivilegeCount;
      public SLuidAndAttributes Privileges;
   }

}
