using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;
using MO.Common.Content;

namespace MObj.Windows.Schedule {

   public class FSchedules : FObjects<FSchedule> {

      public const string NAME = "Schedules";

      internal IScheduleConsole _console;

      public FSchedules() {
      }

      public FSchedules(IScheduleConsole console) {
         _console = console;
      }

      public void LoadConfig(FXmlNode config) {
         if (config.HasNode()) {
            foreach (FXmlNode node in config.Nodes) {
               if (node.IsName(FSchedule.NAME)) {
                  // Load schedule
                  FSchedule schedule = new FSchedule();
                  schedule._console = _console;
                  schedule.LoadConfig(node);
                  Push(schedule);
               }
            }
         }
      }

      public void SaveConfig(FXmlNode config) {
         // Save schedules
         if(!IsEmpty()) {
            foreach (FSchedule schedule in this) {
               schedule.SaveConfig(config.CreateNode(FSchedule.NAME));
            }
         }
      }
   }

}
