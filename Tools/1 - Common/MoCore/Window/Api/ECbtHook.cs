using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Api {

   public enum ECbtHook {
      MoveSize = 0,
      MinMax = 1,
      Qs = 2,
      CreateWnd = 3,
      DestroyWnd = 4,
      Activate = 5,
      ClickSkipped = 6,
      KeySkipped = 7,
      SysCommand = 8,
      SetFocus = 9
   }

}
