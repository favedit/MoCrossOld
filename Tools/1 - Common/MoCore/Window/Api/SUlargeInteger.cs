using System;
using System.Runtime.InteropServices;
using System.Security;

namespace MO.Core.Window.Api {

   [StructLayout(LayoutKind.Sequential, Pack = 8)]
   public struct SUlargeInteger {
      public ulong QuadPart;
   }
}
