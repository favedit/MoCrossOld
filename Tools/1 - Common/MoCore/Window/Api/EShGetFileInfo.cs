using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Api {

   [Flags()]
   public enum EShGetFileInfo : uint {
      AddOverlays = 0x20,
      AttrSpecified = 0x20000,
      Attributes = 0x800,
      DisplayName = 0x200,
      ExeType = 0x2000,
      Icon = 0x100,
      IconLocation = 0x1000,
      LargeIcon = 0,
      LinkOverlay = 0x8000,
      OpenIcon = 2,
      OverlayIndex = 0x40,
      Pidl = 8,
      Selected = 0x10000,
      ShellIconSize = 4,
      SmallIcon = 0x01,
      SysIconIndex = 0x4000,
      TypeName = 0x400,
      UseFileAttributes = 0x10
   }

}
