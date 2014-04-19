using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Content;
using MO.Common.Lang;
using System.Diagnostics;

namespace MObj.Windows.Schedule {

   public class FScheduleEventExecute : FScheduleEvent {

      public const string RES_DETAIL_FILE = "detail.file";

      private static ILogger _logger = RLogger.Find(typeof(FScheduleEventExecute));

      private static IResource _resource = RResource.Find(typeof(FScheduleEventExecute));

      public FScheduleEventExecute() {
         _type = EScheduleEventType.Execute;
      }

      public override string GetNameInfo() {
         return _resource.FindDisplay(RES_NAME);
      }

      public override string GetDetailInfo() {
         if (RString.IsEmpty(_fileName)) {
            return null;
         }
         return _resource.FindDisplay(RES_DETAIL_FILE, _fileName);
      }

      public override void Process() {
         _logger.Debug(this, "Process", "Execute (file={0})", _fileName);
         ProcessStartInfo startInfo = new ProcessStartInfo(_fileName);
         startInfo.UseShellExecute = false;
         startInfo.CreateNoWindow = true;
         startInfo.WindowStyle = ProcessWindowStyle.Hidden;
         startInfo.RedirectStandardOutput = false;
         startInfo.RedirectStandardError = false;
         Process process = new Process();
         process.StartInfo = startInfo;
         process.Start();
         process.WaitForExit();
         if (process.ExitCode != 0) {
            _logger.Debug(this, "Process", "Execute failure (code={0})", process.ExitCode);
         } else {
            _logger.Debug(this, "Process", "Execute success");
         }
      }

   }

}
