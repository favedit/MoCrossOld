using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   /* typedef struct PROCESSENTRY32 {
    *    DWORD dwSize;
    *    DWORD cntUsage;
    *    DWORD th32ProcessID;
    *    DWORD th32DefaultHeapID;
    *    DWORD th32ModuleID;
    *    DWORD cntThreads;
    *    DWORD th32ParentProcessID;
    *    LONG  pcPriClassBase;
    *    DWORD dwFlags;
    *    TCHAR szExeFile[MAX_PATH];
    *    DWORD th32MemoryBase;
    *    DWORD th32AccessKey;
    * } */
   [StructLayout(LayoutKind.Sequential)]
   public struct SProcessEntry32 {
      public uint dwSize;
      public uint cntUsage;
      public int th32ProcessID;
      public IntPtr th32DefaultHeapID;
      public uint th32ModuleID;

      // 创建线程数
      public uint cntThreads;
      public uint th32ParentProcessID;
      
      // 基本优先权
      public int pcPriClassBase;
      public uint dwFlags;
      [MarshalAs(UnmanagedType.ByValTStr, SizeConst = 260)]
      public string szExeFile;
   }
}
