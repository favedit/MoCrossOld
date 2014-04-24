using System;
using System.Windows;
using System.Windows.Interop;

namespace MO.Scout.Face.Forms
{
   //============================================================
   // <T>控制台窗口。</T>
   //
   // @history MAOCY 140424
   //============================================================
   public partial class WConsole : Window
   {
      // 消息定义
      private const int WM_NCHITTEST = 0x0084;

      // 处理线程
      protected FConsoleThread _thread = new FConsoleThread();

      //============================================================
      // <T>构造控制台窗口。</T>
      //============================================================
      public WConsole() {
         InitializeComponent();
      }

      //============================================================
      // <T>启动窗口。</T>
      //============================================================
      private void Window_Loaded(object sender, RoutedEventArgs e) {
         // 添加一个消息过滤器
         IntPtr hwnd = new WindowInteropHelper(this).Handle;
         HwndSource.FromHwnd(hwnd).AddHook(new HwndSourceHook(WindowHandleProcess));
         // 启动处理
         RScoutManager.Startup();
         // 启动处理线程
         _thread._console = this;
         _thread.Start();
      }

      //============================================================
      // <T>卸载窗口。</T>
      //============================================================
      private void Window_Unloaded(object sender, RoutedEventArgs e) {
         // 关闭处理线程
         _thread.Stop();
         // 关闭处理
         RScoutManager.Shutdown();
      }

      //============================================================
      // <T>关闭处理。</T>
      //============================================================
      private void Button_Click(object sender, RoutedEventArgs e) {
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
         if (position.X >= 0 && position.X <= imgTitle.Width && position.Y > 0 && position.Y < imgTitle.Height) {
            return true;
         }
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

      //============================================================
      // <T>数据刷新。</T>
      //============================================================
      public void DataRefresh() {
         ctlStatistics.DataRefresh();
      }
   }
}
