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
         // 恢复文件
         RScoutManager.InfoConsole.RestoreFiles();
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
         // 检查位置
         Point titleStart = grdTitle.TranslatePoint(new Point(0, 0), this);
         Point titleStop = grdTitle.TranslatePoint(new Point(grdTitle.ActualWidth, grdTitle.ActualHeight), this);
         if((position.X < titleStart.X) || (position.X > titleStop.X) || (position.Y < titleStart.Y) || (position.Y > titleStop.Y)) {
            return false;
         }
         // 在图标区
         Point imageStart = imgTitle.TranslatePoint(new Point(), this);
         Point imageStop = imgTitle.TranslatePoint(new Point(imgTitle.ActualWidth, imgTitle.ActualHeight), this);
         if((position.X >= imageStart.X) && (position.X <= imageStop.X) && (position.Y >= imageStart.Y) && (position.Y <= imageStop.Y)) {
            return true;
         }
         // 在标题区
         Point canvasStart = cvsTitle.TranslatePoint(new Point(), this);
         Point canvasStop = cvsTitle.TranslatePoint(new Point(cvsTitle.ActualWidth, cvsTitle.ActualHeight), this);
         if((position.X >= canvasStart.X) && (position.X <= canvasStop.X) && (position.Y >= canvasStart.Y) && (position.Y <= canvasStop.Y)) {
            return true;
         }
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

      //============================================================
      // <T>数据刷新。</T>
      //============================================================
      public void DataRefresh() {
         ctlStatistics.AnsyDataRefresh();
      }

      //============================================================
      // <T>加载处理。</T>
      //============================================================
      private void btnLoad_Click(object sender, RoutedEventArgs e) {
         RScoutManager.InfoConsole.RestoreFiles();
         MessageBox.Show("Restore files success.");
      }

      //============================================================
      // <T>保存处理。</T>
      //============================================================
      private void btnSave_Click(object sender, RoutedEventArgs e) {
         RScoutManager.InfoConsole.StoreFiles();
         MessageBox.Show("Store files success.");
      }
   }
}
