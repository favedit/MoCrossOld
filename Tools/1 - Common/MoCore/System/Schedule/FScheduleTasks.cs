using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;
using MO.Common.Content;

namespace MObj.Windows.Schedule {

   public class FScheduleTasks : FObjects<FScheduleTask> {

      public const string NAME = "Tasks";

      internal ISchedule _schedule;

      public override void Push(FScheduleTask task) {
         task._tasks = this;
         base.Push(task);
      }

      public FScheduleTask ConvertToType(FScheduleTask task, EScheduleTaskType type) {
         FScheduleTask resultTask = _schedule.Console.Factory.CreateTask(type);
         resultTask.Assign(task);
         Replace(task, resultTask);
         return resultTask;
      }

      public void LoadConfig(FXmlNode config) {
         // Load children
         if (config.HasNode()) {
            foreach (FXmlNode node in config.Nodes) {
               if (node.IsName(FScheduleTask.NAME)) {
                  // Load task
                  FScheduleTask task = _schedule.Console.Factory.CreateTask(node);
                  task._tasks = this;
                  task.LoadConfig(node);
                  Push(task);
               }
            }
         }
      }

      public void SaveConfig(FXmlNode config) {
         // Save tasks
         if(!IsEmpty()) {
            foreach (FScheduleTask task in this) {
               task.SaveConfig(config.CreateNode(FScheduleTask.NAME));
            }
         }
      }

   }

}
