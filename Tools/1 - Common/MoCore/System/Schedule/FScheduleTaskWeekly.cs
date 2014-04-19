using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Content;
using MO.Common.Lang;

namespace MObj.Windows.Schedule {

   public class FScheduleTaskWeekly : FScheduleTask {

      public const string RES_DETAIL_WEEK = "detail.week";

      public const string RES_DETAIL_INTERVAL = "detail.interval";

      private static IResource _resource = RResource.Find(typeof(FScheduleTaskWeekly));

      public FScheduleTaskWeekly() {
         _type = EScheduleTaskType.Weekly;
      }

      public string WeekDaysInfo {
         get {
            // Week info
            FString info = new FString();
            info.Append((_weekDays & EScheduleWeekDay.Monday) == EScheduleWeekDay.Monday ? "," + _resource.FindDisplay("week.monday") : null);
            info.Append((_weekDays & EScheduleWeekDay.Tuesday) == EScheduleWeekDay.Tuesday ? "," + _resource.FindDisplay("week.tuesday") : null);
            info.Append((_weekDays & EScheduleWeekDay.Wednesday) == EScheduleWeekDay.Wednesday ? "," + _resource.FindDisplay("week.wednesday") : null);
            info.Append((_weekDays & EScheduleWeekDay.Thursday) == EScheduleWeekDay.Thursday ? "," + _resource.FindDisplay("week.thursday") : null);
            info.Append((_weekDays & EScheduleWeekDay.Friday) == EScheduleWeekDay.Friday ? "," + _resource.FindDisplay("week.friday") : null);
            info.Append((_weekDays & EScheduleWeekDay.Saturday) == EScheduleWeekDay.Saturday ? "," + _resource.FindDisplay("week.saturday") : null);
            info.Append((_weekDays & EScheduleWeekDay.Sunday) == EScheduleWeekDay.Sunday ? "," + _resource.FindDisplay("week.sunday") : null);
            string result = info.ToString();
            if (!info.IsEmpty) {
               result = result.Substring(1);
            }
            return result;
         }
      }

      public override string GetNameInfo() {
         return _resource.FindDisplay(RES_NAME);
      }

      public override string GetDetailInfo() {
         if (_weekDays == EScheduleWeekDay.None) {
            return null;
         }
         if (_weekInterval == 0) {
            return _resource.FindDisplay(RES_DETAIL_WEEK, WeekDaysInfo, _time.Hour, _time.Minute);
         }
         return _resource.FindDisplay(RES_DETAIL_INTERVAL, _weekInterval, WeekDaysInfo, _time.Hour, _time.Minute);
      }

      protected bool TestWeekDay(DayOfWeek weekDay) {
         switch (weekDay) {
            case DayOfWeek.Monday:
               return (_weekDays & EScheduleWeekDay.Monday) == EScheduleWeekDay.Monday;
            case DayOfWeek.Tuesday:
               return (_weekDays & EScheduleWeekDay.Tuesday) == EScheduleWeekDay.Tuesday;
            case DayOfWeek.Wednesday:
               return (_weekDays & EScheduleWeekDay.Wednesday) == EScheduleWeekDay.Wednesday;
            case DayOfWeek.Thursday:
               return (_weekDays & EScheduleWeekDay.Thursday) == EScheduleWeekDay.Thursday;
            case DayOfWeek.Friday:
               return (_weekDays & EScheduleWeekDay.Friday) == EScheduleWeekDay.Friday;
            case DayOfWeek.Saturday:
               return (_weekDays & EScheduleWeekDay.Saturday) == EScheduleWeekDay.Saturday;
            case DayOfWeek.Sunday:
               return (_weekDays & EScheduleWeekDay.Sunday) == EScheduleWeekDay.Sunday;
         }
         return false;
      }

      protected DateTime QueryNextInWeek(DateTime date) {
         for (int n = 0; n < RDate.WEEK_DAYS; n++) {
            if (TestWeekDay(date.DayOfWeek)) {
               return date;
            }
            date = date.AddDays(1);
         }
         return DateTime.MaxValue;
      }

      protected DateTime QueryNextRunTimeInner(DateTime date) {
         // Before first start datetime
         long ticks = _time.TimeOfDay.Ticks;
         DateTime startDate = _beginDate.AddTicks(ticks);
         if (date < startDate) {
            date = startDate.Date;
         }
         // Week days 
         TimeSpan diff = date.Date - _beginDate;
         int weeks = diff.Days / RDate.WEEK_DAYS;
         int week = weeks % (_weekInterval + 1);
         DateTime fromDate = date.Date.AddTicks(ticks);
         if (week == 0) {
            if (fromDate < date) {
               fromDate = fromDate.AddDays(1);
            }
            DateTime execute = QueryNextInWeek(fromDate);
            if (execute > date && execute.DayOfWeek >= fromDate.DayOfWeek) {
               return execute;
            }
            week = -1;
         }
         // Out this week
         fromDate = fromDate.AddDays(RDate.WEEK_DAYS * (_weekInterval - week));
         fromDate = fromDate.AddDays(-(int)fromDate.DayOfWeek);
         return QueryNextInWeek(fromDate);
      }

      public override DateTime QueryNextRunTime(DateTime date) {
         DateTime endDate = _endDate.AddDays(1).AddMilliseconds(-1);
         // Check end date
         if (_endValid && date > endDate || _weekDays == EScheduleWeekDay.None) {
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
