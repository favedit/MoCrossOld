using System;
using System.Runtime.InteropServices;
using System.Security;

namespace MO.Core.Window.Api {

   [StructLayout(LayoutKind.Explicit, Pack = 4)]
   public struct SMidlIWinTypes0009 {

      [FieldOffset(0)]
      public int hInproc;

      [FieldOffset(0)]
      public int hRemote;
   }
}
