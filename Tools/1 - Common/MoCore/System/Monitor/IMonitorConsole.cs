using System;
using System.Collections.Generic;
using System.Text;
using MO.Core.Aop;

namespace MO.Core.Monitor {

   [AProxyFace]
   public interface IMonitorConsole {

      void AddMonitor(object test);

   }

}
