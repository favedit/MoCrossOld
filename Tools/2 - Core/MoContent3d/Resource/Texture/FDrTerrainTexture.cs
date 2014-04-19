using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Core;

namespace MO.Content3d.Resource.Texture
{
   //============================================================
   // <T>地形纹理信息。</T>
   //============================================================
   public class FDrTerrainTexture : FDrTexture
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FDrColorTexture));

      //============================================================
      // <T>构造地形纹理信息。</T>
      //============================================================
      public FDrTerrainTexture() {
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
         _exportFileDataName = _directoryExprot + ".dt\\tx_" + Code + ".swf";
         // 查询图片
         string resourceDirectory = RContent3dManager.TextureConsole.ResourceDirectory;
         foreach(string filename in RDirectory.ListFiles(_directory)) {
            FFileInfo info = new FFileInfo(filename);
            int typeCd = EDrTexture.TryParse(info.ShortName);
            if(-1 != typeCd) {
               FDrTextureBitmap bitmap = new FDrTextureBitmap();
               bitmap.TypeCd = typeCd;
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
      // <T>序列化单层数据。</T>
      //
      // @param output 输出流
      //============================================================
      public override void Serialize(IOutput output) {
         // 输出属性
         output.WriteString(_name);
         // 输出位图列表
         output.WriteInt16((short)_bitmaps.Count);
         foreach(FDrTextureBitmap bitmap in _bitmaps) {
            bitmap.Serialize(output);
         }
         // 压缩颜色
         //int compressColor = 255;
         //int compressSize = 128;
         // 合并漫反射和透明纹理
         //FDrTextureBitmap bitmapDiffuse = new FDrTextureBitmap();
         //bitmapDiffuse.LoadImage(_path + "\\diffuse.jpg");
         //FDrTextureBitmap bitmapSpecular = new FDrTextureBitmap();
         //bitmapSpecular.LoadImage(_path + "\\specular.level.jpg");
         //FDrTextureBitmap bitmapHeight = new FDrTextureBitmap();
         //bitmapHeight.LoadImage(_path + "\\height.jpg");
         //FDrTextureBitmap bitmapNormal = new FDrTextureBitmap();
         //bitmapNormal.LoadImage(_path + "\\normal.jpg");
         //int width = bitmapDiffuse.Image.Native.Width;
         //int height = bitmapDiffuse.Image.Native.Height;
         //FBitmap bit = new FBitmap(width, height * 2);
         //for(int x = 0; x < width; x++) {
         //   for(int y = 0; y < height; y++) {
         //      Color diffuseColor = bitmapDiffuse.Image.Native.GetPixel(x, y);
         //      byte byteA = bitmapDiffuse.Image.Native.GetPixel(x, y).A;
         //      bit.Bitmap.SetPixel(x, y, Color.FromArgb(byteA, diffuseColor));
         //   }
         //}
         //for(int x = 0; x < width; x++) {
         //   for(int y = height; y < height * 2; y++) {
         //      Color diffuseColor = bitmapNormal.Image.Native.GetPixel(x, y - height);
         //      byte byteA = bitmapHeight.Image.Native.GetPixel(x, y - height).A;
         //      bit.Bitmap.SetPixel(x, y, Color.FromArgb(byteA, diffuseColor));
         //   }
         //}
         //FCompressPicture resource = new FCompressPicture();
         //resource.StoreCompress(output, bit.Bitmap, compressColor, 1, Math.Max(bit.Bitmap.Height / compressSize, 1));
      }
   }
}
