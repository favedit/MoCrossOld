using System;
using System.Runtime.InteropServices;
using System.Security;

namespace MO.Core.Window.Api {

   [StructLayout(LayoutKind.Sequential, Pack = 8)]
   public struct SScripFontInfo {

      public long scripts;

      [MarshalAs(UnmanagedType.ByValArray, SizeConst = 0x20)]
      public ushort[] wszFont;
   }
}
