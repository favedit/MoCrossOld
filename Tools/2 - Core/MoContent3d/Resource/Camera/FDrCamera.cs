using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d;
using MO.Content2d.Common;
using MO.Content2d.Core;
using MO.Core;
using MO.Content3d.Common;
using MO.Content3d.Resource.Common;
using MO.Content3d.Resource.Material;
using MO.Content3d.Resource.Model;
using MO.Content3d.Resource.Model.Mesh;
using MO.Content3d.Resource.Template;
using MO.Content3d.Resource.Texture;
using MO.Content3d.Util.Light;
using System;
using System.IO;

namespace MO.Content3d.Resource.Camera
{
   //============================================================
   // <T>模板定义。</T>
   //============================================================
   public class FDrCamera : FDrResource, IDisposable
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FDrCamera));

      // 加载配置
      protected int _optionLoaded = EDrFlag.Inherit;

      // 选择配置
      protected int _optionSelect = EDrFlag.Inherit;

      // 地表配置
      protected int _optionGround = EDrFlag.Inherit;

      // 合并顶点配置
      protected int _optionMergeVertex = EDrFlag.Inherit;

      // 合并材质配置
      protected int _optionMergeMaterial = EDrFlag.Inherit;

      // 光照配置
      protected int _optionLightMap = EDrFlag.Inherit;

      // 轮廓
      protected SFloatOutline3 _outline = new SFloatOutline3();

      // 材质集合
      protected FVector<FDrMaterialGroup> _materials = new FVector<FDrMaterialGroup>();

      // 渲染集合
      protected FVector<FDrTemplateRenderable> _renderables = new FVector<FDrTemplateRenderable>();

      // 引用集合
      protected FVector<FDrTemplateReference> _references = new FVector<FDrTemplateReference>();

      // 动画
      protected FDrModel _model;

      // 动画
      protected FDrTemplateAnimation _animation = new FDrTemplateAnimation();

      protected string _exprotTemplatePath;

      protected string _exprotScenePath;

      protected string _exportFileName;

      protected string _exportDataFileName;

      //============================================================
      // <T>构造模板定义。</T>
      //============================================================
      public FDrCamera() {
         _typeCode = "tp";
         _typeName = EDrResourceType.Template;
         _typeLabel = "Template";
      }

      //============================================================
      // <T>设置或获得加载配置。</T>
      //============================================================
      public int OptionLoaded {
         get { return _optionLoaded; }
         set { _optionLoaded = value; }
      }

      //============================================================
      // <T>获得或设置选中配置。</T>
      //============================================================
      public int OptionSelect {
         get { return _optionSelect; }
         set { _optionSelect = value; }
      }

      //============================================================
      // <T>获得或设置地表配置。</T>
      //============================================================
      public int OptionGround{
         get { return _optionGround; }
         set { _optionGround = value; }
      }

      //============================================================
      // <T>设置或获得合并顶点配置。</T>
      //============================================================
      public int OptionMergeVertex {
         get { return _optionMergeVertex; }
         set { _optionMergeVertex = value; }
      }

      //============================================================
      // <T>设置或获得合并材质配置。</T>
      //============================================================
      public int OptionMergeMaterial {
         get { return _optionMergeMaterial; }
         set { _optionMergeMaterial = value; }
      }

      //============================================================
      // <T>设置或获得光照配置。</T>
      //============================================================
      public int OptionLightMap {
         get { return _optionLightMap; }
         set { _optionLightMap = value; }
      }

      //============================================================
      // <T>获得轮廓。</T>
      //============================================================
      public SFloatOutline3 Outline {
         get { return _outline; }
      }

      //============================================================
      // <T>获得材质集合。</T>
      //============================================================
      public FVector<FDrMaterialGroup> Materials {
         get { return _materials; }
      }

      //============================================================
      // <T>获得渲染集合。</T>
      //============================================================
      public FVector<FDrTemplateRenderable> Renderables {
         get { return _renderables; }
      }

      //============================================================
      // <T>获得动画。</T>
      //============================================================
      public FDrTemplateAnimation Animation {
         get { return _animation; }
      }

      //============================================================
      public string ExportTemplatePath {
         get { return _exprotTemplatePath; }
         set { _exprotTemplatePath = value; }
      }

      //============================================================
      public string ExportScenePath {
         get { return _exprotScenePath; }
         set { _exprotScenePath = value; }
      }

      //============================================================
      // <T>根据模块名称和几何体名称，获得渲染对象。</T>
      //
      // @param modelName 模块名称
      // @param geometryName 几何体名称
      // @return 渲染对象
      //============================================================
      public FDrTemplateRenderable SyncRenderable(string modelName, string geometryName) {
         // 查询渲染对象
         if(!_renderables.IsEmpty) {
            foreach(FDrTemplateRenderable renderable in _renderables) {
               if(renderable.ModelName.Equals(modelName) && renderable.GeometryName.Equals(geometryName)) {
                  return renderable;
               }
            }
         }
         // 新建渲染对象
         FDrTemplateRenderable newRenderable = new FDrTemplateRenderable();
         newRenderable.ModelName = modelName;
         newRenderable.GeometryName = geometryName;
         _renderables.Push(newRenderable);
         return newRenderable;
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         _optionLoaded = xconfig.GetInteger("option_loaded", EDrFlag.Inherit);
         _optionSelect = xconfig.GetInteger("option_select", EDrFlag.Inherit);
         _optionGround = xconfig.GetInteger("option_ground", EDrFlag.Inherit);
         if (xconfig.Contains("option_merge")) {
            _optionMergeVertex = xconfig.GetInteger("option_merge");
         }
         if (xconfig.Contains("option_merge_vertex")) {
            _optionMergeVertex = xconfig.GetInteger("option_merge_vertex");
         }
         _optionMergeMaterial = xconfig.GetInteger("option_merge_material", EDrFlag.Inherit);
         _optionLightMap = xconfig.GetInteger("option_light_map", EDrFlag.Inherit);
         //............................................................
         //............................................................
         // 加载引用列表
         FXmlNode xreferences = xconfig.Find("References");
         if(null != xreferences) {
            foreach(FXmlNode xreference in xreferences.Nodes) {
               if(xreference.IsName("Reference")) {
                  FDrTemplateReference reference = new FDrTemplateReference();
                  reference.LoadConfig(xreference);
                  _references.Push(reference);
               }
            }
         }
         //............................................................
         // 加载动画列表
         FXmlNode xanimation = xconfig.Find("Animation");
         if (null != xanimation) {
            _animation.Model = _model;
            _animation.LoadConfig(xanimation);
         }
         //............................................................
         // 材质集合排序
         if(!_materials.IsEmpty) {
            _materials.Sort(_materials.First);
         }
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         // 设置信息
         xconfig.Set("name", _name);
         xconfig.Set("label", _label);
         xconfig.Set("option_loaded", _optionLoaded);
         xconfig.Set("option_select", _optionSelect);
         xconfig.Set("option_ground", _optionGround);
         xconfig.Set("option_merge_vertex", _optionMergeVertex);
         xconfig.Set("option_merge_material", _optionMergeMaterial);
         xconfig.Set("option_light_map", _optionLightMap);
         //............................................................
         // 存储渲染列表
         if(!_renderables.IsEmpty) {
            FXmlNode xrenderables = xconfig.CreateNode("Renderables");
            int count = _renderables.Count;
            xrenderables.Set("count", count);
            for(int n = 0; n < count; n++) {
               FDrTemplateRenderable renderable = _renderables[n];
               if(renderable.Id == 0) {
                  renderable.Id = n + 1;
               }
               renderable.SaveConfig(xrenderables.CreateNode("Renderable"));
            }
         }
         //............................................................
         // 存储引用列表
         if(!_references.IsEmpty) {
            FXmlNode xreferences = xconfig.CreateNode("References");
            xreferences.Set("count", _references.Count);
            foreach(FDrTemplateReference reference in _references) {
               reference.SaveConfig(xreferences.CreateNode("Renderable"));
            }
         }
         //............................................................
         // 存储引动画列表
         FXmlNode xanimation = xconfig.CreateNode("Animation");
         _animation.SaveConfig(xanimation);
      }

      //============================================================
      // <T>打开材质信息。</T>
      //============================================================
      public override void Scan() {
         base.Scan();
         _exportFileName = RContent3dManager.TemplateConsole.ExportDirectory + "\\tp_" + Code + ".swf";
         _exportDataFileName = RContent3dManager.TemplateConsole.ExportDirectory + ".dt\\tp_" + Code + ".swf";
         // 加载设置文件
         if(RFile.Exists(_configFileName)) {
            LoadConfigFile(_configFileName);
         }
      }

      //============================================================
      // <T>打开模板信息。</T>
      //============================================================
      public override void Open() {
         if(!_opened) {
            base.Open();
         }
      }

      //============================================================
      public void LoadConfigFile(string fileName) {
         FXmlDocument xdoc = new FXmlDocument(fileName);
         FXmlNode xroot = xdoc.Root;
         FXmlNode xtemplate = xroot.Find("Template");
         if(null != xtemplate) {
            LoadConfig(xtemplate);
         }
      }

      //============================================================
      public void Store() {
         // 打开内容
         Open();
         // 存储设置
         FXmlDocument xdoc = new FXmlDocument();
         FXmlNode xroot = xdoc.Root;
         SaveConfig(xroot.CreateNode("Template"));
         xdoc.SaveFile(_configFileName);
         _logger.Debug(this, "SaveConfigFile", "Save template config success. (file_name={0})", _configFileName);
         // 释放内容
         Dispose();
      }

      //============================================================
      public override void Serialize(IOutput output) {
         base.Serialize(output);
         // 存储属性
         output.WriteInt8((sbyte)_optionLoaded);
         output.WriteInt8((sbyte)_optionSelect);
         output.WriteInt8((sbyte)_optionGround);
         output.WriteInt8((sbyte)_optionMergeVertex);
         output.WriteInt8((sbyte)_optionMergeMaterial);
         output.WriteInt8((sbyte)_optionLightMap);
         //............................................................
         // 统计材质次数
         FDictionary<int> materialsCount = new FDictionary<int>();
         foreach (FDrTemplateRenderable renderable in  _renderables){
            int materialCount = 0;
            if(materialsCount.Contains(renderable.MaterialCode)){
               materialCount = materialsCount.Get(renderable.MaterialCode);
            }
            materialCount++;
            materialsCount.Set(renderable.MaterialCode, materialCount);
         }
         //............................................................
         // 存储渲染列表
         int count = _renderables.Count;
         output.WriteInt16((short)count);
         for (int n = 0; n < count; n++) {
            FDrTemplateRenderable renderable = _renderables[n];
            int materialCount = materialsCount.Get(renderable.MaterialCode);
            if (1 == materialCount) {
               renderable.OptionMerge = EDrFlag.No;
            }
            renderable.Id = n + 1;
            renderable.Serialize(output);
         }
         //............................................................
         // 存储动画列表
         if(_animation.IsEmpty) {
            output.WriteBool(false);
         } else {
            output.WriteBool(true);
            _animation.Serialize(output);
         }
      }

      //============================================================
      // <T>序列化数据。</T>
      //============================================================
      public void Import() {
         string fileName = _directory + "\\model.m3x";
         FXmlDocument xdoc = new FXmlDocument(fileName);
         FXmlNode xscene = xdoc.Root.Find("Scene");
         // 重新导入所有集合体(可以重复)
         _renderables.Clear();
         int n = 0;
         FXmlNode xmesh = xscene.Find("Mesh");
         foreach(FXmlNode xgeometry in xmesh.Nodes) {
            if(xgeometry.IsName("Geometry")) {
               string name = xgeometry.Get("name");
               string[] items = name.Split('|');
               if(items.Length >= 2) {
                  // 获得模型和集合体对象
                  string modelCode = items[0];
                  string geometryName = items[1];
                  FDrModel model = RContent3dManager.ModelConsole.Find(modelCode);
                  if(null == model) {
                     RMoCore.TrackConsole.Write(this, "Import", "Model is not exists. (model={0})", modelCode);
                     continue;
                  }
                  model.Open();
                  FDrGeometry geometry = model.Mesh.Find(geometryName);
                  if (null == geometry) {
                     RMoCore.TrackConsole.Write(this, "Import", "Model geometry is not exists. (model={0}, geometry={1})", modelCode, geometryName);
                     continue;
                  }
                  // 新建渲染对象
                  FDrTemplateRenderable renderable = new FDrTemplateRenderable();
                  renderable.Id = n++;
                  renderable.Name = name;
                  renderable.LightMapName = RString.Right(name, "|");
                  renderable.ModelName = model.Name;
                  renderable.GeometryName = geometry.Name;
                  renderable.MaterialName = geometry.MaterialName;
                  renderable.LoadExportConfig(xgeometry);
                  _renderables.Push(renderable);
               }
            }
         }
         _logger.Debug(this, "Import", "Import template success. (file_name={0})", fileName);
         Store();
      }

      //============================================================
      // <T>根据指定模式导出数据。</T>
      //
      // @param modeCd 导出模式
      //============================================================
      public override void Export(ERsExportMode modeCd) {
         // 打开资源
         Open();
         //............................................................
         // 序列化数据
         FByteStream stream = new FByteStream();
         Serialize(stream);
         //............................................................
         //// 保存数据
         //using (FRsCompressFile file = new FRsCompressFile(ERsCompress.Lzma, stream, RContent2dManager.CompressBlockSplit)) {
         //   byte[] data = file.CompressBytes();
         //   string fileName1 = RContent3dManager.TemplateConsole.ExportDirectory + "/tp_" + Code + ".swf";
         //   File.WriteAllBytes(fileName1, data);
         //   string fileName2 = RContent3dManager.ResourceConsole.ExportResourceDirectory + "/tp_" + Code + ".stg";
         //   File.WriteAllBytes(fileName2, data);
         //   _logger.Debug(this, "Export", "Export template success. (file_name={0})", fileName1);
         //}
         //............................................................
         // 释放资源
         Dispose();
      }

      //============================================================
      // <T>导出光照信息。</T>
      //============================================================
      public void ExportLight() {
         FDrModel model = new FDrModel();
         model.Directory = _directory;
         model.ModelFileName = _directory + "/model.m3x";
         model.MeshFileName = _directory + "/model.msh";
         model.LoadDataFile();
         // 存储模板文件
         FDrLightMap map = new FDrLightMap();
         map.Export3d(model);
         _logger.Debug(this, "Export", "Export template success. (file_name={0})", _exportFileName);
      }

      //============================================================
      // <T>导出光照信息。</T>
      //============================================================
      public void ExportLightResource() {
         FDrLightTexture texture = new FDrLightTexture();
         texture.Directory = _directory;
         foreach (FDrTemplateRenderable renderable in _renderables){
            texture.Name = renderable.LightMapName;
            texture.LoadPicture(_directory + "/" + renderable.LightMapName + ".png");
         }
         texture.ExportFile(@"D:\WP-Webroot\3d-web\tx\tx_test.npt3.swf");
         _logger.Debug(this, "Export", "Export template success. (file_name={0})", _exportFileName);
      }
      
      //============================================================
      public void ExportScene() {
         FXmlDocument xdoc = new FXmlDocument();
         FXmlNode xscene = xdoc.Root;
         xscene.Name = "Scene";
         // 创建区域
         FXmlNode xregion = xscene.CreateNode("Region");
         xregion.Set("technique", "shadow");
         // 创建相机
         FXmlNode xcamera = xregion.CreateNode("Camera");
         xcamera.Set("type", "perspective");
         FXmlNode xviewport = xcamera.CreateNode("Viewport");
         xviewport.Set("angle", "45");
         xviewport.Set("znear", "1");
         xviewport.Set("zfar", "500");
         FXmlNode xposition = xcamera.CreateNode("Position");
         xposition.Set("x", "0");
         xposition.Set("y", "0");
         xposition.Set("z", "0");
         FXmlNode xtarget = xcamera.CreateNode("Target");
         xtarget.Set("x", "0");
         xtarget.Set("y", "0");
         xtarget.Set("z", "100");
         // 创建光源
         FXmlNode xlight = xregion.CreateNode("Light");
         xlight.Set("type", "directional");
         // 创建区域
         FXmlNode xspace = xscene.CreateNode("Space");
         FXmlNode xnode = xspace.CreateNode("Node");
         xnode.Set("valid", "Y");
         xnode.Set("type", "still");
         FXmlNode xdisplay = xnode.CreateNode("Display");
         xdisplay.Set("valid", "Y");
         xdisplay.Set("src", Name);
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
