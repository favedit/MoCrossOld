using System;
using System.Collections.Generic;
using System.Text;

namespace MObj.Windows.Schedule {

   public interface IScheduleConsole {

      IScheduleFactory Factory { get;}

      FSchedules Schedules { get;}

   }

}
