using MO.Common.Collection;
using MO.Content2d.Common;
using MO.Content3d.Common;
using MO.Content3d.Resource.Common;
using MO.Core;
using System;

namespace MO.Content3d.Resource.Theme
{
   //============================================================
   // <T>主题控制台信息。</T>
   //============================================================
   public class FDrThemeConsole : FDrResourceConsole
   {
      // 主题字典
      protected FDictionary<FDrTheme> _themes = new FDictionary<FDrTheme>();

      //============================================================
      // <T>构造主题控制台信息。</T>
      //============================================================
      public FDrThemeConsole() {
         _name = "Content3d.Theme.Console";
      }

      //============================================================
      // <T>获得主题字典。</T>
      //
      // @return 主题字典
      //============================================================
      public FDictionary<FDrTheme> Themes {
         get { return _themes; }
      }

      //============================================================
      // <T>获得默认的主题。</T>
      //
      // @return 主题
      //============================================================
      public FDrTheme DefaultTheme {
         get{
            FDrTheme theme = null;
            if(!_themes.IsEmpty){
               theme = _themes.Value(0);
               theme.Open();
            }
            return theme;
         }
      }

      //============================================================
      // <T>根据名称查找主题。</T>
      //
      // @param name 名称
      // @return 主题
      //============================================================
      public FDrTheme Find(string name) {
         string code = RDrUtil.FormatPathToCode(name);
         return _themes.Find(code);
      }

      //============================================================
      // <T>根据名称查找打开主题。</T>
      //
      // @param name 名称
      // @return 主题
      //============================================================
      public FDrTheme FindOpen(string name) {
         string code = RDrUtil.FormatPathToCode(name);
         FDrTheme theme = _themes.Find(code);
         if (theme != null) {
            theme.Open();
         }
         return theme;
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
         foreach (FDrFolder subfloder in folder.Folders) {
            // 获得经过处理的名称
            string[] items = subfloder.Name.Split('-');
            if (items.Length >= 3) {
               string type = items[0];
               string dotPath = path + "\\" + items[1];
               if("fd" == type) {
                  subfloder.Type = "folder";
                  subfloder.Label = items[1] + " [" + items[2] + "]";
               } else if ("tm" == type) {
                  subfloder.Type = "theme";
                  FDrTheme theme = new FDrTheme();
                  subfloder.Label = items[1] + " [" + items[2] + "]";
                  theme.Name = dotPath;
                  theme.Label = items[2];
                  theme.Directory = subfloder.Directory;
                  theme.DirectoryExprot = _exportDirectory;
                  theme.Scan();
                  subfloder.Tag = theme;
                  // 存储对照表
                  _themes.Set(theme.Code, theme);
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
      // <T>存储全部</T>
      //============================================================
      public void SaveAll() {
         foreach(FDrTheme theme in _themes.Values) {
            theme.Store();
         }
      }

      //============================================================
      // <T>清空导出目录。</T>
      //============================================================
      public void ExportDirectoryClear() {
         //string exportDeflateDirectory = RContent3dManager.ContentConsole.ExportDeflateDirectory + "\\p3.tm";
         //RDirectory.Clear(exportDeflateDirectory);
         //string exportLzmaDirectory = RContent3dManager.ContentConsole.ExportLzmaDirectory + "\\p3.tm";
         //RDirectory.Clear(exportLzmaDirectory);
      }
   
      //============================================================
      // <T>添加全部导出任务。</T>
      //============================================================
      public void TaskExportAll() {
         // 清除导出目录
         ExportDirectoryClear();
         // 导出主题
         foreach (FDrTheme theme in _themes.Values) {
            FRsExportTask task = new FRsExportTask();
            task.Label = theme.TypeLabel;
            task.Exporter = theme;
            RMoCore.TaskConsole.Push(task);
         }
      }
   }
}
