using System.Drawing;
using System.Drawing.Imaging;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Core.Content.Drawing;
using MO.Core.Content.Picture.Quantizers;
using MO.Core.IO;

namespace MO.Core.Content.Picture
{
   //============================================================
   // <T>游戏资源图片。</T>
   //
   // Size：图片尺寸 (4Byte : Width, 4Byte : Height)
   // SpliteSize：分割尺寸 (4Byte : Width, 4Byte : Height)
   // Palette : 调色板
   // - 1Byte :  Color Length - 颜色数量
   // - Colors : 调色 (4Byte*Width*Height : Colors - 颜色信息)
   // Alpha : 透明度
   // - 1Byte :  Color Length - 颜色数量
   // - 0.5Byte*Width*Height : Alphas - 透明信息
   //============================================================
   public class FCompressPicture : FObject
   {
      private static ILogger _logger = RLogger.Find(typeof(FCompressPicture));

      public const string EXTEND_NAME = "mrp";

      protected Bitmap _bitmap;

      protected EColorQuantizer _quantizerCd = EColorQuantizer.Octree;

      //============================================================
      public Bitmap Bitmap {
         get { return _bitmap; }
         set { _bitmap = value; }
      }

     //============================================================
      public EColorQuantizer QuantizerCd {
         get { return _quantizerCd; }
         set { _quantizerCd = value; }
      }

      //============================================================
      // <T>加载图片文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public void LoadBitmap(string fileName) {
         _bitmap = new Bitmap(fileName);
      }

