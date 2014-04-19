using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Core;
using MO.Core;
using MO.Core.Content.Drawing;
using MO.Content3d.Resource.Common;
using System;
using System.Drawing;
using System.Drawing.Imaging;

namespace MO.Content3d.Resource.Texture
{
   //============================================================
   // <T>剪辑纹理信息。</T>
   //============================================================
   public class FDrLayerTexture : FDrTexture
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FDrLayerTexture));

      //============================================================
      // <T>构造剪辑纹理信息。</T>
      //============================================================
      public FDrLayerTexture() {
         _typeName = EDrResourceType.TextureLayer;
         _typeLabel = "Texture.Layer";
         _sortAble = false;
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public override void LoadConfig(FXmlNode xconfig) {
         foreach(FXmlNode xnode in xconfig.Nodes) {
            if(xnode.IsName("Bitmap")) {
               // 查找图片对象
               string typeName = null;
               if(xnode.Contains("type")) {
                  typeName = xnode.Get("type");
               } else {
                  typeName = xnode.Get("type_name");
               }
               int typeCd = EDrTexture.Parse(typeName);
               FDrTextureBitmap bitmap = FindByTypeCd(typeCd);
               if(null != bitmap) {
                  // 加载设置
                  bitmap.LoadConfig(xnode);
               } else {
                  RMoCore.TrackConsole.Write(this, "LoadConfig", "Texture bitmap is not exists in config. (type_name={0}, file_name={1})", typeName, _configFileName);
               }
            }
         }
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public override void SaveConfig(FXmlNode config) {
         // 设置信息
         config.Set("name", _name);
         config.Set("label", _label);
         // 存储位图列表
         foreach(FDrTextureBitmap bitmap in _bitmaps) {
            bitmap.SaveConfig(config.CreateNode("Bitmap"));
         }
      }

      //============================================================
      // <T>扫描工作路径，获得对象列表。</T>
      //============================================================
      public override void Scan() {
         base.Scan();
         // 设置目录
         _exportFileName = _directoryExprot + "\\tx_" + Code + ".swf";
         // 查询图片
         string resourceDirectory = RContent3dManager.TextureConsole.ResourceDirectory;
         foreach(string filename in RDirectory.ListFiles(_directory)) {
            if (filename.EndsWith("layer.merge.jpg")) {
               // 加入和冰层
               FFileInfo info = new FFileInfo(filename);
               FDrTextureBitmap bitmap = new FDrTextureBitmap();
               bitmap.TypeCd = EDrTexture.LayerMerge;
               bitmap.Source = info.FullName.Substring(resourceDirectory.Length);
               bitmap.FileName = info.FullName;
               bitmap.Length = info.Info.Length;
               _bitmaps.Push(bitmap);
            }
         }
         foreach (string filename in RDirectory.ListFiles(_directory)) {
            if (filename.EndsWith("layer.merge.jpg")) {
               continue;
            }else if (filename.EndsWith(".jpg") && !filename.EndsWith(".m.jpg")) {
               // 加入纹理层
               FFileInfo info = new FFileInfo(filename);
               FDrTextureBitmap bitmap = new FDrTextureBitmap();
               if (filename.EndsWith("layer.1.jpg")) {
                  bitmap.TypeCd = EDrTexture.Layer1;
               }else if (filename.EndsWith("layer.2.jpg")) {
                  bitmap.TypeCd = EDrTexture.Layer2;
               } else if (filename.EndsWith("layer.3.jpg")) {
                  bitmap.TypeCd = EDrTexture.Layer3;
               } else if (filename.EndsWith("layer.4.jpg")) {
                  bitmap.TypeCd = EDrTexture.Layer4;
               }
               bitmap.Source = info.FullName.Substring(resourceDirectory.Length);
               bitmap.FileName = info.FullName;
               bitmap.Length = info.Info.Length;
               _bitmaps.Push(bitmap);
            }
         }
         // 加载设置文件
         if(RFile.Exists(_configFileName)) {
            LoadConfigFile(_configFileName);
         }
      }

      //============================================================
      // <T>导出合并处理。</T>
      //============================================================
      public override void ExportMerge() {
         // 输出属性
         //Bitmap layer = new Bitmap(2048, 2048, PixelFormat.Format32bppArgb);
         Bitmap layer = new Bitmap(2048, 2048, PixelFormat.Format32bppRgb);
         // 存储信息
         Bitmap layer01 = new Bitmap(_directory + "\\source\\01_01.png");
         Bitmap layer02 = new Bitmap(_directory + "\\source\\02_02.png");
         Bitmap layer03 = new Bitmap(_directory + "\\source\\03_01.png");
         Bitmap layer04 = new Bitmap(_directory + "\\source\\04_04.png");
         Bitmap layer05 = new Bitmap(_directory + "\\source\\05_03.png");
         Bitmap layer06 = new Bitmap(_directory + "\\source\\06_04.png");
         Bitmap layer07 = new Bitmap(_directory + "\\source\\07_02.png");
         Bitmap layer08 = new Bitmap(_directory + "\\source\\08_03.png");
         Bitmap layer09 = new Bitmap(_directory + "\\source\\09_04.png");
         Bitmap layer10 = new Bitmap(_directory + "\\source\\10_01.png");
         Bitmap layer11 = new Bitmap(_directory + "\\source\\11_02.png");
         Bitmap layer12 = new Bitmap(_directory + "\\source\\12_03.png");
         for (int y = 0; y < 2048; y++) {
            for (int x = 0; x < 2048; x++) {
               Color l1 = layer01.GetPixel(x, y);
               Color l2 = layer02.GetPixel(x, y);
               Color l3 = layer03.GetPixel(x, y);
               Color l4 = layer04.GetPixel(x, y);
               Color l5 = layer05.GetPixel(x, y);
               Color l6 = layer06.GetPixel(x, y);
               Color l7 = layer07.GetPixel(x, y);
               Color l8 = layer08.GetPixel(x, y);
               Color l9 = layer09.GetPixel(x, y);
               Color l10 = layer10.GetPixel(x, y);
               Color l11 = layer11.GetPixel(x, y);
               Color l12 = layer12.GetPixel(x, y);
               // 生成各层的合并信息
               int m1 = Math.Min((int)l1.A, 255);
               int m2 = Math.Min((int)l2.A, 255 - m1);
               int m3 = Math.Min((int)l3.A, 255 - m1 - m2);
               int m4 = Math.Min((int)l4.A, 255 - m1 - m2 - m3);
               int m5 = Math.Min((int)l5.A, 255 - m1 - m2 - m3 - m4);
               int m6 = Math.Min((int)l6.A, 255 - m1 - m2 - m3 - m4 - m5);
               int m7 = Math.Min((int)l7.A, 255 - m1 - m2 - m3 - m4 - m5 - m6);
               int m8 = Math.Min((int)l8.A, 255 - m1 - m2 - m3 - m4 - m5 - m6 - m7);
               int m9 = Math.Min((int)l9.A, 255 - m1 - m2 - m3 - m4 - m5 - m6 - m7 - m8);
               int m10 = Math.Min((int)l10.A, 255 - m1 - m2 - m3 - m4 - m5 - m6 - m7 - m8 - m9);
               int m11 = Math.Min((int)l11.A, 255 - m1 - m2 - m3 - m4 - m5 - m6 - m7 - m8 - m9 - m10);
               int m12 = Math.Min((int)l12.A, 255 - m1 - m2 - m3 - m4 - m5 - m6 - m7 - m8 - m9 - m10 - m11);
               // 合并信息
               int a1 = Math.Min(m1 + m3 + m10, 255);
               int a2 = Math.Min(m2 + m7 + m11, 255);
               int a3 = Math.Min(m5 + m8 + m12, 255);
               int a4 = Math.Min(m4 + m6 + m9, 255);
               int r1 = a1;
               int r2 = Math.Min(a2, 255 - r1);
               int r3 = Math.Min(a3, 255 - r1 - r2);
               int r4 = Math.Min(a4, 255 - r1 - r2 - r3);
               //layer.SetPixel(x, y, Color.FromArgb(r4, r1, r2, r3));
               layer.SetPixel(x, y, Color.FromArgb(255, r1, r2, r3));
            }
         }
         layer01.Dispose();
         layer02.Dispose();
         layer03.Dispose();
         layer04.Dispose();
         layer05.Dispose();
         layer06.Dispose();
         layer07.Dispose();
         layer08.Dispose();
         layer09.Dispose();
         layer10.Dispose();
         layer11.Dispose();
         layer12.Dispose();
         layer.Save(_directory + "\\layer.merge.jpg");
      }

      //============================================================
      // <T>序列化多层数据。</T>
      //
      // @param output 输出流
      //============================================================
      public override void SerializeIndex(IOutput output) {
         base.Serialize(output);
         // 输出位图列表
         output.WriteInt16((short)_bitmaps.Count);
         foreach (FDrTextureBitmap bitmap in _bitmaps) {
            output.WriteInt8((sbyte)bitmap.TypeCd);
            output.WriteInt16((short)0);
            // 存储信息
            bitmap.Serialize(output);
            // 存储图片
            using (FIndexBitmap resource = new FIndexBitmap(bitmap.Image.Native)) {
               //resource.OptionAlpha = true;
               resource.OptionAlpha = false;
               output.WriteInt8((sbyte)bitmap.TypeCd);
               output.WriteInt16((short)0);
               //resource.SerializeUnpackIndexed(output, compressColor, pixelCount);
               resource.SerializeIndexed(output, EDrTexture.SplitColorCount, EDrTexture.SplitPixelCount);
            }
         }
      }
      
      //============================================================
      // <T>序列化多层数据。</T>
      //
      // @param output 输出流
      //============================================================
      public override void Serialize(IOutput output) {
         base.Serialize(output);
         // 输出位图列表
         output.WriteInt16((short)_bitmaps.Count);
         foreach(FDrTextureBitmap bitmap in _bitmaps) {
            output.WriteInt8((sbyte)bitmap.TypeCd);
            output.WriteInt16((short)0);
            using (FRsBitmapTexture merger = new FRsBitmapTexture(bitmap.Image.Native)) {
               merger.Serialize(output);
            }
         }
      }
   }
}
