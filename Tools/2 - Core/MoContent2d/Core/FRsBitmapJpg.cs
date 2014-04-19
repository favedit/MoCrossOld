using MO.Common.Geom;
using MO.Common.IO;
using MO.Content2d.Common;
using MO.Core.Content.Drawing;
using MO.Core.IO;
using MoCompress;
using System;
using System.Drawing;
using System.Drawing.Imaging;
using System.IO;
using System.Runtime.InteropServices;

namespace MO.Content2d.Core
{
   //============================================================
   // <T>JPG位图。</T>
   // <P>
   //    Size(Width=short, height=short)
   //    Pad (Left=short, Top=short, Right=short, Bottom=short)
   //    Channels
   //       Count = short
   //       Channel (JPG Encoder)
   //          Flags = byte [rgba]
   //          Data = (length=int, bytes)
   // </P>
   //============================================================
   public class FRsBitmapJpg : FRsBitmap
   {
      // 数据
      protected FByteStream _data = new FByteStream();

      // 质量
      protected int _qualityColor = 90;

      // 质量
      protected int _qualityAlpha = 90;

      //============================================================
      // <T>构造位图。</T>
      //============================================================
      public FRsBitmapJpg() {
         _typeName = ERsBitmap.Jpg;
      }

      //============================================================
      // <T>构造位图。</T>
      //
      // @param bitmap 位图
      //============================================================
      public FRsBitmapJpg(Bitmap bitmap) {
         _typeName = ERsBitmap.Jpg;
         _bitmap = bitmap;
         _size.Set(_bitmap.Width, _bitmap.Height);
      }

      //============================================================
      // <T>构造位图。</T>
      //
      // @param width 宽度
      // @param height 高度
      //============================================================
      public FRsBitmapJpg(int width, int height) {
         _typeName = ERsBitmap.Jpg;
         _bitmap = new Bitmap(width, height, PixelFormat.Format32bppArgb);
         _size.Set(_bitmap.Width, _bitmap.Height);
      }

      //============================================================
      // <T>加载位图。</T>
      //============================================================
      public Bitmap Bitmap {
         get { return _bitmap; }
      }

      //============================================================
      // <T>获得数据。</T>
      //============================================================
      public FByteStream Data {
         get { return _data; }
      }

      //============================================================
      // <T>获得或设置颜色质量。</T>
      //============================================================
      public int QualityColor {
         get { return _qualityColor; }
         set { _qualityColor = value; }
      }

      //============================================================
      // <T>获得或设置透明质量。</T>
      //============================================================
      public int QualityAlpha {
         get { return _qualityAlpha; }
         set { _qualityAlpha = value; }
      }

      //============================================================
      // <T>序列化数据内容到输出流中。</T>
      //
      // @param output 输出流
      //============================================================
      public override void Serialize(IOutput output) {
         // 处理位图
         Bitmap bitmap = _bitmap;
         if ((_validLocation.X != 0) || (_validLocation.Y != 0) || (_validSize.Width != _bitmap.Width) || ((_validSize.Height != _bitmap.Height))) {
            bitmap = new Bitmap(_validSize.Width, _validSize.Height, PixelFormat.Format32bppArgb);
            // 复制图片
            RBitmap.Copy(_bitmap, new SIntRectangle(_validLocation.X, _validLocation.Y, _validSize.Width, _validSize.Height), bitmap, new SIntPoint2(0, 0));
         }
         // 写入宽度和高度
         int width = _bitmap.Width;
         int height = _bitmap.Height;
         output.WriteInt16((short)width);
         output.WriteInt16((short)height);
         // 写入有效区域
         output.WriteInt16((short)_validLocation.X);
         output.WriteInt16((short)_validLocation.Y);
         output.WriteInt16((short)_validSize.Width);
         output.WriteInt16((short)_validSize.Height);
         // 检测是否有Aplah值
         bool channelA = RBitmap.HasChannel(bitmap, EBitmapChannel.A);
         if (channelA) {
            output.WriteInt8(2);
         } else {
            output.WriteInt8(1);
         }
         // 保存图片信息
         byte[] data = RBitmap.ToBytes(bitmap);
         //if(RResourceManager.IsColoPremultiplied) {
         //   int colorLength = data.Length;
         //   for (int n = 0; n < colorLength; n += 4) {
         //      float a = (float)data[n + 3];
         //      data[n    ] = (byte)(((float)data[n    ] * a) / 255.0f);
         //      data[n + 1] = (byte)(((float)data[n + 1] * a) / 255.0f);
         //      data[n + 2] = (byte)(((float)data[n + 2] * a) / 255.0f);
         //   }
         //}else if(RResourceManager.IsColoSkipProcess) {
         //   int skipAlpha = RResourceManager.ColoSkipAlpha;
         //   int colorLength = data.Length;
         //   for(int n = 0; n < colorLength; n += 4) {
         //      if(data[n + 3] < skipAlpha) {
         //         data[n] = 0;
         //         data[n + 1] = 0;
         //         data[n + 2] = 0;
         //         data[n + 3] = 0;
         //      }
         //   }
         //}
         byte[] dataResult = new byte[4096 + data.Length * 2];
         int length = RCompressJpg.Compress(dataResult, 0, dataResult.Length, data, 0, data.Length, bitmap.Width, bitmap.Height, _qualityColor, 1);
         output.WriteInt32(length);
         output.WriteBytes(dataResult, 0, length);
         // 保存图片A信息
         if (channelA) {
            length = RCompressJpg.Compress(dataResult, 0, dataResult.Length, data, 0, data.Length, bitmap.Width, bitmap.Height, _qualityAlpha, 3);
            output.WriteInt32(length);
            output.WriteBytes(dataResult, 0, length);
         }
         // 释放图片
         if (bitmap != _bitmap) {
            bitmap.Dispose();
            bitmap = null;
         }
      }

