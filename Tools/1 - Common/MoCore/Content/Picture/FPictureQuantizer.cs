using System;
using System.Drawing;
using System.Drawing.Imaging;
using System.Runtime.InteropServices;
using MO.Common.Geom;
using MO.Common.Lang;
using MO.Core.Content.Picture.Quantizers;
using MO.Core.Content.Picture.Quantizers.HSB;
using MO.Core.Content.Picture.Quantizers.Median;
using MO.Core.Content.Picture.Quantizers.Octree;
using MO.Core.Content.Picture.Quantizers.Octree16;
using MO.Core.Content.Picture.Quantizers.Popularity;
using MO.Core.Content.Picture.Quantizers.Uniform;

namespace MO.Core.Content.Picture
{
   /// ============================================================
   /// <summary>图形优化器。</summary>
   /// ============================================================
   public class FPictureQuantizer : FObject
   {
      private static ILogger _logger = RLogger.Find(typeof(FPictureQuantizer));

      public enum EQuantizedMode : int
      {
         Color,
         Rgb,
         Alpha
      }

      protected Bitmap _imageSource;

      protected Bitmap _imageTarget;

      protected int _colorCount = 256;

      protected EColorQuantizer _colorQuantizer;

      protected IColorQuantizer _quantizer;

      protected IColorQuantizer[] _quantizers = new IColorQuantizer[] {
         new FPaletteQuantizer(),
         new FMedianCutQuantizer(),
         new FOctreeQuantizer(),
         new FOctreeQuantizer16(),
         new FPopularityQuantizer(),
         new FUniformQuantizer(),
      };

      /// ============================================================
      /// <summary>生成图形优化器。</summary>
      /// <param name="count">颜色数目</param>
      /// <returns>颜色列表</returns>
      /// ============================================================
      public FPictureQuantizer() {
         _quantizer = _quantizers[(int)EColorQuantizer.Octree];
      }

      /// ============================================================
      /// <summary>生成图形优化器。</summary>
      /// <param name="quantizerCd">优化器类型</param>
      /// ============================================================
      public FPictureQuantizer(EColorQuantizer quantizerCd) {
         ColorQuantizer = quantizerCd;
      }

      /// ============================================================
      /// <summary>生成图形优化器。</summary>
      /// <param name="imageSource">源图片</param>
      /// ============================================================
      public FPictureQuantizer(Bitmap imageSource) {
         _quantizer = _quantizers[(int)EColorQuantizer.Octree];
         _imageSource = imageSource;
      }

      //============================================================
      public Bitmap ImageSource {
         get { return _imageSource; }
         set { _imageSource = value; }
      }

      //============================================================
      public Bitmap ImageTarget {
         get { return _imageTarget; }
      }

      //============================================================
      public int ColorCount {
         get { return _colorCount; }
         set { _colorCount = value; }
      }

      //============================================================
      public EColorQuantizer ColorQuantizer {
         get { return _colorQuantizer; }
         set {
            _colorQuantizer = value;
            _quantizer = _quantizers[(int)_colorQuantizer];
         }
      }

      //============================================================
      public IColorQuantizer Quantizer {
         get { return _quantizer; }
      }

      /// ============================================================
      /// <summary>加载图形的优化器颜色。</summary>
      /// <param name="bitmap">图形对象</param>
      /// <param name="modeCd">颜色模式</param>
      /// <returns>颜色优化器</returns>
      /// ============================================================
      public IColorQuantizer LoadQuantizerColors(Bitmap bitmap, EQuantizedMode modeCd) {
         // 检查参数错误
         if (null == bitmap) {
            throw new ArgumentNullException("Cannot quantize a null bitmap.");
         }
         // 锁定原图像，获得所有颜色
         int width = bitmap.Width;
         int height = bitmap.Height;
         Rectangle bounds = Rectangle.FromLTRB(0, 0, width, height);
         BitmapData data = bitmap.LockBits(bounds, ImageLockMode.ReadOnly, PixelFormat.Format32bppArgb);
         try {
            // 读取所有颜色
            int[] buffer = new int[width];
            long offset = data.Scan0.ToInt64();
            for (int row = 0; row < height; row++) {
               // 复制一行颜色到缓冲中
               Marshal.Copy(new IntPtr(offset), buffer, 0, width);
               // 增加到优化器中
               for (int n = 0; n < width; n++) {
                  Color color = Color.FromArgb(buffer[n]);
                  switch (modeCd) {
                     case EQuantizedMode.Color:
                        break;
                     case EQuantizedMode.Rgb:
                        color = Color.FromArgb(255, color);
                        break;
                     case EQuantizedMode.Alpha:
                        color = Color.FromArgb(255, color.A, color.A, color.A);
                        break;
                     default:
                        throw new FFatalException("Unknown type");
                  }
                  _quantizer.AddColor(color);
               }
               // 修正行边界
               offset += data.Stride;
            }
         } finally {
            // 释放图像对象
            bitmap.UnlockBits(data);
         }
         return _quantizer;
      }

