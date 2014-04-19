using System;
using System.Drawing;
using System.Drawing.Imaging;
using System.IO;
using System.Runtime.InteropServices;
using MO.Common.IO;
using MO.Core.Content.Drawing;
using MoCompress;

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
   public class FRsBitmapTexture : FRsBitmap
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
      public FRsBitmapTexture() {
         _typeName = ERsBitmap.Texture;
      }

      //============================================================
      // <T>构造位图。</T>
      //
      // @param bitmap 位图
      //============================================================
      public FRsBitmapTexture(Bitmap bitmap) {
         _typeName = ERsBitmap.Texture;
         _bitmap = bitmap;
      }

      //============================================================
      // <T>构造位图。</T>
      //
      // @param width 宽度
      // @param height 高度
      //============================================================
      public FRsBitmapTexture(int width, int height) {
         _typeName = ERsBitmap.Texture;
         _bitmap = new Bitmap(width, height, PixelFormat.Format32bppArgb);
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
         base.Serialize(output);
         // 写入宽度和高度
         int width = _bitmap.Width;
         int height = _bitmap.Height;
         output.WriteInt16((short)width);
         output.WriteInt16((short)height);
         // 检测是否有Aplah值
         bool channelA = RBitmap.HasChannel(_bitmap, EBitmapChannel.A);
         if (channelA) {
            output.WriteInt8(2);
         } else {
            output.WriteInt8(1);
         }
         // 保存图片信息
         byte[] data = RBitmap.ToBytes(_bitmap);
         byte[] dataResult = new byte[data.Length * 2];
         int length = RCompressJpg.Compress(dataResult, 0, dataResult.Length, data, 0, data.Length, _bitmap.Width, _bitmap.Height, _qualityColor, 1);
         output.WriteInt32(length);
         output.WriteBytes(dataResult, 0, length);
         // 保存图片A信息
         if (channelA) {
            length = RCompressJpg.Compress(dataResult, 0, dataResult.Length, data, 0, data.Length, _bitmap.Width, _bitmap.Height, _qualityAlpha, 2);
            output.WriteInt32(length);
            output.WriteBytes(dataResult, 0, length);
         }
      }

      //============================================================
      // <T>序列化简单位图数据。</T>
      //
      // @param output 输出流
      //============================================================
      public void SerializeSimple(IOutput output) {
         int width = _bitmap.Width;
         int height = _bitmap.Height;
         // 存储尺寸
         output.WriteInt16((short)width);
         output.WriteInt16((short)height);
         // 存储颜色
         for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
               Color color = _bitmap.GetPixel(x, y);
               output.WriteUint8(color.R);
               output.WriteUint8(color.G);
               output.WriteUint8(color.B);
               output.WriteUint8(color.A);
            }
         }
      }
      
      //============================================================
      // <T>序列化数据内容到输出流中。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize2(IOutput output) {
         base.Serialize(output);
         // 写入宽度和高度
         int width = _bitmap.Width;
         int height = _bitmap.Height;
         output.WriteInt16((short)width);
         output.WriteInt16((short)height);
         // 获得编码器
         ImageCodecInfo jpgCodec = RBitmap.FindImageCodecInfo(EBitmap.Jpg);
         // 检测是否有Aplah值
         bool channelA = RBitmap.HasChannel(_bitmap, EBitmapChannel.A);
         if (channelA) {
            output.WriteInt8(2);
         } else {
            output.WriteInt8(1);
         }
         // 保存图片RGB信息
         using(Bitmap bitmapRgb = new Bitmap(width, height, PixelFormat.Format32bppRgb)){
            EncoderParameters parameters = new EncoderParameters(1);
            parameters.Param[0] = new EncoderParameter(System.Drawing.Imaging.Encoder.Quality, (long)_qualityColor);
            RBitmap.CopyChannels(_bitmap, bitmapRgb, EBitmapChannels.RGB);
            using(MemoryStream buffer = new MemoryStream()){
               bitmapRgb.Save(buffer, jpgCodec, parameters);
               byte[] data = buffer.ToArray();
               output.WriteInt32(data.Length);
               output.WriteBytes(data, 0, data.Length);
            }
         }
         // 保存图片A信息
         if (channelA) {
            using(Bitmap bitmapA = new Bitmap(width, height, PixelFormat.Format32bppRgb)){
               EncoderParameters parameters = new EncoderParameters(1);
               parameters.Param[0] = new EncoderParameter(System.Drawing.Imaging.Encoder.Quality, (long)_qualityAlpha);
               RBitmap.CopyChannel(_bitmap, EBitmapChannel.A, bitmapA, EBitmapChannels.RGB, _validAlpha);
               using(MemoryStream buffer = new MemoryStream()){
                  bitmapA.Save(buffer, jpgCodec, parameters);
                  byte[] data = buffer.ToArray();
                  output.WriteInt32(data.Length);
                  output.WriteBytes(data, 0, data.Length);
               }
            }
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
