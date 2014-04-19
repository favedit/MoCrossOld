using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   /* typedef struct MSG {
    *    HWND   hwnd;
    *    UINT   message;
    *    WPARAM wParam;
    *    LPARAM lParam;
    *    DWORD  time;
    *    POINT  pt;
    * } */
   [StructLayout(LayoutKind.Sequential, CharSet = CharSet.Auto)]
   public struct SMsg {

      public IntPtr hwnd;

      public uint message;

      public uint wParam;

      public uint lParam;

      public uint time;

      public SPoint pt;
   }

}
