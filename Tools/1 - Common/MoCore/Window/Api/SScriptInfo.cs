using System;
using System.Runtime.InteropServices;
using System.Security;

namespace MO.Core.Window.Api {

   [StructLayout(LayoutKind.Sequential, Pack = 4)]
   public struct SScriptInfo {
      public byte ScriptId;
      public uint uiCodePage;
      [MarshalAs(UnmanagedType.ByValArray, SizeConst = 0x30)]
      public ushort[] wszDescription;
      [MarshalAs(UnmanagedType.ByValArray, SizeConst = 0x20)]
      public ushort[] wszFixedWidthFont;
      [MarshalAs(UnmanagedType.ByValArray, SizeConst = 0x20)]
      public ushort[] wszProportionalFont;
   }
}
