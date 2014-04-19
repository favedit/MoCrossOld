using System;
using System.Drawing;
using System.Drawing.Imaging;
using System.Runtime.InteropServices;

namespace MO.Content3d.Common
{
   //============================================================
   public class FPngMerger : IDisposable
   {
      protected string _fileName;

      protected Bitmap _bitmapColor;

      protected Bitmap _bitmapR;

      protected Bitmap _bitmapG;

      protected Bitmap _bitmapB;
      
      protected Bitmap _bitmapAlpha;

      protected Bitmap _bitmap;

      //============================================================
      public FPngMerger() {
      }

      //============================================================
      public Bitmap Bitmap {
         get { return _bitmap; }
      }

      //============================================================
      public void LoadColorFile(string fileName) {
         _fileName = fileName;
         _bitmapColor = new Bitmap(fileName);
      }

      //============================================================
      public void LoadColorFile(string fileName,Bitmap bits) {
         _fileName = fileName;
         _bitmapColor = new Bitmap(fileName);
         bits = _bitmapColor;
      }

      //============================================================
      public void LoadRFile(string fileName) {
         _fileName = fileName;
         _bitmapR = new Bitmap(fileName);
      }

      //============================================================
      public void LoadGFile(string fileName) {
         _fileName = fileName;
         _bitmapG = new Bitmap(fileName);
      }

      //============================================================
      public void LoadBFile(string fileName) {
         _fileName = fileName;
         _bitmapB = new Bitmap(fileName);
      }

      //============================================================
      public void LoadAlphaFile(string fileName) {
         _fileName = fileName;
         _bitmapAlpha = new Bitmap(fileName);
      }

      //============================================================
      public void SaveFile(string fileName) {
//          int width = _bitmapColor.Width;
//          int height = _bitmapColor.Height;
//          _bitmap = new Bitmap(width, height, PixelFormat.Format32bppArgb);
//          for (int y = 0; y < height; y++) {
//             for (int x = 0; x < width; x++) {
//                Color color = Color.White;
//                if (null != _bitmapColor) {
//                   color = _bitmapColor.GetPixel(x, y);
//                }
//                Color alpha = Color.White;
//                if (null != _bitmapAlpha) {
//                   alpha = _bitmapAlpha.GetPixel(x, y);
//                }
//                _bitmap.SetPixel(x, y, Color.FromArgb(alpha.R, color));
//             }
//          }
//          _bitmap.Save(fileName, ImageFormat.Png);
         int width = _bitmapColor.Width;
         int height = _bitmapColor.Height;
         Rectangle bounds = Rectangle.FromLTRB(0, 0, width, height);
         BitmapData data = _bitmap.LockBits(bounds, ImageLockMode.ReadOnly, PixelFormat.Format32bppArgb);
         int[] buffer = new int[_bitmap.Width];
         try {
            long offset = data.Scan0.ToInt64();
            for (int row = 0; row < height; row++) {
               // 复制一行颜色到缓冲中
               Marshal.Copy(new IntPtr(offset), buffer, 0, width);               
               // 增加到优化器中
               foreach (int value in buffer) {
                  Color color = Color.FromArgb(value);
                  color = Color.FromArgb(255, color);
                  // _bitmap.SetPixel(0, 0, color);
               }
            }
            // 修正行边界
            offset += data.Stride;

         } finally {
            // 释放图像对象
            _bitmap.UnlockBits(data);
         }
         _bitmap.Save(fileName, ImageFormat.Png);
      }

