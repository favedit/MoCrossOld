using MO.Common.Geom;
using MO.Common.IO;
using MO.Content2d.Common;
using MO.Core;
using MO.Core.Content.Picture;
using MO.Core.Content.Picture.Quantizers;
using MO.Core.IO;
using System;
using System.Collections.Generic;
using System.Drawing;

namespace MO.Content2d.Core
{
   //============================================================
   // <T>分块索引位图图片。</T>
   //============================================================
   public class FRsBitmapBlockIndex : FRsBitmap
   {
      //============================================================
      // <T>构造分块索引位图图片。</T>
      //============================================================
      public FRsBitmapBlockIndex(Bitmap bitmap)
         : base(bitmap) {
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
         FByteStream stream = new FByteStream();
         int blockWidth = rect.Width;
         int blockHeight = rect.Height;
         // 写入设置
         stream.WriteBool(_optionAlpha);
         stream.WriteUint16((ushort)blockWidth);
         stream.WriteUint16((ushort)blockHeight);
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
            colorList.Add(Color.FromArgb(0, 0, 0, 0));
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
      public bool Serialize(IOutput output, int colorCount, int pixelCount) {
         // 计算分割信息
         int width = _validSize.Width;
         int height = _validSize.Height;
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
         output.WriteBool(_optionAlpha);
         output.WriteUint16((ushort)_size.Width);
         output.WriteUint16((ushort)_size.Height);
         output.WriteUint16((ushort)_validLocation.X);
         output.WriteUint16((ushort)_validLocation.Y);
         output.WriteUint16((ushort)_validSize.Width);
         output.WriteUint16((ushort)_validSize.Height);
         output.WriteUint16((ushort)splitCount);
         // 若不支持半透明，去掉一个颜色，添加一个透明色
         //if (!_optionAlpha) {
         //   colorCount--;
         //}
         // 要序列化的源矩形区域
         SIntRectangle subrect = new SIntRectangle();
         subrect.Left = _validLocation.X;
         subrect.Top = _validLocation.Y;
         subrect.Width = splitWidth;
         subrect.Height = splitHeight;
         // 源矩形数据
         FByteStream data = new FByteStream();
         for (int n = 0; n < splitCount; n++) {
            subrect.Bottom = Math.Min(subrect.Top + splitHeight, _validLocation.Y + height);
            // 创建图片优化器
            FPictureQuantizer pictureQuantizer = null;
            IColorQuantizer colorQuantizer = null;
            if (_optionAlpha) {
               pictureQuantizer = new FPictureQuantizer(EColorQuantizer.Octree);
               colorQuantizer = pictureQuantizer.LoadQuantizerColors(_bitmap, subrect, FPictureQuantizer.EQuantizedMode.Rgb);
            } else {
               pictureQuantizer = new FPictureQuantizer(EColorQuantizer.Octree16);
               colorQuantizer = pictureQuantizer.LoadQuantizerColors(_bitmap, subrect, FPictureQuantizer.EQuantizedMode.Color);
            }
            // 存储图片优化数据
            if (SerializeBlock(data, colorQuantizer, subrect, colorCount)) {
               // _logger.Debug(this, "SerializeIndexed", "index={0}/{1}, rect={2}, size={3}", n, splitCount, rect.ToString(), bs.Length);
               subrect.Top += splitHeight;
            } else {
               RMoCore.TrackConsole.Write(this, "Serialize", "Picture is all transparent.");
               return false;
            }
         }
         output.WriteInt32(data.Length);
         output.WriteBytes(data.Memory, 0, data.Length);
         return true;
      }

      //============================================================
      // <T>序列化位图数据。</T>
      //
      // @params output     输出流
      // @params colorCount 调色板颜色数量
      // @params pixelCount 分块的像素个数
      //============================================================
      public bool Compress(IOutput output, int compressCd, int colorCount, int pixelCount) {
         // 保存数据
         using(FByteStream stream = new FByteStream()) {
            Serialize(stream, colorCount, pixelCount);
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
