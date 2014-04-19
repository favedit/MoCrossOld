using MO.Common.Lang;
using MO.Common.System;
using MO.DX.Core.Common;
using MO.DX.Core.Device;
using MO.DX.Core.Resource;

namespace MO.DX.Core.Model
{
   //============================================================
   public class FDxSpatialConsole : FConsole
   {
      protected FObjects<FDxSpatial> _spatials = new FObjects<FDxSpatial>();

      //============================================================
      public FDxSpatialConsole() {
      }

      //============================================================
      public FDxSpatial Create(FDxDevice3D device, string code) {
         // 获得资源定义
         FDxRsTemplate resource = RDxCore.TemplateResourceConsole.Get(code);
         // 加载资源
         FDxSpatial spatial = RDxCore.Factory.Create<FDxSpatial>(EDxObject.ModelSpatial);
         spatial.Device = device;
         spatial.LoadResource(resource);
         _spatials.Push(spatial);
         return spatial;
      }
   }
}
