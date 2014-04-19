using System;
using System.Drawing;
using System.Drawing.Imaging;
using System.Runtime.InteropServices;
using MO.Common.Geom;

namespace MO.Core.Content.Drawing
{
   //============================================================
   // <T>位图工具类。<T>      
   //============================================================
   public class RBitmap
   {
      //============================================================
      // <T>获得指定名称的编码器信息。<T>
      //
      // @param type 类型名称
      // @return 编码器信息
      //============================================================
      public static ImageCodecInfo FindImageCodecInfo(string typeCd) {
         foreach (ImageCodecInfo info in ImageCodecInfo.GetImageEncoders()) {
            if (info.MimeType.Equals(typeCd)) {
               return info;
            }
         }
         return null;
      }

      //============================================================
      // <T>检测指定位图是否含有某个通道数据。<T>
      //
      // @param bitmap 位图
      // @param channels 通道
      // @param value 通道
      //============================================================
      public static bool HasChannel(Bitmap bitmap, int channel, int value = 255) {
         return HasChannel(bitmap, new SIntRectangle(0, 0, bitmap.Width, bitmap.Height), channel, value);
      }

      //============================================================
      // <T>检测指定位图是否含有某个通道数据。<T>
      //
      // @param bitmap 位图
      // @param rectangle 矩形范围
      // @param channels 通道
      // @param value 通道
      //============================================================
      public static bool HasChannel(Bitmap bitmap, SIntRectangle rectangle, int channel, int value = 255) {
         // 取得图片的宽度
         int width = rectangle.Width;
         int height = rectangle.Height;
         int size = width * 4;
         // 内存锁定
         BitmapData data = bitmap.LockBits(new Rectangle(0, 0, width, height), ImageLockMode.ReadOnly, PixelFormat.Format32bppArgb);
         long dataPtr = data.Scan0.ToInt64();
         // 存储数组
         byte[] buffer = new byte[size];
         for (int row = 0; row < height; row++) {
            // 读取源信息
            Marshal.Copy(new IntPtr(dataPtr), buffer, 0, size);
            for (int n = 0; n < size; n += 4) {
               if (buffer[n + channel] != value) {
                  bitmap.UnlockBits(data);
                  return true;
               }
            }
            dataPtr += data.Stride;
         }
         bitmap.UnlockBits(data);
         return false;
      }

      //============================================================
      // <T>测试有效范围区域。</T>
      //
      // @param bitmap 位图
      // @param rect 有效范围区域
      // @param alpha 透明度
      //============================================================
      public static bool TestValidRectangle(Bitmap bitmap, SIntRectangle rect, int alpha = 0) {
         // 获得初始位置
         int width = bitmap.Width;
         int height = bitmap.Height;
         int left = width;
         int right = 0;
         int top = height;
         int bottom = 0;
         // 获得内存位置
         BitmapData data = bitmap.LockBits(new Rectangle(0, 0, width, height), ImageLockMode.ReadOnly, PixelFormat.Format32bppArgb);
         IntPtr pointer = new IntPtr(data.Scan0.ToInt64());
         // 处理所有行
         int[] buffer = new int[width];
         for (int y = 0; y < height; y++) {
            Marshal.Copy(pointer, buffer, 0, width);
            for (int x = 0; x < width; x++) {
               Color color = Color.FromArgb(buffer[x]);
               if (color.A > alpha) {
                  // 确定左边界
                  if (x < left) {
                     left = x;
                  }
                  // 确定右边界
                  if (x > right) {
                     right = x;
                  }
                  // 确定上边界
                  if (y < top) {
                     top = y;
                  }
                  // 确定下边界
                  if (y > bottom) {
                     bottom = y;
                  }
               }
            }
            pointer += data.Stride;
         }
         bitmap.UnlockBits(data);
         // 设置返回内容
         rect.Left = left;
         rect.Top = top;
         if ((right < left) || (bottom < top)) {
            rect.Width = 0;
            rect.Height = 0;
            return false;
         }
         rect.Width = right - left + 1;
         rect.Height = bottom - top + 1;
         return true;
      }

      //============================================================
      // <T>指定复制位图的指定通道到目标位图。<T>      
      //
      // @param source 来源位图
      // @param target 目标位图
      // @param channels 通道集合
      //============================================================
      public static void CopyChannel(Bitmap source, int sourceChannel, Bitmap target, int targetChannels, int validValue = 0) {
         CopyChannel(source, sourceChannel, target, targetChannels, new SIntRectangle(0, 0, source.Width, source.Height), validValue);
      }

