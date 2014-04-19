using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Api {

   [Flags()]
   public enum EShgfi : uint {
      ADDOVERLAYS = 0x20,
      ATTR_SPECIFIED = 0x20000,
      ATTRIBUTES = 0x800,
      DISPLAYNAME = 0x200,
      EXETYPE = 0x2000,
      ICON = 0x100,
      ICONLOCATION = 0x1000,
      LARGEICON = 0,
      LINKOVERLAY = 0x8000,
      OPENICON = 2,
      OVERLAYINDEX = 0x40,
      PIDL = 8,
      SELECTED = 0x10000,
      SHELLICONSIZE = 4,
      SMALLICON = 1,
      SYSICONINDEX = 0x4000,
      TYPENAME = 0x400,
      USEFILEATTRIBUTES = 0x10
   }

}
