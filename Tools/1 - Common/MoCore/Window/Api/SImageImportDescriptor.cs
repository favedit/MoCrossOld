using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   /* typedef struct IMAGE_IMPORT_DESCRIPTOR {
    *    union {
    *       DWORD   Characteristics;
    *       DWORD   OriginalFirstThunk;
    *    };
    *    DWORD   TimeDateStamp;
    *    DWORD   ForwarderChain;
    *    DWORD   Name;
    *    DWORD   FirstThunk;
    * } */
   [StructLayout(LayoutKind.Explicit)]
   public struct SImageImportDescriptor {
      [FieldOffset(0)]
      public uint Characteristics;
      [FieldOffset(0)]
      public uint OriginalFirstThunk;
      [FieldOffset(4)]
      public uint TimeDateStamp;
      [FieldOffset(8)]
      public uint ForwarderChain;
      [FieldOffset(12)]
      public uint Name;
      [FieldOffset(16)]
      public uint FirstThunk;
   }

}
