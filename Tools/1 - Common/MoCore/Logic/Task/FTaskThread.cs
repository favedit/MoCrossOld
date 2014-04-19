using MO.Common.System;

namespace MO.Core.Logic.Task
{
   //============================================================
   // <T>任务线程。</T>
   //============================================================
   public class FTaskThread : FThread
   {
      // 任务控制台
      protected FTaskConsole _console;

      // 休眠间隔
      protected int interval = 10;

      //============================================================
      // <T>构造任务线程。</T>
      //============================================================
      public FTaskThread() {
      }

      //============================================================
      // <T>设置或获得任务控制台。</T>
      //============================================================
      public FTaskConsole Console {
         get { return _console; }
         set { _console = value; }
      }

      //============================================================
      // <T>任务处理。</T>
      //============================================================
      public override void OnProcess() {
         while (!_stop) {
            // 获得执行任务
            ITask task = _console.Shift();
            // 执行任务
            if (null != task) {
               task.OnBegin();
               task.OnProcess();
               task.OnEnd();
            }
            // 休眠
            Sleep(interval);
         }
      }
   }
}
