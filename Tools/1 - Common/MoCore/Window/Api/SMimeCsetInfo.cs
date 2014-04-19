using System;
using System.Runtime.InteropServices;
using System.Security;

namespace MO.Core.Window.Api {

   [StructLayout(LayoutKind.Sequential, Pack = 4)]
   public struct SMimeCsetInfo {

      public uint uiCodePage;

      public uint uiInternetEncoding;

      [MarshalAs(UnmanagedType.ByValArray, SizeConst = 50)]
      public ushort[] wszCharset;
   }
}
