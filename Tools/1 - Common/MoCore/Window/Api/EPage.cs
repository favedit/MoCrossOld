using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Api {

   public enum EPage {
      None = 0x00,
      Execute = 0x10,
      ExeRead = 0x20,
      ExeReadWrite = 0x40,
      ExeWriteCopy = 0x80,
      NoAccess = 0x01,
      Readonly = 0x02,
      ReadWrite = 0x04,
      WriteCopy = 0x08,
      Guard = 0x100,
      NoCache = 0x200,
      WriteCombine = 0x400
   }

}
