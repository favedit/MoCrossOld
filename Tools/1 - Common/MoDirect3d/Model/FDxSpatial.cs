using MO.Common.Collection;
using MO.Common.Lang;
using MO.DX.Core.Common;
using MO.DX.Core.Resource;

namespace MO.DX.Core.Model
{
   //============================================================
   public class FDxSpatial : FDxDisplay
   {
      private static ILogger _logger = RLogger.Find(typeof(FDxSpatial));

      protected FDictionary<FDxSpatialMaterial> _materials = new FDictionary<FDxSpatialMaterial>();

      protected FObjects<FDxGeometry> _geometries = new FObjects<FDxGeometry>();

      //============================================================
      public FDxSpatial() {
      }

      //============================================================
      public FDictionary<FDxSpatialMaterial> Materials {
         get { return _materials; }
      }

      //============================================================
      public void DoScale(float rate) {
         foreach(FDxGeometry geometry in _geometries){
            geometry.Matrix.AppendScaleAll(rate);
            geometry.Matrix.UpdateNative();
         }
      }

      //============================================================
      public void DoRotationY(float angle) {
         foreach(FDxGeometry geometry in _geometries) {
            geometry.Matrix.AppendRotationY(angle);
            geometry.Matrix.UpdateNative();
         }
      }

      //============================================================
      public void LoadResource(FDxRsTemplate resource) {
         //............................................................
         // 加载材质列表
         int materialCount = resource.Materials.Count;
         for(int n = 0; n < materialCount; n++) {
            // 创建几何体
            FDxRsTemplateMaterial rsMaterial = resource.Materials[n];
            FDxSpatialMaterial material = new FDxSpatialMaterial();
            material.Device = _device;
            material.LoadResource(rsMaterial);
            // 增加渲染对象
            _materials.Set(material.Name, material);
         }
         //............................................................
         // 加载渲染列表
         int renderableCount = resource.Renderables.Count;
         for(int n = 0; n < renderableCount; n++) {
            // 创建几何体
            FDxRsTemplateRenderable rsRenderable = resource.Renderables[n];
            FDxGeometry geometry = RDxCore.GeometryConsole.Create(_device, rsRenderable.ModelCode, rsRenderable.GeometryName);
            geometry.Display = this;
            geometry.LoadRenderable(rsRenderable);
            _geometries.Push(geometry);
            // 设置材质
            FDxSpatialMaterial material = _materials.Get(rsRenderable.MaterialCode);
            geometry.ModelMaterial = material;
            // 增加渲染对象
            _renderables.Push(geometry);
         }
      }
   }
}
