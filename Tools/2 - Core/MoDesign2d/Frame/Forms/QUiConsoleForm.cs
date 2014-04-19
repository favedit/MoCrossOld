using MO.Content2d;
using System.Windows.Forms;

namespace MO.Design2d.Frame.Forms
{
   //============================================================
   // <T>界面控制台表单。</T>
   //============================================================
   public partial class QUiConsoleForm : Form
   {
      // 界面控制台表单
      protected static QUiConsoleForm _instance;

      //============================================================
      // <T>获得界面控制台表单。</T>
      //============================================================
      public static QUiConsoleForm Instance {
         get {
            if(null == _instance) {
               _instance = new QUiConsoleForm();
            }
            return _instance;
         }
      }

      //============================================================
      // <T>构造界面控制台表单。</T>
      //============================================================
      public QUiConsoleForm() {
         InitializeComponent();
      }

      //============================================================
      // <T>打开资源信息。</T>
      //============================================================
      public void Open() {
         qdsDesign.Open();
      }

      //============================================================
      // <T>全部导出处理。</T>
      //============================================================
      private void tsbExportAll_Click(object sender, System.EventArgs e) {
         Enabled = false;
         RContent2dManager.FrameConsole.ExportAll();
         Enabled = true;
         MessageBox.Show("导出界面成功。");
      }

      //============================================================
      // <T>关闭处理。</T>
      //============================================================
      private void tsbExit_Click(object sender, System.EventArgs e) {
         Close();
      }
   }
}
