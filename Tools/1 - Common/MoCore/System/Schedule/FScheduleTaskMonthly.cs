using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Content;
using MO.Common.Lang;

namespace MObj.Windows.Schedule {

   public class FScheduleTaskMonthly : FScheduleTask {

      public const string RES_DETAIL_MONTH = "detail.month";

      public const string RES_DETAIL_DATE = "detail.date";

      private static IResource _resource = RResource.Find(typeof(FScheduleTaskMonthly));

      public FScheduleTaskMonthly() {
         _type = EScheduleTaskType.Monthly;
      }

      public string MonthsInfo {
         get {
            // Week info
            FString info = new FString();
            info.Append((_months & EScheduleMonth.Month1) == EScheduleMonth.Month1 ? ",1" : null);
            info.Append((_months & EScheduleMonth.Month2) == EScheduleMonth.Month2 ? ",2" : null);
            info.Append((_months & EScheduleMonth.Month3) == EScheduleMonth.Month3 ? ",3" : null);
            info.Append((_months & EScheduleMonth.Month4) == EScheduleMonth.Month4 ? ",4" : null);
            info.Append((_months & EScheduleMonth.Month5) == EScheduleMonth.Month5 ? ",5" : null);
            info.Append((_months & EScheduleMonth.Month6) == EScheduleMonth.Month6 ? ",6" : null);
            info.Append((_months & EScheduleMonth.Month7) == EScheduleMonth.Month7 ? ",7" : null);
            info.Append((_months & EScheduleMonth.Month8) == EScheduleMonth.Month8 ? ",8" : null);
            info.Append((_months & EScheduleMonth.Month9) == EScheduleMonth.Month9 ? ",9" : null);
            info.Append((_months & EScheduleMonth.Month10) == EScheduleMonth.Month10 ? ",10" : null);
            info.Append((_months & EScheduleMonth.Month11) == EScheduleMonth.Month11 ? ",11" : null);
            info.Append((_months & EScheduleMonth.Month12) == EScheduleMonth.Month12 ? ",12" : null);
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
         if (_months == EScheduleMonth.None) {
            return null;
         }
         if (_monthType == EScheduleMonthType.Date) {
            return _resource.FindDisplay(RES_DETAIL_MONTH, MonthsInfo, _monthDay, _time.Hour, _time.Minute);
         }
         string monthWeek = _resource.FindDisplay("item." + _monthWeek.ToString());
         string monthWeekDay = _resource.FindDisplay("week." + _monthWeekDay);
         return _resource.FindDisplay(RES_DETAIL_DATE, MonthsInfo, monthWeek, monthWeekDay, _time.Hour, _time.Minute);
      }

      protected bool TestMonth(int month) {
         switch (month) {
            case 1:
               return (_months & EScheduleMonth.Month1) == EScheduleMonth.Month1;
            case 2:
               return (_months & EScheduleMonth.Month2) == EScheduleMonth.Month2;
            case 3:
               return (_months & EScheduleMonth.Month3) == EScheduleMonth.Month3;
            case 4:
               return (_months & EScheduleMonth.Month4) == EScheduleMonth.Month4;
            case 5:
               return (_months & EScheduleMonth.Month5) == EScheduleMonth.Month5;
            case 6:
               return (_months & EScheduleMonth.Month6) == EScheduleMonth.Month6;
            case 7:
               return (_months & EScheduleMonth.Month7) == EScheduleMonth.Month7;
            case 8:
               return (_months & EScheduleMonth.Month8) == EScheduleMonth.Month8;
            case 9:
               return (_months & EScheduleMonth.Month9) == EScheduleMonth.Month9;
            case 10:
               return (_months & EScheduleMonth.Month10) == EScheduleMonth.Month10;
            case 11:
               return (_months & EScheduleMonth.Month11) == EScheduleMonth.Month11;
            case 12:
               return (_months & EScheduleMonth.Month12) == EScheduleMonth.Month12;
         }
         return false;
      }

      protected DateTime QueryNextInMonth(DateTime date) {
         for (int n = 0; n < RDate.MONTHS; n++) {
            if (TestMonth(date.Month)) {
               return date;
            }
            date = date.AddMonths(1);
         }
         return DateTime.MaxValue;
      }

      protected DateTime QueryNextMonthByDate(DateTime date, long ticks) {
         DateTime month = QueryNextInMonth(date);
         int endYear = month.Year + 4;
         while (true) {
            int maxDay = DateTime.DaysInMonth(month.Year, month.Month);
            if (_monthDay <= maxDay) {
               break;
            }
            if (month.Year > endYear) {
               return DateTime.MaxValue;
            }
            month = QueryNextInMonth(month.AddMonths(1));
         }
         return month.AddDays(_monthDay - 1).AddTicks(ticks);
      }

      protected DateTime QueryNextMonthByInterval(DateTime date, long ticks) {
         DateTime fromDate = QueryNextInMonth(date);
         DateTime firstDate = new DateTime(fromDate.Year, fromDate.Month, 1);
         int week = (int)_monthWeek;
         if ((int)_monthWeekDay < (int)firstDate.DayOfWeek) {
            week++;
         }
         int day = RDate.WEEK_DAYS * (week - 1) + _monthWeekDay - (int)firstDate.DayOfWeek + 1;
         if (day > DateTime.DaysInMonth(firstDate.Year, firstDate.Month)) {
            day -= RDate.WEEK_DAYS;
         }
         return new DateTime(fromDate.Year, fromDate.Month, day).AddTicks(ticks);
      }

      protected DateTime QueryNextRunTimeInner(DateTime date) {
         // Before first start datetime
         long ticks = _time.TimeOfDay.Ticks;
         DateTime startDate = _beginDate.AddTicks(ticks);
         if (date < startDate) {
            date = startDate.Date;
         }
         // Week days
         if (_monthType == EScheduleMonthType.Date) {
            // This month day
            DateTime firstDay;
            int monthDays = DateTime.DaysInMonth(date.Year, date.Month);
            if (_monthDay > monthDays) {
               firstDay = RDate.FirstDateForMonth(date.AddMonths(1));
            } else {
               DateTime monthDate = new DateTime(date.Year, date.Month, Math.Min(_monthDay, monthDays)).AddTicks(ticks);
               firstDay = RDate.FirstDateForMonth((monthDate < date) ? date.AddMonths(1) : date);
            }
            // Next month day
            return QueryNextMonthByDate(firstDay, ticks);
         } else if (_monthType == EScheduleMonthType.Interval) {
            DateTime resultDate = QueryNextMonthByInterval(date, ticks);
            if (resultDate < date) {
               resultDate = QueryNextMonthByInterval(RDate.FirstDateForMonth(date).AddMonths(1), ticks);
            }
            return resultDate;
         }
         return DateTime.MaxValue;
      }

      public override DateTime QueryNextRunTime(DateTime date) {
         DateTime endDate = _endDate.AddDays(1).AddMilliseconds(-1);
         // Check end date
         if (_endValid && date > endDate || _months == EScheduleMonth.None) {
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
