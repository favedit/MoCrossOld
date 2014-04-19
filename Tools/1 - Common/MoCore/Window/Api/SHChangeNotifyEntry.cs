using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {
   
   [StructLayout(LayoutKind.Sequential, CharSet = CharSet.Auto)]
   public struct SHChangeNotifyEntry {
      
      public IntPtr pIdl;
      
      //[MarshalAs(UnmanagedType.Bool)]
      //public Boolean Recursively;
      public Int32 Recursively;
   
   }

}
