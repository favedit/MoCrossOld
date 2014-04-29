using System.Windows;
using MO.SceneEditor.Face.Forms;

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
         WSceneWindow sceneWindow = new WSceneWindow();
         sceneWindow.Show();
      }
   }
}
