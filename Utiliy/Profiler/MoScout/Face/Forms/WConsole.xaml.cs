using System;
using System.Windows;
using System.Windows.Interop;

namespace MoScout.Console
{
   /// <summary>
   /// WScoutConsole.xaml 的交互逻辑
   /// </summary>
   public partial class WConsole : Window
   {
      private const int WM_NCHITTEST = 0x0084;

      public WConsole() {
         InitializeComponent();
      }

      private void MenuItem_Click(object sender, RoutedEventArgs e) {
      }

      private void Window_Loaded(object sender, RoutedEventArgs e) {
         // 添加一个消息过滤器
         IntPtr hwnd = new WindowInteropHelper(this).Handle;
         HwndSource.FromHwnd(hwnd).AddHook(new HwndSourceHook(WndProc));
      }

      private IntPtr WndProc(IntPtr hwnd, int msg, IntPtr wParam, IntPtr lParam, ref bool handled) {
         if(msg == WM_NCHITTEST) {
            // 获取屏幕坐标
            Point p = new Point();
            int pInt = lParam.ToInt32();
            p.X = (pInt << 16) >> 16;
            p.Y = pInt >> 16;
            if(isOnTitleBar(PointFromScreen(p))) {
               // 欺骗系统鼠标在标题栏上
               handled = true;
               return new IntPtr(2);
            }
         }
         return IntPtr.Zero;
      }

      private bool isOnTitleBar(Point p) {
         if(p.Y >= 0 && p.Y < 24) {
            return true;
         }
         return false;
      }

      private void TextBox_DragOver(object sender, DragEventArgs e) {

      }
   }
}
