using System;
using System.ComponentModel;
using System.Drawing;
using System.Drawing.Imaging;
using System.Runtime.InteropServices;
using System.Windows.Forms;
using MO.Common.Lang;
using MO.Common.Geom;

namespace MO.Core.Forms.Controls
{
   //============================================================
   // <T>图片控件。</T>
   //
   // @author CJJUN 120129
   //============================================================
   public partial class QPictureBox : QControl
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(QPictureBox));

      // 缩放模式
      protected EPictureBoxMode _scaleMode = EPictureBoxMode.Auto;

      // 缩放比率
      protected float _scaleValue = 1.0f;

      // 原始分辨率
      protected SFloatSize _resolution = new SFloatSize();

      // 原始位图
      protected Bitmap _bitmap;

      // 显示位图
      protected Bitmap _bitmapPreview;

      // 判断是否鼠标已经按下
      protected bool _mousePressed = false;

      // 用来记录鼠标按下时的坐标
      protected Point _mousePosition = new Point();

      // 用来记录鼠标移动时的当前坐标
      private Point _currentPosition = new Point();

      //============================================================
      // <T>构造图片控件。</T>
      //============================================================
      public QPictureBox() {
         InitializeComponent();
      }

      //============================================================
      // <T>内部设置大小尺寸。</T>
      //
      // @param width 宽度
      // @param height 高度
      //============================================================
      protected void InnerSetSize(int width, int height) {
         // 设置信息
         int contentLeft = _borderOuter.Left.Width + _borderInner.Left.Width;
         int contentTop = _borderOuter.Top.Width + _borderInner.Top.Width;
         int contentRight = _borderOuter.Right.Width + _borderInner.Right.Width;
         int contentBottom = _borderOuter.Bottom.Width + _borderInner.Bottom.Width;
         // 设置内容
         pnlScroll.SetBounds(contentLeft, contentTop, width - contentLeft - contentRight, height - contentTop - contentBottom);
         // 强制重绘
         Invalidate();
      }

      //============================================================
      // <T>当位图的大小发生改变时。调用此方法来改变。</T>
      //============================================================
      protected void InnerChangePreviewSize(int width, int height, Image image = null) {
         // 释放内存
         if(null != _bitmapPreview) {
            if((_bitmapPreview.Width != width) || (_bitmapPreview.Height != height)) {
               _bitmapPreview.Dispose();
               _bitmapPreview = null;
            }
         }
         // 创建新图片
         if(null == _bitmapPreview) {
            _bitmapPreview = new Bitmap(width, height, PixelFormat.Format32bppArgb);
            _bitmapPreview.SetResolution(_resolution.Width, _resolution.Height);
         }
      }

      //============================================================
      //<T>将位图置于空间的中央。</T>
      //============================================================
      protected void InnerCenter(Bitmap bitmap) {
         // 重绘图片到中间
         pbxImage.Image = bitmap;
         pbxImage.Invalidate();
         pnlScroll.HorizontalScroll.Value = 0;
         pnlScroll.VerticalScroll.Value = 0;
         Point po = new Point();
         po.X = pnlScroll.Size.Width / 2 - bitmap.Width / 2;
         po.Y = pnlScroll.Size.Height / 2 - bitmap.Height / 2;
         po.X = (po.X < 0) ? 0 : po.X;
         po.Y = (po.Y < 0) ? 0 : po.Y;
         pbxImage.SetBounds(po.X, po.Y, bitmap.Width, bitmap.Height);
         pbxImage.Invalidate();
         // 移动滚动条到中间
         bool needUpdateX = false;
         bool needUpdateY = false;
         int posX = 0;
         int posY = 0;
         if(bitmap.Width > pnlScroll.Width) {
            needUpdateX = true;
            posX = (pnlScroll.HorizontalScroll.Maximum - pnlScroll.HorizontalScroll.LargeChange) / 2;
         }
         if(bitmap.Height > pnlScroll.Height) {
            needUpdateY = true;
            posY = (pnlScroll.VerticalScroll.Maximum - pnlScroll.VerticalScroll.LargeChange) / 2;
         }
         if(needUpdateX && needUpdateY) {
            pnlScroll.AutoScrollPosition = new Point(posX, posY);
         } else if(needUpdateX) {
            pnlScroll.AutoScrollPosition = new Point(posX, pnlScroll.AutoScrollPosition.Y);
         } else if(needUpdateY) {
            pnlScroll.AutoScrollPosition = new Point(pnlScroll.AutoScrollPosition.X, posY);
         }
      }
      
      //============================================================
      // <T>绘制自动尺寸的图片.</T>
      //============================================================
      protected void DrawAutoSize() {
         // 设置滑动条不可见
         pnlScroll.AutoScroll = false;
         // 计算纵横相对于控件的缩放比
         float scaleWidth = (float)pnlScroll.Width / (float)_bitmap.Width;
         float scaleHeight = (float)pnlScroll.Height / (float)_bitmap.Height;
         _scaleValue = Math.Min(scaleWidth, scaleHeight);
         // 选择较小的作为整体的缩放比
         if(0 != _scaleValue) {
            // 设置图片显示大小
            int width = (int)(_bitmap.Width * _scaleValue) - 3;
            int height = (int)(_bitmap.Height * _scaleValue) - 3;
            InnerChangePreviewSize(width, height);
            using(Graphics g = Graphics.FromImage(_bitmapPreview)) {
               g.DrawImage(_bitmap, new Rectangle(0, 0, width, height));
            }
            // 设置图片显示位置
            int top = (pnlScroll.Width - width) / 2 - 1;
            int left = (pnlScroll.Height - height) / 2 - 1;
            pbxImage.Image = _bitmapPreview;
            pbxImage.SetBounds(top, left, width, height);
         }
      }

      //============================================================
      // <T>绘制当位图的大小发生改变时。调用此方法来改变。</T>
      //============================================================
      protected void DrawScale() {
         // 设置滑动条可见
         pnlScroll.AutoScroll = true;
         // 计算图片大小
         int previewWidth = (int)(_bitmap.Width * _scaleValue);
         int previewHeight = (int)(_bitmap.Height * _scaleValue);
         if((pnlScroll.Width - previewWidth) < 3) {
            previewHeight = pnlScroll.Width - 3;
         }
         if((pnlScroll.Height - previewHeight) < 3) {
            previewHeight = pnlScroll.Height - 3;
         }
         InnerChangePreviewSize(previewWidth, previewHeight);
         // 绘制图片内容
         using(Graphics g = Graphics.FromImage(_bitmapPreview)) {
            g.DrawImage(_bitmap, new Rectangle(0, 0, previewWidth, previewHeight));
         }
         // 放在中间
         InnerCenter(_bitmapPreview);
      }

      //============================================================
      // <T>按照资源的像素点放大或者缩小。</T>
      //============================================================
      protected void DrawScalePixel() {
         // 设置滑动条可见
         pnlScroll.AutoScroll = true;
         // 设置图片大小
         int scale = (int)_scaleValue;
         if(scale <= 0 || scale > 64) {
            return;
         }
         int width = _bitmap.Width;
         int height = _bitmap.Height;
         int previewWidth = width * scale;
         int previewHeight = height * scale;
         InnerChangePreviewSize(previewWidth, previewHeight);
         // 绘制图片内容
         BitmapData data = _bitmap.LockBits(new Rectangle(0, 0, width, height), ImageLockMode.ReadOnly, PixelFormat.Format32bppArgb);
         long dataColor = data.Scan0.ToInt64();
         int[] buffer = new int[width];
         BitmapData previewData = _bitmapPreview.LockBits(Rectangle.FromLTRB(0, 0, previewWidth, previewHeight), ImageLockMode.WriteOnly, PixelFormat.Format32bppArgb);
         long previewColor = previewData.Scan0.ToInt64();
         int[] previewBuffer = new int[previewWidth];
         for(int row = 0; row < height; row++) {
            // 计算一行数据。
            Marshal.Copy(new IntPtr(dataColor), buffer, 0, width);
            int position = 0;
            for(int n = 0; n < width; n++) {
               int value = buffer[n];
               int count = 0;
               do {
                  previewBuffer[position + count] = value;
               } while(++count < scale);
               position += scale;
            }
            dataColor += data.Stride;
            // 复制一行数据。
            for(int n = 0; n < scale; n++) {
               Marshal.Copy(previewBuffer, 0, new IntPtr(previewColor), previewWidth);
               previewColor += previewData.Stride;
            }
         }
         if(null != data) {
            _bitmap.UnlockBits(data);
         }
         if(null != previewData) {
            _bitmapPreview.UnlockBits(previewData);
         }
         // 放在中间
         InnerCenter(_bitmapPreview);
      }
      
      //============================================================
      // <T>当位图的大小发生改变时。调用此方法来改变。</T>
      //============================================================
      protected void InnerRefresh() {
         // 检查图片存在
         if(null == _bitmap) {
            return;
         }
         // 绘制指定大小的图片
         switch(_scaleMode) {
            case EPictureBoxMode.Auto:
               // 绘制自动缩放
               DrawAutoSize();
               break;
            case EPictureBoxMode.Scale:
               DrawScale();
               break;
            case EPictureBoxMode.ScalePixel:
               if(_scaleValue < 1.0f) {
                  DrawScale();
               } else {
                  DrawScalePixel();
               }
               break;
            default:
               throw new FFatalException("Unknown scale mode. (mode={0})", _scaleMode);
         }
         // 设置图片
         pbxImage.Invalidate();
      }
      
      //============================================================
      // <T>加载一张图片。</T>
      // 
      // @param bitmap 需要载入的位图
      //============================================================
      public void LoadBitmap(Image image) {
         pbxImage.Visible = true;
         // 图片存在的时候，进行缩放处理
         if(null != _bitmap) {
            _bitmap.Dispose();
            _bitmap = null;
         }
         // 绘制内存图片
         if (null != image) {
            _resolution.Set(image.HorizontalResolution, image.VerticalResolution);
            _bitmap = new Bitmap(image.Width, image.Height, PixelFormat.Format32bppArgb);
            _bitmap.SetResolution(_resolution.Width, _resolution.Height);
            using (Graphics g = Graphics.FromImage(_bitmap)) {
               g.DrawImageUnscaled(image, 0, 0);
            }
         }
         // 刷新显示
         InnerRefresh();
      }

      //============================================================
      // <T>获取或设定尺寸大小。</T>
      //============================================================
      [Browsable(true)]
      public new Size Size {
         get { return base.Size; }
         set { InnerSetSize(value.Width, value.Height); }
      }

      //============================================================
      // <T>获取关联图片是否为空。</T>
      //============================================================
      public bool IsBitmapEmpty() {
         return (null != _bitmap);
      }

      //============================================================
      // <T>读取位图。</T>
      //============================================================
      public Bitmap Bitmap {
         get { return _bitmap; }
      }

      //============================================================
      // <T>图片控件的缩放模式。</T>
      //============================================================
      public EPictureBoxMode ScaleMode {
         get { return _scaleMode; }
         set {
            if(_scaleMode != value) {
               _scaleMode = value;
               InnerRefresh();
            }
         }
      }
      
      //============================================================
      // <T>读取或者设置缩放大小。</T>
      //============================================================
      public float ScaleValue {
         get { return _scaleValue; }
         set {
            if(_scaleValue != value) {
               _scaleValue = value;
               InnerRefresh();
            }
         }
      }

      //============================================================
      // <T>处理大小改变事件。</T>
      //
      // @param sender 发送者
      // @param e 事件
      //============================================================
      private void QPictureBox_Resize(object sender, EventArgs e) {
         InnerSetSize(Width, Height);
         if(null != _bitmap) {
            InnerRefresh();
         }
      }

      //============================================================
      // <T>鼠标按键按下时处理。</T>
      //============================================================
      private void pbxImage_MouseDown(object sender, MouseEventArgs e) {
         _mousePressed = true;
         pbxImage.Cursor = System.Windows.Forms.Cursors.Hand;
         _mousePosition = pbxImage.PointToScreen(e.Location);
      }

      //============================================================
      // <T>鼠标移动时处理事件</T>
      //============================================================
      private void pbxImage_MouseMove(object sender, MouseEventArgs e) {
         if(_mousePressed) {
            _currentPosition = pbxImage.PointToScreen(e.Location);
            int offsetX = (_mousePosition.X - _currentPosition.X);
            int offsetY = (_mousePosition.Y - _currentPosition.Y);
            if((pnlScroll.HorizontalScroll.Value + offsetX) > pnlScroll.HorizontalScroll.Maximum) {
               pnlScroll.HorizontalScroll.Value = pnlScroll.HorizontalScroll.Maximum;
            } else if((pnlScroll.HorizontalScroll.Value + offsetX) < 0) {
               pnlScroll.HorizontalScroll.Value = 0;
            } else {
               pnlScroll.HorizontalScroll.Value = pnlScroll.HorizontalScroll.Value + offsetX;
            }
            if((pnlScroll.VerticalScroll.Value + offsetY) > pnlScroll.VerticalScroll.Maximum) {
               pnlScroll.VerticalScroll.Value = pnlScroll.VerticalScroll.Maximum;
            } else if((pnlScroll.VerticalScroll.Value + offsetY) < 0) {
               pnlScroll.VerticalScroll.Value = 0;
            } else {
               pnlScroll.VerticalScroll.Value = pnlScroll.VerticalScroll.Value + offsetY;
            }
            _mousePosition = _currentPosition;
            //_logger.Debug(null, "", "HMax:{0}    VMax:{1}    HValue{2}    VValue:{3}   offset:{4}   mosePoint:{5}    current:{6}", pnlScroll.HorizontalScroll.Maximum, pnlScroll.VerticalScroll.Maximum, pnlScroll.HorizontalScroll.Value, pnlScroll.VerticalScroll.Value, new Point(offsetX, offsetY), MousePosition, new Point(e.X, e.Y));
         }
      }

      //============================================================
      // <T>鼠标按键放开时处理。</T>
      //============================================================
      private void pbxImage_MouseUp(object sender, MouseEventArgs e) {
         pbxImage.Cursor = System.Windows.Forms.Cursors.Hand;
         _mousePressed = false;
      }

      //============================================================
      // <T> 清空空间里面的信息，释放空间</T>
      //============================================================
      public void Clear() {
         pbxImage.Image = null;
         pbxImage.Visible = false;
         if(null != _bitmap) {
            _bitmap.Dispose();
            _bitmap = null;
         }
         if(null != _bitmapPreview){
            _bitmapPreview.Dispose();
            _bitmapPreview = null;
         }
      }
   }
}
