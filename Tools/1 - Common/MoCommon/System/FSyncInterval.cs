using System.Threading;

namespace MO.Common.System
{
   //============================================================
   // <T>同步化间隔器。</T>
   //============================================================
   public class FSyncInterval : MInterval
   {
      // 同步环境
      protected SynchronizationContext _syncContext;

      //============================================================
      // <T>构造同步化间隔器。</T>
      //============================================================
      public FSyncInterval()
         : this(SynchronizationContext.Current, null) {
      }

      //============================================================
      // <T>构造同步化间隔器。</T>
      //
      // @param process 回调处理
      //============================================================
      public FSyncInterval(HThreadProcess process)
         : this(SynchronizationContext.Current, process) {
      }

      //============================================================
      // <T>构造同步化间隔器。</T>
      //
      // @param context 同步环境
      // @param process 回调处理
      //============================================================
      public FSyncInterval(SynchronizationContext context, HThreadProcess process)
         : base(process) {
         _syncContext = context;
      }

      //============================================================
      // <T>获得或设置同步环境。</T>
      //
      // @param value 同步环境
      // @return 同步环境
      //============================================================
      public SynchronizationContext SyncContext {
         get { return _syncContext; }
         set { _syncContext = value; }
      }

      //============================================================
      // <T>同步处理。</T>
      //
      // @param status 状态对象
      //============================================================
      public virtual void SyncProcess(object status) {
         OnProcess();
      }

      //============================================================
      // <T>执行处理。</T>
      //============================================================
      public override void Process() {
         while(RSystem.InRuning && _active) {
            if(null != _syncContext) {
               // Sync process
               _syncContext.Post(SyncProcess, this);
            } else {
               // Interval process
               OnProcess();
            }
            // Sleep
            Thread.Sleep(_interval);
         }
         OnRelease();
      }
   }
}
