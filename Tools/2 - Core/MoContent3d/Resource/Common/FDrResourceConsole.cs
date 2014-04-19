using MO.Common;
using MO.Common.Content;
using MO.Common.Lang;
using MO.Common.System;
using MO.Content3d.Common;
using MO.Core.Content.Catalog;

namespace MO.Content3d.Resource.Common
{
   //============================================================
   // <T>资源控制台基础类。</T>
   //============================================================
   public class FDrResourceConsole : FConsole
   {
      // 资源目录
      protected string _resourceDirectory;

      // 导出目录
      protected string _exportDirectory;

      // 导出目录
      protected string _exportResourceDirectory;

      // 文件夹
      protected FDrFolder _folder = new FDrFolder();

      // 文件夹集合
      protected FObjects<FDrFolder> _folders = new FObjects<FDrFolder>();

      //============================================================
      // <T>构造资源控制台基础类。</T>
      //============================================================
      public FDrResourceConsole() {
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
      // <T>获得导出资源目录。</T>
      //============================================================
      public string ExportResourceDirectory {
         get { return _exportResourceDirectory; }
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置节点
      //============================================================
      public override void LoadConfig(FXmlNode config) {
         // 读取设置
         foreach (FXmlNode node in config.Nodes) {
            string name = node.Get("name");
            if(node.IsAttribute("name", "resource.directory")) {
               _resourceDirectory = RMoCommon.ParseEnvironment(node.Text);
            }
            if(node.IsAttribute("name", "export.directory")) {
               _exportDirectory = RMoCommon.ParseEnvironment(node.Text);
            }
            if (node.IsAttribute("name", "export.resource.directory")) {
               _exportResourceDirectory = RMoCommon.ParseEnvironment(node.Text);
            }
         }
         // 设置根路径
         _folder.Directory = _resourceDirectory;
      }

      //============================================================
      // <T>打开处理。</T>
      //============================================================
      public virtual void Open() {
         _folder.LoadAll();
      }

      //============================================================
      // <T>获得打开处理。</T>
      //============================================================
      public FCfgFolder Folder {
         get { return _folder; }
      }
   }
}
