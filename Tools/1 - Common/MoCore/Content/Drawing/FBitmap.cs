using System;
using System.Drawing;
using System.Drawing.Imaging;
using System.Runtime.InteropServices;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Core.Content.Drawing
{
   //============================================================
   // <T>位图图片。</T>
   //============================================================
   public class FBitmap : FObject, IDisposable
   {
      // 本地图片
      protected Bitmap _native;

      //============================================================
      // <T>构造位图图片。</T>
      //============================================================
      public FBitmap() {
      }

      //============================================================
      // <T>构造位图图片。</T>
      //
      // @param native 位图
      //============================================================
      public FBitmap(Bitmap native) {
         _native = native;
      }

      //============================================================
      // <T>构造位图图片。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public FBitmap(string fileName) {
         LoadFile(fileName);
      }

      //============================================================
      // <T>构造位图图片。</T>
      //
      // @param width 宽度
      // @param height 高度
      //============================================================
      public FBitmap(int width, int height) {
         Create(width, height);
      }

      //============================================================
      // <T>构造位图图片。</T>
      //
      // @param width 宽度
      // @param height 高度
      // @param formatCd 格式
      //============================================================
      public FBitmap(int width, int height, PixelFormat formatCd) {
         Create(width, height, formatCd);
      }

      //============================================================
      // <T>获得和设置位图。</T>
      //============================================================
      public Bitmap Native {
         get { return _native; }
      }

      //============================================================
      // <T>获得位图宽度。</T>
      //============================================================
      public int Width {
         get { return _native.Width; }
      }

      //============================================================
      // <T>获得位图高度。</T>
      //============================================================
      public int Height {
         get { return _native.Height; }
      }

      //============================================================
      // <T>创建图片。</T>
      //
      // @param width 宽度
      // @param height 高度
      //============================================================
      public void Create(int width, int height) {
         Dispose();
         _native = new Bitmap(width, height, PixelFormat.Format32bppArgb);
      }

      //============================================================
      // <T>创建图片。</T>
      //
      // @param width 宽度
      // @param height 高度
      // @param formatCd 格式
      //============================================================
      public void Create(int width, int height, PixelFormat formatCd) {
         Dispose();
         _native = new Bitmap(width, height, formatCd);
      }

      //============================================================
      // <T>测试有效范围区域。</T>
      //
      // @param alpha 透明度
      // @return 有效范围区域
      //============================================================
      public SIntRectangle TestValidRectangle(int alpha) {
         SIntRectangle rect = new SIntRectangle();
         TestValidRectangle(rect, alpha);
         return rect;
      }

      //============================================================
      public void Assign(FBitmap bitmap) {
         Dispose();
         if(bitmap._native != null) {
            _native = (Bitmap)bitmap._native.Clone();
         }
      }
      
      //============================================================
      //public FGraphics GetGraphics() {
      //   return new FGraphics(Graphics.FromImage(_bitmap));
      //}
      
      //============================================================
      // <T>测试有效范围区域。</T>
      //
      // @param alpha 透明度
      // @return 有效范围区域
      //============================================================
      public bool TestValidRectangle(SIntRectangle rect, int alpha) {
         // 获得初始位置
         int width = _native.Width;
         int height = _native.Height;
         int left = width;
         int right = 0;
         int top = height;
         int bottom = 0;
         // 获得内存位置
         BitmapData data = _native.LockBits(new Rectangle(0, 0, width, height), ImageLockMode.ReadOnly, PixelFormat.Format32bppArgb);
         IntPtr pointer = new IntPtr(data.Scan0.ToInt64());
         // 处理所有行
         int[] buffer = new int[width];
         for(int y = 0; y < height; y++) {
            Marshal.Copy(pointer, buffer, 0, width);
            for(int x = 0; x < width; x++) {
               Color color = Color.FromArgb(buffer[x]);
               if(color.A > alpha) {
                  // 确定左边界
                  if(x < left) {
                     left = x;
                  }
                  // 确定右边界
                  if(x > right) {
                     right = x;
                  }
                  // 确定上边界
                  if(y < top) {
                     top = y;
                  }
                  // 确定下边界
                  if(y > bottom) {
                     bottom = y;
                  }
               }
            }
            pointer += data.Stride;
         }
         _native.UnlockBits(data);
         // 设置返回内容
         rect.Left = left;
         rect.Top = top;
         if((right < left) || (bottom < top)) {
            rect.Width = 0;
            rect.Height = 0;
            return false;
         }
         rect.Width = right - left + 1;
         rect.Height = bottom - top + 1;
         return true;
      }

      //============================================================
      public void Fill(Image image, int x, int y) {
         int width = _native.Width;
         int height = _native.Height;
         using(Graphics g = Graphics.FromImage(_native)) {
            g.DrawImage(image,
               new Rectangle(0, 0, width, height),
               new Rectangle(x, y, width, height),
               GraphicsUnit.Pixel);
         }
      }
      
      //============================================================
      // <T>指定位置复制图片<T>      
      //============================================================
      public void CopyFrom(Bitmap image, int x, int y) {
         // 取得图片的宽度
         int width = image.Width;
         // 内存锁定
         BitmapData data = image.LockBits(new Rectangle(x, y, _native.Width, _native.Height), ImageLockMode.ReadOnly, PixelFormat.Format32bppArgb);
         // 读出大小
         long dataColor = data.Scan0.ToInt64();
         BitmapData bitData = _native.LockBits(Rectangle.FromLTRB(0, 0, _native.Width, _native.Height), ImageLockMode.WriteOnly, PixelFormat.Format32bppArgb);
         long bufferData = bitData.Scan0.ToInt64();
         // 存储数组
         int[] buffer = new int[width];
         int[] bufferColor = new int[width];
         int height = _native.Height;
         for(int row = 0; row < height; row++) {
            Marshal.Copy(new IntPtr(dataColor), buffer, 0, _native.Width);
            dataColor += data.Stride;
            // 复制数组
            Array.Copy(buffer, bufferColor, buffer.Length);
            Marshal.Copy(bufferColor, 0, new IntPtr(bufferData), _native.Width);
            bufferData += bitData.Stride;
         }
         if(null != data) {
            image.UnlockBits(data);
         }
         if(null != bitData) {
            _native.UnlockBits(bitData);
         }
      }

      //============================================================
      public void SetTransparent(int alpha) {
         int cx = _native.Width;
         int cy = _native.Height;
         for(int y = 0; y < cy; y++) {
            for(int x = 0; x < cx; x++) {
               Color c = _native.GetPixel(x, y);
               _native.SetPixel(x, y, Color.FromArgb(alpha, c));
            }
         }
      }

      //============================================================
      public void SetTransparentOnly() {
         int cx = _native.Width;
         int cy = _native.Height;
         for(int y = 0; y < cy; y++) {
            for(int x = 0; x < cx; x++) {
               Color c = _native.GetPixel(x, y);
               _native.SetPixel(x, y, Color.FromArgb(255, c.A, c.A, c.A));
            }
         }
      }
      
      //============================================================
      // <T>改变大小。</T>
      //
      // @param width 宽度
      // @param height 高度
      //============================================================
      public void Resize(int width, int height) {
         if(null == _native) {
            _native = new Bitmap(width, height, PixelFormat.Format32bppArgb);
         } else if((width > _native.Width) || (height > _native.Height)) {
            // 释放图片
            _native.Dispose();
            // 重建大小图片
            _native = new Bitmap(width, height, PixelFormat.Format32bppArgb);
         }
      }

      //============================================================
      // <T>序列化位图数据。</T>
      //
      // @param output 输出流
      //============================================================
      public void SerializeData(IOutput output) {
         int width = _native.Width;
         int height = _native.Height;
         for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
               Color color = _native.GetPixel(x, y);
               output.WriteUint8(color.R);
               output.WriteUint8(color.G);
               output.WriteUint8(color.B);
               output.WriteUint8(color.A);
            }
         }
      }

      //============================================================
      // <T>序列化简单位图数据。</T>
      //
      // @param output 输出流
      //============================================================
      public void SerializeSimple(IOutput output) {
         int width = _native.Width;
         int height = _native.Height;
         // 存储尺寸
         output.WriteInt16((short)width);
         output.WriteInt16((short)height);
         // 存储颜色
         for(int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
               Color color = _native.GetPixel(x, y);
               output.WriteUint8(color.R);
               output.WriteUint8(color.G);
               output.WriteUint8(color.B);
               output.WriteUint8(color.A);
            }
         }
      }

      //============================================================
      // <T>序列化位图数据。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         int width = _native.Width;
         int height = _native.Height;
         output.WriteInt32(width);
         output.WriteInt32(height);
         BitmapData data = _native.LockBits(new Rectangle(0, 0, width, height), ImageLockMode.ReadOnly, PixelFormat.Format32bppArgb);
         long postion = data.Scan0.ToInt64();
         IntPtr pointer = new IntPtr(postion);
         int size = width * 4;
         byte[] buffer = new byte[size];
         for (int n = 0; n < height; n++) {
            Marshal.Copy(pointer, buffer, 0, size);
            output.WriteBytes(buffer, 0, buffer.Length);
            pointer += data.Stride;
         }
         _native.UnlockBits(data);
      }

      //============================================================
      // <T>序列化位图数据。</T>
      //
      // @param output 输出流
      //============================================================
      public void Unserialize(IInput input) {
         int width = input.ReadInt32();
         int height = input.ReadInt32();
         _native = new Bitmap(width, height, PixelFormat.Format32bppArgb);
         BitmapData data = _native.LockBits(new Rectangle(0, 0, width, height), ImageLockMode.WriteOnly, PixelFormat.Format32bppArgb);
         long postion = data.Scan0.ToInt64();
         IntPtr pointer = new IntPtr(postion);
         int size = width * 4;
         byte[] buffer = new byte[size];
         for(int n = 0; n < height; n++) {
            input.ReadBytes(buffer, 0, size);
            Marshal.Copy(pointer, buffer, 0, size);
            pointer += data.Stride;
         }
         _native.UnlockBits(data);
      }

      //============================================================
      // <T>加载图片文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public void LoadFile(string fileName) {
         Dispose();
         _native = new Bitmap(fileName);
      }

      //============================================================
      // <T>保存图片文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public void SaveFile(string fileName) {
         _native.Save(fileName);
      }

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public void Dispose() {
         if(null != _native) {
            _native.Dispose();
            _native = null;
         }
      }
   }
}
