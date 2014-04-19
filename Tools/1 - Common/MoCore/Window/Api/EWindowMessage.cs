using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Api {

   public class EWindowMessage {

      public const int WM_DRIVEADD = 0x0401;
      public const int WM_DRIVEREMOVE = WM_DRIVEADD + 1;

      public const int WH_KEYBOARD = 2;
      public const int WH_KEYBOARD_LL = 13;

      public const int WM_KEYDOWN = 0x100;
      public const int WM_KEYUP = 0x101;
      public const int WM_SYSKEYDOWN = 0x104;
      public const int WM_SYSKEYUP = 0x105;
      public const int WM_SHNOTIFY = 0x401;

   }
}
