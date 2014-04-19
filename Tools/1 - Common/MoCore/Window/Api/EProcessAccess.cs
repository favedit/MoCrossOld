using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Api {

   public enum EProcessAccess {
      Terminate = 0x0001,
      CreateThread = 0x0002,
      SetSessionid = 0x0004,
      VmOperation = 0x0008,
      VmRead = 0x0010,
      VmWrite = 0x0020,
      DupHandle = 0x0040,
      CreateProcess = 0x0080,
      SetQuota = 0x0100,
      SetInformation = 0x0200,
      QueryInformation = 0x0400,
      SuspendResume = 0x0800,
      QueryLimitedInformation = 0x1000,
      AllAccess = 0xffff
   }

}
