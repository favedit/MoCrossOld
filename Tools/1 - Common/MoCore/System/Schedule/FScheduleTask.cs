using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Content;
using MO.Common.Lang;

namespace MObj.Windows.Schedule {

   public class FScheduleTask : IScheduleTask {

      public const string NAME = "Task";

      public const string RES_NAME = "name";

      public const string FMT_DATE = "YYYYMMDD";

      public const string FMT_TIME = "HH24MISS";

      public const string PTY_TYPE = "type";

      public const string PTY_VALID = "valid";

      public const string PTY_DAY_INTERVAL = "day_interval";

      public const string PTY_WEEK_INTERVAL = "week_interval";

      public const string PTY_WEEKDAYS = "week_days";

      public const string PTY_MONTH_TYPE = "month_type";

      public const string PTY_MONTH_INTERVAL = "month_interval";

      public const string PTY_MONTH_DAY = "month_day";

      public const string PTY_MONTH_WEEK = "month_week";

      public const string PTY_MONTH_WEEK_DAY = "month_week_day";

      public const string PTY_MONTHS = "months";

      public const string PTY_ONCE_DATE = "once_date";

      public const string PTY_BEGIN_DATE = "begin_date";

      public const string PTY_END_VALID = "end_valid";

      public const string PTY_END_DATE = "end_date";

      public const string PTY_TIME = "time";

      private static IResource _resource = RResource.Find(typeof(FScheduleTask));

      internal FScheduleTasks _tasks;

      protected EScheduleTaskType _type = EScheduleTaskType.Daily;

      protected bool _valid = true;

      protected int _dayInterval = 0;

      protected int _weekInterval = 0;

      protected EScheduleWeekDay _weekDays = EScheduleWeekDay.None;

      protected EScheduleMonthType _monthType = EScheduleMonthType.Date;

      protected int _monthInterval = 0;

      protected int _monthDay = 1;

      protected EScheduleMonthWeek _monthWeek = EScheduleMonthWeek.Week1;

      protected int _monthWeekDay = 1;

      protected EScheduleMonth _months = EScheduleMonth.None;

      protected DateTime _onceDate = DateTime.Today;

      protected DateTime _beginDate = DateTime.Today;

      protected bool _endValid = false;

      protected DateTime _endDate = DateTime.Today;

      protected DateTime _time = DateTime.Now;

      private object _tag;

      public FScheduleTask() {
      }

      public EScheduleTaskType Type {
         get { return _type; }
         set { _type = value; }
      }

      public string TypeName {
         get { return REnum.ToString<EScheduleTaskType>(_type); }
      }

      public bool Valid {
         get { return _valid; }
         set { _valid = value; }
      }

      public int DayInterval {
         get { return _dayInterval; }
         set { _dayInterval = value; }
      }

      public int WeekInterval {
         get { return _weekInterval; }
         set { _weekInterval = value; }
      }

      public EScheduleWeekDay WeekDays {
         get { return _weekDays; }
         set { _weekDays = value; }
      }

      public EScheduleMonthType MonthType {
         get { return _monthType; }
         set { _monthType = value; }
      }

      public int MonthInterval {
         get { return _monthInterval; }
         set { _monthInterval = value; }
      }

      public int MonthDay {
         get { return _monthDay; }
         set { _monthDay = value; }
      }

      public EScheduleMonthWeek MonthWeek {
         get { return _monthWeek; }
         set { _monthWeek = value; }
      }

      public int MonthWeekDay {
         get { return _monthWeekDay; }
         set { _monthWeekDay = value; }
      }

      public EScheduleMonth Months {
         get { return _months; }
         set { _months = value; }
      }

      public DateTime OnceDate {
         get { return _onceDate; }
         set { _onceDate = value; }
      }

      public DateTime BeginDate {
         get { return _beginDate; }
         set { _beginDate = value; }
      }

      public bool EndValid {
         get { return _endValid; }
         set { _endValid = value; }
      }

      public DateTime EndDate {
         get { return _endDate; }
         set { _endDate = value; }
      }

      public DateTime Time {
         get { return _time; }
         set { _time = value; }
      }

      public object Tag {
         get { return _tag; }
         set { _tag = value; }
      }

      public virtual string GetNameInfo() {
         return "Not support for GetNameInfo";
      }

      public virtual string GetDetailInfo() {
         return "Not support for GetDetailInfo";
      }

      public void Assign(FScheduleTask task) {
         _tasks = task._tasks;
         _type = task._type;
         _valid = task._valid;
         _dayInterval = task._dayInterval;
         _weekInterval = task._weekInterval;
         _weekDays = task._weekDays;
         _monthType = task._monthType;
         _monthInterval = task._monthInterval;
         _monthDay = task._monthDay;
         _monthWeek = task._monthWeek;
         _monthWeekDay = task._monthWeekDay;
         _months = task._months;
         _onceDate = task._onceDate;
         _beginDate = task._beginDate;
         _endValid = task._endValid;
         _endDate = task._endDate;
         _time = task._time;
         _tag = task._tag;
      }

