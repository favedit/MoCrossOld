using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Core;
using MO.Core;
using MO.Core.Content.Drawing;
using MO.Content3d.Common;
using MO.Content3d.Resource.Common;
using System.Drawing;
using System.Drawing.Imaging;

namespace MO.Content3d.Resource.Texture
{
   //============================================================
   // <T>光照纹理信息。</T>
   //============================================================
   public class FDrColorTexture : FDrTexture
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FDrColorTexture));

      //============================================================
      // <T>构造光照纹理信息。</T>
      //============================================================
      public FDrColorTexture() {
         _typeName = EDrResourceType.TextureColor;
         _typeLabel = "Texture.Color";
         _sortAble = true;
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public override void LoadConfig(FXmlNode xconfig) {
         foreach (FXmlNode xnode in xconfig.Nodes) {
            if (xnode.IsName("Bitmap")) {
               // 查找图片对象
               string typeName = null;
               if (xnode.Contains("type")) {
                  typeName = xnode.Get("type");
               } else {
                  typeName = xnode.Get("type_name");
               }
               int typeCd = EDrTexture.Parse(typeName);
               FDrTextureBitmap bitmap = FindByTypeCd(typeCd);
               if (null != bitmap) {
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
         foreach (FDrTextureBitmap bitmap in _bitmaps) {
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
         _exportFileDataName = _directoryExprot + ".dt\\tx_" + Code + ".swf";
         // 查询图片
         string resourceDirectory = RContent3dManager.TextureConsole.ResourceDirectory;
         foreach (string filename in RDirectory.ListFiles(_directory)) {
            FFileInfo info = new FFileInfo(filename);
            int typeCd = EDrTexture.TryParse(info.ShortName);
            if (-1 != typeCd) {
               FDrTextureBitmap bitmap = new FDrTextureBitmap();
               bitmap.TypeCd = typeCd;
               bitmap.Source = info.FullName.Substring(resourceDirectory.Length);
               bitmap.FileName = info.FullName;
               bitmap.Length = info.Info.Length;
               _bitmaps.Push(bitmap);
            }
         }
         // 加载设置文件
         if (RFile.Exists(_configFileName)) {
            LoadConfigFile(_configFileName);
         }
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
            bitmap.Serialize(output);
         }
         // 压缩颜色
         int bitmapCount = 0;
         string path = RContent3dManager.TextureConsole.ResourceDirectory;
         FByteStream bytes = new FByteStream();
         // 合并漫反射和透明纹理
         FDrTextureBitmap bitmapDiffuse = FindByTypeCd(EDrTexture.Diffuse);
         FDrTextureBitmap bitmapAlpha = FindByTypeCd(EDrTexture.Alpha);
         if ((null != bitmapDiffuse) || (null != bitmapAlpha)) {
            bitmapCount++;
            bytes.WriteInt8(EDrTexture.PackDiffuse);
            bytes.WriteInt16(0);
            using (FPngMerger merger = new FPngMerger()) {
               if (null != bitmapDiffuse) {
                  merger.LoadColorFile(path + bitmapDiffuse.Source);
               }
               if (null != bitmapAlpha) {
                  merger.LoadAlphaFile(path + bitmapAlpha.Source);
               }
               Bitmap bitmap = merger.Merge();
               using (FIndexBitmap resource = new FIndexBitmap(bitmap)) {
                  resource.OptionAlpha = true;
                  resource.SerializeIndexed(bytes, EDrTexture.SplitColorCount, EDrTexture.SplitPixelCount);
               }
            }
         }
         // 合并法线和高光级别纹理
         FDrTextureBitmap bitmapNormal = FindByTypeCd(EDrTexture.Normal);
         FDrTextureBitmap bitmapSpecularLevel = FindByTypeCd(EDrTexture.SpecularLevel);
         if ((null != bitmapNormal) || (null != bitmapSpecularLevel)) {
            bitmapCount++;
            bytes.WriteInt8(EDrTexture.PackNormal);
            bytes.WriteInt16(0);
            using (FPngMerger merger = new FPngMerger()) {
               if (null != bitmapNormal) {
                  merger.LoadColorFile(path + bitmapNormal.Source);
               }
               if (null != bitmapSpecularLevel) {
                  merger.LoadAlphaFile(path + bitmapSpecularLevel.Source);
               }
               Bitmap bitmap = merger.Merge();
               using (FIndexBitmap resource = new FIndexBitmap(bitmap)) {
                  resource.OptionAlpha = true;
                  resource.SerializeIndexed(bytes, EDrTexture.SplitColorCount, EDrTexture.SplitPixelCount);
               }
            }
         }
         // 合并高光和高度纹理
         FDrTextureBitmap bitmapSpecular = FindByTypeCd(EDrTexture.Specular);
         FDrTextureBitmap bitmapHeight = FindByTypeCd(EDrTexture.Height);
         if ((null != bitmapSpecular) || (null != bitmapHeight)) {
            bitmapCount++;
            bytes.WriteInt8(EDrTexture.PackSpecular);
            bytes.WriteInt16(0);
            using (FPngMerger merger = new FPngMerger()) {
               if (null != bitmapSpecular) {
                  merger.LoadColorFile(path + bitmapSpecular.Source);
               }
               if (null != bitmapHeight) {
                  merger.LoadAlphaFile(path + bitmapHeight.Source);
               }
               Bitmap bitmap = merger.Merge();
               using (FIndexBitmap resource = new FIndexBitmap(bitmap)) {
                  resource.OptionAlpha = true;
                  resource.SerializeIndexed(bytes, EDrTexture.SplitColorCount, EDrTexture.SplitPixelCount);
               }
            }
         }
         // 合并透射和透射级别纹理
         FDrTextureBitmap bitmapTransmittanceColor = FindByTypeCd(EDrTexture.TransmittanceColor);
         FDrTextureBitmap bitmapTransmittanceLevel = FindByTypeCd(EDrTexture.TransmittanceLevel);
         if ((null != bitmapTransmittanceColor) || (null != bitmapTransmittanceLevel)) {
            bitmapCount++;
            bytes.WriteInt8(EDrTexture.PackTransmittance);
            bytes.WriteInt16(0);
            using (FPngMerger merger = new FPngMerger()) {
               if (null != bitmapTransmittanceColor) {
                  merger.LoadColorFile(path + bitmapTransmittanceColor.Source);
               }
               if (null != bitmapTransmittanceLevel) {
                  merger.LoadAlphaFile(path + bitmapTransmittanceLevel.Source);
               }
               Bitmap bitmap = merger.Merge();
               using (FIndexBitmap resource = new FIndexBitmap(bitmap)) {
                  resource.OptionAlpha = true;
                  resource.SerializeIndexed(bytes, EDrTexture.SplitColorCount, EDrTexture.SplitPixelCount);
               }
            }
         }
         // 合并其他纹理
         FDrTextureBitmap bitmapLight = FindByTypeCd(EDrTexture.Light);
         FDrTextureBitmap bitmapReflect = FindByTypeCd(EDrTexture.Reflect);
         FDrTextureBitmap bitmapRefract = FindByTypeCd(EDrTexture.Refract);
         FDrTextureBitmap bitmapEmissive = FindByTypeCd(EDrTexture.Emissive);
         if ((null != bitmapLight) || (null != bitmapReflect) || (null != bitmapRefract) || (null != bitmapEmissive)) {
            bitmapCount++;
            bytes.WriteInt8(EDrTexture.PackLight);
            bytes.WriteInt16(0);
            using (FPngMerger merger = new FPngMerger()) {
               if (null != bitmapLight) {
                  merger.LoadRFile(path + bitmapLight.Source);
               }
               if (null != bitmapReflect) {
                  merger.LoadGFile(path + bitmapReflect.Source);
               }
               if (null != bitmapRefract) {
                  merger.LoadBFile(path + bitmapRefract.Source);
               }
               if (null != bitmapEmissive) {
                  merger.LoadAlphaFile(path + bitmapEmissive.Source);
               }
               Bitmap bitmap = merger.MergeAll();
               using (FIndexBitmap resource = new FIndexBitmap(bitmap)) {
                  resource.OptionAlpha = true;
                  resource.SerializeIndexed(bytes, EDrTexture.SplitColorCount, EDrTexture.SplitPixelCount);
               }
            }
         }
         // 输出环境纹理
         FDrTextureBitmap bitmapEnvironment = FindByTypeCd(EDrTexture.Environment);
         if (null != bitmapEnvironment) {
            bitmapCount++;
            bytes.WriteInt8(EDrTexture.Environment);
            bytes.WriteInt16(0);
            Bitmap bitmap = new Bitmap(path + bitmapEnvironment.Source);
            int size = bitmap.Height;
            int block = bitmap.Width / bitmap.Height;
            Bitmap cubeBitmap = new Bitmap(bitmap.Height, bitmap.Width, PixelFormat.Format32bppArgb);
            using (Graphics g = Graphics.FromImage(cubeBitmap)) {
               for (int n = 0; n < 6; n++) {
                  g.DrawImage(bitmap,
                     new Rectangle(0, size * n, size, size),
                     new Rectangle(size * n, 0, size, size),
                     GraphicsUnit.Pixel);
               }
            }
            using (FIndexBitmap resource = new FIndexBitmap(cubeBitmap)) {
               resource.OptionAlpha = true;
               resource.SerializeIndexed(bytes, EDrTexture.SplitColorCount, EDrTexture.SplitPixelCount);
            }
            cubeBitmap.Dispose();
         }
         // 输出纹理列表
         output.WriteInt16((sbyte)bitmapCount);
         output.WriteBytes(bytes.Memory, 0, bytes.Length);
      }

      //============================================================
      // <T>序列化多层数据。</T>
      //
      // @param output 输出流
      //============================================================
      public override void Serialize(IOutput output) {
         // 输出属性
         //output.WriteInt32(CodeNumber);
         //output.WriteInt32(0);
         output.WriteString(Code);
         // 压缩颜色
         int bitmapCount = 0;
         string path = RContent3dManager.TextureConsole.ResourceDirectory;
         FByteStream bytes = new FByteStream();
         // 合并漫反射和透明纹理
         FDrTextureBitmap bitmapDiffuse = FindByTypeCd(EDrTexture.Diffuse);
         FDrTextureBitmap bitmapAlpha = FindByTypeCd(EDrTexture.Alpha);
         if ((null != bitmapDiffuse) || (null != bitmapAlpha)) {
            bitmapCount++;
            bytes.WriteInt8(EDrTexture.Texture2d);
            //bytes.WriteInt8(EDrTexture.PackDiffuse);
            bytes.WriteAnsiString(EContent3dSampler.PackDiffuseCode);
            using (FRsBitmapTexture merger = new FRsBitmapTexture()) {
               if (null != bitmapDiffuse) {
                  merger.LoadFile(path + bitmapDiffuse.Source, EBitmapChannels.RGB);
               }
               if (null != bitmapAlpha) {
                  merger.LoadFileChannel(path + bitmapAlpha.Source, EBitmapChannel.R, EBitmapChannels.A);
               }
               merger.SerializeSimple(bytes);
            }
         }
         // 合并法线和高光级别纹理
         FDrTextureBitmap bitmapNormal = FindByTypeCd(EDrTexture.Normal);
         FDrTextureBitmap bitmapSpecularLevel = FindByTypeCd(EDrTexture.SpecularLevel);
         if ((null != bitmapNormal) || (null != bitmapSpecularLevel)) {
            bitmapCount++;
            bytes.WriteInt8(EDrTexture.Texture2d);
            //bytes.WriteInt8(EDrTexture.PackNormal);
            bytes.WriteAnsiString(EContent3dSampler.PackNormalCode);
            using(FRsBitmapTexture merger = new FRsBitmapTexture()) {
               if (null != bitmapNormal) {
                  merger.LoadFile(path + bitmapNormal.Source, EBitmapChannels.RGB);
               }
               if (null != bitmapSpecularLevel) {
                  merger.LoadFileChannel(path + bitmapSpecularLevel.Source, EBitmapChannel.R, EBitmapChannels.A);
               }
               merger.SerializeSimple(bytes);
            }
         }
         // 合并高光和高度纹理
         FDrTextureBitmap bitmapSpecular = FindByTypeCd(EDrTexture.Specular);
         FDrTextureBitmap bitmapHeight = FindByTypeCd(EDrTexture.Height);
         if ((null != bitmapSpecular) || (null != bitmapHeight)) {
            bitmapCount++;
            bytes.WriteInt8(EDrTexture.Texture2d);
            //bytes.WriteInt8(EDrTexture.PackSpecular);
            bytes.WriteAnsiString(EContent3dSampler.PackSpecularCode);
            using(FRsBitmapTexture merger = new FRsBitmapTexture()) {
               if (null != bitmapSpecular) {
                  merger.LoadFile(path + bitmapSpecular.Source, EBitmapChannels.RGB);
               }
               if (null != bitmapHeight) {
                  merger.LoadFileChannel(path + bitmapHeight.Source, EBitmapChannel.R, EBitmapChannels.A);
               }
               merger.SerializeSimple(bytes);
            }
         }
         // 合并透射和透射级别纹理
         FDrTextureBitmap bitmapTransmittanceColor = FindByTypeCd(EDrTexture.TransmittanceColor);
         FDrTextureBitmap bitmapTransmittanceLevel = FindByTypeCd(EDrTexture.TransmittanceLevel);
         if ((null != bitmapTransmittanceColor) || (null != bitmapTransmittanceLevel)) {
            bitmapCount++;
            bytes.WriteInt8(EDrTexture.Texture2d);
            //bytes.WriteInt8(EDrTexture.PackTransmittance);
            bytes.WriteAnsiString(EContent3dSampler.PackTransmittanceCode);
            using(FRsBitmapTexture merger = new FRsBitmapTexture()) {
               if (null != bitmapTransmittanceColor) {
                  merger.LoadFile(path + bitmapTransmittanceColor.Source, EBitmapChannels.RGB);
               }
               if (null != bitmapTransmittanceLevel) {
                  merger.LoadFileChannel(path + bitmapTransmittanceLevel.Source, EBitmapChannel.R, EBitmapChannels.A);
               }
               merger.SerializeSimple(bytes);
            }
         }
         // 合并其他纹理
         FDrTextureBitmap bitmapLight = FindByTypeCd(EDrTexture.Light);
         FDrTextureBitmap bitmapReflect = FindByTypeCd(EDrTexture.Reflect);
         FDrTextureBitmap bitmapRefract = FindByTypeCd(EDrTexture.Refract);
         FDrTextureBitmap bitmapEmissive = FindByTypeCd(EDrTexture.Emissive);
         if ((null != bitmapLight) || (null != bitmapReflect) || (null != bitmapRefract) || (null != bitmapEmissive)) {
            bitmapCount++;
            bytes.WriteInt8(EDrTexture.Texture2d);
            //bytes.WriteInt8(EDrTexture.PackLight);
            bytes.WriteAnsiString(EContent3dSampler.PackLightCode);
            using(FRsBitmapTexture merger = new FRsBitmapTexture()) {
               if (null != bitmapLight) {
                  merger.LoadFileChannel(path + bitmapLight.Source, EBitmapChannel.R, EBitmapChannels.R);
               }
               if (null != bitmapReflect) {
                  merger.LoadFileChannel(path + bitmapReflect.Source, EBitmapChannel.R, EBitmapChannels.G);
               }
               if (null != bitmapRefract) {
                  merger.LoadFileChannel(path + bitmapRefract.Source, EBitmapChannel.R, EBitmapChannels.B);
               }
               if (null != bitmapEmissive) {
                  merger.LoadFileChannel(path + bitmapEmissive.Source, EBitmapChannel.R, EBitmapChannels.A);
               }
               merger.SerializeSimple(bytes);
            }
         }
         // 输出环境纹理
         FDrTextureBitmap bitmapEnvironment = FindByTypeCd(EDrTexture.Environment);
         if (null != bitmapEnvironment) {
            bitmapCount++;
            bytes.WriteInt8(EDrTexture.TextureCube);
            bytes.WriteAnsiString(EContent3dSampler.EnvironmentCode);
            //bytes.WriteInt8(EDrTexture.Environment);
            Bitmap bitmap = new Bitmap(path + bitmapEnvironment.Source);
            int size = bitmap.Height;
            int block = bitmap.Width / bitmap.Height;
            using (Bitmap cubeBitmap = new Bitmap(bitmap.Height, bitmap.Width, PixelFormat.Format32bppArgb)) {
               cubeBitmap.SetResolution(bitmap.HorizontalResolution, bitmap.VerticalResolution);
               using (Graphics g = Graphics.FromImage(cubeBitmap)) {
                  for (int n = 0; n < 6; n++) {
                     g.DrawImage(bitmap,
                        new Rectangle(0, size * n, size, size),
                        new Rectangle(size * n, 0, size, size),
                        GraphicsUnit.Pixel);
                  }
               }
               using (FRsBitmapTexture merger = new FRsBitmapTexture(cubeBitmap)) {
                  merger.SerializeSimple(bytes);
               }
            }
         }
         // 输出纹理列表
         output.WriteInt16((sbyte)bitmapCount);
         output.WriteBytes(bytes.Memory, 0, bytes.Length);
         // 输出位图列表
         //output.WriteInt16((short)_bitmaps.Count);
         //foreach (FDrTextureBitmap bitmap in _bitmaps) {
         //   using (FBitmap dataBitmap = new FBitmap(bitmap.Image.Native)) {
         //      output.WriteUint8((byte)bitmap.TypeCd);
         //      dataBitmap.SerializeSimple(output);
         //   }
         //}
      }

      //============================================================
      // <T>序列化多层数据。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize2(IOutput output) {
         base.Serialize(output);
         // 输出位图列表
         output.WriteInt16((short)_bitmaps.Count);
         foreach (FDrTextureBitmap bitmap in _bitmaps) {
            bitmap.Serialize(output);
         }
         // 压缩颜色
         int bitmapCount = 0;
         string path = RContent3dManager.TextureConsole.ResourceDirectory;
         FByteStream bytes = new FByteStream();
         // 合并漫反射和透明纹理
         FDrTextureBitmap bitmapDiffuse = FindByTypeCd(EDrTexture.Diffuse);
         FDrTextureBitmap bitmapAlpha = FindByTypeCd(EDrTexture.Alpha);
         if ((null != bitmapDiffuse) || (null != bitmapAlpha)) {
            bitmapCount++;
            bytes.WriteInt8(EDrTexture.PackDiffuse);
            bytes.WriteInt16(0);
            using (FRsBitmapTexture merger = new FRsBitmapTexture()) {
               if (null != bitmapDiffuse) {
                  merger.LoadFile(path + bitmapDiffuse.Source, EBitmapChannels.RGB);
               }
               if (null != bitmapAlpha) {
                  merger.LoadFileChannel(path + bitmapAlpha.Source, EBitmapChannel.R, EBitmapChannels.A);
               }
               merger.Serialize(bytes);
            }
         }
         // 合并法线和高光级别纹理
         FDrTextureBitmap bitmapNormal = FindByTypeCd(EDrTexture.Normal);
         FDrTextureBitmap bitmapSpecularLevel = FindByTypeCd(EDrTexture.SpecularLevel);
         if ((null != bitmapNormal) || (null != bitmapSpecularLevel)) {
            bitmapCount++;
            bytes.WriteInt8(EDrTexture.PackNormal);
            bytes.WriteInt16(0);
            using (FRsBitmapTexture merger = new FRsBitmapTexture()) {
               if (null != bitmapNormal) {
                  merger.LoadFile(path + bitmapNormal.Source, EBitmapChannels.RGB);
               }
               if (null != bitmapSpecularLevel) {
                  merger.LoadFileChannel(path + bitmapSpecularLevel.Source, EBitmapChannel.R, EBitmapChannels.A);
               }
               merger.Serialize(bytes);
            }
         }
         // 合并高光和高度纹理
         FDrTextureBitmap bitmapSpecular = FindByTypeCd(EDrTexture.Specular);
         FDrTextureBitmap bitmapHeight = FindByTypeCd(EDrTexture.Height);
         if ((null != bitmapSpecular) || (null != bitmapHeight)) {
            bitmapCount++;
            bytes.WriteInt8(EDrTexture.PackSpecular);
            bytes.WriteInt16(0);
            using (FRsBitmapTexture merger = new FRsBitmapTexture()) {
               if (null != bitmapSpecular) {
                  merger.LoadFile(path + bitmapSpecular.Source, EBitmapChannels.RGB);
               }
               if (null != bitmapHeight) {
                  merger.LoadFileChannel(path + bitmapHeight.Source, EBitmapChannel.R, EBitmapChannels.A);
               }
               merger.Serialize(bytes);
            }
         }
         // 合并透射和透射级别纹理
         FDrTextureBitmap bitmapTransmittanceColor = FindByTypeCd(EDrTexture.TransmittanceColor);
         FDrTextureBitmap bitmapTransmittanceLevel = FindByTypeCd(EDrTexture.TransmittanceLevel);
         if ((null != bitmapTransmittanceColor) || (null != bitmapTransmittanceLevel)) {
            bitmapCount++;
            bytes.WriteInt8(EDrTexture.PackTransmittance);
            bytes.WriteInt16(0);
            using (FRsBitmapTexture merger = new FRsBitmapTexture()) {
               if (null != bitmapTransmittanceColor) {
                  merger.LoadFile(path + bitmapTransmittanceColor.Source, EBitmapChannels.RGB);
               }
               if (null != bitmapTransmittanceLevel) {
                  merger.LoadFileChannel(path + bitmapTransmittanceLevel.Source, EBitmapChannel.R, EBitmapChannels.A);
               }
               merger.Serialize(bytes);
            }
         }
         // 合并其他纹理
         FDrTextureBitmap bitmapLight = FindByTypeCd(EDrTexture.Light);
         FDrTextureBitmap bitmapReflect = FindByTypeCd(EDrTexture.Reflect);
         FDrTextureBitmap bitmapRefract = FindByTypeCd(EDrTexture.Refract);
         FDrTextureBitmap bitmapEmissive = FindByTypeCd(EDrTexture.Emissive);
         if ((null != bitmapLight) || (null != bitmapReflect) || (null != bitmapRefract) || (null != bitmapEmissive)) {
            bitmapCount++;
            bytes.WriteInt8(EDrTexture.PackLight);
            bytes.WriteInt16(0);
            using (FRsBitmapTexture merger = new FRsBitmapTexture()) {
               if (null != bitmapLight) {
                  merger.LoadFileChannel(path + bitmapLight.Source, EBitmapChannel.R, EBitmapChannels.R);
               }
               if (null != bitmapReflect) {
                  merger.LoadFileChannel(path + bitmapReflect.Source, EBitmapChannel.R, EBitmapChannels.G);
               }
               if (null != bitmapRefract) {
                  merger.LoadFileChannel(path + bitmapRefract.Source, EBitmapChannel.R, EBitmapChannels.B);
               }
               if (null != bitmapEmissive) {
                  merger.LoadFileChannel(path + bitmapEmissive.Source, EBitmapChannel.R, EBitmapChannels.A);
               }
               merger.Serialize(bytes);
            }
         }
         // 输出环境纹理
         FDrTextureBitmap bitmapEnvironment = FindByTypeCd(EDrTexture.Environment);
         if (null != bitmapEnvironment) {
            bitmapCount++;
            bytes.WriteInt8(EDrTexture.Environment);
            bytes.WriteInt16(0);
            Bitmap bitmap = new Bitmap(path + bitmapEnvironment.Source);
            int size = bitmap.Height;
            int block = bitmap.Width / bitmap.Height;
            using (Bitmap cubeBitmap = new Bitmap(bitmap.Height, bitmap.Width, PixelFormat.Format32bppArgb)) {
               cubeBitmap.SetResolution(bitmap.HorizontalResolution, bitmap.VerticalResolution);
               using (Graphics g = Graphics.FromImage(cubeBitmap)) {
                  for (int n = 0; n < 6; n++) {
                     g.DrawImage(bitmap,
                        new Rectangle(0, size * n, size, size),
                        new Rectangle(size * n, 0, size, size),
                        GraphicsUnit.Pixel);
                  }
               }
               using (FRsBitmapTexture merger = new FRsBitmapTexture(cubeBitmap)) {
                  merger.Serialize(bytes);
               }
            }
         }
         // 输出纹理列表
         output.WriteInt16((sbyte)bitmapCount);
         output.WriteBytes(bytes.Memory, 0, bytes.Length);
      }
   }
}
