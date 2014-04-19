using MO.DX.Core.Common;
using MO.DX.Core.Device;
using MO.DX.Core.Resource;

namespace MO.DX.Core.Model
{
   //============================================================
   public class FDxModelMaterial : FDxMaterial
   {
      protected string _name;

      //============================================================
      public FDxModelMaterial() {
      }

      //============================================================
      public string Name {
         get { return _name; }
      }

      //============================================================
      public void LoadResource(FDxRsTemplateMaterial resource) {
         _name = resource.Code;
         // 加载纹理
         int textureCount = resource.Textures.Count;
         for(int n = 0; n < textureCount; n++) {
            //FDxRsTemplateTexture rsTexture = resource.Textures[n];
            //FDxModelTexture texture = new FDxModelTexture();
            //texture.Device = _device;
            //texture.LoadResource(rsTexture);
            //_textures.Push(texture);
         }
      }
   }
}
