using System.Threading;

namespace MO.Common.System
{
   //============================================================
   // <T>同步化线程。</T>
   //============================================================
   public class FSyncThread : MThread
   {
      // 同步环境
      protected SynchronizationContext _syncContext;

      //============================================================
      // <T>构造同步化线程。</T>
      //============================================================
      public FSyncThread()
         : this(SynchronizationContext.Current, null) {
      }

      //============================================================
      // <T>构造同步化线程。</T>
      //
      // @param process 回调处理
      //============================================================
      public FSyncThread(HThreadProcess process)
         : this(SynchronizationContext.Current, process) {
      }

      //============================================================
      // <T>构造同步化线程。</T>
      //
      // @param context 同步环境
      // @param process 回调处理
      //============================================================
      public FSyncThread(SynchronizationContext context, HThreadProcess process)
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
         base.Process();
      }

      //============================================================
      // <T>执行处理。</T>
      //============================================================
      public override void Process() {
         if(null != _syncContext) {
            _syncContext.Post(SyncProcess, this);
         } else {
            base.Process();
         }
      }
   }
}
