using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Api {

   [Flags()]
   public enum EShcontf {
      Folders = 0x20,
      NonFolders = 0x40,
      IncludeHidden = 0x80,
      InitOnFirstNext = 0x100,
      NetPrinterSrch = 0x200,
      Shareable = 0x400,
      Storage = 0x800
   }

}
