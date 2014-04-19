using System;
using MO.Common.Collection;
using MO.Common.IO;
using MO.Core;
using MO.Content3d.Common;
using MO.Content3d.Resource.Common;

namespace MO.Content3d.Resource.Scene
{
   //============================================================
   // <T>场景控制台信息。</T>
   //============================================================
   public class FDrSceneConsole : FDrResourceConsole
   {
      // 场景字典
      protected FDictionary<FDrSceneGroup> _sceneGroups = new FDictionary<FDrSceneGroup>();

      //============================================================
      // <T>构造场景控制台信息。</T>
      //============================================================
      public FDrSceneConsole() {
         _name = "Content3d.Scene.console";
      }

      //============================================================
      // <T>获得场景字典。</T>
      //
      // @return 场景字典
      //============================================================
      public FDictionary<FDrSceneGroup> SceneGroups {
         get { return _sceneGroups; }
      }

      //============================================================
      // <T>根据名称查找场景。</T>
      //
      // @param name 名称
      // @return 场景
      //============================================================
      public FDrSceneGroup Find(string name) {
         string code = RDrUtil.FormatPathToCode(name);
         return _sceneGroups.Find(code);
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
                  subfloder.Type = "folder";
                  subfloder.Label = items[1] + " [" + items[2] + "]";
               } else if("sc" == type) {
                  subfloder.Type = "scene";
                  FDrSceneGroup group = new FDrSceneGroup();
                  subfloder.Label = items[1] + " [" + items[2] + "]";
                  group.Name = dotPath;
                  group.Label = items[2];
                  group.Directory = subfloder.Directory;
                  group.DirectoryExprot = _exportDirectory;
                  group.Scan();
                  subfloder.Tag = group;
                  // 存储对照表
                  _sceneGroups.Set(group.Code, group);
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
      public void TaskExport(FDrSceneGroup sceneGroup) {
         FDrSceneGroupExportTask task = new FDrSceneGroupExportTask();
         task.SceneGroup = sceneGroup;
         RMoCore.TaskConsole.Push(task);
      }

      //============================================================
      // <T>添加导出集合任务。<T>
      //
      // @param textures 纹理集合
      //============================================================
      public void TaskExports(FVector<FDrSceneGroup> sceneGroups) {
         foreach (FDrSceneGroup sceneGroup in sceneGroups) {
            FDrSceneGroupExportTask task = new FDrSceneGroupExportTask();
            task.SceneGroup = sceneGroup;
            RMoCore.TaskConsole.Push(task);
         }
      }

      //============================================================
      // <T>清空导出目录。</T>
      //============================================================
      public void ExportDirectoryClear() {
         //string exportDeflateDirectory = RContent3dManager.ContentConsole.ExportDeflateDirectory + "\\p3.sc";
         //RDirectory.Clear(exportDeflateDirectory);
         //string exportLzmaDirectory = RContent3dManager.ContentConsole.ExportLzmaDirectory + "\\p3.sc";
         //RDirectory.Clear(exportLzmaDirectory);
      }

      //============================================================
      // <T>添加全部导出任务。</T>
      //============================================================
      public void TaskExportAll() {
         // 清除导出目录
         ExportDirectoryClear();
         // 导出场景
         foreach (INamePair<FDrSceneGroup> pair in _sceneGroups) {
            FDrSceneGroupExportTask task = new FDrSceneGroupExportTask();
            task.Label = pair.Value.TypeLabel;
            task.SceneGroup = pair.Value;
            RMoCore.TaskConsole.Push(task);
         }
      }

      //============================================================
      // <T>存储全部。</T>
      //============================================================
      public void SaveAll() {
         foreach (INamePair<FDrSceneGroup> pair in _sceneGroups) {
            pair.Value.Store();
         }
      }
   }
}
