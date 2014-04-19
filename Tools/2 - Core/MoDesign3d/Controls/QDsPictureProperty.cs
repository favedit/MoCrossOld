using MO.Content3d.Common;
using MO.Core.Forms.Controls;
using System;
using System.Drawing;
using System.Windows.Forms;

namespace MO.Design3d.Controls
{
   public partial class QDsPictureProperty : UserControl
   {
      // 自定义控件
      protected QPictureBox pic;

      // 鼠标当前坐标
      private Point o;

      // 原始图片
      private Bitmap _localBitmap;

      // 原始大小
      protected Size size=new Size();

      // 倍数
      protected int _scale=1;

      //
      FDrImageSize _sizeChange=new FDrImageSize();

      public QDsPictureProperty() {
         InitializeComponent();
      }

      public PictureBox PictureBox {
         get { return qPictureBox; }
      }

      public Bitmap LocalBitmap {
         get { return _localBitmap; }
         set { _localBitmap = value; }
      }

      public int ScaleValue {
         get { return _scale; }
      }

      //============================================================
      // <T>绘制图片。</T>
      //
      //@param bitmap将要绘制的图形
      //============================================================
      public void DrawImage(Bitmap bit) {
         //qPictureBox.Image = null;
         size.Height = bit.Height;
         size.Width = bit.Width;
         qPictureBox.Width = bit.Width;
         qPictureBox.Height = bit.Height;
         Point po = new Point(0, 0);
         if (bit.Height < this.Height || bit.Width < this.Width) {
            po.Y = this.Height / 2 - bit.Height / 2;
            po.X = this.Width / 2 - bit.Width / 2;
            qPictureBox.Location = po;
         } else {
            qPictureBox.Location = po;
         }
         qPictureBox.Image = bit;
      }

      //============================================================
      // <T>点击获取当前的坐标。</T>
      //============================================================
      private void qPictureBox_MouseDown(object sender, MouseEventArgs e) {
         o = new Point(-e.X, -e.Y);
      }

      //============================================================
      // <T>移动控件。</T>
      //============================================================
      private void qPictureBox_MouseMove(object sender, MouseEventArgs e) {
         Point po = new Point();
         if (qPictureBox.Location.X > 0 && qPictureBox.Location.Y > 0) {
            if (e.Button == MouseButtons.Right) {
               return;
            }
         }
         ((System.Windows.Forms.Control)sender).Cursor = Cursors.Arrow;
         if (e.Button == MouseButtons.Right) {
            this.Cursor = Cursors.Hand;
            Point mousePos = System.Windows.Forms.Control.MousePosition;
            mousePos.Offset(o.X, o.Y);
            po = ((System.Windows.Forms.Control)sender).Parent.PointToClient(mousePos);
            ((System.Windows.Forms.Control)sender).Location = MovePoint(po);
         }
      }

      //============================================================
      // <T>控件移动时的情况判别。</T>
      //
      //@param po移动后的坐标值
      //@return 坐标位置
      //============================================================
      private Point MovePoint(Point po) {
         // 高度超出父控件时1
         if (qPictureBox.Width <= TxturePanel.Width && qPictureBox.Height > TxturePanel.Height) {
            po.X = qPictureBox.Location.X;
            if (po.Y > 0) {
               po.Y = 0;
            }
            if (po.Y + qPictureBox.Height < TxturePanel.Location.Y + TxturePanel.Height) {
               po.Y = TxturePanel.Location.Y + TxturePanel.Height - qPictureBox.Height;
            }
            return po;
         } else if (qPictureBox.Height <= TxturePanel.Height && qPictureBox.Width <= TxturePanel.Width) {
            // 宽超出父控件时1
            po.Y = qPictureBox.Location.Y;
            if (po.X > 0) {
               po.X = 0;
            }
            if (po.X + qPictureBox.Width < TxturePanel.Location.X + TxturePanel.Width) {
               po.X = TxturePanel.Location.X + (TxturePanel.Width - qPictureBox.Width) / 2;
            }
            return po;
         } else if (qPictureBox.Height <= TxturePanel.Height && qPictureBox.Width > TxturePanel.Width) {
            po.X = qPictureBox.Location.X;
            if (po.Y > 0) {
               po.Y = 0;
            }
            po.Y = (TxturePanel.Height - qPictureBox.Height) / 2;
            return po;
         } else {
            // 整个图大于父控件时
            if (po.X > 0) {
               po.X = 0;
            }
            if (po.Y > 0) {
               po.Y = 0;
            }
            if (po.X + qPictureBox.Width < TxturePanel.Location.X + TxturePanel.Width) {
               po.X = TxturePanel.Location.X + TxturePanel.Width - qPictureBox.Width;
            }
            if (po.Y + qPictureBox.Height < TxturePanel.Location.Y + TxturePanel.Height) {
               po.Y = TxturePanel.Location.Y + TxturePanel.Height - qPictureBox.Height;
            }
            return po;
         }
      }

