using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.System;

namespace MO.Core.Logic.Task
{
   //============================================================
   // <T>任务控制台。</T>
   //============================================================
   public class FTaskConsole : FConsole, ITaskConsole
   {
      // 处理线程数目
      protected int _threadCount = 1;

      // 任务处理线程
      protected FVector<FTaskThread> _threads = new FVector<FTaskThread>();

      // 任务列表
      protected FList<ITask> _tasks = new FList<ITask>();

      //============================================================
      // <T>构造任务控制台。</T>
      //============================================================
      public FTaskConsole() {
         _name = "Core.Task.Console";
      }

      //============================================================
      // <T>获得或设置任务个数集合。</T>
      //============================================================
      public int ThreadCount {
         get { return _threadCount; }
         set { _threadCount = value; }
      }

      //============================================================
      // <T>获得任务集合。</T>
      //============================================================
      public FList<ITask> Tasks {
         get { return _tasks; }
      }

      //============================================================
      // <T>获得任务集合。</T>
      //
      // @return 任务集合
      //============================================================
      public FVector<ITask> FetchTasks(int count) {
         FVector<ITask> tasks = null;
         lock (_tasks) {
            tasks = new FVector<ITask>(count);
            int n = 0;
            foreach (ITask task in _tasks) {
               n++;
               tasks.Push(task);
               if (n > count) {
                  break;
               }
            }
         }
         return tasks;
      }

      //============================================================
      // <T>获得线程集合。</T>
      //============================================================
      public FVector<FTaskThread> Threads {
         get { return _threads; }
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置节点
      //============================================================
      public override void LoadConfig(FXmlNode xconfig) {
         foreach (FXmlNode xnode in xconfig.Nodes) {
            if (xnode.IsName("Property")) {
               string name = xnode.Get("name");
               if (name == "Thread.Count") {
                  _threadCount = xnode.GetInteger("value");
               }
            }
         }
      }

      //============================================================
      // <T>追加任务。<T>      
      //============================================================
      public void Push(ITask task) {
         lock (_tasks) {
            _tasks.Push(task);
         }
      }

      //============================================================
      // <T>弹出空闲任务。<T>      
      //============================================================
      public ITask Shift() {
         ITask task = null;
         lock (_tasks) {
            if (!_tasks.IsEmpty) {
               task = _tasks.Shift();
            }
         }
         return task;
      }

      //============================================================
      // <T>启动任务控制台。<T>
      //============================================================
      public void Start() {
         // 创建所有线程
         for (int n = 0; n < _threadCount; n++) {
            FTaskThread thread = new FTaskThread();
            thread.Console = this;
            _threads.Push(thread);
         }
         // 启动所有线程
         for (int n = 0; n < _threadCount; n++) {
            _threads[n].Start();
         }
      }

      //============================================================
      // <T>关闭任务控制台。<T>
      //============================================================
      public void Stop() {
         foreach (FTaskThread thread in _threads) {
            thread.Stop();
         }
      }

      //============================================================
      // <T>关闭任务控制台。<T>
      //============================================================
      public void Show() {
         QTaskForm.Instance.Show();
         QTaskForm.Instance.BringToFront();
         QTaskForm.Instance.RefreshTasks();
      }
   }
}
