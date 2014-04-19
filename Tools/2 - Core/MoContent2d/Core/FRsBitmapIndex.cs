using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Common;
using MO.Core;
using MO.Core.Content.Picture;
using MO.Core.Content.Picture.Quantizers;
using MO.Core.IO;
using System.Collections.Generic;
using System.Drawing;

namespace MO.Content2d.Core
{
   //============================================================
   // <T>索引位图。</T>
   //============================================================
   public class FRsBitmapIndex : FRsBitmap
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FRsBitmapIndex));

      //============================================================
      // <T>构造索引位图。</T>
      //============================================================
      public FRsBitmapIndex() {
         _typeName = ERsBitmap.Index;
      }

      //============================================================
      // <T>构造位图图片。</T>
      //
      // @param native 位图
      //============================================================
      public FRsBitmapIndex(Bitmap native)
         : base(native) {
      }

      //============================================================
      // <T>构造位图图片。</T>
      //
      // @param width 宽度
      // @param height 高度
      //============================================================
      public FRsBitmapIndex(int width, int height)
         : base(width, height) {
      }

      //============================================================
      // <T>序列化位图的一个区块。</T>
      //
      // @params output         输出流
      // @params colorQuantizer 颜色优化器
      // @params rect           序列化的矩形区域
      // @params colorCount     颜色数量
      //============================================================
      protected bool SerializeUnpack(IOutput output, IColorQuantizer colorQuantizer, SIntRectangle rect, int colorCount) {
         FByteStream stream = new FByteStream();
         int blockWidth = rect.Width;
         int blockHeight = rect.Height;
         // 输入属性
         stream.WriteBool(_optionAlpha);
         stream.WriteInt16((short)blockWidth);
         stream.WriteInt16((short)blockHeight);
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
               colorList = colorQuantizer.MakePalette(colorCount);
               return false;
            }
            palette = colorList.ToArray();
         }
         // 输出调色板
         int paletteCount = palette.Length;
         stream.WriteUint16((ushort)paletteCount);
         //if(RResourceManager.IsColoPremultiplied) {
         //   foreach (Color color in palette) {
         //      if (_optionAlpha) {
         //         stream.WriteInt32(color.ToArgb() & 0x00FFFFFF);
         //      } else {
         //         byte a = color.A;
         //         byte r = (byte)(((float)color.R * (float)a) / 255.0f);
         //         byte g = (byte)(((float)color.G * (float)a) / 255.0f);
         //         byte b = (byte)(((float)color.B * (float)a) / 255.0f);
         //         stream.WriteInt32(Color.FromArgb(a, r, g, b).ToArgb());
         //      }
         //   }
         //} else if(RResourceManager.IsColoSkipProcess) {
         //   int skipAlpha = RResourceManager.ColoSkipAlpha;
         //   foreach(Color color in palette) {
         //      if(_optionAlpha) {
         //         stream.WriteInt32(color.ToArgb() & 0x00FFFFFF);
         //      } else {
         //         if(color.A < skipAlpha) {
         //            stream.WriteInt32(0);
         //         } else {
         //            stream.WriteInt32(color.ToArgb());
         //         }
         //      }
         //   }
         //} else {
         //   foreach(Color color in palette) {
         //      if(_optionAlpha) {
         //         stream.WriteInt32(color.ToArgb() & 0x00FFFFFF);
         //      } else {
         //         stream.WriteInt32(color.ToArgb());
         //      }
         //   }
         //}
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
                  Color color = _bitmap.GetPixel(x + w, y + h);
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
                  Color color = _bitmap.GetPixel(x + w, y + h);
                  int index = colorQuantizer.FindPaletteIndex(color);
                  bytes[postion++] = (byte)index;
               }
            }
         }
         stream.WriteBytes(bytes, 0, postion);
         // 写入数据
         output.WriteInt32(stream.Length);
         output.WriteBytes(stream.Memory, 0, stream.Length);
         return true;
      }

      //============================================================
      // <T>序列化位图数据。</T>
      //
      // @params output     输出流
      // @params colorCount 调色板颜色数量
      // @params pixelCount 分块的像素个数
      //============================================================
      public bool Serialize(IOutput output, SIntRectangle rect, int colorCount) {
         // 写入设置
         output.WriteBool(_optionAlpha);
         // 写入属性
         output.WriteUint16((ushort)_size.Width);
         output.WriteUint16((ushort)_size.Height);
         output.WriteUint16((ushort)rect.Left);
         output.WriteUint16((ushort)rect.Top);
         output.WriteUint16((ushort)rect.Width);
         output.WriteUint16((ushort)rect.Height);
         // 若不支持半透明，去掉一个颜色，添加一个透明色
         //if (!_optionAlpha) {
         //   colorCount--;
         //}
         // 写入头信息
         FPictureQuantizer pictureQuantizer = null;
         IColorQuantizer colorQuantizer = null;
         if (_optionAlpha) {
            pictureQuantizer = new FPictureQuantizer(EColorQuantizer.Octree);
            colorQuantizer = pictureQuantizer.LoadQuantizerColors(_bitmap, rect, FPictureQuantizer.EQuantizedMode.Rgb);
         } else {
            pictureQuantizer = new FPictureQuantizer(EColorQuantizer.Octree16);
            colorQuantizer = pictureQuantizer.LoadQuantizerColors(_bitmap, rect, FPictureQuantizer.EQuantizedMode.Color);
         }
         using (FByteStream stream = new FByteStream()) {
            SerializeUnpack(stream, colorQuantizer, rect, colorCount);
            output.WriteInt32(stream.Length);
            output.WriteBytes(stream.Memory, 0, stream.Length);
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
      public bool Compress(IOutput output, int compressCd, SIntRectangle rect, int colorCount) {
         // 保存数据
         using(FByteStream stream = new FByteStream()) {
            Serialize(stream, rect, colorCount);
            // 压缩数据
            byte[] data = null;
            if(compressCd == ERsCompress.NoneValue) {
               data = stream.ToArray();
            } else if(compressCd == ERsCompress.DeflateValue) {
               //using(FRsCompressFile compress = new FRsCompressFile(ERsCompress.Deflate, stream, RResourceManager.CompressBlockSplit)) {
               using(FCompressFile compress = new FCompressFile(stream)) {
                  data = compress.Compress();
               }
               if(data.Length > stream.Length) {
                  compressCd = ERsCompress.NoneValue;
                  data = stream.ToArray();
               }
            } else if(compressCd == ERsCompress.LzmaValue) {
               using(FLzmaFile compress = new FLzmaFile(stream)) {
                  data = compress.CompressNative();
               }
               if(data.Length > stream.Length) {
                  compressCd = ERsCompress.NoneValue;
                  data = stream.ToArray();
               }
            }
            output.WriteInt32(compressCd);
            output.WriteInt32(data.Length);
            output.WriteBytes(data, 0, data.Length);
         }
         return true;
      }
   }
}