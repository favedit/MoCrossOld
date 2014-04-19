using MO.Common.Collection;
using MO.Common.System;
using MO.DX.Core.Device;
using MO.DX.Core.Resource;

namespace MO.DX.Core.Model
{
   //============================================================
   public class FDxModelConsole : FConsole
   {
      protected FDictionary<FDxModel> _models = new FDictionary<FDxModel>();

      //============================================================
      public FDxModelConsole() {
      }

      //============================================================
      public FDictionary<FDxModel> Models{
         get{return _models;}
      }

      //============================================================
      public FDxModel Get(FDxDevice3D device, string code) {
         FDxModel model = _models.Find(code);
         if(null == model) {
            // 创建模型
            model = new FDxModel();
            model.Device = device;
            // 加载模型资源
            FDxRsModel rsModel = RDxCore.ModelResourceConsole.Get(code);
            model.LoadResource(rsModel);
            // 存储模型
            _models.Set(code, model);
         }
         return model;
      }
   }
}