      //============================================================
      // <T>指定复制位图的指定通道到目标位图。<T>      
      //
      // @param source 来源位图
      // @param target 目标位图
      // @param rectangle 矩形范围
      // @param channels 通道集合
      //============================================================
      public static void CopyChannel(Bitmap source, int sourceChannel, Bitmap target, int targetChannels, SIntRectangle rectangle, int validValue = 0) {
         // 获得通道
         bool channelsA = (EBitmapChannels.A == (targetChannels & EBitmapChannels.A));
         bool channelsR = (EBitmapChannels.R == (targetChannels & EBitmapChannels.R));
         bool channelsG = (EBitmapChannels.G == (targetChannels & EBitmapChannels.G));
         bool channelsB = (EBitmapChannels.B == (targetChannels & EBitmapChannels.B));
         // 取得图片的宽度
         int width = rectangle.Width;
         int height = rectangle.Height;
         int size = width << 2;
         // 获得来源内存
         BitmapData sourceData = source.LockBits(new Rectangle(rectangle.Left, rectangle.Top, width, height), ImageLockMode.ReadOnly, PixelFormat.Format32bppArgb);
         long sourcePtr = sourceData.Scan0.ToInt64();
         byte[] sourceBuffer = new byte[size];
         // 获得目标内存
         BitmapData targetData = target.LockBits(new Rectangle(rectangle.Left, rectangle.Top, width, height), ImageLockMode.ReadWrite, PixelFormat.Format32bppArgb);
         long targetPtr = targetData.Scan0.ToInt64();
         byte[] targetBuffer = new byte[size];
         // 复制数据
         for (int row = 0; row < height; row++) {
            // 读取源信息
            Marshal.Copy(new IntPtr(sourcePtr), sourceBuffer, 0, size);
            sourcePtr += sourceData.Stride;
            // 读取目标源信息
            Marshal.Copy(new IntPtr(targetPtr), targetBuffer, 0, size);
            // 复制通道数据
            for (int n = 0; n < size; n += 4) {
               byte value = sourceBuffer[n + sourceChannel];
               if (value < validValue) {
                  value = 0;
               }
               if (channelsB) {
                  targetBuffer[n + 0] = value;
               }
               if (channelsG) {
                  targetBuffer[n + 1] = value;
               }
               if (channelsR) {
                  targetBuffer[n + 2] = value;
               }
               if (channelsA) {
                  targetBuffer[n + 3] = value;
               }
            }
            Marshal.Copy(targetBuffer, 0, new IntPtr(targetPtr), size);
            targetPtr += targetData.Stride;
         }
         // 释放资源
         source.UnlockBits(sourceData);
         target.UnlockBits(targetData);
      }
      
      //============================================================
      // <T>指定复制位图的指定通道到目标位图。<T>      
      //
      // @param source 来源位图
      // @param target 目标位图
      // @param channels 通道集合
      //============================================================
      public static void CopyChannels(Bitmap source, Bitmap target, int channels) {
         CopyChannels(source, target, new SIntRectangle(0, 0, source.Width, source.Height), channels);
      }

      //============================================================
      // <T>指定复制位图的指定通道到目标位图。<T>      
      //
      // @param source 来源位图
      // @param target 目标位图
      // @param rectangle 矩形范围
      // @param channels 通道集合
      //============================================================
      public static void CopyChannels(Bitmap source, Bitmap target, SIntRectangle rectangle, int channels) {
         // 获得通道
         bool channelsA = (EBitmapChannels.A == (channels & EBitmapChannels.A));
         bool channelsR = (EBitmapChannels.R == (channels & EBitmapChannels.R));
         bool channelsG = (EBitmapChannels.G == (channels & EBitmapChannels.G));
         bool channelsB = (EBitmapChannels.B == (channels & EBitmapChannels.B));
         // 取得图片的宽度
         int width = rectangle.Width;
         int height = rectangle.Height;
         int size = width << 2;
         // 获得来源内存
         BitmapData sourceData = source.LockBits(new Rectangle(rectangle.Left, rectangle.Top, width, height), ImageLockMode.ReadOnly, PixelFormat.Format32bppArgb);
         long sourcePtr = sourceData.Scan0.ToInt64();
         byte[] sourceBuffer = new byte[size];
         // 获得目标内存
         BitmapData targetData = target.LockBits(new Rectangle(rectangle.Left, rectangle.Top, width, height), ImageLockMode.ReadWrite, PixelFormat.Format32bppArgb);
         long targetPtr = targetData.Scan0.ToInt64();
         byte[] targetBuffer = new byte[size];
         // 复制数据
         for (int row = 0; row < height; row++) {
            // 读取源信息
            Marshal.Copy(new IntPtr(sourcePtr), sourceBuffer, 0, size);
            sourcePtr += sourceData.Stride;
            // 读取目标源信息
            Marshal.Copy(new IntPtr(targetPtr), targetBuffer, 0, size);
            // 复制通道数据
            for (int n = 0; n < size; n += 4) {
               if (channelsB) {
                  targetBuffer[n + 0] = sourceBuffer[n + 0];
               }
               if (channelsG) {
                  targetBuffer[n + 1] = sourceBuffer[n + 1];
               }
               if (channelsR) {
                  targetBuffer[n + 2] = sourceBuffer[n + 2];
               }
               if (channelsA) {
                  targetBuffer[n + 3] = sourceBuffer[n + 3];
               }
            }
            Marshal.Copy(targetBuffer, 0, new IntPtr(targetPtr), size);
            targetPtr += targetData.Stride;
         }
         // 释放资源
         source.UnlockBits(sourceData);
         target.UnlockBits(targetData);
      }

