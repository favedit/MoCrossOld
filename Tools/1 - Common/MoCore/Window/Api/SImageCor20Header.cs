using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   /* typedef struct IMAGE_COR20_HEADER {
    *    DWORD                   cb;
    *    WORD                    MajorRuntimeVersion;
    *    WORD                    MinorRuntimeVersion;
    *    IMAGE_DATA_DIRECTORY    MetaData;
    *    DWORD                   Flags;
    *    DWORD                   EntryPointToken;
    *    IMAGE_DATA_DIRECTORY    Resources;
    *    IMAGE_DATA_DIRECTORY    StrongNameSignature;
    *    IMAGE_DATA_DIRECTORY    CodeManagerTable;
    *    IMAGE_DATA_DIRECTORY    VTableFixups;
    *    IMAGE_DATA_DIRECTORY    ExportAddressTableJumps;
    *    IMAGE_DATA_DIRECTORY    ManagedNativeHeader;
    * } */
   [StructLayout(LayoutKind.Sequential)]
   public struct SImageCor20Header {
      uint cb;
      ushort MajorRuntimeVersion;
      ushort MinorRuntimeVersion;
      SImageDataDirectory MetaData;
      uint Flags;
      uint EntryPointToken;
      SImageDataDirectory Resources;
      SImageDataDirectory StrongNameSignature;
      SImageDataDirectory CodeManagerTable;
      SImageDataDirectory VTableFixups;
      SImageDataDirectory ExportAddressTableJumps;
      SImageDataDirectory ManagedNativeHeader;
   }

}
