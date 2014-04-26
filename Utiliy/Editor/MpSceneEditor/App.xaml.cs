using MO.SceneEditor.Face.Forms;
using System.Windows;

namespace MpSceneEditor
{
   /// <summary>
   /// App.xaml 的交互逻辑
   /// </summary>
   public partial class App : Application
   {
      protected override void OnStartup(StartupEventArgs e) {
         base.OnStartup(e);
         // 启动窗口
         WConsole console = new WConsole();
         console.Show();
      }
   }
}
