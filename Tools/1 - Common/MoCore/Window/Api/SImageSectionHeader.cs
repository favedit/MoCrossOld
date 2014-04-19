using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   /* typedef struct IMAGE_SECTION_HEADER {
    *    BYTE    Name[8];
    *    union {
    *       DWORD   PhysicalAddress;
    *       DWORD   VirtualSize;
    *    } Misc;
    *    DWORD   VirtualAddress;
    *    DWORD   SizeOfRawData;
    *    DWORD   PointerToRawData;
    *    DWORD   PointerToRelocations;
    *    DWORD   PointerToLinenumbers;
    *    WORD    NumberOfRelocations;
    *    WORD    NumberOfLinenumbers;
    *    DWORD   Characteristics;
    * } */
   [StructLayout(LayoutKind.Explicit)]
   public struct SImageSectionHeader {
      [FieldOffset(0)]
      [MarshalAs(UnmanagedType.ByValArray, SizeConst = 8)]
      public byte[] Name;
      [FieldOffset(8)]
      public uint PhysicalAddress;
      [FieldOffset(8)]
      public uint VirtualSize;
      [FieldOffset(12)]
      public uint VirtualAddress;
      [FieldOffset(16)]
      public uint SizeOfRawData;
      [FieldOffset(20)]
      public uint PointerToRawData;
      [FieldOffset(24)]
      public uint PointerToRelocations;
      [FieldOffset(28)]
      public uint PointerToLinenumbers;
      [FieldOffset(32)]
      public ushort NumberOfRelocations;
      [FieldOffset(34)]
      public ushort NumberOfLinenumbers;
      [FieldOffset(36)]
      public uint Characteristics;
   }

}
