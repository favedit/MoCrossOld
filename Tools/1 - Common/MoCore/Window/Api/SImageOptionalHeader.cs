using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   /* typedef struct IMAGE_OPTIONAL_HEADER {
    *    WORD    Magic;
    *    BYTE    MajorLinkerVersion;
    *    BYTE    MinorLinkerVersion;
    *    DWORD   SizeOfCode;
    *    DWORD   SizeOfInitializedData;
    *    DWORD   SizeOfUninitializedData;
    *    DWORD   AddressOfEntryPoint;
    *    DWORD   BaseOfCode;
    *    DWORD   BaseOfData;
    *    DWORD   ImageBase;
    *    DWORD   SectionAlignment;
    *    DWORD   FileAlignment;
    *    WORD    MajorOperatingSystemVersion;
    *    WORD    MinorOperatingSystemVersion;
    *    WORD    MajorImageVersion;
    *    WORD    MinorImageVersion;
    *    WORD    MajorSubsystemVersion;
    *    WORD    MinorSubsystemVersion;
    *    DWORD   Win32VersionValue;
    *    DWORD   SizeOfImage;
    *    DWORD   SizeOfHeaders;
    *    DWORD   CheckSum;
    *    WORD    Subsystem;
    *    WORD    DllCharacteristics;
    *    DWORD   SizeOfStackReserve;
    *    DWORD   SizeOfStackCommit;
    *    DWORD   SizeOfHeapReserve;
    *    DWORD   SizeOfHeapCommit;
    *    DWORD   LoaderFlags;
    *    DWORD   NumberOfRvaAndSizes;
    *    IMAGE_DATA_DIRECTORY DataDirectory[IMAGE_NUMBEROF_DIRECTORY_ENTRIES];
    * } */
   [StructLayout(LayoutKind.Sequential)]
   public struct SImageOptionalHeader {
      public ushort Magic;
      public byte MajorLinkerVersion;
      public byte MinorLinkerVersion;
      public uint SizeOfCode;
      public uint SizeOfInitializedData;
      public uint SizeOfUninitializedData;
      public uint AddressOfEntryPoint;
      public uint BaseOfCode;
      public uint BaseOfData;
      public uint ImageBase;
      public uint SectionAlignment;
      public uint FileAlignment;
      public ushort MajorOperatingSystemVersion;
      public ushort MinorOperatingSystemVersion;
      public ushort MajorImageVersion;
      public ushort MinorImageVersion;
      public ushort MajorSubsystemVersion;
      public ushort MinorSubsystemVersion;
      public uint Win32VersionValue;
      public uint SizeOfImage;
      public uint SizeOfHeaders;
      public uint CheckSum;
      public ushort Subsystem;
      public ushort DllCharacteristics;
      public uint SizeOfStackReserve;
      public uint SizeOfStackCommit;
      public uint SizeOfHeapReserve;
      public uint SizeOfHeapCommit;
      public uint LoaderFlags;
      public uint NumberOfRvaAndSizes;
      [MarshalAs(UnmanagedType.ByValArray, SizeConst = 16)]
      public SImageDataDirectory[] DataDirectory;
   }
   
}
