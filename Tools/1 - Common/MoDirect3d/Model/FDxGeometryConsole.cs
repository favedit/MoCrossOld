using MO.Common.System;
using MO.DX.Core.Common;
using MO.DX.Core.Device;

namespace MO.DX.Core.Model
{
   //============================================================
   public class FDxGeometryConsole : FConsole
   {
      //============================================================
      public FDxGeometryConsole() {
      }

      //============================================================
      public FDxGeometry Create(FDxDevice3D device, string modelCode, string geometryCode) {
         // 创建几何体
         FDxGeometry geometry = RDxCore.Factory.Create<FDxGeometry>(EDxObject.ModelGeometry);
         geometry.Device = device;
         // 加载资源
         FDxModel model = RDxCore.ModelConsole.Get(device, modelCode);
         FDxModelGeometry modelGeometry = model.Geometries.Get(geometryCode);
         geometry.ModelGeometry = modelGeometry;
         geometry.Setup();
         return geometry;
      }
   }
}
