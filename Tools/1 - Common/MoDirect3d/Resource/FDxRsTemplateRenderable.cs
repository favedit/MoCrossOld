using MO.Common.IO;
using MO.Common.Lang;
using MO.DX.Core.Common.Geom;

namespace MO.DX.Core.Resource
{
   //============================================================
   public class FDxRsTemplateRenderable : FObject
   {
      private static ILogger _logger = RLogger.Find(typeof(FDxRsTemplateRenderable));

      protected FDxRsTemplate _template;

      protected int _id;

      protected string _modelCode;

      protected FDxRsModel _model;

      protected string _geometryName;

      protected FDxRsGeometry _geometry;

      protected string _materialCode;

      protected FDxRsTemplateMaterial _material;

      protected SDxMatrix _originMatrix = new SDxMatrix();

      protected SDxMatrix _worldMatrix = new SDxMatrix();

      //============================================================
      public FDxRsTemplateRenderable() {
      }

      //============================================================
      public FDxRsTemplate Template {
         get { return _template; }
         set { _template = value; }
      }

      //============================================================
      public int Id {
         get { return _id; }
      }

      //============================================================
      public string ModelCode {
         get { return _modelCode; }
      }

      //============================================================
      public string GeometryName {
         get { return _geometryName; }
      }

      //============================================================
      public string MaterialCode {
         get { return _materialCode; }
      }

      //============================================================
      public SDxMatrix OriginMatrix {
         get { return _originMatrix; }
      }

      //============================================================
      public SDxMatrix WorldMatrix {
         get { return _worldMatrix; }
      }

      //============================================================
      public void Unserialize(IInput input) {
         // 读取设置
         _id = input.ReadInt32();
         _modelCode = input.ReadString();
         _geometryName = input.ReadString();
         _materialCode = input.ReadString();
         // 存储矩阵
         _originMatrix.Unserialize(input);
         _worldMatrix.Unserialize(input);
         // 读取模型
         _model = RDxCore.ModelResourceConsole.Get(_modelCode);
         _geometry = _model.Mesh.Geometries.Get(_geometryName);
         _material = _template.Materials.Get(_materialCode);
      }
   }
}
