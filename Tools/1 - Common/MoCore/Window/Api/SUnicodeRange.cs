using System;
using System.Runtime.InteropServices;
using System.Security;

namespace MO.Core.Window.Api {

   [StructLayout(LayoutKind.Sequential, Pack = 2)]
   public struct SUnicodeRange {

      public ushort wcFrom;

      public ushort wcTo;
   }
}
