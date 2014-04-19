using System;
using System.Collections.Generic;
using System.Drawing;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Core.Content.Picture;
using MO.Core.Content.Picture.Quantizers;

namespace MO.Core.Content.Drawing
{
   //============================================================
   // <T>索引位图图片。</T>
   //============================================================
   public class FIndexBitmap : FBitmap
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FIndexBitmap));

      // 是否支持透明度
      protected bool _optionAlpha;

      //============================================================
      // <T>构造位图图片。</T>
      //============================================================
      public FIndexBitmap() {
      }

      //============================================================
      // <T>构造位图图片。</T>
      //
      // @param native 位图
      //============================================================
      public FIndexBitmap(Bitmap native)
         : base(native) {
      }

      //============================================================
      // <T>构造位图图片。</T>
      //
      // @param width 宽度
      // @param height 高度
      //============================================================
      public FIndexBitmap(int width, int height)
         : base(width, height) {
      }

      //============================================================
      // <T>获得和设置支持透明度。</T>
      //============================================================
      public bool OptionAlpha {
         get { return _optionAlpha; }
         set { _optionAlpha = value; }
      }

      //============================================================
      // <T>序列化位图的一个区块。</T>
      //
      // @params output         输出流
      // @params colorQuantizer 颜色优化器
      // @params rect           序列化的矩形区域
      // @params colorCount     颜色数量
      //============================================================
      protected bool SerializeBlock(IOutput output, IColorQuantizer colorQuantizer, SIntRectangle rect, int colorCount) {
         int blockWidth = rect.Width;
         int blockHeight = rect.Height;
         output.WriteUint16((ushort)blockWidth);
         output.WriteUint16((ushort)blockHeight);
         // 构造调色板
         Color[] palette = null;
         if (_optionAlpha) {
            palette = colorQuantizer.MakePalette(colorCount).ToArray();
            if (0 == palette.Length) {
               RMoCore.TrackConsole.Write(this, "SerializeBlock", "Palette color is empty.");
               return false;
            }
         } else {
            List<Color> colorList = colorQuantizer.MakePalette(colorCount);
            if (0 == colorList.Count) {
               RMoCore.TrackConsole.Write(this, "SerializeBlock", "Palette color is empty.");
               return false;
            }
            colorList.Add(Color.Transparent);
            palette = colorList.ToArray();
         }
         // 输出调色板
         int paletteCount = palette.Length;
         output.WriteUint16((ushort)paletteCount);
         foreach (Color color in palette) {
            if (_optionAlpha) {
               output.WriteInt32(color.ToArgb() & 0x00FFFFFF);
            } else {
               output.WriteInt32(color.ToArgb());
            }
         }
         // _logger.Debug(this, "SerializeIndexed", "block_size={0}x{1}, color={2}, alpha={3}", blockWidth, blockHeight, paletteCount, _optionAlpha);
         // 输出颜色数组
         int x = rect.Left;
         int y = rect.Top;
         // 透明色索引
         int transparentIndex = palette.Length - 1;
         int size = blockWidth * blockHeight;
         // 写入数组
         int postion = 0;
         byte[] bytes = null;
         if (_optionAlpha) {
            // 写入索引颜色和透明度
            bytes = new byte[size * 2];
            for (int h = 0; h < blockHeight; h++) {
               for (int w = 0; w < blockWidth; w++) {
                  Color color = _native.GetPixel(x + w, y + h);
                  int index = colorQuantizer.FindPaletteIndex(Color.FromArgb(255, color));
                  bytes[postion++] = (byte)index;
                  bytes[postion++] = color.A;
               }
            }
         } else {
            // 写入带透明的索引颜色
            bytes = new byte[size];
            for (int h = 0; h < blockHeight; h++) {
               for (int w = 0; w < blockWidth; w++) {
                  Color color = _native.GetPixel(x + w, y + h);
                  int index = colorQuantizer.FindPaletteIndex(color);
                  bytes[postion++] = (byte)index;
               }
            }
         }
         output.WriteBytes(bytes, 0, postion);
         return true;
      }

      //============================================================
      // <T>序列化位图数据。</T>
      //
      // @params output     输出流
      // @params rect       范围
      // @params colorCount 调色板颜色数量
      // @params pixelCount 分块的像素个数
      //============================================================
      public bool SerializeIndexed(IOutput output, SIntRectangle rect, int colorCount, int pixelCount) {
         // 若不支持半透明，去掉一个颜色，添加一个透明色
         if (!_optionAlpha) {
            colorCount--;
         }
         // 计算分割信息
         int width = rect.Width;
         int height = rect.Height;
         int totalPixel = width * height;
         int splitWidth = width;
         int splitCount = totalPixel / pixelCount;
         int splitHeight = height;
         if (0 == splitCount) {
            splitHeight = height;
            splitCount = 1;
         } else {
            splitHeight = height / splitCount;
            splitCount = height / splitHeight;
            if (0 != (height % splitHeight)) {
               splitCount++;
            }
         }
         // 写入头信息
         ushort option = 0;
         if (_optionAlpha) {
            option |= (ushort)EBitmapOption.Alpha;
         }
         output.WriteUint16(option);
         output.WriteUint16((ushort)width);
         output.WriteUint16((ushort)height);
         output.WriteUint16((ushort)splitCount);
         // 要序列化的源矩形区域
         SIntRectangle subrect = new SIntRectangle();
         subrect.Left = rect.Left;
         subrect.Top = rect.Top;
         subrect.Width = splitWidth;
         subrect.Height = splitHeight;
         // 源矩形数据
         FByteStream bs = new FByteStream();
         for (int n = 0; n < splitCount; n++) {
            bs.Clear();
            subrect.Bottom = Math.Min(subrect.Top + splitHeight, rect.Top + height);
            //FPictureQuantizer pictureQuantizer = new FPictureQuantizer(EColorQuantizer.Octree);
            FPictureQuantizer pictureQuantizer = new FPictureQuantizer(EColorQuantizer.Octree16);
            //IColorQuantizer colorQuantizer = pictureQuantizer.LoadQuantizerColors(_native, subrect, FPictureQuantizer.EQuantizedMode.Rgb);
            IColorQuantizer colorQuantizer = pictureQuantizer.LoadQuantizerColors(_native, subrect, FPictureQuantizer.EQuantizedMode.Color);
            if (SerializeBlock(bs, colorQuantizer, subrect, colorCount)) {
               output.WriteUint32((uint)bs.Length);
               output.WriteBytes(bs.Memory, 0, bs.Length);
               // _logger.Debug(this, "SerializeIndexed", "index={0}/{1}, rect={2}, size={3}", n, splitCount, rect.ToString(), bs.Length);
               subrect.Top += splitHeight;
            } else {
               RMoCore.TrackConsole.Write(this, "SerializeIndexed", "Picture is all transparent.");
               return false;
            }
         }
         return true;
      }

      //============================================================
      // <T>序列化位图数据。</T>
      //
      // @params output     输出流
      // @params colorCount 调色板颜色数量
      // @params pixelCount 分块的像素个数
      //============================================================
      public bool SerializeIndexed(IOutput output, int colorCount, int pixelCount) {
         // 若不支持半透明，去掉一个颜色，添加一个透明色
         SIntRectangle rect = new SIntRectangle();
         rect.Left = 0;
         rect.Top = 0;
         rect.Width = _native.Width;
         rect.Height = _native.Height;
         // 计算分割信息
         int width = rect.Width;
         int height = rect.Height;
         int totalPixel = width * height;
         int splitWidth = width;
         int splitCount = totalPixel / pixelCount;
         int splitHeight = height;
         if (0 == splitCount) {
            splitHeight = height;
            splitCount = 1;
         } else {
            splitHeight = height / splitCount;
            splitCount = height / splitHeight;
            if (0 != (height % splitHeight)) {
               splitCount++;
            }
         }
         // 写入头信息
         ushort option = 0;
         if (_optionAlpha) {
            option |= (ushort)EBitmapOption.Alpha;
         }
         output.WriteUint16(option);
         output.WriteUint16((ushort)width);
         output.WriteUint16((ushort)height);
         output.WriteUint16((ushort)splitCount);
         // 要序列化的源矩形区域
         SIntRectangle subrect = new SIntRectangle();
         subrect.Left = rect.Left;
         subrect.Top = rect.Top;
         subrect.Width = splitWidth;
         subrect.Height = splitHeight;
         // 源矩形数据
         FByteStream bs = new FByteStream();
         for (int n = 0; n < splitCount; n++) {
            bs.Clear();
            subrect.Bottom = Math.Min(subrect.Top + splitHeight, rect.Top + height);
            // 创建图片优化器
            FPictureQuantizer pictureQuantizer = null;
            IColorQuantizer colorQuantizer = null;
            if (_optionAlpha) {
               pictureQuantizer = new FPictureQuantizer(EColorQuantizer.Octree);
               colorQuantizer = pictureQuantizer.LoadQuantizerColors(_native, subrect, FPictureQuantizer.EQuantizedMode.Rgb);
            } else {
               pictureQuantizer = new FPictureQuantizer(EColorQuantizer.Octree16);
               colorQuantizer = pictureQuantizer.LoadQuantizerColors(_native, subrect, FPictureQuantizer.EQuantizedMode.Color);
            }
            // 存储图片优化数据
            if (SerializeBlock(bs, colorQuantizer, subrect, colorCount)) {
               output.WriteUint32((uint)bs.Length);
               output.WriteBytes(bs.Memory, 0, bs.Length);
               // _logger.Debug(this, "SerializeIndexed", "index={0}/{1}, rect={2}, size={3}", n, splitCount, rect.ToString(), bs.Length);
               subrect.Top += splitHeight;
            } else {
               RMoCore.TrackConsole.Write(this, "SerializeIndexed", "Picture is all transparent.");
               return false;
            }
         }
         return true;
      }

      //============================================================
      // <T>序列化位图的一个区块。</T>
      //
      // @params output         输出流
      // @params colorQuantizer 颜色优化器
      // @params rect           序列化的矩形区域
      // @params colorCount     颜色数量
      //============================================================
      protected bool SerializeUnpackBlock(IOutput output, IColorQuantizer colorQuantizer, SIntRectangle rect, int colorCount) {
         int blockWidth = rect.Width;
         int blockHeight = rect.Height;
         output.WriteUint16((ushort)blockWidth);
         output.WriteUint16((ushort)blockHeight);
         // 构造调色板
         Color[] palette = null;
         if (_optionAlpha) {
            palette = colorQuantizer.MakePalette(colorCount).ToArray();
            if (0 == palette.Length) {
               RMoCore.TrackConsole.Write(this, "SerializeBlock", "Palette color is empty.");
               return false;
            }
         } else {
            List<Color> colorList = colorQuantizer.MakePalette(colorCount);
            if (0 == colorList.Count) {
               RMoCore.TrackConsole.Write(this, "SerializeBlock", "Palette color is empty.");
               return false;
            }
            colorList.Add(Color.Transparent);
            palette = colorList.ToArray();
         }
         // 输出颜色数组
         int x = rect.Left;
         int y = rect.Top;
         // 写入数组
         if (_optionAlpha) {
            // 写入索引颜色和透明度
            for (int h = 0; h < blockHeight; h++) {
               for (int w = 0; w < blockWidth; w++) {
                  Color color = _native.GetPixel(x + w, y + h);
                  int index = colorQuantizer.FindPaletteIndex(Color.FromArgb(255, color));
                  Color c = Color.FromArgb(color.A, palette[index]);
                  output.WriteInt32(c.ToArgb());
               }
            }
         } else {
            // 写入带透明的索引颜色
            for (int h = 0; h < blockHeight; h++) {
               for (int w = 0; w < blockWidth; w++) {
                  Color color = _native.GetPixel(x + w, y + h);
                  int index = colorQuantizer.FindPaletteIndex(color);
                  output.WriteInt32(palette[index].ToArgb());
               }
            }
         }
         return true;
      }

      //============================================================
      // <T>序列化位图的一个区块。</T>
      //
      // @params output         输出流
      // @params colorQuantizer 颜色优化器
      // @params rect           序列化的矩形区域
      // @params colorCount     颜色数量
      //============================================================
      protected bool SerializeUnpackBlock3d(IOutput output, IColorQuantizer colorQuantizer, SIntRectangle rect, int colorCount) {
         int blockWidth = rect.Width;
         int blockHeight = rect.Height;
         // 构造调色板
         Color[] palette = null;
         if (_optionAlpha) {
            palette = colorQuantizer.MakePalette(colorCount).ToArray();
            if (0 == palette.Length) {
               RMoCore.TrackConsole.Write(this, "SerializeBlock", "Palette color is empty.");
               return false;
            }
         } else {
            List<Color> colorList = colorQuantizer.MakePalette(colorCount);
            if (0 == colorList.Count) {
               RMoCore.TrackConsole.Write(this, "SerializeBlock", "Palette color is empty.");
               return false;
            }
            colorList.Add(Color.Transparent);
            palette = colorList.ToArray();
         }
         // 输出调色板
         int paletteCount = palette.Length;
         output.WriteUint16((ushort)paletteCount);
         foreach (Color color in palette) {
            if (_optionAlpha) {
               output.WriteInt32(color.ToArgb() & 0x00FFFFFF);
            } else {
               output.WriteInt32(color.ToArgb());
            }
         }
         // _logger.Debug(this, "SerializeIndexed", "block_size={0}x{1}, color={2}, alpha={3}", blockWidth, blockHeight, paletteCount, _optionAlpha);
         // 输出颜色数组
         int x = rect.Left;
         int y = rect.Top;
         // 透明色索引
         int transparentIndex = palette.Length - 1;
         int size = blockWidth * blockHeight;
         // 写入数组
         int postion = 0;
         byte[] bytes = null;
         if (_optionAlpha) {
            // 写入索引颜色和透明度
            bytes = new byte[size * 2];
            for (int h = 0; h < blockHeight; h++) {
               for (int w = 0; w < blockWidth; w++) {
                  Color color = _native.GetPixel(x + w, y + h);
                  int index = colorQuantizer.FindPaletteIndex(Color.FromArgb(255, color));
                  bytes[postion++] = (byte)index;
                  bytes[postion++] = color.A;
               }
            }
         } else {
            // 写入带透明的索引颜色
            bytes = new byte[size];
            for (int h = 0; h < blockHeight; h++) {
               for (int w = 0; w < blockWidth; w++) {
                  Color color = _native.GetPixel(x + w, y + h);
                  int index = colorQuantizer.FindPaletteIndex(color);
                  bytes[postion++] = (byte)index;
               }
            }
         }
         output.WriteBytes(bytes, 0, postion);
         return true;
      }

      //============================================================
      // <T>序列化位图数据。</T>
      //
      // @params output     输出流
      // @params colorCount 调色板颜色数量
      // @params pixelCount 分块的像素个数
      //============================================================
      public bool SerializeUnpackIndexed(IOutput output, int colorCount, int pixelCount) {
         // 若不支持半透明，去掉一个颜色，添加一个透明色
         SIntRectangle rect = new SIntRectangle();
         rect.Left = 0;
         rect.Top = 0;
         rect.Width = _native.Width;
         rect.Height = _native.Height;
         // 计算分割信息
         int width = rect.Width;
         int height = rect.Height;
         int totalPixel = width * height;
         int splitWidth = width;
         int splitCount = totalPixel / pixelCount;
         int splitHeight = height;
         if (0 == splitCount) {
            splitHeight = height;
            splitCount = 1;
         } else {
            splitHeight = height / splitCount;
            splitCount = height / splitHeight;
            if (0 != (height % splitHeight)) {
               splitCount++;
            }
         }
         // 写入头信息
         ushort option = 0;
         if (_optionAlpha) {
            option |= (ushort)EBitmapOption.Alpha;
         }
         output.WriteUint16(option);
         output.WriteUint16((ushort)width);
         output.WriteUint16((ushort)height);
         output.WriteUint16((ushort)splitCount);
         // 要序列化的源矩形区域
         SIntRectangle subrect = new SIntRectangle();
         subrect.Left = rect.Left;
         subrect.Top = rect.Top;
         subrect.Width = splitWidth;
         subrect.Height = splitHeight;
         // 源矩形数据
         FByteStream bs = new FByteStream();
         for (int n = 0; n < splitCount; n++) {
            bs.Clear();
            subrect.Bottom = Math.Min(subrect.Top + splitHeight, rect.Top + height);
            // 创建图片优化器
            FPictureQuantizer pictureQuantizer = null;
            IColorQuantizer colorQuantizer = null;
            if (_optionAlpha) {
               pictureQuantizer = new FPictureQuantizer(EColorQuantizer.Octree);
               colorQuantizer = pictureQuantizer.LoadQuantizerColors(_native, subrect, FPictureQuantizer.EQuantizedMode.Rgb);
            } else {
               pictureQuantizer = new FPictureQuantizer(EColorQuantizer.Octree16);
               colorQuantizer = pictureQuantizer.LoadQuantizerColors(_native, subrect, FPictureQuantizer.EQuantizedMode.Color);
            }
            // 存储图片优化数据
            if (SerializeUnpackBlock(bs, colorQuantizer, subrect, colorCount)) {
               output.WriteUint32((uint)bs.Length);
               output.WriteBytes(bs.Memory, 0, bs.Length);
               // _logger.Debug(this, "SerializeIndexed", "index={0}/{1}, rect={2}, size={3}", n, splitCount, rect.ToString(), bs.Length);
               subrect.Top += splitHeight;
            } else {
               RMoCore.TrackConsole.Write(this, "SerializeIndexed", "Picture is all transparent.");
               return false;
            }
         }
         return true;
      }

      //============================================================
      // <T>序列化位图数据。</T>
      //
      // @params output     输出流
      // @params colorCount 调色板颜色数量
      // @params pixelCount 分块的像素个数
      //============================================================
      public bool SerializeIndexed3d(IOutput output, SIntRectangle rect, int colorCount) {
         // 写入设置
         output.WriteUint8(_optionAlpha ? (byte)1 : (byte)0);
         // 写入属性
         output.WriteUint16((ushort)Width);
         output.WriteUint16((ushort)Height);
         output.WriteUint16((ushort)rect.Left);
         output.WriteUint16((ushort)rect.Top);
         output.WriteUint16((ushort)rect.Width);
         output.WriteUint16((ushort)rect.Height);
         // 若不支持半透明，去掉一个颜色，添加一个透明色
         if (!_optionAlpha) {
            colorCount--;
         }
         // 写入头信息
         FPictureQuantizer pictureQuantizer = null;
         IColorQuantizer colorQuantizer = null;
         if (_optionAlpha) {
            pictureQuantizer = new FPictureQuantizer(EColorQuantizer.Octree);
            colorQuantizer = pictureQuantizer.LoadQuantizerColors(_native, rect, FPictureQuantizer.EQuantizedMode.Rgb);
         } else {
            pictureQuantizer = new FPictureQuantizer(EColorQuantizer.Octree16);
            colorQuantizer = pictureQuantizer.LoadQuantizerColors(_native, rect, FPictureQuantizer.EQuantizedMode.Color);
         }
         SerializeUnpackBlock3d(output, colorQuantizer, rect, colorCount);
         return true;
      }
   }
}