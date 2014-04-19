using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   /* typedef struct IMAGE_FILE_HEADER {
    *    WORD  Machine;
    *    WORD  NumberOfSections;
    *    DWORD TimeDateStamp;
    *    DWORD PointerToSymbolTable;
    *    DWORD NumberOfSymbols;
    *    WORD  SizeOfOptionalHeader;
    *    WORD  Characteristics;
    * } */
   [StructLayout(LayoutKind.Sequential)]
   public struct SImageFileHeader {
      public EImageFileMachine Machine;
      public ushort NumberOfSections;
      public uint TimeDateStamp;
      public uint PointerToSymbolTable;
      public uint NumberOfSymbols;
      public ushort SizeOfOptionalHeader;
      public EImageFileCharacteristics Characteristics;
   }

}