      //============================================================
      // <T>指定复制位图的指定通道到目标位图。<T>      
      //
      // @param source 来源位图
      // @param sourceRectangle 来源矩形
      // @param target 目标位图
      // @param targetPosition 目标位置
      //============================================================
      public static void Copy(Bitmap source, SIntRectangle sourceRectangle, Bitmap target, SIntPoint2 targetPosition) {
         int lineSize = sourceRectangle.Width;
         int height = sourceRectangle.Height;
         int[] buffer = new int[lineSize];
         // 获得来源内存
         BitmapData sourceData = source.LockBits(new Rectangle(sourceRectangle.Left, sourceRectangle.Top, sourceRectangle.Width, sourceRectangle.Height), ImageLockMode.ReadOnly, PixelFormat.Format32bppArgb);
         long sourcePtr = sourceData.Scan0.ToInt64();
         // 获得目标内存
         BitmapData targetData = target.LockBits(new Rectangle(targetPosition.X, targetPosition.Y, sourceRectangle.Width, sourceRectangle.Height), ImageLockMode.ReadWrite, PixelFormat.Format32bppArgb);
         long targetPtr = targetData.Scan0.ToInt64();
         // 复制数据
         for (int row = 0; row < height; row++) {
            // 读取源信息
            Marshal.Copy(new IntPtr(sourcePtr), buffer, 0, lineSize);
            sourcePtr += sourceData.Stride;
            // 读取目标源信息
            Marshal.Copy(buffer, 0, new IntPtr(targetPtr), lineSize);
            targetPtr += targetData.Stride;
         }
         // 释放资源
         source.UnlockBits(sourceData);
         target.UnlockBits(targetData);
      }

      //============================================================
      // <T>清除位图为指定颜色。<T>
      //
      // @param bitmap 位图
      // @param value 颜色
      //============================================================
      public static void Clear(Bitmap bitmap, int value = -1) {
         Clear(bitmap, new SIntRectangle(0, 0, bitmap.Width, bitmap.Height), value);
      }

      //============================================================
      // <T>获得图片一部分字节。<T>
      //
      // @param bitmap 位图
      // @param rectangle 矩形范围
      // @param value 颜色
      //============================================================
      public static byte[] ToBytes(Bitmap bitmap, SIntRectangle rectangle = null) {
         // 取得矩形范围
         Rectangle rect;
         if (null != rectangle) {
            rect = new Rectangle(rectangle.Left, rectangle.Top, rectangle.Width, rectangle.Height);
         } else {
            rect = new Rectangle(0, 0, bitmap.Width, bitmap.Height);
         }
         int width = rect.Width;
         int height = rect.Height;
         int size = width * height * 4;
         byte[] data = new byte[size];
         // 内存锁定
         BitmapData bitmapData = bitmap.LockBits(rect, ImageLockMode.ReadOnly, PixelFormat.Format32bppArgb);
         long dataPtr = bitmapData.Scan0.ToInt64();
         // 存储数组
         int stride = width * 4;
         byte[] buffer = new byte[stride];
         for(int row = 0; row < height; row++){
            // 读取源信息
            Marshal.Copy(new IntPtr(dataPtr), buffer, 0, stride);
            Array.Copy(buffer, 0, data, width * row * 4, stride);
            dataPtr += bitmapData.Stride;
         }
         bitmap.UnlockBits(bitmapData);
         // 返回数据
         return data;
      }
      
      //============================================================
      // <T>清除位图为指定颜色。<T>
      //
      // @param bitmap 位图
      // @param rectangle 矩形范围
      // @param value 颜色
      //============================================================
      public static void Clear(Bitmap bitmap, SIntRectangle rectangle, int value = -1) {
         // 取得图片的宽度
         int width = rectangle.Width;
         int height = rectangle.Height;
         // 内存锁定
         BitmapData data = bitmap.LockBits(new Rectangle(0, 0, width, height), ImageLockMode.WriteOnly, PixelFormat.Format32bppArgb);
         long dataPtr = data.Scan0.ToInt64();
         // 存储数组
         int[] buffer = new int[width];
         for (int n = 0; n < width; n++) {
            buffer[n] = value;
         }
         for (int row = 0; row < height; row++) {
            // 读取源信息
            Marshal.Copy(buffer, 0, new IntPtr(dataPtr), width);
            dataPtr += data.Stride;
         }
         bitmap.UnlockBits(data);
      }
   }
}