      //============================================================
      // <T>保存图片文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public void SaveBitmap(string fileName) {
         _bitmap.Save(fileName);
      }

      //============================================================
      // <T>回复索引颜色。</T>
      //
      // @param input 输入流
      // @param bitmap 位图对象
      // @param x 左坐标
      // @param y 右坐标
      // @param width 宽度
      // @param height 高度
      //============================================================
      protected void RestoreIndexedColor(IInput input, Bitmap bitmap, int x, int y, int width, int height) {
         // 读取调色板
         int colorCount = input.ReadUint8();
         Color[] palette = new Color[colorCount];
         for (int n = 0; n < colorCount; n++) {
            palette[n] = Color.FromArgb(input.ReadInt32());
         }
         // 读取颜色数组
         for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
               int index = input.ReadUint8();
               Color color = palette[index];
               bitmap.SetPixel(x + w, y + h, color);
            }
         }
         // 回复透明色
         int alphaCount = input.ReadUint8();
         int[] alphaPalette = new int[alphaCount];
         for (int n = 0; n < alphaCount; n++) {
            alphaPalette[n] = input.ReadUint8();
         }
         for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w += 2) {
               int alpha = input.ReadUint8();
               // 设置高位透明度
               int ah = alphaPalette[(alpha >> 4) & 0x0F];
               Color ch = bitmap.GetPixel(w, h);
               bitmap.SetPixel(x + w, y + h, Color.FromArgb(ah, ch));
               // 设置低位透明度
               int al = alphaPalette[alpha & 0x0F];
               Color cl = bitmap.GetPixel(w + 1, h);
               bitmap.SetPixel(x + w + 1, y + h, Color.FromArgb(al, cl));
            }
         }
         _logger.Debug(this, "RestoreIndexedColor", "Restore indexed color. (position={0}-{1}, color_count={2})",
            x, y, colorCount);
      }

      //============================================================
      // <T>存储压缩文件。</T>
      //
      // @param fileName 文件名称
      // @param widthCount 横向分割个数
      // @param hightCount 纵向分割个数
      //============================================================
      protected Bitmap RestoreCompress(IInput input) {
         // 获得图片尺寸
         int width = input.ReadUint16();
         int height = input.ReadUint16();
         if (null != _bitmap) {
            _bitmap.Dispose();
         }
         Bitmap bitmap = new Bitmap(width, height, PixelFormat.Format32bppArgb);
         // 获得分割尺寸
         int splitWidth = input.ReadUint16();
         int splitHeight = input.ReadUint16();
         int widthCount = width / splitWidth;
         int hightCount = height / splitHeight;
         // 分割图片
         for (int y = 0; y < hightCount; y++) {
            for (int x = 0; x < widthCount; x++) {
               // 存储索引图片
               RestoreIndexedColor(input, bitmap, splitWidth * x, splitHeight * y, splitWidth, splitHeight);
            }
         }
         return bitmap;
      }

      //============================================================
      // <T>存储索引颜色。</T>
      //
      // @param output 输出流
      // @param quantizer 优化器
      // @param bitmap 位图对象
      //============================================================
      protected void StoreIndexedColor(IOutput output, Bitmap bitmap, int colorCount, int cx, int cy) {
         // 优化图形
         FPictureQuantizer pictureQuantizer = new FPictureQuantizer(_quantizerCd);
         IColorQuantizer colorQuantizer = pictureQuantizer.LoadQuantizerColors(bitmap, FPictureQuantizer.EQuantizedMode.Rgb);
         int colorTotal = colorQuantizer.GetColorCount();
         // 构造调色板
         Color[] palette = colorQuantizer.MakePalette(colorCount).ToArray();
         // 输出调色板
         int paletteCount = palette.Length;
         output.WriteUint8((byte)paletteCount);
         foreach (Color color in palette) {
            output.WriteInt32(color.ToArgb() & 0x00FFFFFF);
         }
         // 输出颜色数组
         int width = bitmap.Width;
         int height = bitmap.Height;
         for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
               Color color = bitmap.GetPixel(w, h);
               int index = colorQuantizer.FindPaletteIndex(Color.FromArgb(255, color));
               output.WriteUint8((byte)index);
               output.WriteUint8(color.A);
            }
         }
         _logger.Debug(this, "StoreIndexedColor", "Make indexed color. (position={0}-{1}, color_total={2}, color_count={3})",
            cx, cy, colorTotal, paletteCount);
      }

      //============================================================
      // <T>存储压缩文件。</T>
      //
      // @param fileName 文件名称
      // @param widthCount 横向分割个数
      // @param hightCount 纵向分割个数
      //============================================================
      public void StoreCompress(IOutput output, Bitmap bitmap, int colorCount, int widthCount, int hightCount) {
         // 输出图片尺寸
         int width = bitmap.Width;
         int height = bitmap.Height;
         output.WriteUint16((ushort)width);
         output.WriteUint16((ushort)height);
         // 输出分割尺寸
         int splitWidth = width / widthCount;
         int splitHeight = height / hightCount;
         int splitCount = widthCount * hightCount;
         output.WriteUint16((ushort)splitCount);
         // 分割图片
         for (int y = 0; y < hightCount; y++) {
            for (int x = 0; x < widthCount; x++) {
               FByteStream bs = new FByteStream();
               bs.WriteUint16((ushort)splitWidth);
               bs.WriteUint16((ushort)splitHeight);
               // 绘制局部图形
               FBitmap image = new FBitmap(splitWidth, splitHeight);
               image.CopyFrom(bitmap, splitWidth * x, splitHeight * y);
               // 存储索引图片
               StoreIndexedColor(bs, image.Native, colorCount, x, y);
               // 输出数据
               output.WriteInt32(bs.Length);
               output.WriteBytes(bs.Memory, 0, bs.Length);
            }
         }
      }

      //============================================================
      // <T>导入打包文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public void ImportFile(string fileName) {
         // 创建压缩文件
         FCompressFile file = new FCompressFile();
         file.Decompress(fileName);
         _bitmap = RestoreCompress(file);
      }

      //============================================================
      // <T>导出打包文件。</T>
      //
      // @param fileName 文件名称
      // @param widthCount 横向分割个数
      // @param hightCount 纵向分割个数
      //============================================================
      public void ExportFile(string fileName, int colorCount, int widthCount, int hightCount) {
         _logger.Debug(this, "ExportFile", "Export file. (file_name={0}, color_count={1}, width_count={2}, hight_count={3})",
            fileName, colorCount, widthCount, hightCount);
         // 创建压缩文件
         FLzmaFile file = new FLzmaFile();
         file.EnsureSize(_bitmap.Width * _bitmap.Height);
         StoreCompress(file, _bitmap, colorCount, widthCount, hightCount);
         file.Compress(fileName);
      }

      //============================================================
      // <T>导出多个打包到目录。</T>
      //
      // @param fileName 文件名称
      // @param widthCount 横向分割个数
      // @param hightCount 纵向分割个数
      //============================================================
      public void ExportPath(string path, int tileWidth, int tileHight, int colorCount, int widthCount, int hightCount) {
         // 计算分割数量
         int tileCx = _bitmap.Width / tileWidth;
         int tileCy = _bitmap.Height / tileHight;
         // 分割图片
         for (int y = 0; y < tileCy; y++) {
            for (int x = 0; x < tileCx; x++) {
               // 绘制局部图形
               FBitmap bitmap = new FBitmap(tileWidth, tileHight);
               bitmap.Fill(_bitmap, tileWidth * x, tileHight * y);
               // 存储索引图片
               string fileName = path + "_" + RString.PadLeft(y.ToString(), 2, '0') + "_" + RString.PadLeft(x.ToString(), 2, '0') + "." + EXTEND_NAME;
               _logger.Debug(this, "ExportPath", "Export path file. (file_name={0}, color_count={1}, width_count={2}, hight_count={3})",
               fileName, colorCount, widthCount, hightCount);
               
               FLzmaFile file = new FLzmaFile();
               file.EnsureSize(tileWidth * tileHight);
               StoreCompress(file, bitmap.Native, colorCount, widthCount, hightCount);
               file.Compress(fileName);
               file.Reset();
            }
         }
      }
   }
}

