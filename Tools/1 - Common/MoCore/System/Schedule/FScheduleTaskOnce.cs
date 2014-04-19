using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Content;
using MO.Common.Lang;

namespace MObj.Windows.Schedule {

   public class FScheduleTaskOnce : FScheduleTask {

      public const string RES_DETAIL_ONCE = "detail.once";

      private static IResource _resource = RResource.Find(typeof(FScheduleTaskOnce));

      public FScheduleTaskOnce() {
         _type = EScheduleTaskType.Once;
      }

      public override string GetNameInfo() {
         return _resource.FindDisplay(RES_NAME);
      }

      public override string GetDetailInfo() {
         return _resource.FindDisplay(RES_DETAIL_ONCE, _onceDate.Month, _onceDate.Day, _time.Hour, _time.Minute);
      }

      public override DateTime QueryNextRunTime(DateTime date) {
         DateTime execute = _onceDate.Date.AddTicks(_time.TimeOfDay.Ticks);
         if (execute > date) {
            return execute;
         }
         return DateTime.MaxValue;
      }

   }

}
