using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   /* typedef struct IMAGE_THUNK_DATA32 {
    *    union {
    *       DWORD ForwarderString;
    *       DWORD Function;
    *       DWORD Ordinal;
    *       DWORD AddressOfData;
    *    } u1;
    * }*/
   [StructLayout(LayoutKind.Explicit)]
   public struct SImageThunkData32 {
      [FieldOffset(0)]
      public uint ForwarderString;      // PBYTE 
      [FieldOffset(0)]
      public uint Function;             // PDWORD
      [FieldOffset(0)]
      public uint Ordinal;
      [FieldOffset(0)]
      public uint AddressOfData;        // PIMAGE_IMPORT_BY_NAME
   }

}
