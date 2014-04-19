using System;
using System.Collections.Generic;
using System.Text;

namespace MObj.Windows.Schedule {

   public interface ISchedule {

      IScheduleConsole Console { get;}

      FScheduleTasks Tasks { get;}

      FScheduleConditions Conditions { get;}

      FScheduleEvents Events { get;}

      bool CheckCondition();

      void Process();

   }

}
