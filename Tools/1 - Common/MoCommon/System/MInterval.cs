using System.Threading;

namespace MO.Common.System
{
   //============================================================
   // <T>间隔器基类。</T>
   //============================================================
   public class MInterval : MThread
   {
      // 默认间隔
      public const int DEFAULT_INTERVAL = 100;

      // 间隔时间
      protected int _interval = DEFAULT_INTERVAL;

      //============================================================
      // <T>构造间隔器基类。</T>
      //============================================================
      public MInterval() {
      }

      //============================================================
      // <T>构造间隔器基类。</T>
      //
      // @param process 处理函数
      //============================================================
      public MInterval(HThreadProcess process)
         : base(process) {
      }

      //============================================================
      // <T>获得或设置间隔。</T>
      //
      // @param value 间隔
      // @return 间隔
      //============================================================
      public int Interval {
         get { return _interval; }
         set { _interval = value; }
      }

      //============================================================
      // <T>执行处理。</T>
      //============================================================
      public override void Process() {
         while(RSystem.InRuning && !_stop) {
            // 执行处理
            OnProcess();
            // 睡眠处理
            Thread.Sleep(_interval);
         }
      }
   }
}
