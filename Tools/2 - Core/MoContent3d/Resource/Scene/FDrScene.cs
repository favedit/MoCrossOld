using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Common;
using MO.Content2d.Core;
using MO.Core;
using MO.Content3d.Common;
using MO.Content3d.Resource.Common;
using MO.Content3d.Resource.Material;
using System;

namespace MO.Content3d.Resource.Scene
{
   //============================================================
   // <T>场景信息。</T>
   //============================================================
   public class FDrScene : FDrResource, IDisposable
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FDrScene));

      // 主题名称
      protected string _themeName = "shadow";

      // 技术名称
      protected string _techniqueName = "shadow.map";

      // 技术
      protected FDrSceneTechnique _technique = new FDrSceneTechnique();

      // 区域
      protected FDrSceneRegion _region = new FDrSceneRegion();

      // 天空
      protected FDrSceneSky _sky = new FDrSceneSky();

      // 地图
      protected FDrSceneMap _map = new FDrSceneMap();

      // 空间
      protected FDrSceneSpace _space = new FDrSceneSpace();

      // 导出文件名称
      protected string _exportFileName;

      // 导出数据名称
      protected string _exportFileDataName;

      //============================================================
      // <T>构造场景信息。</T>
      //============================================================
      public FDrScene() {
         _typeName = EDrResourceType.Scene;
         _typeLabel = "Scene";
         _sky.Scene = this;
         _map.Scene = this;
         _space.Scene = this;
      }

      //============================================================
      // <T>获得主题代码。</T>
      //============================================================
      public string ThemeCode {
         get { return RDrUtil.FormatPathToCode(_themeName); }
      }

      //============================================================
      // <T>获得或设置主题名称。</T>
      //============================================================
      public string ThemeName {
         get { return _themeName; }
         set { _themeName = value; }
      }

      //============================================================
      // <T>获得或设置技术名称。</T>
      //============================================================
      public string TechniqueName {
         get { return _techniqueName; }
         set { _techniqueName = value; }
      }

      //============================================================
      // <T>获得技术。</T>
      //============================================================
      public FDrSceneTechnique Technique {
         get { return _technique; }
      }

      //============================================================
      // <T>获得区域。</T>
      //============================================================
      public FDrSceneRegion Region {
         get { return _region; }
      }

      //============================================================
      // <T>获得天空。</T>
      //============================================================
      public FDrSceneSky Sky {
         get { return _sky; }
      }

      //============================================================
      // <T>获得地图。</T>
      //============================================================
      public FDrSceneMap Map {
         get { return _map; }
      }

      //============================================================
      // <T>获得空间。</T>
      //============================================================
      public FDrSceneSpace Space {
         get { return _space; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadOrignConfig(FXmlNode xconfig) {
         // 读取子节点
         foreach(FXmlNode xnode in xconfig.Nodes) {
            // 读取区域信息
            if(xnode.IsName("Region")) {
               // 读取技术信息
               _technique.Name = xnode.Get("technique");
               FXmlNode xtechnique = xnode.Find("Technique");
               if(null != xtechnique) {
                  _technique.LoadOrignConfig(xtechnique);
               }
               // 读取区域设置
               _region.LoadOrignConfig(xnode);
            }
            // 读取天空信息
            if (xnode.IsName("Sky")) {
               _sky.LoadOrignConfig(xnode);
            }
            // 读取地图信息
            if(xnode.IsName("Map")) {
               _map.LoadOrignConfig(xnode);
            }
            // 读取区域信息
            if (xnode.IsName("Space")) {
               _space.LoadOrignConfig(xnode);
            }
         }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         // 存储属性
         _themeName = xconfig.Get("theme_code", _themeName);
         // 读取子节点
         foreach(FXmlNode xnode in xconfig.Nodes) {
            // 读取技术信息
            if(xnode.IsName("Technique")) {
               _technique.LoadConfig(xnode);
            }
            // 读取区域信息
            if(xnode.IsName("Region")) {
               _region.LoadConfig(xnode);
            }
            // 读取天空信息
            if (xnode.IsName("Sky")) {
               _sky.LoadConfig(xnode);
            }
            // 读取地图信息
            if(xnode.IsName("Map")) {
               _map.LoadConfig(xnode);
            }
            // 读取区域信息
            if(xnode.IsName("Space")) {
               _space.LoadConfig(xnode);
            }
         }
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         // 存储属性
         xconfig.Set("theme_code", _themeName);
         // 存储技术信息
         _technique.SaveConfig(xconfig.CreateNode("Technique"));
         // 存储区域信息
         _region.SaveConfig(xconfig.CreateNode("Region"));
         // 存储天空信息
         _sky.SaveConfig(xconfig.CreateNode("Sky"));
         // 存储地图信息
         _map.SaveConfig(xconfig.CreateNode("Map"));
         // 存储空间信息
         _space.SaveConfig(xconfig.CreateNode("Space"));
      }

      //============================================================
      // <T>扫描工作路径，获得对象列表。</T>
      //============================================================
      public override void Scan() {
         base.Scan();
         _configFileName = _directory + "\\" + _techniqueName + ".xml";
         // 设置目录
         if (_techniqueName == "config") {
            _exportFileName = _directoryExprot + "\\sc_" + Code + ".swf";
         } else {
            _exportFileName = _directoryExprot + "\\sc_" + Code + "." + _techniqueName + ".swf";
         }
         // 加载设置文件
         if (RFile.Exists(_configFileName)) {
            string fileName = _directory + "\\" + _techniqueName + ".xml";
            try {
               LoadConfig(new FXmlDocument(fileName).Root);
            } catch (Exception e){
               RMoCore.TrackConsole.Write(this, "Scan", "Load config file failure. (file_name={0}, error={1})", fileName, e.Message);
               LoadConfig(new FXmlDocument(fileName).Root);
            }
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
         SaveConfig(xroot.CreateNode("Texture"));
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
         SaveConfig(xroot);
         xdoc.SaveFile(_configFileName);
         _logger.Debug(this, "SaveConfig", "Save Texture config success. (file_name={0})", _configFileName);
      }

      //============================================================
      // <T>序列化内部数据到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public override void Serialize(IOutput output) {
         base.Serialize(output);
         // 输出属性
         output.WriteString(_themeName);
         // 输出配置
         _technique.Serialize(output);
         _region.Serialize(output);
         _sky.Serialize(output);
         _map.Serialize(output);
         _space.Serialize(output);
      }

      //============================================================
      // <T>导入设置信息。</T>
      //============================================================
      public void ImportConfig() {
         Dispose();
         // 加载设置文件
         string fileName = _directory + "\\import." + _techniqueName + ".xml";
         if(RFile.Exists(fileName)) {
            LoadOrignConfig(new FXmlDocument(fileName).Root);
         }
         // 压缩文件
         FCompressFile file = new FCompressFile();
         Serialize(file);
         file.Compress(_exportFileName);
         _logger.Debug(this, "Export", "Export scene success. (file_name={0})", _exportFileName);
         // 存储信息
         Store();
         _logger.Debug(this, "Import", "Import scene success. (file_name={0})", fileName);
      }

      //============================================================
      // <T>导入材质信息。</T>
      //============================================================
      public void ImportMaterial() {
         Open();
         // 获得显示列表
         FObjects<FDrSceneDisplay> displays = new FObjects<FDrSceneDisplay>();
         displays.Append(_sky.Displays);
         displays.Append(_map.Displays);
         displays.Append(_space.Displays);
         // 压缩文件
         foreach (FDrSceneDisplay display in displays) {
            foreach (FDrSceneMaterial sceneMaterial in display.Materials) {
               // 获得场景
               FDrMaterialGroup group = RContent3dManager.MaterialConsole.FindGroup(sceneMaterial.Name);
               FDrMaterial material = group.SyncMaterial(_themeName);
               _logger.Debug(this, "ImportMaterial", "Import material. (material={0}, theme={1})", group.Code, _themeName);
               // 设置颜色
               material.ColorMin = sceneMaterial.ColorMin;
               material.ColorMax = sceneMaterial.ColorMax;
               material.ColorMerge = sceneMaterial.ColorMerge;
               material.ColorRate = sceneMaterial.ColorRate;
               // 设置透明
               material.AlphaBase = sceneMaterial.AlphaBase;
               material.AlphaRate = sceneMaterial.AlphaRate;
               material.AlphaLevel = sceneMaterial.AlphaLevel;
               material.AlphaMerge = sceneMaterial.AlphaMerge;
               // 设置颜色
               material.AmbientColor.Assign(sceneMaterial.AmbientColor);
               material.DiffuseColor.Assign(sceneMaterial.DiffuseColor);
               material.DiffuseViewColor.Assign(sceneMaterial.DiffuseViewColor);
               material.SpecularColor.Assign(sceneMaterial.SpecularColor);
               material.SpecularBase = sceneMaterial.SpecularBase;
               material.SpecularRate = sceneMaterial.SpecularRate;
               material.SpecularAverage = sceneMaterial.SpecularAverage;
               material.SpecularShadow = sceneMaterial.SpecularShadow;
               material.SpecularViewColor.Assign(sceneMaterial.SpecularViewColor);
               material.SpecularViewBase = sceneMaterial.SpecularViewBase;
               material.SpecularViewRate = sceneMaterial.SpecularViewRate;
               material.SpecularViewAverage = sceneMaterial.SpecularViewAverage;
               material.SpecularViewShadow = sceneMaterial.SpecularViewShadow;
               // 存储资源组
               group.Store();
            }
         }
         _logger.Debug(this, "Export", "Export scene success. (file_name={0})", _exportFileName);
      }
      
      //============================================================
      // <T>序列化数据。</T>
      //============================================================
      public override void Export(ERsExportMode modeCd) {
         // 打开资源
         Open();
         //............................................................
         string exportFileName = RContent3dManager.SenceConsole.ExportDirectory + "\\" + Code + ".ser";
         //............................................................
         // 序列化数据
         FByteFile file = new FByteFile();
         Serialize(file);
         file.SaveFile(exportFileName);
         //............................................................
         // 释放资源
         Dispose();
         _logger.Debug(this, "Export", "Export model success. (file_name={0})", exportFileName);
         //// 打开资源
         //Open();
         ////............................................................
         //string exportDeflateDirectory = RContent3dManager.ContentConsole.ExportDeflateDirectory + "\\p3.sc";
         //string exportLzmaDirectory = RContent3dManager.ContentConsole.ExportLzmaDirectory + "\\p3.sc";
         //string code = Code;
         //if (_techniqueName != "config") {
         //   code = Code + "." + _techniqueName;
         //}
         ////............................................................
         //// 序列化数据
         //FByteStream stream = new FByteStream();
         //Serialize(stream);
         ////............................................................
         //// 保存Deflate数据
         //using (FRsCompressFile file = new FRsCompressFile(ERsCompress.Deflate, stream, RResourceManager.CompressBlockSplit)) {
         //   byte[] data = file.CompressBytes();
         //   RFile.WriteAllBytes(exportDeflateDirectory + "/sc_" + code + ".swf", data);
         //}
         ////............................................................
         //// 保存LZMA数据
         //using (FRsCompressFile file = new FRsCompressFile(ERsCompress.Lzma, stream, RResourceManager.CompressBlockSplit)) {
         //   byte[] data = file.CompressBytes();
         //   RFile.WriteAllBytes(exportLzmaDirectory + "/sc_" + code + ".swf", data);
         //}
         ////............................................................
         //// 释放资源
         //Dispose();
      }

      //============================================================
      // <T>释放内容。</T>
      //============================================================
      public override void Dispose() {
         if(_opened) {
            // 释放属性
            _technique.Dispose();
            _region.Dispose();
            _sky.Dispose();
            _map.Dispose();
            _space.Dispose();
            // 释放内容
            base.Dispose();
         }
      }
   
      //============================================================
      // <T>打开资源内容。</T>
      //============================================================
      public override string ToString() {
         return _label + " (" + _name + ") @ " + _techniqueName;
      }
   }
}
