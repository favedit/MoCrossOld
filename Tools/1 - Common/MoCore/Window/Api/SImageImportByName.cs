using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   /*typedef struct IMAGE_IMPORT_BY_NAME {
       WORD    Hint;
       BYTE    Name[1];
   } IMAGE_IMPORT_BY_NAME, *PIMAGE_IMPORT_BY_NAME;*/
   public struct SImageImportByName {
      public ushort Hint;
      [MarshalAs(UnmanagedType.ByValArray, SizeConst = 256)]
      public byte[] Name;
   }

}
