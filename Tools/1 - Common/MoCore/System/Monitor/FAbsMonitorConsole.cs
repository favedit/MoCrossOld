using System;
using System.Collections.Generic;
using System.Text;
using MO.Core.Aop;

namespace MO.Core.Monitor {

   public abstract class FAbsMonitorConsole : FAopObject, IMonitorConsole {

      [ALinkerAttribute]
      protected IMonitorConsole _monitor3;

      public abstract void AddMonitor(object test);

   }

}
