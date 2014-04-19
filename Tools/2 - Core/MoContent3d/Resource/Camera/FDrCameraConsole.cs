using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.IO;
using MO.Content2d.Common;
using MO.Core;
using MO.Content3d.Common;
using MO.Content3d.Resource.Common;
using System;

namespace MO.Content3d.Resource.Camera
{
   //============================================================
   // <T>模版控制台信息。</T>
   //============================================================
   public class FDrCameraConsole : FDrResourceConsole
   {
      protected string _pathHttp;

      protected string _exportTemplate;

      protected string _exportScene;

      // 模板字典
      public FDictionary<FDrCamera> _cameras = new FDictionary<FDrCamera>();

      //============================================================
      // <T>构造模版控制台信息。</T>
      //============================================================
      public FDrCameraConsole() {
         _name = "Content3d.Camera.Console";
      }

      //============================================================
      // <T>获得模板字典。</T>
      //
      // @return 模板字典
      //============================================================
      public FDictionary<FDrCamera> Templates {
         get { return _cameras; }
      }

      //============================================================
      // <T>根据名称查找模板。</T>
      //
      // @param name 名称
      // @return 模板
      //============================================================
      public FDrCamera Find(string name) {
         string code = RDrUtil.FormatPathToCode(name);
         return _cameras.Find(code);
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
         foreach (FDrFolder subfloder in folder.Folders) {
            // 获得经过处理的名称
            string[] items = subfloder.Name.Split('-');
            if (items.Length >= 3) {
               string type = items[0];
               string dotPath = path + "\\" + items[1];
               if ("fd" == type) {
                  subfloder.Type = "folder";
                  subfloder.Label = items[1] + " [" + items[2] + "]";
               } else if ("tp" == type) {
                  subfloder.Type = "camera";
                  FDrCamera camera = new FDrCamera();
                  subfloder.Label = items[1] + " [" + items[2] + "]";
                  camera.Name = dotPath;
                  camera.Label = items[2];
                  camera.Directory = subfloder.Directory;
                  camera.Scan();
                  subfloder.Tag = camera;
                  // 存储对照表
                  _cameras.Set(camera.Code, camera);
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
      // <T>添加任务对象<T>
      //
      // @param camera 相机
      //============================================================
      public void TaskExport(FDrCamera camera) {
         FRsExportTask task = new FRsExportTask();
         task.Exporter = camera;
         task.Label = "Camera";
         RMoCore.TaskConsole.Push(task);
      }

      //============================================================
      // <T>添加任务集合<T>
      //
      // @param cameras 相机集合
      //============================================================
      public void TaskExport(FVector<FDrCamera> cameras) {
         foreach (FDrCamera camera in cameras) {
            FRsExportTask task = new FRsExportTask();
            task.Label = "Camera";
            task.Exporter = camera;
            RMoCore.TaskConsole.Push(task);
         }
      }

      //============================================================
      public override void LoadConfig(FXmlNode config) {
         base.LoadConfig(config);
         foreach(FXmlNode node in config.Nodes) {
            if(node.IsAttribute("name", "path.http")) {
               _pathHttp = node.Text;
            }
            if(node.IsAttribute("name", "export.template")) {
               _exportTemplate = node.Text;
            }
            if(node.IsAttribute("name", "export.scene")) {
               _exportScene = node.Text;
            }
         }
      }

      //============================================================
      // <T>导出全部</T>
      //============================================================
      public void TaskExportAll() {
         // 清除文件
         RDirectory.Clear(_exportDirectory);
         // 导出模板
         foreach (INamePair<FDrCamera> pair in _cameras) {
            FRsExportTask task = new FRsExportTask();
            task.Label = pair.Value.TypeLabel;
            task.Exporter = pair.Value;
            RMoCore.TaskConsole.Push(task);
         }
      }

      //============================================================
      // <T>存储全部</T>
      //============================================================
      public void SaveAll() {
         foreach(INamePair<FDrCamera> pair in _cameras) {
            pair.Value.Store();
         }
      }
   }
}
