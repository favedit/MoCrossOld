using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Api {

   public enum ESePrivilege : uint {
      None = 0x00000000,
      EnabledByDefault = 0x00000001,
      Enabled = 0x00000002,
      Removed = 0X00000004,
      UsedForAccess = 0x80000000
   }
}
