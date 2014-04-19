using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   /* typedef struct FOLDERSETTINGS{
    *    UINT ViewMode;
    *    UINT fFlags;
    * } */
   [StructLayout(LayoutKind.Sequential)]
   public class SFolderSettings {

      uint ViewMode;

      uint fFlags;
   }
}
