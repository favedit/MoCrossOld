using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   /* typedef struct IMAGE_EXPORT_DIRECTORY {
    *    DWORD   Characteristics;
    *    DWORD   TimeDateStamp;
    *    WORD    MajorVersion;
    *    WORD    MinorVersion;
    *    DWORD   Name;
    *    DWORD   Base;
    *    DWORD   NumberOfFunctions;
    *    DWORD   NumberOfNames;
    *    DWORD   AddressOfFunctions;
    *    DWORD   AddressOfNames;
    *    DWORD   AddressOfNameOrdinals;
    * } */
   [StructLayout(LayoutKind.Sequential)]
   public struct SImageExportDirectory {
      public uint Characteristics;
      public uint TimeDateStamp;
      public ushort MajorVersion;
      public ushort MinorVersion;
      public uint Name;
      public uint Base;
      public int NumberOfFunctions;
      public int NumberOfNames;
      public uint AddressOfFunctions;
      public uint AddressOfNames;
      public uint AddressOfNameOrdinals;
   }

}
