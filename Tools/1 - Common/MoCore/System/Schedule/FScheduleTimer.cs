using System;
using System.Collections.Generic;
using System.Text;
using System.Timers;
using MO.Common.Lang;

namespace MObj.Windows.Schedule {

   public class FScheduleTimer {

      // Interval: 10s
      public const double DEF_INTERVAL = 10000;

      public static TimeSpan MAX_INTERVAL = new TimeSpan(0, 0, 10);

      private static ILogger _logger = RLogger.Find(typeof(FScheduleTimer));

      private Timer _timer = new Timer(DEF_INTERVAL);

      private IScheduleConsole _console;

      private DateTime _lastDateTime = DateTime.Now;

      private bool _isActive = false;

      private FSchedulePool _pool = new FSchedulePool();

      private FScheduleJobs _scheduleJobs = new FScheduleJobs();

      public FScheduleTimer() {
         Construct();
      }

      public FScheduleTimer(IScheduleConsole console) {
         _console = console;
         Construct();
      }

      public void Construct() {
         _timer.Elapsed += OnTimerElapsed;
      }

      public bool IsActive {
         get { return _isActive; }
      }

      public void Start() {
         if (_logger.DebugAble) {
            _logger.Debug(this, "Start", "Schedule timer start");
         }
         QueryNextJobs(DateTime.Now);
      }

      protected void UpdateJobs(DateTime result, DateTime date) {
         foreach (FSchedule schedule in _console.Schedules) {
            if (schedule.Valid && (result == schedule.QueryNextRunTime(date))) {
               _scheduleJobs.Push(new FScheduleJob(schedule));
            }
         }
      }

      protected void QueryNextJobs(DateTime date) {
         // Query next datetime
         DateTime result = DateTime.MaxValue;
         while (true) {
            result = DateTime.MaxValue;
            foreach (FSchedule schedule in _console.Schedules) {
               if (schedule.Valid) {
                  DateTime next = schedule.QueryNextRunTime(_lastDateTime);
                  result = (next < result) ? next : result;
               }
            }
            if (result == DateTime.MaxValue || result > date) {
               break;
            }
            if (result <= date) {
               UpdateJobs(result, _lastDateTime);
               _lastDateTime = result;
            }
         }
         if(!_scheduleJobs.IsEmpty()) {
            FScheduleThread thread = new FScheduleThread(_scheduleJobs.Flush());
            thread.Process();
         }
         // Set next timer
         TimeSpan intervalSpan = result - date;
         if (intervalSpan == TimeSpan.Zero) {
            throw new FFatalException("Invalid interval(zero)");
         }
         if (intervalSpan > MAX_INTERVAL) {
            intervalSpan = MAX_INTERVAL;
         } else {
            UpdateJobs(result, _lastDateTime);
         }
         _lastDateTime = date;
         if (_logger.DebugAble) {
            _logger.Debug(this, "QueryNextJobs", "Last=[{0}] next=[{1}] count=[{2}] interval=[{3}]", 
                  RDate.Format(_lastDateTime, "yymmdd hh24miss"),
                  RDate.Format(result, "yymmdd hh24miss"),
                  _scheduleJobs.Count, intervalSpan.ToString());
         }
         // Set next timer
         _timer.Interval = intervalSpan.TotalMilliseconds;
         if (!_isActive) {
            _timer.Start();
            _isActive = true;
         }
      }

      protected void OnTimerElapsed(object sender, ElapsedEventArgs e) {
         try {
            if (_isActive) {
               _timer.Stop();
               _isActive = false;
            }
            // Recalculate jobs
            QueryNextJobs(e.SignalTime);
         } catch (Exception exception) {
            _logger.Error(this, "OnTimerElapsed", exception);
         }
      }

      public void Stop() {
         if (_logger.DebugAble) {
            _logger.Debug(this, "Stop", "Schedule timer stop");
         }
         if (_isActive) {
            _timer.Stop();
            _isActive = false;
         }
      }

   }

}
