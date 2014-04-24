using MoScout.Core;
using System.IO;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;
using System.Windows.Media.Imaging;

namespace MoScout.Panel
{
   /// <summary>
   /// WStatistics.xaml 的交互逻辑
   /// </summary>
   public partial class WStatistics : UserControl
   {
      DrawingVisual visual;

      RenderTargetBitmap bitmap;

      public WStatistics() {
         InitializeComponent();
      }

      public void RefreshTime() { 
         //cvTracker.
      }

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

      private void Button_Click(object sender, RoutedEventArgs e) {
         FApplicationInfo info = RScoutManager.InfoConsole.ActiveInfo;

         if(imgCanvas.Source == null) {
            visual = new DrawingVisual();
            bitmap = new RenderTargetBitmap(1024, 1024, 96, 96, PixelFormats.Pbgra32);
            imgCanvas.Source = bitmap;
         }

         SolidColorBrush brush = new SolidColorBrush(Color.FromArgb(255, 255, 0, 0));
         Pen pen = new Pen(brush, 1);
         DrawingContext context = visual.RenderOpen();
         int width = 10;
         int count = info.Frames.Count;
         for(int n = 0; n < count; n++) {
            FFrameInfo frameInfo = info.Frames.Get(n);
            int loggerCount = frameInfo.Loggers.Count;
            int y = 150 - loggerCount;
            context.DrawRectangle(brush, pen, new Rect(width * n, y, width * n + 8, 200 - y));
         }
         context.Close();
         bitmap.Clear();
         bitmap.Render(visual);
      }
   }
}
