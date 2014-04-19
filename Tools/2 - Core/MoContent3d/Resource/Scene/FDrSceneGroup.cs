using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Common;
using MO.Content3d.Resource.Common;
using System;

namespace MO.Content3d.Resource.Scene
{
   //============================================================
   // <T>场景组信息。</T>
   //============================================================
   public class FDrSceneGroup : FDrResource, IDisposable
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FDrSceneGroup));

      // 场景集合
      protected FObjects<FDrScene> _scenes = new FObjects<FDrScene>();

      // 导出文件名称
      protected string _exportFileName;

      // 导出数据名称
      protected string _exportFileDataName;

      //============================================================
      // <T>构造场景组信息。</T>
      //============================================================
      public FDrSceneGroup() {
      }

      //============================================================
      // <T>获得场景集合。</T>
      //============================================================
      public FObjects<FDrScene> Scenes {
         get { return _scenes; }
      }

      //============================================================
      // <T>扫描工作路径，获得对象列表。</T>
      //============================================================
      public override void Scan() {
         base.Scan();
         // 检查文件集合
         FStrings fileNames = RDirectory.ListFiles(_directory);
         foreach (string fileName in fileNames) {
            if (fileName.EndsWith(".xml")) {
               string name = RFile.GetFileName(fileName);
               name = name.Substring(0, name.Length - 4);
               if (name.StartsWith("import.") || name.StartsWith("temp.")) {
                  continue;
               }
               FDrScene scene = new FDrScene();
               scene.Name = _name;
               scene.Label = _label;
               scene.TechniqueName = name;
               scene.Directory = _directory;
               scene.DirectoryExprot = _directoryExprot;
               scene.Scan();
               _scenes.Push(scene);
            }
         }
         // 设置目录
         _exportFileName = _directoryExprot + "\\sc_" + Code + ".swf";
         // 加载设置文件
         if(RFile.Exists(_configFileName)) {
            
            //LoadConfig(new FXmlDocument(_directory + "\\config.xml").Root);
         }
      }

      //============================================================
      // <T>打开数据内容。</T>
      //============================================================
      public override void Open() {
         if(!_opened) {
            base.Open();
         }
      }

      //============================================================
      // <T>保存设置文件。</T>
      //============================================================
      public void SaveConfigFile(string fileName) {
         FXmlDocument xdoc = new FXmlDocument();
         FXmlNode xroot = xdoc.Root;
         //SaveConfig(xroot.CreateNode("Texture"));
         xdoc.SaveFile(fileName);
      }

      //============================================================
      // <T>保存内容。</T>
      //============================================================
      public void Store() {
         // 存储信息
         FXmlDocument xdoc = new FXmlDocument();
         FXmlNode xroot = xdoc.Root;
         xroot.Name = "Scene";
         xroot.Set("version", "1.0");
         //SaveConfig(xroot);
         xdoc.SaveFile(_configFileName);
         _logger.Debug(this, "SaveConfig", "Save Texture config success. (file_name={0})", _configFileName);
      }

      //============================================================
      // <T>导入设置信息。</T>
      //============================================================
      public void ImportConfig() {
         Dispose();
      }

      //============================================================
      // <T>导入材质信息。</T>
      //============================================================
      public void ImportMaterial() {
         Open();
         // 压缩文件
         //foreach(FDrSceneDisplay display in _space.Displays){
         //   foreach (FDrSceneMaterial sceneMaterial in display.Materials) {
         //      // 获得场景
         //      FDrMaterial material = RContent3dManager.MaterialConsole.Find(sceneMaterial.Name);
         //      // 设置数据
         //      material.Ambient.Assign(sceneMaterial.Ambient);
         //      material.Diffuse.Assign(sceneMaterial.Diffuse);
         //      material.DiffuseView.Assign(sceneMaterial.DiffuseView);
         //      material.Specular.Assign(sceneMaterial.Specular);
         //      material.SpecularBase = sceneMaterial.SpecularBase;
         //      material.SpecularRate = sceneMaterial.SpecularRate;
         //      material.SpecularAverage = sceneMaterial.SpecularAverage;
         //      material.SpecularShadow = sceneMaterial.SpecularShadow;
         //      material.SpecularView.Assign(sceneMaterial.SpecularView);
         //      material.SpecularViewBase = sceneMaterial.SpecularViewBase;
         //      material.SpecularViewRate = sceneMaterial.SpecularViewRate;
         //      material.SpecularViewAverage = sceneMaterial.SpecularViewAverage;
         //      material.SpecularViewShadow = sceneMaterial.SpecularViewShadow;
         //      material.Store();
         //   }
         //}
         //_logger.Debug(this, "Export", "Export scene success. (file_name={0})", _exportFileName);
      }
      
      //============================================================
      // <T>根据指定模式导出数据。</T>
      //
      // @param modeCd 导出模式
      //============================================================
      public override void Export(ERsExportMode modeCd){
         // 打开资源
         Open();
         // 压缩文件
         foreach (FDrScene scene in _scenes){
            scene.Export(modeCd);
         }
         _logger.Debug(this, "Export", "Export scene success. (file_name={0})", _exportFileName);
         // 释放资源
         Dispose();
      }

      //============================================================
      // <T>释放内容。</T>
      //============================================================
      public override void Dispose() {
         if(_opened) {
            // 释放内容
            base.Dispose();
         }
      }
   }
}
