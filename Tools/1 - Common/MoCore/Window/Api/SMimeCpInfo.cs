using System;
using System.Runtime.InteropServices;
using System.Security;

namespace MO.Core.Window.Api {

   [StructLayout(LayoutKind.Sequential, Pack = 4)]
   public struct SMimeCpInfo {
      public uint dwFlags;
      public uint uiCodePage;
      public uint uiFamilyCodePage;
      [MarshalAs(UnmanagedType.ByValArray, SizeConst = 0x40)]
      public ushort[] wszDescription;
      [MarshalAs(UnmanagedType.ByValArray, SizeConst = 50)]
      public ushort[] wszWebCharset;
      [MarshalAs(UnmanagedType.ByValArray, SizeConst = 50)]
      public ushort[] wszHeaderCharset;
      [MarshalAs(UnmanagedType.ByValArray, SizeConst = 50)]
      public ushort[] wszBodyCharset;
      [MarshalAs(UnmanagedType.ByValArray, SizeConst = 0x20)]
      public ushort[] wszFixedWidthFont;
      [MarshalAs(UnmanagedType.ByValArray, SizeConst = 0x20)]
      public ushort[] wszProportionalFont;
      public byte bGDICharset;
   }
}
