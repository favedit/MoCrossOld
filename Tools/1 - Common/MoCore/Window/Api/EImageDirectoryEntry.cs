using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Api {

   public enum EImageDirectoryEntry :int {
      Export = 0,
      Import = 1,
      Resource = 2,
      Exception = 3,
      Security = 4,
      Basereloc = 5,
      Debug = 6,
      Architecture = 7,
      Globalptr = 8,
      Tls = 9,
      LoadConfig = 10,
      BoundImport = 11,
      Iat = 12,
      DelayImport = 13,
      ComDescriptor = 14
   }

}
