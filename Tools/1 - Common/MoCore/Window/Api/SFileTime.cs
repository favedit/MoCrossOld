using System;
using System.Runtime.InteropServices;
using System.Security;

namespace MO.Core.Window.Api {

   [StructLayout(LayoutKind.Sequential, Pack = 4)]
   public struct SFileTime {
      public uint dwLowDateTime;
      public uint dwHighDateTime;
   }
}
