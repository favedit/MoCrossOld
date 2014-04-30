using System;
using System.Windows;
using System.Windows.Interop;

namespace MO.Editor.Scene.Console.Face
{
   //============================================================
   // <T>控制台窗口。</T>
   //
   // @history MAOCY 140424
   //============================================================
   public partial class WConsoleWindow : Window
   {
      // 消息定义
      private const int WM_NCHITTEST = 0x0084;

      //============================================================
      // <T>构造控制台窗口。</T>
      //============================================================
      public WConsoleWindow() {
         InitializeComponent();
      }

      //============================================================
      // <T>启动窗口。</T>
      //============================================================
      private void Window_Loaded(object sender, RoutedEventArgs e) {
         // 添加一个消息过滤器
         IntPtr hwnd = new WindowInteropHelper(this).Handle;
         HwndSource.FromHwnd(hwnd).AddHook(new HwndSourceHook(WindowHandleProcess));
      }

      //============================================================
      // <T>卸载窗口。</T>
      //============================================================
      private void Window_Unloaded(object sender, RoutedEventArgs e) {
      }

      //============================================================
      // <T>关闭处理。</T>
      //============================================================
      private void CloseButton_Click(object sender, RoutedEventArgs e) {
         Window_Unloaded(sender, e) ;
         Close();
         Application.Current.Shutdown();
      }

      //============================================================
      // <T>测试是否窗口栏。</T>
      //
      // @return 是否窗口栏
      //============================================================
      private bool isOnTitleBar(Point position) {
         // 检查位置
         Point titleStart = pnlTitle.TranslatePoint(new Point(0, 0), this);
         Point titleStop = pnlTitle.TranslatePoint(new Point(pnlTitle.ActualWidth, pnlTitle.ActualHeight), this);
         if((position.X < titleStart.X) || (position.X > titleStop.X) || (position.Y < titleStart.Y) || (position.Y > titleStop.Y)) {
            return false;
         }
         // 在图标区
         Point imageStart = imgTitle.TranslatePoint(new Point(), this);
         Point imageStop = imgTitle.TranslatePoint(new Point(imgTitle.ActualWidth, imgTitle.ActualHeight), this);
         if((position.X >= imageStart.X) && (position.X <= imageStop.X) && (position.Y >= imageStart.Y) && (position.Y <= imageStop.Y)) {
            return true;
         }
         //// 在标题区
         //Point canvasStart = cvsTitle.TranslatePoint(new Point(), this);
         //Point canvasStop = cvsTitle.TranslatePoint(new Point(cvsTitle.ActualWidth, cvsTitle.ActualHeight), this);
         //if((position.X >= canvasStart.X) && (position.X <= canvasStop.X) && (position.Y >= canvasStart.Y) && (position.Y <= canvasStop.Y)) {
         //   return true;
         //}
         // 返回结果
         return false;
      }

      //============================================================
      // <T>窗口事件处理。</T>
      //============================================================
      private IntPtr WindowHandleProcess(IntPtr hwnd, int msg, IntPtr wParam, IntPtr lParam, ref bool handled) {
         if (msg == WM_NCHITTEST) {
            // 获取屏幕坐标
            Point p = new Point();
            int pInt = lParam.ToInt32();
            p.X = (pInt << 16) >> 16;
            p.Y = pInt >> 16;
            if (isOnTitleBar(PointFromScreen(p))) {
               // 欺骗系统鼠标在标题栏上
               handled = true;
               return new IntPtr(2);
            }
         }
         return IntPtr.Zero;
      }
   }
}
