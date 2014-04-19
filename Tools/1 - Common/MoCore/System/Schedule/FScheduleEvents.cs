using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;
using MO.Common.Content;

namespace MObj.Windows.Schedule {

   public class FScheduleEvents : FObjects<FScheduleEvent> {

      public const string NAME = "Events";

      private static ILogger _logger = RLogger.Find(typeof(FScheduleEvents));

      internal ISchedule _schedule;

      public override void Push(FScheduleEvent scheduleEvent) {
         scheduleEvent._events = this;
         base.Push(scheduleEvent);
      }

      public FScheduleEvent ConvertToType(FScheduleEvent scheduleEvent, EScheduleEventType type) {
         FScheduleEvent resultEvent = _schedule.Console.Factory.CreateEvent(type);
         resultEvent.Assign(scheduleEvent);
         Replace(scheduleEvent, resultEvent);
         return resultEvent;
      }

      public void LoadConfig(FXmlNode config) {
         // Load children
         if (config.HasNode()) {
            foreach (FXmlNode node in config.Nodes) {
               if (node.IsName(FScheduleEvent.NAME)) {
                  // Load event
                  FScheduleEvent scheduleEvent = _schedule.Console.Factory.CreateEvent(node);
                  scheduleEvent.LoadConfig(node);
                  Push(scheduleEvent);
               }
            }
         }
      }

      public void SaveConfig(FXmlNode config) {
         // Save events
         if(!IsEmpty()) {
            foreach (FScheduleEvent scheduleEvent in this) {
               scheduleEvent.SaveConfig(config.CreateNode(FScheduleEvent.NAME));
            }
         }
      }

      public void Process() {
         foreach (FScheduleEvent scheduleEvent in this) {
            if (scheduleEvent.Valid) {
               try {
                  scheduleEvent.Process();
               } catch (Exception e) {
                  _logger.Error(this, "Process", e);
               }
            }
         }
      }

   }
}
