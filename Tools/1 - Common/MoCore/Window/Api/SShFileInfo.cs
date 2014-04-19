using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   [StructLayout(LayoutKind.Sequential, CharSet = CharSet.Auto)]
   public struct SShFileInfo {

      public IntPtr hIcon;

      public int iIcon;

      public ESfgao dwAttributes;

      [MarshalAs(UnmanagedType.ByValTStr, SizeConst = RShell32.MAX_PATH)]
      public string szDisplayName;

      [MarshalAs(UnmanagedType.ByValTStr, SizeConst = 80)]
      public string szTypeName;
   }

}
