using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Api {

   public enum EProcessToken {
      AssignPrimary = 0x0001,
      Duplicate = 0x0002,
      Impersonate = 0x0004,
      Query = 0x0008,
      QuerySource = 0x0010,
      AdjustPrivileges = 0x0020,
      AdjustGroups = 0x0040,
      AdjustDefault = 0x0080,
      AdjustSessionid = 0x0100
   }

}
