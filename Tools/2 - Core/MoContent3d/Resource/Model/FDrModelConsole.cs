using MO.Common.Collection;
using MO.Content2d.Common;
using MO.Core;
using MO.Content3d.Common;
using MO.Content3d.Resource.Common;
using System;

namespace MO.Content3d.Resource.Model
{
   //============================================================
   // <T>模型控制台信息。</T>
   //============================================================
   public class FDrModelConsole : FDrResourceConsole
   {
      // 模型字典
      protected FDictionary<FDrModel> _models = new FDictionary<FDrModel>();

      //============================================================
      // <T>构造模型控制台信息。</T>
      //============================================================
      public FDrModelConsole() {
         _name = "Content3d.Model.Console";
      }

      //============================================================
      // <T>获得模型字典。</T>
      //
      // @return 材质
      //============================================================
      public FDictionary<FDrModel> Models {
         get { return _models; }
      }

      //============================================================
      // <T>根据名称查找模型。</T>
      //
      // @param name 名称
      // @return 模型
      //============================================================
      public FDrModel Find(string name) {
         string code = RDrUtil.FormatPathToCode(name);
         return _models.Find(code);
      }

      //============================================================
      // <T>扫描所有节点。</T>
      //
      // @param folder 文件夹
      // @param path 路径
      //============================================================
      private void ScanNodes(FDrFolder folder, string path) {
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
               } else if("md" == type) {
                  subfloder.Type = "material";
                  FDrModel model = new FDrModel();
                  subfloder.Label = items[1] + " [" + items[2] + "]";
                  model.Name = dotPath;
                  model.Label = items[2];
                  model.Directory = subfloder.Directory;
                  model.DirectoryExprot = _exportDirectory;
                  model.Scan();
                  subfloder.Tag = model;
                  // 存储对照表
                  _models.Set(model.Code, model);
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
         ScanNodes(_folder, String.Empty);
      }

      //============================================================
      // <T>添加任务对象<T>
      //
      // @param model 模型
      //============================================================
      public void TaskExport(FDrModel model) {
         FRsExportTask task = new FRsExportTask();
         task.Exporter = model;
         RMoCore.TaskConsole.Push(task);
      }

      //============================================================
      // <T>添加任务集合<T>
      //
      // @param models 模型集合
      //============================================================
      public void TaskExport(FVector<FDrModel> models) {
         foreach(FDrModel model in models) {
            FRsExportTask task = new FRsExportTask();
            task.Exporter = model;
            RMoCore.TaskConsole.Push(task);
         }
      }

      //============================================================
      // <T>清空导出目录。</T>
      //============================================================
      public void ExportDirectoryClear() {
         //string exportDeflateDirectory = RContent3dManager.ContentConsole.ExportDeflateDirectory + "\\p3.md";
         //RDirectory.Clear(exportDeflateDirectory);
         //string exportLzmaDirectory = RContent3dManager.ContentConsole.ExportLzmaDirectory + "\\p3.md";
         //RDirectory.Clear(exportLzmaDirectory);
      }

      //============================================================
      // <T>导出全部。</T>
      //============================================================
      public void TaskExportAll() {
         // 清除导出目录
         ExportDirectoryClear();
         // 导出模型
         foreach (INamePair<FDrModel> pair in _models) {
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
         foreach(INamePair<FDrModel> pair in _models) {
            pair.Value.Store();
         }
      }
   }
}
