using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   [StructLayout(LayoutKind.Sequential)]
   public struct SCopyData {

      public int dwData;

      public int cbData;

      public IntPtr lpData;
   }

}
