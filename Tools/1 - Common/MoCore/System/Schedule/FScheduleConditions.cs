using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;
using MO.Common.Content;

namespace MObj.Windows.Schedule {

   public class FScheduleConditions : FObjects<FScheduleCondition> {

      public const string NAME = "Conditions";

      internal ISchedule _schedule;

      public override void Push(FScheduleCondition condition) {
         condition._conditions = this;
         base.Push(condition);
      }

      public FScheduleCondition ConvertToType(FScheduleCondition condition, EScheduleConditionType type) {
         FScheduleCondition resultCondition = _schedule.Console.Factory.CreateCondition(type);
         resultCondition.Assign(condition);
         Replace(condition, resultCondition);
         return resultCondition;
      }
      
      public void LoadConfig(FXmlNode config) {
         // Load children
         if (config.HasNode()) {
            foreach (FXmlNode node in config.Nodes) {
               if (node.IsName(FScheduleCondition.NAME)) {
                  // Load condition
                  FScheduleCondition condition = _schedule.Console.Factory.CreateCondition(node);
                  condition.LoadConfig(node);
                  Push(condition);
               }
            }
         }
      }

      public void SaveConfig(FXmlNode config) {
         // Save conditions
         if(!IsEmpty()) {
            foreach (FScheduleCondition condition in this) {
               condition.SaveConfig(config.CreateNode(FScheduleCondition.NAME));
            }
         }
      }

   }

}