      public virtual void LoadConfig(FXmlNode config) {
         // Type
         if(config.Contains(PTY_TYPE)) {
            _type = REnum.ToValue<EScheduleTaskType>(config[PTY_TYPE]);
         }
         // Valid
         if(config.Contains(PTY_VALID)) {
            _valid = config.GetBoolean(PTY_VALID);
         }
         // Day Interval
         if(config.Contains(PTY_DAY_INTERVAL)) {
            _dayInterval = RInt.Parse(config[PTY_DAY_INTERVAL]);
         }
         // Week Interval
         if(config.Contains(PTY_WEEK_INTERVAL)) {
            _weekInterval = RInt.Parse(config[PTY_WEEK_INTERVAL]);
         }
         // Month Interval
         if(config.Contains(PTY_MONTH_INTERVAL)) {
            _monthInterval = RInt.Parse(config[PTY_MONTH_INTERVAL]);
         }
         // WeekDays
         if(config.Contains(PTY_WEEKDAYS)) {
            string weekDays = config[PTY_WEEKDAYS];
            if(!RString.IsEmpty(weekDays)) {
               _weekDays = (EScheduleWeekDay)RInt.Parse(weekDays);
            }
         }
         // Month type
         if(config.Contains(PTY_MONTH_TYPE)) {
            string monthType = config[PTY_MONTH_TYPE];
            if(!RString.IsEmpty(monthType)) {
               _monthType = (EScheduleMonthType)RInt.Parse(monthType);
            }
         }
         // Month day
         if(config.Contains(PTY_MONTH_DAY)) {
            _monthDay = RInt.Parse(config[PTY_MONTH_DAY]);
         }
         // Month week
         if(config.Contains(PTY_MONTH_WEEK)) {
            string monthWeek = config[PTY_MONTH_WEEK];
            if(!RString.IsEmpty(monthWeek)) {
               _monthWeek = (EScheduleMonthWeek)RInt.Parse(monthWeek);
            }
         }
         // Month week day
         if(config.Contains(PTY_MONTH_WEEK_DAY)) {
            _monthWeekDay = RInt.Parse(config[PTY_MONTH_WEEK_DAY]);
         }
         // Months
         if(config.Contains(PTY_MONTHS)) {
            string months = config[PTY_MONTHS];
            if(!RString.IsEmpty(months)) {
               _months = (EScheduleMonth)RInt.Parse(months);
            }
         }
         // Once date
         if(config.Contains(PTY_ONCE_DATE)) {
            _onceDate = RDate.Parse(config[PTY_ONCE_DATE]);
         }
         // Begin date
         if(config.Contains(PTY_BEGIN_DATE)) {
            _beginDate = RDate.Parse(config[PTY_BEGIN_DATE]);
         }
         // End valid
         if(config.Contains(PTY_END_VALID)) {
            _endValid = config.GetBoolean(PTY_END_VALID);
         }
         // End date
         if(config.Contains(PTY_END_DATE)) {
            _endDate = RDate.Parse(config[PTY_END_DATE]);
         }
         // Time
         if(config.Contains(PTY_TIME)) {
            _time = RDate.Parse(config[PTY_TIME]);
         }
      }

      public virtual void SaveConfig(FXmlNode config) {
         // Type
         config[PTY_TYPE] = _type.ToString();
         config.Set(PTY_VALID, _valid);
         // Day config
         config.Set(PTY_DAY_INTERVAL, _dayInterval);
         // Week config
         config.Set(PTY_WEEK_INTERVAL, _weekInterval);
         config[PTY_WEEKDAYS] = ((int)_weekDays).ToString();
         // Month config
         config[PTY_MONTH_TYPE] = ((int)_monthType).ToString();
         config.Set(PTY_MONTH_INTERVAL, _monthInterval);
         config.Set(PTY_MONTH_DAY, _monthDay);
         config[PTY_MONTH_WEEK] = ((int)_monthWeek).ToString();
         config.Set(PTY_MONTH_WEEK_DAY, _monthWeekDay);
         config[PTY_MONTHS] = ((int)_months).ToString();
         // Once config
         config[PTY_ONCE_DATE] = RDate.Format(_onceDate, FMT_DATE);
         // Public config
         config[PTY_BEGIN_DATE] = RDate.Format(_beginDate, FMT_DATE);
         config.Set(PTY_END_VALID, _endValid);
         config[PTY_END_DATE] = RDate.Format(_endDate, FMT_DATE);
         config[PTY_TIME] = RDate.Format(_time, FMT_TIME);
      }

      public virtual DateTime QueryNextRunTime(DateTime time) {
         throw new FFatalException("Not support for QueryNextRunTime");
      }

      public FScheduleTask ConvertToType() {
         return _tasks.ConvertToType(this, _type);
      }

      public FScheduleTask ConvertToType(EScheduleTaskType type) {
         return _tasks.ConvertToType(this, type);
      }

   }

}
