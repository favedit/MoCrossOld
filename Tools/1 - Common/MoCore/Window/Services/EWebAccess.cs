using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Services {

   [Flags]
   public enum EWebAccess{

      Read = 0x00000001,
      Write = 0x00000002,
      Execute = 0x00000004,
      Script = 0x00000200,
      Source = 0x00000010,
      NoPhysicalDir = 0x00008000,
      NoRemoteRead = 0x00001000,
      NoRemoteWrite = 0x00000400,
      NoRemoteExecute = 0x00002000,
      NoRemoteScript = 0x00004000
   }

}