      //============================================================
      public Bitmap Merge() {
         int width = 0;
         int height = 0;
         // 获得数据对象
         BitmapData dataColor = null;
         long offsetColor = 0;
         if (null != _bitmapColor) {
            // 获得操作数据
            width = _bitmapColor.Width;
            height = _bitmapColor.Height;
            if (!IsPower(width) || !IsPower(height)) {
               System.Windows.Forms.MessageBox.Show("高度宽度不是2的幂次方:" + _fileName);
            }
            dataColor = _bitmapColor.LockBits(Rectangle.FromLTRB(0, 0, width, height), ImageLockMode.ReadOnly, PixelFormat.Format32bppArgb);
            offsetColor = dataColor.Scan0.ToInt64();
         }
         BitmapData dataAlpha = null;
         long offsetAlpha = 0;
         if (null != _bitmapAlpha) {
            width = _bitmapAlpha.Width;
            height = _bitmapAlpha.Height;
            if (!IsPower(width) || !IsPower(height)) {
               System.Windows.Forms.MessageBox.Show("高度宽度不是2的幂次方:" + _fileName);
            }
            dataAlpha = _bitmapAlpha.LockBits(Rectangle.FromLTRB(0, 0, width, height), ImageLockMode.ReadOnly, PixelFormat.Format32bppArgb);
            offsetAlpha = dataAlpha.Scan0.ToInt64();
         }
         // 同时存在判断高宽是否相等
         if (null != _bitmapColor && null != _bitmapAlpha) {
            if (_bitmapColor.Width != _bitmapAlpha.Width && _bitmapColor.Height != _bitmapAlpha.Height) {
               System.Windows.Forms.MessageBox.Show("高度宽度不相等:" + _fileName);
            }
         }
         _bitmap = new Bitmap(width, height, PixelFormat.Format32bppArgb);
         BitmapData data = _bitmap.LockBits(Rectangle.FromLTRB(0, 0, width, height), ImageLockMode.WriteOnly, PixelFormat.Format32bppArgb);
         // 处理颜色行
         int color, alpha;
         int[] bufferColor = new int[width];
         int[] bufferAlpha = new int[width];
         int[] buffer = new int[width];
         long offset = data.Scan0.ToInt64();
         for (int row = 0; row < height; row++) {
            // 复制一行颜色到缓冲中
            if(null != dataColor){
               Marshal.Copy(new IntPtr(offsetColor), bufferColor, 0, width);
               offsetColor += dataColor.Stride;
            }
            if(null != dataAlpha){
               Marshal.Copy(new IntPtr(offsetAlpha), bufferAlpha, 0, width);
               offsetAlpha += dataAlpha.Stride;
            }
            // 增加到优化器中
            for (int n = 0; n < width; n++) {
               color = (null != dataColor) ? bufferColor[n] : -1;
               alpha = (null != dataAlpha) ? bufferAlpha[n] : -1;
               buffer[n] = ((alpha & 0x00FF0000) << 8) | (color & 0x00FFFFFF);
               // buffer[n] = Color.FromArgb((alpha & 0x00FF0000) >> 16, (color & 0x00FF0000) >> 16, (color & 0x0000FF00) >> 8, color & 0x000000FF).ToArgb();
            }
            Marshal.Copy(buffer, 0, new IntPtr(offset), width);
            offset += data.Stride;
         }
         if (null != _bitmapColor) {
            _bitmapColor.UnlockBits(dataColor);
         }
         if (null != _bitmapAlpha) {
            _bitmapAlpha.UnlockBits(dataAlpha);
         }
         _bitmap.UnlockBits(data);
         return _bitmap;
      }