      /// ============================================================
      /// <summary>加载图形的优化器颜色。</summary>
      /// <param name="bitmap">图形对象</param>
      /// <param name="modeCd">颜色模式</param>
      /// <returns>颜色优化器</returns>
      /// ============================================================
      public IColorQuantizer LoadQuantizerColors(Bitmap bitmap, SIntRectangle rect, EQuantizedMode modeCd) {
         // 检查参数错误
         if (null == bitmap) {
            throw new ArgumentNullException("Cannot quantize a null bitmap.");
         }
         // 锁定原图像，获得所有颜色
         int width = rect.Width;
         int height = rect.Height;
         Rectangle bounds = new Rectangle(rect.Left, rect.Top, width, height);
         BitmapData data = bitmap.LockBits(bounds, ImageLockMode.ReadOnly, PixelFormat.Format32bppArgb);
         try {
            // 读取所有颜色
            int[] buffer = new int[width];
            long offset = data.Scan0.ToInt64();
            for (int row = 0; row < height; row++) {
               // 复制一行颜色到缓冲中
               Marshal.Copy(new IntPtr(offset), buffer, 0, width);
               // 增加到优化器中
               for (int n = 0; n < width; n++) {
                  uint color = (uint)buffer[n];
                  switch (modeCd) {
                     case EQuantizedMode.Color:
                        break;
                     case EQuantizedMode.Rgb:
                        color |= 0xFF000000;
                        break;
                     case EQuantizedMode.Alpha:
                        color &= 0xFF000000;
                        break;
                     default:
                        throw new FFatalException("Unknown type");
                  }
                  _quantizer.AddColor(Color.FromArgb((int)color));
               }
               // 修正行边界
               offset += data.Stride;
            }
         } finally {
            // 释放图像对象
            bitmap.UnlockBits(data);
         }
         return _quantizer;
      }

      /// ============================================================
      /// <summary>生成指定颜色数目的图形。</summary>
      /// <param name="colorCount">颜色数目</param>
      /// <returns>图形</returns>
      /// ============================================================
      public Bitmap BuildQuantizedImage(int colorCount) {
         // 生成调色板
         Color[] palette = _quantizer.MakePalette(colorCount).ToArray();
         // 创建输出图像
         int width = _imageSource.Width;
         int height = _imageSource.Height;
         Bitmap result = new Bitmap(width, height, PixelFormat.Format32bppArgb);
         // 绘制输出图形颜色
         Rectangle bounds = Rectangle.FromLTRB(0, 0, width, height);
         BitmapData sourceData = _imageSource.LockBits(bounds, ImageLockMode.ReadOnly, PixelFormat.Format32bppArgb);
         BitmapData targetData = result.LockBits(bounds, ImageLockMode.WriteOnly, PixelFormat.Format32bppArgb);
         try {
            // 初始化读取和写入缓冲
            int[] sourceBuffer = new int[width];
            int[] targetBuffer = new int[width];
            long sourceOffset = sourceData.Scan0.ToInt64();
            long targetOffset = targetData.Scan0.ToInt64();
            // 处理所有原图像颜色
            for (int row = 0; row < height; row++) {
               // 读取一行
               Marshal.Copy(new IntPtr(sourceOffset), sourceBuffer, 0, width);
               // 通过调色板转换颜色
               for (Int32 index = 0; index < width; index++) {
                  Color color = Color.FromArgb(sourceBuffer[index]);
                  int paletteIndex = _quantizer.FindPaletteIndex(color);
                  targetBuffer[index] = palette[paletteIndex].ToArgb();
               }
               // 写入一行
               Marshal.Copy(targetBuffer, 0, new IntPtr(targetOffset), width);
               // 修正行边界
               sourceOffset += sourceData.Stride;
               targetOffset += targetData.Stride;
            }
         } finally {
            // 释放图像对象
            _imageSource.UnlockBits(sourceData);
            result.UnlockBits(targetData);
         }
         // 返回结果
         return result;
      }

      //============================================================
      public Bitmap Process() {
         // 检查参数错误
         if (null == _imageSource) {
            throw new ArgumentNullException("Cannot quantize a null image.");
         }
         // 加载优化颜色
         LoadQuantizerColors(_imageSource, EQuantizedMode.Color);
         _imageTarget = BuildQuantizedImage(_colorCount);
         // 返回结果
         return _imageTarget;
      }
   }
}
