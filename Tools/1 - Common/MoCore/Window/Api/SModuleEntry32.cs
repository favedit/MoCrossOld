using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   /* typedef struct MODULEENTRY32 {
    *    DWORD dwSize;
    *    DWORD th32ModuleID;
    *    DWORD th32ProcessID;
    *    DWORD GlblcntUsage;
    *    DWORD ProccntUsage;
    *    BYTE* modBaseAddr;
    *    DWORD modBaseSize;
    *    HMODULE hModule;
    *    TCHAR szModule[MAX_MODULE_NAME32 + 1];
    *    TCHAR szExePath[MAX_PATH];
    * } */
   [StructLayout(LayoutKind.Sequential)]
   public struct SModuleEntry32 {
      public int dwSize;
      public uint th32ModuleID;
      public uint th32ProcessID;
      public uint GlblcntUsage;
      public uint ProccntUsage;
      public uint modBaseAddr;
      public uint modBaseSize;
      public IntPtr hModule;
      [MarshalAs(UnmanagedType.ByValTStr, SizeConst = 256)]
      //[MarshalAs(UnmanagedType.ByValTStr, SizeConst = 512)]
      public string szModule;
      [MarshalAs(UnmanagedType.ByValTStr, SizeConst = 260)]
      //[MarshalAs(UnmanagedType.ByValTStr, SizeConst = 520)]
      public string szExePath;
   }

}
