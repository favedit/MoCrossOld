using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {
   
   public class RShlwapi {

      /* HRESULT StrRetToBuf(
       *    STRRET *pstr,
       *    PCUITEMID_CHILD pidl,
       *    LPTSTR pszBuf,
       *    UINT cchBuf
       * ) */
      [DllImport("Shlwapi.Dll", CharSet = CharSet.Auto)]
      public static extern Int32 StrRetToBuf(
         IntPtr pstr,
         IntPtr pidl, 
         StringBuilder pszBuf, 
         int cchBuf);
   
   }

}
