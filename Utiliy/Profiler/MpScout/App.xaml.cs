using MO.Scout;
using MO.Scout.Face.Forms;
using System;
using System.Windows;

namespace MpScout
{
   /// <summary>
   /// App.xaml 的交互逻辑
   /// </summary>
   public partial class App : Application
   {
      protected override void OnStartup(StartupEventArgs e) {
         base.OnStartup(e);

         int value = 1024;
         int n = 1;
         int v = value - 1;
         while ((v >>= 1) > 0) {
            n <<= 1;
         }

         // 启动窗口
         WConsole console = new WConsole();
         console.Show();
      }
   }
}
