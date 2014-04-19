using System;
using System.Windows.Forms;
using MO.Common.Collection;

namespace MO.Core.Logic.Task
{
   //============================================================
   // <T>获得任务表单。</T>
   //============================================================
   public partial class QTaskForm : Form
   {
      // 任务最大个数
      protected static int TaskLimitCount = 256;

      // 任务表单
      protected static QTaskForm _instance;

      //============================================================
      // <T>获得任务表单单件。</T>
      //============================================================
      public static QTaskForm Instance {
         get {
            if(_instance == null) {
               _instance = new QTaskForm();
            }
            return _instance;
         }
      }

      //============================================================
      // <T>构造任务表单。</T>
      //============================================================
      public QTaskForm() {
         InitializeComponent();
      }

      //============================================================
      // <T>刷新器。</T>
      //============================================================
      public void RefreshTasks() {
         // 获得任务列表
         int count = RMoCore.TaskConsole.Tasks.Count;
         FVector<ITask> tasks = RMoCore.TaskConsole.FetchTasks(TaskLimitCount);
         // 设置属性
         tsslTaskCount.Text = count.ToString();
         // 显示任务信息
         lvwTasks.BeginUpdate();
         lvwTasks.Items.Clear();
         foreach(ITask task in tasks) {
            ListViewItem lviTask = new ListViewItem();
            lviTask.Text = task.StartDateTime.ToString();
            lviTask.SubItems.Add(task.Label);
            lviTask.SubItems.Add(task.ToString());
            lvwTasks.Items.Add(lviTask);
         }
         lvwTasks.EndUpdate();
      }

      //============================================================
      // <T>打开操作。</T>
      //============================================================
      public void Open() {
         timRefresh.Enabled = true;
      }

      //============================================================
      // <T>关闭窗口。</T>
      //============================================================
      private void QTaskForm_FormClosing(object sender, FormClosingEventArgs e) {
         e.Cancel = true;
         Hide();
      }

      //============================================================
      // <T>刷新按键处理。</T>
      //============================================================
      private void tsbRefresh_Click(object sender, EventArgs e) {
         RefreshTasks();
      }

      //============================================================
      // <T>关闭窗口处理。</T>
      //============================================================
      private void tsbExit_Click(object sender, EventArgs e) {
         Close();
      }

      //============================================================
      // <T>时间刷新器处理。</T>
      //============================================================
      private void timRefresh_Tick(object sender, EventArgs e) {
         RefreshTasks();
      }
   }
}
