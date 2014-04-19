using System.Drawing;
using System.Drawing.Imaging;
using MO.Common.Geom;
using MO.Common.Lang;
using System.Runtime.InteropServices;
using System;

namespace MO.Core.Forms.Core
{
   public class FBitmap : FObject
   {
      protected Bitmap _bitmap;

      //============================================================
      public FBitmap() {
      }

      //============================================================
      public FBitmap(int width, int height) {
         _bitmap = new Bitmap(width, height, PixelFormat.Format32bppArgb);
      }

      //============================================================
      public FBitmap(string file) {
         LoadFile(file);
      }

      //============================================================
      public Bitmap Bitmap {
         get { return _bitmap; }
         set { _bitmap = value; }
      }

      //============================================================
      public void Assign(FBitmap bitmap) {
         _bitmap = null;
         if (bitmap._bitmap != null) {
            _bitmap = (Bitmap)bitmap._bitmap.Clone();
         }
      }

      //============================================================
      public void Create(SIntSize size) {
         _bitmap = new Bitmap(size.Width, size.Height);
      }

      //============================================================
      public void Create(int width, int height) {
         _bitmap = new Bitmap(width, height);
      }

      //============================================================
      public void LoadFile(string file) {
         _bitmap = (Bitmap)Bitmap.FromFile(file);
      }

      //============================================================
      public void Fill(Image image, int x, int y) {
         int width = _bitmap.Width;
         int height = _bitmap.Height;
         using (Graphics g = Graphics.FromImage(_bitmap)) {
            g.DrawImage(image,
               new Rectangle(0, 0, width, height),
               new Rectangle(x, y, width, height),
               GraphicsUnit.Pixel);
         }
      }

      //============================================================
      // <T>指定位置复制图片<T>      
      //============================================================
      public void FillPixels(Bitmap image, int x, int y)
      {
         // 取得图片的宽度
         int width = image.Width;         
         
         // 内存锁定
         BitmapData data = image.LockBits(new Rectangle(x,y, _bitmap.Width, _bitmap.Height), ImageLockMode.ReadOnly, PixelFormat.Format32bppArgb);
         // 读出大小
         long dataColor = data.Scan0.ToInt64();                  
         BitmapData bitData = _bitmap.LockBits(Rectangle.FromLTRB(0, 0, _bitmap.Width, _bitmap.Height), ImageLockMode.WriteOnly, PixelFormat.Format32bppArgb);
         long bufferData = bitData.Scan0.ToInt64();
         // 存储数组
         int[] buffer = new int[width];
         int[] bufferColor = new int[width];
         int height = _bitmap.Height;
         for (int row = 0; row < height; row++) {
            Marshal.Copy(new IntPtr(dataColor), buffer, 0, _bitmap.Width);
            dataColor += data.Stride;
            // 复制数组
            Array.Copy(buffer, bufferColor, buffer.Length);
            Marshal.Copy(bufferColor, 0, new IntPtr(bufferData), _bitmap.Width);
            bufferData += bitData.Stride;
         }
         if (null != data) {
            image.UnlockBits(data);
         }
         if (null != bitData) {
            _bitmap.UnlockBits(bitData);
         }
      }

      //============================================================
      public void SetTransparent(int alpha) {
         int cx = _bitmap.Width;
         int cy = _bitmap.Height;
         for (int y = 0; y < cy; y++) {
            for (int x = 0; x < cx; x++) {
               Color c = _bitmap.GetPixel(x, y);
               _bitmap.SetPixel(x, y, Color.FromArgb(alpha, c));
            }
         }
      }

      //============================================================
      public void SetTransparentOnly() {
         int cx = _bitmap.Width;
         int cy = _bitmap.Height;
         for (int y = 0; y < cy; y++) {
            for (int x = 0; x < cx; x++) {
               Color c = _bitmap.GetPixel(x, y);
               _bitmap.SetPixel(x, y, Color.FromArgb(255, c.A, c.A, c.A));
            }
         }
      }

      //============================================================
      public FGraphics GetGraphics() {
         return new FGraphics(Graphics.FromImage(_bitmap));
      }

      //============================================================
      public void Resize(int width, int height) {
         if (null == _bitmap) {
            _bitmap = new Bitmap(width, height, PixelFormat.Format32bppArgb);
         } else if ((width > _bitmap.Width) || (height > _bitmap.Height)) {
            // 释放图片
            _bitmap.Dispose();
            // 重建大小图片
            _bitmap = new Bitmap(width, height, PixelFormat.Format32bppArgb);
         }
      }
   }
}
