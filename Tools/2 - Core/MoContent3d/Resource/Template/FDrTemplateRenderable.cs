using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Core;
using MO.Content3d.Common;
using MO.Content3d.Resource.Common;
using MO.Content3d.Resource.Material;
using MO.Content3d.Resource.Model;
using MO.Content3d.Resource.Model.Mesh;

namespace MO.Content3d.Resource.Template
{
   //============================================================
   // <T>模板渲染。</T>
   //============================================================
   public class FDrTemplateRenderable : FObject
   {
      // 模板对象
      protected FDrTemplate _template;

      // 编号
      protected int _id;

      // 名称
      protected string _name;

      // 动态配置
      protected int _optionDynamic = EDrFlag.Inherit;

      // 是否可合并
      protected int _optionMerge = EDrFlag.Inherit;

      // 是否可选中
      protected int _optionSelect = EDrFlag.Inherit;

      // 是否可显示
      protected int _optionVisible = EDrFlag.Inherit;

      // 是否地面
      protected int _optionGround = EDrFlag.Inherit;

      // 是否骨骼缩放
      protected int _optionBoneScale = EDrFlag.Inherit;

      // 是否实体化
      protected int _optionInstnaced = EDrFlag.Inherit;

      // 实体个数
      protected int _instanceCount = 1;

      // 模型名称
      protected string _modelName;

      // 模型对象
      protected FDrModel _model;

      // 几何名称
      protected string _geometryName;

      // 几何体对象
      protected FDrGeometry _geometry;

      // 轮廓
      protected SFloatOutline3 _outline = new SFloatOutline3();

      // 材质名称
      protected string _materialName;

      // 光源名称
      protected string _lightMapName;

      // 材质对象
      protected FDrMaterialGroup _material;

      // 原始矩阵
      protected SFloatMatrix _originMatrix = new SFloatMatrix();

      // 矩阵
      protected SFloatMatrix _matrix = new SFloatMatrix();

      // 光照贴图
      protected FDrTemplateLightMap _lightMap = new FDrTemplateLightMap();

      //============================================================
      // <T>模板渲染。</T>
      //============================================================
      public FDrTemplateRenderable() {
      }

      //============================================================
      public FDrTemplate Template {
         get { return _template; }
         set { _template = value; }
      }

      //============================================================
      public int Id {
         get { return _id; }
         set { _id = value; }
      }

      //============================================================
      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      //============================================================
      // <T>获得或设置动态配置。</T>
      //============================================================
      public int OptionDynamic {
         get { return _optionDynamic; }
         set { _optionDynamic = value; }
      }

      //============================================================
      // <T>获得或设置是否可合并。</T>
      //============================================================
      public int OptionMerge {
         get { return _optionMerge; }
         set { _optionMerge = value; }
      }

      //============================================================
      // <T>获得或设置是否可选中。</T>
      //============================================================
      public int OptionSelect {
         get { return _optionSelect; }
         set { _optionSelect = value; }
      }

      //============================================================
      // <T>获得或设置是否可见。</T>
      //============================================================
      public int OptionVisible {
         get { return _optionVisible; }
         set { _optionVisible = value; }
      }

      //============================================================
      // <T>获得或设置是否可地面。</T>
      //============================================================
      public int OptionGround {
         get { return _optionGround; }
         set { _optionGround = value; }
      }

      //============================================================
      // <T>获得或设置是否实体化。</T>
      //============================================================
      public int OptionInstnaced {
         get { return _optionInstnaced; }
         set { _optionInstnaced = value; }
      }
      
      //============================================================
      public string DisplayLabel {
         get { return _modelName + " - " + _geometryName + " (" + _materialName + ")"; }
      }

      //============================================================
      public string ModelCode {
         get {
            string code = _modelName.Replace('\\', '.');
            if (code.StartsWith(".")) {
               code = code.Substring(1);
            }
            return code;
         }
      }

      //============================================================
      public string ModelName {
         get { return _modelName; }
         set { _modelName = value; }
      }

      //============================================================
      // <T>获得模型对象。</T>
      //============================================================
      public FDrModel Model {
         get { return _model; }
      }

      //============================================================
      public string GeometryName {
         get { return _geometryName; }
         set { _geometryName = value; }
      }

      //============================================================
      // <T>获得轮廓。</T>
      //============================================================
      public SFloatOutline3 Outline {
         get { return _outline; }
      }
      
      //============================================================
      public string MaterialCode {
         get { return RDrUtil.FormatPathToCode(_materialName); }
      }

      //============================================================
      public string MaterialName {
         get { return _materialName; }
         set { _materialName = value; }
      }

      //============================================================
      public FDrMaterialGroup Material {
         get { return _material; }
      }

