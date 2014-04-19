using MO.Common.Collection;
using MO.DX.Core.Common;
using MO.DX.Core.Resource;

namespace MO.DX.Core.Model
{
   //============================================================
   public class FDxModel : FDxDisplay
   {
      protected string _name;

      protected FDictionary<FDxModelGeometry> _geometries = new FDictionary<FDxModelGeometry>();

      //============================================================
      public FDxModel() {
      }

      //============================================================
      public FDictionary<FDxModelGeometry> Geometries {
         get { return _geometries; }
      }

      //============================================================
      public void LoadResource(FDxRsModel resource) {
         int count = resource.Mesh.Geometries.Count;
         for(int n = 0; n < count; n++) {
            // 创建几何体
            FDxModelGeometry geometry = new FDxModelGeometry();
            geometry.Device = _device;
            // 加载资源
            FDxRsGeometry geometryResource = resource.Mesh.Geometries[n];
            geometry.LoadResource(geometryResource);
            // 存储对象
            _geometries.Set(geometry.Name, geometry);
            _renderables.Push(geometry);
         }
      }
   }
}