      //============================================================
      // <T>序列化数据内容到输出流中。</T>
      //
      // @param output 输出流
      //============================================================
      public void SerializeBitmap(IOutput output) {
         // 处理位图
         Bitmap bitmap = _bitmap;
         if((_validLocation.X != 0) || (_validLocation.Y != 0) || (_validSize.Width != _bitmap.Width) || ((_validSize.Height != _bitmap.Height))) {
            bitmap = new Bitmap(_validSize.Width, _validSize.Height, PixelFormat.Format32bppArgb);
            // 复制图片
            RBitmap.Copy(_bitmap, new SIntRectangle(_validLocation.X, _validLocation.Y, _validSize.Width, _validSize.Height), bitmap, new SIntPoint2(0, 0));
         }
         // 保存图片信息
         byte[] data = RBitmap.ToBytes(bitmap);
         //if(RResourceManager.IsColoPremultiplied) {
         //   int colorLength = data.Length;
         //   for(int n = 0; n < colorLength; n += 4) {
         //      float a = (float)data[n + 3];
         //      data[n] = (byte)(((float)data[n] * a) / 255.0f);
         //      data[n + 1] = (byte)(((float)data[n + 1] * a) / 255.0f);
         //      data[n + 2] = (byte)(((float)data[n + 2] * a) / 255.0f);
         //   }
         //} else if(RResourceManager.IsColoSkipProcess) {
         //   int skipAlpha = RResourceManager.ColoSkipAlpha;
         //   int colorLength = data.Length;
         //   for(int n = 0; n < colorLength; n += 4) {
         //      if(data[n + 3] < skipAlpha) {
         //         data[n] = 0;
         //         data[n + 1] = 0;
         //         data[n + 2] = 0;
         //         data[n + 3] = 0;
         //      }
         //   }
         //}
         byte[] dataResult = new byte[4096 + data.Length * 2];
         int length = RCompressJpg.Compress(dataResult, 0, dataResult.Length, data, 0, data.Length, bitmap.Width, bitmap.Height, _qualityColor, 1);
         output.WriteBytes(dataResult, 0, length);
         // 释放图片
         if(bitmap != _bitmap) {
            bitmap.Dispose();
            bitmap = null;
         }
      }

