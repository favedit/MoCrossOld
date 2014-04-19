using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d;
using MO.Content2d.Common;
using MO.Content2d.Core;
using MO.Content3d.Resource.Common;
using System;

namespace MO.Content3d.Resource.Texture
{
   //============================================================
   // <T>纹理信息</T>
   //============================================================
   public class FDrTexture : FDrResource, IRsExport, IDisposable
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FDrTexture));

      // 排序
      protected bool _sortAble;

      // 导出文件名称
      protected string _exportFileName;

      // 导出数据名称
      protected string _exportFileDataName;

      // 图片集合
      protected FVector<FDrTextureBitmap> _bitmaps = new FVector<FDrTextureBitmap>();

      //============================================================
      // <T>构造纹理信息</T>
      //============================================================
      public FDrTexture() {
         _typeCode = "tx";
      }

      //============================================================
      // <T>获得图片集合。</T>
      //============================================================
      public FVector<FDrTextureBitmap> Bitmaps {
         get { return _bitmaps; }
      }

      //============================================================
      // <T>查找指定类型的纹理。</T>
      //
      // @param typeCd 类型
      // @return 纹理
      //============================================================
      public FDrTextureBitmap FindByTypeCd(int typeCd) {
         foreach(FDrTextureBitmap bitmap in _bitmaps) {
            if(bitmap.TypeCd == typeCd) {
               return bitmap;
            }
         }
         return null;
      }

      //============================================================
      // <T>查找指定类型的纹理。</T>
      //
      // @param typeCd 类型
      // @return 纹理
      //============================================================
      public FDrTextureBitmap GetByTypeCd(int typeCd) {
         foreach (FDrTextureBitmap bitmap in _bitmaps) {
            if (bitmap.TypeCd == typeCd) {
               return bitmap;
            }
         }
         throw new FFatalException("Unknown type cd. (type_cd=%d)", typeCd);
      }

      //============================================================
      // <T>查找指定类型的纹理。</T>
      //
      // @param typeCd 类型
      // @return 纹理
      //============================================================
      public FDrTextureBitmap FindByTypeCd(int typeCd, int index) {
         foreach(FDrTextureBitmap bitmap in _bitmaps) {
            if((bitmap.TypeCd == typeCd) && (bitmap.Index == index)) {
               return bitmap;
            }
         }
         return null;
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public virtual void LoadConfig(FXmlNode xconfig) {
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public virtual void SaveConfig(FXmlNode config) {
      }

      //============================================================
      public virtual void SerializeIndex(IOutput output) {
      }

      //============================================================
      // <T>打开数据内容。</T>
      //============================================================
      public override void Open() {
         if(!_opened) {
            base.Open();
            // 打开所有图片
            foreach(FDrTextureBitmap bitmap in _bitmaps) {
               bitmap.Open();
            }
            // 按照类型排序
            if(_sortAble && !_bitmaps.IsEmpty) {
               _bitmaps.Sort(_bitmaps.First);
            }
            int count = _bitmaps.Count;
            for(int n = 0; n < count; n++) {
               _bitmaps[n].Index = n;
            }
         }
      }

      //============================================================
      // <T>加载设置文件。</T>
      //============================================================
      public void LoadConfigFile(string fileName) {
         FXmlDocument xdoc = new FXmlDocument(fileName);
         FXmlNode xroot = xdoc.Root;
         FXmlNode xtexture = xroot.Find("Texture");
         if(null != xtexture) {
            LoadConfig(xtexture);
         }
      }

      //============================================================
      // <T>保存设置文件。</T>
      //============================================================
      public void SaveConfigFile(string fileName) {
         FXmlDocument xdoc = new FXmlDocument();
         FXmlNode xroot = xdoc.Root;
         SaveConfig(xroot.CreateNode("Texture"));
         xdoc.SaveFile(fileName);
      }

      //============================================================
      // <T>保存内容。</T>
      //============================================================
      public void Store() {
         // 打开纹理
         Open();
         // 存储信息
         FXmlDocument xdoc = new FXmlDocument();
         FXmlNode xroot = xdoc.Root;
         SaveConfig(xroot.CreateNode("Texture"));
         xdoc.SaveFile(_configFileName);
         _logger.Debug(this, "SaveConfig", "Save Texture config success. (file_name={0})", _configFileName);
         // 释放资源
         Dispose();
      }

      //============================================================
      // <T>导出合并处理。</T>
      //============================================================
      public virtual void ExportMerge() {
      }
      
      //============================================================
      // <T>根据指定模式导出数据。</T>
      //
      // @param modeCd 导出模式
      //============================================================
      public override void Export(ERsExportMode modeCd) {
         // 打开资源
         Open();
         //............................................................
         //string exportFileName = RContent3dManager.TextureConsole.ExportDirectory + "\\" + CodeNumber + ".ser";
         string exportFileName = RContent3dManager.TextureConsole.ExportDirectory + "\\" + Code + ".ser";
         //............................................................
         // 序列化数据
         FByteFile file = new FByteFile();
         Serialize(file);
         file.SaveFile(exportFileName);
         //............................................................
         // 释放资源
         Dispose();
         _logger.Debug(this, "Export", "Export texture success. (file_name={0})", exportFileName);
      }

      //============================================================
      // <T>序列化数据。</T>
      //============================================================
      public void ExportFile(string fileName) {
         // 打开
         Open();
         // 压缩文件
         FCompressFile file = new FCompressFile();
         Serialize(file);
         file.Compress(_exportFileName);
         _logger.Debug(this, "Export", "Export texture success. (file_name={0})", _exportFileName);
         // 释放资源
         Dispose();
      }
      
      //============================================================
      // <T>释放内容。</T>
      //============================================================
      public override void Dispose() {
         if(_opened) {
            foreach(FDrTextureBitmap bitmap in _bitmaps) {
               bitmap.Dispose();
            }
            // 释放内容
            base.Dispose();
         }
      }
   }
}
