using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   /* typedef struct IMAGE_NT_HEADERS {
    *    DWORD Signature;
    *    IMAGE_FILE_HEADER FileHeader;
    *    IMAGE_OPTIONAL_HEADER32 OptionalHeader;
    * } */
   [StructLayout(LayoutKind.Sequential)]
   public struct SImageNtHeaders {
      public uint Signature;
      public SImageFileHeader FileHeader;
      public SImageOptionalHeader OptionalHeader;
   }

}