      //============================================================
      // <T>绘制放大图片</T>
      //============================================================
      private void tsbMagnify2_Click(object sender, System.EventArgs e) {
         _scale = 2;
         MagnifyImage(_scale);
      }

      //============================================================
      // <T>绘制放大图片</T>
      //
      //@prama Width 宽度
      //@prama Height 高度
      //============================================================
      public void MagnifyImage(double dou) {
        Bitmap newbitmap = _sizeChange.PicSized(_localBitmap, dou);
        DrawImage(newbitmap);
      }

      //============================================================
      // <T>还原图片</T>
      //============================================================
      private void tsbRestore_Click(object sender, EventArgs e) {
         _scale = 1;
         MagnifyImage(_scale);
      }

      //============================================================
      // <T>自动适应</T> //可能会出问题
      //============================================================
      private void tsbAutoSize_Click(object sender, EventArgs e) {
         int height = 0;
         int width = 0;
         int ne = (int)Math.Round((decimal)(TxturePanel.Width / TxturePanel.Height));
         if (_localBitmap.Width <= _localBitmap.Height) {
            int nx = (int)Math.Round((decimal)(_localBitmap.Height / _localBitmap.Width));
            if (TxturePanel.Height < TxturePanel.Width) {
               width = TxturePanel.Height/nx;
               height = TxturePanel.Height;
            } else {
               width = TxturePanel.Height / nx;
               height = TxturePanel.Height;
            }
         } else {
            int n = (int)Math.Round((decimal)(_localBitmap.Width / _localBitmap.Height));
            if (TxturePanel.Height < TxturePanel.Width) {
               width = TxturePanel.Height * n;
               height = TxturePanel.Height;
               if (width>TxturePanel.Width) {
                  width = TxturePanel.Width ;
                  height = TxturePanel.Width/n;
               }
            } else {
               width = TxturePanel.Width;
               height = TxturePanel.Width / n;
            }
         }
         AoutSize(width, height);
      }

      //============================================================
      // <T>放大八倍图片</T>
      //============================================================
      private void AoutSize(int width, int height) {
         Point po = new Point(0, 0);
         Size si = new Size(0, 0);
         qPictureBox.Size = si;
         qPictureBox.Width = width;
         qPictureBox.Height = height;
         if (qPictureBox.Height < this.Height || qPictureBox.Width < this.Width) {
            po.Y = TxturePanel.Height / 2 - qPictureBox.Height / 2;
            po.X = TxturePanel.Width / 2 - qPictureBox.Width / 2;
            qPictureBox.Location = po;
         } else {
            qPictureBox.Location = po;
         }
      }

      //============================================================
      // <T>放大八倍图片</T>
      //============================================================
      private void tsbMagnify8_Click(object sender, EventArgs e) {
         _scale = 8;
         MagnifyImage(_scale);
      }

      //============================================================
      // <T>放大四倍图片</T>
      //============================================================
      private void tsbMagnify4_Click(object sender, EventArgs e) {
         _scale = 4;
         MagnifyImage(_scale);
      }
   }
}
