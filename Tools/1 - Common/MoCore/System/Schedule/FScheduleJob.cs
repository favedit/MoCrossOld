using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Content;
using MO.Common.Lang;

namespace MObj.Windows.Schedule {

   public class FScheduleJob {

      private static ILogger _logger = RLogger.Find(typeof(FScheduleJob));

      private FSchedule _schedule;

      public FScheduleJob() {
      }

      public FScheduleJob(FSchedule schedule) {
         _schedule = schedule;
      }

      public FSchedule Schedule {
         get { return _schedule; }
         set { _schedule = value; }
      }

      public void Process() {
         _schedule.Process();
      }

   }

}
