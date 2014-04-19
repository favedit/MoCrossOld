using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   /* typedef struct TBBUTTON {
    *    int iBitmap;
    *    int idCommand;
    *    BYTE fsState;
    *    BYTE fsStyle;
    *    [WIN64] BYTE bReserved[6];
    *    [WIN32] BYTE bReserved[2];
    *    DWORD_PTR dwData;
    *    INT_PTR iString;
    * } */
   [StructLayout(LayoutKind.Sequential)]
   public struct STbButton {
      public int iBitmap;
      public int idCommand;
      public byte fsState;
      public byte fsStyle;
#if _WIN64
      [MarshalAs(UnmanagedType.ByValArray, SizeConst=6)]
      public byte[] bReserved;
#else
      [MarshalAs(UnmanagedType.ByValArray, SizeConst = 2)]
      public byte[] bReserved;
#endif
      public uint dwData;
      public uint iString;
   }

}
