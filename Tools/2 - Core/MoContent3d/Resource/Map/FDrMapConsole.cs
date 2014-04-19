using System;
using MO.Common.Collection;
using MO.Common.Lang;
using MO.Common.System;
using MO.Core;
using MO.Core.Content.Catalog;
using MO.Content3d.Common;
using MO.Content3d.Resource.Common;
using MO.Content3d.Resource.Map;

namespace MO.Content3d.Resource.Map
{
   //============================================================
   public class FDrMapConsole : FDrResourceConsole
   {
      public FDictionary<FDrMap> _mapDictionary = new FDictionary<FDrMap>();

      protected FCallers _exportBeginCallers = new FCallers();

      protected FCallers _exportProcessCallers = new FCallers();

      protected FCallers _exportEndCallers = new FCallers();

      protected FCallers _importBeginCallers = new FCallers();

      protected FCallers _importProcessCallers = new FCallers();

      protected FCallers _importEndCallers = new FCallers();

      protected FDrMapExportTask _allTasks;

      protected FDrMapExportTask _selectedTasks;

      //============================================================
      // <T>构造模版控制台信息。</T>
      //============================================================
      public FDrMapConsole() {
         _name = "Content3d.Map.Console";
      }

      //============================================================
      public FDictionary<FDrMap> MapDictionary {
         get { return _mapDictionary; }
      }

      //============================================================
      public FCallers ExportBeginCallers {
         get { return _exportBeginCallers; }
      }

      //============================================================
      public FCallers ExportProcessCallers {
         get { return _exportProcessCallers; }
      }

      //============================================================
      public FCallers ExportEndCallers {
         get { return _exportEndCallers; }
      }

      //============================================================
      public FCallers ImportBeginCallers {
         get { return _importBeginCallers; }
      }

      //============================================================
      public FCallers ImportProcessCallers {
         get { return _importProcessCallers; }
      }

      //============================================================
      public FCallers ImportEndCallers {
         get { return _importEndCallers; }
      }

      //============================================================
      public void OnTaskBegin(FDrMapExportTask task) {
         _exportBeginCallers.Process(task);
      }

      //============================================================
      public void OnTaskProcessing(FDrMapExportTask task) {
         _exportProcessCallers.Process(task);
      }

      //============================================================
      public void OnTaskEnd(FDrMapExportTask task) {
         _exportEndCallers.Process(task);
      }

      //============================================================
      public void TaskExport(FDrMap map) {
         FDrMapExportTask task = new FDrMapExportTask(this);
         task.Maps.Push(map);
         RMoCore.TaskConsole.Push(task);
      }

      //============================================================
      public void TaskExport(FVector<FDrMap> maps) {
         FDrMapExportTask task = new FDrMapExportTask(this);
         task.Maps.Append(maps);
         RMoCore.TaskConsole.Push(task);
      }

      //============================================================
      // <T>加载。</T>
      //============================================================
      private void ScanNodes(FCfgFolder folder, string path) {
         string fileTag = string.Empty;
         // 文件夹排序
         folder.Folders.Sort();
         // 循环取得每个文件
         foreach (FCfgFolder subfloder in folder.Folders) {
            // 获得经过处理的名称
            string[] items = subfloder.Name.Split('-');
            if (items.Length >= 3) {
               string type = items[0];
               string dotPath = RString.IsEmpty(path) ? items[1] : path + "." + items[1];
               if ("fd" == type) {
                  subfloder.Type = "folder";
                  subfloder.Label = items[1] + " [" + items[2] + "]";
               } else if ("mp" == type) {
                  subfloder.Type = "map";
                  FDrMap map = new FDrMap();
                  subfloder.Label = items[1] + " [" + items[2] + "]";
                  map.Name = dotPath;
                  map.Label = items[2];
                  map.Path = subfloder.Directory;
                  map.ExprotPath = _exportDirectory;
                  subfloder.Tag = map;
                  // 存储对照表
                  _mapDictionary.Set(dotPath, map);
                  subfloder.Folders.Clear();
               }
               ScanNodes(subfloder, dotPath);
            }
         }
      }

      //============================================================
      // <T>选中存储</T>
      //============================================================
      public void SaveSelected() {
         //if (null != _selectedTasks) {
         //   FVector<FDrMap> maps = _selectedTasks.Maps;
         //   foreach (FDrMap map in maps) {
         //      map.Open();
         //      map.Store();
         //   }
         //   _selectedTasks = null;
         //} else {
         //   FVector<FDrFolder> selecteds = FetchSelected();
         //   foreach (FDrFolder folder in selecteds) {
         //      FDrMap map = folder.Tag as FDrMap;
         //      map.Open();
         //      map.Store();
         //   }
         //}
      }

      //============================================================
      // <T>导出全部</T>
      //============================================================
      public void ExportSelected() {
         //FDrMapExportTask task = new FDrMapExportTask(this);
         //FVector<FDrFolder> selecteds = FetchSelected();
         //foreach (FDrFolder folder in selecteds) {
         //   FDrMap map = folder.Tag as FDrMap;
            
         //}
         //RMoCore.TaskConsole.Push(task);
         //_selectedTasks = task;
      }

      //============================================================
      public override void Open() {
         base.Open();
         ScanNodes(_folder, String.Empty);
      }

      //============================================================
      // <T>导出全部</T>
      //============================================================
      public void ExportAll() {
         foreach (INamePair<FDrMap> pair in _mapDictionary) {
            FDrMap map = pair.Value;
            map.Open();
            map.Export();
         }
      }

      //============================================================
      // <T>存储全部</T>
      //============================================================
      public void SaveAll() {
         foreach (INamePair<FDrMap> pair in _mapDictionary) {
            FDrMap map = pair.Value;
            map.Open();
            map.Store();
         }
      }
   }      
}
