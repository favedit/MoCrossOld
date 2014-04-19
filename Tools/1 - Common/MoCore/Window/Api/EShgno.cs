using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Api {

   [Flags()]
   public enum EShgno {
      Normal = 0x0,
      InFolder = 0x1,
      ForEditing = 0x1000,
      ForAddressBar = 0x4000,
      ForParsing = 0x8000,
   }

}
