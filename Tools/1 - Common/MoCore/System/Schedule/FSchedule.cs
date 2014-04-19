using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Content;
using MO.Common.Lang;

namespace MObj.Windows.Schedule {

   public class FSchedule : ISchedule {

      public const string NAME = "Schedule";

      public const string PTY_VALID = "valid";

      public const string PTY_ID = "id";

      public const string PTY_TEXT = "text";

      private static ILogger _logger = RLogger.Find(typeof(FSchedule));

      internal IScheduleConsole _console;

      protected bool _valid = true;

      protected bool _inUsing = false;

      private string _id;

      private string _text;

      private FScheduleTasks _tasks = new FScheduleTasks();

      private FScheduleConditions _conditions = new FScheduleConditions();

      private FScheduleEvents _events = new FScheduleEvents();

      private object _tag;

      public FSchedule() {
         _tasks._schedule = this;
         _conditions._schedule = this;
         _events._schedule = this;
      }

      public IScheduleConsole Console {
         get { return _console; }
      }

      public bool Valid {
         get { return _valid; }
         set { _valid = value; }
      }

      public string Id {
         get { return _id; }
         set { _id = value; }
      }

      public string Text {
         get { return _text; }
         set { _text = value; }
      }

      public FScheduleTasks Tasks {
         get { return _tasks; }
      }

      public FScheduleConditions Conditions {
         get { return _conditions; }
      }

      public FScheduleEvents Events {
         get { return _events; }
      }

      public object Tag {
         get { return _tag; }
         set { _tag = value; }
      }

      public bool InUsing {
         get { return _inUsing; }
         set { _inUsing = value; }
      }

      public bool TestExecute(DateTime datetime) {
         return false;
      }

      public void Execute() {
         _inUsing = true;
      }

      public void LoadConfig(FXmlNode config) {
         if (config.Contains(PTY_VALID)) {
            _valid = config.GetBoolean(PTY_VALID);
         }
         _id = config[PTY_ID];
         _text = config[PTY_TEXT];
         // Load children
         if (config.HasNode()) {
            foreach (FXmlNode node in config.Nodes) {
               if (node.IsName(FScheduleTasks.NAME)) {
                  // Load task
                  _tasks.LoadConfig(node);
               } else if (node.IsName(FScheduleConditions.NAME)) {
                  // Load condition
                  _conditions.LoadConfig(node);
               } else if (node.IsName(FScheduleEvents.NAME)) {
                  // Load event
                  _events.LoadConfig(node);
               }
            }
         }
      }

      public void SaveConfig(FXmlNode config) {
         config.Set(PTY_VALID, _valid);
         config[PTY_ID] = _id;
         config[PTY_TEXT] = _text;
         // Save tasks
         _tasks.SaveConfig(config.CreateNode(FScheduleTasks.NAME));
         // Save events
         _events.SaveConfig(config.CreateNode(FScheduleEvents.NAME));
         // Save conditions
         _conditions.SaveConfig(config.CreateNode(FScheduleConditions.NAME));
      }

      public DateTime QueryNextRunTime(DateTime time) {
         DateTime result = DateTime.MaxValue;
         foreach (FScheduleTask task in _tasks) {
            DateTime next = task.QueryNextRunTime(time);
            result = (next < result) ? next : result;
         }
         return result;
      }

      public bool CheckCondition() {
         foreach (FScheduleCondition condition in _conditions) {
            if (condition.Valid) {
               if (!condition.CheckCondition()) {
                  return false;
               }
            }
         }
         return true;
      }

      public void Process() {
         if (CheckCondition()) {
            _logger.Debug(this, "Process", "Process schedule - start (date=[{0}] text=[{1}])", RDate.Format(DateTime.Now, "yymmdd hh24miss"), _text);
            _events.Process();
            _logger.Debug(this, "Process", "Process schedule - end");
         }
      }

   }

}
