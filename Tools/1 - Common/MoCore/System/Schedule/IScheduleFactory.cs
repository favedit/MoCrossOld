using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Content;

namespace MObj.Windows.Schedule {

   public interface IScheduleFactory {

      FScheduleTask CreateTask(EScheduleTaskType taskType);

      FScheduleTask CreateTask(FXmlNode config);

      FScheduleEvent CreateEvent(EScheduleEventType eventType);

      FScheduleEvent CreateEvent(FXmlNode config);

      FScheduleCondition CreateCondition(EScheduleConditionType conditionType);

      FScheduleCondition CreateCondition(FXmlNode config);

   }

}
