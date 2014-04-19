using System.Threading;
using MO.Common.Collection;

namespace MO.Common.System
{
   //============================================================
   // <T>线程基类。</T>
   //============================================================
   public abstract class MThread
   {
      // 线程对象
      protected Thread _thread;

      // 线程是否激活
      protected bool _active;

      // 线程是否停止
      protected bool _stop;

      // 线程参数
      protected FObjectDictionary _parameters;

      // 处理函数回调
      public event HThreadProcess EventProcess;

      // 释放函数回调
      public event HThreadProcess EventRelease;

      //============================================================
      // <T>构造线程基类。</T>
      //============================================================
      public MThread() {
      }

      //============================================================
      // <T>构造线程基类。</T>
      //
      // @param process 处理函数
      //============================================================
      public MThread(HThreadProcess process) {
         if(null != process) {
            EventProcess += process;
         }
      }

      //============================================================
      // <T>获得线程对象。</T>
      //
      // @return 线程对象
      //============================================================
      public Thread Thread {
         get { return _thread; }
      }

      //============================================================
      // <T>获得线程是否激活。</T>
      //
      // @return 是否激活
      //============================================================
      public bool IsActive {
         get { return _active; }
      }

      //============================================================
      // <T>获得线程是否停止。</T>
      //
      // @return 停止
      //============================================================
      public bool IsStop {
         get { return _stop; }
      }

      //============================================================
      // <T>获得参数集合。</T>
      //
      // @return 参数集合
      //============================================================
      public FObjectDictionary Parameters {
         get {
            if(null == _parameters) {
               _parameters = new FObjectDictionary();
            }
            return _parameters;
         }
      }

      //============================================================
      // <T>启动处理。</T>
      //
      // @return 处理结果
      //============================================================
      public virtual bool OnInitialize() {
         return true;
      }

      //============================================================
      // <T>执行处理。</T>
      //============================================================
      public virtual void OnProcess() {
      }

      //============================================================
      // <T>释放处理。</T>
      //============================================================
      public virtual void OnRelease() {
      }

      //============================================================
      // <T>启动线程。</T>
      //============================================================
      public void Start() {
         // 创建线程对象
         if(null == _thread) {
            _thread = new Thread(Process);
         }
         // 启动线程
         _stop = false;
         _thread.Start();
      }

      //============================================================
      // <T>停止线程。</T>
      //============================================================
      public void Sleep(int time) {
         Thread.Sleep(time);
      }

      //============================================================
      // <T>执行处理。</T>
      //============================================================
      public virtual void Process() {
         _active = true;
         // 初始化处理
         if(OnInitialize()) {
            // 线程处理
            OnProcess();
            // 线程回调处理
            if(null != EventProcess) {
               EventProcess(this);
            }
            // 释放处理
            OnRelease();
            // 释放回调处理
            if(null != EventRelease) {
               EventRelease(this);
            }
         }
         _active = false;
         _stop = false;
      }

      //============================================================
      // <T>停止线程。</T>
      //============================================================
      public void Stop() {
         if(null != _thread) {
            _stop = true;
         }
      }
   }
}
