using MO.Common.Lang;
using MoScout.Core;
using System;
using System.IO;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;
using System.Windows.Media.Imaging;

namespace MO.Scout.Face.Panel
{
   //============================================================
   // <T>统计信息面板。</T>
   //
   // @history MAOCY 140425
   //============================================================
   public partial class WStatistics : UserControl
   {
      protected bool _statusSetup;

      protected RenderTargetBitmap _bitmap;

      protected DrawingVisual _visual;

      protected SolidColorBrush _brush;

      protected Pen _pen;

      protected int _drawFrameWidth = 10;

      protected FObjects<FFrameInfo> _activeFrames = new FObjects<FFrameInfo>();

      //============================================================
      // <T>构造统计信息面板。</T>
      //============================================================
      public WStatistics() {
         InitializeComponent();
      }

      //============================================================
      // <T>加载完成处理。</T>
      //============================================================
      private void UserControl_Loaded(object sender, RoutedEventArgs e) {
         int width = (int)cvTracker.ActualWidth;
         int height = (int)cvTracker.ActualHeight;
         _visual = new DrawingVisual();
         _bitmap = new RenderTargetBitmap(width, height, 96, 96, PixelFormats.Pbgra32);
         imgCanvas.Source = _bitmap;
         _brush = new SolidColorBrush(Color.FromArgb(255, 255, 0, 0));
         SolidColorBrush brush = new SolidColorBrush(Color.FromArgb(255, 0, 255, 0));
         _pen = new Pen(brush, 1);
         _statusSetup = true;
      }

      //============================================================
      // <T>卸载完成处理。</T>
      //============================================================
      private void UserControl_Unloaded(object sender, RoutedEventArgs e) {
      }

      //============================================================
      // <T>保存图形。</T>
      //============================================================
      public void SaveCanvas() {
         string path = "C:\\Test.jpg";
         FileStream fs = new FileStream(path, FileMode.Create); //文件流对象  
         //RenderTargetBitmap用来创建一副位图对象  
         RenderTargetBitmap rtb = new RenderTargetBitmap((int)cvTracker.ActualWidth, (int)cvTracker.ActualHeight, 1 / 100, 1 / 100, PixelFormats.Pbgra32);
         rtb.Render(cvTracker); //呈现位图对象  
         //BitmapEncoder用来保存BitmapFrame对象，并保存为指定的文件  
         //BitmapFrame是图像数据  
         BitmapEncoder be = new TiffBitmapEncoder(); //指定格式  
         be.Frames.Add(BitmapFrame.Create(rtb));
         be.Save(fs);
         fs.Close();
      }

      //============================================================
      // <T>数据刷新处理。</T>
      //============================================================
      public void DrawFrame(DrawingContext context, FFrameInfo frameInfo) {
         int loggerCount = frameInfo.Loggers.Count;
         int index = frameInfo.Index;
         int left = _drawFrameWidth * index;
         int top = 150 - loggerCount;
         context.DrawRectangle(_brush, _pen, new Rect(left, top, left + _drawFrameWidth - 1, 200 - top));
      }

      //============================================================
      // <T>数据刷新处理。</T>
      //============================================================
      public void DataRefresh() {
         // 获得信息
         FApplicationInfo info = RScoutManager.InfoConsole.ActiveInfo;
         DrawingContext context = _visual.RenderOpen();
         // 绘制所有帧
         int count = info.Frames.Count;
         for (int n = 0; n < count; n++) {
            FFrameInfo frameInfo = info.Frames.Get(n);
            DrawFrame(context, frameInfo);
         }
         context.Close();
         // 绘制处理
         _bitmap.Clear();
         _bitmap.Render(_visual);
      }

      //============================================================
      // <T>异步数据刷新处理。</T>
      //============================================================
      public void AnsyDataRefresh() {
         if (!_statusSetup) {
            return;
         }
         FApplicationInfo info = RScoutManager.InfoConsole.ActiveInfo;
         if(info == null) {
            return;
         }
         Dispatcher.InvokeAsync(new Action(DataRefresh));
      }
   }
}
