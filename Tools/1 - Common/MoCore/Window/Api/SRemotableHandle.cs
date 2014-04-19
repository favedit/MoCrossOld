using System;
using System.Runtime.InteropServices;
using System.Security;

namespace MO.Core.Window.Api {

   [StructLayout(LayoutKind.Sequential, Pack = 4)]
   public struct SRemotableHandle {

      public int fContext;

      public SMidlIWinTypes0009 u;
   }

}
