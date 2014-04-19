using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Common;
using MO.Content2d.Core;
using MO.Content3d.Resource.Common;
using MO.Content3d.Resource.Model.Animation;
using MO.Content3d.Resource.Model.Mesh;
using System;

namespace MO.Content3d.Resource.Model
{
   //============================================================
   // <T>模型。</T>
   //============================================================
   public class FDrModel : FDrResource, IDisposable
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FDrModel));

      // 动画
      protected FDrAnimation _animation;

      // 材质集合
      protected FDrModelMaterialCollection _materials;

      // 模型
      protected FDrMesh _mesh;

      // 骨骼
      protected FDrSkeleton _skeleton;

      // 模型名称
      protected string _modelFileName;

      // 网格名称
      protected string _meshFileName;

      // 导出文件名称
      protected string _exportFileName;

      // 导出数据文件名称
      protected string _exportDataFileName;

      //============================================================
      // <T>构造模型。</T>
      //============================================================
      public FDrModel() {
         _typeCode = "md";
         _typeName = EDrResourceType.Model;
         _typeLabel = "Model";
         _materials = new FDrModelMaterialCollection(this);
         _mesh = new FDrMesh(this);
         _skeleton = new FDrSkeleton(this);
         _animation = new FDrAnimation(this);
      }

      //============================================================
      // <T>获得或设置模型名称。</T>
      //============================================================
      public string ModelFileName {
         get { return _modelFileName; }
         set { _modelFileName = value; }
      }

      //============================================================
      // <T>获得或设置网格名称。</T>
      //============================================================
      public string MeshFileName {
         get { return _meshFileName; }
         set { _meshFileName = value; }
      }

      //============================================================
      // <T>获得材质集合。</T>
      //============================================================
      public FDrModelMaterialCollection Materials {
         get { return _materials; }
      }

      //============================================================
      // <T>获得模型。</T>
      //============================================================
      public FDrMesh Mesh {
         get { return _mesh; }
      }

      //============================================================
      // <T>获得骨骼。</T>
      //============================================================
      public FDrSkeleton Skeleton {
         get { return _skeleton; }
      }

      //============================================================
      // <T>获得动画。</T>
      //============================================================
      public FDrAnimation Animation {
         get { return _animation; }
      }

      //============================================================
      // <T>加载模型设置。</T>
      //============================================================
      public void LoadModelSceneConfig(FXmlNode config) {
         // 加载材质信息
         FXmlNode xmaterials = config.Find("MaterialCollection");
         if (xmaterials != null) {
            _materials.LoadModelConfig(xmaterials);
         }
         // 加载骨骼信息
         FXmlNode xskeleton = config.Find("Skeleton");
         if (xskeleton != null) {
            _skeleton.LoadModelConfig(xskeleton);
         }
         // 加载网格信息
         FXmlNode xmesh = config.Find("Mesh");
         if (xmesh != null) {
            _mesh.LoadModelConfig(xmesh);
         }
         // 加载网格信息
         FXmlNode xanimation = config.Find("Animation");
         if (xanimation != null) {
            _animation.LoadModelConfig(xanimation);
         }
      }

      //============================================================
      // <T>数据反序列化文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public void DataUnserializeFile(string fileName) {
         using (FByteFile file = new FByteFile(fileName)) {
            _mesh.DataUnserialize(file);
         }
      }

      //============================================================
      // <T>加载模型配置文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public void LoadModelConfigFile(string fileName) {
         FXmlDocument document = new FXmlDocument();
         document.LoadFile(fileName);
         foreach (FXmlNode node in document.Root.Nodes) {
            switch (node.Name) {
               case "Struct":
                  break;
               case "Scene":
                  LoadModelSceneConfig(node);
                  break;
            }
         }
      }

      //============================================================
      // <T>保存设置信息。</T>
      //
      // @param 配置节点
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         // 存储属性
         xconfig.Set("name", _name);
         xconfig.Set("label", _label);
         // 存储网格信息
         FXmlNode xmesh = xconfig.CreateNode("Mesh");
         _mesh.SaveConfig(xmesh);
         // 存储动画信息
         FXmlNode xanimation = xconfig.CreateNode("Animation");
         _animation.SaveConfig(xanimation);
      }

      //============================================================
      // <T>保存设置信息。</T>
      //
      // @param 配置节点
      //============================================================
      public void SaveInfoConfig(FXmlNode xconfig) {
         // 存储属性
         xconfig.Set("name", _name);
         xconfig.Set("label", _label);
         // 存储网格信息
         FXmlNode xmesh = xconfig.CreateNode("Mesh");
         _mesh.SaveInfoConfig(xmesh);
         // 存储动画信息
         FXmlNode xanimation = xconfig.CreateNode("Animation");
         _animation.SaveConfig(xanimation);
      }
      
      //============================================================
      // <T>加载设置文件。</T>
      //
      // @param fileName 设置名称
      //============================================================
      public void LoadConfigFile(string fileName) {
         FXmlDocument xdocument = new FXmlDocument(fileName);
         FXmlNode xconfig = xdocument.Root.Find("Model");
         // 加载节点信息
         foreach (FXmlNode xnode in xconfig.Nodes) {
            // 加载网格
            if(xnode.IsName("Mesh")) {
               _mesh.LoadConfig(xnode);
            }
            // 加载动画
            if(xnode.IsName("Animation")) {
               _animation.LoadConfig(xnode);
            }
         }
      }

      //============================================================
      // <T>打开设置文件。</T>
      //============================================================
      public void LoadDataFile() {
         if (!_opened) {
            // 加载模型文件
            LoadModelConfigFile(_modelFileName);
            // 加载网格文件
            DataUnserializeFile(_meshFileName);
            // 测试几何体
            foreach (INamePair<FDrGeometry> pair in _mesh.GeometryDictionary) {
               pair.Value.TestScale();
            }
            base.Open();
         }
      }

      //============================================================
      // <T>打开设置文件。</T>
      //============================================================
      public override void Scan() {
         base.Scan();
         // 设置文件名称
         _modelFileName = _directory + "\\model.m3x";
         _meshFileName = _directory + "\\model.msh";
         _exportFileName = _directoryExprot + "\\md_" + Code + ".swf";
         _exportDataFileName = _directoryExprot + ".dt\\md_" + Code + ".swf";
         // 加载设置文件
         if(RFile.Exists(_configFileName)) {
            LoadConfigFile(_configFileName);
         }
      }
      
      //============================================================
      // <T>打开设置文件。</T>
      //============================================================
      public override void Open() {
         if(!_opened) {
            // 加载模型文件
            LoadModelConfigFile(_modelFileName);
            // 加载网格文件
            DataUnserializeFile(_meshFileName);
            // 测试几何体
            foreach (INamePair<FDrGeometry> pair in _mesh.GeometryDictionary) {
               pair.Value.TestScale();
            }
            base.Open();
         }
      }

      //============================================================
      // <T>存储设置文件。</T>
      //============================================================
      public void Store() {
         // 打开内容
         Open();
         // 存储设置
         FXmlDocument xdoc = new FXmlDocument();
         FXmlNode xroot = xdoc.Root;         
         SaveConfig(xroot.CreateNode("Model"));
         xdoc.SaveFile(_configFileName);
         _logger.Debug(this, "SaveConfig", "Save Model config success. (file_name={0})", _configFileName);
         // 释放内容
         Dispose();
      }

      //============================================================
      public void ExportConfig(FXmlNode config) {
         FXmlNode meshNode = config.CreateNode("Mesh");
         _mesh.ExportConfig(meshNode);
      }

      //============================================================
      public void ExportConfigFile(string fileName) {
         FXmlDocument document = new FXmlDocument();
         ExportConfig(document.Root);
         document.SaveFile(fileName);
      }

      //============================================================
      // <T>导出内容信息。</T>
      //============================================================
      public void ExportInfo() {
         string fileName = _directory + "\\model.info.xml";
         Open();
         FXmlDocument xdoc = new FXmlDocument();
         SaveInfoConfig(xdoc.Root);
         xdoc.SaveFile(fileName);
         _logger.Debug(this, "Export", "Export model info success. (file_name={0})", fileName);
         Dispose();
      }

      //============================================================
      // <T>序列化数据内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public override void Serialize(IOutput output) {
         //output.WriteInt32(CodeNumber);
         //output.WriteInt32(0);
         output.WriteString(Code);
         // 输出网格
         _mesh.Serialize(output);
         // 输出骨骼
         _skeleton.Serialize(output);
         // 输出动画
         _animation.Serialize(output);
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
         //string exportFileName = RContent3dManager.ModelConsole.ExportDirectory + "\\" + CodeNumber + ".ser";
         string exportFileName = RContent3dManager.ModelConsole.ExportDirectory + "\\" + Code + ".ser";
         //............................................................
         // 序列化数据
         FByteFile file = new FByteFile();
         Serialize(file);
         file.SaveFile(exportFileName);
         //............................................................
         // 释放资源
         Dispose();
         _logger.Debug(this, "Export", "Export model success. (file_name={0})", exportFileName);
      }

      //============================================================
      // <T>释放内容。</T>
      //============================================================
      public override void Dispose() {
         if (_opened) {
            //_materials.Dispose();
            //_mesh.Dispose();
            //_skeleton.Dispose();
            //_animation.Dispose();
            base.Dispose();
         }
      }
   }
}