      //============================================================
      public Bitmap MergeAll() {
         int width = 0;
         int height = 0;
         // 获得数据对象
         BitmapData dataR = null;
         long offsetR = 0;
         if (null != _bitmapR) {
            // 获得操作数据
            width = _bitmapR.Width;
            height = _bitmapR.Height;
            dataR = _bitmapR.LockBits(Rectangle.FromLTRB(0, 0, width, height), ImageLockMode.ReadOnly, PixelFormat.Format32bppArgb);
            offsetR = dataR.Scan0.ToInt64();
         }
         BitmapData dataG = null;
         long offsetG = 0;
         if (null != _bitmapG) {
            // 获得操作数据
            width = _bitmapG.Width;
            height = _bitmapG.Height;
            dataG = _bitmapG.LockBits(Rectangle.FromLTRB(0, 0, width, height), ImageLockMode.ReadOnly, PixelFormat.Format32bppArgb);
            offsetG = dataG.Scan0.ToInt64();
         }
         BitmapData dataB = null;
         long offsetB = 0;
         if (null != _bitmapB) {
            // 获得操作数据
            width = _bitmapB.Width;
            height = _bitmapB.Height;
            dataB = _bitmapB.LockBits(Rectangle.FromLTRB(0, 0, width, height), ImageLockMode.ReadOnly, PixelFormat.Format32bppArgb);
            offsetB = dataB.Scan0.ToInt64();
         }
         BitmapData dataAlpha = null;
         long offsetAlpha = 0;
         if (null != _bitmapAlpha) {
            width = _bitmapAlpha.Width;
            height = _bitmapAlpha.Height;
            dataAlpha = _bitmapAlpha.LockBits(Rectangle.FromLTRB(0, 0, width, height), ImageLockMode.ReadOnly, PixelFormat.Format32bppArgb);
            offsetAlpha = dataAlpha.Scan0.ToInt64();
         }
         _bitmap = new Bitmap(width, height, PixelFormat.Format32bppArgb);
         BitmapData data = _bitmap.LockBits(Rectangle.FromLTRB(0, 0, width, height), ImageLockMode.WriteOnly, PixelFormat.Format32bppArgb);
         // 处理颜色行
         int r, g, b, a;
         int[] bufferR = new int[width];
         int[] bufferG = new int[width];
         int[] bufferB = new int[width];
         int[] bufferAlpha = new int[width];
         int[] buffer = new int[width];
         long offset = data.Scan0.ToInt64();
         for (int row = 0; row < height; row++) {
            // 复制一行颜色到缓冲中
            if (null != dataR) {
               Marshal.Copy(new IntPtr(offsetR), bufferR, 0, width);
               offsetR += dataR.Stride;
            }
            if (null != dataG) {
               Marshal.Copy(new IntPtr(offsetG), bufferG, 0, width);
               offsetG += dataG.Stride;
            }
            if (null != dataB) {
               Marshal.Copy(new IntPtr(offsetB), bufferB, 0, width);
               offsetB += dataB.Stride;
            }
            if (null != dataAlpha) {
               Marshal.Copy(new IntPtr(offsetAlpha), bufferAlpha, 0, width);
               offsetAlpha += dataAlpha.Stride;
            }
            // 增加到优化器中
            for (int n = 0; n < width; n++) {
               r = (null != dataR) ? bufferR[n] : -1;
               g = (null != dataG) ? bufferG[n] : -1;
               b = (null != dataB) ? bufferB[n] : -1;
               a = (null != dataAlpha) ? bufferAlpha[n] : -1;
               buffer[n] = ((a & 0x00FF0000) << 8) | (r & 0x00FF0000) | (g & 0x0000FF00) | (b & 0x000000FF);
            }
            Marshal.Copy(buffer, 0, new IntPtr(offset), width);
            offset += data.Stride;
         }
         if (null != _bitmapR) {
            _bitmapR.UnlockBits(dataR);
         }
         if (null != _bitmapG) {
            _bitmapG.UnlockBits(dataG);
         }
         if (null != _bitmapB) {
            _bitmapB.UnlockBits(dataB);
         }
         if (null != _bitmapAlpha) {
            _bitmapAlpha.UnlockBits(dataAlpha);
         }
         _bitmap.UnlockBits(data);
         return _bitmap;
      }

      //============================================================
      public void Dispose() {
         if (null != _bitmapColor) {
            _bitmapColor.Dispose();
            _bitmapColor = null;
         }
         if (null != _bitmapR) {
            _bitmapR.Dispose();
            _bitmapR = null;
         }
         if (null != _bitmapG) {
            _bitmapG.Dispose();
            _bitmapG = null;
         }
         if (null != _bitmapB) {
            _bitmapB.Dispose();
            _bitmapB = null;
         }
         if (null != _bitmapAlpha) {
            _bitmapAlpha.Dispose();
            _bitmapAlpha = null;
         }
         if (null != _bitmap) {
            _bitmap.Dispose();
            _bitmap = null;
         }
      }

      //============================================================
      protected bool IsPower(int number) {
         if (number <= 0) {
            return false;
         }
         return (number|(number-1))==(2*number-1);
      }
   }
}
