using System;
using System.Collections.Generic;
using System.Text;
using System.Threading;
using MO.Common.Lang;
using MO.Common.Content;

namespace MObj.Windows.Schedule {

   public class FScheduleThread {

      private static ILogger _logger = RLogger.Find(typeof(FScheduleThread));

      private FScheduleJob[] _scheduleJobs;

      private Thread _thread;

      public FScheduleThread(FScheduleJob[] scheduleJobs) {
         _scheduleJobs = scheduleJobs;
      }

      public void Process() {
         if (_logger.DebugAble) {
            _logger.Debug(this, "Process", "Start schedule jobs (count={0})" + _scheduleJobs.Length);
         }
         _thread = new Thread(OnProcess);
         _thread.Start();
      }

      public void OnProcess() {
         if (_logger.DebugAble) {
            _logger.Debug(this, "OnProcess", "Process schedule jobs - start");
            foreach (FScheduleJob scheduleJob in _scheduleJobs) {
               try {
                  scheduleJob.Process();
               } catch (Exception e) {
                  _logger.Error(this, "OnProcess", e);
               }
            }
            _logger.Debug(this, "OnProcess", "Process schedule jobs - end");
         }
      }

   }

}
