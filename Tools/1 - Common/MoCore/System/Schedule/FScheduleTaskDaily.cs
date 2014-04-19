using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Content;
using MO.Common.Lang;

namespace MObj.Windows.Schedule {

   public class FScheduleTaskDaily : FScheduleTask {

      public const string RES_DETAIL_DAY = "detail.day";

      public const string RES_DETAIL_DATE = "detail.date";

      private static IResource _resource = RResource.Find(typeof(FScheduleTaskDaily));

      public FScheduleTaskDaily() {
         _type = EScheduleTaskType.Daily;
      }

      public override string GetNameInfo() {
         return _resource.FindDisplay(RES_NAME);
      }

      public override string GetDetailInfo() {
         if (_dayInterval == 0) {
            return _resource.FindDisplay(RES_DETAIL_DAY, _time.Hour, _time.Minute);
         }
         return _resource.FindDisplay(RES_DETAIL_DATE, _dayInterval, _time.Hour, _time.Minute);
      }

      protected DateTime QueryNextRunTimeInner(DateTime date) {
         // Before first start datetime
         long ticks = _time.TimeOfDay.Ticks;
         DateTime startDate = _beginDate.AddTicks(ticks);
         if (date < startDate) {
            return startDate;
         }
         // In this day
         TimeSpan diff = date.Date - _beginDate;
         int day = (_dayInterval > 0) ? diff.Days % _dayInterval : 0;
         if (day == 0) {
            DateTime execute = date.Date.AddTicks(ticks);
            if (execute > date) {
               return execute;
            }
            day = -1;
         }
         // Out this day
         date = date.Date.AddDays(_dayInterval - day);
         return date.AddTicks(ticks);
      }

      public override DateTime QueryNextRunTime(DateTime date) {
         DateTime endDate = _endDate.AddDays(1).AddMilliseconds(-1);
         // Check end date
         if (_endValid && date > endDate) {
            return DateTime.MaxValue;
         }
         // Query next date
         DateTime resultDate = QueryNextRunTimeInner(date);
         if (_endValid && resultDate > endDate) {
            return DateTime.MaxValue;
         }
         return resultDate;
      }

   }

}
