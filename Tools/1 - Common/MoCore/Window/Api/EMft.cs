using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Api {

   [Flags]
   public enum EMft : uint {
      GRAYED = 0x00000003,
      DISABLED = 0x00000003,
      CHECKED = 0x00000008,
      SEPARATOR = 0x00000800,
      RADIOCHECK = 0x00000200,
      BITMAP = 0x00000004,
      OWNERDRAW = 0x00000100,
      MENUBARBREAK = 0x00000020,
      MENUBREAK = 0x00000040,
      RIGHTORDER = 0x00002000,
      BYCOMMAND = 0x00000000,
      BYPOSITION = 0x00000400,
      POPUP = 0x00000010
   }

}
