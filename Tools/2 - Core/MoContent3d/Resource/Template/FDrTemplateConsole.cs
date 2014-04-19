using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.IO;
using MO.Content2d.Common;
using MO.Core;
using MO.Content3d.Common;
using MO.Content3d.Resource.Common;
using System;

namespace MO.Content3d.Resource.Template
{
   //============================================================
   // <T>模版控制台信息。</T>
   //============================================================
   public class FDrTemplateConsole : FDrResourceConsole
   {
      protected string _pathHttp;

      protected string _exportTemplate;

      protected string _exportScene;

      // 模板字典
      public FDictionary<FDrTemplate> _templates = new FDictionary<FDrTemplate>();

      //============================================================
      // <T>构造模版控制台信息。</T>
      //============================================================
      public FDrTemplateConsole() {
         _name = "Content3d.Template.Console";
      }

      //============================================================
      // <T>获得模板字典。</T>
      //
      // @return 模板字典
      //============================================================
      public FDictionary<FDrTemplate> Templates {
         get { return _templates; }
      }

      //============================================================
      // <T>根据名称查找模板。</T>
      //
      // @param name 名称
      // @return 模板
      //============================================================
      public FDrTemplate Find(string name) {
         string code = RDrUtil.FormatPathToCode(name);
         return _templates.Find(code);
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
               } else if("tp" == type) {
                  subfloder.Type = "template";
                  FDrTemplate template = new FDrTemplate();
                  subfloder.Label = items[1] + " [" + items[2] + "]";
                  template.Name = dotPath;
                  template.Label = items[2];
                  template.Directory = subfloder.Directory;
                  // template.ExportPath = _exportDirectory;
                  template.Scan();
                  subfloder.Tag = template;
                  // 存储对照表
                  _templates.Set(template.Code, template);
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
      // @param template 模板
      //============================================================
      public void TaskExport(FDrTemplate template) {
         FRsExportTask task = new FRsExportTask();
         task.Exporter = template;
         RMoCore.TaskConsole.Push(task);
      }

      //============================================================
      // <T>添加任务集合<T>
      //
      // @param templates 模板集合
      //============================================================
      public void TaskExport(FVector<FDrTemplate> templates) {
         foreach(FDrTemplate template in templates) {
            FRsExportTask task = new FRsExportTask();
            task.Exporter = template;
            RMoCore.TaskConsole.Push(task);
         } 
      }

      //============================================================
      // <T>添加任务集合<T>
      //
      // @param textures 任务集合
      //============================================================
      public void TaskImprotExport(FDrTemplate template) {
         FDrTemplateImportTask task = new FDrTemplateImportTask();
         task.Template = template;
         RMoCore.TaskConsole.Push(task);
      }

      //============================================================
      public string PathHttp {
         get { return _pathHttp; }
      }

      //============================================================
      public string ExportTemplate {
         get { return _exportTemplate; }
      }

      //============================================================
      public string ExportScene {
         get { return _exportScene; }
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
      // <T>清空导出目录。</T>
      //============================================================
      public void ExportDirectoryClear() {
         //string exportDeflateDirectory = RContent3dManager.ContentConsole.ExportDeflateDirectory + "\\p3.tp";
         //RDirectory.Clear(exportDeflateDirectory);
         //string exportLzmaDirectory = RContent3dManager.ContentConsole.ExportLzmaDirectory + "\\p3.tp";
         //RDirectory.Clear(exportLzmaDirectory);
      }

      //============================================================
      // <T>导出全部</T>
      //============================================================
      public void TaskExportAll() {
         // 清除导出目录
         ExportDirectoryClear();
         // 导出模板
         foreach (INamePair<FDrTemplate> pair in _templates) {
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
         foreach(INamePair<FDrTemplate> pair in _templates) {
            pair.Value.Store();
         }
      }
   }
}