      //============================================================
      // <T>序列化数据内容到输出流中。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize2(IOutput output) {
         // 处理位图
         Bitmap bitmap = _bitmap;
         if ((_validLocation.X != 0) || (_validLocation.Y != 0) || (_validSize.Width != _bitmap.Width) || ((_validSize.Height != _bitmap.Height))) {
            bitmap = new Bitmap(_validSize.Width, _validSize.Height, PixelFormat.Format32bppArgb);
            // 复制图片
            RBitmap.Copy(_bitmap, new SIntRectangle(_validLocation.X, _validLocation.Y, _validSize.Width, _validSize.Height), bitmap, new SIntPoint2(0, 0));
         }
         // 写入宽度和高度
         int width = _bitmap.Width;
         int height = _bitmap.Height;
         output.WriteInt16((short)width);
         output.WriteInt16((short)height);
         // 写入有效区域
         output.WriteInt16((short)_validLocation.X);
         output.WriteInt16((short)_validLocation.Y);
         output.WriteInt16((short)_validSize.Width);
         output.WriteInt16((short)_validSize.Height);
         // 获得编码器
         ImageCodecInfo jpgCodec = RBitmap.FindImageCodecInfo(EBitmap.Jpg);
         // 检测是否有Aplah值
         bool channelA = RBitmap.HasChannel(bitmap, EBitmapChannel.A);
         if (channelA) {
            output.WriteInt8(2);
         } else {
            output.WriteInt8(1);
         }
         // 保存图片RGB信息
         using (Bitmap bitmapRgb = new Bitmap(_validSize.Width, _validSize.Height, PixelFormat.Format32bppRgb)) {
            EncoderParameters parameters = new EncoderParameters(1);
            parameters.Param[0] = new EncoderParameter(System.Drawing.Imaging.Encoder.Quality, (long)_qualityColor);
            RBitmap.CopyChannels(bitmap, bitmapRgb, EBitmapChannels.RGB);
            using (MemoryStream buffer = new MemoryStream()) {
               bitmapRgb.Save(buffer, jpgCodec, parameters);
               byte[] data = buffer.ToArray();
               output.WriteInt32(data.Length);
               output.WriteBytes(data, 0, data.Length);
            }
         }
         // 保存图片A信息
         if (channelA) {
            using (Bitmap bitmapA = new Bitmap(_validSize.Width, _validSize.Height, PixelFormat.Format32bppRgb)) {
               EncoderParameters parameters = new EncoderParameters(1);
               parameters.Param[0] = new EncoderParameter(System.Drawing.Imaging.Encoder.Quality, (long)_qualityAlpha);
               RBitmap.CopyChannel(bitmap, EBitmapChannel.A, bitmapA, EBitmapChannels.RGB, _validAlpha);
               using (MemoryStream buffer = new MemoryStream()) {
                  bitmapA.Save(buffer, jpgCodec, parameters);
                  byte[] data = buffer.ToArray();
                  output.WriteInt32(data.Length);
                  output.WriteBytes(data, 0, data.Length);
               }
            }
         }
         // 释放图片
         if (bitmap != _bitmap) {
            bitmap.Dispose();
            bitmap = null;
         }
      }

      //============================================================
      // <T>从输入流中反序列化数据内容。</T>
      //
      // @param input 输入流
      //============================================================
      public override void Unserialize(IInput input) {
         int width = input.ReadInt32();
         int height = input.ReadInt32();
         _bitmap = new Bitmap(width, height, PixelFormat.Format32bppArgb);
         BitmapData data = _bitmap.LockBits(new Rectangle(0, 0, width, height), ImageLockMode.WriteOnly, PixelFormat.Format32bppArgb);
         long postion = data.Scan0.ToInt64();
         IntPtr pointer = new IntPtr(postion);
         int size = width * 4;
         byte[] buffer = new byte[size];
         for (int n = 0; n < height; n++) {
            input.ReadBytes(buffer, 0, size);
            Marshal.Copy(pointer, buffer, 0, size);
            pointer += data.Stride;
         }
         _bitmap.UnlockBits(data);
      }

      //============================================================
      // <T>序列化位图数据。</T>
      //
      // @params output     输出流
      // @params colorCount 调色板颜色数量
      // @params pixelCount 分块的像素个数
      //============================================================
      public bool Compress(IOutput output, int compressCd) {
         // 保存数据
         using(FByteStream stream = new FByteStream()) {
            Serialize(stream);
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

      //============================================================
      // <T>数据打包。</T>
      //============================================================
      public void Pack() {
         _data.Clear();
         Serialize(_data);
      }

      //============================================================
      // <T>数据解包。</T>
      //================ ============================================
      public void Unpack() {
         Serialize(_data);
      }

      //============================================================
      // <T>导出数据文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public void ExportFile(string fileName) {
         FCompressFile file = new FCompressFile();
         Serialize(file);
         file.SaveFile(fileName);
      }

      //============================================================
      // <T>构造位图。</T>
      //============================================================
      public override void Dispose() {
         base.Dispose();
         // 释放数据
         _data.Dispose();
      }
   }
}
