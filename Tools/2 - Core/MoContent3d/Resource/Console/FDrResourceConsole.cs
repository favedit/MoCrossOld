using MO.Common.Content;
using MO.Common.Lang;
using MO.Common.System;
using MO.Content2d.Common;
using MO.Core;
using MO.Content3d.Resource.Model;
using MO.Content3d.Resource.Scene;
using MO.Content3d.Resource.Template;
using MO.Content3d.Resource.Texture;
using MO.Content3d.Resource.Theme;

namespace MO.Content3d.Resource.Console
{
   //============================================================
   // <T>资源控制台。</T>   
   //============================================================
   public class FDrResourceConsole : FConsole
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FDrResourceConsole));

      // 资源导出目录
      protected string _exportResourceDirectory;

      //============================================================
      // <T>构造资源控制台。</T>   
      //============================================================
      public FDrResourceConsole() {
         _name = "resource3d.resource.console";
      }

      //============================================================
      // <T>获得资源导出目录。</T>
      //============================================================
      public string ExportResourceDirectory {
         get { return _exportResourceDirectory; }
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
                  case "export.resource.directory":
                     _exportResourceDirectory = xnode.Text;
                     break; ;
               }
            }
         }
      }

      //============================================================
      // <T>导出所有资源。</T>
      //============================================================
      public void TaskExportAll() {
         // 清除目录
         RContent3dManager.TextureConsole.ExportDirectoryClear();
         RContent3dManager.ModelConsole.ExportDirectoryClear();
         RContent3dManager.TemplateConsole.ExportDirectoryClear();
         RContent3dManager.SenceConsole.ExportDirectoryClear();
         RContent3dManager.ThemeConsole.ExportDirectoryClear();
         // 导出纹理
         foreach (FDrTexture texture in RContent3dManager.TextureConsole.Textures.Values) {
            //if (texture.OptionExport) {
               FRsExportTask task = new FRsExportTask();
               task.ModeCd = ERsExportMode.Storage;
               task.Label = texture.TypeLabel;
               task.Exporter = texture;
               RMoCore.TaskConsole.Push(task);
            //}
         }
         // 导出模型
         foreach (FDrModel model in RContent3dManager.ModelConsole.Models.Values) {
            //if (model.OptionExport) {
               FRsExportTask task = new FRsExportTask();
               task.ModeCd = ERsExportMode.Storage;
               task.Label = model.TypeLabel;
               task.Exporter = model;
               RMoCore.TaskConsole.Push(task);
            //}
         }
         // 导出模板
         foreach (FDrTemplate template in RContent3dManager.TemplateConsole.Templates.Values) {
            //if (template.OptionExport) {
               FRsExportTask task = new FRsExportTask();
               task.Label = template.TypeLabel;
               task.Exporter = template;
               task.ModeCd = ERsExportMode.Storage;
               RMoCore.TaskConsole.Push(task);
            //}
         }
         // 导出场景
         foreach (FDrSceneGroup sceneGroup in RContent3dManager.SenceConsole.SceneGroups.Values) {
            FRsExportTask task = new FRsExportTask();
            task.ModeCd = ERsExportMode.Storage;
            task.Label = sceneGroup.TypeLabel;
            task.Exporter = sceneGroup;
            RMoCore.TaskConsole.Push(task);
         }
         // 导出主题
         foreach (FDrTheme theme in RContent3dManager.ThemeConsole.Themes.Values) {
            FRsExportTask task = new FRsExportTask();
            task.ModeCd = ERsExportMode.Storage;
            task.Label = theme.TypeLabel;
            task.Exporter = theme;
            RMoCore.TaskConsole.Push(task);
         }
      }
   }
}

