using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Content;
using MO.Common.Lang;

namespace MObj.Windows.Schedule {

   public class FScheduleFactory : IScheduleFactory {

      private static IResource _resource = RResource.Find(typeof(FScheduleFactory));

      public const string PTY_TYPE = "type";

      public const string PTY_CLASS = "class";

      public FScheduleFactory() {
      }

      protected T CreateInstance<T>(string name, string type) {
         /*foreach (FXmlNode node in _resource.Config.Nodes) {
            if (node.IsName(name)) {
               if (node[PTY_TYPE] == type) {
                  return RClass.CreateInstance<T>(node[PTY_CLASS]);
               }
            }
         }*/
         throw new FFatalException("Unknown type name={0} type={1}", name, type);
      }

      public FScheduleTask CreateTask(EScheduleTaskType taskType) {
         return CreateInstance<FScheduleTask>(FScheduleTask.NAME, taskType.ToString());
      }

      public FScheduleTask CreateTask(FXmlNode config) {
         return CreateInstance<FScheduleTask>(FScheduleTask.NAME, config[PTY_TYPE]);
      }

      public FScheduleEvent CreateEvent(EScheduleEventType eventType) {
         return CreateInstance<FScheduleEvent>(FScheduleEvent.NAME, eventType.ToString());
      }

      public FScheduleEvent CreateEvent(FXmlNode config) {
         return CreateInstance<FScheduleEvent>(FScheduleEvent.NAME, config[PTY_TYPE]);
      }

      public FScheduleCondition CreateCondition(EScheduleConditionType conditionType) {
         return CreateInstance<FScheduleCondition>(FScheduleCondition.NAME, conditionType.ToString());
      }

      public FScheduleCondition CreateCondition(FXmlNode config) {
         return CreateInstance<FScheduleCondition>(FScheduleCondition.NAME, config[PTY_TYPE]);
      }

   }

}
