using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Core;
using MO.Core.Content.Drawing;
using MO.Content3d.Resource.Common;

namespace MO.Content3d.Resource.Texture
{
   //============================================================
   // <T>剪辑纹理信息。</T>
   //============================================================
   public class FDrClipTexture : FDrTexture
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FDrClipTexture));

      //============================================================
      // <T>构造剪辑纹理信息。</T>
      //============================================================
      public FDrClipTexture() {
         _typeName = EDrResourceType.TextureClip;
         _typeLabel = "Texture.Clip";
         _sortAble = true;
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
            if(filename.EndsWith(".jpg")) {
               FFileInfo info = new FFileInfo(filename);
               FDrTextureBitmap bitmap = new FDrTextureBitmap();
               bitmap.TypeCd = EDrTexture.Diffuse;
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
      // <T>序列化多层数据。</T>
      //
      // @param output 输出流
      //============================================================
      public override void Serialize(IOutput output) {
         base.Serialize(output);
         // 输出位图列表
         output.WriteInt16((short)_bitmaps.Count);
         foreach(FDrTextureBitmap bitmap in _bitmaps) {
            //// 存储图片
            //output.WriteInt8((sbyte)bitmap.TypeCd);
            //output.WriteInt16((short)bitmap.Index);
            //using (FDsBitmap merger = new FDsBitmap(bitmap.Image.Native)) {
            //   merger.Serialize(output);
            //}
            // 存储信息
            bitmap.Serialize(output);
            using (FIndexBitmap resource = new FIndexBitmap(bitmap.Image.Native)) {
               resource.OptionAlpha = true;
               output.WriteInt8((sbyte)bitmap.TypeCd);
               output.WriteInt16((short)bitmap.Index);
               resource.SerializeIndexed(output, EDrTexture.SplitColorCount, EDrTexture.SplitPixelCount);
            }
         }
      }
   }
}
