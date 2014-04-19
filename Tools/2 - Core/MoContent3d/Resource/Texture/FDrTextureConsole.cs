using MO.Common.Collection;
using MO.Common.IO;
using MO.Content2d.Common;
using MO.Core;
using MO.Content3d.Common;
using MO.Content3d.Resource.Common;
using System;

namespace MO.Content3d.Resource.Texture
{
   //============================================================
   // <T>纹理控制台信息。</T>
   //============================================================
   public class FDrTextureConsole : FDrResourceConsole
   {
      // 纹理字典
      protected FDictionary<FDrTexture> _textures = new FDictionary<FDrTexture>();

      //============================================================
      // <T>构造纹理控制台信息。</T>
      //============================================================
      public FDrTextureConsole() {
         _name = "Content3d.Texture.Console";
      }

      //============================================================
      // <T>获得纹理字典。</T>
      //
      // @return 纹理字典
      //============================================================
      public FDictionary<FDrTexture> Textures {
         get { return _textures; }
      }

      //============================================================
      // <T>根据名称查找纹理。</T>
      //
      // @param name 名称
      // @return 纹理
      //============================================================
      public FDrTexture Find(string name) {
         string code = RDrUtil.FormatPathToCode(name);
         return _textures.Find(code);
      }

      //============================================================
      // <T>扫描所有节点。</T>
      //
      // @param folder 文件夹
      // @param path 路径
      //============================================================
      protected void ScanNodes(FDrFolder folder, string path) {
         string fileTag = string.Empty;
         // 文件夹排序
         folder.Folders.Sort();
         // 循环取得每个文件
         foreach(FDrFolder subfloder in folder.Folders) {
            // 获得经过处理的名称
            string[] items = subfloder.Name.Split('-');
            if(items.Length >= 3) {
               string type = items[0];
               string dotPath = path + "\\" + items[1];
               if("fd" == type) {
                  // 存储文件夹
                  subfloder.Type = "folder";
                  subfloder.Label = items[1] + " [" + items[2] + "]";
               } else if("tx" == type) {
                  // 纹理光源
                  subfloder.Type = "texture.light";
                  FDrTexture texture = new FDrColorTexture();
                  subfloder.Label = items[1] + " [" + items[2] + "]";
                  texture.Name = dotPath;
                  texture.Label = items[2];
                  texture.Folder = subfloder;
                  texture.Directory = subfloder.Directory;
                  texture.DirectoryExprot = _exportDirectory;
                  texture.Scan();
                  subfloder.Tag = texture;
                  // 存储对照表
                  _textures.Set(texture.Code, texture);
                  _folders.Push(subfloder);
               } else if("tc" == type) {
                  // 纹理剪辑
                  subfloder.Type = "texture.clip";
                  FDrTexture texture = new FDrClipTexture();
                  subfloder.Label = items[1] + " [" + items[2] + "]";
                  texture.Name = dotPath;
                  texture.Label = items[2];
                  texture.Folder = subfloder;
                  texture.Directory = subfloder.Directory;
                  texture.DirectoryExprot = _exportDirectory;
                  texture.Scan();
                  subfloder.Tag = texture;
                  // 存储对照表
                  _textures.Set(texture.Code, texture);
                  _folders.Push(subfloder);
               } else if("tl" == type) {
                  // 纹理分层
                  subfloder.Type = "texture.layer";
                  FDrTexture texture = new FDrLayerTexture();
                  subfloder.Label = items[1] + " [" + items[2] + "]";
                  texture.Name = dotPath;
                  texture.Label = items[2];
                  texture.Folder = subfloder;
                  texture.Directory = subfloder.Directory;
                  texture.DirectoryExprot = _exportDirectory;
                  texture.Scan();
                  subfloder.Tag = texture;
                  // 存储对照表
                  _textures.Set(texture.Code, texture);
                  _folders.Push(subfloder);
               } else if ("tt" == type) {
                  // 纹理地形
                  subfloder.Type = "texture.terrain";
                  FDrTexture texture = new FDrTerrainTexture();
                  subfloder.Label = items[1] + " [" + items[2] + "]";
                  texture.Name = dotPath;
                  texture.Label = items[2];
                  texture.Folder = subfloder;
                  texture.Directory = subfloder.Directory;
                  texture.DirectoryExprot = _exportDirectory;
                  texture.Scan();
                  subfloder.Tag = texture;
                  // 存储对照表
                  _textures.Set(texture.Code, texture);
                  _folders.Push(subfloder);
               }
               ScanNodes(subfloder, dotPath);
            }
         }
      }

      //============================================================
      // <T>打开处理。</T>
      //============================================================
      public override void Open() {
         base.Open();
         ScanNodes(_folder as FDrFolder, String.Empty);
      }

      //============================================================
      // <T>添加导出纹理任务。<T>
      //
      // @param texture 纹理
      //============================================================
      public void TaskExport(FDrTexture texture) {
         FRsExportTask task = new FRsExportTask();
         task.Exporter = texture;
         RMoCore.TaskConsole.Push(task);
      }

      //============================================================
      // <T>添加导出集合任务。<T>
      //
      // @param textures 纹理集合
      //============================================================
      public void TaskExports(FVector<FDrTexture> textures) {
         foreach(FDrTexture texture in textures) {
            FRsExportTask task = new FRsExportTask();
            task.Exporter = texture;
            RMoCore.TaskConsole.Push(task);
         }
      }

      //============================================================
      // <T>清空导出目录。</T>
      //============================================================
      public void ExportDirectoryClear() {
         RDirectory.Clear(RContent3dManager.TextureConsole.ExportDirectory);
      }

      //============================================================
      // <T>添加全部导出任务。</T>
      //============================================================
      public void TaskExportAll() {
         // 清除导出目录
         ExportDirectoryClear();
         // 导出纹理
         foreach (INamePair<FDrTexture> pair in _textures) {
            FRsExportTask task = new FRsExportTask();
            task.Label = pair.Value.TypeLabel;
            task.Exporter = pair.Value;
            RMoCore.TaskConsole.Push(task);
         }
      }

      //============================================================
      // <T>存储全部。</T>
      //============================================================
      public void SaveAll() {
         foreach(INamePair<FDrTexture> pair in _textures) {
            pair.Value.Store();
         }
      }

   }
}