      //============================================================
      public string LightMapName {
         get { return _lightMapName; }
         set { _lightMapName = value; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         // 读取设置
         _id = xconfig.GetInteger("id", 0);
         _name = xconfig.Get("name", _name);
         _modelName = xconfig.Nvl("model");
         _modelName = _modelName.Replace('/', '\\');
         _model = RContent3dManager.ModelConsole.Find(_modelName);
         if(null == _model) {
            RMoCore.TrackConsole.Write(this, "LoadConfig", "Model is not exists in tempalte. (template={0}, model={1})", _template.Name, _modelName);
            return;
         }
         _geometryName = xconfig.Nvl("geometry");
         _geometry = _model.Mesh.Find(_geometryName);
         if(null == _geometry) {
            RMoCore.TrackConsole.Write(this, "LoadConfig", "Model geometry is not exists. (template={0}, model={1}, geometry={2})", _template.Name, _modelName, _geometryName);
         } else {
            _optionSelect = _geometry.OptionSelect;
            _optionGround = _geometry.OptionGround;
            _optionBoneScale = _geometry.OptionBoneScale;
            _optionInstnaced = _geometry.OptionInstanced;
            _instanceCount = _geometry.InstanceCount;
            _outline.min.Assign(_geometry.OutlineMin);
            _outline.max.Assign(_geometry.OutlineMax);
         }
         _materialName = xconfig.Nvl("material");
         _materialName = _materialName.Replace('/', '\\');
         _material = RContent3dManager.MaterialConsole.FindGroup(_materialName);
         if (null == _material) {
            RMoCore.TrackConsole.Write(this, "LoadConfig", "Material is not exists in tempalte. (template={0}, model={1}, material={2})", _template.Name, _modelName, _materialName);
         } else {
            if (!_material.Materials.IsEmpty) {
               FDrMaterial material = _material.Materials.First;
               _optionDynamic = material.OptionDynamic;
               _optionMerge = material.OptionMerge;
            }
         }
         _lightMapName = xconfig.Get("light_map", "");
         // 读取选项
         if (xconfig.Contains("option_visible")) {
            _optionVisible = xconfig.GetInteger("option_visible");
         }
         // 读取矩阵
         FXmlNode xmatrix = xconfig.Find("Matrix");
         if (null != xmatrix) {
            _matrix.LoadSimpleConfig(xmatrix);
         }
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         // 存储设置
         xconfig.Set("id", _id);
         xconfig.Set("name", _name);
         xconfig.Set("model", _modelName);
         xconfig.Set("geometry", _geometryName);
         xconfig.Set("material", _materialName);
         xconfig.Set("light_map", _lightMapName);
         // 存储设置
         xconfig.Set("option_select", _optionSelect);
         xconfig.Set("option_visible", _optionVisible);
         xconfig.Set("option_ground", _optionGround);
         xconfig.Set("option_bone_scale", _optionBoneScale);
         xconfig.Set("option_instnaced", _optionInstnaced);
         // 存储实体
         xconfig.Set("instnace_count", _instanceCount);
         // 存储矩阵
         if (_matrix.IsChanged) {
            _matrix.SaveSimpleConfig(xconfig.CreateNode("Matrix"));
         }
      }

      //============================================================
      public void Serialize(IOutput output) {
         //output.WriteInt32(_model.CodeNumber);
         output.WriteString(_model.Code);
         output.WriteInt16((short)_geometry.Index);
         //output.WriteInt32(_material.CodeNumber);
         output.WriteString(_material.Code);
         //// 存储名称
         //output.WriteInt32(_id);
         //output.WriteString(_name);
         //output.WriteString(ModelCode);
         //output.WriteString(_geometryName);
         //output.WriteString(MaterialCode);
         //output.WriteString(_lightMapName);
         // 存储设置
         output.WriteInt8((sbyte)_optionInstnaced);
         output.WriteUint8((byte)_instanceCount);
         output.WriteInt8((sbyte)_optionDynamic);
         output.WriteInt8((sbyte)_optionMerge);
         output.WriteInt8((sbyte)_optionBoneScale);
         output.WriteInt8((sbyte)_optionSelect);
         output.WriteInt8((sbyte)_optionVisible);
         output.WriteInt8((sbyte)_optionGround);
         // 存储矩阵
         _matrix.Serialize(output);
      }

      //============================================================
      public void FixMatrix(FDrMatrix matrix) {
         _originMatrix.tx = matrix.Translation.X;
         _originMatrix.ty = matrix.Translation.Y;
         _originMatrix.tz = matrix.Translation.Z;
         _originMatrix.rx = matrix.Euler.X * RFloat.DegreeRate;
         _originMatrix.ry = matrix.Euler.Y * RFloat.DegreeRate;
         _originMatrix.rz = matrix.Euler.Z * RFloat.DegreeRate;
         _originMatrix.sx = matrix.Scale.X;
         _originMatrix.sy = matrix.Scale.Y;
         _originMatrix.sz = matrix.Scale.Z;
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadExportConfig(FXmlNode xconfig) {
         FXmlNode xmatrix = xconfig.Find("Matrix");
         FXmlNode xworldmatrix = xmatrix.Find("WorldMatrix");
         _matrix.LoadSimpleDegreeConfig(xworldmatrix);
      }
   }
}
