using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;

namespace MO.Common.System {

   public class FSpeedTest : IDump {

      private TimeSpan _total = new TimeSpan();

      private DateTime _first = DateTime.Now;

      private DateTime _start;

      private DateTime _end;

      private DateTime _last;

      public FSpeedTest() {
         _start = DateTime.Now;
      }

      public TimeSpan Total {
         get { return _total; }
      }

      public DateTime First {
         get { return _first; }
      }

      public DateTime Start {
         get { return _start; }
      }

      public DateTime End {
         get { return _end; }
      }

      public DateTime Last {
         get { return _last; }
      }

      public void DoStart() {
         _start = DateTime.Now;
      }

      public void DoEnd() {
         _end = DateTime.Now;
         _last = DateTime.Now;
         _total += _end.Subtract(_start);
      }

      public TimeSpan GetDiff() {
         return _end.Subtract(_start);
      }

      public string GetDiffSecond() {
         return FormatSpan(GetDiff());
      }

      public string GetTotalSecond() {
         return FormatSpan(_total);
      }

      public string GetTotalNowSecond() {
         return FormatSpan(DateTime.Now.Subtract(_first));
      }

      public string GetNowSecond() {
         return FormatSpan(DateTime.Now.Subtract(_start));
      }

      public string FormatSpan(TimeSpan ts) {
         string totalSeconds = ts.TotalSeconds.ToString();
         int find = totalSeconds.IndexOf('.');
         if (find != -1) {
            string num = totalSeconds.Substring(0, find);
            string aft = totalSeconds.Substring(find + 1);
            if (aft.Length > 3) {
               aft = aft.Substring(0, 3);
            }
            return num + "." + aft.PadRight(3, '0') + "s";
         }
         return totalSeconds + ".000s";
      }

      #region IDump members

      public FDumpInfo Dump() {
         return Dump(new FDumpInfo(this));
      }

      public virtual FDumpInfo Dump(FDumpInfo info) {
         RDump.StartDump(info);
         info.Append("[", _start.ToString("yyMMdd-hhmmss"));
         info.Append(" - ", _end.ToString("yyMMdd-hhmmss"));
         info.Append("] = ", GetDiff().ToString(), "s");
         return info;
      }

      #endregion
   }

}
