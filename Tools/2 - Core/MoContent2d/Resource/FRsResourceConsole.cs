using MO.Common;
using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Common.System;
using MO.Content2d.Common;
using MO.Content2d.Resource.Animation;
using MO.Content2d.Resource.Common;
using MO.Content2d.Resource.Music;
using MO.Content2d.Resource.Picture;
using MO.Content2d.Resource.Sound;
using MO.Core;

namespace MO.Content2d.Content
{
   //============================================================
   // <T>内容控制台。</T>   
   //============================================================
   public class FRsResourceConsole : FConsole
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FResourceConsole));

      // 资源目录
      protected string _resourceDirectory;

      // 导出目录
      protected string _exportDirectory;

      // 合并目录
      protected string _mergeDirectory;

      // 资源目录集合
      protected FObjects<FRsResourceFolder> _folders = new FObjects<FRsResourceFolder>();

      // 全部目录集合
      protected FObjects<FRsResourceFolder> _allFolders = new FObjects<FRsResourceFolder>();

      // 资源字典
      protected FRsResourceDictionary _resources = new FRsResourceDictionary();

      // 资源包集合
      protected FDictionary<FRsResourceGroup> _groupDictionary = new FDictionary<FRsResourceGroup>();

      //============================================================
      // <T>构造资源控制台。</T>   
      //============================================================
      public FRsResourceConsole() {
         _name = "Content.Resource.Console";
      }

      //============================================================
      // <T>获得资源目录。</T>
      //============================================================
      public string ResourceDirectory {
         get { return _resourceDirectory; }
      }

      //============================================================
      // <T>获得导出目录。</T>
      //============================================================
      public string ExportDirectory {
         get { return _exportDirectory; }
      }

      //============================================================
      // <T>获得合并目录。</T>
      //============================================================
      public string MergeDirectory {
         get { return _mergeDirectory; }
      }

      //============================================================
      // <T>获得资源目录集合。</T>
      //============================================================
      public FObjects<FRsResourceFolder> Folders {
         get { return _folders; }
      }

      //============================================================
      // <T>获得资源字典。</T>
      //============================================================
      public FRsResourceDictionary Resources {
         get { return _resources; }
      }

      //============================================================
      // <T>获得资源组集合。</T>
      //============================================================
      public FDictionary<FRsResourceGroup> GroupDictionary {
         get { return _groupDictionary; }
      }

      //============================================================
      // <T>创建资源对象。</T>
      //
      // @param typeCd 资源类型
      // @param code 代码
      //============================================================
      public FRsResource CreateResource(int typeCd, int code) {
         // 创建资源
         FRsResource resource = null;
         switch (typeCd) {
            case ERsResource.Picture:
               resource = new FRsResourcePicture();
               break;
            case ERsResource.Animation:
               resource = new FRsResourceAnimation();
               break;
            case ERsResource.Sound:
               resource = new FRsResourceSound();
               break;
            case ERsResource.Music:
               resource = new FRsResourceMusic();
               break;
         }
         // 检查存在性
         if (_resources.Contains(code)) {
            RMoCore.TrackConsole.Write(this, "CreateResource", "Duplicate resource code. (code={0})", code);
         }
         // 设置对照表
         _resources.Set(code, resource);
         return resource;
      }

      //============================================================
      // <T>根据代码查找资源对象。</T>
      //
      // @param code 代码
      // @return 资源对象
      //============================================================
      public FRsResource Find(int code) {
         return _resources.Find(code);
      }

      //============================================================
      // <T>根据代码查找打开的资源对象。</T>
      //
      // @param code 代码
      // @return 资源对象
      //============================================================
      public FRsResource FindOpen(int code) {
         FRsResource resource = _resources.Find(code);
         if (resource != null) {
            resource.Open();
         }
         return resource;
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置节点
      //============================================================
      public override void LoadConfig(FXmlNode xconfig) {
         foreach (FXmlNode xnode in xconfig.Nodes) {
            if (xnode.IsName("Property")) {
               string name = xnode.Get("name");
               // 创建资源文件夹
               switch (name) {
                  case "resource_directory":
                     FRsResourceFolder folder = new FRsResourceFolder();
                     folder.Directory = RMoCommon.ParseEnvironment(xnode.Text);
                     _folders.Push(folder);
                     break;
                  case "export_directory":
                     _exportDirectory = RMoCommon.ParseEnvironment(xnode.Text);
                     break;
                  case "merge_directory":
                     _mergeDirectory = RMoCommon.ParseEnvironment(xnode.Text);
                     break;
               }
            }
         }
      }

      //============================================================
      // <T>递归加载文件信息。</T>
      // 
      // @param folders 文件集合
      // @author TYFNG 1203024
      //============================================================
      protected void ScanFolder(FRsResourceFolder parentFolder) {
         // 处理所有子文件夹
         FStrings directoryNames = RDirectory.ListDirectories(parentFolder.Directory);
         foreach (string directoryName in directoryNames) {
            if (-1 != directoryName.IndexOf(".svn")) {
               continue;
            }
            string fileName = directoryName.Substring(directoryName.LastIndexOf("\\") + 1);
            // 解析动画资源
            if (fileName.StartsWith("RA-")) {
               string name = fileName.Substring(3);
               string fullName = name;
               string label = name;
               int codeIndex = name.IndexOf("-");
               if (-1 != codeIndex) {
                  name = name.Substring(0, codeIndex);
               }
               name = name.Replace(".", "");
               // 创建图片资源
               int code = RInt.Parse(name);
               FRsResourceAnimation animation = CreateResource(ERsResource.Animation, code) as FRsResourceAnimation;
               animation.Folder = parentFolder;
               animation.Code = code;
               animation.Name = name;
               animation.Label = label;
               animation.FullLabel = parentFolder.FullLabel + "\\" + label;
               animation.Keyword = label.Replace(".", "");
               animation.Directory = directoryName;
               animation.Scan();
               parentFolder.Resources.Push(animation);
            } else {
               // 创建子文件夹
               FRsResourceFolder folder = new FRsResourceFolder();
               folder.Label = fileName;
               folder.FullLabel = parentFolder.FullLabel + "\\" + fileName;
               folder.Directory = directoryName;
               parentFolder.Folders.Push(folder);
               // 扫描子文件夹
               ScanFolder(folder);
            }
         }
         // 扫描文件夹
         parentFolder.Scan();
      }

      //============================================================
      // <T>扫描资源控制台。</T>
      //============================================================
      public void Open() {
         foreach (FRsResourceFolder folder in _folders) {
            _logger.Debug(this, "Open", "Scan folder. (directory={0})", folder.Directory);
            ScanFolder(folder);
         }
      }

      //============================================================
      // <T>存储所有资源信息。</T>
      //============================================================
      public void SaveAll() {
         //foreach (FRsResource resource in _resources.Values) {
         //   resource.Store();
         //}
      }

      //============================================================
      // <T>导出所有资源。</T>
      //
      // @param modeCd 导出模式
      //============================================================
      public void TaskExportAll(ERsExportMode modeCd) {
         // 清除导出目录
         RDirectory.Clear(_exportDirectory);
         // 导出资源
         foreach (FRsResource resource in _resources.Values) {
            //if (!resource.OptionExport) {
            //   continue;
            //}
            if (resource is FRsResourcePicture) {
               FRsExportTask task = new FRsExportTask();
               task.Exporter = resource;
               task.ModeCd = modeCd;
               RMoCore.TaskConsole.Push(task);
            }
            if (resource is FRsResourceAnimation) {
               FRsExportTask task = new FRsExportTask();
               task.Exporter = resource;
               task.ModeCd = modeCd;
               RMoCore.TaskConsole.Push(task);
            }
            if (resource is FRsResourceSound) {
               FRsExportTask task = new FRsExportTask();
               task.Exporter = resource;
               task.ModeCd = modeCd;
               RMoCore.TaskConsole.Push(task);
            }
            if (resource is FRsResourceMusic) {
               FRsExportTask task = new FRsExportTask();
               task.Exporter = resource;
               task.ModeCd = modeCd;
               RMoCore.TaskConsole.Push(task);
            }
         }
      }
   }
}
