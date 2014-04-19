using MO.Common.Geom;
using MO.Content2d.Resource.Picture;
using System.Drawing;
using System.Windows.Forms;

namespace MO.Design2d.Resource.Controls
{
   public partial class QDsResourceClipViewer : UserControl
   {
      protected SIntSize _showSize = new SIntSize();

      protected FRsResourcePicture _picture;

      protected Bitmap _image;

      protected float _pictureScale = 1.0f;

      // 当前鼠标动作
      protected EMouseAction _mouseAction = EMouseAction.Unknown;

      protected SIntPoint2 _mousePosition = new SIntPoint2();

      protected SIntPoint2 _scrollPosition = new SIntPoint2();

      public event MouseEventHandler OnPictureMouseMove;

      //============================================================
      protected enum EMouseAction : int
      {
         Unknown,
         Drag,
         Mask,
      }

      //============================================================
      public QDsResourceClipViewer() {
         InitializeComponent();
         //初始化
      }

      //============================================================
      public void LoadPicture(FRsResourcePicture picture) {
         if (null != picture) {
            _picture = picture;
            int w = (int)(picture.Size.Width * _pictureScale);
            int h = (int)(picture.Size.Height * _pictureScale);
            ChangeSize(w, h);
         }
      }

      //============================================================
      public void DrawPicture() {
         if (null != _picture) {
            Graphics g = Graphics.FromImage(_image);
            int w = (int)(_picture.Size.Width * _pictureScale);
            int h = (int)(_picture.Size.Height * _pictureScale);
            g.DrawImage(_picture.Bitmap.Native, 0, 0, w, h);
         }
         pbxViewer.Invalidate();
      }

      //============================================================
      public void ChangeSize(int width, int height) {
         // 重新设置位置
         if ((width < Width) || (height < Height)) {
            AutoScroll = false;
            pbxViewer.Left = Width / 2 - width / 2;
            pbxViewer.Top = Height / 2 - height / 2;
         } else {
            AutoScroll = true;
            if (pbxViewer.Left > 0) {
               pbxViewer.Left = 0;
            }
            if (pbxViewer.Top > 0) {
               pbxViewer.Top = 0;
            }
         }
         // 重新设置尺寸
         pbxViewer.Width = width;
         pbxViewer.Height = height;
         // 重新设置图片
         if (null == _image) {
            _image = new Bitmap(width, height);
         } else {
               _image.Dispose();
               _image = new Bitmap(width, height);
         }
         pbxViewer.Image = _image;
         DrawPicture();
      }

      //============================================================
      public void ChangeScale(float scale) {
         _pictureScale = scale;
         if (null != _picture) {
            int w = (int)(_picture.Size.Width * _pictureScale);
            int h = (int)(_picture.Size.Height * _pictureScale);
            ChangeSize(w, h);
         }
      }

      //============================================================
      private void pbxViewer_MouseDown(object sender, MouseEventArgs e) {
         if (MouseButtons.Right == e.Button) {
            // 设置鼠标状态
            _mouseAction = EMouseAction.Drag;
            // 设置鼠标样式
            Cursor = Cursors.Hand;
            pbxViewer.Capture = true;
            // 储存视窗位置
            _scrollPosition.Set(-pbxViewer.Left, -pbxViewer.Top);
            // 储存鼠标点击位置
            _mousePosition.Set(e.X + pbxViewer.Left, e.Y + pbxViewer.Top);
         }
      }

      //============================================================
      private void pbxViewer_MouseUp(object sender, MouseEventArgs e) {
         _mouseAction = EMouseAction.Unknown;
         Cursor = Cursors.Default;
         pbxViewer.Capture = false;
      }

      //============================================================
      private void pbxViewer_MouseMove(object sender, MouseEventArgs e) {
         if (EMouseAction.Drag == _mouseAction) {
            int x = e.X;
            int y = e.Y;
            int cx = _scrollPosition.X - (x + pbxViewer.Left - _mousePosition.X);
            if ((cx >= HorizontalScroll.Minimum) && (cx < HorizontalScroll.Maximum)) {
               HorizontalScroll.Value = cx;
            }
            int cy = _scrollPosition.Y - (y + pbxViewer.Top - _mousePosition.Y);
            if ((cy >= VerticalScroll.Minimum) && (cy < VerticalScroll.Maximum)) {
               VerticalScroll.Value = cy;
            }
         }
         if (null != OnPictureMouseMove) {
            OnPictureMouseMove(sender, e);
         }
      }

      //============================================================
      private void QResPictureViewer_Resize(object sender, System.EventArgs e) {
         if (null != _picture) {
            int w = (int)(_picture.Size.Width * _pictureScale);
            int h = (int)(_picture.Size.Height * _pictureScale);
            ChangeSize(w, h);
         }
      }
   }
}
