using MO.Common.Lang;
using MO.Core.Aop;

namespace MO.Core.Monitor
{
   public class FMonitorConsole : FAbsMonitorConsole
   {
      private static ILogger _logger = RLogger.Find(typeof(FMonitorConsole));

      [ALinker]
      protected IMonitorConsole _monitor;

      [ALinker]
      protected IMonitorConsole _monitor2;

      [AProperty]
      protected string _work;

      [AProperty]
      protected string _work1;

      protected string _work4;

      //============================================================
      public FMonitorConsole() {
      }

      //============================================================
      public FMonitorConsole(string work) {
         _work4 = work;
      }

      //============================================================
      public void sessionStart1() {
         _logger.Debug(this, "sessionStart1", "Session start 1");
      }

      //============================================================
      public void sessionStart2() {
         _logger.Debug(this, "sessionStart2", "Session start 2");
      }

      //============================================================
      public override void AddMonitor(object test) {
         _logger.Debug(this, "AddMonitor", "test={0}", test);
      }

      //============================================================
      public void sessionStart2(string hello) {
         _logger.Debug(this, "sessionStart2", "Session start 2={0}", hello);
      }
   }
}
