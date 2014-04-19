using System;
using System.Runtime.InteropServices;
using System.Security;

namespace MO.Core.Window.Api {

   [StructLayout(LayoutKind.Sequential, Pack = 8)]
   public struct SStatsTg {
      [MarshalAs(UnmanagedType.LPWStr)]
      public string pwcsName;
      public uint type;
      public SUlargeInteger cbSize;
      public SFileTime mtime;
      public SFileTime ctime;
      public SFileTime atime;
      public uint grfMode;
      public uint grfLocksSupported;
      public Guid clsid;
      public uint grfStateBits;
      public uint reserved;
   }
}
